package minitienda;

// Importamos las librerias necesarias
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

// Clase Controlador
public class Controlador extends HttpServlet {

    // Atributo
    GestorBaseDatos gestorBD;

    // Metodo para inicializar el servlet y establecer la conexion con la base de datos
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gestorBD = new GestorBaseDatos();
    }

    // Metodo para manejar las peticiones GET (redirigimos a doPost)
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    // Metodo para manejar las peticiones POST
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Creamos una instancia del helper del controlador
        HelperControlador helperControlador = new HelperControlador();

        // Obtenemos la sesion actual
        HttpSession session = req.getSession(true);

        // Si se ha pulsado el boton de ir al carrito
        if (req.getParameter("disco") != null) {
            // Obtenemos la informacion del disco
            String info = req.getParameter("disco");

            // Obtenemos la cantidad seleccionada
            int cantidad = Integer.parseInt(req.getParameter("cantidad"));

            // Obtenemos el precio del disco y lo insertamos en el carrito
            if (!helperControlador.existeDisco(session, info)) {
                StringTokenizer t = new StringTokenizer(info,"|");
                t.nextToken();
                t.nextToken();
                t.nextToken();
                String precioString = t.nextToken();
                precioString = precioString.replace('$',' ').trim();
                float precio = Float.parseFloat(precioString);
                helperControlador.anhadirDisco(session, info, precio, cantidad);
            }

            // Si ese disco ya estaba en el carrito, actualizamos la cantidad
            else {
                helperControlador.actualizarDisco(session, info, cantidad);
            }

            // Redirigimos a la vista del carrito
            gotoPage("/vistaCarrito.jsp", req, resp);
        }

        // Si se ha marcado algun disco para eliminarlo del carrito
        else if (req.getParameter("posDiscoMarcado") != null) {
            // Obtenemos la posicion del disco en el carrito
            String posDiscoMarcado = req.getParameter("posDiscoMarcado");
            int posicion = Integer.parseInt(posDiscoMarcado);

            // Eliminamos el disco del carrito
            helperControlador.eliminarDisco(session, posicion);

            // Redirigimos a la vista del carrito
            gotoPage("/vistaCarrito.jsp", req, resp);
        }

        // Si se ha pulsado el boton de ir al inicio
        else if (req.getParameter("inicio") != null) {
            gotoPage("/index.html", req, resp);
        }

        // Si se ha pulsado el boton de ir a la caja
        else if (req.getParameter("irCaja") != null) {
            gotoPage("/vistaCaja.jsp", req, resp);
        }

        // Comprobamos si se ha pulsado el boton de confirmar pago
        else if (req.getParameter("confirmarPago") != null) {
            // Obtenemos la accion realizada (login o registrar)
            String accion = req.getParameter("accion");

            // Si se ha registrado un usuario
            if (accion.equals("registrar")) {
                // Obtenemos la informacion de pago del formulario
                String nombre = req.getParameter("nombre");
                String correo = req.getParameter("correo");
                String contrasenha = req.getParameter("contrasenha");
                String tipo = req.getParameter("tipo");
                String numero = req.getParameter("numero");

                // Registramos el usuario en la base de datos
                int exito = gestorBD.registrarUsuario(nombre, correo, contrasenha, tipo, numero);

                // Si no se registra correctamente, redirigimos a la vista de error
                if (exito == 0) {
                    session.setAttribute("nombre", nombre);
                    session.setAttribute("correo", correo);
                    gotoPage("/vistaError.jsp", req, resp);
                }

                // Si se registra correctamente, continuamos con el proceso
                else {
                    // Registramos el pedido en la base de datos
                    gestorBD.registrarPedido(nombre, helperControlador.obtenerCarrito(session).getImporteTotal());

                    // Vaciamos el carrito
                    helperControlador.vaciarCarrito(session);

                    // Obtenemos el importe total del ultimo pedido del usuario
                    Float importeTotal = gestorBD.obtenerPedido(nombre);

                    // Actualizamos la sesion con el nombre, el correo y el importe total
                    session.setAttribute("nombre", nombre);
                    session.setAttribute("correo", correo);
                    session.setAttribute("importeTotal", importeTotal);

                    // Redirigimos a la vista final
                    gotoPage("/vistaFinal.jsp", req, resp);
                }
            }

            // Si se ha iniciado sesion
            else if (accion.equals("login")) {
                // Obtenemos la informacion de pago del formulario
                String nombre = req.getParameter("nombre");
                String correo = req.getParameter("correo");
                String contrasenha = req.getParameter("contrasenha");

                // Iniciamos sesion para obtener el usuario
                Usuario usuario = gestorBD.iniciarSesion(nombre, correo, contrasenha);

                // Si no se inicia sesion correctamente, redirigimos a la vista de error
                if (usuario == null) {
                    session.setAttribute("nombre", nombre);
                    session.setAttribute("correo", correo);
                    gotoPage("/vistaError.jsp", req, resp);
                }

                // Si se inicia sesion correctamente, continuamos con el proceso
                else {
                    // Registramos el pedido en la base de datos
                    gestorBD.registrarPedido(nombre, helperControlador.obtenerCarrito(session).getImporteTotal());

                    // Vaciamos el carrito
                    helperControlador.vaciarCarrito(session);

                    // Obtenemos el importe total del ultimo pedido del usuario
                    Float importeTotal = gestorBD.obtenerPedido(nombre);

                    // Actualizamos la sesion con el nombre, el correo y el importe total
                    session.setAttribute("nombre", usuario.getNombre());
                    session.setAttribute("correo", usuario.getCorreo());
                    session.setAttribute("importeTotal", importeTotal);

                    // Redirigimos a la vista final
                    gotoPage("/vistaFinal.jsp", req, resp);
                }
            }
        }
    }

    // Metodo para cambiar la vista de la pagina (mediante el uso de RequestDispatcher)
    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    // Metodo para cerrar la conexion con la base de datos y destruir el servlet
    public void destroy() {
        gestorBD.cerrarConexion();
    }

}
