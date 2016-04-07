package it.reexon.reexon.lib.files;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/**
 * @author marco.velluto
 * @since Java 1.8
 */
public class ImportFilesUtils
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
    public static Collection<File> importDocument(File folderFile) throws IllegalArgumentException, NullPointerException
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
}
