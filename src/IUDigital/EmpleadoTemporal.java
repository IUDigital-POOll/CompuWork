public class EmpleadoTemporal extends Empleado {
    private int duracionContrato;

    public EmpleadoTemporal(String id, String nombre, String apellido, int duracionContrato) {
        super(id, nombre, apellido);
        this.duracionContrato = duracionContrato;
    }

    // Otros métodos específicos de EmpleadoTemporal
}

