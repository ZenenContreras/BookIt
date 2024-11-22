package views;

import javax.swing.*;
import java.awt.*;

public class RevisarVentasFrame extends BasePanel {

    public RevisarVentasFrame() {
        super("BookIt - Revisar Ventas");

        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Revisar Ventas");
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

        JLabel descriptionLabel = new JLabel("<html><center>Consulta el informe detallado de ventas.<br>Selecciona una opción para continuar.</center></html>");
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

        JButton btnViewSales = UIUtils.createStyledButton("Ver Ventas");
        btnViewSales.addActionListener(e -> {
            // Lógica para ver ventas
        });
        buttonPanel.add(btnViewSales);


        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(buttonPanel);

        setSize(450, 600);
        setLocationRelativeTo(null);
    }
}
