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
    <script src="resources/assets/js/gladius/scriptsEmpl.js"></script>
  </head>

  <script>
    function actualizar(){
        $('#modalLoadingVac').modal('show');
    }
  </script>

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
                                <div id="orderTable" data-list='{"valueNames":["perini","perfin","fecini","fecfin","dsald","dvenc"],"page":20,"pagination":true}' >
                                    <div class="search-box w-100 mb-3">
                                      <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                        <input class="form-control search-input search" type="search" placeholder="Search..." aria-label="Search" />
                                        <span class="fas fa-search search-box-icon"></span>
                                      </form>
                                    </div>
                                    <div class="card mb-3 border-200 shadow-none">
                                      <div class="card-header py-2 px-3 bg-100 d-flex justify-content-between align-items-center">
                                        <small class="mb-0 fw-semi-bold text-900">Vacaciones — registros</small>
                                        <small class="text-muted">Registros: <span data-list-info="data-list-info"></span></small>
                                      </div>
                                      <div class="card-body p-0">
                                        <div class="table-responsive scrollbar">
                                          <table class="table table-hover table-sm mb-0">
                                            <thead class="bg-white">
                                              <tr class="fs--2">
                                                <th class="align-middle ps-2 pe-2 text-uppercase text-center" scope="col" data-sort="perini">Per. Inicio</th>
                                                <th class="align-middle ps-2 pe-2 text-uppercase text-center" scope="col" data-sort="perfin">Per. Fin</th>
                                                <th class="align-middle ps-2 pe-2 text-uppercase text-center" scope="col" data-sort="fecini">Fecha Inicio</th>
                                                <th class="align-middle ps-2 pe-2 text-uppercase text-center" scope="col" data-sort="fecfin">Fecha Fin</th>
                                                <th class="align-middle ps-2 pe-2 text-uppercase text-center" scope="col" data-sort="dsald">Dias Saldo</th>
                                                <th class="align-middle ps-2 pe-2 text-uppercase text-center" scope="col">Dias Gan</th>
                                                <th class="align-middle ps-2 pe-2 text-uppercase text-center" scope="col">Dias Pag y Goz</th>
                                                <th class="align-middle ps-2 pe-2 text-uppercase text-center" scope="col" data-sort="dvenc">Dias Ven</th>
                                                <th class="align-middle ps-2 pe-2 text-uppercase text-center" scope="col"></th>
                                              </tr>
                                            </thead>
                                            <tbody class="list" id="customer-order-table-body">
                                              <c:forEach var="LstVacacionesCtl" items="${requestScope.LstVacacionesCtl}">
                                                <tr class="hover-actions-trigger btn-reveal-trigger position-static align-middle">
                                                  <td class="perini text-center py-1"><span class="badge bg-secondary text-white small">${LstVacacionesCtl.iexpermesini}</span></td>
                                                  <td class="perfin text-center py-1"><span class="badge bg-secondary text-white small">${LstVacacionesCtl.iexpermesfin}</span></td>
                                                  <td class="fecini text-center py-1"><span class="fa-solid fa-calendar-days me-2 text-muted"></span> ${LstVacacionesCtl.iexfecini}</td>
                                                  <td class="fecfin text-center py-1"><span class="fa-solid fa-calendar-days me-2 text-muted"></span> ${LstVacacionesCtl.iexfecfin}</td>
                                                  <td class="text-center py-1">
                                                      <c:if test="${LstVacacionesCtl.iexdiassaldo<=0}"><span class="dsald p-1 text-white bg-danger rounded-circle">${LstVacacionesCtl.iexdiassaldo}</span></c:if>
                                                      <c:if test="${LstVacacionesCtl.iexdiassaldo>0}"><span class="dsald badge badge-phoenix fs--2 badge-phoenix-success small"><span class="badge-label">+ ${LstVacacionesCtl.iexdiassaldo}</span></span></c:if>
                                                  </td>
                                                  <td class="text-center py-1 text-700">${LstVacacionesCtl.iexdiasgan}</td>
                                                  <td class="text-center py-1 text-700">${LstVacacionesCtl.iexdiasgoz}</td>
                                                  <td class="dvenc text-center py-1 text-700">${LstVacacionesCtl.iexdiasven}</td>
                                                  <td class="text-end py-1">
                                                    <div class="btn-group btn-group-sm">
                                                      <a class="btn btn-outline-secondary btn-sm" href="verDetalleVac@${idTrab}@${LstVacacionesCtl.iexpermesini}@${LstVacacionesCtl.iexpermesfin}" title="Detalle"><span class="fa-solid fa-chart-bar"></span></a>
                                                    </div>
                                                  </td>
                                                </tr>
                                              </c:forEach>
                                            </tbody>
                                          </table>
                                        </div>
                                        <div class="row align-items-center justify-content-between py-1 px-3 fs--2">
                                          <div class="col-auto">
                                            <p class="mb-0 d-none d-sm-block text-muted" data-list-info="data-list-info"></p>
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
              <jsp:include page="../../../../footer.jsp"></jsp:include>
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