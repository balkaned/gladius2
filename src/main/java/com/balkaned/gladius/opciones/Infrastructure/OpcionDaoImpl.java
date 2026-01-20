package com.balkaned.gladius.opciones.Infrastructure;

import com.balkaned.gladius.opciones.Domain.Opciones;
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
@Repository("OpcionDao")
public class OpcionDaoImpl implements OpcionDao {

    private static final String CLASS_NAME = "OpcionDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Opciones> listarOpciones() {

        String sql = "select  " +
                "o.iexcodopc, " +
                "o.iexdesopc, " +
                "o.iexurlopc, " +
                "o.iexurlimg, " +
                "o.iexflgest, " +
                "o.iexcodsec, " +
                "e.iexdessec, " +
                "s.iexdessys as dessys, " +
                "o.iexdescripcion, " +
                "o.iexcodapps, " +
                "o.iexaction, " +
                "o.iexactionspring, " +
                "o.iexusucre, o.iexfeccre, o.iexusumod, o.iexfecmod " +
                "from iexopciones o " +
                "full outer join iexseccion e on e.iexcodsec = o.iexcodsec " +
                "full outer join iexsystemas s on e.iexcodsys = s.iexcodsys " +
                "order by o.iexcodopc ASC ";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        List<Opciones> lsOpc = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Opciones.class));

        return lsOpc;
    }

    public Integer getIdOpciones() {

        String sql = "select coalesce(max(iexcodopc),0)+1 as idex " +
                "from iexopciones ";

        SqlParameterSource namedParameter = new MapSqlParameterSource();

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameter, Integer.class);
    }

    public void insertarOpciones(Opciones opc) {

        String sql = "insert into iexopciones( " +
                "iexcodopc, iexdesopc, iexurlopc, iexurlimg, " +
                "iexflgest, iexcodsec, iexdescripcion, iexcodapps, " +
                "iexaction, iexactionspring, " +
                "iexusucre, iexfeccre " +
                " ) values ( " +
                " ?, ?, ?, ?, " +
                " ?, ?, ?, ?, ?, ?, " +
                " ?, current_date " +
                " ) ";

        jdbc.update(sql,
                opc.getIexcodopc(),
                opc.getIexdesopc(),
                opc.getIexurlopc(),
                opc.getIexurlimg(),
                opc.getIexflgest(),
                opc.getIexcodsec(),
                opc.getIexdescripcion(),
                opc.getIexcodapps(),
                opc.getIexaction(),
                opc.getIexactionspring(),
                opc.getIexusucre()
        );
    }

    public Opciones getOpciones(Integer codopc) {

        String sql = "select " +
                "o.iexcodopc, o.iexdesopc, " +
                "o.iexurlopc, o.iexurlimg, " +
                "o.iexflgest, o.iexcodsec, " +
                "e.iexdessec as dessec, " +
                "s.iexdessys as dessys, " +
                "o.iexdescripcion, o.iexcodapps, o.iexaction, o.iexactionspring, " +
                "o.iexusucre, o.iexfeccre, o.iexusumod, o.iexfecmod " +
                "from iexopciones o " +
                "full outer join iexseccion e  on e.iexcodsec = o.iexcodsec " +
                "full outer join iexsystemas s on e.iexcodsys = s.iexcodsys " +
                "where o.iexcodopc = :codopc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codopc", codopc);

        Opciones opc = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Opciones.class));

        return opc;
    }

    public void actualizarOpciones(Opciones opc) {

        String sql = "update iexopciones set " +
                "iexdesopc =? , iexurlopc =?, iexurlimg =?, " +
                "iexflgest =?, iexcodsec =?, iexdescripcion =?, iexcodapps =?, " +
                "iexaction =?, iexactionspring=?, " +
                "iexusumod =?, iexfeccre = current_date " +
                "where iexcodopc = ? ";

        jdbc.update(sql,
                opc.getIexdesopc(),
                opc.getIexurlopc(),
                opc.getIexurlimg(),
                opc.getIexflgest(),
                opc.getIexcodsec(),
                opc.getIexdescripcion(),
                opc.getIexcodapps(),
                opc.getIexaction(),
                opc.getIexactionspring(),
                opc.getIexusumod(),
                opc.getIexcodopc()
        );
    }

    public void eliminarOpciones(Opciones opc) {

        String sql = "delete from iexopciones " +
                "where iexcodopc = ? ";

        jdbc.update(sql,
                opc.getIexcodopc()
        );
    }
}
