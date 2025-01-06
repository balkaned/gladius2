package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.Empleado;
import com.balkaned.gladius.models.PrestamoCab;
import com.balkaned.gladius.models.PrestamoDet;
import com.balkaned.gladius.dao.PrestamoDao;
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
@Repository("PrestamoDao")
public class PrestamoDaoImpl implements PrestamoDao {

    private static final String CLASS_NAME = "PrestamoDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<PrestamoCab> listarPrestamoCab(Empleado empleado) {

        String sql = "select " +
                "c.iexcodcia, " +
                "c.iexcodtra, " +
                "c.iexcorrel, " +
                "c.iextippres, " +
                "i.desdet destippres, " +
                "c.iextipinteres, " +
                "j.desdet destipinteres, " +
                "c.iexfrecuencia, " +
                "j.desdet destipfrecuencia, " +
                "to_char(c.iexfecpres,'DD/MM/YYYY') iexfecpres, " +
                "to_char(c.iexfecinivig,'DD/MM/YYYY') iexfecinivig, " +
                "c.iexnrocuotas, " +
                "c.ieximpbru, " +
                "c.iexinteres, " +
                "c.ieximptotal, " +
                "c.iexglosa, " +
                "c.iexusucrea, " +
                "to_char(c.iexfeccrea,'DD/MM/YYYY') iexfeccrea, " +
                "c.iexusumod, " +
                "to_char(c.iexfecmod,'DD/MM/YYYY') iexfecmod, " +
                "c.iexestado " +
                "from iexprestamocab c, " +
                " (select iexkey, desdet from iexttabled where iexcodtab='59') i, " +
                " (select iexkey, desdet from iexttabled where iexcodtab='60') j, " +
                " (select iexkey, desdet from iexttabled where iexcodtab='61') h " +
                "where c.iextippres =  i.iexkey and " +
                "c.iextipinteres = j.iexkey and " +
                "c.iexfrecuencia = h.iexkey and " +
                "iexcodcia = :codcia and " +
                "iexcodtra = :codtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", empleado.getIexcodcia())
                .addValue("codtra", empleado.getIexcodtra());

        List<PrestamoCab> lsPres = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(PrestamoCab.class));

        return lsPres;
    }

    public Integer getIdPrestamoCab(PrestamoCab prestcab) {

        String sql = "select coalesce(max(iexcorrel),0)+1 as idex " +
                "from iexprestamocab " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", prestcab.getIexcodcia())
                .addValue("codtra", prestcab.getIexcodtra());

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public void insertarPrestamoCab(PrestamoCab prestcab) {

        String sql = "insert into iexprestamocab( " +
                "iexcodcia, iexcodtra, iexcorrel, iextippres, " +
                "iextipinteres, iexfrecuencia, iexfecpres, iexfecinivig, " +
                "iexnrocuotas, ieximpbru, iexinteres, ieximptotal, " +
                "iexglosa, iexestado, iexusucrea, iexfeccrea " +
                " ) values ( " +
                " ?, ?, ?, ?, " +
                " ?, ?, to_date(?,'DD/MM/YYYY'), to_date(?,'DD/MM/YYYY'), " +
                " ?, ?, ?, ?, " +
                " ?, ?, ?, current_date " +
                " ) ";

        jdbc.update(sql,
                prestcab.getIexcodcia(),
                prestcab.getIexcodtra(),
                prestcab.getIexcorrel(),
                prestcab.getIextippres(),
                prestcab.getIextipinteres(),
                prestcab.getIexfrecuencia(),
                prestcab.getIexfecpres(),
                prestcab.getIexfecinivig(),
                prestcab.getIexnrocuotas(),
                prestcab.getIeximpbru(),
                prestcab.getIexinteres(),
                prestcab.getIeximptotal(),
                prestcab.getIexglosa(),
                prestcab.getIexestado(),
                prestcab.getIexusucrea()
        );

        generacuotasPrestamoCab(prestcab);

    }

    public void generacuotasPrestamoCab(PrestamoCab prestcab) {

        String sql = "call pl_prestamo_cuotas(?,?,?) ";

        jdbc.update(sql,
                prestcab.getIexcodcia(),
                prestcab.getIexcodtra(),
                prestcab.getIexcorrel()
        );
    }

    public PrestamoCab getPrestamoCab(PrestamoCab prestcab) {

        String sql = "select " +
                "c.iexcodcia, " +
                "c.iexcodtra, " +
                "c.iexcorrel, " +
                "c.iextippres, " +
                "i.desdet destippres, " +
                "c.iextipinteres, " +
                "j.desdet destipinteres, " +
                "c.iexfrecuencia, " +
                "j.desdet destipfrecuencia, " +
                "to_char(c.iexfecpres,'DD/MM/YYYY') iexfecpres, " +
                "to_char(c.iexfecinivig,'DD/MM/YYYY') iexfecinivig, " +
                "c.iexnrocuotas, " +
                "c.ieximpbru, " +
                "c.iexinteres, " +
                "c.ieximptotal, " +
                "c.iexglosa, " +
                "c.iexusucrea, " +
                "to_char(c.iexfeccrea,'DD/MM/YYYY') iexfeccrea, " +
                "c.iexusumod, " +
                "to_char(c.iexfecmod,'DD/MM/YYYY') iexfecmod, " +
                "c.iexestado " +
                "from iexprestamocab c, " +
                " ( select iexkey, desdet from iexttabled where iexcodtab='59') i, " +
                " ( select iexkey, desdet from iexttabled where iexcodtab='60') j, " +
                " ( select iexkey, desdet from iexttabled where iexcodtab='61') h " +
                "where c.iextippres =  i.iexkey and " +
                "c.iextipinteres = j.iexkey and " +
                "c.iexfrecuencia =  h.iexkey and iexcodcia = :codcia and " +
                "iexcodtra = :codtra and iexcorrel = :iexcorrel ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", prestcab.getIexcodcia())
                .addValue("codtra", prestcab.getIexcodtra())
                .addValue("iexcorrel", prestcab.getIexcorrel());

        PrestamoCab pres = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(PrestamoCab.class));

        return pres;
    }

    public List<PrestamoDet> listarPrestamoDet(PrestamoCab prestcab) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexcorrel, " +
                "iexidcuota, " +
                "to_char(iexfecpre,'DD/MM/YYYY') iexfecpre, " +
                "ieximpbru, " +
                "iexinteres, " +
                "ieximptotal, " +
                "iexusucrea, " +
                "to_char(iexfeccrea,'DD/MM/YYYY') iexfeccrea, " +
                "iexusumod, " +
                "to_char(iexfecmod,'DD/MM/YYYY') iexfecmod, " +
                "iexflgest, " +
                "iexcodpropla, " +
                "iexcodconpla, " +
                "ieximportepla, " +
                "iexglosa " +
                "from iexprestamodet " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra and " +
                "iexcorrel = :iexcorrel ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", prestcab.getIexcodcia())
                .addValue("codtra", prestcab.getIexcodtra())
                .addValue("iexcorrel", prestcab.getIexcorrel());

        List<PrestamoDet> lsPrest = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(PrestamoDet.class));

        return lsPrest;
    }

    public void eliminarPrestamoDetAll(PrestamoCab prestcab) {

        String sql = "delete from iexprestamodet " +
                "where iexcodcia = ? and iexcodtra = ? and iexcorrel= ? ";

        jdbc.update(sql,
                prestcab.getIexcodcia(),
                prestcab.getIexcodtra(),
                prestcab.getIexcorrel()
        );
    }

    public void eliminarPrestamoCab(PrestamoCab prestcab) {

        String sql = "delete from iexprestamocab " +
                "where iexcodcia = ?  and iexcodtra = ? and iexcorrel = ? ";

        jdbc.update(sql,
                prestcab.getIexcodcia(),
                prestcab.getIexcodtra(),
                prestcab.getIexcorrel()
        );
    }

}
