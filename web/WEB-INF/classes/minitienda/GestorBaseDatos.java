package minitienda;

// Importamos las librerias necesarias
import java.sql.*;
import java.util.Properties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Clase GestorBaseDatos
public class GestorBaseDatos {

    // Atributo
    private Connection conexion;

    // Constructor
    public GestorBaseDatos() {
        try {
            testDriver();
            Properties properties = new Properties();
            String url = "jdbc:postgresql://localhost:5432/minitienda";
            properties.setProperty("user", "postgres");
            properties.setProperty("password", "1234");
            conexion = DriverManager.getConnection(url, properties);
        } catch (Exception e) {
            System.out.println("Error al establecer conexion con la base de datos: " + e.getMessage());
        }
    }

    // Metodo para registrar un usuario en la base de datos
    public void registrarUsuario(String nombre, String correo, String contrasenha, String tipoTarjeta, String numeroTarjeta) {
        PreparedStatement preparedStatement;
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String contraCifrada = passwordEncoder.encode(contrasenha);
            System.out.println("Contrasenha cifrada: " + contraCifrada);
            conexion.setAutoCommit(true);
            preparedStatement = conexion.prepareStatement("INSERT INTO Usuario(nombre, correo, contrasenha, tipoTarjeta, numeroTarjeta) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, correo);
            preparedStatement.setString(3, contraCifrada);
            preparedStatement.setString(4, tipoTarjeta);
            preparedStatement.setString(5, numeroTarjeta);
            int filasInsertadas = preparedStatement.executeUpdate();
            if (filasInsertadas > 0) System.out.println("Usuario registrado correctamente.");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Error al registrar al usuario: " + e.getMessage());
        }
    }

    // Metodo para iniciar la sesion de un usuario
    public Usuario iniciarSesionUsuario(String nombre, String contrasenha) {
        Usuario usuario = null;
        PreparedStatement preparedStatement;
        ResultSet datosConsulta;
        try {
            conexion.setAutoCommit(true);
            // Obtenemos el usuario por nombre
            preparedStatement = conexion.prepareStatement("SELECT * FROM Usuario WHERE nombre = ?");
            preparedStatement.setString(1, nombre);
            datosConsulta = preparedStatement.executeQuery();
            // Si encontramos al usuario
            if (datosConsulta.next()) {
                String contrasenhaAlmacenada = datosConsulta.getString("contrasenha");
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                // Verificamos la contrasenha
                if (passwordEncoder.matches(contrasenha, contrasenhaAlmacenada)) {
                    usuario = new Usuario(datosConsulta.getString("nombre"), datosConsulta.getString("correo"));
                }
            }
            preparedStatement.close();
            datosConsulta.close();
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesion: " + e.getMessage());
        }
        return usuario;
    }

    // Metodo para registrar un pedido en la base de datos
    public void registrarPedido(String usuario, Float importeTotal) {
        PreparedStatement preparedStatement;
        try {
            conexion.setAutoCommit(true);
            preparedStatement = conexion.prepareStatement("INSERT INTO Pedido(usuario, importeTotal) VALUES (?, ?)");
            preparedStatement.setString(1, usuario);
            preparedStatement.setFloat(2, importeTotal);
            int filasInsertadas = preparedStatement.executeUpdate();
            if (filasInsertadas > 0) System.out.println("Pedido registrado correctamente.");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Error al registar el pedido: " + e.getMessage());
        }
    }

    // Metodo para obtener el ultimo pedido de un usuario
    public Pedido obtenerUltimoPedido(String usuario) {
        Pedido pedido = null;
        PreparedStatement preparedStatement;
        ResultSet datosConsulta;
        try {
            conexion.setAutoCommit(true);
            preparedStatement = conexion.prepareStatement("SELECT * FROM Pedido where usuario = ? ORDER BY id DESC LIMIT 1");
            preparedStatement.setString(1, usuario);
            datosConsulta = preparedStatement.executeQuery();
            if (datosConsulta.next()) {
                pedido = new Pedido(datosConsulta.getInt("id"), datosConsulta.getFloat("importeTotal"));
            }
            preparedStatement.close();
            datosConsulta.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener el ultimo pedido: " + e.getMessage());
        }
        return pedido;
    }

    // Metodo para cerrar la conexion con la base de datos
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion: " + e.getMessage());
        }
    }

    // Metodo auxiliar para comprobar si el driver de PostgreSQL esta instalado
    protected void testDriver() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Encontrado el driver de PostgreSQL.");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver no encontrado ... ");
            throw(e);
        }
    }

}
