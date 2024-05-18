package minitienda;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class ConfirmacionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Carrito cart = (Carrito) session.getAttribute("cart");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (cart != null) {
            double total = cart.calculateTotal();
            out.println("<h2>Total a pagar: $" + total + "</h2>");
            session.removeAttribute("cart");
        } else {
            out.println("<h2>El carrito está vacío</h2>");
        }
    }
}
