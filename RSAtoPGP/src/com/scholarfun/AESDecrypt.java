package com.scholarfun;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class AESDecrypt {
    private static SecretKeySpec secretKey;
    private static byte[] key;
    static String path = "C:\\Users\\samarth\\IdeaProjects\\PGP";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("encryptedText.txt"));
        String line = reader.readLine();
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        final String AESKey;

        System.out.print("Enter your key: ");
        AESKey = scanner.nextLine();


        String decodedMessage = AESDecrypt.decrypt(line, AESKey);

        System.out.println(decodedMessage);
    }
    public static String decrypt(String strToDecrypt, String secret)
    {
        System.out.println("Key: "+secret);
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
