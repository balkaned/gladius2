package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.Grpfile;
import com.balkaned.gladius.beans.RetencionJudicial;
import com.balkaned.gladius.dao.LegajoDao;
import com.balkaned.gladius.dao.RetJudicialDao;
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
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<Grpfile> listarGrpfile(Integer codcia, Integer codtra, String grpfile){

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
                " i.iexcodcia = "+codcia+"  and   " +
                " i.iexcodtra = "+codtra+"  " ;

        if(!grpfile.equals("%")){
            sql=sql+" and  i.iexgrpfile='"+grpfile+"' ";
        }

        sql=sql+" order by grp.iexkey ,  i.iexcodgrpfile asc  ";

        return template.query(sql, new ResultSetExtractor<List<Grpfile>>() {
            public List<Grpfile> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Grpfile> lista = new ArrayList<Grpfile>();

                while (rs.next()) {
                    Grpfile p = new Grpfile();

                    p.setIexcodcia(rs.getInt("iexcodcia")) ;
                    p.setIexcodtra(rs.getInt("iexcodtra")) ;
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
/*
    public Integer getIdRetencionJudicial(RetencionJudicial retjud){

        final Integer[] idfinal = {0};

        String sql = " select  coalesce(max(iexcorrel),0)+1 as idex from iexretjudic where iexcodcia="+retjud.getIexcodcia()+" and iexcodtra="+retjud.getIexcodtra()+" " ;
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
                while(rs.next()) {
                    idfinal[0] =rs.getInt("idex");
                }
                return idfinal[0];
            }
        });
    }

    public void insertarRetencionJudicial(RetencionJudicial retjud){

        template.update("  insert into iexretjudic( " +
                "iexcodcia,       iexcodtra,        iexcorrel,      iexcodpro, " +
                "iextipretjud,    iexresolucion,    iexfecini,      iexfecfin, " +
                "iexpordesct,     ieximpfijo,       iexusucrea,     iexfeccrea " +
                " ) values ( " +
                "  ?,   ? ,  ?,   ?,  "+
                "  ?,   ? ,   to_date(?,'DD/MM/YYYY'),   to_date(?,'DD/MM/YYYY') ,"+
                "  ?  ,  ?,   ? ,  current_date "+
                ")  ",

                retjud.getIexcodcia(),
                retjud.getIexcodtra(),
                retjud.getIexcorrel(),
                retjud.getIexcodpro(),
                retjud.getIextipretjud(),
                retjud.getIexresolucion(),
                retjud.getIexfecini(),
                retjud.getIexfecfin(),
                retjud.getIexpordesct(),
                retjud.getIeximpfijo(),
                "1");
    }*/

}
