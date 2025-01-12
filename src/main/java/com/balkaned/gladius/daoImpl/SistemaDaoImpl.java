package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.Sistemas;
import com.balkaned.gladius.dao.SistemaDao;
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
@Repository("SistemaDao")
public class SistemaDaoImpl implements SistemaDao {

    private static final String CLASS_NAME = "SistemaDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Sistemas> listarSistemas() {

        String sql = "select " +
                "iexcodsys, " +
                "iexdessys " +
                "from iexsystemas ";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        List<Sistemas> lsSistemas = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Sistemas.class));

        return lsSistemas;
    }

    public void insertarSistemas(Sistemas systema) {

        String sql = "insert into iexsystemas ( " +
                "iexcodsys, iexdessys " +
                " ) values ( " +
                " ?, ? " +
                " ) ";

        jdbc.update(sql,
                systema.getIexcodsys(),
                systema.getIexdessys()
        );
    }

    public Sistemas getSistemas(Integer codsis) {

        String sql = "select  " +
                "iexcodsys, " +
                "iexdessys, " +
                "iexactiondefault " +
                "from iexsystemas " +
                "where iexcodsys = :codsis ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codsis", codsis);

        Sistemas sistemas = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Sistemas.class));

        return sistemas;
    }

    public void actualizarSistemas(Sistemas systema) {

        String sql = "update iexsystemas set iexdessys =? " +
                "where iexcodsys=? ";

        jdbc.update(sql,
                systema.getIexdessys(),
                systema.getIexcodsys()
        );
    }

    public void eliminarSistemas(Sistemas systema) {

        String sql = "delete from iexsystemas " +
                "where iexcodsys=? ";

        jdbc.update(sql,
                systema.getIexcodsys()
        );
    }
}
