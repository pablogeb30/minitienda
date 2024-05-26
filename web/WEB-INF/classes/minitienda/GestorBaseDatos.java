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
    public int registrarUsuario(String nombre, String correo, String contrasenha, String tipoTarjeta, String numeroTarjeta) {
        int filasInsertadas = 0;
        PreparedStatement preparedStatement;
        try {
            // Ciframos la contrasenha antes de guardarla en la base de datos
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String contraCifrada = passwordEncoder.encode(contrasenha);
            // Insertamos el usuario en la base de datos
            conexion.setAutoCommit(true);
            preparedStatement = conexion.prepareStatement("INSERT INTO Usuario(nombre, correo, contrasenha, tipoTarjeta, numeroTarjeta) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, correo);
            preparedStatement.setString(3, contraCifrada);
            preparedStatement.setString(4, tipoTarjeta);
            preparedStatement.setString(5, numeroTarjeta);
            filasInsertadas = preparedStatement.executeUpdate();
            if (filasInsertadas > 0) System.out.println("Usuario registrado correctamente.");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
        return filasInsertadas;
    }

    // Metodo para iniciar sesion (obteniendo un usuario de la base de datos)
    public Usuario iniciarSesion(String nombre, String correo, String contrasenha) {
        Usuario usuario = null;
        PreparedStatement preparedStatement;
        ResultSet datosConsulta;
        try {
            conexion.setAutoCommit(true);
            // Obtenemos el usuario mediante el nombre y el correo
            preparedStatement = conexion.prepareStatement("SELECT * FROM Usuario WHERE nombre = ? AND correo = ?");
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, correo);
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
            System.out.println("Error al registar pedido: " + e.getMessage());
        }
    }

    // Metodo para obtener el importe total del ultimo pedido de un usuario
    public Float obtenerPedido(String usuario) {
        float importeTotal = 0;
        PreparedStatement preparedStatement;
        ResultSet datosConsulta;
        try {
            conexion.setAutoCommit(true);
            preparedStatement = conexion.prepareStatement("SELECT importeTotal FROM Pedido where usuario = ? ORDER BY id DESC LIMIT 1");
            preparedStatement.setString(1, usuario);
            datosConsulta = preparedStatement.executeQuery();
            if (datosConsulta.next()) {
                importeTotal = datosConsulta.getFloat("importeTotal");
            }
            preparedStatement.close();
            datosConsulta.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener el importe del ultimo pedido: " + e.getMessage());
        }
        return importeTotal;
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
            System.out.println("PostgreSQL JDBC Driver no encontrado.");
            throw(e);
        }
    }

}
