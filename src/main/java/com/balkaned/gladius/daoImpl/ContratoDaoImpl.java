package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.ContratoEmp;
import com.balkaned.gladius.models.Empleado;
import com.balkaned.gladius.dao.ContratoDao;
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
@Repository("ContratoDao")
public class ContratoDaoImpl implements ContratoDao {

    private static final String CLASS_NAME = "ContratoDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<ContratoEmp> listarContratoEmp(Empleado empleado) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexcorrel, " +
                "iextipcont, " +
                "d.desdet destipcont, " +
                "to_char(iexfecini,'DD/MM/YYYY') as iexfecini, " +
                "to_char(iexfecfin,'DD/MM/YYYY') as iexfecfin, " +
                "iexmodcont, " +
                "iexmodcont as desmodcont, " +
                "iexusucrea, " +
                "iexusumod, " +
                "iexfeccrea, " +
                "iexfecmod, " +
                "case " +
                "   when iexestado='0' then 'Inactivo' " +
                "   when iexestado='1' then 'Activo' " +
                "   else 'Inactivo' " +
                "   end iexestado " +
                "from iexcontctl, " +
                "   ( select  iexkey, desdet from iexttabled where iexcodtab='12' ) d " +
                "where iexcodcia = :iexcodcia and " +
                "iexcodtra = :iexcodtra and " +
                "iextipcont = d.iexkey ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", empleado.getIexcodcia())
                .addValue("iexcodtra", empleado.getIexcodtra());

        List<ContratoEmp> lsContrEmp = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ContratoEmp.class));

        return lsContrEmp;
    }

    public Integer getIdContratoEmp(ContratoEmp contemp) {

        String sql = "select coalesce(max(iexcorrel),0)+1 as idex " +
                "from iexcontctl " +
                "where iexcodcia = :iexcodcia and iexcodtra = :iexcodtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", contemp.getIexcodcia())
                .addValue("iexcodtra", contemp.getIexcodtra());

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public void insertarContratoEmp(ContratoEmp contemp) {

        String sql = "insert into iexcontctl( " +
                "iexcodcia, iexcodtra, iexcorrel, iextipcont, " +
                "iexfecini, iexfecfin, iexmodcont, iexusucrea, iexestado, " +
                "iexfeccrea " +
                " ) values ( " +
                " ?, ?, ?, ?, " +
                " to_date(?,'DD/MM/YYYY'), to_date(?,'DD/MM/YYYY'), ?, ?, ?, " +
                " current_date " +
                " ) ";

        jdbc.update(sql,
                contemp.getIexcodcia(),
                contemp.getIexcodtra(),
                contemp.getIexcorrel(),
                contemp.getIextipcont(),
                contemp.getIexfecini(),
                contemp.getIexfecfin(),
                contemp.getIexmodcont(),
                contemp.getIexusucrea(),
                contemp.getIexestado()
        );
    }

    public ContratoEmp getContratoEmp(ContratoEmp contemp) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexcorrel, " +
                "iextipcont, " +
                "d.desdet as destipcont, " +
                "to_char(iexfecini,'DD/MM/YYYY') as iexfecini, " +
                "to_char(iexfecfin,'DD/MM/YYYY') as iexfecfin, " +
                "iexmodcont, " +
                "iexmodcont as desmodcont, " +
                "iexusucrea, " +
                "iexusumod, " +
                "iexfeccrea, " +
                "iexfecmod, " +
                "iexestado " +
                "from iexcontctl, (select iexkey, desdet from iexttabled where iexcodtab='12') d " +
                "where iexcodcia = :iexcodcia and " +
                "iexcodtra = :iexcodtra and " +
                "iexcorrel = :iexcorrel and " +
                "iextipcont = d.iexkey ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", contemp.getIexcodcia())
                .addValue("iexcodtra", contemp.getIexcodtra())
                .addValue("iexcorrel", contemp.getIexcorrel());

        ContratoEmp contratoEmp = namedParameterJdbcTemplate.queryForObject(sql,
                namedParameters, BeanPropertyRowMapper.newInstance(ContratoEmp.class));

        return contratoEmp;
    }

    public void actualizarContratoEmp(ContratoEmp contemp) {

        String sql = "update iexcontctl set " +
                "iextipcont=?, iexfecini=to_date(?,'DD/MM/YYYY'), iexfecfin=to_date(?,'DD/MM/YYYY'), " +
                "iexmodcont=?, iexusumod=?, iexfecmod=current_date, iexestado = ? " +
                "where iexcodcia=? and iexcodtra=? and iexcorrel=? ";

        jdbc.update(sql,
                contemp.getIextipcont(),
                contemp.getIexfecini(),
                contemp.getIexfecfin(),
                contemp.getIexmodcont(),
                contemp.getIexusumod(),
                contemp.getIexestado(),
                contemp.getIexcodcia(),
                contemp.getIexcodtra(),
                contemp.getIexcorrel()
        );
    }

    public void eliminarContratoEmp(ContratoEmp contemp) {

        String sql = "delete from iexcontctl where iexcodcia=? and iexcodtra=? and iexcorrel=? ";

        jdbc.update(sql,
                contemp.getIexcodcia(),
                contemp.getIexcodtra(),
                contemp.getIexcorrel()
        );
    }
}
