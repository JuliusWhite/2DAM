import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ClientGUI extends JFrame implements ActionListener {
    private JLabel messageLabel;
    private JTextField messageTextField;
    private JButton sendButton;
    private JTextArea messageArea;
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;

    public ClientGUI(String title) {
        super(title);

        // Create the GUI components
        messageLabel = new JLabel("Message:");
        messageTextField = new JTextField(20);
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        messageArea = new JTextArea(10, 20);
        messageArea.setEditable(false);

        // Add the GUI components to the frame
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(messageLabel);
        panel.add(messageTextField);
        panel.add(sendButton);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Set up the networking
        try {
            socket = new Socket("localhost", 5555);

            // Instanciación de data input y output stream, ya que es más fácil trabajar con strings y doubles que con arreglos de bytes
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Start a new thread to receive messages from the server
        Thread thread = new Thread(new MessageReceiver());
        thread.start();
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == sendButton) {
            // Send the message to the server
            String message = messageTextField.getText();

            messageArea.append("You: " + message + "\n");
            try {
                dos.writeUTF(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            messageTextField.setText("");
        }
    }

    private class MessageReceiver implements Runnable {
        public void run() {
            try {
                String message;
                while ((message = dis.readUTF()) != null) {
                    if (message.equals("exit")){
                        messageArea.append("Adiós cliente\n");
                        break;
                    }
                    else messageArea.append("Server: " + message + "\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Create the client GUI and show it
        ClientGUI client = new ClientGUI("Client");
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.pack();
        client.setVisible(true);
    }
}
