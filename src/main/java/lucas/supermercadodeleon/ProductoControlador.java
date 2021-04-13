package lucas.supermercadodeleon;

import java.util.ArrayList;
import java.util.Collections;
import spark.Request;
import spark.Response;
import spark.Route;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class ProductoControlador {
    
    //Ruta que muestra la home por defecto
    public static Route home = (Request req, Response res) -> {
        ProductoDAO pdao = new ProductoDAO();
        List<Producto> listaProd = pdao.selectAll();

        HashMap model = new HashMap();
        model.put("productos", listaProd);
        model.put("template", "templates/home.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/plantilla.vtl"));
    };

    //Ruta que busca el producto ingresado en el buscador
    public static Route buscarProducto = (Request req, Response res) -> {
        HashMap model = new HashMap();
        List<Producto> listaProd;
        Producto p;
        p = new Producto();
        String nombre = req.queryParams("nombre");
        ProductoDAO pDAO = new ProductoDAO();
        
                
        if (nombre.equals("")) {
            listaProd = pDAO.selectAll();
        } else {
            listaProd = pDAO.selectProductoxNombre(nombre);
        }
        
        
        model.put("productos", listaProd);
        model.put("producto", p);
        model.put("template", "templates/search.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/plantilla.vtl"));
    };

    //Ruta que muestra un producto dado al hacerle clic
    public static Route mostrarDetalleProducto = (Request req, Response res) -> {
        HashMap model = new HashMap();
        Integer id = Integer.valueOf(req.queryParams("id"));

        ProductoDAO pDAO = new ProductoDAO();
        Producto p;
        p = pDAO.getProductoID(id);

        model.put("producto", p);
        model.put("template", "templates/productoPorId.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/plantilla.vtl"));
    };
}
