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

@RestController
@Slf4j
public class RolesController {

    @Autowired
    RolService rolService;
    @Autowired
    OpcionService opcionService;
    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listRoles")
    public ModelAndView listRoles(ModelMap model, HttpServletRequest request) {
        log.info("/listRoles");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("LstRole", rolService.listarRoles());

        return new ModelAndView("public/gladius/configuracion/roles/listRoles");
    }

    @RequestMapping("/nuevoRol")
    public ModelAndView nuevoRol(ModelMap model, HttpServletRequest request) {
        log.info("/nuevoRol");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        return new ModelAndView("public/gladius/configuracion/roles/nuevoRol");
    }

    @RequestMapping("/insertarRol")
    public ModelAndView insertarRol(ModelMap model, HttpServletRequest request) {
        log.info("/insertarRol");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Role rol = new Role();
        rol.setIdRole(Integer.parseInt(request.getParameter("iexcodrol")));
        rol.setDesRole(request.getParameter("iexdesrol"));

        rolService.insertarRole(rol);

        return new ModelAndView("redirect:/listRoles");
    }

    @RequestMapping("/verOpcion@{idRol}")
    public ModelAndView verOpcion(ModelMap model, HttpServletRequest request,
                                  @PathVariable String idRol) {
        log.info("/verOpcion");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idRol", idRol);
        model.addAttribute("xRolxopc", rolService.listarRolesxOpcion(Integer.valueOf(idRol)));

        return new ModelAndView("public/gladius/configuracion/roles/listRolesxOpcion");
    }

    @RequestMapping("/nuevoRolxOpcion@{idRol}")
    public ModelAndView nuevoRolxOpcion(ModelMap model, HttpServletRequest request, @PathVariable String idRol) {
        log.info("/nuevoRolxOpcion");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idRol", idRol);
        model.addAttribute("lovOpcion", opcionService.listarOpciones());

        return new ModelAndView("public/gladius/configuracion/roles/nuevoRolxOpcion");
    }

    @RequestMapping("/insertarRolxOpcion@{idRol}")
    public ModelAndView insertarRolxOpcion(ModelMap model, HttpServletRequest request, @PathVariable String idRol) {
        log.info("/insertarRolxOpcion");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Rolesxopciones rolopc = new Rolesxopciones();
        rolopc.setIexcodrol(Integer.parseInt(request.getParameter("iexcodrol")));
        rolopc.setIexcodopc(Integer.parseInt(request.getParameter("iexcodopc")));
        rolopc.setIex_consultar(request.getParameter("iex_consultar"));
        rolopc.setIex_registrar(request.getParameter("iex_registrar"));
        rolopc.setIex_modificar(request.getParameter("iex_modificar"));
        rolopc.setIex_eliminar(request.getParameter("iex_eliminar"));
        rolopc.setIex_descargar_pdf(request.getParameter("iex_descargar_pdf"));
        rolopc.setIex_descargar_xls(request.getParameter("iex_descargar_xls"));
        rolopc.setIexflgest(request.getParameter("iexflgest"));
        rolopc.setIexusucre(usuario);

        rolService.insertarRolesxopciones(rolopc);

        return new ModelAndView("redirect:/verOpcion@" + idRol);
    }

    @RequestMapping("/editarOpcion@{idRol}")
    public ModelAndView editarOpcion(ModelMap model, HttpServletRequest request, @PathVariable String idRol) {
        log.info("/editarOpcion");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idRol", idRol);

        Role rol = new Role();
        rol.setIdRole(Integer.valueOf(idRol));
        Role rol2 = rolService.getRole(rol);
        model.addAttribute("rol", rol2);

        return new ModelAndView("public/gladius/configuracion/roles/editarRol");
    }

    @RequestMapping("/modificarRol")
    public ModelAndView modificarRol(ModelMap model, HttpServletRequest request) {
        log.info("/modificarRol");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Role rol = new Role();
        rol.setIdRole(Integer.parseInt(request.getParameter("iexcodrol")));
        rol.setDesRole(request.getParameter("iexdesrol"));
        rolService.actualizarRole(rol);

        return new ModelAndView("redirect:/listRoles");
    }

    @RequestMapping("/editarOpcionxRol@{idRolxOpc}@{idRol}")
    public ModelAndView editarOpcionxRol(ModelMap model, HttpServletRequest request,
                                         @PathVariable String idRolxOpc,
                                         @PathVariable String idRol) {
        log.info("/editarOpcionxRol");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idRolxOpc", idRolxOpc);
        model.addAttribute("idRol", idRol);

        Rolesxopciones rolopc = new Rolesxopciones();
        rolopc.setIexcodrol(Integer.valueOf(idRol));
        rolopc.setIexcodopc(Integer.valueOf(idRolxOpc));

        model.addAttribute("exRolxopc", rolService.getRolesxopciones(rolopc));
        model.addAttribute("iexcodrol", idRol);
        model.addAttribute("lovOpcion", opcionService.listarOpciones());

        return new ModelAndView("public/gladius/configuracion/roles/editarRolxOpcion");
    }

    @RequestMapping("/modificarRolxOpcion")
    public ModelAndView modificarRolxOpcion(ModelMap model, HttpServletRequest request) {
        log.info("/modificarRolxOpcion");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iexcodrol = request.getParameter("iexcodrol");

        Rolesxopciones rolopc = new Rolesxopciones();
        rolopc.setIexcodrol(Integer.valueOf(iexcodrol));
        rolopc.setIexcodopc(Integer.parseInt(request.getParameter("iexcodopc")));
        rolopc.setIex_consultar(request.getParameter("iex_consultar"));
        rolopc.setIex_registrar(request.getParameter("iex_registrar"));
        rolopc.setIex_modificar(request.getParameter("iex_modificar"));
        rolopc.setIex_eliminar(request.getParameter("iex_eliminar"));
        rolopc.setIex_descargar_pdf(request.getParameter("iex_descargar_pdf"));
        rolopc.setIex_descargar_xls(request.getParameter("iex_descargar_xls"));
        rolopc.setIexflgest(request.getParameter("iexflgest"));
        rolopc.setIexusumod(usuario);

        rolService.actualizarRolesxopciones(rolopc);

        return new ModelAndView("redirect:/verOpcion@" + iexcodrol);
    }

    @RequestMapping("/deleteRol@{idRol}")
    public ModelAndView deleteRol(ModelMap model, HttpServletRequest request, @PathVariable String idRol) {
        log.info("/deleteRol");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Role rol = new Role();
        rol.setIdRole(Integer.valueOf(idRol));

        rolService.eliminarRole(rol);

        return new ModelAndView("redirect:/listRoles");
    }

    @RequestMapping("/deleteOpcionxRol@{idOpc}@{idRol}")
    public ModelAndView deleteOpcionxRol(ModelMap model, HttpServletRequest request,
                                         @PathVariable String idOpc,
                                         @PathVariable String idRol) {
        log.info("/deleteOpcionxRol");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Rolesxopciones rolopc = new Rolesxopciones();
        rolopc.setIexcodrol(Integer.valueOf(idRol));
        rolopc.setIexcodopc(Integer.valueOf(idOpc));

        rolService.eliminarRolesxopciones(rolopc);

        return new ModelAndView("redirect:/verOpcion@" + idRol);
    }
}

