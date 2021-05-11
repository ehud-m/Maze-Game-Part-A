package test;

import Client.*;
import Server.ServerStrategyGenerateMaze;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainClient {

    public static void main(String[] args) throws UnknownHostException {
        for (int i = 0; i < 12; i++) {
            Client c = new Client(InetAddress.getLocalHost(),5400, new ClientStrategyGenerateMaze());
            c.communicateWithServer();
        }

    }
}
