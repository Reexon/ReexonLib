package it.reexon.reexon.lib.security.tests;

import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.reexon.lib.security.GenerateSecureString;


public class GenerateSecureStringTest
{

    @Test
    public final void nextSessionId()
    {
        System.out.println("nextSessionId");
        try
        {
            System.out.println(GenerateSecureString.secureString());
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
    }

}
