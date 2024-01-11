package com.balkaned.gladius;


import com.balkaned.gladius.beans.Compania;
import com.balkaned.gladius.beans.UsuarioConeccion;
import com.balkaned.gladius.beans.UsuxOpciones;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class IndexController {
    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    UsuxOpcionesService usuxOpcionesService;

    @Autowired
    Sessionattributes sessionattributes;

    @Autowired
    UsuxSystemaService usuxSystemaService;

    @RequestMapping("/login2")
    public ModelAndView login(ModelMap model, HttpServletRequest request) {

        UsuarioConeccion uc = new UsuarioConeccion();
        model.addAttribute("usuarioConeccion", uc);
        String tip = "";

        if (request.getSession().getAttribute("tiposession") == null) {
            tip = "0";
        } else {

            if (request.getSession().getAttribute("tiposession").equals("2")) {
                model.addAttribute("mensaje", "Contraseña Erronea");
            }

            if (request.getSession().getAttribute("tiposession").equals("3")) {
                model.addAttribute("mensaje", "Usuario o Contraseña Incorrecta");
            }

            if (request.getSession().getAttribute("tiposession").equals("4")) {
                model.addAttribute("mensaje", "No se puede Ingresar Campos Vacíos, por favor ingrese Usuario y Contraseña");
            }

            if (request.getSession().getAttribute("tiposession").equals("5")) {
                model.addAttribute("mensaje", "Alerta!, Hemos detectado que no es el Admnistrador de este software, "
                        + "Este software es un producto Licenciado y Registrado en Indecopi  Copyright© 2023 Balkaned www.balkaned.com Todos los derechos reservados. Derechos de autor. Todos los contenidos de este Sitio No se va a poder "
                        + "instalar puede conectar con Base de Datos Postgres, Mysql, SQl Server, revise que los parámetros de conexión en la aplicación "
                        + "para el archivo de configuración application.properties coincidan con la configuración del motor de base Datos, "
                        + "puede que la base de datos no este aún restaurada o el proceso de restauración falló, "
                        + "no olvide copiar el Driver de Conexión Postgres com.mysql.jdbc_5.1.5.jar con extensión jar, en la carpeta del Tomcat "
                        + "C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 7.0\\webapps\\Gladius\\WEB-INF\\lib\\ "
                        + "luego debe Parar y Volver a Desplegar el WAR, esta aplicación esta configurada para Apache Tomcat v7, en caso el problema persista consulte con el administrador del "
                        + "sistema balkanedperu@gmail.com o ebaldeonp@gmail.com");
            }

            if (request.getSession().getAttribute("tiposession").equals("6")) {
                model.addAttribute("mensaje", "Usuario no existe o esta desactivado o falta permisos");
            }
        }

        return new ModelAndView("public/login2a");
    }

    @RequestMapping("/verificarLogin2")
    public ModelAndView verificarLogin2(ModelMap model, HttpServletRequest request, @ModelAttribute("usuarioConeccion") UsuarioConeccion uc, BindingResult result, SessionStatus status) {

        UsuarioConeccion uc2 = usuarioConeccionService.obtenerUsuarioConeccionByName(uc);
        log.info("uc.getUser(): "+uc.getUser());
        log.info("uc2.getUser(): "+uc2.getUser());

        if (uc.getUser()==null || uc.getUser().equals("")) {
            log.info("Campos Vacios");
            request.getSession().setAttribute("tiposession", "4");

            return new ModelAndView("redirect:/login2");
        }

        if (uc2.getUser()==null) {
            log.info("Usuario o Contraseña Incorrecta");
            request.getSession().setAttribute("tiposession", "3");

            return new ModelAndView("redirect:/login2");
        }

        if (uc2.getUser().equals("sinbd")) {
            request.getSession().setAttribute("tiposession", "5");

            return new ModelAndView("redirect:/login2");
        }

        if (uc2.getPass().equals(uc.getPass())) {
            UsuarioConeccion uc3 = usuarioConeccionService.obtenerUsuarioConeccionById(uc2.getId_usuario());

            log.info("ID_Usuario: " + uc2.getId_usuario());
            log.info("uc3.getId_usuario(): " + uc3.getId_usuario());
            log.info("uc3.getUser: " + uc3.getUser());

            if (uc3.getUser() == null) {
                log.info("Usuario no existe o esta desactivado: ");
                request.getSession().setAttribute("tiposession", "6");

                return new ModelAndView("redirect:/login2");
            }

            char firstCharacter = uc3.getUser().charAt(0);
            char char1UpperCase = Character.toUpperCase(firstCharacter);
            String cast1 = String.valueOf(char1UpperCase);

            String nombre = uc3.getUser();
            String resultado = nombre.toUpperCase().charAt(0) + nombre.substring(1, nombre.length()).toLowerCase();

            //###### SETEAMOS VARIABLES DE SESION ###########
            request.getSession().setAttribute("user", resultado);
            request.getSession().setAttribute("idUser", uc3.getId_usuario());
            request.getSession().setAttribute("email", uc3.getEmail());
            request.getSession().setAttribute("firstCharacter", cast1);
            request.getSession().setAttribute("idCompania", uc3.getCodCia());
            request.getSession().setAttribute("tiposession", "1");
            request.getSession().setAttribute("nombrecomp", uc3.getDesCia());
            request.getSession().setAttribute("ruccomp", uc3.getRuccia());

            String sqlURL = "jdbc:postgresql://ec2-18-191-189-102.us-east-2.compute.amazonaws.com:5432/" + uc3.getSourceDes();

            String redirect = "redirect:/selcompanias@" + uc3.getId_usuario();
            return new ModelAndView(redirect);
        } else {
            log.info("Contraseña Erronea: ");
            request.getSession().setAttribute("tiposession", "2");

            return new ModelAndView("redirect:/login2");
        }
    }


    @RequestMapping(value = "/selcompanias@{idUser}", method = RequestMethod.GET)
    public ModelAndView selcompanias(ModelMap model, HttpServletRequest request, @PathVariable String idUser) {

        log.info("idUser: " + idUser);
        List<Compania> companiaList = usuarioConeccionService.listarCompaniasBycodUsu(idUser);

        model.addAttribute("compList", companiaList);
        model.addAttribute("schema", "dark");

        return new ModelAndView("public/ecompanias");
    }

    @RequestMapping(value = "/selcompaniasChange@{idUser}", method = RequestMethod.GET)
    public ModelAndView selcompaniasChange(ModelMap model, HttpServletRequest request, @PathVariable String idUser) {

        log.info("idUser: " + idUser);
        List<Compania> companiaList = usuarioConeccionService.listarCompaniasBycodUsu(idUser);

        model.addAttribute("compList", companiaList);
        model.addAttribute("schema", "dark");

        request.getSession().setAttribute("email", null);
        request.getSession().setAttribute("firstCharacter", null);
        request.getSession().setAttribute("idCompania", null);
        request.getSession().setAttribute("nombrecomp", null);
        request.getSession().setAttribute("ruccomp", null);

        return new ModelAndView("public/ecompanias");
    }

    @RequestMapping("/logoff")
    public ModelAndView logoff(ModelMap model, HttpServletRequest request) {

        request.getSession().setAttribute("user", null);
        request.getSession().setAttribute("idUser", null);
        request.getSession().setAttribute("email", null);
        request.getSession().setAttribute("firstCharacter", null);
        request.getSession().setAttribute("idCompania", null);
        request.getSession().setAttribute("nombrecomp", null);
        request.getSession().setAttribute("ruccomp", null);

        return new ModelAndView("redirect:/login2");
    }

    @RequestMapping("/home@{idComp}@{idUser}")
    public ModelAndView home(ModelMap model, HttpServletRequest request, @PathVariable String idComp, @PathVariable String idUser) {
        log.info("/home");
        log.info("idComp: " + idComp);
        log.info("idUser: " + idUser);

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        UsuarioConeccion uc1 = usuarioConeccionService.obtenerUsuarioConeccionById(idUser);
        Compania comp1 = companiaService.getCompaniaAll(Integer.parseInt(idComp));

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");

        request.getSession().setAttribute("idCompania", comp1.getIdCodcia());
        request.getSession().setAttribute("urlLogo", comp1.getUrlLogo());
        request.getSession().setAttribute("nombrecomp", comp1.getDescCia());
        request.getSession().setAttribute("ruccomp", comp1.getNroRuc());
        request.getSession().setAttribute("schema", comp1.getSchema());

        model.addAttribute("idComp", idComp);
        model.addAttribute("urlLogo", comp1.getUrlLogo());
        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", comp1.getDescCia());
        model.addAttribute("rucComp", comp1.getNroRuc());
        model.addAttribute("schema", comp1.getSchema());

        int idCompconv=Integer.parseInt(idComp);
        int idUsuarioconv=Integer.parseInt(idUser);

        List<UsuxOpciones> listaMenus = usuxOpcionesService.listarOpciones(idCompconv,idUsuarioconv,1);
        model.addAttribute("usuxsysxopc",listaMenus);
        model.addAttribute("ususys",usuxSystemaService.eligeSystema(idCompconv,idUsuarioconv,1));

        return new ModelAndView("public/kanban");
    }
}
