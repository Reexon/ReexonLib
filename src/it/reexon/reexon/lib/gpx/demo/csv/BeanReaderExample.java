package it.reexon.reexon.lib.gpx.demo.csv;

import java.io.File;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;


public class BeanReaderExample
{
    public static void main(String[] args) throws Exception
    {
        // create a StreamFactory
        StreamFactory factory = StreamFactory.newInstance();
        // load the mapping file
        factory.load("C:\\Users\\Marco.Velluto\\git\\ReexonLib\\src\\it\\reexon\\reexon\\lib\\gpx\\demo\\csv\\mapping.xml");

        // use a StreamFactory to create a BeanReader
        BeanReader in = factory.createReader("employeeFile",
                                             new File("C:\\Users\\Marco.Velluto\\git\\ReexonLib\\src\\it\\reexon\\reexon\\lib\\gpx\\demo\\csv\\employee.csv"));
        Employee employee;
        while ((employee = (Employee) in.read()) != null)
        {
            employee.getFirstName();
            // process the employee...
        }
        in.close();
    }
}
