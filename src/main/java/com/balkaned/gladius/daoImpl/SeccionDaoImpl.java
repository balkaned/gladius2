package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Seccion;
import com.balkaned.gladius.dao.SeccionDao;
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

@Repository("SeccionDao")
public class SeccionDaoImpl implements SeccionDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<Seccion> listarSeccion() {

        List<Seccion> lista = null;
        String sql = " select " +
                "s.iexcodsec, " +
                "s.iexdessec, " +
                "s.iexordsec, " +
                "s.iexcodsys, " +
                "y.iexdessys, " +
                "s.iexsecurl, " +
                "s.iexsecimg, " +
                "s.iexsecobs, " +
                "s.iexopcdef,  " +
                "s.iexactiondef  " +
                "from iexseccion s, iexsystemas y " +
                "where " +
                "s.iexcodsys= y.iexcodsys ";
        return template.query(sql, new ResultSetExtractor<List<Seccion>>() {

            public List<Seccion> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Seccion> lista = new ArrayList<Seccion>();

                while (rs.next()) {
                    Seccion p = new Seccion();

                    p.setIexcodsec(rs.getInt("iexcodsec"));
                    p.setIexdessec(rs.getString("iexdessec"));
                    p.setIexordsec(rs.getInt("iexordsec"));
                    p.setIexcodsys(rs.getInt("iexcodsys"));
                    p.setDessys(rs.getString("iexdessys"));
                    p.setIexsecurl(rs.getString("iexsecurl"));
                    p.setIexsecimg(rs.getString("iexsecimg"));
                    p.setIexsecobs(rs.getString("iexsecobs"));
                    p.setIexactiondef(rs.getString("iexactiondef"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public Integer getIdSeccion() {

        final Integer[] idfinal = {0};

        String sql = " select  coalesce(max(iexcodsec),0)+1 as idex from iexseccion  ";

        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    idfinal[0] = rs.getInt("idex");
                }
                return idfinal[0];
            }
        });
    }

    public void insertarSeccion(Seccion seccion) {

        template.update("  insert into iexseccion( " +
                        " iexcodsec,   iexdessec,   iexordsec,    iexcodsys,    iexsecurl, " +
                        " iexsecimg,   iexsecobs , iexactiondef  " +
                        " ) values ( " +
                        "  ? ,      ?  ,       ? ,     ?,       ?,  " +
                        "  ? ,      ?   ,     ? " +
                        ")  ",

                seccion.getIexcodsec(),
                seccion.getIexdessec(),
                seccion.getIexordsec(),
                seccion.getIexcodsys(),
                seccion.getIexsecurl(),
                seccion.getIexsecimg(),
                seccion.getIexsecobs(),
                seccion.getIexactiondef());
    }

    public Seccion getSeccion(Integer codsec) {

        String sql = " select " +
                "s.iexcodsec, " +
                "s.iexdessec, " +
                "s.iexordsec, " +
                "s.iexcodsys, " +
                "y.iexdessys, " +
                "s.iexsecurl, " +
                "s.iexsecimg, " +
                "s.iexsecobs, " +
                "s.iexopcdef,  " +
                "s.iexactiondef  " +
                "from iexseccion s, iexsystemas y " +
                "where " +
                "s.iexcodsys= y.iexcodsys  and s.iexcodsec= " + codsec + "  ";

        return (Seccion) template.query(sql, new ResultSetExtractor<Seccion>() {
            public Seccion extractData(ResultSet rs) throws SQLException, DataAccessException {
                Seccion p = new Seccion();
                while (rs.next()) {
                    p.setIexcodsec(rs.getInt("iexcodsec"));
                    p.setIexdessec(rs.getString("iexdessec"));
                    p.setIexordsec(rs.getInt("iexordsec"));
                    p.setIexcodsys(rs.getInt("iexcodsys"));
                    p.setDessys(rs.getString("iexdessys"));
                    p.setIexsecurl(rs.getString("iexsecurl"));
                    p.setIexsecimg(rs.getString("iexsecimg"));
                    p.setIexsecobs(rs.getString("iexsecobs"));
                    p.setIexactiondef(rs.getString("iexactiondef"));
                }
                return p;
            }
        });
    }

    public void actualizarSeccion(Seccion seccion) {

        template.update("  update iexseccion set " +
                        "    iexdessec= ?  , iexordsec= ?  ,  iexcodsys= ?  ,  iexsecurl= ? , " +
                        " iexsecimg = ? ,   iexsecobs= ? , iexactiondef = ?  " +
                        "  where iexcodsec  = ? ",

                seccion.getIexdessec(),
                seccion.getIexordsec(),
                seccion.getIexcodsys(),
                seccion.getIexsecurl(),
                seccion.getIexsecimg(),
                seccion.getIexsecobs(),
                seccion.getIexactiondef(),
                seccion.getIexcodsec());
    }

    public void eliminarSeccion(Seccion seccion) {

        template.update("  delete from iexseccion   " +
                        "  where iexcodsec  = ? ",

                seccion.getIexcodsec());
    }

}
