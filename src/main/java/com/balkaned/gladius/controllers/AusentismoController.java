package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController
public class AusentismoController {
    static Logger logger = Logger.getLogger(AusentismoController.class.getName());
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    LovsService lovsService;

    @Autowired
    VacacionesService vacacionesService;
    @Autowired
    AusentismoService ausentismoService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/ausentismo@{idTrab}")
    public ModelAndView ausentismo(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        logger.info("/ausentismo");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("idTrab: " + idTrab);
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
        logger.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        logger.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        model.addAttribute("LstAusentismoDet", ausentismoService.listarAusentismoPrg(emp));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/ausentismo/ausentismo");
    }

    @RequestMapping("/nuevoAusentismo@{idTrab}")
    public ModelAndView nuevoAusentismo(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        logger.info("/nuevoAusentismo");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("idTrab: " + idTrab);
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
        logger.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        logger.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        model.addAttribute("lovTipaus", lovsService.getLovs("57", "%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/ausentismo/nuevoAusentismo");
    }

    @RequestMapping("/insertarAusentismo")
    public ModelAndView insertarAusentismo(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarAusentismo");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        String iexcodtra = request.getParameter("iexcodtra");

        Empleado emp = empleadoService.recuperarCabecera(idCompania, Integer.parseInt(iexcodtra));
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
        logger.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        logger.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        Integer codcorrel = 0;
        Integer validador = 0;
        String Msg_form_global = "";

        String iexfecini = request.getParameter("iexfecini");
        String iexfecfin = request.getParameter("iexfecfin");

        AusentismoProgramacion ausprg = new AusentismoProgramacion();
        ausprg.setIexcodcia(emp.getIexcodcia());
        ausprg.setIexcodtra(emp.getIexcodtra());

        codcorrel = ausentismoService.getIdAusentismoPrg(ausprg);
        logger.info("codcorrel: " + codcorrel);
        validador = ausentismoService.validaAus(emp.getIexcodcia(), emp.getIexcodtra(), iexfecini, iexfecfin, 0);
        logger.info("validador: " + validador);

        if (validador == 0) {
            //if(codcorrel >0) {
            ausprg.setIexcorrel(codcorrel);
            ausprg.setIexfecini(iexfecini);
            ausprg.setIexfecfin(iexfecfin);
            ausprg.setIextipaus(request.getParameter("iextipaus"));
            ausprg.setIexnrodias(Double.parseDouble(request.getParameter("iexnrodias")));
            ausprg.setIexglosa(request.getParameter("iexglosa"));
            ausprg.setIexusucrea("1");

            ausentismoService.insertarAusentismoPrg(ausprg);
            Msg_form_global = "OK";
            return new ModelAndView("redirect:/ausentismo@" + iexcodtra);
            //}
        } else {
            Msg_form_global = "Error";
            model.addAttribute("idTrab", iexcodtra);
            model.addAttribute("lovTipaus", lovsService.getLovs("57", "%"));
            model.addAttribute("msg", "Error Las fechas se cruzan con programaciones anteriores");
        }

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/ausentismo/nuevoAusentismo");
    }


    @RequestMapping("/gestionTiempoListAusentismo")
    public ModelAndView gestionTiempoListAusentismo(ModelMap model, HttpServletRequest request) {
        logger.info("/gestionTiempoListAusentismo");
        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        String regimen = request.getParameter("iexcodreg");
        String fecini = request.getParameter("fecini");
        String fecfin = request.getParameter("fecfin");
        String codtra = request.getParameter("iexcodtra");

        Integer xcodtra = 0;

        if (codtra == null || codtra.equals("")) {
            xcodtra = 0;
        } else {
            xcodtra = Integer.parseInt(codtra);
        }

        model.addAttribute("fecini", fecini);
        model.addAttribute("fecfin", fecfin);
        model.addAttribute("iexcodreg", regimen);
        model.addAttribute("iexcodtra", codtra);

        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        if (fecini != null && fecfin != null) {
            model.addAttribute("LstAusentismoView", ausentismoService.listaAusentismoGen(idCompania, regimen, fecini, fecfin, xcodtra));
        }
        model.addAttribute("LstTrabajadorReg", vacacionesService.listaTrabajadoresReg(idCompania, regimen));
        model.addAttribute("iexcodtra", xcodtra);
        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        return new ModelAndView("public/gladius/gestionTiempo/ausentismo/gestionTiempoListAusentismo");
    }


    @RequestMapping("/nuevoGestionAusentismo")
    public ModelAndView nuevoGestionAusentismo(ModelMap model, HttpServletRequest request) {
        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");


        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        model.addAttribute("lovTipaus", lovsService.getLovs("57", "%"));

        return new ModelAndView("public/gladius/gestionTiempo/ausentismo/nuevoGestionAusentismo");
    }

    @RequestMapping("/insertarGestionAusentismo")
    public ModelAndView insertarGestionAusentismo(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarGestionAusentismo");
        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        Empleado empleado = new Empleado();
        Integer codcorrel = 0;
        Integer validador = 0;
        String Msg_form_global = "";

        AusentismoProgramacion ausprg = new AusentismoProgramacion();
        ausprg.setIexcodcia(idCompania);
        ausprg.setIexcodtra(Integer.parseInt(request.getParameter("iexcodtra")));

        codcorrel = ausentismoService.getIdAusentismoPrg(ausprg);
        validador = ausentismoService.validaAus(empleado.getIexcodcia(), empleado.getIexcodtra(), request.getParameter("iexfecini"), request.getParameter("iexfecfin"), 0);

        logger.info("codcorrelVAc: " + codcorrel);
        logger.info("idCompania: " + idCompania);
        logger.info("validador: " + validador);
        logger.info("iexfecini: " + request.getParameter("iexfecini"));
        logger.info("iexfecini: " + request.getParameter("iexfecfin"));

        if (validador == 0) {

            if (codcorrel > 0) {
                ausprg.setIexcorrel(codcorrel);
                ausprg.setIexfecini(request.getParameter("iexfecini"));
                ausprg.setIexfecfin(request.getParameter("iexfecfin"));
                ausprg.setIextipaus(request.getParameter("iextipaus"));
                ausprg.setIexnrodias(Double.parseDouble(request.getParameter("iexnrodias")));
                ausprg.setIexglosa(request.getParameter("iexglosa"));
                ausprg.setIexusucrea("1");

                ausentismoService.insertarAusentismoPrg(ausprg);
                Msg_form_global = "OK";
            }

        } else {

            Msg_form_global = "Error";
        }


        model.addAttribute("LstAusentismoView", ausentismoService.listaAusentismoGen(idCompania, request.getParameter("iexcodreg"), ausprg.getIexfecini(), ausprg.getIexfecfin(), Integer.parseInt(request.getParameter("iexcodtra"))));
        model.addAttribute("LstTrabajadorReg", vacacionesService.listaTrabajadoresReg(idCompania, request.getParameter("iexcodreg")));
        model.addAttribute("iexcodtra", Integer.parseInt(request.getParameter("iexcodtra")));
        model.addAttribute("fecini", ausprg.getIexfecini());
        model.addAttribute("fecfin", ausprg.getIexfecfin());
        model.addAttribute("iexcodreg", request.getParameter("iexcodreg"));
        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        model.addAttribute("lovTipaus", lovsService.getLovs("57", "%"));

        return new ModelAndView("redirect:/gestionTiempoListAusentismo");
    }

    @RequestMapping("/editarGestionAusentismo@{idTrab}@{iexcorrel}")
    public ModelAndView editarGestionAusentismo(ModelMap model, HttpServletRequest request, @PathVariable String idTrab,
                                                @PathVariable String iexcorrel) {
        logger.info("/editarGestionAusentismo@{idTrab}@{iexcorrel}");


        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        logger.info("idTrab: " + idTrab);
        model.addAttribute("idTrab", idTrab);

        logger.info("iexcorrel: " + iexcorrel);
        model.addAttribute("iexcorrel", iexcorrel);

        logger.info("idCompaniaXXXX: " + idCompania);
        logger.info("idTraXXXXXb: " + idTrab);


        AusentismoProgramacion ausprg = new AusentismoProgramacion();
        ausprg.setIexcodcia(idCompania);
        ausprg.setIexcodtra(Integer.valueOf(idTrab));
        ausprg.setIexcorrel(Integer.valueOf(iexcorrel));

        Integer saldo2 = 0;
        Empleado empleado2 = new Empleado();
        empleado2 = empleadoService.recuperarCabecera(ausprg.getIexcodcia(), ausprg.getIexcodtra());


        model.addAttribute("iexcodreg", empleado2.getIexreglab());
        model.addAttribute("xAusentismoDet", ausentismoService.getAusentismoPrg(ausprg));
        model.addAttribute("LstTrabajadorReg", vacacionesService.listaTrabajadoresReg(idCompania, empleado2.getIexreglab()));
        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        model.addAttribute("lovTipaus", lovsService.getLovs("57", "%"));

        return new ModelAndView("public/gladius/gestionTiempo/ausentismo/actualizarGestionAusentismo");
    }

    @RequestMapping("/actualizarGestionAusentismo")
    public ModelAndView actualizarGestionAusentismo(ModelMap model, HttpServletRequest request) {
        logger.info("/actualizarGestionAusentismo");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        AusentismoProgramacion ausprg = new AusentismoProgramacion();
        Empleado empleado = new Empleado();
        Integer codcorrel = 0;

        ausprg.setIexcodcia(idCompania);
        ausprg.setIexcodtra(Integer.parseInt(request.getParameter("iexcodtra")));
        codcorrel = Integer.parseInt(request.getParameter("iexcorrel"));

        empleado = empleadoService.recuperarCabecera(ausprg.getIexcodcia(),ausprg.getIexcodtra());

        ausprg.setIexcorrel(codcorrel);
        ausprg.setIexfecini(request.getParameter("iexfecini"));
        ausprg.setIexfecfin(request.getParameter("iexfecfin"));
        ausprg.setIextipaus( request.getParameter("iextipaus") );
        ausprg.setIexnrodias(Double.parseDouble(request.getParameter("iexnrodias")));
        ausprg.setIexusucrea("1");

        ausentismoService.actualizarAusentismoPrg(ausprg);

        model.addAttribute("LstAusentismoView",ausentismoService.listaAusentismoGen(idCompania,  request.getParameter("iexcodreg") ,ausprg.getIexfecini(), ausprg.getIexfecfin() ,Integer.parseInt(request.getParameter("iexcodtra"))) );
        model.addAttribute("LstTrabajadorReg",vacacionesService.listaTrabajadoresReg(idCompania, request.getParameter("iexcodreg")));
        model.addAttribute("iexcodtra", Integer.parseInt(request.getParameter("iexcodtra")));
        model.addAttribute("fecini",ausprg.getIexfecini());
        model.addAttribute("fecfin",ausprg.getIexfecfin());
        model.addAttribute("iexcodreg", request.getParameter("iexcodreg") );
        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        model.addAttribute("lovTipaus",lovsService.getLovs("57","%"));

        return new ModelAndView("redirect:/gestionTiempoListAusentismo");
    }



}

