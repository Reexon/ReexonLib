package it.reexon.reexon.lib.convertions;

public class ConvertUtility
{
    /** 
     * How-to for a faster way to convert
     * a byte array to a HEX string
     * 
     * @param byteArray
     * @return
     */
    public static String byteArrayToHexString(byte[] byteArray)
    {
        String result = "";

        for (int i = 0; i < byteArray.length; i++)
        {
            result += Integer.toString((byteArray[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
