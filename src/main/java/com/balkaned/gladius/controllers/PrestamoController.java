package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.PrestamoCab;
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
public class PrestamoController {
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    LovsService lovsService;

    @Autowired
    PrestamoService prestamoService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/prestamos@{idTrab}")
    public ModelAndView prestamos(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/prestamos");

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

        model.addAttribute("LstPrestCab", prestamoService.listarPrestamoCab(emp));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/prestamos/prestamos");
    }

    @RequestMapping("/nuevoPrestamo@{idTrab}")
    public ModelAndView nuevoPrestamo(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/nuevoPrestamo");

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

        model.addAttribute("lovTippres", lovsService.getLovs("59", "%"));
        model.addAttribute("lovTipInteres", lovsService.getLovs("60", "%"));
        model.addAttribute("lovFrecPrestamo", lovsService.getLovs("61", "%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/prestamos/nuevoPrestamo");
    }

    @RequestMapping("/insertarPrestamo")
    public ModelAndView insertarPrestamo(ModelMap model, HttpServletRequest request) {
        log.info("/insertarPrestamo");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer idcab = 0;
        String iexcodcia = request.getParameter("iexcodcia");
        String iexcodtra = request.getParameter("iexcodtra");

        PrestamoCab prestcab = new PrestamoCab();
        prestcab.setIexcodcia(Integer.valueOf(iexcodcia));
        prestcab.setIexcodtra(Integer.valueOf(iexcodtra));

        idcab = prestamoService.getIdPrestamoCab(prestcab);
        log.info("idcab: " + idcab);

        prestcab.setIexcorrel(idcab);
        prestcab.setIextippres(request.getParameter("iextipprestamo"));
        prestcab.setIextipinteres(request.getParameter("iextipinteres"));
        prestcab.setIexfrecuencia(request.getParameter("iexfrecuencia"));
        prestcab.setIexfecpres(request.getParameter("iexfecpres"));
        prestcab.setIexfecinivig(request.getParameter("iexfecinivig"));
        prestcab.setIexnrocuotas(Double.parseDouble(request.getParameter("iexnrocuota")));
        prestcab.setIeximpbru(Double.parseDouble(request.getParameter("ieximpbruto")));
        prestcab.setIexinteres(Double.parseDouble(request.getParameter("iexinteres")));
        prestcab.setIeximptotal(Double.parseDouble(request.getParameter("ieximptotal")));
        prestcab.setIexglosa(request.getParameter("iexglosa"));
        prestcab.setIexestado("1");
        prestcab.setIexusucrea("1");

        prestamoService.insertarPrestamoCab(prestcab);

        return new ModelAndView("redirect:/prestamos@" + iexcodtra);
    }

    @RequestMapping("/detalleCron@{idTrab}@{iexcorrel}")
    public ModelAndView detalleCron(ModelMap model, HttpServletRequest request,
                                    @PathVariable String idTrab,
                                    @PathVariable String iexcorrel) {
        log.info("/detalleCron");

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
        model.addAttribute("lovTippres", lovsService.getLovs("59", "%"));
        model.addAttribute("lovTipInteres", lovsService.getLovs("60", "%"));
        model.addAttribute("lovFrecPrestamo", lovsService.getLovs("61", "%"));

        PrestamoCab prestcab = new PrestamoCab();
        prestcab.setIexcodcia(idCompania);
        prestcab.setIexcodtra(Integer.valueOf(idTrab));
        prestcab.setIexcorrel(Integer.valueOf(iexcorrel));

        model.addAttribute("xPrestCab", prestamoService.getPrestamoCab(prestcab));
        model.addAttribute("xPrestDet", prestamoService.listarPrestamoDet(prestcab));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/prestamos/detallePrestamo");
    }

}

