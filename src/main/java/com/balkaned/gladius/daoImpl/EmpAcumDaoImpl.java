package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.EmpAcum;
import com.balkaned.gladius.dao.EmpAcumDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

@Repository("EmpAcumDao")
@Slf4j
public class EmpAcumDaoImpl implements EmpAcumDao {


    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }


    public EmpAcum getEmpAcum(Integer codcia, Integer codtra, String anio) {

        String sql = " select  " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexaniotrib, " +
                "iexrem_acum, " +
                "iexrem5taafec_acum, " +
                "iexrenta5ta_acum , " +
                "iexremafec5ta_otrcia, " +
                "iexrent5ta_otrcia, " +
                "iexrem4ta_acum	, " +
                "iexrenta4ta_acum, " +
                "iexremotr_acum	, " +
                "iexrenta_acum, " +
                "iexusucrea, " +
                "iexfeccrea, " +
                "iexusumod, " +
                "iexfecmod " +
                "from  " +
                "iexacumval where iexcodcia="+codcia+" and iexcodtra="+codtra+"  and  iexaniotrib='"+anio+"'"  ;
        return (EmpAcum) template.query(sql, new ResultSetExtractor<EmpAcum>() {
            public EmpAcum extractData(ResultSet rs) throws SQLException, DataAccessException {
                EmpAcum p = new EmpAcum();
                while (rs.next()) {

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexaniotrib(rs.getString("iexaniotrib"));
                    p.setIexrem_acum(rs.getDouble("iexrem_acum"));
                    p.setIexrem5taafec_acum(rs.getDouble("iexrem5taafec_acum"));
                    p.setIexrenta5ta_acum(rs.getDouble("iexrenta5ta_acum"));
                    p.setIexremafec5ta_otrcia(rs.getDouble("iexremafec5ta_otrcia"));
                    p.setIexrent5ta_otrcia(rs.getDouble("iexrent5ta_otrcia"));
                    p.setIexrem4ta_acum(rs.getDouble("iexrem4ta_acum"));
                    p.setIexrenta4ta_acum(rs.getDouble("iexrenta4ta_acum"));
                    p.setIexremotr_acum(rs.getDouble("iexremotr_acum"));
                    p.setIexrenta_acum(rs.getDouble("iexrenta_acum"));
                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));

                }
                return p;
            }
        });
    }


}
