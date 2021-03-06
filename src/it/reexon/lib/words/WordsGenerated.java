/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.words;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;


/**
 * 
 * @author Marco Velluto
 * @since Java 1.8
 */
public class WordsGenerated
{
    private static final int STRING_LENGTH = 500;

    public static void main(String args[])
    {
        String randomString = RandomStringUtils.randomAlphanumeric(STRING_LENGTH);
        try
        {
            FileWriter writer = new FileWriter("c:\\temp\\file.json");
            writer.write(randomString);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println(randomString);
    }
}
