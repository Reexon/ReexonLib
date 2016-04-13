package it.reexon.reexon.lib.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.reexon.reexon.lib.exceptions.FileMoveException;


public class IOUtils
{
    /**
     * Generates a list of files
     * 
     * @param folderFile folder from which to import files 
     * @return list of files
     * 
     * @throws IllegalArgumentException if the folderFile is not a folder
     * @throws NullPointerException if folderFile is null
     */
    public static Collection<File> importFiles(File folderFile) throws IllegalArgumentException, NullPointerException
    {
        if (folderFile == null)
            throw new NullPointerException("folderFile is null");
        if (!folderFile.isDirectory())
            throw new IllegalArgumentException("ForderPath param is not a folder!! ");
        List<File> files = new LinkedList<>();

        File[] listOfFile = folderFile.listFiles();
        for (int i = 0; i < listOfFile.length; i++)
        {
            if (listOfFile[i].isFile())
            {
                files.add(listOfFile[i]);
            }
        }
        return files;
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
        if (!new File(newDirectory).exists())
            new File(newDirectory).mkdirs();
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
            org.apache.commons.io.IOUtils.writeLines(lines, null, fout);
        }
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
            org.apache.commons.io.IOUtils.copy(inputStream, outputStream);
            outputStream.close();
        }
    }
}
