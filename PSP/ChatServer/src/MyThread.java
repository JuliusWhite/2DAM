import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MyThread extends Thread {
    private final Socket clienteSocket;

    public MyThread(String name, Socket clienteSocket) {
        super(name);
        this.clienteSocket = clienteSocket;
    }

    @Override
    public void run() {
        try {
            System.out.println("\nCreando el hilo " + getName());

            // Instanciación de data input y output stream, ya que es más fácil trabajar con strings que con arreglos de bytes
            DataInputStream dis = new DataInputStream(clienteSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());

            String mensaje;
            do {
                mensaje = dis.readUTF();
                System.out.println("\nMensaje cliente " + getName() + ": " + mensaje);

                String toret;
                switch (mensaje.toLowerCase()) {
                    case "hello world", "hello", "hi" -> toret = "Hi, client!";
                    case "hola", "hola!" -> toret = "Hola, cliente!";
                    case "que hora es?", "que hora es", "what time is it?", "what time is it" -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                        LocalTime today = LocalTime.now();
                        toret = today.format(formatter);
                    }
                    case "que día es hoy?", "que día es hoy", "que dia es hoy?", "que dia es hoy", "what day is it today?", "what day is it today" ->
                            toret = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    case "exit", "bye", "goodbye" -> {
                        toret = "exit";
                        mensaje = "exit";
                    }
                    case "salir", "adios", "adiós" -> {
                        toret = "salir";
                        mensaje = "exit";
                    }
                    default -> toret = "Error de introducción";
                }

                System.out.println("Respuesta: " + toret);

                dos.writeUTF(toret);

            } while (!mensaje.equals("exit"));
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
