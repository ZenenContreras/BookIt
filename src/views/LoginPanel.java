package views;

import controllers.UsuarioController;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JFrame {
    private UsuarioController usuarioController;

    public LoginPanel() {
        this.usuarioController = new UsuarioController();

        setTitle("BookIt - Login");
        setSize(400, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblIcon = new JLabel(new ImageIcon("icons/bookit.png"));
        lblIcon.setBounds(160, 20, 80, 80);
        add(lblIcon);

        JLabel lblTitulo = new JLabel("BookIt");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(50, 50, 50));
        lblTitulo.setBounds(140, 100, 120, 40);
        add(lblTitulo);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(50, 150, 300, 20);
        lblEmail.setForeground(new Color(80, 80, 80));
        add(lblEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(50, 170, 300, 40);
        txtEmail.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(txtEmail);

        JLabel lblContraseña = new JLabel("Password");
        lblContraseña.setBounds(50, 230, 300, 20);
        lblContraseña.setForeground(new Color(80, 80, 80));
        add(lblContraseña);

        JPasswordField txtContraseña = new JPasswordField();
        txtContraseña.setBounds(50, 250, 300, 40);
        txtContraseña.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(txtContraseña);

        JButton btnLogin = new JButton("Sign In");
        btnLogin.setBounds(50, 320, 300, 40);
        btnLogin.setBackground(new Color(0, 0, 0));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        add(btnLogin);

        JLabel lblRegistro = new JLabel("Don't have an account? Sign Up");
        lblRegistro.setBounds(50, 380, 300, 20);
        lblRegistro.setForeground(new Color(0, 120, 255));
        lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lblRegistro);

        btnLogin.addActionListener(e -> {
            String email = txtEmail.getText();
            String password = new String(txtContraseña.getPassword());

            if (usuarioController.validarCredenciales(email, password)) {
                String tipoUsuario = usuarioController.obtenerTipoUsuario(email);
                if ("Administrador".equals(tipoUsuario)) {
                    new AdminPanel(); // Redirige al panel de administrador
                } else if ("Cliente".equals(tipoUsuario)) {
                    new ClientePanel(); // Redirige al panel de cliente
                } else {
                    JOptionPane.showMessageDialog(this, "Tipo de usuario no reconocido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dispose(); // Cierra el login después de iniciar sesión
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });



        lblRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new RegistroFrame();
                dispose();
            }
        });

        setVisible(true);
    }
}
