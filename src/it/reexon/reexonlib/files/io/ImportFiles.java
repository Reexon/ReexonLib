/**
 * 
 */
package it.reexon.reexonlib.files.io;

import java.io.File;
import java.util.ArrayList;


/**
 * @author marco.velluto
 *
 */
public class ImportFiles
{
    /**
     * 
     * @param forderPath
     * @return
     */
    public static ArrayList<File> importDocument(String forderPath)
    {
        ArrayList<File> files = new ArrayList<>();

        File folder = new File(forderPath);
        File[] listOfFile = folder.listFiles();
        for (int i = 0; i < listOfFile.length; i++)
        {
            if (listOfFile[i].isFile())
            {
                files.add(listOfFile[i]);
            }
        }
        return files;
    }
}
