/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import it.reexon.lib.security.algorithmics.SecureRandomAlgorithmics;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class SecureSaltUtils
{
    private static final int BIT_SALT = 16;
    private static final String ALGORITHM = SecureRandomAlgorithmics.SHA1PRNG;

    /**
     * Create a random 16 bit salt using SHA1PRNG algorithm
     * 
     * <pre>
     * Note:
     * SHA1PRNG algorithm is used as cryptographically strong pseudo-random number generator based on the SHA-1 message digest algorithm. 
     * Note that if a seed is not provided, it will generate a seed from a true random number generator (TRNG).
     * </pre>
     * 
     * @return the salt
     * @throws GeneralSecurityException If there was a mistake with the salt generation
     */
    public static byte[] getSalt() throws GeneralSecurityException
    {
        return getSalt(BIT_SALT, ALGORITHM);
    }

    /**
     * Create a random salt using SHA1PRNG algorithm
     * @param size size of salt
     * <pre>
     * Note:
     * SHA1PRNG algorithm is used as cryptographically strong pseudo-random number generator based on the SHA-1 message digest algorithm. 
     * Note that if a seed is not provided, it will generate a seed from a true random number generator (TRNG).
     * </pre>
     * 
     * @return the salt
     * @throws GeneralSecurityException If there was a mistake with the salt generation
     * @throws IllegalArgumentException If size is less than 0
     */
    public static byte[] getSalt(int size) throws GeneralSecurityException
    {
        return getSalt(size, ALGORITHM);
    }

    /**
     * Create a random salt using SHA1PRNG algorithm
     * @param size      size of salt
     * @param algorithm algorihm to use. Please use {@linkplain{@link it.reexon.lib.security.algorithmics.SecureRandomAlgorithmics}
     * 
     * @return the salt
     * @throws NoSuchAlgorithmException  If no Provider supports a SecureRandomSpi implementation for the specified algorithm.
     * @throws IllegalArgumentException if szie is less than 0 or algorithm is null
     */
    public static byte[] getSalt(int size, String algorithm) throws NoSuchAlgorithmException
    {
        if (size < 0)
            throw new IllegalArgumentException("String length cannot be less than 0");

        if (algorithm == null)
            throw new IllegalArgumentException("Algorithm cannot be null");

        SecureRandom sr = SecureRandom.getInstance(algorithm);

        //Create array for salt
        byte[] salt = new byte[size];

        sr.nextBytes(salt);
        return salt;
    }
}
