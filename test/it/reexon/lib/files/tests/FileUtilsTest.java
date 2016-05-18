/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.files.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reexon.lib.files.CheckFilesUtils;
import it.reexon.lib.files.FileUtils;
import it.reexon.lib.files.IOUtils;
import it.reexon.lib.list.ListUtils;
import it.reexon.lib.security.algorithmics.MessageDigestAlgorithms;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class FileUtilsTest
{
    private static final Logger logger = LogManager.getLogger(FileUtilsTest.class);

    private static final File DIRECTORY = new File("test/FileUtilsTest");
    private static final File DIRECTORY_2 = new File("test/checkFileUtils_2");
    private static final File DIRECTORY_3 = new File("test/checkFileUtils_3");

    private static File fileA = null;
    private static File fileA1 = null;
    private static File fileB = null;
    private static File fileB1 = null;
    private static File fileB4 = null;
    private static File fileB5 = null;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        if (!DIRECTORY.exists())
            DIRECTORY.mkdirs();

        fileA = File.createTempFile("fileA", ".txt", DIRECTORY);
        List<String> linesA = new LinkedList<>(ListUtils.createList("CiaoA, Ciao1"));
        IOUtils.writeLines(fileA, linesA);
        fileA1 = File.createTempFile("fileA1", ".txt", DIRECTORY);
        IOUtils.writeLines(fileA1, linesA);

        fileB = File.createTempFile("fileB", ".txt", DIRECTORY);
        List<String> linesB = new LinkedList<>(ListUtils.createList("CiaoB, CiaoB"));
        IOUtils.writeLines(fileB, linesB);
        fileB1 = File.createTempFile("fileB1", ".txt", DIRECTORY);
        IOUtils.writeLines(fileB1, linesB);

        FileUtils.copyDirectory(DIRECTORY, DIRECTORY_2);

        if (!DIRECTORY_3.exists())
            DIRECTORY_3.mkdirs();

        fileB4 = File.createTempFile("fileB", ".txt", DIRECTORY_3);
        List<String> linesB3 = new LinkedList<>(ListUtils.createList("CiaoB3, CiaoB3"));
        IOUtils.writeLines(fileB4, linesB3);
        fileB5 = File.createTempFile("fileB3", ".txt", DIRECTORY_3);
        IOUtils.writeLines(fileB5, linesB);

    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        FileUtils.forceDelete(DIRECTORY);
        FileUtils.forceDelete(DIRECTORY_2);
        FileUtils.forceDelete(DIRECTORY_3);
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#moveFile(java.nio.file.Path, java.nio.file.Path)}.
     */
    public final void moveFileTest()
    {
        //TODO
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {}

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {}

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#bitmapToImage(java.io.InputStream, java.lang.String)}.
     */
    @Test
    public final void testBitmapToImage()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#checkEqualDirecoty(java.io.File, java.io.File)}.
     */
    public final void testCheckEqualDirecoty()
    {
        try
        {
            Boolean isEquals = FileUtils.checkEqualDirecoty(DIRECTORY, DIRECTORY_2);
            Assert.assertTrue(isEquals.booleanValue());

            isEquals = FileUtils.checkEqualDirecoty(DIRECTORY_2, DIRECTORY);
            Assert.assertTrue(isEquals.booleanValue());

            isEquals = FileUtils.checkEqualDirecoty(DIRECTORY, DIRECTORY_3);
            Assert.assertFalse(isEquals.booleanValue());

            isEquals = FileUtils.checkEqualDirecoty(DIRECTORY_3, DIRECTORY);
            Assert.assertFalse(isEquals.booleanValue());

            try
            {
                FileUtils.checkEqualDirecoty(null, DIRECTORY_2);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }

            try
            {
                FileUtils.checkEqualDirecoty(DIRECTORY, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                FileUtils.checkEqualDirecoty(DIRECTORY, DIRECTORY);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#checkEqualFiles(java.io.File, java.io.File)}.
     */
    @Test
    public final void testCheckEqualFiles()
    {
        try
        {
            Boolean isEquals = FileUtils.checkEqualFiles(fileA1, fileA1);
            Assert.assertTrue(isEquals);

            isEquals = FileUtils.checkEqualFiles(fileA1, fileB1);
            Assert.assertFalse(isEquals);

            try
            {
                FileUtils.checkEqualFiles(null, fileB1);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }

            try
            {
                FileUtils.checkEqualFiles(fileB1, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
            try
            {
                FileUtils.checkEqualFiles(fileB1, new File(""));
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), FileNotFoundException.class);
            }

        }
        catch (Exception e)
        {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#copyDirectory(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public final void testCopyDirectory() throws IOException
    {
        File newDirecoty = new File(DIRECTORY.getPath() + "_new");
        try
        {
            FileUtils.copyDirectory(DIRECTORY, newDirecoty);
            Boolean isEquals = CheckFilesUtils.checkEqualsDirectories(DIRECTORY, newDirecoty, MessageDigestAlgorithms.getDefault());
            Assert.assertTrue(isEquals.booleanValue());

            try
            {
                FileUtils.copyDirectory(null, newDirecoty);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                FileUtils.copyDirectory(DIRECTORY, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                FileUtils.copyDirectory(new File(""), DIRECTORY);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error(e);
            throw new RuntimeException(e);
        }
        finally
        {
            FileUtils.forceDelete(newDirecoty);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#copyFile(java.io.File, java.io.File)}.
     */
    @Test
    public final void testCopyFile()
    {
        File destFile = new File(DIRECTORY.getPath() + "/" + fileA.getName());
        try
        {
            FileUtils.copyFile(fileA, destFile);
            Boolean isEquals = FileUtils.checkEqualFiles(fileA, destFile);
            Assert.assertTrue(isEquals.booleanValue());

            try
            {
                FileUtils.copyFile(fileA, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
            try
            {
                FileUtils.copyFile(null, fileA);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
            try
            {
                FileUtils.copyFile(new File(""), fileA);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), java.io.FileNotFoundException.class);
            }
        }
        catch (Exception e)
        {
            logger.error(e);
            throw new RuntimeException(e);
        }
        finally
        {
            try
            {
                FileUtils.forceDelete(destFile);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#deleteDirectory(java.io.File)}.
     */
    @Test
    public final void testDeleteDirectoryFile()
    {
        File newDirectory_1 = new File(DIRECTORY.getPath() + "_testDeleteDirectoryFile_1");
        File newDirectory_2 = new File(DIRECTORY.getPath() + "_testDeleteDirectoryFile_2");
        try
        {
            Assert.assertTrue("Directory must exists", newDirectory_1.mkdirs());

            //Delete empty directory
            FileUtils.deleteDirectory(newDirectory_1);
            Assert.assertFalse("The Directory should no longer exist", newDirectory_1.exists());

            //Delete not empty directory
            Assert.assertTrue("Directory must exists", newDirectory_2.mkdirs());
            File fileA = File.createTempFile("fileA", ".txt", newDirectory_2);
            List<String> linesA = new LinkedList<>(ListUtils.createList("CiaoA, Ciao1"));
            IOUtils.writeLines(fileA, linesA);
            File fileA1 = File.createTempFile("fileA1", ".txt", newDirectory_2);
            IOUtils.writeLines(fileA1, linesA);
            FileUtils.deleteDirectory(newDirectory_2);
            Assert.assertFalse("The Directory should no longer exist", newDirectory_1.exists());

            try
            {
                FileUtils.deleteDirectory(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                FileUtils.deleteDirectory(new File(""));
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(FileNotFoundException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error(e);
            throw new RuntimeException(e);
        }
        finally
        {
            try
            {
                Files.deleteIfExists(newDirectory_1.toPath());
            }
            catch (IOException e)
            {
                logger.error(e);
            }
            try
            {
                Files.deleteIfExists(newDirectory_2.toPath());
            }
            catch (IOException e)
            {
                logger.error(e);
            }
        }
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#deleteFile(java.nio.file.Path)}.
     */
    @Test
    public final void testDeleteFile()
    {
        try
        {
            File fileToDelete = File.createTempFile("fileToDelete", ".txt", DIRECTORY);
            Boolean isDeleted = FileUtils.deleteFile(fileToDelete.toPath());
            Assert.assertTrue(isDeleted.booleanValue());
            isDeleted = fileToDelete.exists();
            Assert.assertFalse(isDeleted.booleanValue());

            FileUtils.deleteFile(new File("").toPath());
            isDeleted = fileToDelete.exists();
            Assert.assertFalse(isDeleted.booleanValue());

            try
            {
                FileUtils.deleteFile(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
        }
        catch (Exception e)
        {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#forceDelete(java.io.File)}.
     */
    @Test
    public final void testForceDelete()
    {
        try
        {
            File file = new File("testForceDelete_FILE");
            FileUtils.copyFile(fileA1, file);
            Assert.assertTrue("File must exists", file.exists());

            FileUtils.forceDelete(file);
            Assert.assertFalse("The File should no longer exist", file.exists());

            try
            {
                FileUtils.forceDelete(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                FileUtils.forceDelete(new File(""));
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(FileNotFoundException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#getByteFromFile(java.io.File)}.
     */
    @Test
    public final void testGetByteFromFile()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#getScaledInstance(java.awt.image.BufferedImage, int, int, java.lang.Object, boolean)}.
     */
    @Test
    public final void testGetScaledInstance()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#imageToBitmap(java.awt.image.BufferedImage, java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testImageToBitmap()
    {
        // TODO
    }
}
