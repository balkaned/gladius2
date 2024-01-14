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
                  <li class="breadcrumb-item"><a href="#!">Gestión de Procesos Externos</a></li>
                  <li class="breadcrumb-item active">Asientos Contables</li>
                </ol>
              </nav>
              <div class="mb-9">
                <div class="row g-3 mb-4">
                  <div class="col-auto">
                    <h2 id="h2top" class="mb-0">Nuevo asiento contable</h2>
                  </div>
                </div>

                <div class="row g-5">
                  <div class="col-xl-8">
                    <div class="row gx-3 gy-4">
                      <form class="row g-4 mb-0 needs-validation" method="POST" action="" novalidate>
                        <input class="form-control" name="iexcodcia" type="hidden"
                          value="${requestScope.emp.iexcodcia}" />
                        <input class="form-control" name="iexcodtra" type="hidden"
                          value="${requestScope.emp.iexcodtra}" />
                        <div class="col-sm-6 col-md-6">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Proceso</label>
                          <select class="form-select" name="iexcodpro" required>
                            <option value="" selected>Seleccionar</option>
                            <c:forEach var="lovProcesos" items="${requestScope.lovProcesos}" varStatus="loopCounter">
                              <option value="${lovProcesos.idProceso}" ${lovProcesos.idProceso==requestScope.iexcodpro
                                ? 'selected' : '' }>${lovProcesos.desProceso}</option>
                            </c:forEach>
                          </select>
                        </div>
                        <div class="col-sm-6 col-md-3">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Año</label>
                          <input type="text" name="anio" id="anio" value="${requestScope.anio}" class="form-control" placeholder="yyyy">
                        </div>
                        <div class="col-sm-4   col-md-4">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Mes</label>
                          <input type="text" name="permes" id="permes" value="${requestScope.permes}"
                            class="form-control" placeholder="mm">
                        </div>
                        <div class="col-sm-4 col-md-4">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Periodo</label>
                          <input type="text" name="iexnroper" id="iexnroper" value="${requestScope.iexnroper}"
                            class="form-control" placeholder="yyyymm">
                        </div>

                        <div class="col-sm-4 col-md-4">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">ID Asiento</label>
                          <input type="text" name="iexnroasientocab" id="iexnroasientocab"
                            value="${requestScope.iexnroasientocab}" class="form-control" readonly="true" style="background-color:#F1F4F8;">
                        </div>

                        <div class="d-grid gap-2 d-md-block col-12">
                          <a class="btn btn-primary" href="#"><span class="fas fa-plus me-2"></span>Crear</a>
                          <a class="btn btn-phoenix-primary" href="#"><span class="fa-solid fa-pen me-2"></span>Editar</a>
                          <a class="btn btn-phoenix-warning" href="#"><span
                              class="fa-solid fa-rotate-right me-2"></span>Procesar</a>
                          <a class="btn btn-phoenix-secondary" href="gestionAsientosContables">Atras</a>
                          <a class="btn btn-link text-900 me-4 px-0" href="#"><span
                              class="fa-solid fa-file-export fs--1 ms-2 me-2"></span>PDF</a>
                          <a class="btn btn-link text-900 me-4 px-0" href="#"><span
                              class="fa-solid fa-file-export fs--1 me-2"></span>Excel</a>
                        </div>
                        <div id="orderTable"
                          data-list='{"valueNames":["order","total","customer","payment_status","fulfilment_status","delivery_type","date"],"page":10,"pagination":true}'>
                          <div
                            class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1"
                            style="width: 160%;">
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
                                      style="width:5%;">SEC.ID</th>
                                    <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">TIPO.CTA
                                    </th>
                                    <th class="sort align-middle text-center ps-8 pe-4" scope="col" data-sort="date">
                                      CTA</th>
                                    <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">
                                      DES.CTA</th>
                                    <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">TIPO.DET
                                    </th>
                                    <th class="sort align-middle text-center ps-5 pe-5" scope="col" data-sort="date">
                                      COD.DET</th>
                                    <th class="sort align-middle text-center pe-3" scope="col" data-sort="date">
                                      COD.COM
                                    </th>
                                    <th class="sort align-middle text-center pe-3" scope="col" data-sort="date">CONCEPTO
                                    </th>
                                    <th class="sort align-middle text-center pe-3" scope="col" data-sort="date">
                                      CREDI.MNA
                                    </th>
                                    <th class="sort align-middle text-center pe-3" scope="col" data-sort="date">DEBI.MNA
                                    </th>
                                    <th class="sort align-middle text-center pe-3" scope="col" data-sort="date">GLOSA
                                    </th>
                                  </tr>
                                </thead>
                                <tbody class="list" id="order-table-body">
                                  <c:forEach var="lst_asiento_det" items="${requestScope.lst_asiento_det}">
                                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                      <td class="fs--1 align-middle px-0 py-3">
                                        <div class="form-check mb-0 fs-0">
                                          <input class="form-check-input" type="checkbox"
                                            data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}' />
                                        </div>
                                      </td>
                                      <td class="order align-middle white-space-nowrap py-0">
                                        ${lst_asiento_det.iexsecuencia}</td>
                                      <td class="total align-middle text-center fw-semi-bold text-1000">
                                        ${lst_asiento_det.tipcta_des}</td>
                                      <td class="total align-middle text-center fw-semi-bold text-1000">
                                        ${lst_asiento_det.iexcodcta}</td>
                                      <td
                                        class=" fulfilment_status align-middle white-space-nowrap text-center fw-bold text-700">
                                        ${lst_asiento_det.cta_des}</td>
                                      <td
                                        class="delivery_type align-middle white-space-nowrap text-center  fs--1 text-start">
                                        ${lst_asiento_det.tipdet_des}</td>
                                      <td
                                        class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">
                                        ${lst_asiento_det.iexcoddet}</td>
                                      <td
                                        class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">
                                        ${lst_asiento_det.iexcodcon}</td>
                                      <td
                                        class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">
                                        ${lst_asiento_det.concepto_des}</td>
                                      <td
                                        class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">
                                        ${lst_asiento_det.val_cre_na}</td>
                                      <td
                                        class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">
                                        ${lst_asiento_det.val_deb_na}</td>
                                      <td
                                        class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">
                                        ${lst_asiento_det.iexglosadet}</td>


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