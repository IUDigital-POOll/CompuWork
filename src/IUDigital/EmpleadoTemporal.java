public class EmpleadoTemporal extends Empleado {
    private int duracionContrato;

    public EmpleadoTemporal(String id, String nombre, String apellido, int duracionContrato) {
        super(id, nombre, apellido);
        this.duracionContrato = duracionContrato;
    }

    public int getDuracionContrato() {
        return duracionContrato;
    }

    public void setDuracionContrato(int duracionContrato) {
        this.duracionContrato = duracionContrato;
    }

    @Override
    public String toString() {
        return "EmpleadoTemporal{" +
                "duracionContrato=" + duracionContrato +
                ", " + super.toString() +
                '}';
    }
}