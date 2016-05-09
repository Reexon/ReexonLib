/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.pdf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import it.reexon.lib.list.ListUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class PDFUtils
{
    /** User password. */
    private static final byte[] USER = "wzQgwMt0Kd2zHD3CKObd".getBytes();;

    /** Owner password. */
    private static final byte[] OWNER = "UzsPJoVh7hjzv2kdL3Vf".getBytes();;

    /**
     * Extracts and saves the file system each PDF page as if it were an extension PNG
     * 
     * @param sourcePDF pdf to fetch images
     * @param destinationFolder destination folder
     * 
     * @throws IOException              - If there is an error reading from the stream.
     * @throws FileNotFoundException    - If sourcePDF doesen't exist
     */
    public static void createImages(File sourcePDF, File destinationFolder) throws IOException, FileNotFoundException
    {
        if (!destinationFolder.exists())
        {
            destinationFolder.mkdir();
            System.out.println("Folder Created -> " + destinationFolder.getAbsolutePath());
        }
        if (sourcePDF.exists())
        {
            try (PDDocument document = PDDocument.load(sourcePDF);)
            {
                System.out.println("Images copied to Folder: " + destinationFolder.getName());

                List<?> list = document.getDocumentCatalog().getAllPages();
                List<PDPage> listPage = ListUtils.castList(PDPage.class, list);

                System.out.println("Total files to be converted -> " + list.size());

                String fileName = sourcePDF.getName().replace(".pdf", "");
                int pageNumber = 1;
                for (PDPage page : listPage)
                {
                    BufferedImage image = page.convertToImage();
                    File outputfile = new File(destinationFolder + "\\" + fileName + "_" + pageNumber + ".png");
                    System.out.println("Image Created -> " + outputfile.getName());
                    ImageIO.write(image, "png", outputfile);
                    pageNumber++;
                }
                System.out.println("Converted Images are saved at -> " + destinationFolder.getAbsolutePath());
            }
        }
        else
        {
            System.err.println(sourcePDF.getName() + " File not exists");
            throw new FileNotFoundException(sourcePDF.getName() + " File not exists");
        }
    }

    /**
     * Encrypt a PDF with AES 128
     * 
     * @param src       src pdf to crypt
     * @param dest      destination to pdf crypted
     * @throws IOException              - An I/O error
     * @throws DocumentException        - If the document is already open
     * @throws IllegalArgumentException - If some of the parameters is empty or null
     */
    public static void encryptPdf(String src, String dest, String userPassword) throws IOException, DocumentException
    {
        if (StringUtils.isBlank(src))
            throw new IllegalArgumentException("src cannot be null or blank");

        if (StringUtils.isBlank(dest))
            throw new IllegalArgumentException("dest cannot be null or blank");

        if (StringUtils.isEmpty(userPassword))
            throw new IllegalArgumentException("dest cannot be null or empty");

        PdfReader reader = null;
        PdfStamper stamper = null;
        try
        {
            reader = new PdfReader(src);
            stamper = new PdfStamper(reader, new FileOutputStream(dest));
            stamper.setEncryption(userPassword.getBytes(), OWNER, PdfWriter.ALLOW_PRINTING,
                                  PdfWriter.ENCRYPTION_AES_128 | PdfWriter.DO_NOT_ENCRYPT_METADATA); //With AES 256 doesn't work decryption method
        }
        finally
        {
            try
            {
                if (stamper != null)
                    stamper.close();
            }
            catch (@SuppressWarnings("unused") Exception e)
            {}
            try
            {
                if (reader != null)
                    reader.close();
            }
            catch (@SuppressWarnings("unused") Exception e)
            {}
        }
    }

    /**
     * Encrypt a PDF
     * <br> NOTE: works only:<br>
     * <br> Acrobat 3 (40-bit RC4) -> works
     * <br> Acrobat 5 & 6 (128-bit RC4) -> work
     * <br> Acrobat 7 (128-bit AES) -> work
     * <br> Acrobat 9 (256-bit AES) -> doesn't work
     * 
     * @param src       src pdf to crypt
     * @param dest      destination to pdf crypted
     * @throws IOException              - An I/O error
     * @throws CryptographyException    - If there is an error decrypting the document.
     * @throws COSVisitorException      - If an error occurs while generating the data.
     * @throws DocumentException        - If the document is already open
     * @throws IllegalArgumentException - If some of the parameters is empty or null
     */
    public static void decrypt(String src, String dest, String userPassword) throws IOException, CryptographyException, COSVisitorException
    {

        if (StringUtils.isBlank(src))
            throw new IllegalArgumentException("src cannot be null or blank");

        if (StringUtils.isBlank(dest))
            throw new IllegalArgumentException("dest cannot be null or blank");

        if (StringUtils.isEmpty(userPassword))
            throw new IllegalArgumentException("dest cannot be null or empty");

        PDDocument doc = null;
        try
        {
            doc = PDDocument.load(src);
            if (doc.isEncrypted())
            {
                doc.decrypt(userPassword);
                doc.setAllSecurityToBeRemoved(true);
            }
            doc.save(dest);
        }
        finally
        {
            try
            {
                if (doc != null)
                    doc.close();
            }
            catch (@SuppressWarnings("unused") Exception e)
            {}
        }
    }

    /**
     * Return true if PDF document is encrypted
     * 
     * @param src                       src pdf to crypt
     * @return                          - true if PDF docuemnt is encrypted
     * @throws IOException              - If there is an error reading from the stream.
     * @throws IllegalArgumentException - If src is null or blank
     */
    public static boolean isEncrypted(String src) throws IOException
    {
        if (StringUtils.isBlank(src))
            throw new IllegalArgumentException("src cannot be null or blank");

        PDDocument doc = null;
        try
        {
            doc = PDDocument.load(src);
            if (doc.isEncrypted())
                return true;
            return false;
        }
        finally
        {
            try
            {
                if (doc != null)
                    doc.close();
            }
            catch (@SuppressWarnings("unused") Exception e)
            {}
        }
    }

    /**
     * @return static user password 
     */
    public static byte[] getStaticUserPassword()
    {
        return USER;
    }
}
