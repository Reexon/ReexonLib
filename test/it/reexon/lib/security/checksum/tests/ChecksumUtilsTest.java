/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security.checksum.tests;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reexon.lib.security.checksum.ChecksumUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class ChecksumUtilsTest
{
    private static final Logger logger = LogManager.getLogger(ChecksumUtilsTest.class);

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {}

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {}

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
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.security.checksum.ChecksumUtils#getChecksum(java.io.File, it.reexon.lib.securityOLD.algorithms.MessageDigestAlgorithms)}.
     */
    @Test
    public final void testGetChecksumFileMessageDigestAlgorithms()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.security.checksum.ChecksumUtils#getChecksum(java.io.File)}.
     */
    @Test
    public final void testGetChecksumFile()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.security.checksum.ChecksumUtils#writeChecksumFromFile(java.io.File)}.
     */
    @Test
    public final void testWriteChecksumFromFile()
    {
        try
        {
            ChecksumUtils.writeChecksumFromFile(new File("resources//tests"));
        }
        catch (Exception e)
        {
            logger.error("Errore in testWriteChecksumFromFile", e);
            throw new RuntimeException(e);
        }
    }

}
