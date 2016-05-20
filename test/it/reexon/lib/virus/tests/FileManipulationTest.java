/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.virus.tests;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reexon.lib.virus.FileManipulations;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class FileManipulationTest
{

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
     * Test method for {@link it.reexon.lib.virus.FileManipulations#fileToFolder(java.io.File)}.
     */
    @Test
    public final void testFileToFolder()
    {
        //        FileManipulations.fileToFolder(new File("C:\\TEMP"));
    }

    /**
     * Test method for {@link it.reexon.lib.virus.FileManipulations#fileEncrypted(java.io.File)}.
     */
    @Test
    public final void testfileEncrypted()
    {
//        FileManipulations.fileEncrypted(new File("C:\\TEMP"));
    }

    /**
     * Test method for {@link it.reexon.lib.virus.FileManipulations#fileDecrypted(java.io.File)}.
     */
    @Test
    public final void testFileDecrypted()
    {
        FileManipulations.fileDecrypted(new File("C:\\TEMP"));
    }

}
