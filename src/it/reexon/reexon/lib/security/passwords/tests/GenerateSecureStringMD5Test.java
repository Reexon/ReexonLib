package it.reexon.reexon.lib.security.passwords.tests;

import static it.reexon.reexon.lib.security.passwords.GenerateSecureStringMD5.generateSecurePassword;
import static org.junit.Assert.fail;

import org.junit.Test;

import it.reexon.reexon.lib.security.GenerateSecureSalt;


public class GenerateSecureStringMD5Test
{

    @Test
    public final void testGenerateSecurePasswordString()
    {
        System.out.println(" --- testGenerateSecurePasswordString --- ");
        try
        {
            String passwordCrypted = generateSecurePassword("Passw0rd!");
            System.out.println(passwordCrypted);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public final void testGenerateSecurePasswordStringString()
    {
        System.out.println(" --- testGenerateSecurePasswordStringString --- ");
        try
        {

            String passwordCrypted = generateSecurePassword("Passw0rd!", GenerateSecureSalt.getSalt());
            System.out.println(passwordCrypted);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
