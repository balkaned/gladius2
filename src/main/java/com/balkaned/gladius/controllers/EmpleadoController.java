package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Empleado;
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
    public ModelAndView listEmpleados(ModelMap model, HttpServletRequest request) {

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
        model.addAttribute("urlLogo",urlLogo);

        model.addAttribute("lovTipdoc",lovsService.getLovs("3","%"));
        model.addAttribute("lovSexo",lovsService.getLovs("50","%"));
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
        model.addAttribute("lovDist_origen",lovsService.getLovsDist("", emp.getIexprovin_origen()));   // enlita los departamentos que tiene registrado el trabajdorss

        Empleado emp2=empleadoService.recuperarLaboral(idCompania,Integer.parseInt(idTrab));
        model.addAttribute("emp2",emp2);
        model.addAttribute("lovSitPen",lovsService.getLovs("15","%"));
        model.addAttribute("lovTipCont",lovsService.getLovs("12","%"));
        model.addAttribute("lovPliego",lovsService.getLovs("31","%"));
        model.addAttribute("lovSituesp",lovsService.getLovs("35","%"));
        model.addAttribute("lovOcupRegPub",lovsService.getLovs("10","%"));
        model.addAttribute("lovOcupRegPrv",lovsService.getLovs("30","%"));
        model.addAttribute("lovPuesto",lovsService.getPuestoCia(idCompania));
        model.addAttribute("lovCcosto",lovsService.getCCostoCia(idCompania));
        model.addAttribute("lovArea",lovsService.getAreaCia(idCompania));
        model.addAttribute("lovUbicacion",lovsService.getUbicacionCia(idCompania));
        model.addAttribute("lovCateTra",lovsService.getLovs("24","%"));
        model.addAttribute("lovRegLab",lovsService.getLovs("33","%"));
        model.addAttribute("lovTipTra",lovsService.getLovs("8","%"));

        Empleado emp3=empleadoService.recuperarPagos(idCompania,Integer.parseInt(idTrab));
        model.addAttribute("emp3",emp3);
        model.addAttribute("lovBancoHab",lovsService.getLovs("36","%"));
        model.addAttribute("lovMonedaHab",lovsService.getLovs("52","%"));
        model.addAttribute("lovTipCtaHab",lovsService.getLovs("53","%"));
        model.addAttribute("lovBancoCts",lovsService.getLovs("36","%"));
        model.addAttribute("lovMonedaCts",lovsService.getLovs("52","%"));
        model.addAttribute("lovTipCtaCts",lovsService.getLovs("53","%"));
        model.addAttribute("lovTipPago",lovsService.getLovs("16","%"));
        model.addAttribute("lovPerRem",lovsService.getLovs("13","%"));

        Empleado emp4=empleadoService.recuperarSegSocial(idCompania,Integer.parseInt(idTrab));
        model.addAttribute("emp4",emp4);
        model.addAttribute("lovEssalud",lovsService.getLovs("32","%"));
        model.addAttribute("lovProvEps",lovsService.getLovs("14","%"));
        model.addAttribute("lovTipCenEdu",lovsService.getLovs("51","%"));
        model.addAttribute("lovCodAfp",lovsService.getLovs("11","%"));

        Empleado emp5=empleadoService.recuperarDireccion(idCompania,Integer.parseInt(idTrab));
        model.addAttribute("emp5",emp5);
        model.addAttribute("lovTipVia",lovsService.getLovs("5","%"));
        model.addAttribute("lovTipZona",lovsService.getLovs("6","%"));
        model.addAttribute("lovTipVia2",lovsService.getLovs("5","%"));
        model.addAttribute("lovTipZona2",lovsService.getLovs("6","%"));
        model.addAttribute("lovDept_origen1",lovsService.getLovsDept ("", emp5.getIexnacion_origen1()));  // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovProvin_origen1",lovsService.getLovsProv("", emp5.getIexdepart_origen1()));   // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovDist_origen1",lovsService.getLovsDist("", emp5.getIexprovin_origen1()));   // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovDept_origen2",lovsService.getLovsDept("", emp5.getIexnacion_origen2()  ));  // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovProvin_origen2",lovsService.getLovsProv("", emp5.getIexdepart_origen2()));   // enlita los departamentos que tiene registrado el trabajdor
        model.addAttribute("lovDist_origen2",lovsService.getLovsDist("", emp5.getIexprovin_origen2()));   // enlita los departamentos que tiene registrado el trabajdor

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/fichaTrabajador");
    }

    @RequestMapping(value="/updateEmplDarPers",method=RequestMethod.POST)
    public ModelAndView updateEmplDarPers(@ModelAttribute("empleado") Empleado ep, BindingResult result, SessionStatus status, HttpServletRequest request){

        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/");
        }
        Empleado p = new Empleado();
        Integer iexcodcia= Integer.valueOf(request.getParameter("iexcodcia"));;
        String iexcodtra = request.getParameter("iexcodtra");

        logger.info("iexcodcia: "+iexcodcia);
        logger.info("iexcodtra: "+iexcodtra);

        String iextipdocid = request.getParameter("iextipdocid");
        String iexnrodoc = request.getParameter("iexnrodocid");
        String iexnomtra = request.getParameter("iexnomtra");
        String iexapepat= request.getParameter("iexapepat");
        String iexapemat = request.getParameter("iexapemat");
        String iexfecnac = request.getParameter("iexfecnac");
        String iexcodsex = request.getParameter("iexcodsex");
        String iextiptra = request.getParameter("iextiptra");
        String iexfecing = request.getParameter("iexfecing");
        String iexmodform = request.getParameter("iexmodform");
        String iexpaisemisor = request.getParameter("iexpaisemisor");
        String iexnacion_origen = request.getParameter("iexnacion_origen");
        String iexdepart_origen = request.getParameter("iexdepart_origen");
        String iexprovin_origen = request.getParameter("iexprovin_origen");
        String iexdistri_origen = request.getParameter("iexdistri_origen");
        String iexgrdinstruccion = request.getParameter("iexgrdinstruccion");
        String iexcentroform = request.getParameter("iexcentroform");
        String iexflgdomicil = request.getParameter("iexflgdomicil");
        String iexcodant = request.getParameter("iexcodant");
        String iexflgest  = request.getParameter("iexflgest");
        String iexlardist = request.getParameter("iexcodlardist");
        String iexnrotelf = request.getParameter("iexnrotelf");
        String iexemail = request.getParameter("iexemail");
        String iexemail_coorp = request.getParameter("iexemail_coorp");
        String iexestcivil =  request.getParameter("iexestcivil");

        p.setIexcodcia(iexcodcia);
        p.setIexcodtra(Integer.valueOf(iexcodtra));
        p.setIexnomtra(iexnomtra);
        p.setIexapepat(iexapepat);
        p.setIexapemat(iexapemat);
        p.setIextipdocid(iextipdocid);
        p.setIexnrodoc(iexnrodoc);
        p.setIexfecnac(iexfecnac);
        p.setIexfecing(iexfecing);
        p.setIexcodsex(iexcodsex);
        p.setIexpaisemisor(iexpaisemisor);
        p.setIexflgest(iexflgest);
        p.setIexcodant(iexcodant);
        p.setIextiptra(iextiptra);
        p.setIexmodform(iexmodform);
        p.setIexnacion_origen(iexnacion_origen);
        p.setIexdepart_origen(iexdepart_origen);
        p.setIexprovin_origen(iexprovin_origen);
        p.setIexdistri_origen(iexdistri_origen);
        p.setIexcentroform(iexcentroform);
        p.setIexflgdomicil(iexflgdomicil);
        p.setIexusumod(user);
        p.setIexgrdinstruccion(iexgrdinstruccion);
        p.setIexcodlardist(iexlardist);
        p.setIexnrotelf(iexnrotelf);
        p.setIexemail(iexemail);
        p.setIexemail_coorp(iexemail_coorp);
        p.setIexestcivil(iexestcivil);

        empleadoService.actualizarCabecera(p);

        return new ModelAndView("redirect:/detalleEmpl@"+iexcodtra);
    }
}

