package it.reexon.reexon.lib.json;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import it.reexon.reexon.lib.json.dtos.MapRequestDTO;


public class JsonGenerated
{
    public static void main(String[] args)
    {
        MapRequestDTO mapRequestDTO = new MapRequestDTO();
        Gson gson = new Gson();

        // convert java object to JSON format,
        // and returned as JSON formatted string
        String json = gson.toJson(mapRequestDTO);

        try
        {
            //write converted json data to a file named "file.json"
            FileWriter writer = new FileWriter("c:\\temp\\file.json");
            writer.write(json);
            writer.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println(json);

    }
}
