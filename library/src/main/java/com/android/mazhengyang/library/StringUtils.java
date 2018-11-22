package com.android.mazhengyang.library;

/**
 * Created by mazhengyang on 18-11-22.
 */

public class StringUtils {


    /**
     *
     * @param str 字符串
     * @return 是否包含汉字
     */
    public static boolean isChinese(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param c 字符
     * @return 是否是汉字
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    /**
     * 获取字符串的字节数目
     * @param str 字符串
     * @return 字节数
     */
    public static int getStringByteCount(String str) {
        int chineseCount = 0;
        int englishCount = 0;
        for (int i = 0; i < str.length(); i++) {
            String b = Character.toString(str.charAt(i));
            if (isChinese(b)) {
                chineseCount++;
            } else {
                englishCount++;
            }
        }
        return (chineseCount * 2 + englishCount);
    }

}
