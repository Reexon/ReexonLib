/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.reexon.lib.gpx.importer;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;

import it.reexon.reexon.lib.gpx.demo.xml.Employee;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class GpxImporter
{
    public static final String MAPPING_FILE_NAME = "C:\\Users\\Marco.Velluto\\git\\ReexonLib\\src\\it\\reexon\\reexon\\lib\\gpx\\demo\\xml\\mapping.xml";
    public static final String STREAM_NAME = "employeeFile";

    public GpxImporter(File fileToRead)
    {
        if (fileToRead == null)
            throw new NullPointerException("File to read is null!");

        // create a StreamFactory
        StreamFactory factory = StreamFactory.newInstance();
        // load the mapping file
        factory.load(MAPPING_FILE_NAME);

        // use a StreamFactory to create a BeanReader
        BeanReader in = null;
        try
        {
            in = factory.createReader(STREAM_NAME, fileToRead);
            List<Employee> list = new LinkedList<>();
            Employee employee;
            while ((employee = (Employee) in.read()) != null)
            {
                list.add(employee);
                // process the employee...
            }
            list.forEach(p -> System.out.println(p));
        }
        finally
        {
            if (in != null)
                in.close();
        }
    }
}
