

public class EmpleadoPermanente extends Empleado {
    private double salario;

    public EmpleadoPermanente(String id, String nombre, String apellido, double salario) {
        super(id, nombre, apellido);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Salario: %.2f\nTipo de empleado: Permanente\n", salario);
    }
}