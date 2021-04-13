
package lucas.supermercadodeleon;

import spark.*;

public class Filters {
    /* Agregar la barra al final de cada solicitud para evitar si es llamado como "path" solo o "path/" */
     public static Filter agregarBarraFinal = (Request request, Response response) -> {
        if (!request.pathInfo().endsWith("/")) {
            response.redirect(request.pathInfo() + "/");
        }
    };
}
