/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.reexon.lib.exceptions.FileMoveException;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class IOUtils
{
    /**
     * @deprecated use {@link it.reexon.lib.files.IOUtils#importFiles(File, boolean)}
     * Generates a list of files. Contains only file. Non contains sub-directory
     * 
     * @param folderFile folder from which to import files 
     * @return list of files
     * 
     * @throws IllegalArgumentException if the folderFile is not a folder
     * @throws NullPointerException if folderFile is null
     */
    @Deprecated
    public static List<File> importFiles(File folderFile) throws IllegalArgumentException, NullPointerException
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
     * Generates a list of files
     * 
     * @param folderFile folder from which to import files
     * @param subDirecoties true if contains sub direcotory 
     * @return list of files
     * 
     * @throws IllegalArgumentException if the folderFile is not a folder
     * @throws NullPointerException if folderFile is null
     */
    public static List<File> importFiles(File folderFile, boolean subDirecoties) throws IllegalArgumentException, NullPointerException
    {
        if (folderFile == null)
            throw new NullPointerException("folderFile is null");
        if (!folderFile.isDirectory())
            throw new IllegalArgumentException("ForderPath param is not a folder!! ");
        Set<File> files = new HashSet<>();

        File[] listOfFile = folderFile.listFiles();
        for (int i = 0; i < listOfFile.length; i++)
        {
            if (subDirecoties)
                files.add(listOfFile[i]);

            else if (listOfFile[i].isFile())
                files.add(listOfFile[i]);
        }
        return files.stream().collect(Collectors.toList());
    }

    //TODO      public static List<File> importOnlyDirector(File folderFile, boolean subDirecoties) throws IllegalArgumentException, NullPointerException

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
}
