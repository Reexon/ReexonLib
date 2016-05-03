package it.reexon.lib;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.reexon.lib.date.test.DateUtilsTest;
import it.reexon.lib.files.tests.CheckFileUtilsTest;
import it.reexon.lib.files.tests.FileUtilsTest;


@RunWith(Suite.class)
@SuiteClasses({ DateUtilsTest.class, CheckFileUtilsTest.class, FileUtilsTest.class })
public class AllTests
{

}
