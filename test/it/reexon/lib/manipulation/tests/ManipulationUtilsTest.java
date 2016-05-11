/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.manipulation.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reexon.lib.manipulation.ManipulationUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class ManipulationUtilsTest
{
    private static final Logger logger = LogManager.getLogger(ManipulationUtilsTest.class);

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
     * Test method for {@link it.reexon.lib.manipulation.ManipulationUtils#shuffle(java.lang.String)}.
     */
    @Test
    public final void testShuffle()
    {
        try
        {
            String helloStr = "Hello";
            String manipulatedStr = ManipulationUtils.shuffle(helloStr);
            Assert.assertNotEquals("Hello", manipulatedStr);
            Assert.assertEquals(helloStr.length(), manipulatedStr.length());

            String manipulatedEmptyStr = ManipulationUtils.shuffle("");
            Assert.assertEquals("", manipulatedEmptyStr);
            Assert.assertEquals(0, manipulatedEmptyStr.length());

            manipulatedEmptyStr = ManipulationUtils.shuffle(new String());
            Assert.assertEquals("", manipulatedEmptyStr);
            Assert.assertEquals(0, manipulatedEmptyStr.length());

            try
            {
                ManipulationUtils.shuffle(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Errore in testShuffle", e);
            throw new RuntimeException(e);
        }
    }

}
