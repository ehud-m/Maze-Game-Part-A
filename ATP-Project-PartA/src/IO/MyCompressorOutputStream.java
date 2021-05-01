package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;
    static int counter;
    static int last;


    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
        this.counter = 0;
    }

    @Override
    public void write(int b) throws IOException {
        if (last == b)
            counter++;
        else
            last = b;


    }

    public void write(int [] b) throws IOException {

    }
}
