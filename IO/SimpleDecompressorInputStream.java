 package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {

    private InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int i;
        //read start indexes
        for (i = 0; i < 12; i++) {
            b[i]=(byte)read();
        }
        int byteCounter=12;
        int nextChar=1;
        int count;
        while ((count=read())!=-1) {
            byteCounter++;
            for(;count>0;count--,i++)
                b[i]=(byte)nextChar;
            nextChar=Math.abs(nextChar-1);
        }
        return byteCounter;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }
}
