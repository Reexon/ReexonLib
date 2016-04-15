package it.reexon.reexon.lib.gpx.demo.xml;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;


public class BeanReaderExample
{
    public static void main(String[] args) throws Exception
    {
        // create a StreamFactory
        StreamFactory factory = StreamFactory.newInstance();
        // load the mapping file
        factory.load("C:\\Users\\Marco.Velluto\\git\\ReexonLib\\src\\it\\reexon\\reexon\\lib\\gpx\\demo\\xml\\mapping.xml");

        // use a StreamFactory to create a BeanReader
        BeanReader in = factory.createReader("employeeFile",
                                             new File("C:\\Users\\Marco.Velluto\\git\\ReexonLib\\src\\it\\reexon\\reexon\\lib\\gpx\\demo\\xml\\employee.xml"));
        List<Employee> list = new LinkedList<>();
        Employee employee;
        while ((employee = (Employee) in.read()) != null)
        {
            list.add(employee);
            // process the employee...
        }
        in.close();
    }
}
