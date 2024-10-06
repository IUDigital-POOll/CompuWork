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

        // Crear un JComboBox para seleccionar el tipo de empleado
        String[] tiposEmpleado = {"Permanente", "Temporal"};
        JComboBox<String> comboBoxTipo = new JComboBox<>(tiposEmpleado);
        int resultado = JOptionPane.showConfirmDialog(null, comboBoxTipo, "Seleccione el tipo de empleado", JOptionPane.OK_CANCEL_OPTION);
        if (resultado != JOptionPane.OK_OPTION) {
            return; // Salir si se canceló
        }
        String tipo = (String) comboBoxTipo.getSelectedItem();

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
        // Obtener la lista de IDs de empleados existentes
        List<String> idsEmpleados = new ArrayList<>();
        for (Departamento d : departamentos) {
            for (Empleado e : d.getEmpleados()) {
                idsEmpleados.add(e.getId());
            }
        }

        // Crear un JComboBox para seleccionar el empleado
        JComboBox<String> comboBoxEmpleados = new JComboBox<>(idsEmpleados.toArray(new String[0]));
        int resultado = JOptionPane.showConfirmDialog(null, comboBoxEmpleados, "Seleccione un empleado", JOptionPane.OK_CANCEL_OPTION);
        if (resultado != JOptionPane.OK_OPTION) {
            return; // Salir si se canceló
        }

        String idSeleccionado = (String) comboBoxEmpleados.getSelectedItem();
        StringBuilder sb = new StringBuilder();
        boolean encontrado = false;

        for (Departamento departamento : departamentos) {
            Empleado empleado = departamento.buscarEmpleadoPorId(idSeleccionado);
            if (empleado != null) {
                sb.append("Empleado encontrado:\n");
                sb.append("ID: ").append(empleado.getId()).append("\n");
                sb.append("Nombre: ").append(empleado.getNombre()).append("\n");
                sb.append("Apellido: ").append(empleado.getApellido()).append("\n");
                if (empleado instanceof EmpleadoPermanente) {
                    sb.append("Tipo: Permanente\n");
                    sb.append("Salario: ").append(((EmpleadoPermanente) empleado).getSalario()).append("\n");
                } else if (empleado instanceof EmpleadoTemporal) {
                    sb.append("Tipo: Temporal\n");
                    sb.append("Duración del contrato: ").append(((EmpleadoTemporal) empleado).getDuracionContrato()).append(" meses\n");
                }
                sb.append("Departamento: ").append(departamento.getNombre()).append("\n");
                encontrado = true;
                break; // Salir del bucle si se encontró el empleado
            }
        }

        if (!encontrado) {
            sb.append("Empleado no encontrado.\n");
        }

        // Mostrar el reporte en el área de texto
        areaTexto.setText(sb.toString());
    }

    public static void eliminarEmpleado() {
        // Implementar la funcionalidad para eliminar un empleado
    }

    public static void actualizarEmpleado() {
        // Implementar la funcionalidad para actualizar un empleado
    }
}
