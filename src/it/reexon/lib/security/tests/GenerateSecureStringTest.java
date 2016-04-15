package it.reexon.lib.security.tests;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.lib.security.GenerateSecureString;


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
