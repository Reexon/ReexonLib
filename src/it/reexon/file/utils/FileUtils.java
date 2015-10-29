/**
 * 
 */
package it.reexon.file.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import it.reexon.file.exceptions.FileMoveException;

/**
 * @author marco.velluto
 *
 */
public class FileUtils
{
    public static byte[] getByteFromFile(File file) throws IOException
    {
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        try
        {
            byte[] buffer = new byte[4096];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream(file);
            int read = 0;
            while ((read = ios.read(buffer)) != -1)
            {
                ous.write(buffer, 0, read);
            }
        }
        finally
        {
            if (ous != null)
                ous.close();
            if (ios != null)
                ios.close();
        }
        return ous.toByteArray();
    }
    
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
}
