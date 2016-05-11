package it.reexon.lib;

import org.apache.logging.log4j.Level;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.reexon.lib.date.tests.DateUtilsTest;
import it.reexon.lib.files.AllFilesTests;
import it.reexon.lib.list.tests.ListUtilsTest;
import it.reexon.lib.manipulation.tests.ManipulationUtilsTest;
import it.reexon.lib.network.tests.AllNetworkTests;
import it.reexon.lib.pdf.tests.PdfUtilsTest;


@RunWith(Suite.class)
@SuiteClasses({ DateUtilsTest.class, AllFilesTests.class, PdfUtilsTest.class, AllNetworkTests.class, ListUtilsTest.class,
        ManipulationUtilsTest.class })
public class AllTests
{
    protected static final Level LOGGER_LEVEL = Level.ALL;
}
