package com.doku.frames.ParentFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import com.doku.model.Users.Parent;  

public class ParentMainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Parent parent;

    public ParentMainFrame(Parent parent) {
        this.parent = parent;

        setTitle("Ana Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 740, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblParentInfo = new JLabel("Anne: " + parent.getFirstName() + " " + parent.getLastName());
        lblParentInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblParentInfo.setBounds(10, 10, 300, 30);
        contentPane.add(lblParentInfo);
    }
}
