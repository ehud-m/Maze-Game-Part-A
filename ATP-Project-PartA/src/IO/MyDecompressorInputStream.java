package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() {
        try {
            return in.read();
        } catch (IOException e) {

        }
        return -1;
    }

    private static byte[] numToBinary(byte b) {
        int number = b & 0xFF;
        byte[] bin=new byte[8];
        for( int i=7;i>=0;i--) {
            bin[i]=(byte)(number%2);
            number=number/2;
        }
        return bin;
    }

    public int read(byte[] b) {
        try {
            int i;
            for (i = 0; i < 12; i++) {
                b[i] = (byte) read();
            }
            for (; i < b.length; ) {
                byte[] bin = numToBinary((byte) in.read());
                for (int j = 0; j < 8 && i < b.length; i++, j++)
                    b[i] = bin[j];
            }
        }
        catch (Exception e) {}
        return 0;
    }

}
