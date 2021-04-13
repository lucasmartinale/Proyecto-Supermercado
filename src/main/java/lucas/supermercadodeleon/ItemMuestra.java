package lucas.supermercadodeleon;
import lombok.Data;

@Data
public class ItemMuestra {
    private int id_producto;
    private String imagen;
    private String nombre;
    private int cantidad;
    private float precio;
}
