package it.reexon.lib.convertions.tests;

import org.junit.Test;

import it.reexon.lib.convertions.ConvertUtils;


public class ConvertUtilsTest
{

    @Test
    public final void testByteArrayToHexString()
    {
        System.out.println(ConvertUtils.toHexString("[B@17579e0f".getBytes()));
    }

}
