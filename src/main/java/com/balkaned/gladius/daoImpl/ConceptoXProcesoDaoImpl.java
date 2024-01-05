package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.dao.ConceptoXProcesoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.logging.Logger;

@Repository("ConceptoXProcesoDao")
public class ConceptoXProcesoDaoImpl implements ConceptoXProcesoDao {
	static Logger logger = Logger.getLogger(ConceptoXProcesoDaoImpl.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

	@Override
	public List<ConceptoXProceso> listarTipconCtb(Integer xcodcia, Integer idProceso, String slc_grpconcepto) {
		String sqlQuery = "select    " +
                        " co.procodpro, " +
                        " co.procodcon, " +
                        " cn.coodescon, " +
                        " count(b.iexcodcon)  nro_asignacion " +
                        " from  " +
                        " iexproxconcepto co " +
                        " left join iexctbconf b " +
                        "     on  b.iexcodcia = "+xcodcia+" and  " +
                        "	     b.iexcodpro = "+idProceso+" and  " +
                        "	     b.iexcodpro = co.procodpro  and  " +
                        "		 b.iexcodcon = co.procodcon  " +
                        " left join  iexconcepto cn on  " +
                        "      co.procodcon = cn.coocodcon " +
                        "  where co.procodpro = "+idProceso+"  and co.protipcon = '"+slc_grpconcepto+"' " +
                        "  group by  " +
                        "  co.procodpro,  " +
                         " co.procodcon, " +
                        "  cn.coodescon    ";
		try {
			return template.query(sqlQuery, rs -> {
				List<ConceptoXProceso> list = new java.util.ArrayList<>();
				while (rs.next()) {
					ConceptoXProceso p = new ConceptoXProceso();
					p.setProcodpro(rs.getInt("procodpro"));
					p.setProcodcon(rs.getString("procodcon"));
					p.setCoodescon(rs.getString("coodescon"));
					p.setProvalor(rs.getDouble("nro_asignacion"));
					list.add(p);
				}
				return list;
			});
		}
		catch (Exception e) {
			logger.info("Error en listarTipconCtb: " + e.getMessage());
			return null;
		}
	}
}
