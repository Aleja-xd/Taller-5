package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido("Juan", "Calle 123");
    }

    @Test
    void testAgregarProducto() {
        ProductoMenu hamburguesa = new ProductoMenu("Hamburguesa", 15000);
        pedido.agregarProducto(hamburguesa);
        assertTrue(pedido.getProductos().contains(hamburguesa), "El producto no fue agregado correctamente.");
    }

    @Test
    void testGenerarTextoFactura() {
        ProductoMenu hamburguesa = new ProductoMenu("Hamburguesa", 15000);
        pedido.agregarProducto(hamburguesa);
        String expectedFactura = "Cliente: Juan\n" +
                                 "Dirección: Calle 123\n" +
                                 "----------------\n" +
                                 "Hamburguesa\n" +
                                 "            15000\n" +
                                 "----------------\n" +
                                 "Precio Neto:  15000\n" +
                                 "IVA:          2850\n" +
                                 "Precio Total: 17850\n";
        assertEquals(expectedFactura, pedido.generarTextoFactura(), "El texto de la factura no es el esperado.");
    }
    @Test
    void testGuardarFactura() {
        ProductoMenu hamburguesa = new ProductoMenu("Hamburguesa", 15000);
        pedido.agregarProducto(hamburguesa);
        File archivo = new File("factura_test.txt");
        try {
            pedido.guardarFactura(archivo);
            assertTrue(archivo.exists(), "El archivo de la factura no fue creado.");
            // Leer el contenido del archivo y verificar que sea correcto
            StringBuilder contenido = new StringBuilder();
            try (Scanner scanner = new Scanner(archivo)) {
                while (scanner.hasNextLine()) {
                    contenido.append(scanner.nextLine()).append("\n");
                }
            }
            String expectedFactura = "Cliente: Juan\n" +
                                     "Dirección: Calle 123\n" +
                                     "----------------\n" +
                                     "Hamburguesa\n" +
                                     "            15000\n" +
                                     "----------------\n" +
                                     "Precio Neto:  15000\n" +
                                     "IVA:          2850\n" +
                                     "Precio Total: 17850\n";
            assertEquals(expectedFactura, contenido.toString(), "El contenido del archivo de la factura no es el esperado.");
        } catch (FileNotFoundException e) {
            fail("No se pudo guardar la factura: " + e.getMessage());
        } finally {
            // Eliminar el archivo de prueba
            archivo.delete();
        }
    }

}
