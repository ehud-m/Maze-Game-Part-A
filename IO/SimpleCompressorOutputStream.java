package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;
    private int counter;
    private int last;


    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
        this.counter = 0;
    }

    @Override
    public void write(int b) throws IOException {
        if (counter == 0 && last ==b )
            out.write((byte)0);
        if (last == b) {
            counter++;
            if (counter == 255){
                out.write((byte)255);
                counter = 0;
            }
        }
        else {
            last = b;
            out.write((byte)counter);
            counter = 1;
        }

    }

    public void write(byte [] b) throws IOException {
        for (int i = 0; i < 12; i++) {
            out.write(b[i]);
        }
        for (int i = 12; i < b.length; i++) {
            write(b[i]); // maybe slows the prog
        }
    }
}
