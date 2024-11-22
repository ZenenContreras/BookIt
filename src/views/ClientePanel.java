package views;

import controllers.EventoController;

import javax.swing.*;
import java.awt.*;

public class ClientePanel extends BasePanel {
    public ClientePanel(JFrame previousPanel) {
        super("Panel de Cliente", previousPanel);

        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo = new JLabel("Panel de Cliente");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(30, 30, 30));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(lblTitulo);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(400, 1));
        separator.setForeground(new Color(180, 180, 180));
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(separator);

        JLabel descriptionLabel = new JLabel("<html><center>Consulta y realiza reservas.<br>Selecciona una opción para continuar.</center></html>");
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descriptionLabel.setForeground(new Color(100, 100, 100));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(descriptionLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnVerCatalogo = UIUtils.createStyledButton("Ver Catálogo de Eventos");
        btnVerCatalogo.addActionListener(e -> {
            EventoController eventoController = new EventoController();
            new CatalogoEventosFrame(eventoController, this);
            dispose();
        });
        buttonPanel.add(btnVerCatalogo);

        JButton btnCerrarSesion = UIUtils.createStyledButton("Cerrar Sesión");
        btnCerrarSesion.setBackground(new Color(220, 53, 69));
        btnCerrarSesion.addActionListener(e -> {
            new LoginPanel();
            dispose();
        });
        buttonPanel.add(btnCerrarSesion);

        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(buttonPanel);

        setSize(600, 700);
        setLocationRelativeTo(null);
    }
}
