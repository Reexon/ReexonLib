/**
 * 
 */
package it.reexon.lib.list.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author marco.velluto
 *
 */
public class ListTest
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

    @Test
    public final void test()
    {
        List<String> list = new ArrayList<String>(3);
        list.add("hellow");
        list.add("help");
        list.add("bye");

        Assert.assertEquals("bye", list.get(list.size() - 1));
    }

    @Test
    public final void forTest()
    {
        List<StringBuffer> list = new ArrayList<StringBuffer>(3);
        list.add(new StringBuffer("hellow"));
        list.add(new StringBuffer("help"));
        list.add(new StringBuffer("bye"));

        for (StringBuffer sb : list)
        {
            sb.append("TEST");
        }
        
        Assert.assertEquals("hellowTEST", list.get(0).toString());

    }
}
