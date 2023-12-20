package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.FileImageLegajo;
import com.balkaned.gladius.beans.Grpfile;
import com.balkaned.gladius.dao.LegajoDao;
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

@Repository("LegajoDao")
public class LegajoDaoImpl implements LegajoDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

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

}
