package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.EmpDatvar;
import com.balkaned.gladius.beans.EmpSueldo;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.UsuarioxRol;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class SueldosController {
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    LovsService lovsService;

    @Autowired
    SueldoService sueldoService;

    @Autowired
    Sessionattributes sessionattributes;

    @Autowired
    UsuxCompaniaService usuxCompaniaService;

    @RequestMapping("/sueldoFijo@{idTrab}")
    public ModelAndView sueldoFijo(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/sueldoFijo");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
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

        String sexo;
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        log.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        empleado.setIexcodcia(idCompania);
        empleado.setIexcodtra(Integer.valueOf(idTrab));

        model.addAttribute("fsueldox", sueldoService.obtenerEmpSueldo(empleado));

        UsuarioxRol ur = usuxCompaniaService.obtenerRolxUsuario(idCompania, Integer.valueOf(idusuario));
        log.info("ur.getIexdesrol(): "+ur.getIexdesrol());
        log.info("ur.getIexcodTra(): "+ur.getIexcodtra());

        if (ur.getIexdesrol().equals("SYSHRSELF")) {
            return new ModelAndView("public/gladius/organizacion/gestionEmpleado/sueldosFijos/sueldoFijoSysHrSelf");
        } else {
            return new ModelAndView("public/gladius/organizacion/gestionEmpleado/sueldosFijos/sueldoFijo");
        }
    }

    @RequestMapping("/nuevoSueldoFijo@{idTrab}")
    public ModelAndView nuevoSueldoFijo(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/nuevoSueldoFijo");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

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
        model.addAttribute("fsueldox", sueldoService.obtenerEmpSueldo(empleado));
        model.addAttribute("lovConcepSue", sueldoService.ListConceptos(idCompania, "1"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/sueldosFijos/nuevoSueldoFijo");
    }

    @RequestMapping("/insertarSueldoFijo")
    public ModelAndView insertarSueldoFijo(ModelMap model, HttpServletRequest request) {
        log.info("/insertarSueldoFijo");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        Integer idempleado = 0;

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String concepto = request.getParameter("iexcodcon");
        Integer iexcodcia = Integer.valueOf(request.getParameter("iexcodcia"));
        Integer iexcodtra = Integer.valueOf(request.getParameter("iexcodtra"));
        Double valcon = 0.0;

        if (request.getParameter("iexvalcon") == null) {
            valcon = 0.0;
        } else {
            valcon = Double.parseDouble(request.getParameter("iexvalcon"));
        }

        EmpSueldo sueldo = new EmpSueldo();
        sueldo.setIexcodcia(iexcodcia);
        sueldo.setIexcodtra(iexcodtra);
        sueldo.setIexcodcon(concepto);
        sueldo.setIexvalcon(valcon);
        sueldo.setIexflgest("1");

        sueldoService.insertarEmpSueldo(sueldo);

        return new ModelAndView("redirect:/sueldoFijo@" + iexcodtra);
    }

    @RequestMapping("/sueldoVariable@{idTrab}")
    public ModelAndView sueldoVariable(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/sueldoVariable");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

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
        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/sueldosVariables/sueldoVariable");
    }

    @RequestMapping("/verDataSueldoVar@{idTrab}")
    public ModelAndView verDataSueldoVar(ModelMap model, HttpServletRequest request,
                                         @PathVariable String idTrab) {
        log.info("/verDataSueldoVar");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");
        String idusuario = (String) request.getSession().getAttribute("idUser");

        model.addAttribute("idTrab", idTrab);

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
        model.addAttribute("iexlogo", emp.getIexlogo());
        model.addAttribute("urlLogo", urlLogo);

        String iexcodpro = request.getParameter("iexcodpro");
        String iexperiodo = request.getParameter("iexperiodo");
        String iexcodtra = request.getParameter("iexcodtra");

        String sexo;
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        log.info("sexo: " + sexo);

        model.addAttribute("sexo", sexo);
        model.addAttribute("iexcodpro", iexcodpro);
        model.addAttribute("iexperiodo", iexperiodo);
        model.addAttribute("fdatavar", sueldoService.obtenerEmpDatvar(idCompania, Integer.valueOf(iexcodpro), iexperiodo, Integer.valueOf(idTrab), 1));

        UsuarioxRol ur = usuxCompaniaService.obtenerRolxUsuario(idCompania, Integer.valueOf(idusuario));
        log.info("ur.getIexdesrol(): "+ur.getIexdesrol());
        log.info("ur.getIexcodTra(): "+ur.getIexcodtra());

        if (ur.getIexdesrol().equals("SYSHRSELF")) {
            return new ModelAndView("public/gladius/organizacion/gestionEmpleado/sueldosVariables/verDataSueldoVarSysHrSelf");
        } else {
            return new ModelAndView("public/gladius/organizacion/gestionEmpleado/sueldosVariables/verDataSueldoVar");
        }
    }

    @RequestMapping("/verDataSueldoVarBack@{idTrab}@{codpro}@{periodo}")
    public ModelAndView verDataSueldoVarBack(ModelMap model, HttpServletRequest request,
                                             @PathVariable String idTrab,
                                             @PathVariable String codpro,
                                             @PathVariable String periodo) {
        log.info("/verDataSueldoVarBack");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        model.addAttribute("idTrab", idTrab);

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
        model.addAttribute("iexlogo", emp.getIexlogo());
        model.addAttribute("urlLogo", urlLogo);

        String iexcodpro = codpro;
        String iexperiodo = periodo;
        String iexcodtra = idTrab;

        String sexo;
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        log.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);
        model.addAttribute("iexcodpro", iexcodpro);
        model.addAttribute("iexperiodo", iexperiodo);
        model.addAttribute("fdatavar", sueldoService.obtenerEmpDatvar(idCompania, Integer.valueOf(iexcodpro), iexperiodo, Integer.valueOf(iexcodtra), 1));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/sueldosVariables/verDataSueldoVar");
    }


    @RequestMapping("/nuevoSueldoVar@{idTrab}@{codpro}@{nroper}")
    public ModelAndView nuevoSueldoVar(ModelMap model, HttpServletRequest request,
                                       @PathVariable String idTrab,
                                       @PathVariable String codpro,
                                       @PathVariable String nroper) {
        log.info("/nuevoSueldoVar");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        model.addAttribute("idTrab", idTrab);

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

        String concepto2 = request.getParameter("iexcodcon");
        model.addAttribute("lovConcepVar", sueldoService.ListConceptos(idCompania, "2"));
        request.setAttribute("iexcodpro", codpro);
        request.setAttribute("iexperiodo", nroper);

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/sueldosVariables/nuevoSueldoVar");
    }

    @RequestMapping("/insertarConceptoVar")
    public ModelAndView insertarConceptoVar(ModelMap model, HttpServletRequest request) {
        log.info("/insertarConceptoVar");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);

        Integer iexcodcia = Integer.valueOf(request.getParameter("iexcodcia"));
        Integer iexcodtra = Integer.valueOf(request.getParameter("iexcodtra"));
        Integer v_codpro = Integer.valueOf(request.getParameter("iexcodpro2"));
        String periodo = request.getParameter("iexperiodo2");
        String concepto = request.getParameter("iexcodcon");
        Double valcon = 0.0;

        if (request.getParameter("iexvalcon") == null) {
            valcon = 0.0;
        } else {
            valcon = Double.parseDouble(request.getParameter("iexvalcon"));
        }
        ;

        EmpDatvar Datvar = new EmpDatvar();
        Datvar.setIexcodcia(iexcodcia);
        Datvar.setIexcodtra(iexcodtra);
        Datvar.setIexcodpro(v_codpro);
        Datvar.setIexnroper(periodo);
        Datvar.setIexcodcon(concepto);
        Datvar.setIexvalcon(valcon);
        Datvar.setIexflgest("1");
        Datvar.setIexcorrel(1);

        sueldoService.insertarEmpDatvar(Datvar);

        return new ModelAndView("redirect:/verDataSueldoVarBack@" + iexcodtra + "@" + v_codpro + "@" + periodo);
    }
}

