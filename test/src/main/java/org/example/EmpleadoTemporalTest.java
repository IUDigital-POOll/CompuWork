import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EmpleadoTemporalTest {

    @Test
    public void testConstructorYGetters() {
        EmpleadoTemporal empleado = new EmpleadoTemporal("2", "Carlos", "Gómez", 6);

        assertEquals("2", empleado.getId(), "El ID debe ser '2'");
        assertEquals("Carlos", empleado.getNombre(), "El nombre debe ser 'Carlos'");
        assertEquals("Gómez", empleado.getApellido(), "El apellido debe ser 'Gómez'");
        assertEquals(6, empleado.getDuracionContrato(), "La duración del contrato debe ser 6 meses.");
    }

    @Test
    public void testToString() {
        EmpleadoTemporal empleado = new EmpleadoTemporal("2", "Carlos", "Gómez", 6);
        String expected = "ID: 2\nNombre: Carlos Gómez\nDuración del contrato: 6 meses\nTipo de empleado: Temporal\n";
        assertEquals(expected, empleado.toString(), "La representación en cadena debe ser correcta.");
    }
}