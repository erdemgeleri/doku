package com.doku.frames.SocketTest;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class ServerFrame extends JFrame {
    private JTextArea textArea;
    private JTextField messageField;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter writer;

    public ServerFrame() {
        setTitle("Server");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), "Center");

        
        messageField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendMessage());  
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(messageField);
        bottomPanel.add(sendButton);
        add(bottomPanel, "South");

        setVisible(true);

        startServer();
    }

    public void startServer() {
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    serverSocket = new ServerSocket(1234);  
                    publish("Server started, waiting for connection...\n");

                    
                    clientSocket = serverSocket.accept();
                    publish("Client connected.\n");

                    
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    writer = new PrintWriter(clientSocket.getOutputStream(), true);  

                    
                    String message;
                    while ((message = reader.readLine()) != null) {
                        publish("Client: " + message + "\n");
                    }

                    clientSocket.close();
                    publish("Client disconnected.\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    publish("Server error: " + e.getMessage());
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
            textArea.append("Server: " + messageField.getText() + "\n"); 
            messageField.setText("");  
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ServerFrame::new);  
    }
}
