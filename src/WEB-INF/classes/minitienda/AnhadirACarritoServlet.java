package minitienda;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AnhadirACarritoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Carrito cart = (Carrito) session.getAttribute("cart");

        if (cart == null) {
            cart = new Carrito();
            session.setAttribute("cart", cart);
        }

        String cd = request.getParameter("cd");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        cart.addItem(cd, quantity);

        response.sendRedirect("cart");
    }
}
