package it.reexon.reexon.lib.strings.tests;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;


public class StringTest
{

    @Test
    public final void addTest()
    {
        String str = "487/001";
        String seq = StringUtils.substringAfterLast(str, "/");
        assertEquals("001", seq);
        String id = StringUtils.substringBeforeLast(str, "/");
        assertEquals("487", id);
        Long seqL = new Long(seq) + 1;

        String formattedX = String.format("%03d", seqL); // -> 001
        String result = id + "/" + formattedX;
        assertEquals("487/002", result);
    }
}
