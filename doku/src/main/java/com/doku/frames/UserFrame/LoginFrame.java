package com.doku.frames.UserFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.doku.frames.ParentFrame.ParentMainFrame;
import com.doku.model.Users.Parent;
import com.doku.service.ParentService;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tbEmail;
	private JPasswordField tbPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 255, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogin = new JButton("Giriş Yap");
		btnLogin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String email = tbEmail.getText();
		        char[] passwordChars = tbPassword.getPassword();
		        String password = new String(passwordChars);
		        

		        Arrays.fill(passwordChars, ' '); 
		        System.out.println("Frame'den gelen şifre: " + password);

		        if (email.trim().isEmpty() || password.trim().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Email veya Şifre alanları boş bırakılamaz!");
		            return;
		        }
		        JOptionPane.showMessageDialog(null, email);
		        JOptionPane.showMessageDialog(null, password);
		        ParentService parentService = new ParentService();
		        Parent parent = parentService.authenticateParent(email, password);

		        if (parent == null) {
		            JOptionPane.showMessageDialog(null, "NULL GELİYOR AMK");
		        } else {
		            JOptionPane.showMessageDialog(null, "Giriş başarılı! Hoş geldiniz, " );
		            setVisible(false);
		            dispose();
		            new ParentMainFrame(parent).setVisible(true);
		        }
		    }
		});

		btnLogin.setBounds(60, 113, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 42, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Şifre:");
		lblPassword.setBounds(10, 69, 46, 14);
		contentPane.add(lblPassword);
		
		tbEmail = new JTextField();
		tbEmail.setBounds(63, 39, 86, 20);
		contentPane.add(tbEmail);
		tbEmail.setColumns(10);
		
		tbPassword = new JPasswordField();
		tbPassword.setBounds(66, 69, 83, 20);
		contentPane.add(tbPassword);
	}
}
