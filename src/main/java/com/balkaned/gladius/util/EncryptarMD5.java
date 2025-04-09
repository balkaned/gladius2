package com.balkaned.gladius.util;


import lombok.extern.slf4j.Slf4j;
import java.math.BigInteger;
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

}
