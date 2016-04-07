package it.reexon.reexon.lib.security.checksums;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import it.reexon.reexon.lib.convertions.ConvertUtils;
import it.reexon.reexon.lib.security.algorithms.MessageDigestAlgorithms;


public class GenerateSecureChecksum
{

    /**
     * Create a byte[] checksum from file name
     * 
     * @param file  file to generate checksum
     * @param algorithm use org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5
     * @return byte[] checksum
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static byte[] createChecksum(File file, String algorithm) throws NoSuchAlgorithmException, IOException
    {
        try (InputStream fis = new FileInputStream(file);)
        {
            MessageDigest complete = MessageDigest.getInstance(algorithm);
            byte[] buffer = new byte[1024];

            int numRead;

            do
            {
                numRead = fis.read(buffer);
                if (numRead > 0)
                {
                    complete.update(buffer, 0, numRead);
                }
            }
            while (numRead != -1);
            return complete.digest();
        }
    }

    /**
     * Generate a String checksum
     * 
     * @param file  file to generate checksum
     * @param algorithm MessageDigestAlgorithms
     * 
     * @return String checksum
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getChecksum(File file, String algorithm) throws NoSuchAlgorithmException, IOException
    {
        byte[] checksum = createChecksum(file, algorithm);
        return ConvertUtils.byteArrayToHexString(checksum);
    }

    /**
     * Generate a String checksum with MessageDigestAlgorithms SHA-1
     * 
     * @param file  filename to generate checksum
     * 
     * @return String checksum
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getChecksum(File file) throws IOException
    {
        byte[] checksum = null;
        try
        {
            checksum = createChecksum(file, MessageDigestAlgorithms.SHA_1);
        }
        catch (@SuppressWarnings("unused") NoSuchAlgorithmException e)
        {}
        return ConvertUtils.byteArrayToHexString(checksum);
    }

}
