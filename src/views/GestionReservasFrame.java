package views;

import controllers.ReservaController;
import models.Reserva;
import models.UsuarioSesion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GestionReservasFrame extends BasePanel {
    private final ReservaController reservaController;
    private final DefaultTableModel tableModel;

    public GestionReservasFrame(JFrame previousPanel) {
        super("Gestionar Reservas", previousPanel);
        this.reservaController = new ReservaController();

        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Gestionar Reservas");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Tabla
        String[] columnNames = {"ID", "Evento", "Cantidad", "Estado"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable tableReservas = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableReservas);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        cargarReservas();

        // Botón de actualizar
        JButton btnActualizar = UIUtils.createStyledButton("Actualizar");
        btnActualizar.addActionListener(e -> cargarReservas());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnActualizar);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Botones de Aceptar/Rechazar
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton btnAceptar = UIUtils.createStyledButton("Aceptar");
        JButton btnRechazar = UIUtils.createStyledButton("Rechazar");

        btnAceptar.addActionListener(e -> {
            int selectedRow = tableReservas.getSelectedRow();
            if (selectedRow != -1) {
                int reservaId = (int) tableModel.getValueAt(selectedRow, 0); // Obtenemos el ID de la reserva
                Reserva reserva = reservaController.obtenerReservaPorId(reservaId);  // Obtener la reserva
                if (reserva != null) {
                    reserva.setEstado(2);  // Estado aceptado
                    reservaController.actualizarReserva(reserva);  // Actualizar la reserva
                    reservaController.registrarVentaDesdeReserva(reserva);  // Registrar la venta
                    cargarReservas();  // Recargar la tabla
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la reserva", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una reserva primero", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        btnAceptar.addActionListener(e -> {
            // Obtiene la fila seleccionada de la tabla
            int selectedRow = tableReservas.getSelectedRow();
            if (selectedRow != -1) {
                int reservaId = (int) tableModel.getValueAt(selectedRow, 0); // ID de la reserva
                Reserva reserva = reservaController.obtenerReservaPorId(reservaId);

                // Cambiar el estado de la reserva a "aceptada" (2)
                reserva.setEstado(2); // Estado aceptado
                reservaController.actualizarReserva(reserva);

                // Registrar la venta a partir de la reserva aceptada
                reservaController.registrarVentaDesdeReserva(reserva);

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "Venta aceptada y registrada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Recargar la lista de reservas en la tabla
                cargarReservas();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una reserva para aceptar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        botonesPanel.add(btnAceptar);
        botonesPanel.add(btnRechazar);
        contentPanel.add(botonesPanel, BorderLayout.NORTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void cargarReservas() {
        tableModel.setRowCount(0);  // Limpiar tabla
        List<Reserva> reservas = reservaController.obtenerTodasLasReservas();  // Obtener todas las reservas
        for (Reserva reserva : reservas) {
            String estadoTexto;
            switch (reserva.getEstado()) {
                case 1 -> estadoTexto = "Pendiente";  // Estado pendiente
                case 2 -> estadoTexto = "Aceptada";  // Estado aceptada
                case 3 -> estadoTexto = "Rechazada";  // Estado rechazada
                default -> estadoTexto = "Desconocido";  // Si hay algún error con el estado
            }

            // Añadir reserva a la tabla
            tableModel.addRow(new Object[]{
                    reserva.getId(),
                    reserva.getEventoId(),
                    reserva.getCantidad(),
                    estadoTexto
            });
        }
    }
}
