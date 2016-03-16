package it.reexon.reexon.lib.convertions.video;

import org.apache.commons.lang3.StringUtils;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IContainerFormat;


public class AnyMediaConverter
{
    /**
     * 
     * @param arg0 is input file
     * @param arg1 is output file
     * 
     * @throws IllegalArgumentException args is empty
     */
    public void main(String[] args)
    {
        if (StringUtils.isEmpty(args.toString()))
            throw new IllegalArgumentException("args is empty!!!");
        IMediaReader reader = ToolFactory.makeReader(args[0]);
        IMediaWriter writer = ToolFactory.makeWriter(args[1], reader);
        writer.open();
        writer.setForceInterleave(true);
        IContainerFormat outFormat = IContainerFormat.make();
        outFormat.setOutputFormat("3gp", args[1], null);
        IContainer container = writer.getContainer();
        container.open(args[1], IContainer.Type.WRITE, outFormat);
        writer.addVideoStream(0, 0, ICodec.findEncodingCodecByName("h263"), 320, 240);
        writer.addAudioStream(1, 0, ICodec.findEncodingCodecByName("libamr_nb"), 1, 8000);
        reader.addListener(writer);
        while (reader.readPacket() == null);
    }
}
