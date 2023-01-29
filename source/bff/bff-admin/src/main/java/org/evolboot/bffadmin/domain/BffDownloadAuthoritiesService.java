package org.evolboot.bffadmin.domain;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.IdUtil;
import org.evolboot.storage.domain.blob.BlobAppService;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

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
                            .replace(HAS_ROLE_ADMIN, "")
                            .replace(HAS_ROLE_STAFF, "")
                            .replace(or, "")
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
        String authorities = blobAppService.createFile(new FileInputStream(file), filename, 1024L, 1L);
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

}
