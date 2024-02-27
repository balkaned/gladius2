package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.UsuxSys;
import com.balkaned.gladius.dao.UsuxSystemaDao;
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

@Repository("UsuxSystemaDao")
@Slf4j
public class UsuxSystemaDaoImpl implements UsuxSystemaDao {

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public UsuxSys eligeSystema(Integer codcia, Integer codusu, Integer sys) {

        String sql = "select " +
                "                        u.IEXCODCIA, " +
                "                        u.IEXCODUSU, " +
                "                        y.IEXCODSYS, " +
                "                        y.IEXDESSYS, " +
                "                        p.IEXDESROL, " +
                "                        count(1) as nroopciones " +
                "                        from " +
                "                        IEXUSUXCIA u   " +
                "                        INNER JOIN IEXROLXOPC r  ON u.IEXCODROL = r.IEXCODROL " +
                "                        INNER JOIN IEXOPCIONES o ON  r.IEXCODOPC = o.IEXCODOPC " +
                "                        INNER JOIN IEXSECCION s  ON  o.IEXCODSEC = s.IEXCODSEC " +
                "                        INNER JOIN IEXROLES p    ON u.IEXCODROL = p.IEXCODROL  " +
                "						INNER JOIN IEXSYSTEMAS y ON s.IEXCODSYS = y.IEXCODSYS                          " +
                "						where " +
                "						 u.IEXCODCIA = "+codcia+" and " +
                "                        u.IEXCODUSU = "+codusu+" and " +
                "                        y.IEXCODSYS= "+sys+"  " +
                "                    group by " +
                "                    u.IEXCODCIA, " +
                "                    u.IEXCODUSU, " +
                "                    y.IEXCODSYS," +
                "                    y.IEXDESSYS, p.IEXDESROL ";

        return (UsuxSys) template.query(sql, new ResultSetExtractor<UsuxSys>() {

            public UsuxSys extractData(ResultSet rs) throws SQLException, DataAccessException {

                UsuxSys usuxsys = new UsuxSys();
                while (rs.next()) {
                    usuxsys.setIdCodCia(rs.getInt("iexcodcia"));
                    usuxsys.setIdCodUsu(rs.getInt("iexcodusu"));
                    usuxsys.setIdcodSys(rs.getInt("iexcodsys"));
                    usuxsys.setDesSystema(rs.getString("iexdessys"));
                    usuxsys.setDesRol(rs.getString("iexdesrol"));
                    usuxsys.setNroOrden(rs.getInt("nroopciones"));
                }
                return usuxsys;
            }
        });
    }

}
