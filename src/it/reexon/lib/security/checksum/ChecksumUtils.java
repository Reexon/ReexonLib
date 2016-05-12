/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security.checksum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import it.reexon.lib.files.IOUtils;
import it.reexon.lib.securityOLD.algorithms.MessageDigestAlgorithms;
import it.reexon.lib.strings.StringUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class ChecksumUtils
{
    private static final byte[] BUFFER = new byte[1024];
    private static final String PATTERN = " - Checksum " + MessageDigestAlgorithms.getDefault() + ": ";

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
                numRead = fis.read(BUFFER);
                if (numRead > 0)
                {
                    complete.update(BUFFER, 0, numRead);
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
     * Generate a String checksum with MessageDigestAlgorithms SHA-256
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

    public static void writeChecksumFromFile(File file)
    {
        if (file.isDirectory())
        {
            String path = file.getPath();
            File txtFile = new File(path + "\\checksum.txt");
            String info = "Checksum files with algorithm " + MessageDigestAlgorithms.getDefault();
            //is directory
            List<File> files = IOUtils.importFiles(file, true);
            List<String> checksums = new LinkedList<>();
            checksums.add(info);
            int c = 1;
            for (File fileToCheck : files)
            {
                if (fileToCheck.isDirectory())
                    writeChecksumFromFile(fileToCheck);
                else
                {
                    try
                    {
                        checksums.add(c + ". " + fileToCheck.getName() + PATTERN + getChecksum(fileToCheck));
                        c++;
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace(); //TODO
                    }
                }
            }
            try
            {
                org.apache.commons.io.FileUtils.writeLines(txtFile, checksums);
            }
            catch (IOException e)
            {
                e.printStackTrace(); //TODO
            }
        }

    }
}
