package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.ParametrosGen;
import com.balkaned.gladius.dao.ParametroDao;
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

@Repository("ParametroDao")
public class ParametroDaoImpl implements ParametroDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<ParametrosGen> listarParametrosGen() {

        String sql = " select   " +
                " p.iexcodcon, " +
                " c.coodescon, " +
                " p.iextippar,  " +
                " g.desdet,  " +
                " p.iexvalcon, " +
                " p.iexdesobs, " +
                " p.iexusucrea, " +
                " p.iexfeccrea, " +
                " p.iexusumod, " +
                " p.iexfecmod " +
                " from " +
                " iexparameter p , iexconcepto c ,  " +
                "(  select  iexkey, desdet from iexttabled where iexcodtab='67'   ) g " +
                " where  " +
                " p.iexcodcon =  c.coocodcon and " +
                " p.iextippar = g.iexkey  order by p.iextippar , p.iexcodcon asc  ";
        return template.query(sql, new ResultSetExtractor<List<ParametrosGen>>() {

            public List<ParametrosGen> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ParametrosGen> lista = new ArrayList<ParametrosGen>();

                while (rs.next()) {
                    ParametrosGen rol = new ParametrosGen();

                    rol.setIexcodcon(rs.getString("iexcodcon"));
                    rol.setDescon(rs.getString("coodescon"));
                    rol.setIextippar(rs.getString("iextippar"));
                    rol.setDestippar(rs.getString("desdet"));
                    rol.setIexvalcon(rs.getDouble("iexvalcon"));
                    rol.setIexdesobs(rs.getString("iexdesobs"));
                    rol.setIexusucrea(rs.getString("iexusucrea"));
                    rol.setIexfeccrea(rs.getString("iexfeccrea"));
                    rol.setIexusumod(rs.getString("iexusumod"));
                    rol.setIexfecmod(rs.getString("iexfecmod"));

                    lista.add(rol);
                }
                return lista;
            }
        });
    }

    public void insertarParametrosGen(ParametrosGen par) {

        template.update("insert into iexparameter( " +
                        " iexcodcon,     iextippar,      iexvalcon,     iexdesobs,  " +
                        " iexusucrea,    iexfeccrea " +
                        " ) values ( " +
                        "  ? ,        ?,      ?,      ?," +
                        "  ? ,   current_date   " +
                        ")  ",

                par.getIexcodcon(),
                par.getIextippar(),
                par.getIexvalcon(),
                par.getIexdesobs(),
                par.getIexusucrea());

    }

    public ParametrosGen getParametrosGen(String codcon) {

        String sql = " select   " +
                " p.iexcodcon, " +
                " c.coodescon, " +
                " p.iextippar,  " +
                " g.desdet,  " +
                " p.iexvalcon, " +
                " p.iexdesobs, " +
                " p.iexusucrea, " +
                " p.iexfeccrea, " +
                " p.iexusumod, " +
                " p.iexfecmod " +
                " from " +
                " iexparameter p , iexconcepto c ,  " +
                "(  select  iexkey, desdet from iexttabled where iexcodtab='67'   ) g " +
                " where  " +
                " p.iexcodcon =  c.coocodcon and " +
                " p.iextippar = g.iexkey  and  p.iexcodcon = '" + codcon + "' ";
        return (ParametrosGen) template.query(sql, new ResultSetExtractor<ParametrosGen>() {
            public ParametrosGen extractData(ResultSet rs) throws SQLException, DataAccessException {
                ParametrosGen rol = new ParametrosGen();
                while (rs.next()) {

                    rol.setIexcodcon(rs.getString("iexcodcon"));
                    rol.setDescon(rs.getString("coodescon"));
                    rol.setIextippar(rs.getString("iextippar"));
                    rol.setDestippar(rs.getString("desdet"));
                    rol.setIexvalcon(rs.getDouble("iexvalcon"));
                    rol.setIexdesobs(rs.getString("iexdesobs"));
                    rol.setIexusucrea(rs.getString("iexusucrea"));
                    rol.setIexfeccrea(rs.getString("iexfeccrea"));
                    rol.setIexusumod(rs.getString("iexusumod"));
                    rol.setIexfecmod(rs.getString("iexfecmod"));

                }
                return rol;
            }
        });
    }

    public void actualizarParametrosGen(ParametrosGen par) {

        template.update("  update iexparameter  set   " +
                        "     iextippar=?,      iexvalcon=?,     iexdesobs=?,  " +
                        " iexusumod=?,    iexfecmod = current_date  " +
                        "  where  iexcodcon  =  ?  ",
                par.getIextippar(),
                par.getIexvalcon(),
                par.getIexdesobs(),
                par.getIexusumod(),
                par.getIexcodcon());

    }

}
