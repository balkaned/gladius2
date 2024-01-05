package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.FormulaPlanilla;
import com.balkaned.gladius.dao.FormulaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.logging.Logger;

@Repository("FormulaDao")
public class FormulaDaoImpl implements FormulaDao {
	static Logger logger = Logger.getLogger(FormulaDaoImpl.class.getName());

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
                        "a.procodpro="+idprod+"  and  a.forcodfor="+idformula+" "+
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
		}
		catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			return null;
		}
	}
}
