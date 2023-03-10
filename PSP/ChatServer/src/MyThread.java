import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MyThread extends Thread {
    private Socket clienteSocket;

    public MyThread(String name, Socket clienteSocket) {
        super(name);
        this.clienteSocket = clienteSocket;
    }

    @Override
    public void run() {
        try {
            System.out.println("\nCreando el hilo " + getName() + "\n");

            // Instanciación de data input y output stream, ya que es más fácil trabajar con strings y doubles que con arreglos de bytes
            DataInputStream dis = new DataInputStream(clienteSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());

            String mensaje;
            while (true) {
                mensaje = dis.readUTF();
                System.out.println("\nMensaje cliente " + getName() + ": " + mensaje);

                String toret = "";
                switch (mensaje.toLowerCase()) {

                    case "hello world":
                    case "hello":
                    case "hi":
                        toret = "Hi, client!";
                        break;

                    case "hola":
                    case "hola!":
                        toret = "Hola, cliente!";
                        break;

                    case "que hora es?":
                    case "que hora es":
                    case "what time is it?":
                    case "what time is it":
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                        LocalTime today = LocalTime.now();

                        toret = today.format(formatter);
                        break;

                    case "que día es hoy?":
                    case "que día es hoy":
                    case "que dia es hoy?":
                    case "que dia es hoy":
                    case "what day is it today?":
                    case "what day is it today":
                        toret = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString();
                        break;

                    default:
                        toret = "Error de introducción";
                        break;

                    case "exit":
                    case "salir":
                        toret = "exit";
                        break;
                }

                System.out.println("Respuesta: " + toret);

                dos.writeUTF(toret);

                if (mensaje.equals("exit")) {
                    break;
                }
            }
            // Cerrando datastreams y el socket del cliente
            dis.close();
            dos.close();
            System.out.println("\nCerrando el hilo " + getName());
            clienteSocket.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
