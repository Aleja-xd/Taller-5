package uniandes.dpoo.hamburguesas.mundo;

import java.util.ArrayList;
import java.util.List;

/**
 * Un producto ajustado es un producto para el cual el cliente solicitó alguna modificación.
 */
public class ProductoAjustado implements Producto
{
    /**
     * El producto base que el cliente sobre el cual el cliente quiere hacer ajustes
     */
    private ProductoMenu productoBase;

    /**
     * La lista de ingrediente que el usuario quiere agregar. El mismo ingrediente puede aparecer varias veces.
     */
    private ArrayList<Ingrediente> agregados;

    /**
     * La lista de ingrediente que el usuario quiere eliminar.
     */
    private ArrayList<Ingrediente> eliminados;

    /**
     * Construye un nuevo producto ajustado a partir del producto base y sin modificaciones
     * @param productoBase El producto base que se va a ajustar
     */
    public ProductoAjustado( ProductoMenu productoBase )
    {
        this.productoBase = productoBase;
        agregados = new ArrayList<Ingrediente>( );
        eliminados = new ArrayList<Ingrediente>( );
    }

    @Override
    public String getNombre( )
    {
        return productoBase.getNombre( );
    }

    /**
     * Retorna el precio del producto ajustado, que debe ser igual al del producto base, sumándole el precio de los ingredientes adicionales.
     */
    @Override
    public int getPrecio( )
    {
    	int precio = productoBase.getPrecio();
        for (Ingrediente ing : agregados) {
            precio += ing.getCostoAdicional();
        }
        return precio;
    }

    /**
     * Genera el texto que debe aparecer en la factura.
     * 
     * El texto incluye el producto base, los ingredientes adicionales con su costo, los ingredientes eliminados, y el precio total
     */
    @Override
    public String generarTextoFactura() {
        StringBuffer sb = new StringBuffer();
        sb.append(productoBase.getNombre()); // Usar el nombre del producto base
        for (Ingrediente ing : agregados) {
            sb.append("    +" + ing.getNombre());
            sb.append("                " + ing.getCostoAdicional());
        }
        for (Ingrediente ing : eliminados) {
            sb.append("    -" + ing.getNombre());
        }

        sb.append("            " + getPrecio() + "\n");

        return sb.toString();
    }

    /**
     * Agrega un ingrediente al producto ajustado
     * @param ingrediente El ingrediente a agregar
     */
    public void agregarIngrediente(Ingrediente ingrediente) {
        agregados.add(ingrediente);
    }

    /**
     * Quita un ingrediente del producto ajustado
     * @param ingrediente El ingrediente a quitar
     */
    public void quitarIngrediente(Ingrediente ingrediente) {
        eliminados.add(ingrediente);
        agregados.remove(ingrediente);
    }

    /**
     * Retorna la lista de ingredientes agregados
     * @return La lista de ingredientes agregados
     */
    public List<Ingrediente> getIngredientes() {
        return agregados;
    }
}
