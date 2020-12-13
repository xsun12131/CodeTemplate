package com.fatpanda.idea.right;

/**
 * @Author: fatpanda
 * @Date: 2020/12/12
 */
public enum TemplateType {
    CONTROLLER(1, "controller"),
    SERVICE(2, "service"),
    SERVICEIMPL(3, "serviceImpl"),
    REPOSITORY(4, "repositroy");

    private Integer code;

    private String name;

    TemplateType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
