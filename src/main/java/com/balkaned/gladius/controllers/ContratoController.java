package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.ContratoEmp;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.UsuarioxRol;
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
import java.util.logging.Logger;

@RestController
@Slf4j
public class ContratoController {
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    LovsService lovsService;

    @Autowired
    ContratoService contratoService;

    @Autowired
    Sessionattributes sessionattributes;

    @Autowired
    UsuxCompaniaService usuxCompaniaService;

    @RequestMapping("/contrato@{idTrab}")
    public ModelAndView contrato(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/contrato");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

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
        model.addAttribute("LstContratoDet", contratoService.listarContratoEmp(emp));

        UsuarioxRol ur = usuxCompaniaService.obtenerRolxUsuario(idCompania, Integer.valueOf(idusuario));
        log.info("ur.getIexdesrol(): "+ur.getIexdesrol());
        log.info("ur.getIexcodTra(): "+ur.getIexcodtra());

        if (ur.getIexdesrol().equals("SYSHRSELF")) {
            return new ModelAndView("public/gladius/organizacion/gestionEmpleado/contrato/contratosSysHrSelf");
        } else {
            return new ModelAndView("public/gladius/organizacion/gestionEmpleado/contrato/contratos");
        }

    }

    @RequestMapping("/nuevoContrato@{idTrab}")
    public ModelAndView nuevoContrato(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/nuevoContrato");

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

        model.addAttribute("lovTipcont", lovsService.getLovs("12", "%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/contrato/nuevoContrato");
    }

    @RequestMapping("/insertarContrato")
    public ModelAndView insertarContrato(ModelMap model, HttpServletRequest request) {
        log.info("/insertarContrato");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer codcorrel = 0;
        String iexcodcia = request.getParameter("iexcodcia");
        String iexcodtra = request.getParameter("iexcodtra");

        ContratoEmp ausprg = new ContratoEmp();

        ausprg.setIexcodcia(Integer.valueOf(iexcodcia));
        ausprg.setIexcodtra(Integer.valueOf(iexcodtra));

        codcorrel = contratoService.getIdContratoEmp(ausprg);

        if (codcorrel > 0) {
            ausprg.setIexcorrel(codcorrel);
            ausprg.setIexfecini(request.getParameter("iexfecini"));
            ausprg.setIexfecfin(request.getParameter("iexfecfin"));
            ausprg.setIextipcont(request.getParameter("iextipcont"));
            ausprg.setIexmodcont(request.getParameter("iexmodcont"));
            ausprg.setIexestado(request.getParameter("iexestado"));
            ausprg.setIexusucrea("1");

            contratoService.insertarContratoEmp(ausprg);
        }

        return new ModelAndView("redirect:/contrato@" + iexcodtra);
    }

    @RequestMapping("/editarContrato@{idTrab}@{iexcorrel}")
    public ModelAndView editarContrato(ModelMap model, HttpServletRequest request,
                                       @PathVariable String idTrab,
                                       @PathVariable String iexcorrel) {
        log.info("/editarContrato");

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

        model.addAttribute("lovTipcont", lovsService.getLovs("12", "%"));

        ContratoEmp contr = new ContratoEmp();
        contr.setIexcodcia(idCompania);
        contr.setIexcodtra(Integer.valueOf(idTrab));
        contr.setIexcorrel(Integer.valueOf(iexcorrel));
        model.addAttribute("xContratoEmp",contratoService.getContratoEmp(contr));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/contrato/editarContrato");
    }

    @RequestMapping("/modificarContrato")
    public ModelAndView modificarContrato(ModelMap model, HttpServletRequest request) {
        log.info("/modificarContrato");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        Integer iexcodtra= Integer.valueOf(request.getParameter("iexcodtra"));

        ContratoEmp contr = new ContratoEmp();
        contr.setIexcodcia(idCompania);
        contr.setIexcodtra(iexcodtra);
        contr.setIexcorrel(Integer.valueOf(request.getParameter("iexcorrel2")));
        contr.setIexfecini(request.getParameter("iexfecini"));
        contr.setIexfecfin(request.getParameter("iexfecfin"));
        contr.setIextipcont(request.getParameter("iextipcont"));
        contr.setIexmodcont(request.getParameter("iexmodcont"));
        contr.setIexestado(request.getParameter("iexestado"));
        contr.setIexusucrea(user);

        contratoService.actualizarContratoEmp(contr);

        return new ModelAndView("redirect:/contrato@" + iexcodtra);
    }

    @RequestMapping("/detalleContrato@{idTrab}@{iexcorrel}")
    public ModelAndView detalleContrato(ModelMap model, HttpServletRequest request,
                                       @PathVariable String idTrab,
                                       @PathVariable String iexcorrel) {
        log.info("/detalleContrato");

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

        model.addAttribute("lovTipcont", lovsService.getLovs("12", "%"));

        ContratoEmp contr = new ContratoEmp();
        contr.setIexcodcia(idCompania);
        contr.setIexcodtra(Integer.valueOf(idTrab));
        contr.setIexcorrel(Integer.valueOf(iexcorrel));
        model.addAttribute("xContratoEmp",contratoService.getContratoEmp(contr));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/contrato/detalleContrato");
    }

    @RequestMapping("/deleteContrato@{idTrab}@{iexcorrel}")
    public ModelAndView deleteContrato(ModelMap model, HttpServletRequest request,
                                        @PathVariable String idTrab,
                                        @PathVariable String iexcorrel) {
        log.info("/deleteContrato");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        ContratoEmp contr = new ContratoEmp();
        contr.setIexcodcia(idCompania);
        contr.setIexcodtra(Integer.valueOf(idTrab));
        contr.setIexcorrel(Integer.valueOf(iexcorrel));

        contratoService.eliminarContratoEmp(contr);

        return new ModelAndView("redirect:/contrato@" + idTrab);
    }
}

