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
    <jsp:include page="../../../../links.jsp"></jsp:include>
  </head>

  <jsp:include page="../scriptsEmpl.jsp"></jsp:include>

  <body>
    <!-- ===============================================-->
    <!--    Main Content-->
    <!-- ===============================================-->
    <main class="main" id="top">
          <jsp:include page="../../../../navsMenu.jsp"></jsp:include>
          <jsp:include page="../../../../navTop.jsp"></jsp:include>
          <jsp:include page="../../../../modalFade.jsp"></jsp:include>

          <div class="content">
              <div class="pb-9">
                <div class="row">
                  <div class="col-12">
                    <div class="row align-items-center justify-content-between g-3 mb-3">
                    </div>
                  </div>
                </div>
                <div class="row g-0 g-md-4 g-xl-6">

                  <jsp:include page="../navsGenericEmpl.jsp"></jsp:include>

                  <div class="col-md-7 col-lg-7 col-xl-8">
                    <div class="lead-details-container">
                      <div class="tab-content" id="myTabContent">
                         <div class="tab-pane fade active show" id="tab-activity" role="tabpanel" aria-labelledby="activity-tab">
                            <div class="mb-8">
                                <div class="d-flex justify-content-between align-items-center mb-4" id="scrollspyDeals">
                                  <h2 class="mb-0">Vacaciones</h2>
                                  <div class="col-auto">
                                      <a class="btn btn-phoenix-primary px-5" href="detalleEmpl@${idTrab}">Atras</a>
                                      <a class="btn btn-primary btn-sm" href="actualizar@${idTrab}"><span class="fa-solid fas fa-wrench me-2"></span>Actualizar</a>
                                  </div>
                                </div>
                                <div class="search-box w-100 mb-3">
                                  <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                    <input class="form-control search-input search" type="search" placeholder="Search..." aria-label="Search" />
                                    <span class="fas fa-search search-box-icon"></span>
                                  </form>
                                </div>
                                <div class="border-top border-bottom border-200" id="customerOrdersTable" data-list='{"valueNames":["dealName","amount","stage","probability","date","type"],"page":5,"pagination":true}'>
                                  <div class="table-responsive scrollbar">
                                    <table class="table table-sm fs--1 mb-0">
                                      <thead>
                                        <tr>
                                          <th class="sort white-space-nowrap align-middle pe-3 ps-0 text-uppercase" scope="col" data-sort="dealName" >Per. Inicio</th>
                                          <th class="sort align-middle pe-4 text-uppercase text-center" scope="col" data-sort="amount" >Per. Fin</th>
                                          <th class="sort align-middle pe-2 text-center text-uppercase" scope="col" data-sort="stage" >Fecha Inicio</th>
                                          <th class="sort align-middle pe-2 text-center text-uppercase" scope="col" data-sort="probability" >Fecha Fin</th>
                                          <th class="sort align-middle pe-3 text-center text-uppercase" scope="col" data-sort="probability">Dias Gan</th>
                                          <th class="sort align-middle pe-3 text-center text-uppercase" scope="col" data-sort="probability">Dias Pag y Goz</th>
                                          <th class="sort align-middle pe-3 text-center text-uppercase" scope="col" data-sort="probability">Dias Ven</th>
                                          <th class="sort align-middle pe-3 text-center text-uppercase" scope="col" data-sort="probability">Dias Saldo</th>
                                          <th class="sort align-middle pe-3 text-center text-uppercase" scope="col" >Ver</th>
                                        </tr>
                                      </thead>
                                      <tbody class="list" id="customer-order-table-body">
                                        <c:forEach var="LstVacacionesCtl" items="${requestScope.LstVacacionesCtl}">
                                            <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                              <td class="order align-middle white-space-nowrap ps-0 text-center"><a class="fw-semi-bold" href="#!">${LstVacacionesCtl.iexpermesini}</a></td>
                                              <td class="total align-middle text-center fw-semi-bold pe-20 text-1000"><a class="fw-semi-bold" href="#!">${LstVacacionesCtl.iexpermesfin}</a></td>
                                              <td class="align-middle white-space-nowrap text-center ps-3 pe-3 text-700">${LstVacacionesCtl.iexfecini}</td>
                                              <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">${LstVacacionesCtl.iexfecfin}</td>
                                              <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">${LstVacacionesCtl.iexdiasgan}</td>
                                              <td class="date align-middle white-space-nowrap fs--1 text-700 text-center ps-3 pe-3">${LstVacacionesCtl.iexdiasgoz}</td>
                                              <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3"><span class="badge badge-phoenix fs--1 badge-phoenix-danger"><span class="badge-label">${LstVacacionesCtl.iexdiasven}</span></td>
                                              <td class="align-middle white-space-nowrap text-center fw-bold text-1000 "><span class="badge badge-phoenix fs--1 badge-phoenix-info"><span class="badge-label">${LstVacacionesCtl.iexdiassaldo}</span></td>

                                              <td class="align-middle white-space-nowrap text-end pe-0 ps-5">
                                                <div class="font-sans-serif btn-reveal-trigger position-static">
                                                  <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                                  <div class="dropdown-menu dropdown-menu-end py-2">
                                                    <div class="dropdown-divider"></div>
                                                    <a class="dropdown-item text-primary" href="verDetalleVac@${idTrab}@${LstVacacionesCtl.iexpermesini}@${LstVacacionesCtl.iexpermesfin}">Ver Detalle</a>
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
                                      <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!" data-list-view="*">View all<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none" href="#!" data-list-view="less">View Less<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
                                    </div>
                                    <div class="col-auto d-flex">
                                      <button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                                      <ul class="mb-0 pagination"></ul>
                                      <button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span></button>
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

          <jsp:include page="../../../../demoWidget.jsp"></jsp:include>
    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->

    <jsp:include page="../../../../customize.jsp"></jsp:include>
  </body>
</html>