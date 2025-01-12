package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.FormulaPlanilla;
import com.balkaned.gladius.dao.FormulaDao;
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
@Repository("FormulaDao")
public class FormulaDaoImpl implements FormulaDao {

    private static final String CLASS_NAME = "FormulaDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    @Override
    public FormulaPlanilla getByIdProcesoIdFormula(Integer idprod, Integer idformula) {

        String sql = "select " +
                "a.procodpro as idProceso, " +
                "a.forcodfor as idFormula, " +
                "a.proglosa as desGlosa, " +
                "a.fordesfor as desFormula, " +
                "a.forcodcon as idConcepto, " +
                "c.coodescon as desConcepto, " +
                "c.coocodforvar, " +
                "a.FORFLGEST as flgEstado, " +
                "a.FORORDEN as nroOrden, " +
                "a.FORTIPOUT as tipOut, " +
                "a.FORVARDES as desVar, " +
                "a.FORUSUCREA, " +
                "a.FORFECCREA, " +
                "a.FORUSUMOD, " +
                "a.FORFECMOD, " +
                "a.sqlprogram, " +
                "a.grpeje " +
                "from iexformula_cab a inner join iexconcepto c on a.forcodcon = c.coocodcon " +
                "where a.procodpro = :idprod and a.forcodfor = :idformula " +
                "order by a.fororden asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("idprod", idprod)
                .addValue("idformula", idformula);

        FormulaPlanilla formulaPlanilla = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(FormulaPlanilla.class));

        return formulaPlanilla;
    }

    public void actualizar(FormulaPlanilla fplanilla) {

        String sql = "call pl_gestion_formula (?,?, ?, ?, ?, ?, ? ,? , '', '', '1', ?, ?, ?) ";

        jdbc.update(sql,
                fplanilla.getIdProceso(),
                fplanilla.getIdFormula(),
                fplanilla.getDesGlosa(),
                fplanilla.getDesFormula(),
                fplanilla.getIdConcepto(),
                fplanilla.getFlgEstado(),
                fplanilla.getNroOrden(),
                fplanilla.getTipOut(),
                fplanilla.getGrpeje(),
                fplanilla.getSqlprogram(),
                "2"
        );
    }

    public void insertar(FormulaPlanilla fplanilla) {

        String sql = "call pl_gestion_formula(?,0,?,?,?,?,?,?,?,'1','',?,?,?) ";

        jdbc.update(sql,
                fplanilla.getIdProceso(),
                fplanilla.getDesGlosa(),
                fplanilla.getDesFormula(),
                fplanilla.getIdConcepto(),
                fplanilla.getFlgEstado(),
                fplanilla.getNroOrden(),
                fplanilla.getTipOut(),
                fplanilla.getDesVar(),
                fplanilla.getGrpeje(),
                fplanilla.getSqlprogram(),
                "1"
        );
    }

    public void eliminar(Integer idprod, Integer idfor) {

        String sql = "delete from iexformula_cab " +
                "where procodpro=? and " +
                "forcodfor=? ";

        jdbc.update(sql,
                idprod,
                idfor
        );
    }

    public FormulaPlanilla recuperar(Integer idprod, Integer idformula) {

        String sql = "select " +
                "a.procodpro as idProceso, " +
                "a.forcodfor as idFormula, " +
                "a.proglosa as desGlosa, " +
                "a.fordesfor as desFormula, " +
                "a.forcodcon as idConcepto, " +
                "c.coodescon as desConcepto, " +
                "c.coocodforvar, " +
                "a.FORFLGEST as flgEstado, " +
                "a.FORORDEN as nroOrden, " +
                "a.FORTIPOUT tipOut, " +
                "a.FORVARDES as desVar, " +
                "a.FORUSUCREA, " +
                "a.FORFECCREA, " +
                "a.FORUSUMOD, " +
                "a.FORFECMOD, " +
                "a.sqlprogram, " +
                "a.grpeje " +
                "from iexformula_cab a inner join iexconcepto c on a.forcodcon = c.coocodcon " +
                "where a.procodpro = :idprod and " +
                "a.forcodfor = :idformula " +
                "order by a.fororden asc ";

        SqlParameterSource namedParameter = new MapSqlParameterSource()
                .addValue("idprod", idprod)
                .addValue("idformula", idformula);

        FormulaPlanilla formulaPlanilla = namedParameterJdbcTemplate.queryForObject(sql, namedParameter,
                BeanPropertyRowMapper.newInstance(FormulaPlanilla.class));

        return formulaPlanilla;
    }

    public void grabaVariableResultado(Integer idprod, Integer idformula, String Variable, String resultado) {

        String sql = "UPDATE iexformula_cab SET forvardes=?, forresult= ?, forflgest=3 " +
                "WHERE procodpro = ? and " +
                "forcodfor= ? ";

        jdbc.update(sql,
                Variable,
                resultado,
                idprod,
                idformula
        );
    }
}
