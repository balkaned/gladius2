package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Role;
import com.balkaned.gladius.beans.Rolesxopciones;
import com.balkaned.gladius.dao.RolDao;
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

@Repository("RolDao")
public class RolDaoImpl implements RolDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<Role> listarRoles() {

        String sql = "select " +
                "c.iexcodrol    codrol, " +
                "c.iexdesrol    desrol " +
                "from iexroles c ";

        return template.query(sql, new ResultSetExtractor<List<Role>>() {

            public List<Role> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Role> lista = new ArrayList<Role>();

                while (rs.next()) {
                    Role rol = new Role();

                    rol.setIdRole(rs.getInt("codrol"));
                    rol.setDesRole(rs.getString("desrol"));

                    lista.add(rol);
                }
                return lista;
            }
        });
    }

    public void insertarRole(Role rol) {

        template.update("  insert into iexroles( " +
                        " iexcodrol,       iexdesrol, iexflgest " +
                        " ) values ( " +
                        "  ? ,   ?  ,  ?   " +
                        ")  ",

                rol.getIdRole(),
                rol.getDesRole(),
                rol.getFlgest());
    }

    public List<Rolesxopciones> listarRolesxOpcion(Integer codrol) {

        String sql = "select  " +
                "c.iexcodrol,  " +
                "r.iexdesrol, " +
                "c.iexcodopc, " +
                "o.iexdesopc, " +
                "o.iexdessec, " +
                "o.iexdessys, " +
                "c.iexflgest,  " +
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
                "o.iexflgest, o.iexcodsec,  " +
                "e.iexdessec, " +
                "s.iexdessys, " +
                "o.iexdescripcion, o.iexcodapps, o.iexaction,   " +
                "o.iexusucre, o.iexfeccre, o.iexusumod, o.iexfecmod " +
                "from iexopciones o " +
                "full outer join iexseccion e  on e.iexcodsec = o.iexcodsec " +
                "full outer join iexsystemas s on e.iexcodsys = s.iexcodsys ) " +
                "o " +
                "where " +
                "c.iexcodrol = r.iexcodrol and  " +
                "c.iexcodopc = o.iexcodopc and c.iexcodrol = " + codrol + "  order by o.iexdessec, iexdesopc asc  ";

        return template.query(sql, new ResultSetExtractor<List<Rolesxopciones>>() {

            public List<Rolesxopciones> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Rolesxopciones> lista = new ArrayList<Rolesxopciones>();

                while (rs.next()) {
                    Rolesxopciones rol = new Rolesxopciones();

                    rol.setIexcodrol(rs.getInt("iexcodrol"));
                    rol.setDesrol(rs.getString("iexdesrol"));
                    rol.setIexcodopc(rs.getInt("iexcodopc"));
                    rol.setDesopc(rs.getString("iexdesopc"));
                    rol.setDessec(rs.getString("iexdessec"));
                    rol.setDessys(rs.getString("iexdessys"));
                    rol.setIexflgest(rs.getString("iexflgest"));
                    rol.setIex_consultar(rs.getString("iex_consultar"));
                    rol.setIex_registrar(rs.getString("iex_registrar"));
                    rol.setIex_modificar(rs.getString("iex_modificar"));
                    rol.setIex_eliminar(rs.getString("iex_eliminar"));
                    rol.setIex_descargar_xls(rs.getString("iex_descargar_xls"));
                    rol.setIex_descargar_pdf(rs.getString("iex_descargar_pdf"));
                    rol.setIexusucre(rs.getString("iexusucre"));
                    rol.setIexfeccre(rs.getString("iexfeccre"));

                    lista.add(rol);
                }
                return lista;
            }
        });
    }

    public void insertarRolesxopciones(Rolesxopciones rolxopc) {

        template.update("  insert into iexrolxopc( " +
                        " iexcodrol,         iexcodopc,       iexflgest,       iex_consultar, " +
                        "   iex_registrar,     iex_modificar,   iex_eliminar,    iex_descargar_pdf, " +
                        "   iex_descargar_xls, iexusucre,       iexfeccre " +
                        " ) values ( " +
                        "  ? ,   ? ,   ? ,  ?  ,  " +
                        "  ? ,   ? ,   ? ,  ?   , " +
                        "  ? ,   ? ,   current_date    " +
                        ")  ",

                rolxopc.getIexcodrol(),
                rolxopc.getIexcodopc(),
                rolxopc.getIexflgest(),
                rolxopc.getIex_consultar(),
                rolxopc.getIex_registrar(),
                rolxopc.getIex_modificar(),
                rolxopc.getIex_eliminar(),
                rolxopc.getIex_descargar_pdf(),
                rolxopc.getIex_descargar_xls(),
                rolxopc.getIexusucre());
    }

    public Role getRole(Role codrol) {

        String sql = "select " +
                "c.iexcodrol    codrol, " +
                "c.iexdesrol    desrol " +
                "from iexroles c  where c.iexcodrol =" + codrol.getIdRole() + " ";

        return (Role) template.query(sql, new ResultSetExtractor<Role>() {
            public Role extractData(ResultSet rs) throws SQLException, DataAccessException {
                Role rol = new Role();
                while (rs.next()) {
                    rol.setIdRole(rs.getInt("codrol"));
                    rol.setDesRole(rs.getString("desrol"));
                }
                return rol;
            }
        });
    }

    public void actualizarRole(Role rol) {

        template.update("  update iexroles set  " +
                        "        iexdesrol=?, iexflgest=? " +
                        "  where iexcodrol=?  ",

                rol.getDesRole(),
                rol.getFlgest(),
                rol.getIdRole());
    }

    public Rolesxopciones getRolesxopciones(Rolesxopciones rolxopc) {

        String sql = "select  " +
                "c.iexcodrol,  " +
                "r.iexdesrol, " +
                "c.iexcodopc, " +
                "o.iexdesopc, " +
                "o.iexdessec, " +
                "o.iexdessys, " +
                "c.iexflgest,  " +
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
                "o.iexflgest, o.iexcodsec,  " +
                "e.iexdessec, " +
                "s.iexdessys, " +
                "o.iexdescripcion, o.iexcodapps, o.iexaction,   " +
                "o.iexusucre, o.iexfeccre, o.iexusumod, o.iexfecmod " +
                "from iexopciones o " +
                "full outer join iexseccion e  on e.iexcodsec = o.iexcodsec " +
                "full outer join iexsystemas s on e.iexcodsys = s.iexcodsys ) " +
                "o " +
                "where " +
                "c.iexcodrol = r.iexcodrol and  " +
                "c.iexcodopc = o.iexcodopc  and c.iexcodrol =" + rolxopc.getIexcodrol() + "  and c.iexcodopc=" + rolxopc.getIexcodopc() + "   ";

        return (Rolesxopciones) template.query(sql, new ResultSetExtractor<Rolesxopciones>() {
            public Rolesxopciones extractData(ResultSet rs) throws SQLException, DataAccessException {
                Rolesxopciones rol = new Rolesxopciones();
                while (rs.next()) {
                    rol.setIexcodrol(rs.getInt("iexcodrol"));
                    rol.setDesrol(rs.getString("iexdesrol"));
                    rol.setIexcodopc(rs.getInt("iexcodopc"));
                    rol.setDesopc(rs.getString("iexdesopc"));
                    rol.setDessec(rs.getString("iexdessec"));
                    rol.setDessys(rs.getString("iexdessys"));
                    rol.setIexflgest(rs.getString("iexflgest"));
                    rol.setIex_consultar(rs.getString("iex_consultar"));
                    rol.setIex_registrar(rs.getString("iex_registrar"));
                    rol.setIex_modificar(rs.getString("iex_modificar"));
                    rol.setIex_eliminar(rs.getString("iex_eliminar"));
                    rol.setIex_descargar_xls(rs.getString("iex_descargar_xls"));
                    rol.setIex_descargar_pdf(rs.getString("iex_descargar_pdf"));
                    rol.setIexusucre(rs.getString("iexusucre"));
                    rol.setIexfeccre(rs.getString("iexfeccre"));
                }
                return rol;
            }
        });
    }

    public void actualizarRolesxopciones(Rolesxopciones rolxopc) {

        template.update("  update iexrolxopc set" +
                        "        iexflgest=?,       iex_consultar=?, " +
                        "   iex_registrar=?,     iex_modificar=?,   iex_eliminar=?,    iex_descargar_pdf=?, " +
                        "   iex_descargar_xls=?, iexusumod=?,       iexfecmod = current_date " +
                        "  where iexcodrol =?  and  iexcodopc =? ",

                rolxopc.getIexflgest(),
                rolxopc.getIex_consultar(),
                rolxopc.getIex_registrar(),
                rolxopc.getIex_modificar(),
                rolxopc.getIex_eliminar(),
                rolxopc.getIex_descargar_pdf(),
                rolxopc.getIex_descargar_xls(),
                rolxopc.getIexusucre(),
                rolxopc.getIexcodrol(),
                rolxopc.getIexcodopc());
    }

}
