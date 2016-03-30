package it.reexon.reexon.lib.security.checksums;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import it.reexon.reexon.lib.convertions.ConvertUtility;


public class GenerateSecureChecksum
{

    /**
     * Create a byte[] checksum from file name
     * 
     * @param filename  filename to generate checksum
     * @param algorithm use org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5
     * @return byte[] checksum
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static byte[] createChecksum(String filename, String algorithm) throws NoSuchAlgorithmException, IOException
    {
        try (InputStream fis = new FileInputStream(filename);)
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
     * @param filename  filename to generate checksum
     * @param algorithm use org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5
     * @return String checksum
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getChecksum(String filename, String algorithm) throws NoSuchAlgorithmException, IOException
    {
        byte[] checksum = createChecksum(filename, algorithm);
        return ConvertUtility.byteArrayToHexString(checksum);
    }
}
