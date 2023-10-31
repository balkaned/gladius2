<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jean Quiroz
    Email	   : jeanp.quiroz@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <!--<nav class="navbar navbar-vertical navbar-expand-lg bg-${schema}">-->
    <nav class="navbar navbar-vertical navbar-expand-lg">
        <script>
          var navbarStyle = window.config.config.phoenixNavbarStyle;
          if (navbarStyle && navbarStyle !== 'transparent') {
            document.querySelector('body').classList.add(`navbar-${navbarStyle}`);
          }

          var flag=0;
          $(document).ready(function(){
             $("#btnMenuCollapse").click(function(){

                    if(flag==0){
                        $('#usermenu').addClass('collapsedspan');
                        $('#span1').addClass('collapsedspan');
                        $('#span2').addClass('collapsedspan');
                        $('#span3').addClass('collapsedspan');
                        //$('#navbarVerticalCollapse').addClass('expandedmenu');
                        flag=1;
                    }else{
                        $('#usermenu').removeClass('collapsedspan');
                        $('#span1').removeClass('collapsedspan');
                        $('#span2').removeClass('collapsedspan');
                        $('#span3').removeClass('collapsedspan');
                        //$('#navbarVerticalCollapse').removeClass('expandedmenu');
                        //$('#navbarVerticalCollapse').addClass('resizemenu');
                        flag=0;
                    }
             });
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

                <p class="navbar-vertical-label">Configuración</p>
                <p id="p2" class="navbar-vertical-label">Menus de Configuración</p>
                <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#conf" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="conf">
                    <div class="d-flex align-items-center">

                      <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="settings"></span></span><span class="nav-link-text">Configuración</span>
                    </div>
                  </a>
                  <div class="parent-wrapper label-1">

                    <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="conf">

                      <li class="collapsed-nav-item-title d-none">Configuración</li>

                      <li class="nav-item">
                        <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                          <div class="d-flex align-items-center"><span class="nav-link-text">Tablas Genericas</span></div>
                        </a>
                      </li>
                      <li class="nav-item">
                          <a class="nav-link" href="listParametros" data-bs-toggle="" aria-expanded="false">
                            <div class="d-flex align-items-center"><span class="nav-link-text">Parametros</span></div>
                          </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="listUsuarios" data-bs-toggle="" aria-expanded="false">
                          <div class="d-flex align-items-center"><span class="nav-link-text">Usuarios</span></div>
                        </a>
                      </li>
                      <li class="nav-item">
                          <a class="nav-link" href="listCompanias" data-bs-toggle="" aria-expanded="false">
                            <div class="d-flex align-items-center"><span class="nav-link-text">Compañias</span></div>
                          </a>
                      </li>
                      <li class="nav-item">
                            <a class="nav-link" href="listRoles" data-bs-toggle="" aria-expanded="false">
                              <div class="d-flex align-items-center"><span class="nav-link-text">Roles</span></div>
                            </a>
                      </li>
                      <li class="nav-item">
                              <a class="nav-link" href="listOpciones" data-bs-toggle="" aria-expanded="false">
                                <div class="d-flex align-items-center"><span class="nav-link-text">Opciones</span></div>
                              </a>
                      </li>
                      <li class="nav-item">
                            <a class="nav-link" href="listSecciones" data-bs-toggle="" aria-expanded="false">
                              <div class="d-flex align-items-center"><span class="nav-link-text">Secciones</span></div>
                            </a>
                      </li>
                      <li class="nav-item">
                          <a class="nav-link" href="listSistemas" data-bs-toggle="" aria-expanded="false">
                            <div class="d-flex align-items-center"><span class="nav-link-text">Sistemas</span></div>
                          </a>
                      </li>
                    </ul>
                  </div>
                </div>

                <p class="navbar-vertical-label">Conf. Planillas</p>
                <p id="p2" class="navbar-vertical-label">Configuración de Planillas</p>
                <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#plan" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="plan">
                    <div class="d-flex align-items-center">

                      <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="grid"></span></span><span class="nav-link-text">Conf. Planillas</span>
                    </div>
                  </a>
                  <div class="parent-wrapper label-1">

                    <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="plan">

                      <li class="collapsed-nav-item-title d-none">Conf. Planillas</li>

                      <li class="nav-item">
                        <a class="nav-link" href="listConceptos" data-bs-toggle="" aria-expanded="false">
                          <div class="d-flex align-items-center"><span class="nav-link-text">Conceptos</span></div>
                        </a>
                      </li>
                      <li class="nav-item">
                          <a class="nav-link" href="listProcesoFormulas" data-bs-toggle="" aria-expanded="false">
                            <div class="d-flex align-items-center"><span class="nav-link-text">Procesos y Form</span></div>
                          </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                          <div class="d-flex align-items-center"><span class="nav-link-text">C. Contables</span></div>
                        </a>
                      </li>
                    </ul>
                  </div>
                </div>

              <p class="navbar-vertical-label">Organización</p>
              <p id="p2" class="navbar-vertical-label">Gestión de Empleados</p>
              <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#empl" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="empl">
                  <div class="d-flex align-items-center">

                    <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="users"></span></span><span class="nav-link-text">Gestión de Empleados</span>
                  </div>
                </a>
                <div class="parent-wrapper label-1">

                <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="empl">

                <li class="collapsed-nav-item-title d-none">Organización</li>

                <li class="nav-item">
                  <a class="nav-link" href="listAreas" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Areas</span></div>
                  </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="listPuestos" data-bs-toggle="" aria-expanded="false">
                        <div class="d-flex align-items-center"><span class="nav-link-text">Puestos</span></div>
                    </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="listCcostos" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">C. Costos</span></div>
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="listBancos" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Bancos</span></div>
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="listEmpleados" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Trabajadores</span></div>
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="listLocales" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Locales</span></div>
                  </a>
                </li>
              </ul>
            </div>
          </div>

          <p class="navbar-vertical-label">Gestion de Tiempos</p>
          <p id="p2" class="navbar-vertical-label">Asistencias y Vacaciones</p>
            <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#tiemp" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="tiemp">
                <div class="d-flex align-items-center">

                  <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="clock"></span></span><span class="nav-link-text">Gestión de Tiempos</span>
                </div>
              </a>
              <div class="parent-wrapper label-1">

                <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="tiemp">

                  <li class="collapsed-nav-item-title d-none">Gestion de Tiempos</li>

                  <li class="nav-item">
                    <a class="nav-link" href="" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Asistencia</span></div>
                    </a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="listTurno" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Gestions de Turno</span></div>
                  </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                  <div class="d-flex align-items-center"><span class="nav-link-text">Gestion de Vacaciones</span></div>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                  <div class="d-flex align-items-center"><span class="nav-link-text">Gestion de Ausentismo</span></div>
                </a>
              </li>
            </ul>
          </div>
        </div>

        <p class="navbar-vertical-label">Gestión de Planillas</p>
        <p id="p2" class="navbar-vertical-label">Afp, liquidaciones y reportes</p>
            <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#rep" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="rep">
                <div class="d-flex align-items-center">

                  <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="sliders"></span></span><span class="nav-link-text">Gestión de Planillas</span>
                </div>
              </a>
              <div class="parent-wrapper label-1">

                <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="rep">

                  <li class="collapsed-nav-item-title d-none">Gestión de Planillas</li>

                  <li class="nav-item">
                    <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">AFP</span></div>
                    </a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Planillas</span></div>
                  </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                  <div class="d-flex align-items-center"><span class="nav-link-text">Liquidaciones</span></div>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                  <div class="d-flex align-items-center"><span class="nav-link-text">Provisiones</span></div>
                </a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Provisiones</span></div>
                  </a>
              </li>
              <li class="nav-item">
                    <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Reporte 5ta Nomina</span></div>
                    </a>
               </li>
              <li class="nav-item">
                  <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Reporte de Planillas</span></div>
                  </a>
              </li>
              <li class="nav-item">
                    <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Adelantos</span></div>
                    </a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Adelantos</span></div>
                  </a>
              </li>
              <li class="nav-item">
                    <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Adelantos</span></div>
                    </a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Gratificaciones</span></div>
                  </a>
              </li>
              <li class="nav-item">
                    <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Cts</span></div>
                    </a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Utilidades</span></div>
                  </a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Planilla General</span></div>
                  </a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Reporte Planilla x Concepto</span></div>
                  </a>
              </li>
              <li class="nav-item">
                    <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Reporte Nomina x Persona</span></div>
                    </a>
              </li>
            </ul>
          </div>
        </div>

        <p class="navbar-vertical-label">Gestión de Procesos Externos</p>
        <p id="p2" class="navbar-vertical-label">Plame y AfpNet</p>
            <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#ext" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="ext">
                <div class="d-flex align-items-center">

                  <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="layers"></span></span><span class="nav-link-text">Gestión de Procesos Externos</span>
                </div>
              </a>
              <div class="parent-wrapper label-1">

                <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="ext">

                  <li class="collapsed-nav-item-title d-none">Gestión de Procesos Externos</li>

                  <li class="nav-item">
                    <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Plame</span></div>
                    </a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                    <div class="d-flex align-items-center"><span class="nav-link-text">Afp Net</span></div>
                  </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                  <div class="d-flex align-items-center"><span class="nav-link-text">Asientos Contables</span></div>
                </a>
              </li>

            </ul>
          </div>
        </div>

        <p class="navbar-vertical-label">Alquileres</p>
        <p id="p2" class="navbar-vertical-label">Contratos de Alquileres, recibos</p>
            <div class="nav-item-wrapper"><a class="nav-link dropdown-indicator label-1" href="#alq" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="alq">
                <div class="d-flex align-items-center">

                  <div class="dropdown-indicator-icon"><span class="fas fa-caret-right"></span></div><span class="nav-link-icon"><span data-feather="codesandbox"></span></span><span class="nav-link-text">Alquileres</span>
                </div>
              </a>
              <div class="parent-wrapper label-1">

                <ul class="nav collapse parent show" data-bs-parent="#navbarVerticalCollapse" id="alq">

                      <li class="collapsed-nav-item-title d-none">Alquileres</li>

                      <li class="nav-item">
                        <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                          <div class="d-flex align-items-center"><span class="nav-link-text">Gestión de Clientes</span></div>
                        </a>
                      </li>
                      <li class="nav-item">
                          <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                        <div class="d-flex align-items-center"><span class="nav-link-text">Gestión de Prodcutos</span></div>
                      </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                      <div class="d-flex align-items-center"><span class="nav-link-text">Contrato Alquileres</span></div>
                    </a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
                        <div class="d-flex align-items-center"><span class="nav-link-text">Generar Recibos</span></div>
                      </a>
                    </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#" data-bs-toggle="" aria-expanded="false">
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
          <a id="btnMenuCollapse" class="btn navbar-vertical-toggle border-0 fw-semi-bold w-100 white-space-nowrap d-flex align-items-center"><span class="uil uil-left-arrow-to-left fs-0"></span><span class="uil uil-arrow-from-right fs-0"></span><span class="navbar-vertical-footer-text ms-2">Collapsed View</span></a>
        </div>
    </nav>
