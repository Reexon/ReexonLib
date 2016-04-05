package it.reexon.reexon.lib.pdf;

import java.io.File;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;


public class PDFApp
{

    public static void main(String[] args)
    {
        try (Scanner scanner = new Scanner(System.in);)
        {
            boolean continueValue = true;
            while (continueValue)
            {
                System.out.print("Enter file to extrat images: ");
                String pdf = scanner.nextLine();

                System.out.print("Enter the output folder: ");
                String output = scanner.nextLine();

                PDFUtils.createImages(new File(pdf), new File(output));

                System.out.print("Do you want to continue ? (y/n): ");
                String response = scanner.nextLine();
                if (StringUtils.equalsIgnoreCase("N", response))
                {
                    continueValue = false;
                }
            }
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getLocalizedMessage());
        }
        finally
        {
            System.out.println("Bye :)");
        }

    }
}
