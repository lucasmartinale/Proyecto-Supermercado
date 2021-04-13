package lucas.supermercadodeleon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class CarritoControlador {

    public static Route agregarProductoCarrito = (Request req, Response res) -> {
        System.out.println("entro a agregarproductocarrito");
        Integer id_producto = Integer.valueOf(req.queryParams("id"));
        Integer cant = Integer.valueOf(req.queryParams("cantidad"));

        System.out.println("entro a pidio los parametros");
        //Preparar carrito para enviarlo
        ItemCarrito i = new ItemCarrito();
        i.setId_producto(id_producto);
        i.setId_carrito(1);
        i.setCantidad(cant);
        
        System.out.println("termino de armar el itemcarrito");

        CarritoDAO cDAO = new CarritoDAO();
        cDAO.insertItemCarrito(i);

        return ProductoControlador.home.handle(req, res);
    };

    public static Route eliminarProductoCarrito = (Request req, Response res) -> {
        
        Integer id_producto = Integer.valueOf(req.queryParams("id"));

        CarritoDAO cDAO = new CarritoDAO();
        cDAO.deleteItemCarrito(id_producto, 1);

        return CarritoControlador.mostrarProductosCarrito.handle(req, res);
    };

    public static Route mostrarProductosCarrito = (Request req, Response res) -> {
        
        CarritoDAO cDAO = new CarritoDAO();
        List<ItemCarrito> listaItems = cDAO.selectAllItemsCarrito(1);

        ProductoDAO pDAO = new ProductoDAO();
        Producto p;

        List<ItemMuestra> listaMuestra = new ArrayList<ItemMuestra>();

        for (int i = 0; i < listaItems.size(); i++) {

            ItemMuestra itemM = new ItemMuestra(); //itemMuestra es para mostrar el carrito

            //añadimos la cantidad de determinado producto
                itemM.setCantidad(listaItems.get(i).getCantidad());

            //obtenemos los detalles del producto
            int id = listaItems.get(i).getId_producto();
            p = pDAO.getProductoID(id); //obtener producto por el id
            itemM.setImagen(p.getImagen());
            itemM.setNombre(p.getNombre());
            itemM.setPrecio(p.getPrecio());
            itemM.setId_producto(p.getId_producto());

            listaMuestra.add(itemM);
        }

        ItemMuestra item = new ItemMuestra();
        HashMap model = new HashMap();
        model.put("i", item);
        model.put("items", listaMuestra);
        model.put("template", "templates/carrito.vtl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/plantilla.vtl"));
    };

}
