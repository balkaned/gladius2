package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.Afp;
import com.balkaned.gladius.dao.AfpDao;
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
@Repository("AfpDao")
public class AfpDaoImpl implements AfpDao {

    private static final String CLASS_NAME = "AfpDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Afp> listar(String text) {

        String sql = "select " +
                "iexpermes, " +
                "iexcodafp, " +
                "desafp, " +
                "iexcomis_fija, " +
                "iexcomis_sflu, " +
                "iexcomis_sflu_mix, " +
                "iexcomis_anual_mix, " +
                "iexprima_seguro, " +
                "iexaporte_oblig, " +
                "iexremmax_asegu, " +
                "iexcomis_onp " +
                "from iexafponpper c, " +
                "( " +
                " select " +
                " iexkey codafp, desdet desafp " +
                " from iexttabled where iexcodtab='11' " +
                ") d where " +
                "c.iexcodafp= d.codafp and iexpermes= :text ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("text", text);

        List<Afp> lsAfp = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Afp.class));

        return lsAfp;
    }

    public void insertar(Afp afp) {

        String sql = "insert into iexafponpper ( " +
                "iexpermes," +
                "iexcodafp," +
                "iexcomis_fija," +
                "iexcomis_sflu," +
                "iexcomis_sflu_mix," +
                "iexcomis_anual_mix," +
                "iexprima_seguro," +
                "iexaporte_oblig," +
                "iexremmax_asegu," +
                "iexcomis_onp " +
                ") values (?,?,?,?,?,?,?,?,?,?) ";

        jdbc.update(sql,
                afp.getIexpermes(),
                afp.getIexcodafp(),
                afp.getIexcomis_fija(),
                afp.getIexcomis_sflu(),
                afp.getIexcomis_sflu_mix(),
                afp.getIexcomis_anual_mix(),
                afp.getIexprima_seguro(),
                afp.getIexaporte_oblig(),
                afp.getIexremmax_asegu(),
                afp.getIexcomis_onp()
        );
    }

    public Afp recuperar(Afp afp) {

        String sql = "select " +
                "iexpermes, " +
                "iexcodafp, " +
                "desafp, " +
                "iexcomis_fija, " +
                "iexcomis_sflu, " +
                "iexcomis_sflu_mix, " +
                "iexcomis_anual_mix, " +
                "iexprima_seguro, " +
                "iexaporte_oblig, " +
                "iexremmax_asegu, " +
                "iexcomis_onp " +
                "from iexafponpper c, " +
                "( " +
                "   select " +
                "   iexkey codafp, desdet desafp " +
                "   from iexttabled where iexcodtab='11' " +
                ") d where " +
                "c.iexcodafp= d.codafp and " +
                "iexpermes= :iexpermes and " +
                "c.iexcodafp= :iexcodafp ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexpermes", afp.getIexpermes())
                .addValue("iexcodafp", afp.getIexcodafp());

        Afp responseAfp = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Afp.class));

        return responseAfp;
    }

    public void actualizar(Afp afp) {

        String sql = "update iexafponpper set " +
                "iexcomis_fija=?, " +
                "iexcomis_sflu=?, " +
                "iexcomis_sflu_mix=?, " +
                "iexcomis_anual_mix=?, " +
                "iexprima_seguro =?, " +
                "iexaporte_oblig=?, " +
                "iexremmax_asegu =?, " +
                "iexcomis_onp=? " +
                "where iexpermes=? and iexcodafp=? ";

        jdbc.update(sql,
                afp.getIexcomis_fija(),
                afp.getIexcomis_sflu(),
                afp.getIexcomis_sflu_mix(),
                afp.getIexcomis_anual_mix(),
                afp.getIexprima_seguro(),
                afp.getIexaporte_oblig(),
                afp.getIexremmax_asegu(),
                afp.getIexcomis_onp(),
                afp.getIexpermes(),
                afp.getIexcodafp()
        );
    }

    public void eliminar(Afp afp) {

        String sql = "delete from iexafponpper where " +
                " iexpermes=? and " +
                " iexcodafp=? ";

        jdbc.update(sql,
                afp.getIexpermes(),
                afp.getIexcodafp()
        );
    }

    public void insertarDuplicado(String perini, String perfin2) {

        String sql = "call pl_replica_afp(?,?) ";

        jdbc.update(sql,
                perini,
                perfin2
        );
    }

}
