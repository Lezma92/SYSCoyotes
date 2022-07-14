package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author misael
 */
public class conexion {

    String bd = "ebajitos";
    private String url = "jdbc:mysql://localhost/" + bd;
    private String user = "root";
    private String pass = "";
    private Connection con = null;

    private void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            //System.out.println("logica.conexion.conectar(): "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Error en ->:  "+e.getMessage());
        }
    }

    public Connection getConexion() {
        this.conectar();
        return con;
    }
    public static void main(String[] args) {
        conexion c = new conexion();
        Connection x = c.getConexion();
        if (x != null) {
           System.out.println("bien");
        }
    }

}
