package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.UsuxOpciones;
import com.balkaned.gladius.services.UsuxOpcionesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class Sessionattributes {
    @Autowired
    UsuxOpcionesService usuxOpcionesService;

    public ModelAndView getVariablesSession(ModelMap model, HttpServletRequest request){

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
        log.info("idCompania: " + idCompania);

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        int idCompconv=idCompania;
        int idUsuarioconv=Integer.parseInt(idusuario);
        List<UsuxOpciones> listaMenus = usuxOpcionesService.listarOpciones(idCompconv,idUsuarioconv,1);
        model.addAttribute("usuxsysxopc",listaMenus);

        return null;
    }
}
