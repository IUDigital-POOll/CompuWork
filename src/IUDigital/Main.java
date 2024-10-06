import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static Departamento departamento;
    private static List<ReporteDesempenio> reportes = new ArrayList<>();

    public static void main(String[] args) {
        departamento = new Departamento("D1", "Recursos Humanos", 10);

        JFrame frame = new JFrame("Gestión de Empleados y Departamentos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

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

        JButton botonGenerarReporte = new JButton("Generar Reporte");
        botonGenerarReporte.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(botonGenerarReporte, gbc);

        JButton botonEliminarEmpleado = new JButton("Eliminar Empleado");
        botonEliminarEmpleado.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(botonEliminarEmpleado, gbc);

        JButton botonActualizarEmpleado = new JButton("Actualizar Empleado");
        botonActualizarEmpleado.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(botonActualizarEmpleado, gbc);

        JTextArea areaTexto = new JTextArea(10, 30);
        areaTexto.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        gbc.gridx = 0;
        gbc.gridy = 5;
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

        if (tipo.equalsIgnoreCase("Permanente")) {
            double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario del empleado:"));
            EmpleadoPermanente nuevoEmpleado = new EmpleadoPermanente(id, nombre, apellido, salario);
            try {
                departamento.agregarEmpleado(nuevoEmpleado);
                JOptionPane.showMessageDialog(null, "Empleado agregado con éxito.");
            } catch (GestionException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (tipo.equalsIgnoreCase("Temporal")) {
            int duracionContrato = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la duración del contrato en meses:"));
            EmpleadoTemporal nuevoEmpleado = new EmpleadoTemporal(id, nombre, apellido, duracionContrato);
            try {
                departamento.agregarEmpleado(nuevoEmpleado);
                JOptionPane.showMessageDialog(null, "Empleado agregado con éxito.");
            } catch (GestionException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void generarReporte(JTextArea areaTexto) {
        areaTexto.setText(departamento.toString());
    }

    public static void eliminarEmpleado() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del empleado que desea eliminar:");
        Empleado empleado = departamento.buscarEmpleadoPorId(id);
        if (empleado != null) {
            try {
                departamento.eliminarEmpleado(empleado);
                JOptionPane.showMessageDialog(null, "Empleado eliminado con éxito.");
            } catch (GestionException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
        }
    }

    public static void actualizarEmpleado() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del empleado que desea actualizar:");
        String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
        String nuevoApellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido:");

        try {
            departamento.actualizarEmpleado(id, nuevoNombre, nuevoApellido);
            JOptionPane.showMessageDialog(null, "Empleado actualizado con éxito.");
        } catch (GestionException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
