package minitienda;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class CarritoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Carrito cart = (Carrito) session.getAttribute("cart");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (cart != null) {
            out.println("<h2>Carrito de la compra</h2>");
            out.println("<ul>");
            for (String item : cart.getItems().keySet()) {
                out.println("<li>" + item + " - Cantidad: " + cart.getItems().get(item) + "</li>");
            }
            out.println("</ul>");
            out.println("<a href='checkout'>Proceder al pago</a>");
        } else {
            out.println("<h2>El carrito está vacío</h2>");
        }
    }
}
