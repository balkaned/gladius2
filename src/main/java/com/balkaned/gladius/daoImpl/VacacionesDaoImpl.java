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

    public Integer validaVac(Integer codcia, Integer codtra, String fecini, String fecfin){

        final Integer[] valor = {0};

        String sql= "  select " +
                "	sum(e.dias) dias " +
                "  from (  " +
                "	 select coalesce(count(iexcorrel),0) dias from iexvacprg where iexcodcia="+codcia+" and iexcodtra="+codtra+" and to_date('"+fecini+"','dd/mm/yyyy')  >= iexfecini  and  to_date('"+fecini+"','dd/mm/yyyy')  <= iexfecfin  " +
                "	  union " +
                "	  select coalesce(count(iexcorrel),0) dias from iexvacprg where iexcodcia="+codcia+" and iexcodtra="+codtra+" and to_date('"+fecfin+"','dd/mm/yyyy')  >= iexfecini  and  to_date('"+fecfin+"','dd/mm/yyyy')  <= iexfecfin " +
                "  union "+
                " select coalesce(count(iexcorrel),0) dias from iexvacprg where iexcodcia="+codcia+" and iexcodtra="+codtra+" and to_date('"+fecini+"','dd/mm/yyyy')  <= iexfecini  and  to_date('"+fecfin+"','dd/mm/yyyy')  >= iexfecini "+
                " union   "+
                " select coalesce(count(iexcorrel),0) dias from iexvacprg where iexcodcia="+codcia+" and iexcodtra="+codtra+" and to_date('"+fecini+"','dd/mm/yyyy')  <= iexfecfin  and  to_date('"+fecfin+"','dd/mm/yyyy')  >= iexfecfin  "+
                " union  "+
                " select coalesce(count(iexcorrel),0) dias from iexvacprg where iexcodcia="+codcia+" and iexcodtra="+codtra+" and to_date('"+fecini+"','dd/mm/yyyy')  <= iexfecini  and  to_date('"+fecfin+"','dd/mm/yyyy')  >= iexfecfin  "+
                "																  ) e";
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    valor[0] = rs.getInt("dias");
                }
                return valor[0];
            }
        });
    }

    public Integer getIdVacacionPrg(VacacionProgramacion vacprg){

        final Integer[] idfinal = {0};

        String sql = " select  coalesce(max(iexcorrel),0)+1 as idex from iexvacprg where iexcodcia="+vacprg.getIexcodcia()+" and iexcodtra="+vacprg.getIexcodtra()+" " ;
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    idfinal[0] =rs.getInt("idex");
                }
                return idfinal[0];
            }
        });
    }

    public void insertarVacacionPrg(VacacionProgramacion vacprg){

        template.update("  insert into iexvacprg ( " +
                "iexcodcia, iexcodtra, iexcorrel, iexfecini, iexfecfin, iexnrodias,  " +
                "iextipvac , iexglosa,  " +
                "iexpermesini, iexpermesfin, iexusucrea, iexfeccrea " +
                ") values ( " +
                "  ?,   ? ,  ?,  to_date(?,'DD/MM/YYYY'),  to_date(?,'DD/MM/YYYY') ,  ?  ,   " +
                "  ?,   ? ,   "+
                "  ?,   ? ,  ? ,  current_date "+
                ")  ",

                vacprg.getIexcodcia(),
                vacprg.getIexcodtra(),
                vacprg.getIexcorrel(),
                vacprg.getIexfecini(),
                vacprg.getIexfecfin(),
                vacprg.getIexnrodias(),
                vacprg.getIextipvac(),
                vacprg.getIexglosa(),
                vacprg.getIexpermesini(),
                vacprg.getIexpermesfin(),
                vacprg.getIexusucrea());
    }

    public void procesaVacacionCtl(Empleado empleado){

        template.update("  call pl_empleado_vacsal(?,?)  ",

                empleado.getIexcodcia(),
                empleado.getIexcodtra());

    }


    public List<VacacionProgramacion> listaVacacionesGen(Integer codcia, Integer codtra, String regimen ,String fecini, String fecfin){

        String sql= "  select  " +
                "	 c.iexcodcia, " +
                "	 c.iexcodtra, " +
                "	  c.iexapepat||' '|| c.iexapemat||' '|| c.iexnomtra  nomtra, " +
                "    case   " +
                "    when iexflgest='1' then 'activo'  " +
                "    when iexflgest='0' then 'inactivo'  " +
                "    else 'inactivo' end desestado, "+
                "	 to_char(c.iexfecing,'dd/mm/yyyy') fecing, " +
                "	 c.iexnrodoc, " +
                "	 a.iextipvac, " +
                "	 a.iexcorrel as vac_id, " +
                "	 to_char(a.iexfecini,'dd/mm/yyyy') iexfecini, " +
                "	 to_char(a.iexfecfin,'dd/mm/yyyy') iexfecfin, " +
                " 	 case " +
                "	  when (a.iexfecini >=to_date('"+fecini+"','dd/mm/yyyy') and  a.iexfecini <= to_date('"+fecfin+"','dd/mm/yyyy')  and  a.iexfecfin <= to_date('"+fecfin+"','dd/mm/yyyy') )      " +
                "	    then  (a.iexfecfin -  a.iexfecini) +1  " +
                "      when (a.iexfecini < to_date('"+fecini+"','dd/mm/yyyy')  and    a.iexfecfin <= to_date('"+fecfin+"','dd/mm/yyyy') )     " +
                "	    then  (a.iexfecfin -  to_date('"+fecini+"','dd/mm/yyyy') ) +1	" +
                "	  when (a.iexfecini >= to_date('"+fecini+"','dd/mm/yyyy')  and  a.iexfecini <= to_date('"+fecfin+"','dd/mm/yyyy')   and  a.iexfecfin >  to_date('"+fecfin+"','dd/mm/yyyy') )    " +
                "	    then  ( to_date('"+fecfin+"','dd/mm/yyyy')  -  a.iexfecini) +1	  " +
                "	  when (a.iexfecini < to_date('"+fecini+"','dd/mm/yyyy')  and   a.iexfecfin >  to_date('"+fecfin+"','dd/mm/yyyy') )         " +
                "	    then  ( to_date('"+fecfin+"','dd/mm/yyyy') - to_date('"+fecini+"','dd/mm/yyyy') ) +1  " +
                "	 end  dias_vac,  " +
                " case " +
                "	  when (a.iexfecini >=to_date('"+fecini+"','dd/mm/yyyy') and  a.iexfecini <= to_date('"+fecfin+"','dd/mm/yyyy')  and  a.iexfecfin <= to_date('"+fecfin+"','dd/mm/yyyy') )      " +
                "	    then   to_char(a.iexfecfin,'dd/mm/yyyy')    " +
                "      when (a.iexfecini < to_date('"+fecini+"','dd/mm/yyyy')  and    a.iexfecfin <= to_date('"+fecfin+"','dd/mm/yyyy') )     " +
                "	    then  to_char(a.iexfecfin,'dd/mm/yyyy') 	" +
                "	  when (a.iexfecini >= to_date('"+fecini+"','dd/mm/yyyy')  and  a.iexfecini <= to_date('"+fecfin+"','dd/mm/yyyy')   and  a.iexfecfin >  to_date('"+fecfin+"','dd/mm/yyyy') )    " +
                "	    then   '"+fecfin+"' 	  " +
                "	  when (a.iexfecini < to_date('"+fecini+"','dd/mm/yyyy')  and   a.iexfecfin >  to_date('"+fecfin+"','dd/mm/yyyy') )         " +
                "	    then  '"+fecfin+"'  " +
                "	 end  fecfinrep,  " +
                "	 k.des1det codcon ,   k.desdet destipvac   " +
                "	 from iexempleado c, " +
                "	 iexvacprg a  " +
                "	  full outer join ( " +
                "	  select  " +
                "          iexkey, desdet, des1det  " +
                "        from iexttabled where iexcodtab='56'	  " +
                "	  ) k  on  a.iextipvac = k.iexkey  " +
                "	 where " +
                "	 c.iexcodcia = a.iexcodcia and  " +
                "	 c.iexcodtra = a.iexcodtra and	 " +
                "	 c.iexcodcia="+codcia+" and   c.iexreglab='"+regimen+"'	 and " +
                "	 (  " +
                "		 (a.iexfecini >=to_date('"+fecini+"','dd/mm/yyyy') and  a.iexfecini <=to_date('"+fecfin+"','dd/mm/yyyy') )  " +
                "		or   " +
                "	      (a.iexfecfin >=to_date('"+fecini+"','dd/mm/yyyy')  and  a.iexfecfin <=to_date('"+fecfin+"','dd/mm/yyyy') )  " +
                "		 or  " +
                "		 (a.iexfecini <to_date('"+fecini+"','dd/mm/yyyy')  and  a.iexfecfin >to_date('"+fecfin+"','dd/mm/yyyy') )  " +
                "	 									  )  ";
        if(codtra!=0){

            sql =sql+" and c.iexcodtra="+codtra+" ";

        }

        sql= sql+" order by 3,4 asc ";

        ;
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

}
