package com.doku.frames.SocketTest;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            new ServerFrame(); 
        });


        SwingUtilities.invokeLater(() -> {
            new ClientFrame(); 
        });
    }
}
