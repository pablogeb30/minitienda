package desplieguetomcat2bd;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class login extends HttpServlet {

	private ListaUsuarios lista;
	private Connection conn;
	private String dbURL = "jdbc:postgresql://localhost:5432/tomcat";
	private String dbUser = "postgres";
	private String dbPassword = "1234";

	// Inicializacion del servlet y de la conexion a la base de datos
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// If it is a get request forward to doPost()
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtenemos el nombre de usuario a partir de la petición
		String username = request.getParameter("username");
		// Obtenemos la contraseña a partir de la petición
		String password = request.getParameter("password");

		// Insertamos el usuario en la base de datos
		try {
			// Creamos la consulta para insertar el usuario en la base de datos
			String sql = "INSERT INTO usuarios (nombre, password) VALUES (?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);

			// Ejecutamos la consulta
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Usuario insertado correctamente.");
			}

			statement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		// Creamos un usuario nuevo y le pasamos los datos
		Usuario usuario = new Usuario();
		usuario.setNombre(username);
		usuario.setPassword(password);

		// Creamos una nueva sesion
		HttpSession session = request.getSession(true);

		// Recuperamos lista de la sesion
		// Si es la primera vez, creamos la lista
		lista = (ListaUsuarios) session.getAttribute("ListaUsuarios");

		if (lista == null) {
			// Creamos la lista
			lista = new ListaUsuarios();
		}

		// Introducimos el usuario en la lista
		lista.setUsuario(usuario);

		// Guardamos la lista en la sesion
		session.setAttribute("ListaUsuarios", lista);

		// Presentamos los datos
		gotoPage("/vistaUsuarioJSTL2.jsp", request, response);
	}

	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Creamos objeto RequestDispatcher
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	// Cerramos la conexion a la base de datos al destruir el servlet
	public void destroy() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
