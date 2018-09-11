package com.android.mazhengyang.encryption;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.mazhengyang.encryption.Util.AESUtil;
import com.android.mazhengyang.encryption.Util.Base64Util;
import com.android.mazhengyang.encryption.Util.Conversion;
import com.android.mazhengyang.encryption.Util.DESUtil;
import com.android.mazhengyang.encryption.Util.MD5Util;
import com.android.mazhengyang.encryption.Util.RSAUtil;
import com.android.mazhengyang.encryption.Util.SHAUtil;
import com.android.mazhengyang.encryption.Util.XORUtil;

import java.security.KeyPair;
import java.security.Provider;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class MainActivity extends AppCompatActivity {

    /**
     * 1、单向加密：不可逆，例如 MD5，SHA，HMAC
     * 2、对称加密：加密方和解密方利用同一个秘钥对数据进行加密和解密，例如 DES，AES，PBE等
     * 3、非对称加密：非对称加密分为公钥和秘钥，二者是非对称的，例如用私钥加密的内容需要使用公钥来解密，
     * 使用公钥加密的内容需要用私钥来解密，DSA，RSA...
     */

    private static final String TAG = "Encryption.MainActivity";

    private String plainText = "加密解密";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Provider[] providers = Security.getProviders();
        for (Provider p : providers) {
            for (Provider.Service s : p.getServices()) {
                Log.d(TAG, "类型:" + s.getType() + "，算法：" + s.getAlgorithm());
            }
            Log.d(TAG, "----------------------------");
        }

        Log.d(TAG, "onCreate: plainText=" + plainText);

//        byDES();
//        byAES();
//        byRSA();
//        byXOR();
//        byBase64();
//        byMD5();
//        bySHA();
    }

    private void byDES() {

        byte[] key = DESUtil.generateKey();
        byte[] cipherBytes = DESUtil.encrypt(plainText.getBytes(), key);
        byte[] clearBytes = DESUtil.decrypt(cipherBytes, key);

        if (clearBytes != null) {
            String clearText = new String(clearBytes);
            Log.d(TAG, "byDES: clearText = " + clearText);
        }
    }

    private void byAES() {

        byte[] key = AESUtil.generateKey();
        byte[] cipherBytes = AESUtil.encrypt(plainText.getBytes(), key);
        byte[] clearBytes = AESUtil.decrypt(cipherBytes, key);

        if (clearBytes != null) {
            String clearText = new String(clearBytes);
            Log.d(TAG, "byDES: clearText = " + clearText);
        }
    }

    private void byRSA() {
        KeyPair keyPair = RSAUtil.generateRSAKeyPair(RSAUtil.DEFAULT_KEY_SIZE);
        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

//        Log.d(TAG, "byRSA: publicKey " + Conversion.toHexString(publicKey.getEncoded()));
//        Log.d(TAG, "byRSA: privateKey " + Conversion.toHexString(privateKey.getEncoded()));

        //公钥加密,私钥解密
        byte[] cipherBytes = RSAUtil.encryptByPublicKeyForSpilt(plainText.getBytes(), publicKey.getEncoded());
        byte[] clearBytes = RSAUtil.decryptByPrivateKeyForSpilt(cipherBytes, privateKey.getEncoded());

        if (clearBytes != null) {
            String clearText = new String(clearBytes);
            Log.d(TAG, "byRSA: clearText = " + clearText);
        }

//        //私钥加密,公钥解密
//        byte[] cipherBytes1 = RSAUtil.encryptByPrivateKeyForSpilt(plainText.getBytes(), privateKey.getEncoded());
//        byte[] clearBytes1 = RSAUtil.decryptByPublicKeyForSpilt(cipherBytes1, publicKey.getEncoded());
//
//        if (clearBytes1 != null) {
//            String clearText = new String(clearBytes1);
//            Log.d(TAG, "byRSA: clearText = " + clearText);
//        }
    }

    private void byXOR() {
        byte[] cipherBytes = XORUtil.encrypt(plainText.getBytes());//加密
        Log.d(TAG, "byXOR: " + Conversion.toHexString(cipherBytes));

        String clearBytes = new String(XORUtil.encrypt(cipherBytes));//解密

        Log.d(TAG, "byXOR: clearBytes = " + clearBytes);
    }

    private void byBase64() {

        String cipherString = Base64Util.encrypt(plainText);
        Log.d(TAG, "byBase64: cipherString=" + cipherString);

        String clearText = Base64Util.decrypt(cipherString);
        Log.d(TAG, "byBase64: clearText=" + clearText);
    }

    private void byMD5() {

        String result = MD5Util.encryptString(plainText);
        Log.d(TAG, "byMD5: result=" + result);
    }

    private void bySHA() {

        String cipherString = SHAUtil.encryptString(plainText);
        Log.d(TAG, "bySHA: cipherString=" + cipherString);
    }

}
