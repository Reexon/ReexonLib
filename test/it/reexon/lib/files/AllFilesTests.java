package it.reexon.lib.files;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.reexon.lib.files.tests.CheckFileUtilsTest;
import it.reexon.lib.files.tests.FileDeleteStrategyTest;
import it.reexon.lib.files.tests.FileUtilsTest;
import it.reexon.lib.files.tests.IOUtilsTest;
import it.reexon.lib.files.zip.tests.ZipUtilsTest;


@RunWith(Suite.class)
@SuiteClasses({ CheckFileUtilsTest.class, FileDeleteStrategyTest.class, FileUtilsTest.class, IOUtilsTest.class, ZipUtilsTest.class })
public class AllFilesTests
{

}
