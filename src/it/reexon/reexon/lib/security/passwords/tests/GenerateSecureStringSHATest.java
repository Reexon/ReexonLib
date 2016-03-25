package it.reexon.reexon.lib.security.passwords.tests;

import static org.junit.Assert.fail;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import it.reexon.reexon.lib.security.GenerateSecureSalt;
import it.reexon.reexon.lib.security.passwords.GenerateSecureStringSHA;


public class GenerateSecureStringSHATest
{

    @Test
    public final void testGenerateSecurePassword()
    {
        System.out.println(" --- testGenerateSecurePassword --- ");
        try
        {
            System.out.println(GenerateSecureStringSHA.generateSecurePassword("Passw0rd!!", GenerateSecureSalt.getSalt()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public final void testGet_SHA_1_SecurePassword()
    {
        System.out.println(" --- testGet_SHA_1_SecurePassword --- ");
        try
        {
            System.out.println(GenerateSecureStringSHA.get_SHA_1_SecurePassword("Passw0rd!!", GenerateSecureSalt.getSalt()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public final void testGet_SHA_256_SecurePassword()
    {
        System.out.println(" --- testGet_SHA_256_SecurePassword --- ");
        try
        {
            System.out.println(GenerateSecureStringSHA.get_SHA_256_SecurePassword("Passw0rd!!", GenerateSecureSalt.getSalt()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public final void testGet_SHA_384_SecurePassword()
    {
        System.out.println(" --- testGet_SHA_384_SecurePassword --- ");
        try
        {
            System.out.println(GenerateSecureStringSHA.get_SHA_384_SecurePassword("Passw0rd!!", GenerateSecureSalt.getSalt()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public final void testGet_SHA_512_SecurePassword()
    {
        System.out.println(" --- testGet_SHA_512_SecurePassword --- ");
        try
        {
            System.out.println(GenerateSecureStringSHA.get_SHA_512_SecurePassword("Passw0rd!!", GenerateSecureSalt.getSalt()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test(expected = NoSuchAlgorithmException.class)
    public final void testGetSecurePassowrdWithAlgorithm() throws NoSuchAlgorithmException
    {
        System.out.println(" --- testGetSecurePassowrdWithAlgorithm --- ");
        try
        {
            System.out.println(GenerateSecureStringSHA.getSecurePassowrd("Passw0rd!!", GenerateSecureSalt.getSalt(), "SHA-1"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
        }

        try
        {
            System.out.println(GenerateSecureStringSHA.getSecurePassowrd("Passw0rd!!", GenerateSecureSalt.getSalt(), "SHA-221"));
        }
        catch (NoSuchAlgorithmException e)
        {
            throw e;
        }
    }

}
