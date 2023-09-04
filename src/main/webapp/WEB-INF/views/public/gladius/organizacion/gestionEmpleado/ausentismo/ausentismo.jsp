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
                                  <h2 class="mb-0">Ausentismo</h2>
                                  <div class="col-auto">
                                      <a class="btn btn-phoenix-primary px-5" href="detalleEmpl@${idTrab}">Atras</a>
                                      <a class="btn btn-primary btn-sm" href="nuevoAusentismo@${idTrab}"><span class="fa-solid fa-plus me-2"></span>Add Ausentismo</a>
                                  </div>
                                </div>
                                <div class="search-box w-100 mb-3">
                                  <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                    <input class="form-control search-input search" type="search" placeholder="Search..." aria-label="Search" />
                                    <span class="fas fa-search search-box-icon"></span>
                                  </form>
                                </div>
                                <div class="border-top border-bottom border-200" id="leadDetailsTable" data-list='{"valueNames":["dealName","amount","stage","probability","date","type"],"page":5,"pagination":true}'>
                                  <div class="table-responsive scrollbar mx-n1 px-1">
                                    <table class="table fs--1 mb-0">
                                      <thead>
                                        <tr>
                                          <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select='{"body":"lead-details-table-body"}' />
                                            </div>
                                          </th>
                                          <th class="sort white-space-nowrap align-middle pe-3 ps-0 text-uppercase" scope="col" data-sort="dealName" >ID</th>
                                          <th class="sort align-middle pe-6 text-uppercase text-center" scope="col" data-sort="amount" >Tipo de Ausentismo</th>
                                          <th class="sort align-middle text-center text-uppercase" scope="col" data-sort="stage" >Fecha Inicio</th>
                                          <th class="sort align-middle text-center text-uppercase" scope="col" data-sort="probability" >Fecha Fin</th>
                                          <th class="sort align-middle text-center text-uppercase" scope="col" data-sort="probability" >Nro de Dias</th>
                                          <th class="align-middle pe-0 text-center" scope="col"></th>
                                        </tr>
                                      </thead>
                                      <tbody class="list" id="lead-details-table-body">
                                        <c:forEach var="LstAusentismoDet" items="${requestScope.LstAusentismoDet}">
                                            <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                              <td class="fs--1 align-middle px-0 py-3">
                                                <div class="form-check mb-0 fs-0">
                                                  <input class="form-check-input" type="checkbox" data-bulk-select-row='{"dealName":"Mocking Bird","active":true,"amount":"$6,800,000","stage_status":{"label":"won deal","type":"badge-phoenix-success"},"progress":{"min":"67","max":"145","color":"bg-info"},"date":"Dec 29, 2021","type_status":{"label":"warm","type":"badge-phoenix-info"}}' />
                                                </div>
                                              </td>
                                              <td class="dealName align-middle white-space-nowrap py-2 ps-0"><a class="fw-semi-bold text-primary" href="#!">${LstAusentismoDet.iexcorrel}</a></td>
                                              <td class="amount align-middle white-space-nowrap text-center fw-bold text-700 py-2 pe-6">${LstAusentismoDet.destipaus}</td>
                                              <td class="dealName align-middle white-space-nowrap text-center py-2 ps-0">${LstAusentismoDet.iexfecini}</td>
                                              <td class="dealName align-middle white-space-nowrap text-center py-2 ps-0">${LstAusentismoDet.iexfecfin}</td>
                                              <td class="dealName align-middle white-space-nowrap text-center py-2 ps-0">${LstAusentismoDet.iexnrodias}</td>

                                              <td class="align-middle text-end white-space-nowrap pe-0 action py-2">
                                                <div class="font-sans-serif btn-reveal-trigger position-static">
                                                  <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                                  <div class="dropdown-menu dropdown-menu-end py-2">
                                                     <div class="dropdown-divider"></div><a class="dropdown-item text-danger" href="#!">Remove</a>
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