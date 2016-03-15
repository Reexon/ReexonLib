package it.reexon.reexon.lib.convertions.video.tests;

import java.io.File;

import org.junit.Test;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaViewer;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IContainerFormat;


/**
 * @see http://www.ffmpeg.org/general.html#Video-Codecs
 * */
public class AnyMediaConverterTest
{

    @Test
    public final void testV1()
    {
        String path = null;
        String outpath = null;

        IMediaReader reader = ToolFactory.makeReader(path);
        IMediaWriter writer = ToolFactory.makeWriter(outpath, reader);
        writer.open();
        writer.setForceInterleave(true);
        IContainerFormat outFormat = IContainerFormat.make();
        outFormat.setOutputFormat("mp4", outpath, null);
        IContainer container = writer.getContainer();
        container.open(outpath, IContainer.Type.WRITE, outFormat);
        writer.addVideoStream(0, 0, ICodec.findEncodingCodecByName("h264"), 1920, 1080);
        writer.addAudioStream(1, 0, ICodec.findEncodingCodecByName("mp3"), 0, 0);
        reader.addListener(writer);
        while (reader.readPacket() == null);
    }

    @Test
    public final void testV2()
    {
        String inputFile = null;
        String outputFile = null;

        IMediaReader reader = ToolFactory.makeReader(inputFile);
        IMediaWriter writer = ToolFactory.makeWriter(outputFile, reader);
        writer.open();
        writer.setForceInterleave(true);
        IContainerFormat outFormat = IContainerFormat.make();
        outFormat.setOutputFormat("3gp", outputFile, null);
        IContainer container = writer.getContainer();
        container.open(outputFile, IContainer.Type.WRITE, outFormat);
        writer.addVideoStream(0, 0, ICodec.findEncodingCodecByName("h263"), 320, 240);
        writer.addAudioStream(1, 0, ICodec.findEncodingCodecByName("libamr_nb"), 1, 8000);
        reader.addListener(writer);
        while (reader.readPacket() == null);

    }

    @Test
    public final void testV3()
    {
        final String inputFileName = "C:\\temp\\TEST.MOV";
        final String outputFileName = "C:\\temp\\TEST.MP4";

        Long st = System.currentTimeMillis();

        //Create a media reader
        IMediaReader mediaReader = ToolFactory.makeReader(inputFileName);
        // create a media writer  
        IMediaWriter mediaWriter = ToolFactory.makeWriter(outputFileName, mediaReader);

        // add a writer to the reader, to create the output file  
        mediaReader.addListener(mediaWriter);

        // create a media viewer with stats enabled  
        IMediaViewer mediaViewer = ToolFactory.makeViewer(true);

        // add a viewer to the reader, to see the decoded media  
        mediaReader.addListener(mediaViewer);

        // read and decode packets from the source file and  
        // and dispatch decoded audio and video to the writer  

        while (mediaReader.readPacket() == null);

        Long end = System.currentTimeMillis();
        System.out.println("Time Taken In Milli Seconds: " + (end - st));
    }

    /** EXAMPLES 
     * @see http://www.programcreek.com/java-api-examples/index.php?api=com.xuggle.mediatool.IMediaReader 
     * @see http://www.programcreek.com/java-api-examples/index.php?source_dir=Livestream-Processor-master/src/main/java/net/sapium/livestreamprocessor/utils/Processor.java
     * 
     * */

    /**
    * Concatenates a list of video files all must have the same frame size, audio rates, number of channels, and filetype
    * 
    * @param files
    *            array of files to concatenate together in the order of this array
    * @param output
    *            location of the output file
    */
    // TODO: Error handling for when the files array has files of different types
    public void concatenateFiles(ProgressChangedListener listener, File[] files, String output)
    {
        logger.info("Concatenating files and saving as " + output);

        MediaConcatenator concatenator = new MediaConcatenator(0, 1);

        IMediaReader[] readers = new IMediaReader[files.length];
        VideoData data = null;
        long duration = 0;
        for (int i = 0; i < files.length; i++)
        {
            data = new VideoData(files[i]);
            IMediaReader reader = data.getReader();
            reader.addListener(concatenator);
            readers[i] = reader;

            duration += data.getDuration();
            logger.info(files[i].getAbsolutePath());
        }

        IMediaWriter writer = ToolFactory.makeWriter(output);
        if (listener != null)
        {
            ProgressListener progress = new ProgressListener(duration, listener);
            writer.addListener(progress);
        }
        concatenator.addListener(writer);

        writer.addVideoStream(0, 1, data.getWidth(), data.getHeight());
        writer.addAudioStream(1, 0, data.getAudioChannels(), data.getAudioSampleRate());

        for (int i = 0; i < readers.length; i++)
        {
            while (this.shouldContinue() && readers[i].readPacket() == null);
        }

        writer.close();

        for (int i = 0; i < readers.length; i++)
        {
            readers[i].close();
        }

        if (!this.shouldContinue())
        {
            new File(output).delete();
            listener.onTaskEnded();
        }
    }
}
