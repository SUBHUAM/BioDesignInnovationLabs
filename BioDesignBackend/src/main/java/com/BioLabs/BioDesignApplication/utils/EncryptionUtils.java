package com.BioLabs.BioDesignApplication.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtils {

    private static final String ALGORITHM = "AES";

    private static SecretKey secretKey;

    static {
        try {
            String encodedKey = "1234567890123456789101";


            byte[] decodedKey = Base64.getDecoder().decode(encodedKey);

            secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing the secret key", e);
        }
    }


    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }
}
