package com.balkaned.gladius;

import com.balkaned.gladius.beans.UsuarioConeccion;
import com.balkaned.gladius.services.UsuarioConeccionService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IndexController {

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @RequestMapping("/login")
    public ModelAndView login(ModelMap model, HttpServletRequest request) {

        UsuarioConeccion uc = new UsuarioConeccion();
        model.addAttribute("usuarioConeccion", uc);
        String tip="";

        String sqlURL = "jdbc:postgresql://ec2-18-191-189-102.us-east-2.compute.amazonaws.com:5432/gladius_autenticacion";
        model.addAttribute("sqlURL",sqlURL);

        if(request.getSession().getAttribute("tiposession")==null){
            tip="0";
        }else {

            if(request.getSession().getAttribute("tiposession").equals("2")) {
                model.addAttribute("mensaje","Contraseña Erronea");
            }

            if(request.getSession().getAttribute("tiposession").equals("3")) {
                model.addAttribute("mensaje","Usuario o Contraseña Incorrecta");
            }

            if(request.getSession().getAttribute("tiposession").equals("4")) {
                model.addAttribute("mensaje","No se puede Ingresar Campos Vacíos, por favor ingrese Usuario y Contraseña");
            }

            if(request.getSession().getAttribute("tiposession").equals("5")) {
                model.addAttribute("mensaje","Alerta!, Hemos detectado que no es el Admnistrador de la aplicación. "
                        + "Este software es un producto Licenciado y Registrado en Indecopi  Copyright© 2019 Balkaned www.balkaned.com Todos los derechos reservados. Derechos de autor. Todos los contenidos de este Sitio No se va a poder "
                        + "instalar puede conectar con Base de Datos Postgres(por defecto), Mysql, SQl Server, revise que los parámetros de conexión en la aplicación "
                        + "para el archivo de configuración application.properties coincidan con la configuración del motor de base Datos, "
                        + "puede que la base de datos no este aún restaurada o el proceso de restauración falló, "
                        + "no olvide copiar el Driver de Conexión Ojo! Postgres com.mysql.jdbc_5.1.5.jar con extensión jar, en la carpeta del Tomcat "
                        + "C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 7.0\\webapps\\Gladius\\WEB-INF\\lib\\ "
                        + "luego debe Parar y Volver a Desplegar el WAR, esta aplicación esta configurada para Apache Tomcat v7, en caso el problema persista consulte con el administrador del "
                        + "sistema balkanedperu@gmail.com.");
            }
        }

        return new ModelAndView("public/pages/authentication/card/sign-in");
    }

    @RequestMapping("/index")
    public ModelAndView index(ModelMap model, HttpServletRequest request) {
        return new ModelAndView("public/index");
    }

    @RequestMapping("/verificarLogin")
    public ModelAndView verificarLogin(ModelMap model, HttpServletRequest request,@ModelAttribute("usuarioConeccion") UsuarioConeccion uc,BindingResult result,SessionStatus status) {

        UsuarioConeccion uc2=usuarioConeccionService.obtenerUsuarioConeccionByName(uc);

        if(uc2.getUser().equals("sinbd")) {
            request.getSession().setAttribute("tiposession", "5");

            return new ModelAndView("redirect:/api/login");
        }else {
            if(uc.getUser().equals("")) {
                System.out.println("Campos Vacios");
                request.getSession().setAttribute("tiposession", "4");
                //model.addAttribute("mensaje","Contraseña Erronea");

                return new ModelAndView("redirect:/api/login");
            }else {
                if(uc2.getUser().equals("noecontrado")){
                    System.out.println("Usuario o Contraseña Incorrecta");
                    request.getSession().setAttribute("tiposession", "3");
                    //model.addAttribute("mensaje","Usuario o Contraseña Incorrecta");

                    return new ModelAndView("redirect:/api/login");
                }else{
                    if(uc2.getPass().equals(uc.getPass())){

                        UsuarioConeccion uc3=usuarioConeccionService.obtenerUsuarioConeccionById(uc2.getId_usuario());
                        System.out.println("ID_Usuario: "+uc2.getId_usuario());

                        request.getSession().setAttribute("user", uc3.getUser());
                        request.getSession().setAttribute("idUser", uc3.getId_usuario());
                        //request.getSession().setAttribute("menuResponsiveCell", "");
                        request.getSession().setAttribute("tiposession", "1");
                        //request.getSession().setAttribute("iconMenu", "fa fa-arrow-left");

                        System.out.println("User: "+uc3.getUser());
                        System.out.println("Company: "+uc3.getDesCia());
                        System.out.println("Email: "+uc3.getEmail());
                        System.out.println("BdClientConnection: "+uc3.getSourceDes());

                        String sqlURL = "jdbc:postgresql://ec2-18-191-189-102.us-east-2.compute.amazonaws.com:5432/gladius05";
                        model.addAttribute("sqlURL",sqlURL);

                        return new ModelAndView("redirect:/api/index");
                    }else{
                        System.out.println("Contraseña Erronea");
                        request.getSession().setAttribute("tiposession", "2");
                        //model.addAttribute("mensaje","Contraseña Erronea");

                        return new ModelAndView("redirect:/api/login");
                    }
                }
            }
        }
    }
}
