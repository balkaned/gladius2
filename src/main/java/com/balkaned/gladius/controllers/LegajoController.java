package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.Grpfile;
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
public class LegajoController {
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    LovsService lovsService;

    @Autowired
    LegajoService legajoService;

    @Autowired
    Sessionattributes sessionattributes;

    @Autowired
    Sessionattributes sessionattributes2;

    @RequestMapping("/legajo@{idTrab}")
    public ModelAndView legajo(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/legajo");

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

        model.addAttribute("lovGrpFile", lovsService.getLovs("91", "%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/legajo/legajo");
    }

    @RequestMapping("/buscarLegajo@{idTrab}")
    public ModelAndView buscarLegajo(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/buscarLegajo");

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

        String codgrpfile = request.getParameter("codgrpfile");
        Integer iexcodtra = Integer.valueOf(idTrab);
        Integer iexcodcia = idCompania;

        model.addAttribute("lovGrpFile", lovsService.getLovs("91", "%"));
        model.addAttribute("listGrpFile", legajoService.listarGrpfile(iexcodcia, iexcodtra, codgrpfile));
        model.addAttribute("codgrpfile", codgrpfile);

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/legajo/legajo");
    }

    @RequestMapping("/buscarLegajoAtras@{idTrab}@{grpFile}")
    public ModelAndView buscarLegajoAtras(ModelMap model, HttpServletRequest request,
                                     @PathVariable String idTrab,
                                     @PathVariable String grpFile) {
        log.info("/buscarLegajoAtras");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("idTrab: " + idTrab);
        log.info("grpFile: " + grpFile);
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

        String codgrpfile = grpFile;
        Integer iexcodtra = Integer.valueOf(idTrab);
        Integer iexcodcia = idCompania;

        model.addAttribute("lovGrpFile", lovsService.getLovs("91", "%"));
        model.addAttribute("listGrpFile", legajoService.listarGrpfile(iexcodcia, iexcodtra, codgrpfile));
        model.addAttribute("codgrpfile", codgrpfile);

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/legajo/legajo");
    }

    @RequestMapping("/ingresarImagen@{idTrab}@{iexcodgrpfile}@{grpFile}")
    public ModelAndView ingresarImagen(ModelMap model, HttpServletRequest request,
                                       @PathVariable String idTrab,
                                       @PathVariable String iexcodgrpfile,
                                       @PathVariable String grpFile) {
        log.info("/ingresarImagen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("idTrab: " + idTrab);
        model.addAttribute("idTrab", idTrab);
        model.addAttribute("idComp", idCompania);

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

        model.addAttribute("iexcodgrpfile", iexcodgrpfile);
        model.addAttribute("grpFile",grpFile);

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/legajo/ingresarImagen");
    }

    @RequestMapping("/nuevoGrupo@{idTrab}")
    public ModelAndView nuevoGrupo(ModelMap model, HttpServletRequest request,
                                   @PathVariable String idTrab) {
        log.info("/nuevoGrupo");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("idTrabXXXXX: " + idTrab);
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
        model.addAttribute("lovGrpFile", lovsService.getLovs("91", "%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/legajo/nuevoGrupo");
    }

    @RequestMapping("/insertarGrupo")
    public ModelAndView insertarGrupo(ModelMap model, HttpServletRequest request) {
        log.info("/insertarGrupo");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String usuario = (String) request.getSession().getAttribute("user");

        String codgrpfile = request.getParameter("codgrpfile");
        String desfile = request.getParameter("desfile");
        String iexcodcia = request.getParameter("iexcodcia");
        String iexcodtra = request.getParameter("iexcodtra");

        Grpfile grp = new Grpfile();
        grp.setIexcodcia(Integer.valueOf(iexcodcia));
        grp.setIexcodtra(Integer.valueOf(iexcodtra));
        Integer idgrpfile = legajoService.obtieneIdGrpFile(grp);
        log.info("idgrpfile: " + idgrpfile);
        grp.setIexcodgrpfile(idgrpfile);
        grp.setIexgrpfile(codgrpfile);
        grp.setIexdesgrpfile(desfile);
        grp.setIexusucrea(usuario);

        legajoService.insertarGrpFile(grp);

        return new ModelAndView("redirect:/legajo@" + iexcodtra);
    }

    @RequestMapping("/editarGrupoArch@{idTrab}@{iexcodgrpfile}@{grpFile}")
    public ModelAndView editarGrupoArch(ModelMap model, HttpServletRequest request,
                                    @PathVariable String idTrab,
                                    @PathVariable String iexcodgrpfile,
                                    @PathVariable String grpFile) {
        log.info("/editarGrupoArch");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("idTrabXXXXX: " + idTrab);
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

        model.addAttribute("lovGrpFile", lovsService.getLovs("91", "%"));
        model.addAttribute("xGrpFile",legajoService.getGrpfile(idCompania, Integer.valueOf(iexcodgrpfile)));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/legajo/editarGrupoArch");
    }

    @RequestMapping("/modificarGrupoArch")
    public ModelAndView modificarGrupoArch(ModelMap model, HttpServletRequest request) {
        log.info("/modificarGrupoArch");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);

        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String codtra=request.getParameter("iexcodtra");
        String codgrpfilee = request.getParameter("idgrpfile");
        String grpfilee = request.getParameter("codgrpfile");
        String desfile2 = request.getParameter("desfile");
        String desestado = request.getParameter("estado");

        Grpfile grp3  = new Grpfile();
        grp3.setIexcodcia(idCompania);
        grp3.setIexcodtra(Integer.valueOf(codtra));
        grp3.setIexcodgrpfile(Integer.parseInt(codgrpfilee));
        grp3.setIexgrpfile(grpfilee);
        grp3.setIexdesgrpfile(desfile2);
        grp3.setIexestado(desestado);
        grp3.setIexusumod(usuario);

        legajoService.actualizarGrpFile(grp3);

        return new ModelAndView("redirect:/buscarLegajoAtras@"+codtra+"@"+grpfilee);
    }

    @RequestMapping("/delGrupoArch@{idTrab}@{iexcodgrpfile}@{grpFile}")
    public ModelAndView delGrupoArch(ModelMap model, HttpServletRequest request,
                                        @PathVariable String idTrab,
                                        @PathVariable String iexcodgrpfile,
                                        @PathVariable String grpFile) {
        log.info("/delGrupoArch");

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

        Grpfile grp2  = new Grpfile();
        grp2.setIexcodcia(idCompania);
        grp2.setIexcodtra(Integer.valueOf(idTrab));
        grp2.setIexcodgrpfile(Integer.valueOf(iexcodgrpfile));

        log.info("grp2.setIexcodcia: "+grp2.getIexcodcia());
        log.info("grp2.setIexcodtra: "+grp2.getIexcodtra());
        log.info("grp2.getIexcodgrpfile: "+grp2.getIexcodgrpfile());
        legajoService.eliminarGrpFile(grp2);

        return new ModelAndView("redirect:/buscarLegajoAtras@"+idTrab+"@"+grpFile);
    }
}

