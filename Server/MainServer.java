package Server;

import java.util.Scanner;

public class MainServer {
    public static void main(String[] args) {
        // Do not print all of the Debug notes:
        //Configurator.setRootLevel(Level.INFO);
        System.out.println("This is the MainServer class");
        System.out.println("We will now start the operation of the Server class using the StringReverser strategy");
        System.out.println("In order to stop the server, please write 'exit' in the console");
        System.out.println();
        Server serverCreate = new Server(5402, 1000, new ServerStrategyGenerateMaze());
        new Thread(()->{
            serverCreate.runServer();
        }).start();
        /*Server serverSolve = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        new Thread(()->{
            serverSolve.runServer();
        }).start();*/
        String operation;
        Scanner scanner = new Scanner(System.in);
        do{
            operation = scanner.nextLine();
        } while (!operation.equalsIgnoreCase("exit"));
        serverCreate.stop();
        //serverSolve.stop();
    }
}
