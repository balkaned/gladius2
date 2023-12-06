package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.CuentaContable;
import com.balkaned.gladius.dao.CuentaContableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository("CuentaContableDao")
public class CuentaContableDaoImpl implements CuentaContableDao {
    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    @Override
    public List<CuentaContable> listarCuentasContables() {
        String sqlQuery = "select a.iexccodcta, a.iexdescta, d.desdet " +
                "from iexccontable a " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='65' ) d " +
                "on a.iextipocta = d.iexkey " +
                "where iexcodcia = 1";
        return template.query(sqlQuery, rs -> {
            List<CuentaContable> list = new ArrayList<>();

            while (rs.next()) {
                CuentaContable cuentaContable = new CuentaContable();
                cuentaContable.setIexccodcta(rs.getString("iexccodcta"));
                cuentaContable.setIexdescta(rs.getString("iexdescta"));
                cuentaContable.setDesdet(rs.getString("desdet"));
                list.add(cuentaContable);
            }

            return list;
        });
    }
}
