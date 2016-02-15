/**
 * 
 */
package it.reexon.reexon.lib.guava.chache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.cache.Weigher;


/**
 * @author marco.velluto
 * @see http://www.baeldung.com/guava-cache
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
    public void whenCacheMiss_thenValueIsComputed()
    {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>()
        {
            @Override
            public String load(String key)
            {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().build(loader);

        assertEquals(0, cache.size());
        assertEquals("HELLO", cache.getUnchecked("hello"));
        assertEquals(1, cache.size());
    }

    @Test
    public void whenCacheReachMaxSize_thenEviction()
    {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>()
        {
            @Override
            public String load(String key)
            {
                return key.toUpperCase();
            }
        };
        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().maximumSize(3).build(loader);

        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("forth");
        assertEquals(3, cache.size());
        assertNull(cache.getIfPresent("first"));
        assertEquals("FORTH", cache.getIfPresent("forth"));
    }

    @Test
    public void whenCacheReachMaxWeight_thenEviction()
    {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>()
        {
            @Override
            public String load(String key)
            {
                return key.toUpperCase();
            }
        };

        Weigher<String, String> weighByLength;
        weighByLength = new Weigher<String, String>()
        {
            @Override
            public int weigh(String key, String value)
            {
                return value.length();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().maximumWeight(16).weigher(weighByLength).build(loader);

        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("last");
        assertEquals(3, cache.size());
        assertNull(cache.getIfPresent("first"));
        assertEquals("LAST", cache.getIfPresent("last"));
    }

    @Test
    public void whenEntryIdle_thenEviction() throws InterruptedException
    {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>()
        {
            @Override
            public String load(String key)
            {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().expireAfterAccess(2, TimeUnit.MILLISECONDS).build(loader);

        cache.getUnchecked("hello");
        assertEquals(1, cache.size());

        cache.getUnchecked("hello");
        Thread.sleep(300);

        cache.getUnchecked("test");
        assertEquals(1, cache.size());
        assertNull(cache.getIfPresent("hello"));
    }

    @Test
    public void whenEntryLiveTimeExpire_thenEviction() throws InterruptedException
    {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>()
        {
            @Override
            public String load(String key)
            {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.MILLISECONDS).build(loader);

        cache.getUnchecked("hello");
        assertEquals(1, cache.size());
        Thread.sleep(300);
        cache.getUnchecked("test");
        assertEquals(1, cache.size());
        assertNull(cache.getIfPresent("hello"));
    }

    @Test
    public void whenWeakKeyHasNoRef_thenRemoveFromCache()
    {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>()
        {
            @Override
            public String load(String key)
            {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().weakKeys().build(loader);
    }

    @Test
    public void whenSoftValue_thenRemoveFromCache()
    {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>()
        {
            @Override
            public String load(String key)
            {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().softValues().build(loader);
    }

    @Test
    public void whenNullValue_thenOptional()
    {
        CacheLoader<String, Optional<String>> loader;
        loader = new CacheLoader<String, Optional<String>>()
        {
            @Override
            public Optional<String> load(String key)
            {
                return Optional.fromNullable(getSuffix(key));
            }
        };

        LoadingCache<String, Optional<String>> cache;
        cache = CacheBuilder.newBuilder().build(loader);

        assertEquals("txt", cache.getUnchecked("text.txt").get());
        assertFalse(cache.getUnchecked("hello").isPresent());
    }

    private String getSuffix(final String str)
    {
        int lastIndex = str.lastIndexOf('.');
        if (lastIndex == -1)
        {
            return null;
        }
        return str.substring(lastIndex + 1);
    }

    @Test
    public void whenLiveTimeEnd_thenRefresh()
    {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>()
        {
            @Override
            public String load(String key)
            {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.MINUTES).build(loader);
    }

    @Test
    public void whenPreloadCache_thenUsePutAll()
    {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>()
        {
            @Override
            public String load(String key)
            {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().build(loader);

        Map<String, String> map = new HashMap<String, String>();
        map.put("first", "FIRST");
        map.put("second", "SECOND");
        cache.putAll(map);

        assertEquals(2, cache.size());
    }

    @Test
    public void whenEntryRemovedFromCache_thenNotify()
    {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>()
        {
            @Override
            public String load(final String key)
            {
                return key.toUpperCase();
            }
        };

        RemovalListener<String, String> listener;
        listener = new RemovalListener<String, String>()
        {
            @Override
            public void onRemoval(RemovalNotification<String, String> n)
            {
                if (n.wasEvicted())
                {
                    String cause = n.getCause().name();
                    assertEquals(RemovalCause.SIZE.toString(), cause);
                }
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().maximumSize(3).removalListener(listener).build(loader);

        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("last");
        assertEquals(3, cache.size());
    }
}
