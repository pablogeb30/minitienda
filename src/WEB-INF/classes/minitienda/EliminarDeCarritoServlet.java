package minitienda;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EliminarDeCarritoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Carrito cart = (Carrito) session.getAttribute("cart");

        if (cart != null) {
            String cd = request.getParameter("cd");
            cart.removeItem(cd);
        }

        response.sendRedirect("cart");
    }
}
