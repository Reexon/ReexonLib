package it.reexon.reexon.lib.security.crypt.tests;

import org.junit.Test;

import it.reexon.reexon.lib.security.crypt.RSACrypt;


public class RSACryptTest
{

    @Test
    public final void test()
    {
        //        IntStream c = new SecureRandom().ints();
        //        RSACrypt crypt = new RSACrypt(c.findFirst().getAsInt());
        RSACrypt crypt = new RSACrypt(5);
        String decr = crypt.decrypt("15458");
        String enc = crypt.decrypt(decr);
        System.out.println("Decript: " + decr + " enc: " + enc);
    }

}
