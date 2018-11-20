package com.android.mazhengyang.library.encryption;

import android.util.Log;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by mazhengyang on 18-9-7.
 */

public class DESUtil {

    private static final String TAG = "Encryption.DESUtil";

    /**
     * 概述：对称加密算法
     * 优点：算法公开、计算量小、加密速度快、加密效率高
     * 缺点：双方都使用同样密钥，安全性得不到保证
     */

    /**
     * DES一共有电子密码本模式（ECB）、加密分组链接模式（CBC）、加密反馈模式（CFB）和输出反馈模式（OFB）四种模式
     * <p>
     * PKCS5Padding是填充模式，还有其它的填充模式，如PKCS7Padding等
     */

    private static String ALGORITHM = "DES";

    private final static String TRANSFORMATION = "DES/CBC/PKCS5Padding"; //加密方式/工作模式/填充模式

    private static byte[] iv;

    static {
        byte[] randByte = new byte[8]; //初始化向量参数，AES 为16bytes
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randByte);
        iv = randByte;
        Log.d(TAG, "static initializer: randByte toHexString = " + Conversion.toHexString(randByte));
    }

    /**
     * KeyGenerator和 SecretKeyFactory，都是javax.crypto的包，
     * 生成的key主要是提供给AES，DES，3DES，MD5，SHA1等对称和单向加密算法。
     *
     *KeyPairGenerator和 KeyFactory，都是java.security的包，生成的key主要是提供给DSA，RSA，EC等非对称加密算法
     */


    /**
     * KeyGenerator:密钥生成器
     * getAlgorithm();获得算法名称
     * getInstance();通过指定算法,亦可指定提供者来构造KeyGenerator对象,有多个重载方法
     * getProvider();返回此算法实现的提供商
     * init(SecureRandom sRandoom);用于初始化KeyGenerator对象,通过随机源的方式
     * init(int size);通过指定生成秘钥的大小,来初始化的方式
     * init(AlgorithmParameterSpec params);通过指定参数集来初始化
     * init(AlgorithmParameterSpec params,SecureRandom sRandoom);通过指定参数集和随机数源的方式生成
     * init(int arg0, SecureRandom arg1);通过指定大小和随机源的方式产生
     * generatorKey();生成秘钥
     *
     * @return
     */

    public static byte[] generateKey() {

        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(ALGORITHM);//实例化一个用DES加密算法的密钥生成器
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //   keyGenerator.init(56);//密钥生成器，56位数据位，8位校验位，对64位数据块加密
        SecretKey secretKey = keyGenerator.generateKey();//生成密钥
        byte[] keyByte = secretKey.getEncoded();
        Log.d(TAG, "generateKey: keyByte toHexString = " + Conversion.toHexString(keyByte));

        return keyByte;//返回基本编码格式的密钥，如果此密钥不支持编码，则返回null。
    }

    /**
     * 加密
     * <p>
     * cipher.init（)一共有三个参数：mode, key, zeroIv，
     * zeroIv就是初始化向量，作用主要是用于产生密文的第一个block，以使最终生成的密文产生差异（明文相同的情况下），
     * 使密码攻击变得更为困难，除此之外iv并无其它用途，因此iv通过随机方式产生，是简便有效的途径
     * <p>
     * 工作模式、填充模式、初始化向量这三种因素一个都不能少。否则，如果不指定的话，那么就要程序调用默认实现
     */

    public static byte[] encrypt(byte[] data, byte[] key) {

        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);// 定义加密算法
        byte[] cipherBytes = null;

        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION); //创建密码器
            //用密钥secretKey，以加密的方式用密钥初始化
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
            cipherBytes = cipher.doFinal(data); //加密数据
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "encrypt: cipherBytes toHexString = " + Conversion.toHexString(cipherBytes));

        return cipherBytes;
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     */

    public static byte[] decrypt(byte[] data, byte[] key) {

        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        byte[] clearBytes = null;

        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            //用密钥secretKey，以解密的方式用密钥初始化
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
            clearBytes = cipher.doFinal(data); //解密数据
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "decrypt: clearBytes toHexString = " + Conversion.toHexString(clearBytes));

        return clearBytes;
    }


}
