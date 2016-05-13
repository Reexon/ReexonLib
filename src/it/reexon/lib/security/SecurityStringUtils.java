/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.reexon.lib.manipulation.ManipulationUtils;
import it.reexon.lib.security.algorithmics.SecureRandomAlgorithmics;
import sun.misc.BASE64Encoder;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class SecurityStringUtils
{
    private static final Logger logger = LogManager.getLogger(SecurityStringUtils.class);

    /**
     * Generate a secure string with 32 characters.
     * 
     * @return string with length 32 and SHA1PRNG algorithm
     */
    public static String secureString()
    {
        return secureString(32);
    }

    /**
     * Generate a secure string with variable length with algorithm SHA1PRNG
     * 
     * @param stringLength length of string
     * @return string passed as a parameter length
     * 
     * @throws IllegalArgumentException if stringLength is less than 0
     */
    public static String secureString(int stringLength)
    {
        try
        {
            return secureString(stringLength, SecureRandomAlgorithmics.SHA1PRNG);
        }
        catch (NoSuchAlgorithmException e)
        {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate a secure string with length and alogirthm variables
     * 
     * @param stringLength length of string
     * @param algorithmic algorithm to generate string. Please use {@link it.reexon.lib.security.algorithmics.SecureRandomAlgorithmics}
     * 
     * @return a secure string
     * @throws NoSuchAlgorithmException  if no Provider supports a SecureRandomSpi implementation for the specified algorithm.
     * @throws IllegalArgumentException if stringLength is less than 0
     */
    public static String secureString(int stringLength, String algorithmic) throws NoSuchAlgorithmException
    {
        if (stringLength < 0)
            throw new IllegalArgumentException("String length cannot be less than 0");

        StringBuilder sb = new StringBuilder();
        while (sb.length() < stringLength)
        {
            SecureRandom random = SecureRandom.getInstance(algorithmic);
            sb.append(new BASE64Encoder().encode(new BigInteger(stringLength, random).toString(new Random().nextInt()).getBytes()));
        }
        return sb.substring(0, stringLength);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////// WITH INDENTS ////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Generate a secure string with indents
     * <pre>
     * Example:
     *  U7jM-5UjN-0OTY
     * </pre>
     * @return a string with a length of 3 words of 4 alphanumeric characters each
     */
    public static String secureStringWithIndents()
    {
        return secureStringWithindents(3);
    }

    /**
     * Generate a secure string with indents. 
     * <pre>
     * Example:
     *  U7jM-5UjN-0OTY
     * </pre>
     * 
     * @param numberWords number of words that must be generated
     * @return string string composed of the number of words passed as a parameter , with 4 characters each
     * @throws IllegalArgumentException if numberWords is less than 0
     */
    public static String secureStringWithindents(int numberWords)
    {
        if (numberWords < 0)
            throw new IllegalArgumentException("Number of words cannot be less than 0");

        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (count < numberWords)
        {
            sb.append(ManipulationUtils.shuffle(secureString(3) + new Random().nextInt(9)));
            sb.append("-");
            count++;
        }
        return org.apache.commons.lang3.StringUtils.substringBeforeLast(sb.toString(), "-");
    }
}
