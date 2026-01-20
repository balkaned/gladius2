package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.empleado.Domain.EmpAcum;
import com.balkaned.gladius.dao.EmpAcumDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Slf4j
@Repository("EmpAcumDao")
public class EmpAcumDaoImpl implements EmpAcumDao {


    private static final String CLASS_NAME = "EmpAcumDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public EmpAcum getEmpAcum(Integer codcia, Integer codtra, String anio) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexaniotrib, " +
                "iexrem_acum, " +
                "iexrem5taafec_acum, " +
                "iexrenta5ta_acum, " +
                "iexremafec5ta_otrcia, " +
                "iexrent5ta_otrcia, " +
                "iexrem4ta_acum, " +
                "iexrenta4ta_acum, " +
                "iexremotr_acum, " +
                "iexrenta_acum, " +
                "iexusucrea, " +
                "iexfeccrea, " +
                "iexusumod, " +
                "iexfecmod " +
                "from iexacumval " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra and " +
                "iexaniotrib = :anio ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra)
                .addValue("anio", anio);

        try {
            EmpAcum empAcum = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                    BeanPropertyRowMapper.newInstance(EmpAcum.class));

            return empAcum;
        } catch (EmptyResultDataAccessException ex) {
            log.info(CLASS_NAME + " getEmpAcum: No se encontraron resultados ", ex);
            return null;
        }
    }
}
