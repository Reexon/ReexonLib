package it.reexon.reexon.lib.convertions.tests;

import org.junit.Test;

import it.reexon.reexon.lib.convertions.ConvertUtility;


public class ConvertUtilityTest
{

    @Test
    public final void testByteArrayToHexString()
    {
        System.out.println(ConvertUtility.byteArrayToHexString("[B@17579e0f".getBytes()));
    }

}
