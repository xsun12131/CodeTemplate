package com.fatpanda.idea.right.util;

import com.fatpanda.idea.right.TemplateType;
import com.fatpanda.idea.right.ui.ConfigData;
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

    public static Map<String, Object> entityProperty = new HashMap<>();

    public static Map<String, Object> getEntityProperty() {
        return entityProperty;
    }

    public static void setEntityProperty(Map<String, Object> entityProperty) {
        GenerateUtil.entityProperty = entityProperty;
    }

    /**
     * 通过class文件获取信息
     *
     * @param clazz
     * @return
     */
    public static void generateProperty(PsiClass clazz) {
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

        setEntityProperty(entityMap);
    }

    /**
     * 生成模板
     * @param objClass 原对象class
     * @param type 生成的类型
     * @return
     * @throws IOException
     */
    public static Boolean generateTemplate(PsiClass objClass, TemplateType type) throws IOException {

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
            template.process(getEntityProperty(), out);
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

    /**
     * 根据类型生成路径
     * @param objClass
     * @param type
     * @return
     */
    private static String switchTemplatePath(PsiClass objClass, TemplateType type) {
        String packageName = PsiUtil.getPackageName(objClass);
        String path = PsiUtil.getVirtualFile(objClass.getParent()).getPath();
        String fileName = "";
        String customizePackageName = (StringUtils.isBlank(packageName)? "" : File.separator + packageName.substring(0, packageName.lastIndexOf(".")).replace(".", "/"));
        if (!customizePackageName.equals(ConfigData.getPackageName())) {
            //设置父级报名
            getEntityProperty().put("parentPackageName", ConfigData.getPackageName());
            getEntityProperty().put("parentPackageNamePath", ConfigData.getPackageName().replaceAll("\\.", File.separator));
            customizePackageName = ConfigData.getPackageName().replace("." , File.separator);
        }
        switch (type) {
            case CONTROLLER:
                fileName = StringUtils.substringBefore(path, File.separator + packageName.replace(".", File.separator))
                        + File.separator + customizePackageName
                        + File.separator + "controller"
                        + File.separator + objClass.getName() + "Controller.java";
                break;
            case SERVICE:
                fileName = StringUtils.substringBefore(path, File.separator + packageName.replace(".", File.separator))
                        + File.separator + customizePackageName
                        + File.separator + "service"
                        + File.separator + objClass.getName() + "Service.java";
                break;
            case SERVICEIMPL:
                fileName = StringUtils.substringBefore(path, File.separator + packageName.replace(".", File.separator))
                        + File.separator + customizePackageName
                        + File.separator + "service"
                        + File.separator + "impl"
                        + File.separator + objClass.getName() + "ServiceImpl.java";
                break;
            case REPOSITORY:
                fileName = StringUtils.substringBefore(path, File.separator + packageName.replace(".", File.separator))
                        + File.separator + customizePackageName
                        + File.separator + "repository"
                        + File.separator + objClass.getName() + "Repository.java";
                break;
            default: break;
        }
        return fileName;
    }


}
