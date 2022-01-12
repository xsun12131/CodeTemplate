package com.fatpanda.idea.right.util;

/**
 * @author xuyangyang
 * @date 2022/1/12
 */
public class SystemUtil {

    private SystemUtil() {}

    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

}
