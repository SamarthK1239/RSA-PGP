package com.scholarfun;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.*;

public class AESKeyGen {
    static Cipher cipher;
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException,
            InvalidKeyException, IllegalBlockSizeException, IOException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);

        SecretKey secretKey = keyGenerator.generateKey();
        cipher = Cipher.getInstance("AES");

        byte encoded[] = secretKey.getEncoded();
        String encodedKey = Base64.getEncoder().encodeToString(encoded);
        System.out.println(encodedKey);

        PrintStream fileOut = new PrintStream((new FileOutputStream("AES_Key.txt")));
        fileOut.println(encodedKey);

        /*String plainText = "Hello World";

        String encryptedText = encrypt(plainText, secretKey);
        System.out.println(encryptedText);
        PrintStream fileO = new PrintStream(new FileOutputStream("encryptedText.txt"));
        fileO.println(encryptedText);

        BufferedReader reader = new BufferedReader(new FileReader("encryptedText.txt"));
        String line = reader.readLine();
        String decryptedText = decrypt(line, secretKey);
        System.out.println(decryptedText);



*/
    }
  /*  public static String encrypt(String plainText, SecretKey secretKey) throws InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException {
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();

        String encryptedString = encoder.encodeToString(encryptedByte);
        return encryptedString;
    }
    public static String decrypt(String encryptedString, SecretKey secretKey) throws BadPaddingException,
            IllegalBlockSizeException, InvalidKeyException {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encodedStringBytes = decoder.decode(encryptedString);

        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedByte = cipher.doFinal(encodedStringBytes);
        String decryptedText = new String(decryptedByte);
        return decryptedText;
    }*/
}


