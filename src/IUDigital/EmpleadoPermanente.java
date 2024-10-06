public class EmpleadoPermanente extends Empleado {
    private double salario;

    public EmpleadoPermanente(String id, String nombre, String apellido, double salario) {
        super(id, nombre, apellido);
        this.salario = salario;
    }

    // Otros métodos específicos de EmpleadoPermanente
}
