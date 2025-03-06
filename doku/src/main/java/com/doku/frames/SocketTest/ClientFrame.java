package com.doku.frames.SocketTest;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class ClientFrame extends JFrame {
    private JTextField messageField;
    private JTextArea textArea;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public ClientFrame() {
        setTitle("Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        messageField = new JTextField();
        textArea = new JTextArea();
        textArea.setEditable(false);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendMessage());

        add(new JScrollPane(textArea), "Center");
        add(messageField, "South");
        add(sendButton, "North");

        setVisible(true);

        connectToServer();
    }

    public void connectToServer() {
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    socket = new Socket("localhost", 1234);  
                    writer = new PrintWriter(socket.getOutputStream(), true);  
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
                    publish("Connected to server.\n");

                    
                    String message;
                    while ((message = reader.readLine()) != null) {
                        publish("Server: " + message + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    publish("Connection error: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                for (String message : chunks) {
                    textArea.append(message);  
                }
            }
        };
        worker.execute();
    }

    public void sendMessage() {
        if (writer != null && !messageField.getText().isEmpty()) {
            writer.println(messageField.getText());  
            textArea.append("Client: " + messageField.getText() + "\n");  
            messageField.setText("");  
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientFrame::new);  
    }
}
