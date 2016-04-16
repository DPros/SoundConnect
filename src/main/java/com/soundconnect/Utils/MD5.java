package com.soundconnect.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MD5 {

    public static String getHash(String str) {

        MessageDigest md5;
        String hexString;
        try {

            md5 = MessageDigest.getInstance("md5");

            md5.reset();
            md5.update(str.getBytes());

            byte messageDigest[] = md5.digest();

            BigInteger bigInt = new BigInteger(1, messageDigest);
            hexString = bigInt.toString(16);

            while (hexString.length() < 32) {
                hexString = "0" + hexString;
            }

        } catch (NoSuchAlgorithmException e) {
            return getHash("error");
        }

        return hexString;
    }
    public static String getRandomHash(){
    	Random rnd = new Random();
    	return getHash("lms"+rnd.nextInt());
    }
    public static void main(String[] args) {

        MD5 md5 = new MD5();
        System.out.println(md5.getHash("user"));
        System.out.println(md5.getHash("user").length());
        for (int i=0;i<100;i++){
        	System.out.println(md5.getRandomHash());
        }
    }
}
