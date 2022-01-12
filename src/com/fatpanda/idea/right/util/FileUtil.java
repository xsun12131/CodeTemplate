package com.fatpanda.idea.right.util;

import java.io.File;

/**
 * @author xuyangyang
 * @date 2022/1/12
 */
public class FileUtil {

    private FileUtil() {}

    public static final String UTF_8 = "UTF-8";

    public static String replaceToFilePath(String origin, String regex) {

        String result = origin;
        if (SystemUtil.isLinux()) {
            result = origin.replaceAll(regex, File.separator);
        } else if (SystemUtil.isWindows()) {
            result = origin.replaceAll(regex, "\\\\");
        }

        return result;
    }


}
