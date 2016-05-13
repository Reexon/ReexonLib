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
import it.reexon.lib.security.algorithmics.MessageDigestAlgorithms;
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
     * @throws IOException              If some other I/O error occurs.
     * @throws IllegalArgumentException If file is null or algorithm is null.
     * @throws FileNotFoundException    If file not exists.
     */
    public static byte[] createChecksum(File file, MessageDigestAlgorithms algorithm) throws IOException
    {
        if (file == null)
            throw new IllegalArgumentException("File is null!");

        if (algorithm == null)
            throw new IllegalArgumentException("Algorithm is null!");

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
     * @throws IOException              If some other I/O error occurs.
     * @throws IllegalArgumentException If file is null or algorithm is null.  
     * @throws FileNotFoundException    If file not exists.
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
     * @throws IOException              If some other I/O error occurs.
     * @throws IllegalArgumentException If file is null.  
     * @throws FileNotFoundException    If file not exists.
     */
    public static String getChecksum(File file) throws IOException
    {
        byte[] checksum = null;
        checksum = createChecksum(file, MessageDigestAlgorithms.getDefault());
        return StringUtils.toHexString(checksum);
    }

    // TODO public static void writeChecksumFromFile(File file, boolean continueWithErrors);

    /**
     * Generates a file containing all checksums of the files in those folders .
     * Create a file in any of the specified below direcotory.
     * @param file
     * @throws IOException              If some other I/O error occurs.
     * @throws IllegalArgumentException If file is null
     * @throws FileNotFoundException    If file doesn't exist
     */
    public static void writeChecksumFromFile(File file) throws IOException
    {
        if (file == null)
            throw new IllegalArgumentException("File cannot be null");

        if (!file.exists())
            throw new FileNotFoundException("File must be exists");

        if (file.isDirectory())
        {
            String path = file.getPath();
            File txtFile = new File(path + "\\checksum.txt");
            List<File> files = IOUtils.importFiles(file, true);
            List<String> checksums = new LinkedList<>();
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
                        e.printStackTrace();
                    }
                }
            }
            try
            {
                org.apache.commons.io.FileUtils.writeLines(txtFile, checksums);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if (file.isFile())
        {
            String path = file.getPath();
            File txtFile = new File(path + "\\checksum-" + file.getName() + ".txt");
            List<String> checksums = new LinkedList<>();
            checksums.add(file.getName() + PATTERN + getChecksum(file));
            try
            {
                org.apache.commons.io.FileUtils.writeLines(txtFile, checksums);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
