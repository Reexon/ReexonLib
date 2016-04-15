/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.files;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;


/**
 * @author Marco Velluto.
 * @since Java 1.8
 */
public class FileUtils
{
    /**
     * Generate byte[] form file
     * 
     * @param file to be generate byte[]
     * @return byte[] file
     * 
     * @throws IOException
     */
    public static byte[] getByteFromFile(File file) throws IOException
    {
        try (ByteArrayOutputStream ous = new ByteArrayOutputStream(); InputStream ios = new FileInputStream(file);)
        {
            byte[] buffer = new byte[4096];

            int read = 0;
            while ((read = ios.read(buffer)) != -1)
            {
                ous.write(buffer, 0, read);
            }
            return ous.toByteArray();
        }
    }

    /**
     * Check the checksum files with algorithm SHA-256
     * 
     * @param firstFile     file orginal
     * @param secondFile    file to check
     * @return - true if files are equals
     *         - null if there was an error         
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Boolean checkEqualFiles(File firstFile, File secondFile) throws FileNotFoundException, IOException
    {
        return CheckFile.checkEqualsFiles(firstFile, secondFile);
    }

    /**
     * Copy inputstream on file
     * 
     * @param file file you want to copy
     * @param inputStream  that you want to copy
     * 
     * @throws IOException
     */
    public static void copyInputStreamOnFile(File file, InputStream inputStream) throws IOException
    {
        try (OutputStream outputStream = new FileOutputStream(file);)
        {
            IOUtils.copy(inputStream, outputStream);
            outputStream.close();
        }
    }
}
