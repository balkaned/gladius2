package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.*;
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
import java.util.List;

@RestController
@Slf4j
public class GestionTiemposController {
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    LovsService lovsService;

    @Autowired
    TurnoDiarioService turnoDiarioService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/gestionTiempo@{idTrab}")
    public ModelAndView gestionTiempo(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/gestionTiempo");

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
        log.info("/grabarTurno");

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

    @RequestMapping("/listAsistencia")
    public ModelAndView listAsistencia(ModelMap model, HttpServletRequest request) {
        log.info("/listAsistencia");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

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
        log.info("/listarTurnodia");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");


        Empleado Empleado = new Empleado();
        Empleado = (Empleado) request.getSession(Boolean.parseBoolean("FichaEmpleado"));

        String v_fecini = request.getParameter("fecini");
        String v_fecfin = request.getParameter("fecfin");

        request.setAttribute("fecini", v_fecini);
        request.setAttribute("fecfin", v_fecfin);

        model.addAttribute("LstTurno", turnoDiarioService.listarTurnos(idCompania));
        model.addAttribute("lovTipTurno", lovsService.getLovs("69", "%"));
        model.addAttribute("xempxturno", empleadoService.recuperarTurnos(idCompania, Empleado.getIexcodtra()));

        if (v_fecini != null && v_fecfin != null) {
            model.addAttribute("LstTurnoDiario", turnoDiarioService.listarTurnoDia(idCompania, Empleado.getIexcodtra(), v_fecini, v_fecfin));
        }
        return new ModelAndView("public/gladius/gestionTiempo/asistencia/listAsistencia");
    }


    @RequestMapping("/listTurno")
    public ModelAndView lisTurno(ModelMap model, HttpServletRequest request) {
        log.info("/listTurno");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        Turno turn = new Turno();
        turn.setCodcia(idCompania);
        List<Turno> turnoList = turnoDiarioService.listarTurnos(idCompania);
        model.addAttribute("turnoList", turnoList);

        return new ModelAndView("public/gladius/gestionTiempo/turno/listTurno");

    }

    @RequestMapping("/nuevoTurno")
    public ModelAndView nuevoTurno(ModelMap model, HttpServletRequest request) {
        log.info("/nuevoTurno");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        return new ModelAndView("public/gladius/gestionTiempo/turno/nuevoTurno");
    }

    @RequestMapping("/insertarTurno")
    public ModelAndView insertarTurno(ModelMap model, HttpServletRequest request) {
        log.info("/insertarTurno");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

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
        log.info("/editarTurno");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        model.addAttribute("turno", turnoDiarioService.getTurno(idCompania, Integer.parseInt(idTurn)));

        return new ModelAndView("public/gladius/gestionTiempo/turno/editarTurno");
    }


    @RequestMapping("/modificarTurno")
    public ModelAndView modificarTurno(ModelMap model, HttpServletRequest request) {
        log.info("/modificarTurno");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

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
}

