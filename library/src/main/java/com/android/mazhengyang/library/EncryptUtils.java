package com.android.mazhengyang.library;

import android.util.Log;

import com.android.mazhengyang.library.encryption.AESUtil;
import com.android.mazhengyang.library.encryption.Base64Util;
import com.android.mazhengyang.library.encryption.Conversion;
import com.android.mazhengyang.library.encryption.DESUtil;
import com.android.mazhengyang.library.encryption.MD5Util;
import com.android.mazhengyang.library.encryption.RSAUtil;
import com.android.mazhengyang.library.encryption.SHAUtil;
import com.android.mazhengyang.library.encryption.XORUtil;

import java.security.KeyPair;
import java.security.Provider;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by mazhengyang on 18-11-20.
 */

public class EncryptUtils {

    /**
     * 1、单向加密：不可逆，例如 MD5，SHA，HMAC
     * 2、对称加密：加密方和解密方利用同一个秘钥对数据进行加密和解密，例如 DES，AES，PBE等
     * 3、非对称加密：非对称加密分为公钥和秘钥，二者是非对称的，例如用私钥加密的内容需要使用公钥来解密，
     * 使用公钥加密的内容需要用私钥来解密，DSA，RSA...
     */

    private static final String TAG = EncryptUtils.class.getSimpleName();

    private String plainText = "";

    public EncryptUtils(String plainText) {
        this.plainText = plainText;

        Provider[] providers = Security.getProviders();
        for (Provider p : providers) {
            for (Provider.Service s : p.getServices()) {
                Log.d(TAG, "类型:" + s.getType() + "，算法：" + s.getAlgorithm());
            }
            Log.d(TAG, "----------------------------");
        }

    }

    public void byDES() {

        byte[] key = DESUtil.generateKey();
        byte[] cipherBytes = DESUtil.encrypt(plainText.getBytes(), key);
        byte[] clearBytes = DESUtil.decrypt(cipherBytes, key);

        if (clearBytes != null) {
            String clearText = new String(clearBytes);
            Log.d(TAG, "byDES: clearText = " + clearText);
        }
    }

    public void byAES() {

        byte[] key = AESUtil.generateKey();
        byte[] cipherBytes = AESUtil.encrypt(plainText.getBytes(), key);
        byte[] clearBytes = AESUtil.decrypt(cipherBytes, key);

        if (clearBytes != null) {
            String clearText = new String(clearBytes);
            Log.d(TAG, "byDES: clearText = " + clearText);
        }
    }

    public void byRSA() {
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

    public void byXOR() {
        byte[] cipherBytes = XORUtil.encrypt(plainText.getBytes());//加密
        Log.d(TAG, "byXOR: " + Conversion.toHexString(cipherBytes));

        String clearBytes = new String(XORUtil.encrypt(cipherBytes));//解密

        Log.d(TAG, "byXOR: clearBytes = " + clearBytes);
    }

    public void byBase64() {

        String cipherString = Base64Util.encrypt(plainText);
        Log.d(TAG, "byBase64: cipherString=" + cipherString);

        String clearText = Base64Util.decrypt(cipherString);
        Log.d(TAG, "byBase64: clearText=" + clearText);
    }

    public void byMD5() {

        String result = MD5Util.encryptString(plainText);
        Log.d(TAG, "byMD5: result=" + result);
    }

    public void bySHA() {

        String cipherString = SHAUtil.encryptString(plainText);
        Log.d(TAG, "bySHA: cipherString=" + cipherString);
    }

}
