import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static int cont = 0;
    public static void main(String[] args) {
        try{
// Abriendo el socket del cliente
            System.out.println("Creando socket servidor");
            ServerSocket serverSocket = new ServerSocket();

            // Aceptando conexiones con el cliente
            System.out.println("Realizando el bind");
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            serverSocket.bind(addr);

            while (true) {
                System.out.println("Aceptando nuevas conexiones");
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Conexión recibida");
                cont++;

                System.out.println("New client connected: " + clienteSocket.getInetAddress().getHostAddress());

                // Create a new thread for the client
                MyThread thread = new MyThread(String.valueOf(cont) ,clienteSocket);
                thread.start();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}