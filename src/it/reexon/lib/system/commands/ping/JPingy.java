/**
 Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.system.commands.ping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.reexon.lib.system.commands.ping.enums.SystemEnum;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class JPingy
{

    public static final int SECOND_ONE_DAY = 86400;

    private Boolean realtimeWrite = true;
    private String fileName;
    private String location = "C:\\TEMP\\";
    private Integer count;
    private String url;
    private SystemEnum system;

    /**
     * 
     * @param fileName
     * @param url
     * @param count
     * @param system    Opertive system. If null set WINDOWS
     */
    public JPingy(String fileName, String url, int count, SystemEnum system)
    {
        super();
        this.fileName = fileName;
        this.count = count;
        this.url = url;
        this.system = system != null ? system : SystemEnum.WINDOWS;
    }

    public void run()
    {
        BufferedReader reader = null;
        Process process = null;
        File file = new File(fileName);
        PingArguments pingArguments = new PingArguments.Builder(this.system).build().setUrl(url);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)))
        {
            writer.write(createMessage("BEGIN PROCESS", new Date()));
            writer.newLine();

            pingArguments.setCount(this.count);

            Runtime runtime = Runtime.getRuntime();
            process = runtime.exec(pingArguments.getCommand());
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            write(writer, createMessage(pingArguments.getCommand(), new Date()));

            String inputLine;
            List<String> linesResult = new ArrayList<String>();
            while ((inputLine = reader.readLine()) != null)
            {
                Date date = new Date();
                String message = "";
                writer.newLine();
                message = createMessage(inputLine, date);

                System.out.println(message);

                write(writer, message);
                linesResult.add(inputLine);
            }
            writer.newLine();
            writer.write(createMessage("END PROCESS", new Date()));
            analyzeResult(linesResult);
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        finally
        {
            closeReader(reader);
            if (process != null)
                process.destroy();
        }
    }

    @SuppressWarnings("unused")
    private void analyzeResult(List<String> linesResult)
    {
        // TODO Auto-generated method stub

    }

    private void closeReader(BufferedReader reader)
    {
        if (reader != null)
        {
            try
            {
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void write(BufferedWriter writer, String message) throws IOException
    {
        writer.write(message);
        if (realtimeWrite)
            writer.flush();
    }

    private static String createMessage(String inputLine, Date date)
    {
        StringBuilder message = new StringBuilder();
        message.append(date + " ");
        message.append(inputLine);

        return message.toString();
    }

    public Boolean getRealtimeWrite()
    {
        return realtimeWrite;
    }

    public void setRealtimeWrite(Boolean realtimeWrite)
    {
        this.realtimeWrite = realtimeWrite;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public SystemEnum getSystem()
    {
        return system;
    }

    public void setSystem(SystemEnum system)
    {
        this.system = system;
    }
}
