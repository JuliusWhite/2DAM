import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ClientGUI2 extends JFrame implements ActionListener {
    private JLabel mensajeLabel;
    private JTextField mensajeTextField;
    private JButton sendButton;
    private JTextArea mensajeArea;
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;

    public ClientGUI2(String title) {
        super(title);

        // Creando los componentes gráficos
        mensajeLabel = new JLabel("Mensaje:");
        mensajeTextField = new JTextField(20);
        sendButton = new JButton("Enviar");
        sendButton.addActionListener(this);
        mensajeArea = new JTextArea(10, 20);
        mensajeArea.setEditable(false);

        // Añadir los componentes a la GUI
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(mensajeLabel);
        panel.add(mensajeTextField);
        panel.add(sendButton);
        JScrollPane scrollPane = new JScrollPane(mensajeArea);
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Creando la conexion con el server
        try {
            socket = new Socket("localhost", 5555);

            // Instanciación de data input y output stream, ya que es más fácil trabajar con strings y doubles que con arreglos de bytes
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == sendButton) {
            // Se envian los datos al server
            String message = mensajeTextField.getText();

            mensajeArea.append("Tu: " + message + "\n");
            try {
                dos.writeUTF(message);

                // Leyendo datos del server
                String serverMsg = dis.readUTF();
                if (serverMsg.equals("exit")) {
                    mensajeArea.append("Server desconectado, adiós cliente\n\n");
                } else mensajeArea.append("Server: " + serverMsg + "\n\n");
            } catch (IOException e) {
                mensajeArea.append("Server desconectado.\n");
                throw new RuntimeException(e);
            }
            mensajeTextField.setText("");
        }
    }

    public static void main(String[] args) {
        ClientGUI client = new ClientGUI("Cliente 2");
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.pack();
        client.setVisible(true);
    }
}
