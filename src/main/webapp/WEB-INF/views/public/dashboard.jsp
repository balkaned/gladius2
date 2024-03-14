<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jean Quiroz
    Email	   : jeanp.quiroz@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
  <head>
    <jsp:include page="links.jsp"></jsp:include>

    <link href="resources/vendors/dhtmlx-gantt/dhtmlxgantt.css" rel="stylesheet">

    <script src="resources/vendors/choices/choices.min.js"></script>
    <script src="resources/vendors/echarts/echarts.min.js"></script>
    <script src="resources/vendors/dhtmlx-gantt/dhtmlxgantt.js"></script>
    <script src="resources/assets/js/projectmanagement-dashboard.js"></script>
    <script src="resources/assets/js/basic-bar-chart.js"></script>
    <script src="resources/assets/js/crm-dashboard.js"></script>
    <script src="resources/assets/js/ecommerce-dashboard.js"></script>
    <script src="resources/assets/js/series-bar-chart.js"></script>
  </head>

  <body>
      <!-- ===============================================-->
      <!--    Main Content-->
      <!-- ===============================================-->
      <main class="main" id="top">
            <jsp:include page="navsMenu.jsp"></jsp:include>
            <jsp:include page="navTop.jsp"></jsp:include>
            <jsp:include page="modalFade.jsp"></jsp:include>

            <div class="content">
                <div class="row gy-3 mb-6 justify-content-between">
                  <div class="col-md-9 col-auto">
                    <h2 class="mb-2 text-1100">Dashboard</h2>
                    <h5 class="text-700 fw-semi-bold">Dashboard gladius, aquí se muestra el resumen de algunas operaciones</h5>
                  </div>
                  <div class="col-md-3 col-auto">
                    <div class="flatpickr-input-container">
                      <a href="listEmpleados" class="btn btn-phoenix-secondary btn-sm"><span class="fa-regular fa-star me-2"></span>Trabajadores</a>
                    </div>
                  </div>
                </div>
                <div class="row mb-3 gy-6">
                  <div class="col-12 col-xxl-2">
                    <div class="row align-items-center g-3 g-xxl-0 h-100 align-content-between">
                      <div class="col-12 col-sm-6 col-md-3 col-lg-6 col-xl-3 col-xxl-12">
                        <div class="d-flex align-items-center"><span class="fs-4 lh-1 uil uil-users-alt text-primary-500"></span>
                          <div class="ms-2">
                            <div class="d-flex align-items-end">
                              <h2 class="mb-0 me-2">${cantEmpl}</h2><span class="fs-1 fw-semi-bold text-900">Empleados</span>
                            </div>
                            <p class="text-800 fs--1 mb-0">Empleados activos</p>
                          </div>
                        </div>
                      </div>
                      <div class="col-12 col-sm-6 col-md-3 col-lg-6 col-xl-3 col-xxl-12">
                        <div class="d-flex align-items-center"><span class="fs-4 lh-1 fa-solid fa-ticket text-success-500"></span>
                          <div class="ms-2">
                            <div class="d-flex align-items-end">
                              <h2 class="mb-0 me-2">${cantFondos}</h2><span class="fs-1 fw-semi-bold text-900">Fondos</span>
                            </div>
                            <p class="text-800 fs--1 mb-0">Fondos de pensión colectivos y de inversión</p>
                          </div>
                        </div>
                      </div>
                      <div class="col-12 col-sm-6 col-md-3 col-lg-6 col-xl-3 col-xxl-12">
                        <div class="d-flex align-items-center"><span class="fs-4 lh-1 fa-solid fa-briefcase text-warning-500"></span>
                          <div class="ms-2">
                            <div class="d-flex align-items-end">
                              <h2 class="mb-0 me-2">${cantAreas}</h2><span class="fs-1 fw-semi-bold text-900">Areas</span>
                            </div>
                            <p class="text-800 fs--1 mb-0">Total areas</p>
                          </div>
                        </div>
                      </div>
                      <div class="col-12 col-sm-6 col-md-3 col-lg-6 col-xl-3 col-xxl-12">
                        <div class="d-flex align-items-center"><span class="fs-4 lh-1 fa-solid fa-city text-danger-500"></span>
                          <div class="ms-2">
                            <div class="d-flex align-items-end">
                              <h2 class="mb-0 me-2">${cantBancosHab}</h2><span class="fs-1 fw-semi-bold text-900">Bancos</span>
                            </div>
                            <p class="text-800 fs--1 mb-0">Banco de haberes</p>
                          </div>
                        </div>
                      </div>
                      <div class="col-12 col-sm-6 col-md-3 col-lg-6 col-xl-3 col-xxl-12 mt-4">
                          <div class="d-flex align-items-center"><span class="fs-4 lh-1 fa-solid fa-cash-register text-success-500"></span>
                            <div class="ms-2">
                              <div class="d-flex align-items-end">
                                <h2 class="mb-0 me-2">${cantCcostos}</h2><span class="fs-1 fw-semi-bold text-900">C. costos</span>
                              </div>
                              <p class="text-800 fs--1 mb-0">Total centros de costos</p>
                            </div>
                          </div>
                      </div>
                      <div class="col-12 col-sm-6 col-md-3 col-lg-6 col-xl-3 col-xxl-12 mt-4">
                          <div class="d-flex align-items-center"><span class="fs-4 lh-1 fa-solid fa-house text-info-500"></span>
                            <div class="ms-2">
                              <div class="d-flex align-items-end">
                                <h2 class="mb-0 me-2">${cantLocales}</h2><span class="fs-1 fw-semi-bold text-900">Locales</span>
                              </div>
                              <p class="text-800 fs--1 mb-0">Total Locales o sucursales</p>
                            </div>
                          </div>
                      </div>
                      <div class="col-12 col-sm-6 col-md-3 col-lg-6 col-xl-3 col-xxl-12 mt-4">
                          <div class="d-flex align-items-center"><span class="fs-4 lh-1 fa-solid fa-user-nurse text-secondary-500"></span>
                            <div class="ms-2">
                              <div class="d-flex align-items-end">
                                <h2 class="mb-0 me-2">${cantPuestos}</h2><span class="fs-1 fw-semi-bold text-900">Puestos</span>
                              </div>
                              <p class="text-800 fs--1 mb-0">Total puestos</p>
                            </div>
                          </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white pt-7 pb-3 border-y border-300">
                  <div class="row">
                    <div class="col-12 col-xl-7 col-xxl-6">
                      <div class="row g-3 mb-3">
                        <div class="col-12 col-md-6">
                          <h3 class="text-1100 text-nowrap">Empleados por sexo</h3>
                          <p class="text-700 mb-md-7">Resumen de trabajadores por sexo</p>
                          <div class="d-flex align-items-center justify-content-between">
                            <p class="mb-0 fw-bold">Empleados activos </p>
                            <p class="mb-0 fs--1">Total contados <span class="fw-bold">${ds.cantidad_total}</span></p>
                          </div>
                          <hr class="bg-200 mb-2 mt-2" />
                          <div class="d-flex align-items-center mb-1"><span class="d-inline-block bg-info-300 bullet-item me-2"></span>
                            <p class="mb-0 fw-semi-bold text-900 lh-sm flex-1">Masculino M</p>
                            <h5 class="mb-0 text-900">${ds.cantidad_m}</h5>
                          </div>
                          <div class="d-flex align-items-center mb-1"><span class="d-inline-block bg-warning-300 bullet-item me-2"></span>
                            <p class="mb-0 fw-semi-bold text-900 lh-sm flex-1">Femenino F</p>
                            <h5 class="mb-0 text-900">${ds.cantidad_f}</h5>
                          </div>
                          <div class="d-flex align-items-center mb-1"><span class="d-inline-block bg-danger-300 bullet-item me-2"></span>
                            <p class="mb-0 fw-semi-bold text-900 lh-sm flex-1">Sin Sexo MA</p>
                            <h5 class="mb-0 text-900">${ds.cantidad_ma}</h5>
                          </div>
                          <input id="cantidad_total" type="hidden" value="${cantidad_total}" />
                          <input id="cantidad_m" type="hidden" value="${cantidad_m}" />
                          <input id="cantidad_f" type="hidden" value="${cantidad_f}" />
                          <input id="cantidad_ma" type="hidden" value="${cantidad_ma}" />

                          <button class="btn btn-phoenix-secondary btn-sm mt-5">Detalle empl<span class="fas fa-angle-right ms-2 fs--2 text-center"></span></button>
                        </div>

                        <div class="col-12 col-md-6">
                          <div class="position-relative mb-sm-4 mb-xl-0">
                            <div class="echart-issue-chart" style="min-height:390px;width:100%"></div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-12 col-xl-5 col-xxl-6">
                      <h3>Puestos</h3>
                      <p class="text-700 mb-0 mb-xl-3">Cantidad de empleados por cada posición laboral</p>
                        <!--<div class="echart-zero-burnout-chart" style="min-height:320px;width:100%"></div>-->

                      <div class="echart-revenue-target-conversion-puestos" style="min-height:500px;width:100%"></div>
                      <c:set var="cont_ps" value="${1}" />
                      <c:forEach var="lsPuestosBar" items="${lsPuestosBar}">
                              <input id="cantidad_ps_${cont_ps}" type="hidden" value="${lsPuestosBar.cantidad}" />
                              <input id="iexdespuesto_ps_${cont_ps}" type="hidden" value="${lsPuestosBar.iexdespuesto}" />
                              <c:set var="cont_ps" value="${cont_ps + 1}" />
                      </c:forEach>
                              <input id="contador_ps" type="hidden" value="${cont_ps-1}" />

                    </div>
                  </div>
                </div>

                <div class="col-xxl-6 mb-6 mt-6">
                    <h3>Fondos de pensión</h3>
                    <p class="text-700 mb-1">Fondos de pensión asociados por empleados</p>
                    <div class="echart-contacts-created" style="min-height:270px; width:100%"></div>
                    <c:set var="cont2" value="${1}" />
                    <c:forEach var="lsfnd" items="${lsFondBar}">
                            <input id="cantidad2_${cont2}" type="hidden" value="${lsfnd.cantidad}" />
                            <input id="desdet2_${cont2}" type="hidden" value="${lsfnd.desdet}" />
                            <c:set var="cont2" value="${cont2 + 1}" />
                    </c:forEach>
                            <input id="contador2" type="hidden" value="${cont2-1}" />
                </div>

                <div class="col-sm-6 col-md-12 row">
                    <div class="col-sm-6 col-md-6 mt-3">
                        <div class="card h-100">
                            <div class="card-body">
                                <div>
                                    <h3>Areas</h3>
                                    <p>Cantidad de empleados por areas</p>
                                    <div class="echart-revenue-target-conversion" style="min-height:200px"></div>
                                    <c:set var="cont" value="${1}" />
                                    <c:forEach var="lsarea" items="${lsAreaBar}">
                                            <input id="cantidad_${cont}" type="hidden" value="${lsarea.cantidad}" />
                                            <input id="desarea_${cont}" type="hidden" value="${lsarea.desarea}" />
                                            <c:set var="cont" value="${cont + 1}" />
                                    </c:forEach>
                                            <input id="contador" type="hidden" value="${cont-1}" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-6 mt-3">
                        <div class="card h-100">
                            <div class="card-body">
                                <div class="">
                                    <h3>C.costos</h3>
                                    <p>Número de empleados por centro de costos</p>
                                    <div class="echart-revenue-target-conversion-ccosto" style="min-height:200px"></div>
                                    <c:set var="cont_cc" value="${1}" />
                                    <c:forEach var="lsCcostoBar" items="${lsCcostoBar}">
                                            <input id="cantidad_cc_${cont_cc}" type="hidden" value="${lsCcostoBar.cantidad}" />
                                            <input id="iexdesccosto_cc_${cont_cc}" type="hidden" value="${lsCcostoBar.iexdesccosto}" />
                                            <c:set var="cont_cc" value="${cont_cc + 1}" />
                                    </c:forEach>
                                            <input id="contador_cc" type="hidden" value="${cont_cc-1}" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-12 row">
                    <div class="col-sm-6 col-md-6 mt-3">
                        <div class="card h-100">
                            <div class="card-body">
                                <div class="">
                                    <h3>Locales</h3>
                                    <p>Número de empleados por Locales o ubigeos</p>

                                    <div class="echart-revenue-target-conversion-locales" style="min-height:350px"></div>
                                    <c:set var="cont_lc" value="${1}" />
                                    <c:forEach var="lsLocalBar" items="${lsLocalBar}">
                                            <input id="cantidad_lc_${cont_lc}" type="hidden" value="${lsLocalBar.cantidad}" />
                                            <input id="iexubides_lc_${cont_lc}" type="hidden" value="${lsLocalBar.iexubides}" />
                                            <c:set var="cont_lc" value="${cont_lc + 1}" />
                                    </c:forEach>
                                            <input id="contador_lc" type="hidden" value="${cont_lc-1}" />
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="col-12 col-xxl-6 mb-3 mb-sm-0 mt-7">
                    <div class="row">
                      <div class="col-sm-7 col-md-8 col-xxl-8 mb-md-3 mb-lg-0">
                        <h3>Bancos</h3>
                        <p class="text-700">Cantidad de empleados asociados a bancos de haberes</p>
                        <div class="row g-0">
                            <c:forEach var="lsbnp" items="${lsBanPie}">
                              <div class="col-6 col-xl-4">
                                    <div class="d-flex flex-column flex-center align-items-sm-start flex-md-row justify-content-md-between flex-xxl-column p-3 ps-sm-3 ps-md-4 p-md-3 h-100 border-1 border-bottom border-end">
                                      <div class="d-flex align-items-center mb-1"><span class="fa-solid fa-square fs--3 me-2 text-success" data-fa-transform="up-2"></span><span class="mb-0 fs--1 text-900">${lsbnp.desdet}</span></div>
                                      <h3 class="fw-semi-bold ms-xl-3 ms-xxl-0 pe-md-2 pe-xxl-0 mb-0 mb-sm-3">${lsbnp.cantidad}</h3>
                                    </div>
                              </div>
                            </c:forEach>
                        </div>
                      </div>
                      <div class="col-sm-5 col-md-4 col-xxl-4 my-3 my-sm-0">
                        <div class="position-relative d-flex flex-center mb-sm-4 mb-xl-0 echart-contact-by-source-container mt-sm-7 mt-lg-4 mt-xl-0">

                          <c:set var="cont3" value="${1}" />
                          <c:forEach var="lsbnp" items="${lsBanPie}">
                                  <input id="cantidad3_${cont3}" type="hidden" value="${lsbnp.cantidad}" />
                                  <input id="desdet3_${cont3}" type="hidden" value="${lsbnp.desdet}" />
                                  <c:set var="cont3" value="${cont3 + 1}" />
                          </c:forEach>
                                  <input id="contador3" type="hidden" value="${cont3-1}" />

                          <div class="echart-contact-by-source" style="min-height:245px;width:100%"></div>

                          <div class="position-absolute rounded-circle bg-primary-100 top-50 start-50 translate-middle d-flex flex-center" style="height:100px; width:100px;">
                            <h3 class="mb-0 text-primary-600 dark__text-primary-300 fw-bolder" data-label="data-label"></h3>
                          </div>
                        </div>
                      </div>
                    </div>
                </div>

                <div class="col-12 mt-6 mb-5">
                    <h2 class="mb-2 text-1100">${nombreEnMes}</h2>
                    <h5 class="text-700 fw-semi-bold">Lista de personas que cumplen años este mes, empleados que ingresan y retirados</h5>
                </div>
                <div class="mx-lg-n4 mt-3">
                  <div class="row g-3">
                    <div class="col-12 col-xl-6 col-xxl-7">
                      <div class="card todo-list h-100">
                        <div class="card-header border-bottom-0 pb-0">
                          <div class="row justify-content-between align-items-center mb-4">
                            <div class="col-auto">
                              <h4 class="text-1100">Cumpleaños</h4>
                              <p class="mb-0 text-700">Trabajadores que cumple años en este mes</p>
                            </div>
                            <div class="col-auto w-100 w-md-auto mt-3">
                              <div class="row align-items-center g-0 justify-content-between">
                                <div class="col-12 col-sm-auto">
                                  <div class="search-box w-100 mb-2 mb-sm-0" style="max-width:30rem;">
                                    <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                      <input class="form-control search-input search" type="search" placeholder="Buscar trabajador" aria-label="Search" />
                                      <span class="fas fa-search search-box-icon"></span>
                                    </form>
                                  </div>
                                </div>
                                <div class="col-auto d-flex">
                                  <p class="mb-0 ms-sm-3 fs--1 text-700 fw-bold"><span class="fa-solid fa-wine-bottle me-1 fw-extra-bold fs--1"></span>${cantCumpl} Personas</p>
                                  <button class="btn btn-link p-0 ms-3 fs--1 text-primary fw-bold"><span class="fas fa-sort me-1 fw-extra-bold fs--2"></span>Sorting</button>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="card-body py-0 scrollbar to-do-list-body">
                            <c:if test="${mensaje3!=null}">
                                <div id="alert" class="alert alert-outline-info bg-info bg-opacity-10 d-flex align-items-center" role="alert">
                                    <span class="fa-solid fa-info  text-info fs-0 me-3"></span>
                                    <div class="col-11">
                                        <strong class="text-black">No hay datos</strong>
                                        <p class="mb-0 fw-semi-bold text-1000">${mensaje3} <a href="#">Mas información</a></p>
                                    </div>
                                    <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </c:if>
                            <c:forEach var="listCumple" items="${requestScope.listCumple}">
                              <div class="d-flex hover-actions-trigger py-3 border-top border-dashed pt-0 pb-0">
                                <input class="form-check-input form-check-input-todolist flex-shrink-0 my-1 me-2 form-check-input-undefined" type="checkbox" id="checkbox-todo-0" data-event-propagation-prevent="data-event-propagation-prevent" />
                                <div class="row justify-content-between align-items-md-center btn-reveal-trigger border-200 gx-0 flex-1 cursor-pointer" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                      <div class="d-flex py-3 align-items-center">
                                        <div class="me-2">
                                          <div class="avatar avatar-m status-online ">
                                            <!--<img class="rounded-circle avatar-placeholder" src="resources/assets/img/team/avatar.webp" alt="" />-->
                                            <c:if test="${listCumple.iexlogo!=null}"><img id="imgCumple" class="rounded-circle avatar-placeholder" src="AWSorFTP_flgsource@verFotoEmpl@${idComp}@${listCumple.iexcodtra}@${listCumple.iexlogo}@null@null@null@null@null@null" alt="" /></c:if>
                                            <c:if test="${listCumple.sexo.equals('MA') && listCumple.iexlogo==null}"><img id="imgCumple" class="rounded-circle avatar-placeholder" src="resources/assets/img/team/avatar.webp" alt="" /></c:if>
                                            <c:if test="${listCumple.iexlogo==null && listCumple.sexo.equals('M')}"><img id="imgCumple" class="rounded-circle avatar-placeholder" src="resources/assets/img/man_user.jpg" alt="" /></c:if>
                                            <c:if test="${listCumple.iexlogo==null && listCumple.sexo.equals('F')}"><img id="imgCumple" class="rounded-circle avatar-placeholder" src="resources/assets/img/woman_user.jpg" alt="" /></c:if>
                                          </div>
                                        </div>
                                        <a class="text-decoration-none flex-1" href="#">
                                            <h5>${listCumple.iexnomtra} ${listCumple.iexapepat}<span class="badge badge-phoenix ms-2 fs--2 badge-phoenix-primary">${listCumple.edad} años</span><span class="fa-solid fa-cake-candles text-warning me-2 ms-2"></span></h5>
                                            <p class="text-700 fw-semi-bold fs--1 mb-0 lh-sm line-clamp-1">Fec nac: ${listCumple.iexfecnacFormat}, Sexo: ${listCumple.sexo}</p>
                                            <p><a class="text-700 fs--1 me-2 text-primary" target="_blank" href="detalleEmpl@${listCumple.iexcodtra}">codtra: #${listCumple.iexcodtra}</a></p>
                                        </a>
                                      </div>

                                      <!--<div class="col-12 col-md-auto col-xl-12 col-xxl-auto">
                                        <div class="mb-1 mb-md-0 d-flex align-items-center lh-1">
                                          <div class="avatar avatar-xl pe-2">
                                            <c:if test="${listCumple.iexlogo!=null}"><img id="imgCumple" class="rounded-circle" src="AWSorFTP_flgsource@verFotoEmpl@${idComp}@${listCumple.iexcodtra}@${listCumple.iexlogo}@null@null@null@null@null@null" alt="" /></c:if>
                                            <c:if test="${listCumple.sexo.equals('MA') && listCumple.iexlogo==null}"><img id="imgCumple" class="rounded-circle" src="resources/assets/img/user_blank.jpg" alt="" /></c:if>
                                            <c:if test="${listCumple.iexlogo==null && listCumple.sexo.equals('M')}"><img id="imgCumple" class="rounded-circle" src="resources/assets/img/man_user.jpg" alt="" /></c:if>
                                            <c:if test="${listCumple.iexlogo==null && listCumple.sexo.equals('F')}"><img id="imgCumple" class="rounded-circle" src="resources/assets/img/woman_user.jpg" alt="" /></c:if>
                                          </div>
                                          <label class="form-check-label mb-1 mb-md-0 mb-xl-1 mb-xxl-0 fs-0 me-2 line-clamp-1 text-900 cursor-pointer">${listCumple.iexnomtra} ${listCumple.iexapepat} ${listCumple.iexapemat}</label><span class="fa-solid fa-cake-candles text-warning me-2 ms-2"></span><span class="badge badge-phoenix ms-auto fs--2 badge-phoenix-primary">${listCumple.edad} años</span>
                                        </div>
                                      </div>
                                      <div class="col-12 col-md-auto col-xl-12 col-xxl-auto mt-2">
                                        <div class="d-flex lh-1 align-items-center"><a class="text-700 fw-bold fs--0 me-2 text-primary" href="detalleEmpl@${listCumple.iexcodtra}"><span class="fas fa-paperclip me-1"></span>Codtra: ${listCumple.iexcodtra}</a>
                                          <p class="text-700 fw-semi-bold fs--2 mb-md-0 me-2 me-md-3 me-xl-2 me-xxl-3 mb-0">Fec Nac: ${listCumple.iexfecnac}, Sexo: ${listCumple.sexo}</p>
                                          <div class="hover-md-hide hover-xl-show hover-xxl-hide">
                                            <p class="text-700 fs--2 fw-bold mb-md-0 mb-0 ps-md-3 ps-xl-0 ps-xxl-3 border-start-md border-xl-0 border-start-xxl border-300"></p>
                                          </div>
                                        </div>
                                      </div>-->
                                </div>
                              </div>
                            </c:forEach>
                        </div>
                        <div class="col-sm-6 col-md-6">
                            <a class="fw-bold fs--1 mt-4 btn btn-primary btn-sm ms-3 mb-3" href="valRegEmpleado"><span class="fas fa-plus me-1"></span>Add empleado</a>
                        </div>
                      </div>
                    </div>

                    <div class="col-12 col-xl-6 col-xxl-7">
                      <div class="card todo-list h-100">
                        <div class="card-header border-bottom-0 pb-0">
                          <div class="row justify-content-between align-items-center mb-4">
                            <div class="col-auto">
                              <h4 class="text-1100">Trabajadores ingresantes</h4>
                              <p class="mb-0 text-700">Trabajadores que ingresaron en este mes</p>
                            </div>
                            <div class="col-auto w-100 w-md-auto mt-3">
                              <div class="row align-items-center g-0 justify-content-between">
                                <div class="col-12 col-sm-auto">
                                  <div class="search-box w-100 mb-2 mb-sm-0" style="max-width:30rem;">
                                    <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                      <input class="form-control search-input search" type="search" placeholder="Buscar trabajador" aria-label="Search" />
                                      <span class="fas fa-search search-box-icon"></span>
                                    </form>
                                  </div>
                                </div>
                                <div class="col-auto d-flex">
                                  <p class="mb-0 ms-sm-3 fs--1 text-700 fw-bold"><span class="fa-solid fa-arrow-up me-1 fw-extra-bold fs--1"></span>${cantIngresantes} Ingresantes</p>
                                  <button class="btn btn-link p-0 ms-3 fs--1 text-primary fw-bold"><span class="fas fa-sort me-1 fw-extra-bold fs--2"></span>Sorting</button>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="card-body py-0 scrollbar to-do-list-body">
                            <c:if test="${mensaje!=null}">
                                <div id="alert" class="alert alert-outline-info bg-info bg-opacity-10 d-flex align-items-center" role="alert">
                                    <span class="fa-solid fa-info  text-info fs-0 me-3"></span>
                                    <div class="col-11">
                                        <strong class="text-black">No hay datos</strong>
                                         <p class="mb-0 fw-semi-bold text-1000">${mensaje} <a href="#">Mas información</a></p>
                                    </div>
                                    <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </c:if>
                            <c:forEach var="listIngresantes" items="${requestScope.listIngresantes}">
                              <div class="d-flex hover-actions-trigger py-3 border-top">
                                <input class="form-check-input form-check-input-todolist flex-shrink-0 my-1 me-2 form-check-input-undefined" type="checkbox" id="checkbox-todo-0" data-event-propagation-prevent="data-event-propagation-prevent" />
                                <div class="row justify-content-between align-items-md-center btn-reveal-trigger border-200 gx-0 flex-1 cursor-pointer" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                  <div class="col-12 col-md-auto col-xl-12 col-xxl-auto">
                                    <div class="mb-1 mb-md-0 d-flex align-items-center lh-1">
                                      <label class="form-check-label mb-1 mb-md-0 mb-xl-1 mb-xxl-0 fs-0 me-2 line-clamp-1 text-900 cursor-pointer">${listIngresantes.iexnomtra} ${listIngresantes.iexapepat} ${listIngresantes.iexapemat}</label><span class="badge badge-phoenix ms-auto fs--2 badge-phoenix-secondary">${listIngresantes.iexfecing}</span>
                                    </div>
                                  </div>
                                  <div class="col-12 col-md-auto col-xl-12 col-xxl-auto">
                                    <div class="d-flex lh-1 align-items-center"><a class="text-700 fw-bold fs--2 me-2" href="#!"><span class="fas fa-paperclip me-1"></span>2</a>
                                      <p class="text-1000 fw-semi-bold fs--2 mb-md-0 me-2 me-md-3 me-xl-2 me-xxl-3 mb-0">Fecha de ingreso: ${listIngresantes.iexfecing}</p>
                                      <div class="hover-md-hide hover-xl-show hover-xxl-hide">
                                        <p class="text-700 fs--2 fw-bold mb-md-0 mb-0 ps-md-3 ps-xl-0 ps-xxl-3 border-start-md border-xl-0 border-start-xxl border-300"></p>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </c:forEach>
                        </div>
                      </div>
                    </div>

                    <div class="col-12 col-xl-6 col-xxl-7 mt-3">
                      <div class="card todo-list h-100">
                        <div class="card-header border-bottom-0 pb-0">
                          <div class="row justify-content-between align-items-center mb-4">
                            <div class="col-auto">
                              <h4 class="text-1100">Trabajadores retirados</h4>
                              <p class="mb-0 text-700">Trabajadores que dejaron de laborar en este mes</p>
                            </div>
                            <div class="col-auto w-100 w-md-auto mt-3">
                              <div class="row align-items-center g-0 justify-content-between">
                                <div class="col-12 col-sm-auto">
                                  <div class="search-box w-100 mb-2 mb-sm-0" style="max-width:30rem;">
                                    <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                      <input class="form-control search-input search" type="search" placeholder="Buscar trabajador" aria-label="Search" />
                                      <span class="fas fa-search search-box-icon"></span>
                                    </form>
                                  </div>
                                </div>
                                <div class="col-auto d-flex">
                                  <p class="mb-0 ms-sm-3 fs--1 text-700 fw-bold"><span class="fa-solid fa-arrow-down me-1 fw-extra-bold fs--1"></span>${cantRetirados} Retirados</p>
                                  <button class="btn btn-link p-0 ms-3 fs--1 text-primary fw-bold"><span class="fas fa-sort me-1 fw-extra-bold fs--2"></span>Sorting</button>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="card-body py-0 scrollbar to-do-list-body">
                            <c:if test="${mensaje2!=null}">
                                <div id="alert" class="alert alert-outline-warning bg-warning bg-opacity-10 d-flex align-items-center" role="alert">
                                    <span class="fa-solid fa-triangle-exclamation  text-warning fs-0 me-3"></span>
                                    <div class="col-11">
                                        <strong class="text-black">No hay datos</strong>
                                         <p class="mb-0 fw-semi-bold text-1000">${mensaje2} <a href="#">Mas información</a></p>
                                    </div>
                                    <button class="btn-close fs-0" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </c:if>
                            <c:forEach var="listRetirados" items="${requestScope.listRetirados}">
                              <div class="d-flex hover-actions-trigger py-3 border-top">
                                <input class="form-check-input form-check-input-todolist flex-shrink-0 my-1 me-2 form-check-input-undefined" type="checkbox" id="checkbox-todo-0" data-event-propagation-prevent="data-event-propagation-prevent" />
                                <div class="row justify-content-between align-items-md-center btn-reveal-trigger border-200 gx-0 flex-1 cursor-pointer" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                  <div class="col-12 col-md-auto col-xl-12 col-xxl-auto">
                                    <div class="mb-1 mb-md-0 d-flex align-items-center lh-1">
                                      <label class="form-check-label mb-1 mb-md-0 mb-xl-1 mb-xxl-0 fs-0 me-2 line-clamp-1 text-900 cursor-pointer">${listRetirados.iexnomtra} ${listRetirados.iexapepat} ${listRetirados.iexapemat}</label><span class="badge badge-phoenix ms-auto fs--2 badge-phoenix-danger">${listRetirados.iexfecret}</span>
                                    </div>
                                  </div>
                                  <div class="col-12 col-md-auto col-xl-12 col-xxl-auto">
                                    <div class="d-flex lh-1 align-items-center"><a class="text-700 fw-bold fs--2 me-2" href="#!"><span class="fas fa-paperclip me-1"></span>2</a>
                                      <p class="text-1000 fw-semi-bold fs--2 mb-md-0 me-2 me-md-3 me-xl-2 me-xxl-3 mb-0">Fecha de retiro: ${listRetirados.iexfecret}</p>
                                      <div class="hover-md-hide hover-xl-show hover-xxl-hide">
                                        <p class="text-700 fs--2 fw-bold mb-md-0 mb-0 ps-md-3 ps-xl-0 ps-xxl-3 border-start-md border-xl-0 border-start-xxl border-300"></p>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </c:forEach>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
            </div>

        <jsp:include page="demoWidget.jsp"></jsp:include>
      </main>
      <!-- ===============================================-->
      <!--    End of Main Content-->
      <!-- ===============================================-->

      <jsp:include page="customize.jsp"></jsp:include>
  </body>
</html>
