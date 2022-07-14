package mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.conexion;

/**
 *
 * @author misael
 */
public class consultas extends sent_select {

    private final conexion x = new conexion();
    private final Connection con = x.getConexion();
    private PreparedStatement ppt;
    private ResultSet rs;
    private DefaultTableModel modelo;

    public String[] getTiempo(String fecha) {
        String datos[] = new String[1];
        try {
            ppt = con.prepareCall("SELECT WEEKOFYEAR(?) as semana;");
            ppt.setString(1, fecha);
            rs = ppt.executeQuery();
            while (rs.next()) {
                datos[0] = rs.getString("semana");
                System.out.println(datos[0]);
            }
        } catch (SQLException e) {
        }
        return datos;
    }

    public String[] getAreas() {
        String areas[] = new String[3];
        int cont = 0;
        try {
            ppt = con.prepareCall(SELECT_AREAS);
            rs = ppt.executeQuery();
            while (rs.next()) {
                areas[cont] = rs.getString("area");
                System.out.println("Dato -> " + areas[cont]);
                cont++;
            }
            System.out.println("Tam- > " + areas.length);
            for (int i = 0; i < areas.length; i++) {
                System.out.println("Misa -> " + areas[i]);
            }

        } catch (SQLException e) {
            System.out.println("mysql.consultas.getAreas(). " + e.getMessage());
        }

        return areas;
    }

    public ArrayList<String> getPuestos(String area) {
        ArrayList<String> puestos = new ArrayList<String>();
        try {
            ppt = con.prepareCall(SELECT_PUESTOS);
            ppt.setString(1, area);
            rs = ppt.executeQuery();
            while (rs.next()) {
                puestos.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("mysql.consultas.getPuestos(). " + e.getMessage());
        }
        return puestos;
    }

    public DefaultTableModel getTrabajadores() {
        String titulos[] = {"IdT", "Área", "Cargo", "Nombre(s)", "Apellidos", "Núm. Telefono", "Fecha Registro"};
        modelo = new DefaultTableModel(null, titulos);
        String filas[] = new String[7];
        int cont = 1;
        try {
            ppt = con.prepareCall(SELECT_TRABAJADORES);
            rs = ppt.executeQuery();
            while (rs.next()) {
                filas[0] = String.valueOf(rs.getInt(1));
                filas[1] = rs.getString(2);
                filas[2] = rs.getString(3);
                filas[3] = rs.getString(4);
                filas[4] = rs.getString(5);
                filas[5] = rs.getString(6);
                filas[6] = rs.getString(7);
                modelo.addRow(filas);
                cont++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:mysql.consultas.getTrabajadores()" + e.getMessage());
        }
        return modelo;
    }

    public DefaultTableModel asistencia(String area, String fecha) {
        String titulos[] = {"IdT", "Cargo", "Nombre(s)", "Apellidos", "Status Asistencia", "Asistencia"};
        modelo = new DefaultTableModel(null, titulos);
        String filas[] = new String[6];
        try {
            ppt = con.prepareCall(SELECT_PASAR_ASISTENCIA);
            ppt.setString(1, fecha);
            ppt.setString(2, area);
            rs = ppt.executeQuery();
            while (rs.next()) {
                filas[0] = String.valueOf(rs.getInt(1));
                filas[1] = rs.getString(2);
                filas[2] = rs.getString(3);
                filas[3] = rs.getString(4);
                filas[4] = rs.getString("estado");
                filas[5] = rs.getString("asistencia");
                modelo.addRow(filas);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:mysql.consultas.getTrabajadores()" + e.getMessage());
        }
        return modelo;
    }

    public int getValidarAsistencia(String param_fecha) {
        int r = 0;
        try {
            ppt = con.prepareCall(SELECT_VALIDAR_ASISTENCIA);
            ppt.setString(1, param_fecha);
            rs = ppt.executeQuery();
            
            while (rs.next()) {
                r = rs.getInt("filas");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}
