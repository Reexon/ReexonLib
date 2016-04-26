/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.pdf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import it.reexon.lib.list.ListUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class PDFUtils
{
    /**
     * 
     * @param sourcePDF pdf to fetch images
     * @param destinationFolder destination folder
     * 
     * @throws IOException
     * @throws FileNotFoundException
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
}
