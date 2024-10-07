
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Departamento> departamentos = new ArrayList<>();
    private static List<ReporteDesempenio> reportes = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Empleados y Departamentos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        // Ventana emergente con aviso inicial
        JOptionPane.showMessageDialog(null,
                "Asegúrese de crear el departamento antes de agregar un empleado para poder asociarlo.",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("Seleccione una acción:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label, gbc);

        // Botón para agregar empleados
        JButton botonAgregarEmpleado = new JButton("Agregar Empleado");
        botonAgregarEmpleado.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(botonAgregarEmpleado, gbc);

        // Botón para crear departamentos
        JButton botonCrearDepartamento = new JButton("Crear Departamento");
        botonCrearDepartamento.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(botonCrearDepartamento, gbc);

        // Botón para eliminar departamentos
        JButton botonEliminarDepartamento = new JButton("Eliminar Departamento");
        botonEliminarDepartamento.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(botonEliminarDepartamento, gbc);

        // Botón para actualizar departamentos
        JButton botonActualizarDepartamento = new JButton("Actualizar Departamento");
        botonActualizarDepartamento.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(botonActualizarDepartamento, gbc);

        JButton botonGenerarReporte = new JButton("Generar Reporte");
        botonGenerarReporte.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(botonGenerarReporte, gbc);

        JButton botonEliminarEmpleado = new JButton("Eliminar Empleado");
        botonEliminarEmpleado.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(botonEliminarEmpleado, gbc);

        JButton botonActualizarEmpleado = new JButton("Actualizar Empleado");
        botonActualizarEmpleado.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(botonActualizarEmpleado, gbc);

        JTextArea areaTexto = new JTextArea(10, 30);
        areaTexto.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel.add(scrollPane, gbc);

        frame.add(panel);
        frame.setVisible(true);

        // Lógica para agregar empleados
        botonAgregarEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarEmpleado();
            }
        });

        // Lógica para crear departamentos
        botonCrearDepartamento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearDepartamento();
            }
        });

        // Lógica para eliminar departamentos
        botonEliminarDepartamento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarDepartamento();
            }
        });

        // Lógica para actualizar departamentos
        botonActualizarDepartamento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarDepartamento();
            }
        });

        botonGenerarReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarReporte(areaTexto);
            }
        });

        botonEliminarEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarEmpleado();
            }
        });

        botonActualizarEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarEmpleado();
            }
        });
    }

    public static void agregarEmpleado() {
        // Implementación existente para agregar empleados
    }

    public static void crearDepartamento() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del departamento:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del departamento:");
        int capacidadMaxima = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad máxima de empleados:"));

        Departamento nuevoDepartamento = new Departamento(id, nombre, capacidadMaxima);
        departamentos.add(nuevoDepartamento);

        String detallesDepartamento = String.format("ID: %s\nNombre: %s\nCapacidad máxima: %d",
                id, nombre, capacidadMaxima);

        JOptionPane.showMessageDialog(null, "Departamento creado con éxito:\n" + detallesDepartamento);
    }

    public static void eliminarDepartamento() {
        String[] departamentoNombres = departamentos.stream().map(Departamento::getNombre).toArray(String[]::new);
        String departamentoSeleccionado = (String) JOptionPane.showInputDialog(null,
                "Seleccione un departamento a eliminar:",
                "Eliminar Departamento",
                JOptionPane.PLAIN_MESSAGE,
                null,
                departamentoNombres,
                departamentoNombres[0]);

        if (departamentoSeleccionado != null) {
            departamentos.removeIf(departamento -> departamento.getNombre().equals(departamentoSeleccionado));
            JOptionPane.showMessageDialog(null, "Departamento eliminado con éxito: " + departamentoSeleccionado);
        }
    }

    public static void actualizarDepartamento() {
        String[] departamentoNombres = departamentos.stream().map(Departamento::getNombre).toArray(String[]::new);
        String departamentoSeleccionado = (String) JOptionPane.showInputDialog(null,
                "Seleccione un departamento a actualizar:",
                "Actualizar Departamento",
                JOptionPane.PLAIN_MESSAGE,
                null,
                departamentoNombres,
                departamentoNombres[0]);

        if (departamentoSeleccionado != null) {
            Departamento departamento = departamentos.stream()
                    .filter(dep -> dep.getNombre().equals(departamentoSeleccionado))
                    .findFirst()
                    .orElse(null);

            if (departamento != null) {
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del departamento:", departamento.getNombre());
                int nuevaCapacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva capacidad máxima:", departamento.getCapacidadMaxima()));

                departamento.setNombre(nuevoNombre);
                departamento.setCapacidadMaxima(nuevaCapacidad);

                JOptionPane.showMessageDialog(null, "Departamento actualizado con éxito:\nNombre: " + nuevoNombre + "\nCapacidad: " + nuevaCapacidad);
            }
        }
    }

    public static void generarReporte(JTextArea areaTexto) {
        // Implementación existente para generar reportes
    }

    public static void eliminarEmpleado() {
        // Implementación para eliminar empleados
    }

    public static void actualizarEmpleado() {
        // Implementación para actualizar empleados
    }
}

// Prueba