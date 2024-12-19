package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.CuentaContable;
import com.balkaned.gladius.dao.CuentaContableDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository("CuentaContableDao")
public class CuentaContableDaoImpl implements CuentaContableDao {

    private static final String CLASS_NAME = "CuentaContableDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    @Override
    public List<CuentaContable> listarCuentasContables() {

        String sql = "select a.iexccodcta, a.iexdescta, d.desdet " +
                "from iexccontable a " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='65' ) d " +
                "on a.iextipocta = d.iexkey " +
                "where iexcodcia = 1";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        List<CuentaContable> lsCuentaContab = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(CuentaContable.class));

        return lsCuentaContab;
    }

    @Override
    public void insertarCuentaContable(CuentaContable cuentaContable, Integer idCompania) {
        String sql = "insert into iexccontable (iexcodcia, iexccodcta, iexdescta, iextipocta, iexfeccrea) " +
                "values (?, ?, ?, ?, current_date) ";

        jdbc.update(sql,
                idCompania,
                cuentaContable.getIexccodcta(),
                cuentaContable.getIexdescta(),
                cuentaContable.getDesdet()
        );
    }

    public CuentaContable getCuentaContable(Integer codcia, String ccontable) {

        String sql = "select " +
                "a.iexcodcia, " +
                "a.iexccodcta, " +
                "a.iexdescta, " +
                "a.iextipocta, " +
                "d.desdet, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod " +
                "from " +
                "iexccontable a " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='65') d on a.iextipocta = d.iexkey " +
                "where iexcodcia= :iexcodcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia);

        CuentaContable cuentaCont = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(CuentaContable.class));

        return cuentaCont;
    }

    public void actualizarCuentaContable(CuentaContable ccontable) {

        String sql = "update iexccontable set " +
                "iexdescta=?, iextipocta=?, " +
                "iexusumod=?, iexfecmod=current_date " +
                "where iexcodcia=? and iexccodcta=? ";

        jdbc.update(sql,
                ccontable.getIexdescta(),
                ccontable.getIextipocta(),
                "1",
                ccontable.getIexcodcia(),
                ccontable.getIexccodcta());
    }

    public void eliminarCuentaContable(CuentaContable ccontable) {

        String sql = "delete from iexccontable where iexcodcia=? and iexccodcta=? ";

        jdbc.update(sql, ccontable.getIexcodcia(),
                ccontable.getIexccodcta());
    }
}
