package lucas.supermercadodeleon;

import spark.Filter;
import static spark.Spark.after;

import static spark.Spark.get;

import static spark.Spark.staticFiles;

public class Main {

    public static void main(String[] args) {
        staticFiles.location("/public"); 
        

        //CorsFilter.apply();
        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        get("/home", ProductoControlador.home);
        get("/buscarProducto", ProductoControlador.buscarProducto);
        get("/mostrarDetalleProducto", ProductoControlador.mostrarDetalleProducto);
        get("/agregarProductoCarrito", CarritoControlador.agregarProductoCarrito);
        get("/mostrarProductosCarrito", CarritoControlador.mostrarProductosCarrito);
        get("/eliminarProductoCarrito", CarritoControlador.eliminarProductoCarrito);
    }
}
