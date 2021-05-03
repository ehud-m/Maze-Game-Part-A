package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class CompressorFactory {
    private static volatile Object o = new Object();
    public static OutputStream getCompressor(ByteArrayOutputStream byteStream){
        synchronized (o){
            switch (Configurations.getConfigInstance().loadProp().getProperty("compressor")) {
                case "MyCompressorOutputStream":
                    return new MyCompressorOutputStream(byteStream);
                default:
                    return new SimpleCompressorOutputStream(byteStream);
            }
        }
    }
}
