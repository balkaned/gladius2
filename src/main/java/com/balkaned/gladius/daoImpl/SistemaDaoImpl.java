package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Sistemas;
import com.balkaned.gladius.dao.SistemaDao;
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

@Repository("SistemaDao")
public class SistemaDaoImpl implements SistemaDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<Sistemas> listarSistemas() {

        String sql = " select  " +
                "iexcodsys, " +
                "iexdessys " +
                "from iexsystemas ";
        return template.query(sql, new ResultSetExtractor<List<Sistemas>>() {

            public List<Sistemas> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Sistemas> lista = new ArrayList<Sistemas>();

                while (rs.next()) {
                    Sistemas p = new Sistemas();

                    p.setIexcodsys(rs.getInt("iexcodsys"));
                    p.setIexdessys(rs.getString("iexdessys"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public void insertarSistemas(Sistemas systema) {

        template.update("  insert into iexsystemas( " +
                        " iexcodsys,       iexdessys " +
                        " ) values ( " +
                        "  ? ,   ?   " +
                        ")  ",

                systema.getIexcodsys(),
                systema.getIexdessys());
    }

    public Sistemas getSistemas(Integer codsis) {

        String sql = " select  " +
                "iexcodsys, " +
                "iexdessys, " +
                "iexactiondefault " +
                "from iexsystemas where iexcodsys = " + codsis + " ";
        return (Sistemas) template.query(sql, new ResultSetExtractor<Sistemas>() {
            public Sistemas extractData(ResultSet rs) throws SQLException, DataAccessException {
                Sistemas p = new Sistemas();
                while (rs.next()) {
                    p.setIexcodsys(rs.getInt("iexcodsys"));
                    p.setIexdessys(rs.getString("iexdessys"));
                    p.setIexactiondefault(rs.getString("iexactiondefault"));
                }
                return p;
            }
        });
    }

    public void actualizarSistemas(Sistemas systema) {

        template.update("  update iexsystemas  set  iexdessys =? " +
                        " where iexcodsys=?     ",

                systema.getIexdessys(),
                systema.getIexcodsys());

    }

    public void eliminarSistemas(Sistemas systema) {

        template.update("  delete from  iexsystemas  " +
                        "  where iexcodsys=?     ",

                systema.getIexcodsys());
    }

}
