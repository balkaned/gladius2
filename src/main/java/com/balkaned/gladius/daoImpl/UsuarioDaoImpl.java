package com.balkaned.gladius.daoImpl;


import com.balkaned.gladius.models.Usuario;
import com.balkaned.gladius.dao.UsuarioDao;
import com.balkaned.gladius.util.CapitalizarCadena;
import com.balkaned.gladius.util.FormatterFecha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Repository("UsuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {


    private static final String CLASS_NAME = "UsuarioDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Usuario> listar(String text, Integer pag, Integer numregs) {

        String sql = "select * from ( " +
                "select " +
                "row_number()over() idx, " +
                "u.iexcodusu as idUsuario, " +
                "u.iexdesusu as usuario, " +
                "u.iexpassw as password, " +
                "coalesce(u.iexusucre,0) idUsuarioCrea, " +
                "u2.iexdesusu desUsuarioCrea, " +
                "to_char(u.iexfeccre,'dd/mm/yyyy') fechaCrea, " +
                "coalesce(u.iexusumod ,0) idUsuarioMod, " +
                "u3.iexdesusu desUsuarioMod, " +
                "to_char(u.iexfecmod,'dd/mm/yyyy') fechaModfica, " +
                "case u.iexflgest WHEN '1' then 'ACTIVO' ELSE 'INACTIVO' END AS ESTADO, " +
                "u.iexcodusu_mat as idUsuMat, " +
                "u.iexemail as email, " +
                "u.iexurlfoto as urlfoto " +
                "from iexusuario u " +
                "left outer join iexusuario u2 on u.iexusucre = u2.iexcodusu " +
                "left outer join iexusuario u3 on u.iexusumod = u3.iexcodusu " +
                "where upper(u.iexdesusu) like '%:text%' " +
                "order by u.iexcodusu desc ) keke ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("text", text);

        List<Usuario> lsUsuario = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Usuario.class));

        return lsUsuario;
    }

    public void insertar(Usuario usuario) {

        String sql = "call pl_gestion_usuarios(?,?,?,?,?,?,?,?,?) ";

        jdbc.update(sql,
                0,
                usuario.getUsuario(),
                usuario.getPassword(),
                usuario.getEstado(),
                usuario.getIdUsuMat(),
                usuario.getEmail(),
                usuario.getUrlfoto(),
                1,
                "1"
        );
    }

    public Usuario recuperar(Integer id) {

        String sql = "select " +
                "u.iexcodusu as idUsuario, " +
                "u.iexdesusu as usuario, " +
                "u.iexpassw as password, " +
                "coalesce(u.iexusucre,0) iexusucre as idUsuarioCrea, " +
                "u2.iexdesusu usucre as desUsuarioCrea, " +
                "to_char(u.iexfeccre,'dd/mm/yyyy') fechaCrea, " +
                "coalesce(u.iexusumod ,0) iexusumod as idUsuarioMod, " +
                "u3.iexdesusu desUsuarioMod, " +
                "to_char(u.iexfecmod,'dd/mm/yyyy') fechaModfica, " +
                "case u.iexflgest WHEN '1' then 'ACTIVO' ELSE 'INACTIVO' END AS estado, " +
                "u.iexcodusu_mat as idUsuMat, " +
                "u.iexemail as email, " +
                "u.iexurlfoto as urlfoto " +
                "from iexusuario u " +
                "left outer join iexusuario u2 on u.iexusucre = u2.iexcodusu " +
                "left outer join iexusuario u3 on u.iexusumod = u3.iexcodusu " +
                "where u.iexcodusu = :id ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);

        Usuario usuario = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Usuario.class));

        return usuario;
    }

    public void actualizar(Usuario Usuario) {

        String sql = "call pl_gestion_usuarios(?,?,?,?,0,?,?,?,?) ";

        jdbc.update(sql,
                Usuario.getIdUsuario(),
                Usuario.getUsuario(),
                Usuario.getPassword(),
                Usuario.getEstado(),
                Usuario.getEmail(),
                Usuario.getUrlfoto(),
                1, // Código de usuario que crea. Debe tomar el codigo de usuario de la sesion.
                "2"
        );
    }

    public void eliminar(Integer id) {

        String sql = "call pl_gestion_usuarios(?,'','','',0,'','',?,?) ";

        jdbc.update(sql,
                id,
                1,
                "3"
        );
    }
}
