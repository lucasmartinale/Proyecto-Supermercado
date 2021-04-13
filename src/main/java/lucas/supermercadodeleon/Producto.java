package lucas.supermercadodeleon;
import lombok.Data;

public @Data class Producto {
    int id_producto;
    float precio;
    String nombre;
    String descripcion;
    int stock_producto;
    String imagen;
}
