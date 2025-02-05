package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.*;
import com.balkaned.gladius.dao.EmpleadoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository("EmpleadoDao")
public class EmpleadoDaoImpl implements EmpleadoDao {

    private static final String CLASS_NAME = "EmpleadoDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Empleado> listarEmpCabecera(Empleado empleado) {

        String sql = "select " +
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
                "from iexempleado " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", empleado.getIexcodcia())
                .addValue("codtra", empleado.getIexcodtra());

        List<Empleado> lsEmpl = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return lsEmpl;
    }

    public List<Empleado> listarEmpleado(Empleado empleado) {

        String sql = "select " +
                "e.iexcodcia, " +
                "e.iexcodtra, " +
                "e.iexnomtra, " +
                "e.iexapepat, " +
                "e.iexnomtra || '/' || e.iexapepat as completo, " +
                "e.iexapemat, " +
                "e.iextipdocid, " +
                "d.desdet iextipdocid, " +
                "e.iexnrodoc, " +
                "to_char(e.iexfecnac,'dd/mm/yyyy') iexfecnac, " +
                "to_char(e.iexfecing,'dd/mm/yyyy') iexfecing, " +
                "to_char(e.iexfecret,'dd/mm/yyyy') iexfecret, " +
                "e.iextipcese, " +
                "'' as destipcese, " +
                "e.iexcodsex, " +
                "d2.desdet dessex, " +
                "e.iexpaisemisor, " +
                "'' as despaisemisor, " +
                "e.iexflgest, " +
                "d3.desdet desestado, " +
                "e.iexcodant, " +
                "e.iextiptra, " +
                "d4.desdet destiptra, " +
                "p1.iexdespuesto iexpuesto, " +
                "p1.iexdespuesto iexpuestoCap, " +
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
                "e.iexfeccrea, " +
                "e.iexfeccmod, " +
                "e.iexusucrea, " +
                "e.iexusumod " +
                "from iexempleado e " +
                "left join iexttabled d on d.iexcodtab = '3' and d.iexkey = e.iextipdocid " +
                "left join iexttabled d2 on d2.iexcodtab = '50' and d2.iexkey = e.iexcodsex " +
                "left join iexttabled d3 on d3.iexcodtab = '54' and d3.iexkey = e.iexflgest " +
                "left join iexpuesto p1 on p1.iexcodcia = e.iexcodcia and p1.iexpuesto = e.iexpuesto " +
                "left join iexttabled d4 on d4.iexcodtab = '8' and d4.iexkey = e.iextiptra " +
                "where e.iexcodcia = :codcia ";

        if (empleado.getTxtfinder() != null) {
            sql = sql + " and '%'||iexnomtra||'%'||iexapepat||'%'||iexapemat||'%'||iexnrodoc||'%' " +
                    "like '%:txtfinder%' ";
        }

        if (empleado.getIextiptra() != null && !empleado.getIextiptra().equals("%")) {
            sql = sql + " and iextiptra like '%:tiptra%' ";
        }


        if (empleado.getIexflgest() != null && !empleado.getIexflgest().equals("%")) {
            sql = sql + " and iexflgest like '%:flgest%' ";
        }

        if (empleado.getFeciniing_par() != "" && empleado.getFecfining_par() != "") {
            if (empleado.getFeciniing_par() != null && empleado.getFecfining_par() != null) {
                sql = sql + " and iexfecing >= to_date(':feciniing_par','dd/mm/yyyy') " +
                        " and iexfecing <= to_date(':fecfining_par','dd/mm/yyyy') ";
            }
        }

        sql = sql + " order by iexapepat, iexapemat, iexnomtra asc";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", empleado.getIexcodcia())
                .addValue("txtfinder", empleado.getTxtfinder())
                .addValue("tiptra", empleado.getIextiptra())
                .addValue("flgest", empleado.getIexflgeps())
                .addValue("feciniing_par", empleado.getFeciniing_par())
                .addValue("fecfining_par", empleado.getFecfining_par());

        List<Empleado> lsEmpleado = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return lsEmpleado;
    }

    public Empleado recuperarCabecera(Integer ciaid, Integer codtra) {

        String sql = "select " +
                "e.iexcodcia, " +
                "e.iexcodtra, " +
                "e.iexnomtra, " +
                "e.iexapepat, " +
                "e.iexapemat, " +
                //"e.iexnomtra || ' ' || e.iexapepat || ' ' || e.iexapemat as completo, " +
                "e.iexnomtra || '/' || e.iexapepat || '/' || e.iexapemat as completoDetalleEmpl, " +
                "e.iextipdocid, " +
                "e.iexnrodoc, " +
                "to_char(e.iexfecnac,'dd/mm/yyyy') iexfecnac, " +
                "to_char(e.iexfecing,'dd/mm/yyyy') iexfecing, " +
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
                "e.iexcodlardist, e.iexnrotelf, e.iexemail, e.iexemail_coorp, " +
                "to_char(e.iexfeccrea,'dd/mm/yyyy hh24:mi:ss') iexfeccrea, " +
                "to_char(e.iexfeccmod,'dd/mm/yyyy hh24:mi:ss') iexfeccmod, " +
                "e.iexusucrea, " +
                "e.iexusumod, e.iexlogo, e.iexestcivil, " +
                "e.iexreglab, " +
                "get_texto_direccion(e.iexcodcia, e.iexcodtra) direccion1, " +
                " ( select p.iexdespuesto from iexpuesto p " +
                "   where p.iexcodcia = e.iexcodcia and p.iexpuesto = e.iexpuesto) despuesto " +
                "from iexempleado e " +
                "where e.iexcodcia = :codcia and " +
                "e.iexcodtra = :codtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", ciaid)
                .addValue("codtra", codtra);

        Empleado empl = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return empl;
    }

    public Empleado recuperarLaboral(Integer ciaid, Integer codtra) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iextiptra, " +
                "iexsituapen, " +
                "to_char(iexfecing,'DD/MM/YYYY') iexfecing, " +
                "TO_CHAR(iexfecret,'DD/MM/YYYY') iexfecret, iextipcont, " +
                "to_char(iexfecini_cont,'DD/MM/YYYY') iexfecini_cont, " +
                "TO_CHAR(iexfecfin_cont,'DD/MM/YYYY') iexfecfin_cont, " +
                "iexpliego, iexsituaesp, iexocupacion_pub, " +
                "iexocupacion_priv, iexpuesto, iexccosto, iexarea, " +
                "iexubilocal, iexcateg_trabajador, iexreglab, " +
                "to_char(iexfecmodlab,'dd/mm/yyyy hh24:mi:ss') iexfecmodlab, iexusumodlab " +
                "from iexempleado " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", ciaid)
                .addValue("codtra", codtra);

        Empleado empl = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return empl;
    }

    public Empleado recuperarPagos(Integer ciaid, Integer codtra) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iextippago, " +
                "iexperrem, " +
                "coalesce(iexmontorem,0) as iexmontorem, " +
                "iexcodban_hab, " +
                "iextipban_hab, " +
                "iexcodmon_hab, " +
                "iexflgbancci_hab, " +
                "iexnrocta_hab, " +
                "iextipban_cts, " +
                "iexcodban_cts, " +
                "iexcodmon_cts, " +
                "iexflgbancci_cts, " +
                "iexnrocta_cts, " +
                "to_char(iexfecmodpag,'dd/mm/yyyy hh24:mi:ss') iexfecmodpag, " +
                "iexusumodpag " +
                "from iexempleado " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", ciaid)
                .addValue("codtra", codtra);

        Empleado empl = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return empl;
    }

    public Empleado recuperarSegSocial(Integer ciaid, Integer codtra) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexcodafp, " +
                "to_char(iexfecafp,'DD/MM/YYYY') iexfecafp, " +
                "iexcussp, " +
                "iexessalud, " +
                "iexsenati, " +
                "iexflgeps, " +
                "iexcodeps, " +
                "iexconvdobtrib, " +
                "iexdiscapacidad, " +
                "iexsctrpension, " +
                "iexregalter, " +
                "iexjornmax, " +
                "iexhornocturno, " +
                "iexsindicalizado, " +
                "iexexon5ta, " +
                "iexnroruc_cas, " +
                "iexmadreresp, " +
                "iextipocentoedu, " +
                "iexflgcomi_mix, " +
                "iexflgjubil, " +
                "iexflgmas_vida, " +
                "to_char(iexfecmodseg,'dd/mm/yyyy hh24:mi:ss') iexfecmodseg, " +
                "iexusumodseg " +
                "from iexempleado " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra ";

        SqlParameterSource namedParemters = new MapSqlParameterSource()
                .addValue("codcia", ciaid)
                .addValue("codtra", codtra);

        Empleado empl = namedParameterJdbcTemplate.queryForObject(sql, namedParemters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return empl;
    }

    public Empleado recuperarDireccion(Integer ciaid, Integer codtra) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iextipvia_dom1, iexnomvia_dom1, iexnrovia_dom1, iexdeptin_dom1, " +
                "iexinterior_dom1, iexmanzana_dom1, iexlote_dom1, iexkilometro_dom1, " +
                "iexblock_dom1, iexetapa_dom1, iextipzona_dom1, iexnomzona_dom1, " +
                "iexreferencia_dom1, iexubigeo_dom1, iextipvia_dom2, iexnomvia_dom2, " +
                "iexnrovia_dom2, iexdeptin_dom2, iexinterior_dom2, iexmanzana_dom2, " +
                "iexlote_dom2, iexkilometro_dom2, iexblock_dom2, iexetapa_dom2, " +
                "iextipzona_dom2, iexnomzona_dom2, iexreferencia_dom2, iexubigeo_dom2, " +
                "iexflgdomicilio, to_char(iexfecmoddom,'dd/mm/yyyy hh24:mi:ss') iexfecmoddom, " +
                "iexusumoddom, iexnacion_origen1, " +
                "iexdepart_origen1, " +
                "iexprovin_origen1, " +
                "iexnacion_origen2, " +
                "iexdepart_origen2, " +
                "iexprovin_origen2 " +
                "from iexempleado " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", ciaid)
                .addValue("codtra", codtra);

        Empleado empl = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return empl;
    }


    public void actualizarCabecera(Empleado empleado) {

        String sql = "update iexempleado set " +
                "iexnomtra=?, iexapepat=?, iexapemat=?, " +
                "iextipdocid=?, iexnrodoc=?, iexfecnac = to_date(?,'DD/MM/YYYY'), " +
                "iexcodsex=?, iexpaisemisor=?, iexflgest=?, iexcodant=?, " +
                "iexmodform=?, iexnacion_origen=?, iexdepart_origen=?, iexprovin_origen=?, " +
                "iexdistri_origen=?, iexgrdinstruccion=?, iexcentroform=?, iexflgdomicil=?, " +
                "iexfeccmod = CURRENT_TIMESTAMP, iexcodlardist=?, iexnrotelf=?, iexemail=?, " +
                "iexemail_coorp=?, iexusumod=?, iexestcivil=? " +
                "where iexcodcia=? and iexcodtra=? ";

        log.info("empleado.getIexnomtra(): {} ",empleado.getIexnomtra());
        log.info("empleado.getIexnomtraUpd(): {} ",empleado.getIexnomtraUpd());
        log.info("empleado.getIexapepat(): {} ",empleado.getIexapepat());
        log.info("empleado.getIexapemat(): {} ",empleado.getIexapemat());

        jdbc.update(sql,
                empleado.getIexnomtraUpd(),
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
                empleado.getIexcodtra()
        );
    }

    public void actualizarLaboral(Empleado empleado) {

        if (empleado.getIexfecret().isEmpty()) {
            empleado.setIexfecret(null);
        } else {
            empleado.setIexfecret(empleado.getIexfecret());
        }

        if (empleado.getIexfecini_cont().isEmpty()) {
            empleado.setIexfecini_cont(null);
        } else {
            empleado.setIexfecini_cont(empleado.getIexfecini_cont());
        }

        if (empleado.getIexfecfin_cont().isEmpty()) {
            empleado.setIexfecfin_cont(null);
        } else {
            empleado.setIexfecfin_cont(empleado.getIexfecfin_cont());
        }

        String sql = "update iexempleado set " +
                "iextiptra=?, iexsituapen =?, " +
                "iexfecing=TO_DATE(?,'DD/MM/YYYY'), " +
                "iexfecret=TO_DATE(?,'DD/MM/YYYY'), " +
                "iextipcont=?, " +
                "iexfecini_cont = TO_DATE(?,'DD/MM/YYYY'), " +
                "iexfecfin_cont =TO_DATE(?,'DD/MM/YYYY'), " +
                "iexpliego =?, iexsituaesp =?, " +
                "iexocupacion_pub =?, " +
                "iexocupacion_priv =?, " +
                "iexpuesto =?, iexccosto =?, iexarea =?, " +
                "iexubilocal =?, iexcateg_trabajador =?, iexreglab =?, " +
                "iexusumodlab=?, iexfecmodlab=CURRENT_TIMESTAMP " +
                "where iexcodcia=? " +
                "and iexcodtra=? ";

        jdbc.update(sql,
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
                empleado.getIexcodtra()
        );
    }

    public void actualizarPagos(Empleado empleado) {

        String sql = "update iexempleado set " +
                "iextippago=?, iexperrem=?, iexmontorem=?, " +
                "iexcodban_hab=?, iexflgbancci_hab=?, iexcodmon_hab =?, " +
                "iextipban_hab=?, iexnrocta_hab=?, iexcodban_cts=?, " +
                "iexflgbancci_cts =?, iexcodmon_cts =?, iextipban_cts=?, " +
                "iexnrocta_cts=?, iexfecmodpag=CURRENT_TIMESTAMP, " +
                "iexusumodpag=?  " +
                "where iexcodcia=? and " +
                "iexcodtra=? ";

        jdbc.update(sql,
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
                empleado.getIexcodtra()
        );
    }

    public void actualizarSegSocial(Empleado empleado) {

        String sql = "update iexempleado set " +
                "iexcodafp = ?, " +
                "iexfecafp = to_date(?,'DD/MM/YYYY'), " +
                "iexcussp = ?, " +
                "iexessalud =?, " +
                "iexsenati =?, " +
                "iexflgeps =?, " +
                "iexcodeps =?, " +
                "iexconvdobtrib =?, " +
                "iexdiscapacidad =?, " +
                "iexsctrpension =?, " +
                "iexregalter =?, " +
                "iexjornmax =?, " +
                "iexhornocturno =?, " +
                "iexsindicalizado =?, " +
                "iexexon5ta =?, " +
                "iexnroruc_cas =?, " +
                "iexmadreresp =?, " +
                "iextipocentoedu =?, " +
                "iexflgcomi_mix = ?, " +
                "iexflgmas_vida = ?, " +
                "iexflgjubil = ?, " +
                "iexfecmodseg = CURRENT_TIMESTAMP, " +
                "iexusumodseg =? " +
                "where iexcodcia=? and " +
                "iexcodtra=? ";

        jdbc.update(sql,
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
                empleado.getIexcodtra()
        );
    }

    public void actualizarDireccion(Empleado empleado) {

        String sql = "update iexempleado set " +
                "iextipvia_dom1=?, iexnomvia_dom1 =?, iexnrovia_dom1 =?, iexdeptin_dom1 =?, " +
                "iexinterior_dom1=?, iexmanzana_dom1 =?, iexlote_dom1 =?, iexkilometro_dom1 =?, " +
                "iexblock_dom1 =?, iexetapa_dom1 =?, iextipzona_dom1 =?, iexnomzona_dom1 =?, " +
                "iexreferencia_dom1 =?, iextipvia_dom2 =?, iexnomvia_dom2 =?, " +
                "iexnrovia_dom2 =?, iexdeptin_dom2 =?, iexinterior_dom2 =?, iexmanzana_dom2 =?, " +
                "iexlote_dom2 =?, iexkilometro_dom2 =?, iexblock_dom2 =?, iexetapa_dom2 =?, " +
                "iextipzona_dom2 =?, iexnomzona_dom2 =?, iexreferencia_dom2 =?, " +
                "iexflgdomicilio =?, iexfecmoddom=CURRENT_TIMESTAMP, iexusumoddom=?, " +
                " iexnacion_origen1 =?, " +
                " iexdepart_origen1 =?, " +
                " iexprovin_origen1 =?, " +
                " iexubigeo_dom1 =?, " +
                " iexnacion_origen2 =?, " +
                " iexdepart_origen2 =?, " +
                " iexprovin_origen2 =?, " +
                " iexubigeo_dom2 =? " +
                " where iexcodcia=? and " +
                "iexcodtra=? ";

        jdbc.update(sql,
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
                empleado.getIextipvia_dom2(),
                empleado.getIexnomvia_dom2(),
                empleado.getIexnrovia_dom2(),
                empleado.getIexdeptin_dom2(),
                empleado.getIexinterior_dom2(),
                empleado.getIexmanzana_dom2(),
                empleado.getIexlote_dom2(),
                empleado.getIexkilometro_dom2(),
                empleado.getIexblock_dom2(),
                empleado.getIexetapa_dom2(),
                empleado.getIextipzona_dom2(),
                empleado.getIexnomzona_dom2(),
                empleado.getIexreferencia_dom2(),
                empleado.getIexflgdomicilio(),
                empleado.getIexusumod(),
                empleado.getIexnacion_origen1(),
                empleado.getIexdepart_origen1(),
                empleado.getIexprovin_origen1(),
                empleado.getIexdistri_origen1(),
                empleado.getIexnacion_origen2(),
                empleado.getIexdepart_origen2(),
                empleado.getIexprovin_origen2(),
                empleado.getIexdistri_origen2(),
                empleado.getIexcodcia(),
                empleado.getIexcodtra()
        );
    }

    public List<Empleado> validarCabecera(Empleado empleado) {

        String sql = "select " +
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
                "from iexempleado " +
                "where iexcodcia = :codcia and " +
                "iextipdocid = :iextipdocid and " +
                "iexnrodoc = :iexnrodoc and " +
                "iexflgest = '1' ";

        SqlParameterSource namedPArameters = new MapSqlParameterSource()
                .addValue("codcia", empleado.getIexcodcia())
                .addValue("iextipdocid", empleado.getIextipdocid())
                .addValue("iexnrodoc", empleado.getIexnrodoc());

        List<Empleado> lsEmpl = namedParameterJdbcTemplate.query(sql, namedPArameters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return lsEmpl;
    }

    public Integer obtieneIdEmpleado(Empleado empleado) {

        String sql = "select coalesce(max(iexcodtra),0)+1 idcont " +
                "from iexempleado " +
                "where iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", empleado.getIexcodcia());

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public void insertarCabecera(Empleado empleado) {

        String sql = "insert into iexempleado( " +
                "iexcodcia, iexcodtra, iexnomtra, iexapepat, iexapemat, " +
                "iextipdocid, iexnrodoc, iexfecnac, iexfecing, " +
                "iexcodsex, iexflgest, iexcodant, iextiptra, " +
                "iexfeccrea, iexusucrea) values " +
                " (?,?,?,?,?,?,?,to_date(?,'DD/MM/YYYY'), " +
                "to_date(?,'DD/MM/YYYY'),?,?,?,?, current_date,? ) ";

        jdbc.update(sql,
                empleado.getIexcodcia(),
                empleado.getIexcodtra(),
                empleado.getIexnomtra(),
                empleado.getIexapepat(),
                empleado.getIexapemat(),
                empleado.getIextipdocid(),
                empleado.getIexnrodoc(),
                empleado.getIexfecnac(),
                empleado.getIexfecing(),
                empleado.getIexcodsex(),
                1,
                empleado.getIexcodant(),
                empleado.getIextiptra(),
                empleado.getIexusucrea()
        );
    }

    public void actualizarFoto(Empleado empleado) {

        String sql = "update iexempleado set iexlogo=? " +
                "where iexcodcia=? and " +
                "iexcodtra=? ";

        jdbc.update(sql,
                empleado.getIexlogo(),
                empleado.getIexcodcia(),
                empleado.getIexcodtra()
        );
    }

    public Empleado recuperarTurnos(Integer ciaid, Integer codtra) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
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
                "iexturdom, " +
                "iexctlasipag " +
                "from iexempleado " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", ciaid)
                .addValue("codtra", codtra);

        Empleado empl = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return empl;
    }

    public void actualizarTurnos(Empleado empleado) {

        String sql = "update iexempleado set " +
                "iextipturno=?, " +
                "iexlunes=?, " +
                "iexmartes=?, " +
                "iexmiercoles =?, " +
                "iexjueves =?, " +
                "iexviernes =?, " +
                "iexsabado =?, " +
                "iexdomingo =?, " +
                "iexturlun =?, " +
                "iexturmar =?, " +
                "iexturmie =?, " +
                "iexturjue =?, " +
                "iexturvie =?, " +
                "iextursab =?, " +
                "iexturdom =?, " +
                "iexctlasipag = ? " +
                "where iexcodcia=? and " +
                "iexcodtra=? ";

        jdbc.update(sql,
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
                empleado.getIexcodcia(),
                empleado.getIexcodtra()
        );
    }

    public List<Empleado> listarEmpleadoInactivos(Integer codcia) {

        String sql = "select " +
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
                "from iexempleado " +
                "where iexcodcia = :codcia and " +
                "iexflgest='0' and " +
                "iexfecret is not null " +
                "order by iexapepat, iexapemat, iexnomtra, iexfecret asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<Empleado> lsEmpl = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return lsEmpl;
    }

    public void reingresarEmpleado(Integer ciaid, Integer codtra, String fechaing, String desusu, Integer codnew) {

        String sql = "call pl_reingresa_trabajador(?,?,?,?,?) ";

        jdbc.update(sql,
                ciaid,
                codtra,
                fechaing,
                desusu,
                codnew
        );
    }

    public List<Empleado> listarEmpleadoByCodTrab(Empleado empleado) {

        String sql = "select " +
                "e.iexcodcia, " +
                "e.iexcodtra, " +
                "e.iexnomtra, " +
                "e.iexapepat, " +
                "e.iexapemat, " +
                "e.iextipdocid as iextipdocid, " +
                "d.desdet destipdoc, " +
                "e.iexnrodoc, " +
                "to_char(e.iexfecnac,'dd/mm/yyyy') iexfecnac, " +
                "to_char(e.iexfecing,'dd/mm/yyyy') iexfecing, " +
                "to_char(e.iexfecret,'dd/mm/yyyy') iexfecret, " +
                "e.iextipcese, " +
                "'' as destipcese, " +
                "e.iexcodsex, " +
                "d2.desdet dessex, " +
                "e.iexpaisemisor, " +
                "'' as despaisemisor, " +
                "e.iexflgest, " +
                "d3.desdet desestado, " +
                "e.iexcodant, " +
                "e.iextiptra, " +
                "d4.desdet destiptra, " +
                "p1.iexdespuesto iexpuesto, " +
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
                "e.iexfeccrea, " +
                "e.iexfeccmod, " +
                "e.iexusucrea, " +
                "e.iexusumod " +
                "from iexempleado e " +
                "left join iexttabled d on d.iexcodtab = '3' and d.iexkey = e.iextipdocid " +
                "left join iexttabled d2 on d2.iexcodtab = '50' and d2.iexkey = e.iexcodsex " +
                "left join iexttabled d3 on d3.iexcodtab = '54' and d3.iexkey = e.iexflgest " +
                "left join iexpuesto p1 on p1.iexcodcia = e.iexcodcia and p1.iexpuesto = e.iexpuesto " +
                "left join iexttabled d4 on d4.iexcodtab = '8' and d4.iexkey = e.iextiptra " +
                "where e.iexcodcia = :codcia " +
                "and e.iexcodtra = :codtra ";

        if (empleado.getTxtfinder() != null) {
            sql = sql + " and '%'||iexnomtra||'%'||iexapepat||'%'||iexapemat||'%'||iexnrodoc||'%' " +
                    "like '%:txtfinder%' ";
        }

        if (empleado.getIextiptra() != null && !empleado.getIextiptra().equals("%")) {
            sql = sql + " and iextiptra like '%:tiptra%' ";
        }


        if (empleado.getIexflgest() != null && !empleado.getIexflgest().equals("%")) {
            sql = sql + " and iexflgest like '%:iexflgest%' ";
        }

        if (empleado.getFeciniing_par() != "" && empleado.getFecfining_par() != "") {
            if (empleado.getFeciniing_par() != null && empleado.getFecfining_par() != null) {
                sql = sql + " and iexfecing >= to_date(':feciniing_par','dd/mm/yyyy') and " +
                        "iexfecing <= to_date(':fecfining_par','dd/mm/yyyy') ";
            }
        }

        sql = sql + " order by iexapepat, iexapemat, iexnomtra asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", empleado.getIexcodcia())
                .addValue("codtra", empleado.getIexcodtra())
                .addValue("txtfinder", empleado.getTxtfinder())
                .addValue("tiptra", empleado.getIextiptra())
                .addValue("iexflgest", empleado.getIexflgest())
                .addValue("feciniing_par", empleado.getFeciniing_par());

        List<Empleado> lsEmpl = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return lsEmpl;
    }
}
