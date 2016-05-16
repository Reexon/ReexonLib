package it.reexon.lib.securityOLD.tests;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.lib.securityOLD.GenerateSecureString;


@Deprecated

public class GenerateSecureStringTest
{

    @Test
    public final void secureStringTest()
    {
        System.out.println("secureStringTest");
        try
        {
            System.out.println(GenerateSecureString.secureString());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
    }
}
