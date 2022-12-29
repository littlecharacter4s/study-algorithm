package com.lc.structure.hash;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * 哈希函数
 *
 * @author gujixian
 * @since 2022/12/29
 */
public class Hash {
    private MessageDigest hash;

    public Hash(String algorithm) {
        try {
            this.hash = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String hashCode(String s) {
        return DatatypeConverter.printHexBinary(hash.digest(s.getBytes())).toUpperCase();
    }

    public static void main(String[] args) {
        System.out.print("支持的哈希算法：");
        for (String algorithm : Security.getAlgorithms("MessageDigest")) {
            System.out.print(algorithm + " ");
        }
        System.out.println();

        String algorithm = "SHA-256";
        Hash hash = new Hash(algorithm);
        for (int i = 0; i < 10; i++) {
            System.out.println(hash.hashCode("zhonghuarenmingongheguo" + i));
        }
    }
}
