package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.dao.EmpleadoDao;
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

@Repository("EmpleadoDao")
public class EmpleadoDaoImpl implements EmpleadoDao {
    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<Empleado> listarEmpCabecera(Empleado empleado){

        List<Empleado> lista = null;
        String sql = " select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexnomtra, " +
                "iexapepat, " +
                "iexapemat, " +
                "iextipdocid, " +
                "iexnrodoc, " +
                "iexfecnac, " +
                "iexfecing, " +
                "iextipcese, " +
                "'' as destipcese, " +
                "iexcodsex, " +
                "'' dessex, " +
                "iexpaisemisor, " +
                "'' as despaisemisor, " +
                "iexflgest, " +
                "'' desestado, " +
                "iexcodant, " +
                "iextiptra, " +
                "'' destiptra, " +
                "iexmodform, " +
                "'' desmodform, " +
                "iexnacion_origen, " +
                "'' desnacion_origen, " +
                "iexdepart_origen, " +
                "'' desdepart_origen, " +
                "iexprovin_origen, " +
                "'' desprovin_origen, " +
                "iexdistri_origen, " +
                "'' desdistri_origen, " +
                "iexgrdinstruccion, " +
                "'' desinstruccion, " +
                "iexcentroform, " +
                "'' descentroform, " +
                "iexflgdomicil, " +
                "iexfeccrea, " +
                "iexfecmod, " +
                "iexusucrea, " +
                "iexusumod " +
                "from iexempleado where iexcodcia="+empleado.getIexcodcia()+" and iexcodtra="+empleado.getIexcodtra()+" ";


       //System.out.println(sql);
        return template.query(sql, new ResultSetExtractor<List<Empleado>>() {

            public List<Empleado> extractData(ResultSet rs) throws SQLException, DataAccessException{
                List<Empleado> lista = new ArrayList<Empleado>();

                while(rs.next()) {
                    Empleado p = new Empleado();
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia")) ;
                    p.setIexcodtra(rs.getInt("iexcodtra")) ;
                    p.setIexnomtra(rs.getString("iexnomtra")) ;
                    p.setIexapepat(rs.getString("iexapepat")) ;
                    p.setIexapemat(rs.getString("iexapemat")) ;
                    p.setIextipdocid(rs.getString("iextipcodid")) ;
                    p.setIexnrodoc(rs.getString("iexnrodoc")) ;
                    p.setIexfecnac(rs.getString("iexfecnac")) ;
                    p.setIexfecing(rs.getString("iexfecing")) ;
                    p.setIextipcese(rs.getString("iextipcese")) ;
                    p.setIexfecret(rs.getString("iexfecret")) ;
                    p.setIexcodsex(rs.getString("iexcodsec")) ;
                    p.setIexpaisemisor(rs.getString("iexpaisemisor")) ;
                    p.setIexflgest(rs.getString("iexflgest")) ;
                    p.setIexcodant(rs.getString("iexcodant"));
                    p.setIextiptra(rs.getString("iextiptra"));
                    p.setIexmodform(rs.getString("iexmodform"));
                    p.setIexnacion_origen(rs.getString("iexnacion_origen"));
                    p.setIexdepart_origen(rs.getString("iexdepart_origen"));
                    p.setIexprovin_origen(rs.getString("iexprovin_origen"));
                    p.setIexdistri_origen(rs.getString("iexdistri_origen"));
                    p.setIexcentroform(rs.getString("iexcentroform"));
                    p.setIexflgdomicil(rs.getString("iexflgdomicil"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexfeccmod(rs.getString("iexfecmod"));
                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexusumod(rs.getString("iexusumod"));

                    p.setDestipcese(rs.getString("destipcese"));
                    p.setDessex(rs.getString("dessex"));
                    p.setDespaisemisor(rs.getString("despaisemisor"));
                    p.setDesestado(rs.getString("desestado"));
                    p.setDestiptra(rs.getString("destiptra"));
                    p.setDesmodform(rs.getString("desmodform"));
                    p.setDesnacion_origen(rs.getString("desnacion_origen"));
                    p.setDesdepart_origen(rs.getString("desdepart_origen"));
                    p.setDesprovin_origen(rs.getString("desprovin_origen"));
                    p.setDesdistri_origen(rs.getString("desdistri_origen"));

                    p.setDesinstruccion(rs.getString("desinstruccion"));
                    p.setDescentroform(rs.getString("descentroform"));

                    lista.add(p);
                }
                return lista;
            }
        });

    }

    public List<Empleado> listarEmpleado(Empleado empleado) {

        List<Empleado> lista = null;
        String sql = "  select  \n" +
                "                        e.iexcodcia,  " +
                "                        e.iexcodtra,  " +
                "                        e.iexnomtra,  " +
                "                        e.iexapepat,  " +
                "                        e.iexapemat,  " +
                "                        e.iextipdocid, " +
                "			     d.desdet destipdoc, " +
                "                        e.iexnrodoc,  " +
                "                        to_char(e.iexfecnac,'dd/mm/yyyy') iexfecnac,  " +
                "                        to_char(e.iexfecing,'dd/mm/yyyy') iexfecing,  " +
                "                        to_char(e.iexfecret,'dd/mm/yyyy') iexfecret,  " +
                "                        e.iextipcese,  " +
                "                        '' as destipcese,  " +
                "                        e.iexcodsex,   " +
                "                        d2.desdet dessex,  " +
                "                        e.iexpaisemisor,  " +
                "                        '' as despaisemisor,  " +
                "                        e.iexflgest,  " +
                "                        d3.desdet desestado,  " +
                "                        e.iexcodant,  " +
                "                        e.iextiptra,  " +
                "                        d4.desdet destiptra,  " +
                "			     p1.iexdespuesto despuesto , " +
                "                        e.iexmodform,   " +
                "                        '' desmodform,   " +
                "                        e.iexnacion_origen,   " +
                "                        '' desnacion_origen,  " +
                "                        e.iexdepart_origen,   " +
                "                        '' desdepart_origen,  " +
                "                        e.iexprovin_origen,  " +
                "                        '' desprovin_origen,  " +
                "                        e.iexdistri_origen,  " +
                "                        '' desdistri_origen,  " +
                "                        e.iexgrdinstruccion,  " +
                "                        '' desinstruccion,  " +
                "                        e.iexcentroform,  " +
                "                        '' descentroform,  " +
                "                        e.iexflgdomicil,  " +
                "                        e.iexfeccrea,  " +
                "                        e.iexfeccmod,  " +
                "                        e.iexusucrea,  " +
                "                        e.iexusumod   " +
                "                        from iexempleado e " +
                "						left join  iexttabled  d on d.iexcodtab='3' and d.iexkey =  e.iextipdocid   " +
                "						left join  iexttabled  d2 on d2.iexcodtab='50' and d2.iexkey =  e.iexcodsex   " +
                "						left join  iexttabled  d3 on d3.iexcodtab='54' and d3.iexkey =  e.iexflgest    " +
                "						left join  iexpuesto  p1  on p1.iexcodcia= e.iexcodcia and p1.iexpuesto =  e.iexpuesto   " +
                "						left join  iexttabled  d4 on d4.iexcodtab='8' and d4.iexkey =  e.iextiptra    " +
                "						where e.iexcodcia=" + empleado.getIexcodcia() + "  ";


        if (empleado.getTxtfinder() != null) {
            sql = sql + "and  '%'||iexnomtra||'%'||iexapepat||'%'||iexapemat||'%'||iexnrodoc||'%'  like '%" + empleado.getTxtfinder().toUpperCase() + "%' ";
        }

        if (empleado.getIextiptra() != null && !empleado.getIextiptra().equals("%")) {
            sql = sql + "and  iextiptra  like '%" + empleado.getIextiptra() + "%' ";
        }


        if (empleado.getIexflgest() != null && !empleado.getIexflgest().equals("%")) {
            sql = sql + "and  iexflgest  like '%" + empleado.getIexflgest() + "%' ";
        }

        if (empleado.getIexcodsex() != null && !empleado.getIexcodsex().equals("%")) {
            sql = sql + "and  iexcodsex  like '%" + empleado.getIexcodsex() + "%' ";
        }

        if (empleado.getFeciniing_par() != "" && empleado.getFecfining_par() != "") {
            if (empleado.getFeciniing_par() != null && empleado.getFecfining_par() != null) {
                sql = sql + "and  iexfecing >= to_date('" + empleado.getFeciniing_par() + "','dd/mm/yyyy')  and iexfecing <= to_date('" + empleado.getFecfining_par() + "','dd/mm/yyyy') ";
            }
        }

        sql = sql + " order by iexapepat, iexapemat , iexnomtra asc";

        //System.out.println(sql);
        return template.query(sql, new ResultSetExtractor<List<Empleado>>() {
            public List<Empleado> extractData(ResultSet rs) throws SQLException, DataAccessException{
                List<Empleado> lista = new ArrayList<Empleado>();

                while(rs.next()) {
                    Empleado p = new Empleado();
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia")) ;
                    p.setIexcodtra(rs.getInt("iexcodtra")) ;
                    p.setIexnomtra(rs.getString("iexnomtra")) ;
                    p.setIexapepat(rs.getString("iexapepat")) ;
                    p.setIexapemat(rs.getString("iexapemat")) ;
                    p.setIextipdocid(rs.getString("destipdoc")) ;
                    p.setIexnrodoc(rs.getString("iexnrodoc")) ;
                    p.setIexfecnac(rs.getString("iexfecnac")) ;
                    p.setIexfecing(rs.getString("iexfecing")) ;
                    p.setIextipcese(rs.getString("iextipcese")) ;
                    p.setIexfecret(rs.getString("iexfecret")) ;
                    p.setIexcodsex(cap.letras(rs.getString("dessex"))) ;
                    p.setIexpaisemisor(rs.getString("iexpaisemisor")) ;
                    p.setIexflgest(rs.getString("iexflgest")) ;
                    p.setIexcodant(rs.getString("iexcodant"));
                    p.setIextiptra(rs.getString("iextiptra"));
                    p.setIexmodform(rs.getString("iexmodform"));
                    p.setIexnacion_origen(rs.getString("iexnacion_origen"));
                    p.setIexdepart_origen(rs.getString("iexdepart_origen"));
                    p.setIexprovin_origen(rs.getString("iexprovin_origen"));
                    p.setIexdistri_origen(rs.getString("iexdistri_origen"));
                    p.setIexcentroform(rs.getString("iexcentroform"));
                    p.setIexflgdomicil(rs.getString("iexflgdomicil"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));

                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexusumod(rs.getString("iexusumod"));

                    p.setDestipcese(rs.getString("destipcese"));
                    p.setDessex(rs.getString("dessex"));
                    p.setDespaisemisor(rs.getString("despaisemisor"));
                    p.setDesestado(rs.getString("desestado"));
                    p.setDestiptra(rs.getString("destiptra"));
                    p.setIexpuesto(cap.letras(rs.getString("despuesto")));

                    if(p.getIexnomtra()!=null){
                        char firstCharacter = p.getIexnomtra().charAt(0);
                        char char1UpperCase = Character.toUpperCase(firstCharacter);
                        String cast1= String.valueOf(char1UpperCase);

                        p.setLetraIni(cast1);

                        String strMain = p.getIexnomtra();
                        String[] arrSplit = strMain.split(" ");

                        String nombrecompleto=cap.letras(arrSplit[0])+" "+cap.letras(p.getIexapemat());
                        p.setNomCompactoUpper(nombrecompleto);
                    }

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public Empleado recuperarCabecera(Integer ciaid, Integer codtra){

        String sql = " select " +
                "e.iexcodcia, " +
                "e.iexcodtra, " +
                "e.iexnomtra, " +
                "e.iexapepat, " +
                "e.iexapemat, " +
                "e.iextipdocid, " +
                "e.iexnrodoc, " +
                "to_char(e.iexfecnac,'dd/mm/yyyy') iexfecnac, " +
                "to_char(e.iexfecing,'dd/mm/yyyy') iexfecing , " +
                "to_char(e.iexfecret,'dd/mm/yyyy') iexfecret, " +
                "e.iextipcese, " +
                "'' as destipcese, " +
                "e.iexcodsex, " +
                "'' dessex, " +
                "e.iexpaisemisor, " +
                "'' as despaisemisor, " +
                "e.iexflgest, " +
                "'' desestado, " +
                "e.iexcodant, " +
                "e.iextiptra, " +
                "'' destiptra, " +
                "e.iexmodform, " +
                "'' desmodform, " +
                "e.iexnacion_origen, " +
                "'' desnacion_origen, " +
                "e.iexdepart_origen, " +
                "'' desdepart_origen, " +
                "e.iexprovin_origen, " +
                "'' desprovin_origen, " +
                "e.iexdistri_origen, " +
                "'' desdistri_origen, " +
                "e.iexgrdinstruccion, " +
                "'' desinstruccion, " +
                "e.iexcentroform, " +
                "'' descentroform, " +
                "e.iexflgdomicil, " +
                " e.iexcodlardist,       e.iexnrotelf,        e.iexemail,           e.iexemail_coorp, "+
                " to_char(e.iexfeccrea,'dd/mm/yyyy hh24:mi:ss')  iexfeccrea, " +
                " to_char(e.iexfeccmod,'dd/mm/yyyy hh24:mi:ss') iexfeccmod, " +
                "e.iexusucrea, " +
                "e.iexusumod , e.iexlogo , e.iexestcivil  , e.iexreglab ,  get_texto_direccion(e.iexcodcia, e.iexcodtra) direccion  , " +
                " ( select   p.iexdespuesto from iexpuesto p where p.iexcodcia= e.iexcodcia  and p.iexpuesto = e.iexpuesto    ) despuesto "+
                "from iexempleado e where e.iexcodcia="+ciaid+" and e.iexcodtra="+codtra+" ";

        //System.out.println(sql);
        return (Empleado) template.query(sql, new ResultSetExtractor<Empleado>() {
            public Empleado extractData(ResultSet rs) throws SQLException, DataAccessException {
                Empleado p = new Empleado();
                while(rs.next()) {
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia")) ;
                    p.setIexcodtra(rs.getInt("iexcodtra")) ;
                    p.setIexnomtra(rs.getString("iexnomtra")) ;
                    p.setIexapepat(rs.getString("iexapepat")) ;
                    p.setIexapemat(rs.getString("iexapemat")) ;
                    p.setIextipdocid(rs.getString("iextipdocid")) ;
                    p.setIexnrodoc(rs.getString("iexnrodoc")) ;
                    p.setIexfecnac(rs.getString("iexfecnac")) ;
                    p.setIexfecing(rs.getString("iexfecing")) ;
                    p.setIextipcese(rs.getString("iextipcese")) ;
                    p.setIexfecret(rs.getString("iexfecret")) ;
                    p.setIexcodsex(rs.getString("iexcodsex")) ;
                    p.setIexpaisemisor(rs.getString("iexpaisemisor")) ;
                    p.setIexflgest(rs.getString("iexflgest")) ;
                    p.setIexcodant(rs.getString("iexcodant"));
                    p.setIextiptra(rs.getString("iextiptra"));
                    p.setIexmodform(rs.getString("iexmodform"));
                    p.setIexnacion_origen(rs.getString("iexnacion_origen"));
                    p.setIexdepart_origen(rs.getString("iexdepart_origen"));
                    p.setIexprovin_origen(rs.getString("iexprovin_origen"));
                    p.setIexdistri_origen(rs.getString("iexdistri_origen"));
                    p.setIexcentroform(rs.getString("iexcentroform"));
                    p.setIexflgdomicil(rs.getString("iexflgdomicil"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexfeccmod(rs.getString("iexfeccmod"));
                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setDestipcese(rs.getString("destipcese"));
                    p.setDessex(rs.getString("dessex"));
                    p.setDespaisemisor(rs.getString("despaisemisor"));
                    p.setDesestado(rs.getString("desestado"));
                    p.setDestiptra(rs.getString("destiptra"));
                    p.setDesmodform(rs.getString("desmodform"));
                    p.setDesnacion_origen(rs.getString("desnacion_origen"));
                    p.setDesdepart_origen(rs.getString("desdepart_origen"));
                    p.setDesprovin_origen(rs.getString("desprovin_origen"));
                    p.setDesdistri_origen(rs.getString("desdistri_origen"));
                    p.setDesinstruccion(rs.getString("desinstruccion"));
                    p.setIexgrdinstruccion(rs.getString("iexgrdinstruccion"));
                    p.setDescentroform(rs.getString("descentroform"));
                    p.setIexcodlardist(rs.getString("iexcodlardist"));
                    p.setIexnrotelf(rs.getString("iexnrotelf"));
                    p.setIexemail(rs.getString("iexemail"));
                    p.setIexemail_coorp(rs.getString("iexemail_coorp"));
                    p.setIexlogo(rs.getString("iexlogo"));
                    p.setIexestcivil(rs.getString("iexestcivil"));
                    p.setIexreglab(rs.getString("iexreglab"));
                    p.setDireccion1(rs.getString("direccion"));
                    p.setDespuesto(cap.letras(rs.getString("despuesto")));

                    String nombrecompleto=cap.letras(p.getIexnomtra())+" "+cap.letras(p.getIexapepat())+" "+cap.letras(p.getIexapemat());
                    p.setNomCompactoUpper(nombrecompleto);
                }
                return p;
            }
        });
    }

    public Empleado recuperarLaboral(Integer ciaid , Integer codtra){

        Empleado p = null;

        List<Empleado> lista = null;

        String sql = " select " +
                " iexcodcia, iexcodtra,  "
                + "iextiptra,   iexsituapen  , to_char(iexfecing,'DD/MM/YYYY') iexfecing ,   TO_CHAR(iexfecret,'DD/MM/YYYY') iexfecret ,               iextipcont     ,     to_char(iexfecini_cont,'DD/MM/YYYY') iexfecini_cont,  " +
                " TO_CHAR(iexfecfin_cont,'DD/MM/YYYY') iexfecfin_cont ,      iexpliego,         iexsituaesp,         iexocupacion_pub, " +
                " iexocupacion_priv,   iexpuesto,         iexccosto,           iexarea, " +
                " iexubilocal ,        iexcateg_trabajador,  iexreglab , to_char(iexfecmodlab,'dd/mm/yyyy hh24:mi:ss') iexfecmodlab , iexusumodlab   "+
                " from iexempleado where iexcodcia="+ciaid+" and iexcodtra="+codtra+" ";

        //System.out.println(sql);

        return (Empleado) template.query(sql, new ResultSetExtractor<Empleado>() {
            public Empleado extractData(ResultSet rs) throws SQLException, DataAccessException {
                Empleado p = new Empleado();
                while(rs.next()) {

                    p.setIexcodcia(rs.getInt("iexcodcia")) ;
                    p.setIexcodtra(rs.getInt("iexcodtra")) ;
                    p.setIextiptra(rs.getString("iextiptra")) ;
                    p.setIexsituapen(rs.getString("iexsituapen")) ;
                    p.setIexfecing(rs.getString("iexfecing")) ;
                    p.setIexfecret(rs.getString("iexfecret")) ;
                    p.setIextipcont(rs.getString("iextipcont")) ;
                    p.setIexfecini_cont(rs.getString("iexfecini_cont")) ;
                    p.setIexfecfin_cont(rs.getString("iexfecfin_cont")) ;
                    p.setIexpliego(rs.getString("iexpliego")) ;
                    p.setIexsituaesp(rs.getString("iexsituaesp")) ;
                    p.setIexocupacion_pub(rs.getString("iexocupacion_pub")) ;
                    p.setIexocupacion_priv(rs.getString("iexocupacion_priv")) ;
                    p.setIexarea(rs.getString("iexarea")) ;
                    p.setIexpuesto(rs.getString("iexpuesto")) ;
                    p.setIexccosto(rs.getString("iexccosto")) ;
                    p.setIexubilocal(rs.getString("iexubilocal")) ;
                    p.setIexcateg_trabajador(rs.getString("iexcateg_trabajador")) ;
                    p.setIexreglab(rs.getString("iexreglab")) ;
                    p.setIexusumodlab(rs.getString("iexusumodlab"));
                    p.setIexfecmodlab(rs.getString("iexfecmodlab"));

                }
                return p;
            }
        });
    }

    public Empleado recuperarPagos(Integer ciaid, Integer codtra){

        Empleado p = null;

        List<Empleado> lista = null;
        String sql = " select " +
                "    iexcodcia , iexcodtra, iextippago, iexperrem, coalesce(iexmontorem,0) as iexmontorem, "
                + " iexcodban_hab ," +
                " iextipban_hab,  " +
                " iexcodmon_hab,  " +
                "iexflgbancci_hab, " +
                "iexnrocta_hab,  " +
                "iextipban_cts,  " +
                "iexcodban_cts,  " +
                "iexcodmon_cts,  " +
                "iexflgbancci_cts,  " +
                "iexnrocta_cts , to_char(iexfecmodpag,'dd/mm/yyyy hh24:mi:ss') iexfecmodpag , iexusumodpag   "+
                " from iexempleado where iexcodcia="+ciaid+" and iexcodtra="+codtra+" ";

        //System.out.println(sql);

        return (Empleado) template.query(sql, new ResultSetExtractor<Empleado>() {
            public Empleado extractData(ResultSet rs) throws SQLException, DataAccessException {
                Empleado p = new Empleado();
                while(rs.next()) {

                    p.setIexcodcia(rs.getInt("iexcodcia")) ;
                    p.setIexcodtra(rs.getInt("iexcodtra")) ;
                    p.setIextippago(rs.getString("iextippago")) ;
                    p.setIexperrem(rs.getString("iexperrem")) ;
                    p.setIexmontorem(Double.parseDouble(rs.getString("iexmontorem"))) ;
                    p.setIexcodban_hab(rs.getString("iexcodban_hab")) ;
                    p.setIexflgbancci_hab(rs.getString("iexflgbancci_hab")) ;
                    p.setIexcodmon_hab(rs.getString("iexcodmon_hab")) ;
                    p.setIextipban_hab(rs.getString("iextipban_hab")) ;
                    p.setIexnrocta_hab(rs.getString("iexnrocta_hab")) ;
                    p.setIexcodban_cts(rs.getString("iexcodban_cts")) ;
                    p.setIexflgbancci_cts(rs.getString("iexflgbancci_cts")) ;
                    p.setIexcodmon_cts(rs.getString("iexcodmon_cts")) ;
                    p.setIextipban_cts(rs.getString("iextipban_cts")) ;
                    p.setIexnrocta_cts(rs.getString("iexnrocta_cts")) ;
                    p.setIexusumodpag(rs.getString("iexusumodpag"));
                    p.setIexfecmodpag(rs.getString("iexfecmodpag"));
                }
                return p;
            }
        });
    }

    public Empleado recuperarSegSocial(Integer ciaid, Integer codtra){

        Empleado p = null;

        List<Empleado> lista = null;

        String sql = " select " +
                "iexcodcia, iexcodtra, iexcodafp, to_char(iexfecafp,'DD/MM/YYYY') iexfecafp , iexcussp, "
                + "iexessalud  ," +
                "iexsenati,  " +
                "iexflgeps,  " +
                "iexcodeps,  " +
                "iexconvdobtrib, " +
                "iexdiscapacidad,  " +
                "iexsctrpension,   " +
                "iexregalter,  " +
                "iexjornmax,  " +
                "iexhornocturno,  " +
                "iexsindicalizado,  " +
                "iexexon5ta ,  " +
                "iexnroruc_cas, " +
                "iexmadreresp,  " +
                "iextipocentoedu,  " +
                " iexflgcomi_mix,  "+
                "  iexflgjubil ,"+
                "   iexflgmas_vida , to_char(iexfecmodseg,'dd/mm/yyyy hh24:mi:ss') iexfecmodseg , iexusumodseg "+
                "from iexempleado where iexcodcia="+ciaid+" and iexcodtra="+codtra+" ";

        //System.out.println(sql);
        return (Empleado) template.query(sql, new ResultSetExtractor<Empleado>() {
            public Empleado extractData(ResultSet rs) throws SQLException, DataAccessException {
                Empleado p = new Empleado();
                while(rs.next()) {
                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));

                    p.setIexcodafp(rs.getString("iexcodafp"));
                    p.setIexfecafp(rs.getString("iexfecafp"));
                    p.setIexcussp(rs.getString("iexcussp"));

                    p.setIexessalud(rs.getString("iexessalud")) ;
                    p.setIexsenati(rs.getString("iexsenati")) ;
                    p.setIexflgeps(rs.getString("iexflgeps")) ;
                    p.setIexcodeps(rs.getString("iexcodeps")) ;
                    p.setIexconvdobtrib(rs.getString("iexconvdobtrib")) ;
                    p.setIexdiscapacidad(rs.getString("iexdiscapacidad")) ;
                    p.setIexsctrpension(rs.getString("iexsctrpension")) ;
                    p.setIexregalter(rs.getString("iexregalter")) ;
                    p.setIexjornmax(rs.getString("iexjornmax")) ;
                    p.setIexhornocturno(rs.getString("iexhornocturno")) ;
                    p.setIexsindicalizado(rs.getString("iexsindicalizado")) ;
                    p.setIexexon5ta(rs.getString("iexexon5ta")) ;
                    p.setIexnroruc_cas(rs.getString("iexnroruc_cas")) ;
                    p.setIexmadreresp(rs.getString("iexmadreresp")) ;
                    p.setIextipocentoedu(rs.getString("iextipocentoedu")) ;
                    p.setIexflgcomi_mix(rs.getString("iexflgcomi_mix"));
                    p.setIexflgjubil(rs.getString("iexflgjubil"));
                    p.setIexflgmas_vida(rs.getString("iexflgmas_vida"));

                    p.setIexusumodseg(rs.getString("iexusumodseg"));
                    p.setIexfecmodseg(rs.getString("iexfecmodseg"));
                }
                return p;
            }
        });
    }

    public Empleado recuperarDireccion(Integer ciaid , Integer codtra){

        Empleado p = null;

        String sql = " select  iexcodcia, iexcodtra, " +
                " iextipvia_dom1,        iexnomvia_dom1,             iexnrovia_dom1,     		 iexdeptin_dom1, " +
                " iexinterior_dom1,      iexmanzana_dom1,  		   iexlote_dom1,  			  iexkilometro_dom1,  " +
                " iexblock_dom1,         iexetapa_dom1,   		   iextipzona_dom1,   		  iexnomzona_dom1, " +
                " iexreferencia_dom1,    iexubigeo_dom1,             iextipvia_dom2,   		  iexnomvia_dom2, " +
                " iexnrovia_dom2,        iexdeptin_dom2,             iexinterior_dom2,          iexmanzana_dom2, " +
                " iexlote_dom2,          iexkilometro_dom2,          iexblock_dom2,             iexetapa_dom2, " +
                " iextipzona_dom2,       iexnomzona_dom2,    		   iexreferencia_dom2,        iexubigeo_dom2, " +
                " iexflgdomicilio , to_char(iexfecmoddom,'dd/mm/yyyy hh24:mi:ss') iexfecmoddom , iexusumoddom ," +
                "  iexnacion_origen1,  "+
                "   iexdepart_origen1 , "+
                "   iexprovin_origen1 , "+
                "   iexnacion_origen2 , "+
                "   iexdepart_origen2 , "+
                "   iexprovin_origen2   "+
                " from iexempleado where iexcodcia="+ciaid+" and iexcodtra="+codtra+" ";

        //System.out.println(sql);
        return (Empleado) template.query(sql, new ResultSetExtractor<Empleado>() {
            public Empleado extractData(ResultSet rs) throws SQLException, DataAccessException {
                Empleado p = new Empleado();
                while(rs.next()) {
                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIextipvia_dom1(rs.getString("iextipvia_dom1")) ;
                    p.setIexnomvia_dom1(rs.getString("iexnomvia_dom1")) ;
                    p.setIexnrovia_dom1(rs.getString("iexnrovia_dom1")) ;
                    p.setIexdeptin_dom1(rs.getString("iexdeptin_dom1")) ;
                    p.setIexinterior_dom1(rs.getString("iexinterior_dom1")) ;
                    p.setIexmanzana_dom1(rs.getString("iexmanzana_dom1")) ;
                    p.setIexlote_dom1(rs.getString("iexlote_dom1")) ;
                    p.setIexkilometro_dom1(rs.getString("iexkilometro_dom1")) ;
                    p.setIexblock_dom1(rs.getString("iexblock_dom1")) ;
                    p.setIexetapa_dom1(rs.getString("iexetapa_dom1")) ;
                    p.setIextipzona_dom1(rs.getString("iextipzona_dom1")) ;
                    p.setIexnomzona_dom1(rs.getString("iexnomzona_dom1")) ;
                    p.setIexreferencia_dom1(rs.getString("iexreferencia_dom1")) ;
                    p.setIexubigeo_dom1(rs.getString("iexubigeo_dom1")) ;
                    p.setIextipvia_dom2(rs.getString("iextipvia_dom2")) ;
                    p.setIexnomvia_dom2(rs.getString("iexnomvia_dom2")) ;
                    p.setIexnrovia_dom2(rs.getString("iexnrovia_dom2")) ;
                    p.setIexdeptin_dom2(rs.getString("iexdeptin_dom2")) ;
                    p.setIexinterior_dom2(rs.getString("iexinterior_dom2")) ;
                    p.setIexmanzana_dom2(rs.getString("iexmanzana_dom2")) ;
                    p.setIexlote_dom2(rs.getString("iexlote_dom2")) ;
                    p.setIexkilometro_dom2(rs.getString("iexkilometro_dom2")) ;
                    p.setIexblock_dom2(rs.getString("iexblock_dom2")) ;
                    p.setIexetapa_dom2(rs.getString("iexetapa_dom2")) ;
                    p.setIextipzona_dom2(rs.getString("iextipzona_dom2")) ;
                    p.setIexnomzona_dom2(rs.getString("iexnomzona_dom2")) ;
                    p.setIexreferencia_dom2(rs.getString("iexreferencia_dom2")) ;
                    p.setIexubigeo_dom2(rs.getString("iexubigeo_dom2")) ;
                    p.setIexflgdomicilio(rs.getString("iexflgdomicilio")) ;
                    p.setIexusumoddom(rs.getString("iexusumoddom"));
                    p.setIexfecmoddom(rs.getString("iexfecmoddom"));
                    p.setIexnacion_origen1(rs.getString("iexnacion_origen1"));
                    p.setIexdepart_origen1(rs.getString("iexdepart_origen1"));
                    p.setIexprovin_origen1(rs.getString("iexprovin_origen1"));
                    p.setIexnacion_origen2(rs.getString("iexnacion_origen2"));
                    p.setIexdepart_origen2(rs.getString("iexdepart_origen2"));
                    p.setIexprovin_origen2(rs.getString("iexprovin_origen2"));
                }
                return p;
            }
        });
    }


    public void  actualizarCabecera(Empleado empleado){

        template.update(" update  iexempleado set "+
                " iexnomtra=?,     iexapepat=?,    iexapemat=?, " +
                " iextipdocid=?,   iexnrodoc=?,         iexfecnac=to_date(?,'DD/MM/YYYY'), " +
                " iexcodsex=?,     iexpaisemisor=?,     iexflgest=?,     iexcodant=?, " +
                " iexmodform=?,    iexnacion_origen=?,  iexdepart_origen=?,  iexprovin_origen=?, " +
                " iexdistri_origen=?,   iexgrdinstruccion=?,  iexcentroform=?,  iexflgdomicil=?, " +
                " iexfeccmod=CURRENT_TIMESTAMP,     iexcodlardist=?, iexnrotelf=?, iexemail=? , iexemail_coorp=? , iexusumod=? , iexestcivil=?  where  iexcodcia=?   and  iexcodtra=? ",

                empleado.getIexnomtra(),
                empleado.getIexapepat(),
                empleado.getIexapemat(),
                empleado.getIextipdocid(),
                empleado.getIexnrodoc(),
                empleado.getIexfecnac(),
                empleado.getIexcodsex(),
                empleado.getIexpaisemisor(),
                empleado.getIexflgest(),
                empleado.getIexcodant(),
                empleado.getIexmodform(),
                empleado.getIexnacion_origen(),
                empleado.getIexdepart_origen(),
                empleado.getIexprovin_origen(),
                empleado.getIexdistri_origen(),
                empleado.getIexgrdinstruccion(),
                empleado.getIexcentroform(),
                empleado.getIexflgdomicil(),
                empleado.getIexcodlardist(),
                empleado.getIexnrotelf(),
                empleado.getIexemail(),
                empleado.getIexemail_coorp(),
                empleado.getIexusumod(),
                empleado.getIexestcivil(),
                empleado.getIexcodcia(),
                empleado.getIexcodtra());
    }

    public void  actualizarLaboral(Empleado empleado){

        if(empleado.getIexfecret().isEmpty()){
            empleado.setIexfecret(null);
        }else{
            empleado.setIexfecret(empleado.getIexfecret());
        }

        if(empleado.getIexfecini_cont().isEmpty()){
            empleado.setIexfecini_cont(null);
        }else{
            empleado.setIexfecini_cont(empleado.getIexfecini_cont());
        }

        if(empleado.getIexfecfin_cont().isEmpty()){
            empleado.setIexfecfin_cont(null);
        }else{
            empleado.setIexfecfin_cont(empleado.getIexfecfin_cont());
        }

        template.update(" update  iexempleado set "
                + "iextiptra=?, iexsituapen =?,"
                + " iexfecing=TO_DATE(?,'DD/MM/YYYY'),  "+
                " iexfecret=TO_DATE(?,'DD/MM/YYYY'),  "
                + " iextipcont  =?   ,    "
                + " iexfecini_cont =TO_DATE(?,'DD/MM/YYYY'),    iexfecfin_cont =TO_DATE(?,'DD/MM/YYYY'),  "
                + "    iexpliego =?,         iexsituaesp =?,       "
                + "  iexocupacion_pub =?, "+
                " iexocupacion_priv =?,   "
                + "iexpuesto =?,         iexccosto =?,           iexarea =?, "+
                " iexubilocal =?,        iexcateg_trabajador =?,  iexreglab =?,          "+
                " iexusumodlab=?, iexfecmodlab=CURRENT_TIMESTAMP "+
                " where  iexcodcia=?   and  iexcodtra=?  ",

                empleado.getIextiptra(),
                empleado.getIexsituapen(),
                empleado.getIexfecing(),
                empleado.getIexfecret(),
                empleado.getIextipcont(),
                empleado.getIexfecini_cont(),
                empleado.getIexfecfin_cont(),
                empleado.getIexpliego(),
                empleado.getIexsituaesp(),
                empleado.getIexocupacion_pub(),
                empleado.getIexocupacion_priv(),
                empleado.getIexpuesto(),
                empleado.getIexccosto(),
                empleado.getIexarea(),
                empleado.getIexubilocal(),
                empleado.getIexcateg_trabajador(),
                empleado.getIexreglab(),
                empleado.getIexusumod(),
                empleado.getIexcodcia(),
                empleado.getIexcodtra());

    }

    public void actualizarPagos(Empleado empleado) {

        template.update(" update  iexempleado set  "+
                " iextippago=? , iexperrem=?,   iexmontorem=?,   "
                + " iexcodban_hab=?,            iexflgbancci_hab=?,         iexcodmon_hab =?, " +
                "  iextipban_hab=?,            iexnrocta_hab=?,            iexcodban_cts=?  , " +
                " iexflgbancci_cts =?,         iexcodmon_cts =?,            iextipban_cts=?  ," +
                "  iexnrocta_cts=?,   " +
                " iexfecmodpag=CURRENT_TIMESTAMP,  iexusumodpag=?  where  iexcodcia=?   and  iexcodtra=?  ",

                empleado.getIextippago(),
            empleado.getIexperrem(),
            0.0,
            empleado.getIexcodban_hab(),
            empleado.getIexflgbancci_hab(),
            empleado.getIexcodmon_hab(),
            empleado.getIextipban_hab(),
            empleado.getIexnrocta_hab(),
            empleado.getIexcodban_cts(),
            empleado.getIexflgbancci_cts(),
            empleado.getIexcodmon_cts(),
            empleado.getIextipban_cts(),
            empleado.getIexnrocta_cts(),
            empleado.getIexusumod(),
            empleado.getIexcodcia(),
            empleado.getIexcodtra());
    }

    public void actualizarSegSocial(Empleado empleado){

        template.update(" update iexempleado set  "+
                " iexcodafp = ?  , "+
                " iexfecafp = to_date(?,'DD/MM/YYYY'), "+
                " iexcussp = ?  , "+
                " iexessalud =?," +
                " iexsenati =?   , " +
                " iexflgeps =?  , " +
                " iexcodeps  =?  ,  " +
                " iexconvdobtrib =?  ,  " +
                " iexdiscapacidad =?  ,  " +
                " iexsctrpension =? ,  " +
                " iexregalter  =?  ,  " +
                " iexjornmax  =?  ,   " +
                " iexhornocturno  =?  ,  " +
                " iexsindicalizado  =?  , " +
                " iexexon5ta  =?,  " +
                " iexnroruc_cas  =?,  " +
                " iexmadreresp  =?,  " +
                " iextipocentoedu=?,    " +
                " iexflgcomi_mix = ? , " +
                " iexflgmas_vida = ? , " +
                " iexflgjubil = ?  , " +
                " iexfecmodseg=CURRENT_TIMESTAMP,  iexusumodseg=?  where  iexcodcia=?   and  iexcodtra=? ",

                empleado.getIexcodafp(),
                empleado.getIexfecafp(),
                empleado.getIexcussp(),
                empleado.getIexessalud(),
                empleado.getIexsenati(),
                empleado.getIexflgeps(),
                empleado.getIexcodeps(),
                empleado.getIexconvdobtrib(),
                empleado.getIexdiscapacidad(),
                empleado.getIexsctrpension(),
                empleado.getIexregalter(),
                empleado.getIexjornmax(),
                empleado.getIexhornocturno(),
                empleado.getIexsindicalizado(),
                empleado.getIexexon5ta(),
                empleado.getIexnroruc_cas(),
                empleado.getIexmadreresp(),
                empleado.getIextipocentoedu(),
                empleado.getIexflgcomi_mix(),
                empleado.getIexflgmas_vida(),
                empleado.getIexflgjubil(),
                empleado.getIexusumod(),
                empleado.getIexcodcia(),
                empleado.getIexcodtra());

    }

    public void actualizarDireccion(Empleado empleado){

        template.update(" update  iexempleado set  "+
                " iextipvia_dom1=?,        iexnomvia_dom1 =?,             iexnrovia_dom1 =?,     		 iexdeptin_dom1 =?, " +
                " iexinterior_dom1=?,      iexmanzana_dom1 =?,  		   iexlote_dom1 =?,  			  iexkilometro_dom1 =?, " +
                " iexblock_dom1 =?,         iexetapa_dom1 =?,   		   iextipzona_dom1 =?,   		  iexnomzona_dom1 =?,  " +
                //" iexreferencia_dom1 =?,    iexubigeo_dom1 =?,             iextipvia_dom2 =?,   		  iexnomvia_dom2 =?,  " +
                " iexreferencia_dom1 =?,    iextipvia_dom2 =?,   		  iexnomvia_dom2 =?,  " +
                " iexnrovia_dom2 =?,        iexdeptin_dom2 =?,             iexinterior_dom2 =?,          iexmanzana_dom2 =?,  " +
                " iexlote_dom2  =?,          iexkilometro_dom2  =?,          iexblock_dom2  =?,             iexetapa_dom2 =? , " +
                //" iextipzona_dom2 =?,       iexnomzona_dom2  =?,    		   iexreferencia_dom2 =?,        iexubigeo_dom2 =? ," +
                " iextipzona_dom2 =?,       iexnomzona_dom2  =?,    		   iexreferencia_dom2 =?, " +
                " iexflgdomicilio =? , " +
                " iexfecmoddom=CURRENT_TIMESTAMP,  iexusumoddom=?  , "+
                " iexnacion_origen1 =?  ,   "+
                " iexdepart_origen1 =?  , "+
                " iexprovin_origen1 =?  , "+
                " iexnacion_origen2 =?  , "+
                " iexdepart_origen2  =? ,  "+
                " iexprovin_origen2 =?  "+
                " where  iexcodcia=?   and  iexcodtra=?  ",

            empleado.getIextipvia_dom1(),
            empleado.getIexnomvia_dom1(),
            empleado.getIexnrovia_dom1(),
            empleado.getIexdeptin_dom1(),
            empleado.getIexinterior_dom1(),
            empleado.getIexmanzana_dom1(),
            empleado.getIexlote_dom1(),
            empleado.getIexkilometro_dom1(),
            empleado.getIexblock_dom1(),
            empleado.getIexetapa_dom1(),
            empleado.getIextipzona_dom1(),
            empleado.getIexnomzona_dom1(),
            empleado.getIexreferencia_dom1(),
            //empleado.getIexubigeo_dom1(),
            empleado.getIextipvia_dom2(),
            empleado.getIexnomvia_dom2(),
            empleado.getIexnrovia_dom2(),
            empleado.getIexdeptin_dom2(),
            empleado.getIexinterior_dom2(),
            empleado.getIexmanzana_dom2(),
            empleado.getIexlote_dom2(),
            empleado.getIexkilometro_dom2(),
            empleado.getIexblock_dom2(),
            empleado.getIexetapa_dom2 (),
            empleado.getIextipzona_dom2(),
            empleado.getIexnomzona_dom2(),
            empleado.getIexreferencia_dom2(),
            //empleado.getIexubigeo_dom2(),
            empleado.getIexflgdomicilio(),
            empleado.getIexusumod(),
            empleado.getIexnacion_origen1(),
            empleado.getIexdepart_origen1(),
            empleado.getIexprovin_origen1(),
            empleado.getIexnacion_origen2(),
            empleado.getIexdepart_origen2(),
            empleado.getIexprovin_origen2(),
            empleado.getIexcodcia(),
            empleado.getIexcodtra());

    }

    public List<Empleado> validarCabecera(Empleado empleado){

        List<Empleado> lista = null;

        String sql = " select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexnomtra, " +
                "iexapepat, " +
                "iexapemat, " +
                "iextipdocid, " +
                "iexnrodoc, " +
                "iexfecnac, " +
                "iexfecing, " +
                "iextipcese, " +
                "'' as destipcese, " +
                "iexcodsex, " +
                "'' dessex, " +
                "iexpaisemisor, " +
                "'' as despaisemisor, " +
                "iexflgest, " +
                "'' desestado, " +
                "iexcodant, " +
                "iextiptra, " +
                "'' destiptra, " +
                "iexmodform, " +
                "'' desmodform, " +
                "iexnacion_origen, " +
                "'' desnacion_origen, " +
                "iexdepart_origen, " +
                "'' desdepart_origen, " +
                "iexprovin_origen, " +
                "'' desprovin_origen, " +
                "iexdistri_origen, " +
                "'' desdistri_origen, " +
                "iexgrdinstruccion, " +
                "'' desinstruccion, " +
                "iexcentroform, " +
                "'' descentroform, " +
                "iexflgdomicil, " +
                "iexfeccrea, " +
                "iexfeccmod, " +
                "iexusucrea, " +
                "iexusumod " +
                "from iexempleado where iexcodcia = "+empleado.getIexcodcia()+"  and  iextipdocid='"+empleado.getIextipdocid()+"' and iexnrodoc='"+empleado.getIexnrodoc()+"' and iexflgest='1'  ";
        return template.query(sql, new ResultSetExtractor<List<Empleado>>() {
            public List<Empleado> extractData(ResultSet rs) throws SQLException, DataAccessException{
                List<Empleado> lista = new ArrayList<Empleado>();

                while(rs.next()) {
                    Empleado p = new Empleado();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexnomtra(rs.getString("iexnomtra"));
                    p.setIexapepat(rs.getString("iexapepat"));
                    p.setIexapemat(rs.getString("iexapemat"));
                    p.setIextipdocid(rs.getString("iextipdocid"));
                    p.setIexnrodoc(rs.getString("iexnrodoc"));
                    p.setIexfecnac(rs.getString("iexfecnac"));
                    p.setIexfecing(rs.getString("iexfecing"));
                    p.setIextipcese(rs.getString("iextipcese"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public Integer obtieneIdEmpleado(Empleado empleado){

        //StringBuilder sql = new StringBuilder();
        final Integer[] idcont = {0};
        final String[] result = new String[1];

        String sql=" SELECT coalesce(max(iexcodtra),0)+1 idcont  FROM IEXEMPLEADO WHERE IEXCODCIA="+empleado.getIexcodcia();

        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
                public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
                    while(rs.next()) {
                        idcont[0] = rs.getInt("idcont");
                        logger.info("idcont: "+idcont[0]);
                    }
                    return idcont[0];
                }
        });
    }

    public void  insertarCabecera(Empleado empleado){

        String result = null;
        StringBuilder sql = new StringBuilder();

        template.update(" insert into iexempleado(   "+
                " iexcodcia,     iexcodtra,         iexnomtra,     iexapepat,    iexapemat, " +
                //" iextipdocid,   iexnrodoc,         iexfecnac,     iexfecing,     " +
                " iextipdocid,   iexnrodoc, " +
                " iexcodsex,      iexflgest,     iexcodant,    iextiptra, " +
                " iexfeccrea,                 iexusucrea ) values "+
                //"  ( ?,?,?,?,?,   ?,?,to_date(?,'DD/MM/YYYY'),to_date(?,'DD/MM/YYYY'),    ?,?,?,?,  current_date,? )",
                " ( ?,?,?,?,?,?,?,?,?,?,?,current_date,? )",

            empleado.getIexcodcia(),
            empleado.getIexcodtra(),
            empleado.getIexnomtra(),
            empleado.getIexapepat(),
            empleado.getIexapemat(),
            empleado.getIextipdocid(),
            empleado.getIexnrodoc(),
            //empleado.getIexfecnac(),
            //empleado.getIexfecing(),
            empleado.getIexcodsex(),
            1,
            empleado.getIexcodant(),
            empleado.getIextiptra(),
            empleado.getIexusucrea());
    }

    public void actualizarFoto(Empleado empleado){

        template.update("update  iexempleado set  "+
                "    iexlogo=? where  iexcodcia=?   and  iexcodtra=?  ",

            empleado.getIexlogo(),
            empleado.getIexcodcia(),
            empleado.getIexcodtra());

    }

    public Empleado recuperarTurnos(Integer ciaid, Integer codtra){

        String sql = " select " +
                "    iexcodcia , iexcodtra, " +
                "iextipturno, " +
                "iexlunes, " +
                "iexmartes, " +
                "iexmiercoles, " +
                "iexjueves, " +
                "iexviernes, " +
                "iexsabado, " +
                "iexdomingo, " +
                "iexturlun, " +
                "iexturmar, " +
                "iexturmie, " +
                "iexturjue, " +
                "iexturvie, " +
                "iextursab, " +
                "iexturdom , iexctlasipag  "+
                " from iexempleado where iexcodcia="+ciaid+" and iexcodtra="+codtra+" ";
        return (Empleado) template.query(sql, new ResultSetExtractor<Empleado>() {
            public Empleado extractData(ResultSet rs) throws SQLException, DataAccessException{
                Empleado p = new Empleado();
                while(rs.next()) {
                    p.setIexcodcia(rs.getInt("iexcodcia")) ;
                    p.setIexcodtra(rs.getInt("iexcodtra")) ;
                    p.setIextipturno(rs.getString("iextipturno")) ;
                    p.setIexlunes(rs.getString("iexlunes")) ;
                    p.setIexmartes(rs.getString("iexmartes")) ;
                    p.setIexmiercoles(rs.getString("iexmiercoles")) ;
                    p.setIexjueves(rs.getString("iexjueves")) ;
                    p.setIexviernes(rs.getString("iexviernes")) ;
                    p.setIexsabado(rs.getString("iexsabado")) ;
                    p.setIexdomingo(rs.getString("iexdomingo")) ;
                    p.setIexturlun(rs.getInt("iexturlun")) ;
                    p.setIexturmar(rs.getInt("iexturmar")) ;
                    p.setIexturmie(rs.getInt("iexturmie")) ;
                    p.setIexturjue(rs.getInt("iexturjue")) ;
                    p.setIexturvie(rs.getInt("iexturvie")) ;
                    p.setIextursab(rs.getInt("iextursab")) ;
                    p.setIexturdom(rs.getInt("iexturdom")) ;
                    p.setIexctlasipag(rs.getString("iexctlasipag"));
                }
                return p;
            }
        });
    }

    public void actualizarTurnos(Empleado empleado){

        template.update("update  iexempleado set  "+
                "iextipturno=? , " +
                "iexlunes=? , " +
                "iexmartes=? , " +
                "iexmiercoles =? , " +
                "iexjueves=? , " +
                "iexviernes=? , " +
                "iexsabado=? , " +
                "iexdomingo=? , " +
                "iexturlun=?, " +
                "iexturmar=?, " +
                "iexturmie=?, " +
                "iexturjue=?, " +
                "iexturvie=?, " +
                "iextursab=?, " +
                "iexturdom=?  , iexctlasipag = ? "+
                "  where  iexcodcia=?   and  iexcodtra=?  ",

                empleado.getIextipturno(),
                empleado.getIexlunes(),
                empleado.getIexmartes(),
                empleado.getIexmiercoles(),
                empleado.getIexjueves(),
                empleado.getIexviernes(),
                empleado.getIexsabado(),
                empleado.getIexdomingo(),
                empleado.getIexturlun(),
                empleado.getIexturmar(),
                empleado.getIexturmie(),
                empleado.getIexturjue(),
                empleado.getIexturvie(),
                empleado.getIextursab(),
                empleado.getIexturdom(),
                empleado.getIexctlasipag(),
                //pst.setString(14, "usuario");
                empleado.getIexcodcia(),
                empleado.getIexcodtra());

    }


}
