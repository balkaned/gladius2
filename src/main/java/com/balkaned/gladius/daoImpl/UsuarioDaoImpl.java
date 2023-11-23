package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Usuario;
import com.balkaned.gladius.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository("UsuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<Usuario> listar(String text, Integer pag, Integer numregs) {

        String sql = "  select  * from ( " +
                "   select   " +
                "     row_number()over() idx , " +
                "       u.iexcodusu,  " +
                "       u.iexdesusu,  " +
                "       u.iexpassw,  " +
                "      coalesce(u.iexusucre,0) iexusucre,  " +
                "       u2.iexdesusu usucre,   " +
                "      to_char(u.iexfeccre,'dd/mm/yyyy') iexfeccre,  " +
                "       coalesce(u.iexusumod ,0) iexusumod,  " +
                "       u3.iexdesusu usumod,  " +
                "       to_char(u.iexfecmod,'dd/mm/yyyy') iexfecmod,  " +
                "			   case u.iexflgest WHEN '1' then 'ACTIVO' ELSE 'INACTIVO' END AS ESTADO, " +
                "       u.iexcodusu_mat,  " +
                "       u.iexemail,  " +
                "       u.iexurlfoto  " +
                "   from    " +
                "   iexusuario u  " +
                "	         left outer join iexusuario u2 on  u.iexusucre=u2.iexcodusu  " +
                "	         left outer join iexusuario u3  on u.iexusumod=u3.iexcodusu  " +
                "  where  "
                + "upper(u.iexdesusu) like '%" + text + "%' order by u.iexcodusu desc ) keke   ";
        return template.query(sql, new ResultSetExtractor<List<Usuario>>() {

            public List<Usuario> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Usuario> lista = new ArrayList<Usuario>();

                while (rs.next()) {
                    Usuario p = new Usuario();

                    p.setIdUsuario(rs.getInt("iexcodusu"));
                    p.setUsuario(rs.getString("iexdesusu"));
                    p.setPassword(rs.getString("iexpassw"));
                    p.setIdUsuarioCrea(rs.getInt("iexusucre"));
                    p.setIdUsuarioMod(rs.getInt("iexusumod"));
                    p.setDesUsuarioCrea(rs.getString("usucre"));
                    p.setDesUsuarioMod(rs.getString("usumod"));
                    p.setFechaCrea(rs.getString("iexfeccre"));
                    p.setFechaModfica(rs.getString("iexfecmod"));
                    p.setEstado(rs.getString("estado"));
                    p.setIdUsuMat(rs.getInt("iexcodusu_mat"));
                    p.setEmail(rs.getString("iexemail"));
                    p.setUrlfoto(rs.getString("iexurlfoto"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public void insertar(Usuario usuario) {

        template.update("  call pl_gestion_usuarios(?,?,?,?,?,?,?,?,?) ",
                0,
                usuario.getUsuario(),
                usuario.getPassword(),
                usuario.getEstado(),
                usuario.getIdUsuMat(),
                usuario.getEmail(),
                usuario.getUrlfoto(),
                1,
                "1");
    }

    public Usuario recuperar(Integer id) {

        String sql = "select    u.iexcodusu," +
                "                            u.iexdesusu, " +
                "                            u.iexpassw, " +
                "                             coalesce(u.iexusucre,0) iexusucre, " +
                "                             u2.iexdesusu usucre, " +
                "                             to_char(u.iexfeccre,'dd/mm/yyyy') iexfeccre, " +
                "                            coalesce(u.iexusumod ,0) iexusumod, " +
                "                             u3.iexdesusu usumod, " +
                "                             to_char(u.iexfecmod,'dd/mm/yyyy') iexfecmod,  " +
                "							 case u.iexflgest WHEN '1'  then 'ACTIVO' ELSE 'INACTIVO' END AS ESTADO," +
                "                             u.iexcodusu_mat," +
                "                            u.iexemail, " +
                "                            u.iexurlfoto " +
                "                        from      " +
                "                        iexusuario u " +
                "						left outer join iexusuario u2 on  u.iexusucre=u2.iexcodusu " +
                "						left outer join iexusuario u3 on   u.iexusumod=u3.iexcodusu " +
                "                        where u.iexcodusu =" + id + "  ";

        return (Usuario) template.query(sql, new ResultSetExtractor<Usuario>() {
            public Usuario extractData(ResultSet rs) throws SQLException, DataAccessException {
                Usuario p = new Usuario();
                while (rs.next()) {
                    p.setIdUsuario(rs.getInt("iexcodusu"));
                    p.setUsuario(rs.getString("iexdesusu"));
                    p.setPassword(rs.getString("iexpassw"));
                    p.setIdUsuarioCrea(rs.getInt("iexusucre"));
                    p.setIdUsuarioMod(rs.getInt("iexusumod"));
                    p.setDesUsuarioCrea(rs.getString("usucre"));
                    p.setDesUsuarioMod(rs.getString("usumod"));
                    p.setFechaCrea(rs.getString("iexfeccre"));
                    p.setFechaModfica(rs.getString("iexfecmod"));
                    p.setEstado(rs.getString("estado"));
                    p.setIdUsuMat(rs.getInt("iexcodusu_mat"));
                    p.setEmail(rs.getString("iexemail"));
                    p.setUrlfoto(rs.getString("iexurlfoto"));
                    //logger.info("estado: "+p.getEstado());
                }
                return p;
            }
        });
    }

    public void actualizar(Usuario Usuario) {

        template.update("CALL pl_gestion_usuarios(?,?,?,?,0,?,?,?,?)",

                Usuario.getIdUsuario(),
                Usuario.getUsuario(),
                Usuario.getPassword(),
                Usuario.getEstado(),
                //pst.setInt(5, Usuario.getIdUsuMat());
                Usuario.getEmail(),
                Usuario.getUrlfoto(),
                1,   // codigo de usuario que crea. debe tomar el codigo de usuario de la sesion
                "2");
    }

}
