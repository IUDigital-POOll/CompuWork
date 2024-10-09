import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String id;
    private String nombre;
    private int capacidadMaxima;
    private List<Empleado> empleados;

    public Departamento(String id, String nombre, int capacidadMaxima) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.empleados = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public void agregarEmpleado(Empleado empleado) throws GestionException {
        if (empleados.size() >= capacidadMaxima) {
            throw new GestionException("Capacidad máxima alcanzada. No se puede agregar más empleados.");
        }
        empleados.add(empleado);
    }

    public void eliminarEmpleado(String id) throws GestionException {
        boolean encontrado = false;
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {
                empleados.remove(empleado);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new GestionException("Empleado con ID " + id + " no encontrado.");
        }
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }
}
