package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.Concepto;
import com.balkaned.gladius.models.EmpDatvar;
import com.balkaned.gladius.models.EmpSueldo;
import com.balkaned.gladius.models.Empleado;
import com.balkaned.gladius.dao.SueldoDao;
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
@Repository("SueldoDao")
public class SueldosDaoImpl implements SueldoDao {


    private static final String CLASS_NAME = "SueldoDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<EmpSueldo> obtenerEmpSueldo(Empleado empleado) {

        String sql = "select " +
                "s.iexcodcia, " +
                "s.iexcodtra, " +
                "s.iexcodcon, " +
                "c.coodescon as descon, " +
                "s.iexvalcon, " +
                "s.iexflgest " +
                "from iexconcepto c, " +
                "iexsueldos s " +
                "where s.iexcodcia = :codcia and " +
                "s.iexcodtra = :codtra and " +
                "c.coocodcon = s.iexcodcon ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", empleado.getIexcodcia())
                .addValue("codtra", empleado.getIexcodtra());

        List<EmpSueldo> lsEmplSueld = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(EmpSueldo.class));

        return lsEmplSueld;
    }

    public List<Concepto> ListConceptos(Integer codcia, String Tipo) {

        String sql = "select " +
                "coocodcon as codConcepto, " +
                "coodescon as desConcepto " +
                "from iexciaxcon, iexconcepto " +
                "where iexcodcia = :codcia and " +
                "iexcodcon = coocodcon and " +
                "iextipreg = :tipo ";

        SqlParameterSource namedParameter = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("tipo", Tipo);

        List<Concepto> lsConcept = namedParameterJdbcTemplate.query(sql, namedParameter,
                BeanPropertyRowMapper.newInstance(Concepto.class));

        return lsConcept;
    }

    public void insertarEmpSueldo(EmpSueldo empsueldo) {

        String sql = "insert into iexsueldos (iexcodcia, iexcodtra, iexcodcon, iexvalcon, iexflgest) " +
                "values (?, ?, ?, ?, ? ) ";

        jdbc.update(sql,
                empsueldo.getIexcodcia(),
                empsueldo.getIexcodtra(),
                empsueldo.getIexcodcon(),
                empsueldo.getIexvalcon(),
                empsueldo.getIexflgest()
        );
    }

    public List<EmpDatvar> obtenerEmpDatvar(Integer cia, Integer codpro, String nroper, Integer codtra, Integer correl) {

        String sql = "select  " +
                "v.iexcodcia, " +
                "v.iexcodpro, " +
                "v.iexnroper, " +
                "v.iexcorrel, " +
                "v.iexcodtra, " +
                "v.iexcodcon, " +
                "c.coodescon, " +
                "v.iexvalcon, " +
                "v.iexflgest, " +
                "v.iexusucrea, " +
                "v.iexfeccrea, " +
                "v.iexusumod, " +
                "v.iexfecmod " +
                "from iexconcepto c , iexdatavar v " +
                "where v.iexcodcia = :codcia and  " +
                "v.iexcodpro = :codpro and " +
                "v.iexnroper = :nroper and " +
                "v.iexcorrel = :correl and " +
                "v.iexcodtra = :codtra and " +
                "v.iexcodcon = c.coocodcon ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", cia)
                .addValue("codpro", codpro)
                .addValue("nroper", nroper)
                .addValue("correl", correl)
                .addValue("codtra", codtra);

        List<EmpDatvar> lsEmpDat = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(EmpDatvar.class));

        return lsEmpDat;
    }

    public void insertarEmpDatvar(EmpDatvar empdatvar) {

        String sql = "insert into iexdatavar (iexcodcia, iexcodpro, iexnroper, iexcorrel, " +
                "iexcodtra, iexcodcon, iexvalcon, iexflgest) values (?, ?, ?, ?, ?, ?, ?, ? ) ";

        jdbc.update(sql,
                empdatvar.getIexcodcia(),
                empdatvar.getIexcodpro(),
                empdatvar.getIexnroper(),
                empdatvar.getIexcorrel(),
                empdatvar.getIexcodtra(),
                empdatvar.getIexcodcon(),
                empdatvar.getIexvalcon(),
                empdatvar.getIexflgest()
        );
    }

    public EmpSueldo obtenerOneEmpSueldo(Empleado empleado, String concepto) {

        String sql = "select " +
                "s.iexcodcia, " +
                "s.iexcodtra, " +
                "s.iexcodcon, " +
                "c.coodescon as descon, " +
                "s.iexvalcon, " +
                "s.iexflgest " +
                "from iexconcepto c, " +
                "iexsueldos s " +
                "where s.iexcodcia = :codcia and  " +
                "s.iexcodtra = :codtra and " +
                "s.iexcodcon = :concepto and " +
                "c.coocodcon = s.iexcodcon ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", empleado.getIexcodcia())
                .addValue("codtra", empleado.getIexcodtra())
                .addValue("concepto", concepto);

        EmpSueldo emp = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(EmpSueldo.class));

        return emp;
    }

    public void actualizarEmpSueldo(EmpSueldo empsueldo) {

        String sql = "update iexsueldos set iexvalcon=? " +
                "where iexcodcia=? and " +
                "iexcodtra=? and " +
                "iexcodcon=? ";

        jdbc.update(sql,
                empsueldo.getIexvalcon(),
                empsueldo.getIexcodcia(),
                empsueldo.getIexcodtra(),
                empsueldo.getIexcodcon()
        );

    }

    public void eliminarEmpSueldo(EmpSueldo empsueldo) {

        String sql = "delete from iexsueldos " +
                "where iexcodcia=? and " +
                "iexcodtra=? " +
                "and iexcodcon=? ";

        jdbc.update(sql,
                empsueldo.getIexcodcia(),
                empsueldo.getIexcodtra(),
                empsueldo.getIexcodcon()
        );
    }

    public EmpDatvar obtenerOneEmpDatvar(Integer cia, Integer codpro, String nroper, Integer codtra,
                                         Integer correl, String concepto) {

        String sql = "select  " +
                "v.iexcodcia, " +
                "v.iexcodpro, " +
                "v.iexnroper, " +
                "v.iexcorrel, " +
                "v.iexcodtra, " +
                "v.iexcodcon, " +
                "c.coodescon, " +
                "v.iexvalcon, " +
                "v.iexflgest, " +
                "v.iexusucrea, " +
                "v.iexfeccrea, " +
                "v.iexusumod, " +
                "v.iexfecmod " +
                "from iexconcepto c, iexdatavar v " +
                "where v.iexcodcia = :codcia and " +
                "v.iexcodpro = :codpro and " +
                "v.iexnroper = :nroper and " +
                "v.iexcorrel = :correl and " +
                "v.iexcodtra = :codtra and " +
                "v.iexcodcon = :concepto ";

        log.info("cia: {} ", cia);
        log.info("codpro: {} ", codpro);
        log.info("nroper: {} ", nroper);
        log.info("codtra: {} ", codtra);
        log.info("correl: {} ", correl);
        log.info("concepto: {} ", concepto);

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", cia)
                .addValue("codpro", codpro)
                .addValue("nroper", nroper)
                .addValue("correl", correl)
                .addValue("codtra", codtra)
                .addValue("concepto", concepto);

        List<EmpDatvar> lsEmp = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(EmpDatvar.class));

        log.info("lsEmp: {} ", lsEmp);

        if (!lsEmp.isEmpty()) {
            EmpDatvar empDat = lsEmp.get(0);
            return empDat;
        } else {
            return null;
        }
    }

    public void actualizarEmpDatvar(EmpDatvar empdatvar) {

        String sql = "update iexdatavar set iexvalcon=? " +
                "where iexcodcia=? and " +
                "iexcodpro=? and " +
                "iexnroper=? and " +
                "iexcorrel=? and " +
                "iexcodtra=? and " +
                "iexcodcon=? ";

        jdbc.update(sql,
                empdatvar.getIexvalcon(),
                empdatvar.getIexcodcia(),
                empdatvar.getIexcodpro(),
                empdatvar.getIexnroper(),
                empdatvar.getIexcorrel(),
                empdatvar.getIexcodtra(),
                empdatvar.getIexcodcon()
        );
    }

    public void eliminarEmpDatvar(EmpDatvar empdatvar) {

        String sql = "delete from iexdatavar " +
                "where iexcodcia=? and " +
                "iexcodpro=? and " +
                "iexnroper=? " +
                "and iexcorrel=? and " +
                "iexcodtra=? and " +
                "iexcodcon=? ";

        jdbc.update(sql,
                empdatvar.getIexcodcia(),
                empdatvar.getIexcodpro(),
                empdatvar.getIexnroper(),
                empdatvar.getIexcorrel(),
                empdatvar.getIexcodtra(),
                empdatvar.getIexcodcon()
        );
    }

    public List<Concepto> ListConcepProVar(Integer codcia, Integer codpro, String Tipo) {

        String sql = "select  " +
                "coocodcon as codConcepto, " +
                "coodescon as desConcepto" +
                "from iexciaxcon, iexconcepto, iexproxconcepto " +
                "where procodcon =  iexcodcon and " +
                "iexcodcia = :codcia and " +
                "iexcodcon = coocodcon and " +
                "iextipreg = :tipo and " +
                "procodpro = :codpro ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("tipo", Tipo)
                .addValue("codpro", codpro);

        List<Concepto> lsConcept = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Concepto.class));

        return lsConcept;
    }

    public List<EmpDatvar> obtenerEmpResvar(Integer cia, Integer codpro, String nroper, Integer correl) {

        String sql = "select " +
                "v.iexcodcia, " +
                "v.iexcodpro, " +
                "v.iexnroper, " +
                "v.iexcorrel, " +
                "v.iexcodtra, " +
                " a.iexapepat||' '||a.iexapemat||' '||a.iexnomtra as nomdestra, " +
                "v.iexcodcon, " +
                "c.coodescon, " +
                "v.iexvalcon, " +
                "v.iexflgest, " +
                "v.iexusucrea, " +
                "v.iexfeccrea, " +
                "v.iexusumod, " +
                "v.iexfecmod " +
                "from iexconcepto c, iexdatavar v, iexempleado a " +
                "where v.iexcodcia = :codcia and " +
                "v.iexcodpro = :codpro and " +
                "v.iexnroper = :nroper and " +
                "v.iexcorrel = :correl and " +
                "v.iexcodcon = c.coocodcon and " +
                "a.iexcodcia = v.iexcodcia and " +
                "a.iexcodtra = v.iexcodtra " +
                "order by 6 asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", cia)
                .addValue("codpro", codpro)
                .addValue("nroper", nroper)
                .addValue("correl", correl);

        List<EmpDatvar> lsEmpDat = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(EmpDatvar.class));

        return lsEmpDat;
    }

    public void eliminarAllDatvar(Integer cia, Integer codpro, String nroper, Integer correl) {

        String sql = "delete from iexdatavar " +
                "where iexcodcia=? and " +
                "iexcodpro=? and " +
                "iexnroper=? and " +
                "iexcorrel=? ";

        jdbc.update(sql,
                cia,
                codpro,
                nroper,
                correl
        );
    }

    public void insertarDatvarmas(List<EmpDatvar> empdatvar) {

        String sql = "";

        for (EmpDatvar empdat : empdatvar) {

            sql = "insert into iexdatavar ( " +
                    "iexcodcia, iexcodpro, iexnroper, iexcorrel, iexcodtra, " +
                    "iexcodcon, iexvalcon , iexusucrea, iexfeccrea, iexfecmod, iexusumod " +
                    " ) values( " +
                    " ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "current_timestamp, current_timestamp, ? " +
                    " ) ";

            jdbc.update(sql,
                    empdat.getIexcodcia(),
                    empdat.getIexcodpro(),
                    empdat.getIexnroper(),
                    empdat.getIexcorrel(),
                    empdat.getIexcodtra(),
                    empdat.getIexcodcon(),
                    empdat.getIexvalcon(),
                    empdat.getIexusucrea(),
                    empdat.getIexusucrea()
            );
        }
    }

    public void eliminarAllDatvarEmp(Integer cia, Integer codpro, String nroper, Integer correl, Integer codtra, String concepto) {

        String sql = "delete from iexdatavar " +
                "where iexcodcia=? and " +
                "iexcodpro=? and " +
                "iexnroper=? " +
                "and iexcorrel=? and " +
                "iexcodtra=? and " +
                "iexcodcon =? ";

        jdbc.update(sql,
                cia,
                codpro,
                nroper,
                correl,
                codtra,
                concepto
        );
    }
}
