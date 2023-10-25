package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.TTablaCabecera;
import com.balkaned.gladius.dao.TtableDao;
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

@Repository("TtableDao")
public class TtableDaoImpl implements TtableDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<TTablaCabecera> listarTTablac(String text) {

        String sql = "select " +
                "iexcodtab, " +
                "iexdestab," +
                "iexlbl1, " +
                "iexlbl2, " +
                "iexlbl3, " +
                "iexlbl4, " +
                "iexlbl5, " +
                "iexlbl6, " +
                "iexlbl7, " +
                "iexlbl8, " +
                "iexlblflg1, " +
                "iexlblflg2, " +
                "iexlblflg3, " +
                "iexlblflg4, " +
                "iexlblflg5, " +
                "iexlblflg6, " +
                "iexlblflg7, " +
                "iexlblflg8, " +
                "iexlblval9, " +
                "iexlblval10, " +
                "iexlblval11, " +
                "iexlblval12, " +
                "iexlblval13," +
                "iexlblval14, " +
                "iexlblval15," +
                "iexlblval16," +
                "iexlblflg9," +
                "iexlblflg10, " +
                "iexlblflg11," +
                "iexlblflg12, " +
                "iexlblflg13, " +
                "iexlblflg14, " +
                "iexlblflg15, " +
                "iexlblflg16 " +
                "from iexttablec where '%'||iexcodtab||'%'||iexdestab||'%' like '%" + text + "%'  order by iexcodtab asc ";

        return template.query(sql, new ResultSetExtractor<List<TTablaCabecera>>() {

            public List<TTablaCabecera> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<TTablaCabecera> lista = new ArrayList<TTablaCabecera>();

                while (rs.next()) {
                    TTablaCabecera p = new TTablaCabecera();

                    p.setIexcodtab(rs.getString("iexcodtab"));
                    p.setIexdestab(rs.getString("iexdestab"));
                    p.setIexlbl1(rs.getString("iexlbl1"));
                    p.setIexlbl2(rs.getString("iexlbl2"));
                    p.setIexlbl3(rs.getString("iexlbl3"));
                    p.setIexlbl4(rs.getString("iexlbl4"));
                    p.setIexlbl5(rs.getString("iexlbl5"));
                    p.setIexlbl6(rs.getString("iexlbl6"));
                    p.setIexlbl7(rs.getString("iexlbl7"));
                    p.setIexlbl8(rs.getString("iexlbl8"));
                    p.setIexlblflg1(rs.getString("iexlblflg1"));
                    p.setIexlblflg2(rs.getString("iexlblflg2"));
                    p.setIexlblflg3(rs.getString("iexlblflg3"));
                    p.setIexlblflg4(rs.getString("iexlblflg4"));
                    p.setIexlblflg5(rs.getString("iexlblflg5"));
                    p.setIexlblflg6(rs.getString("iexlblflg6"));
                    p.setIexlblflg7(rs.getString("iexlblflg7"));
                    p.setIexlblflg8(rs.getString("iexlblflg8"));
                    p.setIexlblval9(rs.getString("iexlblval9"));
                    p.setIexlblval10(rs.getString("iexlblval10"));
                    p.setIexlblval11(rs.getString("iexlblval11"));
                    p.setIexlblval12(rs.getString("iexlblval12"));
                    p.setIexlblval13(rs.getString("iexlblval13"));
                    p.setIexlblval14(rs.getString("iexlblval14"));
                    p.setIexlblval15(rs.getString("iexlblval15"));
                    p.setIexlblval16(rs.getString("iexlblval16"));
                    p.setIexlblflg9(rs.getString("iexlblflg9"));
                    p.setIexlblflg10(rs.getString("iexlblflg10"));
                    p.setIexlblflg11(rs.getString("iexlblflg11"));
                    p.setIexlblflg12(rs.getString("iexlblflg12"));
                    p.setIexlblflg13(rs.getString("iexlblflg13"));
                    p.setIexlblflg14(rs.getString("iexlblflg14"));
                    p.setIexlblflg15(rs.getString("iexlblflg15"));
                    p.setIexlblflg16(rs.getString("iexlblflg16"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public void insertarTtablac(TTablaCabecera ttc) {

        template.update(" insert into iexttablec (   " +
                        " iexcodtab,iexdestab, " +
                        " iexlbl1, iexlbl2,iexlbl3, " +
                        " iexlbl4, iexlbl5, iexlbl6, " +
                        " iexlbl7, iexlbl8, iexlblflg1, " +
                        " iexlblflg2, iexlblflg3, iexlblflg4, " +
                        " iexlblflg5, iexlblflg6, iexlblflg7, " +
                        " iexlblflg8, iexlblval9, iexlblval10, " +
                        " iexlblval11, iexlblval12, iexlblval13, " +
                        " iexlblval14, iexlblval15, iexlblval16, " +
                        " iexlblflg9,  iexlblflg10,  iexlblflg11, " +
                        " iexlblflg12, iexlblflg13, iexlblflg14," +
                        "iexlblflg15, iexlblflg16) values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",

                ttc.getIexcodtab(),
                ttc.getIexdestab(),
                ttc.getIexlbl1(),
                ttc.getIexlbl2(),
                ttc.getIexlbl3(),
                ttc.getIexlbl4(),
                ttc.getIexlbl5(),
                ttc.getIexlbl6(),
                ttc.getIexlbl7(),
                ttc.getIexlbl8(),
                ttc.getIexlblflg1(),
                ttc.getIexlblflg2(),
                ttc.getIexlblflg3(),
                ttc.getIexlblflg4(),
                ttc.getIexlblflg5(),
                ttc.getIexlblflg6(),
                ttc.getIexlblflg7(),
                ttc.getIexlblflg8(),
                ttc.getIexlblval9(),
                ttc.getIexlblval10(),
                ttc.getIexlblval11(),
                ttc.getIexlblval12(),
                ttc.getIexlblval13(),
                ttc.getIexlblval14(),
                ttc.getIexlblval15(),
                ttc.getIexlblval16(),
                ttc.getIexlblflg9(),
                ttc.getIexlblflg10(),
                ttc.getIexlblflg11(),
                ttc.getIexlblflg12(),
                ttc.getIexlblflg13(),
                ttc.getIexlblflg14(),
                ttc.getIexlblflg15(),
                ttc.getIexlblflg16());
    }

    /*public List<Rolesxopciones> listarRolesxOpcion(Integer codrol) {

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
    }*/

}
