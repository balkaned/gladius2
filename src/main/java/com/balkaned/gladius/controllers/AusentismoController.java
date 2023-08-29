package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.EmpDatvar;
import com.balkaned.gladius.beans.EmpSueldo;
import com.balkaned.gladius.beans.Empleado;
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
public class AusentismoController {
    static Logger logger = Logger.getLogger(AusentismoController.class.getName());
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    AusentismoService ausentismoService;

    @RequestMapping("/ausentismo@{idTrab}")
    public ModelAndView ausentismo(ModelMap model, HttpServletRequest request,@PathVariable String idTrab){
        logger.info("/ausentismo");

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
        logger.info("idTraXXXXXb: "+idTrab);

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
        model.addAttribute("estado", emp.getDesestado());
        model.addAttribute("idComp",idCompania);
        model.addAttribute("iexlogo",emp.getIexlogo());
        model.addAttribute("urlLogo",urlLogo);

        String sexo;
        logger.info("emp.getIexcodsex(): "+emp.getIexcodsex());
        if(emp.getIexcodsex()==null){sexo="NA";}else{sexo=emp.getIexcodsex();}
        logger.info("sexo: "+sexo);
        model.addAttribute("sexo",sexo);

        model.addAttribute("LstAusentismoDet",ausentismoService.listarAusentismoPrg(empleado));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/ausentismo");
    }

   @RequestMapping("/nuevoAusentismo@{idTrab}")
    public ModelAndView nuevoAusentismo(ModelMap model, HttpServletRequest request,@PathVariable String idTrab){
        logger.info("/nuevoAusentismo");

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
        logger.info("idTraXXXXXb: "+idTrab);

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
        model.addAttribute("estado", emp.getDesestado());
        model.addAttribute("idComp",idCompania);
        model.addAttribute("iexlogo",emp.getIexlogo());
        model.addAttribute("urlLogo",urlLogo);

        String sexo;
        logger.info("emp.getIexcodsex(): "+emp.getIexcodsex());
        if(emp.getIexcodsex()==null){sexo="NA";}else{sexo=emp.getIexcodsex();}
        logger.info("sexo: "+sexo);
        model.addAttribute("sexo",sexo);

        model.addAttribute("lovTipaus",lovsService.getLovs("57","%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/nuevoAusentismo");
    }

    /*@RequestMapping("/insertarSueldoFijo")
    public ModelAndView insertarSueldoFijo(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarSueldoFijo");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        Integer idempleado=0;

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);
        model.addAttribute("idComp",idCompania);
        model.addAttribute("urlLogo",urlLogo);

        String concepto = request.getParameter("iexcodcon");
        Integer iexcodcia = Integer.valueOf(request.getParameter("iexcodcia"));
        Integer iexcodtra = Integer.valueOf(request.getParameter("iexcodtra"));
        Double valcon  = 0.0;

        if(request.getParameter("iexvalcon")==null) {
            valcon  = 0.0;
        }else {
            valcon  = Double.parseDouble(request.getParameter("iexvalcon"));
        };

        EmpSueldo sueldo = new EmpSueldo();
        sueldo.setIexcodcia(iexcodcia);
        sueldo.setIexcodtra(iexcodtra);
        sueldo.setIexcodcon(concepto);
        sueldo.setIexvalcon(valcon);
        sueldo.setIexflgest("1");

        sueldoService.insertarEmpSueldo(sueldo);

        return new ModelAndView("redirect:/sueldoFijo@"+iexcodtra);
    }

    @RequestMapping("/sueldoVariable@{idTrab}")
    public ModelAndView sueldoVariable(ModelMap model, HttpServletRequest request,@PathVariable String idTrab){
        logger.info("/sueldoVariable");

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
        logger.info("idTraXXXXXb: "+idTrab);

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
        model.addAttribute("estado", emp.getDesestado());
        model.addAttribute("idComp",idCompania);
        model.addAttribute("iexlogo",emp.getIexlogo());
        model.addAttribute("urlLogo",urlLogo);

        String sexo;
        logger.info("emp.getIexcodsex(): "+emp.getIexcodsex());
        if(emp.getIexcodsex()==null){sexo="NA";}else{sexo=emp.getIexcodsex();}
        logger.info("sexo: "+sexo);
        model.addAttribute("sexo",sexo);

        model.addAttribute("Lovs_regimen",lovsService.getRegimenProc());

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/sueldoVariable");
    }

    @RequestMapping("/verDataSueldoVar@{idTrab}")
    public ModelAndView verDataSueldoVar(ModelMap model, HttpServletRequest request,@PathVariable String idTrab) {
        logger.info("/verDataSueldoVar");
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

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);
        model.addAttribute("idComp",idCompania);
        model.addAttribute("urlLogo",urlLogo);
        model.addAttribute("idTrab",idTrab);

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
        model.addAttribute("iexlogo",emp.getIexlogo());
        model.addAttribute("urlLogo",urlLogo);

        String iexcodpro = request.getParameter("iexcodpro");
        String iexperiodo = request.getParameter("iexperiodo");
        String iexcodtra = request.getParameter("iexcodtra");

        String sexo;
        logger.info("emp.getIexcodsex(): "+emp.getIexcodsex());
        if(emp.getIexcodsex()==null){sexo="NA";}else{sexo=emp.getIexcodsex();}
        logger.info("sexo: "+sexo);
        model.addAttribute("sexo",sexo);

        model.addAttribute("iexcodpro",iexcodpro);
        model.addAttribute("iexperiodo",iexperiodo);

        model.addAttribute("fdatavar",sueldoService.obtenerEmpDatvar(idCompania, Integer.valueOf(iexcodpro),iexperiodo, Integer.valueOf(iexcodtra),1));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/verDataSueldoVar");
    }

    @RequestMapping("/nuevoSueldoVar@{idTrab}@{codpro}@{nroper}")
    public ModelAndView nuevoSueldoVar(ModelMap model, HttpServletRequest request,
        @PathVariable String idTrab,
        @PathVariable String codpro,
        @PathVariable String nroper) {

        logger.info("/nuevoSueldoVar");
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

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);
        model.addAttribute("idComp",idCompania);
        model.addAttribute("urlLogo",urlLogo);
        model.addAttribute("idTrab",idTrab);

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
        model.addAttribute("iexlogo",emp.getIexlogo());
        model.addAttribute("urlLogo",urlLogo);

        String sexo;
        logger.info("emp.getIexcodsex(): "+emp.getIexcodsex());
        if(emp.getIexcodsex()==null){sexo="NA";}else{sexo=emp.getIexcodsex();}
        logger.info("sexo: "+sexo);
        model.addAttribute("sexo",sexo);

        String concepto2 = request.getParameter("iexcodcon");
        model.addAttribute("lovConcepVar",sueldoService.ListConceptos(idCompania, "2"));
        request.setAttribute("iexcodpro", codpro);
        request.setAttribute("iexperiodo", nroper);



        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/nuevoSueldoVar");
    }

    @RequestMapping("/insertarConceptoVar")
    public ModelAndView insertarConceptoVar(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarConceptoVar");
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

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);
        model.addAttribute("idComp",idCompania);
        model.addAttribute("urlLogo",urlLogo);

        Integer iexcodcia  = Integer.valueOf(request.getParameter("iexcodcia"));
        Integer iexcodtra = Integer.valueOf(request.getParameter("iexcodtra"));
        Integer v_codpro = Integer.valueOf( request.getParameter("iexcodpro2"));
        String periodo  =   request.getParameter("iexperiodo2");
        String concepto = request.getParameter("iexcodcon");
        Double valcon  = 0.0;

        if(request.getParameter("iexvalcon")==null) {
            valcon  = 0.0;
        }else {
            valcon  = Double.parseDouble(request.getParameter("iexvalcon"));
        };

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

        return new ModelAndView("redirect:/verDataSueldoVar@"+iexcodtra);
    }*/
}

