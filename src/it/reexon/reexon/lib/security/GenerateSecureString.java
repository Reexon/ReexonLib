package it.reexon.reexon.lib.security;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import it.reexon.reexon.lib.security.crypt.algorithmics.SecureRandomAlgorithmics;
import sun.misc.BASE64Encoder;


public final class GenerateSecureString
{
    /**
     * 
     * @return string with length 32 and SHA1PRNG algorithm
     */
    public static String secureString()
    {
        return secureString(32);
    }

    /**
     * 
     * @param stringLength length of string
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String secureString(int stringLength)
    {
        try
        {
            return secureString(stringLength, SecureRandomAlgorithmics.SHA1PRNG);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
        return new BASE64Encoder().encode(new BigInteger(130, random).toString(stringLength).getBytes());
    }
}