package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Concepto;
import com.balkaned.gladius.beans.EmpSueldo;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.Local;
import com.balkaned.gladius.dao.SueldoDao;
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

@Repository("SueldoDao")
public class SueldosDaoImpl implements SueldoDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<EmpSueldo> obtenerEmpSueldo(Empleado empleado){

        List<EmpSueldo> lista = null;

        String sql =  "select " +
                "s.iexcodcia," +
                "s.iexcodtra," +
                "s.iexcodcon," +
                "c.coodescon," +
                "s.iexvalcon," +
                "s.iexflgest " +
                "from iexconcepto c, " +
                "iexsueldos s " +
                "where " +
                "s.iexcodcia = "+empleado.getIexcodcia()+" and  " +
                "s.iexcodtra= "+empleado.getIexcodtra()+"  and " +
                "c.coocodcon=s.iexcodcon ";

        return template.query(sql, new ResultSetExtractor<List<EmpSueldo>>() {
            public List<EmpSueldo> extractData(ResultSet rs) throws SQLException, DataAccessException{
                List<EmpSueldo> lista = new ArrayList<EmpSueldo>();

                while(rs.next()) {
                    EmpSueldo con = new EmpSueldo();

                    con.setIexcodcia(rs.getInt("iexcodcia"));
                    con.setIexcodtra(rs.getInt("iexcodtra"));
                    con.setIexcodcon(rs.getString("iexcodcon"));
                    con.setDescon(rs.getString("coodescon"));
                    con.setIexvalcon(rs.getDouble("iexvalcon"));
                    con.setIexflgest(rs.getString("iexflgest"));

                    lista.add(con);
                }
                return lista;
            }
        });
    }

    public List<Concepto> ListConceptos(Integer codcia, String Tipo){

        List<Concepto> lista = null;

        String sql =  "select  " +
                "coocodcon, coodescon " +
                "from iexciaxcon, iexconcepto where " +
                "iexcodcia="+codcia+" and  " +
                "iexcodcon=coocodcon and iextipreg='"+Tipo+"' ";

        return template.query(sql, new ResultSetExtractor<List<Concepto>>() {
            public List<Concepto> extractData(ResultSet rs) throws SQLException, DataAccessException{
                List<Concepto> lista = new ArrayList<Concepto>();

                while(rs.next()) {
                    Concepto con = new Concepto();

                    con.setCodConcepto(rs.getString("coocodcon"));
                    con.setDesConcepto(rs.getString("coodescon"));

                    lista.add(con);
                }
                return lista;
            }
        });
    }

    public void insertarEmpSueldo(EmpSueldo empsueldo){

        template.update("INSERT INTO iexsueldos (iexcodcia, iexcodtra, iexcodcon, iexvalcon, iexflgest)  VALUES (?, ?, ?, ?, ? )",

        empsueldo.getIexcodcia(),
        empsueldo.getIexcodtra(),
        empsueldo.getIexcodcon(),
        empsueldo.getIexvalcon(),
        empsueldo.getIexflgest());
    }
}
