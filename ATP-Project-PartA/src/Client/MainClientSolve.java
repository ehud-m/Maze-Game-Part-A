package Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainClientSolve {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 30; i++) {
                Client client = new Client(InetAddress.getLocalHost(), 5401, new ClientStrategySolveMaze());
                new Thread(()->{
                    client.start();
                }).start();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
