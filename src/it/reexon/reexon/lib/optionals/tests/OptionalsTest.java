package it.reexon.reexon.lib.optionals.tests;

import java.util.Collection;
import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class OptionalsTest
{

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {}

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {}

    @Before
    public void setUp() throws Exception
    {}

    @After
    public void tearDown() throws Exception
    {}

    @Test
    public final void test()
    {
        Optional<Collection<Object>> fornitori = Optional.empty();

        Assert.assertNotNull(fornitori);
        if (!fornitori.isPresent())
            Assert.assertTrue(true);
    }

}
