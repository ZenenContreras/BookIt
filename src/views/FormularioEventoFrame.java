package views;

import controllers.EventoController;
import models.Evento;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class FormularioEventoFrame extends JFrame {
    private final EventoController eventoController;
    private final Evento evento;
    private final GestionEventosFrame parent;

    public FormularioEventoFrame(EventoController eventoController, GestionEventosFrame parent, Evento evento) {
        this.eventoController = eventoController;
        this.parent = parent;
        this.evento = evento;

        setTitle("BookIt - Formulario Evento");
        setSize(500, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Título de la ventana
        JLabel lblTitulo = new JLabel(evento == null ? "Agregar Evento" : "Editar Evento");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(0, 20, getWidth(), 40);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        // Campos de texto
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 80, 400, 20);
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblNombre);

        JTextField txtNombre = new JTextField(evento != null ? evento.getNombre() : "");
        txtNombre.setBounds(50, 110, 400, 30);
        txtNombre.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(txtNombre);

        JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
        lblDisponibilidad.setBounds(50, 160, 400, 20);
        lblDisponibilidad.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblDisponibilidad);

        JTextField txtDisponibilidad = new JTextField(evento != null ? String.valueOf(evento.getDisponibilidad()) : "");
        txtDisponibilidad.setBounds(50, 190, 400, 30);
        txtDisponibilidad.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(txtDisponibilidad);

        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        lblFecha.setBounds(50, 240, 400, 20);
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblFecha);

        JTextField txtFecha = new JTextField(evento != null ? evento.getFecha().toString() : "");
        txtFecha.setBounds(50, 270, 400, 30);
        txtFecha.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(txtFecha);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(50, 320, 400, 20);
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblDescripcion);

        JTextField txtDescripcion = new JTextField(evento != null ? evento.getDescripcion() : "");
        txtDescripcion.setBounds(50, 350, 400, 30);
        txtDescripcion.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(txtDescripcion);

        JLabel lblCapacidad = new JLabel("Capacidad:");
        lblCapacidad.setBounds(50, 400, 400, 20);
        lblCapacidad.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblCapacidad);

        JTextField txtCapacidad = new JTextField(evento != null ? String.valueOf(evento.getCapacidad()) : "");
        txtCapacidad.setBounds(50, 430, 400, 30);
        txtCapacidad.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(txtCapacidad);

        // Botón Guardar
        JButton btnSave = new JButton(evento == null ? "Guardar" : "Actualizar");
        btnSave.setBounds(50, 480, 180, 40);
        btnSave.setBackground(Color.BLACK);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnSave);

        // Botón Cancelar
        JButton btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(270, 480, 180, 40);
        btnCancel.setBackground(Color.GRAY);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnCancel);

        // Listeners
        btnSave.addActionListener(e -> {
            String nombre = txtNombre.getText();
            int disponibilidad = Integer.parseInt(txtDisponibilidad.getText());
            Date fecha = java.sql.Date.valueOf(txtFecha.getText());
            String descripcion = txtDescripcion.getText();
            int capacidad = Integer.parseInt(txtCapacidad.getText());

            if (evento == null) {
                eventoController.agregarEvento(new Evento(0, nombre, disponibilidad, fecha, descripcion, capacidad));
            } else {
                evento.setNombre(nombre);
                evento.setDisponibilidad(disponibilidad);
                evento.setFecha(fecha);
                evento.setDescripcion(descripcion);
                evento.setCapacidad(capacidad);
                eventoController.actualizarEvento(evento);
            }
            parent.actualizarTabla();
            dispose();
        });

        btnCancel.addActionListener(e -> dispose());

        setVisible(true);
    }
}
