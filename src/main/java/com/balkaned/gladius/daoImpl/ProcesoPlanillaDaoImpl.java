package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.AsientoContableCab;
import com.balkaned.gladius.beans.ProcesoPlanilla;
import com.balkaned.gladius.dao.ProcesoPlanillaDao;
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

@Repository("ProcesoPlanillaDao")
public class ProcesoPlanillaDaoImpl implements ProcesoPlanillaDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<ProcesoPlanilla> listar(String text) {

        List<ProcesoPlanilla> lista = null;
        String sql = "select " +
                "procodpro, " +
                "prodespro, " +
                "prodescorto, " +
                "procodregimenlab, " +
                "procodregimenlab desregimen, " +
                "progrppro, " +
                "bolproceso, " +
                "idtipproceso, " +
                "bolprocesoind, " +
                "bolprocesores " +
                "from iexprocesos p   " +
                "where " +
                " prodespro like '%" + text + "%' order by procodpro asc ";

        return template.query(sql, new ResultSetExtractor<List<ProcesoPlanilla>>() {

            public List<ProcesoPlanilla> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ProcesoPlanilla> lista = new ArrayList<ProcesoPlanilla>();

                while (rs.next()) {
                    ProcesoPlanilla p = new ProcesoPlanilla();

                    p.setIdProceso(rs.getInt("procodpro"));
                    p.setDesProceso(rs.getString("prodespro"));
                    p.setDesProcesoCorto(rs.getString("prodescorto"));
                    p.setIdRegLab(rs.getString("procodregimenlab"));
                    p.setDesRegLab(rs.getString("desregimen"));
                    p.setDesGrp(rs.getString("progrppro"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<AsientoContableCab> listarAsieCab(Integer codcia, Integer codpro, String nroper){

        List<AsientoContableCab> lista = null;
        String sql = "  select " +
                " e.iexcodcia, e.iexctbper_id , e.iexnroasiento, e.iexcodpro ,  " +
                " p.prodespro ,  e.iexnroper, e.iexpermes, e.iextcmb, e.iexcodmon, d.desdet desmon,  " +
                " e.iexcodmon_ext,  d2.desdet desmon_ext,  e.tot_cre_na, e.tot_deb_na , e.tot_cre_me, e.tot_deb_me, e.iexglosacab  " +
                " from iexctbpercab  e  " +
                " inner join iexprocesos  p  on  e.iexcodpro= p.procodpro  " +
                " left join iexttabled d on d.iexcodtab='52'  and d.iexkey = e.iexcodmon  " +
                " left join iexttabled d2 on d.iexcodtab='52'  and d2.iexkey = e.iexcodmon_ext " +
                " where  " +
                " e.iexcodcia =  "+codcia+" and  e.iexcodpro="+codpro+" and e.iexpermes = '"+nroper+"'  ";

        return template.query(sql, new ResultSetExtractor<List<AsientoContableCab>>() {

            public List<AsientoContableCab> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<AsientoContableCab> lista = new ArrayList<AsientoContableCab>();

                while (rs.next()) {
                    AsientoContableCab p = new AsientoContableCab();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexctbper_id(rs.getInt("iexctbper_id"));
                    p.setIexnroasiento(rs.getString("iexnroasiento"));
                    p.setIexcodpro(rs.getInt("iexcodpro"));
                    p.setDesproceso(rs.getString("prodespro"));
                    p.setIexnroper(rs.getString("iexnroper"));
                    p.setIexpermes(rs.getString("iexpermes"));
                    p.setIextcmb(rs.getDouble("iextcmb"));
                    p.setIexcodmon(rs.getString("iexcodmon"));
                    p.setCodmon_des(rs.getString("desmon"));
                    p.setIexcodmon_ext(rs.getString("iexcodmon_ext"));
                    p.setCodmon_ext_des(rs.getString("desmon_ext"));
                    p.setTot_cre_na(rs.getDouble("tot_cre_na"));
                    p.setTot_deb_na(rs.getDouble("tot_deb_na"));
                    p.setTot_cre_me(rs.getDouble("tot_cre_me"));
                    p.setTot_deb_me(rs.getDouble("tot_deb_me"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    @Override
    public ProcesoPlanilla listarPorProcodpro(Integer cod) {
        String sqlQuery = "select procodpro, prodespro, prodescorto, procodregimenlab, progrppro, " +
         "bolproceso, idtipproceso, bolprocesoind, bolprocesores from iexprocesos p  where procodpro= '" + cod + "' " +
         "order by 1 asc";
        return template.query(sqlQuery, rs -> {
            ProcesoPlanilla p = new ProcesoPlanilla();
            while (rs.next()) {
                p.setIdProceso(rs.getInt("procodpro"));
                p.setDesProceso(rs.getString("prodespro"));
                p.setDesProcesoCorto(rs.getString("prodescorto"));
                p.setIdRegLab(rs.getString("procodregimenlab"));
                p.setDesGrp(rs.getString("progrppro"));
                p.setBolProceso(rs.getString("bolproceso"));
                p.setIdTipProceso(rs.getString("idtipproceso"));
                p.setBolProcesoind(rs.getString("bolprocesoind"));
                p.setBolProcesores(rs.getString("bolprocesores"));
            }
            return p;
        });
    }
}
