package com.balkaned.gladius.auth;


import com.balkaned.gladius.companias.Domain.Compania;
import com.balkaned.gladius.models.*;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import com.balkaned.gladius.util.EncryptarMD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Slf4j
@RestController
public class LoginController {

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    Sessionattributes sessionattributes;

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
                model.addAttribute("mensaje", "Alerta!, Hemos detectado que no es el admnistrador de este software, "
                        + "Este software es un producto Licenciado y Registrado en Indecopi  Copyright© 2023 Balkaned www.balkaned.com Todos los derechos reservados. Derechos de autor. Todo el contenido de este sitio "
                        + "instalar puede conectar con base de datos Postgres, SQl Server, Oracle Database revise que los parámetros de conexión, "
                        + "para el archivo de configuración application.properties coincidan con la configuración del motor de base de datos, "
                        + "puede que la base de datos no este restaurada o el proceso de restauración falló, "
                        + "no olvide copiar el Driver de Conexión com.posgresql.jdbc_5.1.5.jar con extensión jar, en la carpeta de Tomcat "
                        + "C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 7.0\\webapps\\Gladius\\WEB-INF\\lib\\ "
                        + "luego debe parar y volver a desplegar el WAR, esta app esta configurada para Tomcat v9, en caso el problema persista consulte con el administrador del "
                        + "sistema balkanedperu@gmail.com o ebaldeonp@gmail.com, puedes visitar www.balkaned.com");
            }

            if (request.getSession().getAttribute("tiposession").equals("6")) {
                model.addAttribute("mensaje", "Usuario no existe o esta desactivado o falta permisos");
            }

            if (request.getSession().getAttribute("tiposession").equals("7")) {
                model.addAttribute("mensaje", "Falta asignar al usuario a alguna compañía");
            }
        }

        return new ModelAndView("public/login2a");
    }

    @RequestMapping("/verificarLogin2")
    public ModelAndView verificarLogin2(ModelMap model, HttpServletRequest request,
                                        @ModelAttribute("usuarioConeccion") UsuarioConeccion uc,
                                        BindingResult result, SessionStatus status) {

        UsuarioConeccion uc2 = usuarioConeccionService.obtenerUsuarioConeccionByName(uc);

        if (uc.getUser() == null || uc.getUser().equals("")) {
            log.info("Campos Vacios");
            request.getSession().setAttribute("tiposession", "4");

            return new ModelAndView("redirect:/login2");
        }

        if(uc2 == null){
            log.info("Usuario no existe o esta desactivado o falta permisos.");
            request.getSession().setAttribute("tiposession", "6");

            return new ModelAndView("redirect:/login2");
        }

        log.info("uc.getUser(): " + uc.getUser());
        log.info("uc2.getUser(): " + uc2.getUser());

        if (uc2.getUser() == null) {
            log.info("Usuario o Contraseña Incorrecta");
            request.getSession().setAttribute("tiposession", "3");

            return new ModelAndView("redirect:/login2");
        }

        if (uc2.getUser().equals("sinbd")) {
            log.info("Sin Base de datos.");
            request.getSession().setAttribute("tiposession", "5");

            return new ModelAndView("redirect:/login2");
        }

        log.info("uc2.getPass(): "+uc2.getPass());
        log.info("uc.getPass(): "+uc.getPass());

        /*EncryptarMD5 enc = new EncryptarMD5();
        log.info("decode1: "+enc.decodeMD5(uc2.getPass()));

        EncryptarMD5 enc2 = new EncryptarMD5();
        log.info("decode2: "+enc2.decodeMD5(uc.getPass()));*/

        EncryptarMD5 enc3 = new EncryptarMD5();
        String encondePassFromHtml = enc3.getMD5(uc.getPass());
        log.info("encondePassFromHtml: "+encondePassFromHtml);

        //if (uc2.getPass().equals(uc.getPass())) {
        if (uc2.getPass().equals(encondePassFromHtml)) {
            UsuarioConeccion uc3 = usuarioConeccionService.obtenerUsuarioConeccionById(uc2.getId_usuario());

            log.info("ID_Usuario: {} ", uc2.getId_usuario());
            log.info("uc3: {} ", uc3);

            /*if (uc3.getUser() == null) {
                log.info("Usuario no existe o esta desactivado: ");
                request.getSession().setAttribute("tiposession", "6");

                return new ModelAndView("redirect:/login2");
            }*/

            if (uc3 == null) {
                log.info("Falta asignar al usuario a alguna compañía: ");
                request.getSession().setAttribute("tiposession", "7");

                return new ModelAndView("redirect:/login2");
            }

            char firstCharacter = uc3.getUser().charAt(0);
            char char1UpperCase = Character.toUpperCase(firstCharacter);
            String cast1 = String.valueOf(char1UpperCase);

            String nombre = uc3.getUser();
            String resultado = nombre.toUpperCase().charAt(0) + nombre.substring(1, nombre.length()).toLowerCase();

            // Seteamos variables de session
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
        String usuario = (String) request.getSession().getAttribute("user");

        model.addAttribute("compList", companiaList);
        model.addAttribute("schema", "dark");
        model.addAttribute("usuario", usuario);

        return new ModelAndView("public/ecompanias");
    }

    @RequestMapping(value = "/selcompaniasChange@{idUser}", method = RequestMethod.GET)
    public ModelAndView selcompaniasChange(ModelMap model, HttpServletRequest request, @PathVariable String idUser) {

        log.info("idUser: " + idUser);
        List<Compania> companiaList = usuarioConeccionService.listarCompaniasBycodUsu(idUser);
        String usuario = (String) request.getSession().getAttribute("user");

        model.addAttribute("compList", companiaList);
        model.addAttribute("schema", "dark");

        //request.getSession().setAttribute("email", null);
        request.getSession().setAttribute("firstCharacter", null);
        request.getSession().setAttribute("idCompania", null);
        request.getSession().setAttribute("nombrecomp", null);
        request.getSession().setAttribute("ruccomp", null);

        model.addAttribute("usuario", usuario);
        String email = (String) request.getSession().getAttribute("email");
        model.addAttribute("email", email);

        return new ModelAndView("public/ecompanias");
    }

    @RequestMapping(value = "/ingresarCompania@{idCom}@{idUser}", method = RequestMethod.GET)
    public ModelAndView ingresarCompania(ModelMap model, HttpServletRequest request,
                                         @PathVariable Integer idCom,
                                         @PathVariable String idUser) {
        log.info("/ingresarCompania");

        log.info("idUser: " + idUser);
        log.info("idCom: "+idCom);

        request.getSession().setAttribute("idCompania", idCom);
        request.getSession().setAttribute("idUser", idUser);

        return new ModelAndView("redirect:/dashboard");
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
}
