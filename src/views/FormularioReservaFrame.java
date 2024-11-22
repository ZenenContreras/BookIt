package views;

import controllers.ReservaController;
import models.Evento;
import models.Reserva;
import models.UsuarioSesion;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class FormularioReservaFrame extends BasePanel {
    private final Evento evento;
    private final ReservaController reservaController;

    public FormularioReservaFrame(Evento evento, JFrame previousPanel) {
        super("Formulario de Reserva", previousPanel);
        this.evento = evento;
        this.reservaController = new ReservaController();

        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("Formulario de Reserva");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblEvento = new JLabel("Evento:");
        JLabel lblEventoNombre = new JLabel(evento.getNombre());
        JLabel lblCantidad = new JLabel("Cantidad de Entradas:");
        JTextField txtCantidad = new JTextField();

        formPanel.add(lblEvento);
        formPanel.add(lblEventoNombre);
        formPanel.add(lblCantidad);
        formPanel.add(txtCantidad);

        contentPanel.add(formPanel, BorderLayout.CENTER);

        // Botones
        JButton btnReservar = UIUtils.createStyledButton("Reservar");
        btnReservar.addActionListener(e -> {
            try {
                String cantidadTexto = txtCantidad.getText().trim();
                System.out.println("Texto recuperado: " + cantidadTexto); // Debug

                // Verifica si el campo está vacío
                if (cantidadTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El campo está vacío. Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verifica si el texto ingresado es un número válido
                if (!cantidadTexto.matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Convierte a número entero
                int cantidad = Integer.parseInt(cantidadTexto);

                // Verifica si es un número positivo
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Usuario autenticado
                int usuarioId = UsuarioSesion.getInstance().getId();
                if (usuarioId == -1) {
                    JOptionPane.showMessageDialog(this, "Usuario no autenticado. Inicie sesión nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Crear y registrar la reserva con estado inicial numérico (1 para Pendiente)
                Reserva reserva = new Reserva(
                        0,
                        cantidad,
                        usuarioId,
                        evento.getId(),
                        new Date(System.currentTimeMillis()),
                        2 // Estado inicial como número (1 para "Pendiente")
                );

                reservaController.realizarReserva(reserva);
                JOptionPane.showMessageDialog(this, "Reserva realizada con éxito. Estado: Pendiente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        JButton btnCancelar = UIUtils.createStyledButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(btnReservar);
        buttonPanel.add(btnCancelar);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
    }
}
