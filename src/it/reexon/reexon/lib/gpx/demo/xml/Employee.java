package it.reexon.reexon.lib.gpx.demo.xml;

import java.util.Date;

public class Employee
{
    private String firstName;
    private String lastName;
    private String title;
    private int salary;
    private Date hireDate;
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public int getSalary()
    {
        return salary;
    }
    public void setSalary(int salary)
    {
        this.salary = salary;
    }
    public Date getHireDate()
    {
        return hireDate;
    }
    public void setHireDate(Date hireDate)
    {
        this.hireDate = hireDate;
    }
    @Override
    public String toString()
    {
        return String.format("Employee [firstName=%s, lastName=%s, title=%s, salary=%s, hireDate=%s]", firstName, lastName, title, salary, hireDate);
    }
    
    
}
