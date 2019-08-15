package com.teamcs.mm.professionalwebdeveloper;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256Cipher {
    private byte[] ivBytes;
    private AlgorithmParameterSpec ivSpec;
    private byte[] keyBytes;
    private SecretKeySpec keySpec;

    public AES256Cipher(String key, String iv) {
        try {
            this.ivBytes = iv.getBytes("UTF-8");
            this.keyBytes = key.getBytes("UTF-8");
            this.ivSpec = new IvParameterSpec(this.ivBytes);
            this.keySpec = new SecretKeySpec(this.keyBytes, "AES");
        } catch (UnsupportedEncodingException e) {
        }
    }

    public String aesEncode(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] textBytes = str.getBytes("UTF-8");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, this.keySpec, this.ivSpec);
        return Base64.encodeToString(cipher.doFinal(textBytes), 2);
    }

    public String aesDecode(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] textBytes = Base64.decode(str, 0);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, this.keySpec, this.ivSpec);
        return new String(cipher.doFinal(textBytes), "UTF-8");
    }
}
