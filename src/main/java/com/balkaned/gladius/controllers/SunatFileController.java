package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.AsientoContableCab;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class SunatFileController {

    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    UsuarioConeccionService usuarioConeccionService;
    @Autowired
    LovsService lovsService;
    @Autowired
    VacacionesService vacacionesService;
    @Autowired
    ProcesoPlanillaService procesoPlanillaService;
    @Autowired
    PlanillaService planillaService;
    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/gestionPlame")
    public ModelAndView gestionPlame(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/gestionPlame");

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
        v_idperiodo = request.getParameter("permes");
        String file2 = request.getParameter("file");

        planillaService.PlameExe(v_codcia, v_idperiodo, file2);


        log.info("PLAMEEXEv_codcia " + v_codcia);
        log.info("PLAMEEXEv_idperiodo " + v_idperiodo);
        log.info("PLAMEEXEfile2 " + file2);

        model.addAttribute("permes", v_idperiodo);

        return new ModelAndView("public/gladius/gestionProceso/plame/gestionPlame");
    }

    @RequestMapping("/expPlameFile")
    public ModelAndView expPlameFile(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/expPlameFile");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");

        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=\"ctm.jor\"");

        String file = "C://ctm.jor";
        String file2 = "";
        String v_idproceso = "";
        String v_idperiodo = "";

        Integer v_codcia = idCompania;
        String ruc =rucComp;
        v_idperiodo = request.getParameter("permes");
        file2 = request.getParameter("file");
        String idplame = "0601" + v_idperiodo + ruc;


        log.info("v_codcia " + v_codcia);
        log.info("v_ruc " + ruc);
        log.info("v_idperiodo " + v_idperiodo);
        log.info("v_file2 " + file2);
        log.info("v_idPlame " + idplame + v_idperiodo + ruc);


        switch (file2) {
            case "14":   //  el preingreso carga todas as listas de valores para el registro
                response.setHeader("Content-Disposition", "attachment; filename=\"" + idplame + ".jor\"");
                break;
            case "15":
                response.setHeader("Content-Disposition", "attachment; filename=\"" + idplame + ".snl\"");
                break;
            case "18":
                response.setHeader("Content-Disposition", "attachment; filename=\"" + idplame + ".rem\"");
                break;
            case "26":
                response.setHeader("Content-Disposition", "attachment; filename=\"" + idplame + ".toc\"");
                break;
            default:
                break;
        }

        Map<String, Object> parametros = new HashMap<>();

        List<String> lista = planillaService.PlameMes(idCompania, v_idperiodo, file2);

        try {
            PrintWriter writer = response.getWriter();
            String plaproper;
            Iterator<String> l_propertra = lista.iterator();
            while (l_propertra.hasNext()) {
                plaproper = l_propertra.next();
                log.info(" Codtra :" + plaproper);
                writer.println(plaproper);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            log.error("Error ", e);
        }

        return new ModelAndView("public/gladius/gestionProceso/plame/gestionPlame");
    }


    @RequestMapping("/gestionAsientosContables")
    public ModelAndView gestionAsientosContables(ModelMap model, HttpServletRequest request) {
        log.info("/gestionAsientosContables");

        String user = (String) request.getSession().getAttribute("user");

        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        model.addAttribute("lovProcesos", procesoPlanillaService.listar("%"));

        return new ModelAndView("public/gladius/gestionProceso/asientoContable/gestionAsientosContables");
    }


    @RequestMapping("/buscarAsientosContables")
    public ModelAndView buscarAsientosContables(ModelMap model, HttpServletRequest request) {
        log.info("/buscarAsientosContables");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        AsientoContableCab asicontcab = null;

        String v_idproceso = "";
        String v_idperiodo = "";
        v_idproceso = request.getParameter("iexcodpro");
        v_idperiodo = request.getParameter("permes");
        asicontcab = new AsientoContableCab();

        asicontcab.setIexcodcia(idCompania);
        asicontcab.setIexcodpro(Integer.parseInt(v_idproceso));
        asicontcab.setIexnroper(v_idperiodo);

        model.addAttribute("lst_asiento_cab", procesoPlanillaService.listarAsieCab(idCompania, Integer.parseInt(v_idproceso), v_idperiodo));
        model.addAttribute("iexcodpro", v_idproceso);
        model.addAttribute("permes", v_idperiodo);
        model.addAttribute("msg", "Se genero asiento ");

        return new ModelAndView("public/gladius/gestionProceso/plame/gestionAsientosContables");
    }

    @RequestMapping("/nuevoAsientosContables")
    public ModelAndView nuevoAsientosContables(ModelMap model, HttpServletRequest request) {
        log.info("/nuevoAsientosContables");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        model.addAttribute("lovProcesos", procesoPlanillaService.listar("%"));
        log.info("lovProcesos" + procesoPlanillaService.listar("%"));

        return new ModelAndView("public/gladius/gestionProceso/asientoContable/nuevoAsientosContables");
    }


}

