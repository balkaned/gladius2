package com.balkaned.gladius.util;


import io.netty.handler.codec.base64.Base64;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class EncryptarMD5 {

    public String getMD5(String input) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String decodeMD5(String input){
        String md5 = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            md5 = String.valueOf(new BigInteger(1, digest));
        } catch (NoSuchAlgorithmException e) {
            log.info("Error decode");
        }

        return md5;
    }

    /*public String Decrypt(String md5_hash) throws Exception {

        String api_key = "YOUR_VIP_KEY";
        URL md5online = new URL("https://www.md5online.org/api.php?d=1&p="+api_key+"&h="+md5_hash);
        BufferedReader in = new BufferedReader(new InputStreamReader(md5online.openStream()));

        String result = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            result = result+inputLine;
        in.close();

        return result;
    }

    public String decodeMD5(String input) throws NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher aesCipher = Cipher.getInstance("MD%");
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] cleartext = desCipher.doFinal(ciphertext);
    }*/

}
