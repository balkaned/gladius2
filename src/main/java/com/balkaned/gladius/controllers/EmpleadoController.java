package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Compania;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.UsuarioConeccion;
import com.balkaned.gladius.services.CompaniaService;
import com.balkaned.gladius.services.EmpleadoService;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.services.UsuarioConeccionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class EmpleadoController {
    static Logger logger = Logger.getLogger(EmpleadoController.class.getName());
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @RequestMapping("/listEmpleados")
    public ModelAndView empleadosList(ModelMap model, HttpServletRequest request) {

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("usuario: "+usuario);
        logger.info("idusuario: "+idusuario);
        logger.info("email: "+email);
        logger.info("firstCharacter: "+firstCharacter);
        logger.info("idCompania: "+idCompania);
        logger.info("nombreComp: "+nombreComp);
        logger.info("rucComp: "+rucComp);

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);
        model.addAttribute("idComp",idCompania);
        model.addAttribute("urlLogo",urlLogo);

        Empleado emp = new Empleado();
        emp.setIexcodcia(idCompania);

        List<Empleado> empleadoList=empleadoService.listarEmpleado(emp);
        model.addAttribute("empleadoList",empleadoList);

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/listEmpleados");
    }

    @RequestMapping("/detalleEmpl@{idTrab}")
    public ModelAndView detalleEmpl(ModelMap model, HttpServletRequest request,@PathVariable String idTrab) {

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("idCompaniaXXXX: "+idCompania);

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);

        Empleado emp=empleadoService.recuperarCabecera(idCompania,Integer.parseInt(idTrab));

        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl",emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getDesestado());
        model.addAttribute("idComp",idCompania);
        model.addAttribute("urlLogo",urlLogo);

        model.addAttribute("lovTipdoc",lovsService.getLovs("3","%"));
        /*model.addAttribute("lovSexo",lovsService.getLovs("50","%"));
        model.addAttribute("lovModForm",lovsService.getLovs("18","%"));
        model.addAttribute("lovPaisEmisor",lovsService.getLovs("26","%"));
        model.addAttribute("lovNacionalidad",lovsService.getLovs("4","%"));
        model.addAttribute("lovGrdInstruccion",lovsService.getLovs("9","%"));
        model.addAttribute("lovCenForm",lovsService.getLovs("51","%"));
        model.addAttribute("lovEstados",lovsService.getLovs("54","%"));
        model.addAttribute("lovLarDistancia",lovsService.getLovs("29","%"));
        model.addAttribute("lovEstcivil",lovsService.getLovs("68","%"));

        model.addAttribute("lovDept_origen",lovsService.getLovsDept("",emp.getIexpaisemisor() ));  // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovProvin_origen",lovsService.getLovsProv("", emp.getIexdepart_origen()));   // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovDist_origen",lovsService.getLovsDist("", emp.getIexprovin_origen()));   // enlita los departamentos que tiene registrado el trabajdorss*/

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/fichaTrabajador");
    }
}

