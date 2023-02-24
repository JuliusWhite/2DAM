import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Calc extends JFrame {
    private JPanel panel1;
    private JButton camposFutbolButton;
    private JButton mesesJubilacionButton;
    private JButton perezReverteButton;
    private JButton gasolinaButton;
    private JButton exitButton;

    public Calc() {

        super("Calcualtor");
        setContentPane(panel1);

        camposFutbolButton.addActionListener(e -> {
            sendRequest("cm");
        });
        mesesJubilacionButton.addActionListener(e -> {
            sendRequest("j");
        });
        perezReverteButton.addActionListener(e -> {
            sendRequest("pr");
        });
        gasolinaButton.addActionListener(e -> {
            sendRequest("g");
        });
        exitButton.addActionListener(e -> {
            sendRequest("exit");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new Calc();
            frame.setSize(400, 300);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
        });
    }

    public void sendRequest(String mensaje) {
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

            System.out.println("Enviando mensaje");

                System.out.println("\n\tCM -> Comparar un número con campos de fútbol." +
                        "\n\tJ -> Comprobar los meses que te faltan para jubilarte en relación a los años que te quedan." +
                        "\n\tPR -> Comparar un número con la bibliografía de Pérez Reverte" +
                        "\n\tG -> Diferencia de precio de gasoina en comparación a la más barata actual." +
                        "\n\t'exit' -> para salir.");

                double num = 0;

                try {
                    switch (mensaje) {
                        case "cm":
                            num = Double.parseDouble(JOptionPane.showInputDialog(null, "Por favor, introduzca el número que desea comparar con los metros cuadrados de un campo de futbol (7140 m²):", "Campos de fútbol", JOptionPane.QUESTION_MESSAGE));
                            break;

                        case "j":
                            num = Double.parseDouble(JOptionPane.showInputDialog(null, "Por favor, introduzca el número de años que faltan para su jubilación:", "Meses jubilación", JOptionPane.QUESTION_MESSAGE));
                            break;

                        case "pr":
                            num = Double.parseDouble(JOptionPane.showInputDialog(null, "Por favor, introduzca el número que desea comparar con la bibliografía de Pérez Reverte(48 libros):", "Pérez Reverte", JOptionPane.QUESTION_MESSAGE));
                            break;

                        case "g":
                            num = Double.parseDouble(JOptionPane.showInputDialog(null, "Por favor, introduzca el precio de gasolina que desea comparar con la más barata de Vigo(1.519€/l):", "Gasolinera", JOptionPane.QUESTION_MESSAGE));
                            break;

                        case "exit":
                            // Cerrando el socket del cliente
                            dos.writeUTF(mensaje);

                            System.out.println("\nCerrando el socket cliente");

                            clienteSocket.close();

                            System.out.println("Terminado");

                            System.out.println("Hasta la próxima, cuñado!");

                            System.exit(0);

                            break;

                        default:
                            System.out.print("Opción no encontrada. Recuerde que para abandonar el programa debe introducir 'exit'.");
                            num = 0;
                            break;

                    }

                    // Se envian los datos al server
                    dos.writeUTF(mensaje);
                    dos.writeDouble(num);

                    // Se leen los datos que deveulve el server
                    String toret = dis.readUTF();
                    JOptionPane.showMessageDialog(null, toret);

                    // En caso de introducir mal un dato numérico, se avisa al usuario y se devuelve el menú de nuevo
                } catch (NumberFormatException e) {
                    System.err.println("\tError en la introducción de datos, subucaba un número no una caderna de texto.");
                }

            dis.close();
            dos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
