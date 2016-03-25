package it.reexon.reexon.lib.security.passwords.tests;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.reexon.lib.security.passwords.GenerateSecureStringPBKDF2WithHmacSHA1;


public class GenerateSecureStringPBKDF2WithHmacSHA1Test
{

    @Test(timeout = 1000)
    public final void testGenerateSecureSting()
    {
        System.out.println(" --- testGenerateSecureSting --- ");
        try
        {
            String generatedSecuredPasswordHash = GenerateSecureStringPBKDF2WithHmacSHA1.generateSecureSting("Passw0rd!!!");
            System.out.println(generatedSecuredPasswordHash);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test(timeout = 1000)
    public final void testValidatePassword()
    {
        System.out.println(" --- testValidatePassword --- ");
        try
        {
            String originalPassword = "password";
            String generatedSecuredPasswordHash = GenerateSecureStringPBKDF2WithHmacSHA1.generateSecureSting(originalPassword);
            System.out.println(generatedSecuredPasswordHash);

            boolean matched = GenerateSecureStringPBKDF2WithHmacSHA1.validatePassword("password", generatedSecuredPasswordHash);
            Assert.assertTrue(matched);

            matched = GenerateSecureStringPBKDF2WithHmacSHA1.validatePassword("password1", generatedSecuredPasswordHash);
            Assert.assertFalse(matched);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }

}
