package it.reexon.reexon.lib.security.crypt.algorithmics;

import java.security.MessageDigest;

import it.reexon.reexon.lib.security.algorithms.MessageDigestAlgorithms;


/**
 * SHA-1 is a hashing algorithm similar in structure to MD5, but producing a digest of 160 bits (20 bytes).
 * Because of the large digest size, it is less likely that two different messages will have the same SHA-1 message digest. 
 * For this reason SHA-1 is recommended in preference to MD5.
 */
public class SHACrypt
{
    public static void main(String arg[]) throws Exception
    {
        System.out.println(cripta("StringaSHA1"));
        System.out.println(cripta("Prova2-SHA256"));
    }

    public static byte[] cripta(String x) throws Exception
    {
        MessageDigest mess = null;
        mess = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_1);
        mess.reset();
        mess.update(x.getBytes());
        return mess.digest();
    }

}
