package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.UsuxSys;
import com.balkaned.gladius.dao.UsuxSystemaDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;

@Slf4j
@Repository("UsuxSystemaDao")
public class UsuxSystemaDaoImpl implements UsuxSystemaDao {

    private static final String CLASS_NAME = "UsuxSystemaDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public UsuxSys eligeSystema(Integer codcia, Integer codusu, Integer sys) {

        String sql = "select " +
                "u.IEXCODCIA as IdCodCia, " +
                "u.IEXCODUSU as IdCodUsu, " +
                "y.IEXCODSYS as IdcodSys, " +
                "y.IEXDESSYS as DesSystema, " +
                "p.IEXDESROL as DesRol, " +
                "count(1) as nroOrden " +
                "from IEXUSUXCIA u " +
                "INNER JOIN IEXROLXOPC r ON u.IEXCODROL = r.IEXCODROL " +
                "INNER JOIN IEXOPCIONES o ON r.IEXCODOPC = o.IEXCODOPC " +
                "INNER JOIN IEXSECCION s ON o.IEXCODSEC = s.IEXCODSEC " +
                "INNER JOIN IEXROLES p ON u.IEXCODROL = p.IEXCODROL " +
                "INNER JOIN IEXSYSTEMAS y ON s.IEXCODSYS = y.IEXCODSYS " +
                "where u.IEXCODCIA = :codcia and " +
                "u.IEXCODUSU = :codusu and " +
                "y.IEXCODSYS = :sys " +
                "group by " +
                "u.IEXCODCIA, " +
                "u.IEXCODUSU, " +
                "y.IEXCODSYS, " +
                "y.IEXDESSYS, " +
                "p.IEXDESROL ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codusu", codusu)
                .addValue("sys", sys);

        UsuxSys usu = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(UsuxSys.class));

        return usu;
    }
}
