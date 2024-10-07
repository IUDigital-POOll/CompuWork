import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EmpleadoPermanenteTest {

    @Test
    public void testConstructorYGetters() {
        EmpleadoPermanente empleado = new EmpleadoPermanente("1", "Juan", "Pérez", 2500.00);

        assertEquals("1", empleado.getId(), "El ID debe ser '1'");
        assertEquals("Juan", empleado.getNombre(), "El nombre debe ser 'Juan'");
        assertEquals("Pérez", empleado.getApellido(), "El apellido debe ser 'Pérez'");
        assertEquals(2500.00, empleado.getSalario(), "El salario debe ser 2500.00");
    }

    @Test
    public void testToString() {
        EmpleadoPermanente empleado = new EmpleadoPermanente("1", "Juan", "Pérez", 2500.00);
        String expected = "ID: 1\nNombre: Juan Pérez\nSalario: 2500.00\nTipo de empleado: Permanente\n";
        assertEquals(expected, empleado.toString(), "La representación en cadena debe ser correcta.");
    }
}