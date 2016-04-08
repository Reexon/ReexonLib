package it.reexon.reexon.lib.security.applications;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

import it.reexon.reexon.lib.files.FileUtils;
import it.reexon.reexon.lib.security.checksums.GenerateSecureChecksum;


public class SecurityApp
{

    private static final String APP_NAME = "SecurityApp";
    private static final File FILE_LOG = new File("C:\\TEMP\\log.txt");

    public static void main(String[] args) throws IOException
    {
        try (Scanner scanner = new Scanner(System.in);)
        {
            System.out.println("Welcome to " + APP_NAME);
            while (run(scanner));
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

    /**
     * 
     * @param scanner
     * @return true if continue
     * @throws IOException 
     */
    private static Boolean run(Scanner scanner) throws IOException
    {
        try
        {
            System.out.println("Select one of the following operations");
            System.out.println("a - Generate a checksum from file");
            System.out.println("b - Check if two files is equals");
            System.out.println("e - Exit");
            //*** more
            String caseStr = null;
            if (scanner.hasNextLine())
            {
                caseStr = scanner.nextLine();
            }
            if (caseStr == null)
            {
                throw new InvalidParameterException("No choice has been found");
            }

            char choise = caseStr.trim().toUpperCase().charAt(0);
            switch (choise)
            {
                case 'A':
                    generateCheksumFromFile();
                    break;
                case 'B':
                    checkTwoFiles();
                    break;
                case 'E':
                    return false;

                default:
                    throw new InvalidParameterException("The choice is not valid");
            }
        }
        catch (InvalidParameterException e)
        {
            System.err.println(e.getLocalizedMessage());
        }
        catch (Exception e)
        {
            System.err.println("Error : " + e.getLocalizedMessage());
        }
        return true;
    }

    private static void checkTwoFiles()
    {
        try (Scanner scanner = new Scanner(System.in))
        {
            System.out.print("Enter first path file: ");
            File firstFile = new File(scanner.nextLine());

            System.out.print("Enter second path file: ");
            File secondFile = new File(scanner.nextLine());

            Boolean result = FileUtils.checkEqualFiles(firstFile, secondFile);
            if (result == null)
            {
                System.out.print("There was an error while checking files");
                return;
            }
            if (result)
                System.out.print("Both files are equals!");
            else
                System.out.print("The files are different!");
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getLocalizedMessage());
        }
    }

    private static void generateCheksumFromFile()
    {
        try (Scanner scanner = new Scanner(System.in))
        {
            System.out.print("Enter path file from generate checksum: ");
            System.out.print("Checksum: " + GenerateSecureChecksum.getChecksum(new File(scanner.nextLine())));
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getLocalizedMessage());
        }
    }

}
