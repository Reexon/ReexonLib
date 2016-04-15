/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.reexon.lib.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 *
 */
public class ZipUtils
{
    private static final int BUFSIZE = 1024;

    /**
     * Create zip form directory
     * 
     * @param sourceDir
     * @param zipOutput 
     * @param level (0-9)
     * 
     * @throws IOException
     * @throws IllegalArgumentException 
     */
    public static void createZipOfDirectory(File sourceDir, File zipOutput, int level) throws IOException, IllegalArgumentException
    {
        if (level < 0 || level > 9)
            throw new IllegalArgumentException("The level must be between 0 and 9");
    }

    /**
     * Create zip from file list
     * 
     * @param files file to zip
     * @param outputStream
     * 
     * @throws IOException
     */
    public static void createZip(OutputStream out, List<File> files) throws IOException
    {

        try (ZipOutputStream zout = new ZipOutputStream(out);)
        {
            for (File file : files)
            {
                createZip(file, zout);
            }
        }
    }

    /**
     * Create zip from file
     * 
     * @param file file to zip
     * @param out OutputStream
     * 
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void createZip(File file, OutputStream out) throws IOException, FileNotFoundException
    {
        try (ZipOutputStream zout = new ZipOutputStream(out);)
        {
            createZip(file, zout);
        }
    }

    /**
     * 
     * @param file file to zip
     * @param zout ZipOutputStream
     * 
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void createZip(File file, ZipOutputStream zout) throws IOException, FileNotFoundException
    {
        String name = file.getName();

        ZipEntry entry = new ZipEntry(name);
        entry.setTime(file.lastModified());
        zout.putNextEntry(entry);

        byte[] buf = new byte[BUFSIZE];

        try (FileInputStream is = new FileInputStream(file);)
        {
            int l;
            while ((l = is.read(buf)) > -1)
            {
                zout.write(buf, 0, l);
            }
        }
        zout.closeEntry();
    }

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
        if (!sourceDir.exists())
        {
            throw new IllegalArgumentException(sourceDir.getAbsolutePath() + " is not exists!");
        }
        if (!zipOutput.exists())
        {
            throw new IllegalArgumentException(zipOutput.getAbsolutePath() + " is not exists!");
        }

        String baseName = sourceDir.getAbsolutePath() + File.pathSeparator;

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipOutput));)
        {
            addDirToZip(sourceDir, zipOutputStream, baseName);
        }
    }

    /**
     * Unzip file
     * 
     * @param is InputStream
     * @param targetDir 
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void unzip(InputStream is, File targetDir) throws FileNotFoundException, IOException
    {
        ZipEntry entry;
        try (ZipInputStream zis = new ZipInputStream(is);)
        {

            byte[] buf = new byte[BUFSIZE];

            if (!targetDir.exists())
            {
                throw new FileNotFoundException(targetDir.toString() + " does not exist.");
            }

            if (!targetDir.isDirectory())
            {
                throw new FileNotFoundException(targetDir.toString() + " is not a directory.");
            }

            while ((entry = zis.getNextEntry()) != null)
            {
                String name = entry.getName();

                long time = entry.getTime();
                time = (time != -1) ? time : new Date().getTime();

                File f = new File(targetDir, name);

                if (entry.isDirectory())
                {
                    f.mkdirs();
                }
                else
                {
                    f.getParentFile().mkdirs();

                    try (FileOutputStream fos = new FileOutputStream(f);)
                    {
                        int len;
                        while ((len = zis.read(buf, 0, BUFSIZE)) > 0)
                        {
                            fos.write(buf, 0, len);
                        }
                    }
                }

                // size should be 0 here

                f.setLastModified(time);

                zis.closeEntry();
            }
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
