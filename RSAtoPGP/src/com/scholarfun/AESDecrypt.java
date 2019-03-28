package com.scholarfun;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.util.Base64;
import java.util.Scanner;

import static com.scholarfun.AESEncrypt.setKey;
import static java.nio.charset.StandardCharsets.UTF_8;

public class AESDecrypt {
    private static SecretKeySpec secretKey;
    private static byte[] key;
    static String path = "D:\\RSAtoPGP";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("encryptedText.txt"));
        String line = reader.readLine();
        System.out.println(line);

        File filePublicKey = new File(path + "/AES_Key.txt");
        FileInputStream fis = new FileInputStream(path + "/AES_Key.txt");
        byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
        fis.read(encodedPublicKey);
        fis.close();
        final String key = new String(encodedPublicKey, UTF_8);
        System.out.println(key);



        String decodedMessage = AESDecrypt.decrypt(line, key);

        System.out.println(decodedMessage);
    }
    public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
