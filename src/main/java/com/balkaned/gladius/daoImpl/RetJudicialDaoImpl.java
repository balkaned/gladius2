package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.RetencionJudicial;
import com.balkaned.gladius.dao.RetJudicialDao;
import com.balkaned.gladius.utils.CapitalizarCadena;
import com.balkaned.gladius.utils.FormatterFecha;
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

@Repository("RetJudicialDao")
public class RetJudicialDaoImpl implements RetJudicialDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<RetencionJudicial> listarRetencionJudicial(Empleado empleado) {

        String sql = " select " +
                "iexcodcia," +
                "iexcodtra," +
                "iexcorrel," +
                "iexcodpro," +
                "p.prodespro as descodpro, " +
                "iextipretjud, " +
                "d.desdet as destipretjud, " +
                "iexresolucion, " +
                "to_char(iexfecini,'DD/MM/YYYY') iexfecini, " +
                "to_char(iexfecfin,'DD/MM/YYYY') iexfecfin, " +
                "iexpordesct, " +
                "ieximpfijo, " +
                "iexusucrea, " +
                "iexfeccrea, " +
                "iexusumod, " +
                "iexfecmod " +
                "from iexretjudic , " +
                " (  " +
                "                        select  iexkey, desdet from iexttabled where iexcodtab='58'  " +
                "                        ) d,  " +
                "   iexprocesos p " +
                "where  " +
                "iexcodcia=" + empleado.getIexcodcia() + " and iexcodtra=" + empleado.getIexcodtra() + " and  iextipretjud = d.iexkey and iexcodpro= p.procodpro ";

        return template.query(sql, new ResultSetExtractor<List<RetencionJudicial>>() {
            public List<RetencionJudicial> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<RetencionJudicial> lista = new ArrayList<RetencionJudicial>();

                while (rs.next()) {
                    RetencionJudicial p = new RetencionJudicial();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcorrel(rs.getInt("iexcorrel"));
                    p.setIexcodpro(rs.getInt("iexcodpro"));
                    p.setDescodpro(rs.getString("descodpro"));
                    p.setIextipretjud(rs.getString("iextipretjud"));
                    p.setDestipretjud(rs.getString("destipretjud"));
                    p.setIexresolucion(rs.getString("iexresolucion"));

                    p.setIexfecini(rs.getString("iexfecini"));
                    FormatterFecha f = new FormatterFecha();
                    CapitalizarCadena capit= new CapitalizarCadena();
                    p.setIexfecini(f.fechaFormatterDia(p.getIexfecini())+" "+capit.letras(f.fechaFormatterMes(p.getIexfecini()))+", "+f.fechaFormatterAnio(p.getIexfecini()));

                    p.setIexfecfin(rs.getString("iexfecfin"));
                    FormatterFecha f2 = new FormatterFecha();
                    CapitalizarCadena capit2= new CapitalizarCadena();
                    p.setIexfecfin(f2.fechaFormatterDia(p.getIexfecfin())+" "+capit2.letras(f2.fechaFormatterMes(p.getIexfecfin()))+", "+f2.fechaFormatterAnio(p.getIexfecfin()));

                    p.setIexpordesct(rs.getDouble("iexpordesct"));
                    p.setIeximpfijo(rs.getDouble("ieximpfijo"));
                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public Integer getIdRetencionJudicial(RetencionJudicial retjud) {

        final Integer[] idfinal = {0};

        String sql = " select  coalesce(max(iexcorrel),0)+1 as idex from iexretjudic where iexcodcia=" + retjud.getIexcodcia() + " and iexcodtra=" + retjud.getIexcodtra() + " ";
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    idfinal[0] = rs.getInt("idex");
                }
                return idfinal[0];
            }
        });
    }

    public void insertarRetencionJudicial(RetencionJudicial retjud) {

        template.update("  insert into iexretjudic( " +
                        "iexcodcia,       iexcodtra,        iexcorrel,      iexcodpro, " +
                        "iextipretjud,    iexresolucion,    iexfecini,      iexfecfin, " +
                        "iexpordesct,     ieximpfijo,       iexusucrea,     iexfeccrea " +
                        " ) values ( " +
                        "  ?,   ? ,  ?,   ?,  " +
                        "  ?,   ? ,   to_date(?,'DD/MM/YYYY'),   to_date(?,'DD/MM/YYYY') ," +
                        "  ?  ,  ?,   ? ,  current_date " +
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
    }

    public RetencionJudicial getRetencionJudicial(RetencionJudicial retjud){

        String sql=" select " +
                "iexcodcia," +
                "iexcodtra," +
                "iexcorrel," +
                "iexcodpro," +
                "p.prodespro as descodpro, " +
                "iextipretjud, " +
                "d.desdet as destipretjud, " +
                "iexresolucion, " +
                "to_char(iexfecini,'DD/MM/YYYY') iexfecini, " +
                "to_char(iexfecfin,'DD/MM/YYYY') iexfecfin, " +
                "iexpordesct, " +
                "ieximpfijo, " +
                "iexusucrea, " +
                "iexfeccrea, " +
                "iexusumod, " +
                "iexfecmod " +
                "from iexretjudic , " +
                " (  " +
                "                        select  iexkey, desdet from iexttabled where iexcodtab='58'  " +
                "                        ) d,  " +
                "   iexprocesos p " +
                "where  " +
                "iexcodcia="+retjud.getIexcodcia()+" and iexcodtra="+retjud.getIexcodtra()+" and iexcorrel="+retjud.getIexcorrel()+"  and iextipretjud = d.iexkey and iexcodpro= p.procodpro ";

        return (RetencionJudicial) template.query(sql, new ResultSetExtractor<RetencionJudicial>() {
            public RetencionJudicial extractData(ResultSet rs) throws SQLException, DataAccessException{
                RetencionJudicial p = new RetencionJudicial();
                while(rs.next()) {
                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcorrel(rs.getInt("iexcorrel"));
                    p.setIexcodpro(rs.getInt("iexcodpro"));
                    p.setIextipretjud(rs.getString("iextipretjud"));
                    p.setIexresolucion(rs.getString("iexresolucion"));
                    p.setIexfecini(rs.getString("iexfecini"));
                    p.setIexfecfin(rs.getString("iexfecfin"));
                    p.setIexpordesct(rs.getDouble("iexpordesct"));
                    p.setIeximpfijo(rs.getDouble("ieximpfijo"));
                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));
                }
                return p;
            }
        });
    }

    public void actualizarRetencionJudicial(RetencionJudicial retjud){

        template.update("  update iexretjudic set  " +
                        "  iexcodpro=?, " +
                        "iextipretjud =?,    iexresolucion =?,    iexfecini =to_date(?,'DD/MM/YYYY'),      iexfecfin = to_date(?,'DD/MM/YYYY') , " +
                        "iexpordesct =?,     ieximpfijo =?,       iexusumod =?,     iexfeccrea = current_date" +
                        " where iexcodcia =?  and       iexcodtra=?  and        iexcorrel=?     ",

        retjud.getIexcodpro(),
        retjud.getIextipretjud(),
        retjud.getIexresolucion(),
        retjud.getIexfecini(),
        retjud.getIexfecfin(),
        retjud.getIexpordesct(),
        retjud.getIeximpfijo(),
        "1",
        retjud.getIexcodcia(),
        retjud.getIexcodtra(),
        retjud.getIexcorrel());
    }

    public void eliminarRetencionJudicial(RetencionJudicial retjud){

        template.update("  delete from iexretjudic  where iexcodcia =?  and       iexcodtra=?  and        iexcorrel=?     ",

        retjud.getIexcodcia(),
        retjud.getIexcodtra(),
        retjud.getIexcorrel());
    }

}
