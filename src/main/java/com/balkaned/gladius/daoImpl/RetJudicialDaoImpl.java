package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.Empleado;
import com.balkaned.gladius.models.RetencionJudicial;
import com.balkaned.gladius.dao.RetJudicialDao;
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
@Repository("RetJudicialDao")
public class RetJudicialDaoImpl implements RetJudicialDao {

    private static final String CLASS_NAME = "RetJudicialDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<RetencionJudicial> listarRetencionJudicial(Empleado empleado) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexcorrel, " +
                "iexcodpro, " +
                "p.prodespro as descodpro, " +
                "iextipretjud, " +
                "d.desdet as destipretjud, " +
                "iexresolucion, " +
                "to_char(iexfecini,'DD/MM/YYYY') iexfecini, " +
                "to_char(iexfecfin,'DD/MM/YYYY') iexfecfin, " +
                "iexpordesct, " +
                "ieximpfijo, " +
                "iexusucrea, " +
                "iexfeccrea, " +
                "iexusumod, " +
                "iexfecmod " +
                "from iexretjudic, " +
                " ( select  iexkey, desdet from iexttabled where iexcodtab='58') d, " +
                "iexprocesos p " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra and " +
                "iextipretjud = d.iexkey and " +
                "iexcodpro = p.procodpro ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", empleado.getIexcodcia())
                .addValue("codtra", empleado.getIexcodtra());

        List<RetencionJudicial> lsRetencion = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(RetencionJudicial.class));

        return lsRetencion;
    }

    public Integer getIdRetencionJudicial(RetencionJudicial retjud) {

        String sql = "select coalesce(max(iexcorrel),0)+1 as idex " +
                "from iexretjudic " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", retjud.getIexcodcia())
                .addValue("codtra", retjud.getIexcodtra());

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public void insertarRetencionJudicial(RetencionJudicial retjud) {

        String sql = "insert into iexretjudic( " +
                "iexcodcia, iexcodtra, iexcorrel, iexcodpro, " +
                "iextipretjud, iexresolucion, iexfecini, iexfecfin, " +
                "iexpordesct, ieximpfijo, iexusucrea, iexfeccrea " +
                " ) values ( " +
                " ?, ?, ?, ?, " +
                " ?, ?, to_date(?,'DD/MM/YYYY'), to_date(?,'DD/MM/YYYY'), " +
                " ?, ?, ?, current_date " +
                " ) ";

        jdbc.update(sql,
                retjud.getIexcodcia(),
                retjud.getIexcodtra(),
                retjud.getIexcorrel(),
                retjud.getIexcodpro(),
                retjud.getIextipretjud(),
                retjud.getIexresolucion(),
                retjud.getIexfecini(),
                retjud.getIexfecfin(),
                retjud.getIexpordesct(),
                retjud.getIeximpfijo(),
                "1"
        );
    }

    public RetencionJudicial getRetencionJudicial(RetencionJudicial retjud) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexcorrel, " +
                "iexcodpro, " +
                "p.prodespro as descodpro, " +
                "iextipretjud, " +
                "d.desdet as destipretjud, " +
                "iexresolucion, " +
                "to_char(iexfecini,'DD/MM/YYYY') iexfecini, " +
                "to_char(iexfecfin,'DD/MM/YYYY') iexfecfin, " +
                "iexpordesct, " +
                "ieximpfijo, " +
                "iexusucrea, " +
                "iexfeccrea, " +
                "iexusumod, " +
                "iexfecmod " +
                "from iexretjudic , " +
                " (select iexkey, desdet from iexttabled where iexcodtab='58') d, " +
                " iexprocesos p " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra and " +
                "iexcorrel = :iexcorrel and " +
                "iextipretjud = d.iexkey and " +
                "iexcodpro = p.procodpro ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", retjud.getIexcodcia())
                .addValue("codtra", retjud.getIexcodtra())
                .addValue("iexcorrel", retjud.getIexcorrel());

        RetencionJudicial retencion = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(RetencionJudicial.class));

        return retencion;
    }

    public void actualizarRetencionJudicial(RetencionJudicial retjud) {

        String sql = "update iexretjudic set " +
                "iexcodpro=?, iextipretjud =?, iexresolucion =?, " +
                "iexfecini = to_date(?,'DD/MM/YYYY'), iexfecfin = to_date(?,'DD/MM/YYYY'), " +
                "iexpordesct =?, ieximpfijo =?, iexusumod =?, iexfeccrea = current_date " +
                "where iexcodcia =? and iexcodtra=? and iexcorrel= ? ";

        jdbc.update(sql,
                retjud.getIexcodpro(),
                retjud.getIextipretjud(),
                retjud.getIexresolucion(),
                retjud.getIexfecini(),
                retjud.getIexfecfin(),
                retjud.getIexpordesct(),
                retjud.getIeximpfijo(),
                "1",
                retjud.getIexcodcia(),
                retjud.getIexcodtra(),
                retjud.getIexcorrel()
        );
    }

    public void eliminarRetencionJudicial(RetencionJudicial retjud) {

        String sql = "delete from iexretjudic " +
                "where iexcodcia =? and iexcodtra=? and iexcorrel=? ";

        jdbc.update(sql,
                retjud.getIexcodcia(),
                retjud.getIexcodtra(),
                retjud.getIexcorrel()
        );
    }
}
