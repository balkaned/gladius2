package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.CuentaContable;
import com.balkaned.gladius.dao.CuentaContableDao;
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

    public CuentaContable getCuentaContable(Integer codcia, String ccontable) {

        String sql="select  " +
                "a.iexcodcia, " +
                "a.iexccodcta, " +
                "a.iexdescta,  " +
                "a.iextipocta, " +
                "d.desdet , " +
                "a.iexusucrea , " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod  " +
                "from  " +
                "iexccontable  a  " +
                " full outer join (select  iexkey, desdet from iexttabled where iexcodtab='65' ) d  on a.iextipocta = d.iexkey " +
                " where iexcodcia="+codcia+" ";

        return (CuentaContable) template.query(sql, new ResultSetExtractor<CuentaContable>() {
            public CuentaContable extractData(ResultSet rs) throws SQLException, DataAccessException {
                CuentaContable p = new CuentaContable();
                while(rs.next()) {
                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexccodcta(rs.getString("iexccodcta"));
                    p.setIexdescta(rs.getString("iexdescta"));
                    p.setIextipocta(rs.getString("iextipocta"));
                    p.setDestipcta(rs.getString("desdet"));
                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));
                }
                return p;
            }
        });
    }

    public void actualizarCuentaContable(CuentaContable ccontable){

        template.update(" update iexccontable set" +
                        "  iexdescta=?  , iextipocta=? ,  " +
                        " iexusumod=?,      iexfecmod=current_date " +
                        " where  iexcodcia=?    and   iexccodcta=? ",

        ccontable.getIexdescta(),
        ccontable.getIextipocta(),
        "1",
        ccontable.getIexcodcia(),
        ccontable.getIexccodcta());
    }

    public void eliminarCuentaContable(CuentaContable ccontable){

        template.update(" delete from  iexccontable   where  iexcodcia=?    and   iexccodcta=? ",

        ccontable.getIexcodcia(),
        ccontable.getIexccodcta());
    }
}
