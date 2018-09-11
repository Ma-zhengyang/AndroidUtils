package com.android.mazhengyang.encryption.Util;

import android.os.Message;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mazhengyang on 18-9-11.
 */

public class SHAUtil {

    /**
     * 概述：安全散列算法（Secure Hash Algorithm）简称SHA，数字签名工具。
     * 著名的图片加载框架Glide在缓存key时就采用的此加密
     * 优点：破解难度高，不可逆
     * 缺点：可以通过穷举法进行破解
     */

    private static String ALGORITHM = "SHA-256";

    public static String encryptString(String string) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(string.getBytes("UTF-8"));
            byte[] bytes = messageDigest.digest();
            return Conversion.toHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;

    }

}
