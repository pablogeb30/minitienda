package minitienda;

// Importamos las librerias necesarias
import java.util.ArrayList;
import javax.servlet.http.*;

// Clase AsistenteControlador
public class AsistenteControlador {

    // Metodo para insertar un disco en el carrito
    public void insertarDisco(HttpServletRequest request,String info, float precio, int cantidad) {
        HttpSession session = request.getSession(true);
        Carrito carrito = obtenerCarrito(session);
        ArrayList<Disco> discos = carrito.getDiscos();
        float total = carrito.getImporteTotal();
        discos.add(new Disco(info, precio, cantidad));
        total += precio * cantidad;
        carrito.setImporteTotal(total);
        // Almacenamos el carrito en la sesion
        session.setAttribute("carrito", carrito);
    }

    // Metodo para actualizar el importe total del carrito
    private void actualizarImporteTotal(HttpServletRequest request, float precio) {
        HttpSession session = request.getSession(true);
        Carrito carrito = obtenerCarrito(session);
        float total = carrito.getImporteTotal();
        total -= precio;
        carrito.setImporteTotal(total);
        // Almacenamos el carrito en la sesion
        session.setAttribute("carrito", carrito);
    }

    // Metodo para eliminar un disco del carrito
    public void eliminar(HttpServletRequest request, int pos) {
        HttpSession session = request.getSession(true);
        Carrito carrito = obtenerCarrito(session);
        ArrayList<Disco> discos = carrito.getDiscos();
        int cantidad = discos.get(pos).getCantidad();
        actualizarImporteTotal(request, discos.get(pos).getPrecio()*cantidad);
        // Eliminamos los discos
        discos.remove(pos);
        // Almacenamos el carrito en la sesion
        session.setAttribute("carrito", carrito);
    }

    // Metodo para actualizar la cantidad de un disco en el carrito
    public void actualizarCantidadDisco(HttpServletRequest request, String info, int cantidad) {
        HttpSession session = request.getSession(true);
        Carrito carrito = obtenerCarrito(session);
        ArrayList<Disco> discos = carrito.getDiscos();
        float total = carrito.getImporteTotal();
        Disco disco = buscarDisco(info, discos);
        int aux;
        if (disco != null) {
            aux = disco.getCantidad();
            disco.setCantidad(aux+cantidad);
            total += disco.getPrecio() * cantidad;
        }
        carrito.setImporteTotal(total);
        // Almacenamos el carrito en la sesion
        session.setAttribute("carrito", carrito);
    }

    // Metodo para comprobar si un disco ya esta en el carrito
    public Boolean contieneDisco(HttpServletRequest request, String info) {
        HttpSession session = request.getSession(true);
        Carrito carrito = obtenerCarrito(session);
        ArrayList<Disco> discos = carrito.getDiscos();
        Disco disco = buscarDisco(info, discos);
        return disco != null;
    }

    // Metodo para buscar un disco en el carrito
    private Disco buscarDisco(String info, ArrayList<Disco> discos){
        for (Disco disco : discos) {
            if (disco.getInfo().equals(info)) {
                return disco;
            }
        }
        return null;
    }

    // Metodo auxiliar para obtener el carrito de la sesion
    Carrito obtenerCarrito(HttpSession session) {
        Carrito carrito = (Carrito)session.getAttribute("carrito");
        if (carrito == null){
            System.out.println("El carrito está vacío");
            // Inicializamos el atributo
            session.setAttribute("carrito", new Carrito() );
            carrito = (Carrito)session.getAttribute("carrito");
        }
        return carrito;
    }

}
