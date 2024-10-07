import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ReporteDesempenioTest {

    @Test
    public void testConstructorYGetters() {
        Empleado empleado = new EmpleadoPermanente("1", "Juan", "Pérez", 2500.00);
        ReporteDesempenio reporte = new ReporteDesempenio(empleado, "Excelente", "Sigue así.");

        assertEquals(empleado, reporte.getEmpleado(), "El empleado debe ser el mismo que se pasó al constructor.");
        assertEquals("Excelente", reporte.getEvaluacion(), "La evaluación debe ser 'Excelente'.");
        assertEquals("Sigue así.", reporte.getComentarios(), "Los comentarios deben ser 'Sigue así.'.");
    }

    @Test
    public void testToString() {
        Empleado empleado = new EmpleadoPermanente("1", "Juan", "Pérez", 2500.00);
        ReporteDesempenio reporte = new ReporteDesempenio(empleado, "Excelente", "Sigue así.");

        String expected = "------------------------------------\n" +
                "Reporte de Desempeño del Empleado:\n" +
                "Nombre: Juan Pérez\n" +
                "ID: 1\n" +
                "Evaluación: Excelente\n" +
                "Comentarios: Sigue así.\n" +
                "------------------------------------\n";

        assertEquals(expected, reporte.toString(), "La representación en cadena debe ser correcta.");
    }
}