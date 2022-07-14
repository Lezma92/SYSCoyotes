package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mysql.sent_insert;

/**
 *
 * @author misael
 */
public class insert extends sent_insert{

    private final conexion c = new conexion();
    private final Connection cpn = c.getConexion();
    private PreparedStatement ppt;
    private ResultSet rs;
    
    public String insertUsuarios(String datos[]){
        
        return "Mal";
    }
    
}
