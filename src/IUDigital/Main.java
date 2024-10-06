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
        frame.setSize(600, 400);

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

        JButton botonAgregarEmpleado = new JButton("Agregar Empleado");
        botonAgregarEmpleado.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(botonAgregarEmpleado, gbc);

        JButton botonCrearDepartamento = new JButton("Crear Departamento");
        botonCrearDepartamento.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(botonCrearDepartamento, gbc);

        JButton botonGenerarReporte = new JButton("Generar Reporte");
        botonGenerarReporte.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(botonGenerarReporte, gbc);

        JButton botonEliminarEmpleado = new JButton("Eliminar Empleado");
        botonEliminarEmpleado.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(botonEliminarEmpleado, gbc);

        JButton botonActualizarEmpleado = new JButton("Actualizar Empleado");
        botonActualizarEmpleado.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(botonActualizarEmpleado, gbc);

        JTextArea areaTexto = new JTextArea(10, 30);
        areaTexto.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel.add(scrollPane, gbc);

        frame.add(panel);
        frame.setVisible(true);

        botonAgregarEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarEmpleado();
            }
        });

        botonCrearDepartamento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearDepartamento();
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
        String id = JOptionPane.showInputDialog("Ingrese el ID del empleado:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del empleado:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del empleado:");
        String tipo = JOptionPane.showInputDialog("Ingrese el tipo de empleado (Permanente/Temporal):");

        // Mostrar un combo box para seleccionar un departamento
        String[] departamentoNombres = departamentos.stream().map(Departamento::getNombre).toArray(String[]::new);
        String departamentoSeleccionado = (String) JOptionPane.showInputDialog(null,
                "Seleccione un departamento:",
                "Seleccionar Departamento",
                JOptionPane.PLAIN_MESSAGE,
                null,
                departamentoNombres,
                departamentoNombres[0]);

        StringBuilder detallesEmpleado = new StringBuilder();
        detallesEmpleado.append("ID: ").append(id).append("\n");
        detallesEmpleado.append("Nombre: ").append(nombre).append("\n");
        detallesEmpleado.append("Apellido: ").append(apellido).append("\n");
        detallesEmpleado.append("Tipo: ").append(tipo).append("\n");
        detallesEmpleado.append("Departamento: ").append(departamentoSeleccionado).append("\n");

        if (tipo.equalsIgnoreCase("Permanente")) {
            double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario del empleado:"));
            EmpleadoPermanente nuevoEmpleado = new EmpleadoPermanente(id, nombre, apellido, salario);
            try {
                Departamento departamento = departamentos.stream()
                        .filter(dep -> dep.getNombre().equals(departamentoSeleccionado))
                        .findFirst()
                        .orElse(null);
                if (departamento != null) {
                    departamento.agregarEmpleado(nuevoEmpleado);
                    detallesEmpleado.append("Salario: ").append(salario).append("\n");
                    JOptionPane.showMessageDialog(null, "Empleado agregado con éxito:\n" + detallesEmpleado);
                }
            } catch (GestionException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (tipo.equalsIgnoreCase("Temporal")) {
            int duracionContrato = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la duración del contrato en meses:"));
            EmpleadoTemporal nuevoEmpleado = new EmpleadoTemporal(id, nombre, apellido, duracionContrato);
            try {
                Departamento departamento = departamentos.stream()
                        .filter(dep -> dep.getNombre().equals(departamentoSeleccionado))
                        .findFirst()
                        .orElse(null);
                if (departamento != null) {
                    departamento.agregarEmpleado(nuevoEmpleado);
                    detallesEmpleado.append("Duración del contrato: ").append(duracionContrato).append(" meses\n");
                    JOptionPane.showMessageDialog(null, "Empleado agregado con éxito:\n" + detallesEmpleado);
                }
            } catch (GestionException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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

    public static void generarReporte(JTextArea areaTexto) {
        StringBuilder sb = new StringBuilder();
        for (Departamento d : departamentos) {
            sb.append(d.toString()).append("\n");
        }
        areaTexto.setText(sb.toString());
    }

    public static void eliminarEmpleado() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del empleado que desea eliminar:");
        for (Departamento departamento : departamentos) {
            Empleado empleado = departamento.buscarEmpleadoPorId(id);
            if (empleado != null) {
                try {
                    departamento.eliminarEmpleado(empleado);
                    JOptionPane.showMessageDialog(null, "Empleado eliminado con éxito.");
                    return;
                } catch (GestionException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
    }

    public static void actualizarEmpleado() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del empleado que desea actualizar:");
        String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
        String nuevoApellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido:");

        for (Departamento departamento : departamentos) {
            try {
                departamento.actualizarEmpleado(id, nuevoNombre, nuevoApellido);
                JOptionPane.showMessageDialog(null, "Empleado actualizado con éxito.");
                return;
            } catch (GestionException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
    }
}
