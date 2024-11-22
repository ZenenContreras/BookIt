package views;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends BasePanel {

    public AdminPanel(JFrame previousPanel) {
        super("Panel de Administrador", previousPanel);

        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Panel de Administrador");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(new Color(30, 30, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(titleLabel);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(400, 1));
        separator.setForeground(new Color(180, 180, 180));
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(separator);

        JLabel descriptionLabel = new JLabel("<html><center>Gestiona los módulos del sistema.<br>Selecciona una opción para continuar.</center></html>");
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descriptionLabel.setForeground(new Color(100, 100, 100));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(descriptionLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnGestionEventos = UIUtils.createStyledButton("Gestión de Eventos");
        btnGestionEventos.addActionListener(e -> {
            new GestionEventosFrame(this); // Pasar AdminPanel como referencia
            dispose();
        });
        buttonPanel.add(btnGestionEventos);

        JButton btnGestionReservas = UIUtils.createStyledButton("Gestión de Reservas");
        btnGestionReservas.addActionListener(e -> {
            new GestionReservasFrame(this); // Pasar AdminPanel como referencia
            dispose();
        });
        buttonPanel.add(btnGestionReservas);

        JButton btnRevisarVentas = UIUtils.createStyledButton("Revisar Ventas");
        btnRevisarVentas.addActionListener(e -> {
            new RevisarVentasFrame(this); // Pasar AdminPanel como referencia
            dispose();
        });
        buttonPanel.add(btnRevisarVentas);


        JButton btnCerrarSesion = UIUtils.createStyledButton("Cerrar Sesión");
        btnCerrarSesion.setBackground(new Color(220, 53, 69));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.addActionListener(e -> {
            new LoginPanel();
            dispose();
        });
        buttonPanel.add(btnCerrarSesion);

        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(buttonPanel);

        setSize(450, 600);
        setLocationRelativeTo(null);
    }
}
