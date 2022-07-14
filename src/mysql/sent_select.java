package mysql;

/**
 *
 * @author misael
 */
public class sent_select {

    final String SELECT_AREAS = "SELECT * FROM area_trabajo;";

    final String SELECT_PUESTOS = "SELECT categoria, precioxDia FROM puestos_nvo AS p, area_trabajo AS a WHERE"
            + " p.id_area_trabajo = a.id AND a.area = ?;";

    final String SELECT_TRABAJADORES = "SELECT t.id, art.area, p_nvo.categoria, t.nombre, t.apellidos, t.numTel, "
            + "t.fecha_regis FROM trabajadores AS t INNER JOIN puestos_nvo AS p_nvo ON p_nvo.id = t.id_puestos_nvo INNER JOIN "
            + "area_trabajo AS art ON art.id = p_nvo.id_area_trabajo; ";

    final String SELECT_PASAR_ASISTENCIA = "SELECT  t.id, p_nvo.categoria, t.nombre, t.apellidos, "
            + "IFNULL(asis.asistencia, 'A') AS asistencia, IF(asis.fecha,'Capturada', 'Sin capturar') AS estado "
            + "FROM trabajadores AS t INNER JOIN puestos_nvo AS p_nvo ON p_nvo.id = t.id_puestos_nvo INNER JOIN area_trabajo AS "
            + "art ON art.id = p_nvo.id_area_trabajo LEFT JOIN asistencias AS asis ON asis.id_trabajador = t.id "
            + "AND asis.fecha = ? WHERE art.area = ?;";

    final String SELECT_VALIDAR_ASISTENCIA = "SELECT COUNT(*) AS filas FROM asistencias WHERE fecha = ?;";
}
