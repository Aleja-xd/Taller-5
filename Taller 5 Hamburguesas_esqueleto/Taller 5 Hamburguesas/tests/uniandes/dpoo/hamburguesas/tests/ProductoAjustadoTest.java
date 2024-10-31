package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

public class ProductoAjustadoTest {
    private ProductoAjustado productoAjustado;

    @BeforeEach
    void setUp() {
        ProductoMenu hamburguesa = new ProductoMenu("Hamburguesa", 15000);
        productoAjustado = new ProductoAjustado(hamburguesa);
    }

    @Test
    void testAgregarIngrediente() {
        Ingrediente queso = new Ingrediente("Queso", 2000);
        productoAjustado.agregarIngrediente(queso);
        assertTrue(productoAjustado.getIngredientes().contains(queso), "El ingrediente no fue agregado correctamente.");
    }

    @Test
    void testQuitarIngrediente() {
        Ingrediente tomate = new Ingrediente("Tomate", 1000);
        productoAjustado.agregarIngrediente(tomate);
        productoAjustado.quitarIngrediente(tomate);
        assertFalse(productoAjustado.getIngredientes().contains(tomate), "El ingrediente no fue quitado correctamente.");
    }
    @Test
    void testGetNombre() {
        assertEquals("Hamburguesa", productoAjustado.getNombre(), "El nombre del producto no es el esperado.");
    }

    @Test
    void testGetPrecio() {
        Ingrediente queso = new Ingrediente("Queso", 2000);
        productoAjustado.agregarIngrediente(queso);
        assertEquals(17000, productoAjustado.getPrecio(), "El precio del producto ajustado no es el esperado.");
    }

    @Test
    void testGenerarTextoFactura() {
        Ingrediente queso = new Ingrediente("Queso", 2000);
        productoAjustado.agregarIngrediente(queso);
        String expectedFactura = "Hamburguesa    +Queso                2000            17000\n";
        assertEquals(expectedFactura, productoAjustado.generarTextoFactura(), "El texto de la factura no es el esperado.");
    }

}

