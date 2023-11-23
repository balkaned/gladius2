package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.AusentismoProgramacion;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.VacacionControl;
import com.balkaned.gladius.beans.VacacionProgramacion;
import com.balkaned.gladius.dao.AusentismoDao;
import com.balkaned.gladius.dao.VacacionesDao;
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

@Repository("AusentismoDao")
public class AusentismoDaoImpl implements AusentismoDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<AusentismoProgramacion> listarAusentismoPrg(Empleado empleado) {

        String sql = " select  " +
                "v.iexcodcia, v.iexcodtra, v.iexcorrel, to_char(v.iexfecini,'DD/MM/YYYY') as iexfecini, to_char(v.iexfecfin,'DD/MM/YYYY') as iexfecfin, "
                + " v.iexnrodias, v.iextipaus , d.desdet as destipaus, v.iexglosa,  " +
                " v.iexusucrea, to_char(v.iexfeccrea,'DD/MM/YYYY') as iexfeccrea, v.iexusumod, to_char(v.iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                " from " +
                " iexausprg v, " +
                "( " +
                " select  iexkey, desdet from iexttabled where iexcodtab='57' " +
                ") d " +
                " where " +
                " v.iexcodcia=" + empleado.getIexcodcia() + " and v.iexcodtra=" + empleado.getIexcodtra() + " and " +
                " v.iextipaus = d.iexkey ";
        return template.query(sql, new ResultSetExtractor<List<AusentismoProgramacion>>() {
            public List<AusentismoProgramacion> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<AusentismoProgramacion> lista = new ArrayList<AusentismoProgramacion>();

                while (rs.next()) {
                    AusentismoProgramacion p = new AusentismoProgramacion();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcorrel(rs.getInt("iexcorrel"));
                    p.setIexfecini(rs.getString("iexfecini"));
                    p.setIexfecfin(rs.getString("iexfecfin"));
                    p.setIexnrodias(rs.getDouble("iexnrodias"));
                    p.setIextipaus(rs.getString("iextipaus"));
                    p.setDestipaus(rs.getString("destipaus"));
                    p.setIexglosa(rs.getString("iexglosa"));
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

    public Integer getIdAusentismoPrg(AusentismoProgramacion ausprg) {

        final Integer[] idfinal = {0};

        String sql = " select  coalesce(max(iexcorrel),0)+1 as idex from iexausprg where iexcodcia=" + ausprg.getIexcodcia() + " and iexcodtra=" + ausprg.getIexcodtra() + " ";
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    idfinal[0] = rs.getInt("idex");
                }
                return idfinal[0];
            }
        });
    }

    public Integer validaAus(Integer codcia, Integer codtra, String fecini, String fecfin, Integer iexcorrel) {

        final Integer[] valor = {0};

        String sql = "  select " +
                "	sum(e.dias) dias " +
                "  from (  " +
                "	 select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecini + "','dd/mm/yyyy')  >= iexfecini  and  to_date('" + fecini + "','dd/mm/yyyy')  <= iexfecfin and iexcorrel<>" + iexcorrel + " " +
                "	  union " +
                "	  select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecfin + "','dd/mm/yyyy')  >= iexfecini  and  to_date('" + fecfin + "','dd/mm/yyyy')  <= iexfecfin and iexcorrel<>" + iexcorrel + " " +
                "  union " +
                " select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecini + "','dd/mm/yyyy')  <= iexfecini  and  to_date('" + fecfin + "','dd/mm/yyyy')  >= iexfecini  and iexcorrel<>" + iexcorrel + "  " +
                " union   " +
                " select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecini + "','dd/mm/yyyy')  <= iexfecfin  and  to_date('" + fecfin + "','dd/mm/yyyy')  >= iexfecfin  and iexcorrel<>" + iexcorrel + "  " +
                " union  " +
                " select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecini + "','dd/mm/yyyy')  <= iexfecini  and  to_date('" + fecfin + "','dd/mm/yyyy')  >= iexfecfin   and iexcorrel<>" + iexcorrel + "  " +
                "																  ) e";
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    valor[0] = rs.getInt("dias");
                }
                return valor[0];
            }
        });
    }

    public void insertarAusentismoPrg(AusentismoProgramacion ausprg) {

        template.update("  insert into iexausprg ( " +
                        "iexcodcia, iexcodtra, iexcorrel, iexfecini, iexfecfin, iexnrodias,  " +
                        "iextipaus , iexglosa, iexusucrea, iexfeccrea " +
                        ") values ( " +
                        "  ?,   ? ,  ?,    to_date(?,'DD/MM/YYYY'),   to_date(?,'DD/MM/YYYY') ,  ?  ,   " +
                        "  ?,   ? ,  ?,  current_date " +
                        ")  ",

                ausprg.getIexcodcia(),
                ausprg.getIexcodtra(),
                ausprg.getIexcorrel(),
                ausprg.getIexfecini(),
                ausprg.getIexfecfin(),
                ausprg.getIexnrodias(),
                ausprg.getIextipaus(),
                ausprg.getIexglosa(),
                ausprg.getIexusucrea());
    }

}
