public class Empleado {
    private String id;
    private String nombre;
    private String apellido;

    // Constructor
    public Empleado(String id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Métodos get
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    // Métodos set
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Método toString para mostrar información del empleado
    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + " " + apellido;
    }
}
