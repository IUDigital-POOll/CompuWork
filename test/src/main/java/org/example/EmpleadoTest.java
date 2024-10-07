import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmpleadoTest {
    private Empleado empleado;

    @BeforeEach
    public void setUp() {
        empleado = new Empleado("1", "Juan", "Pérez");
    }

    @Test
    public void testGetId() {
        assertEquals("1", empleado.getId(), "El ID debe ser '1'");
    }

    @Test
    public void testGetNombre() {
        assertEquals("Juan", empleado.getNombre(), "El nombre debe ser 'Juan'");
    }

    @Test
    public void testGetApellido() {
        assertEquals("Pérez", empleado.getApellido(), "El apellido debe ser 'Pérez'");
    }

    @Test
    public void testSetNombre() {
        empleado.setNombre("Carlos");
        assertEquals("Carlos", empleado.getNombre(), "El nombre debe ser actualizado a 'Carlos'");
    }

    @Test
    public void testSetApellido() {
        empleado.setApellido("Gómez");
        assertEquals("Gómez", empleado.getApellido(), "El apellido debe ser actualizado a 'Gómez'");
    }

    @Test
    public void testToString() {
        String expected = "ID: 1\nNombre: Juan Pérez\n";
        assertEquals(expected, empleado.toString(), "La representación en cadena debe ser correcta");
    }
}