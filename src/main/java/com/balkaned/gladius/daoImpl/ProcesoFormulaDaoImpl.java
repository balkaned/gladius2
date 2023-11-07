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
        String sqlQuery = "select " +
                "frm.procodpro, " +
                "frm.forcodfor, " +
                "frm.proglosa, " +
                "frm.fordesfor, " +
                "frm.forcodcon, " +
                "frm.forflgest, " +
                "frm.fororden, " +
                "frm.fortipout, " +
                "frm.forvardes, " +
                "frm.forusucrea, " +
                "frm.forfeccrea, " +
                "frm.forusumod, " +
                "frm.forfecmod, " +
                "frm.forresult, " +
                "frm.sqlprogram, " +
                "frm.grpeje, " +
                "con.coocodcon, " +
                "con.coodescon, " +
                "con.coocodforvar, " +
                "con.coodesabrev, " +
                "con.coodescripcion " +
                "from iexformula_cab frm " +
                "inner join iexconcepto con " +
                "on frm.forcodcon = con.coocodcon " +
                "where frm.procodpro = 1 " +
                "order by frm.fororden asc";
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
