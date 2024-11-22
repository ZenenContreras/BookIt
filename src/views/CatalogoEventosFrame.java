package views;

import controllers.EventoController;
import models.Evento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogoEventosFrame extends BasePanel {
    private final EventoController eventoController;
    private final DefaultTableModel tableModel;
    private final JTable tableEventos;
    private final JComboBox<String> cmbFecha;
    private final JComboBox<String> cmbTipoEvento;
    private final JComboBox<String> cmbDisponibilidad;

    public CatalogoEventosFrame(EventoController eventoController, JFrame previousPanel) {
        super("Catálogo de Eventos", previousPanel);
        this.eventoController = eventoController;

        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("Catálogo de Eventos");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de filtros
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(1, 4, 10, 10));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ComboBox para filtros
        cmbFecha = new JComboBox<>(getFechasDisponibles());
        filterPanel.add(createLabeledPanel("Fecha:", cmbFecha));

        cmbTipoEvento = new JComboBox<>(getTipoEventos());
        filterPanel.add(createLabeledPanel("Tipo de Evento:", cmbTipoEvento));

        cmbDisponibilidad = new JComboBox<>(getDisponibilidades());
        filterPanel.add(createLabeledPanel("Disponibilidad:", cmbDisponibilidad));

        JButton btnFiltrar = UIUtils.createStyledButton("Filtrar");
        btnFiltrar.addActionListener(e -> filtrarEventos());
        filterPanel.add(btnFiltrar);

        contentPanel.add(filterPanel, BorderLayout.NORTH);

        // Tabla de eventos
        String[] columnNames = {"ID", "Nombre", "Fecha", "Disponibilidad", "Capacidad", "Tipo"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableEventos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableEventos);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel para el botón de reservar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton btnReservar = UIUtils.createStyledButton("Reservar");
        btnReservar.addActionListener(e -> reservarEvento());
        buttonPanel.add(btnReservar);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        actualizarTablaEventos(eventoController.obtenerEventos());
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void actualizarTablaEventos(List<Evento> eventos) {
        tableModel.setRowCount(0); // Limpiar la tabla
        for (Evento evento : eventos) {
            tableModel.addRow(new Object[]{
                    evento.getId(),
                    evento.getNombre(),
                    evento.getFecha(),
                    evento.getDisponibilidad(),
                    evento.getCapacidad(),
                    evento.getDescripcion()
            });
        }
    }

    private void filtrarEventos() {
        String fechaSeleccionada = (String) cmbFecha.getSelectedItem();
        String tipoSeleccionado = (String) cmbTipoEvento.getSelectedItem();
        String disponibilidadSeleccionada = (String) cmbDisponibilidad.getSelectedItem();

        // Obtener todos los eventos y aplicar filtros
        List<Evento> eventosFiltrados = eventoController.obtenerEventos().stream()
                .filter(e -> (fechaSeleccionada.equals("Todas") || e.getFecha().toString().equals(fechaSeleccionada)))
                .filter(e -> (tipoSeleccionado.equals("Todos") || e.getDescripcion().equals(tipoSeleccionado)))
                .filter(e -> (disponibilidadSeleccionada.equals("Todas") || e.getDisponibilidad() >= Integer.parseInt(disponibilidadSeleccionada)))
                .collect(Collectors.toList());

        actualizarTablaEventos(eventosFiltrados);
    }

    private void reservarEvento() {
        int selectedRow = tableEventos.getSelectedRow();
        if (selectedRow != -1) {
            // Obtener el evento seleccionado de la tabla
            int eventoId = (int) tableModel.getValueAt(selectedRow, 0);
            Evento eventoSeleccionado = eventoController.obtenerEventos()
                    .stream()
                    .filter(e -> e.getId() == eventoId)
                    .findFirst()
                    .orElse(null);

            if (eventoSeleccionado != null) {
                new FormularioReservaFrame(eventoSeleccionado, this); // Pasar el evento y el panel actual
            } else {
                JOptionPane.showMessageDialog(this, "Evento no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un evento para reservar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private String[] getFechasDisponibles() {
        List<Evento> eventos = eventoController.obtenerEventos();
        return eventos.stream()
                .map(e -> e.getFecha().toString())
                .distinct()
                .sorted()
                .toArray(String[]::new);
    }

    private String[] getTipoEventos() {
        List<Evento> eventos = eventoController.obtenerEventos();
        return eventos.stream()
                .map(Evento::getDescripcion)
                .distinct()
                .sorted()
                .toArray(String[]::new);
    }

    private String[] getDisponibilidades() {
        return new String[]{"Todas", "10", "20", "50", "100"}; // Ejemplo de disponibilidades mínimas
    }

    private JPanel createLabeledPanel(String labelText, JComboBox<String> comboBox) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.NORTH);
        panel.add(comboBox, BorderLayout.CENTER);
        return panel;
    }
}
