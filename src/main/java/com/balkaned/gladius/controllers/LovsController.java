package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.services.CompaniaService;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.services.UsuarioConeccionService;
import com.balkaned.gladius.services.VacacionesService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class LovsController {
    static Logger logger = Logger.getLogger(LovsController.class.getName());

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    VacacionesService vacacionesService;

    @RequestMapping(value="/getlovsDEPX", method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getlovsDEPX(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        logger.info("/getlovsDEPX");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String codpais=request.getParameter("codpais");
        logger.info("codpais: "+codpais);

        List<Lovs> listLovs=lovsService.getLovsDept("",codpais);

        String json = new Gson().toJson(listLovs);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping(value="/getlovsPROVX", method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getlovsPROVX(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        logger.info("/getlovsPROVX");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String coddept=request.getParameter("coddept");
        logger.info("coddept: "+coddept);

        List<Lovs> listLovs=lovsService.getLovsProv("",coddept);

        String json = new Gson().toJson(listLovs);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping(value="/getlovsDISTX", method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getlovsDISTX(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        logger.info("/getlovsDISTX");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String codprov=request.getParameter("codprov");
        logger.info("codprov: "+codprov);

        List<Lovs> listLovs=lovsService.getLovsDist("",codprov);

        String json = new Gson().toJson(listLovs);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping(value="/getlovsPROXCON",method= {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getlovsPROXCON(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("/getlovsPROXCON");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String iexcodreg=request.getParameter("iexcodreg");

        List<ProcesoPlanilla> listProRegimen=lovsService.getProxRegimen(iexcodreg);


        String json = new Gson().toJson(listProRegimen);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping(value="/getlovsPERX",method= {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getlovsPERX(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("/getlovsPERX");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String proceso = request.getParameter("iexcodpro");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        List<ProcesoPeriodo> listProPeriodo=lovsService.getPerxproc(idCompania,proceso);

        String json = new Gson().toJson(listProPeriodo);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }
    @RequestMapping(value="/getlovsLOVCODTRA",method= {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getlovsLOVCODTRA(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("/getlovsLOVCODTRA");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }
        String iexcodreg=request.getParameter("iexcodreg");

        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        List<Empleado> listProRegimen=lovsService.listaTrabajadoresReg(idCompania,iexcodreg);


        String json = new Gson().toJson(listProRegimen);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping(value="/getLovsLOVPERVAC",method= {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getLovsLOVPERVAC(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("/getLovsLOVPERVAC");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }


        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String xcodtra = request.getParameter("iexcodtra");
        Empleado empleado10 = new Empleado();
        empleado10.setIexcodtra(Integer.parseInt(xcodtra));
        vacacionesService.procesaVacacionCtl(empleado10);

        List<VacacionControl> listSalVacTras=lovsService.listaSaldoVacTra(idCompania,"01",Integer.parseInt(xcodtra));

        String json = new Gson().toJson(listSalVacTras);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping(value="/getLovsSALVACTRA",method= {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getLovsSALVACTRA(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("/getLovsSALVACTRA");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String xcodtra2 = request.getParameter("iexcodtra");
        String xpervac = request.getParameter("pervacini");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        List<VacacionControl> listSalVacTras=lovsService.getSaldoVacTra(idCompania,Integer.parseInt(xcodtra2),xpervac);

        String json = new Gson().toJson(listSalVacTras);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }



}

