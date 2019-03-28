package com.scholarfun;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class AESEncrypt {
    static Cipher cipher;

    public static void main(String[] args) throws IOException, BadPaddingException, InvalidKeyException,
            IllegalBlockSizeException {
        Scanner scanner = new Scanner(System.in);

        BufferedReader reader = new BufferedReader(new FileReader("AES_Key.txt"));
        String AESKey = reader.readLine();
        System.out.println(AESKey);
        Base64.Decoder decoder = Base64.getDecoder();

        byte[] decodedKey = decoder.decode(AESKey);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        System.out.print("Enter your text: ");
        final String plainText = scanner.nextLine();




        String encryptedString = AESEncrypt.encrypt(plainText, AESKey) ;
        //String decryptedString = AESEncrypt.decrypt(encryptedString, secretKey) ;


        System.out.println(encryptedString);
        //System.out.println(decryptedString);

        /*String encryptedText = encrypt(plainText, originalKey);
        System.out.println(encryptedText);*/
        PrintStream fileO = new PrintStream(new FileOutputStream("encryptedText.txt"));
        fileO.println(encryptedString);
    }

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

