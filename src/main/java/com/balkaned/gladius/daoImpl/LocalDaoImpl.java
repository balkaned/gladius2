package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.Local;
import com.balkaned.gladius.dao.LocalDao;
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
@Repository("LocalDao")
public class LocalDaoImpl implements LocalDao {

    private static final String CLASS_NAME = "LocalDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Local> listarLocales(Integer codcia, String text) {

        String sql = "select " +
                "a.iexcodcia, " +
                "a.iexubicod, " +
                "a.iexubides, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod " +
                "from iexubicacion a " +
                "where a.iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<Local> lsLocal = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Local.class));

        return lsLocal;
    }

    public Local getLocales(Integer codcia, String codubicacion) {

        String sql = "select  " +
                "a.iexcodcia, " +
                "a.iexubicod, " +
                "a.iexubides, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod " +
                "from iexubicacion a " +
                "where a.iexcodcia = :codcia and iexubicod = ':codubicacion' ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codubicacion", codubicacion);

        Local local = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Local.class));

        return local;
    }

    public Integer getIdUbicaion(Integer codcia) {

        String sql = "select coalesce(max(cast(iexubicod as integer)),0)+1 idcont " +
                "from iexubicacion where iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public void insertarUbicacion(Local ubic) {

        String sql = "insert into iexubicacion( " +
                "iexcodcia, iexubicod, iexubides, " +
                "iexusucrea, iexfeccrea " +
                " ) values ( " +
                "  ? ,   ?    ,   ?   ,  " +
                "  ? , current_date " +
                ") ";

        jdbc.update(sql,
                ubic.getIexcodcia(),
                ubic.getIexubicod(),
                ubic.getIexubides(),
                ubic.getIexusucrea()
        );
    }

    public void actualizarUbicaion(Local ubic) {

        String sql = "update iexubicacion set " +
                "iexubides=?, " +
                "iexusumod=?, iexfecmod = current_date " +
                "where iexcodcia=? and iexubicod = ? ";

        jdbc.update(sql,
                ubic.getIexubides(),
                ubic.getIexusumod(),
                ubic.getIexcodcia(),
                ubic.getIexubicod()
        );
    }

    public void eliminarUbicacion(Local ubic) {

        String sql = "delete from iexubicacion " +
                "where iexcodcia=? and iexubicod = ? ";

        jdbc.update(sql,
                ubic.getIexcodcia(),
                ubic.getIexubicod()
        );
    }
}
