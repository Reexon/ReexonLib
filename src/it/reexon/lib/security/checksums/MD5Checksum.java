package it.reexon.lib.security.checksums;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import it.reexon.lib.convertions.ConvertUtils;
import it.reexon.lib.security.algorithms.MessageDigestAlgorithms;


/**
 * @author MarcoVelluto
 * @see http://stackoverflow.com/questions/304268/getting-a-files-md5-checksum-in-java
 * @since Java 1.8
 */
public class MD5Checksum
{

    /**
     * Create a checksum in MD5 from file name
     * 
     * @param filename
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static byte[] createChecksum(String filename) throws NoSuchAlgorithmException, IOException
    {
        try (InputStream fis = new FileInputStream(filename);)
        {
            MessageDigest complete = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
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
     * How-to for a faster way to convert a byte array to a HEX string
     * 
     * @param filename
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static String getMD5Checksum(String filename) throws NoSuchAlgorithmException, IOException
    {
        byte[] checksum = createChecksum(filename);
        return ConvertUtils.toHexString(checksum);
    }

}