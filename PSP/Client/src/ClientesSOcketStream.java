import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientesSOcketStream {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Creando socket cliente");
            Socket clienteSocket = new Socket();
            System.out.println("Estableciendo la conexión");

            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            clienteSocket.connect(addr);

            DataInputStream dis = new DataInputStream(clienteSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());

//            InputStream is = clienteSocket.getInputStream();
//            OutputStream os = clienteSocket.getOutputStream();

            System.out.println("Enviando mensaje");

            String mensaje = "";
            while (!mensaje.equalsIgnoreCase("exit")) {

                System.out.println("\nElija su operación entre las siguientes:" +
                        "\n\tCM -> Comparar un número con campos de fútbol." +
                        "\n\tJ -> Comprobar los meses que te faltan para jubilarte en relación a los años que te quedan." +
                        "\n\tPR -> Comparar un número con la bibliografía de Pérez Reverte" +
                        "\n\tG -> Diferencia de precio de gasoina en comparación a la más barata actual." +
                        "\nIntroduzca 'exit' para salir.");

                mensaje = sc.nextLine();
                mensaje = mensaje.toLowerCase();
                double num = 0;

                switch (mensaje) {
                    case "cm":
                        System.out.println("Por favor, introduzca el número que desea comparar con los metros cuadrados de un campo de futbol (7140):");
                        num = Double.parseDouble(sc.nextLine());
                        break;

                    case "j":
                        System.out.println("Por favor, introduzca el número de años que faltan para su jubilación:");
                        num = Double.parseDouble(sc.nextLine());
                        break;

                    case "pr":
                        System.out.println("Por favor, introduzca el número que desea comparar con la bibliografía de Pérez Reverte(48):");
                        num = Double.parseDouble(sc.nextLine());
                        break;

                    case "g":
                        System.out.println("Por favor, introduzca el precio de gasolina que desea comparar con la más barata de Vigo(1.519):");
                        num = Double.parseDouble(sc.nextLine());
                        break;

                    default:
                        System.out.print("Opción no encontrada. Recuerde que para abandonar el programa debe introducir 'exit'.");
                        num = 0;
                        break;

                }

                dos.writeUTF(mensaje);
                dos.writeDouble(num);
                System.out.println("Mensaje enviado: " + mensaje + " num: " + num);

//                double result = dis.readDouble();
                String toret = dis.readUTF();
                System.out.println(toret);

            }

            System.out.println("\nCerrando el socket cliente");

            clienteSocket.close();

            System.out.println("Terminado");

        } catch (IOException e) {
            System.out.println(e.getMessage());;
        } catch (NumberFormatException e) {
            System.err.println("Error en la introducción númerica.");
        }
    }
}	