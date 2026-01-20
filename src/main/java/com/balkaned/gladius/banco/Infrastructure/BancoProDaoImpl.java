package com.balkaned.gladius.banco.Infrastructure;


import com.balkaned.gladius.banco.Domain.BancoPro;
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
@Repository("BancoProDao")
public class BancoProDaoImpl implements BancoProDao {

    private static final String CLASS_NAME = "BancoProDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<BancoPro> listarBancoPro(Integer codcia, String text) {

        String sql = "select " +
                "a.iexcodcia, " +
                "a.iexcodban, " +
                "c.desdet as desban, " +
                "a.iexcodpro, " +
                "f.prodespro, " +
                "a.iextipcta, " +
                "d.desdet as destipcta, " +
                "a.iexctaban, " +
                "a.iexusucrea, " +
                "a.iexfeccrea, " +
                "a.iexusumod, " +
                "a.iexfecmod " +
                "from iexprobancos a " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='36' ) c " +
                "   on a.iexcodban = c.iexkey " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='66' ) d " +
                "   on a.iextipcta = d.iexkey " +
                "full outer join iexprocesos f on a.iexcodpro = f.procodpro " +
                "where iexcodcia= :iexcodcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia);

        List<BancoPro> lsBanPro = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(BancoPro.class));

        return lsBanPro;
    }

    public BancoPro getBancoPro(Integer codcia, Integer codpro, String banco) {

        String sql = "select " +
                "a.iexcodcia, " +
                "a.iexcodban, " +
                "c.desdet as desban, " +
                "a.iexcodpro, " +
                "f.prodespro, " +
                "a.iextipcta, " +
                "d.desdet as destipcta, " +
                "a.iexctaban, " +
                "a.iexusucrea, " +
                "a.iexfeccrea, " +
                "a.iexusumod, " +
                "a.iexfecmod " +
                "from iexprobancos a " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='36' ) c " +
                "   on a.iexcodban = c.iexkey " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='66' ) d " +
                "   on a.iextipcta = d.iexkey " +
                "full outer join iexprocesos f on a.iexcodpro =f.procodpro " +
                "where iexcodcia = :iexcodcia and " +
                "iexcodban = :iexcodban and " +
                "a.iexcodpro = :iexcodpro ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia)
                .addValue("iexcodban", banco)
                .addValue("iexcodpro", codpro);

        BancoPro banpro = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(BancoPro.class));

        return banpro;
    }

    public void insertarBancoPro(BancoPro bancopro) {

        String sql = "insert into iexprobancos( " +
                "iexcodcia, iexcodban, iexcodpro, iextipcta, " +
                "iexctaban, iexusucrea, iexfeccrea " +
                " ) values ( " +
                " ?, ?, ?, ?, " +
                " ?, ?, current_date " +
                " ) ";

        jdbc.update(sql,
                bancopro.getIexcodcia(),
                bancopro.getIexcodban(),
                bancopro.getIexcodpro(),
                bancopro.getIextipcta(),
                bancopro.getIexctaban(),
                bancopro.getIexusucrea()
        );
    }

    public void actualizarBancoPro(BancoPro bancopro) {

        String sql = "update iexprobancos set " +
                "iextipcta = ?, iexctaban =?, iexusumod=?, iexfecmod = current_date " +
                "where iexcodcia=? and " +
                "iexcodban=? and " +
                "iexcodpro=? ";

        jdbc.update(sql,
                bancopro.getIextipcta(),
                bancopro.getIexctaban(),
                bancopro.getIexusumod(),
                bancopro.getIexcodcia(),
                bancopro.getIexcodban(),
                bancopro.getIexcodpro()
        );
    }

    public void eliminarBancoPro(BancoPro bancopro) {

        String sql = "delete from iexprobancos " +
                "where iexcodcia=? and " +
                "iexcodban=? and iexcodpro=? ";

        jdbc.update(sql,
                bancopro.getIexcodcia(),
                bancopro.getIexcodban(),
                bancopro.getIexcodpro()
        );
    }
}
