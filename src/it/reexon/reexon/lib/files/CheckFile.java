/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.reexon.lib.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

import it.reexon.reexon.lib.security.algorithms.MessageDigestAlgorithms;
import it.reexon.reexon.lib.security.checksums.GenerateSecureChecksum;


/**
 * Checks if the two files are equal. 
 * Also check if either file has been corrupted . 
 * 
 * @author Marco Velluto
 * @since Java 1.8
 */
public class CheckFile
{
    /**
     * Check the checksum files with algorithm SHA-256
     * 
     * @param firstFile     file orginal
     * @param secondFile    file to check
     * @return - true if files are equals
     *         - null if there was an error         
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static final Boolean checkEqualsFiles(File firstFile, File secondFile) throws FileNotFoundException, IOException
    {
        try
        {
            return checkEqualsFiles(firstFile, secondFile, MessageDigestAlgorithms.SHA_256);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Check the checksum files with algorithm MD5
     * 
     * @param firstFile     file orginal
     * @param secondFile    file to check
     * @return - true if files are equals
     *         - null if there was an error       
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static final Boolean checkEqualsFasterFiles(File firstFile, File secondFile) throws FileNotFoundException, IOException
    {
        try
        {
            return checkEqualsFiles(firstFile, secondFile, MessageDigestAlgorithms.MD5);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Check the checksum files with algorithm SHA-512
     * 
     * @param firstFile     file orginal
     * @param secondFile    filefileName to check
     * @return - true if files are equals
     *         - null if there was an error         
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static final Boolean checkEqualsSlowFiles(File firstFile, File secondFile) throws FileNotFoundException, IOException
    {
        try
        {
            return checkEqualsFiles(firstFile, secondFile, MessageDigestAlgorithms.SHA_512);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Check the checksum files with algorithm
     * 
     * @param firstFile     file orginal 
     * @param secondFile    file to check
     * @param algorithm         algorithm to use. @see it.reexon.reexon.lib.security.algorithms.MessageDigestAlgorithms
     * 
     * @return true             if files are equals 
     * @throws IOException
     * @throws NoSuchAlgorithmException 
     */
    public static Boolean checkEqualsFiles(File firstFile, File secondFile, String algorithm) throws IOException, NoSuchAlgorithmException
    {
        String checksumFirst = GenerateSecureChecksum.getChecksum(firstFile, algorithm);
        String checksumSecond = GenerateSecureChecksum.getChecksum(secondFile, algorithm);
        if (StringUtils.equals(checksumFirst, checksumSecond))
            return true;
        return false;
    }
}
