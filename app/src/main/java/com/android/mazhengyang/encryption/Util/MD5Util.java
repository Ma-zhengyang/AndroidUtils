package com.android.mazhengyang.encryption.Util;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mazhengyang on 18-9-8.
 */

public class MD5Util {

    /**
     *概述：消息摘要算法（Message Digest Algorithm 5）
     *优点：不可逆，压缩性，不容易修改，容易计算
     *缺点：穷举法可以破解
     *
     *压缩性：任意长度的数据，算出的MD5值长度都是固定的。
     *容易计算：从原数据计算出MD5值很容易。
     *抗修改性：对原数据进行任何改动，哪怕只修改1个字节，所得到的MD5值都有很大区别。
     *强抗碰撞：已知原数据和其MD5值，想找到一个具有相同MD5值的数据（即伪造数据）是非常困难的。
     *
     *应用场景：一致性验证 / 数字签名 / 安全访问认证
     */

    /**
     * 计算字符串MD5值
     *
     * @param string
     * @return
     */
    public static String encryptString(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(string.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                stringBuffer.append(temp);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 计算文件的MD5值
     *
     * @param file
     * @return
     */
    public static String encryptFile(File file) {
        if (file == null || !file.isFile() || !file.exists()) {
            return "";
        }
        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = new StringBuffer();
        byte buffer[] = new byte[8192];
        int len;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            while ((len = fileInputStream.read(buffer)) != -1) {
                messageDigest.update(buffer, 0, len);
            }
            byte[] bytes = messageDigest.digest();

            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                stringBuffer.append(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 虽然说MD5加密本身是不可逆的，但并不是不可破译的，网上有关MD5解密的网站数不胜数，破解机制采用穷举法，就是我们平时说
     * 的跑字典。所以如何才能加大MD5破解的难度呢？
     */

    /**
     * 多次加密
     *
     * @param string
     * @param times
     * @return
     */
    public static String repeat(String string, int times) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        String md5 = encryptString(string);
        for (int i = 0; i < times - 1; i++) {
            md5 = encryptString(md5);
        }
        return md5;
    }

    /**
     * MD5加盐
     *
     * @param string
     * @param slat
     * @return
     */
    public static String addSlat(String string, String slat) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest((string + slat).getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                stringBuffer.append(temp);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
