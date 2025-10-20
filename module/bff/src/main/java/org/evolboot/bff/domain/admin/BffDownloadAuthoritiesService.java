package org.evolboot.bff.domain.admin;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.bff.domain.admin.dto.AuthorityOption;
import org.evolboot.bff.domain.admin.dto.AuthorityTree;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.AuthorityModule;
import org.evolboot.core.annotation.AuthorityResource;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.IdUtil;
import org.evolboot.storage.StorageConstant;
import org.evolboot.storage.domain.blob.BlobAppService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

import static org.evolboot.security.api.access.AccessAuthorities.*;

/**
 * @author evol
 */
@Service
@Slf4j
public class BffDownloadAuthoritiesService {

    private final BlobAppService blobAppService;

    public BffDownloadAuthoritiesService(BlobAppService blobAppService) {
        this.blobAppService = blobAppService;
    }

    @SneakyThrows
    public String download() {
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation("org.evolboot", AdminClient.class);
        List<Authorities> rows = Lists.newArrayList();
        classes.forEach(clazz -> {
            Method[] methods = ReflectUtil.getMethods(clazz);
            for (Method method : methods) {
                Operation operation = method.getAnnotation(Operation.class);
                PreAuthorize preAuthorize = method.getAnnotation(PreAuthorize.class);
                String url = getUrl(clazz, method);
                if (preAuthorize != null) {
                    Authorities authorities = new Authorities();
                    authorities.setTitle(operation.summary());
                    authorities.setPerm(preAuthorize.value()
                            .replace(HAS_ROLE_SUPER_ADMIN, "")
                            .replace(HAS_ROLE_STAFF, "")
                            .replace(OR, "")
                            .replace(AUTHORITY_PREFIX, "")
                            .replace(AUTHORITY_SUFFIX, ""));
                    authorities.setUrl(url);
                    rows.add(authorities);
                }
            }
        });
        String defaultBaseDir = System.getProperty("java.io.tmpdir");
        String filename = IdUtil.nextId() + "-authorities.xlsx";
        File file = new File(defaultBaseDir + "/" + filename);
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter(file);
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(3, "权限");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
        String authorities = blobAppService.createFile(new FileInputStream(file), filename, 1024L, StorageConstant.DEFAULT_OWNER_USER_ID).getUrl();
        return authorities;

    }

    @Getter
    @Setter
    public static class Authorities {
        private String title;
        private String perm;
        private String url;
    }


    public String getUrl(Class<?> clazz, Method method) {
        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        PutMapping putMapping = method.getAnnotation(PutMapping.class);
        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
        GetMapping getMapping = method.getAnnotation(GetMapping.class);
        RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);


        String prefix = getUrl(requestMapping.value(), requestMapping.path());
        String url = null;
        if (postMapping != null) {
            url = getUrl(postMapping.value(), postMapping.path());
        }

        if (null == url && putMapping != null) {
            url = getUrl(putMapping.value(), putMapping.path());
        }

        if (null == url && deleteMapping != null) {
            url = getUrl(deleteMapping.value(), deleteMapping.path());
        }

        if (null == url && getMapping != null) {
            url = getUrl(getMapping.value(), getMapping.path());
        }

        return prefix + url;
    }

    public String getUrl(String[] value, String[] path) {
        if (!ExtendObjects.isEmpty(value)) {
            return value[0];
        }
        if (!ExtendObjects.isEmpty(path)) {
            return path[0];
        }
        return null;
    }

    /**
     * 获取所有可用权限列表
     */
    public List<AuthorityOption> getAvailableAuthorities(final Class<? extends Annotation> annotationClass) {
        // 1. 从 *AccessAuthorities 扫描，获取元数据
        Map<String, AuthorityMeta> metaMap = scanFromAccessAuthorities();

        // 2. 从 Controller 扫描，获取 URL 和标题
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation("org.evolboot", annotationClass);
        List<AuthorityOption> authorities = new ArrayList<>();

        classes.forEach(clazz -> {
            Method[] methods = ReflectUtil.getMethods(clazz);
            for (Method method : methods) {
                Operation operation = method.getAnnotation(Operation.class);
                PreAuthorize preAuthorize = method.getAnnotation(PreAuthorize.class);

                if (preAuthorize != null && operation != null) {
                    String perm = extractPerm(preAuthorize.value());
                    if (perm == null || perm.isEmpty()) {
                        continue;
                    }

                    // 从元数据映射中查找
                    AuthorityMeta meta = metaMap.get(perm);

                    authorities.add(AuthorityOption.builder()
                            .perm(perm)
                            .title(operation.summary())
                            .url(getUrl(clazz, method))
                            .module(meta != null ? meta.getModule() : null)
                            .moduleLabel(meta != null ? meta.getModuleLabel() : null)
                            .resource(meta != null ? meta.getResource() : null)
                            .resourceLabel(meta != null ? meta.getResourceLabel() : null)
                            .action(meta != null ? meta.getAction() : null)
                            .build());
                }
            }
        });

        return authorities;
    }

    /**
     * 构建权限树形结构
     */
    public List<AuthorityTree> getAuthoritiesTree(final Class<? extends Annotation> annotationClass) {
        List<AuthorityOption> authorities = getAvailableAuthorities(annotationClass);

        // 按 module -> resource -> action 分组
        Map<String, Map<String, List<AuthorityOption>>> grouped = authorities.stream()
                .filter(auth -> auth.getModule() != null && auth.getResource() != null)
                .collect(Collectors.groupingBy(
                        AuthorityOption::getModule,
                        Collectors.groupingBy(AuthorityOption::getResource)
                ));

        return buildTree(grouped);
    }

    /**
     * 搜索权限
     */
    public List<AuthorityOption> searchAuthorities(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAvailableAuthorities(AdminClient.class);
        }

        String lowerKeyword = keyword.toLowerCase();
        return getAvailableAuthorities(AdminClient.class).stream()
                .filter(auth ->
                        (auth.getPerm() != null && auth.getPerm().toLowerCase().contains(lowerKeyword)) ||
                        (auth.getTitle() != null && auth.getTitle().toLowerCase().contains(lowerKeyword)) ||
                        (auth.getUrl() != null && auth.getUrl().toLowerCase().contains(lowerKeyword))
                )
                .collect(Collectors.toList());
    }

    /**
     * 提取权限字符串
     */
    private String extractPerm(String preAuthorizeValue) {
        if (preAuthorizeValue == null) {
            return null;
        }

        return preAuthorizeValue
                .replace(HAS_ROLE_SUPER_ADMIN, "")
                .replace(HAS_ROLE_STAFF, "")
                .replace(OR, "")
                .replace(AUTHORITY_PREFIX, "")
                .replace(AUTHORITY_SUFFIX, "")
                .trim();
    }

    /**
     * 从 *AccessAuthorities 接口扫描元数据
     * @return 权限码 -> 元数据的映射
     */
    private Map<String, AuthorityMeta> scanFromAccessAuthorities() {
        Map<String, AuthorityMeta> metaMap = new HashMap<>();

        // 扫描所有 *AccessAuthorities 接口
        Set<Class<?>> allClasses = ClassUtil.scanPackage("org.evolboot", null);
        Set<Class<?>> authorityClasses = allClasses.stream()
                .filter(Class::isInterface)
                .filter(clazz -> clazz.getSimpleName().endsWith("AccessAuthorities"))
                .collect(Collectors.toSet());

        authorityClasses.forEach(moduleClass -> {
            AuthorityModule moduleAnnotation = moduleClass.getAnnotation(AuthorityModule.class);
            if (moduleAnnotation == null) {
                return; // 跳过没有注解的接口
            }

            String module = moduleAnnotation.value();
            String moduleLabel = moduleAnnotation.label().isEmpty() ? module : moduleAnnotation.label();

            // 遍历嵌套接口（资源）
            Class<?>[] innerClasses = moduleClass.getDeclaredClasses();
            for (Class<?> resourceClass : innerClasses) {
                AuthorityResource resourceAnnotation = resourceClass.getAnnotation(AuthorityResource.class);
                if (resourceAnnotation == null) {
                    continue; // 跳过没有注解的嵌套接口
                }

                String resource = resourceAnnotation.value();
                String resourceLabel = resourceAnnotation.label().isEmpty() ? resource : resourceAnnotation.label();

                // 扫描权限常量字段
                Field[] fields = resourceClass.getDeclaredFields();
                for (Field field : fields) {
                    if (!Modifier.isStatic(field.getModifiers()) || !Modifier.isFinal(field.getModifiers())) {
                        continue;
                    }

                    try {
                        String constantValue = (String) field.get(null);
                        String perm = extractPerm(constantValue);
                        String action = extractActionFromFieldName(field.getName());

                        if (perm != null && !perm.isEmpty()) {
                            metaMap.put(perm, new AuthorityMeta(
                                    module,
                                    moduleLabel,
                                    resource,
                                    resourceLabel,
                                    action
                            ));
                        }
                    } catch (IllegalAccessException e) {
                        log.warn("无法访问字段: {}.{}", resourceClass.getName(), field.getName(), e);
                    }
                }
            }
        });

        return metaMap;
    }

    /**
     * 从字段名提取操作
     * HAS_CREATE -> create
     * HAS_PASSWORD_RESET -> password_reset
     */
    private String extractActionFromFieldName(String fieldName) {
        if (fieldName.startsWith("HAS_")) {
            return fieldName.substring(4).toLowerCase();
        }
        return fieldName.toLowerCase();
    }

    /**
     * 权限元数据
     */
    @Data
    @AllArgsConstructor
    private static class AuthorityMeta {
        private String module;
        private String moduleLabel;
        private String resource;
        private String resourceLabel;
        private String action;
    }

    /**
     * 构建树形结构
     */
    private List<AuthorityTree> buildTree(Map<String, Map<String, List<AuthorityOption>>> grouped) {
        List<AuthorityTree> tree = new ArrayList<>();

        grouped.forEach((module, resourceMap) -> {
            // 获取第一个权限的 moduleLabel（同一模块下所有权限的 moduleLabel 都相同）
            String moduleLabel = resourceMap.values().stream()
                    .flatMap(List::stream)
                    .map(AuthorityOption::getModuleLabel)
                    .filter(label -> label != null && !label.isEmpty())
                    .findFirst()
                    .orElse(module);

            // 模块级节点
            AuthorityTree moduleNode = AuthorityTree.builder()
                    .label(moduleLabel)
                    .value(module)
                    .isLeaf(false)
                    .children(new ArrayList<>())
                    .build();

            resourceMap.forEach((resource, authList) -> {
                // 获取第一个权限的 resourceLabel
                String resourceLabel = authList.stream()
                        .map(AuthorityOption::getResourceLabel)
                        .filter(label -> label != null && !label.isEmpty())
                        .findFirst()
                        .orElse(resource);

                // 资源级节点
                AuthorityTree resourceNode = AuthorityTree.builder()
                        .label(resourceLabel)
                        .value(resource)
                        .isLeaf(false)
                        .children(new ArrayList<>())
                        .build();

                // 操作级节点（叶子节点）
                authList.forEach(auth -> {
                    AuthorityTree actionNode = AuthorityTree.builder()
                            .label(auth.getTitle())
                            .value(auth.getPerm())
                            .perm(auth.getPerm())
                            .url(auth.getUrl())
                            .isLeaf(true)
                            .build();
                    resourceNode.getChildren().add(actionNode);
                });

                moduleNode.getChildren().add(resourceNode);
            });

            tree.add(moduleNode);
        });

        return tree;
    }

}
