package lucas.supermercadodeleon;

import java.util.List;
import org.sql2o.Connection;
import util.Sql2oDAO;

public class CarritoDAO {

    public void insertItemCarrito(ItemCarrito i) {
        System.out.println("entro a insertItemCarrito");
        String insertSQL = "INSERT INTO itemcarrito (id_producto, id_carrito, cantidad) VALUES (:id_producto, :id_carrito, :cantidad)";
        System.out.println(insertSQL);

        //try (Connection con = Conexion.getConexion()) {
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(insertSQL).bind(i).executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al Insertar con {}");
        }
        System.out.println("FINALIZO LA INSERCION {}");
    }

    public void deleteItemCarrito(Integer id_producto, Integer id_carrito) {
        String deleteSQL = "DELETE FROM itemcarrito WHERE id_producto = :id_producto and id_carrito = :id_carrito";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(deleteSQL)
                    .addParameter("id_producto", id_producto)
                    .addParameter("id_carrito", id_carrito)
                    .executeUpdate();

        } catch (Exception e) {
            System.out.println("Error Delete con {}");
        }
    }

    public List<ItemCarrito> selectAllItemsCarrito(Integer id_carrito) {
        String sql = "SELECT * FROM itemcarrito WHERE id_carrito= :id_carrito;";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            List<ItemCarrito> listaItems = con
                    .createQuery(sql)
                    .addParameter("id_carrito", id_carrito)
                    .executeAndFetch(ItemCarrito.class);
            return listaItems;
        }

    }
}
