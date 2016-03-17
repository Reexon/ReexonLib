package it.reexon.reexon.lib.security;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import it.reexon.reexon.lib.security.crypt.algorithmics.SecureRandomAlgorithmics;


public final class GenerateSecureString
{
    /**
     * 
     * @return string with length 32
     * @throws NoSuchAlgorithmException
     */
    public static String secureString() throws NoSuchAlgorithmException
    {
        return secureString(32);
    }

    /**
     * 
     * @param stringLength length of string
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String secureString(int stringLength) throws NoSuchAlgorithmException
    {
        return secureString(stringLength, SecureRandomAlgorithmics.SHA1PRNG);
    }

    /**
     * 
     * @param stringLength length of string
     * @param algorithmic use SecureRandomAlgorithmics
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String secureString(int stringLength, String algorithmic) throws NoSuchAlgorithmException
    {
        SecureRandom random = SecureRandom.getInstance(algorithmic);
        return new BigInteger(130, random).toString(stringLength);
    }

}