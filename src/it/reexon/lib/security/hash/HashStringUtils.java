/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import it.reexon.lib.security.algorithmics.MessageDigestAlgorithms;
import it.reexon.lib.strings.StringUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class HashStringUtils
{
    /**
     * Generate a hash string from password. Using algorithm SHA-256
     * 
     * @param passwordToHash    Password to hash.
     * 
     * @return  - The hashed password.
     * @throws IllegalArgumentException If password is null.
     */
    public static String generateSecurePassword(String passwordToHash)
    {
        return generateSecurePassword(passwordToHash, MessageDigestAlgorithms.getDefault());
    }

    /**
     * Generate a hash string from password, salt and algorithm
     * 
     * @param passwordToHash    Password to hash.
     * @param algorithm         Algorithm with which to make the hash function.
     * 
     * @return  - The hashed password.
     * @throws IllegalArgumentException If password or algorithm are null.
     */
    public static String generateSecurePassword(String passwordToHash, MessageDigestAlgorithms algorithm)
    {
        return generateSecurePassword(passwordToHash, null, algorithm);
    }

    /**
     * Generate a hash string from password, salt. Using algorithm SHA-256
     * 
     * @param passwordToHash    Password to hash.
     * @param salt              The salt.
     * 
     * @return  - The hashed password.
     * @throws IllegalArgumentException If password is null.
     */
    public static String generateSecurePassword(String passwordToHash, byte[] salt)
    {
        return generateSecurePassword(passwordToHash, salt, MessageDigestAlgorithms.getDefault());
    }

    /**
     * Generate a hash string from password, salt and algorithm
     * 
     * @param passwordToHash    Password to hash.
     * @param salt              The salt.
     * @param algorithm         Algorithm with which to make the hash function.
     * 
     * @return  - The hashed password.
     * @throws IllegalArgumentException If password or algorithm are null.
     */
    public static String generateSecurePassword(String passwordToHash, byte[] salt, MessageDigestAlgorithms algorithm)
    {
        if (passwordToHash == null)
            throw new IllegalArgumentException("Password to hash cannot be null");

        if (algorithm == null)
            throw new IllegalArgumentException("Algorithm cannot be null");

        //Create MessageDigest instance
        try
        {
            MessageDigest md = MessageDigest.getInstance(algorithm.getName());

            byte[] bytes;
            if (salt == null)
            {
                md.update(passwordToHash.getBytes());
                bytes = md.digest();
            }
            else
            {
                md.update(salt);
                bytes = md.digest(passwordToHash.getBytes());
            }

            //This bytes[] has byte in decimal format
            //Convert it to hexadecimal format
            //Get complete hashed password in hex format
            return StringUtils.toHexString(bytes);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
