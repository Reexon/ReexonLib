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

import it.reexon.lib.security.SecuretyStringUtils;
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
     * Test method for {@link it.reexon.lib.security.SecuretyStringUtils#secureString()}.
     */
    @Test(timeout = 10000)
    public final void testSecureString()
    {
        try
        {
            String secureString = SecuretyStringUtils.secureString();
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
     * Test method for {@link it.reexon.lib.security.SecuretyStringUtils#secureString(int)}.
     */
    @Test(timeout = 10000)
    public final void testSecureStringInt()
    {
        try
        {
            String secureString = SecuretyStringUtils.secureString(0);
            Assert.assertTrue(StringUtils.isBlank(secureString));
            Assert.assertEquals(0, secureString.length());

            for (int i = 0; i < 1000; i++)
            {
                int randomInt = new Random().nextInt(999) + 1;
                String string = SecuretyStringUtils.secureString(randomInt);
                Assert.assertTrue(StringUtils.isNotBlank(string));
                Assert.assertEquals(randomInt, string.length());
            }

            try
            {
                SecuretyStringUtils.secureString(-1);
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
     * Test method for {@link it.reexon.lib.security.SecuretyStringUtils#secureString(int, java.lang.String)}.
     */
    @Test(timeout = 10000)
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
                    String secureString = SecuretyStringUtils.secureString(randomInt, algorithm);
                    Assert.assertTrue(StringUtils.isNotBlank(secureString));
                    Assert.assertEquals(randomInt, secureString.length());
                }
            }
            for (String algorithm : algorithms)
            {
                String secureString = SecuretyStringUtils.secureString(0, algorithm);
                Assert.assertTrue(StringUtils.isBlank(secureString));
                Assert.assertEquals(0, secureString.length());

                try
                {
                    SecuretyStringUtils.secureString(-1, algorithm);
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
     * Test method for {@link it.reexon.lib.security.SecuretyStringUtils#secureStringWithIndents()}.
     */
    @Test(timeout = 10000)
    public final void testSecureStringWithindents()
    {
        try
        {
            for (int j = 0; j < 100; j++)
            {
                String secureString = SecuretyStringUtils.secureStringWithIndents();
                Assert.assertTrue(StringUtils.countMatches(secureString, '-') == 2);
                String[] strings = StringUtils.split(secureString, "-");
                Assert.assertEquals(3, strings.length);
                for (int i = 0; i < strings.length; i++)
                    Assert.assertEquals(4, strings[i].length());
            }
        }
        catch (Exception e)
        {
            logger.error("Errore in testSecureStringWithindents", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.SecuretyStringUtils#secureStringWithindents(int)}.
     */
    @Test(timeout = 10000)
    public final void testSecureStringWithindentsInt()
    {
        try
        {
            for (int i = 0; i < 100; i++)
            {
                int numberWords = new Random().nextInt(999) + 1;
                String secureString = SecuretyStringUtils.secureStringWithindents(numberWords);
                Assert.assertEquals((numberWords) - 1, StringUtils.countMatches(secureString, '-'));
                String[] strings = StringUtils.split(secureString, "-");
                Assert.assertEquals(numberWords, strings.length);
                for (int j = 0; j < strings.length; j++)
                    Assert.assertEquals(4, strings[j].length());
            }
            try
            {
                SecuretyStringUtils.secureStringWithindents(-(new Random().nextInt(999)));
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Errore in testSecureStringWithindentsInt", e);
            throw new RuntimeException(e);
        }
    }

}
