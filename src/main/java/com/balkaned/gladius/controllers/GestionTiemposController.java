package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.AusentismoProgramacion;
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
public class GestionTiemposController {
    static Logger logger = Logger.getLogger(GestionTiemposController.class.getName());
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    TurnoDiarioService turnoDiarioService;

    @RequestMapping("/gestionTiempo@{idTrab}")
    public ModelAndView gestionTiempo(ModelMap model, HttpServletRequest request,@PathVariable String idTrab){
        logger.info("/gestionTiempo");
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

        String v_fecini = request.getParameter("fecini");
        String v_fecfin = request.getParameter("fecfin");

        model.addAttribute("fecini",v_fecini);
        model.addAttribute("fecfin", v_fecfin);
        model.addAttribute("LstTurno",turnoDiarioService.listarTurnos(idCompania));
        model.addAttribute("lovTipTurno",lovsService.getLovs("69","%"));
        model.addAttribute("xempxturno",empleadoService.recuperarTurnos(idCompania, Integer.valueOf(idTrab)));

        if (v_fecini!= null && v_fecfin!= null )  {
            model.addAttribute("LstTurnoDiario", turnoDiarioService.listarTurnoDia(idCompania, Integer.valueOf(idTrab), v_fecini, v_fecfin));

        }

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/gestionTiempo/gestionTiempo");
    }

  /* @RequestMapping("/nuevoAusentismo@{idTrab}")
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
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp",idCompania);
        model.addAttribute("iexlogo",emp.getIexlogo());
        model.addAttribute("urlLogo",urlLogo);

        String sexo;
        logger.info("emp.getIexcodsex(): "+emp.getIexcodsex());
        if(emp.getIexcodsex()==null){sexo="NA";}else{sexo=emp.getIexcodsex();}
        logger.info("sexo: "+sexo);
        model.addAttribute("sexo",sexo);

        model.addAttribute("lovTipaus",lovsService.getLovs("57","%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/ausentismo/nuevoAusentismo");
    }

    @RequestMapping("/insertarAusentismo")
    public ModelAndView insertarAusentismo(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarAusentismo");
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

        Integer codcorrel = 0;
        Integer validador =0;
        String Msg_form_global="";

        String iexfecini = request.getParameter("iexfecini");
        String iexfecfin = request.getParameter("iexfecfin");

        AusentismoProgramacion ausprg = new AusentismoProgramacion();
        ausprg.setIexcodcia(emp.getIexcodcia());
        ausprg.setIexcodtra(emp.getIexcodtra());

        codcorrel = ausentismoService.getIdAusentismoPrg(ausprg);
        logger.info("codcorrel: "+codcorrel);
        validador= ausentismoService.validaAus(emp.getIexcodcia(), emp.getIexcodtra(), iexfecini,iexfecfin,0);
        logger.info("validador: "+validador);

        if(validador==0){
            //if(codcorrel >0) {
                ausprg.setIexcorrel(codcorrel);
                ausprg.setIexfecini(iexfecini);
                ausprg.setIexfecfin(iexfecfin);
                ausprg.setIextipaus(request.getParameter("iextipaus") );
                ausprg.setIexnrodias(Double.parseDouble(request.getParameter("iexnrodias")));
                ausprg.setIexglosa(request.getParameter("iexglosa"));
                ausprg.setIexusucrea("1");

                ausentismoService.insertarAusentismoPrg(ausprg);
                Msg_form_global="OK";
                return new ModelAndView("redirect:/ausentismo@"+iexcodtra);
            //}
        }else{
            Msg_form_global="Error";
            model.addAttribute("idTrab",iexcodtra);
            model.addAttribute("lovTipaus",lovsService.getLovs("57","%"));
            model.addAttribute("msg","Error Las fechas se cruzan con programaciones anteriores");
        }

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/ausentismo/nuevoAusentismo");
    }*/

}

