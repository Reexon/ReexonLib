package it.reexon.files.convertions;

import com.aspose.imaging.Image;
import com.aspose.imaging.imageoptions.CadRasterizationOptions;
import com.aspose.imaging.imageoptions.PdfOptions;

import it.reexon.files.exceptions.ConvertionException;


/**
 *  * @author marco.velluto
 *  
 * Exporting DWG AutoCAD Drawings to PDF
 * 
 *  Supported AutoCAD Primitives
 *  TEXT
 *  MTEXT
 *  ATTDEF
 *  ATTRIB
 *  ARC
 *  ELLIPSE
 *  HATCH
 *  LEADER
 *  POINT
 *  VERTEX 2D
 *  VERTEX 3D
 *  POLYLINE 2D
 *  LWPOLYLINE
 *  RAY
 *  CIRCLE
 *  DIMENSION ORDINATE
 *  DIMENSION LINEAR
 *  DIMENSION ALIGNED
 *  DIMENSION ANG 3Pt
 *  DIMENSION ANG 2Ln
 *  DIMENSION RADIUS
 *  DIMENSION DIAMETER
 *  SHAPE
 *  SOLID
 *  SPLINE
 *  MLINE
 *  LINE
 *  XLINE
 *  STYLE
 *  DIMSTYLE
 *  LTYPE
 *  MLINESTYLE
 *  LAYER
 *  VIEWPORT
 *  LAYOUT
 *  
 *  Memory Management
 *      The property ExactReallocateOnly is used to control memory re-allocation. Re-allocation is most likely to occur for pre-allocated caches. It can happen when the system figures out that the allocated space will not be sufficient.
 *
 *      If ExactReallocateOnly is set to the default value, false, the space is re-allocated to the same medium.
 *      When set to true, re-allocation cannot exceed the maximum specified space. In this case the existing allocated in-memory cache (which requires re-allocation) is freed and extended space is allocated on disk.
 *
 */
public class DwgToPdf
{

    /**
     * 
     *  Exporting DWG to PDF is simple as below steps:
     *
     * Load the DWG file into an instance of Image.
     * Create an object of the CadRasterizationOptions class and set the PageHeight & PageWidth properties.
     * Create an object of the PdfOptions class and set the VectorRasterizationOptions property.
     * Call Image.save while passing an object of PdfOptions as second parameter.
     * 
     * @param sourceFile
     * @param outputFile
     */
    public static void convertionSimple(String sourceFile, String outputFile)
    {
        try
        {
            Image image = Image.load(sourceFile);

            //Create an instance of CadRasterizationOptions and set its various properties
            CadRasterizationOptions rasterizationOptions = new CadRasterizationOptions();
            rasterizationOptions.setPageWidth(1600);
            rasterizationOptions.setPageHeight(1600);

            //Create an instance of PdfOptions
            PdfOptions pdfOptions = new PdfOptions();

            //Set the VectorRasterizationOptions property
            pdfOptions.setVectorRasterizationOptions(rasterizationOptions);

            //Export the CAD to PDF
            image.save(outputFile, pdfOptions);
        }
        catch (Exception e)
        {
            throw new ConvertionException(e);
        }

    }

    /**
     * Exporting Specific Layouts of DWG to PDF
     * This approach works as follows:
    
     * Load the DWG file into an instance of Image.
     * Create an object of the CadRasterizationOptions class and set the PageHeight & PageWidth properties.
     * Set the Layouts property for the CadRasterizationOptions object.
     * Create an object of the PdfOptions class and set the VectorRasterizationOptions property.
     * Export the image to PDF using the Image.save(<stream/file>, <PdfOptions instance>) method.
     * The programming sample below shows how to convert a specific layout of DWG to PDF.
     * @param sourceFile
     * @param outputFile
     */
    public static void convertionSpecificLayout(String sourceFile, String outputFile)
    {
        //Load an existing CAD in an instance of Image
        Image image = Image.load(sourceFile);

        //Create an instance of CadRasterizationOptions and set its various properties
        CadRasterizationOptions rasterizationOptions = new CadRasterizationOptions();
        rasterizationOptions.setPageWidth(1600);
        rasterizationOptions.setPageHeight(1600);
        //Specify desired layout names
        rasterizationOptions.setLayouts(new String[] { "Model", "Layout1" });

        //Create an instance of PdfOptions
        PdfOptions pdfOptions = new PdfOptions();

        //Set the VectorRasterizationOptions property
        pdfOptions.setVectorRasterizationOptions(rasterizationOptions);

        //Export the CAD to PDF
        image.save(outputFile, pdfOptions);
    }

    /**
     * Exporting Specific Layer of DXF to PDF
     * 
     * Open a DXF drawing file using the Image.load factory method.
     * Create an instance of CadRasterizationOptions and specify PageWidth & PageHeight properties.
     * Add layers to the object of CadRasterizationOptions.
     * Create an instance of PdfOptions & set its VectorRasterizationOptions property.
     * Export the drawing to PDF using the Image.save(<stream/file>, <PdfOptions instance>) method.
     * 
     * @param sourceImage
     * @param outputImage
     */
    public static void convertionSpecificLayer(String sourceImage, String outputImage)
    {
        //Load an existing CAD in an instance of Image
        Image image = Image.load(sourceImage);

        //Create an instance of CadRasterizationOptions and set its various properties
        CadRasterizationOptions rasterizationOptions = new CadRasterizationOptions();
        rasterizationOptions.setPageWidth(1600);
        rasterizationOptions.setPageHeight(1600);

        //Add desired layers
        rasterizationOptions.getLayers().add("layer1");
        rasterizationOptions.getLayers().add("layer2");

        //Create an instance of PdfOptions
        PdfOptions pdfOptions = new PdfOptions();

        //Set the VectorRasterizationOptions property
        pdfOptions.setVectorRasterizationOptions(rasterizationOptions);

        //Export the CAD to PDF
        image.save(outputImage, pdfOptions);
    }
}
