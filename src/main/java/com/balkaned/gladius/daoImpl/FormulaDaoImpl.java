package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.FormulaPlanilla;
import com.balkaned.gladius.dao.FormulaDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("FormulaDao")
@Slf4j
public class FormulaDaoImpl implements FormulaDao {

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    @Override
    public FormulaPlanilla getByIdProcesoIdFormula(Integer idprod, Integer idformula) {
        String sqlQuery = "select " +
                "a.procodpro," +
                "a.forcodfor," +
                "a.proglosa," +
                "a.fordesfor," +
                "a.forcodcon," +
                "c.coodescon," +
                "c.coocodforvar," +
                "a.FORFLGEST, " +
                "a.FORORDEN, " +
                "a.FORTIPOUT fortipout," +
                "a.FORVARDES," +
                "a.FORUSUCREA," +
                "a.FORFECCREA," +
                "a.FORUSUMOD," +
                "a.FORFECMOD, " +
                "a.sqlprogram, " +
                "a.grpeje " +
                "from iexformula_cab a inner join  iexconcepto c on  a.forcodcon= c.coocodcon " +
                "where  " +
                "a.procodpro=" + idprod + "  and  a.forcodfor=" + idformula + " " +
                "order by a.fororden asc     ";
        try {
            return template.query(sqlQuery, rs -> {
                FormulaPlanilla p = new FormulaPlanilla();
                while (rs.next()) {
                    p.setIdProceso(rs.getInt("procodpro"));
                    p.setIdFormula(rs.getInt("forcodfor"));
                    p.setIdConcepto(rs.getString("forcodcon"));
                    p.setDesConcepto(rs.getString("coodescon"));
                    p.setDesGlosa(rs.getString("proglosa"));
                    p.setDesFormula(rs.getString("fordesfor"));
                    p.setNroOrden(rs.getInt("fororden"));
                    p.setDesVar(rs.getString("FORVARDES"));
                    p.setFlgEstado(rs.getString("FORFLGEST"));
                    p.setTipOut(rs.getString("fortipout"));
                    p.setGrpeje(rs.getString("grpeje"));
                    p.setSqlprogram(rs.getString("sqlprogram"));
                }
                return p;
            });
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return null;
        }
    }

    public void actualizar(FormulaPlanilla fplanilla) {

        template.update(" call pl_gestion_formula(?,?, ?, ?, ?, ?, ? ,? , '', '', '1', ?, ?, ?)  ",

                fplanilla.getIdProceso(),
                fplanilla.getIdFormula(),
                fplanilla.getDesGlosa(),
                fplanilla.getDesFormula(),
                fplanilla.getIdConcepto(),
                fplanilla.getFlgEstado(),
                fplanilla.getNroOrden(),
                fplanilla.getTipOut(),
                fplanilla.getGrpeje(),
                fplanilla.getSqlprogram(),
                "2");
    }

    public void insertar(FormulaPlanilla fplanilla){

        template.update(" call pl_gestion_formula(?,0,?,?,?,?,?,?,?,'1','',?,?,?) ",
        fplanilla.getIdProceso(),
        fplanilla.getDesGlosa(),
        fplanilla.getDesFormula(),
        fplanilla.getIdConcepto(),
        fplanilla.getFlgEstado(),
        fplanilla.getNroOrden(),
        fplanilla.getTipOut(),
        fplanilla.getDesVar(),
        fplanilla.getGrpeje(),
        fplanilla.getSqlprogram(),
        "1");

    }

    public void eliminar(Integer idprod, Integer idfor) {

        template.update("  delete from iexformula_cab where procodpro=? and forcodfor=?  ",
                idprod,
                idfor);
    }

    public FormulaPlanilla recuperar(Integer idprod, Integer idformula) {

        String sql="select " +
                "a.procodpro," +
                "a.forcodfor," +
                "a.proglosa," +
                "a.fordesfor," +
                "a.forcodcon," +
                "c.coodescon," +
                "c.coocodforvar," +
                "a.FORFLGEST, " +
                "a.FORORDEN, " +
                "a.FORTIPOUT fortipout," +
                "a.FORVARDES," +
                "a.FORUSUCREA," +
                "a.FORFECCREA," +
                "a.FORUSUMOD," +
                "a.FORFECMOD, " +
                "a.sqlprogram, " +
                "a.grpeje " +
                "from iexformula_cab a inner join  iexconcepto c on  a.forcodcon= c.coocodcon " +
                "where  " +
                "a.procodpro="+idprod+"  and  a.forcodfor="+idformula+" "+
                "order by a.fororden asc     ";

        return (FormulaPlanilla) template.query(sql, new ResultSetExtractor<FormulaPlanilla>() {
            public FormulaPlanilla extractData(ResultSet rs) throws SQLException, DataAccessException {
                FormulaPlanilla p = new FormulaPlanilla();
                while(rs.next()) {
                    p.setIdProceso(rs.getInt("procodpro"));
                    p.setIdFormula(rs.getInt("forcodfor"));
                    p.setIdConcepto(rs.getString("forcodcon"));
                    p.setDesConcepto(rs.getString("coodescon"));
                    p.setDesGlosa(rs.getString("proglosa"));
                    p.setDesFormula(rs.getString("fordesfor"));
                    p.setNroOrden(rs.getInt("fororden"));
                    p.setDesVar(rs.getString("FORVARDES"));
                    p.setFlgEstado(rs.getString("FORFLGEST"));
                    p.setTipOut(rs.getString("fortipout"));
                    p.setGrpeje(rs.getString("grpeje"));
                    p.setSqlprogram(rs.getString("sqlprogram"));
                }
                return p;
            }
        });
    }

    public void grabaVariableResultado(Integer idprod, Integer idformula, String Variable, String resultado){

        template.update("UPDATE iexformula_cab SET forvardes=? , forresult= ? ,forflgest=3  WHERE procodpro = ?  and forcodfor= ? ",

        Variable,
        resultado,
        idprod,
        idformula);
    }
}
