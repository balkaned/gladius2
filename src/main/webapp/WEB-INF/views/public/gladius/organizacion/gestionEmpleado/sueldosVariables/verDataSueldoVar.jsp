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
                                    <h2 class="mb-0">Conceptos variables</h2>
                                  </div>
                                  <div class="col-12 mt-5 mb-2 d-flex justify-content-end">
                                      <a class="btn btn-phoenix-primary px-5" href="sueldoVariable@${idTrab}">Atras</a>
                                      <a class="btn btn-primary ms-2" href="nuevoSueldoVar@${idTrab}@${iexcodpro}@${iexperiodo}"><span class="fa-solid fa-plus me-2"></span>Add Concepto</a>
                                  </div>
                               </div>

                               <div class="row mt-1 gx-4 gy-6 g-xl-7 justify-content-sm-center justify-content-xl-start">
                                   <div class="col-12 col-sm-auto">
                                     <div class="row g-4 flex-sm-column">
                                       <div class="col-6 col-sm-12">
                                         <div class="d-flex align-items-center mb-1"><span class="me-2" data-feather="user" style="stroke-width:2.5;"></span>
                                           <h6 class="mb-0">Planilla</h6>
                                         </div><a class="fs--1 ms-4" href="#!">#${iexcodpro}</a>
                                       </div>
                                     </div>
                                   </div>
                                   <div class="col-12 col-sm-auto">
                                     <div class="row g-4 flex-sm-column">
                                       <div class="col-6 col-sm-12">
                                         <div class="d-flex align-items-center mb-1"><span class="me-2" data-feather="calendar" style="stroke-width:2.5;">  </span>
                                           <h6 class="mb-0">Periodo pendiente</h6>
                                         </div><a class="fs--1 ms-4" href="!"><span class="badge badge-phoenix fs--1 badge-phoenix-danger"><span class="badge-label">${iexperiodo}</span></a>
                                       </div>
                                     </div>
                                   </div>
                               </div>

                               <div class="search-box w-100 mt-4 mb-3">
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
                               			  <th class="sort align-middle text-center ps-5 pe-5 text-uppercase" scope="col" data-sort="total">Descripcion Concepto</th>
                               			  <th class="sort align-middle text-center white-space-nowrap ps-3 pe-3 text-uppercase" scope="col" data-sort="payment_status" >Valor</th>
                               			  <th class="sort text-end text-center align-middle ps-3 pe-3 text-uppercase" scope="col"></th>
                               			</tr>
                               		  </thead>
                               		  <tbody class="list" id="customer-order-table-body">
                               			<c:forEach var="fdatavar" items="${requestScope.fdatavar}">
                               				<tr class="hover-actions-trigger btn-reveal-trigger position-static">
                               				  <td class="order align-middle white-space-nowrap ps-3 pe-3 text-start"><a class="fw-semi-bold" href="#!">#${fdatavar.iexcodpro}</a></td>
                               				  <td class="total align-middle text-start fw-semi-bold ps-3 pe-3 text-1000"><span class="badge badge-phoenix fs--2 badge-phoenix-warning"><span class="badge-label">${fdatavar.coodescon}</span></td>
                               				  <td class="align-middle white-space-nowrap text-center text-800 ps-3 pe-3">${fdatavar.iexvalcon}</td>

                               				  <td class="align-middle white-space-nowrap text-end pe-0 ps-5">
                               					<div class="font-sans-serif btn-reveal-trigger position-static">
                               					  <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                                  data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                                  <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                                  <div class="dropdown-menu dropdown-menu-end py-2">
                               						<a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-pencil me-2"></span>Editar</a>
                               						<div class="dropdown-divider"></div>
                               						<a id="dropdownmenutable" class="dropdown-item" href="#!"><span class="fa-solid fa-trash me-2"></span>Eliminar</a>
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