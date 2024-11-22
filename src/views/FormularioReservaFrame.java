package views;

import controllers.ReservaController;
import models.Evento;
import models.Reserva;
import models.UsuarioSesion;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class FormularioReservaFrame extends BasePanel {
    private final ReservaController reservaController;
    private final Evento evento;

    public FormularioReservaFrame(Evento evento) {

        super("BookIt - Formulario de Reserva");
        this.evento = evento;
        this.reservaController = new ReservaController();

        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Formulario de Reserva");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(lblTitulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblCantidad = new JLabel("Cantidad:");
        JTextField txtCantidad = new JTextField();

        JLabel lblMetodoPago = new JLabel("Método de Pago:");
        JComboBox<String> cmbMetodoPago = new JComboBox<>(new String[]{"Tarjeta de Crédito", "PayPal"});

        formPanel.add(lblCantidad);
        formPanel.add(txtCantidad);
        formPanel.add(lblMetodoPago);
        formPanel.add(cmbMetodoPago);

        contentPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));



        JButton btnReservar = UIUtils.createStyledButton("Reservar");
        btnReservar.addActionListener(e -> {
            try {
                int cantidad = Integer.parseInt(txtCantidad.getText());
                String metodoPago = cmbMetodoPago.getSelectedItem().toString();
                int usuarioId = UsuarioSesion.getInstance().getId();
                if (usuarioId == -1) {
                    JOptionPane.showMessageDialog(this, "Usuario no autenticado", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Reserva reserva = new Reserva(
                        0, cantidad, usuarioId, evento.getId(),
                        new Date(System.currentTimeMillis()), 1
                );
                reservaController.realizarReserva(reserva);
                JOptionPane.showMessageDialog(this, "Reserva realizada con éxito");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnCancel = UIUtils.createStyledButton("Cancelar");
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnReservar);
        buttonPanel.add(btnCancel);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(null);
    }
}
