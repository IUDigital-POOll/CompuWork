import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepartamentoTest {
    private Departamento departamento;

    @BeforeEach
    public void setUp() {
        departamento = new Departamento("D1", "Recursos Humanos", 2);
    }

    @Test
    public void testAgregarEmpleado() throws GestionException {
        Empleado empleado1 = new Empleado("1", "Juan", "Pérez");
        Empleado empleado2 = new Empleado("2", "Carlos", "Gómez");

        departamento.agregarEmpleado(empleado1);
        departamento.agregarEmpleado(empleado2);

        assertEquals(2, departamento.getEmpleados().size(), "Se deben agregar 2 empleados.");
    }

    @Test
    public void testAgregarEmpleadoExcedeCapacidad() {
        Empleado empleado1 = new Empleado("1", "Juan", "Pérez");
        Empleado empleado2 = new Empleado("2", "Carlos", "Gómez");
        Empleado empleado3 = new Empleado("3", "Ana", "López");

        try {
            departamento.agregarEmpleado(empleado1);
            departamento.agregarEmpleado(empleado2);
            departamento.agregarEmpleado(empleado3);
            fail("Se esperaba una excepción por exceder la capacidad máxima.");
        } catch (GestionException e) {
            assertEquals("Capacidad máxima del departamento alcanzada.", e.getMessage());
        }
    }

    @Test
    public void testEliminarEmpleadoPorId() throws GestionException {
        Empleado empleado = new Empleado("1", "Juan", "Pérez");
        departamento.agregarEmpleado(empleado);
        departamento.eliminarEmpleadoPorId("1");

        assertEquals(0, departamento.getEmpleados().size(), "El empleado debe ser eliminado.");
    }

    @Test
    public void testEliminarEmpleadoPorIdNoEncontrado() {
        try {
            departamento.eliminarEmpleadoPorId("999");
            fail("Se esperaba una excepción porque el empleado no existe.");
        } catch (GestionException e) {
            assertEquals("Empleado con ID 999 no encontrado.", e.getMessage());
        }
    }

    @Test
    public void testBuscarEmpleadoPorId() throws GestionException {
        Empleado empleado = new Empleado("1", "Juan", "Pérez");
        departamento.agregarEmpleado(empleado);

        Empleado encontrado = departamento.buscarEmpleadoPorId("1");
        assertNotNull(encontrado, "El empleado debería ser encontrado.");
        assertEquals("Juan", encontrado.getNombre(), "El nombre del empleado encontrado debe ser 'Juan'.");
    }

    @Test
    public void testActualizarDepartamento() {
        departamento.actualizarDepartamento("Administración", 5);
        assertEquals("Administración", departamento.getNombre(), "El nombre del departamento debe actualizarse.");
        assertEquals(5, departamento.getCapacidadMaxima(), "La capacidad máxima debe actualizarse.");
    }
}