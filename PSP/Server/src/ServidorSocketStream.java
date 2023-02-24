import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.text.DecimalFormat;

public class ServidorSocketStream {

    public static void main(String[] args) {
        try {

            // Abriendo el socket del cliente
            System.out.println("Creando socket servidor");
            ServerSocket serverSocket = new ServerSocket();

            // Aceptando conexiones con el cliente
            System.out.println("Realizando el bind");
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");
            Socket newSocket = serverSocket.accept();
            System.out.println("Conexión recibida");

            // Instanciación de data input y output stream, ya que es más fácil trabajar con strings y doubles que con arreglos de bytes
            DataInputStream dis = new DataInputStream(newSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(newSocket.getOutputStream());

            // Menteniendo abierto el servicio con las opciones de la calculadora hasta que le cliente envíe un 'exit'
            String mensaje;
            while (!(mensaje = dis.readUTF()).equals("exit")) {
                double num = dis.readDouble();

                System.out.println("\n2 Mensaje recibido: " + mensaje +
                        "\nNúmero recicido: " + num);
                double result = 0;
                String toret = "";
                DecimalFormat df = new DecimalFormat("0.000");

                switch (mensaje) {

                    case "cm":
                        result = num / 7140;
                        toret = "El resultado es que " + num + "m² son " + df.format(result) + " campos de fútbol.";
                        break;

                    case "j":
                        result = num * 12;
                        toret = "Quedan " + (int) result + " meses para su jubilación.";
                        break;

                    case "pr":
                        result = num / 48;
                        toret = "El resultado es que " + num + " libros son " + df.format(result) + " bibliografías de Reverte.";
                        break;

                    case "g":
                        result = num - 1.519;
                        if (result > 0) {
                            toret = "Si pagas " + num + "€/l de gasolina estás pagando " + df.format(result) + "€/l de más.";
                        } else toret = "Eso es imposible, yo conozco la gasolinera más barata de Vigo!";
                        break;

                    default:
                        System.err.println("Error de introducción");
                        break;

                }

//                System.out.println("Result: " + df.format(result));
//                dos.writeDouble(result);
                System.out.println(toret);
                dos.writeUTF(toret);

            }

            // Cerrando socket de server
            System.out.println("\nCerrando el nuevo socket");
            newSocket.close();
            System.out.println("Cerrando el socket servidor");
            serverSocket.close();
            System.out.println("Terminado");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}