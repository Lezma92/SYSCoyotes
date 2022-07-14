package mysql;

public class sent_insert {

    String INSERT_TRABAJADORES = "INSERT INTO trabajadores VALUES(null,(SELECT id FROM puestos_nvo WHERE categoria = ?),?,?,?,CURDATE(),'A')";

    String INSERT_ASISTENCIA = "INSERT INTO asistencias VALUES(null,?,?,CURDATE());";

}
