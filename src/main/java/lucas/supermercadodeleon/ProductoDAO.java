package lucas.supermercadodeleon;

import java.util.List;
import org.sql2o.Connection;
import spark.Request;
import spark.Response;
import spark.Route;
import util.Sql2oDAO;


public class ProductoDAO {
   
    //Devuelve una lista de productos como resultado
    public List<Producto> selectProductoxNombre(String nombre){
        nombre="%"+nombre+"%";
       
       String sql = "SELECT * FROM producto WHERE nombre like :nombre ";
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Producto> respuesta = con
                    .createQuery(sql)
                    .addParameter("nombre", nombre)
                    .executeAndFetch(Producto.class);
                return respuesta;
            }
    }   
    
    //retorna el producto a detallar
    public Producto getProductoID(Integer id_producto){
            String sql = "SELECT * FROM PRODUCTO WHERE id_producto = :id_producto ";
            
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Producto> respuesta = 
                        con.createQuery(sql)
                                .addParameter("id_producto", id_producto)
                                .executeAndFetch(Producto.class);
                              
             if (respuesta.size() > 1) 
                    System.out.println("ERROR BUSCAR ELEMENTO {} en selectId"+id_producto); //manejar el ERROR producto VACIO o GENERAR UN ERROR
                
                System.out.println("SELECT ID con {}"+ id_producto);
                return respuesta.get(0);
                
            } catch(Exception e){
                System.out.println("Error selectId con {} "+ sql+ e);
            }
            System.out.println("ERROR AL NO ENCONTRAR EL ELEMENTO en SelectId con {}"+id_producto);
            return null;
    }
    
    
        public List<Producto> selectAll() {
        String selectALLSQL = "SELECT * FROM PRODUCTO;";
        List<Producto> productos = null;
        try (Connection con = Sql2oDAO.getSql2o().open()) {
           productos = con.createQuery(selectALLSQL).executeAndFetch(Producto.class);
        } catch(Exception e){
            System.out.println("Error selectALL con {}"+selectALLSQL+ e);
        }
        return productos;
    }   
    
}