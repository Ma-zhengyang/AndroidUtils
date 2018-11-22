package com.android.mazhengyang.androidutils;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.mazhengyang.library.AppUtils;
import com.android.mazhengyang.library.ByteUtils;
import com.android.mazhengyang.library.DisplayUtils;
import com.android.mazhengyang.library.EncryptUtils;
import com.android.mazhengyang.library.FileUtils;
import com.android.mazhengyang.library.RandomUtils;
import com.android.mazhengyang.library.StringUtils;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testEncrypt();
//        testApp();
//        testRandom();
//        testString();
//        testDisplay();
//        testFile();
        testByte();
    }

    private void testEncrypt() {
        EncryptUtils encryptionUtil = new EncryptUtils("加密解密");
        encryptionUtil.byAES();
//        encryptionUtil.byDES();
//        encryptionUtil.byAES();
//        encryptionUtil.byRSA();
//        encryptionUtil.byXOR();
//        encryptionUtil.byBase64();
//        encryptionUtil.byMD5();
//        encryptionUtil.bySHA();
    }

    private void testApp() {

        AppUtils appUtils = new AppUtils(this, getPackageName());

        String appName = appUtils.getAppName();
        String versionName = appUtils.getVersionName();
        int versionCode = appUtils.getVersionCode();
        Drawable drawable = appUtils.getAppIcon();
        String[] appPermission = appUtils.getAppPermission();
        String appSignature = appUtils.getAppSignature();
        boolean apkDebugable = appUtils.isApkDebugable();
        boolean apkDebugable2 = appUtils.isApkDebugable2();
        boolean appInBackground = appUtils.isAppInBackground();

        Log.d(TAG, "testApp: appName=" + appName);
        Log.d(TAG, "testApp: versionName=" + versionName);
        Log.d(TAG, "testApp: versionCode=" + versionCode);
        Log.d(TAG, "testApp: drawable=" + drawable);
        Log.d(TAG, "testApp: appPermission=" + appPermission);
        if (appPermission != null) {
            for (String s : appPermission) {
                Log.d(TAG, "testApp: appPermission=" + s);
            }
        }
        Log.d(TAG, "testApp: appSignature=" + appSignature);
        Log.d(TAG, "testApp: apkDebugable=" + apkDebugable);
        Log.d(TAG, "testApp: apkDebugable2=" + apkDebugable2);
        Log.d(TAG, "testApp: appInBackground=" + appInBackground);

    }

    private void testRandom() {

        Log.d(TAG, "testRandom: RandomUtils.randomInt()=" + RandomUtils.randomInt());
        Log.d(TAG, "testRandom: RandomUtils.randomInt(int n)=" + RandomUtils.randomInt(100));
        Log.d(TAG, "testRandom: RandomUtils.randomInt(int min, int max)=" + RandomUtils.randomInt(100, 200));
        Log.d(TAG, "testRandom: RandomUtils.randomFloat()=" + RandomUtils.randomFloat());
        Log.d(TAG, "testRandom: RandomUtils.randomDouble()=" + RandomUtils.randomDouble());
        Log.d(TAG, "testRandom: RandomUtils.randomLong()=" + RandomUtils.randomLong());
        Log.d(TAG, "testRandom: RandomUtils.randomBoolean()=" + RandomUtils.randomBoolean());
        Log.d(TAG, "testRandom: RandomUtils.randomGaussian()=" + RandomUtils.randomGaussian());
        byte[] buf = new byte[10];
        RandomUtils.randomBytes(buf);
        for (int i = 0; i < buf.length; i++) {
            Log.d(TAG, "testRandom: RandomUtils.randomBytes(byte[] buf)=" + buf[i]);
        }
    }

    private void testString() {

        Log.d(TAG, "testString: " + StringUtils.isChinese("我的wo"));
        Log.d(TAG, "testString: " + StringUtils.isChinese("wo"));
        Log.d(TAG, "testString: " + StringUtils.getStringByteCount("我的wo"));
    }

    private void testDisplay() {

        Log.d(TAG, "testDisplay: getScreenWidth=" + DisplayUtils.getScreenWidth(this));
        Log.d(TAG, "testDisplay: getScreenHeight=" + DisplayUtils.getScreenHeight(this));
        Log.d(TAG, "testDisplay: getScreenDensity=" + DisplayUtils.getScreenDensity(this));
        Log.d(TAG, "testDisplay: getScreenDensityDPI=" + DisplayUtils.getScreenDensityDPI(this));
        Log.d(TAG, "testDisplay: getStatusBarHeight=" + DisplayUtils.getStatusBarHeight(this));
    }

    private void testFile() {

        Log.d(TAG, "testFile: testFile=" + FileUtils.formatFileSize(100000));
    }

    private void testByte() {

        byte[] b = new byte[0];
        try {
            b = "abc".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < b.length; i++) {
            Log.d(TAG, "testFile: testByte=" + b[i]);
        }
        Log.d(TAG, "testByte: bytes2HexStr " + ByteUtils.bytes2HexStr(b));

        Log.d(TAG, "testByte: " + new String(b));
    }

}
