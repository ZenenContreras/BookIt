package views;

import controllers.ReservaController;
import models.Reserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GestionReservasFrame extends BasePanel {
    private final JTable tableReservas;
    private final DefaultTableModel tableModel;
    private final ReservaController reservaController;

    public GestionReservasFrame() {
        super("BookIt - Gestión de Reservas");
        reservaController = new ReservaController();

        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Gestión de Reservas");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Tabla de reservas
        String[] columnNames = {"ID", "Evento", "Usuario", "Cantidad", "Estado"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableReservas = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableReservas);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnAceptar = UIUtils.createStyledButton("Aceptar");
        btnAceptar.addActionListener(e -> actualizarEstadoReserva(2));
        buttonPanel.add(btnAceptar);

        JButton btnRechazar = UIUtils.createStyledButton("Rechazar");
        btnRechazar.addActionListener(e -> actualizarEstadoReserva(3));
        buttonPanel.add(btnRechazar);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        actualizarTablaReservas();

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void actualizarTablaReservas() {
        tableModel.setRowCount(0);
        List<Reserva> reservas = reservaController.obtenerReservasPendientes();
        for (Reserva reserva : reservas) {
            tableModel.addRow(new Object[]{
                    reserva.getId(),
                    reserva.getEventoId(),
                    reserva.getUsuarioId(),
                    reserva.getCantidadEntradas(),
                    reserva.getEstadoPagoId() == 1 ? "Pendiente" : (reserva.getEstadoPagoId() == 2 ? "Aceptada" : "Rechazada")
            });
        }
    }

    private void actualizarEstadoReserva(int nuevoEstado) {
        int filaSeleccionada = tableReservas.getSelectedRow();
        if (filaSeleccionada != -1) {
            int reservaId = (int) tableReservas.getValueAt(filaSeleccionada, 0);
            reservaController.actualizarEstadoReserva(reservaId, nuevoEstado);
            actualizarTablaReservas();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una reserva.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
