package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.EmpDatvar;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.VacacionProgramacion;
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
public class VacacionesController {
    static Logger logger = Logger.getLogger(VacacionesController.class.getName());
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    VacacionesService vacacionesService;

    @RequestMapping("/vacaciones@{idTrab}")
    public ModelAndView vacaciones(ModelMap model, HttpServletRequest request,@PathVariable String idTrab){
        logger.info("/vacaciones");
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

        empleado.setIexcodcia(idCompania);
        empleado.setIexcodtra(Integer.valueOf(idTrab));

        model.addAttribute("LstVacacionesCtl",vacacionesService.listarVacacionesCtl(empleado));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/vacaciones/listVacaciones");
    }

    @RequestMapping("/verDetalleVac@{idTrab}@{perMesIni}@{perMesFin}")
    public ModelAndView nuevoSueldoFijo(ModelMap model, HttpServletRequest request,
        @PathVariable String idTrab,
        @PathVariable String perMesIni,
        @PathVariable String perMesFin){
        logger.info("/verDetalleVac");
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

        model.addAttribute("perini",perMesIni);
        model.addAttribute("perfin",perMesFin);

        String sexo;
        logger.info("emp.getIexcodsex(): "+emp.getIexcodsex());
        if(emp.getIexcodsex()==null){sexo="NA";}else{sexo=emp.getIexcodsex();}
        logger.info("sexo: "+sexo);
        model.addAttribute("sexo",sexo);

        model.addAttribute("LstVacacionesPer",vacacionesService.listarVacacionesPer(emp,perMesIni, perMesFin));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/vacaciones/verDetalleVac");
    }

    @RequestMapping("/nuevasVacacionesValidacion@{idTrab}@{perMesIni}@{perMesFin}")
    public ModelAndView nuevasVacacionesValidacion(ModelMap model, HttpServletRequest request,
         @PathVariable String idTrab,
         @PathVariable String perMesIni,
         @PathVariable String perMesFin) {
        logger.info("/nuevasVacacionesValidacion");
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

        model.addAttribute("perini",perMesIni);
        model.addAttribute("perfin",perMesFin);

        Integer saldo =0;
        saldo=vacacionesService.saldotraVac(idCompania, Integer.valueOf(idTrab),perMesIni,perMesFin);
        logger.info("Saldo="+saldo);

        if (saldo>0) {
            return new ModelAndView("redirect:/nuevasVacacionesIns@{idTrab}@{perMesIni}@{perMesFin}@"+saldo);
        }else{
            model.addAttribute("msgErrorSaldoVacId","ERVAC01");
            model.addAttribute("msgErrorSaldoVac","No cuenta con saldo de dias para programar vacaciones");
        }

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/vacaciones/verDetalleVac");
    }


    @RequestMapping("/nuevasVacacionesIns@{idTrab}@{perini}@{perfin}@{saldo}")
    public ModelAndView nuevasVacacionesIns(ModelMap model, HttpServletRequest request,
        @PathVariable String idTrab,
        @PathVariable String perini,
        @PathVariable String perfin,
        @PathVariable String saldo){
        logger.info("/nuevasVacacionesIns");
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

        model.addAttribute("saldo",saldo);
        model.addAttribute("lovTipvaca",lovsService.getLovs("56","%"));
        model.addAttribute("perini",perini);
        model.addAttribute("perfin",perfin);

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/vacaciones/nuevasVacacionesIns");
    }

    @RequestMapping("/insertarVacaciones")
    public ModelAndView insertarVacaciones(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarVacaciones");
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

        String iexcodtra = request.getParameter("iexcodtra");

        Empleado emp=empleadoService.recuperarCabecera(idCompania,Integer.parseInt(iexcodtra));
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

        String perini2 = request.getParameter("perini2");
        String perfin2 = request.getParameter("perfin2");
        String saldo = request.getParameter("saldo2");

        String iexcodcia = request.getParameter("iexcodcia");
        String iexfecini = request.getParameter("iexfecini");
        String iexfecfin = request.getParameter("iexfecfin");

        String Msg_form_global ="" ;
        String resultado ="Error";

        Integer codcorrel =0;
        Empleado empleado = new Empleado();
        Integer validador =0;

        validador= vacacionesService.validaVac(Integer.valueOf(iexcodcia), Integer.valueOf(iexcodtra), iexfecini, iexfecfin);

        if(validador==0){
            VacacionProgramacion vacprg = new VacacionProgramacion();
            vacprg.setIexcodcia(Integer.valueOf(iexcodcia));
            vacprg.setIexcodtra(Integer.valueOf(iexcodtra));

            codcorrel = vacacionesService.getIdVacacionPrg(vacprg);
            logger.info("codcorrelVAc: "+codcorrel);

            if(codcorrel>0) {

                String iexpermesini2 = request.getParameter("iexpermesini2");

                logger.info("iexpermesini2: "+iexpermesini2);
                Integer mesfinal= Integer.valueOf(iexpermesini2)+1;

                vacprg.setIexcorrel(codcorrel);
                vacprg.setIextipvac(request.getParameter("iextipvac"));
                vacprg.setIexfecini(request.getParameter("iexfecini"));
                vacprg.setIexfecfin(request.getParameter("iexfecfin"));
                vacprg.setIexnrodias(Double.parseDouble(request.getParameter("iexnrodias")));
                vacprg.setIexglosa(request.getParameter("iexglosa"));
                vacprg.setIexusucrea("1");
                vacprg.setIexpermesini(iexpermesini2);
                vacprg.setIexpermesfin(String.valueOf(mesfinal));

                vacacionesService.insertarVacacionPrg(vacprg);
                Msg_form_global="OK";
            }
        }else{

            Msg_form_global="Error";
        }

        resultado=Msg_form_global;

        if(resultado.equals("OK")){
            Empleado emp2 = new Empleado();
            emp2.setIexcodtra(Integer.valueOf(iexcodtra));
            emp2.setIexcodcia(Integer.valueOf(iexcodcia));

            vacacionesService.procesaVacacionCtl(emp2);
            return new ModelAndView("redirect:/verDetalleVac@"+iexcodtra+"@"+perini2+"@"+perfin2);

        }else{
            model.addAttribute("saldo",saldo);
            model.addAttribute("lovTipvaca",lovsService.getLovs("56","%"));
            model.addAttribute("perini",perini2);
            model.addAttribute("perfin",perfin2);
            model.addAttribute("idTrab",iexcodtra);
            model.addAttribute("msg","Las fechas se cruzan con programaciones anteriores");
        }

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/vacaciones/nuevasVacacionesIns");
    }
}

