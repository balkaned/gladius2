package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.Concepto;
import com.balkaned.gladius.dao.ConceptoDao;
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

@Repository("ConceptoDao")
@Slf4j
public class ConceptoDaoImpl implements ConceptoDao {

    private static final String CLASS_NAME = "ConceptoDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Concepto> listardet() {

        String sql = "select " +
                "coocodcon codConcepto, " +
                "coodescon desConcepto, " +
                "coocodforvar desVariable, " +
                "coodesabrev desAbreviacion,  " +
                "coodescripcion descripcion " +
                "from iexconcepto order by coodescon asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        List<Concepto> lsConcept = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Concepto.class));

        return lsConcept;
    }

    @Override
    public List<Concepto> listConceptos() {

        String sql = "select " +
                "coocodcon codConcepto, " +
                "coodescon desConcepto, " +
                "coocodforvar desVariable, " +
                "coodesabrev desAbreviacion, " +
                "coodescripcion descripcion " +
                "from iexconcepto order by coodescon asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        List<Concepto> lsConcept = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Concepto.class));

        return lsConcept;
    }

    @Override
    public List<Concepto> listarConceptoIns(Integer idProceso) {

        String sql = "select " +
                "coocodcon codConcepto, " +
                "coodescon desConcepto " +
                "from iexconcepto " +
                "where coocodcon not in " +
                " (select procodcon from iexproxconcepto " +
                " where procodpro = :procodpro ) ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("procodpro", idProceso);

        List<Concepto> lsConcept = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Concepto.class));

        return lsConcept;
    }

    @Override
    public void insertarConcepto(Concepto concepto) {

        String sql = "insert into iexconcepto " +
                "(coocodcon, " +
                "coodescon, " +
                "coocodforvar, " +
                "coodesabrev, " +
                "coodescripcion) " +
                "values (?, ?, ?, ?, ?)";

        jdbc.update(sql,
                concepto.getCodConcepto(),
                concepto.getDesConcepto(),
                concepto.getDesVariable(),
                concepto.getDesAbreviacion(),
                concepto.getDescripcion());
    }

    @Override
    public Concepto getById(String id) {

        String sql = "select " +
                "coocodcon codConcepto, " +
                "coodescon desConcepto, " +
                "coocodforvar desVariable, " +
                "coodesabrev desAbreviacion, " +
                "coodescripcion descripcion " +
                "from iexconcepto " +
                "where coocodcon = :coocodcon ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("coocodcon", id);

        Concepto concept = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Concepto.class));

        return concept;
    }

    @Override
    public void actualizarConcepto(Concepto concepto) {

        String sql = "update iexconcepto " +
                "set coodescon = ?, " +
                "coocodforvar = ?, " +
                "coodesabrev = ?, " +
                "coodescripcion = ? " +
                "where coocodcon = ? ";

        jdbc.update(sql,
                concepto.getDesConcepto(),
                concepto.getDesVariable(),
                concepto.getDesAbreviacion(),
                concepto.getDescripcion(),
                concepto.getCodConcepto()
        );
    }

    public Concepto recuperar(String id) {

        String sql = "select " +
                "coocodcon codConcepto, " +
                "coodescon desConcepto, " +
                "coocodforvar desVariable, " +
                "coodesabrev desAbreviacion, " +
                "coodescripcion descripcion " +
                "from iexconcepto where TRIM(coocodcon) = TRIM(:id) ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);

        Concepto concepto = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Concepto.class));

        return concepto;
    }

    public void eliminar(String id) {

        String sql = "delete from iexconcepto where trim(coocodcon) = trim(?) ";

        jdbc.update(sql, id);
    }
}