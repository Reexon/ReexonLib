package it.reexon.lib.security;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.reexon.lib.security.checksum.tests.ChecksumUtilsTest;
import it.reexon.lib.security.hash.tests.HashStringUtilsTest;
import it.reexon.lib.security.tests.CryptoUtilsTest;
import it.reexon.lib.security.tests.SecureSaltUtilsTest;
import it.reexon.lib.security.tests.SecureStringUtilsTest;


@RunWith(Suite.class)
@SuiteClasses({ SecureSaltUtilsTest.class, SecureStringUtilsTest.class, ChecksumUtilsTest.class, HashStringUtilsTest.class,
        CryptoUtilsTest.class })
public class AllSecuretyTests
{

}
