package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author misael
 */
public class sesion {

    private conexion c = new conexion();
    private Connection con = c.getConexion();
    private PreparedStatement ppt;
    private ResultSet rs;

    public int iniciar_sesion(String user, String pass) {
        if (!user.equals("") && !pass.equals("")) {
            String st_sql = "SELECT * FROM usuarios WHERE user = ? and pass = ?;";
            String userbd = "", passbd = "";
            try {
                ppt = con.prepareCall(st_sql);
                ppt.setString(1, user);
                ppt.setString(2, pass);
                rs = ppt.executeQuery();
                while (rs.next()) {
                    userbd = rs.getString("user");
                    passbd = rs.getString("pass");
                }
                if (userbd.equals(user) && passbd.equals(passbd)) {
                    return 1;
                }else{
                    return 2;
                }
            } catch (SQLException e) {
                System.out.println("logica.sesion.iniciar_sesion()= " + e.getMessage());
                JOptionPane.showMessageDialog(null, "!Error inesperado¡, "+e.getMessage());
            }
        }else{
            return 3;
        }

        return 0;
    }

    private int validarUser() {
        return 0;
    }
}
