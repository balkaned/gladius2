<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jan Quiroz
    Email	   : janquirozs@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <nav id="navbar2a" class="navbar navbar-vertical navbar-expand-lg border-end border-500 border-1" >
        <script>
          var navbarStyle = window.config.config.phoenixNavbarStyle;
          if (navbarStyle && navbarStyle !== 'transparent') {
            document.querySelector('body').classList.add(`navbar-${navbarStyle}`);
          }

          var flag=0;
          var seleccion="";

          $(document).ready(function(){
             // Menu lateral general
             $('#menunav a').click(function(){
                  $(this).addClass('active');
                  var thisselc=this.id;
                  sessionStorage.setItem("menunav",thisselc);
             });

             var menunav = sessionStorage.getItem("menunav");
             $('#'+menunav).addClass("active");
          });
        </script>
        <div class="collapse navbar-collapse" id="navbarVerticalCollapse">
          <!-- scrollbar removed-->
          <div class="navbar-vertical-content">
            <ul class="navbar-nav flex-column" id="navbarVerticalNav">
              <li class="nav-item">
                <!-- parent pages-->
                <!--<div id="usermenu">
                    <img id="span1" src="verFoto@LOGO@${idComp}@${urlLogo}"></img>
                    <span id="span2">${usuario}</span>
                    <span id="span3">${email}</span>
                </div>-->

                <p class="navbar-vertical-label">Dashboard</p>
                <p id="p2" class="navbar-vertical-label">Menu Dashboard</p>
                <div class="nav-item-wrapper">
                    <a class="nav-link dropdown-indicator label-1" href="dashboard" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="">
                        <div class="d-flex align-items-center">
                          <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="home"></span></span><span class="nav-link-text">Dashboard</span>
                        </div>
                    </a>
                    <div class="parent-wrapper label-1">
                        <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="">
                          <li class="collapsed-nav-item-title d-none">Dashboard</li>

                          <li id="menunav" class="nav-item">
                            <a class="nav-link" id="#" href="dashboard" data-bs-toggle="" aria-expanded="false">
                              <div class="d-flex align-items-center">
                                <span class="nav-link-text"><span class="fa fa-tachometer fs--1 me-2"></span>Dashboard</span>
                                <!--<span id="circle" class="fa-solid fa-circle text-success ms-1 new-page-indicator" style="font-size: 6px; display:none;"></span>-->
                              </div>
                            </a>
                          </li>
                        </ul>
                    </div>
                </div>

                <!--Empiezo a mostrar los menus segun la bd perfiles y roles de usuario-->
                <c:set var="seccion_cur" value=""/>
                <c:set var="init" value="0"/>
                <c:forEach var="usuxsysxopc" items="${requestScope.usuxsysxopc}">
                    <c:if test="${usuxsysxopc.codsec != seccion_cur}">
                        <c:if test="${init > 0}">
                                                    </ul>
                                                </div>
                                            </div>
                        </c:if>
                    </c:if>
                    <c:if test="${usuxsysxopc.codsec != seccion_cur}">
                            <p class="navbar-vertical-label">${usuxsysxopc.dessecCapi}</p>
                            <p id="p2" class="navbar-vertical-label">Menu de ${usuxsysxopc.dessecCapi}</p>
                            <div class="nav-item-wrapper">
                                <a class="nav-link dropdown-indicator label-1" href="#${usuxsysxopc.codsec}" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="${usuxsysxopc.dessecCapi}">
                                    <div class="d-flex align-items-center">
                                      <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="${usuxsysxopc.icon}"></span></span><span class="nav-link-text">${usuxsysxopc.dessecCapi}</span>
                                    </div>
                                </a>
                                <div class="parent-wrapper label-1">
                                    <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="${usuxsysxopc.codsec}">
                                          <li class="collapsed-nav-item-title d-none">${usuxsysxopc.dessecCapi}</li>
                    </c:if>

                                          <li id="menunav" class="nav-item">
                                            <a class="nav-link" id="${usuxsysxopc.path}" href="${usuxsysxopc.path}" data-bs-toggle="" aria-expanded="false">
                                              <div class="d-flex align-items-center">
                                                <span class="nav-link-text"><span class="${usuxsysxopc.urlimg} fs--1 me-2"></span>${usuxsysxopc.desopc}</span>
                                                <!--<span id="circle${usuxsysxopc.path}" class="fa-solid fa-circle text-success ms-1 new-page-indicator" style="font-size: 6px; display:none;"></span>-->
                                              </div>
                                            </a>
                                          </li>

                    <c:set var="seccion_cur" value="${usuxsysxopc.codsec}" />
                    <c:set var="init" value="1" />
                </c:forEach>
        </div>

        <div class="navbar-vertical-footer ">
          <a id="btnMenuCollap" class="btn navbar-vertical-toggle fw-semi-bold w-100 white-space-nowrap d-flex align-items-center">
            <span class="uil uil-left-arrow-to-left fs-0"></span>
            <span class="uil uil-arrow-from-right fs-0"></span>
            <span class="navbar-vertical-footer-text ms-2">Collapsed View</span>
          </a>
        </div>
    </nav>
