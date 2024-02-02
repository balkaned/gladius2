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
    <!--<script src="../src/js/theme/charts/echarts/examples/pie-chart.js"></script>-->
    <!--<script src="resources/src/js/theme/charts/echarts/examples/pie-chart.js"></script>-->
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
                              <h2 class="mb-0 me-2">150</h2><span class="fs-1 fw-semi-bold text-900">Empleados</span>
                            </div>
                            <p class="text-800 fs--1 mb-0">Empleados activos</p>
                          </div>
                        </div>
                      </div>
                      <div class="col-12 col-sm-6 col-md-3 col-lg-6 col-xl-3 col-xxl-12">
                        <div class="d-flex align-items-center"><span class="fs-4 lh-1 fa-solid fa-ticket text-success-500"></span>
                          <div class="ms-2">
                            <div class="d-flex align-items-end">
                              <h2 class="mb-0 me-2">94</h2><span class="fs-1 fw-semi-bold text-900">Fondos</span>
                            </div>
                            <p class="text-800 fs--1 mb-0">Fondos colectivos y de inversión</p>
                          </div>
                        </div>
                      </div>
                      <div class="col-12 col-sm-6 col-md-3 col-lg-6 col-xl-3 col-xxl-12">
                        <div class="d-flex align-items-center"><span class="fs-4 lh-1 fa-solid fa-briefcase text-warning-500"></span>
                          <div class="ms-2">
                            <div class="d-flex align-items-end">
                              <h2 class="mb-0 me-2">23</h2><span class="fs-1 fw-semi-bold text-900">Areas</span>
                            </div>
                            <p class="text-800 fs--1 mb-0">Total Areas</p>
                          </div>
                        </div>
                      </div>
                      <div class="col-12 col-sm-6 col-md-3 col-lg-6 col-xl-3 col-xxl-12">
                        <div class="d-flex align-items-center"><span class="fs-4 lh-1 fa-solid fa-city text-danger-500"></span>
                          <div class="ms-2">
                            <div class="d-flex align-items-end">
                              <h2 class="mb-0 me-2">3</h2><span class="fs-1 fw-semi-bold text-900">Bancos</span>
                            </div>
                            <p class="text-800 fs--1 mb-0">Bancos para pagos</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <!--<div class="col-sm-6 col-md-12 border-200">
                        <div class="echart-pie-chart-example" style="min-height:320px"></div>
                  </div>-->
                </div>

                <div class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white pt-7 pb-3 border-y border-300">
                  <div class="row">
                    <div class="col-12 col-xl-7 col-xxl-6">
                      <div class="row g-3 mb-3">
                        <div class="col-12 col-md-6">
                          <h3 class="text-1100 text-nowrap">Gráfico Pie</h3>
                          <p class="text-700 mb-md-7">Resumen de trabajadores por sexo</p>
                          <div class="d-flex align-items-center justify-content-between">
                            <p class="mb-0 fw-bold">Empleados activos </p>
                            <p class="mb-0 fs--1">Total contados <span class="fw-bold">150</span></p>
                          </div>
                          <hr class="bg-200 mb-2 mt-2" />
                          <div class="d-flex align-items-center mb-1"><span class="d-inline-block bg-info-300 bullet-item me-2"></span>
                            <p class="mb-0 fw-semi-bold text-900 lh-sm flex-1">Masculino</p>
                            <h5 class="mb-0 text-900">80</h5>
                          </div>
                          <div class="d-flex align-items-center mb-1"><span class="d-inline-block bg-warning-300 bullet-item me-2"></span>
                            <p class="mb-0 fw-semi-bold text-900 lh-sm flex-1">Femenino</p>
                            <h5 class="mb-0 text-900">50</h5>
                          </div>
                          <div class="d-flex align-items-center mb-1"><span class="d-inline-block bg-danger-300 bullet-item me-2"></span>
                            <p class="mb-0 fw-semi-bold text-900 lh-sm flex-1">Sin Sexo</p>
                            <h5 class="mb-0 text-900">20</h5>
                          </div>

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
                      <h3>Fondos</h3>
                      <p class="text-700 mb-0 mb-xl-3">Fondos mutuos y de inversión</p>
                      <div class="echart-zero-burnout-chart" style="min-height:320px;width:100%"></div>
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
                            <c:forEach var="listCumple" items="${requestScope.listCumple}">
                              <div class="d-flex hover-actions-trigger py-3 border-top">
                                <input class="form-check-input form-check-input-todolist flex-shrink-0 my-1 me-2 form-check-input-undefined" type="checkbox" id="checkbox-todo-0" data-event-propagation-prevent="data-event-propagation-prevent" />
                                <div class="row justify-content-between align-items-md-center btn-reveal-trigger border-200 gx-0 flex-1 cursor-pointer" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                  <div class="col-12 col-md-auto col-xl-12 col-xxl-auto">
                                    <div class="mb-1 mb-md-0 d-flex align-items-center lh-1">
                                      <label class="form-check-label mb-1 mb-md-0 mb-xl-1 mb-xxl-0 fs-0 me-2 line-clamp-1 text-900 cursor-pointer">${listCumple.iexnomtra} ${listCumple.iexapepat} ${listCumple.iexapemat}</label><span class="badge badge-phoenix ms-auto fs--2 badge-phoenix-primary">${listCumple.edad} años</span>
                                    </div>
                                  </div>
                                  <div class="col-12 col-md-auto col-xl-12 col-xxl-auto">
                                    <div class="d-flex lh-1 align-items-center"><a class="text-700 fw-bold fs--2 me-2" href="#!"><span class="fas fa-paperclip me-1"></span>2</a>
                                      <p class="text-1000 fw-semi-bold fs--2 mb-md-0 me-2 me-md-3 me-xl-2 me-xxl-3 mb-0">Fec nac: ${listCumple.iexfecnac}</p>
                                      <div class="hover-md-hide hover-xl-show hover-xxl-hide">
                                        <p class="text-700 fs--2 fw-bold mb-md-0 mb-0 ps-md-3 ps-xl-0 ps-xxl-3 border-start-md border-xl-0 border-start-xxl border-300"></p>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </c:forEach>
                        </div>
                        <a class="fw-bold fs--1 mt-4 btn btn-phoenix-secondary btn-sm col-4 ms-3 mb-3" href="valRegEmpleado"><span class="fas fa-plus me-1"></span>Add empleado</a>
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

                    <div class="col-12 col-xl-6 col-xxl-7 mt-4">
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
                                  <p class="mb-0 ms-sm-3 fs--1 text-700 fw-bold"><span class="fas fa-filter me-1 fw-extra-bold fs--2"></span>23 tasks</p>
                                  <button class="btn btn-link p-0 ms-3 fs--1 text-primary fw-bold"><span class="fas fa-sort me-1 fw-extra-bold fs--2"></span>Sorting</button>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="card-body py-0 scrollbar to-do-list-body">
                          <div class="d-flex hover-actions-trigger py-3 border-top">
                            <input class="form-check-input form-check-input-todolist flex-shrink-0 my-1 me-2 form-check-input-undefined" type="checkbox" id="checkbox-todo-0" data-event-propagation-prevent="data-event-propagation-prevent" />
                            <div class="row justify-content-between align-items-md-center btn-reveal-trigger border-200 gx-0 flex-1 cursor-pointer" data-bs-toggle="modal" data-bs-target="#exampleModal">
                              <div class="col-12 col-md-auto col-xl-12 col-xxl-auto">
                                <div class="mb-1 mb-md-0 d-flex align-items-center lh-1">
                                  <label class="form-check-label mb-1 mb-md-0 mb-xl-1 mb-xxl-0 fs-0 me-2 line-clamp-1 text-900 cursor-pointer">Designing the dungeon</label><span class="badge badge-phoenix ms-auto fs--2 badge-phoenix-primary">DRAFT</span>
                                </div>
                              </div>
                              <div class="col-12 col-md-auto col-xl-12 col-xxl-auto">
                                <div class="d-flex lh-1 align-items-center"><a class="text-700 fw-bold fs--2 me-2" href="#!"><span class="fas fa-paperclip me-1"></span>2</a>
                                  <p class="text-700 fs--2 mb-md-0 me-2 me-md-3 me-xl-2 me-xxl-3 mb-0">12 Nov, 2021</p>
                                  <div class="hover-md-hide hover-xl-show hover-xxl-hide">
                                    <p class="text-700 fs--2 fw-bold mb-md-0 mb-0 ps-md-3 ps-xl-0 ps-xxl-3 border-start-md border-xl-0 border-start-xxl border-300">12:00 PM</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
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
