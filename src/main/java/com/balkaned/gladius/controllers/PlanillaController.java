package com.balkaned.gladius.controllers;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
public class PlanillaController {

    JdbcTemplate template;
    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    @Autowired
    ProcesoPlanillaService procesoPlanillaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    Sessionattributes sessionattributes;

    @Autowired
    PlanillaService planillaService;

    @Autowired
    SueldoService sueldoService;

    @Autowired
    CompaniaService companiaService;

    @RequestMapping("/listPlanillaGeneral")
    public ModelAndView listPlanillaGeneral(ModelMap model, HttpServletRequest request) {
        log.info("/listPlanillaGeneral");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/planillaGeneral");
    }

    @RequestMapping("/buscarPlanillaGen")
    public ModelAndView buscarPlanillaGen(ModelMap model, HttpServletRequest request) {
        log.info("/buscarPlanillaGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iexcodreg = request.getParameter("iexcodreg");
        String iexpermes = request.getParameter("iexpermes");

        model.addAttribute("iexcodreg", iexcodreg);
        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        model.addAttribute("List_Procesos", procesoPlanillaService.listarProRegpla(idCompania, iexcodreg, iexpermes));

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/planillaGeneral");
    }

    @RequestMapping("/insertarPeriodoPlan")
    public ModelAndView insertarPeriodoPlan(ModelMap model, HttpServletRequest request) {
        log.info("/insertarPeriodoPlan");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String proceso = request.getParameter("idproceso");
        Integer p_Iexcodpro = 0;

        if (request.getParameter("idproceso") == null) {
            p_Iexcodpro = 0;
        } else {
            p_Iexcodpro = Integer.parseInt(proceso);
        }

        String p_Iexanio = request.getParameter("idanio");
        String p_Iexpermes = request.getParameter("idpermes");
        String p_Iexnroper = request.getParameter("idperiodo");
        String p_Iexfecini = request.getParameter("fecini");
        String p_Iexfecfin = request.getParameter("fecfin");
        String p_Timerfecini = request.getParameter("fecinit");
        String p_Timerfecfin = request.getParameter("fecfint");
        String p_Iexfecpago = request.getParameter("fecpago");
        String p_Iexfeccerti = request.getParameter("feccerti");

        ProcesoPeriodo p = new ProcesoPeriodo();
        p.setIexcodcia(idCompania);
        p.setIexcodpro(p_Iexcodpro);
        p.setIexanio(p_Iexanio);
        p.setIexpermes(p_Iexpermes);
        p.setIexnroper(p_Iexnroper);
        p.setIexfecini(p_Iexfecini);
        p.setIexfecfin(p_Iexfecfin);
        p.setTimerfecini(p_Timerfecini);
        p.setTimerfecfin(p_Timerfecfin);
        p.setIexfecpago(p_Iexfecpago);
        p.setIexfeccerti(p_Iexfeccerti);

        procesoPlanillaService.insertarProper(p);

        return new ModelAndView("redirect:/listPlanillaGeneral");
    }

    @RequestMapping(value = "/getDatosPeriodo", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getDatosPeriodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/getDatosPeriodo");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer codproceso = Integer.valueOf(request.getParameter("codproceso"));
        String periodo = request.getParameter("periodo");

        ProcesoPeriodo proper = procesoPlanillaService.recuperarPeriodo2(idCompania, codproceso, periodo);

        String json = new Gson().toJson(proper);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping("/modificarPeriodoPlan")
    public ModelAndView modificarPeriodoPlan(ModelMap model, HttpServletRequest request) {
        log.info("/modificarPeriodoPlan");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String proceso = request.getParameter("idprocesoEdit");
        Integer p_Iexcodpro = 0;

        if (request.getParameter("idprocesoEdit") == null) {
            p_Iexcodpro = 0;
        } else {
            p_Iexcodpro = Integer.parseInt(proceso);
        }

        String p_Iexanio = request.getParameter("idanioEdit");
        String p_Iexpermes = request.getParameter("idpermesEdit");
        String p_Iexnroper = request.getParameter("idperiodoEdit");
        String p_Iexfecini = request.getParameter("feciniEdit");
        String p_Iexfecfin = request.getParameter("fecfinEdit");
        String p_Timerfecini = request.getParameter("fecinitEdit");
        String p_Timerfecfin = request.getParameter("fecfintEdit");
        String p_Iexfecpago = request.getParameter("fecpagoEdit");
        String p_Iexfeccerti = request.getParameter("feccertiEdit");

        ProcesoPeriodo p = new ProcesoPeriodo();
        p.setIexcodcia(idCompania);
        p.setIexcodpro(p_Iexcodpro);
        p.setIexanio(p_Iexanio);
        p.setIexpermes(p_Iexpermes);
        p.setIexnroper(p_Iexnroper);
        p.setIexfecini(p_Iexfecini);
        p.setIexfecfin(p_Iexfecfin);
        p.setTimerfecini(p_Timerfecini);
        p.setTimerfecfin(p_Timerfecfin);
        p.setIexfecpago(p_Iexfecpago);
        p.setIexfeccerti(p_Iexfeccerti);

        procesoPlanillaService.actualizarProper(p);

        return new ModelAndView("redirect:/listPlanillaGeneral");
    }

    @RequestMapping("/listarDetallePlanillaGen@{codreg}@{codproceso}@{periodo}")
    public ModelAndView listarDetallePlanillaGen(ModelMap model, HttpServletRequest request,
                                                 @PathVariable Integer codreg,
                                                 @PathVariable Integer codproceso,
                                                 @PathVariable String periodo) {
        log.info("/listarDetallePlanillaGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("iexcodreg", codreg);
        model.addAttribute("iexcodpro", codproceso);
        model.addAttribute("iexperiodo", periodo);
        model.addAttribute("idCom",idCompania);

        model.addAttribute("xproplaper", procesoPlanillaService.recuperarPeriodo2(idCompania, Integer.valueOf(codproceso), periodo));
        List<PlaProPeriodo> lista = planillaService.listPlaProper(idCompania, codproceso, periodo, -1, 1, "%");
        model.addAttribute("LstPlanillaRes", lista);

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/detallePlanillaGeneral");
    }

    @RequestMapping("/procesarPlanilla")
    public ModelAndView procesarPlanilla(ModelMap model, HttpServletRequest request) {
        log.info("/procesarPlanilla");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String accion = request.getParameter("accion");
        String iexcodreg = request.getParameter("iexcodreg");
        Integer iexcodpro = Integer.valueOf(request.getParameter("iexcodpro"));
        String iexperiodo = request.getParameter("iexperiodo");
        Integer iexcodtra = Integer.valueOf(request.getParameter("iexcodtra"));
        Integer iexcorrel = Integer.valueOf(request.getParameter("iexcorrel"));
        String grupopla = request.getParameter("grppla");

        if (accion.equals("INIPRO")) {
            planillaService.iniPlaProper(idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, grupopla, user);
        }

        if (accion.equals("CALFASIST")) {
            planillaService.calificacion_tiempo_mas(idCompania, iexcodpro, iexperiodo, -1, 1);
        }

        if (accion.equals("EXEPRO")) {
            planillaService.iniPlaProper_proc(idCompania, iexcodpro, iexperiodo, -1, 1, grupopla, user);

            // Obtiene la lista de trabajadores
            planillaService.timeIniexe(idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel);

            // Obtener la lista de conceptos  de todos las personas
            Date utilDate = new Date();
            log.info("************** Inicia Proceso de planilla :" + utilDate + "  *****************");

            // La lista de todas las personas
            String txt_buscar = request.getParameter("txt_buscar");

            if (txt_buscar == null) {
                txt_buscar = "%";
            }

            // Obtiene el universo de trabajadores a procesar
            List<PlaProPeriodo> lp_persona = planillaService.listPlaProper(idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, txt_buscar);

            // Ejecuta la formulacion
            Iterator<PlaProPeriodo> pi;
            PlaProPeriodo pi_persona = null;
            Integer counter = 0;

            List<PlaProPeriodo> lp_persona_s1 = new ArrayList<PlaProPeriodo>();
            List<PlaProPeriodo> lp_persona_s2 = new ArrayList<PlaProPeriodo>();
            List<PlaProPeriodo> lp_persona_s3 = new ArrayList<PlaProPeriodo>();
            List<PlaProPeriodo> lp_persona_s4 = new ArrayList<PlaProPeriodo>();

            pi = lp_persona.iterator();

            while (pi.hasNext()) {
                pi_persona = pi.next();

                if (counter >= 0 && counter <= 350) {
                    lp_persona_s1.add(pi_persona);
                } else if (counter > 350 && counter <= 700) {
                    lp_persona_s2.add(pi_persona);
                } else if (counter > 700 && counter <= 1000) {
                    lp_persona_s3.add(pi_persona);
                } else if (counter > 1000 && counter <= 1350) {
                    lp_persona_s4.add(pi_persona);
                }

                log.info(" MAESTRO ===>  <--------->  p_codtra =" + pi_persona.getDestra() + " ");
                counter++;
            }

            ExecutorService executor = Executors.newFixedThreadPool(4);

            Runnable worker = new WorkerThread("Hilo 1", idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, lp_persona_s1, 1);
            executor.execute(worker);

            Runnable worker2 = new WorkerThread("Hilo 2", idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, lp_persona_s2, 2);
            executor.execute(worker2);

            Runnable worker3 = new WorkerThread("Hilo 3", idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, lp_persona_s3, 3);
            executor.execute(worker3);

            Runnable worker4 = new WorkerThread("Hilo 4", idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, lp_persona_s4, 4);
            executor.execute(worker4);

            /////////////////////////////////////////////////////////////////////
            executor.shutdown();
            while (!executor.isTerminated()) {
            }

            log.info("Finished all threads");

            planillaService.guardarNomina2020(idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel);

            Date utilDatef = new Date();
            log.info("************** Fin de  Proceso de planilla :" + utilDatef + " **********************");

            planillaService.timeFinexe(idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel);
        }

        if (accion.equals("DELPRO")) {
            planillaService.delPlaProper(idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, grupopla, user);
        }

        if (accion.equals("QRYPLA")) {
            model.addAttribute("xproplaper", procesoPlanillaService.recuperarPeriodo2(idCompania, Integer.valueOf(iexcodpro), iexperiodo));
            model.addAttribute("LstPlanillaRes", planillaService.listPlaProper(idCompania, iexcodpro, iexperiodo, -1, 1, "%"));
        }

        model.addAttribute("iexcodreg", iexcodreg);
        model.addAttribute("iexcodpro", iexcodpro);
        model.addAttribute("iexperiodo", iexperiodo);

        model.addAttribute("xproplaper", procesoPlanillaService.recuperarPeriodo2(idCompania, Integer.valueOf(iexcodpro), iexperiodo));
        model.addAttribute("LstPlanillaRes", planillaService.listPlaProper(idCompania, iexcodpro, iexperiodo, -1, 1, "%"));

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/detallePlanillaGeneral");
    }

    @RequestMapping("/verDetalleVariable@{iexcodreg}@{iexcodpro}@{iexperiodo}")
    public ModelAndView verDetalleVariable(ModelMap model, HttpServletRequest request,
                                           @PathVariable Integer iexcodreg,
                                           @PathVariable Integer iexcodpro,
                                           @PathVariable String iexperiodo) {
        log.info("/verDetalleVariable");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("iexcodreg", iexcodreg);
        model.addAttribute("iexcodpro", iexcodpro);
        model.addAttribute("iexperiodo", iexperiodo);
        model.addAttribute("xproplaper", procesoPlanillaService.recuperarPeriodo2(idCompania, Integer.valueOf(iexcodpro), iexperiodo));
        model.addAttribute("LstPlanillaRes", planillaService.listPlaProper(idCompania, iexcodpro, iexperiodo, -1, 1, "%"));

        model.addAttribute("lovConcepProVar", sueldoService.ListConcepProVar(idCompania, iexcodpro, "2"));
        model.addAttribute("fdatavar", sueldoService.obtenerEmpResvar(idCompania, iexcodpro, iexperiodo, 1));

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/detalleVariablePlanGen");
    }

    @SneakyThrows
    @RequestMapping(value ="/gestionarTrabPlanConcept", method = RequestMethod.POST)
    public ModelAndView gestionarTrabPlanConcept(ModelMap model, HttpServletRequest request,HttpServletResponse response,
                                               @RequestParam("uploadFile") MultipartFile uploadFile) throws UncheckedIOException {
        log.info("/gestionarTrabPlanConcept");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String accion = request.getParameter("accion");
        String iexcodreg = request.getParameter("iexcodreg");
        Integer v_codpro = Integer.valueOf(request.getParameter("iexcodpro"));
        String periodo = request.getParameter("iexperiodo");
        Integer v_correl = Integer.valueOf(request.getParameter("iexcorrel"));
        String codtra = request.getParameter("slc_codtra");
        String codcon = request.getParameter("slc_codcon");
        String importe = request.getParameter("txt_importe");

        Double valor = 0.00;

        if (importe == null || importe == "" || importe.equals("")) {
            valor = 0.00;
        } else {
            valor = Double.parseDouble(request.getParameter("txt_importe"));
        }

        if (accion.equals("DELMASVAR")) {
            sueldoService.eliminarAllDatvar(idCompania, v_codpro, periodo, v_correl);
        } else if(accion.equals("INSTRAVAR")){
            EmpDatvar Datvar = new EmpDatvar();
            Datvar.setIexcodcia(idCompania);
            Datvar.setIexcodtra(Integer.parseInt(codtra));
            Datvar.setIexcodpro(v_codpro);
            Datvar.setIexnroper(periodo);
            Datvar.setIexcodcon(codcon);
            Datvar.setIexcorrel(v_correl);
            Datvar.setIexvalcon(valor);
            Datvar.setIexflgest("1");

            sueldoService.insertarEmpDatvar(Datvar);
        }

        if (accion.equals("UPXLSVAR")) {
            // Process only if its multipart content
            PrintWriter out = response.getWriter();

            Integer v_codcab = 1;
            String v_codtra = "0";
            String tipo_excel = "";

            InputStream file = uploadFile.getInputStream();

            tipo_excel = FilenameUtils.getExtension(uploadFile.getOriginalFilename());

            log.info("Tipo excel " + tipo_excel);
            // Create Workbook instance holding reference to .xlsx file
            // XSSFWorkbook workbook = new XSSFWorkbook(file);
            // Get first/desired sheet from the workbook
            // XSSFSheet sheet = workbook.getSheetAt(1);

            XSSFWorkbook workbook;
            XSSFSheet sheet;

            HSSFWorkbook workbook2;
            HSSFSheet sheet2;

            Iterator<Row> rowIterator = null;

            try {
                if (tipo_excel.equals("xls") || tipo_excel.equals("XLS")) {
                    workbook2 = new HSSFWorkbook(file);
                    sheet2 = workbook2.getSheetAt(0);
                    rowIterator = sheet2.iterator();
                } else if (tipo_excel.equals("xlsx") || tipo_excel.equals("XLSX")) {
                    workbook = new XSSFWorkbook(file);
                    sheet = workbook.getSheetAt(0);
                    rowIterator = sheet.iterator();
                } else {
                    model.addAttribute("message", "El archivo debe ser de extensión xls y/o xlsx");
                }
            } catch (IOException ex2) {
                model.addAttribute("message", "el archivo debe ser de extensión xls y/o xlsx");
            }

            String result = null;
            StringBuilder sql = new StringBuilder();
            String inputName = null;

            ArrayList<String> v_con = new ArrayList<String>(35);
            EmpDatvar empvar = null;
            List<EmpDatvar> l_empvar = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                log.info("Fila <" + v_codcab + ">");

                for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                    Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    // Print the cell for debugging
                    if (cn == 0 && v_codcab >= 3) {

                        if (cell.getCellType() != CellType.NUMERIC) { //si el valor es numérico
                            v_codtra = cell.getStringCellValue();
                        } else {
                            int num = (int) cell.getNumericCellValue();
                            v_codtra = Integer.toString(num);
                        }
                        log.info("v_codtra: "+v_codtra);
                    }

                    if (v_codcab == 2 && cn >= 2) {
                        v_con.add(cell.toString());
                        log.info("i: " + cn + " --> " + cell.toString() + "  ");
                    } else if (v_codcab < 2) {
                        log.info("i: " + cn + " --> " + cell.toString() + "  ");
                    } else if (v_codcab >= 3 && cn >= 2) {
                        log.info("i: " + cn + " --> " + cell.toString() + "  con:(" + v_con.get(cn - 2) + ") <-->  codtra :" + v_codtra + " -- ");

                        empvar = new EmpDatvar();

                        // Insertar valor
                        empvar.setIexcodcia(idCompania);
                        empvar.setIexcodpro(v_codpro);
                        empvar.setIexnroper(periodo);
                        empvar.setIexcodtra(Integer.valueOf(v_codtra));
                        empvar.setIexcodcon(v_con.get(cn - 2));
                        empvar.setIexvalcon(Double.parseDouble(cell.toString()));
                        empvar.setIexcorrel(v_correl);
                        empvar.setIexusucrea(user);
                        l_empvar.add(empvar);
                    }
                }

                v_codcab++;
            }

            // Insertar detalle
            sueldoService.insertarDatvarmas(l_empvar);
        }

        model.addAttribute("iexcodreg", iexcodreg);
        model.addAttribute("iexcodpro", v_codpro);
        model.addAttribute("iexperiodo", periodo);
        model.addAttribute("xproplaper", procesoPlanillaService.recuperarPeriodo2(idCompania, Integer.valueOf(v_codpro), periodo));
        model.addAttribute("LstPlanillaRes", planillaService.listPlaProper(idCompania, v_codpro, periodo, -1, 1, "%"));
        model.addAttribute("lovConcepProVar", sueldoService.ListConcepProVar(idCompania, v_codpro, "2"));
        model.addAttribute("fdatavar", sueldoService.obtenerEmpResvar(idCompania, v_codpro, periodo, 1));

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/detalleVariablePlanGen");
    }

    @RequestMapping("/actualizarValorTrabConcept@{codtra}@{codproceso}@{periodo}@{iexcodcon}@{iexcorrel}@{iexcodreg}@{valor}")
    public ModelAndView actualizarValorTrabConcept(ModelMap model, HttpServletRequest request,
                                                   @PathVariable String codtra,
                                                   @PathVariable Integer codproceso,
                                                   @PathVariable String periodo,
                                                   @PathVariable String iexcodcon,
                                                   @PathVariable Integer iexcorrel,
                                                   @PathVariable Integer iexcodreg,
                                                   @PathVariable String valor) {
        log.info("/actualizarValorTrabConcept");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        EmpDatvar Datvar = new EmpDatvar();
        Datvar.setIexcodcia(idCompania);
        Datvar.setIexcodtra(Integer.parseInt(codtra));
        Datvar.setIexcodpro(codproceso);
        Datvar.setIexnroper(periodo);
        Datvar.setIexcodcon(iexcodcon);
        Datvar.setIexcorrel(iexcorrel);
        Datvar.setIexvalcon(Double.valueOf(valor));
        Datvar.setIexflgest("1");

        sueldoService.actualizarEmpDatvar(Datvar);

        return new ModelAndView("redirect:/verDetalleVariable@" + iexcodreg + "@" + codproceso + "@" + periodo);
    }

    @RequestMapping(value = "/traerDatosDeBoleta", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView traerDatosDeBoleta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/traerDatosDeBoleta");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer iexcodpro = Integer.valueOf(request.getParameter("iexcodpro"));
        Integer iexcodtra = Integer.valueOf(request.getParameter("iexcodtra"));
        String iexperiodo = request.getParameter("iexperiodo");
        String xgrppla = request.getParameter("xgrppla");
        Integer iexcorrel = Integer.valueOf(request.getParameter("iexcorrel"));
        String iexcodreg = request.getParameter("iexcodreg");

        PlaProPeriodo plaperpro7= planillaService.listPlaProperTra(idCompania,iexcodpro,iexperiodo,iexcodtra,iexcorrel);

        String json = new Gson().toJson(plaperpro7);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping(value = "/traerDatosDeBoletaParam", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView traerDatosDeBoletaParam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/traerDatosDeBoletaParam");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer iexcodpro = Integer.valueOf(request.getParameter("iexcodpro"));
        Integer iexcodtra = Integer.valueOf(request.getParameter("iexcodtra"));
        String iexperiodo = request.getParameter("iexperiodo");
        String xgrppla = request.getParameter("xgrppla");
        Integer iexcorrel = Integer.valueOf(request.getParameter("iexcorrel"));
        String iexcodreg = request.getParameter("iexcodreg");

        List<ConceptoxProcesoxTra> listap = planillaService.listProperconConZeros(idCompania,iexcodpro,iexperiodo,iexcodtra,iexcorrel,"0");

        String json = new Gson().toJson(listap);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping(value = "/botonEliminarPlanTrab", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView botonEliminarPlanTrab(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/botonEliminarPlanTrab");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer iexcodpro = Integer.valueOf(request.getParameter("iexcodpro"));
        Integer iexcodtra = Integer.valueOf(request.getParameter("iexcodtra"));
        String iexperiodo = request.getParameter("iexperiodo");
        String xgrppla = request.getParameter("xgrppla");
        Integer iexcorrel = Integer.valueOf(request.getParameter("iexcorrel"));
        String iexcodreg = request.getParameter("iexcodreg");

        planillaService.delPlaProper(idCompania,iexcodpro,iexperiodo,iexcodtra,iexcorrel,xgrppla,user);

        String json = new Gson().toJson(null);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping("/traerDatosReporteResumenPlanilla")
    public ModelAndView traerDatosReporteResumenPlanilla(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        log.info("/traerDatosReporteResumenPlanilla");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer iexcodpro = Integer.valueOf(request.getParameter("iexcodpro"));
        Integer nroper = Integer.valueOf(request.getParameter("nroper"));
        Integer nroper2 = Integer.valueOf(request.getParameter("nroper2"));

        String fileName_pdf = "";

        Regions cliRegion = null;
        String bucket_name_pdf = "";
        String key_name_pdf = "";
        String passPhrase_pdf = "";

        InputStream inputStream = null;

        Compania ciainfo = companiaService.getCompaniaAll(Integer.valueOf(idCompania));

        cliRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());
        bucket_name_pdf = ciainfo.getIexsourcedes().trim();
        key_name_pdf = ciainfo.getIexususource().trim();
        passPhrase_pdf = ciainfo.getIexpasssource().trim();

        fileName_pdf = "reportes/ReportResumenPla02.jasper";

        AWSCredentials credentials = new BasicAWSCredentials(key_name_pdf, passPhrase_pdf);
        AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(cliRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
        S3Object o = s3.getObject(bucket_name_pdf, fileName_pdf);
        inputStream = o.getObjectContent();

        Map parametros = new HashMap();
        parametros.put("P_CODCIA", idCompania);
        parametros.put("P_CODPRO", iexcodpro);
        parametros.put("P_NROPER", nroper);
        parametros.put("P_NROPER2", nroper2);
        parametros.put("P_XNROPER", "");
        parametros.put("P_XCODCON", "");
        parametros.put("SUBREPORT_DIR", "");

        Connection conn = template.getDataSource().getConnection();

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conn);

            if (jasperPrint != null) {
                log.info("Jasper encontrado");
                try (PrintWriter writer = response.getWriter()) {
                    HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(new SimpleHtmlExporterOutput(writer));
                    exporter.exportReport();
                    writer.flush();
                } catch (IOException e) {
                    log.info("Error de entrada/salida al escribir en el flujo de salida: " + e.getMessage());
                    throw new RuntimeException(e);
                }
            } else {
                log.info("JasperPrint es nulo");
            }
        } catch (JRException ex) {
            log.info("Error al procesar el informe Jasper: " + ex.getMessage());
        } catch (Exception e) {
            log.info("Otro error inesperado: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }

}

