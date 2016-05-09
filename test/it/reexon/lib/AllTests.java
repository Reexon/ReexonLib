package it.reexon.lib;

import org.apache.logging.log4j.Level;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.reexon.lib.date.test.DateUtilsTest;
import it.reexon.lib.files.AllFilesTests;
import it.reexon.lib.pdf.test.PdfUtilsTest;


@RunWith(Suite.class)
@SuiteClasses({ DateUtilsTest.class, AllFilesTests.class, PdfUtilsTest.class })
public class AllTests
{
    protected static final Level LOGGER_LEVEL = Level.ALL;
}
