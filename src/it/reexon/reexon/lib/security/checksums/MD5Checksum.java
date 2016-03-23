package it.reexon.reexon.lib.security.checksums;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import it.reexon.reexon.lib.convertions.ConvertUtility;


/**
 * 
 * @author MarcoVelluto
 * @see http://stackoverflow.com/questions/304268/getting-a-files-md5-checksum-in-java
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
        InputStream fis = new FileInputStream(filename);

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
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

        fis.close();
        return complete.digest();
    }

    /**
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String createChecksumString(String fileName) throws IOException
    {
        try (FileInputStream fis = new FileInputStream(new File("foo"));)
        {
            String md5 = DigestUtils.md5Hex(fis);
            return md5;
        }
    }

    /**
     * 
     * @param filename
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static byte[] createChecksumV1(String filename) throws NoSuchAlgorithmException, IOException
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try (InputStream is = Files.newInputStream(Paths.get("file.txt")); DigestInputStream dis = new DigestInputStream(is, md))
        {
            /* Read decorated stream (dis) to EOF as normal... */
        }
        byte[] digest = md.digest();
        return digest;
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
        return ConvertUtility.byteArrayToHexString(checksum);
    }

}