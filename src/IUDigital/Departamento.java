import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String id;
    private String nombre;
    private int capacidadMaxima;
    private List<Empleado> empleados; // Lista de empleados en el departamento

    public Departamento(String id, String nombre, int capacidadMaxima) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.empleados = new ArrayList<>(); // Inicializar la lista de empleados
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

    public List<Empleado> getEmpleados() { // Método que retorna la lista de empleados
        return empleados;
    }

    public void agregarEmpleado(Empleado empleado) throws GestionException {
        if (empleados.size() < capacidadMaxima) {
            empleados.add(empleado);
        } else {
            throw new GestionException("Capacidad máxima alcanzada en el departamento " + nombre);
        }
    }

    public Empleado buscarEmpleadoPorId(String id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {
                return empleado;
            }
        }
        return null; // Retornar null si no se encuentra el empleado
    }
}