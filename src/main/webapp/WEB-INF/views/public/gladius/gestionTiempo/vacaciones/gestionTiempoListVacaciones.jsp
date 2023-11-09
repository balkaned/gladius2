<%-- Created on : 15/06/2023, 12:20:00 PM Author : Jean Quiroz Email : jeanp.quiroz@gmail.com --%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
      <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en-US" dir="ltr">

        <head>
          <jsp:include page="../../../links.jsp"></jsp:include>
        </head>
        <script>
          function mostrarAlert() {
            //alert("se grabo exitosamente");
            var div = document.getElementById('alert');
            div.style.display = '';

            setTimeout(function () {
              $("#alerts").hide(6000);
            }, 3000);
          }

          function formatearFecha1() {
            var fechaSeleccionada = $('#fecini').val();

            var anio = fechaSeleccionada.substring(0, 4);
            var mes = fechaSeleccionada.substring(5, 7);
            var dia = fechaSeleccionada.substring(8, 10);

            var fechaFormat = dia + "/" + mes + "/" + anio;
            $("#fecini").val(fechaFormat);
          }

          function formatearFecha2() {
            var fechaSeleccionada = $('#fecfin').val();

            var anio = fechaSeleccionada.substring(0, 4);
            var mes = fechaSeleccionada.substring(5, 7);
            var dia = fechaSeleccionada.substring(8, 10);

            var fechaFormat = dia + "/" + mes + "/" + anio;
            $("#fecfin").val(fechaFormat);
          }
        </script>

        <body>
          <!-- ===============================================-->
          <!--    Main Content-->
          <!-- ===============================================-->
          <main class="main" id="top">
            <jsp:include page="../../../navsMenu.jsp"></jsp:include>
            <jsp:include page="../../../navTop.jsp"></jsp:include>
            <jsp:include page="../../../modalFade.jsp"></jsp:include>

            <div class="content">
              <nav class="mb-2" aria-label="breadcrumb">
                <ol class="breadcrumb mb-0">
                  <li class="breadcrumb-item"><a href="#!">Page</a></li>
                  <li class="breadcrumb-item active">Default</li>
                </ol>
              </nav>
              <div class="mb-9">
                <div class="row g-3 mb-4">
                  <div class="col-auto">
                    <h2 id="h2top" class="mb-0">Vacacciones</h2>
                  </div>
                </div>

                <div class="row g-5">
                  <div class="col-xl-8">
                    <div class="row gx-3 gy-4">
                      <form class="row g-4 mb-0 needs-validation" method="POST" action="" novalidate>
                        <input class="form-control" name="codcia" type="hidden" value="${requestScope.emp.codcia}" />
                        <input class="form-control" name="iexcodtra" type="hidden"
                          value="${requestScope.emp.iexcodtra}" />
                        <div class="col-sm-6 col-md-6">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Regimen</label>
                          <select class="form-select" name="iexcodreg" id="iexcodreg" onchange="regimen();" required>
                            <option value="" selected>Seleccionar</option>
                            <c:forEach var="Lovs_regimen" items="${requestScope.Lovs_regimen}">
                              <option value="${Lovs_regimen.idLov}" ${Lovs_regimen.idLov==requestScope.iexcodreg
                                ? 'selected' : '' }>${Lovs_regimen.desLov}</option>
                            </c:forEach>
                          </select>
                        </div>
                        <div class="col-sm-6 col-md-6">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Estado</label>
                          <select class="form-select" name="slc_estado" required="">
                            <option value="0">Inactivos</option>
                            <option value="1" selected="">Activos</option>
                          </select>
                        </div>
                        <div class="col-sm-6 col-md-6">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha Inicio</label><span
                            class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                          <input class="form-control datetimepicker" name="fecini" id="fecini"
                            onchange="formatearFecha1();" type="text" placeholder="dd/mm/yyyy"
                            data-options='{"disableMobile":true}' />
                        </div>

                        <div class="col-sm-6 col-md-6">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha Fin</label><span
                            class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                          <input class="form-control datetimepicker" name="fecfin" id="fecfin"
                            onchange="formatearFecha2();" type="text" placeholder="dd/mm/yyyy"
                            data-options='{"disableMobile":true}' />
                        </div>
                        <div class="d-grid gap-2 d-md-block">
                          <button class="btn btn-primary" type="submit"><svg
                              class="svg-inline--fa fa-magnifying-glass me-2" aria-hidden="true" focusable="false"
                              data-prefix="fas" data-icon="magnifying-glass" role="img"
                              xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg="">
                              <path fill="currentColor"
                                d="M500.3 443.7l-119.7-119.7c27.22-40.41 40.65-90.9 33.46-144.7C401.8 87.79 326.8 13.32 235.2 1.723C99.01-15.51-15.51 99.01 1.724 235.2c11.6 91.64 86.08 166.7 177.6 178.9c53.8 7.189 104.3-6.236 144.7-33.46l119.7 119.7c15.62 15.62 40.95 15.62 56.57 0C515.9 484.7 515.9 459.3 500.3 443.7zM79.1 208c0-70.58 57.42-128 128-128s128 57.42 128 128c0 70.58-57.42 128-128 128S79.1 278.6 79.1 208z">
                              </path>
                            </svg>Buscar</button>
                          
                            <a class="btn btn-primary" href="nuevoGestionVacaciones"><svg
                              class="svg-inline--fa fa-plus me-2" aria-hidden="true" focusable="false" data-prefix="fas"
                              data-icon="plus" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"
                              data-fa-i2svg="">
                              <path fill="currentColor"
                                d="M432 256c0 17.69-14.33 32.01-32 32.01H256v144c0 17.69-14.33 31.99-32 31.99s-32-14.3-32-31.99v-144H48c-17.67 0-32-14.32-32-32.01s14.33-31.99 32-31.99H192v-144c0-17.69 14.33-32.01 32-32.01s32 14.32 32 32.01v144h144C417.7 224 432 238.3 432 256z">
                              </path>
                            </svg>Agregar</a>

                          <button class="btn btn-primary" type="button">Exportar Programación</button>
                          <button class="btn btn-primary" type="button">Exportar Saldo</button>
                        </div>
                        <div id="orderTable"
                          data-list='{"valueNames":["order","total","customer","payment_status","fulfilment_status","delivery_type","date"],"page":10,"pagination":true}'>
                          <div
                            class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1" style="width: 160%;">
                            <div class="table-responsive scrollbar mx-n1 px-1">
                              <table class="table table-sm fs--1 mb-0">
                                <thead>
                                  <tr>
                                    <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox"
                                          data-bulk-select='{"body":"order-table-body"}' />
                                      </div>
                                    </th>
                                    <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="order"
                                      style="width:5%;">ID</th>
                                    <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">
                                      DOC</th>
                                    <th class="sort align-middle text-center ps-6" scope="col" data-sort="date">CODTRA
                                    </th>
                                    <th class="sort align-middle text-center ps-8" scope="col" data-sort="date">
                                      NOMBRES y APELLIDOS</th>
                                    <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">ESTADO
                                    </th>
                                    <th class="sort align-middle text-center ps-5 pe-5" scope="col" data-sort="date">
                                      F.INGRESO</th>
                                    <th class="sort align-middle text-start pe-0" scope="col" data-sort="date">TIPO
                                      VACA.</th>
                                    <th class="sort align-middle text-center pe-3" scope="col" data-sort="date">
                                      FECINI</th>
                                    <th class="sort align-middle text-center pe-3" scope="col" data-sort="date">
                                      FECFIN</th>
                                    <th class="sort align-middle text-center pe-0" scope="col">DIAS</th>
                                     <th class="sort align-middle text-center pe-0" scope="col">FICHA</th>
                                  </tr>
                                </thead>
                                <tbody class="list" id="order-table-body">
                                  <c:forEach var="LstVacacionesView" items="${requestScope.LstVacacionesView}">
                                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                      <td class="fs--1 align-middle px-0 py-3">
                                        <div class="form-check mb-0 fs-0">
                                          <input class="form-check-input" type="checkbox"
                                            data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}' />
                                        </div>
                                      </td>
                                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold"
                                          href="">#${LstVacacionesView.iexcorrel}</a></td>
                                      <td class="total align-middle text-center fw-semi-bold text-1000">
                                        ${LstVacacionesView.nrodoc}</td>
                                      <td class="total align-middle text-center fw-semi-bold text-1000">
                                        ${LstVacacionesView.iexcodtra}</td>
                                      <td class="total align-middle text-center fw-semi-bold text-1000">
                                        ${LstVacacionesView.desnomtra}</td>
                                      <td class="total align-middle text-center fw-semi-bold text-1000">
                                        ${LstVacacionesView.desestado}</td>
                                      <td class="total align-middle text-center fw-semi-bold text-1000">
                                        ${LstVacacionesView.fecing}</td>
                                      <td class="total align-middle text-center fw-semi-bold text-1000">
                                        ${LstVacacionesView.destipvac}</td>
                                      <td class="total align-middle text-center fw-semi-bold text-1000">
                                        ${LstVacacionesView.iexfecini}</td>
                                      <td class="total align-middle text-center fw-semi-bold text-1000">
                                        ${LstVacacionesView.fecfinrep}</td>
                                      <td class="total align-middle text-center fw-semi-bold text-1000">
                                        ${LstVacacionesView.iexnrodias}</td>

                                      <td class="align-middle text-end white-space-nowrap pe-0 action">
                                        <div class="font-sans-serif btn-reveal-trigger position-static">
                                          <button
                                            class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2"
                                            type="button" data-bs-toggle="dropdown" data-boundary="window"
                                            aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span
                                              class="fas fa-ellipsis-h fs--2"></span></button>
                                          <div class="dropdown-menu dropdown-menu-end py-2">
                                            <a class="dropdown-item" href="">Editar
                                              Vacacciones</a>
                                            <a class="dropdown-item" href="fichaEmpl@${empl.iexcodtra}">Descargar Ficha
                                              PDF</a>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item text-danger" href="#!">Eliminar</a>
                                          </div>
                                        </div>
                                      </td>
                                    </tr>
                                  </c:forEach>
                                </tbody>
                              </table>
                            </div>
                            <div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
                              <div class="col-auto d-flex">
                                <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900"
                                  data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!"
                                  data-list-view="*">View all<span class="fas fa-angle-right ms-1"
                                    data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none" href="#!"
                                  data-list-view="less">View Less<span class="fas fa-angle-right ms-1"
                                    data-fa-transform="down-1"></span></a>
                              </div>
                              <div class="col-auto d-flex">
                                <button class="page-link" data-list-pagination="prev"><span
                                    class="fas fa-chevron-left"></span></button>
                                <ul class="mb-0 pagination"></ul>
                                <button class="page-link pe-0" data-list-pagination="next"><span
                                    class="fas fa-chevron-right"></span></button>
                              </div>
                            </div>
                          </div>
                        </div>

                    </div>

                    <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                      Se grabó exitosamente los cambios.
                    </div>
                    <div class="modal fade" id="confirmModal" tabindex="-1">
                      <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content border">
                          <form id="addEventForm" autocomplete="off">
                            <div class="modal-header border-200 p-4">
                              <h5 class="modal-title text-1000 fs-4 lh-sm">Confirmar</h5>
                              <button class="btn p-1 text-900" type="button" data-bs-dismiss="modal"
                                aria-label="Close"><span class="fas fa-times fs--1"></span></button>
                            </div>
                            <div class="modal-body pt-4 pb-2 px-4">
                              <div class="mb-3">
                                <label class="fw-bold mb-2 text-1000" for="leadStatus">Esta seguro que desea
                                  confirmar la operacion?</label>
                              </div>
                            </div>
                          </form>
                          <div
                            class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
                            <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="button"
                              data-bs-dismiss="modal">Cancel</button>
                            <button class="btn btn-sm btn-primary px-9 fs--2 my-0" onclick="mostrarAlert();"
                              type="submit" data-bs-dismiss="modal">Confirmar</button>
                          </div>
                        </div>
                      </div>
                    </div>
                    </form>
                  </div>
                </div>
              </div>

            </div>

            </div>

            <jsp:include page="../../../demoWidget.jsp"></jsp:include>

          </main>
          <!-- ===============================================-->
          <!--    End of Main Content-->
          <!-- ===============================================-->

          <jsp:include page="../../../customize.jsp"></jsp:include>
        </body>

        </html>