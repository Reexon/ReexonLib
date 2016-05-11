package it.reexon.lib.securityOLD.checksums.tests;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.lib.securityOLD.checksums.MD5Checksum;


/**
 * @author marco.velluto
 */
public class MD5ChecksumTest
{

    @Test
    public final void test()
    {
        System.out.println("test");

        try
        {
            String checksumMD5 = MD5Checksum.getMD5Checksum("resources/tests/apache-tomcat-9.0.0.M3.zip");
            System.out.println(checksumMD5);

            Assert.assertEquals("857093659f35c3ee76de54cacc3a7e7e", checksumMD5);

            // ref :
            // https://www.apache.org/dist/tomcat/tomcat-9/v9.0.0.M3/bin/apache-tomcat-9.0.0.M3.zip.md5
        }
        catch (Exception e)
        {
            System.out.println("Errore in testChecksum: " + e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public final void testChecksum()
    {
        System.out.println("testChecksum");

        try
        {
            byte[] createCheck = MD5Checksum.createChecksum("resources/tests/apache-tomcat-9.0.0.M3.zip");
            String getMd5 = MD5Checksum.getMD5Checksum("resources/tests/apache-tomcat-9.0.0.M3.zip");

            System.out.println("createMD5 - byte: " + createCheck);
            System.out.println("createMD5 - string: " + createCheck.toString());
            System.out.println("getMd5 - string: " + getMd5);
            System.out.println("getMd5 - byte: " + getMd5.getBytes());

            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            System.out.println("Errore in testChecksum: " + e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(timeout = 10000L)
    public final void testChecksumTrue()
    {
        System.out.println("testChecksumTrue");

        try
        {
            String getMd5 = MD5Checksum.getMD5Checksum("C:\\Users\\Marco.Velluto\\Downloads\\IMG_5341.dng");

            System.out.println("getMd5 - string: " + getMd5);
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println("Errore in testChecksumTrue: " + e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
        catch (IOException e)
        {
            System.out.println("Errore in testChecksumTrue: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
