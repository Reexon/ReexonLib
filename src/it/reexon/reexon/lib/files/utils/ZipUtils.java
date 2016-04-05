package it.reexon.reexon.lib.files.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;


/**
 * 
 * @author Marco Velluto
 * @since Java 1.8
 *
 */
public class ZipUtils
{
    /**
     * Create Zip of File Directory
     * 
     * @param sourceDir source directory
     * @param zipOutput zip output file
     * 
     * @throws IOException
     */
    public static void createZipFileOfDirectory(File sourceDir, File zipOutput) throws IOException
    {
        if (!sourceDir.exists() || !sourceDir.isDirectory())
        {
            throw new IllegalArgumentException(sourceDir.getAbsolutePath() + " is not a directory!");
        }
        if (zipOutput.exists() && !zipOutput.isFile())
        {
            throw new IllegalArgumentException(zipOutput.getAbsolutePath() + " exists but is not a file!");
        }

        String baseName = sourceDir.getAbsolutePath() + File.pathSeparator;

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipOutput));)
        {
            addDirToZip(sourceDir, zipOutputStream, baseName);
        }
    }

    private static void addDirToZip(File dir, ZipOutputStream zip, String baseName) throws IOException
    {
        File[] files = dir.listFiles();

        for (File file : files)
        {
            if (file.isDirectory())
            {
                addDirToZip(file, zip, baseName);
            }
            else
            {
                String entryName = file.getAbsolutePath().substring(baseName.length());
                ZipEntry zipEntry = new ZipEntry(entryName);
                zip.putNextEntry(zipEntry);

                try (FileInputStream fileInput = new FileInputStream(file);)
                {
                    IOUtils.copy(fileInput, zip);
                    zip.closeEntry();

                }
            }
        }
    }
}
