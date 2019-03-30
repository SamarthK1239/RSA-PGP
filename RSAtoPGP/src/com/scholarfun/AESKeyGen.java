package com.scholarfun;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.*;

public class AESKeyGen {
    static Cipher cipher;
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);

        SecretKey secretKey = keyGenerator.generateKey();
        cipher = Cipher.getInstance("AES");

        byte encoded[] = secretKey.getEncoded();
        String encodedKey = Base64.getEncoder().encodeToString(encoded);
        System.out.println(encodedKey);

        PrintStream fileOut = new PrintStream((new FileOutputStream("AES_Key.txt")));
        fileOut.println(encodedKey);

    }
}


