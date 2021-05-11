package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class CompressorFactory {
    public static OutputStream getCompressor(ByteArrayOutputStream byteStream){
        switch (Configurations.getConfigInstance().loadProp().getProperty("compressor")) {
            case "MyCompressorOutputStream":
                return new MyCompressorOutputStream(byteStream);
            default:
                return new SimpleCompressorOutputStream(byteStream);
        }
    }
}
