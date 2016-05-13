package it.reexon.lib.security;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.reexon.lib.security.checksum.tests.ChecksumUtilsTest;
import it.reexon.lib.security.tests.SecurityStringUtilsTest;
import it.reexon.lib.security.tests.SecuritySaltUtilsTest;


@RunWith(Suite.class)
@SuiteClasses({ SecuritySaltUtilsTest.class, SecurityStringUtilsTest.class, ChecksumUtilsTest.class })
public class AllSecuretyTests
{

}
