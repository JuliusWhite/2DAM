import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientesSOcketStream {

    public static void main(String[] args) {
        try {
            // Creacion de un scanner para leer los mensajes introducidos por teclado
            Scanner sc = new Scanner(System.in);

            // Abriendo el socket del cliente
            System.out.println("Creando socket cliente");
            Socket clienteSocket = new Socket();

            // Estableciendo conexiones
            System.out.println("Estableciendo la conexión");
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            clienteSocket.connect(addr);

            // Instanciación de data input y output stream, ya que es más fácil trabajar con strings y doubles que con arreglos de bytes
            DataInputStream dis = new DataInputStream(clienteSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());

//            InputStream is = clienteSocket.getInputStream();
//            OutputStream os = clienteSocket.getOutputStream();

            System.out.println("Enviando mensaje");

            // Empieza un menú con las opciones que permite el server, solo se cerrará cuando se introduzca la cadena 'exit'
            String mensaje = "";
            while (!mensaje.equalsIgnoreCase("exit")) {

                System.out.println("\nElija su operación entre las siguientes:" +
                        "\n\tCM -> Comparar un número con campos de fútbol." +
                        "\n\tJ -> Comprobar los meses que te faltan para jubilarte en relación a los años que te quedan." +
                        "\n\tPR -> Comparar un número con la bibliografía de Pérez Reverte" +
                        "\n\tG -> Diferencia de precio de gasoina en comparación a la más barata actual." +
                        "\nIntroduzca 'exit' para salir.");

                // Lee el mensaje por teclado
                mensaje = sc.nextLine();
                mensaje = mensaje.toLowerCase();

                if (mensaje.equals("exit")){
                    System.out.println("Hasta la próxima, cuñado!");
                    break;
                }

                double num = 0;

                try {

                    switch (mensaje) {
                        case "cm":
                            System.out.println("Por favor, introduzca el número que desea comparar con los metros cuadrados de un campo de futbol (7140 m²):");
                            num = Double.parseDouble(sc.nextLine());
                            break;

                        case "j":
                            System.out.println("Por favor, introduzca el número de años que faltan para su jubilación:");
                            num = Double.parseDouble(sc.nextLine());
                            break;

                        case "pr":
                            System.out.println("Por favor, introduzca el número que desea comparar con la bibliografía de Pérez Reverte(48 libros):");
                            num = Double.parseDouble(sc.nextLine());
                            break;

                        case "g":
                            System.out.println("Por favor, introduzca el precio de gasolina que desea comparar con la más barata de Vigo(1.519€/l):");
                            num = Double.parseDouble(sc.nextLine());
                            break;

                        default:
                            System.out.print("Opción no encontrada. Recuerde que para abandonar el programa debe introducir 'exit'.");
                            num = 0;
                            break;

                    }

                    // En caso de introducir mal un dato numérico, se avisa al usuario y se devuelve el menú de nuevo
                } catch (NumberFormatException e) {
                    System.err.println("\tError en la introducción de datos, subucaba un número no una caderna de texto.");
                }

                // Se envian los datos al server
                dos.writeUTF(mensaje);
                dos.writeDouble(num);

//                System.out.println("Mensaje enviado: " + mensaje + " num: " + num);
//                double result = dis.readDouble();

                // Se leen los datos que deveulve el server
                String toret = dis.readUTF();
                System.out.println(toret);

            }

            // Cerrando el socket del cliente
            System.out.println("\nCerrando el socket cliente");

            clienteSocket.close();

            System.out.println("Terminado");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}	