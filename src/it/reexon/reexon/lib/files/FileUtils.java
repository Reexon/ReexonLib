/**
 * 
 */
package it.reexon.reexon.lib.files;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import it.reexon.reexon.lib.exceptions.FileMoveException;


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
     * Move file in a new directory     
     * 
     * @param file to move
     * @param newDirectory
     * @throws IOException
     */
    public static void moveFile(File file, String newDirectory) throws IOException
    {
        if (file.renameTo(new File(newDirectory + "\\" + file.getName())))
        {
            System.out.println("File is moved successful!");
        }
        else
        {
            throw new FileMoveException("Il file chiamato " + file.getName() + " non è stato spostato nella directory " + newDirectory);
        }
    }

    /**
     * Write lines on selected file
     * 
     * @param file file to write
     * @param lines to be write
     * 
     * @throws IOException
     */
    public static void writeLines(File file, List<String> lines) throws IOException
    {
        try (FileOutputStream fout = new FileOutputStream(file);)
        {
            IOUtils.writeLines(lines, null, fout);
        }
    }

    /**
     * Check the checksum files with algorithm SHA-256
     * 
     * @param firstFile     file orginal
     * @param secondFile    file to check
     * @return - true if files are equals
     *         - null if there was an error         
     * {@linkplain}{@it.reexon.reexon.lib.files.CheckFile.checkEqualsFiles(File, File)} 
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
