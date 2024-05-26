package minitienda;

// Importamos la libreria necesaria
import javax.servlet.http.*;

// Clase HelperControlador
public class HelperControlador {

    // Metodo para comprobar si un disco ya existe en el carrito
    public boolean existeDisco(HttpSession session, String info) {
        // Obtenemos el carrito de la sesion
        Carrito carrito = obtenerCarrito(session);

        // Iteramos sobre la lista de discos del carrito
        for (Disco disco : carrito.getDiscos()) {
            if (disco.getInfo().equals(info)) {
                return true;
            }
        }
        return false;
    }

    // Metodo para anhadir un disco al carrito
    public void anhadirDisco(HttpSession session, String info, float precio, int cantidad) {
        // Obtenemos el carrito de la sesion
        Carrito carrito = obtenerCarrito(session);

        // Actualizamos el importe total del carrito sumando el precio del disco por la cantidad
        carrito.setImporteTotal(carrito.getImporteTotal() + (precio * cantidad));

        // Anhadimos el nuevo disco a la lista de discos del carrito
        carrito.getDiscos().add(new Disco(info, precio, cantidad));

        // Actualizamos el carrito de la sesion
        session.setAttribute("carrito", carrito);
    }

    // Metodo para actualizar la cantidad de un disco en el carrito
    public void actualizarDisco(HttpSession session, String info, int cantidad) {
        // Obtenemos el carrito de la sesion
        Carrito carrito = obtenerCarrito(session);

        // Iteramos sobre la lista de discos del carrito para actualizar la cantidad y el importe total
        for (Disco disco : carrito.getDiscos()) {
            if (disco.getInfo().equals(info)) {
                disco.setCantidad(disco.getCantidad() + cantidad);
                carrito.setImporteTotal(carrito.getImporteTotal() + (disco.getPrecio() * cantidad));
            }
        }

        // Actualizamos el carrito de la sesion
        session.setAttribute("carrito", carrito);
    }

    // Metodo para eliminar un disco del carrito
    public void eliminarDisco(HttpSession session, int posicion) {
        // Obtenemos el carrito de la sesion
        Carrito carrito = obtenerCarrito(session);

        // Obtenemos el disco de la lista con la posicion pasada
        Disco disco = carrito.getDiscos().get(posicion);

        // Actualizamos el importe total del carrito y eliminamos el disco de la lista
        carrito.setImporteTotal(carrito.getImporteTotal() - disco.getPrecio() * disco.getCantidad());
        carrito.getDiscos().remove(posicion);

        // Actualizamos el carrito de la sesion
        session.setAttribute("carrito", carrito);
    }

    // Metodo para vaciar el carrito
    public void vaciarCarrito(HttpSession session) {
        // Obtenemos el carrito de la sesion
        Carrito carrito = obtenerCarrito(session);

        // Ponemos el importe total a 0 y vaciamos la lista de discos
        carrito.setImporteTotal(0);
        carrito.getDiscos().clear();

        // Actualizamos el carrito de la sesion
        session.setAttribute("carrito", carrito);
    }

    // Metodo auxiliar para obtener el carrito de la sesion
    public Carrito obtenerCarrito(HttpSession session) {
        Carrito carrito = (Carrito)session.getAttribute("carrito");
        if (carrito == null) {
            session.setAttribute("carrito", new Carrito());
            carrito = (Carrito)session.getAttribute("carrito");
        }
        return carrito;
    }

}
