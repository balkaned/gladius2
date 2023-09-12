package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
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
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<Turno> listarTurnos(Integer codcia){

        String sql = " select  " +
                " iexcodcia, iexcodturno, iexflgturno, iexdesturno, " +
                " iexhorini, iexhorfin, " +
                " iexflgdiasig, iextopminantes, iextopmaxpost, " +
                //     " lunes, martes, miercoles, jueves, viernes, sabado, domingo,  " +
                " iexdesusu, iexfeccrea " +
                " from iexturno where iexcodcia="+codcia+"  "  ;

        return template.query(sql, new ResultSetExtractor<List<Turno>>() {
            public List<Turno> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Turno> lista = new ArrayList<Turno>();

                while (rs.next()) {
                    Turno p = new Turno();

                    p.setCodcia(rs.getInt("iexcodcia"));
                    p.setIexcodturno(rs.getInt("iexcodturno"))  ;
                    p.setIexflgturno(rs.getString("iexflgturno")) ;
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

    /*public Integer getIdRetencionJudicial(RetencionJudicial retjud){

        final Integer[] idfinal = {0};

        String sql = " select  coalesce(max(iexcorrel),0)+1 as idex from iexretjudic where iexcodcia="+retjud.getIexcodcia()+" and iexcodtra="+retjud.getIexcodtra()+" " ;
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
                while(rs.next()) {
                    idfinal[0] =rs.getInt("idex");
                }
                return idfinal[0];
            }
        });
    }

    public void insertarRetencionJudicial(RetencionJudicial retjud){

        template.update("  insert into iexretjudic( " +
                "iexcodcia,       iexcodtra,        iexcorrel,      iexcodpro, " +
                "iextipretjud,    iexresolucion,    iexfecini,      iexfecfin, " +
                "iexpordesct,     ieximpfijo,       iexusucrea,     iexfeccrea " +
                " ) values ( " +
                "  ?,   ? ,  ?,   ?,  "+
                "  ?,   ? ,   to_date(?,'DD/MM/YYYY'),   to_date(?,'DD/MM/YYYY') ,"+
                "  ?  ,  ?,   ? ,  current_date "+
                ")  ",

                retjud.getIexcodcia(),
                retjud.getIexcodtra(),
                retjud.getIexcorrel(),
                retjud.getIexcodpro(),
                retjud.getIextipretjud(),
                retjud.getIexresolucion(),
                retjud.getIexfecini(),
                retjud.getIexfecfin(),
                retjud.getIexpordesct(),
                retjud.getIeximpfijo(),
                "1");
    }*/

    public List<Turnodiario> listarTurnoDia(Integer codcia, Integer codtra, String fecini, String fecfin){

        String sql = " select	" +
                "    t.iexcodcia ," +
                "    t.iexcodtra ," +
                "    t.iexcodfec ," +
                "    t.iexfecdia ," +
                "    to_char(t.iexfecdia,'dd/mm/yyyy') desfecdia ," +
                "    t.iexcodturno ," +
                "    e.iexflgturno, "+
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
                "    t.iexcodcia="+codcia+"  and " +
                "	t.iexcodtra="+codtra+" and " +
                "	t.iexfecdia >= to_date('"+fecini+"','dd/mm/yyyy') and t.iexfecdia <= to_date('"+fecfin+"','dd/mm/yyyy') order by iexcodfec asc  "  ;
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

}
