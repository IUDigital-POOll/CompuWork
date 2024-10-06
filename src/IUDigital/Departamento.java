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

    public void agregarEmpleado(Empleado empleado) throws GestionException {
        // Imprimir la cantidad de empleados actuales y la capacidad máxima
        System.out.println("Empleados actuales: " + empleados.size() + "/" + capacidadMaxima);

        if (empleados.size() >= capacidadMaxima) {
            throw new GestionException("No se puede agregar más empleados. Capacidad máxima alcanzada.");
        }
        empleados.add(empleado);
    }

    public void eliminarEmpleado(Empleado empleado) throws GestionException {
        if (empleados.contains(empleado)) {
            empleados.remove(empleado);
        } else {
            throw new GestionException("El empleado no se encuentra en el departamento.");
        }
    }

    public void actualizarEmpleado(String id, String nuevoNombre, String nuevoApellido) throws GestionException {
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {
                empleado.setNombre(nuevoNombre);
                empleado.setApellido(nuevoApellido);
                return;
            }
        }
        throw new GestionException("Empleado no encontrado.");
    }

    public Empleado buscarEmpleadoPorId(String id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {
                return empleado;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder reporte = new StringBuilder("Reporte de Departamento: " + nombre + "\n");
        reporte.append("ID: ").append(id).append("\n");
        reporte.append("Capacidad máxima: ").append(capacidadMaxima).append("\n");
        reporte.append("Empleados actuales: ").append(empleados.size()).append("\n");
        reporte.append("Lista de Empleados:\n");

        for (Empleado empleado : empleados) {
            reporte.append(empleado.toString()).append("\n");
        }

        return reporte.toString();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
