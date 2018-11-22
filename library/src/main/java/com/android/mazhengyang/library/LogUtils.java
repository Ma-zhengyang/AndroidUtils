package com.android.mazhengyang.library;

/**
 * Created by mazhengyang on 18-11-22.
 */

public class LogUtils {
    /*

    log记录工具

    1、
    AndroidManifest.xml中添加权限，并在设置中使能
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />



    2、
    在Appliction中:

    private static Logger mLogger;

    @Override
    public void onCreate() {
        super.onCreate();

        mLogger = Logger.newBuilder(this)
                .setLogType(LogType.ALL)//日志输出类型
                .withPrint(true)//是否输出到控制台
                .bulid();
        mLogger.cleanPreviewLog();//清除之前全部log文件
        mLogger.start();//开始记录

        mLogger.d(TAG, "onCreate: ");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mLogger.d(TAG, "onTerminate: ");
        mLogger.stop();
    }

    public static Logger getLogger() {
        return mLogger;
    }


    3、引用：
        Logger mLogger = MyApplication.getLogger();
        mLogger.v(TAG, "this is a verbose log");
        mLogger.d(TAG, "this is a debug log");
        mLogger.i(TAG, "this is a info log");
        mLogger.w(TAG, "this is a warn log");
        mLogger.e(TAG, "this is a error log");


       log默认保存在/sdcard/Android/data/应用包名/files中

     */
}
