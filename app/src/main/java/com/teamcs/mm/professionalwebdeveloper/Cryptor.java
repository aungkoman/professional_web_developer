package com.teamcs.mm.professionalwebdeveloper;


public class Cryptor {
    private static Cryptor cryptor;
    private AES256Cipher cipher;
    private String iv;
    private String key;

    public static Cryptor getInstance() {
        if (cryptor == null) {
            cryptor = new Cryptor();
        }
        return cryptor;
    }

    public static Cryptor getInstance(String customKey) {
        if (cryptor == null) {
            cryptor = new Cryptor(customKey);
        }
        return cryptor;
    }

    private Cryptor() {
        this.iv = "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000";
        this.key = "a7ed82bd28578ca7";
        this.cipher = new AES256Cipher(this.key, this.iv);
    }

    private Cryptor(String customKey) {
        this.iv = "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000";
        this.key = "a7ed82bd28578ca7";
        this.cipher = new AES256Cipher(customKey, this.iv);
    }

    public String encrypt(String text) {
        String encryptedString = null;
        try {
            encryptedString = this.cipher.aesEncode(text);
        } catch (Exception e) {
        }
        return encryptedString;
    }

    public String decrypt(String text) {
        String decryptedString = null;
        try {
            decryptedString = this.cipher.aesDecode(text);
        } catch (Exception e) {
           // Log.s(e.getMessage());
        }
        return decryptedString;
    }
}
