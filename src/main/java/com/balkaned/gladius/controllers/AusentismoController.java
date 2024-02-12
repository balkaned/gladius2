package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.*;
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
public class AusentismoController {
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
    @Autowired
    UsuxCompaniaService usuxCompaniaService;

    @RequestMapping("/ausentismo@{idTrab}")
    public ModelAndView ausentismo(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/ausentismo");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");
        String idusuario = (String) request.getSession().getAttribute("idUser");

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
        model.addAttribute("LstAusentismoDet", ausentismoService.listarAusentismoPrg(emp));

        UsuarioxRol ur = usuxCompaniaService.obtenerRolxUsuario(idCompania, Integer.valueOf(idusuario));
        log.info("ur.getIexdesrol(): "+ur.getIexdesrol());
        log.info("ur.getIexcodTra(): "+ur.getIexcodtra());

        if (ur.getIexdesrol().equals("SYSHRSELF")) {
            return new ModelAndView("public/gladius/organizacion/gestionEmpleado/ausentismo/ausentismoSysHrSelf");
        } else {
            return new ModelAndView("public/gladius/organizacion/gestionEmpleado/ausentismo/ausentismo");
        }
    }

    @RequestMapping("/nuevoAusentismo@{idTrab}")
    public ModelAndView nuevoAusentismo(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/nuevoAusentismo");

        String user = (String) request.getSession().getAttribute("user");

        log.info("user:" + user);
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
        model.addAttribute("lovTipaus", lovsService.getLovs("57", "%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/ausentismo/nuevoAusentismo");
    }

    @RequestMapping("/insertarAusentismo")
    public ModelAndView insertarAusentismo(ModelMap model, HttpServletRequest request) {
        log.info("/insertarAusentismo");

        String user = (String) request.getSession().getAttribute("user");

        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

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
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }

        log.info("sexo: " + sexo);
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
        log.info("codcorrel: " + codcorrel);
        validador = ausentismoService.validaAus(emp.getIexcodcia(), emp.getIexcodtra(), iexfecini, iexfecfin, 0);
        log.info("validador: " + validador);

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
        log.info("/gestionTiempoListAusentismo");

        String user = (String) request.getSession().getAttribute("user");

        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

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
        log.info("/nuevoGestionAusentismo");

        String user = (String) request.getSession().getAttribute("user");

        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");


        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        model.addAttribute("lovTipaus", lovsService.getLovs("57", "%"));

        return new ModelAndView("public/gladius/gestionTiempo/ausentismo/nuevoGestionAusentismo");
    }

    @RequestMapping("/insertarGestionAusentismo")
    public ModelAndView insertarGestionAusentismo(ModelMap model, HttpServletRequest request) {
        log.info("/insertarGestionAusentismo");

        String user = (String) request.getSession().getAttribute("user");

        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }
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

        log.info("codcorrelVAc: " + codcorrel);
        log.info("idCompania: " + idCompania);
        log.info("validador: " + validador);
        log.info("iexfecini: " + request.getParameter("iexfecini"));
        log.info("iexfecini: " + request.getParameter("iexfecfin"));

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
        log.info("/editarGestionAusentismo@{idTrab}@{iexcorrel}");

        String user = (String) request.getSession().getAttribute("user");

        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }
        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        log.info("idTrab: " + idTrab);
        model.addAttribute("idTrab", idTrab);

        log.info("iexcorrel: " + iexcorrel);
        model.addAttribute("iexcorrel", iexcorrel);

        log.info("idCompaniaXXXX: " + idCompania);
        log.info("idTraXXXXXb: " + idTrab);

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
        log.info("/actualizarGestionAusentismo");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        AusentismoProgramacion ausprg = new AusentismoProgramacion();
        Empleado empleado = new Empleado();
        Integer codcorrel = 0;

        ausprg.setIexcodcia(idCompania);
        ausprg.setIexcodtra(Integer.parseInt(request.getParameter("iexcodtra")));
        codcorrel = Integer.parseInt(request.getParameter("iexcorrel"));

        empleado = empleadoService.recuperarCabecera(ausprg.getIexcodcia(), ausprg.getIexcodtra());

        ausprg.setIexcorrel(codcorrel);
        ausprg.setIexfecini(request.getParameter("iexfecini"));
        ausprg.setIexfecfin(request.getParameter("iexfecfin"));
        ausprg.setIextipaus(request.getParameter("iextipaus"));
        ausprg.setIexnrodias(Double.parseDouble(request.getParameter("iexnrodias")));
        ausprg.setIexusucrea("1");

        ausentismoService.actualizarAusentismoPrg(ausprg);

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


    @RequestMapping("/eliminarAusentismo@{idTrab}@{iexcorrel}")
    public ModelAndView eliminarAusentismo(ModelMap model, HttpServletRequest request, @PathVariable String idTrab,
                                           @PathVariable String iexcorrel) {
        log.info("/eliminarAusentismo@{idTrab}@{iexcorrel}");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        log.info("idTrab: " + idTrab);
        model.addAttribute("idTrab", idTrab);

        log.info("iexcorrel: " + iexcorrel);
        model.addAttribute("iexcorrel", iexcorrel);

        log.info("idCompaniaXXXX: " + idCompania);
        log.info("idTraXXXXXb: " + idTrab);

        AusentismoProgramacion ausprg = new AusentismoProgramacion();

        Empleado empleado = new Empleado();

        ausprg.setIexcodcia(idCompania);
        ausprg.setIexcodtra(Integer.valueOf(idTrab));
        ausprg.setIexcorrel(Integer.valueOf(iexcorrel));

        empleado = empleadoService.recuperarCabecera(ausprg.getIexcodcia(), ausprg.getIexcodtra());

        ausentismoService.eliminarAusentismoPrg(ausprg);
        if (request.getParameter("fecini") != null && request.getParameter("iexfecfin") != null) {
            model.addAttribute("LstAusentismoView", ausentismoService.listaAusentismoGen(idCompania, request.getParameter("iexcodreg"), request.getParameter("fecini"), request.getParameter("fecfin"), Integer.parseInt(idTrab)));

        }
        model.addAttribute("LstTrabajadorReg", vacacionesService.listaTrabajadoresReg(idCompania, empleado.getIexreglab()));
        model.addAttribute("iexcodtra", Integer.parseInt(idTrab));
        model.addAttribute("fecini", request.getParameter("fecini"));
        model.addAttribute("fecfin", request.getParameter("fecfin"));
        model.addAttribute("iexcodreg", request.getParameter("iexcodreg"));
        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        model.addAttribute("lovTipaus", lovsService.getLovs("57", "%"));

        return new ModelAndView("public/gladius/gestionTiempo/ausentismo/gestionTiempoListAusentismo");
    }

    @RequestMapping("/editarAusentismo@{idTrab}@{iexcorrel}")
    public ModelAndView editarAusentismo(ModelMap model, HttpServletRequest request,
                                         @PathVariable String idTrab,
                                         @PathVariable String iexcorrel) {
        log.info("/editarAusentismo");

        String user = (String) request.getSession().getAttribute("user");

        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("idTrab: " + idTrab);
        model.addAttribute("idTrab", idTrab);
        model.addAttribute("iexcorrel",iexcorrel);

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
        model.addAttribute("lovTipaus", lovsService.getLovs("57", "%"));

        AusentismoProgramacion ausprg = new AusentismoProgramacion();
        ausprg.setIexcodcia(idCompania);
        ausprg.setIexcodtra(Integer.valueOf(idTrab));
        ausprg.setIexcorrel(Integer.valueOf(iexcorrel));
        model.addAttribute("xAusentismoDet",ausentismoService.getAusentismoPrg(ausprg));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/ausentismo/editarAusentismo");
    }

    @RequestMapping("/modificarAusentismo")
    public ModelAndView modificarAusentismo(ModelMap model, HttpServletRequest request) {
        log.info("/modificarAusentismo");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

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
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }

        log.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        Integer codcorrel = 0;
        Integer validador = 0;
        String Msg_form_global = "";

        String iexfecini = request.getParameter("iexfecini");
        String iexfecfin = request.getParameter("iexfecfin");
        codcorrel = Integer.valueOf(request.getParameter("iexcorrel2"));

        AusentismoProgramacion ausprg = new AusentismoProgramacion();
        ausprg.setIexcodcia(emp.getIexcodcia());
        ausprg.setIexcodtra(emp.getIexcodtra());

        validador = ausentismoService.validaAus(emp.getIexcodcia(), emp.getIexcodtra(), iexfecini, iexfecfin, 0);
        log.info("validador: " + validador);

        if (validador == 0) {
            ausprg.setIexcorrel(codcorrel);
            ausprg.setIexfecini(iexfecini);
            ausprg.setIexfecfin(iexfecfin);
            ausprg.setIextipaus(request.getParameter("iextipaus"));
            ausprg.setIexnrodias(Double.parseDouble(request.getParameter("iexnrodias")));
            ausprg.setIexglosa(request.getParameter("iexglosa"));
            ausprg.setIexusucrea("1");

            ausentismoService.actualizarAusentismoPrg(ausprg);
            Msg_form_global = "OK";
            return new ModelAndView("redirect:/ausentismo@" + iexcodtra);
        } else {
            Msg_form_global = "Error";
            model.addAttribute("idTrab", iexcodtra);
            model.addAttribute("lovTipaus", lovsService.getLovs("57", "%"));
            model.addAttribute("msg", "Error Las fechas se cruzan con programaciones anteriores");

            AusentismoProgramacion ausprg2 = new AusentismoProgramacion();
            ausprg2.setIexcodcia(idCompania);
            ausprg2.setIexcodtra(Integer.valueOf(iexcodtra));
            ausprg2.setIexcorrel(Integer.valueOf(codcorrel));

            model.addAttribute("iexcorrel",codcorrel);
            model.addAttribute("xAusentismoDet",ausentismoService.getAusentismoPrg(ausprg2));
        }

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/ausentismo/editarAusentismo");
    }

    @RequestMapping("/detalleAusentismo@{idTrab}@{iexcorrel}")
    public ModelAndView detalleAusentismo(ModelMap model, HttpServletRequest request,
                                         @PathVariable String idTrab,
                                         @PathVariable String iexcorrel) {
        log.info("/detalleAusentismo");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("idTrab: " + idTrab);
        model.addAttribute("idTrab", idTrab);
        model.addAttribute("iexcorrel",iexcorrel);

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
        model.addAttribute("lovTipaus", lovsService.getLovs("57", "%"));

        AusentismoProgramacion ausprg = new AusentismoProgramacion();
        ausprg.setIexcodcia(idCompania);
        ausprg.setIexcodtra(Integer.valueOf(idTrab));
        ausprg.setIexcorrel(Integer.valueOf(iexcorrel));
        model.addAttribute("xAusentismoDet",ausentismoService.getAusentismoPrg(ausprg));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/ausentismo/detalleAusentismo");
    }

    @RequestMapping("/deleteAusentismo@{idTrab}@{iexcorrel}")
    public ModelAndView deleteAusentismo(ModelMap model, HttpServletRequest request,
                                          @PathVariable String idTrab,
                                          @PathVariable String iexcorrel) {
        log.info("/deleteAusentismo");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        AusentismoProgramacion ausprg = new AusentismoProgramacion();
        ausprg.setIexcodcia(idCompania);
        ausprg.setIexcodtra(Integer.valueOf(idTrab));
        ausprg.setIexcorrel(Integer.valueOf(iexcorrel));

        ausentismoService.eliminarAusentismoPrg(ausprg);

        return new ModelAndView("redirect:/ausentismo@" + idTrab);
    }

}

