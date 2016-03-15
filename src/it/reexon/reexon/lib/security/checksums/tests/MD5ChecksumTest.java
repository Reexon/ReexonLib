package it.reexon.reexon.lib.security.checksums.tests;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.reexon.lib.security.checksums.MD5Checksum;


/**
 * @author marco.velluto
 */
public class MD5ChecksumTest
{

    @Test
    public final void test()
    {
        try
        {
            String checksumMD5 = MD5Checksum.getMD5Checksum("resources\\apache-tomcat-9.0.0.M3.zip");
            System.out.println(checksumMD5);

            Assert.assertEquals("857093659f35c3ee76de54cacc3a7e7e", checksumMD5);

            // ref : https://www.apache.org/dist/tomcat/tomcat-9/v9.0.0.M3/bin/apache-tomcat-9.0.0.M3.zip.md5
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
