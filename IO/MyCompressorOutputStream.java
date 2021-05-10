package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    private byte binaryToByte(byte[] b,int index) {
        int x=0;
        for (int i=0;i<8 && index+i<b.length ;i++)
            x+=b[index+i]*Math.pow(2,7-i);
        return (byte)x;
    }

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
            }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    public void write(byte[] b) throws IOException {
        int i;
        for (i = 0; i < 12; i++) {
            write(b[i]);
        }

        for (;  i<b.length ; i+=8) {
            write(binaryToByte(b,i));
        }
    }

    /*public static void main(String[] args) {
        int x =128;
        System.out.println((byte)x);
    }*/
}
