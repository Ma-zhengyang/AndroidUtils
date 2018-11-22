package com.android.mazhengyang.library;

import android.content.Context;
import android.text.format.Formatter;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by mazhengyang on 18-11-22.
 */

public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    /**
     * The number of bytes in a kilobyte.
     */
    public static final long ONE_KB = 1024;

    /**
     * The number of bytes in a megabyte.
     */
    public static final long ONE_MB = ONE_KB * ONE_KB;

    /**
     * The number of bytes in a gigabyte.
     */
    public static final long ONE_GB = ONE_KB * ONE_MB;

    public static String formatFileSize(long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        if (size == 0) {
            return "0B";
        }
        if (size < ONE_KB) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < ONE_MB) {
            fileSizeString = df.format((double) size / ONE_KB) + "KB";
        } else if (size < ONE_GB) {
            fileSizeString = df.format((double) size / ONE_MB) + "MB";
        } else {
            fileSizeString = df.format((double) size / ONE_GB) + "GB";
        }
        return fileSizeString;
    }

    private static long getFileSize(File file) {
        long size = 0;
        FileInputStream fis = null;
        try {
            if (file.exists()) {
                fis = new FileInputStream(file);
                size = fis.available();
            } else {
                Log.e(TAG, "getFileSize: file not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return size;
    }
}
