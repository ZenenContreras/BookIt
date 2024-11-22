package views;

import controllers.UsuarioController;

import javax.swing.*;
import java.awt.*;

public class RegistroFrame extends JFrame {
    private UsuarioController usuarioController;

    public RegistroFrame() {
        this.usuarioController = new UsuarioController();

        setTitle("BookIt - Sign Up");
        setSize(400, 600);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblIcon = new JLabel(new ImageIcon("icons/bookit.png"));
        lblIcon.setBounds(160, 10, 80, 80);
        add(lblIcon);

        JLabel lblTitulo = new JLabel("Sign Up");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(50, 50, 50));
        lblTitulo.setBounds(140, 90, 120, 40);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblNombre = new JLabel("Name");
        lblNombre.setBounds(50, 140, 300, 20);
        lblNombre.setForeground(new Color(80, 80, 80));
        add(lblNombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(50, 160, 300, 40);
        txtNombre.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(txtNombre);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(50, 210, 300, 20);
        lblEmail.setForeground(new Color(80, 80, 80));
        add(lblEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(50, 230, 300, 40);
        txtEmail.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(txtEmail);

        JLabel lblContraseña = new JLabel("Password");
        lblContraseña.setBounds(50, 280, 300, 20);
        lblContraseña.setForeground(new Color(80, 80, 80));
        add(lblContraseña);

        JPasswordField txtContraseña = new JPasswordField();
        txtContraseña.setBounds(50, 300, 300, 40);
        txtContraseña.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(txtContraseña);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setBounds(50, 370, 300, 40);
        btnSignUp.setBackground(new Color(0, 0, 0));
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setFocusPainted(false);
        add(btnSignUp);

        JLabel lblLogin = new JLabel("Already have an account? Login");
        lblLogin.setBounds(50, 430, 300, 20);
        lblLogin.setForeground(new Color(0, 120, 255));
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lblLogin);

        btnSignUp.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String correo = txtEmail.getText();
            String contraseña = new String(txtContraseña.getPassword());

            if (nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                usuarioController.registrarUsuario(nombre, correo, contraseña);
                JOptionPane.showMessageDialog(this, "Account created successfully!");
                new LoginPanel();
                dispose();
            }
        });


        lblLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new LoginPanel();
                dispose();
            }
        });

        setVisible(true);
    }
}
