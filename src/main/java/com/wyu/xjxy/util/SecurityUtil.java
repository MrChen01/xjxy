package com.wyu.xjxy.util;

import org.springframework.util.DigestUtils;

public class SecurityUtil {
    //用于加密md5
    private static final String slat = "jksaj242@%$#$^$*^^";
    public static String getMD5(String value) {
        String base = value + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
    public static void main(String[] args) {
        System.out.println(getMD5("123"));
    }
}
