package com.fatpanda.idea.right.ui;

public class ConfigData {

    private static String className;

    private static String packageName;

    private static Boolean selectController;

    private static Boolean selectedService;

    private static Boolean selectedServiceImpl;

    private static Boolean selectedRepository;

    private static String controllerSuffix = "Controller";

    private static String serviceSuffix = "Service";

    private static String serviceImplSuffix = "ServiceImpl";

    private static String repositorySuffix = "Repository";

    public static String getClassName() {
        return className;
    }

    public static void clearSet() {
        ConfigData.setClassName("");
        ConfigData.setPackageName("");
        ConfigData.setSelectController(false);
        ConfigData.setSelectedService(false);
        ConfigData.setSelectedServiceImpl(false);
        ConfigData.setSelectedRepository(false);
    }

    private ConfigData() {}

    public static void setClassName(String className) {
        ConfigData.className = className;
    }

    public static String getPackageName() {
        return packageName;
    }

    public static void setPackageName(String packageName) {
        ConfigData.packageName = packageName;
    }

    public static Boolean getSelectController() {
        return selectController;
    }

    public static void setSelectController(Boolean selectController) {
        ConfigData.selectController = selectController;
    }

    public static Boolean getSelectedService() {
        return selectedService;
    }

    public static void setSelectedService(Boolean selectedService) {
        ConfigData.selectedService = selectedService;
    }

    public static Boolean getSelectedServiceImpl() {
        return selectedServiceImpl;
    }

    public static void setSelectedServiceImpl(Boolean selectedServiceImpl) {
        ConfigData.selectedServiceImpl = selectedServiceImpl;
    }

    public static Boolean getSelectedRepository() {
        return selectedRepository;
    }

    public static void setSelectedRepository(Boolean selectedRepository) {
        ConfigData.selectedRepository = selectedRepository;
    }

    public static String getControllerSuffix() {
        return controllerSuffix;
    }

    public static void setControllerSuffix(String controllerSuffix) {
        ConfigData.controllerSuffix = controllerSuffix;
    }

    public static String getServiceSuffix() {
        return serviceSuffix;
    }

    public static void setServiceSuffix(String serviceSuffix) {
        ConfigData.serviceSuffix = serviceSuffix;
    }

    public static String getServiceImplSuffix() {
        return serviceImplSuffix;
    }

    public static void setServiceImplSuffix(String serviceImplSuffix) {
        ConfigData.serviceImplSuffix = serviceImplSuffix;
    }

    public static String getRepositorySuffix() {
        return repositorySuffix;
    }

    public static void setRepositorySuffix(String repositorySuffix) {
        ConfigData.repositorySuffix = repositorySuffix;
    }
}
