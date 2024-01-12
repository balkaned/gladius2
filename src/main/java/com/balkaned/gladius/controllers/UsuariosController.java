package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Usuario;
import com.balkaned.gladius.beans.UsuarioConeccion;
import com.balkaned.gladius.beans.UsuxCompania;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Slf4j
public class UsuariosController {
    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    UsuxCompaniaService usuxCompaniaService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listUsuarios")
    public ModelAndView listUsuarios(ModelMap model, HttpServletRequest request) {
        log.info("/listUsuarios");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer pagina = 1;
        Integer numRegs = 15;
        String txtbuscar = "%";

        model.addAttribute("LstUsuario", usuarioService.listar("%", 1, 15));

        return new ModelAndView("public/gladius/configuracion/usuarios/listUsuarios");
    }

    @RequestMapping("/nuevoUsuario")
    public ModelAndView nuevoUsuario(ModelMap model, HttpServletRequest request) {
        log.info("/nuevoUsuario");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        return new ModelAndView("public/gladius/configuracion/usuarios/nuevoUsuario");
    }

    @RequestMapping("/insertarUsuario")
    public ModelAndView insertarUsuario(ModelMap model, HttpServletRequest request) {
        log.info("/insertarUsuario");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String email = (String) request.getSession().getAttribute("email");

        String Msg_form_global = "";
        String ret;
        String msg = "";

        String usuario2 = request.getParameter("txt_usuario");
        String password = request.getParameter("txt_password");
        String password2 = request.getParameter("txt_password2");
        String email2 = request.getParameter("txt_email");
        String estado = request.getParameter("lov_estado");

        String msg_txtusuario = "";
        String msg_txtpassword = "";
        String msg_txtemail = "";
        String msg_lovestado = "Correcto";
        String msg_frmstatus = "";
        int cont = 0;

        UsuarioConeccion uc = new UsuarioConeccion();
        uc.setUser(usuario2);

        UsuarioConeccion ucConsulta = usuarioConeccionService.obtenerUsuarioConeccionByName(uc);
        log.info("ucConsulta.getUser():" + ucConsulta.getUser());

        if (ucConsulta.getUser() == null) {
            if (password.equals(password2)) {
                msg_txtpassword = "Correcto";
                cont++;
            } else {
                msg = "El password de confirmación no es igual";
            }

            if (esEmailValida(email) == true) {
                cont++;
                msg_txtemail = "Correcto";
            } else {
                msg = "El formato de email no es correcto";
            }

            if (cont == 2) {
                log.info("contadorOK: " + cont);
                String foto = "";
                Integer codusumat = 1;

                Usuario p = new Usuario();
                p.setUsuario(usuario2);
                p.setPassword(password);
                p.setEstado(estado);
                p.setEmail(email);
                p.setUrlfoto(foto);
                p.setIdUsuMat(codusumat);

                usuarioService.insertar(p);

                return new ModelAndView("redirect:/listUsuarios");
            } else {
                model.addAttribute("msg", msg);
            }
        } else {
            msg = "El usuario ya existe en la Base de Datos ingresar otro nombre";
            model.addAttribute("msg", msg);
        }

        return new ModelAndView("public/gladius/configuracion/usuarios/nuevoUsuario");
    }

    protected static boolean esEmailValida(String email) {

        boolean valido = false;

        Pattern patronEmail = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");

        Matcher mEmail = patronEmail.matcher(email.toLowerCase());
        if (mEmail.matches()) {
            valido = true;
        }
        return valido;
    }

    @RequestMapping("/editarUsuario@{idUsu}")
    public ModelAndView editarUsuario(ModelMap model, HttpServletRequest request, @PathVariable String idUsu) {
        log.info("/editarUsuario");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idUsu", idUsu);
        Usuario usuariox = usuarioService.recuperar(Integer.valueOf(idUsu));
        model.addAttribute("usuariox", usuariox);

        return new ModelAndView("public/gladius/configuracion/usuarios/editarUsuario");
    }

    @RequestMapping("/modificarUsuario")
    public ModelAndView modificarUsuario(ModelMap model, HttpServletRequest request) {
        log.info("/modificarUsuario");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer codusuario = Integer.valueOf(request.getParameter("id_usuario"));
        String usuariox = request.getParameter("txt_usuario");
        String password = request.getParameter("txt_password");
        String emailx = request.getParameter("txt_email");
        //String urlfoto = request.getParameter("txt_urlfoto");
        String estado = request.getParameter("lov_estado");

        Usuario p = new Usuario();
        p.setIdUsuario(codusuario);
        p.setUsuario(usuariox);
        p.setPassword(password);
        p.setEmail(emailx);
        p.setEstado(estado);
        //p.setUrlfoto(urlfoto);
        p.setUrlfoto(null);

        usuarioService.actualizar(p);

        return new ModelAndView("redirect:/listUsuarios");
    }

    @RequestMapping("/asignarRolUs@{idUsu}")
    public ModelAndView asignarRolUs(ModelMap model, HttpServletRequest request, @PathVariable String idUsu) {
        log.info("/asignarRolUs");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idUsu", idUsu);
        model.addAttribute("usuxciaxrol", usuxCompaniaService.listar(Integer.valueOf(idUsu)));
        model.addAttribute("listacia", companiaService.listarTodo());
        model.addAttribute("listarol", rolService.listarRoles());

        return new ModelAndView("public/gladius/configuracion/usuarios/asignarRolUs");
    }

    @RequestMapping("/asignarRolxCiaIns")
    public ModelAndView asignarRolxCiaIns(ModelMap model, HttpServletRequest request) {
        log.info("/asignarRolxCiaIns");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer codusuario = Integer.valueOf(request.getParameter("usuario_id"));
        Integer lov_compania = Integer.valueOf(request.getParameter("lov_compania"));
        Integer lov_rol = Integer.valueOf(request.getParameter("lov_rol"));
        String codtra = request.getParameter("iexcodtra");

        Integer xcodtra = 0;
        if (codtra.equals("") || codtra == null) {
            xcodtra = 0;
        } else {
            xcodtra = Integer.parseInt(codtra);
        }

        UsuxCompania usuxcia = new UsuxCompania();
        usuxcia.setCodcia(lov_compania);
        usuxcia.setCodrol(lov_rol);
        usuxcia.setCodusu(codusuario);
        usuxcia.setCodtra(xcodtra);

        usuxCompaniaService.insertar(usuxcia);

        return new ModelAndView("redirect:/asignarRolUs@" + codusuario);
    }

    @RequestMapping("/eliminarRolXciaUsu@{idUsu}@{idRol}@{idCia}")
    public ModelAndView eliminarRolXciaUsu(ModelMap model, HttpServletRequest request,
                                           @PathVariable String idUsu,
                                           @PathVariable String idRol,
                                           @PathVariable String idCia) {
        log.info("/eliminarRolXciaUsu");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer codusuario = Integer.valueOf(idUsu);
        Integer lov_compania = Integer.valueOf(idCia);
        Integer lov_rol = Integer.valueOf(idRol);

        UsuxCompania usuxcia = new UsuxCompania();
        usuxcia.setCodcia(lov_compania);
        usuxcia.setCodrol(lov_rol);
        usuxcia.setCodusu(codusuario);

        usuxCompaniaService.eliminar(usuxcia);

        return new ModelAndView("redirect:/asignarRolUs@" + idUsu);
    }
}

