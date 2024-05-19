package minitienda;

// Importamos las librerias necesarias
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

// Clase Controlador
public class Controlador extends HttpServlet {

    // Atributo
    GestorBaseDatos conBD;

    // Metodo para inicializar el servlet y establecer la conexion con la base de datos
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        conBD = new GestorBaseDatos();
    }

    // Metodo para manejar las peticiones GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    // Metodo para manejar las peticiones POST
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos un objeto de la clase AsistenteControlador
        AsistenteControlador helper_carrito = new AsistenteControlador();

        // Comprobamos si se ha seleccionado algun disco para eliminarlo del carrito
        if(request.getParameter("posEliminar") != null){
            String posDiscoString = request.getParameter("posEliminar");
            int posDisco = Integer.parseInt(posDiscoString);
            helper_carrito.eliminar(request, posDisco);
            gotoPage("/vistaCarrito.jsp", request, response);
        }

        // Comprobamos si se ha seleccionado algun disco para anhadirlo al carrito
        else if(request.getParameter("cd") != null) {
            // Obtenemos la informacion del disco
            String infoCd = request.getParameter("cd");
            // Obtenemos la cantidad
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            // Si ya estaba en el carrito
            if(helper_carrito.contieneDisco(request, infoCd)){
                helper_carrito.actualizarCantidadDisco(request, infoCd, cantidad);

            }
            // En caso contrario
            else{
                // Recuperamos informacion del precio del CD
                StringTokenizer t = new StringTokenizer(infoCd,"|");
                t.nextToken();
                t.nextToken();
                t.nextToken();
                String precioString = t.nextToken();
                precioString = precioString.replace('$',' ').trim();
                float precioDisco = Float.parseFloat(precioString);
                helper_carrito.insertarDisco(request, infoCd, precioDisco, cantidad);
            }
            gotoPage("/vistaCarrito.jsp", request, response);
        }

        // Comprobamos si se ha pulsado el boton de ir a la caja
        else if(request.getParameter("go_caja") != null){
            gotoPage("/vistaCaja.jsp", request, response);
        }

        // Comprobamos si se ha pulsado el boton de ir al inicio
        else if(request.getParameter("go_index") != null){
            gotoPage("/index.html", request, response);
        }

        // Comprobamos si se ha pulsado el boton de realizar pedido
        else if(request.getParameter("realizar_pedido") != null) {
            // Obtenemos los datos insertados
            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String contrasenha = request.getParameter("contrasenha");
            String tipo = request.getParameter("tipo");
            String tarjeta = request.getParameter("tarjeta");
            // Insertamos el usuario en la base de datos
            conBD.registrarUsuario(nombre, correo, contrasenha, tipo, tarjeta);
            // Insertamos el pedido en la base de datos
            HttpSession session = request.getSession(true);
            Carrito carrito = helper_carrito.obtenerCarrito(session);
            conBD.registrarPedido(nombre, carrito.getImporteTotal());
            // Cogemos lo que insertamos en la base de datos y los introducimos en la sesion, para mostrarlos por pantalla en el jsp
            Usuario usuario = conBD.iniciarSesionUsuario(nombre, contrasenha);
            Pedido pedido = conBD.obtenerUltimoPedido(nombre);
            session.setAttribute("usuario", usuario);
            session.setAttribute("pedido", pedido);
            gotoPage("/vistaFinal.jsp", request, response);
        }
    }

    // Metodo para cambiar la vista de la pagina (mediante el uso de un dispatcher)
    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    // Metodo para destruir el servlet y cerrar la conexion con la base de datos
    public void destroy() {
        conBD.cerrarConexion();
    }

}
