package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.EmpAcum;
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
public class AcumuladoController {
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    AcumuladoService acumuladoService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/acumulado@{idTrab}")
    public ModelAndView acumulado(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/acumulado");

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

        model.addAttribute("LstAcumEmp", acumuladoService.listarEmpAcum(emp.getIexcodcia(), emp.getIexcodtra()));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/acumulado/acumulados");
    }

    @RequestMapping("/nuevoAcumulado@{idTrab}")
    public ModelAndView nuevoAcumulado(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/nuevoAcumulado");

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

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/acumulado/nuevoAcumulado");
    }

    @RequestMapping("/insertarAcumulado")
    public ModelAndView insertarAcumulado(ModelMap model, HttpServletRequest request) {
        log.info("/insertarAcumulado");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iexcodcia = request.getParameter("iexcodcia");
        String iexcodtra = request.getParameter("iexcodtra");

        EmpAcum empacum = new EmpAcum();

        empacum.setIexcodcia(Integer.valueOf(iexcodcia));
        empacum.setIexcodtra(Integer.valueOf(iexcodtra));
        empacum.setIexaniotrib(request.getParameter("iexaniotrib"));
        empacum.setIexrem_acum(Double.parseDouble(request.getParameter("iexrem_acum")));
        empacum.setIexrem5taafec_acum(Double.parseDouble(request.getParameter("iexrem5taafec_acum")));
        empacum.setIexrenta5ta_acum(Double.parseDouble(request.getParameter("iexrenta5ta_acum")));
        empacum.setIexremafec5ta_otrcia(Double.parseDouble(request.getParameter("iexremafec5ta_otrcia")));
        empacum.setIexrent5ta_otrcia(Double.parseDouble(request.getParameter("iexrent5ta_otrcia")));
        empacum.setIexrem4ta_acum(Double.parseDouble(request.getParameter("iexrem4ta_acum")));
        empacum.setIexrenta4ta_acum(Double.parseDouble(request.getParameter("iexrenta4ta_acum")));
        empacum.setIexremotr_acum(Double.parseDouble(request.getParameter("iexremotr_acum")));
        empacum.setIexrenta_acum(Double.parseDouble(request.getParameter("iexrenta_acum")));
        empacum.setIexusucrea(user);

        acumuladoService.insertarEmpAcum(empacum);

        return new ModelAndView("redirect:/acumulado@" + iexcodtra);
    }
}

