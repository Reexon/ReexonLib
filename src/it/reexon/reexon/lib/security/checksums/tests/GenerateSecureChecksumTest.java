package it.reexon.reexon.lib.security.checksums.tests;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.reexon.lib.security.algorithms.MessageDigestAlgorithms;
import it.reexon.reexon.lib.security.checksums.GenerateSecureChecksum;


public class GenerateSecureChecksumTest
{

    @Test(timeout = 10000L)
    public final void getChecksumWithAlgoritmsTest()
    {
        System.out.println("getChecksumWithAlgoritmsTest");
        try
        {
            String md2 = GenerateSecureChecksum.getChecksum("C:\\Users\\Marco.Velluto\\Downloads\\IMG_5341.dng", MessageDigestAlgorithms.MD2);
            System.out.println("md2: " + md2);

            String md5 = GenerateSecureChecksum.getChecksum("C:\\Users\\Marco.Velluto\\Downloads\\IMG_5341.dng", MessageDigestAlgorithms.MD5);
            System.out.println("md5: " + md5);

            String SHA_1 = GenerateSecureChecksum.getChecksum("C:\\Users\\Marco.Velluto\\Downloads\\IMG_5341.dng", MessageDigestAlgorithms.SHA_1);
            System.out.println("SHA_1: " + SHA_1);

            String SHA_224 = GenerateSecureChecksum.getChecksum("C:\\Users\\Marco.Velluto\\Downloads\\IMG_5341.dng", MessageDigestAlgorithms.SHA_224);
            System.out.println("SHA_224: " + SHA_224);

            String SHA_256 = GenerateSecureChecksum.getChecksum("C:\\Users\\Marco.Velluto\\Downloads\\IMG_5341.dng", MessageDigestAlgorithms.SHA_256);
            System.out.println("SHA_256: " + SHA_256);

            String SHA_384 = GenerateSecureChecksum.getChecksum("C:\\Users\\Marco.Velluto\\Downloads\\IMG_5341.dng", MessageDigestAlgorithms.SHA_384);
            System.out.println("SHA_384: " + SHA_384);

            String SHA_512 = GenerateSecureChecksum.getChecksum("C:\\Users\\Marco.Velluto\\Downloads\\IMG_5341.dng", MessageDigestAlgorithms.SHA_512);
            System.out.println("SHA_512: " + SHA_512);

        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println("Errore in getChecksumWithAlgoritmsTest: " + e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
        catch (IOException e)
        {
            System.out.println("Errore in getChecksumWithAlgoritmsTest: " + e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
    }

}
