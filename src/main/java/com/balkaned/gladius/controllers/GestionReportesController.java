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

        // Verificación de sesión y obtención de datos
        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.isEmpty() || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        // Obtención de la lista de empleados
        Empleado empleado = new Empleado();
        empleado.setIexcodcia(idCompania);
        List<Empleado> listarEmp = empleadoService.listarEmpleado(empleado);

        // Obtención de parámetros
        String anio = request.getParameter("peranio");
        String iexcodtra = request.getParameter("percodtra");


        // Listas y objetos relacionados
        List<PlaProPeriodo> lista = new ArrayList<>();
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

        // Añadir atributos al modelo
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
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        model.addAttribute("lovProcesos", procesoPlanillaService.listar("%"));
        return new ModelAndView("public/gladius/gestionDePlanilla/reportePlanilla/listReportePlanillas");
    }


    @RequestMapping("/lisReportePdf")
    public ModelAndView lisReportePdf(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        log.info("/lisReportePdf");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }
        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        String v_idproceso = "";
        String v_idperiodo = "";
        Integer v_codcia = idCompania;
        String v_nroper = request.getParameter("nroper");
        String v_nroper2 = request.getParameter("nroper2");
        v_idproceso = request.getParameter("iexcodpro");


        log.info("v_codcia: " + v_codcia);
        log.info("v_idproceso " + v_idproceso);
        log.info("v_idperiodo " + v_idperiodo);
        log.info("v_nroper " + v_nroper);
        log.info("v_nroper2 " + v_nroper2);


        String fileName_pdf = "";
        Regions cliRegion = null;
        String bucket_name_pdf = "";
        String key_name_pdf = "";
        String passPhrase_pdf = "";
        InputStream inputStream = null;


        Compania ciainfo = companiaService.getCompaniaAll(Integer.valueOf(v_codcia));

        cliRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());
        bucket_name_pdf = ciainfo.getIexsourcedes().trim();
        key_name_pdf = ciainfo.getIexususource().trim();
        passPhrase_pdf = ciainfo.getIexpasssource().trim();


        log.info("cliRegion: " + cliRegion);
        log.info("bucket_name_pdf: " + bucket_name_pdf);
        log.info("key_name_pdf: " + key_name_pdf);
        log.info("passPhrase_pdf: " + passPhrase_pdf);

        fileName_pdf = "reportes/ReportResumenPla02.jasper";

        AWSCredentials credentials = new BasicAWSCredentials(key_name_pdf, passPhrase_pdf);
        AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(cliRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
        S3Object o = s3.getObject(bucket_name_pdf, fileName_pdf);
        inputStream = o.getObjectContent();


        log.info("Entro al reporte lisReportePdf ");


        Map parametros = new HashMap();
        parametros.put("P_CODCIA", v_codcia);
        parametros.put("P_CODPRO", Integer.parseInt(v_idproceso));
        parametros.put("P_NROPER", v_nroper);
        parametros.put("P_NROPER2", v_nroper2);
        parametros.put("P_XNROPER", "");
        parametros.put("P_XCODCON", "");
        parametros.put("SUBREPORT_DIR", "");


        log.info("cliRegion: " + cliRegion);
        log.info("bucket_name_pdf: " + bucket_name_pdf);
        log.info("key_name_pdf: " + key_name_pdf);
        log.info("passPhrase_pdf: " + passPhrase_pdf);


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


        model.addAttribute("lovProcesos", procesoPlanillaService.listar("%"));
        return new ModelAndView("public/gladius/gestionDePlanilla/reportePlanilla/listReportePlanillas");
    }

    @RequestMapping("/listReportePlanillaxConcepto")
    public ModelAndView listReportePlanillaxConcepto(ModelMap model, HttpServletRequest request) {
        log.info("/listReportePlanillaxConcepto");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

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
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Empleado Empleado = new Empleado();
        Empleado.setIexcodcia(idCompania);
        List<Empleado> listaEmpl = empleadoService.listarEmpleado(Empleado);


        String perini = request.getParameter("perini");
        String perfin = request.getParameter("perfin");
        String iexcodtra = request.getParameter("codtra");
        String codpro = request.getParameter("codpro");

        try {
            int codtrab = Integer.parseInt(iexcodtra);
            Empleado empleado = empleadoService.recuperarCabecera(idCompania, codtrab);

            log.info("recuperar" + empleado);

            if (codpro == null || codpro.trim().isEmpty()) {
                List<PlaProPeriodo> lista = planillaService.listAllPlaPerTra(idCompania, codtrab, perini, perfin);
                model.addAttribute("Res_planAllPerTra", lista);
                model.addAttribute("perini", perini);
                model.addAttribute("perfin", perfin);
                model.addAttribute("codtra", codtrab);
                model.addAttribute("fichaEmp", empleado);
            } else {
                List<PlaProPeriodo> lista = planillaService.listAllPlaPerTraPro(idCompania, codtrab, Integer.parseInt(codpro), perini, perfin);
                model.addAttribute("Res_planAllPerTra", lista);
                model.addAttribute("perini", perini);
                model.addAttribute("perfin", perfin);
                model.addAttribute("codtra", codtrab);
                model.addAttribute("codpro", codpro);
                model.addAttribute("fichaEmp", empleado);

                log.info("Res_planAllPerTra" + planillaService.listAllPlaPerTraPro(idCompania, codtrab, Integer.parseInt(codpro), perini, perfin));
            }
        } catch (NumberFormatException e) {
            log.error("Invalid value for iexcodtra: " + iexcodtra, e);
            // Manejar el error de conversión de manera apropiada, si es necesario
        }
        log.info("codpro : " + codpro);
        log.info("iexcodtra : " + iexcodtra);
        log.info("perini : " + perini);
        log.info("perfin : " + perfin);


        model.addAttribute("LstEmpleadoRes", listaEmpl);
        model.addAttribute("LstProcesoPlanilla", procesoPlanillaService.listar("%"));
        return new ModelAndView("public/gladius/gestionDePlanilla/ReporteNominaXPersona/listReporteNominaxPersona");
    }


}
