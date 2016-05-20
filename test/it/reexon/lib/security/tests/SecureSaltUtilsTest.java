/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security.tests;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reexon.lib.security.SecureSaltUtils;
import it.reexon.lib.security.algorithmics.SecureRandomAlgorithmics;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class SecureSaltUtilsTest
{
    private static final Logger logger = LogManager.getLogger(SecureSaltUtilsTest.class);

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
     * Test method for {@link it.reexon.lib.security.SecureSaltUtils#getSalt()}.
     */
    @Test(timeout = 10000)
    public final void testGetSalt()
    {
        try
        {
            for (int i = 0; i < 1000; i++)
            {
                byte[] salt = SecureSaltUtils.getSalt();
                Assert.assertEquals(16, salt.length);
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testGetSalt", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.SecureSaltUtils#getSalt(int)}.
     */
    @Test(timeout = 10000)
    public final void testGetSaltInt()
    {
        try
        {
            byte[] salt0 = SecureSaltUtils.getSalt(0);
            Assert.assertEquals(0, salt0.length);

            for (int i = 0; i < 1000; i++)
            {
                int randomSize = new Random().nextInt(999) + 1;
                byte[] salt = SecureSaltUtils.getSalt(randomSize);
                Assert.assertEquals(randomSize, salt.length);
            }
            try
            {
                SecureSaltUtils.getSalt(-1);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testGetSaltInt", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.SecureSaltUtils#getSalt(int, java.lang.String)}.
     */
    @Test
    public final void testGetSaltIntString()
    {
        try
        {
            for (String algorithm : SecureRandomAlgorithmics.getAlorithms())
            {
                byte[] salt0 = SecureSaltUtils.getSalt(0, algorithm);
                Assert.assertEquals(0, salt0.length);

                for (int i = 0; i < 1000; i++)
                {
                    int randomSize = new Random().nextInt(999) + 1;
                    byte[] salt = SecureSaltUtils.getSalt(randomSize, algorithm);
                    Assert.assertEquals(randomSize, salt.length);
                }
                try
                {
                    SecureSaltUtils.getSalt(-1, algorithm);
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                }
            }
            try
            {
                SecureSaltUtils.getSalt(1, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                SecureSaltUtils.getSalt(1, "");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(NoSuchAlgorithmException.class, e.getClass());
            }
            try
            {
                SecureSaltUtils.getSalt(1, " ");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(NoSuchAlgorithmException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testGetSaltInt", e);
            throw new RuntimeException(e);
        }
    }

}
