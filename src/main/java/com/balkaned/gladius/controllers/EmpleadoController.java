package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@RestController
@Slf4j
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    LovsService lovsService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listEmpleados")
    public ModelAndView listEmpleados(ModelMap model, HttpServletRequest request) {
        log.info("/listEmpleados");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Empleado emp = new Empleado();
        emp.setIexcodcia(idCompania);

        List<Empleado> empleadoList = empleadoService.listarEmpleado(emp);
        model.addAttribute("empleadoList", empleadoList);

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/listEmpleados");
    }

    @RequestMapping("detalleEmpl@{idTrab}")
    public ModelAndView detalleEmpl(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/detalleEmpl");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        log.info("idCompania: " + idCompania);
        log.info("idTrab: " + idTrab);

        model.addAttribute("idTrab", idTrab);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);

        Empleado emp = empleadoService.recuperarCabecera(idCompania, Integer.parseInt(idTrab));
        log.info("fecha nac: " + emp.getIexfecnac());
        model.addAttribute("fecnacIEX", emp.getIexfecnac());
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

        log.info("estadoFlag: " + emp.getIexflgest());

        String sexo;
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        log.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        log.info("iexlogo: " + emp.getIexlogo());
        model.addAttribute("lovTipdoc", lovsService.getLovs("3", "%"));
        model.addAttribute("lovSexo", lovsService.getLovs("50", "%"));
        model.addAttribute("lovModForm", lovsService.getLovs("18", "%"));
        model.addAttribute("lovPaisEmisor", lovsService.getLovs("26", "%"));
        model.addAttribute("lovNacionalidad", lovsService.getLovs("4", "%"));
        model.addAttribute("lovGrdInstruccion", lovsService.getLovs("9", "%"));
        model.addAttribute("lovCenForm", lovsService.getLovs("51", "%"));
        model.addAttribute("lovEstados", lovsService.getLovs("54", "%"));
        model.addAttribute("lovLarDistancia", lovsService.getLovs("29", "%"));
        model.addAttribute("lovEstcivil", lovsService.getLovs("68", "%"));
        model.addAttribute("lovDept_origen", lovsService.getLovsDept("", emp.getIexpaisemisor()));  // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovProvin_origen", lovsService.getLovsProv("", emp.getIexdepart_origen()));   // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovDist_origen", lovsService.getLovsDist("", emp.getIexprovin_origen()));   // enlita los departamentos que tiene registrado el trabajdorss

        Empleado emp2 = empleadoService.recuperarLaboral(idCompania, Integer.parseInt(idTrab));
        model.addAttribute("emp2", emp2);
        model.addAttribute("lovSitPen", lovsService.getLovs("15", "%"));
        model.addAttribute("lovTipCont", lovsService.getLovs("12", "%"));
        model.addAttribute("lovPliego", lovsService.getLovs("31", "%"));
        model.addAttribute("lovSituesp", lovsService.getLovs("35", "%"));
        model.addAttribute("lovOcupRegPub", lovsService.getLovs("10", "%"));
        model.addAttribute("lovOcupRegPrv", lovsService.getLovs("30", "%"));
        model.addAttribute("lovPuesto", lovsService.getPuestoCia(idCompania));
        model.addAttribute("lovCcosto", lovsService.getCCostoCia(idCompania));
        model.addAttribute("lovArea", lovsService.getAreaCia(idCompania));
        model.addAttribute("lovUbicacion", lovsService.getUbicacionCia(idCompania));
        model.addAttribute("lovCateTra", lovsService.getLovs("24", "%"));
        model.addAttribute("lovRegLab", lovsService.getLovs("33", "%"));
        model.addAttribute("lovTipTra", lovsService.getLovs("8", "%"));

        Empleado emp3 = empleadoService.recuperarPagos(idCompania, Integer.parseInt(idTrab));
        model.addAttribute("emp3", emp3);
        model.addAttribute("lovBancoHab", lovsService.getLovs("36", "%"));
        model.addAttribute("lovMonedaHab", lovsService.getLovs("52", "%"));
        model.addAttribute("lovTipCtaHab", lovsService.getLovs("53", "%"));
        model.addAttribute("lovBancoCts", lovsService.getLovs("36", "%"));
        model.addAttribute("lovMonedaCts", lovsService.getLovs("52", "%"));
        model.addAttribute("lovTipCtaCts", lovsService.getLovs("53", "%"));
        model.addAttribute("lovTipPago", lovsService.getLovs("16", "%"));
        model.addAttribute("lovPerRem", lovsService.getLovs("13", "%"));

        Empleado emp4 = empleadoService.recuperarSegSocial(idCompania, Integer.parseInt(idTrab));
        model.addAttribute("emp4", emp4);
        model.addAttribute("lovEssalud", lovsService.getLovs("32", "%"));
        model.addAttribute("lovProvEps", lovsService.getLovs("14", "%"));
        model.addAttribute("lovTipCenEdu", lovsService.getLovs("51", "%"));
        model.addAttribute("lovCodAfp", lovsService.getLovs("11", "%"));

        Empleado emp5 = empleadoService.recuperarDireccion(idCompania, Integer.parseInt(idTrab));
        model.addAttribute("emp5", emp5);
        model.addAttribute("lovTipVia", lovsService.getLovs("5", "%"));
        model.addAttribute("lovTipZona", lovsService.getLovs("6", "%"));
        model.addAttribute("lovTipVia2", lovsService.getLovs("5", "%"));
        model.addAttribute("lovTipZona2", lovsService.getLovs("6", "%"));
        model.addAttribute("lovDept_origen1", lovsService.getLovsDept("", emp5.getIexnacion_origen1()));  // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovProvin_origen1", lovsService.getLovsProv("", emp5.getIexdepart_origen1()));   // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovDist_origen1", lovsService.getLovsDist("", emp5.getIexprovin_origen1()));   // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovDept_origen2", lovsService.getLovsDept("", emp5.getIexnacion_origen2()));  // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovProvin_origen2", lovsService.getLovsProv("", emp5.getIexdepart_origen2()));   // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovDist_origen2", lovsService.getLovsDist("", emp5.getIexprovin_origen2()));   // enlita los departamentos que tiene registrado el trabajdor

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/datosPersonales/fichaTrabajador");
    }

    @RequestMapping(value = "/updateEmplDatPers", method = RequestMethod.POST)
    public ModelAndView updateEmplDatPers(ModelMap model, @ModelAttribute("empleado") Empleado ep, BindingResult result, SessionStatus status, HttpServletRequest request) {
        log.info("/updateEmplDatPers");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        Empleado p = new Empleado();
        Integer iexcodcia = Integer.valueOf(request.getParameter("iexcodcia"));
        String iexcodtra = request.getParameter("iexcodtra");

        log.info("iexcodcia: " + iexcodcia);
        log.info("iexcodtra: " + iexcodtra);

        String iextipdocid = request.getParameter("iextipdocid");
        String iexnrodoc = request.getParameter("iexnrodoc");
        String iexnomtra = request.getParameter("iexnomtra");
        String iexapepat = request.getParameter("iexapepat");
        String iexapemat = request.getParameter("iexapemat");
        String iexfecnac = request.getParameter("iexfecnac");
        String iexcodsex = request.getParameter("iexcodsex");
        String iextiptra = request.getParameter("iextiptra");
        String iexfecing = request.getParameter("iexfecing");
        String iexmodform = request.getParameter("iexmodform");
        String iexpaisemisor = request.getParameter("iexpaisemisor");
        String iexnacion_origen = request.getParameter("iexnacion_origen");
        String iexdepart_origen = request.getParameter("iexdepart_origen");
        String iexprovin_origen = request.getParameter("iexprovin_origen");
        String iexdistri_origen = request.getParameter("iexdistri_origen");
        String iexgrdinstruccion = request.getParameter("iexgrdinstruccion");
        String iexcentroform = request.getParameter("iexcentroform");
        String iexflgdomicil = request.getParameter("iexflgdomicil");
        String iexcodant = request.getParameter("iexcodant");
        String iexflgest = request.getParameter("iexflgest");
        String iexlardist = request.getParameter("iexcodlardist");
        String iexnrotelf = request.getParameter("iexnrotelf");
        String iexemail = request.getParameter("iexemail");
        String iexemail_coorp = request.getParameter("iexemail_coorp");
        String iexestcivil = request.getParameter("iexestcivil");

        p.setIexcodcia(iexcodcia);
        p.setIexcodtra(Integer.valueOf(iexcodtra));
        p.setIexnomtra(iexnomtra);
        p.setIexapepat(iexapepat);
        p.setIexapemat(iexapemat);
        p.setIextipdocid(iextipdocid);
        p.setIexnrodoc(iexnrodoc);
        p.setIexfecnac(iexfecnac);
        p.setIexfecing(iexfecing);
        p.setIexcodsex(iexcodsex);
        p.setIexpaisemisor(iexpaisemisor);
        p.setIexflgest(iexflgest);
        p.setIexcodant(iexcodant);
        p.setIextiptra(iextiptra);
        p.setIexmodform(iexmodform);
        p.setIexnacion_origen(iexnacion_origen);
        p.setIexdepart_origen(iexdepart_origen);
        p.setIexprovin_origen(iexprovin_origen);
        p.setIexdistri_origen(iexdistri_origen);
        p.setIexcentroform(iexcentroform);
        p.setIexflgdomicil(iexflgdomicil);
        p.setIexusumod(user);
        p.setIexgrdinstruccion(iexgrdinstruccion);
        p.setIexcodlardist(iexlardist);
        p.setIexnrotelf(iexnrotelf);
        p.setIexemail(iexemail);
        p.setIexemail_coorp(iexemail_coorp);
        p.setIexestcivil(iexestcivil);

        empleadoService.actualizarCabecera(p);

        return new ModelAndView("redirect:/detalleEmpl@" + iexcodtra);
    }

    @RequestMapping(value = "/updateEmplDatLab", method = RequestMethod.POST)
    public ModelAndView updateEmplDatLab(ModelMap model, @ModelAttribute("empleado") Empleado ep, BindingResult result, SessionStatus status, HttpServletRequest request) {
        log.info("/updateEmplDatLab");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        Empleado p = new Empleado();
        Integer iexcodcia = Integer.valueOf(request.getParameter("iexcodcia"));
        String iexcodtra = request.getParameter("iexcodtra");

        log.info("iexcodcia: " + iexcodcia);
        log.info("iexcodtra: " + iexcodtra);

        String iextiptra = request.getParameter("iextiptra");
        String iexsituapen = request.getParameter("iexsituapen");
        String iexfecing = request.getParameter("iexfecing");
        String iexfecret = request.getParameter("iexfecret");
        String iextipcont = request.getParameter("iextipcont");
        String iexfecini_cont = request.getParameter("iexfecini_cont");
        String iexfecfin_cont = request.getParameter("iexfecfin_cont");
        String iexpliego = request.getParameter("iexpliego");
        String iexsituaesp = request.getParameter("iexsituaesp");
        String iexocupacion_pub = request.getParameter("iexocupacion_pub");
        String iexocupacion_priv = request.getParameter("iexocupacion_priv");
        String iexarea = request.getParameter("iexarea");
        String iexpuesto = request.getParameter("iexpuesto");
        String iexccosto = request.getParameter("iexccosto");
        String iexlocal = request.getParameter("iexubilocal");
        String iexcateg_trabajador = request.getParameter("iexcateg_trabajador");
        String iexreglab = request.getParameter("iexreglab");

        p.setIexcodcia(iexcodcia);
        p.setIexcodtra(Integer.valueOf(iexcodtra.trim()));
        p.setIextiptra(iextiptra);
        p.setIexsituapen(iexsituapen);
        p.setIexfecing(iexfecing);
        p.setIexfecret(iexfecret);
        p.setIextipcont(iextipcont);
        p.setIexfecini_cont(iexfecini_cont);
        p.setIexfecfin_cont(iexfecfin_cont);
        p.setIexpliego(iexpliego);
        p.setIexsituaesp(iexsituaesp);
        p.setIexocupacion_pub(iexocupacion_pub);
        p.setIexocupacion_priv(iexocupacion_priv);
        p.setIexarea(iexarea);
        p.setIexpuesto(iexpuesto);
        p.setIexccosto(iexccosto);
        p.setIexubilocal(iexlocal);
        p.setIexcateg_trabajador(iexcateg_trabajador);
        p.setIexreglab(iexreglab);
        p.setIexusumod(user);

        empleadoService.actualizarLaboral(p);

        return new ModelAndView("redirect:/detalleEmpl@" + iexcodtra);
    }

    @RequestMapping(value = "/updateInfoPago", method = RequestMethod.POST)
    public ModelAndView updateInfoPago(ModelMap model, @ModelAttribute("empleado") Empleado ep, BindingResult result, SessionStatus status, HttpServletRequest request) {
        log.info("/updateInfoPago");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        Empleado p = new Empleado();
        Integer iexcodcia = Integer.valueOf(request.getParameter("iexcodcia"));
        String iexcodtra = request.getParameter("iexcodtra");

        log.info("iexcodcia: " + iexcodcia);
        log.info("iexcodtra: " + iexcodtra);

        String iextippago = request.getParameter("iextippago");
        String iexperrem = request.getParameter("iexperrem");
        //Double iexmontorem = Double.parseDouble(request.getParameter("iexmontorem"));
        String iexcodban_hab = request.getParameter("iexcodban_hab");
        String iextipban_hab = request.getParameter("iextipban_hab");
        String iexcodmon_hab = request.getParameter("iexcodmon_hab");
        String iexnrocta_hab = request.getParameter("iexnrocta_hab");
        String iexflgbancci_hab = request.getParameter("iexflgbancci_hab");
        String iextipban_cts = request.getParameter("iextipban_cts");
        String iexcodban_cts = request.getParameter("iexcodban_cts");
        String iexcodmon_cts = request.getParameter("iexcodmon_cts");
        String iexflgbancci_cts = request.getParameter("iexflgbancci_cts");
        String iexnrocta_cts = request.getParameter("iexnrocta_cts");

        p.setIexcodcia(iexcodcia);
        p.setIexcodtra(Integer.valueOf(iexcodtra.trim()));
        p.setIextippago(iextippago);
        p.setIexperrem(iexperrem);
        // p.setIexmontorem(iexmontorem);
        p.setIexcodban_hab(iexcodban_hab);
        p.setIexflgbancci_hab(iexflgbancci_hab);
        p.setIexcodmon_hab(iexcodmon_hab);
        p.setIextipban_hab(iextipban_hab);
        p.setIexnrocta_hab(iexnrocta_hab);
        p.setIexcodban_cts(iexcodban_cts);
        p.setIexflgbancci_cts(iexflgbancci_cts);
        p.setIexcodmon_cts(iexcodmon_cts);
        p.setIextipban_cts(iextipban_cts);
        p.setIexnrocta_cts(iexnrocta_cts);
        p.setIexusumod(user);

        empleadoService.actualizarPagos(p);

        return new ModelAndView("redirect:/detalleEmpl@" + iexcodtra);
    }

    @RequestMapping(value = "/updateSegurSocial", method = RequestMethod.POST)
    public ModelAndView updateSegurSocial(ModelMap model, @ModelAttribute("empleado") Empleado ep, BindingResult result, SessionStatus status, HttpServletRequest request) {
        log.info("/updateSegurSocial");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        Empleado p = new Empleado();
        Integer iexcodcia = Integer.valueOf(request.getParameter("iexcodcia"));
        String iexcodtra = request.getParameter("iexcodtra");

        log.info("iexcodcia: " + iexcodcia);
        log.info("iexcodtra: " + iexcodtra);

        String iexcodafp = request.getParameter("iexcodafp");
        String iexflgcomi_mix = request.getParameter("iexflgcomi_mix");
        String iexfecafp = request.getParameter("iexfecafp");
        String iexcussp = request.getParameter("iexcussp");
        String iexessalud = request.getParameter("iexessalud");
        String iexsenati = request.getParameter("iexsenati");
        String iexflgeps = request.getParameter("iexflgeps");
        String iexcodeps = request.getParameter("iexcodeps");
        String iexconvdobtrib = request.getParameter("iexconvdobtrib");
        String iexdiscapacidad = request.getParameter("iexdiscapacidad");
        String iexsctrpension = request.getParameter("iexsctrpension");
        String iexregalter = request.getParameter("iexregalter");
        String iexjornmax = request.getParameter("iexjornmax");
        String iexhornocturno = request.getParameter("iexhornocturno");
        String iexsindicalizado = request.getParameter("iexsindicalizado");
        String iexexon5ta = request.getParameter("iexexon5ta");
        String iexnroruc_cas = request.getParameter("iexnroruc_cas");
        String iexmadreresp = request.getParameter("iexmadreresp");
        String iextipocentoedu = request.getParameter("iextipocentoedu");
        String iexmasvida = request.getParameter("iexflgmas_vida");
        String iexflgjubil = request.getParameter("iexflgjubil");

        p.setIexcodcia(iexcodcia);
        p.setIexcodtra(Integer.parseInt(iexcodtra.trim()));
        p.setIexcodafp(iexcodafp);
        p.setIexflgcomi_mix(iexflgcomi_mix);
        p.setIexfecafp(iexfecafp);
        p.setIexcussp(iexcussp);
        p.setIexessalud(iexessalud);
        p.setIexsenati(iexsenati);
        p.setIexflgeps(iexflgeps);
        p.setIexcodeps(iexcodeps);
        p.setIexconvdobtrib(iexconvdobtrib);
        p.setIexdiscapacidad(iexdiscapacidad);
        p.setIexsctrpension(iexsctrpension);
        p.setIexregalter(iexregalter);
        p.setIexjornmax(iexjornmax);
        p.setIexhornocturno(iexhornocturno);
        p.setIexsindicalizado(iexsindicalizado);
        p.setIexexon5ta(iexexon5ta);
        p.setIexnroruc_cas(iexnroruc_cas);
        p.setIexmadreresp(iexmadreresp);
        p.setIextipocentoedu(iextipocentoedu);
        p.setIexflgmas_vida(iexmasvida);
        p.setIexflgjubil(iexflgjubil);
        p.setIexusumod(user);

        empleadoService.actualizarSegSocial(p);

        return new ModelAndView("redirect:/detalleEmpl@" + iexcodtra);
    }

    @RequestMapping(value = "/updateEmplDatDomic", method = RequestMethod.POST)
    public ModelAndView updateEmplDatDomic(ModelMap model, @ModelAttribute("empleado") Empleado ep, BindingResult result, SessionStatus status, HttpServletRequest request) {
        log.info("/updateEmplDatDomic");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        Empleado p = new Empleado();
        Integer iexcodcia = Integer.valueOf(request.getParameter("iexcodcia"));
        String iexcodtra = request.getParameter("iexcodtra");

        log.info("iexcodcia: " + iexcodcia);
        log.info("iexcodtra: " + iexcodtra);

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
        //String iexubigeo_dom2 = request.getParameter("iexubigeo_dom2");
        String iexflgdomicilio = request.getParameter("iexflgdomicilio");

        String iexnacion_origen1 = request.getParameter("iexnacion_origen1");
        String iexdepart_origen1 = request.getParameter("iexdepart_origen1");
        String iexprovin_origen1 = request.getParameter("iexprovin_origen1");
        //String iexdistri_origen1 = request.getParameter("iexdistri_origen1");

        String iexnacion_origen2 = request.getParameter("iexnacion_origen2");
        String iexdepart_origen2 = request.getParameter("iexdepart_origen2");
        String iexprovin_origen2 = request.getParameter("iexprovin_origen2");
        //String iexdistri_origen2 = request.getParameter("iexdistri_origen2");

        p.setIexcodcia(iexcodcia);
        p.setIexcodtra(Integer.parseInt(iexcodtra.trim()));
        p.setIextipvia_dom1(iextipvia_dom1);
        p.setIexnomvia_dom1(iexnomvia_dom1);
        p.setIexnrovia_dom1(iexnrovia_dom1);
        p.setIexdeptin_dom1(iexdeptin_dom1);
        p.setIexinterior_dom1(iexinterior_dom1);
        p.setIexmanzana_dom1(iexmanzana_dom1);
        p.setIexlote_dom1(iexlote_dom1);
        p.setIexkilometro_dom1(iexkilometro_dom1);
        p.setIexblock_dom1(iexblock_dom1);
        p.setIexetapa_dom1(iexetapa_dom1);
        p.setIextipzona_dom1(iextipzona_dom1);
        p.setIexnomzona_dom1(iexnomzona_dom1);
        p.setIexreferencia_dom1(iexreferencia_dom1);
        p.setIexubigeo_dom1(iexubigeo_dom1);
        p.setIextipvia_dom2(iextipvia_dom2);
        p.setIexnomvia_dom2(iexnomvia_dom2);
        p.setIexnrovia_dom2(iexnrovia_dom2);
        p.setIexdeptin_dom2(iexdeptin_dom2);
        p.setIexinterior_dom2(iexinterior_dom2);
        p.setIexmanzana_dom2(iexmanzana_dom2);
        p.setIexlote_dom2(iexlote_dom2);
        p.setIexkilometro_dom2(iexkilometro_dom2);
        p.setIexblock_dom2(iexblock_dom2);
        p.setIexetapa_dom2(iexetapa_dom2);
        p.setIextipzona_dom2(iextipzona_dom2);
        p.setIexnomzona_dom2(iexnomzona_dom2);
        p.setIexreferencia_dom2(iexreferencia_dom2);
        //p.setIexubigeo_dom2(iexubigeo_dom2);
        p.setIexflgdomicilio(iexflgdomicilio);
        p.setIexusumod(user);

        p.setIexnacion_origen1(iexnacion_origen1);
        p.setIexdepart_origen1(iexdepart_origen1);
        p.setIexprovin_origen1(iexprovin_origen1);
        p.setIexnacion_origen2(iexnacion_origen2);
        p.setIexdepart_origen2(iexdepart_origen2);
        p.setIexprovin_origen2(iexprovin_origen2);

        empleadoService.actualizarDireccion(p);

        return new ModelAndView("redirect:/detalleEmpl@" + iexcodtra);
    }

    @RequestMapping("/valRegEmpleado")
    public ModelAndView valRegEmpleado(ModelMap model, HttpServletRequest request) {
        log.info("/valRegEmpleado");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/valRegEmpleado");
    }

    @RequestMapping("/validarNroDoc")
    public ModelAndView validarNroDoc(ModelMap model, HttpServletRequest request) {
        log.info("/validarNroDoc");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iextipdocid = request.getParameter("iextipdocid");
        String iexnrodocid = request.getParameter("iexnrodocid");

        String resultado = "";
        String Msg_form_global = "";

        Empleado empleado = new Empleado();
        empleado.setIexcodcia(idCompania);
        empleado.setIextipdocid(iextipdocid);
        empleado.setIexnrodoc(iexnrodocid);

        List<Empleado> lista = empleadoService.validarCabecera(empleado);

        Integer val = 0;
        model.addAttribute("LstPerRegistrada", lista);

        Iterator<Empleado> it = lista.iterator();
        // mientras al iterador queda proximo juego
        while (it.hasNext()) {
            val++;
            it.next();
        }

        if (val == 0) {
            Msg_form_global = "OK"; //Verifica si la lista esta llena. De esta vacio significa que el registro a validar seria nuevo
        } else {
            Msg_form_global = "Error";
            model.addAttribute("msg", "Existen registros activos con el mismo numero de documento. Contactar con el Administrador");
        }

        log.info("ValidadEmpleado --> Mensaje global:" + Msg_form_global + " , cantidad de items=" + val);

        if (Msg_form_global == "OK") {
            return new ModelAndView("redirect:/nuevoEmpleado");
        }

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/valRegEmpleado");
    }

    @RequestMapping("/nuevoEmpleado")
    public ModelAndView nuevoEmpleado(ModelMap model, HttpServletRequest request) {
        log.info("/nuevoEmpleado");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        model.addAttribute("lovTipdoc", lovsService.getLovs("3", "%"));
        model.addAttribute("lovSexo", lovsService.getLovs("50", "%"));
        model.addAttribute("lovTipTra", lovsService.getLovs("8", "%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/nuevoEmpleado");
    }

    @RequestMapping("/insertarEmpleado")
    public ModelAndView insertarEmpleado(ModelMap model, HttpServletRequest request) {
        log.info("/insertarEmpleado");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        Integer idempleado = 0;
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iextipdocid = request.getParameter("iextipdocid");
        String iexnrodocid = request.getParameter("iexnrodocid");
        String iexnomtra = request.getParameter("iexnomtra");
        String iexapepat = request.getParameter("iexapepat");
        String iexapemat = request.getParameter("iexapemat");
        String iexfecnac = request.getParameter("iexfecnac");
        String iexcodsex = request.getParameter("iexcodsex");
        String iextiptra = request.getParameter("iextiptra");
        String iexfecing = request.getParameter("iexfecing");
        String iexcodant = request.getParameter("iexcodant");

        Empleado emp = new Empleado();
        emp.setIexcodcia(idCompania);
        emp.setIexnomtra(iexnomtra);
        emp.setIexapepat(iexapepat);
        emp.setIexapemat(iexapemat);
        emp.setIextipdocid(iextipdocid);
        emp.setIexnrodoc(iexnrodocid);
        emp.setIexfecnac(iexfecnac);
        emp.setIexcodsex(iexcodsex);
        emp.setIextiptra(iextiptra);
        emp.setIexfecing(iexfecing);
        emp.setIexcodant(iexcodant);
        emp.setIexusucrea(usuario);

        idempleado = empleadoService.obtieneIdEmpleado(emp);
        log.info("Obtiene Id empleado :" + idempleado);

        if (idempleado > 0) {
            emp.setIexcodtra(idempleado);
            empleadoService.insertarCabecera(emp);

            //id_new_emp=  idempleado;
        } else {
            idempleado = 0;
        }

        return new ModelAndView("redirect:/detalleEmpl@" + idempleado);
    }

    @RequestMapping("/reingresoEmpleado")
    public ModelAndView reingresoEmpleado(ModelMap model, HttpServletRequest request) {
        log.info("/reingresoEmpleado");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("LstEmpleadoInactivo", empleadoService.listarEmpleadoInactivos(idCompania));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/reingresoEmpleado");
    }

    @RequestMapping("/procesarEmpleadoInactivo")
    public ModelAndView procesarEmpleadoInactivo(ModelMap model, HttpServletRequest request) {
        log.info("/procesarEmpleadoInactivo");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String accion = request.getParameter("accion");
        String idTrab = request.getParameter("iexempid");

        if (accion.equals("SELPIN2")) {
            model.addAttribute("iexempid", idTrab);
            model.addAttribute("LstEmpleadoInactivo", empleadoService.listarEmpleadoInactivos(idCompania));
            model.addAttribute("xtrainactivo", empleadoService.recuperarCabecera(idCompania, Integer.valueOf(idTrab)));
        }

        if (accion.equals("REING")) {
            String fecing = request.getParameter("fechaing");

            Empleado emp = new Empleado();
            emp.setIexcodcia(idCompania);
            Integer codtranuevo = empleadoService.obtieneIdEmpleado(emp);
            empleadoService.reingresarEmpleado(idCompania, Integer.valueOf(idTrab), fecing, usuario, codtranuevo);

            return new ModelAndView("redirect:/empleadoReactivado@" + codtranuevo);
        }

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/reingresoEmpleado");
    }

    @RequestMapping("/empleadoReactivado@{idNewTrab}")
    public ModelAndView empleadoReactivado(ModelMap model, HttpServletRequest request, @PathVariable String idNewTrab) {
        log.info("/empleadoReactivado");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("xtrabnuevo", empleadoService.recuperarCabecera(idCompania, Integer.valueOf(idNewTrab)));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/empleadoReactivado");
    }
}

