package com.balkaned.gladius.puesto.Infrastructure;

import com.balkaned.gladius.puesto.Domain.Puesto;
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
@Repository("PuestoDao")
public class PuestoDaoImpl implements PuestoDao {

    private static final String CLASS_NAME = "PuestoDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Puesto> listarPuesto(Integer codcia, String text) {

        String sql = "select " +
                "a.iexcodcia, " +
                "a.iexpuesto, " +
                "a.iexdespuesto, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod, " +
                "a.iexcodcat, " +
                "d.desdet as descodcat " +
                "from iexpuesto a " +
                "full outer join " +
                "   (select  iexkey, desdet from iexttabled where iexcodtab='63' ) d on a.iexcodcat = d.iexkey " +
                "where a.iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<Puesto> puesto = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Puesto.class));

        return puesto;
    }

    public Puesto getPuesto(Integer codcia, String codpuesto) {

        String sql = "select " +
                "a.iexcodcia, " +
                "a.iexpuesto, " +
                "a.iexdespuesto, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod, " +
                "a.iexcodcat, " +
                "d.desdet as descodcat " +
                "from iexpuesto a " +
                "full outer join " +
                "   (select  iexkey, desdet from iexttabled where iexcodtab='63' ) d on a.iexcodcat = d.iexkey " +
                "where a.iexcodcia = :codcia and a.iexpuesto = :codpuesto ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codpuesto", codpuesto);

        Puesto puesto = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Puesto.class));

        return puesto;
    }

    public Integer getIdPuesto(Integer codcia) {

        String sql = "select coalesce(max(cast(iexpuesto as integer)),0)+1 idcont " +
                "from iexpuesto " +
                "where iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public void insertarPuesto(Puesto puesto) {

        String sql = "insert into iexpuesto( " +
                "iexcodcia, iexpuesto, iexdespuesto, iexcodcat, " +
                "iexusucrea, iexfeccrea " +
                " ) values ( " +
                " ?, ?, ?, ?, " +
                " ?, current_date " +
                " ) ";

        jdbc.update(sql,
                puesto.getIexcodcia(),
                puesto.getIexpuesto(),
                puesto.getIexdespuesto(),
                puesto.getIexcodcat(),
                puesto.getIexusucrea()
        );
    }

    public void actualizarPuesto(Puesto puesto) {

        String sql = "update iexpuesto set " +
                "iexdespuesto = ?, iexcodcat = ?, " +
                "iexusucrea = ?, iexfeccrea=current_date " +
                "where iexcodcia = ? and " +
                "iexpuesto = ? ";

        jdbc.update(sql,
                puesto.getIexdespuesto(),
                puesto.getIexcodcat(),
                "1",
                puesto.getIexcodcia(),
                puesto.getIexpuesto()
        );
    }

    public void eliminarPuesto(Puesto puesto) {

        String sql = "delete from iexpuesto " +
                "where iexcodcia=? and " +
                "iexpuesto =? ";

        jdbc.update(sql,
                puesto.getIexcodcia(),
                puesto.getIexpuesto()
        );
    }
}
