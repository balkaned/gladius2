package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Concepto;
import com.balkaned.gladius.dao.ConceptoDao;
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

@Repository("ConceptoDao")
public class ConceptoDaoImpl implements ConceptoDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<Concepto> listardet(){

        String sql =  "select  " +
                "	coocodcon, " +
                "	coodescon, " +
                "	coocodforvar, " +
                "	coodesabrev,  " +
                "	coodescripcion " +
                "from iexconcepto  order by coodescon asc ";

        return template.query(sql, new ResultSetExtractor<List<Concepto>>() {

            public List<Concepto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Concepto> lista = new ArrayList<Concepto>();

                while(rs.next()) {
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
}
