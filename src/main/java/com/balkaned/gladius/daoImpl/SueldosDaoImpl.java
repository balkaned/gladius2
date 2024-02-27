package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.Concepto;
import com.balkaned.gladius.beans.EmpDatvar;
import com.balkaned.gladius.beans.EmpSueldo;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.dao.SueldoDao;
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

@Repository("SueldoDao")
@Slf4j
public class SueldosDaoImpl implements SueldoDao {


    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<EmpSueldo> obtenerEmpSueldo(Empleado empleado) {

        List<EmpSueldo> lista = null;

        String sql = "select " +
                "s.iexcodcia," +
                "s.iexcodtra," +
                "s.iexcodcon," +
                "c.coodescon," +
                "s.iexvalcon," +
                "s.iexflgest " +
                "from iexconcepto c, " +
                "iexsueldos s " +
                "where " +
                "s.iexcodcia = " + empleado.getIexcodcia() + " and  " +
                "s.iexcodtra= " + empleado.getIexcodtra() + "  and " +
                "c.coocodcon=s.iexcodcon ";

        return template.query(sql, new ResultSetExtractor<List<EmpSueldo>>() {
            public List<EmpSueldo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<EmpSueldo> lista = new ArrayList<EmpSueldo>();

                while (rs.next()) {
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

    public List<Concepto> ListConceptos(Integer codcia, String Tipo) {

        List<Concepto> lista = null;

        String sql = "select  " +
                "coocodcon, coodescon " +
                "from iexciaxcon, iexconcepto where " +
                "iexcodcia=" + codcia + " and  " +
                "iexcodcon=coocodcon and iextipreg='" + Tipo + "' ";

        return template.query(sql, new ResultSetExtractor<List<Concepto>>() {
            public List<Concepto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Concepto> lista = new ArrayList<Concepto>();

                while (rs.next()) {
                    Concepto con = new Concepto();

                    con.setCodConcepto(rs.getString("coocodcon"));
                    con.setDesConcepto(rs.getString("coodescon"));

                    lista.add(con);
                }
                return lista;
            }
        });
    }

    public void insertarEmpSueldo(EmpSueldo empsueldo) {

        template.update("INSERT INTO iexsueldos (iexcodcia, iexcodtra, iexcodcon, iexvalcon, iexflgest)  VALUES (?, ?, ?, ?, ? )",

                empsueldo.getIexcodcia(),
                empsueldo.getIexcodtra(),
                empsueldo.getIexcodcon(),
                empsueldo.getIexvalcon(),
                empsueldo.getIexflgest());
    }

    public List<EmpDatvar> obtenerEmpDatvar(Integer cia, Integer codpro, String nroper, Integer codtra, Integer correl) {

        String sql = " select  " +
                "v.iexcodcia," +
                "v.iexcodpro, " +
                "v.iexnroper, " +
                "v.iexcorrel, " +
                "v.iexcodtra, " +
                "v.iexcodcon, " +
                "c.coodescon, " +
                "v.iexvalcon, " +
                "v.iexflgest, " +
                "v.iexusucrea, " +
                "v.iexfeccrea, " +
                "v.iexusumod, " +
                "v.iexfecmod " +
                "from iexconcepto c , iexdatavar v " +
                "where " +
                "v.iexcodcia = " + cia + " and  " +
                "v.iexcodpro= " + codpro + "  and " +
                "v.iexnroper = '" + nroper + "' and " +
                "v.iexcorrel = " + correl + "  and " +
                "v.iexcodtra = " + codtra + "  and v.iexcodcon = c.coocodcon ";

        return template.query(sql, new ResultSetExtractor<List<EmpDatvar>>() {
            public List<EmpDatvar> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<EmpDatvar> lista = new ArrayList<EmpDatvar>();

                while (rs.next()) {
                    EmpDatvar con = new EmpDatvar();

                    con.setIexcodcia(rs.getInt("iexcodcia"));
                    con.setIexcodpro(rs.getInt("iexcodpro"));
                    con.setIexnroper(rs.getString("iexnroper"));
                    con.setIexcodtra(rs.getInt("iexcodtra"));
                    con.setIexcorrel(rs.getInt("iexcorrel"));
                    con.setIexcodcon(rs.getString("iexcodcon"));
                    con.setCoodescon(rs.getString("coodescon"));
                    con.setIexvalcon(rs.getDouble("iexvalcon"));

                    lista.add(con);
                }
                return lista;
            }
        });
    }

    public void insertarEmpDatvar(EmpDatvar empdatvar) {

        template.update("INSERT INTO  iexdatavar (iexcodcia, iexcodpro, iexnroper,  iexcorrel, iexcodtra, iexcodcon,  iexvalcon,  iexflgest )  VALUES (?, ?, ?, ?, ?,?,?,? )",
                empdatvar.getIexcodcia(),
                empdatvar.getIexcodpro(),
                empdatvar.getIexnroper(),
                empdatvar.getIexcorrel(),
                empdatvar.getIexcodtra(),
                empdatvar.getIexcodcon(),
                empdatvar.getIexvalcon(),
                empdatvar.getIexflgest());
    }

    public EmpSueldo obtenerOneEmpSueldo(Empleado empleado, String concepto) {

        String sql = "select " +
                "s.iexcodcia," +
                "s.iexcodtra," +
                "s.iexcodcon," +
                "c.coodescon," +
                "s.iexvalcon," +
                "s.iexflgest " +
                "from iexconcepto c, " +
                "iexsueldos s " +
                "where " +
                "s.iexcodcia = " + empleado.getIexcodcia() + " and  " +
                "s.iexcodtra= " + empleado.getIexcodtra() + "  and " +
                "s.iexcodcon= '" + concepto + "'  and " +
                "c.coocodcon=s.iexcodcon ";

        return (EmpSueldo) template.query(sql, new ResultSetExtractor<EmpSueldo>() {
            public EmpSueldo extractData(ResultSet rs) throws SQLException, DataAccessException {
                EmpSueldo con = new EmpSueldo();
                while (rs.next()) {
                    con.setIexcodcia(rs.getInt("iexcodcia"));
                    con.setIexcodtra(rs.getInt("iexcodtra"));
                    con.setIexcodcon(rs.getString("iexcodcon"));
                    con.setDescon(rs.getString("coodescon"));
                    con.setIexvalcon(rs.getDouble("iexvalcon"));
                    con.setIexflgest(rs.getString("iexflgest"));
                }
                return con;
            }
        });
    }

    public void actualizarEmpSueldo(EmpSueldo empsueldo) {

        template.update("update  iexsueldos set iexvalcon=? where iexcodcia=? and iexcodtra=?  and iexcodcon=? ",

                empsueldo.getIexvalcon(),
                empsueldo.getIexcodcia(),
                empsueldo.getIexcodtra(),
                empsueldo.getIexcodcon());

    }

    public void eliminarEmpSueldo(EmpSueldo empsueldo) {

        template.update("delete from  iexsueldos  where iexcodcia=? and iexcodtra=?  and iexcodcon=? ",

                empsueldo.getIexcodcia(),
                empsueldo.getIexcodtra(),
                empsueldo.getIexcodcon());
    }

    public EmpDatvar obtenerOneEmpDatvar(Integer cia, Integer codpro, String nroper, Integer codtra, Integer correl, String concepto) {

        String sql = " select  " +
                "v.iexcodcia," +
                "v.iexcodpro, " +
                "v.iexnroper, " +
                "v.iexcorrel, " +
                "v.iexcodtra, " +
                "v.iexcodcon, " +
                "c.coodescon, " +
                "v.iexvalcon, " +
                "v.iexflgest, " +
                "v.iexusucrea, " +
                "v.iexfeccrea, " +
                "v.iexusumod, " +
                "v.iexfecmod " +
                "from iexconcepto c , iexdatavar v " +
                "where " +
                "v.iexcodcia = " + cia + " and  " +
                "v.iexcodpro= " + codpro + "  and " +
                "v.iexnroper = '" + nroper + "' and " +
                "v.iexcorrel = " + correl + "  and " +
                "v.iexcodtra = " + codtra + "  and  v.iexcodcon='" + concepto + "'  ";

        return (EmpDatvar) template.query(sql, new ResultSetExtractor<EmpDatvar>() {
            public EmpDatvar extractData(ResultSet rs) throws SQLException, DataAccessException {
                EmpDatvar con = new EmpDatvar();
                while (rs.next()) {
                    con.setIexcodcia(rs.getInt("iexcodcia"));
                    con.setIexcodpro(rs.getInt("iexcodpro"));
                    con.setIexnroper(rs.getString("iexnroper"));
                    con.setIexcodtra(rs.getInt("iexcodtra"));
                    con.setIexcorrel(rs.getInt("iexcorrel"));
                    con.setIexcodcon(rs.getString("iexcodcon"));
                    con.setCoodescon(rs.getString("coodescon"));
                    con.setIexvalcon(rs.getDouble("iexvalcon"));
                }
                return con;
            }
        });
    }

    public void actualizarEmpDatvar(EmpDatvar empdatvar) {

        template.update(" update iexdatavar set iexvalcon=?  where iexcodcia=? and iexcodpro=? and iexnroper=?  and  iexcorrel=? and iexcodtra=?  and  iexcodcon=?  ",

                empdatvar.getIexvalcon(),
                empdatvar.getIexcodcia(),
                empdatvar.getIexcodpro(),
                empdatvar.getIexnroper(),
                empdatvar.getIexcorrel(),
                empdatvar.getIexcodtra(),
                empdatvar.getIexcodcon());

    }

    public void eliminarEmpDatvar(EmpDatvar empdatvar) {

        template.update(" delete from iexdatavar  where iexcodcia=? and iexcodpro=? and iexnroper=?  and  iexcorrel=? and iexcodtra=?  and  iexcodcon=?  ",

                empdatvar.getIexcodcia(),
                empdatvar.getIexcodpro(),
                empdatvar.getIexnroper(),
                empdatvar.getIexcorrel(),
                empdatvar.getIexcodtra(),
                empdatvar.getIexcodcon());

    }
}
