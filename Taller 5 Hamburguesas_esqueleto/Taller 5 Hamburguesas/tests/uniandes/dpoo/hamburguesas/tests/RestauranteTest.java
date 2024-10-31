package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;

import java.io.File;
import java.io.IOException;

public class RestauranteTest {
    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    void testIniciarPedido() {
        try {
            restaurante.iniciarPedido("Juan", "Calle 123");
            assertNotNull(restaurante.getPedidoEnCurso(), "El pedido no se inició correctamente.");
        } catch (YaHayUnPedidoEnCursoException e) {
            fail("No debería haber un pedido en curso al iniciar uno nuevo.");
        }
    }

    @Test
    void testCerrarPedido() {
        try {
            restaurante.iniciarPedido("Juan", "Calle 123");
            restaurante.cerrarYGuardarPedido();
            assertNull(restaurante.getPedidoEnCurso(), "El pedido no se cerró correctamente.");
        } catch (YaHayUnPedidoEnCursoException e) {
            fail("No debería haber un pedido en curso al iniciar uno nuevo.");
        } catch (NoHayPedidoEnCursoException | IOException e) {
            fail("No debería haber un error al cerrar y guardar el pedido.");
        }
    }
    @Test
    void testGetMenuBase() {
        assertNotNull(restaurante.getMenuBase(), "El menú base no debería ser null.");
    }
    @Test
    void testGetMenuCombos() {
        assertNotNull(restaurante.getMenuCombos(), "El menú de combos no debería ser null.");
    }
    @Test
    void testGetIngredientes() {
        assertNotNull(restaurante.getIngredientes(), "La lista de ingredientes no debería ser null.");
    }
    @Test
    void testGetPedidoEnCurso() {
        try {
            restaurante.iniciarPedido("Juan", "Calle 123");
            assertNotNull(restaurante.getPedidoEnCurso(), "El pedido en curso no debería ser null.");
        } catch (YaHayUnPedidoEnCursoException e) {
            fail("No debería haber un pedido en curso al iniciar uno nuevo.");
        }
    }

    @Test
    void testGetPedidos() {
        try {
            restaurante.iniciarPedido("Juan", "Calle 123");
            restaurante.cerrarYGuardarPedido();
            assertFalse(restaurante.getPedidos().isEmpty(), "La lista de pedidos no debería estar vacía.");
        } catch (YaHayUnPedidoEnCursoException | NoHayPedidoEnCursoException | IOException e) {
            fail("No debería haber un error al cerrar y guardar el pedido.");
        }
    }
    @Test
    void testCargarInformacionRestaurante() {
        File archivoIngredientes = new File("ingredientes.txt");
        File archivoMenu = new File("menu.txt");
        File archivoCombos = new File("combos.txt");
        try {
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
            assertFalse(restaurante.getIngredientes().isEmpty(), "Los ingredientes no se cargaron correctamente.");
            assertFalse(restaurante.getMenuBase().isEmpty(), "El menú base no se cargó correctamente.");
            assertFalse(restaurante.getMenuCombos().isEmpty(), "Los combos no se cargaron correctamente.");
        } catch (IOException | HamburguesaException e) {
            e.printStackTrace(); // Agregar traza de la excepción para depuración
            fail("No debería haber un error al cargar la información del restaurante.");
        }
    }
}
