import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String id;
    private String nombre;
    private List<Empleado> empleados;
    private int capacidadMaxima; // Nuevo atributo para capacidad

    public Departamento(String id, String nombre, int capacidadMaxima) {
        this.id = id;
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
        this.capacidadMaxima = capacidadMaxima;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    // Método para agregar un empleado con gestión de excepciones
    public void agregarEmpleado(Empleado empleado) throws GestionException {
        if (empleados.size() >= capacidadMaxima) {
            throw new GestionException("El departamento ha alcanzado su capacidad máxima.");
        }
        empleados.add(empleado);
        empleado.setDepartamentoId(this.id);
    }

    // Método para eliminar un empleado con gestión de excepciones
    public void eliminarEmpleado(Empleado empleado) throws GestionException {
        if (!empleados.contains(empleado)) {
            throw new GestionException("El empleado no existe en este departamento.");
        }
        empleados.remove(empleado);
        empleado.setDepartamentoId(null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Departamento ID: ").append(id)
                .append("\nNombre: ").append(nombre)
                .append("\nEmpleados:\n");

        for (Empleado empleado : empleados) {
            sb.append(empleado).append("\n");
        }
        return sb.toString();
    }

}


