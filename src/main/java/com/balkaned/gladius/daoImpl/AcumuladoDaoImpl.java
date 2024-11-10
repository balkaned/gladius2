package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.EmpAcum;
import com.balkaned.gladius.dao.AcumuladoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository("AcumuladoDao")
public class AcumuladoDaoImpl implements AcumuladoDao {

    private static final String CLASS_NAME = "AcumuladoDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<EmpAcum> listarEmpAcum(Integer codcia, Integer codtra) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexaniotrib, " +
                "iexrem_acum, " +
                "iexrem5taafec_acum, " +
                "iexrenta5ta_acum , " +
                "iexremafec5ta_otrcia, " +
                "iexrent5ta_otrcia, " +
                "iexrem4ta_acum	, " +
                "iexrenta4ta_acum, " +
                "iexremotr_acum	, " +
                "iexrenta_acum, " +
                "iexusucrea, " +
                "iexfeccrea, " +
                "iexusumod, " +
                "iexfecmod " +
                "from iexacumval " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra " +
                "order by iexaniotrib asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra);

        List<EmpAcum> lsEmpAcum = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(EmpAcum.class));

        return lsEmpAcum;
    }

    public void insertarEmpAcum(EmpAcum empacu) {

        jdbc.update("insert into iexacumval( " +
                        "iexcodcia, iexcodtra, iexaniotrib, iexrem_acum, " +
                        "iexrem5taafec_acum, iexrenta5ta_acum, iexremafec5ta_otrcia, iexrent5ta_otrcia, " +
                        "iexrem4ta_acum, iexrenta4ta_acum, iexremotr_acum, iexrenta_acum, " +
                        "iexusucrea, iexfeccrea) values ( " +
                        "  ? ,       ?    ,       ?   ,        ?  ," +
                        "  ? ,       ?    ,       ?   ,        ?  ," +
                        "  ? ,       ?    ,       ?   ,        ?  ," +
                        "  ? ,   current_date) ",

                empacu.getIexcodcia(),
                empacu.getIexcodtra(),
                empacu.getIexaniotrib(),
                empacu.getIexrem_acum(),
                empacu.getIexrem5taafec_acum(),
                empacu.getIexrenta5ta_acum(),
                empacu.getIexremafec5ta_otrcia(),
                empacu.getIexrent5ta_otrcia(),
                empacu.getIexrem4ta_acum(),
                empacu.getIexrenta4ta_acum(),
                empacu.getIexremotr_acum(),
                empacu.getIexrenta_acum(),
                empacu.getIexusucrea());
    }

    public Integer validarAnioTrib(EmpAcum empacu) {

        String sql = "select count(iexcodtra) as result " +
                "from iexacumval " +
                "where iexcodcia= :iexcodcia " +
                "and iexcodtra= :iexcodtra " +
                "and iexaniotrib= :iexaniotrib ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", empacu.getIexcodcia())
                .addValue("iexcodtra", empacu.getIexcodtra())
                .addValue("iexaniotrib", empacu.getIexaniotrib());

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
        } catch (NullPointerException ex) {
            log.info(CLASS_NAME + " validarAnioTrib: No se encontraron resultados");
            return 0;
        }
    }

    public EmpAcum getEmpAcum(Integer codcia, Integer codtra, String anio) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexaniotrib, " +
                "iexrem_acum, " +
                "iexrem5taafec_acum, " +
                "iexrenta5ta_acum , " +
                "iexremafec5ta_otrcia, " +
                "iexrent5ta_otrcia, " +
                "iexrem4ta_acum	, " +
                "iexrenta4ta_acum, " +
                "iexremotr_acum	, " +
                "iexrenta_acum, " +
                "iexusucrea, " +
                "iexfeccrea, " +
                "iexusumod, " +
                "iexfecmod " +
                "from iexacumval " +
                "where iexcodcia = :iexcodcia and " +
                "iexcodtra= :iexcodtra and " +
                "iexaniotrib= :iexaniotrib ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia)
                .addValue("iexcodtra", codtra)
                .addValue("iexaniotrib", anio);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                    BeanPropertyRowMapper.newInstance(EmpAcum.class));
        } catch (IncorrectResultSizeDataAccessException ex) {
            log.info(CLASS_NAME + " getEmpAcum: No se encontraron resultados.");
            return null;
        }
    }

    public void actualizarEmpAcum(EmpAcum empacu) {

        String sql = "update iexacumval set " +
                "iexrem_acum=?, " +
                "iexrem5taafec_acum =?, iexrenta5ta_acum =?, iexremafec5ta_otrcia =?, iexrent5ta_otrcia =?, " +
                "iexrem4ta_acum=?, iexrenta4ta_acum=?, iexremotr_acum =?, iexrenta_acum =?, " +
                "iexusucrea=?, iexfecmod=current_date " +
                "where iexcodcia=? and iexcodtra=? and iexaniotrib=? ";

        jdbc.update(sql,
                empacu.getIexrem_acum(),
                empacu.getIexrem5taafec_acum(),
                empacu.getIexrenta5ta_acum(),
                empacu.getIexremafec5ta_otrcia(),
                empacu.getIexrent5ta_otrcia(),
                empacu.getIexrem4ta_acum(),
                empacu.getIexrenta4ta_acum(),
                empacu.getIexremotr_acum(),
                empacu.getIexrenta_acum(),
                empacu.getIexusucrea(),
                empacu.getIexcodcia(),
                empacu.getIexcodtra(),
                empacu.getIexaniotrib());
    }

    public void eliminarEmpAcum(EmpAcum empacu) {

        String sql = "delete from iexacumval " +
                "where iexcodcia=? and iexcodtra=? and iexaniotrib=? ";

        jdbc.update(sql,
                empacu.getIexcodcia(),
                empacu.getIexcodtra(),
                empacu.getIexaniotrib());
    }
}
