package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.Concepto;
import com.balkaned.gladius.dao.ConceptoDao;
import lombok.extern.slf4j.Slf4j;
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

@Repository("ConceptoDao")
@Slf4j
public class ConceptoDaoImpl implements ConceptoDao {


    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<Concepto> listardet() {

        String sql = "select  " +
                "	coocodcon, " +
                "	coodescon, " +
                "	coocodforvar, " +
                "	coodesabrev,  " +
                "	coodescripcion " +
                "from iexconcepto  order by coodescon asc ";

        return template.query(sql, new ResultSetExtractor<>() {

            public List<Concepto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Concepto> lista = new ArrayList<Concepto>();

                while (rs.next()) {
                    Concepto con = new Concepto();
                    con.setCodConcepto(rs.getString("coocodcon"));
                    con.setDesConcepto(rs.getString("coodescon"));
                    con.setDesVariable(rs.getString("coocodforvar"));
                    con.setDesAbreviacion(rs.getString("coodesabrev"));
                    con.setDescripcion(rs.getString("coodescripcion"));

                    lista.add(con);
                }
                return lista;
            }
        });
    }

    /*@Override
    public List<Concepto> listardet() {
        return null;
    }*/

    @Override
    public List<Concepto> listConceptos() {
        String sqlQuery = "SELECT " +
                "coocodcon, " +
                "coodescon, " +
                "coocodforvar, " +
                "coodesabrev, " +
                "coodescripcion " +
                "FROM iexconcepto order by coodescon asc";
        return template.query(sqlQuery, rs -> {
            List<Concepto> list = new ArrayList<>();

            while (rs.next()) {
                Concepto con = new Concepto();
                con.setCodConcepto(rs.getString("coocodcon"));
                con.setDesConcepto(rs.getString("coodescon"));
                con.setDesVariable(rs.getString("coocodforvar"));
                con.setDesAbreviacion(rs.getString("coodesabrev"));
                con.setDescripcion(rs.getString("coodescripcion"));

                list.add(con);
            }

            return list;
        });
    }

    @Override
    public List<Concepto> listarConceptoIns(Integer idProceso) {
        String sqlQuery = "select coocodcon, coodescon from iexconcepto where coocodcon not in " +
         " (select procodcon from iexproxconcepto where procodpro="+idProceso+"  ) ";
        try {
            return template.query(sqlQuery, rs -> {
                List<Concepto> list = new ArrayList<>();

                while (rs.next()) {
                    Concepto con = new Concepto();
                    con.setCodConcepto(rs.getString("coocodcon"));
                    con.setDesConcepto(rs.getString("coodescon"));

                    list.add(con);
                }

                return list;
            });
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void insertarConcepto(Concepto concepto) {
        template.update("INSERT INTO iexconcepto " +
                        "(coocodcon, " +
                        "coodescon, " +
                        "coocodforvar, " +
                        "coodesabrev, " +
                        "coodescripcion) " +
                        "VALUES (?, ?, ?, ?, ?)",

                concepto.getCodConcepto(),
                concepto.getDesConcepto(),
                concepto.getDesVariable(),
                concepto.getDesAbreviacion(),
                concepto.getDescripcion());
    }

    @Override
    public Concepto getById(String id) {
        String sqlQuery = "SELECT " +
                "coocodcon, " +
                "coodescon, " +
                "coocodforvar, " +
                "coodesabrev, " +
                "coodescripcion " +
                "FROM iexconcepto " +
                "WHERE coocodcon = '" + id + "' ";
        return template.query(sqlQuery, rs -> {
            Concepto con = new Concepto();
            while (rs.next()) {
                con.setCodConcepto(rs.getString("coocodcon"));
                con.setDesConcepto(rs.getString("coodescon"));
                con.setDesVariable(rs.getString("coocodforvar"));
                con.setDesAbreviacion(rs.getString("coodesabrev"));
                con.setDescripcion(rs.getString("coodescripcion"));
            }

            return con;
        });
    }

    @Override
    public void actualizarConcepto(Concepto concepto) {
        template.update("UPDATE iexconcepto " +
                        "SET coodescon = ?, " +
                        "coocodforvar = ?, " +
                        "coodesabrev = ?, " +
                        "coodescripcion = ? " +
                        "WHERE coocodcon = ? ",

                concepto.getDesConcepto(),
                concepto.getDesVariable(),
                concepto.getDesAbreviacion(),
                concepto.getDescripcion(),
                concepto.getCodConcepto()
        );
    }

    public Concepto recuperar (String id) {
        String sqlQuery =  "select  " +
                "	coocodcon, " +
                "	coodescon, " +
                "	coocodforvar, " +
                "	coodesabrev,  " +
                "	coodescripcion " +
                "from iexconcepto where TRIM(coocodcon) = TRIM('"+id+"')  ";
        return template.query(sqlQuery, rs -> {
            Concepto con = new Concepto();
            while (rs.next()) {
                con.setCodConcepto(rs.getString("coocodcon"));
                con.setDesConcepto(rs.getString("coodescon"));
                con.setDesVariable(rs.getString("coocodforvar"));
                con.setDesAbreviacion(rs.getString("coodesabrev"));
                con.setDescripcion(rs.getString("coodescripcion"));
            }

            return con;
        });
    }

    public void eliminar(String id){

        template.update("delete from  iexconcepto where trim(coocodcon) = trim(?) ",
                id);
    }

}