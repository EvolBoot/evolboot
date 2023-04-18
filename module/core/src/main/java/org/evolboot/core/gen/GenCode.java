package org.evolboot.core.gen;

import cn.hutool.core.io.FileUtil;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.Version;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Objects;

/**
 * @author evol
 */
@Slf4j
public class GenCode {

    /**
     * 项目根路径
     */
    private final static String CODE_GENERATOR_PATH =
            new File(Objects.requireNonNull(Version.class.getResource("/")).getPath())
                    .getParentFile()
                    .getParentFile()
                    .getParentFile()
                    .getParentFile()
                    .getPath()
                    + "/docs/code-generator";
    /**
     * 配置文件路径
     */
    private final static String CONFIG_YAML_RELATIVE_PATH = CODE_GENERATOR_PATH + "/config.yml";

    /**
     * 模版路径
     */
    private final static String TEMPLATE_PATH = CODE_GENERATOR_PATH + "/template";

    private final static String TARGET_PATH = CODE_GENERATOR_PATH + "/target";


    public static void main(String[] args) throws IOException {
        Yaml yaml = new Yaml();
        GenConfig genConfig = yaml.loadAs(new FileInputStream(CONFIG_YAML_RELATIVE_PATH), GenConfig.class);

        String templatePath = ExtendObjects.isBlank(genConfig.getTemplatePath()) ? TEMPLATE_PATH : genConfig.getTemplatePath();
        File template = new File(templatePath);
        File target = new File(TARGET_PATH);

        Assert.isTrue(template.exists(), "模板文件不存在");
        // 删除之前的生成代码
        FileUtil.del(TARGET_PATH);
        // 拷贝一份新的模板
        FileUtil.copyContent(template, target, true);
        GenConfig.ConfigYaml placeholder = genConfig.getPlaceholder();
        GenConfig.ConfigYaml replace = genConfig.getReplace();


        // 包名,去除下划线转小写
        String boundContext =  replace.getBoundContext().replaceAll("_", "").toLowerCase();
        // 类名前缀,驼峰
        String boundContextClass = toCamelCase(replace.getBoundContext());
        // URL,将_ 转为 - 转小写
        String boundContextUrl = replace.getBoundContext().replaceAll("_","-").toLowerCase();

        // 领域名称
        String module = replace.getDomainName().replaceAll("_", "").toLowerCase();
        // 类名前缀,驼峰
        String getClassNamePrefix = toCamelCase(replace.getDomainName());
        // 变量名,首字母小写
        String getInstantiationObjectName = firstToLower(toCamelCase(replace.getDomainName()));
        // URL,将_ 转为 - 转小写
        String getClassNameUrl = replace.getDomainName().replaceAll("_", "-").toLowerCase();


        // 替换文件内容
        replaceInFiles(target, placeholder.getBoundContext(), boundContext);
        replaceInFiles(target, placeholder.getBoundContextClass(), boundContextClass);
        replaceInFiles(target, placeholder.getBoundContextUrl(), boundContextUrl);


        replaceInFiles(target, placeholder.getDomainName(), module);
        replaceInFiles(target, placeholder.getClassNamePrefix(), getClassNamePrefix);
        replaceInFiles(target, placeholder.getProjectPackage(), replace.getProjectPackage());
        replaceInFiles(target, placeholder.getRemark(), replace.getRemark());
        replaceInFiles(target, placeholder.getAuthor(), replace.getAuthor());
        replaceInFiles(target, placeholder.getInstantiationObjectName(), getInstantiationObjectName);
        replaceInFiles(target, placeholder.getClassNameUrl(), getClassNameUrl);
        replaceInFiles(target, placeholder.getTablePrefix(), replace.getTablePrefix());
        replaceInFiles(target, placeholder.getDatePlaceholder(), ExtendDateUtil.now());
        replaceInFiles(target, placeholder.getPkIdClass(), replace.getPkIdClass());

        // 替换文件名
        renameFilesAndDirs(target, placeholder.getBoundContext(), boundContext);
        renameFilesAndDirs(target, placeholder.getBoundContextClass(), boundContextClass);
        renameFilesAndDirs(target, placeholder.getClassNamePrefix(), getClassNamePrefix);
        renameFilesAndDirs(target, placeholder.getDomainName(), module);

        System.out.println(genConfig);

    }

    public static void renameFilesAndDirs(File dir, String oldName, String newName) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                // 递归调用
                renameFilesAndDirs(file, oldName, newName);
                // 处理文件夹名称
                String foldername = file.getName();
                if (foldername.contains(oldName)) {
                    String newFoldername = foldername.replace(oldName, newName);
                    File newFolder = new File(file.getParentFile(), newFoldername);
                    boolean success = file.renameTo(newFolder);
                    if (success) {
                        System.out.println("Renamed folder: " + foldername + " to " + newFoldername);
                    } else {
                        System.out.println("Failed to rename folder: " + foldername);
                    }
                }
            } else {
                // 处理文件名称
                String filename = file.getName();
                if (filename.contains(oldName)) {
                    String newFilename = filename.replace(oldName, newName);
                    File newFile = new File(file.getParentFile(), newFilename);
                    boolean success = file.renameTo(newFile);
                    if (success) {
                        System.out.println("Renamed file: " + filename + " to " + newFilename);
                    } else {
                        System.out.println("Failed to rename file: " + filename);
                    }
                }
            }
        }
    }


    public static void replaceInFiles(File directory, String oldStr, String newStr) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    replaceInFiles(file, oldStr, newStr);
                }
            }
        } else if (directory.isFile()) {
            String filePath = directory.getAbsolutePath();
            try {
                FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                    stringBuffer.append("\n");
                }
                bufferedReader.close();
                fileReader.close();
                String fileContent = stringBuffer.toString();
                fileContent = fileContent.replaceAll(oldStr, newStr);
                FileWriter fileWriter = new FileWriter(filePath);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(fileContent);
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 驼峰
     * @param str
     * @return
     */
    public static String toCamelCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String[] parts = str.split("[_\\s]");
        StringBuilder camelCaseBuilder = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                camelCaseBuilder.append(Character.toUpperCase(part.charAt(0)));
                camelCaseBuilder.append(part.substring(1).toLowerCase());
            }
        }
        return camelCaseBuilder.toString();
    }

    /**
     * 首字母小写
     * @param str
     * @return
     */
    public static String firstToLower(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.replace(0, 1, String.valueOf(Character.toLowerCase(sb.charAt(0))));
        return sb.toString();
    }
}
