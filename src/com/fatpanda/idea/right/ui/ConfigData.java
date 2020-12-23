package com.fatpanda.idea.right.ui;

public class ConfigData {

    private String className;

    private String packageName;

    private Boolean isController;

    private String controllerSuffix = "Controller";

    private String serviceSuffix = "Service";

    private String serviceImplSuffix = "ServiceImpl";

    private String repositorySuffix = "Repository";

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Boolean getController() {
        return isController;
    }

    public void setController(Boolean controller) {
        isController = controller;
    }

    public String getControllerSuffix() {
        return controllerSuffix;
    }

    public void setControllerSuffix(String controllerSuffix) {
        this.controllerSuffix = controllerSuffix;
    }

    public String getServiceSuffix() {
        return serviceSuffix;
    }

    public void setServiceSuffix(String serviceSuffix) {
        this.serviceSuffix = serviceSuffix;
    }

    public String getServiceImplSuffix() {
        return serviceImplSuffix;
    }

    public void setServiceImplSuffix(String serviceImplSuffix) {
        this.serviceImplSuffix = serviceImplSuffix;
    }

    public String getRepositorySuffix() {
        return repositorySuffix;
    }

    public void setRepositorySuffix(String repositorySuffix) {
        this.repositorySuffix = repositorySuffix;
    }
}
