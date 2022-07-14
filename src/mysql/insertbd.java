package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import logica.conexion;

/**
 *
 * @author misael
 */
public class insertbd extends sent_insert {

    private final conexion x = new conexion();
    private final Connection con = x.getConexion();
    private PreparedStatement ppt;
    private ResultSet rs;

    public int insertTrabajador(String datos[]) {
        int r = 0;
        try {
            ppt = con.prepareCall(INSERT_TRABAJADORES);
            ppt.setString(1, datos[0]);
            ppt.setString(2, datos[1]);
            ppt.setString(3, datos[2]);
            ppt.setString(4, datos[3]);
            int x = ppt.executeUpdate();
            if (x > 0) {
                r = 200;
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println("mysql.insertbd.insertTrabajador(). " + e.getMessage());
        }
        return r;
    }

    public boolean insertAsistencia(JTable tabla) {
        int t = tabla.getRowCount();
        int cont = 0;
        for (int i = 0; i < t; i++) {
            try {
                ppt = con.prepareCall(INSERT_ASISTENCIA);
                ppt.setInt(1, Integer.parseInt(String.valueOf(tabla.getValueAt(i, 0))));
                ppt.setString(2, String.valueOf(tabla.getValueAt(i, 5)));
                int respuesta = ppt.executeUpdate();
                if (respuesta > 0) {
                    cont++;
                }
            } catch (Exception e) {
                System.out.println("mysql.insertbd.insertAsistencia(). " + e.getMessage());
                e.printStackTrace();
            }
        }
        if (cont == t) {
            return true;
        }
        return false;
    }

}
