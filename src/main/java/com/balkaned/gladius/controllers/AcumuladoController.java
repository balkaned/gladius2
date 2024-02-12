package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.EmpAcum;
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

@RestController
@Slf4j
public class AcumuladoController {
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    AcumuladoService acumuladoService;

    @Autowired
    Sessionattributes sessionattributes;

    @Autowired
    UsuxCompaniaService usuxCompaniaService;

    @RequestMapping("/acumulado@{idTrab}")
    public ModelAndView acumulado(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/acumulado");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
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
        model.addAttribute("LstAcumEmp", acumuladoService.listarEmpAcum(emp.getIexcodcia(), emp.getIexcodtra()));

        UsuarioxRol ur = usuxCompaniaService.obtenerRolxUsuario(idCompania, Integer.valueOf(idusuario));
        log.info("ur.getIexdesrol(): " + ur.getIexdesrol());
        log.info("ur.getIexcodTra(): " + ur.getIexcodtra());

        if (ur.getIexdesrol().equals("SYSHRSELF")) {
            return new ModelAndView("public/gladius/organizacion/gestionEmpleado/acumulado/acumuladosSysHrSelf");
        } else {
            return new ModelAndView("public/gladius/organizacion/gestionEmpleado/acumulado/acumulados");
        }
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
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        String iexcodtra = request.getParameter("iexcodtra");

        log.info("idTrab: " + iexcodtra);
        model.addAttribute("idTrab", iexcodtra);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);

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


        String iexaniotrib = request.getParameter("iexaniotrib");

        EmpAcum empacum = new EmpAcum();
        empacum.setIexcodcia(idCompania);
        empacum.setIexcodtra(Integer.valueOf(iexcodtra));
        empacum.setIexaniotrib(iexaniotrib);
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

        Integer result = acumuladoService.validarAnioTrib(empacum);
        log.info("result: " + result);

        if (result > 0) {
            model.addAttribute("msg", "El año tributario ingresado ya existe, no se puede agregar uno nuevo con el mismo periodo, trate de editar ese año Tributario en la opción Editar.");
            return new ModelAndView("public/gladius/organizacion/gestionEmpleado/acumulado/nuevoAcumulado");
        } else {
            acumuladoService.insertarEmpAcum(empacum);
            return new ModelAndView("redirect:/acumulado@" + iexcodtra);
        }
    }

    @RequestMapping("/editarAcumulado@{idTrab}@{iexaniotrib}")
    public ModelAndView editarAcumulado(ModelMap model, HttpServletRequest request,
                                        @PathVariable String idTrab,
                                        @PathVariable String iexaniotrib) {
        log.info("/editarAcumulado");

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

        model.addAttribute("xEmpAcum", acumuladoService.getEmpAcum(idCompania, Integer.valueOf(idTrab),iexaniotrib));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/acumulado/editarAcumulado");
    }

    @RequestMapping("/modificarAcumulado")
    public ModelAndView modificarAcumulado(ModelMap model, HttpServletRequest request) {
        log.info("/modificarAcumulado");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        String iexcodtra = request.getParameter("iexcodtra");

        log.info("idTrab: " + iexcodtra);
        model.addAttribute("idTrab", iexcodtra);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);

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

        String iexaniotrib = request.getParameter("iexaniotrib2");

        EmpAcum empacum = new EmpAcum();
        empacum.setIexcodcia(idCompania);
        empacum.setIexcodtra(Integer.valueOf(iexcodtra));
        empacum.setIexaniotrib(iexaniotrib);
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

        acumuladoService.actualizarEmpAcum(empacum);

        return new ModelAndView("redirect:/acumulado@" + iexcodtra);
    }

    @RequestMapping("/detalleAcumulado@{idTrab}@{iexaniotrib}")
    public ModelAndView detalleAcumulado(ModelMap model, HttpServletRequest request,
                                        @PathVariable String idTrab,
                                        @PathVariable String iexaniotrib) {
        log.info("/detalleAcumulado");

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
        model.addAttribute("xEmpAcum", acumuladoService.getEmpAcum(idCompania, Integer.valueOf(idTrab),iexaniotrib));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/acumulado/detalleAcumulado");
    }

    @RequestMapping("/deleteAcumulado@{idTrab}@{iexaniotrib}")
    public ModelAndView deleteAcumulado(ModelMap model, HttpServletRequest request,
                                         @PathVariable String idTrab,
                                         @PathVariable String iexaniotrib) {
        log.info("/deleteAcumulado");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        EmpAcum empacum  = new EmpAcum();
        empacum.setIexcodcia(idCompania);
        empacum.setIexcodtra(Integer.valueOf(idTrab));
        empacum.setIexaniotrib(iexaniotrib);

        acumuladoService.eliminarEmpAcum(empacum);

        return new ModelAndView("redirect:/acumulado@" + idTrab);
    }
}

