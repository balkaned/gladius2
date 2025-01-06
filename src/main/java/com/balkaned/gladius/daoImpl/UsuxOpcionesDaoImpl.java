package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.UsuxOpciones;
import com.balkaned.gladius.dao.UsuxOpcionesDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository("UsuxOpcionesDao")
public class UsuxOpcionesDaoImpl implements UsuxOpcionesDao {

    private static final String CLASS_NAME = "UsuxOpcionesDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public UsuxOpciones ObtieneAccesoOpcion(Integer codcia, Integer codusu, Integer codopc) {

        String sql = "SELECT " +
                "O.IEXURLOPC as urlopc, " +
                "O.IEXCODOPC as codopc, " +
                "O.IEXDESCRIPCION as desopc, " +
                "IEX_CONSULTAR as consultarOpc, " +
                "IEX_REGISTRAR as registrarOpc, " +
                "IEX_MODIFICAR as modificarOpc, " +
                "IEX_ELIMINAR as eliminarOpc, " +
                "IEX_DESCARGAR_PDF as descargarPdfOpc, " +
                "IEX_DESCARGAR_XLS as descargarXlsOpc, " +
                "O.IEXCODSEC as codsec, " +
                "S.IEXDESSEC as dessec, " +
                "O.IEXACTION as desaction " +
                "FROM IEXUSUXCIA C " +
                "inner join IEXROLXOPC R on C.IEXCODROL = R.IEXCODROL " +
                "inner join IEXOPCIONES O on R.IEXCODOPC = O.IEXCODOPC " +
                "inner join IEXSECCION S on O.IEXCODSEC = S.IEXCODSEC " +
                "WHERE C.IEXCODCIA = :codcia AND " +
                "C.IEXCODUSU = :codusu AND " +
                "R.IEXCODOPC = :codopc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codusu", codusu)
                .addValue("codopc", codopc);

        UsuxOpciones usuxopc = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(UsuxOpciones.class));

        return usuxopc;
    }

    public List<UsuxOpciones> listarOpciones(Integer codcia, Integer codusu, Integer codsys) {

        String sql = "SELECT " +
                "U.IEXCODCIA as codcia, " +
                "U.IEXCODUSU as codusu, " +
                "U.IEXCODROL as codrol, " +
                "R.IEXCODOPC as codopc, " +
                "O.IEXDESOPC as desopc, " +
                "O.IEXURLOPC as urlopc, " +
                "O.IEXURLIMG as urlimg, " +
                "S.IEXCODSEC as codsec, " +
                "S.IEXDESSEC as dessec, " +
                "S.IEXSECIMG as dessecimg, " +
                "S.IEXORDSEC as ordsec, " +
                "Y.IEXDESSYS as dessys, " +
                "CASE " +
                "WHEN S.IEXCODSEC = '1' THEN 'settings' " +
                "WHEN S.IEXCODSEC = '2' THEN 'grid' " +
                "WHEN S.IEXCODSEC = '3' THEN 'users' " +
                "WHEN S.IEXCODSEC = '5' THEN 'clock' " +
                "WHEN S.IEXCODSEC = '6' THEN 'sliders' " +
                "WHEN S.IEXCODSEC = '7' THEN 'layers' " +
                "WHEN S.IEXCODSEC = '11' THEN 'codesandbox' " +
                "END as icon, " +
                "iexactionspring as Path" +
                "FROM IEXUSUXCIA U " +
                "INNER JOIN IEXROLXOPC R ON U.IEXCODROL = R.IEXCODROL " +
                "INNER JOIN IEXOPCIONES O ON R.IEXCODOPC = O.IEXCODOPC " +
                "INNER JOIN IEXSECCION S  ON O.IEXCODSEC = S.IEXCODSEC " +
                "INNER JOIN IEXSYSTEMAS Y ON S.IEXCODSYS = Y.IEXCODSYS " +
                "WHERE U.IEXCODCIA = :codcia AND " +
                "U.IEXCODUSU = :codusu AND " +
                "S.IEXCODSYS = :codsys " +
                "ORDER BY s.iexordsec, R.IEXCODOPC ASC ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codusu", codusu)
                .addValue("codsys", codsys);

        List<UsuxOpciones> lsUsuxOpc = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(UsuxOpciones.class));

        return lsUsuxOpc;
    }
}
