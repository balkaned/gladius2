package com.balkaned.gladius.daoImpl;


import com.balkaned.gladius.models.*;
import com.balkaned.gladius.dao.TurnoDiarioDao;
import com.balkaned.gladius.util.CapitalizarCadena;
import com.balkaned.gladius.util.FormatterFecha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("TurnoDiarioDao")
@Slf4j
public class TurnoDiarioDaoImpl implements TurnoDiarioDao {


    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<Turno> listarTurnosModalAsis(Integer codcia, String fecini) {

        String sql = " select   " +
                "t.iexcodcia," +
                " t.iexcodturno," +
                " t.iexdesturno," +
                " t.iexhorini, " +
                "t.iexhorfin,  " +
                "t.iexflgdiasig," +
                " t.iextopminantes," +
                " t.iextopmaxpost,  " +
                "iexflgturno,  " +
                "t.iexdesusu, " +
                "t.iexfeccrea " +
                "from iexturno t   " +
                "where iexcodcia=" + codcia + "   ";

        return template.query(sql, new ResultSetExtractor<List<Turno>>() {
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
                    /*p.setLunes(rs.getString("lunes"));
                    p.setMartes(rs.getString("martes"));
                    p.setMiercoles(rs.getString("miercoles"));
                    p.setJueves(rs.getString("jueves"));
                    p.setViernes(rs.getString("viernes"));
                    p.setSabado(rs.getString("sabado"));
                    p.setDomingo(rs.getString("domingo")); */
                    p.setIexdesusu(rs.getString("iexdesusu"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));

                    log.info("fecini: " + fecini);
                    Date fecha = new Date(fecini);
                    log.info("fecha: " + fecha);

                    FormatterFecha fec = new FormatterFecha();
                    String mes = fec.fechaFormatterMes(fecini);
                    p.setMesDes(mes);

                    FormatterFecha fec2 = new FormatterFecha();
                    String anio = fec2.fechaFormatterAnio(fecini);
                    p.setAnioDes(anio);

                    log.info("mes: " + mes);
                    log.info("anio: " + anio);

                    lista.add(p);
                }

                return lista;
            }
        });
    }

    public List<Turno> listarTurnos(Integer codcia) {

        String sql = " select   " +
                "t.iexcodcia," +
                " t.iexcodturno," +
                " t.iexdesturno," +
                " t.iexhorini, " +
                "t.iexhorfin,  " +
                "t.iexflgdiasig," +
                " t.iextopminantes," +
                " t.iextopmaxpost,  " +
                "iexflgturno,  " +
                "t.iexdesusu, " +
                "t.iexfeccrea " +
                "from iexturno t   " +
                "where iexcodcia=" + codcia + "   ";

        return template.query(sql, new ResultSetExtractor<List<Turno>>() {
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
                    /*p.setLunes(rs.getString("lunes"));
                    p.setMartes(rs.getString("martes"));
                    p.setMiercoles(rs.getString("miercoles"));
                    p.setJueves(rs.getString("jueves"));
                    p.setViernes(rs.getString("viernes"));
                    p.setSabado(rs.getString("sabado"));
                    p.setDomingo(rs.getString("domingo")); */
                    p.setIexdesusu(rs.getString("iexdesusu"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Turnodiario> listarTurnoDia(Integer codcia, Integer codtra, String fecini, String fecfin) {

        String sql = " select	" +
                "    t.iexcodcia ," +
                "    t.iexcodtra ," +
                "    t.iexcodfec ," +
                "    t.iexfecdia ," +
                "    to_char(t.iexfecdia,'dd/mm/yyyy') desfecdia ," +
                "    t.iexcodturno ," +
                "    e.iexflgturno, " +
                "    e.iexdesturno," +
                "    t.iexiniturno ," +
                "    t.iexfinturno ," +
                "    t.iexiniasist ," +
                "    t.iexfinasist ," +
                "    to_char(t.iexiniturno,'HH24:MI') desiniturno ," +
                "    to_char(t.iexfinturno,'HH24:MI') desfinturno ," +
                "    to_char(t.iexiniasist,'HH24:MI') desiniasist ," +
                "    to_char(t.iexfinasist,'HH24:MI') desfinasist ," +
                "    t.iexhrstotal ," +
                "    t.iexmintotal ," +
                "    t.iexminantes ," +
                "    t.iexminpost ," +
                "    t.iexhrspost ," +
                "    t.iexcoddiasem ," +
                "    t.iexfeccrea ," +
                "    t.iexdesusu ," +
                "    t.iexflgest ," +
                "    t.iexhrsantes ," +
                "    t.iexhrssale_antes ," +
                "    t.iexminsale_antes ," +
                "    t.iexhrstarde ," +
                "    t.iexmintarde ,  t.iexausenid, t.iexpermiso, t.iexvacaind, t.iexindferiado,  t.iexindfalta  " +
                "	from iexturnodia t , iexturno e " +
                "	where " +
                "	t.iexcodcia = e.iexcodcia and " +
                "    t.iexcodturno = e.iexcodturno and " +
                "    t.iexcodcia=" + codcia + "  and " +
                "	t.iexcodtra=" + codtra + " and " +
                "	t.iexfecdia >= to_date('" + fecini + "','dd/mm/yyyy') and t.iexfecdia <= to_date('" + fecfin + "','dd/mm/yyyy') order by iexcodfec asc  ";

        return template.query(sql, new ResultSetExtractor<List<Turnodiario>>() {
            public List<Turnodiario> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Turnodiario> lista = new ArrayList<Turnodiario>();

                while (rs.next()) {
                    Turnodiario p = new Turnodiario();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcodfec(rs.getString("iexcodfec"));
                    p.setIexfecdia(rs.getString("iexfecdia"));
                    p.setIexcodturno(rs.getInt("iexcodturno"));
                    p.setDesturno(rs.getString("iexdesturno"));
                    p.setIexflgturno(rs.getString("iexflgturno"));
                    p.setIexiniturno(rs.getString("iexiniturno"));
                    p.setIexfinturno(rs.getString("iexfinturno"));
                    p.setIexiniasist(rs.getString("iexiniasist"));
                    p.setIexfinasist(rs.getString("iexfinasist"));
                    p.setDesiniturno(rs.getString("desiniturno"));
                    p.setDesfinturno(rs.getString("desfinturno"));
                    p.setDesiniasist(rs.getString("desiniasist"));
                    p.setDesfinasist(rs.getString("desfinasist"));
                    p.setIexhrstotal(rs.getDouble("iexhrstotal"));
                    p.setIexmintotal(rs.getDouble("iexmintotal"));
                    p.setIexminantes(rs.getDouble("iexminantes"));
                    p.setIexminpost(rs.getDouble("iexminpost"));
                    p.setIexhrspost(rs.getDouble("iexhrspost"));
                    p.setIexcoddiasem(rs.getInt("iexcoddiasem"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexdesusu(rs.getString("iexdesusu"));
                    p.setIexflgest(rs.getString("iexflgest"));
                    p.setIexhrsantes(rs.getDouble("iexhrsantes"));
                    p.setIexhrssale_antes(rs.getDouble("iexhrssale_antes"));
                    p.setIexminsale_antes(rs.getDouble("iexminsale_antes"));
                    p.setIexhrstarde(rs.getDouble("iexhrstarde"));

                    p.setDesfecdia(rs.getString("desfecdia"));
                    FormatterFecha fec = new FormatterFecha();
                    p.setDiaCalendar(fec.fechaFormatterDia(p.getDesfecdia()));

                    p.setIexvacaind(rs.getString("iexvacaind"));
                    p.setIexauseind(rs.getString("iexausenid"));
                    p.setIexindferiado(rs.getString("iexindferiado"));
                    p.setIexindfalta(rs.getString("iexindfalta"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Empleado> listarTurMasTra(Integer codcia, String fecini, String fecfin) {

        String sql = " select  " +
                "e.iexcodcia,  e.iexcodtra, " +
                "e.iexapepat, e.iexapemat, e.iexnomtra, " +
                "e.iexfecing, " +
                "e.iexfecret " +
                "from  " +
                "iexempleado e " +
                "where  " +
                "e.iexcodcia=" + codcia + " and  " +
                "e.iexflgest='1' " +
                "group by  " +
                "e.iexcodcia, e.iexcodtra, " +
                "e.iexapepat, e.iexapemat, e.iexnomtra , e.iexfecing, " +
                "e.iexfecret order by  e.iexapepat, e.iexapemat, e.iexnomtra asc ";

        return template.query(sql, new ResultSetExtractor<List<Empleado>>() {
            public List<Empleado> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Empleado> lista = new ArrayList<Empleado>();

                while (rs.next()) {
                    Empleado p = new Empleado();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexapepat(rs.getString("iexapepat"));
                    p.setIexapemat(rs.getString("iexapemat"));
                    p.setIexnomtra(rs.getString("iexnomtra"));
                    p.setIexfecing(rs.getString("iexfecing"));
                    p.setIexfecret(rs.getString("iexfecret"));
                    lista.add(p);
                }
                return lista;
            }
        });
    }


    public void insertarTurno(Turno turno) {

        template.update("  insert into iexturno( " +
                        " iexcodcia,iexcodturno, iexflgturno,iexdesturno,iexhorini,iexhorfin,iexflgdiasig,iextopminantes,iextopmaxpost" +
                        " ) values ( " +
                        "  ? ,  ?  ,  ? , ? ,  ?  ,  ? , ? ,  ?  ,  ?  " +
                        ")  ",


                turno.getCodcia(),
                turno.getIexcodturno(),
                turno.getIexflgturno(),
                turno.getIexdesturno(),
                turno.getIexhorini(),
                turno.getIexhorfin(),
                turno.getIexflgdiasig(),
                turno.getIextopminantes(),
                turno.getIextopmaxpost());

    }


    public Turno getTurno(Integer codcia, Integer codTurn) {

        String sql = " select   " +
                "t.iexcodcia, " +
                "t.iexcodturno, " +
                "t.iexdesturno, " +
                "t.iexflgturno, " +
                " t.iexhorini," +
                " t.iexhorfin,  " +
                "t.iexflgdiasig, " +
                "t.iextopminantes," +
                " t.iextopmaxpost, " +
                "t.iexdesusu, " +
                "t.iexfeccrea  " +
                "from iexturno t   " +
                "where iexcodcia=" + codcia + "  and t.iexcodturno = " + codTurn + " ";

        return (Turno) template.query(sql, new ResultSetExtractor<Turno>() {
            public Turno extractData(ResultSet rs) throws SQLException, DataAccessException {
                Turno tur = new Turno();
                while (rs.next()) {
                    tur.setCodcia(rs.getInt("iexcodcia"));
                    tur.setIexcodturno(rs.getInt("iexcodturno"));

                    tur.setIexdesturno(rs.getString("iexdesturno"));
                    CapitalizarCadena cap = new CapitalizarCadena();
                    tur.setIexdesturno(cap.letras(tur.getIexdesturno()));

                    tur.setIexhorini(rs.getString("iexhorini"));
                    tur.setIexhorfin(rs.getString("iexhorfin"));
                    tur.setIexflgdiasig(rs.getString("iexflgdiasig"));
                    tur.setIextopminantes(rs.getDouble("iextopminantes"));
                    tur.setIextopmaxpost(rs.getDouble("iextopmaxpost"));
                    tur.setIexdesusu(rs.getString("iexdesusu"));
                    tur.setIexfeccrea(rs.getString("iexfeccrea"));
                    tur.setIexflgturno(rs.getString("iexflgturno"));
                }
                return tur;
            }
        });
    }

    public void actualizarTurno(Turno turno) {

        template.update(
                "  update iexturno  set  " +
                        "	iexflgturno =?, iexdesturno =?, iexhorini =?, iexhorfin =?,  " +
                        "	iexflgdiasig =?, iextopminantes =?, iextopmaxpost =?, " +
                        "	iexdesusumod =?, iexfecmod =current_timestamp  " +
                        "       where  iexcodcia=? and  iexcodturno =?",

                turno.getIexflgturno(),
                turno.getIexdesturno(),
                turno.getIexhorini(),
                turno.getIexhorfin(),
                turno.getIexflgdiasig(),
                turno.getIextopminantes(),
                turno.getIextopmaxpost(),
                turno.getIexdesusu(),
                turno.getCodcia(),
                turno.getIexcodturno());
    }

    public void eliminarTurno(Turno turno) {

        template.update("  delete from  iexturno  where  iexcodcia=? and  iexcodturno =? ",

                turno.getCodcia(),
                turno.getIexcodturno());

    }

    public Turnodiario obtenerTurnoDia(Integer codcia, Integer codtra, String codfec) {

        String sql = " select	" +
                "    t.iexcodcia ," +
                "    t.iexcodtra ," +
                "    t.iexcodfec ," +
                "    t.iexfecdia ," +
                "    to_char(t.iexfecdia,'dd/mm/yyyy') desfecdia ," +
                "    t.iexcodturno ," +
                "    e.iexflgturno, " +
                "    e.iexdesturno," +
                "    t.iexiniturno ," +
                "    t.iexfinturno ," +
                "    t.iexiniasist ," +
                "    t.iexfinasist ," +
                "    to_char(t.iexiniturno,'HH24:MI') desiniturno ," +
                "    to_char(t.iexfinturno,'HH24:MI') desfinturno ," +
                "    to_char(t.iexiniasist,'HH24:MI') desiniasist ," +
                "    to_char(t.iexfinasist,'HH24:MI') desfinasist ," +
                "    t.iexhrstotal ," +
                "    t.iexmintotal ," +
                "    t.iexminantes ," +
                "    t.iexminpost ," +
                "    t.iexhrspost ," +
                "    t.iexcoddiasem ," +
                "    t.iexfeccrea ," +
                "    t.iexdesusu ," +
                "    t.iexflgest ," +
                "    t.iexhrsantes ," +
                "    t.iexhrssale_antes ," +
                "    t.iexminsale_antes ," +
                "    t.iexhrstarde ," +
                "    t.iexmintarde ," +
                "    t.iexvacaind ," +
                "    t.iexausenid ," +
                "    t.iexpermiso ," +
                "    to_char(t.iexhriniperm,'HH24:MI') iexhriniperm ," +
                "    to_char(t.iexhrfinperm,'HH24:MI') iexhrfinperm ," +
                "    t.iexhrsperm ," +
                "    t.iexminsperm , t.iexindferiado,  t.iexindfalta  " +
                "	from iexturnodia t , iexturno e " +
                "	where " +
                "	t.iexcodcia = e.iexcodcia and " +
                "    t.iexcodturno = e.iexcodturno and " +
                "    t.iexcodcia=" + codcia + "  and " +
                "	t.iexcodtra=" + codtra + " and " +
                "	t.iexcodfec = '" + codfec + "'  ";

        return (Turnodiario) template.query(sql, new ResultSetExtractor<Turnodiario>() {
            public Turnodiario extractData(ResultSet rs) throws SQLException, DataAccessException {
                Turnodiario p = new Turnodiario();

                while (rs.next()) {
                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcodfec(rs.getString("iexcodfec"));
                    p.setIexfecdia(rs.getString("iexfecdia"));
                    p.setIexcodturno(rs.getInt("iexcodturno"));
                    p.setDesturno(rs.getString("iexdesturno"));
                    p.setIexflgturno(rs.getString("iexflgturno"));
                    p.setIexiniturno(rs.getString("iexiniturno"));
                    p.setIexfinturno(rs.getString("iexfinturno"));
                    p.setIexiniasist(rs.getString("iexiniasist"));
                    p.setIexfinasist(rs.getString("iexfinasist"));
                    p.setDesiniturno(rs.getString("desiniturno"));
                    p.setDesfinturno(rs.getString("desfinturno"));
                    p.setDesiniasist(rs.getString("desiniasist"));
                    p.setDesfinasist(rs.getString("desfinasist"));
                    p.setIexhrstotal(rs.getDouble("iexhrstotal"));
                    p.setIexmintotal(rs.getDouble("iexmintotal"));
                    p.setIexminantes(rs.getDouble("iexminantes"));
                    p.setIexminpost(rs.getDouble("iexminpost"));
                    p.setIexhrspost(rs.getDouble("iexhrspost"));
                    p.setIexcoddiasem(rs.getInt("iexcoddiasem"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexdesusu(rs.getString("iexdesusu"));
                    p.setIexflgest(rs.getString("iexflgest"));
                    p.setIexhrsantes(rs.getDouble("iexhrsantes"));
                    p.setIexhrssale_antes(rs.getDouble("iexhrssale_antes"));
                    p.setIexminsale_antes(rs.getDouble("iexminsale_antes"));
                    p.setIexhrstarde(rs.getDouble("iexhrstarde"));
                    p.setDesfecdia(rs.getString("desfecdia"));
                    p.setIexvacaind(rs.getString("iexvacaind"));
                    p.setIexauseind(rs.getString("iexausenid"));
                    p.setIexpermiso(rs.getString("iexpermiso"));
                    p.setIexhriniperm(rs.getString("iexhriniperm"));
                    p.setIexhrfinperm(rs.getString("iexhrfinperm"));
                    p.setIexminsperm(rs.getDouble("iexminsperm"));
                    p.setIexhrsperm(rs.getDouble("iexhrsperm"));
                    p.setIexindferiado(rs.getString("iexindferiado"));
                    p.setIexindfalta(rs.getString("iexindfalta"));
                }

                return p;
            }
        });
    }

    public void actualizaTurnoDia(Integer codcia, Integer codtra, Integer codturno, String fecdia, String desusu) {

        template.update(" call pl_actualiza_turno(? ,to_date(?,'dd/mm/yyyy'),?, ?,?) ",

                codcia,
                fecdia,
                codtra,
                codturno,
                desusu);

    }

    public void calificarTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu) {

        template.update(" call pl_califica_asistencia(?,to_date(?,'dd/mm/yyyy'),?,?) ",

                codcia,
                fecdia,
                codtra,
                desusu);
    }

    public void programarTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu) {

        template.update(" call pl_programa_turno(?,to_date(?,'dd/mm/yyyy'),?,?) ",

                codcia,
                fecdia,
                codtra,
                desusu);
    }

    public void marcacionesTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu) {

        template.update(" call pl_procesa_asistencia(?,to_date(?,'dd/mm/yyyy'),?,?) ",

                codcia,
                fecdia,
                codtra,
                desusu);
    }

    public void eliminaTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu) {

        template.update("  delete from iexturnodia where iexcodcia=? and iexcodtra=? and iexcodfec=to_char(to_date(?,'dd/mm/yyyy'),'yyyymmdd')  ",

                codcia,
                codtra,
                fecdia);

        template.update("  delete from iexturno_marks where iexcodcia=? and iexcodtra=? and iexcodfecha=to_char(to_date(?,'dd/mm/yyyy'),'yyyymmdd')  ",

                codcia,
                codtra,
                fecdia);
    }

    public void consolidaAsistencia(Integer codcia, Integer codpro, Integer codtra, String nroper, Integer correl, String desusu) {

        template.update(" call pl_exe_cons_tiempos(?,?,?,?,?,?) ",

                codcia,
                codpro,
                nroper,
                codtra,
                correl,
                desusu);
    }

    public List<TurnoMarks> obtenerTurnoDiaMarks(Integer codcia, Integer codtra, String codfec) {

        String sql = " select  " +
                " iexcodcia, iexcodtra, iexcodfecha, " +
                " to_char(iexfechamarks,'dd/mm/yyyy hh24:mi:ss') as iexfechamarks ,  " +
                " iexflgmanual " +
                " from iexturno_marks where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and iexcodfecha ='" + codfec + "' ";

        return template.query(sql, new ResultSetExtractor<List<TurnoMarks>>() {

            public List<TurnoMarks> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<TurnoMarks> lista = new ArrayList<TurnoMarks>();

                while (rs.next()) {
                    TurnoMarks p = new TurnoMarks();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcodfecha(rs.getString("iexcodfecha"));
                    log.info("p.getIexcodfecha: " + p.getIexcodfecha());
                    p.setIexfechamarks(rs.getString("iexfechamarks"));
                    log.info("p.getIexfechamarks: " + p.getIexfechamarks());
                    p.setIexflgmanual(rs.getString("iexflgmanual"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public void insertaMarkDia(Integer codcia, Integer codtra, String codfec, String fechora, String desusu) {

        template.update(" insert into iexmarkas_manual (iexcodcia, iexcodtra, iexcodfecha, iexfechamarks , iexdesusu ,  iexfeccrea ) values(?,?,?,to_timestamp(?,'dd/mm/yyyy hh24:mi') , ? ,   CURRENT_TIMESTAMP) ",

                codcia,
                codtra,
                codfec,
                fechora,
                desusu);

    }

    public List<MarkaManual> obtenerMarksMDia(Integer codcia, Integer codtra, String codfec) {

        String sql = " select " +
                "iexcodcia  ," +
                "iexcodtra ," +
                "iexcodfecha ," +
                "to_char(iexfechamarks ,'dd/mm/yyyy hh24:mi:ss' ) iexfechamarks,  " +
                "iexdesusu ," +
                "iexfeccrea " +
                "from iexmarkas_manual where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and iexcodfecha='" + codfec + "'  order by iexfechamarks asc ";

        return template.query(sql, new ResultSetExtractor<List<MarkaManual>>() {

            public List<MarkaManual> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<MarkaManual> lista = new ArrayList<MarkaManual>();

                while (rs.next()) {
                    MarkaManual p = new MarkaManual();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcodfec(rs.getString("iexcodfecha"));
                    p.setIexfecmarkas(rs.getString("iexfechamarks"));
                    log.info("p.getIexfecmarkasAAA: " + p.getIexfecmarkas());
                    //p.setIexfecmarkas(rs.getString("iexfechamarks"));
                    p.setIexdesusu(rs.getString("iexdesusu"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public void automarkTurnoDia(Integer codcia, Integer codtra, Integer codturno, String fecdia, String desusu) {

        template.update(" call pl_automark(? ,to_date(?,'dd/mm/yyyy'),?, ?,?) ",

                codcia,
                fecdia,
                codtra,
                codturno,
                desusu);
    }

    public void deleteMarkDia(Integer codcia, Integer codtra, String codfec, String fechora) {

        template.update(" delete from  iexmarkas_manual where iexcodcia=?  and iexcodtra=?  and iexcodfecha=?  and iexfechamarks=to_timestamp(?,'dd/mm/yyyy hh24:mi:ss') ",

                codcia,
                codtra,
                codfec,
                fechora);
    }
}
