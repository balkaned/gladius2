package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.PrestamoCab;
import com.balkaned.gladius.dao.PrestamoDao;
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

@Repository("PrestamoDao")
public class PrestamoDaoImpl implements PrestamoDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<PrestamoCab> listarPrestamoCab(Empleado empleado){

        String sql = " select " +
                "c.iexcodcia	," +
                "c.iexcodtra	," +
                "c.iexcorrel	," +
                "c.iextippres	," +
                "i.desdet destippres," +
                "c.iextipinteres	," +
                "j.desdet destipinteres," +
                "c.iexfrecuencia	," +
                "j.desdet desfrecuencia," +
                " to_char(c.iexfecpres,'DD/MM/YYYY') iexfecpres	," +
                " to_char(c.iexfecinivig,'DD/MM/YYYY') iexfecinivig, " +
                "c.iexnrocuotas	," +
                "c.ieximpbru	," +
                "c.iexinteres	," +
                "c.ieximptotal	," +
                "c.iexglosa	," +
                "c.iexusucrea	," +
                "to_char(c.iexfeccrea,'DD/MM/YYYY') iexfeccrea	," +
                "c.iexusumod	," +
                "to_char(c.iexfecmod,'DD/MM/YYYY') iexfecmod	," +
                "c.iexestado" +
                " from  " +
                "iexprestamocab c , " +
                "( select  iexkey, desdet from iexttabled where iexcodtab='59'  )  i, " +
                "( select  iexkey, desdet from iexttabled where iexcodtab='60'  )  j, " +
                "( select  iexkey, desdet from iexttabled where iexcodtab='61'  )  h " +
                " where "+
                "c.iextippres =  i.iexkey and  " +
                "c.iextipinteres = j.iexkey and " +
                "c.iexfrecuencia =  h.iexkey and  iexcodcia="+empleado.getIexcodcia()+" and iexcodtra="+empleado.getIexcodtra()+"  "  ;

        return template.query(sql, new ResultSetExtractor<List<PrestamoCab>>() {
            public List<PrestamoCab> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<PrestamoCab> lista = new ArrayList<PrestamoCab>();

                while (rs.next()) {
                    PrestamoCab p = new PrestamoCab();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcorrel(rs.getInt("iexcorrel"));
                    p.setIextippres(rs.getString("iextippres"));
                    p.setDestippres(rs.getString("destippres"));
                    p.setDestipinteres(rs.getString("destipinteres"));
                    p.setDestipfrecuencia(rs.getString("desfrecuencia"));
                    p.setIextipinteres(rs.getString("iextipinteres"));
                    p.setIexfrecuencia(rs.getString("iexfrecuencia"));
                    p.setIexfecpres(rs.getString("iexfecpres"));
                    p.setIexfecinivig(rs.getString("iexfecinivig"));
                    p.setIexnrocuotas(rs.getDouble("iexnrocuotas"));
                    p.setIeximpbru(rs.getDouble("ieximpbru"));
                    p.setIexinteres(rs.getDouble("iexinteres"));
                    p.setIeximptotal(rs.getDouble("ieximptotal"));
                    p.setIexglosa(rs.getString("iexglosa"));
                    p.setIexestado(rs.getString("iexestado"));
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

    public Integer getIdPrestamoCab(PrestamoCab prestcab){

        final Integer[] idfinal = {0};

        String sql = " select  coalesce(max(iexcorrel),0)+1 as idex from iexprestamocab where iexcodcia="+prestcab.getIexcodcia()+" and iexcodtra="+prestcab.getIexcodtra()+" ";

        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
                while(rs.next()) {
                    idfinal[0] =rs.getInt("idex");
                }
                return idfinal[0];
            }
        });
    }

    public void insertarPrestamoCab(PrestamoCab prestcab){

        template.update("  insert into iexprestamocab( " +
                " iexcodcia	    ,    iexcodtra	,      iexcorrel	,       iextippres	,"+
                //" iextipinteres	,    iexfrecuencia	,  iexfecpres	,       iexfecinivig , " +
                " iextipinteres	,    iexfrecuencia	, " +
                " iexnrocuotas	,    ieximpbru	,      iexinteres	,       ieximptotal	," +
                " iexglosa  ,        iexestado	,        iexusucrea	,      iexfeccrea		" +
                " ) values ( " +
                "  ?,   ? ,  ?,   ?,  "+
                //"   ?,   ? ,  to_date(?,'DD/MM/YYYY'),   to_date(?,'DD/MM/YYYY'),  "+
                "   ?,   ? ,  "+
                "   ?,   ? ,  ?,   ?,  "+
                "   ?,   ? ,  ?,   current_date "+
                " )  ",

                prestcab.getIexcodcia(),
                prestcab.getIexcodtra(),
                prestcab.getIexcorrel(),
                prestcab.getIextippres(),
                prestcab.getIextipinteres(),
                prestcab.getIexfrecuencia(),
                //prestcab.getIexfecpres(),
                //prestcab.getIexfecinivig(),
                prestcab.getIexnrocuotas(),
                prestcab.getIeximpbru(),
                prestcab.getIexinteres(),
                prestcab.getIeximptotal(),
                prestcab.getIexglosa(),
                prestcab.getIexestado(),
                prestcab.getIexusucrea());

        generacuotasPrestamoCab(prestcab);

    }

    public void generacuotasPrestamoCab(PrestamoCab prestcab){

        template.update("  call pl_prestamo_cuotas(?,?,?)  ",

        prestcab.getIexcodcia(),
        prestcab.getIexcodtra(),
        prestcab.getIexcorrel());

    }

}
