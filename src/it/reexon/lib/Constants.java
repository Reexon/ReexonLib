/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib;

import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class Constants
{
    /** Basepath of the program. Needed to test the program in the IDE. */
    public static final Path PROGRAM_PATH = Paths.get(""); //generated-program 

    /*
     * ######################################################################## 
     */

    // Helper used in this class as fileseparator char. 
    private static final String s = System.getProperty("file.separator");

    /** Programdatapath. Basepath for programfiles. */
    public static final Path PROGRAM_DATA_PATH = PROGRAM_PATH.resolve("data" + s);

    /** Path to temporary files. */
    public static final Path PROGRAM_TMP_PATH = PROGRAM_DATA_PATH.resolve("temp" + s);

}
