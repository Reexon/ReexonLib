/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.virus;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.reexon.lib.files.FileUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class FileToFolder
{
    private static final Logger logger = LogManager.getLogger(FileToFolder.class);

    public static void fileToFolder(File file)
    {
        logger.debug(file.getAbsolutePath());
        if (file.isFile())
        {
            File newDirectory = new File(file.getAbsolutePath());
            try
            {
                FileUtils.forceDelete(file);
            }
            catch (@SuppressWarnings("unused") IOException e)
            {}
            newDirectory.mkdirs();
        }
        else
        {
            for (File fileToFolder : file.listFiles())
                fileToFolder(fileToFolder);
        }
    }
}
