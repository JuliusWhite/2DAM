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


            byte[] mensaje = new byte[4];
            is.read(mensaje);
            while (true) {
                if (!new String(mensaje).equals("exit")) {
                    System.out.println("Mensaje recibido: " + new String(mensaje));
                } else {
                    System.out.println("Mensaje recibido: " + new String(mensaje));

                    System.out.println("Cerrando el nuevo socket");

                    newSocket.close();

                    System.out.println("Cerrando el socket servidor");

                    serverSocket.close();

                    System.out.println("Terminado");
                    break;
                }
                is.read(mensaje);
            }
        } catch (IOException e) {
        }
    }
}