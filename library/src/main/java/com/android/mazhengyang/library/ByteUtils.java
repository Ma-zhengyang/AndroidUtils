package com.android.mazhengyang.library;

/**
 * Created by mazhengyang on 18-11-22.
 */

public class ByteUtils {

    private final static char[] mChars = "0123456789ABCDEF".toCharArray();

    public static String bytes2HexStr(byte[] bytes) {

        if (bytes == null || bytes.length == 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            builder.append(mChars[(bytes[i] & 0xFF) >> 4]);
            builder.append(mChars[bytes[i] & 0x0F]);
            builder.append(" ");
        }
        return builder.toString();
    }


}
