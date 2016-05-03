/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.files;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.aspose.imaging.internal.Exceptions.IO.FileNotFoundException;

import it.reexon.lib.security.algorithms.MessageDigestAlgorithms;
import it.reexon.lib.security.checksums.GenerateSecureChecksum;


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
     * Check the checksum files with algorithm
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
}
