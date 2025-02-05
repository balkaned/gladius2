package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.Compania;
import com.balkaned.gladius.models.UsuarioConeccion;
import com.balkaned.gladius.dao.UsuarioConeccionDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Slf4j
@Repository("UsuarioConecionDao")
public class UsuarioConeccionDaoImpl implements UsuarioConeccionDao {

    private static final String CLASS_NAME = "UsuarioConeccionDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public UsuarioConeccion obtenerUsuarioConeccionById(String id) {

        String sql = "select " +
                "us.iexcodusu as id_usuario, " +
                "us.iexdesusu as user, " +
                "us.iexpassw as pass, " +
                "us.iexemail as email, " +
                "comp.iexcodcia as codCia, " +
                "comp.iexdescia as desCia, " +
                "comp.iexsourcedes as sourceDes, " +
                "comp.iexnroruc as ruccia " +
                "from iexusuario us " +
                "inner join iexusuxcia ucia on ucia.iexcodusu = us.iexcodusu " +
                "inner join iexcompania comp on comp.iexcodcia = ucia.iexcodcia " +
                "where us.iexcodusu = :id ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", Integer.parseInt(id));

        try {
            List<UsuarioConeccion> lsUsuario = namedParameterJdbcTemplate.query(sql, namedParameters,
                    BeanPropertyRowMapper.newInstance(UsuarioConeccion.class));

            return lsUsuario.get(0);
        } catch (IndexOutOfBoundsException ex) {
            log.info(CLASS_NAME + " obtenerUsuarioConeccionBYId, No se encontraron resultados. " + ex);
            return null;
        }
    }

    @Override
    public UsuarioConeccion obtenerUsuarioConeccionByName(UsuarioConeccion uc) {

        String sql = "select " +
                "iexcodusu as id_usuario, " +
                "iexdesusu as user, " +
                "iexpassw as pass " +
                "from iexusuario " +
                "where iexdesusu = :user ";

        String finalUser = "'" + uc.getUser() + "'";

        log.info("uc.getUser(): {} ", uc.getUser());

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("user", uc.getUser());

        try {
            UsuarioConeccion usuario = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                    BeanPropertyRowMapper.newInstance(UsuarioConeccion.class));

            log.info("usuario: {} ", usuario);

            return usuario;
        } catch (EmptyResultDataAccessException ex) {
            log.info(CLASS_NAME + " obtenerUsuarioConeccionByName: El usuario no existe o no se " +
                    "econtraron resultados.");
            return null;
        }
    }

    public List<Compania> listarCompaniasBycodUsu(String idUser) {

        String sql = "select " +
                "us.iexcodusu as id_usuario, " +
                "us.iexdesusu, " +
                "cp.iexcodcia as id_companias, " +
                "cp.iexdescia as nombre, " +
                "cp.iexdescia as nombreCap, " +
                "cp.iexnroruc as ruc, " +
                "cp.iexdireccion as direccion, " +
                "cp.iexdireccion as direccionCap, " +
                "cp.iexreplogo as urlLogo, " +
                "cp.iexschema as schema, " +
                "cp.iexflgsource " +
                "from iexcompania cp " +
                "inner join iexusuxcia uc on uc.iexcodcia = cp.iexcodcia " +
                "inner join iexusuario us on us.iexcodusu = uc.iexcodusu " +
                "where us.iexcodusu = :idUser ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("idUser", Integer.parseInt(idUser));

        List<Compania> lsCompania = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Compania.class));

        return lsCompania;
    }
}
