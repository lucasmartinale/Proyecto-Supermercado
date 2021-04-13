package lucas.supermercadodeleon;
import lombok.Data;

@Data
public class ItemCarrito {
    private int cantidad;
    private int id_producto;
    private int id_carrito;
}
