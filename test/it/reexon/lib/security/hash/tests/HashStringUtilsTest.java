/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security.hash.tests;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reexon.lib.security.SecureSaltUtils;
import it.reexon.lib.security.SecureStringUtils;
import it.reexon.lib.security.algorithmics.MessageDigestAlgorithms;
import it.reexon.lib.security.algorithmics.SecureRandomAlgorithmics;
import it.reexon.lib.security.hash.HashStringUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class HashStringUtilsTest
{
    private static final Logger loggger = LogManager.getLogger(HashStringUtilsTest.class);

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
     * Test method for {@link it.reexon.lib.security.hash.HashStringUtils#generateSecurePassword(java.lang.String)}.
     */
    @Test
    public final void testGenerateSecurePasswordString()
    {
        try
        {
            for (int c = 0; c < 1000; c++)
            {
                final String passwordToHash = SecureStringUtils.secureString();
                String passwordHased1 = HashStringUtils.generateSecurePassword(passwordToHash);
                Assert.assertTrue(StringUtils.isNotBlank(passwordHased1));
                Assert.assertNotEquals(passwordToHash, passwordHased1);

                String passwordHased2 = HashStringUtils.generateSecurePassword(passwordToHash);
                Assert.assertTrue(StringUtils.isNotBlank(passwordHased1));
                Assert.assertNotEquals(passwordHased2, passwordToHash);
                Assert.assertEquals(passwordHased1, passwordHased2);

                try
                {
                    HashStringUtils.generateSecurePassword(null);
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                }
                HashStringUtils.generateSecurePassword("");
                HashStringUtils.generateSecurePassword(" ");
            }
        }
        catch (Exception e)
        {
            loggger.error("Error in testGenerateSecurePasswordString", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.hash.HashStringUtils#generateSecurePassword(java.lang.String, it.reexon.lib.security.algorithmics.MessageDigestAlgorithms)}.
     */
    @Test
    public final void testGenerateSecurePasswordStringMessageDigestAlgorithms()
    {
        try
        {
            for (int c = 0; c < 1000; c++)
            {
                for (MessageDigestAlgorithms algorithm : MessageDigestAlgorithms.values())
                {
                    final String passwordToHash = SecureStringUtils.secureString();
                    String passwordHased1 = HashStringUtils.generateSecurePassword(passwordToHash, algorithm);
                    Assert.assertTrue(StringUtils.isNotBlank(passwordHased1));
                    Assert.assertNotEquals(passwordToHash, passwordHased1);

                    String passwordHased2 = HashStringUtils.generateSecurePassword(passwordToHash, algorithm);
                    Assert.assertTrue(StringUtils.isNotBlank(passwordHased1));
                    Assert.assertNotEquals(passwordHased2, passwordToHash);
                    Assert.assertEquals(passwordHased1, passwordHased2);

                    try
                    {
                        HashStringUtils.generateSecurePassword(null, algorithm);
                        Assert.fail("Should have thrown an exception");
                    }
                    catch (Exception e)
                    {
                        Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                    }
                    HashStringUtils.generateSecurePassword("", algorithm);
                    HashStringUtils.generateSecurePassword(" ", algorithm);
                }
            }
        }
        catch (Exception e)
        {
            loggger.error("Error in testGenerateSecurePasswordStringMessageDigestAlgorithms", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.hash.HashStringUtils#generateSecurePassword(java.lang.String, byte[])}.
     */
    @Test
    public final void testGenerateSecurePasswordStringByteArray()
    {
        try
        {
            for (int c = 0; c < 1000; c++)
            {
                for (String ra : SecureRandomAlgorithmics.getAlorithms())
                {
                    final String passwordToHash = SecureStringUtils.secureString();
                    final byte[] salt = SecureSaltUtils.getSalt(32, ra);

                    String passwordHased1 = HashStringUtils.generateSecurePassword(passwordToHash, salt);
                    Assert.assertTrue(StringUtils.isNotBlank(passwordHased1));
                    Assert.assertNotEquals(passwordToHash, passwordHased1);

                    String passwordHased2 = HashStringUtils.generateSecurePassword(passwordToHash, salt);
                    Assert.assertTrue(StringUtils.isNotBlank(passwordHased1));
                    Assert.assertNotEquals(passwordHased2, passwordToHash);
                    Assert.assertEquals(passwordHased1, passwordHased2);

                    final byte[] saltV2 = SecureSaltUtils.getSalt();
                    String passwordHasedV2 = HashStringUtils.generateSecurePassword(passwordToHash, saltV2);
                    Assert.assertTrue(StringUtils.isNotBlank(passwordHased1));
                    Assert.assertNotEquals(passwordHasedV2, passwordHased2);

                    try
                    {
                        HashStringUtils.generateSecurePassword(null, salt);
                        Assert.fail("Should have thrown an exception");
                    }
                    catch (Exception e)
                    {
                        Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                    }
                    HashStringUtils.generateSecurePassword("", salt);
                    HashStringUtils.generateSecurePassword(" ", salt);
                }
            }
        }
        catch (Exception e)
        {
            loggger.error("Error in testGenerateSecurePasswordString", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.hash.HashStringUtils#generateSecurePassword(java.lang.String, byte[], it.reexon.lib.security.algorithmics.MessageDigestAlgorithms)}.
     */
    @Test
    public final void testGenerateSecurePasswordStringByteArrayMessageDigestAlgorithms()
    {
        try
        {
            for (int c = 0; c < 1000; c++)
            {
                for (MessageDigestAlgorithms algorithm : MessageDigestAlgorithms.values())
                {
                    for (String ra : SecureRandomAlgorithmics.getAlorithms())
                    {
                        final String passwordToHash = SecureStringUtils.secureString();
                        final byte[] salt = SecureSaltUtils.getSalt(32, ra);

                        String passwordHased1 = HashStringUtils.generateSecurePassword(passwordToHash, salt, algorithm);
                        Assert.assertTrue(StringUtils.isNotBlank(passwordHased1));
                        Assert.assertNotEquals(passwordToHash, passwordHased1);

                        String passwordHased2 = HashStringUtils.generateSecurePassword(passwordToHash, salt, algorithm);
                        Assert.assertTrue(StringUtils.isNotBlank(passwordHased1));
                        Assert.assertNotEquals(passwordHased2, passwordToHash);
                        Assert.assertEquals(passwordHased1, passwordHased2);

                        final byte[] saltV2 = SecureSaltUtils.getSalt();
                        String passwordHasedV2 = HashStringUtils.generateSecurePassword(passwordToHash, saltV2, algorithm);
                        Assert.assertTrue(StringUtils.isNotBlank(passwordHased1));
                        Assert.assertNotEquals(passwordHasedV2, passwordHased2);

                        try
                        {
                            HashStringUtils.generateSecurePassword(null, salt, algorithm);
                            Assert.fail("Should have thrown an exception");
                        }
                        catch (Exception e)
                        {
                            Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                        }
                        HashStringUtils.generateSecurePassword("", salt, algorithm);
                        HashStringUtils.generateSecurePassword(" ", salt, algorithm);

                        try
                        {
                            HashStringUtils.generateSecurePassword(null, salt, null);
                            Assert.fail("Should have thrown an exception");
                        }
                        catch (Exception e)
                        {
                            Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            loggger.error("Error in testGenerateSecurePasswordStringByteArrayMessageDigestAlgorithms", e);
            throw new RuntimeException(e);
        }
    }

}
