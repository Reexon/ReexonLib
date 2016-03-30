package it.reexon.reexon.lib.files;

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
     * @param firstFileName     fileName orginal
     * @param secondFileName    fileName to check
     * @return - true if files are equals
     *         - null if there was an error         
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static final Boolean checkFiles(String firstFileName, String secondFileName) throws FileNotFoundException, IOException
    {
        try
        {
            return generalCheckFiles(firstFileName, secondFileName, MessageDigestAlgorithms.SHA_256);
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
     * @param firstFileName     fileName orginal
     * @param secondFileName    fileName to check
     * @return - true if files are equals
     *         - null if there was an error       
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static final Boolean checkFasterFiles(String firstFileName, String secondFileName) throws FileNotFoundException, IOException
    {
        try
        {
            return generalCheckFiles(firstFileName, secondFileName, MessageDigestAlgorithms.MD5);
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
     * @param firstFileName     fileName orginal
     * @param secondFileName    fileName to check
     * @return - true if files are equals
     *         - null if there was an error         
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static final Boolean checkSlowFiles(String firstFileName, String secondFileName) throws FileNotFoundException, IOException
    {
        try
        {
            return generalCheckFiles(firstFileName, secondFileName, MessageDigestAlgorithms.SHA_512);
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
     * @param firstFileName     fileName orginal 
     * @param secondFileName    fileName to check
     * @param algorithm         algorithm to use. @see it.reexon.reexon.lib.security.algorithms.MessageDigestAlgorithms
     * 
     * @return true             if files are equals 
     * @throws IOException
     * @throws NoSuchAlgorithmException 
     */
    public static Boolean generalCheckFiles(String firstFileName, String secondFileName, String algorithm)
            throws IOException, NoSuchAlgorithmException
    {
        String checksumFirst = GenerateSecureChecksum.getChecksum(firstFileName, algorithm);
        String checksumSecond = GenerateSecureChecksum.getChecksum(secondFileName, algorithm);
        if (StringUtils.equals(checksumFirst, checksumSecond))
            return true;
        return false;
    }
}
