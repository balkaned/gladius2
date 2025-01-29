package com.balkaned.gladius.daoImpl;


import com.balkaned.gladius.models.*;
import com.balkaned.gladius.dao.TurnoDiarioDao;
import com.balkaned.gladius.util.FormatterFecha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Repository("TurnoDiarioDao")
public class TurnoDiarioDaoImpl implements TurnoDiarioDao {

    private static final String CLASS_NAME = "TurnoDiarioDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Turno> listarTurnosModalAsis(Integer codcia, String fecini) {

        String sql = "select " +
                "t.iexcodcia, " +
                "t.iexcodturno, " +
                "t.iexdesturno, " +
                "t.iexhorini, " +
                "t.iexhorfin, " +
                "t.iexflgdiasig, " +
                "t.iextopminantes, " +
                "t.iextopmaxpost, " +
                "iexflgturno, " +
                "t.iexdesusu, " +
                "t.iexfeccrea " +
                "from iexturno t " +
                "where iexcodcia = " + codcia + " ";

        return jdbc.query(sql, new ResultSetExtractor<List<Turno>>() {
            public List<Turno> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Turno> lista = new ArrayList<Turno>();

                while (rs.next()) {
                    Turno p = new Turno();

                    p.setCodcia(rs.getInt("iexcodcia"));
                    p.setIexcodturno(rs.getInt("iexcodturno"));
                    p.setIexflgturno(rs.getString("iexflgturno"));
                    p.setIexdesturno(rs.getString("iexdesturno"));
                    p.setIexhorini(rs.getString("iexhorini"));
                    p.setIexhorfin(rs.getString("iexhorfin"));
                    p.setIexflgdiasig(rs.getString("iexflgdiasig"));
                    p.setIextopminantes(rs.getDouble("iextopminantes"));
                    p.setIextopmaxpost(rs.getDouble("iextopmaxpost"));

                    p.setIexdesusu(rs.getString("iexdesusu"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));

                    FormatterFecha fec = new FormatterFecha();
                    String mes = fec.fechaFormatterMes2(fecini);
                    p.setMesDes(mes);

                    FormatterFecha fec2 = new FormatterFecha();
                    String anio = fec2.fechaFormatterAnio2(fecini);
                    p.setAnioDes(anio);

                    lista.add(p);
                }

                return lista;
            }
        });
    }

    public List<Turno> listarTurnos(Integer codcia) {

        String sql = "select " +
                "t.iexcodcia as codcia, " +
                "t.iexcodturno, " +
                "t.iexdesturno, " +
                "t.iexhorini, " +
                "t.iexhorfin, " +
                "t.iexflgdiasig, " +
                "t.iextopminantes, " +
                "t.iextopmaxpost, " +
                "iexflgturno, " +
                "t.iexdesusu, " +
                "t.iexfeccrea " +
                "from iexturno t " +
                "where iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<Turno> lsTurno = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Turno.class));

        return lsTurno;
    }

    public List<Turnodiario> listarTurnoDia(Integer codcia, Integer codtra, String fecini, String fecfin) {

        String sql = "select " +
                "t.iexcodcia, " +
                "t.iexcodtra, " +
                "t.iexcodfec, " +
                "t.iexfecdia, " +
                "to_char(t.iexfecdia,'dd/mm/yyyy') desfecdia, " +
                "to_char(t.iexfecdia,'dd/mm/yyyy') desfecdia2, " +
                "t.iexcodturno, " +
                "e.iexflgturno, " +
                "e.iexdesturno as desturno, " +
                "t.iexiniturno, " +
                "t.iexfinturno, " +
                "t.iexiniasist, " +
                "t.iexfinasist, " +
                "to_char(t.iexiniturno,'HH24:MI') desiniturno, " +
                "to_char(t.iexfinturno,'HH24:MI') desfinturno, " +
                "to_char(t.iexiniasist,'HH24:MI') desiniasist, " +
                "to_char(t.iexfinasist,'HH24:MI') desfinasist, " +
                "t.iexhrstotal, " +
                "t.iexmintotal, " +
                "t.iexminantes, " +
                "t.iexminpost, " +
                "t.iexhrspost, " +
                "t.iexcoddiasem, " +
                "t.iexfeccrea, " +
                "t.iexdesusu, " +
                "t.iexflgest, " +
                "t.iexhrsantes, " +
                "t.iexhrssale_antes, " +
                "t.iexminsale_antes, " +
                "t.iexhrstarde, " +
                "t.iexmintarde, " +
                "t.iexausenid, " +
                "t.iexpermiso, " +
                "t.iexvacaind, " +
                "t.iexindferiado, " +
                "t.iexindfalta " +
                "from iexturnodia t, iexturno e " +
                "where t.iexcodcia = e.iexcodcia and " +
                "t.iexcodturno = e.iexcodturno and " +
                "t.iexcodcia = :codcia and " +
                "t.iexcodtra = :codtra and " +
                "t.iexfecdia >= to_date(:fecini,'dd/mm/yyyy') and " +
                "t.iexfecdia <= to_date(:fecfin,'dd/mm/yyyy') " +
                "order by iexcodfec asc ";


        FormatterFecha fec1 = new FormatterFecha();
        String fechaIniFormat = fec1.fechaFormatterIngltoEsp2(fecini);

        FormatterFecha fec2 = new FormatterFecha();
        String fechaFinFormat = fec2.fechaFormatterIngltoEsp2(fecfin);

        SqlParameterSource namedParameteres = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra)
                .addValue("fecini", fechaIniFormat)
                .addValue("fecfin", fechaFinFormat);

        List<Turnodiario> lsTurno = namedParameterJdbcTemplate.query(sql, namedParameteres,
                BeanPropertyRowMapper.newInstance(Turnodiario.class));

        return lsTurno;
    }

    public List<Empleado> listarTurMasTra(Integer codcia, String fecini, String fecfin) {

        String sql = "select " +
                "e.iexcodcia, " +
                "e.iexcodtra, " +
                "e.iexapepat, " +
                "e.iexapemat, " +
                "e.iexnomtra, " +
                "e.iexfecing, " +
                "e.iexfecret " +
                "from iexempleado e " +
                "where e.iexcodcia = :codcia and " +
                "e.iexflgest = '1' " +
                "group by e.iexcodcia, e.iexcodtra, " +
                "e.iexapepat, e.iexapemat, e.iexnomtra, e.iexfecing, " +
                "e.iexfecret order by " +
                "e.iexapepat, e.iexapemat, e.iexnomtra asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<Empleado> lsEmpleado = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return lsEmpleado;
    }


    public void insertarTurno(Turno turno) {

        String sql = "insert into iexturno( " +
                "iexcodcia, iexcodturno, iexflgturno, iexdesturno, iexhorini, " +
                "iexhorfin, iexflgdiasig, iextopminantes, iextopmaxpost " +
                " ) values ( " +
                " ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";

        jdbc.update(sql,
                turno.getCodcia(),
                turno.getIexcodturno(),
                turno.getIexflgturno(),
                turno.getIexdesturno(),
                turno.getIexhorini(),
                turno.getIexhorfin(),
                turno.getIexflgdiasig(),
                turno.getIextopminantes(),
                turno.getIextopmaxpost()
        );
    }


    public Turno getTurno(Integer codcia, Integer codTurn) {

        String sql = "select " +
                "t.iexcodcia as codcia, " +
                "t.iexcodturno, " +
                "t.iexdesturno, " +
                "t.iexflgturno, " +
                "t.iexhorini, " +
                "t.iexhorfin, " +
                "t.iexflgdiasig, " +
                "t.iextopminantes, " +
                "t.iextopmaxpost, " +
                "t.iexdesusu, " +
                "t.iexfeccrea " +
                "from iexturno t " +
                "where iexcodcia = :codcia and " +
                "t.iexcodturno = :codTurn ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codTurn", codTurn);

        Turno turno = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Turno.class));

        return turno;
    }

    public void actualizarTurno(Turno turno) {

        String sql = "update iexturno set " +
                "iexflgturno =?, iexdesturno =?, iexhorini =?, iexhorfin =?, " +
                "iexflgdiasig =?, iextopminantes =?, iextopmaxpost =?, " +
                "iexdesusumod =?, iexfecmod = current_timestamp " +
                "where iexcodcia=? and iexcodturno =? ";

        jdbc.update(sql,
                turno.getIexflgturno(),
                turno.getIexdesturno(),
                turno.getIexhorini(),
                turno.getIexhorfin(),
                turno.getIexflgdiasig(),
                turno.getIextopminantes(),
                turno.getIextopmaxpost(),
                turno.getIexdesusu(),
                turno.getCodcia(),
                turno.getIexcodturno()
        );
    }

    public void eliminarTurno(Turno turno) {

        String sql = "delete from iexturno " +
                "where iexcodcia=? and " +
                "iexcodturno =? ";

        jdbc.update(sql,
                turno.getCodcia(),
                turno.getIexcodturno()
        );
    }

    public Turnodiario obtenerTurnoDia(Integer codcia, Integer codtra, String codfec) {

        String sql = "select " +
                "t.iexcodcia, " +
                "t.iexcodtra, " +
                "t.iexcodfec, " +
                "t.iexfecdia, " +
                "to_char(t.iexfecdia,'dd/mm/yyyy') desfecdia, " +
                "to_char(t.iexfecdia,'dd/mm/yyyy') desfecdia2, " +
                "t.iexcodturno, " +
                "e.iexflgturno, " +
                "e.iexdesturno as desturno, " +
                "t.iexiniturno, " +
                "t.iexfinturno, " +
                "t.iexiniasist, " +
                "t.iexfinasist, " +
                "to_char(t.iexiniturno,'HH24:MI') desiniturno, " +
                "to_char(t.iexfinturno,'HH24:MI') desfinturno, " +
                "to_char(t.iexiniasist,'HH24:MI') desiniasist, " +
                "to_char(t.iexfinasist,'HH24:MI') desfinasist, " +
                "t.iexhrstotal, " +
                "t.iexmintotal, " +
                "t.iexminantes, " +
                "t.iexminpost, " +
                "t.iexhrspost, " +
                "t.iexcoddiasem, " +
                "t.iexfeccrea, " +
                "t.iexdesusu, " +
                "t.iexflgest, " +
                "t.iexhrsantes, " +
                "t.iexhrssale_antes, " +
                "t.iexminsale_antes, " +
                "t.iexhrstarde, " +
                "t.iexmintarde, " +
                "t.iexvacaind, " +
                "t.iexausenid, " +
                "t.iexpermiso, " +
                "to_char(t.iexhriniperm,'HH24:MI') iexhriniperm, " +
                "to_char(t.iexhrfinperm,'HH24:MI') iexhrfinperm, " +
                "t.iexhrsperm, " +
                "t.iexminsperm, " +
                "t.iexindferiado, " +
                "t.iexindfalta " +
                "from iexturnodia t, iexturno e " +
                "where t.iexcodcia = e.iexcodcia and " +
                "t.iexcodturno = e.iexcodturno and " +
                "t.iexcodcia = :codcia and " +
                "t.iexcodtra = :codtra and " +
                "t.iexcodfec = :codfec ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra)
                .addValue("codfec", codfec);

        Turnodiario turno = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Turnodiario.class));

        return turno;
    }

    public void actualizaTurnoDia(Integer codcia, Integer codtra, Integer codturno, String fecdia, String desusu) {

        String sql = "call pl_actualiza_turno(? ,to_date(?,'dd/mm/yyyy'),?, ?,?) ";

        jdbc.update(sql,
                codcia,
                fecdia,
                codtra,
                codturno,
                desusu
        );
    }

    /*public void calificarTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu) {

        String sql = " call pl_califica_asistencia(?,to_date(?,'dd/mm/yyyy'),?,?) ";

        log.info("codcia: {} ", codcia);
        log.info("fecdia: {} ", fecdia);
        log.info("codtra: {} ", codtra);
        log.info("desusu: {} ", desusu);

        FormatterFecha fec = new FormatterFecha();
        String fecdiaFormat = fec.fechaFormatterEspToIngl(fecdia);

        log.info("fecdiaFormat: {} ", fecdiaFormat);

        jdbc.update(sql,
                codcia,
                fecdia,
                codtra,
                desusu
        );
    }*/

    public void calificarTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu) {

        log.info("codcia: {} ", codcia);
        log.info("fecdia: {} ", fecdia);
        log.info("codtra: {} ", codtra);
        log.info("desusu: {} ", desusu);

        jdbc.update(" call pl_califica_asistencia(?,to_date(?,'dd/mm/yyyy'),?,?) ",
                codcia,
                fecdia,
                codtra,
                desusu);

        log.info("Actualización Completada...");
    }

    public void programarTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu) {

        String sql = "call pl_programa_turno(?,to_date(?,'dd/mm/yyyy'),?,?) ";

        jdbc.update(sql,
                codcia,
                fecdia,
                codtra,
                desusu
        );
    }

    public void marcacionesTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu) {

        String sql = "call pl_procesa_asistencia(?,to_date(?,'dd/mm/yyyy'),?,?) ";

        jdbc.update(sql,
                codcia,
                fecdia,
                codtra,
                desusu
        );
    }

    public void eliminaTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu) {

        String sql = "delete from iexturnodia " +
                "where iexcodcia=? and " +
                "iexcodtra=? and " +
                "iexcodfec = to_char(to_date(?,'dd/mm/yyyy'),'yyyymmdd') ";

        jdbc.update(sql,
                codcia,
                codtra,
                fecdia
        );

        String sql2 = "delete from iexturno_marks " +
                "where iexcodcia=? and " +
                "iexcodtra=? and " +
                "iexcodfecha = to_char(to_date(?,'dd/mm/yyyy'), 'yyyymmdd') ";

        jdbc.update(sql2,
                codcia,
                codtra,
                fecdia
        );
    }

    public void consolidaAsistencia(Integer codcia, Integer codpro, Integer codtra, String nroper, Integer correl, String desusu) {

        String sql = "call pl_exe_cons_tiempos(?,?,?,?,?,?) ";

        jdbc.update(sql,
                codcia,
                codpro,
                nroper,
                codtra,
                correl,
                desusu
        );
    }

    public List<TurnoMarks> obtenerTurnoDiaMarks(Integer codcia, Integer codtra, String codfec) {

        String sql = "select iexcodcia, " +
                "iexcodtra, " +
                "iexcodfecha, " +
                "to_char(iexfechamarks,'dd/mm/yyyy hh24:mi:ss') as iexfechamarks, " +
                "iexflgmanual " +
                "from iexturno_marks " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra and " +
                "iexcodfecha = :codfec ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra)
                .addValue("codfec", codfec);

        List<TurnoMarks> lsTurnoM = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(TurnoMarks.class));

        return lsTurnoM;
    }

    public void insertaMarkDia(Integer codcia, Integer codtra, String codfec, String fechora, String desusu) {

        String sql = "insert into iexmarkas_manual (iexcodcia, iexcodtra, iexcodfecha, iexfechamarks, " +
                "iexdesusu, iexfeccrea ) values(?,?,?,to_timestamp(?,'dd/mm/yyyy hh24:mi'), ?, CURRENT_TIMESTAMP) ";

        jdbc.update(sql,
                codcia,
                codtra,
                codfec,
                fechora,
                desusu
        );
    }

    public List<MarkaManual> obtenerMarksMDia(Integer codcia, Integer codtra, String codfec) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexcodfecha as iexcodfec, " +
                "to_char(iexfechamarks ,'dd/mm/yyyy hh24:mi:ss') iexfecmarkas, " +
                "iexdesusu, " +
                "iexfeccrea " +
                "from iexmarkas_manual " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra and " +
                "iexcodfecha = :codfec " +
                "order by iexfechamarks asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra)
                .addValue("codfec", codfec);

        List<MarkaManual> lsMarkmanual = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(MarkaManual.class));

        return lsMarkmanual;
    }

    public void automarkTurnoDia(Integer codcia, Integer codtra, Integer codturno, String fecdia, String desusu) {

        String sql = "call pl_automark(? ,to_date(?,'dd/mm/yyyy'),?, ?,?) ";

        jdbc.update(sql,
                codcia,
                fecdia,
                codtra,
                codturno,
                desusu
        );
    }

    public void deleteMarkDia(Integer codcia, Integer codtra, String codfec, String fechora) {

        String sql = "delete from iexmarkas_manual " +
                "where iexcodcia=? " +
                "and iexcodtra=? and " +
                "iexcodfecha=? and " +
                "iexfechamarks = to_timestamp(?,'dd/mm/yyyy hh24:mi:ss') ";

        jdbc.update(sql,
                codcia,
                codtra,
                codfec,
                fechora
        );
    }

    public void actualizaTurnoDiaCol(Integer codcia, Integer codtra, Integer codturno, String fecini, String fecfin, Integer diasem, String desusu) {

        String sql = "call pl_programa_mas_turno_col(? ,?, ?, to_date(?,'dd/mm/yyyy'), " +
                "to_date(?,'dd/mm/yyyy'),?, ?) ";

        jdbc.update(sql,
                codcia,
                codtra,
                codturno,
                fecini,
                fecfin,
                diasem,
                desusu
        );
    }
}
