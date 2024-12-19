package com.balkaned.gladius.daoImpl;


import com.balkaned.gladius.models.CentroCosto;
import com.balkaned.gladius.dao.CcostoDao;
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

@Repository("CcostoDao")
@Slf4j
public class CcostoDaoImpl implements CcostoDao {

    private static final String CLASS_NAME = "CcostoDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<CentroCosto> listarCentroCosto(Integer codcia, String text) {

        String sql = "select " +
                "a.iexcodcia, " +
                "a.iexccosto, " +
                "a.iexdesccosto, " +
                "a.iexcodcat, " +
                "d.desdet," +
                "a.iexusucrea," +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod " +
                "from " +
                "iexccosto a " +
                "full outer join (select iexkey, desdet from iexttabled " +
                "where iexcodtab='64') d on a.iexcodcat = d.iexkey " +
                "where iexcodcia= :iexcodcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia);

        List<CentroCosto> lsCentr = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(CentroCosto.class));

        return lsCentr;
    }

    public CentroCosto getCentroCosto(Integer codcia, String codccosto) {

        String sql = "select " +
                "a.iexcodcia, " +
                "a.iexccosto, " +
                "a.iexdesccosto, " +
                "a.iexcodcat, " +
                "d.desdet, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod " +
                "from " +
                "iexccosto a " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='64') d " +
                "   on a.iexcodcat = d.iexkey " +
                "where iexcodcia= :iexcodcia and a.iexccosto= :iexccosto ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia)
                .addValue("iexccosto", codccosto);

        CentroCosto centro = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(CentroCosto.class));

        return centro;
    }

    public Integer getIdCentroCosto(Integer codcia) {

        String sql = "select coalesce(max(cast(iexccosto as integer)),0)+1 idcont " +
                "from iexccosto where iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        Integer response = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);

        return response;
    }

    public void insertarCentroCosto(CentroCosto ccosto) {

        String sql = "insert into iexccosto( " +
                " iexcodcia, iexccosto, iexdesccosto, iexcodcat, " +
                " iexusucrea, iexfeccrea " +
                " ) values ( " +
                "  ?, ?, ?, ?, " +
                "  ?, current_date " +
                ") ";

        jdbc.update(sql,
                ccosto.getIexcodcia(),
                ccosto.getIexccosto(),
                ccosto.getIexdesccosto(),
                ccosto.getIexcodcat(),
                "1");
    }

    public void actualizarCentroCosto(CentroCosto ccosto) {

        String sql = "update iexccosto set " +
                "iexdesccosto=?, iexcodcat=?, " +
                "iexusumod =?, iexfecmod=current_date " +
                "where iexcodcia=? and iexccosto=? ";

        jdbc.update(sql,
                ccosto.getIexdesccosto(),
                ccosto.getIexcodcat(),
                ccosto.getIexusumod(),
                ccosto.getIexcodcia(),
                ccosto.getIexccosto());
    }

    public void eliminarCentroCosto(CentroCosto ccosto) {

        String sql = "delete from iexccosto " +
                "where iexcodcia=? and iexccosto=? ";

        jdbc.update(sql,
                ccosto.getIexcodcia(),
                ccosto.getIexccosto());
    }
}
