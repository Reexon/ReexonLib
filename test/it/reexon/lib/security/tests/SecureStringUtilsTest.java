/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security.tests;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reexon.lib.security.SecureStringUtils;
import it.reexon.lib.security.algorithmics.SecureRandomAlgorithmics;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class SecureStringUtilsTest
{
    private static final Logger logger = LogManager.getLogger(SecureStringUtilsTest.class);

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
     * Test method for {@link it.reexon.lib.security.SecureStringUtils#secureString()}.
     */
    @Test
    public final void testSecureString()
    {
        try
        {
            String secureString = SecureStringUtils.secureString();
            Assert.assertTrue(StringUtils.isNotBlank(secureString));
            Assert.assertEquals(32, secureString.length());
        }
        catch (Exception e)
        {
            logger.error("Errore in testSecureString", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.SecureStringUtils#secureString(int)}.
     */
    @Test
    public final void testSecureStringInt()
    {
        try
        {
            String secureString = SecureStringUtils.secureString(0);
            Assert.assertTrue(StringUtils.isBlank(secureString));
            Assert.assertEquals(0, secureString.length());

            for (int i = 0; i < 1000; i++)
            {
                int randomInt = new Random().nextInt(999) + 1;
                String string = SecureStringUtils.secureString(randomInt);
                Assert.assertTrue(StringUtils.isNotBlank(string));
                Assert.assertEquals(randomInt, string.length());
            }

            try
            {
                SecureStringUtils.secureString(-1);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }

        }
        catch (Exception e)
        {
            logger.error("Errore in testSecureStringInt", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.SecureStringUtils#secureString(int, java.lang.String)}.
     */
    @Test
    public final void testSecureStringIntString()
    {
        try
        {
            List<String> algorithms = SecureRandomAlgorithmics.getAlorithms();
            for (String algorithm : algorithms)
            {
                for (int i = 0; i < 1000; i++)
                {
                    int randomInt = new Random().nextInt(999) + 1;
                    String secureString = SecureStringUtils.secureString(randomInt, algorithm);
                    Assert.assertTrue(StringUtils.isNotBlank(secureString));
                    Assert.assertEquals(randomInt, secureString.length());
                }
            }
            for (String algorithm : algorithms)
            {
                String secureString = SecureStringUtils.secureString(0, algorithm);
                Assert.assertTrue(StringUtils.isBlank(secureString));
                Assert.assertEquals(0, secureString.length());

                try
                {
                    SecureStringUtils.secureString(-1, algorithm);
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Errore in testSecureStringIntString", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.SecureStringUtils#secureStringWithindents()}.
     */
    @Test
    public final void testSecureStringWithindents()
    {
        try
        {
            String secureString = SecureStringUtils.secureStringWithindents(); //U7jM-5UjN-0OTY
            Assert.assertTrue(StringUtils.countMatches(secureString, '-') == 2);
            String[] strings = StringUtils.split(secureString, "-");
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.security.SecureStringUtils#secureStringWithindents(int)}.
     */
    @Test
    public final void testSecureStringWithindentsInt()
    {
        // TODO
    }

}
