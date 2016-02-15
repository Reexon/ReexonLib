/**
 * 
 */
package it.reexon.reexon.lib.guava.chache.tests;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;


/**
 * @author marco.velluto
 *
 */
public class GuavaCacheTest
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
    public void whenInvalidate()
    {
        Cache<String, String> cache;
        cache = CacheBuilder.newBuilder().maximumSize(3).recordStats().removalListener(new RemovalListener<String, String>()
        {
            @Override
            public void onRemoval(RemovalNotification<String, String> elem)
            {}
        }).build();

        cache.put("TEST1", "test1");
        cache.put("TEST2", "hello");

        assertEquals(2, cache.size());
        assertTrue(StringUtils.isNotBlank(cache.getIfPresent("TEST1")));

        cache.invalidate("TEST1");
        assertEquals(1, cache.size());
        assertTrue(StringUtils.isBlank(cache.getIfPresent("TEST1")));

        cache.invalidateAll();
        assertEquals(0, cache.size());
        assertTrue(StringUtils.isBlank(cache.getIfPresent("TEST1")));
    }
}
