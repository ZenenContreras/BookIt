package views;

import controllers.VentaController;
import models.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RevisarVentasFrame extends BasePanel {
    private final VentaController ventaController;
    private final DefaultTableModel tableModel;

    public RevisarVentasFrame(JFrame previousPanel) {
        super("Revisar Ventas", previousPanel);
        this.ventaController = new VentaController();

        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Revisar Ventas");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Cantidad", "Usuario", "Evento", "Fecha"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable tableVentas = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableVentas);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        cargarVentas();

        JButton btnActualizar = UIUtils.createStyledButton("Actualizar");
        btnActualizar.addActionListener(e -> cargarVentas());
        contentPanel.add(btnActualizar, BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void cargarVentas() {
        tableModel.setRowCount(0);
        List<Venta> ventas = ventaController.obtenerTodasLasVentas();
        for (Venta venta : ventas) {
            tableModel.addRow(new Object[]{
                    venta.getId(),
                    venta.getCantidad(),
                    venta.getUsuarioId(),
                    venta.getEventoId(),
                    venta.getFecha()
            });
        }
    }
}
