package it.reexon.lib.security;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.reexon.lib.security.checksum.tests.ChecksumUtilsTest;
import it.reexon.lib.security.tests.SecureStringUtilsTest;
import it.reexon.lib.security.tests.SecuretySaltUtilsTest;


@RunWith(Suite.class)
@SuiteClasses({ SecuretySaltUtilsTest.class, SecureStringUtilsTest.class, ChecksumUtilsTest.class })
public class AllSecuretyTests
{

}
