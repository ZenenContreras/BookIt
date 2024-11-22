package views;

import javax.swing.*;
import java.awt.*;

public class BasePanel extends JFrame {

    public BasePanel(String title) {
        setTitle("BookIt - " + title);
        setSize(400, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header con el título de la aplicación
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 0, 0));
        headerPanel.setPreferredSize(new Dimension(400, 50));
        JLabel headerLabel = new JLabel("BookIt");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Contenedor central (para el contenido del panel)
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        add(contentPanel, BorderLayout.CENTER);

        // Botón de "Regresar" en el pie de página
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setPreferredSize(new Dimension(400, 70));
        JButton backButton = UIUtils.createStyledButton("Regresar");
        backButton.addActionListener(e -> {
            new AdminPanel(); // Cambiar según el panel al que se debe regresar
            dispose();
        });
        footerPanel.add(backButton);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
