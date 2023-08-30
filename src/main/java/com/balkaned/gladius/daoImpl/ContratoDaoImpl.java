package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.ContratoEmp;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.dao.ContratoDao;
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

@Repository("ContratoDao")
public class ContratoDaoImpl implements ContratoDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<ContratoEmp> listarContratoEmp(Empleado empleado){
        List<ContratoEmp> lista = null;

        String sql = " select  " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexcorrel, " +
                "iextipcont, " +
                "d.desdet destipcont, "+
                "to_char(iexfecini,'DD/MM/YYYY') as iexfecini, " +
                "to_char(iexfecfin,'DD/MM/YYYY') as iexfecfin, " +
                "iexmodcont, " +
                "iexmodcont as desmodcont, " +
                "iexusucrea, " +
                "iexusumod,  " +
                "iexfeccrea, " +
                "iexfecmod  ,  case  " +
                " when iexestado='0' then 'Inactivo' " +
                " when iexestado='1' then  'Activo' " +
                " else 'Inactivo'  " +
                " end iexestado  " +
                " from iexcontctl,  ( " +
                " select  iexkey, desdet from iexttabled where iexcodtab='12' " +
                " ) d where iexcodcia="+empleado.getIexcodcia()+" and iexcodtra="+empleado.getIexcodtra()+" and iextipcont = d.iexkey ";

        return template.query(sql, new ResultSetExtractor<List<ContratoEmp>>() {
            public List<ContratoEmp> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ContratoEmp> lista = new ArrayList<ContratoEmp>();

                while (rs.next()) {
                    ContratoEmp p = new ContratoEmp();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcorrel(rs.getInt("iexcorrel"));
                    p.setIextipcont(rs.getString("iextipcont"));
                    p.setDestipcont(rs.getString("destipcont"));
                    p.setIexfecini(rs.getString("iexfecini"));
                    p.setIexfecfin(rs.getString("iexfecfin"));
                    p.setIexmodcont(rs.getString("iexmodcont"));
                    p.setDesmodcont(rs.getString("desmodcont"));
                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));
                    p.setIexestado(rs.getString("iexestado"));

                    System.out.println("p.getDestipcont(): "+p.getDestipcont());

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public Integer getIdContratoEmp(ContratoEmp contemp){

        final Integer[] idfinal = {0};

        String sql = " select  coalesce(max(iexcorrel),0)+1 as idex from iexcontctl where iexcodcia="+contemp.getIexcodcia()+" and iexcodtra="+contemp.getIexcodtra()+" ";
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
                while(rs.next()) {
                    idfinal[0] =rs.getInt("idex");
                }
                return idfinal[0];
            }
        });
    }

    public void insertarContratoEmp(ContratoEmp contemp){

        template.update("  insert into iexcontctl( " +
                "iexcodcia,   iexcodtra,     iexcorrel,      iextipcont, " +
                //"iexfecini,   iexfecfin,     iexmodcont,     iexusucrea, iexestado, " +
                "iexmodcont,     iexusucrea, iexestado, " +
                "   iexfeccrea " +
                " ) values ( " +
                "  ?,   ? ,  ?,   ?,  "
                //+ "  to_date(?,'DD/MM/YYYY'),   to_date(?,'DD/MM/YYYY') ,  ?  ,  ?,  ? , " +
                + "  ?  ,  ?,  ? , " +
                " current_date  "+
                ")  ",

            contemp.getIexcodcia(),
            contemp.getIexcodtra(),
            contemp.getIexcorrel(),
            contemp.getIextipcont(),
            //contemp.getIexfecini(),
            //contemp.getIexfecfin(),
            contemp.getIexmodcont(),
            contemp.getIexusucrea(),
            contemp.getIexestado());

    }

}
