package test;

import Client.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainClientCreate {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 1; i++) {
                Client client = new Client(InetAddress.getLocalHost(), 5402, new ClientStrategyGenerateMaze());
                new Thread(()->{
                    client.communicateWithServer();
                }).start();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
