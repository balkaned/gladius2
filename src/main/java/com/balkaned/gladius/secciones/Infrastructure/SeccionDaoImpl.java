package com.balkaned.gladius.secciones.Infrastructure;


import com.balkaned.gladius.secciones.Domain.Seccion;
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
@Repository("SeccionDao")
public class SeccionDaoImpl implements SeccionDao {

    private static final String CLASS_NAME = "SeccionDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Seccion> listarSeccion() {

        String sql = "select " +
                "s.iexcodsec, " +
                "s.iexdessec, " +
                "s.iexordsec, " +
                "s.iexcodsys, " +
                "y.iexdessys as dessys, " +
                "s.iexsecurl, " +
                "s.iexsecimg, " +
                "s.iexsecobs, " +
                "s.iexopcdef,  " +
                "s.iexactiondef  " +
                "from iexseccion s, iexsystemas y " +
                "where s.iexcodsys = y.iexcodsys ";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        List<Seccion> lsSeccion = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Seccion.class));

        return lsSeccion;
    }

    public Integer getIdSeccion() {

        String sql = "select coalesce(max(iexcodsec),0)+1 as idex " +
                "from iexseccion ";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public void insertarSeccion(Seccion seccion) {

        String sql = "insert into iexseccion ( " +
                "iexcodsec, iexdessec, iexordsec, iexcodsys, iexsecurl, " +
                "iexsecimg, iexsecobs, iexactiondef " +
                " ) values ( " +
                " ?, ?, ?, ?, ?, " +
                " ?, ?, ? " +
                " ) ";

        jdbc.update(sql,
                seccion.getIexcodsec(),
                seccion.getIexdessec(),
                seccion.getIexordsec(),
                seccion.getIexcodsys(),
                seccion.getIexsecurl(),
                seccion.getIexsecimg(),
                seccion.getIexsecobs(),
                seccion.getIexactiondef()
        );
    }

    public Seccion getSeccion(Integer codsec) {

        String sql = "select " +
                "s.iexcodsec, " +
                "s.iexdessec, " +
                "s.iexordsec, " +
                "s.iexcodsys, " +
                "y.iexdessys as dessys, " +
                "s.iexsecurl, " +
                "s.iexsecimg, " +
                "s.iexsecobs, " +
                "s.iexopcdef,  " +
                "s.iexactiondef  " +
                "from iexseccion s, iexsystemas y " +
                "where s.iexcodsys = y.iexcodsys and " +
                "s.iexcodsec = :codsec ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codsec", codsec);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Seccion.class));
    }

    public void actualizarSeccion(Seccion seccion) {

        String sql = "update iexseccion set " +
                "iexdessec= ?, iexordsec= ?, iexcodsys= ?, iexsecurl= ?, " +
                "iexsecimg = ?, iexsecobs= ?, iexactiondef = ? " +
                "where iexcodsec = ? ";

        jdbc.update(sql,
                seccion.getIexdessec(),
                seccion.getIexordsec(),
                seccion.getIexcodsys(),
                seccion.getIexsecurl(),
                seccion.getIexsecimg(),
                seccion.getIexsecobs(),
                seccion.getIexactiondef(),
                seccion.getIexcodsec()
        );
    }

    public void eliminarSeccion(Seccion seccion) {

        String sql = "delete from iexseccion " +
                "where iexcodsec = ? ";

        jdbc.update(sql,
                seccion.getIexcodsec()
        );
    }
}
