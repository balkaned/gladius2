package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Double.parseDouble;

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

    @RequestMapping("/listAsistencia")
    public ModelAndView listAsistencia(ModelMap model, HttpServletRequest request) {
        logger.info("/listAsistencia");

        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);


        String v_fecini = request.getParameter("fecini");
        String v_fecfin = request.getParameter("fecfin");

        model.addAttribute("fecini", v_fecini);
        model.addAttribute("fecfin", v_fecfin);

        model.addAttribute("empleadoList", turnoDiarioService.listarTurMasTra(idCompania, v_fecini, v_fecfin));
        model.addAttribute("lovTipTurno", lovsService.getLovs("69", "%"));


        return new ModelAndView("public/gladius/gestionTiempo/asistencia/listAsistencia");
    }


    @RequestMapping("/listarTurnodia")
    public ModelAndView listarTurnodia(ModelMap model, HttpServletRequest request) {
        logger.info("/listarTurnodia");

        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);



        Empleado Empleado = new Empleado();
        Empleado = (Empleado) request.getSession(Boolean.parseBoolean("FichaEmpleado"));

        String v_fecini = request.getParameter("fecini");
        String v_fecfin = request.getParameter("fecfin");

        request.setAttribute("fecini",v_fecini );
        request.setAttribute("fecfin", v_fecfin );


        model.addAttribute("LstTurno",turnoDiarioService.listarTurnos(idCompania));
        model.addAttribute("lovTipTurno",lovsService.getLovs("69","%"));
        model.addAttribute("xempxturno",empleadoService.recuperarTurnos(idCompania, Empleado.getIexcodtra()));

        if (v_fecini!= null && v_fecfin!= null )  {
            model.addAttribute("LstTurnoDiario", turnoDiarioService.listarTurnoDia(idCompania, Empleado.getIexcodtra(), v_fecini, v_fecfin));
        }
        return new ModelAndView("public/gladius/gestionTiempo/asistencia/listAsistencia");
    }


    @RequestMapping("/listTurno")
    public ModelAndView lisTurno(ModelMap model, HttpServletRequest request) {
        logger.info("/listTurno");

        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        Turno turn = new Turno();
        turn.setCodcia(idCompania);
        List<Turno> turnoList = turnoDiarioService.listarTurnos(idCompania);
        model.addAttribute("turnoList", turnoList);

        return new ModelAndView("public/gladius/gestionTiempo/turno/listTurno");

    }

    @RequestMapping("/nuevoTurno")
    public ModelAndView nuevoTurno(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevoTurno");
        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        return new ModelAndView("public/gladius/gestionTiempo/turno/nuevoTurno");
    }

    @RequestMapping("/insertarTurno")
    public ModelAndView insertarTurno(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarTurno");
        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);


        Turno turno = new Turno();
        turno.setCodcia(idCompania);
        turno.setIexcodturno(Integer.parseInt(request.getParameter("iexcodturno")));
        turno.setIexdesturno(request.getParameter("iexdesturno"));
        turno.setIexflgturno(request.getParameter("iexflgturno"));
        turno.setIexdesturno(request.getParameter("iexdesturno"));
        turno.setIexhorini(request.getParameter("iexhorini"));
        turno.setIexhorfin(request.getParameter("iexhorfin"));
        turno.setIexflgdiasig(request.getParameter("iexflgdiasig"));

        String iextopmaxpost = request.getParameter("iextopmaxpost");
        String iextopminantes = request.getParameter("iextopminantes");

        if (iextopminantes != null && !iextopminantes.isEmpty()) {
            turno.setIextopminantes(Double.parseDouble(iextopminantes));
        } else {
            turno.setIextopminantes(0.0);
        }

        if (iextopmaxpost != null && !iextopmaxpost.isEmpty()) {
            turno.setIextopmaxpost(Double.parseDouble(iextopmaxpost));
        } else {
            turno.setIextopmaxpost(0.0);
        }
        turnoDiarioService.insertarTurno(turno);

        return new ModelAndView("redirect:/listTurno");
    }

    @RequestMapping("/editarTurno@{idTurn}")
    public ModelAndView editarTurno(ModelMap model, HttpServletRequest request, @PathVariable String idTurn) {
        logger.info("/editarTurno");

        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);
        model.addAttribute("idTurn", idTurn);

        model.addAttribute("turno", turnoDiarioService.getTurno(idCompania, Integer.parseInt(idTurn)));

        return new ModelAndView("public/gladius/gestionTiempo/turno/editarTurno");
    }


    @RequestMapping("/modificarTurno")
    public ModelAndView modificarTurno(ModelMap model, HttpServletRequest request) {
        logger.info("/modificarTurno");
        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        Turno turno = new Turno();
        turno.setCodcia(idCompania);
        turno.setIexcodturno(Integer.parseInt(request.getParameter("iexcodturno")));
        turno.setIexdesturno(request.getParameter("iexdesturno"));
        turno.setIexflgturno(request.getParameter("iexflgturno"));
        turno.setIexdesturno(request.getParameter("iexdesturno"));
        turno.setIexhorini(request.getParameter("iexhorini"));
        turno.setIexhorfin(request.getParameter("iexhorfin"));
        turno.setIexflgdiasig(request.getParameter("iexflgdiasig"));

        String iextopmaxpost = request.getParameter("iextopmaxpost");
        String iextopminantes = request.getParameter("iextopminantes");

        if (iextopminantes != null && !iextopminantes.isEmpty()) {
            turno.setIextopminantes(Double.parseDouble(iextopminantes));
        } else {
            turno.setIextopminantes(0.0);
        }

        if (iextopmaxpost != null && !iextopmaxpost.isEmpty()) {
            turno.setIextopmaxpost(Double.parseDouble(iextopmaxpost));
        } else {
            turno.setIextopmaxpost(0.0);
        }
        turnoDiarioService.actualizarTurno(turno);

        return new ModelAndView("redirect:/listTurno");
    }

    @RequestMapping("/gestionTiempo@{idTrab}")
    public ModelAndView gestionTiempo(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        logger.info("/gestionTiempo");
        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        logger.info("idCompaniaXXXX: " + idCompania);
        logger.info("idTraXXXXXb: " + idTrab);

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
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
        logger.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        logger.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        String v_fecini = request.getParameter("fecini");
        String v_fecfin = request.getParameter("fecfin");

        model.addAttribute("fecini", v_fecini);
        model.addAttribute("fecfin", v_fecfin);
        model.addAttribute("LstTurno", turnoDiarioService.listarTurnos(idCompania));
        model.addAttribute("lovTipTurno", lovsService.getLovs("69", "%"));
        model.addAttribute("xempxturno", empleadoService.recuperarTurnos(idCompania, Integer.valueOf(idTrab)));

        if (v_fecini != null && v_fecfin != null) {
            model.addAttribute("LstTurnoDiario", turnoDiarioService.listarTurnoDia(idCompania, Integer.valueOf(idTrab), v_fecini, v_fecfin));
        }

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/gestionTiempo/gestionTiempo");
    }

    @RequestMapping("/grabarTurno@{idTrab}")
    public ModelAndView grabarTurno(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        logger.info("/grabarTurno");

        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        logger.info("idCompaniaXXXX: " + idCompania);
        logger.info("idTraXXXXXb: " + idTrab);

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
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
        logger.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        logger.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        Integer v_codcia = idCompania;
        //v_codcia, Empleado.getIexcodtra(), Integer.parseInt(v_codturno),v_desfecdia , (String)session.getAttribute("desusu")
        String tipturno = request.getParameter("slc_tipturno");

        String lunes = request.getParameter("iexlunes");
        String martes = request.getParameter("iexmartes");
        String miercoles = request.getParameter("iexmiercoles");
        String jueves = request.getParameter("iexjueves");
        String viernes = request.getParameter("iexviernes");
        String sabado = request.getParameter("iexsabado");
        String domingo = request.getParameter("iexdomingo");
        String flagasipag = request.getParameter("iexctlasipag");

        Integer turlunes = 999;
        Integer turmartes = 999;
        Integer turmiercoles = 999;
        Integer turjueves = 999;
        Integer turviernes = 999;
        Integer tursabado = 999;
        Integer turdomingo = 999;

        if (request.getParameter("iexlunes") == null) {
            lunes = "0";
        }

        if (request.getParameter("iexmartes") == null) {
            martes = "0";
        }

        if (request.getParameter("iexmiercoles") == null) {
            miercoles = "0";
        }

        if (request.getParameter("iexjueves") == null) {
            jueves = "0";
        }

        if (request.getParameter("iexviernes") == null) {
            viernes = "0";
        }

        if (request.getParameter("iexsabado") == null) {
            sabado = "0";
        }

        if (request.getParameter("iexdomingo") == null) {
            domingo = "0";
        }

        if (request.getParameter("iexturlun") != null) {
            turlunes = Integer.parseInt(request.getParameter("iexturlun").trim());
        } else {
            turlunes = 999;
        }

        if (request.getParameter("iexturmar") != null) {
            turmartes = Integer.parseInt(request.getParameter("iexturmar").trim());
        }

        if (request.getParameter("iexturmie") != null) {
            turmiercoles = Integer.parseInt(request.getParameter("iexturmie").trim());
        }

        if (request.getParameter("iexturjue") != null) {
            turjueves = Integer.parseInt(request.getParameter("iexturjue").trim());
        }

        if (request.getParameter("iexturvie") != null) {
            turviernes = Integer.parseInt(request.getParameter("iexturvie").trim());
        }

        if (request.getParameter("iextursab") != null) {
            tursabado = Integer.parseInt(request.getParameter("iextursab").trim());
        }

        if (request.getParameter("iexturdom") != null) {
            turdomingo = Integer.parseInt(request.getParameter("iexturdom").trim());
        }

        Empleado emp2 = new Empleado();

        emp2.setIextipturno(tipturno);
        emp2.setIexlunes(lunes);
        emp2.setIexmartes(martes);
        emp2.setIexmiercoles(miercoles);
        emp2.setIexjueves(jueves);
        emp2.setIexviernes(viernes);
        emp2.setIexsabado(sabado);
        emp2.setIexdomingo(domingo);
        emp2.setIexturlun(turlunes);
        emp2.setIexturmar(turmartes);
        emp2.setIexturmie(turmiercoles);
        emp2.setIexturjue(turjueves);
        emp2.setIexturvie(turviernes);
        emp2.setIextursab(tursabado);
        emp2.setIexturdom(turdomingo);
        emp2.setIexctlasipag(flagasipag);
        emp2.setIexcodcia(v_codcia);
        emp2.setIexcodtra(Integer.valueOf(idTrab));

        empleadoService.actualizarTurnos(emp2);

        return new ModelAndView("redirect:/gestionTiempo@" + idCompania);
    }


/*
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

