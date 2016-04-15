package it.reexon.reexon.lib.strings;

import it.reexon.reexon.lib.convertions.ConvertUtils;


public class StringUtils
{
    /** 
     * How-to for a faster way to convert
     * a byte array to a HEX string
     * 
     * @param byteArray hex byte[] to covert in string
     * @return string converted
     */
    public static String toHexString(byte[] byteArray)
    {
        return ConvertUtils.toHexString(byteArray);
    }

    /**
     * Convert string in hex characters
     * 
     * @param str String to convert
     * @return byte[] of characters
     */
    public static byte[] toHex(String str)
    {
        return ConvertUtils.toHex(str);
    }
}
