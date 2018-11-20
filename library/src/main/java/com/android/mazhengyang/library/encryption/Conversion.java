package com.android.mazhengyang.library.encryption;

/**
 * Created by mazhengyang on 18-9-7.
 */

public class Conversion {

    private final static String HEX = "0123456789ABCDEF";

    public static String toHexString(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer sb = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            sb.append(HEX.charAt((buf[i] >> 4) & 0x0f)).append(HEX.charAt(buf[i] & 0x0f));
        }
        return sb.toString();
    }

/*    public static String toHexString(byte[] buf) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < buf.length; ++i) {

            String s = Integer.toHexString(buf[i] & 0xFF);
            if (s.length() == 1) {
                buffer.append("0" + s);
                return "0" + s;
            } else {
                buffer.append(s);
            }
        }
        return buffer.toString();
    }*/

}
