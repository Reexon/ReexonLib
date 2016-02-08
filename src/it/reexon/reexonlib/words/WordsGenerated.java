package it.reexon.reexonlib.words;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;


/**
 * 
 * @author marco.velluto
 *
 */
public class WordsGenerated
{
    private static final int STRING_LENGTH = 500;

    public static void main(String args[])
    {
        String randomString = RandomStringUtils.randomAlphanumeric(STRING_LENGTH);
        try
        {
            //write converted json data to a file named "file.json"
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
