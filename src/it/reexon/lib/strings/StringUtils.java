package it.reexon.lib.strings;

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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++)
        {
            sb.append(Integer.toString((byteArray[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    /**
     * Convert string in hex characters
     * 
     * @param str String to convert
     * @return byte[] of characters
     */
    public static byte[] toHex(String str)
    {
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < bytes.length; i++)
        {
            bytes[i] = (byte) Integer.parseInt(str.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
