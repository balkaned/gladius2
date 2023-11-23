package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.BancoPro;
import com.balkaned.gladius.beans.ProcesoPlanilla;
import com.balkaned.gladius.dao.BancoProDao;
import com.balkaned.gladius.dao.ProcesoPlanillaDao;
import com.balkaned.gladius.utils.CapitalizarCadena;
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


}
