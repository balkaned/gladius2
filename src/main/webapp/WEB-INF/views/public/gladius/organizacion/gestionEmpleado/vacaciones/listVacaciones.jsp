<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jan Quiroz
    Email	   : janquirozs@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
  <head>
    <jsp:include page="../../../../links.jsp"></jsp:include>
  </head>

  <script>
    function actualizar(){
        $('#modalLoadingVac').modal('show');
    }
  </script>

  <jsp:include page="../scriptsEmpl.jsp"></jsp:include>

  <body>
    <!-- ===============================================-->
    <!--    Main Content-->
    <!-- ===============================================-->
    <main class="main" id="top">
          <jsp:include page="../../../../navsMenu.jsp"></jsp:include>
          <jsp:include page="../../../../navTop.jsp"></jsp:include>
          <jsp:include page="../../../../modalFade.jsp"></jsp:include>

          <div class="content bg-100">
              <div class="pb-9">
                <div class="row mt-0 mb-1">
                    <div class="col-12">
                      <div class="row align-items-center justify-content-between">
                        <div class="col-12 col-md-auto">
                          <h2 class="mb-0"></h2>
                        </div>
                        <div class="col-12 col-md-auto">
                          <div class="d-flex">
                            <div class="flex-1 d-md-none">
                              <button class="btn px-3 btn-phoenix-secondary text-700 me-2" data-phoenix-toggle="offcanvas" data-phoenix-target="#productFilterColumn"><span class="fa-solid fa-bars"></span></button>
                            </div>
                          </div>
                        </div>
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
                                <div>
                                  <div class="col-12 mt-4">
                                    <h2 class="mb-0">Vacaciones</h2>
                                  </div>
                                  <div class="col-12 mt-4 mb-2 d-flex justify-content-end">
                                      <a class="btn btn-phoenix-secondary btn-sm px-5" href="detalleEmpl@${idTrab}"><span class="fa-solid fa-reply me-2"></span>Atras</a>
                                      <a class="btn btn-primary btn-sm ms-1" onclick="actualizar();" href="actualizarVacEmpl@${idTrab}"><span class="fa-solid fas fa-arrows-rotate me-2"></span>Actualizar</a>
                                  </div>
                                </div>
                                <div class="search-box w-100 mb-3">
                                  <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                    <input class="form-control search-input search" type="search" placeholder="Search..." aria-label="Search" />
                                    <span class="fas fa-search search-box-icon"></span>
                                  </form>
                                </div>
                                <div class="border-top border-bottom border-200" id="customerOrdersTable" data-list='{"valueNames":["dealName","amount","stage","probability","date","type"],"page":10,"pagination":true}'>
                                  <div class="table-responsive scrollbar">
                                    <table class="table table-sm fs--1 mb-0">
                                      <thead>
                                        <tr>
                                          <th class="sort white-space-nowrap align-middle pe-3 ps-0 text-uppercase" scope="col" data-sort="dealName" >Per. Inicio</th>
                                          <th class="sort align-middle pe-4 text-uppercase text-center" scope="col" data-sort="amount" >Per. Fin</th>
                                          <th class="sort align-middle pe-2 text-center text-uppercase" scope="col" data-sort="stage" >Fecha Inicio</th>
                                          <th class="sort align-middle pe-2 text-center text-uppercase" scope="col" data-sort="probability" >Fecha Fin</th>
                                          <th class="sort align-middle pe-3 text-center text-uppercase" scope="col" data-sort="probability">Dias Saldo</th>
                                          <th class="sort align-middle pe-3 text-center text-uppercase" scope="col" data-sort="probability">Dias Gan</th>
                                          <th class="sort align-middle pe-3 text-center text-uppercase" scope="col" data-sort="probability">Dias Pag y Goz</th>
                                          <th class="sort align-middle pe-3 text-center text-uppercase" scope="col" data-sort="probability">Dias Ven</th>
                                          <th class="sort align-middle pe-3 text-center text-uppercase" scope="col" ></th>
                                        </tr>
                                      </thead>
                                      <tbody class="list" id="customer-order-table-body">
                                        <c:forEach var="LstVacacionesCtl" items="${requestScope.LstVacacionesCtl}">
                                            <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                              <td class="order align-middle white-space-nowrap ps-0 text-center">${LstVacacionesCtl.iexpermesini}</td>
                                              <td class="total align-middle text-center fw-semi-bold pe-20 text-1000">${LstVacacionesCtl.iexpermesfin}</td>
                                              <td class="align-middle white-space-nowrap text-center fw-semi-bold ps-3 pe-3 text-1000"><a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstVacacionesCtl.iexfecini}</td>
                                              <td class="align-middle white-space-nowrap text-center fw-semi-bold text-1000 ps-3 pe-3"><a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstVacacionesCtl.iexfecfin}</td>
                                              <td class="align-middle white-space-nowrap text-center fw-bold text-1000 ">
                                                  <c:if test="${LstVacacionesCtl.iexdiassaldo<=0}"><span class="p-2 text-white bg-danger rounded-circle">${LstVacacionesCtl.iexdiassaldo}</span></c:if>
                                                  <c:if test="${LstVacacionesCtl.iexdiassaldo>0}"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">+ ${LstVacacionesCtl.iexdiassaldo}</span></c:if>
                                              </td>
                                              <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">${LstVacacionesCtl.iexdiasgan}</td>
                                              <td class="date align-middle white-space-nowrap fs--1 text-700 text-center ps-3 pe-3">${LstVacacionesCtl.iexdiasgoz}</td>
                                              <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3"><span class="badge badge-phoenix fs--2 badge-phoenix-danger">- ${LstVacacionesCtl.iexdiasven}</span></td>


                                              <td class="align-middle white-space-nowrap text-end pe-0 ps-5">
                                                <div class="font-sans-serif btn-reveal-trigger position-static">
                                                  <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                                  data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                                  <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                                  <div class="dropdown-menu dropdown-menu-end py-2">
                                                    <a id="dropdownmenutable" class="dropdown-item" href="verDetalleVac@${idTrab}@${LstVacacionesCtl.iexpermesini}@${LstVacacionesCtl.iexpermesfin}"><span class="fa-solid fa-chart-bar me-2"></span>Detalle</a>
                                                    <!--<div class="dropdown-divider"></div>
                                                    <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-trash me-2"></span>Eliminar</a>-->
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

  <div id="modalLoadingVac" class="modal fade" tabindex="-1" data-bs-backdrop="static" aria-labelledby="scrollingLongModalLabel2" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered">
  	  <div class="modal-content bg-100 rounded-2 border border-300">
  		<form class="needs-validation" method="POST" action="" novalidate >
  			<div class="modal-header border-bottom border-300 bg-300 bg-opacity-25 p-4">
  			   <h5 id="h5modalLoadinglabel" class="modal-title text-1000 fs-2 lh-sm">Actualizando data de vacaciones</h5>
  			   <!--<button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>-->
  			</div>
  			<div class="modal-body p-4 bg-300 bg-opacity-50 pt-3 pb-0">
  			  <div class="mt-0 mb-0">
  				  <p class="fs--1">Se esta procesando la transacción y actualizando espere unos minutos hasta que haya finalizado la tarea...</p>
  				  <div class="col-12 text-center">
  					  <div id="iconspinner" class="spinner-border text-primary" role="status">
  						<span class="visually-hidden">Loading...</span>
  					  </div>
  				  </div>
  			  </div>
  			</div>
  			<div class="modal-footer bg-300 bg-opacity-25 d-flex justify-content-end align-items-center px-0 pb-0 border-top border-300 pt-0">
  				<button id="btnFooter" class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" href="#"><div class="spinner-border spinner-border-sm" style="height:13px; width:13px;" role="status"></div><span id="spanbtnModalLoading" class="ms-2">Procesando transacción</span></button>
  			</div>
  		</form>
  	  </div>
    </div>
  </div>
</html>