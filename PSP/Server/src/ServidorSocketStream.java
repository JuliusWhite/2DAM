import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.text.DecimalFormat;

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

            DataInputStream dis = new DataInputStream(newSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(newSocket.getOutputStream());

//            InputStream is = newSocket.getInputStream();
//            OutputStream os = newSocket.getOutputStream();

//            byte[] mensaje = new byte[4];
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
                        toret = "El resultado es que " + num + " son " + result + " campos de fútbol.";
                        break;

                    case "j":
                        System.out.println("Por favor, introduzca el número de años que faltan para su jubilación:");
                        result = num * 12;
                        toret = "Quedan " + result + "meses para su jubilación.";
                        break;

                    case "pr":
                        System.out.println("Por favor, introduzca el número que desea comparar con la bibliografía de Pérez Reverte(48):");
                        result = num / 48;
                        toret = "El resultado es que " + num + " son " + result + "bibliografías de reverte.";
                        break;

                    case "g":
                        result = num - 1.519;
                        toret = "Si pagas " + num + "€/l de gasolin estás pagando " + df.format(result) + " de más.";
                        break;

                    default:
                        System.err.print("Error de introducción");
                        break;

                }

                System.out.println("Result: " + df.format(result));
//                dos.writeDouble(result);
                dos.writeUTF(toret);

            }

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