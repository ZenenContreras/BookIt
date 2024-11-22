package views;

import controllers.EventoController;

import javax.swing.*;
import java.awt.*;

public class ClientePanel extends BasePanel {
    public ClientePanel() {
        super("BookIt - Cliente");

        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("Catálogo de Eventos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(lblTitulo);

        // Botón Catálogo de Eventos
        JButton btnVerCatalogo = UIUtils.createStyledButton("Ver Catálogo de Eventos");
        btnVerCatalogo.addActionListener(e -> {
            EventoController eventoController = new EventoController();
            new CatalogoEventosFrame(eventoController);
            dispose();
        });
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(btnVerCatalogo);

        // Botón Cerrar Sesión
        JButton btnCerrarSesion = UIUtils.createStyledButton("Cerrar Sesión");
        btnCerrarSesion.setBackground(Color.RED);
        btnCerrarSesion.addActionListener(e -> {
            new LoginPanel();
            dispose();
        });
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(btnCerrarSesion);

        setSize(600, 700);
        setLocationRelativeTo(null);
    }
}
