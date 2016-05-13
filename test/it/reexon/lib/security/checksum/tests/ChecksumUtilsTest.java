/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security.checksum.tests;

import java.io.File;
import java.io.FileNotFoundException;
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

import it.reexon.lib.files.FileUtils;
import it.reexon.lib.files.IOUtils;
import it.reexon.lib.list.ListUtils;
import it.reexon.lib.security.algorithmics.MessageDigestAlgorithms;
import it.reexon.lib.security.checksum.ChecksumUtils;
import it.reexon.lib.strings.StringUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class ChecksumUtilsTest
{
    private static final String TOMCAT_CHECKSUM_MD5 = "857093659f35c3ee76de54cacc3a7e7e";

    private static final Logger logger = LogManager.getLogger(ChecksumUtilsTest.class);

    private static final File DIRECTORY = new File("resources/tests/ChecksumUtilsTest_1");
    private static final File DIRECTORY_A = new File("resources/tests/ChecksumUtilsTest_1/DirecotyA");
    private static final File DIRECTORY_2 = new File("resources/tests/ChecksumUtilsTest_2");
    private static final File DIRECTORY_3 = new File("resources/tests/ChecksumUtilsTest_3");
    private static final File TOMCAT = new File("resources/tests/apache-tomcat-9.0.0.M3.zip");

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

        if (!DIRECTORY_A.exists())
            DIRECTORY_A.mkdir();

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
    @SuppressWarnings("unused")
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        try
        {
            FileUtils.forceDelete(DIRECTORY);
        }
        catch (Exception e)
        {}
        try
        {
            FileUtils.forceDelete(DIRECTORY_A);
        }
        catch (Exception e)
        {}
        try
        {
            FileUtils.forceDelete(DIRECTORY_2);
        }
        catch (Exception e)
        {}
        try
        {
            FileUtils.forceDelete(DIRECTORY_3);
        }
        catch (Exception e)
        {}
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
     * Test method for {@link it.reexon.lib.security.checksum.ChecksumUtils#createChecksum(java.io.File, it.reexon.lib.securityOLD.algorithms.MessageDigestAlgorithms)}.
     */
    @Test
    public final void testCreateChecksum()
    {
        try
        {
            byte[] checksumTomcat = ChecksumUtils.createChecksum(TOMCAT, MessageDigestAlgorithms.MD5);
            Assert.assertEquals(TOMCAT_CHECKSUM_MD5, StringUtils.toHexString(checksumTomcat));
            for (MessageDigestAlgorithms algorithm : MessageDigestAlgorithms.values())
            {
                byte[] checksum1 = ChecksumUtils.createChecksum(fileA, algorithm);
                byte[] checksum2 = ChecksumUtils.createChecksum(fileA, algorithm);
                Assert.assertEquals(StringUtils.toHexString(checksum1), StringUtils.toHexString(checksum2));

                checksum1 = ChecksumUtils.createChecksum(fileA, algorithm);
                checksum2 = ChecksumUtils.createChecksum(fileA1, algorithm);
                Assert.assertEquals(StringUtils.toHexString(checksum1), StringUtils.toHexString(checksum2));

                checksum1 = ChecksumUtils.createChecksum(fileB, algorithm);
                checksum2 = ChecksumUtils.createChecksum(fileA1, algorithm);
                Assert.assertNotEquals(StringUtils.toHexString(checksum1), StringUtils.toHexString(checksum2));

                try
                {
                    ChecksumUtils.createChecksum(null, algorithm);
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                }
                try
                {
                    ChecksumUtils.createChecksum(new File(""), algorithm);
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(FileNotFoundException.class, e.getClass());
                }
            }
            try
            {
                ChecksumUtils.createChecksum(new File(""), null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Errore in testCreateChecksum", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.checksum.ChecksumUtils#getChecksum(java.io.File, it.reexon.lib.securityOLD.algorithms.MessageDigestAlgorithms)}.
     */
    @Test
    public final void testGetChecksumFileMessageDigestAlgorithms()
    {
        try
        {
            String checksumTomcat = ChecksumUtils.getChecksum(TOMCAT, MessageDigestAlgorithms.MD5);
            Assert.assertEquals(TOMCAT_CHECKSUM_MD5, checksumTomcat);
            for (MessageDigestAlgorithms algorithm : MessageDigestAlgorithms.values())
            {
                String checksum1 = ChecksumUtils.getChecksum(fileA, algorithm);
                String checksum2 = ChecksumUtils.getChecksum(fileA, algorithm);
                Assert.assertEquals(checksum1, checksum2);

                checksum1 = ChecksumUtils.getChecksum(fileA, algorithm);
                checksum2 = ChecksumUtils.getChecksum(fileA1, algorithm);
                Assert.assertEquals(checksum1, checksum2);

                checksum1 = ChecksumUtils.getChecksum(fileB, algorithm);
                checksum2 = ChecksumUtils.getChecksum(fileA1, algorithm);
                Assert.assertNotEquals(checksum1, checksum2);

                try
                {
                    ChecksumUtils.getChecksum(null, algorithm);
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                }
                try
                {
                    ChecksumUtils.getChecksum(new File(""), algorithm);
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(FileNotFoundException.class, e.getClass());
                }
            }
            try
            {
                ChecksumUtils.getChecksum(new File(""), null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Errore in testGetChecksumFileMessageDigestAlgorithms", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.checksum.ChecksumUtils#getChecksum(java.io.File)}.
     */
    @Test
    public final void testGetChecksumFile()
    {
        try
        {
            String checksum1 = ChecksumUtils.getChecksum(fileA);
            String checksum2 = ChecksumUtils.getChecksum(fileA);
            Assert.assertEquals(checksum1, checksum2);

            checksum1 = ChecksumUtils.getChecksum(fileA);
            checksum2 = ChecksumUtils.getChecksum(fileA1);
            Assert.assertEquals(checksum1, checksum2);

            checksum1 = ChecksumUtils.getChecksum(fileB);
            checksum2 = ChecksumUtils.getChecksum(fileA1);
            Assert.assertNotEquals(checksum1, checksum2);

            try
            {
                ChecksumUtils.getChecksum(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                ChecksumUtils.getChecksum(new File(""));
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(FileNotFoundException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Errore in testGetChecksumFile", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.checksum.ChecksumUtils#writeChecksumFromFile(java.io.File)}.
     */
    @Test
    public final void testWriteChecksumFromFile()
    {
        try
        {
            ChecksumUtils.writeChecksumFromFile(DIRECTORY);
            List<File> files = IOUtils.importFiles(DIRECTORY, true);
            boolean isPresentSubDir = false;
            boolean isPresentRootDir = false;
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    for (File subFile : file.listFiles())
                        if (subFile.getName().contains("checksum"))
                            isPresentSubDir = true;
                }
                else
                {
                    if (file.getName().contains("checksum"))
                        isPresentRootDir = true;
                }
            }
            Assert.assertEquals(true, isPresentSubDir);
            Assert.assertEquals(true, isPresentRootDir);

            try
            {
                ChecksumUtils.writeChecksumFromFile(new File(""));
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(FileNotFoundException.class, e.getClass());
            }
            try
            {
                ChecksumUtils.writeChecksumFromFile(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Errore in testWriteChecksumFromFile", e);
            throw new RuntimeException(e);
        }
    }
}
