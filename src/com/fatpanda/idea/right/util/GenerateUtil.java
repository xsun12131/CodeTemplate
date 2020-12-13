package com.fatpanda.idea.right.util;

import com.fatpanda.idea.right.TemplateType;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.util.PsiUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Id;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: fatpanda
 * @Date: 2020/12/11
 */
public class GenerateUtil {

    /**
     * 通过class文件获取信息
     *
     * @param clazz
     * @return
     */
    public static Map<String, Object> generateProperty(PsiClass clazz) {
        Map<String, Object> entityMap = new HashMap<>();

        //获取对象名
        String entityName = clazz.getName();
        entityMap.put("entityName", entityName);
        entityMap.put("entityNameFirstAlphabet", entityName.substring(0, 1).toLowerCase());

        //获取包名
        // 获取Java类所在的Package
        String packageName = PsiUtil.getPackageName(clazz);
        entityMap.put("packageName", packageName);
        entityMap.put("packagePath", packageName.replaceAll("\\.", File.separator));
        //获取父级报名
        String parentPackageName = StringUtils.isBlank(packageName) ? "" : packageName.substring(0, packageName.lastIndexOf("."));
        entityMap.put("parentPackageName", parentPackageName);
        entityMap.put("parentPackagePath", parentPackageName.replaceAll("\\.", File.separator));

        //获取属性值
        PsiField[] fields = clazz.getFields();
        //存放名称->类型Map
        Map<String, String> propertyMap = new HashMap<>();
        Arrays.stream(fields).forEach(field -> {
            propertyMap.put(field.getName(), StringUtils.substringAfter(field.getType().toString(), "PsiType:").trim());

            //获取Id标记的注解
            if (field.hasAnnotation(Id.class.getName())) {
                entityMap.put("idName", field.getName());
                String idType = StringUtils.substringAfter(field.getType().toString(), "PsiType:").trim();
                entityMap.put("idType", idType);
            }

        });
        entityMap.put("propertyMap", propertyMap);

        return entityMap;
    }

    public static Boolean generateTemplate(Map<String, Object> entityMap, PsiClass objClass, TemplateType type) throws IOException {

        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(GenerateUtil.class, "/templates");
        Template template = switchTemplate(cfg, type);

        String fileName = switchTemplatePath(objClass, type);
        File file = new File(fileName);
        if (!file.exists()) {
            String parent = file.getParent();
            new File(parent).mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        Writer out = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
        try {
            template.process(entityMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
        return false;
    }

    private static Template switchTemplate(Configuration cfg, TemplateType type) throws IOException {
        switch (type) {
            case CONTROLLER:
                return cfg.getTemplate("controller.ftl", "UTF-8");
            case SERVICE:
                return cfg.getTemplate("service.ftl", "UTF-8");
            case SERVICEIMPL:
                return cfg.getTemplate("serviceImpl.ftl", "UTF-8");
            case REPOSITORY:
                return cfg.getTemplate("repository.ftl", "UTF-8");
            default:
                return null;
        }
    }

    private static String switchTemplatePath(PsiClass objClass, TemplateType type) {
        String packageName = PsiUtil.getPackageName(objClass);
        String path = PsiUtil.getVirtualFile(objClass.getParent()).getPath();
        String fileName = "";
        switch (type) {
            case CONTROLLER:
                fileName = StringUtils.substringBefore(path, File.separator + packageName.replace(".", File.separator))
                        + (StringUtils.isBlank(packageName)? "" : File.separator + packageName.substring(0, packageName.lastIndexOf(".")).replace(".", "/"))
                        + File.separator + "controller"
                        + File.separator + objClass.getName() + "Controller.java";
                break;
            case SERVICE:
                fileName = StringUtils.substringBefore(path, File.separator + packageName.replace(".", File.separator))
                        + (StringUtils.isBlank(packageName)? "" : File.separator + packageName.substring(0, packageName.lastIndexOf(".")).replace(".", "/"))
                        + File.separator + "service"
                        + File.separator + objClass.getName() + "Service.java";
                break;
            case SERVICEIMPL:
                fileName = StringUtils.substringBefore(path, File.separator + packageName.replace(".", File.separator))
                        + (StringUtils.isBlank(packageName)? "" : File.separator + packageName.substring(0, packageName.lastIndexOf(".")).replace(".", "/"))
                        + File.separator + "service"
                        + File.separator + "impl"
                        + File.separator + objClass.getName() + "ServiceImpl.java";
                break;
            case REPOSITORY:
                fileName = StringUtils.substringBefore(path, File.separator + packageName.replace(".", File.separator))
                        + (StringUtils.isBlank(packageName)? "" : File.separator + packageName.substring(0, packageName.lastIndexOf(".")).replace(".", "/"))
                        + File.separator + "repository"
                        + File.separator + objClass.getName() + "Repository.java";
                break;
        }
        return fileName;
    }


}
