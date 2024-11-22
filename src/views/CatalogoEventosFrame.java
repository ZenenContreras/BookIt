package views;

import controllers.EventoController;
import models.Evento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CatalogoEventosFrame extends BasePanel {
    private final EventoController eventoController;
    private final DefaultTableModel tableModel;
    private final JTable tableEventos;

    public CatalogoEventosFrame(EventoController eventoController) {
        super("BookIt - Catálogo de Eventos");
        this.eventoController = eventoController;

        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Catálogo de Eventos");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Nombre", "Fecha", "Disponibilidad", "Capacidad"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableEventos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableEventos);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnBack = UIUtils.createStyledButton("Regresar");
        btnBack.addActionListener(e -> {
            new ClientePanel();
            dispose();
        });

        JButton btnReservar = UIUtils.createStyledButton("Reservar");
        btnReservar.addActionListener(e -> {
            int filaSeleccionada = tableEventos.getSelectedRow();
            if (filaSeleccionada != -1) {
                Evento eventoSeleccionado = eventoController.obtenerEventos().get(filaSeleccionada);
                new FormularioReservaFrame(eventoSeleccionado);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un evento para reservar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(btnBack);
        buttonPanel.add(btnReservar);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        actualizarTablaEventos();
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void actualizarTablaEventos() {
        tableModel.setRowCount(0);
        List<Evento> eventos = eventoController.obtenerEventos();
        for (Evento evento : eventos) {
            tableModel.addRow(new Object[]{
                    evento.getId(),
                    evento.getNombre(),
                    evento.getFecha(),
                    evento.getDisponibilidad(),
                    evento.getCapacidad()
            });
        }
    }
}
