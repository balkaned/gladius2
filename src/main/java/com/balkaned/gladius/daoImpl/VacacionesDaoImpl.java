package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.dao.SueldoDao;
import com.balkaned.gladius.dao.VacacionesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository("VacacionesDao")
public class VacacionesDaoImpl implements VacacionesDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
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

    public List<VacacionProgramacion> listarVacacionesPer(Empleado empleado, String perini, String perfin) {

        String sql = " select  " +
                "v.iexcodcia, v.iexcodtra, v.iexcorrel, to_char(v.iexfecini,'DD/MM/YYYY') as iexfecini, to_char(v.iexfecfin,'DD/MM/YYYY') as iexfecfin, "
                + " v.iexnrodias, v.iextipvac , d.desdet as destipvac, v.iexglosa,  " +
                "v.iexpermesini, v.iexpermesfin, v.iexusucrea, to_char(v.iexfeccrea,'DD/MM/YYYY') as iexfeccrea, v.iexusumod, to_char(v.iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                "from " +
                "iexvacprg v, " +
                "( " +
                "select  iexkey, desdet from iexttabled where iexcodtab='56' " +
                ") d " +
                "where " +
                "v.iexcodcia=" + empleado.getIexcodcia() + " and v.iexcodtra=" + empleado.getIexcodtra() + " and v.iexpermesini='" + perini + "'  and  v.iexpermesfin='" + perfin + "'  and " +
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

    public Integer saldotraVac(Integer codcia, Integer codtra, String perini, String perfin) {

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

    public Integer validaVac(Integer codcia, Integer codtra, String fecini, String fecfin) {

        final Integer[] valor = {0};

        String sql = "  select " +
                "	sum(e.dias) dias " +
                "  from (  " +
                "	 select coalesce(count(iexcorrel),0) dias from iexvacprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecini + "','dd/mm/yyyy')  >= iexfecini  and  to_date('" + fecini + "','dd/mm/yyyy')  <= iexfecfin  " +
                "	  union " +
                "	  select coalesce(count(iexcorrel),0) dias from iexvacprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecfin + "','dd/mm/yyyy')  >= iexfecini  and  to_date('" + fecfin + "','dd/mm/yyyy')  <= iexfecfin " +
                "  union " +
                " select coalesce(count(iexcorrel),0) dias from iexvacprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecini + "','dd/mm/yyyy')  <= iexfecini  and  to_date('" + fecfin + "','dd/mm/yyyy')  >= iexfecini " +
                " union   " +
                " select coalesce(count(iexcorrel),0) dias from iexvacprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecini + "','dd/mm/yyyy')  <= iexfecfin  and  to_date('" + fecfin + "','dd/mm/yyyy')  >= iexfecfin  " +
                " union  " +
                " select coalesce(count(iexcorrel),0) dias from iexvacprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecini + "','dd/mm/yyyy')  <= iexfecini  and  to_date('" + fecfin + "','dd/mm/yyyy')  >= iexfecfin  " +
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

    public Integer getIdVacacionPrg(VacacionProgramacion vacprg) {

        final Integer[] idfinal = {0};

        String sql = " select  coalesce(max(iexcorrel),0)+1 as idex from iexvacprg where iexcodcia=" + vacprg.getIexcodcia() + " and iexcodtra=" + vacprg.getIexcodtra() + " ";
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    idfinal[0] = rs.getInt("idex");
                }
                return idfinal[0];
            }
        });
    }

    public void insertarVacacionPrg(VacacionProgramacion vacprg) {

        template.update("  insert into iexvacprg ( " +
                        "iexcodcia, iexcodtra, iexcorrel, iexfecini, iexfecfin, iexnrodias,  " +
                        "iextipvac , iexglosa,  " +
                        "iexpermesini, iexpermesfin, iexusucrea, iexfeccrea " +
                        ") values ( " +
                        "  ?,   ? ,  ?,  to_date(?,'DD/MM/YYYY'),  to_date(?,'DD/MM/YYYY') ,  ?  ,   " +
                        "  ?,   ? ,   " +
                        "  ?,   ? ,  ? ,  current_date " +
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

    public void procesaVacacionCtl(Empleado empleado) {

        template.update("  call pl_empleado_vacsal(?,?)  ",

                empleado.getIexcodcia(),
                empleado.getIexcodtra());

    }


    public List<VacacionProgramacion> listaVacacionesGen(Integer codcia, String regimen, String fecini, String fecfin, Integer codtra) {
        String sql = "  select  " +
                "	 c.iexcodcia, " +
                "	 c.iexcodtra, " +
                "	  c.iexapepat||' '|| c.iexapemat||' '|| c.iexnomtra  nomtra, " +
                "    case   " +
                "    when iexflgest='1' then 'activo'  " +
                "    when iexflgest='0' then 'inactivo'  " +
                "    else 'inactivo' end desestado, " +
                "	 to_char(c.iexfecing,'DD/MM/YYYY') fecing, " +
                "	 c.iexnrodoc, " +
                "	 a.iextipvac, " +
                "	 a.iexcorrel as vac_id, " +
                "	 to_char(a.iexfecini,'DD/MM/YYYY') iexfecini, " +
                "	 to_char(a.iexfecfin,'DD/MM/YYYY') iexfecfin, " +
                " 	 case " +
                "	  when (a.iexfecini >=to_date('" + fecini + "','DD/MM/YYYY') and  a.iexfecini <= to_date('" + fecfin + "','DD/MM/YYYY')  and  a.iexfecfin <= to_date('" + fecfin + "','DD/MM/YYYY') )" +
                "	    then  (a.iexfecfin -  a.iexfecini) +1  " +
                "      when (a.iexfecini < to_date('" + fecini + "','DD/MM/YYYY')  and    a.iexfecfin <= to_date('" + fecfin + "','DD/MM/YYYY') )     " +
                "	    then  (a.iexfecfin -  to_date('" + fecini + "','DD/MM/YYYY') ) +1	" +
                "	  when (a.iexfecini >= to_date('" + fecini + "','DD/MM/YYYY')  and  a.iexfecini <= to_date('" + fecfin + "','DD/MM/YYYY')   and  a.iexfecfin >  to_date('" + fecfin + "','DD/MM/YYYY') )    " +
                "	    then  ( to_date('" + fecfin + "','DD/MM/YYYY')  -  a.iexfecini) +1	  " +
                "	  when (a.iexfecini < to_date('" + fecini + "','DD/MM/YYYY')  and   a.iexfecfin >  to_date('" + fecfin + "','DD/MM/YYYY') )         " +
                "	    then  ( to_date('" + fecfin + "','DD/MM/YYYY') - to_date('" + fecini + "','DD/MM/YYYY') ) +1  " +
                "	 end  dias_vac,  " +
                " case " +
                "	  when (a.iexfecini >=to_date('" + fecini + "','DD/MM/YYYY') and  a.iexfecini <= to_date('" + fecfin + "','DD/MM/YYYY')  and  a.iexfecfin <= to_date('" + fecfin + "','DD/MM/YYYY') )      " +
                "	    then   to_char(a.iexfecfin,'DD/MM/YYYY')    " +
                "      when (a.iexfecini < to_date('" + fecini + "','DD/MM/YYYY')  and    a.iexfecfin <= to_date('" + fecfin + "','DD/MM/YYYY') )     " +
                "	    then  to_char(a.iexfecfin,'DD/MM/YYYY') 	" +
                "	  when (a.iexfecini >= to_date('" + fecini + "','DD/MM/YYYY')  and  a.iexfecini <= to_date('" + fecfin + "','DD/MM/YYYY')   and  a.iexfecfin >  to_date('" + fecfin + "','DD/MM/YYYY') )    " +
                "	    then   '" + fecfin + "'" +
                "	  when (a.iexfecini < to_date('" + fecini + "','DD/MM/YYYY')  and   a.iexfecfin >  to_date('" + fecfin + "','DD/MM/YYYY') )         " +
                "	    then  '" + fecfin + "'  " +
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
                "	 c.iexcodcia=" + codcia + " and   c.iexreglab='" + regimen + "'	 and " +
                "	 (  " +
                "		 (a.iexfecini >=to_date('" + fecini + "','DD/MM/YYYY') and  a.iexfecini <=to_date('" + fecfin + "','DD/MM/YYYY') )  " +
                "		or   " +
                "	      (a.iexfecfin >=to_date('" + fecini + "','DD/MM/YYYY')  and  a.iexfecfin <=to_date('" + fecfin + "','DD/MM/YYYY') )  " +
                "		 or  " +
                "		 (a.iexfecini <to_date('" + fecini + "','DD/MM/YYYY')  and  a.iexfecfin >to_date('" + fecfin + "','DD/MM/YYYY') )  " +
                "	 									  )  ";
        if (codtra != 0) {

            sql = sql + " and c.iexcodtra=" + codtra + " ";

        }

        sql = sql + " order by 3,4 asc ";
        ;
        return template.query(sql, new ResultSetExtractor<List<VacacionProgramacion>>() {
            public List<VacacionProgramacion> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<VacacionProgramacion> lista = new ArrayList<VacacionProgramacion>();

                while (rs.next()) {
                    VacacionProgramacion p = new VacacionProgramacion();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcorrel(rs.getInt("vac_id"));
                    p.setNrodoc(rs.getString("iexnrodoc"));
                    p.setDesnomtra(rs.getString("nomtra"));
                    p.setFecing(rs.getString("fecing"));
                    p.setIexfecini(rs.getString("iexfecini"));
                    p.setIexfecfin(rs.getString("iexfecfin"));
                    p.setIexnrodias(rs.getDouble("dias_vac"));
                    p.setFecfinrep(rs.getString("fecfinrep"));
                    p.setDesestado(rs.getString("desestado"));
                    p.setDestipvac(rs.getString("destipvac"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Empleado> listaTrabajadoresReg(Integer codcia, String regimen) {

        String sql = " select  " +
                "iexcodtra, " +
                "iexapepat, iexapemat, iexnomtra, " +
                " to_char(iexfecing,'dd/mm/yyyy') as fecing " +
                "from iexempleado where iexcodcia=" + codcia + " and iexflgest='1' and iexreglab='" + regimen + "' order by 2,3,4 asc ";
        ;
        return template.query(sql, new ResultSetExtractor<List<Empleado>>() {
            public List<Empleado> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Empleado> lista = new ArrayList<Empleado>();

                while (rs.next()) {
                    Empleado p = new Empleado();

                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexapepat(rs.getString("iexapepat"));
                    p.setIexapemat(rs.getString("iexapemat"));
                    p.setIexnomtra(rs.getString("iexnomtra"));
                    p.setIexfecing(rs.getString("fecing"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }


    public VacacionProgramacion getVacacionPrg(VacacionProgramacion vacprg) {

        String sql = " select  " +
                " v.iexcodcia, v.iexcodtra, v.iexcorrel, to_char(v.iexfecini,'DD/MM/YYYY') as iexfecini, to_char(v.iexfecfin,'DD/MM/YYYY') as iexfecfin, "
                + " v.iexnrodias, v.iextipvac , d.desdet as destipvac, v.iexglosa,  " +
                " v.iexpermesini, v.iexpermesfin, v.iexusucrea, to_char(v.iexfeccrea,'DD/MM/YYYY') as iexfeccrea, v.iexusumod, to_char(v.iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                " from " +
                " iexvacprg v, " +
                "( " +
                " select  iexkey, desdet from iexttabled where iexcodtab='56' " +
                ") d " +
                " where " +
                " v.iexcodcia=" + vacprg.getIexcodcia() + " and v.iexcodtra=" + vacprg.getIexcodtra() + " and v.iexcorrel=" + vacprg.getIexcorrel() + " and " +
                " v.iextipvac = d.iexkey ";
        return (VacacionProgramacion) template.query(sql, new ResultSetExtractor<VacacionProgramacion>() {
            public VacacionProgramacion extractData(ResultSet rs) throws SQLException, DataAccessException {
                VacacionProgramacion p = new VacacionProgramacion();
                while (rs.next()) {

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

                }
                return p;
            }
        });
    }


    public List<VacacionControl> listaSaldoVacTra(Integer codcia, String regimen, Integer codtra) {

        String sql = " select  " +
                "iexcodcia, iexcodtra, iexpermesini, iexpermesfin, to_char(iexfecini,'DD/MM/YYYY') as iexfecini, to_char(iexfecfin,'DD/MM/YYYY') as iexfecfin,  " +
                "iexdiasgan, iexdiasgoz, iexdiasven, iexdiasper, iexdiascom, iexdiassaldo, " +
                "iexusucrea, to_char(iexfeccrea,'DD/MM/YYYY') as iexfeccrea,  " +
                "iexusumod, to_char(iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                "from iexvacctl " +
                "where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and iexdiasgan>0 order by iexpermesini desc  ";

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
                    p.setIexdiassaldo(rs.getDouble("iexdiassaldo"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }


    public void actualizarVacacionPrg(VacacionProgramacion vacprg) {

        template.update("UPDATE iexvacprg SET iexfecini = TO_DATE(?, 'DD/MM/YYYY'),iexfecfin = TO_DATE(?, 'DD/MM/YYYY'),iexnrodias = ?,iextipvac = ?,  iexglosa = ?,iexpermesini = ?,iexpermesfin = ?,iexusucrea = ?,iexfecmod = CURRENT_DATE WHERE iexcodcia = ? AND iexcodtra = ? AND iexcorrel = ?");

        vacprg.getIexfecini();
        vacprg.getIexfecfin();
        vacprg.getIexnrodias();
        vacprg.getIextipvac();
        vacprg.getIexglosa();
        vacprg.getIexpermesini();
        vacprg.getIexpermesfin();
        vacprg.getIexusucrea();
        vacprg.getIexcodcia();
        vacprg.getIexcodtra();
        vacprg.getIexcorrel();
    }


}
