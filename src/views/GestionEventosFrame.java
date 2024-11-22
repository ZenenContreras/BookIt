package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controllers.EventoController;
import models.Evento;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class GestionEventosFrame extends BasePanel {
    private final EventoController eventoController;
    private final DefaultTableModel tableModel;
    private final JTable table;

    public GestionEventosFrame() {
        super("BookIt - Gestión de Eventos");
        eventoController = new EventoController();

        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Gestión de Eventos");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Tabla de eventos
        String[] columnNames = {"ID", "Nombre", "Disponibilidad", "Fecha", "Descripción", "Capacidad"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnAdd = UIUtils.createStyledButton("Añadir");
        btnAdd.addActionListener(e -> abrirFormularioEvento(null));
        buttonPanel.add(btnAdd);

        JButton btnEdit = UIUtils.createStyledButton("Modificar");
        btnEdit.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int eventoId = (int) tableModel.getValueAt(selectedRow, 0);
                String nombre = (String) tableModel.getValueAt(selectedRow, 1);
                int disponibilidad = (int) tableModel.getValueAt(selectedRow, 2);
                Date fecha = (Date) tableModel.getValueAt(selectedRow, 3);
                String descripcion = (String) tableModel.getValueAt(selectedRow, 4);
                int capacidad = (int) tableModel.getValueAt(selectedRow, 5);

                Evento evento = new Evento(eventoId, nombre, disponibilidad, fecha, descripcion, capacidad);
                abrirFormularioEvento(evento);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un evento para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });
        buttonPanel.add(btnEdit);

        JButton btnDelete = UIUtils.createStyledButton("Eliminar");
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int eventoId = (int) tableModel.getValueAt(selectedRow, 0);
                eventoController.eliminarEvento(eventoId);
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un evento para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });
        buttonPanel.add(btnDelete);


        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        actualizarTabla();
        setSize(600, 500);
        setLocationRelativeTo(null);
    }

    public void actualizarTabla() {
        tableModel.setRowCount(0);
        List<Evento> eventos = eventoController.obtenerEventos();
        for (Evento evento : eventos) {
            tableModel.addRow(new Object[]{
                    evento.getId(),
                    evento.getNombre(),
                    evento.getDisponibilidad(),
                    evento.getFecha(),
                    evento.getDescripcion(),
                    evento.getCapacidad()
            });
        }
    }

    private void abrirFormularioEvento(Evento evento) {
        new FormularioEventoFrame(eventoController, this, evento);
    }

}
