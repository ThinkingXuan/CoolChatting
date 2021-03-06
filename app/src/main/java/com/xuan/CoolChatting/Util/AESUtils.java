package com.xuan.CoolChatting.Util;

import android.util.Base64;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * http://www.cnblogs.com/freeliver54/archive/2011/10/08/2202136.html
 */
public class AESUtils {
    private static String CHARSETNAME = "UTF-8";// 编码
    private static String ALGORITHM = "AES";// 加密类型


    private static byte[] getKey() {
        byte[] seed = "moziqi@#$%^&*!123456".getBytes();
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        sr.setSeed(seed);
        kg.init(128, sr);
        SecretKey sk = kg.generateKey();
        byte[] key = sk.getEncoded();
        return key;
    }

    /**
     * 对str进行DES加密
     *
     * @param str
     * @return
     */
    public static String getEncryptString(String str) {
        //long time = System.currentTimeMillis();
        try {
            SecretKeySpec key = new SecretKeySpec(getKey(), ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = str.getBytes(CHARSETNAME);
            byte[] doFinal = cipher.doFinal(bytes);
            return Base64.encodeToString(doFinal, Base64.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //VolleyLog.e("use time is %d", System.currentTimeMillis() - time);
        }
    }

    /**
     * 对str进行DES解密
     *
     * @param str
     * @return
     */
    public static String getDecryptString(String str) {
        //long time = System.currentTimeMillis();
        try {
            SecretKeySpec key = new SecretKeySpec(getKey(), ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] bytes = Base64.decode(str, Base64.DEFAULT);
            byte[] doFinal = cipher.doFinal(bytes);
            return new String(doFinal, CHARSETNAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //VolleyLog.e("use time is %d", System.currentTimeMillis() - time);
        }
    }


    /**
     * 对bytes进行DES加密
     *
     * @param bytes
     * @return
     */
    public static byte[] getEncryptBytes(byte[] bytes) {
        //long time = System.currentTimeMillis();
        try {
            SecretKeySpec key = new SecretKeySpec(getKey(), ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] doFinal = cipher.doFinal(bytes);
            return Base64.encode(doFinal, Base64.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //VolleyLog.e("use time is %d", System.currentTimeMillis() - time);
        }
    }

    /**
     * 对sBytes进行DES解密
     *
     * @param sBytes
     * @return
     */
    public static byte[] getDecryptBytes(byte[] sBytes) {
        //long time = System.currentTimeMillis();
        try {
            SecretKeySpec key = new SecretKeySpec(getKey(), ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] bytes = Base64.decode(sBytes, Base64.DEFAULT);
            byte[] doFinal = cipher.doFinal(bytes);
            return doFinal;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //VolleyLog.e("use time is %d", System.currentTimeMillis() - time);
        }
    }


    /**
     * 对数据进行装箱
     *
     * @param sBytes
     * @return
     */
    public static Byte[] autoBoxing(byte[] sBytes) {
        Byte[] aByte = new Byte[sBytes.length];
        for (int i = 0; i < sBytes.length; i++) {
            aByte[i] = Byte.valueOf(sBytes[i]);
        }
        return aByte;
    }

    public static byte[] autoBoxing(Byte[] sBytes) {
        byte[] aByte = new byte[sBytes.length];
        for (int i = 0; i < sBytes.length; i++) {
            aByte[i] = Byte.valueOf(sBytes[i]);
        }
        return aByte;
    }
}