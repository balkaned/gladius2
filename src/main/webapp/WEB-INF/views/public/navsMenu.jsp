<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jean Quiroz
    Email	   : jeanp.quiroz@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    .navbar-vertical-label{
    color:#818CE4 !important;
    font-size:12px !important;
    }

    .nav-link-text{
    color:#B8BABF !important;
    font-size:14px !important;
    margin-left:10px !important;
    }

    .nav-link-text:hover{
        color:#111827 !important;
    }

    .nav-link.dropdown-indicator.label-1:hover{
     color:#111827 !important;
    }

    .nav-link-icon{
    color:#B8BABF !important;

    }

    .navbar-vertical-label-under{
    color:#B8BABF !important;
    font-size:12px;
    margin-left:32px;
    margin-top:-8px;
    }

    .btn.btn-primary{
    border-radius:30px !important;
    background-color:#4F46E5 !important;
    }

    .form-control.search-input.search{
    border-radius:30px !important;
    }

    .list{
    }

    .content{
    /*background-color:#F1F5F9;*/
    }

    .navbar-vertical-footer{
    background-color:#1E283D;
    color:white;
    border-top:1px solid #373E53 !important;
    }

    .btn.p-0.border.border-200.btn-support-chat{
    background-color:#EF5350 !important;
    color:white !important;
    }

    .btn.p-0.border.border-200.btn-support-chat span{
    color:white !important;
    }

    .svg-inline--fa.fa-circle.text-success.fs--1.ms-2{
    color:white!important;
    }

    .card-body.d-flex.align-items-center.px-2.py-1{
    background-color:#111827 !important;
    color:white !important;
    border-radius:17px 17px 0px 0px;
    }

    .icon-spin.position-absolute.all-0.d-flex.flex-center svg{
    color:white !important;
    }

    .text-uppercase.text-700.fw-bold.py-2.pe-2.ps-1.rounded-end{
    color:white!important;
    }

    #usermenu{
    float:left;
    border:0px solid red;
    height:200px;
    width:100%;
    }

    #span1{
    float:left;
    width:93px;
    height:90px;
    margin-left:80px;
    border:0px solid #FFB628;
    font-size:65px;
    font-weight:600;
    padding:0px 0px 0px 25px;
    background-color:orange;
    color:white;
    border-radius:130px;
    }

    #span2{
    float:left;
    text-align:center;
    margin-top:30px;
    width:100%;
    font-size:15px;
    color:white;
    border:0px solid blue;
    }

    #span3{
    float:left;
    text-align:center;
    width:100%;
    font-size:14px;
    color:#8C969C;
    font-weight:700;
    border:0px solid yellow;
    }

</style>

<nav class="navbar navbar-vertical navbar-expand-lg">
    <script>
      var navbarStyle = window.config.config.phoenixNavbarStyle;
      if (navbarStyle && navbarStyle !== 'transparent') {
        document.querySelector('body').classList.add(`navbar-${navbarStyle}`);
      }
    </script>
    <div class="collapse navbar-collapse" id="navbarVerticalCollapse">
      <!-- scrollbar removed-->
      <div class="navbar-vertical-content" style="background-color:#111827;">
        <ul class="navbar-nav flex-column" id="navbarVerticalNav">
          <li class="nav-item">
            <!-- parent pages-->
            <div id="usermenu">
                <span id="span1">E</span>
                <span id="span2">Enrique Baldeon</span>
                <span id="span3">ebaldeon@gmail.com</span>
            </div>

            <p class="navbar-vertical-label">Home</p>
            <p class="navbar-vertical-label-under">Apps Principales</p>
            <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#home" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="home">
                <div class="d-flex align-items-center">

                  <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="home"></span></span><span class="nav-link-text">Home</span>
                </div>
              </a>
              <div class="parent-wrapper label-1">

                <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="home">

                  <li class="collapsed-nav-item-title d-none">Home
                  </li>
                  <li class="nav-item"><a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">E commerce</span>
                      </div>
                    </a>
                    <!-- more inner pages-->
                  </li>
                  <li class="nav-item"><a class="nav-link" href="dashboard/project-management.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Project management</span>
                      </div>
                    </a>
                    <!-- more inner pages-->
                  </li>
                  <li class="nav-item"><a class="nav-link" href="dashboard/crm.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">CRM</span><span class="badge ms-2 badge badge-phoenix badge-phoenix-info ">New</span>
                      </div>
                    </a>
                    <!-- more inner pages-->
                  </li>
                  <li class="nav-item"><a class="nav-link" href="apps/social/feed.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Social feed</span>
                      </div>
                    </a>
                    <!-- more inner pages-->
                  </li>
                </ul>
              </div>
            </div>





            <p class="navbar-vertical-label">Configuración</p>
            <p class="navbar-vertical-label-under">Menus de Configuración</p>
            <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#conf" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="conf">
                <div class="d-flex align-items-center">

                  <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="settings"></span></span><span class="nav-link-text">Configuración</span>
                </div>
              </a>
              <div class="parent-wrapper label-1">

                <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="conf">

                  <li class="collapsed-nav-item-title d-none">Home</li>

                  <li class="nav-item">
                    <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Tablas Genericas</span></div>
                    </a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                        <div class="d-flex align-items-center"><span class="nav-link-text">Parametros</span></div>
                      </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Usuarios</span></div>
                    </a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                        <div class="d-flex align-items-center"><span class="nav-link-text">Compañias</span></div>
                      </a>
                  </li>
                  <li class="nav-item">
                        <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                          <div class="d-flex align-items-center"><span class="nav-link-text">Roles</span></div>
                        </a>
                  </li>
                  <li class="nav-item">
                          <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                            <div class="d-flex align-items-center"><span class="nav-link-text">Opciones</span></div>
                          </a>
                  </li>
                  <li class="nav-item">
                        <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                          <div class="d-flex align-items-center"><span class="nav-link-text">Secciones</span></div>
                        </a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                        <div class="d-flex align-items-center"><span class="nav-link-text">Sistemas</span></div>
                      </a>
                  </li>
                </ul>
              </div>
            </div>

            <p class="navbar-vertical-label">Conf. Planillas</p>
            <p class="navbar-vertical-label-under">Configuración de Planillas</p>
            <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#plan" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="plan">
                <div class="d-flex align-items-center">

                  <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="grid"></span></span><span class="nav-link-text">Conf. Planillas</span>
                </div>
              </a>
              <div class="parent-wrapper label-1">

                <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="plan">

                  <li class="collapsed-nav-item-title d-none">Home</li>

                  <li class="nav-item">
                    <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Conceptos</span></div>
                    </a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                        <div class="d-flex align-items-center"><span class="nav-link-text">Procesos y Form</span></div>
                      </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">C. Contables</span></div>
                    </a>
                  </li>
                </ul>
              </div>
            </div>


          <p class="navbar-vertical-label">Organización</p>
          <p class="navbar-vertical-label-under">Gestión de Empleados</p>
          <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#empl" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="empl">
              <div class="d-flex align-items-center">

                <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="users"></span></span><span class="nav-link-text">Gestión de Empleados</span>
              </div>
            </a>
            <div class="parent-wrapper label-1">

              <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="empl">

                <li class="collapsed-nav-item-title d-none">Home</li>

                <li class="nav-item">
                  <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Areas</span></div>
                  </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                  <div class="d-flex align-items-center"><span class="nav-link-text">Puestos</span></div>
                </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                <div class="d-flex align-items-center"><span class="nav-link-text">C. Costos</span></div>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                <div class="d-flex align-items-center"><span class="nav-link-text">Bancos</span></div>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                <div class="d-flex align-items-center"><span class="nav-link-text">Trabajadores</span></div>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                <div class="d-flex align-items-center"><span class="nav-link-text">Locales</span></div>
              </a>
            </li>
          </ul>
        </div>
      </div>

      <p class="navbar-vertical-label">Gestion de Tiempos</p>
      <p class="navbar-vertical-label-under">Asistencias y Vacaciones</p>
        <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#tiemp" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="tiemp">
            <div class="d-flex align-items-center">

              <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="clock"></span></span><span class="nav-link-text">Gestión de Tiempos</span>
            </div>
          </a>
          <div class="parent-wrapper label-1">

            <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="tiemp">

              <li class="collapsed-nav-item-title d-none">Home</li>

              <li class="nav-item">
                <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                  <div class="d-flex align-items-center"><span class="nav-link-text">Asistencia</span></div>
                </a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                <div class="d-flex align-items-center"><span class="nav-link-text">Gestions de Turno</span></div>
              </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
              <div class="d-flex align-items-center"><span class="nav-link-text">Gestion de Vacaciones</span></div>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
              <div class="d-flex align-items-center"><span class="nav-link-text">Gestion de Ausentismo</span></div>
            </a>
          </li>
        </ul>
      </div>
    </div>

    <p class="navbar-vertical-label">Gestion de Planillas</p>
          <p class="navbar-vertical-label-under">Afp, liquidaciones y reportes</p>
            <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#rep" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="rep">
                <div class="d-flex align-items-center">

                  <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="sliders"></span></span><span class="nav-link-text">Gestión de Planillas</span>
                </div>
              </a>
              <div class="parent-wrapper label-1">

                <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="rep">

                  <li class="collapsed-nav-item-title d-none">Home</li>

                  <li class="nav-item">
                    <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">AFP</span></div>
                    </a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Planillas</span></div>
                  </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                  <div class="d-flex align-items-center"><span class="nav-link-text">Liquidaciones</span></div>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                  <div class="d-flex align-items-center"><span class="nav-link-text">Provisiones</span></div>
                </a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Provisiones</span></div>
                  </a>
              </li>
              <li class="nav-item">
                    <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Reporte 5ta Nomina</span></div>
                    </a>
               </li>
              <li class="nav-item">
                  <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Reporte de Planillas</span></div>
                  </a>
              </li>
              <li class="nav-item">
                    <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Adelantos</span></div>
                    </a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Adelantos</span></div>
                  </a>
              </li>
              <li class="nav-item">
                    <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Adelantos</span></div>
                    </a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Gratificaciones</span></div>
                  </a>
              </li>
              <li class="nav-item">
                    <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Cts</span></div>
                    </a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Utilidades</span></div>
                  </a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Planilla General</span></div>
                  </a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Reporte Planilla x Concepto</span></div>
                  </a>
              </li>
              <li class="nav-item">
                    <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Reporte Nomina x Persona</span></div>
                    </a>
              </li>
            </ul>
          </div>
        </div>

        <p class="navbar-vertical-label">Gestion de Procesos Externos</p>
          <p class="navbar-vertical-label-under">Plame y AfpNet</p>
            <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#ext" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="ext">
                <div class="d-flex align-items-center">

                  <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="layers"></span></span><span class="nav-link-text">Gestión de Procesos Externos</span>
                </div>
              </a>
              <div class="parent-wrapper label-1">

                <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="ext">

                  <li class="collapsed-nav-item-title d-none">Home</li>

                  <li class="nav-item">
                    <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Plame</span></div>
                    </a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Afp Net</span></div>
                  </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                  <div class="d-flex align-items-center"><span class="nav-link-text">Asientos Contables</span></div>
                </a>
              </li>

            </ul>
          </div>
        </div>

        <p class="navbar-vertical-label">Alquileres</p>
                  <p class="navbar-vertical-label-under">Contratos de Alquileres, recibos</p>
                    <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#alq" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="alq">
                        <div class="d-flex align-items-center">

                          <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="codesandbox"></span></span><span class="nav-link-text">Alquileres</span>
                        </div>
                      </a>
                      <div class="parent-wrapper label-1">

                        <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="alq">

                          <li class="collapsed-nav-item-title d-none">Home</li>

                          <li class="nav-item">
                            <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                              <div class="d-flex align-items-center"><span class="nav-link-text">Gestión de Clientes</span></div>
                            </a>
                          </li>
                          <li class="nav-item">
                              <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                            <div class="d-flex align-items-center"><span class="nav-link-text">Gestión de Prodcutos</span></div>
                          </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                          <div class="d-flex align-items-center"><span class="nav-link-text">Contrato Alquileres</span></div>
                        </a>
                      </li>
                      <li class="nav-item">
                          <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                            <div class="d-flex align-items-center"><span class="nav-link-text">Generar Recibos</span></div>
                          </a>
                        </li>
                      <li class="nav-item">
                        <a class="nav-link" href="index.html" data-bs-toggle="" aria-expanded="false">
                          <div class="d-flex align-items-center"><span class="nav-link-text">Reporte de Recibos</span></div>
                        </a>
                      </li>

                    </ul>
                  </div>
                </div>
        </ul>
      </div>
    </div>
    <div class="navbar-vertical-footer">
      <button class="btn navbar-vertical-toggle border-0 fw-semi-bold w-100 white-space-nowrap d-flex align-items-center"><span class="uil uil-left-arrow-to-left fs-0" style="color:white;"></span><span class="uil uil-arrow-from-right fs-0" style="color:white;"></span><span class="navbar-vertical-footer-text ms-2" style="color:white;">Collapsed View</span></button>
    </div>
</nav>
