package com.android.mazhengyang.library.encryption;

/**
 * Created by mazhengyang on 18-9-7.
 */

public class XORUtil {

    /**
     *概述：异或加密：原字符或数字 m 与一个数值 k 进行异或运算得到结果 r ，则用 r 与 k 做异或运算即可还原到 m
     *优点：两个变量的互换（不借助第三个变量），简单的数据加密
     *缺点：加密方式简单
     */

    /**
     * 异或的运算方法是一个二进制运算：
     * 1^1=0
     * 0^0=0
     * 1^0=1
     * 0^1=1
     * 两者相等为0,不等为1.
     * <p>
     * 这样我们交换两个整数的值时可以不用第三个参数。
     * 如a=11,b=9.以下是二进制
     * a=a^b=1011^1001=0010;
     * b=b^a=1001^0010=1011;
     * a=a^b=0010^1011=1001;
     * 这样一来a=9,b=13
     */

    private static final int key = 0x2a;

    public static byte[] encrypt(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        int len = bytes.length;
        for (int i = 0; i < len; i++) {
            bytes[i] ^= key;
        }
        return bytes;
    }
}
