import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class ServidorSocketStream {

    public static void main(String[] args) {
        try {
            System.out.println("Creando socket servidor");

            ServerSocket serverSocket = new ServerSocket();

            System.out.println("Realizando el bind");

            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");

            Socket newSocket = serverSocket.accept();

            System.out.println("Conexión recibida");

            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();

            while (true) {
                byte[] mensaje = new byte[4];
                is.read(mensaje);
                if (new String(mensaje).equals("exit")) {
                    System.out.println("Mensaje recibido: " + new String(mensaje));
                    break;
                } else {
                    System.out.println("2 Mensaje recibido: " + new String(mensaje));
                }
                is.read(mensaje);
            }
            System.out.println("Cerrando el nuevo socket");

            newSocket.close();

            System.out.println("Cerrando el socket servidor");

            serverSocket.close();

            System.out.println("Terminado");
        } catch (IOException e) {
        }
    }
}