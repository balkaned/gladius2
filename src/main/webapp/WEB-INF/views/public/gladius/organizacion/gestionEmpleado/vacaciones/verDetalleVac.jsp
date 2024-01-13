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
                                  <div class="col-12 mt-2">
                                    <h2 class="mb-0">Tabla de vacaciones</h2>
                                  </div>
                                  <div class="col-12 mt-2 mb-2 d-flex justify-content-end">
                                      <a class="btn btn-phoenix-primary px-5" href="vacaciones@${idTrab}">Atras</a>
                                      <a class="btn btn-primary ms-2" href="nuevasVacacionesValidacion@${idTrab}@${perini}@${perfin}"><span class="fa-solid fa-plus me-2"></span>Add Vacaciones</a>
                                  </div>
                                </div>
                                <c:if test="${requestScope.msgErrorSaldoVacId eq 'ERVAC01'}">
                                     <div class="alert alert-danger alert-dismissible " role="alert">
                                        <strong>Error!</strong> ${requestScope.msgErrorSaldoVac}
                                     </div>
                                </c:if>
                                <div class="mb-4">
                                    <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-calendar-alt"></span>
                                      <h5 class="mb-0">Año</h5>
                                    </div>
                                    <p class="mb-0 text-800"><span class="badge badge-phoenix fs--1 badge-phoenix-primary"><span class="badge-label">${perini} - ${perfin}</span><span class="ms-1" style="height:12.8px;width:12.8px;"></span></span></p>
                                </div>
                                <div class="search-box w-100 mb-3">
                                  <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                    <input class="form-control search-input search" type="search" placeholder="Search..." aria-label="Search" />
                                    <span class="fas fa-search search-box-icon"></span>
                                  </form>
                                </div>

                                <div class="border-top border-bottom border-200" id="customerOrdersTable" data-list='{"valueNames":["order","total","payment_status","fulfilment_status","delivery_type","date"],"page":6,"pagination":true}'>
                                	<div class="table-responsive scrollbar">
                                		<table class="table table-sm fs--1 mb-0">
                                		  <thead>
                                			<tr>
                                			  <th class="sort white-space-nowrap align-middle ps-0 pe-3 text-uppercase" scope="col" data-sort="order" >ID</th>
                                			  <th class="sort align-middle text-center ps-5 pe-5 text-uppercase" scope="col" data-sort="total">Año Ini</th>
                                			  <th class="sort align-middle text-center white-space-nowrap ps-3 pe-3 text-uppercase" scope="col" data-sort="payment_status" >Año Fin</th>
                                			  <th class="sort align-middle text-center white-space-nowrap text-start ps-3 pe-3 text-uppercase" scope="col" data-sort="fulfilment_status" >Fecha Inicio</th>
                                			  <th class="sort align-middle white-space-nowrap text-center text-uppercase ps-3 pe-3" scope="col" data-sort="delivery_type" >Fecha Fin</th>
                                			  <th class="sort align-middle text-center ps-3 pe-3 text-uppercase" scope="col" data-sort="date">Nro Dias</th>
                                			  <th class="sort align-middle text-center ps-3 pe-3 text-uppercase" scope="col" data-sort="date">Tipo de Vacacion</th>
                                			  <th class="sort text-end text-center align-middle ps-3 pe-3 text-uppercase" scope="col"></th>
                                			</tr>
                                		  </thead>
                                		  <tbody class="list" id="customer-order-table-body">
                                			<c:forEach var="LstVacacionesPer" items="${requestScope.LstVacacionesPer}">
                                				<tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                				  <td class="align-middle white-space-nowrap ps-3 pe-3"><a class="fw-semi-bold" href="#!">#${LstVacacionesPer.iexcorrel}</a></td>
                                				  <td class="align-middle text-center fw-semi-bold ps-3 pe-3 text-1000">${LstVacacionesPer.iexpermesini}</td>
                                				  <td class="align-middle white-space-nowrap text-center text-1000 ps-3 pe-3">${LstVacacionesPer.iexpermesfin}</td>
                                				  <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">${LstVacacionesPer.iexfecini}</td>
                                				  <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">${LstVacacionesPer.iexfecfin}</td>
                                				  <td class="date align-middle white-space-nowrap fs--1 text-700 text-center ps-3 pe-3">${LstVacacionesPer.iexnrodias}</td>
                                				  <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3"><span class="badge badge-phoenix fs--1 badge-phoenix-info"><span class="badge-label">${LstVacacionesPer.destipvac}</span></td>

                                				  <td class="align-middle white-space-nowrap text-end pe-0 ps-5">
                                					<div class="font-sans-serif btn-reveal-trigger position-static">
                                					  <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                					  <div class="dropdown-menu dropdown-menu-end py-2">
                                						<div class="dropdown-divider"></div>
                                						<a class="dropdown-item text-warning" href="#!">Remove</a>
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