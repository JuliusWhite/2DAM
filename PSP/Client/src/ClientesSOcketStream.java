import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

            InputStream is = clienteSocket.getInputStream();
            OutputStream os = clienteSocket.getOutputStream();

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
                os.write(mensaje.getBytes());
                switch (mensaje.toLowerCase()){
                    case "cm":
                        System.out.println("Por favor, introduzca el número que desea comparar con los metros cuadrados de un campo de futbol (7140):");
                        break;

                    case "j":
                        System.out.println("Por favor, introduzca el número de años que faltan para su jubilación:");
                        break;

                    case "pr":
                        System.out.println("Por favor, introduzca el número que desea comparar con la bibliografía de Pérez Reverte(48):");
                        break;

                    case "g":
                        System.out.println("Por favor, introduzca el precio de gasolina que desea comparar con la más barata de Vigo(1.519):");
                        break;

                    default:
                        System.out.print("Opción no encontrada. Recuerde que para abandonar el programa debe introducir 'exit'.");
                        break;

                }

                System.out.println("Mensaje enviado");
            }

            System.out.println("\nCerrando el socket cliente");

            clienteSocket.close();

            System.out.println("Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}	