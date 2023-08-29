package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.dao.SueldoDao;
import com.balkaned.gladius.dao.VacacionesDao;
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

@Repository("VacacionesDao")
public class VacacionesDaoImpl implements VacacionesDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<VacacionControl> listarVacacionesCtl(Empleado empleado) {

        String sql = " select  " +
                "iexcodcia, iexcodtra, iexpermesini, iexpermesfin, to_char(iexfecini,'DD/MM/YYYY') as iexfecini, to_char(iexfecfin,'DD/MM/YYYY') as iexfecfin,  " +
                "iexdiasgan, iexdiasgoz, iexdiasven, iexdiasper, iexdiascom, iexdiassaldo, " +
                "iexusucrea, to_char(iexfeccrea,'DD/MM/YYYY') as iexfeccrea,  " +
                "iexusumod, to_char(iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                "from iexvacctl " +
                "where iexcodcia=" + empleado.getIexcodcia() + " and iexcodtra=" + empleado.getIexcodtra() + "   order by iexpermesini desc  ";

        return template.query(sql, new ResultSetExtractor<List<VacacionControl>>() {
            public List<VacacionControl> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<VacacionControl> lista = new ArrayList<VacacionControl>();

                while (rs.next()) {
                    VacacionControl p = new VacacionControl();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexpermesini(rs.getString("iexpermesini"));
                    p.setIexpermesfin(rs.getString("iexpermesfin"));
                    p.setIexfecini(rs.getString("iexfecini"));
                    p.setIexfecfin(rs.getString("iexfecfin"));
                    p.setIexdiasgan(rs.getDouble("iexdiasgan"));
                    p.setIexdiasgoz(rs.getDouble("iexdiasgoz"));
                    p.setIexdiasven(rs.getDouble("iexdiasven"));
                    p.setIexdiasper(rs.getDouble("iexdiasper"));
                    p.setIexdiascom(rs.getDouble("iexdiascom"));
                    p.setIexdiassaldo(rs.getDouble("iexdiassaldo"));
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

    public List<VacacionProgramacion> listarVacacionesPer(Empleado empleado, String perini, String perfin){

        String sql= " select  " +
                "v.iexcodcia, v.iexcodtra, v.iexcorrel, to_char(v.iexfecini,'DD/MM/YYYY') as iexfecini, to_char(v.iexfecfin,'DD/MM/YYYY') as iexfecfin, "
                + " v.iexnrodias, v.iextipvac , d.desdet as destipvac, v.iexglosa,  " +
                "v.iexpermesini, v.iexpermesfin, v.iexusucrea, to_char(v.iexfeccrea,'DD/MM/YYYY') as iexfeccrea, v.iexusumod, to_char(v.iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                "from " +
                "iexvacprg v, " +
                "( " +
                "select  iexkey, desdet from iexttabled where iexcodtab='56' " +
                ") d " +
                "where " +
                "v.iexcodcia="+empleado.getIexcodcia()+" and v.iexcodtra="+empleado.getIexcodtra()+" and v.iexpermesini='"+perini+"'  and  v.iexpermesfin='"+perfin+"'  and " +
                "v.iextipvac = d.iexkey order by v.iexfecini desc ";
        return template.query(sql, new ResultSetExtractor<List<VacacionProgramacion>>() {
            public List<VacacionProgramacion> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<VacacionProgramacion> lista = new ArrayList<VacacionProgramacion>();

                while (rs.next()) {
                    VacacionProgramacion p = new VacacionProgramacion();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcorrel(rs.getInt("iexcorrel"));
                    p.setIexfecini(rs.getString("iexfecini"));
                    p.setIexfecfin(rs.getString("iexfecfin"));
                    p.setIexnrodias(rs.getDouble("iexnrodias"));
                    p.setIextipvac(rs.getString("iextipvac"));
                    p.setDestipvac(rs.getString("destipvac"));
                    p.setIexglosa(rs.getString("iexglosa"));
                    p.setIexpermesini(rs.getString("iexpermesini"));
                    p.setIexpermesfin(rs.getString("iexpermesfin"));
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

    public Integer saldotraVac(Integer codcia, Integer codtra, String perini, String perfin){

        String sql = " select  coalesce(sum(iexdiassaldo),0) as dias from iexvacctl where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and iexpermesini='" + perini + "' and iexpermesfin='" + perfin + "' ";
        final Integer[] valor = {0};
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    valor[0] = rs.getInt("dias");
                }
                return valor[0];
            }
        });
    }

}
