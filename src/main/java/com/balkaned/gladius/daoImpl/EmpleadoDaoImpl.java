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
                //" iextipdocid=?,   iexnrodoc=?,         iexfecnac=to_date(?,'DD/MM/YYYY'), " +
                 " iextipdocid=?,   iexnrodoc=?,         " +
                " iexcodsex=?,     iexpaisemisor=?,     iexflgest=?,     iexcodant=?, " +
                " iexmodform=?,    iexnacion_origen=?,  iexdepart_origen=?,  iexprovin_origen=?, " +
                " iexdistri_origen=?,   iexgrdinstruccion=?,  iexcentroform=?,  iexflgdomicil=?, " +
                " iexfeccmod=CURRENT_TIMESTAMP,     iexcodlardist=?, iexnrotelf=?, iexemail=? , iexemail_coorp=? , iexusumod=? , iexestcivil=?  where  iexcodcia=?   and  iexcodtra=? ",

                empleado.getIexnomtra(),
                empleado.getIexapepat(),
                empleado.getIexapemat(),
                empleado.getIextipdocid(),
                empleado.getIexnrodoc(),
                //empleado.getIexfecnac(),
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
                //+ " iexfecing=TO_DATE(?,'DD/MM/YYYY'),  "+
                //" iexfecret=TO_DATE(?,'DD/MM/YYYY'),  "
                + " iextipcont  =?   ,    "
                //+ " iexfecini_cont =TO_DATE(?,'DD/MM/YYYY'),    iexfecfin_cont =TO_DATE(?,'DD/MM/YYYY'),  "
                + "    iexpliego =?,         iexsituaesp =?,       "
                + "  iexocupacion_pub =?, "+
                " iexocupacion_priv =?,   "
                + "iexpuesto =?,         iexccosto =?,           iexarea =?, "+
                " iexubilocal =?,        iexcateg_trabajador =?,  iexreglab =?,          "+
                " iexusumodlab=?, iexfecmodlab=CURRENT_TIMESTAMP "+
                " where  iexcodcia=?   and  iexcodtra=?  ",

                empleado.getIextiptra(),
                empleado.getIexsituapen(),
                //empleado.getIexfecing(),
                //empleado.getIexfecret(),
                empleado.getIextipcont(),
                //empleado.getIexfecini_cont(),
                //empleado.getIexfecfin_cont(),
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

        template.update(" update  iexempleado set  "+
                " iexcodafp = ?  , "+
                //" iexfecafp = to_date(?,'DD/MM/YYYY'), "+
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
                " iexfecmodseg=CURRENT_TIMESTAMP,  iexusumodseg=?  where  iexcodcia=?   and  iexcodtra=?  ",

                empleado.getIexcodafp(),
                //empleado.getIexfecafp(),
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

    /*
    public List<Empleado> listarEmpleadoActivos(Integer codcia) throws DAOException {

        List<Empleado> lista = null;
        String sql = " select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexnomtra, " +
                "iexapepat, " +
                "iexapemat, " +
                "iextipdocid, " +
                "iexnrodoc, " +
                "to_char(iexfecnac,'dd/mm/yyyy') iexfecnac, " +
                "to_char(iexfecing,'dd/mm/yyyy') iexfecing, " +
                "to_char(iexfecret,'dd/mm/yyyy') iexfecret, " +
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
                "from iexempleado where iexcodcia="+codcia+" and iexflgest='1'  order by iexapepat , iexapemat, iexnomtra asc  ";


        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                Statement st = cn.createStatement();


                ResultSet rs = st.executeQuery(sql);) {

            lista = new ArrayList<>();
            while (rs.next()) {
                Empleado p = new Empleado();

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

                p.setIexusucrea(rs.getString("iexusucrea"));
                p.setIexusumod(rs.getString("iexusumod"));


                p.setDestipcese(rs.getString("destipcese"));
                p.setDessex(rs.getString("dessex"));
                p.setDespaisemisor(rs.getString("despaisemisor"));
                p.setDesestado(rs.getString("desestado"));
                p.setDestiptra(rs.getString("destiptra"));


                lista.add(p);

                //System.out.println("proceso: "+rs.getString("prodespro"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }



    public List<Empleado> listarEmpleadoInactivos(Integer codcia) throws DAOException {

        List<Empleado> lista = null;
        String sql = " select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexnomtra, " +
                "iexapepat, " +
                "iexapemat, " +
                "iextipdocid, " +
                "iexnrodoc, " +
                "to_char(iexfecnac,'dd/mm/yyyy') iexfecnac, " +
                "to_char(iexfecing,'dd/mm/yyyy') iexfecing, " +
                "to_char(iexfecret,'dd/mm/yyyy') iexfecret, " +
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
                "from iexempleado where iexcodcia="+codcia+" and iexflgest='0' and  iexfecret is not null  order by iexapepat , iexapemat, iexnomtra, iexfecret asc  ";


        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                Statement st = cn.createStatement();


                ResultSet rs = st.executeQuery(sql);) {

            lista = new ArrayList<>();
            while (rs.next()) {
                Empleado p = new Empleado();

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

                p.setIexusucrea(rs.getString("iexusucrea"));
                p.setIexusumod(rs.getString("iexusumod"));


                p.setDestipcese(rs.getString("destipcese"));
                p.setDessex(rs.getString("dessex"));
                p.setDespaisemisor(rs.getString("despaisemisor"));
                p.setDesestado(rs.getString("desestado"));
                p.setDestiptra(rs.getString("destiptra"));


                lista.add(p);

                //System.out.println("proceso: "+rs.getString("prodespro"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }


    public List<Empleado>  validarCabecera(Empleado Empleado) throws DAOException {

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
                "from iexempleado where iexcodcia = "+Empleado.getIexcodcia()+"  and  iextipdocid='"+Empleado.getIextipdocid()+"' and iexnrodoc='"+Empleado.getIexnrodoc()+"' and iexflgest='1'  ";


        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                Statement st = cn.createStatement();


                ResultSet rs = st.executeQuery(sql);) {

            lista = new ArrayList<>();
            while (rs.next()) {
                Empleado p = new Empleado();

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


                lista.add(p);

                //System.out.println("proceso: "+rs.getString("prodespro"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOEmpleadoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Termino validar Cabecera ");

        return lista;


    }

    public Integer obtieneIdEmpleado(Empleado Empleado) throws DAOException {

        String result = null;
        StringBuilder sql = new StringBuilder();
        Integer idcont =0;


        sql.append(" SELECT coalesce(max(iexcodtra),0)+1 idcont  FROM IEXEMPLEADO WHERE IEXCODCIA="+Empleado.getIexcodcia()+"  ");

        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                PreparedStatement pst = cn.prepareStatement(sql.toString());
                ResultSet rs = pst.executeQuery();
        ) {
            // Statement st = cn.createStatement();
            if (rs.next()) {
                idcont= Integer.valueOf(rs.getString("idcont"));
            }


            pst.close();
            cn.close();

        } catch (SQLException e) {
            result = e.getMessage();
            idcont=-1;
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }



        return idcont ;

    }


    public void  insertarCabecera(Empleado Empleado) throws DAOException {

        String result = null;
        StringBuilder sql = new StringBuilder();

        System.out.println("Insertar cabecera");

        sql.append(" insert into iexempleado(   "+
                " iexcodcia,     iexcodtra,         iexnomtra,     iexapepat,    iexapemat, " +
                " iextipdocid,   iexnrodoc,         iexfecnac,     iexfecing,     " +
                " iexcodsex,      iexflgest,     iexcodant,    iextiptra, " +
                " iexfeccrea,                 iexusucrea ) values "+
                "  ( ?,?,?,?,?,   ?,?,to_date(?,'DD/MM/YYYY'),to_date(?,'DD/MM/YYYY'),    ?,?,?,?,  current_date,? )");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {

            pst.setInt(1, Empleado.getIexcodcia());
            pst.setInt(2, Empleado.getIexcodtra());
            pst.setString(3, Empleado.getIexnomtra());
            pst.setString(4, Empleado.getIexapepat());
            pst.setString(5, Empleado.getIexapemat());

            pst.setString(6, Empleado.getIextipdocid());
            pst.setString(7, Empleado.getIexnrodoc());
            pst.setString(8, Empleado.getIexfecnac());
            pst.setString(9, Empleado.getIexfecing());

            pst.setString(10, Empleado.getIexcodsex());
            pst.setString(11, "1");
            pst.setString(12, Empleado.getIexcodant());
            pst.setString(13, Empleado.getIextiptra());

            pst.setString(14, "usuario");

            System.out.println(sql);

            pst.execute();




            pst.close();
            cn.close();

        } catch (SQLException e) {
            result = e.getMessage();
            System.out.println("Error en insertar cabecera"+e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    public void  actualizarOtros(Empleado Empleado) throws DAOException  {

        String result = null;
        StringBuilder sql = new StringBuilder();

        sql.append(" update  iexempleado set  "+
                "    iexnomtra=?,     iexapepat=?,    iexapemat=?, " +
                " iextipdocid=?,   iexnrodoc=?,         iexfecnac=?,     iexfecing=?,    iextipcese=?, " +
                " iexcodsex=?,     iexpaisemisor=?,     iexflgest=?,     iexcodant=?,    iextiptra=?, " +
                " iexmodform=?,    iexnacion_origen=?,  iexdepart_origen=?,  iexprovin_origen=?,    " +
                " iexdistri_origen=?,   iexgrdinstruccion=?,  iexcentroform=?,  iexflgdomicil=?,    " +
                " iexfeccrea=?,  iexusucrea=?  where  iexcodcia=?   and  iexcodtra=?  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {


            pst.setString(1, Empleado.getIexnomtra());
            pst.setString(2, Empleado.getIexapepat());
            pst.setString(3, Empleado.getIexapemat());

            pst.setString(4, Empleado.getIextipdocid());
            pst.setString(5, Empleado.getIexnrodoc());
            pst.setString(6, Empleado.getIexfecnac());
            pst.setString(7, Empleado.getIexfecing());
            pst.setString(8, Empleado.getIextipcese());

            pst.setString(9, Empleado.getIexcodsex());
            pst.setString(10, Empleado.getIexpaisemisor());
            pst.setString(11, Empleado.getIexflgest());
            pst.setString(12, Empleado.getIexcodant());
            pst.setString(13, Empleado.getIextiptra());

            pst.setString(14, Empleado.getIexmodform());
            pst.setString(15, Empleado.getIexnacion_origen());
            pst.setString(16, Empleado.getIexdepart_origen());
            pst.setString(17, Empleado.getIexprovin_origen());



            pst.setString(18, Empleado.getIexdistri_origen());
            pst.setString(19, Empleado.getIexgrdinstruccion());
            pst.setString(20, Empleado.getIexcentroform());
            pst.setString(21, Empleado.getIexflgdomicil());

            pst.setString(22, Empleado.getIexusumod());

            pst.setInt(23, Empleado.getIexcodcia());
            pst.setInt(24, Empleado.getIexcodtra());


            pst.execute();




            pst.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            result = e.getMessage();
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void  actualizarDireccion(Empleado Empleado) throws DAOException  {

        String result = null;
        StringBuilder sql = new StringBuilder();

        sql.append(" update  iexempleado set  "+
                "   iextipvia_dom1=?,        iexnomvia_dom1 =?,             iexnrovia_dom1 =?,     		 iexdeptin_dom1 =?, " +
                "  iexinterior_dom1=?,      iexmanzana_dom1 =?,  		   iexlote_dom1 =?,  			  iexkilometro_dom1 =?, " +
                " iexblock_dom1 =?,         iexetapa_dom1 =?,   		   iextipzona_dom1 =?,   		  iexnomzona_dom1 =?,  " +
                " iexreferencia_dom1 =?,    iexubigeo_dom1 =?,             iextipvia_dom2 =?,   		  iexnomvia_dom2 =?,  " +
                " iexnrovia_dom2 =?,        iexdeptin_dom2 =?,             iexinterior_dom2 =?,          iexmanzana_dom2 =?,  " +
                " iexlote_dom2  =?,          iexkilometro_dom2  =?,          iexblock_dom2  =?,             iexetapa_dom2 =? , " +
                " iextipzona_dom2 =?,       iexnomzona_dom2  =?,    		   iexreferencia_dom2 =?,        iexubigeo_dom2 =? ," +
                " iexflgdomicilio =? , " +
                " iexfecmoddom=CURRENT_TIMESTAMP,  iexusumoddom=?  , "+
                " iexnacion_origen1 =?  ,   "+
                "  iexdepart_origen1 =?  , "+
                "  iexprovin_origen1 =?  , "+
                "  iexnacion_origen2 =?  , "+
                "  iexdepart_origen2  =? ,  "+
                "  iexprovin_origen2 =?  "+
                "  where  iexcodcia=?   and  iexcodtra=?  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {

            pst.setString(1, Empleado.getIextipvia_dom1());
            pst.setString(2, Empleado.getIexnomvia_dom1());
            pst.setString(3, Empleado.getIexnrovia_dom1());
            pst.setString(4, Empleado.getIexdeptin_dom1());

            pst.setString(5, Empleado.getIexinterior_dom1());
            pst.setString(6, Empleado.getIexmanzana_dom1());
            pst.setString(7, Empleado.getIexlote_dom1());
            pst.setString(8, Empleado.getIexkilometro_dom1());

            pst.setString(9, Empleado.getIexblock_dom1());
            pst.setString(10, Empleado.getIexetapa_dom1());
            pst.setString(11, Empleado.getIextipzona_dom1());
            pst.setString(12, Empleado.getIexnomzona_dom1());

            pst.setString(13, Empleado.getIexreferencia_dom1());
            pst.setString(14, Empleado.getIexubigeo_dom1());
            pst.setString(15, Empleado.getIextipvia_dom2());
            pst.setString(16, Empleado.getIexnomvia_dom2());

            pst.setString(17, Empleado.getIexnrovia_dom2());
            pst.setString(18, Empleado.getIexdeptin_dom2());
            pst.setString(19, Empleado.getIexinterior_dom2());
            pst.setString(20, Empleado.getIexmanzana_dom2());

            pst.setString(21, Empleado.getIexlote_dom2());
            pst.setString(22, Empleado.getIexkilometro_dom2());
            pst.setString(23, Empleado.getIexblock_dom2());
            pst.setString(24, Empleado.getIexetapa_dom2 ());

            pst.setString(25, Empleado.getIextipzona_dom2());
            pst.setString(26, Empleado.getIexnomzona_dom2());
            pst.setString(27, Empleado.getIexreferencia_dom2());
            pst.setString(28, Empleado.getIexubigeo_dom2());

            pst.setString(29, Empleado.getIexflgdomicilio());

            pst.setString(30, Empleado.getIexusumod());


            pst.setString(31, Empleado.getIexnacion_origen1());
            pst.setString(32, Empleado.getIexdepart_origen1());
            pst.setString(33, Empleado.getIexprovin_origen1());

            pst.setString(34, Empleado.getIexnacion_origen2());
            pst.setString(35, Empleado.getIexdepart_origen2());
            pst.setString(36, Empleado.getIexprovin_origen2());

            pst.setInt(37, Empleado.getIexcodcia());
            pst.setInt(38, Empleado.getIexcodtra());








            pst.execute();




            pst.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            result = e.getMessage();
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public Empleado recuperarCabecera(Integer ciaid, Integer codtra) throws DAOException {


        Empleado p = null;

        List<Empleado> lista = null;
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


        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                Statement st = cn.createStatement();


                ResultSet rs = st.executeQuery(sql);) {

            lista = new ArrayList<>();
            if (rs.next()) {
                p = new Empleado();

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

                p.setDespuesto(rs.getString("despuesto"));
                //lista.add(p);

                //System.out.println("proceso: "+rs.getString("prodespro"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;

    }





    public void  cesarEmpleado(Empleado Empleado) throws DAOException  {

        String result = null;
        StringBuilder sql = new StringBuilder();

        sql.append(" update  iexempleado set  "+
                "    iexflgest='0' where  iexcodcia=?   and  iexcodtra=?  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {



            pst.setInt(1, Empleado.getIexcodcia());
            pst.setInt(2, Empleado.getIexcodtra());


            pst.execute();




            pst.close();
            cn.close();

        } catch (SQLException e) {
            result = e.getMessage();
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


    public void  eliminarEmpleado(Integer ciaid , Integer codtra) throws DAOException{

        String result = null;
        StringBuilder sql = new StringBuilder();

        sql.append(" delete from  iexempleado where  iexcodcia="+ciaid+"   and  iexcodtra="+codtra+"  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {
            pst.execute();




            pst.close();
            cn.close();

        } catch (SQLException e) {
            result = e.getMessage();
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


    public void   actualizarFoto(Empleado Empleado) throws DAOException  {

        String result = null;
        StringBuilder sql = new StringBuilder();

        sql.append(" update  iexempleado set  "+
                "    iexlogo=? where  iexcodcia=?   and  iexcodtra=?  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {


            pst.setString(1, Empleado.getIexlogo());
            pst.setInt(2, Empleado.getIexcodcia());
            pst.setInt(3, Empleado.getIexcodtra());


            pst.execute();




            pst.close();
            cn.close();

        } catch (SQLException e) {
            result = e.getMessage();
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void  actualizarTurnos(Empleado Empleado) throws DAOException  {

        String result = null;
        StringBuilder sql = new StringBuilder();

        sql.append(" update  iexempleado set  "+
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
                "  where  iexcodcia=?   and  iexcodtra=?  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {

            pst.setString(1, Empleado.getIextipturno());
            pst.setString(2, Empleado.getIexlunes());

            pst.setString(3, Empleado.getIexmartes());
            pst.setString(4, Empleado.getIexmiercoles());
            pst.setString(5, Empleado.getIexjueves());
            pst.setString(6, Empleado.getIexviernes());
            pst.setString(7, Empleado.getIexsabado());
            pst.setString(8, Empleado.getIexdomingo());

            pst.setInt(9 , Empleado.getIexturlun());
            pst.setInt(10 , Empleado.getIexturmar());
            pst.setInt(11 , Empleado.getIexturmie());
            pst.setInt(12 , Empleado.getIexturjue());
            pst.setInt(13 , Empleado.getIexturvie());
            pst.setInt(14 , Empleado.getIextursab());
            pst.setInt(15 , Empleado.getIexturdom());
            pst.setString(16 , Empleado.getIexctlasipag());

            //pst.setString(14, "usuario");

            pst.setInt(17, Empleado.getIexcodcia());
            pst.setInt(18, Empleado.getIexcodtra());


            pst.execute();




            pst.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            result = e.getMessage();
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Empleado recuperarTurnos(Integer ciaid , Integer codtra) throws DAOException {
        Empleado p = null;

        List<Empleado> lista = null;
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


        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                Statement st = cn.createStatement();


                ResultSet rs = st.executeQuery(sql);) {

            lista = new ArrayList<>();
            if (rs.next()) {
                p = new Empleado();

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
                //lista.add(p);

                //System.out.println("proceso: "+rs.getString("prodespro"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }


    public void insertarTraMas(List<Empleado> Empleado) throws DAOException {


        String result = null;
        StringBuilder sql = new StringBuilder();

        Iterator<Empleado> pi = Empleado.iterator();
        Empleado empdat = null;
        Integer v_codtra = 0;
        //  sql.append("call pl_migra_datapla_tra(?,?,?,?,?,?,?,?)");

        sql.append("  insert into iexempleado ( " +
                " iexcodcia,     iexcodtra,     iextipdocid,     iexnrodoc,     " +
                " iexapepat,     iexapemat,     iexnomtra,       iexfecnac, " +
                " iexcodsex,     iexpuesto,     iexarea,         iexubilocal, " +
                " iexfecing,     iexreglab,     iextiptra,       iexessalud, " +
                " iexsenati,     iexsctrpension,    iexcodafp,       iexcussp, " +
                " iexfecafp,     iexflgcomi_mix,     iexcodban_hab,     iextipban_hab, " +
                " iexcodmon_hab,    iexnrocta_hab,    iexflgbancci_hab , iexflgest , iexccosto  " +
                "		 ) values( " +
                " ?,       ?,         ?,         ? ,    " +
                " ?,       ?,         ?,         to_date(?,'DD/MM/YYYY') ,   " +
                " ?,       ?,         ?,         ? ,  " +
                " to_date(?,'DD/MM/YYYY'),       ?,         ?,         ? ,  " +
                " ?,       ?,         ?,         ? ,  " +
                " to_date(?,'DD/MM/YYYY'),       ?,         ?,         ? ,   " +
                " ?,       ?,         ? ,       ?   ,  ?  " +
                "    ) ");




        System.out.println(sql);

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
        ) {

            CallableStatement pst=null;
            while(pi.hasNext())  /// iteraciòn por cada trabajador
            {
                empdat = pi.next();
                pst =cn.prepareCall(sql.toString());
                v_codtra = obtieneIdEmpleado(empdat);




                pst.setInt(1, empdat.getIexcodcia());
                pst.setInt(2, v_codtra);
                pst.setString(3, empdat.getIextipdocid());
                pst.setString(4, empdat.getIexnrodoc());

                //  " iexapepat,     iexapemat,     iexnomtra,       iexfecnac, " +
                pst.setString(5, empdat.getIexapepat());
                pst.setString(6, empdat.getIexapemat());
                pst.setString(7, empdat.getIexnomtra());
                pst.setString(8, empdat.getIexfecnac());

                //" iexcodsex,     iexpuesto,     iexarea,         iexubilocal, " +
                pst.setString(9, empdat.getIexcodsex());
                pst.setString(10, empdat.getIexpuesto());
                pst.setString(11, empdat.getIexarea());
                pst.setString(12, empdat.getIexubilocal());

                //" iexfecing,     iexreglab,     iextiptra,       iexessalud, " +

                pst.setString(13, empdat.getIexfecing());
                pst.setString(14, empdat.getIexreglab());
                pst.setString(15, empdat.getIextiptra());
                pst.setString(16, empdat.getIexessalud());

                //" iexsenati,     iexflgsctr,    iexcodafp,       iexcussp, " +

                pst.setString(17, empdat.getIexsenati());
                pst.setString(18, empdat.getIexsctrpension());
                pst.setString(19, empdat.getIexcodafp());
                pst.setString(20, empdat.getIexcussp());

                //" iexfecafp,     iexflgcomi_mix,     iexcodban_hab,     iextipban_hab, " +

                pst.setString(21, empdat.getIexfecafp());
                pst.setString(22, empdat.getIexflgcomi_mix());
                pst.setString(23, empdat.getIexcodban_hab());
                pst.setString(24, empdat.getIextipban_hab());

                //    " iexcodmon_hab,    iexnrocta_hab,    iexflgbancci_hab  " +

                pst.setString(25, empdat.getIexcodmon_hab());
                pst.setString(26, empdat.getIexnrocta_hab());
                pst.setString(27, empdat.getIexflgbancci_hab());
                pst.setString(28, "1");
                pst.setString(29, empdat.getIexccosto());




                //   System.out.println(" codtra xxxxxxrrrrrr "+empdat.getIexapepat()+" - "+empdat.getIexapemat()+" - "+empdat.getIexnomtra() );




                try{
                    pst.execute();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    result = e.getMessage();
                }

            }


            pst.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            result = e.getMessage();
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    public String listarEmpleadoDyn(Integer ciaid, String destra) throws DAOException {


        List<Empleado> lista = null;
        String sql = " select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexnomtra||' '|| " +
                "iexapepat||' '||  " +
                "iexapemat as destra  " +
                "from iexempleado where iexcodcia="+ciaid+" and iexflgest='1' and '%'||upper(iexapepat)||'%'||upper(iexapemat)||'%'||upper(iexnomtra)||'%' like  '%"+destra.toUpperCase()+"%' order by iexapepat , iexapemat, iexnomtra asc  ";

        String var ="";
        System.out.println(sql);

        var = " <ul class=\"list-unstyled\"> ";
        try (
                Connection cn = cf.getConnection();
                Statement st = cn.createStatement();


                ResultSet rs = st.executeQuery(sql);) {

            lista = new ArrayList<>();
            while (rs.next()) {
                Empleado p = new Empleado();

                p.setIexcodcia(rs.getInt("iexcodcia")) ;
                p.setIexcodtra(rs.getInt("iexcodtra")) ;
                p.setIexnomtra(rs.getString("destra")) ;



                // lista.add(p);
                var = var + "<li>["+p.getIexcodtra()+"] "+p.getIexnomtra()+" </li>" ;
                //System.out.println("proceso: "+rs.getString("prodespro"));
            }

            var = var + "  </ul>  " ;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return var;
    }


    public Integer  reingresarEmpleado(Integer ciaid, Integer codtrareing , String fechaing , String desusu  ) throws DAOException  {

        String result = null;
        StringBuilder sql = new StringBuilder();

        System.out.println("Entra al DAO reingresa");
        Empleado Empleado = new Empleado();
        Empleado.setIexcodcia(ciaid);

        Integer codnew =  obtieneIdEmpleado(Empleado)  ;

        sql.append("call pl_reingresa_trabajador(?,?,?,? ,?  )");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {

            pst.setInt(1, ciaid);
            pst.setInt(2,  codtrareing );
            pst.setString(3, fechaing);
            pst.setString(4,  desusu);
            pst.setInt(5,  codnew );

            pst.execute();

            pst.close();
            cn.close();

        } catch (SQLException e) {
            result = e.getMessage();
            System.out.println(e.getMessage());
            codnew =-1 ;
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codnew ;
    }*/

}
