package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.PrestamoCab;
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
public class PrestamoController {
    static Logger logger = Logger.getLogger(PrestamoController.class.getName());
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    PrestamoService prestamoService;

    @RequestMapping("/prestamos@{idTrab}")
    public ModelAndView prestamos(ModelMap model, HttpServletRequest request,@PathVariable String idTrab){
        logger.info("/prestamos");
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

        model.addAttribute("LstPrestCab",prestamoService.listarPrestamoCab(emp));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/prestamos/prestamos");
    }

   @RequestMapping("/nuevoPrestamo@{idTrab}")
    public ModelAndView nuevoPrestamo(ModelMap model, HttpServletRequest request,@PathVariable String idTrab){
        logger.info("/nuevoPrestamo");
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
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp",idCompania);
        model.addAttribute("iexlogo",emp.getIexlogo());
        model.addAttribute("urlLogo",urlLogo);

        String sexo;
        logger.info("emp.getIexcodsex(): "+emp.getIexcodsex());
        if(emp.getIexcodsex()==null){sexo="NA";}else{sexo=emp.getIexcodsex();}
        logger.info("sexo: "+sexo);
        model.addAttribute("sexo",sexo);

        model.addAttribute("lovTippres",lovsService.getLovs("59","%"));
        model.addAttribute("lovTipInteres",lovsService.getLovs("60","%"));
        model.addAttribute("lovFrecPrestamo",lovsService.getLovs("61","%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/prestamos/nuevoPrestamo");
    }

    @RequestMapping("/insertarPrestamo")
    public ModelAndView insertarPrestamo(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarPrestamo");
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

        Integer idcab = 0;
        String iexcodcia = request.getParameter("iexcodcia");
        String iexcodtra = request.getParameter("iexcodtra");

        PrestamoCab prestcab = new PrestamoCab();
        prestcab.setIexcodcia(Integer.valueOf(iexcodcia));
        prestcab.setIexcodtra(Integer.valueOf(iexcodtra));

        idcab =  prestamoService.getIdPrestamoCab(prestcab);
        logger.info("idcab: "+idcab);

        prestcab.setIexcorrel(idcab);
        prestcab.setIextippres(request.getParameter("iextipprestamo"));
        prestcab.setIextipinteres(request.getParameter("iextipinteres"));
        prestcab.setIexfrecuencia(request.getParameter("iexfrecuencia"));
        //prestcab.setIexfecpres(request.getParameter("iexfecpres"));
        //prestcab.setIexfecinivig(request.getParameter("iexfecinivig"));
        prestcab.setIexnrocuotas(Double.parseDouble(request.getParameter("iexnrocuota")));
        prestcab.setIeximpbru(Double.parseDouble(request.getParameter("ieximpbruto")));
        prestcab.setIexinteres(Double.parseDouble(request.getParameter("iexinteres")));
        prestcab.setIeximptotal(Double.parseDouble(request.getParameter("ieximptotal")));
        prestcab.setIexglosa(request.getParameter("iexglosa"));
        prestcab.setIexestado("1");
        prestcab.setIexusucrea("1");

        prestamoService.insertarPrestamoCab(prestcab);

        return new ModelAndView("redirect:/prestamos@"+iexcodtra);
    }

}

