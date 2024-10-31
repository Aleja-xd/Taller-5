package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
    private ProductoMenu producto;

    @BeforeEach
    void setUp() {
        producto = new ProductoMenu("Hamburguesa", 15000);
    }

    @Test
    void testGetNombre() {
        assertEquals("Hamburguesa", producto.getNombre(), "El nombre del producto no es el esperado.");
    }

    @Test
    void testGetPrecio() {
        assertEquals(15000, producto.getPrecio(), "El precio del producto no es el esperado.");
    }
    @Test
    void testGenerarTextoFactura() {
        String expectedFactura = "Hamburguesa\n            15000\n";
        assertEquals(expectedFactura, producto.generarTextoFactura(), "El texto de la factura no es el esperado.");
    }

}
