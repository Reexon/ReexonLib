/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.files;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.aspose.imaging.internal.Exceptions.IO.FileNotFoundException;
import com.google.common.collect.Lists;

import it.reexon.lib.securityOLD.algorithms.MessageDigestAlgorithms;
import it.reexon.lib.securityOLD.checksums.GenerateSecureChecksum;


/**
 * Checks if the two files are equal. 
 * Also check if either file has been corrupted . 
 * 
 * @author Marco Velluto
 * @since Java 1.8
 */
public class CheckFilesUtils
{
    /**
     * Checks the checksum files with algorithm
     * 
     * @param firstFile     file orginal 
     * @param secondFile    file to check
     * @param algorithm     algorithm to use. 
     * 
     * @return true                     If files are equals 
     * @throws IOException              If the first byte cannot be read for any reason other than the end of the file, if the input stream has been closed, or if some other I/O error occurs. 
     * @throws IllegalArgumentException If either params is null  
     * @throws FileNotFoundException    If file not exists
     */
    public static Boolean checkEqualsFiles(File firstFile, File secondFile, MessageDigestAlgorithms algorithm) throws IOException
    {
        if (firstFile == null || secondFile == null)
            throw new IllegalArgumentException("file cannot be null");
        if (algorithm == null)
            throw new IllegalArgumentException("algorithm cannot be null");

        String checksumFirst = GenerateSecureChecksum.getChecksum(firstFile, algorithm);
        String checksumSecond = GenerateSecureChecksum.getChecksum(secondFile, algorithm);
        if (StringUtils.equals(checksumFirst, checksumSecond))
            return true;
        return false;
    }

    /**
     * Checks if the folders are the same and if they contain the same files if two direcotory are equals and content
     * 
     * @param firstDirectory
     * @param secondDirectory
     * @param algorithm
     * @return - true if directories are equals
     * @throws IOException If the first byte cannot be read for any reason other than the end of the file, if the input stream has been closed, or if some other I/O error occurs.
     * @throws IllegalArgumentException If either params is null  
     */
    public static Boolean checkEqualsDirectories(File firstDirectory, File secondDirectory, MessageDigestAlgorithms algorithm) throws IOException
    {
        if (firstDirectory == null || secondDirectory == null)
            throw new IllegalArgumentException("file cannot be null");
        if (algorithm == null)
            throw new IllegalArgumentException("algorithm cannot be null");
        if (!firstDirectory.isDirectory() || !secondDirectory.isDirectory())
            return false;

        List<File> firstFiles = Lists.newArrayList(firstDirectory.listFiles());
        List<File> secondFiles = Lists.newArrayList(secondDirectory.listFiles());

        if (firstFiles.size() != secondFiles.size())
            return false;

        for (File firstFile : firstFiles)
        {
            Boolean isEqulas = Boolean.FALSE;
            for (File secondFile : secondFiles)
            {
                isEqulas = checkEqualsFiles(firstFile, secondFile, MessageDigestAlgorithms.getDefault());
                if (isEqulas)
                    break;
            }
            if (!isEqulas)
                return false;
        }
        return true;
    }
}
