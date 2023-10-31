package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.FormulaXConcepto;
import com.balkaned.gladius.beans.ProcesoForm;
import com.balkaned.gladius.dao.ProcesoFormulaDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository("ProFoDao")
public class ProcesoFormulaDaoImpl implements ProcesoFormulaDao {
    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    @Override
    public List<ProcesoForm> listProcesoFormula() {
        String sqlQuery = "SELECT " +
                "	procodpro, " +
                "	prodespro, " +
                "	prodescorto, " +
                "	procodregimenlab, " +
                "	progrppro, " +
                "	bolproceso, " +
                "	idtipproceso, " +
                "	bolprocesoind, " +
                "	bolprocesores, " +
                "	diasteo " +
                "FROM iexprocesos " +
                "ORDER BY procodpro ASC";
        return template.query(sqlQuery, rs -> {
            List<ProcesoForm> list = new ArrayList<>();

            while (rs.next()) {
                ProcesoForm proFo = new ProcesoForm();
                proFo.setProcodpro(rs.getInt("procodpro"));
                proFo.setProdespro(rs.getString("prodespro"));
                proFo.setProdescorto(rs.getString("prodescorto"));
                proFo.setProcodregimenlab(rs.getString("procodregimenlab"));
                proFo.setProgrppro(rs.getString("progrppro"));
                proFo.setBolproceso(rs.getString("bolproceso"));
                proFo.setIdtipproceso(rs.getString("idtipproceso"));
                proFo.setBolprocesoind(rs.getString("bolprocesoind"));
                proFo.setBolprocesores(rs.getString("bolprocesores"));
                proFo.setDiasteo(rs.getInt("diasteo"));

                list.add(proFo);
            }

            return list;
        });
    }

    @Override
    public List<FormulaXConcepto> listFormulaXConcepto() {
        String sqlQuery = "select frm.procodpro,\n" +
                "       frm.forcodfor,\n" +
                "       frm.proglosa,\n" +
                "       frm.fordesfor,\n" +
                "       frm.forcodcon,\n" +
                "       frm.forflgest,\n" +
                "       frm.fororden,\n" +
                "       frm.fortipout,\n" +
                "       frm.forvardes,\n" +
                "       frm.forusucrea,\n" +
                "       frm.forfeccrea,\n" +
                "       frm.forusumod,\n" +
                "       frm.forfecmod,\n" +
                "       frm.forresult,\n" +
                "       frm.sqlprogram,\n" +
                "       frm.grpeje,\n" +
                "       con.coocodcon,\n" +
                "       con.coodescon,\n" +
                "       con.coocodforvar,\n" +
                "       con.coodesabrev,\n" +
                "       con.coodescripcion\n" +
                "from iexformula_cab frm\n" +
                "inner join iexconcepto con\n" +
                "on frm.forcodcon = con.coocodcon\n" +
                "ORDER BY frm.fororden ASC";
        return template.query(sqlQuery, rs -> {
            List<FormulaXConcepto> list = new ArrayList<>();

            while (rs.next()) {
                FormulaXConcepto proFo = new FormulaXConcepto();
                proFo.setFormprocodpro(rs.getString("procodpro"));
                proFo.setFormforcodfor(rs.getString("forcodfor"));
                proFo.setFormproglosa(rs.getString("proglosa"));
                proFo.setFormfordesfor(rs.getString("fordesfor"));
                proFo.setFormforcodcon(rs.getString("forcodcon"));
                proFo.setFormforflgest(rs.getString("forflgest"));
                proFo.setFormfororden(rs.getString("fororden"));
                proFo.setFormfortipout(rs.getString("fortipout"));
                proFo.setFormforvardes(rs.getString("forvardes"));
                proFo.setFormforusucrea(rs.getString("forusucrea"));
                proFo.setFormforfeccrea(rs.getString("forfeccrea"));
                proFo.setFormforusumod(rs.getString("forusumod"));
                proFo.setFormforfecmod(rs.getString("forfecmod"));
                proFo.setFormforresult(rs.getString("forresult"));
                proFo.setFormsqlprogram(rs.getString("sqlprogram"));
                proFo.setFormgrpeje(rs.getString("grpeje"));
                proFo.setConccoocodcon(rs.getString("coocodcon"));
                proFo.setConccoodescon(rs.getString("coodescon"));
                proFo.setConccoocodforvar(rs.getString("coocodforvar"));
                proFo.setConccoodesabrev(rs.getString("coodesabrev"));
                proFo.setConccoodescripcion(rs.getString("coodescripcion"));

                list.add(proFo);
            }

            return list;
        });
    }
}
