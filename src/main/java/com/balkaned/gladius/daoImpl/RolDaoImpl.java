package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.Role;
import com.balkaned.gladius.models.Rolesxopciones;
import com.balkaned.gladius.dao.RolDao;
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
@Repository("RolDao")
public class RolDaoImpl implements RolDao {

    private static final String CLASS_NAME = "RolDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Role> listarRoles() {

        String sql = "select " +
                "c.iexcodrol idRole, " +
                "c.iexdesrol desRole " +
                "from iexroles c ";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        List<Role> lsR = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Role.class));

        return lsR;
    }

    public void insertarRole(Role rol) {

        String sql = "insert into iexroles( " +
                "iexcodrol, iexdesrol, iexflgest " +
                " ) values ( " +
                " ?, ?, ? " +
                " ) ";

        jdbc.update(sql,
                rol.getIdRole(),
                rol.getDesRole(),
                rol.getFlgest()
        );
    }

    public List<Rolesxopciones> listarRolesxOpcion(Integer codrol) {

        String sql = "select " +
                "c.iexcodrol, " +
                "r.iexdesrol as desrol, " +
                "c.iexcodopc, " +
                "o.iexdesopc as desopc, " +
                "o.iexdessec as dessec, " +
                "o.iexdessys as dessys, " +
                "c.iexflgest, " +
                "c.iex_consultar, " +
                "c.iex_registrar, " +
                "c.iex_modificar,  " +
                "c.iex_eliminar, " +
                "c.iex_descargar_pdf, " +
                "c.iex_descargar_xls, " +
                "c.iexusucre, " +
                "c.iexfeccre, " +
                "c.iexusumod, " +
                "c.iexfecmod " +
                "from iexrolxopc c, iexroles r, " +
                "( select  " +
                "o.iexcodopc, o.iexdesopc, o.iexurlopc, o.iexurlimg, " +
                "o.iexflgest, o.iexcodsec, " +
                "e.iexdessec, " +
                "s.iexdessys, " +
                "o.iexdescripcion, o.iexcodapps, o.iexaction, " +
                "o.iexusucre, o.iexfeccre, o.iexusumod, o.iexfecmod " +
                "from iexopciones o " +
                "full outer join iexseccion e  on e.iexcodsec = o.iexcodsec " +
                "full outer join iexsystemas s on e.iexcodsys = s.iexcodsys ) o " +
                "where c.iexcodrol = r.iexcodrol and " +
                "c.iexcodopc = o.iexcodopc and " +
                "c.iexcodrol = :codrol order by o.iexdessec, iexdesopc asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codrol", codrol);

        List<Rolesxopciones> lsRolesxOpc = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Rolesxopciones.class));

        return lsRolesxOpc;
    }

    public void insertarRolesxopciones(Rolesxopciones rolxopc) {

        String sql = "insert into iexrolxopc( " +
                "iexcodrol, iexcodopc, iexflgest, iex_consultar, " +
                "iex_registrar, iex_modificar, iex_eliminar, iex_descargar_pdf, " +
                "iex_descargar_xls, iexusucre, iexfeccre " +
                " ) values ( " +
                "  ?, ?, ?, ?, " +
                "  ?, ?, ?, ?, " +
                "  ?, ?, current_date " +
                " ) ";

        jdbc.update(sql,
                rolxopc.getIexcodrol(),
                rolxopc.getIexcodopc(),
                rolxopc.getIexflgest(),
                rolxopc.getIex_consultar(),
                rolxopc.getIex_registrar(),
                rolxopc.getIex_modificar(),
                rolxopc.getIex_eliminar(),
                rolxopc.getIex_descargar_pdf(),
                rolxopc.getIex_descargar_xls(),
                rolxopc.getIexusucre()
        );
    }

    public Role getRole(Role codrol) {

        String sql = "select " +
                "c.iexcodrol idRole, " +
                "c.iexdesrol desRole " +
                "from iexroles c " +
                "where c.iexcodrol = :codrol";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codrol", codrol);

        Role rol = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Role.class));

        return rol;
    }

    public void actualizarRole(Role rol) {

        String sql = "update iexroles set " +
                "iexdesrol=?, iexflgest=? " +
                "where iexcodrol=? ";

        jdbc.update(sql,
                rol.getDesRole(),
                rol.getFlgest(),
                rol.getIdRole()
        );
    }

    public Rolesxopciones getRolesxopciones(Rolesxopciones rolxopc) {

        String sql = "select " +
                "c.iexcodrol, " +
                "r.iexdesrol as desrol, " +
                "c.iexcodopc, " +
                "o.iexdesopc as desopc, " +
                "o.iexdessec as dessec, " +
                "o.iexdessys as dessys, " +
                "c.iexflgest, " +
                "c.iex_consultar, " +
                "c.iex_registrar, " +
                "c.iex_modificar, " +
                "c.iex_eliminar, " +
                "c.iex_descargar_pdf, " +
                "c.iex_descargar_xls, " +
                "c.iexusucre, " +
                "c.iexfeccre, " +
                "c.iexusumod, " +
                "c.iexfecmod " +
                "from iexrolxopc c, iexroles r, " +
                " ( select " +
                "o.iexcodopc, o.iexdesopc, o.iexurlopc, o.iexurlimg, " +
                "o.iexflgest, o.iexcodsec,  " +
                "e.iexdessec, " +
                "s.iexdessys, " +
                "o.iexdescripcion, o.iexcodapps, o.iexaction, " +
                "o.iexusucre, o.iexfeccre, o.iexusumod, o.iexfecmod " +
                "from iexopciones o " +
                "full outer join iexseccion e  on e.iexcodsec = o.iexcodsec " +
                "full outer join iexsystemas s on e.iexcodsys = s.iexcodsys ) o " +
                "where c.iexcodrol = r.iexcodrol and " +
                "c.iexcodopc = o.iexcodopc  and c.iexcodrol = :codrol  and " +
                "c.iexcodopc = :codopc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codrol", rolxopc.getIexcodrol())
                .addValue("codopc", rolxopc.getIexcodopc());

        Rolesxopciones rolxopciones = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Rolesxopciones.class));

        return rolxopciones;
    }

    public void actualizarRolesxopciones(Rolesxopciones rolxopc) {

        String sql = "update iexrolxopc set " +
                "iexflgest=?, iex_consultar=?, " +
                "iex_registrar=?, iex_modificar=?, iex_eliminar=?, iex_descargar_pdf=?, " +
                "iex_descargar_xls=?, iexusumod=?, iexfecmod = current_date " +
                "where iexcodrol =? and iexcodopc =? ";

        jdbc.update(sql,
                rolxopc.getIexflgest(),
                rolxopc.getIex_consultar(),
                rolxopc.getIex_registrar(),
                rolxopc.getIex_modificar(),
                rolxopc.getIex_eliminar(),
                rolxopc.getIex_descargar_pdf(),
                rolxopc.getIex_descargar_xls(),
                rolxopc.getIexusucre(),
                rolxopc.getIexcodrol(),
                rolxopc.getIexcodopc()
        );
    }

    public void eliminarRole(Role rol) {

        String sql = "delete from iexroles " +
                "where iexcodrol=? ";

        jdbc.update(sql,
                rol.getIdRole()
        );
    }

    public void eliminarRolesxopciones(Rolesxopciones rolxopc) {

        String sql = "delete from iexrolxopc " +
                "where iexcodrol =? and iexcodopc =? ";

        jdbc.update(sql,
                rolxopc.getIexcodrol(),
                rolxopc.getIexcodopc()
        );
    }
}
