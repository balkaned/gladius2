package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.DerechoHabiente;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;


@RestController
@Slf4j
public class DerechoHabientesController {
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    LovsService lovsService;

    @Autowired
    DerechoHabientesService derechoHabientesService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/derechoHab@{idTrab}")
    public ModelAndView derechoHab(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/derechoHab");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("idTrab: " + idTrab);
        model.addAttribute("idTrab", idTrab);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);
        Empleado emp = empleadoService.recuperarCabecera(idCompania, Integer.parseInt(idTrab));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl", emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp", idCompania);
        model.addAttribute("iexlogo", emp.getIexlogo());
        model.addAttribute("urlLogo", urlLogo);

        String sexo;
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        log.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        model.addAttribute("LovDerhab", derechoHabientesService.listar(emp.getIexcodcia(), emp.getIexcodtra()));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/derechoHabientes/derechoHab");
    }

    @RequestMapping("/nuevoDerechoHab@{idTrab}")
    public ModelAndView nuevoDerechoHab(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/nuevoDerechoHab");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("idTrab: " + idTrab);
        model.addAttribute("idTrab", idTrab);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);

        Empleado emp = empleadoService.recuperarCabecera(idCompania, Integer.parseInt(idTrab));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl", emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp", idCompania);
        model.addAttribute("iexlogo", emp.getIexlogo());
        model.addAttribute("urlLogo", urlLogo);

        String sexo;
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());

        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }

        log.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        model.addAttribute("lovTipdoc", lovsService.getLovs("3", "%"));
        model.addAttribute("lovSexo", lovsService.getLovs("50", "%"));
        model.addAttribute("lovPaisEmisor", lovsService.getLovs("26", "%"));
        model.addAttribute("lovVincul", lovsService.getLovs("19", "%"));
        model.addAttribute("lovAcredVincul", lovsService.getLovs("27", "%"));
        model.addAttribute("lovTipVia", lovsService.getLovs("5", "%"));
        model.addAttribute("lovTipZona", lovsService.getLovs("6", "%"));
        model.addAttribute("lovTipVia2", lovsService.getLovs("5", "%"));
        model.addAttribute("lovTipZona2", lovsService.getLovs("6", "%"));
        model.addAttribute("lovLarDistancia", lovsService.getLovs("29", "%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/derechoHabientes/nuevoDerechoHab");
    }

    @RequestMapping("/insertarDerechoHab")
    public ModelAndView insertarDerechoHab(ModelMap model, HttpServletRequest request) {
        log.info("/insertarDerechoHab");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String Msg_form_global = "";
        Integer coddep = 0;
        Integer idfinal = 0;
        Integer count = 0;

        Integer iexcodcia = Integer.valueOf(request.getParameter("iexcodcia"));
        Integer iexcodtra = Integer.valueOf(request.getParameter("iexcodtra"));
        String iextipnroiddep = request.getParameter("iextipnroiddep");
        String iexnroiddep = request.getParameter("iexnroiddep");

        DerechoHabiente derhab = new DerechoHabiente();
        derhab.setIexcodcia(iexcodcia);
        derhab.setIexcodtra(iexcodtra);
        derhab.setIextipnroiddep(iextipnroiddep);
        derhab.setIexnroiddep(iexnroiddep);

        count = derechoHabientesService.validarDerhabiente(derhab);
        log.info("count: " + count);

        if (count > 0) {
            idfinal = -1;
            model.addAttribute("msg", "Ya existe un empleado con ese Nro de Documento");
            model.addAttribute("idTrab", iexcodtra);
        } else {
            Msg_form_global = "ok";

            coddep = derechoHabientesService.getIdDerechoHab(derhab);
            log.info("coddep: " + coddep);

            if (Msg_form_global == "ok") {
                if (coddep > 0) {

                    DerechoHabiente derhab2 = new DerechoHabiente();

                    Integer iexcodcia2 = iexcodcia;
                    Integer iexcodtra2 = iexcodtra;
                    //String iexcoddep = request.getParameter("iexcoddep");
                    String iextipnroiddep2 = iextipnroiddep;
                    String iexnroiddep2 = iexnroiddep;
                    String iexpaisemisor = request.getParameter("iexpaisemisor");
                    String iexfecnac = request.getParameter("iexfecnac");
                    String iexapepatdep = request.getParameter("iexapepatdep");
                    String iexapematdep = request.getParameter("iexapematdep");
                    String iexnomdep = request.getParameter("iexnomdep");
                    String iexsexo = request.getParameter("iexsexo");
                    String iextipvinculo = request.getParameter("iextipvinculo");
                    String iextipdocacredit = request.getParameter("iextipdocacredit");
                    String iexnrodocacredit = request.getParameter("iexnrodocacredit");
                    String iexmesconcep = request.getParameter("iexmesconcep");
                    String iextipvia_dom1 = request.getParameter("iextipvia_dom1");
                    String iexnomvia_dom1 = request.getParameter("iexnomvia_dom1");
                    String iexnrovia_dom1 = request.getParameter("iexnrovia_dom1");
                    String iexdeptin_dom1 = request.getParameter("iexdeptin_dom1");
                    String iexinterior_dom1 = request.getParameter("iexinterior_dom1");
                    String iexmanzana_dom1 = request.getParameter("iexmanzana_dom1");
                    String iexlote_dom1 = request.getParameter("iexlote_dom1");
                    String iexkilometro_dom1 = request.getParameter("iexkilometro_dom1");
                    String iexblock_dom1 = request.getParameter("iexblock_dom1");
                    String iexetapa_dom1 = request.getParameter("iexetapa_dom1");
                    String iextipzona_dom1 = request.getParameter("iextipzona_dom1");
                    String iexnomzona_dom1 = request.getParameter("iexnomzona_dom1");
                    String iexreferencia_dom1 = request.getParameter("iexreferencia_dom1");
                    String iexubigeo_dom1 = request.getParameter("iexubigeo_dom1");
                    String iextipvia_dom2 = request.getParameter("iextipvia_dom2");
                    String iexnomvia_dom2 = request.getParameter("iexnomvia_dom2");
                    String iexnrovia_dom2 = request.getParameter("iexnrovia_dom2");
                    String iexdeptin_dom2 = request.getParameter("iexdeptin_dom2");
                    String iexinterior_dom2 = request.getParameter("iexinterior_dom2");
                    String iexmanzana_dom2 = request.getParameter("iexmanzana_dom2");
                    String iexlote_dom2 = request.getParameter("iexlote_dom2");
                    String iexkilometro_dom2 = request.getParameter("iexkilometro_dom2");
                    String iexblock_dom2 = request.getParameter("iexblock_dom2");
                    String iexetapa_dom2 = request.getParameter("iexetapa_dom2");
                    String iextipzona_dom2 = request.getParameter("iextipzona_dom2");
                    String iexnomzona_dom2 = request.getParameter("iexnomzona_dom2");
                    String iexreferencia_dom2 = request.getParameter("iexreferencia_dom2");
                    String iexubigeo_dom2 = request.getParameter("iexubigeo_dom2");
                    String iexcenasis = request.getParameter("iexcenasis");
                    String iexcodlar = request.getParameter("iexcodlar");
                    String iexnrotelf = request.getParameter("iexnrotelf");
                    String iexemail = request.getParameter("iexemail");
                    String iexnacion_origen1 = request.getParameter("iexpaisemisor1");
                    String iexdepart_origen1 = request.getParameter("iexdepart_origen1");
                    String iexprovin_origen1 = request.getParameter("iexprovin_origen1");
                    String iexnacion_origen2 = request.getParameter("iexpaisemisor2");
                    String iexdepart_origen2 = request.getParameter("iexdepart_origen2");
                    String iexprovin_origen2 = request.getParameter("iexprovin_origen2");

                    derhab2.setIexcodcia(iexcodcia2);
                    derhab2.setIexcodtra(iexcodtra2);
                    derhab2.setIexcoddep(coddep);
                    derhab2.setIextipnroiddep(iextipnroiddep2);
                    derhab2.setIexnroiddep(iexnroiddep2);
                    derhab2.setIexpaisemisor(iexpaisemisor);
                    derhab2.setIexfecnac(iexfecnac);
                    derhab2.setIexapepatdep(iexapepatdep);
                    derhab2.setIexapematdep(iexapematdep);
                    derhab2.setIexnomdep(iexnomdep);
                    derhab2.setIexsexo(iexsexo);
                    derhab2.setIextipvinculo(iextipvinculo);
                    derhab2.setIextipdocacredit(iextipdocacredit);
                    derhab2.setIexnrodocacredit(iexnrodocacredit);
                    derhab2.setIexmesconcep(iexmesconcep);
                    derhab2.setIextipvia_dom1(iextipvia_dom1);
                    derhab2.setIexnomvia_dom1(iexnomvia_dom1);
                    derhab2.setIexnrovia_dom1(iexnrovia_dom1);
                    derhab2.setIexdeptin_dom1(iexdeptin_dom1);
                    derhab2.setIexinterior_dom1(iexinterior_dom1);
                    derhab2.setIexmanzana_dom1(iexmanzana_dom1);
                    derhab2.setIexlote_dom1(iexlote_dom1);
                    derhab2.setIexkilometro_dom1(iexkilometro_dom1);
                    derhab2.setIexblock_dom1(iexblock_dom1);
                    derhab2.setIexetapa_dom1(iexetapa_dom1);
                    derhab2.setIextipzona_dom1(iextipzona_dom1);
                    derhab2.setIexnomzona_dom1(iexnomzona_dom1);
                    derhab2.setIexreferencia_dom1(iexreferencia_dom1);
                    derhab2.setIexubigeo_dom1(iexubigeo_dom1);
                    derhab2.setIextipvia_dom2(iextipvia_dom2);
                    derhab2.setIexnomvia_dom2(iexnomvia_dom2);
                    derhab2.setIexnrovia_dom2(iexnrovia_dom2);
                    derhab2.setIexdeptin_dom2(iexdeptin_dom2);
                    derhab2.setIexinterior_dom2(iexinterior_dom2);
                    derhab2.setIexmanzana_dom2(iexmanzana_dom2);
                    derhab2.setIexlote_dom2(iexlote_dom2);
                    derhab2.setIexkilometro_dom2(iexkilometro_dom2);
                    derhab2.setIexblock_dom2(iexblock_dom2);
                    derhab2.setIexetapa_dom2(iexetapa_dom2);
                    derhab2.setIextipzona_dom2(iextipzona_dom2);
                    derhab2.setIexnomzona_dom2(iexnomzona_dom2);
                    derhab2.setIexreferencia_dom2(iexreferencia_dom2);
                    derhab2.setIexubigeo_dom2(iexubigeo_dom2);
                    derhab2.setIexcenasis(iexcenasis);
                    derhab2.setIexcodlar(iexcodlar);
                    derhab2.setIexnrotelf(iexnrotelf);
                    derhab2.setIexemail(iexemail);
                    derhab2.setIexnacion_origen1(iexnacion_origen1);
                    derhab2.setIexdepart_origen1(iexdepart_origen1);
                    derhab2.setIexprovin_origen1(iexprovin_origen1);
                    derhab2.setIexnacion_origen2(iexnacion_origen2);
                    derhab2.setIexdepart_origen2(iexdepart_origen2);
                    derhab2.setIexprovin_origen2(iexprovin_origen2);

                    derechoHabientesService.insertar(derhab2);

                    return new ModelAndView("redirect:/derechoHab@" + iexcodtra);
                }
            }
        }

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/derechoHabientes/nuevoDerechoHab");
    }
}

