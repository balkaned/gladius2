package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.*;
import com.balkaned.gladius.dao.PlanillaDao;
import com.balkaned.gladius.services.FormulaPlanillaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Repository("PlanillaDao")
public class PlanillaDaoImpl implements PlanillaDao {

    private static final String CLASS_NAME = "PlanillaDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;
    private FormulaPlanillaService formulaPlanillaService;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<PlaProPeriodo> listPla5ta(Integer codcia, String anio, Integer codtra) {

        String sql = "select " +
                "k.iexcodcia, " +
                "k.iexcodpro, " +
                "k.despro as descodpro, " +
                "k.iexnroper, " +
                "k.iexcodtra, " +
                "k.progrppro, " +
                "k.iexfecing, " +
                "k.iextipdoc, " +
                "k.iexnrodoc, " +
                "k.iexfecini, " +
                "k.iexfecfin, " +
                "k.iexcorrel, " +
                "sum(k.T4000) AS totalingreso, " +
                "sum(k.G0019) AS impafecto5ta, " +
                "sum(k.D2070) AS desc5ta, " +
                "sum(k.T4020) AS totalneto, " +
                "sum(k.P0145) AS remafect5taotrcia_mes, " +
                "sum(k.P0146) AS rentafect5taotrcia_mes " +
                "from ( " +
                "   select " +
                "   n.iexcodcia, " +
                "   n.iexcodpro, " +
                "   p.prodespro despro, " +
                "   n.iexnroper, " +
                "   n.iexcodtra, " +
                "   p.progrppro, " +
                "   e.iexfecing, " +
                "   e.iextipdoc, " +
                "   e.iexnrodoc, " +
                "   e.iexfecini, " +
                "   e.iexfecfin, " +
                "   n.iexcorrel, " +
                "   case " +
                "   when procodcon ='T4000'  then provalor else 0 " +
                "   END T4000, " +
                "   case " +
                "   when procodcon ='G0019'  then provalor else 0 " +
                "   END G0019, " +
                "   case " +
                "   when procodcon ='D2070'  then provalor else 0 " +
                "   END D2070, " +
                "   case " +
                "   when procodcon ='T4020'  then provalor else 0 " +
                "   END T4020, " +
                "   case " +
                "   when procodcon ='P0145'  then provalor else 0 " +
                "   END P0145, " +
                "   case " +
                "   when procodcon ='P0146' then provalor else 0 " +
                "   END P0146 " +
                "from iexpropertra_nomina n, " +
                "iexprocesos p, " +
                "iexpropertra e " +
                "where n.iexcodcia = e.iexcodcia and " +
                "n.iexcodpro = e.iexcodpro and " +
                "n.iexnroper = e.iexnroper and " +
                "n.iexcodtra = e.iexcodtra and " +
                "n.iexcorrel = e.iexcorrel and " +
                "n.iexcodcia = :codcia and " +
                "n.iexcodpro = p.procodpro and " +
                "substring(n.iexnroper,1,4) = ':anio' and " +
                "n.iexcodtra = :codtra and " +
                "n.procodcon in ('T4000','G0019','D2070','T4020','P0145','P0146') and " +
                "p.procodregimenlab = '01' and progrppro in ('PLA','LIQ','GRA') " +
                ") k " +
                "group by " +
                "k.iexcodcia, " +
                "k.iexcodpro, " +
                "k.despro, " +
                "k.iexnroper, " +
                "k.iexcodtra, " +
                "k.progrppro, " +
                "k.iexfecing, " +
                "k.iextipdoc, " +
                "k.iexnrodoc, " +
                "k.iexfecini, " +
                "k.iexfecfin, " +
                "k.iexcorrel " +
                "order by k.iexnroper, k.iexcodpro asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("anio", anio)
                .addValue("codtra", codtra);

        List<PlaProPeriodo> lsPlaPro = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(PlaProPeriodo.class));

        return lsPlaPro;
    }

    public List<ConceptoxProcesoxTra> listPlaProperDetCon(Integer codcia, Integer idproceso, String perpro,
                                                          String codcon) {

        String sql = "select " +
                "j.iexcodcia, " +
                "j.iexcodpro as procodpro, " +
                "c.coodescon, " +
                "j.provalor " +
                "from iexpropertra_nomina j, iexconcepto c, iexempleado e " +
                "where j.procodcon = c.coocodcon and " +
                "j.iexcodcia = e.iexcodcia and " +
                "j.iexcodtra = e.iexcodtra and " +
                "j.iexcodcia = :codcia and " +
                "iexcodpro = :idproceso and " +
                "iexnroper = ':perpro' and " +
                "j.procodcon= ':codcon'  and j.provalor <>0 " +
                "order by e.iexapepat||' '||e.iexapemat||' '||e.iexnomtra, j.procodcon asc ";

        SqlParameterSource namedParameter = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idproceso", idproceso)
                .addValue("perpro", perpro)
                .addValue("codcon", codcon);

        List<ConceptoxProcesoxTra> lsConcxPro = namedParameterJdbcTemplate.query(sql, namedParameter,
                BeanPropertyRowMapper.newInstance(ConceptoxProcesoxTra.class));

        return lsConcxPro;
    }

    public List<PlaProPeriodo> listAllPlaPerTra(Integer codcia, Integer codtra, String perini, String perfin) {

        String sql = "select " +
                "k.iexcodcia, " +
                "k.iexcodpro, " +
                "k.despro, " +
                "k.iexnroper, " +
                "k.iexcodtra, " +
                "k.progrppro, " +
                "k.iexfecing, " +
                "k.iextipdoc, " +
                "k.iexnrodoc, " +
                "k.iexfecini, " +
                "k.iexfecfin, " +
                "k.iexcorrel, " +
                "sum(k.T4000) AS totalingreso, " +
                "sum(k.T4010) AS totaldescuento, " +
                "sum(k.D2070) AS desc5ta, " +
                "sum(k.T4020) AS totalneto, " +
                "sum(k.T4030) AS totalaporte " +
                "from ( " +
                "select " +
                "n.iexcodcia, " +
                "n.iexcodpro, " +
                "p.prodespro descodpro, " +
                "n.iexnroper, " +
                "n.iexcodtra, " +
                "p.progrppro, " +
                "e.iexfecing, " +
                "e.iextipdoc, " +
                "e.iexnrodoc, " +
                "e.iexfecini, " +
                "e.iexfecfin, " +
                "n.iexcorrel, " +
                "case " +
                "when procodcon ='T4000' then provalor else 0 " +
                "END T4000, " +
                "case " +
                "when procodcon ='T4010' then provalor else 0 " +
                "END T4010, " +
                "case " +
                "when procodcon ='D2070' then provalor else 0 " +
                "END D2070, " +
                "case " +
                "when procodcon ='T4020' then provalor else 0 " +
                "END T4020, " +
                "case " +
                "when procodcon ='T4030' then provalor else 0 " +
                "END T4030 " +
                "from iexpropertra_nomina n, iexprocesos p, iexpropertra e " +
                "where n.iexcodcia = e.iexcodcia and " +
                "n.iexcodpro = e.iexcodpro and " +
                "n.iexnroper = e.iexnroper and " +
                "n.iexcodtra = e.iexcodtra and " +
                "n.iexcorrel = e.iexcorrel and " +
                "n.iexcodcia = :codcia and " +
                "n.iexcodpro = p.procodpro and " +
                "n.iexcodtra = :codtra and " +
                "n.iexnroper >= ':perini' and " +
                "n.iexnroper <= ':perfin' and " +
                "n.procodcon in ('T4000','T4010','D2070','T4020','T4030') " +
                " ) k " +
                "group by " +
                "k.iexcodcia, " +
                "k.iexcodpro, " +
                "k.despro, " +
                "k.iexnroper, " +
                "k.iexcodtra, " +
                "k.progrppro, " +
                "k.iexfecing, " +
                "k.iextipdoc, " +
                "k.iexnrodoc, " +
                "k.iexfecini, " +
                "k.iexfecfin," +
                "k.iexcorrel order by k.iexnroper, k.iexcodpro asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra)
                .addValue("perini", perini)
                .addValue("perfin", perfin);

        List<PlaProPeriodo> lsPlaPro = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(PlaProPeriodo.class));

        return lsPlaPro;
    }

    public List<PlaProPeriodo> listAllPlaPerTraPro(Integer codcia, Integer codtra, Integer codpro,
                                                   String perini, String perfin) {

        String sql = "select " +
                "k.iexcodcia, " +
                "k.iexcodpro, " +
                "k.despro, " +
                "k.iexnroper, " +
                "k.iexcodtra, " +
                "k.progrppro, " +
                "k.iexfecing, " +
                "k.iextipdoc, " +
                "k.iexnrodoc, " +
                "k.iexfecini, " +
                "k.iexfecfin, " +
                "k.iexcorrel, " +
                "sum(k.T4000) AS totalingreso, " +
                "sum(k.T4010) AS totaldescuento, " +
                "sum(k.D2070) AS desc5ta, " +
                "sum(k.T4020) AS totalneto, " +
                "sum(k.T4030) AS totalaporte " +
                "from ( " +
                "select " +
                "n.iexcodcia, " +
                "n.iexcodpro, " +
                "p.prodespro descodpro, " +
                "n.iexnroper, " +
                "n.iexcodtra, " +
                "p.progrppro, " +
                "e.iexfecing, " +
                "e.iextipdoc, " +
                "e.iexnrodoc, " +
                "e.iexfecini, " +
                "e.iexfecfin, " +
                "n.iexcorrel, " +
                "case " +
                "when procodcon ='T4000' then provalor else 0 " +
                "END T4000, " +
                "case " +
                "when procodcon ='T4010' then provalor else 0 " +
                "END T4010, " +
                "case " +
                "when procodcon ='D2070' then provalor else 0 " +
                "END D2070, " +
                "case " +
                "when procodcon ='T4020' then provalor else 0 " +
                "END T4020, " +
                "case " +
                "when procodcon ='T4030' then provalor else 0 " +
                "END T4030 " +
                "from iexpropertra_nomina n, iexprocesos p, iexpropertra e " +
                "where n.iexcodcia = e.iexcodcia and " +
                "n.iexcodpro = e.iexcodpro and	" +
                "n.iexnroper = e.iexnroper and " +
                "n.iexcodtra = e.iexcodtra and " +
                "n.iexcorrel = e.iexcorrel and " +
                "n.iexcodcia = :codcia and " +
                "n.iexcodpro = :codpro and " +
                "n.iexcodpro = p.procodpro and " +
                "n.iexcodtra = :codtra and " +
                "n.iexnroper >= ':perini' and " +
                "n.iexnroper <= ':perfin' and " +
                "n.procodcon in ('T4000','T4010','D2070','T4020','T4030')) k " +
                "group by " +
                "k.iexcodcia, " +
                "k.iexcodpro, " +
                "k.despro, " +
                "k.iexnroper, " +
                "k.iexcodtra, " +
                "k.progrppro, " +
                "k.iexfecing, " +
                "k.iextipdoc, " +
                "k.iexnrodoc, " +
                "k.iexfecini, " +
                "k.iexfecfin, " +
                "k.iexcorrel order by k.iexnroper, k.iexcodpro asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra)
                .addValue("codpro", codpro)
                .addValue("perini", perini)
                .addValue("perfin", perfin);

        List<PlaProPeriodo> lsPlaPro = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(PlaProPeriodo.class));

        return lsPlaPro;

    }

    public void PlameExe(Integer codcia, String permes, String file) {

        String sql = "call prc_plame_sunat(?,?,? ) ";

        jdbc.update(sql,
                codcia,
                file,
                permes
        );

        log.info("Base PLameExe ");
    }

    public List<String> PlameMes(Integer codcia, String permes, String file) {

        String sql = "select iexdesfile " +
                "from iexsunatfile " +
                "where iexcodcia = :codcia and " +
                "iexcodfile = ':file' and " +
                "iexpermes = ':permes' ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("file", file)
                .addValue("permes", permes);

        List<String> lsPlameMes = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(String.class));

        return lsPlameMes;
    }


    public void AfpNetExe(Integer codcia, String permes) {

        String sql = "call prc_afpnet_permes(?,?) ";

        jdbc.update(sql,
                codcia,
                permes
        );
    }

    public List<PlaProPeriodo> listPlaProper(Integer codcia, Integer idproceso, String perpro, Integer codtra,
                                             Integer correl, String txt) {

        String sql = "select p.iexcodcia, p.iexcodpro, p.iexnroper, p.iexpermes, " +
                "p.iexcorrel, p.iexcodtra, e.iexapepat||' '||e.iexapemat||' '||e.iexnomtra as destra, " +
                "p.iextipdoc, p.iexnrodoc, p.iexcodpuesto, " +
                "p.iexcodarea, p.iexcodlocal, p.iexcodccosto, p.iexfecini, " +
                "p.iexfecfin, p.iexdiamestot, p.iexdiasteorico, p.iexdiavaca, p.iexdiadm, " +
                "p.iexdiasub, p.iexdialic, p.iexdiaperm, p.iexdiafalta, " +
                "p.iexdiaefectivo, p.iexdiaspago, p.totalingreso, p.totaldescuento, " +
                "p.totalneto, p.totalaporte, p.iexusucrea, p.iexfeccrea, " +
                "p.iexcodafp, p.iextipafp, to_char(p.iexfecing,'DD/MM/YYYY') iexfecing, " +
                "TO_CHAR(p.iexfeccese,'DD/MM/YYYY') iexfeccese, " +
                "p.iextipcese, p.iexobscese, p.iexanio_benef, p.iexmes_benef, " +
                "p.iexdia_benef, p.iexinivaca, p.iexfinvaca, p.usumod, p.fecmod, p.flgboltrunc, p.iexdominical " +
                "from iexpropertra p, iexempleado e where " +
                "p.iexcodcia = e.iexcodcia and " +
                "p.iexcodtra = e.iexcodtra and p.iexcodcia = :codcia and p.iexcodpro = :idproceso and " +
                "p.iexnroper = ':perpro' and iexcorrel = :correl ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idproceso", idproceso)
                .addValue("perpro", perpro)
                .addValue("correl", correl);

        List<PlaProPeriodo> lsPlaPro = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(PlaProPeriodo.class));

        return lsPlaPro;
    }

    public void iniPlaProper(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl,
                             String grppla, String usu) {

        String sql = "call pl_ini_exe(?,?,?,?,?,?,?) ";

        jdbc.update(sql,
                codcia,
                idproceso,
                perpro,
                codtra,
                correl,
                grppla,
                usu
        );
    }

    public void calificacion_tiempo_mas(Integer codcia, Integer idproceso, String idPeriodo, Integer codtra,
                                        Integer correl) {

        String sql = "call pl_exe_cons_tiempos(?,?,?,?,?,'') ";

        jdbc.update(sql,
                codcia,
                idproceso,
                idPeriodo,
                codtra,
                correl
        );
    }

    public void iniPlaProper_proc(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl,
                                  String grppla, String usu) {

        String sql = "call pl_ini_exe_for_proc(?,?,?,?,?,?,?) ";

        jdbc.update(sql,
                codcia,
                idproceso,
                perpro,
                codtra,
                correl,
                grppla,
                usu
        );
    }

    public void timeIniexe(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl) {

        String sql = "update iexproperiodo " +
                "set timeini_proc = now() " +
                "where iexcodcia = ? and " +
                "iexcodpro = ? and " +
                "iexnroper= '?' ";

        jdbc.update(sql,
                codcia,
                idproceso,
                perpro
        );
    }

    public void procesarPla2020(List<PlaProPeriodo> Persona, Integer codcia, Integer idproceso, String idPeriodo,
                                Integer codtra, Integer correl, Integer thread) {

        Integer v_salto = 0;
        Iterator<ProPeriodoDet> L_data = null;
        ProPeriodoDet data = null;

        // Carga las fórmulas desde base de datos para el proceso en curso
        List<FormulaPlanilla> lstFormula = formulaPlanillaService.listar(String.valueOf(idproceso));

        String v_variables_concat = null; // En esta variable se alojarán las variables concatenadas con los valores

        // Inicializa la iteración de la lista de personas.
        Iterator<PlaProPeriodo> pi;
        PlaProPeriodo pi_persona = null;

        Iterator<FormulaPlanilla> l_formula;
        FormulaPlanilla for_det;
        Double v_resultadoFinal = null;

        List<String> l_variables_glob;
        String v_variables_glob_concat = "";

        Iterator<ConceptoxAgrup> i_conagrup_det;
        Double v_resultado_glob_Final = null;
        ConceptoxAgrup conagrup_glb = null;
        String v_conagrup_det_final;
        Iterator<String> i_conagrup_det_final;
        Iterator<String> i_variables_glob;

        String result = null;
        String sql = "";

        String sql_var_general = "";
        String sql_var_global = "";
        String conagrup_glob = "";

        List<ConceptoXProceso> LoadGrpcon;
        Iterator<ConceptoXProceso> i_grpcon;
        ConceptoXProceso congrp = null;

        List<ProPeriodoDet> LoadData2 = new ArrayList<ProPeriodoDet>();
        Iterator<ProPeriodoDet> i_Data2;
        ProPeriodoDet p_Data2 = null;

        // Recorre todos los trabajadores para colocarle el hilo al que pertenece
        Integer thread_id = thread;
        pi = Persona.iterator();

        while (pi.hasNext()) {
            pi_persona = pi.next();

            try {
                update_iexpropertra_proc(thread_id, codcia, idproceso, idPeriodo, pi_persona.getIexcodtra(), correl);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }

        l_formula = lstFormula.iterator();  // Se delcara la iteración de la lista formula por cada trabajador

        while (l_formula.hasNext()) { // Iteración de fórmula por cada trabajador. Por cada trabajador se va a recorrer la lista de formula.

            for_det = l_formula.next();
            // Se asigna valor de la lista al objeto Fórmula.
            log.info("--Formula :" + for_det.getIdFormula());  // Se verifica el identificador de la formula

            if (for_det.getTipOut().equals("1") || for_det.getTipOut().equals("3")) {
                l_variables_glob = null; // Selecciona los conceptos que son grupo de conceptos resultantes
                v_variables_glob_concat = "";
                v_resultado_glob_Final = 0.0; // Sumarizar los valores en el concepto grupo resultante

                // Guardar los valores del  resultado en una nueva concatenacion de variables
                sql_var_general = formulaPlanillaService.getListVars(idproceso, for_det.getDesVar());
                LoadGrpcon = formulaPlanillaService.obtenerListVariables_glb(idproceso, for_det.getDesVar());

                i_grpcon = LoadGrpcon.iterator();

                while (i_grpcon.hasNext()) {
                    congrp = i_grpcon.next();

                    try {
                        // Actualizar la suma por cada trabajador de los conceptos globales
                        update_prc_proceso_upd_grupocon_v3(codcia, codtra, idproceso, idPeriodo, correl, congrp.getConvar(), congrp.getProcodcon(), thread_id);
                    } catch (Exception e) {
                        log.info(e.getMessage());
                    }
                }

                // Inicia la iteración por persona
                LoadData2 = getMetanominaDatav3(codcia, idproceso, idPeriodo, codtra, sql_var_general, correl, thread_id);

                pi = Persona.iterator();
                while (pi.hasNext()) {
                    pi_persona = pi.next();

                    v_variables_concat = "";
                    i_Data2 = LoadData2.iterator();

                    while (i_Data2.hasNext()) {
                        p_Data2 = i_Data2.next();
                        if (p_Data2.getIexcodtra().equals(pi_persona.getIexcodtra())) {
                            v_variables_concat = " " + v_variables_concat + " " + p_Data2.getVarcon() + "=" + p_Data2.getValue() + "; ";
                        }
                    }

                    v_resultadoFinal = formulaPlanillaService.realEjecucion(for_det.getDesVar(), v_variables_concat, for_det.getDesFormula());

                    guardarMetaTrav2(codcia, idproceso, pi_persona.getIexcodtra(), idPeriodo, for_det.getIdConcepto(), v_resultadoFinal, correl);
                }
            } else if (for_det.getTipOut().equals("2")) {

                log.info("Ejecución de Procedure");

                if (for_det.getGrpeje().equals("1")) {   // Ejecución del procedure por trabajador

                    log.info("Ejecución de Procedure x trabajador :" + for_det.getSqlprogram());

                    // Grabar la data en la metanómina  /// crear método
                    // guardarInformacionMeta(LoadData2,codcia, idproceso, idPeriodo);
                    // Iterar por trabajador. Ejecutar el procedure de planillas

                    pi = Persona.iterator();

                    while (pi.hasNext()) { // Iteración por cada trabajador
                        pi_persona = pi.next();

                        try {
                            update_slq_program(for_det.getSqlprogram(), codcia, pi_persona.getIexcodtra(), idproceso, idPeriodo);
                        } catch (Exception e) {
                            log.info(e.getMessage());
                        }
                    }

                    log.info("Se ejecuto el procedure 1");
                    // Traer la data de la base de datos
                    // Traer la data de la metanómina a memoria
                    // LoadData2=getProPeriodoDet(codcia, idproceso, idPeriodo,1);
                    // Colocar la data en el DataLoad.

                } else if (for_det.getGrpeje().equals("2")) {  // ejecucion del procedure de modo masivo

                    log.info("Ejecucion de Procedure x masivo :" + for_det.getSqlprogram());
                    // Grabar la data en la metanomina
                    // guardarInformacionMeta(LoadData2,codcia, idproceso, idPeriodo);
                    // Ejecutar el procedure de planillas modo masivo.
                    log.info("Se ejecuto el procedure 2");

                    try {
                        update_slq_program_masivo(for_det.getSqlprogram(), codcia, 1, idproceso, idPeriodo);
                    } catch (Exception e) {
                        log.info(e.getMessage());
                    }

                    log.info("Finalizo procedure 2");

                    // Traer la data de la base de datos
                    // LoadData2=getProPeriodoDet(codcia, idproceso, idPeriodo,1);
                    // Traer la data de la metanomina a memoria
                    // Colocar la data en el DataLoad.
                }
            }
        } // While de la fórmula

        // Verificar la lista de
        // Actualiza estado del proceso
    }

    public void update_iexpropertra_proc(Integer thread_id, Integer codcia, Integer idproceso, String idPeriodo,
                                         Integer codtra, Integer correl) {

        String sql = "UPDATE iexpropertra_proc " +
                "set thread = ? where iexcodcia = ? and iexcodpro = ? and " +
                "iexnroper = ? and iexcodtra = ? and iexcorrel = ? ";

        jdbc.update(sql,
                thread_id,
                codcia,
                idproceso,
                idPeriodo,
                codtra,
                correl
        );
    }

    public void update_prc_proceso_upd_grupocon_v3(Integer codcia, Integer codtra, Integer idproceso,
                                                   String idPeriodo, Integer correl, String convar,
                                                   String procodcon, Integer thread_id) {

        String sql = "call prc_proceso_upd_grupocon_v3(?,?,?,?,?,?,?,?) ";

        jdbc.update(sql,
                codcia,
                codtra,
                idproceso,
                idPeriodo,
                correl,
                convar,
                procodcon,
                thread_id
        );
    }

    public List<ProPeriodoDet> getMetanominaDatav3(Integer codcia, Integer idproceso, String perpro, Integer codtra,
                                                   String sqlcomand, Integer correl, Integer thread) {

        String var_concat = "";

        String sql = "";

        if (codtra == -1) {
            sql = "select " +
                    "p.iexcodcia, p.iexcodpro, p.iexnroper, p.iexcodtra, p.procodcon as codcon, " +
                    "p.coocodforvar as varcon, p.provalor as value " +
                    "from iexpropertra_nomina_proc p, iexpropertra_proc a " +
                    "where p.iexcodcia = a.iexcodcia and " +
                    "p.iexcodpro = a.iexcodpro and " +
                    "p.iexnroper = a.iexnroper and " +
                    "p.iexcodtra = a.iexcodtra and " +
                    "p.iexcorrel = a.iexcorrel and " +
                    "p.iexcodcia = :codcia and p.iexcodpro = :idproceso and " +
                    "p.iexnroper = ':perpro' and p.coocodforvar in :sqlcomand and " +
                    "p.iexcorrel = :correl and a.thread = :thread order by 4,5 asc ";
        } else {
            sql = " select " +
                    "p.iexcodcia, p.iexcodpro, p.iexnroper, p.iexcodtra, p.procodcon as codcon, " +
                    "p.coocodforvar as varcon, p.provalor as value" +
                    "from iexpropertra_nomina_proc p, iexpropertra_proc a " +
                    "where p.iexcodcia = a.iexcodcia and " +
                    "p.iexcodpro = a.iexcodpro and " +
                    "p.iexnroper = a.iexnroper and " +
                    "p.iexcodtra = a.iexcodtra and " +
                    "p.iexcorrel = a.iexcorrel and " +
                    "p.iexcodcia = :codcia and " +
                    "p.iexcodpro = :idproceso and " +
                    "p.iexnroper = ':perpro' and " +
                    "p.iexcodtra = :codtra and " +
                    "p.coocodforvar in :sqlcomand and " +
                    "p.iexcorrel = :correl and " +
                    "a.thread = :thread order by 4,5 asc ";
        }

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idproceso", idproceso)
                .addValue("perpro", perpro)
                .addValue("codtra", codtra)
                .addValue("sqlcomand", sqlcomand)
                .addValue("correl", correl);

        List<ProPeriodoDet> lsProPer = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ProPeriodoDet.class));

        return lsProPer;
    }

    public void guardarMetaTrav2(Integer codcia, Integer idproceso, Integer idcodtra, String idPeriodo,
                                 String codcon, Double valor, Integer correl) {

        String sql = "update iexpropertra_nomina_proc " +
                "set provalor = ? " +
                "where iexcodcia = ? and " +
                "iexcodpro = ? and " +
                "iexnroper = '?' and " +
                "iexcodtra = ? and " +
                "trim(procodcon) = trim('?') and " +
                "iexcorrel = ? ";

        jdbc.update(sql,
                valor,
                codcia,
                idproceso,
                idPeriodo,
                idcodtra,
                codcon,
                correl
        );
    }

    public void update_slq_program(String sql_program, Integer codcia, Integer codtra, Integer idproceso, String idPeriodo) {

        String sql = "call (?,?,?,?) ";

        jdbc.update(sql,
                sql_program.trim(),
                codcia,
                codtra,
                idproceso,
                idPeriodo
        );
    }

    public void update_slq_program_masivo(String sql_program, Integer codcia, Integer codtra, Integer idproceso,
                                          String idPeriodo) {

        String sql = "call (?,?,?,?) ";

        jdbc.update(sql,
                sql_program.trim(),
                codcia,
                codtra,
                idproceso,
                idPeriodo
        );
    }

    public void guardarNomina2020(Integer codcia, Integer idproceso, String idPeriodo, Integer codtra, Integer correl) {

        pl_pla_upgrade(codcia, idproceso, idPeriodo, codtra, correl);
        update_iexproperiodo(codcia, idproceso, idPeriodo);

    }

    public void pl_pla_upgrade(Integer codcia, Integer idproceso, String idPeriodo, Integer codtra, Integer correl) {

        String sql = "call pl_pla_upgrade(?,?,?,?,?) ";

        jdbc.update(sql,
                codcia,
                idproceso,
                idPeriodo,
                codtra,
                correl
        );
    }

    public void update_iexproperiodo(Integer codcia, Integer idproceso, String idPeriodo) {
        String sql = "update iexproperiodo set flgestado='2' " +
                "where iexcodcia=? and iexcodpro=? and iexnroper=? ";

        jdbc.update(sql,
                codcia,
                idproceso,
                idPeriodo
        );
    }

    public void timeFinexe(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl) {

        String sql = "update iexproperiodo " +
                "set timefin_proc = now(), " +
                "timenroimp_proc = round(EXTRACT (SECOND FROM ( now() - timeini_proc ))) " +
                "where iexcodcia = :codcia and " +
                "iexcodpro = :idproceso and " +
                "iexnroper = ':perpro' ";

        jdbc.update(sql,
                codcia,
                idproceso,
                perpro
        );
    }

    public void delPlaProper(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String grppla, String usu) {

        String sql = "call pl_borrar_exe(?,?,?,?,?,?,?) ";

        jdbc.update(sql,
                codcia,
                idproceso,
                perpro,
                codtra,
                correl,
                grppla,
                usu
        );
    }

    public PlaProPeriodo listPlaProperTra(Integer codcia, Integer idproceso, String perpro,
                                          Integer codtra, Integer correl) {

        String sql = "select p.iexcodcia, p.iexcodpro, p.iexnroper, p.iexpermes, p.iexcorrel, " +
                "p.iexcodtra, e.iexapepat||' '||e.iexapemat||' '||e.iexnomtra as destra, " +
                "p.iextipdoc, p.iexnrodoc, p.iexcodpuesto, " +
                "p.iexcodarea, p.iexcodlocal, p.iexcodccosto, p.iexfecini, " +
                "p.iexfecfin, p.iexdiamestot, p.iexdiasteorico, p.iexdiavaca, p.iexdiadm, " +
                "p.iexdiasub, p.iexdialic, p.iexdiaperm, p.iexdiafalta, " +
                "p.iexdiaefectivo, p.iexdiaspago, p.totalingreso, p.totaldescuento, " +
                "p.totalneto, p.totalaporte, p.iexusucrea, p.iexfeccrea, " +
                " p.iexcodafp, p.iextipafp, to_char(p.iexfecing,'DD/MM/YYYY') iexfecing, " +
                "TO_CHAR(p.iexfeccese,'DD/MM/YYYY') iexfeccese, " +
                "p.iextipcese, p.iexobscese, p.iexanio_benef, p.iexmes_benef, " +
                "p.iexdia_benef, p.iexinivaca, p.iexfinvaca, p.usumod, p.fecmod, p.flgboltrunc " +
                "from iexpropertra p, iexempleado e where " +
                "p.iexcodcia = e.iexcodcia and " +
                "p.iexcodtra = e.iexcodtra and p.iexcodcia = :codcia and " +
                "p.iexcodpro = :idproceso and p.iexnroper = ':perpro' and " +
                "p.iexcodtra = :codtra and iexcorrel = :correl ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("perpro", perpro)
                .addValue("correl", correl);

        PlaProPeriodo plaProPeriodo = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(PlaProPeriodo.class));

        return plaProPeriodo;
    }

    public List<ConceptoxProcesoxTra> listProperconConZeros(Integer codcia, Integer idproceso, String perpro,
                                                            Integer codtra, Integer correl, String flgcon) {

        String sql = "select " +
                "j.iexcodcia, " +
                "j.iexcodpro, " +
                "j.iexnroper, " +
                "j.iexcodtra, " +
                "j.iexcorrel as correl, " +
                "j.protipcon, " +
                "j.procodcon, " +
                "c.coodescon, " +
                "j.provalor " +
                "from iexpropertra_nomina j, iexconcepto c " +
                "where j.procodcon = c.coocodcon and " +
                "iexcodcia = :codcia and " +
                "iexcodpro = :idproceso and " +
                "iexnroper = ':perpro' and " +
                "iexcodtra = :codtra and " +
                "iexcorrel = :correl and " +
                "j.protipcon = ':flgcon' order by j.procodcon asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idproceso", idproceso)
                .addValue("perpro", perpro)
                .addValue("codtra", codtra)
                .addValue("correl", correl)
                .addValue("flgcon", flgcon);

        List<ConceptoxProcesoxTra> lsConcept = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ConceptoxProcesoxTra.class));

        return lsConcept;
    }

    public List<ConceptoxProcesoxTra> listProperconSinZeros(Integer codcia, Integer idproceso, String perpro,
                                                            Integer codtra, Integer correl, String flgcon) {

        String sql = "select " +
                "j.iexcodcia, " +
                "j.iexcodpro as procodpro, " +
                "j.iexnroper, " +
                "j.iexcodtra, " +
                "j.iexcorrel as correl, " +
                "j.protipcon, " +
                "j.procodcon, " +
                "c.coodescon, " +
                "j.provalor " +
                "from iexpropertra_nomina j, iexconcepto c " +
                "where j.procodcon = c.coocodcon and " +
                "iexcodcia = :codcia and " +
                "iexcodpro = :idproceso and " +
                "iexnroper = ':perpro' and " +
                "iexcodtra = :codtra and " +
                "iexcorrel = :correl and " +
                "j.protipcon = ':flgcon' and provalor <> 0 ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idproceso", idproceso)
                .addValue("perpro", perpro)
                .addValue("codtra", codtra)
                .addValue("correl", correl)
                .addValue("flgcon", flgcon);

        List<ConceptoxProcesoxTra> lsConcept = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ConceptoxProcesoxTra.class));

        return lsConcept;
    }

    public List<BancoResumenPer> listBankProper(Integer codcia, Integer idproceso, String perpro, Integer correl) {

        String sql = "select " +
                "c.iexcodcia, c.iexcodpro, c.iexnroper, " +
                "c.iexcorrel, c.iexpermes as permes, c.codbank, " +
                "j.desban as desbank, c.moneda, " +
                "m.desmon as desmoneda, c.nroctabank_gen as nroctabank, " +
                "c.totalneto as impneto, c.heads as heads " +
                "from iexpropertra_resbank c " +
                "full outer join ( SELECT  iexkey codban, desdet desban  FROM IEXTTABLED WHERE IEXCODTAB='36') j " +
                "   on j.codban = c.codbank " +
                "full outer join ( SELECT  iexkey codmon, desdet desmon  FROM IEXTTABLED WHERE IEXCODTAB='52') m " +
                "   on m.codmon = c.moneda " +
                "where c.iexcodcia = :codcia and " +
                "c.iexcodpro = :idproceso and " +
                "c.iexnroper = ':perpro' and " +
                "c.iexcorrel = :correl ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idproceso", idproceso)
                .addValue("perpro", perpro)
                .addValue("correl", correl);

        List<BancoResumenPer> lsBan = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(BancoResumenPer.class));

        return lsBan;
    }

    public void exeBankProper(Integer codcia, Integer idproceso, String perpro, Integer correl, String usu, Double tmcb, String fecpago) {

        String sql = "call pl_reichbank_res(?,?,?,?,?,?,?) ";

        jdbc.update(sql,
                codcia,
                idproceso,
                perpro,
                correl,
                usu,
                tmcb,
                fecpago
        );
    }

    public List<String> txtBancos(Integer codcia, Integer idproceso, String nroper, Integer correl, String codbank, String codmon) {

        String sql = "select glosatxt " +
                "from iexpropertra_txtbank " +
                "where iexcodcia = :codcia and iexcodpro = :idproceso and " +
                "iexnroper = ':nroper' and iexcorrel = :correl and " +
                "codbank = ':codbank' and moneda = ':codmon' ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idproceso", idproceso)
                .addValue("nroper", nroper)
                .addValue("correl", correl)
                .addValue("codbank", codbank)
                .addValue("codmon", codmon);

        List<String> ls = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(String.class));

        return ls;
    }

    public List<PlaProPerDet> iniPlaProper_vac(Integer codcia, Integer idproceso, String perpro, Integer codtra,
                                               Integer correl, String grppla, String usu) {

        String sql = "select " +
                "e.iexcodtra, " +
                "e.iexapepat||' '||e.iexapemat||' '||e.iexnomtra destra, " +
                "tt.desdet as tiporegistro, " +
                "to_char(d.fecinivac,'dd/mm/yyyy') fecini, " +
                "to_char(d.fecfinvac,'dd/mm/yyyy') fecfin, " +
                "d.nrodias, " +
                "d.codcon " +
                "from iexpropertra_vac d, iexempleado e, " +
                " ( " +
                " select iexkey, desdet from iexttabled where iexcodtab='56' " +
                " ) tt, iexpuesto p " +
                "where d.iexcodcia = e.iexcodcia and " +
                "d.iexcodtra = e.iexcodtra and " +
                "d.tipvac = tt.iexkey and " +
                "e.iexcodcia = p.iexcodcia and " +
                "e.iexpuesto = p.iexpuesto and " +
                "d.iexcodcia = :codcia and d.iexcodpro = :idproceso and d.iexnroper = ':perpro' " +
                "order by " +
                "e.iexapepat, " +
                "e.iexapemat, " +
                "e.iexnomtra asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idproceso", idproceso)
                .addValue("perpro", perpro);

        List<PlaProPerDet> lsPla = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(PlaProPerDet.class));

        return lsPla;
    }

    public List<PlaProPerDet> iniPlaProper_aus(Integer codcia, Integer idproceso, String perpro,
                                               Integer codtra, Integer correl, String grppla, String usu) {

        String sql = "select " +
                "e.iexcodtra, " +
                "e.iexapepat||' '||e.iexapemat||' '||e.iexnomtra destra, " +
                "tt.desdet tiporegistro, " +
                "to_char(d.feciniaus,'dd/mm/yyyy') fecini, " +
                "to_char(d.fecfinaus,'dd/mm/yyyy') fecfin, " +
                "d.nrodias, " +
                "d.codcon " +
                "from iexpropertra_aus d, iexempleado e, " +
                " ( " +
                " select iexkey, desdet from iexttabled where iexcodtab='57' " +
                " ) tt, iexpuesto p " +
                "where d.iexcodcia = e.iexcodcia and " +
                "d.iexcodtra = e.iexcodtra and " +
                "d.tipaus = tt.iexkey and " +
                "e.iexcodcia = p.iexcodcia and " +
                "e.iexpuesto = p.iexpuesto and " +
                "d.iexcodcia = :codcia and d.iexcodpro = :idproceso and d.iexnroper = ':perpro' " +
                "order by " +
                "e.iexapepat, " +
                "e.iexapemat, " +
                "e.iexnomtra asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idproceso", idproceso)
                .addValue("perpro", perpro);

        List<PlaProPerDet> lsPlaPro = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(PlaProPerDet.class));

        return lsPlaPro;
    }

    public List<PlaProPerDet> iniPlaProper_prest(Integer codcia, Integer idproceso, String perpro, Integer codtra,
                                                 Integer correl, String grppla, String usu) {

        String sql = "select " +
                "e.iexcodtra, " +
                "e.iexapepat||' '||e.iexapemat||' '||e.iexnomtra destra, " +
                "tt.desdet tiporegistro, " +
                "d.idcuota, " +
                "to_char(d.feccuota,'dd/mm/yyyy') feccuota, " +
                "importe cuota , tt.des1det codcon " +
                "from iexpropertra_prestamo d, iexempleado e, iexpuesto p, " +
                " ( " +
                " select iexkey, desdet, des1det from iexttabled where iexcodtab='59' " +
                " ) tt " +
                "where d.iexcodcia = e.iexcodcia and " +
                "d.iexcodtra = e.iexcodtra and " +
                "e.iexcodcia = p.iexcodcia and " +
                "e.iexpuesto = p.iexpuesto and " +
                " .tippre = tt.iexkey and " +
                "d.iexcodcia = :codcia and d.iexcodpro = :idproceso and " +
                "d.iexnroper = ':perpro' " +
                "order by " +
                "e.iexapepat, " +
                "e.iexapemat, " +
                "e.iexnomtra asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idproceso", idproceso)
                .addValue("perpro", perpro);

        List<PlaProPerDet> lsPlaPro = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(PlaProPerDet.class));

        return lsPlaPro;
    }

    public List<PlaProPerDet> iniPlaProper_prom(Integer codcia, Integer idproceso, String perpro, Integer codtra,
                                                Integer correl, String grppla, String usu) {

        String sql = "select " +
                "e.iexcodtra, " +
                "e.iexapepat||' '||e.iexapepat||' '||e.iexnomtra as destra, " +
                "po.iexnroper periodo_proceso, " +
                "po.codconpar concepto_destino, " +
                "po.nroperpar periodo_anterior, " +
                "po.codcondet concepto_origen, " +
                "po.valcon valor " +
                "from iexpropertra_promedio po, " +
                "iexempleado e " +
                "where po.iexcodcia = e.iexcodcia and  " +
                "po.iexcodtra = e.iexcodtra and " +
                "po.iexcodcia = :codcia and po.iexcodpro = :idproceso and " +
                "po.iexnroper = ':perpro' and po.iexcorrel = :correl " +
                "order by " +
                "2,3,4,5,6 asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idproceso", idproceso)
                .addValue("perpro", perpro)
                .addValue("correl", correl);

        List<PlaProPerDet> lsPlaProPer = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(PlaProPerDet.class));

        return lsPlaProPer;
    }

    public void migraTrabajador(Integer cia, Integer codpro, String nroper, Integer codtra, Integer correl) {

        log.info("--- Migraciones  --- ");
        log.info("codcia : " + cia);
        log.info("proceso : " + codpro);
        log.info("perpro : " + nroper);
        log.info("codtra : " + codtra);
        log.info("correl : " + correl);

        String sql = "call pl_migra_planilla_tra(?,?,?,?,?) ";

        jdbc.update(sql,
                cia,
                codpro,
                nroper,
                codtra,
                correl
        );
    }

    public void migraInsertarPla(List<EmpDatvar> empdatvar) {

        for (EmpDatvar empdat : empdatvar) {

            String sql = "call pl_migra_datapla_tra(?,?,?,?,?,?,?,?) ";

            jdbc.update(sql,
                    empdat.getIexcodcia(),
                    empdat.getIexcodpro(),
                    empdat.getIexnroper(),
                    empdat.getIexcodtra(),
                    empdat.getIexcorrel(),
                    empdat.getIexcodcon(),
                    empdat.getIexvalcon(),
                    empdat.getIexusucrea()
            );
        }
    }

    public List<Asistencia> consultaMarka(Integer codcia, Integer codtra, String fecini, String fecfin) {

        String sql = "select " +
                "iexcodcia, iexcodtra, " +
                "to_char(iexfecha,'dd/mm/yyyy hh24:mi:ss') as iexcodfec, " +
                "iextipmarka as tipmarka, " +
                "to_char(iexfeccrea,'dd/mm/yyyy hh24:mi:ss') iesfeccrea, " +
                "iexusucrea " +
                "from iexasistencia where iexcodcia = :codcia and " +
                "iexcodtra = :codtra and iexfecha >= to_date(':fecini','dd/mm/yyyy') and " +
                "iexfecha <= to_date(':fecfin','dd/mm/yyyy') order by iexfecha asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra)
                .addValue("fecini", fecini)
                .addValue("fecfin", fecfin);

        List<Asistencia> lsAsis = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Asistencia.class));

        return lsAsis;
    }
}

