package com.scholarfun;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class EncryptMessage {
    String path = "D:\\RSAtoPGP";
    public static PublicKey LoadKeyPair(String path, String algorithm)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        File filePublicKey = new File(path + "/public.key");
        FileInputStream fis = new FileInputStream(path + "/public.key");
        byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
        fis.read(encodedPublicKey);
        fis.close();


        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
                encodedPublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        return publicKey;
    }
    public static String encryptMessage(String plainText, PublicKey publicKey) throws InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText =  encryptCipher.doFinal(plainText.getBytes(UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decryptMessage(String cipherText, PrivateKey privateKey) throws NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decryptCipher.doFinal(bytes), UTF_8);
    }
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException,
            IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException {

        PrintStream fileOut = new PrintStream(new FileOutputStream("EncodedText.txt"));
        PublicKey publicKey = LoadKeyPair("D:\\RSAtoPGP", "RSA");

        BufferedReader reader = new BufferedReader(new FileReader("AES_Key.txt"));


        Scanner scanner = new Scanner(System.in);

        System.out.println("Encode String or AES key? ");
        String command = scanner.nextLine();
        command = command.toUpperCase();

        if(command.equals("STRING")) {
            System.out.print("Enter your message or your AES key: ");
            String message = scanner.nextLine();

            String cipherText = encryptMessage(message, publicKey);
            System.out.println(cipherText);
            fileOut.println(cipherText);
        }
        else if (command.equals("AES KEY")){
            String message = reader.readLine();

            String cipherText = encryptMessage(message, publicKey);
            System.out.println(cipherText);
            fileOut.println(cipherText);
        }





    }
}
