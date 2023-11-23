package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Local;
import com.balkaned.gladius.dao.LocalDao;
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

@Repository("LocalDao")
public class LocalDaoImpl implements LocalDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<Local> listarLocales(Integer codcia, String text) {

        String sql = " select  " +
                "a.iexcodcia, " +
                "a.iexubicod, " +
                "a.iexubides, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod " +
                "from iexubicacion a  where a.iexcodcia=" + codcia + "  ";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Local>>() {

            public List<Local> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Local> lista = new ArrayList<Local>();

                while (rs.next()) {
                    Local p = new Local();
                    CapitalizarCadena cap = new CapitalizarCadena();
                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexubicod(rs.getString("iexubicod"));
                    p.setIexubides(rs.getString("iexubides"));

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

    public Local getLocales(Integer codcia, String codubicacion) {

        Local p = null;

        String sql = " select  " +
                "a.iexcodcia, " +
                "a.iexubicod, " +
                "a.iexubides, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod " +
                "from iexubicacion a  where a.iexcodcia=" + codcia + " and iexubicod ='" + codubicacion + "'  ";

        //System.out.println(sql);

        return (Local) template.query(sql, new ResultSetExtractor<Local>() {
            public Local extractData(ResultSet rs) throws SQLException, DataAccessException {
                Local p = new Local();
                while (rs.next()) {
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexubicod(rs.getString("iexubicod"));
                    p.setIexubides(rs.getString("iexubides"));

                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));
                }
                return p;
            }
        });
    }

    public Integer getIdUbicaion(Integer codcia) {

        final Integer[] idcont = {0};

        String sql = " select  coalesce(max(cast(iexubicod as integer)),0)+1  idcont  from iexubicacion  where iexcodcia =" + codcia;

        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    idcont[0] = Integer.valueOf(rs.getString("idcont"));
                }
                return idcont[0];
            }
        });
    }

    public void insertarUbicacion(Local ubic) {

        template.update("  insert into iexubicacion( " +
                        " iexcodcia,     iexubicod,    iexubides,       " +
                        " iexusucrea,    iexfeccrea  " +
                        " ) values ( " +
                        "  ? ,   ?    ,   ?   ,  " +
                        "  ? ,   current_date   " +
                        ")  ",

                ubic.getIexcodcia(),
                ubic.getIexubicod(),
                ubic.getIexubides(),
                ubic.getIexusucrea());
    }

}
