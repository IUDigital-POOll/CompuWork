import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static Departamento departamento;  // Usaremos un departamento global para gestionar los empleados
    private static List<ReporteDesempenio> reportes = new ArrayList<>();  // Lista para almacenar los reportes de desempeño

    public static void main(String[] args) {
        // Inicializamos el departamento
        departamento = new Departamento("D1", "Recursos Humanos", 10);

        // Crear la ventana
        JFrame frame = new JFrame("Gestión de Empleados y Departamentos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Crear el panel con GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espacio alrededor de los componentes

        // Crear componentes para la ventana
        JLabel label = new JLabel("Seleccione una acción:");
        label.setHorizontalAlignment(SwingConstants.CENTER);  // Centramos el texto
        label.setFont(new Font("Arial", Font.BOLD, 20));  // Aplicamos fuente en negrita y tamaño 20
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // El label ocupa dos columnas
        gbc.anchor = GridBagConstraints.CENTER;  // Centrar el texto
        panel.add(label, gbc);

        // Botón "Agregar Empleado"
        JButton botonAgregarEmpleado = new JButton("Agregar Empleado");
        botonAgregarEmpleado.setFont(new Font("Arial", Font.BOLD, 16));  // Aplicamos fuente en negrita y tamaño 16
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Ocupa una columna
        gbc.anchor = GridBagConstraints.CENTER;  // Centramos el botón
        gbc.fill = GridBagConstraints.NONE;  // Evitamos que el botón ocupe todo el ancho
        panel.add(botonAgregarEmpleado, gbc);

        // Botón "Generar Reporte"
        JButton botonGenerarReporte = new JButton("Generar Reporte");
        botonGenerarReporte.setFont(new Font("Arial", Font.BOLD, 16));  // Aplicamos fuente en negrita y tamaño 16
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(botonGenerarReporte, gbc);

        // Área de texto para mostrar información (observaciones)
        JTextArea areaTexto = new JTextArea(10, 30);  // Área de texto para mostrar información
        areaTexto.setFont(new Font("Arial", Font.PLAIN, 14));  // Fuente para el área de texto
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;  // El área de texto ocupa dos columnas
        gbc.fill = GridBagConstraints.BOTH;  // Ocupa todo el espacio disponible en ambos ejes
        gbc.weightx = 1;
        gbc.weighty = 1;  // Aumentamos el peso vertical para que el área de texto crezca
        panel.add(scrollPane, gbc);

        // Agregar el panel a la ventana
        frame.add(panel);

        // Mostrar la ventana
        frame.setVisible(true);

        // Acción al presionar el botón de agregar empleado
        botonAgregarEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarEmpleado();
            }
        });

        // Acción al presionar el botón de generar reporte
        botonGenerarReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarReporte(areaTexto);
            }
        });
    }

    // Método para agregar empleado
    public static void agregarEmpleado() {
        // Crear ventana de diálogo para ingresar los datos del empleado
        JTextField idField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField apellidoField = new JTextField();
        String[] tipoEmpleado = {"Permanente", "Temporal"};
        JComboBox<String> tipoEmpleadoBox = new JComboBox<>(tipoEmpleado);

        // Crear formulario para el diálogo
        Object[] inputFields = {
                "ID:", idField,
                "Nombre:", nombreField,
                "Apellido:", apellidoField,
                "Tipo de Empleado:", tipoEmpleadoBox
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Agregar Empleado", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            String tipo = (String) tipoEmpleadoBox.getSelectedItem();

            Empleado empleado;
            if (tipo.equals("Permanente")) {
                empleado = new EmpleadoPermanente(id, nombre, apellido, 50000);  // Salario fijo para ejemplo
            } else {
                empleado = new EmpleadoTemporal(id, nombre, apellido, 12);  // Contrato de 12 meses para ejemplo
            }

            // Intentar agregar el empleado al departamento
            try {
                departamento.agregarEmpleado(empleado);
                JOptionPane.showMessageDialog(null, "Empleado agregado exitosamente.");
            } catch (GestionException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para generar reporte
    public static void generarReporte(JTextArea areaTexto) {
        // Seleccionar un empleado para generar el reporte
        List<Empleado> empleados = departamento.getEmpleados();
        if (empleados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay empleados en el departamento.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] empleadosArray = empleados.stream().map(Empleado::getNombre).toArray(String[]::new);
        JComboBox<String> empleadoBox = new JComboBox<>(empleadosArray);

        JTextField evaluacionField = new JTextField();
        JTextField comentariosField = new JTextField();

        // Crear formulario para el diálogo
        Object[] inputFields = {
                "Seleccione Empleado:", empleadoBox,
                "Evaluación:", evaluacionField,
                "Comentarios:", comentariosField
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Generar Reporte", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            Empleado empleadoSeleccionado = empleados.get(empleadoBox.getSelectedIndex());
            String evaluacion = evaluacionField.getText();
            String comentarios = comentariosField.getText();

            // Crear el reporte de desempeño
            ReporteDesempenio reporte = new ReporteDesempenio(empleadoSeleccionado, evaluacion, comentarios);
            reportes.add(reporte);

            // Mostrar el reporte en el área de texto
            areaTexto.append(reporte.toString() + "\n");
        }
    }
}
