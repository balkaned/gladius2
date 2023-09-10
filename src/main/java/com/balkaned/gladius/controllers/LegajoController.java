package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.Grpfile;
import com.balkaned.gladius.beans.RetencionJudicial;
import com.balkaned.gladius.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController
public class LegajoController {
    static Logger logger = Logger.getLogger(LegajoController.class.getName());
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    LegajoService legajoService;

    @RequestMapping("/legajo@{idTrab}")
    public ModelAndView legajo(ModelMap model, HttpServletRequest request,@PathVariable String idTrab){
        logger.info("/legajo");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("idCompaniaXXXX: "+idCompania);
        logger.info("idTrabXXXXX: "+idTrab);

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);
        model.addAttribute("idTrab",idTrab);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado",empleado);
        Empleado emp=empleadoService.recuperarCabecera(idCompania,Integer.parseInt(idTrab));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl",emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp",idCompania);
        model.addAttribute("iexlogo",emp.getIexlogo());
        model.addAttribute("urlLogo",urlLogo);

        String sexo;
        logger.info("emp.getIexcodsex(): "+emp.getIexcodsex());
        if(emp.getIexcodsex()==null){sexo="NA";}else{sexo=emp.getIexcodsex();}
        logger.info("sexo: "+sexo);
        model.addAttribute("sexo",sexo);

        model.addAttribute("lovGrpFile",lovsService.getLovs("91","%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/legajo/legajo");
    }

    @RequestMapping("/buscarLegajo@{idTrab}")
    public ModelAndView buscarLegajo(ModelMap model, HttpServletRequest request,@PathVariable String idTrab){
        logger.info("/buscarLegajo");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("idCompaniaXXXX: "+idCompania);
        logger.info("idTrabXXXXX: "+idTrab);

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);
        model.addAttribute("idTrab",idTrab);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado",empleado);
        Empleado emp=empleadoService.recuperarCabecera(idCompania,Integer.parseInt(idTrab));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl",emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp",idCompania);
        model.addAttribute("iexlogo",emp.getIexlogo());
        model.addAttribute("urlLogo",urlLogo);

        String sexo;
        logger.info("emp.getIexcodsex(): "+emp.getIexcodsex());
        if(emp.getIexcodsex()==null){sexo="NA";}else{sexo=emp.getIexcodsex();}
        logger.info("sexo: "+sexo);
        model.addAttribute("sexo",sexo);

        model.addAttribute("lovGrpFile",lovsService.getLovs("91","%"));

        String codgrpfile = request.getParameter("codgrpfile");
        Integer iexcodtra  = Integer.valueOf(request.getParameter("iexcodtra"));
        Integer iexcodcia  = Integer.valueOf(request.getParameter("iexcodcia"));

        model.addAttribute("lovGrpFile",lovsService.getLovs("91","%"));
        model.addAttribute("listGrpFile",legajoService.listarGrpfile(iexcodcia, iexcodtra, codgrpfile));
        model.addAttribute("codgrpfile",codgrpfile);

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/legajo/legajo");
    }

    @RequestMapping("/ingresarImagen@{idTrab}@{iexcodgrpfile}")
    public ModelAndView ingresarImagen(ModelMap model, HttpServletRequest request,
                                       @PathVariable String idTrab,
                                       @PathVariable String iexcodgrpfile){
        logger.info("/ingresarImagen");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("idCompaniaXXXX: "+idCompania);
        logger.info("idTrabXXXXX: "+idTrab);

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);
        model.addAttribute("idTrab",idTrab);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado",empleado);
        Empleado emp=empleadoService.recuperarCabecera(idCompania,Integer.parseInt(idTrab));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl",emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp",idCompania);
        model.addAttribute("iexlogo",emp.getIexlogo());
        model.addAttribute("urlLogo",urlLogo);

        String sexo;
        logger.info("emp.getIexcodsex(): "+emp.getIexcodsex());
        if(emp.getIexcodsex()==null){sexo="NA";}else{sexo=emp.getIexcodsex();}
        logger.info("sexo: "+sexo);
        model.addAttribute("sexo",sexo);

        model.addAttribute("iexcodgrpfile",iexcodgrpfile);

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/legajo/ingresarImagen");
    }

    @RequestMapping("/nuevoGrupo@{idTrab}")
    public ModelAndView nuevoGrupo(ModelMap model, HttpServletRequest request,
                                       @PathVariable String idTrab){
        logger.info("/nuevoGrupo");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("idCompaniaXXXX: "+idCompania);
        logger.info("idTrabXXXXX: "+idTrab);

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);
        model.addAttribute("idTrab",idTrab);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado",empleado);
        Empleado emp=empleadoService.recuperarCabecera(idCompania,Integer.parseInt(idTrab));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl",emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp",idCompania);
        model.addAttribute("iexlogo",emp.getIexlogo());
        model.addAttribute("urlLogo",urlLogo);

        String sexo;
        logger.info("emp.getIexcodsex(): "+emp.getIexcodsex());
        if(emp.getIexcodsex()==null){sexo="NA";}else{sexo=emp.getIexcodsex();}
        logger.info("sexo: "+sexo);
        model.addAttribute("sexo",sexo);

        model.addAttribute("lovGrpFile",lovsService.getLovs("91","%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/legajo/nuevoGrupo");
    }

    @RequestMapping("/insertarGrupo")
    public ModelAndView insertarGrupo(ModelMap model, HttpServletRequest request){
        logger.info("/insertarGrupo");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        String codgrpfile = request.getParameter("codgrpfile");
        String desfile = request.getParameter("desfile");
        String iexcodcia = request.getParameter("iexcodcia");
        String iexcodtra = request.getParameter("iexcodtra");

        Grpfile grp  = new Grpfile();
        grp.setIexcodcia(Integer.valueOf(iexcodcia));
        grp.setIexcodtra(Integer.valueOf(iexcodtra));
        Integer idgrpfile = legajoService.obtieneIdGrpFile(grp);
        logger.info("idgrpfile: "+idgrpfile);
        grp.setIexcodgrpfile(idgrpfile);
        grp.setIexgrpfile(codgrpfile);
        grp.setIexdesgrpfile(desfile);
        grp.setIexusucrea(usuario);

        legajoService.insertarGrpFile(grp);

        return new ModelAndView("redirect:/legajo@"+iexcodtra);
    }
}

