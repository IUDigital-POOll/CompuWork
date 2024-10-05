public class Empleado {
    private String id;
    private String nombre;
    private String apellido;
    private String departamentoId;

    public Empleado(String id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(String departamentoId) {
        this.departamentoId = departamentoId;
    }

    @Override
    public String toString() {
        return "\tEmpleado ID: " + id +
                "\n\tNombre: " + nombre +
                "\n\tApellido: " + apellido +
                "\n\tDepartamento ID: " + departamentoId + "\n";
    }

}