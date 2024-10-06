public class EmpleadoPermanente extends Empleado {
    private double salario;

    public EmpleadoPermanente(String id, String nombre, String apellido, double salario) {
        super(id, nombre, apellido);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "EmpleadoPermanente{" +
                "salario=" + salario +
                ", " + super.toString() +
                '}';
    }
}