package com.wyu.xjxy.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

public class DownloadUtil {
    public static String base64EncodeFileName(String filename) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            return "=?UTF-8?B?"
                    + new String(base64Encoder.encode(filename
                    .getBytes("UTF-8"))) + "?=";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
