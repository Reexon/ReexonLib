package it.reexon.lib.securityOLD.checksums;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import it.reexon.lib.securityOLD.algorithms.MessageDigestAlgorithms;
import it.reexon.lib.strings.StringUtils;


/**
 * @author MarcoVelluto
 * @since Java 1.8
 */
@Deprecated

public class MD5Checksum
{
    private static final byte[] buffer = new byte[1024];

    /**
     * Create a checksum in MD5 from file name
     * 
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] createChecksum(String filename) throws IOException
    {
        try (InputStream fis = new FileInputStream(filename);)
        {
            MessageDigest complete = MessageDigest.getInstance(MessageDigestAlgorithms.MD5.getName());

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
        return StringUtils.toHexString(checksum);
    }

}