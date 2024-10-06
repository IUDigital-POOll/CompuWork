import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String id;
    private String nombre;
    private int capacidadMaxima;
    private List<Empleado> empleados = new ArrayList<>();

    public Departamento(String id, String nombre, int capacidadMaxima) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
    }

    public void agregarEmpleado(Empleado empleado) throws GestionException {
        if (empleados.size() >= capacidadMaxima) {
            throw new GestionException("No se puede agregar más empleados. Capacidad máxima alcanzada.");
        }
        empleados.add(empleado);
    }

    public String getNombre() {
        return nombre;
    }

    public Empleado buscarEmpleadoPorId(String id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {
                return empleado;
            }
        }
        return null;
    }

    public void eliminarEmpleado(Empleado empleado) throws GestionException {
        if (!empleados.remove(empleado)) {
            throw new GestionException("Empleado no encontrado en el departamento.");
        }
    }

    public void actualizarEmpleado(String id, String nuevoNombre, String nuevoApellido) throws GestionException {
        Empleado empleado = buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new GestionException("Empleado no encontrado.");
        }
        empleado.setNombre(nuevoNombre);
        empleado.setApellido(nuevoApellido);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Departamento: %s\n", nombre));
        sb.append(String.format("Capacidad máxima: %d\n", capacidadMaxima));
        sb.append(String.format("Empleados actuales: %d\n", empleados.size()));
        sb.append("Lista de Empleados:\n");
        for (Empleado empleado : empleados) {
            sb.append(empleado.toString());
        }
        return sb.toString();
    }
}
