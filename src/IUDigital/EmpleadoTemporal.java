package IUDigital;

public class EmpleadoTemporal extends Empleado {
    private int duracionContrato;

    public EmpleadoTemporal(String id, String nombre, String apellido, int duracionContrato) {
        super(id, nombre, apellido);
        this.duracionContrato = duracionContrato;
    }

    public int getDuracionContrato() {
        return duracionContrato;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Duración del contrato: %d meses\nTipo de empleado: Temporal\n", duracionContrato);
    }
}