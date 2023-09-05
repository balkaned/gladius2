package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.AusentismoProgramacion;
import com.balkaned.gladius.beans.EmpAcum;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.RetencionJudicial;
import com.balkaned.gladius.dao.AcumuladoDao;
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

@Repository("AcumuladoDao")
public class AcumuladoDaoImpl implements AcumuladoDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<EmpAcum> listarEmpAcum(Integer codcia, Integer codtra){

        String sql = " select  " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexaniotrib, " +
                "iexrem_acum, " +
                "iexrem5taafec_acum, " +
                "iexrenta5ta_acum , " +
                "iexremafec5ta_otrcia, " +
                "iexrent5ta_otrcia, " +
                "iexrem4ta_acum	, " +
                "iexrenta4ta_acum, " +
                "iexremotr_acum	, " +
                "iexrenta_acum, " +
                "iexusucrea, " +
                "iexfeccrea, " +
                "iexusumod, " +
                "iexfecmod " +
                "from  " +
                "iexacumval where iexcodcia="+codcia+" and iexcodtra="+codtra+"  order by iexaniotrib asc  "  ;

        return template.query(sql, new ResultSetExtractor<List<EmpAcum>>() {
            public List<EmpAcum> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<EmpAcum> lista = new ArrayList<EmpAcum>();

                while (rs.next()) {
                    EmpAcum p = new EmpAcum();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexaniotrib(rs.getString("iexaniotrib"));
                    p.setIexrem_acum(rs.getDouble("iexrem_acum"));
                    p.setIexrem5taafec_acum(rs.getDouble("iexrem5taafec_acum"));
                    p.setIexrenta5ta_acum(rs.getDouble("iexrenta5ta_acum"));
                    p.setIexremafec5ta_otrcia(rs.getDouble("iexremafec5ta_otrcia"));
                    p.setIexrent5ta_otrcia(rs.getDouble("iexrent5ta_otrcia"));
                    p.setIexrem4ta_acum(rs.getDouble("iexrem4ta_acum"));
                    p.setIexrenta4ta_acum(rs.getDouble("iexrenta4ta_acum"));
                    p.setIexremotr_acum(rs.getDouble("iexremotr_acum"));
                    p.setIexrenta_acum(rs.getDouble("iexrenta_acum"));
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

    public void insertarEmpAcum(EmpAcum empacu){

        template.update("  insert into iexacumval( " +
                " iexcodcia,            iexcodtra,           iexaniotrib,           iexrem_acum, " +
                "iexrem5taafec_acum,   iexrenta5ta_acum ,   iexremafec5ta_otrcia,  iexrent5ta_otrcia, " +
                "iexrem4ta_acum	,     iexrenta4ta_acum,    iexremotr_acum	,     iexrenta_acum, " +
                "iexusucrea,           iexfeccrea  " +
                " ) values ( " +
                "  ? ,       ?    ,       ?   ,        ?  ,"+
                "  ? ,       ?    ,       ?   ,        ?  ,"+
                "  ? ,       ?    ,       ?   ,        ?  ,"+
                "  ? ,   current_date  "+
                ")  ",

                empacu.getIexcodcia(),
                empacu.getIexcodtra(),
                empacu.getIexaniotrib(),
                empacu.getIexrem_acum(),
                empacu.getIexrem5taafec_acum(),
                empacu.getIexrenta5ta_acum(),
                empacu.getIexremafec5ta_otrcia(),
                empacu.getIexrent5ta_otrcia(),
                empacu.getIexrem4ta_acum(),
                empacu.getIexrenta4ta_acum(),
                empacu.getIexremotr_acum(),
                empacu.getIexrenta_acum(),
                empacu.getIexusucrea());

    }

}
