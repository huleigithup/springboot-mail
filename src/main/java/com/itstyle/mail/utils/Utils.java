package com.itstyle.mail.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.UUID;

public class Utils {

    private static final String PATH = "D:\\test\\";
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);
    public static final String SF_FILE_SEPARATOR = "\\";
    public static final String SF_LINE_SEPARATOR = " ";
    public static final String SF_PATH_SEPARATOR = ";";
    public static final String SF_SEPA_SEPARATOR = ",";
    public static final String SF_SPOT_SEPARATOR = ".";
//    public static final String SF_FILE_SEPARATOR = System.getProperty("file.separator"); // \
//    public static final String SF_LINE_SEPARATOR = System.getProperty("line.separator"); //
//    public static final String SF_PATH_SEPARATOR = System.getProperty("path.separator"); // ;
//    public static final String SF_SEPA_SEPARATOR = System.getProperty("spea.separator"); // ,


    public static boolean existSuffix(String attachName) {
        if (StringUtils.contains(attachName, SF_SPOT_SEPARATOR)) {
            return true;
        }
        return false;
    }

    public static String getSuffix(String fileName) {
        if (fileName.contains(SF_SPOT_SEPARATOR)) {
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            return suffix.toLowerCase().trim();
        }
        throw new IllegalArgumentException("文件没有后缀");
    }

    public static String getTmpDir() {
        return System.getProperty("java.io.tmpdir");
    }


    public static File createTmpFile(String suffix) {
        return new File(getTmpDir(), UUID.randomUUID().toString().replace("-", "") + suffix);
    }


    public static File createTmpFileWithName(String fileName) {
        return new File(getTmpDir(), fileName);
    }


}
