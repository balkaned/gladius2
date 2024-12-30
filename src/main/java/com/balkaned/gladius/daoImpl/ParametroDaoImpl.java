package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.ParametrosGen;
import com.balkaned.gladius.dao.ParametroDao;
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
@Repository("ParametroDao")
public class ParametroDaoImpl implements ParametroDao {

    private static final String CLASS_NAME = "ParametroDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<ParametrosGen> listarParametrosGen() {

        String sql = "select " +
                "p.iexcodcon, " +
                "c.coodescon as descon, " +
                "p.iextippar, " +
                "g.desdet as destippar, " +
                "p.iexvalcon, " +
                "p.iexdesobs, " +
                "p.iexusucrea, " +
                "p.iexfeccrea, " +
                "p.iexusumod, " +
                "p.iexfecmod " +
                "from iexparameter p, iexconcepto c, " +
                " (select iexkey, desdet from iexttabled where iexcodtab='67') g " +
                "where p.iexcodcon = c.coocodcon and " +
                "p.iextippar = g.iexkey order by p.iextippar, p.iexcodcon asc ";

        SqlParameterSource namedParameter = new MapSqlParameterSource();

        List<ParametrosGen> lsParam = namedParameterJdbcTemplate.query(sql, namedParameter,
                BeanPropertyRowMapper.newInstance(ParametrosGen.class));

        return lsParam;
    }

    public void insertarParametrosGen(ParametrosGen par) {

        String sql = "insert into iexparameter( " +
                "iexcodcon, iextippar, iexvalcon, iexdesobs, " +
                "iexusucrea, iexfeccrea " +
                " ) values ( " +
                " ?, ?, ?, ?, " +
                " ?, current_date " +
                " ) ";

        jdbc.update(sql,
                par.getIexcodcon(),
                par.getIextippar(),
                par.getIexvalcon(),
                par.getIexdesobs(),
                par.getIexusucrea()
        );
    }

    public ParametrosGen getParametrosGen(String codcon) {

        String sql = "select " +
                "p.iexcodcon, " +
                "c.coodescon as descon, " +
                "p.iextippar, " +
                "g.desdet as destippar, " +
                "p.iexvalcon, " +
                "p.iexdesobs, " +
                "p.iexusucrea, " +
                "p.iexfeccrea, " +
                "p.iexusumod, " +
                "p.iexfecmod " +
                "from iexparameter p, iexconcepto c, " +
                " ( select iexkey, desdet from iexttabled where iexcodtab='67') g " +
                " where " +
                " p.iexcodcon = c.coocodcon and " +
                " p.iextippar = g.iexkey and p.iexcodcon = :codcon ";

        SqlParameterSource namedParameter = new MapSqlParameterSource()
                .addValue("codcon", codcon);

        ParametrosGen param = namedParameterJdbcTemplate.queryForObject(sql, namedParameter,
                BeanPropertyRowMapper.newInstance(ParametrosGen.class));

        return param;
    }

    public void actualizarParametrosGen(ParametrosGen par) {

        String sql = "update iexparameter set " +
                "iextippar=?, iexvalcon=?, iexdesobs=?, " +
                "iexusumod=?, iexfecmod = current_date " +
                "where iexcodcon = ? ";

        jdbc.update(sql,
                par.getIextippar(),
                par.getIexvalcon(),
                par.getIexdesobs(),
                par.getIexusumod(),
                par.getIexcodcon()
        );
    }

    public void eliminarParametrosGen(ParametrosGen par) {

        String sql = "delete from iexparameter where iexcodcon = ? ";

        jdbc.update(sql, par.getIexcodcon());
    }
}
