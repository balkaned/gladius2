package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Area;
import com.balkaned.gladius.dao.AreaDao;
import com.balkaned.gladius.utils.CapitalizarCadena;
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

@Repository("AreaDao")
public class AreaDaoImpl implements AreaDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<Area> listarArea(Integer codcia, String text) {

        List<Area> lista = null;
        String sql = " select  " +
                "a.iexcodcia, " +
                "a.iexcodarea, " +
                "a.iexdesarea, " +
                "a.iexdesarea_descripcion, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod, " +
                "a.iexcodcat, " +
                "d.desdet as descodcat, " +
                "  case " +
                " WHEN length(a.iexareapadre)>0 THEN a.iexareapadre " +
                " else 'null'  end iexareapadre, " +
                "f.iexdesarea as desareapadre " +
                "from iexarea a  " +
                "full outer join iexarea f on a.iexcodcia= f.iexcodcia and  a.iexareapadre =  f.iexcodarea  " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='62' ) d  on a.iexcodcat = d.iexkey " +
                " where a.iexcodcia=" + codcia + "  order by a.iexcodcia, a.iexcodarea asc  ";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Area>>() {

            public List<Area> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Area> lista = new ArrayList<Area>();

                while (rs.next()) {
                    Area p = new Area();
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodarea(rs.getString("iexcodarea"));
                    p.setIexdesarea(rs.getString("iexdesarea"));
                    p.setIexdesarea_descripcion(rs.getString("iexdesarea_descripcion"));
                    p.setIexareapadre(rs.getString("iexareapadre"));
                    p.setIexcodcat(rs.getString("iexcodcat"));
                    p.setDescodcat(rs.getString("descodcat"));
                    p.setDesareapadre(rs.getString("desareapadre"));

                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public Area getArea(Integer codcia, String codarea) {

        String sql = " select  " +
                "a.iexcodcia, " +
                "a.iexcodarea, " +
                "a.iexdesarea, " +
                "a.iexdesarea_descripcion, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod, " +
                "a.iexcodcat, " +
                "d.desdet as descodcat, " +
                "a.iexareapadre, " +
                "f.iexdesarea as desareapadre " +
                "from iexarea a  " +
                "full outer join iexarea f on  a.iexareapadre =  f.iexcodarea  " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='12' ) d  on a.iexcodcat = d.iexkey " +
                "where a.iexcodcia=" + codcia + "  and a.iexcodarea='" + codarea + "' ";

        return (Area) template.query(sql, new ResultSetExtractor<Area>() {
            public Area extractData(ResultSet rs) throws SQLException, DataAccessException {
                Area p = new Area();
                while (rs.next()) {

                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodarea(rs.getString("iexcodarea"));
                    p.setIexdesarea(rs.getString("iexdesarea"));
                    p.setIexdesarea_descripcion(rs.getString("iexdesarea_descripcion"));
                    p.setIexareapadre(rs.getString("iexareapadre"));
                    p.setIexcodcat(rs.getString("iexcodcat"));
                    p.setDescodcat(rs.getString("descodcat"));
                    p.setDesareapadre(rs.getString("desareapadre"));

                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));
                }
                return p;
            }
        });
    }

    public Integer getIdArea(Integer codcia) {

        final Integer[] idcont = {0};

        String sql = " select  coalesce(max(cast(iexcodarea as integer)),0)+1 idcont from iexarea where iexcodcia =" + codcia;
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    idcont[0] = Integer.valueOf(rs.getString("idcont"));
                }
                return idcont[0];
            }
        });
    }

    public void insertarArea(Area area) {

        template.update("  insert into iexarea( " +
                        " iexcodcia,     iexcodarea,    iexdesarea,       iexdesarea_descripcion, " +
                        " iexusucrea,    iexfeccrea,    iexcodcat,        iexareapadre " +
                        " ) values ( " +
                        "  ? ,   ?    ,   ?   ,   ?  ," +
                        "  ? ,   current_date  ,   ?   ,   ?  " +
                        ")  ",

                area.getIexcodcia(),
                area.getIexcodarea(),
                area.getIexdesarea(),
                area.getIexdesarea_descripcion(),
                area.getIexusucrea(),
                area.getIexcodcat(),
                area.getIexareapadre());
    }

    public void actualizarArea(Area area) {

        template.update("  update iexarea  set " +
                        "     iexdesarea=?,       iexdesarea_descripcion=?, " +
                        " iexusumod=?,    iexfecmod=current_date,    iexcodcat=?,        iexareapadre=? " +
                        " where iexcodcia=?  and   iexcodarea = ? ",

                area.getIexdesarea(),
                area.getIexdesarea_descripcion(),
                area.getIexusumod(),
                area.getIexcodcat(),
                area.getIexareapadre(),
                area.getIexcodcia(),
                area.getIexcodarea());

    }

    public void eliminarArea(Area area){

        template.update("  delete from iexarea  where iexcodcia=?  and   iexcodarea = ?",

        area.getIexcodcia(),
        area.getIexcodarea());
    }

}
