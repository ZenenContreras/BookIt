package views;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends BasePanel {

    public AdminPanel() {
        super("BookIt - Panel de Administrador");

        // Configuración del contenedor principal
        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Título principal
        JLabel titleLabel = new JLabel("Panel de Administrador");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(new Color(30, 30, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(Box.createVerticalStrut(10)); // Espacio pequeño con el borde superior
        contentPanel.add(titleLabel);

        // Línea divisoria
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(400, 1));
        separator.setForeground(new Color(180, 180, 180));
        contentPanel.add(Box.createVerticalStrut(5)); // Espacio corto entre título y separador
        contentPanel.add(separator);

        // Descripción centrada
        JLabel descriptionLabel = new JLabel("<html><center>Gestiona los módulos del sistema.<br>Selecciona una opción para continuar.</center></html>");
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descriptionLabel.setForeground(new Color(100, 100, 100));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(Box.createVerticalStrut(20)); // Espacio moderado entre separador y descripción
        contentPanel.add(descriptionLabel);

        // Contenedor para botones (centrado vertical y horizontal)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 15)); // 5 filas, 1 columna, espacio entre botones
        buttonPanel.setOpaque(false); // Fondo transparente
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botones
        JButton btnGestionEventos = UIUtils.createStyledButton("Gestión de Eventos");
        btnGestionEventos.addActionListener(e -> {
            new GestionEventosFrame();
            dispose();
        });
        buttonPanel.add(btnGestionEventos);

        JButton btnGestionReservas = UIUtils.createStyledButton("Gestión de Reservas");
        btnGestionReservas.addActionListener(e -> {
            new GestionReservasFrame();
            dispose();
        });
        buttonPanel.add(btnGestionReservas);

        JButton btnRevisarVentas = UIUtils.createStyledButton("Revisar Ventas");
        btnRevisarVentas.addActionListener(e -> {
            new RevisarVentasFrame();
            dispose();
        });
        buttonPanel.add(btnRevisarVentas);

        JButton btnCerrarSesion = UIUtils.createStyledButton("Cerrar Sesión");
        btnCerrarSesion.setBackground(new Color(220, 53, 69)); // Rojo sutil para botón crítico
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.addActionListener(e -> {
            new LoginPanel();
            dispose();
        });
        buttonPanel.add(btnCerrarSesion);

        // Añadir botones al contenedor principal
        contentPanel.add(Box.createVerticalStrut(30)); // Espacio largo entre descripción y botones
        contentPanel.add(buttonPanel);

        // Ajustar tamaño de ventana
        setSize(450, 600); // Ventana más amplia
        setLocationRelativeTo(null); // Centrar ventana en la pantalla
    }
}
