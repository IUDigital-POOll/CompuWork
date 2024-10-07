import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String id;
    private String nombre;
    private int capacidadMaxima;
    private List<Empleado> empleados;

    // Constructor
    public Departamento(String id, String nombre, int capacidadMaxima) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.empleados = new ArrayList<>();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    // Método para agregar un empleado al departamento
    public void agregarEmpleado(Empleado empleado) throws GestionException {
        if (empleados.size() >= capacidadMaxima) {
            throw new GestionException("Capacidad máxima del departamento alcanzada.");
        }
        empleados.add(empleado);
    }

    // Método para eliminar un empleado por ID
    public void eliminarEmpleadoPorId(String id) throws GestionException {
        Empleado empleado = buscarEmpleadoPorId(id);
        if (empleado != null) {
            empleados.remove(empleado);
        } else {
            throw new GestionException("Empleado con ID " + id + " no encontrado.");
        }
    }

    // Método para buscar un empleado por ID
    public Empleado buscarEmpleadoPorId(String id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {
                return empleado;
            }
        }
        return null;
    }

    // Método para actualizar el nombre y capacidad del departamento
    public void actualizarDepartamento(String nuevoNombre, int nuevaCapacidadMaxima) {
        setNombre(nuevoNombre);
        setCapacidadMaxima(nuevaCapacidadMaxima);
    }
}