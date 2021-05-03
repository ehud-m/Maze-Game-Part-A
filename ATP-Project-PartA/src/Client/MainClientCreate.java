package Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainClientCreate {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 12; i++) {
                Client client = new Client(InetAddress.getLocalHost(), 5402, new ClientStrategyGenerateMaze());
                new Thread(()->{
                    client.start();
                }).start();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
