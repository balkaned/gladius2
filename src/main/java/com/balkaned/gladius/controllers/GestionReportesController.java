package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.HtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.export.SimpleExporterInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
@Slf4j
@RestController
public class GestionReportesController {


    public static Logger logger = Logger.getLogger(GestionReportesController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    @Autowired
    PlanillaService planillaService;
    @Autowired
    ProcesoPlanillaService procesoPlanillaService;
    @Autowired
    LovsService lovsService;
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    ConceptoService conceptoService;
    @Autowired
    CompaniaService companiaService;
    @Autowired
    EmpAcumService empAcumService;
    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listReporte5taNomina")
    public ModelAndView listReporte5taNomina(ModelMap model, HttpServletRequest request) {
        log.info("/listReporte5taNomina");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Empleado Empleado = new Empleado();
        Empleado.setIexcodcia(idCompania);
        List<Empleado> listarEmp = empleadoService.listarEmpleado(Empleado);

        String v_idproceso = "";
        String v_idperiodo = "";
        String anio = request.getParameter("peranio");
        String iexcodtra = request.getParameter("percodtra");

        List<PlaProPeriodo> lista = new ArrayList<>();
        Empleado empleado = new Empleado();
        EmpAcum xEmpAcumAnio = new EmpAcum();
        try {
            if (iexcodtra != null && !iexcodtra.isEmpty() && iexcodtra.matches("\\d+")) {

                empleado = empleadoService.recuperarCabecera(idCompania, Integer.valueOf(iexcodtra));
                lista = planillaService.listPla5ta(idCompania, anio, Integer.parseInt(iexcodtra));
                xEmpAcumAnio = empAcumService.getEmpAcum(idCompania, Integer.parseInt(iexcodtra), anio);
            } else {
                log.info("Error no encontrado" + iexcodtra);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        model.addAttribute("Res_planilla5ta", lista);
        model.addAttribute("peranio", anio);
        model.addAttribute("percodtra", iexcodtra);
        model.addAttribute("LstEmpleadoRes", listarEmp);
        model.addAttribute("fichaEmp", empleado);
        model.addAttribute("xEmpAcumAnio", xEmpAcumAnio);

        return new ModelAndView("public/gladius/gestionDePlanilla/reporte5Nomina/listReporte5taNomina");
    }


    @RequestMapping("/listReportePlanillas")
    public ModelAndView listReportePlanillas(ModelMap model, HttpServletRequest request) {
        log.info("/listReportePlanillas");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        model.addAttribute("lovProcesos", procesoPlanillaService.listar("%"));
        return new ModelAndView("public/gladius/gestionDePlanilla/reportePlanilla/listReportePlanillas");
    }


    @RequestMapping("/lisReportePdf")
    public ModelAndView lisReportePdf(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        log.info("/lisReportePdf");

        String user = (String) request.getSession().getAttribute("user");
        logger.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            logger.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }
        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        String v_idproceso = "";
        String v_idperiodo = "";
        Integer v_codcia = idCompania;
        String v_nroper = request.getParameter("nroper");
        String v_nroper2 = request.getParameter("nroper2");
        v_idproceso = request.getParameter("codpro");

        byte[] bytes = null;

        String fileName_pdf = "";
        Regions cliRegion = null;
        String bucket_name_pdf = "";
        String key_name_pdf = "";
        String passPhrase_pdf = "";
        InputStream inputStream = null;


        logger.info("v_codcia: " + v_codcia);
        Compania ciainfo = companiaService.getCompaniaAll(Integer.valueOf(v_codcia));

        cliRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());
        bucket_name_pdf = ciainfo.getIexsourcedes().trim();
        key_name_pdf = ciainfo.getIexususource().trim();
        passPhrase_pdf = ciainfo.getIexpasssource().trim();


        logger.info("cliRegion: " + cliRegion);
        logger.info("bucket_name_pdf: " + bucket_name_pdf);
        logger.info("key_name_pdf: " + key_name_pdf);
        logger.info("passPhrase_pdf: " + passPhrase_pdf);

        fileName_pdf = "reportes/ReportResumenPla02.jasper";

        AWSCredentials credentials = new BasicAWSCredentials(key_name_pdf, passPhrase_pdf);
        AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(cliRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
        S3Object o = s3.getObject(bucket_name_pdf, fileName_pdf);
        inputStream = o.getObjectContent();


        logger.info("Entro al reporte pdf ResumenPla ");


        Map parametros = new HashMap();
        parametros.put("P_CODCIA", v_codcia);
        parametros.put("P_CODPRO", v_idproceso);
        parametros.put("P_NROPER", v_nroper);
        parametros.put("P_NROPER2", v_nroper2);
        parametros.put("P_XNROPER", "");
        parametros.put("P_XCODCON", "");
        parametros.put("SUBREPORT_DIR", "");

        Connection conn = template.getDataSource().getConnection();
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conn);

            logger.info("jasperPrint: " + jasperPrint.getName());

            if (jasperPrint != null) {
                logger.info("Se encontró jasper");
                HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
                ServletOutputStream outputStream = response.getOutputStream();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
                exporter.exportReport();
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException ex) {
            logger.info("No compila ");
        } catch (JRException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }

        model.addAttribute("lovProcesos", procesoPlanillaService.listar("%"));
        return new ModelAndView("public/gladius/gestionDePlanilla/reportePlanilla/listReportePlanillas");
    }

    @RequestMapping("/listReportePlanillaxConcepto")
    public ModelAndView listReportePlanillaxConcepto(ModelMap model, HttpServletRequest request) {
        log.info("/listReportePlanillaxConcepto");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String codcon = request.getParameter("codcon");
        String perini = request.getParameter("perini");
        String perfin = request.getParameter("perfin");

        List<Concepto> listacon = (List<Concepto>) model.getAttribute("listacon");
        List<Concepto> lista = (List<Concepto>) model.getAttribute("lstConcepto");

        if (listacon == null) {
            listacon = new ArrayList<>();
            model.addAttribute("listacon", listacon);
        }
        if (lista == null) lista = conceptoService.listardet();

        Concepto condes = conceptoService.recuperar(codcon);
        if (condes != null) {
            listacon.add(condes);
        } else {
            log.info("Error en " + condes);
        }

        log.info("lstConcepto" + lista);
        log.info("listacon" + listacon);

        model.addAttribute("lstConcepto", lista);
        model.addAttribute("xperini", perini);
        model.addAttribute("xperfin", perfin);

        return new ModelAndView("public/gladius/gestionDePlanilla/listReportePlanillasXConceptos/listReportePlanillaxConcepto");
    }

    @RequestMapping("/listReporteNominaxPersona")
    public ModelAndView listReporteNominaxPersona(ModelMap model, HttpServletRequest request) {
        log.info("/listReporteNominaxPersona");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Empleado Empleado = new Empleado();
        Empleado.setIexcodcia(idCompania);
        List<Empleado> listarEmp = empleadoService.listarEmpleado(Empleado);

        Empleado empleado = new Empleado();

        String perini = request.getParameter("perini");
        String perfin = request.getParameter("perfin");
        String iexcodtra = request.getParameter("codtra");
        String codpro = request.getParameter("codpro");

        try {
            if (iexcodtra != null && !iexcodtra.isEmpty() && iexcodtra.matches("\\d+")) {

                empleado = empleadoService.recuperarCabecera(idCompania, Integer.valueOf(iexcodtra));
                if (codpro == null || codpro.trim().equals("")) {

                    List<PlaProPeriodo> lista = planillaService.listAllPlaPerTra(idCompania, Integer.parseInt(iexcodtra), perini, perfin);
                    model.addAttribute("Res_planAllPerTra", lista);
                    model.addAttribute("perini", perini);
                    model.addAttribute("perfin", perfin);
                    model.addAttribute("codtra", iexcodtra);
                    model.addAttribute("fichaEmp", empleado);

                } else {
                    List<PlaProPeriodo> lista = planillaService.listAllPlaPerTraPro(idCompania, Integer.parseInt(iexcodtra), Integer.parseInt(codpro), perini, perfin);
                    model.addAttribute("Res_planAllPerTra", lista);
                    model.addAttribute("perini", perini);
                    model.addAttribute("perfin", perfin);
                    model.addAttribute("codtra", iexcodtra);
                    model.addAttribute("codpro", codpro);
                    model.addAttribute("fichaEmp", empleado);

                }

            } else {
                log.info("Error no encontrado" + iexcodtra);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        model.addAttribute("LstEmpleadoRes", listarEmp);
        model.addAttribute("LstProcesoPlanilla", procesoPlanillaService.listar("%"));
        return new ModelAndView("public/gladius/gestionDePlanilla/ReporteNominaXPersona/listReporteNominaxPersona");
    }
}
