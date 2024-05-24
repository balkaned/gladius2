<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jan Quiroz
    Email	   : janquirozs@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script>
$(document).ready(function(){
    // Sub menu de detalle empleados
    $('#linav a').click(function(){
          $(this).addClass('activelsempl');
          var thisselc=this.id;
          sessionStorage.setItem("navempl",thisselc);
    });

    var navempl = sessionStorage.getItem("navempl");
    $('#'+navempl).addClass("activelsempl");
    $('#'+navempl+" div span").addClass("text-primary");
    document.getElementById(navempl).focus();

    /*if(menunav=="listEmpleados"){
        $('#'+navempl).addClass("activelsempl");
        document.getElementById(navempl).focus();
    }

    if(menunav!=null){
        document.getElementById(menunav).focus();
    }*/
});
</script>

<div id="otropciones" class="email-content scrollbar-overlay bg-white rounded-3 ps-3 pe-3 pt-2 border border-200">
  <div class="d-flex justify-content-between align-items-center">
    <p class="text-uppercase fs--2 text-600 mb-2 fw-bold">Menú de Opciones</p>
    <!--<button class="btn d-lg-none p-0 mb-2" data-phoenix-dismiss="offcanvas"><span class="uil uil-times fs-0"></span></button>-->
  </div>
  <ul class="nav flex-column border-top fs--1 vertical-nav mb-4">
    <li class="nav-item mt-1" id="linav"><a id="detalleEmpl" class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="detalleEmpl@${idTrab}">
        <div class="d-flex align-items-center" id="navsOpcionesEmpl"><span class="me-2 nav-icons uil uil-inbox"></span><span class="">Datos Personales</span><span class="nav-item-count"></span>
        </div>
      </a></li>
    <li class="nav-item mt-1" id="linav"><a id="sueldoFijo" class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="sueldoFijo@${idTrab}">
        <div class="d-flex align-items-center" id="navsOpcionesEmpl"><span class="me-2 nav-icons uil uil-bill"></span><span class="">Sueldos Fijos</span><span class="nav-item-count"></span>
        </div>
      </a></li>
    <li class="nav-item mt-1" id="linav"><a id="sueldoVariable" class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="sueldoVariable@${idTrab}">
        <div class="d-flex align-items-center" id="navsOpcionesEmpl"><span class="me-2 nav-icons uil uil-dollar-alt"></span><span class="">Sueldos Variables</span>
        </div>
      </a></li>
    <li class="nav-item mt-1" id="linav"><a id="vacaciones" class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="vacaciones@${idTrab}">
        <div class="d-flex align-items-center" id="navsOpcionesEmpl"><span class="me-2 nav-icons uil uil-calendar-alt"></span><span class="">Vacaciones</span>
        </div>
      </a>
    </li>
    <li class="nav-item mt-1" id="linav">
        <a id="ausentismo" class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="ausentismo@${idTrab}">
            <div class="d-flex align-items-center" id="navsOpcionesEmpl"><span class="me-2 nav-icons uil uil-assistive-listening-systems"></span><span class="">Ausentismo</span></div>
        </a>
    </li>
    <li class="nav-item mt-1" id="linav">
        <a id="contrato" class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="contrato@${idTrab}">
            <div class="d-flex align-items-center" id="navsOpcionesEmpl"><span class="me-2 nav-icons uil uil-clipboard"></span><span class="">Contrato</span></div>
        </a>
    </li>
    <li class="nav-item mt-1" id="linav">
        <a id="derechoHab" class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="derechoHab@${idTrab}">
            <div class="d-flex align-items-center" id="navsOpcionesEmpl"><span class="me-2 nav-icons uil uil-chat-bubble-user"></span><span class="">Derecho Habientes</span></div>
        </a>
    </li>
    <li class="nav-item mt-1" id="linav">
        <a id="retencionJud" class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="retencionJud@${idTrab}">
            <div class="d-flex align-items-center" id="navsOpcionesEmpl"><span class="me-2 nav-icons uil uil-balance-scale"></span><span class="">Retención Judicial</span></div>
        </a>
    </li>
    <li class="nav-item mt-1" id="linav">
        <a id="prestamos" class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="prestamos@${idTrab}">
            <div class="d-flex align-items-center" id="navsOpcionesEmpl"><span class="me-2 nav-icons uil uil-file-contract-dollar"></span><span class="">Préstamos</span></div>
        </a>
    </li>
    <li class="nav-item mt-1" id="linav">
        <a id="acumulado" class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="acumulado@${idTrab}">
            <div class="d-flex align-items-center" id="navsOpcionesEmpl"><span class="me-2 nav-icons uil uil-align-center-h"></span><span class="">Acumulado</span></div>
        </a>
    </li>
    <li class="nav-item mt-1" id="linav">
        <a id="gestionTiempo" class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="gestionTiempo@${idTrab}">
            <div class="d-flex align-items-center" id="navsOpcionesEmpl"><span class="me-2 nav-icons uil uil-clock"></span><span class="">Gestión de Tiempo</span></div>
        </a>
    </li>
    <li class="nav-item mt-1" id="linav">
        <a id="legajo" class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="legajo@${idTrab}">
            <div class="d-flex align-items-center" id="navsOpcionesEmpl"><span class="me-2 nav-icons uil uil-cloud-upload"></span><span class="">Legajo</span></div>
        </a>
    </li>
  </ul>
</div>