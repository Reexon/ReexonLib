/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.files.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reexon.lib.files.FileUtils;
import it.reexon.lib.files.IOUtils;
import it.reexon.lib.list.ListUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class FileUtilsTest
{
    private static final File DIRECTORY = new File("test/FileUtilsTest");

    private static File fileA = null;
    private static File fileA1 = null;
    private static File fileB = null;
    private static File fileB1 = null;

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

    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        FileUtils.forceDelete(DIRECTORY);
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
     * Test method for {@link it.reexon.lib.files.FileUtils#getByteFromFile(java.io.File)}.
     */
    @Test
    public final void testGetByteFromFile()
    {
        // TODO
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
            System.err.println(e);
            fail(e.getLocalizedMessage());
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
            System.err.println(e);
            fail(e.getLocalizedMessage());
        }
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#copyFile(java.io.File, java.io.File)}.
     */
    @Test
    public final void testCopyFile()
    {
        try
        {
            File destFile = new File(DIRECTORY.getPath() + "/" + fileA.getName());
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
            System.err.println(e);
            fail(e.getLocalizedMessage());
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
            // TODO - Check if directory is been copy successfull.
        }
        catch (Exception e)
        {
            System.err.println(e);
            fail(e.getLocalizedMessage());
        }
        finally
        {
            FileUtils.forceDelete(newDirecoty);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#deleteDirectory(java.io.File, boolean)}.
     */
    @Test
    public final void testDeleteDirectoryFileBoolean()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#joinFiles(java.lang.String[])}.
     */
    @Test
    public final void testJoinFiles()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#bitmapToImage(java.io.InputStream, java.lang.String)}.
     */
    @Test
    public final void testBitmapToImage()
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

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#getScaledInstance(java.awt.image.BufferedImage, int, int, java.lang.Object, boolean)}.
     */
    @Test
    public final void testGetScaledInstance()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#addDirectory(java.io.File)}.
     */
    @Test
    public final void testAddDirectory()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#moveFile(java.nio.file.Path, java.nio.file.Path)}.
     */
    @Test
    public final void testMoveFile()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#forceDelete(java.io.File)}.
     */
    @Test
    public final void testForceDelete()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.files.FileUtils#deleteDirectory(java.io.File)}.
     */
    @Test
    public final void testDeleteDirectoryFile()
    {
        // TODO
    }

}
