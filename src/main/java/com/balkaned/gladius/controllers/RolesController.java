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
import java.util.logging.Logger;

@RestController
public class RolesController {
    static Logger logger = Logger.getLogger(RolesController.class.getName());

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    RolService rolService;
    @Autowired
    OpcionService opcionService;

    @RequestMapping("/listRoles")
    public ModelAndView listRoles(ModelMap model, HttpServletRequest request) {
        logger.info("/listRoles");

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

        logger.info("################### idCompania: "+idCompania);

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);
        model.addAttribute("idComp",idCompania);
        model.addAttribute("urlLogo",urlLogo);

        model.addAttribute("LstRole",rolService.listarRoles());

        return new ModelAndView("public/gladius/configuracion/roles/listRoles");
    }

    @RequestMapping("/nuevoRol")
    public ModelAndView nuevoRol(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevoRol");
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

        return new ModelAndView("public/gladius/configuracion/roles/nuevoRol");
    }

    @RequestMapping("/insertarRol")
    public ModelAndView insertarRol(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarRol");
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

        Role rol  = new Role();
        rol.setIdRole(Integer.parseInt(request.getParameter("iexcodrol")));
        rol.setDesRole(request.getParameter("iexdesrol"));

        rolService.insertarRole(rol);

        return new ModelAndView("redirect:/listRoles");
    }

    @RequestMapping("/verOpcion@{idRol}")
    public ModelAndView verOpcion(ModelMap model, HttpServletRequest request,
                                   @PathVariable String idRol){
        logger.info("/verOpcion");

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

        model.addAttribute("idRol",idRol);

        model.addAttribute("xRolxopc",rolService.listarRolesxOpcion(Integer.valueOf(idRol)));

        return new ModelAndView("public/gladius/configuracion/roles/listRolesxOpcion");
    }

    @RequestMapping("/nuevoRolxOpcion@{idRol}")
    public ModelAndView nuevoRolxOpcion(ModelMap model, HttpServletRequest request, @PathVariable String idRol) {
        logger.info("/nuevoRolxOpcion");

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

        model.addAttribute("idRol",idRol);

        model.addAttribute("lovOpcion",opcionService.listarOpciones());

        return new ModelAndView("public/gladius/configuracion/roles/nuevoRolxOpcion");
    }

    @RequestMapping("/insertarRolxOpcion@{idRol}")
    public ModelAndView insertarRolxOpcion(ModelMap model, HttpServletRequest request,@PathVariable String idRol) {
        logger.info("/insertarRolxOpcion");
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

        Rolesxopciones rolopc = new  Rolesxopciones();
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

        return new ModelAndView("redirect:/verOpcion@"+idRol);
    }

    @RequestMapping("/editarOpcion@{idRol}")
    public ModelAndView editarOpcion(ModelMap model, HttpServletRequest request, @PathVariable String idRol) {
        logger.info("/editarOpcion");

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

        model.addAttribute("idRol",idRol);

        Role rol = new Role();
        rol.setIdRole(Integer.valueOf(idRol));
        Role rol2=rolService.getRole(rol);
        model.addAttribute("rol",rol2);

        return new ModelAndView("public/gladius/configuracion/roles/editarRol");
    }

    @RequestMapping("/modificarRol")
    public ModelAndView modificarRol(ModelMap model, HttpServletRequest request) {
        logger.info("/modificarRol");
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

        Role rol  = new Role();
        rol.setIdRole(Integer.parseInt(request.getParameter("iexcodrol")));
        rol.setDesRole(request.getParameter("iexdesrol"));
        rolService.actualizarRole(rol);

        return new ModelAndView("redirect:/listRoles");
    }

    @RequestMapping("/editarOpcionxRol@{idRolxOpc}@{idRol}")
    public ModelAndView editarOpcionxRol(ModelMap model, HttpServletRequest request,
                                         @PathVariable String idRolxOpc,
                                         @PathVariable String idRol) {
        logger.info("/editarOpcionxRol");

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
        model.addAttribute("idRolxOpc",idRolxOpc);
        model.addAttribute("idRol",idRol);

        Rolesxopciones rolopc = new  Rolesxopciones();
        rolopc.setIexcodrol(Integer.valueOf(idRol));
        rolopc.setIexcodopc(Integer.valueOf(idRolxOpc));

        model.addAttribute("exRolxopc",rolService.getRolesxopciones(rolopc));
        model.addAttribute("iexcodrol", idRol);
        model.addAttribute("lovOpcion",opcionService.listarOpciones());

        return new ModelAndView("public/gladius/configuracion/roles/editarRolxOpcion");
    }

    @RequestMapping("/modificarRolxOpcion")
    public ModelAndView modificarRolxOpcion(ModelMap model, HttpServletRequest request) {
        logger.info("/modificarRolxOpcion");
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

        String iexcodrol=request.getParameter("iexcodrol");

        Rolesxopciones rolopc = new  Rolesxopciones();
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

        return new ModelAndView("redirect:/verOpcion@"+iexcodrol);
    }

}

