import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MyThread extends Thread {
    private Socket clienteSocket;

    public MyThread(String name, Socket clienteSocket) {
        super(name);
        this.clienteSocket = clienteSocket;
    }

    @Override
    public void run() {
        try {
            // Instanciación de data input y output stream, ya que es más fácil trabajar con strings y doubles que con arreglos de bytes
            DataInputStream dis = new DataInputStream(clienteSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());

            String mensaje;
            while ((mensaje = dis.readUTF()) != null) {
                System.out.println("\n2 Mensaje recibido: " + mensaje);

                switch (mensaje.toLowerCase()) {

                    case "hello":
                        dos.writeUTF("Hola, cliente!");
                        break;

                    default:
                        dos.writeUTF("Error de introducción");
                        break;

                    case "exit":
                        dos.writeUTF("exit");
                        break;
                }

                if (mensaje.equals("exit")) {
                    break;
                }
            }
            // Cerrando deatastreams y el socket del cliente
            dis.close();
            dos.close();

            System.out.println("\nCerrando el hilo " + getName());
            ;
            clienteSocket.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
