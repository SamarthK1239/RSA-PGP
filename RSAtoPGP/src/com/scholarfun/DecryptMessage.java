package com.scholarfun;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import static com.scholarfun.EncryptMessage.decryptMessage;

public class DecryptMessage {
    String path = "D:\\RSAtoPGP";

    public static PrivateKey LoadKeyPair(String path, String algorithm)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        File filePublicKey = new File(path + "/public.key");
        FileInputStream fis = new FileInputStream(path + "/public.key");
        byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
        fis.read(encodedPublicKey);
        fis.close();

        File filePrivateKey = new File(path + "/private.key");
        fis = new FileInputStream(path + "/private.key");
        byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
        fis.read(encodedPrivateKey);
        fis.close();

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);


        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
                encodedPrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        return privateKey;
    }
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException,
            IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {

        PrivateKey privateKey =  LoadKeyPair("D:\\RSAtoPGP", "RSA");
        BufferedReader reader = new BufferedReader(new FileReader("EncodedText.txt"));
        String line = reader.readLine();

        String decryptedMessage = decryptMessage(line, privateKey);
        System.out.println(decryptedMessage);
    }
}
