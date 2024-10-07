import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GestionExceptionTest {

    @Test
    public void testGestionException() {
        String mensaje = "Este es un mensaje de error";
        GestionException exception = new GestionException(mensaje);

        assertEquals(mensaje, exception.getMessage(), "El mensaje de la excepción debe coincidir.");
    }
}