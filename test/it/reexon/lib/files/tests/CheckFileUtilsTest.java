/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.files.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

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
import it.reexon.lib.security.algorithms.MessageDigestAlgorithms;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class CheckFileUtilsTest
{
    private static final File DIRECTORY = new File("test/checkFileUtils");

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
     * Test method for {@link it.reexon.lib.files.CheckFilesUtils#checkEqualsFiles(java.io.File, java.io.File, java.lang.String)}.
     */
    @Test
    public final void testCheckEqualsFilesFileFileString()
    {
        try
        {

            for (MessageDigestAlgorithms algorithm : MessageDigestAlgorithms.values())
                testEqualsFile(fileA1, fileB1, algorithm);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    public void testEqualsFile(File file1, File file2, MessageDigestAlgorithms algorithm)
    {
        try
        {
            Boolean isEquals = CheckFilesUtils.checkEqualsFiles(file1, file1, algorithm);
            Assert.assertTrue(isEquals);

            isEquals = CheckFilesUtils.checkEqualsFiles(file1, file1, algorithm);
            Assert.assertTrue(isEquals);

            try
            {
                CheckFilesUtils.checkEqualsFiles(null, null, algorithm);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }

            isEquals = CheckFilesUtils.checkEqualsFiles(file1, file2, algorithm);
            Assert.assertFalse(isEquals);

            isEquals = CheckFilesUtils.checkEqualsFiles(file2, file1, algorithm);
            Assert.assertFalse(isEquals);

            try
            {
                CheckFilesUtils.checkEqualsFiles(file2, file1, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
            try
            {
                CheckFilesUtils.checkEqualsFiles(new File(""), file1, algorithm);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), FileNotFoundException.class);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

}
