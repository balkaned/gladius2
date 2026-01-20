package com.balkaned.gladius.daoImpl;


import com.balkaned.gladius.empleado.Domain.Empleado;
import com.balkaned.gladius.models.UsuarioxRol;
import com.balkaned.gladius.models.UsuxCompania;
import com.balkaned.gladius.dao.UsuxCompaniaDao;
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
@Repository("UsuxCompaniaDao")
public class UsuxCompaniaDaoImpl implements UsuxCompaniaDao {

    private static final String CLASS_NAME = "UsuxCompaniaDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<UsuxCompania> listar(Integer codusu) {

        String sql = "select "
                + "u.IEXCODCIA codcia, "
                + "c.IEXDESCIA descia, "
                + "c.IEXNRORUC nroruc, "
                + "c.IEXDESCORTO descorto, "
                + "c.IEXDIRECCION direccion, "
                + "c.IEXNROTELF telefono, "
                + "c.IEXCODACT codactividad, "
                + "c.IEXREPNOMBRE nombrerepresentate, "
                + "c.IEXREPCARGO cargorepresentante, "
                + "c.IEXREPDOCID nrodocrepresentante, "
                + "c.IEXREPLOGO urllogo, "
                + "u.IEXCODROL codrol, "
                + "r.IEXDESROL desrol, "
                + "u.IEXUSUCRE usucreades, "
                + "u.IEXFECCRE feccrea, "
                + "u.IEXUSUMOD usumod, "
                + "u.IEXFECMOD fecmod, "
                + "u.IEXCODUSU codusu, "
                + "COALESCE (u.iexcodtra, null, 0) iexcodtra, iexapepat||' '||iexapemat||' '||iexnomtra as destra "
                + "from  public.IEXCOMPANIA c "
                + "INNER JOIN IEXUSUXCIA u ON c.IEXCODCIA = u.IEXCODCIA "
                + "INNER JOIN public.IEXROLES r ON u.IEXCODROL=r.IEXCODROL "
                + "left outer JOIN iexempleado e ON u.IEXCODcia=e.IEXCODcia and u.iexcodtra = e.iexcodtra "
                + "where u.IEXCODUSU = :codusu ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codusu", codusu);

        List<UsuxCompania> lsUsuXcomp = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(UsuxCompania.class));

        return lsUsuXcomp;
    }

    public List<Empleado> listaTrabajadoresCia(Integer codcia) {

        String sql = "select " +
                "iexcodtra, " +
                "iexapepat, " +
                "iexapemat, " +
                "iexnomtra, " +
                "to_char(iexfecing,'dd/mm/yyyy') as iexfecing " +
                "from iexempleado " +
                "where iexcodcia = :codcia and " +
                "iexflgest = '1' " +
                "order by 2,3,4 asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<Empleado> lsEmpl = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return lsEmpl;
    }

    public void insertar(UsuxCompania usuxcia) {

        String sql = "call pl_gestion_rolciaxusu(?,?,?,?,?) ";

        jdbc.update(sql,
                usuxcia.getCodusu(),
                usuxcia.getCodcia(),
                usuxcia.getCodrol(),
                usuxcia.getCodtra(),
                "1"
        );
    }

    public void eliminar(UsuxCompania usuxcia) {

        String sql = "call pl_gestion_rolciaxusu(?,?,?,?,? ) ";

        jdbc.update(sql,
                usuxcia.getCodusu(),
                usuxcia.getCodcia(),
                usuxcia.getCodrol(),
                0,
                "3"
        );
    }

    public UsuarioxRol obtenerRolxUsuario(Integer codcia, Integer codusu) {

        String sql = "select " +
                "u.iexcodusu, " +
                "u.iexdesusu, " +
                "com.iexcodcia, " +
                "com.iexdescia, " +
                "r.iexcodrol, " +
                "r.iexdesrol, " +
                "uc.iexcodtra " +
                "from iexusuario u " +
                "inner join iexusuxcia uc on uc.iexcodusu = u.iexcodusu " +
                "inner join iexcompania com on com.iexcodcia = uc.iexcodcia " +
                "inner join iexroles r on r.iexcodrol = uc.iexcodrol " +
                "where com.iexcodcia = :codcia " +
                "and u.iexcodusu = :codusu ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codusu", codusu);

        UsuarioxRol user = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(UsuarioxRol.class));

        return user;
    }
}
