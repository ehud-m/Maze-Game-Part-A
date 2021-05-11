package Server;


/*
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
*/
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool; // Thread pool


    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(Integer.parseInt(Configurations.getConfigInstance().loadProp().getProperty("threadPoolSize")));
    }

    public void start(){

        new Thread(() ->{
            runServer();
        }).start();
    }
    public void runServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    // Insert the new task into the thread pool:
                    threadPool.submit(() -> {
                        handleClient(clientSocket);
                    });
                } catch (SocketTimeoutException e){
                }
            }
            serverSocket.close();
            threadPool.shutdown(); // do not allow any new tasks into the thread pool (not doing anything to the current tasks and running threads)
        } catch (IOException e) {
        //    LOG.error("IOException", e);
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
      //      LOG.info("Done handling client: " + clientSocket.toString());
            clientSocket.close();
        } catch (IOException e){
  //          LOG.error("IOException", e);

        }
    }

    public void stop(){
   //     LOG.info("Stopping server...");

        stop = true;
    }
}
