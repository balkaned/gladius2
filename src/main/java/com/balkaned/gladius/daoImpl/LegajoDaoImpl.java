package com.balkaned.gladius.daoImpl;


import com.balkaned.gladius.beans.FileImageLegajo;
import com.balkaned.gladius.beans.Grpfile;
import com.balkaned.gladius.dao.LegajoDao;
import com.balkaned.gladius.utils.CapitalizarCadena;
import lombok.extern.slf4j.Slf4j;
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

@Repository("LegajoDao")
@Slf4j
public class LegajoDaoImpl implements LegajoDao {

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<Grpfile> listarGrpfile(Integer codcia, Integer codtra, String grpfile) {

        String sql = " select   i.iexcodcia, i.iexcodtra, " +
                " grp.iexkey idgrp, " +
                " grp.desdet as desgrupo, " +
                " i.iexcodgrpfile,  " +
                " i.iexdesgrpfile, " +
                " e.iexcodimage, e.iexurlimage, e.iexdesimage, e.iexestado , i.iexgrpfile  " +
                " from iexgrpfile i " +
                " inner join ( select  iexkey, desdet " +
                " from iexttabled where iexcodtab='91' )  grp on  i.iexgrpfile = grp.iexkey  " +
                " left outer join  iexfileimage e  on  i.iexcodcia = e.iexcodcia and i.iexcodgrpfile =  e.iexcodgrpfile   " +
                " where  " +
                " i.iexcodcia = " + codcia + "  and   " +
                " i.iexcodtra = " + codtra + "  ";

        if (!grpfile.equals("%")) {
            sql = sql + " and  i.iexgrpfile='" + grpfile + "' ";
        }

        sql = sql + " order by grp.iexkey ,  i.iexcodgrpfile asc  ";

        return template.query(sql, new ResultSetExtractor<List<Grpfile>>() {
            public List<Grpfile> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Grpfile> lista = new ArrayList<Grpfile>();

                while (rs.next()) {
                    Grpfile p = new Grpfile();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setDesgrangrupo(rs.getString("desgrupo"));

                    CapitalizarCadena cap = new CapitalizarCadena();
                    String capitalize=cap.letras(p.getDesgrangrupo());
                    p.setDesgrangrupo(capitalize);

                    p.setIexcodgrpfile(rs.getInt("iexcodgrpfile"));
                    p.setIexdesgrpfile(rs.getString("iexdesgrpfile"));
                    p.setIexcodimage(rs.getInt("iexcodimage"));
                    p.setIexurlimage(rs.getString("iexurlimage"));
                    p.setIexgrpfile(rs.getString("iexgrpfile"));
                    p.setIexdesimage(rs.getString("iexdesimage"));
                    p.setIexestado_det(rs.getString("iexestado"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public Integer obtieneIdGrpFile(Grpfile grpfile) {

        final Integer[] idfinal = {0};

        String sql = " SELECT coalesce(max(iexcodgrpfile),0)+1 idcont  FROM iexgrpfile WHERE IEXCODCIA=" + grpfile.getIexcodcia() + "  ";
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    idfinal[0] = rs.getInt("idcont");
                }
                return idfinal[0];
            }
        });
    }

    public void insertarGrpFile(Grpfile grpfile) {

        template.update("insert into iexgrpfile(   " +
                        " iexcodcia, " +
                        "iexcodgrpfile, " +
                        "iexcodtra, " +
                        "iexgrpfile, " +
                        "iexdesgrpfile, " +
                        "iexestado, " +
                        "iexusucrea, " +
                        "iexfeccrea  ) values " +
                        "  ( ?,?,?,?,?,   ?,?,   CURRENT_TIMESTAMP  ) ",

                grpfile.getIexcodcia(),
                grpfile.getIexcodgrpfile(),
                grpfile.getIexcodtra(),
                grpfile.getIexgrpfile(),
                grpfile.getIexdesgrpfile(),
                grpfile.getIexestado(),
                grpfile.getIexusucrea());
    }

    public void insertarImage(FileImageLegajo fileImageLegajo){

        template.update(" insert into iexfileimage(   iexcodcia, iexcodgrpfile, iexcodimage, iexurlimage, iexdesimage, iexestado , iexusucrea, iexfeccrea ) values "+
                        "  ( ?,  ?,   ?,   ?,   ?,   ?,   ?,   CURRENT_TIMESTAMP  ) ",

                fileImageLegajo.getIexcodcia(),
                fileImageLegajo.getIexcodgrpfile(),
                fileImageLegajo.getIexcodimage(),
                fileImageLegajo.getIexurlimage(),
                fileImageLegajo.getIexdesimage(),
                fileImageLegajo.getIexestado(),
                fileImageLegajo.getIexusucrea());
    }

    public Integer obtieneIdImage(Integer codcia, Integer idgrpfile){

        final Integer[] idfinal = {0};

        String sql = " SELECT coalesce(max(iexcodimage),0)+1 idcont  FROM iexfileimage WHERE IEXCODCIA="+codcia+"  and iexcodgrpfile = "+idgrpfile+" ";

        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
                while(rs.next()) {
                    idfinal[0] =rs.getInt("idcont");
                }
                return idfinal[0];
            }
        });
    }

    public Grpfile getGrpfile(Integer codcia, Integer idgrpfile){

        String sql=" select   i.iexcodcia, i.iexcodtra, " +
                " i.iexcodgrpfile,  " +
                " i.iexdesgrpfile, " +
                "  i.iexgrpfile , i.iexestado , i.iexusucrea, i.iexfeccrea, i.iexusumod, i.iexfecmod "+
                " from iexgrpfile i  "+
                " where  " +
                " i.iexcodcia = "+codcia+"  and   " +
                " i.iexcodgrpfile = "+idgrpfile+"  " ;
        return (Grpfile) template.query(sql, new ResultSetExtractor<Grpfile>() {
            public Grpfile extractData(ResultSet rs) throws SQLException, DataAccessException{
                Grpfile p = new Grpfile();
                while(rs.next()) {
                    p.setIexcodcia(rs.getInt("iexcodcia")) ;
                    p.setIexcodtra(rs.getInt("iexcodtra")) ;
                    p.setIexcodgrpfile(rs.getInt("iexcodgrpfile"));
                    p.setIexdesgrpfile(rs.getString("iexdesgrpfile"));
                    p.setIexgrpfile(rs.getString("iexgrpfile"));
                    p.setIexestado(rs.getString("iexestado"));
                }
                return p;
            }
        });
    }

    public void actualizarGrpFile(Grpfile grpfile){

        template.update(" update iexgrpfile set   "+
                        "iexgrpfile =? ," +
                        "iexdesgrpfile =?   , " +
                        "iexusumod =? , iexestado = ? , " +
                        "iexfecmod = CURRENT_TIMESTAMP    where iexcodcia=?   and " +
                        "    iexcodgrpfile = ?   and  iexcodtra = ? ",

        grpfile.getIexgrpfile(),
        grpfile.getIexdesgrpfile(),
        grpfile.getIexusumod(),
        grpfile.getIexestado(),
        grpfile.getIexcodcia(),
        grpfile.getIexcodgrpfile(),
        grpfile.getIexcodtra());
    }

    public void eliminarGrpFile(Grpfile grpfile){

        template.update(" delete from  iexgrpfile   where iexcodcia=?   and " +
                        "    iexcodgrpfile = ?   and  iexcodtra = ? ",

        grpfile.getIexcodcia(),
        grpfile.getIexcodgrpfile(),
        grpfile.getIexcodtra());

    }

    public void eliminarImage(FileImageLegajo fileImageLegajo){

        template.update(" delete from  iexfileimage where   iexcodcia =? and  iexcodgrpfile =?  and iexcodimage =?  ",

                fileImageLegajo.getIexcodcia(),
                fileImageLegajo.getIexcodgrpfile(),
                fileImageLegajo.getIexcodimage());

    }

}
