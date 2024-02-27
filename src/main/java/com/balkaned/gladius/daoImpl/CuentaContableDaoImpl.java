package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.CuentaContable;
import com.balkaned.gladius.dao.CuentaContableDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository("CuentaContableDao")
@Slf4j
public class CuentaContableDaoImpl implements CuentaContableDao {

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

    @Override
    public void insertarCuentaContable(CuentaContable cuentaContable, Integer idCompania) {
        String sqlQuery = "insert into iexccontable (iexcodcia, iexccodcta, iexdescta, iextipocta, iexfeccrea) values (?, ?, ?, ?, current_date)";
        template.update(
                sqlQuery,
                idCompania,
                cuentaContable.getIexccodcta(),
                cuentaContable.getIexdescta(),
                cuentaContable.getDesdet()
        );
    }
}
