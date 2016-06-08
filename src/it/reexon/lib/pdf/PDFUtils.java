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
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

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
    /** Owner password. */
    private static final String OWNER = "UzsPJoVh7hjzv2kdL3Vf";

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
            stamper.setEncryption(userPassword.getBytes(), OWNER.getBytes(), PdfWriter.ALLOW_PRINTING,
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
     * Decrypt a PDF
     * <pre>
     *  NOTE: works only:
     * Acrobat 3 (40-bit RC4)      -> works
     * Acrobat 5 & 6 (128-bit RC4) -> work
     * Acrobat 7 (128-bit AES)     -> work
     * Acrobat 9 (256-bit AES)     -> doesn't work
     * </pre>
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
     * Modify PDF permissions and only allows you to print the document.
     * 
     * @param srcFile           Begin file.
     * @param destFile          Destination file.  
     * @param password          User password. Can be null.
     * 
     * @throws IllegalArgumentException         If begin file or destination file are null.
     * @throws IOException                      If there is an error saving the document.
     * @throws BadSecurityHandlerException      If there is an error during protection.
     * @throws COSVisitorException              If an error occurs while generating the data.
     */
    public static final void setAccessPermissionOnlyPrint(File srcFile, File destFile, String password)
            throws IOException, BadSecurityHandlerException, COSVisitorException
    {
        if (srcFile == null)
            throw new IllegalArgumentException("Begin File cannot be null!");

        if (destFile == null)
            throw new IllegalArgumentException("Destination File cannot be null!");

        PDDocument doc = PDDocument.load(srcFile);
        AccessPermission perms = new AccessPermission(3);
        perms.setCanPrint(true);
        StandardProtectionPolicy sp = new StandardProtectionPolicy(OWNER, password, perms);
        doc.protect(sp);
        doc.save(destFile);
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

}
