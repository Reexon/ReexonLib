package it.reexon.lib.security.checksums;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.aspose.imaging.internal.Exceptions.IO.FileNotFoundException;

import it.reexon.lib.security.algorithms.MessageDigestAlgorithms;
import it.reexon.lib.strings.StringUtils;


public class GenerateSecureChecksum
{

    private static final byte[] buffer = new byte[1024];

    /**
     * Create a byte[] checksum from file name
     * 
     * @param file  file to generate checksum
     * @param algorithm use  to use
     * @return byte[] checksum
     *
     * @throws IOException              If the first byte cannot be read for any reason other than the end of the file, if the input stream has been closed, or if some other I/O error occurs.
     * @throws NullPointerException     If file is null  
     * @throws FileNotFoundException    If file not exists
     * @throws IllegalArgumentException If algorithm is not valid
     */
    public static byte[] createChecksum(File file, MessageDigestAlgorithms algorithm) throws IOException
    {
        if (file == null)
            throw new NullPointerException("File is null!");

        if (!file.exists())
            throw new FileNotFoundException("File not found!");

        try (InputStream fis = new FileInputStream(file);)
        {
            MessageDigest complete = MessageDigest.getInstance(algorithm.getName());

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
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate a String checksum
     * 
     * @param file  file to generate checksum
     * @param algorithm 
     * 
     * @return String checksum
     * @throws IOException              If the first byte cannot be read for any reason other than the end of the file, if the input stream has been closed, or if some other I/O error occurs.
     */
    public static String getChecksum(File file, MessageDigestAlgorithms algorithm) throws IOException
    {
        byte[] checksum = createChecksum(file, algorithm);
        return StringUtils.toHexString(checksum);
    }

    /**
     * Generate a String checksum with MessageDigestAlgorithms SHA-1
     * 
     * @param file  filename to generate checksum
     * 
     * @return String checksum
     * @throws IOException
     */
    public static String getChecksum(File file) throws IOException
    {
        byte[] checksum = null;
        checksum = createChecksum(file, MessageDigestAlgorithms.SHA_256);
        return StringUtils.toHexString(checksum);
    }

}
