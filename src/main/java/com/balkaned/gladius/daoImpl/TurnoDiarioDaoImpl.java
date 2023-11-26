package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.Role;
import com.balkaned.gladius.beans.Turno;
import com.balkaned.gladius.beans.Turnodiario;
import com.balkaned.gladius.dao.TurnoDiarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository("TurnoDiarioDao")
public class TurnoDiarioDaoImpl implements TurnoDiarioDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
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

    public List<Empleado> listarTurMasTra(Integer codcia,  String fecini, String fecfin ) {

        String sql = " select  " +
                "e.iexcodcia,  e.iexcodtra, " +
                "e.iexapepat, e.iexapemat, e.iexnomtra, " +
                "e.iexfecing, " +
                "e.iexfecret " +
                "from  " +
                "iexempleado e " +
                "where  " +
                "e.iexcodcia="+codcia+" and  " +
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
                    p.setIexcodtra(rs.getInt("iexcodtra"))  ;
                    p.setIexapepat(rs.getString("iexapepat")) ;
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

}
