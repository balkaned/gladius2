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
                                    <h2 class="mb-0">Derecho habientes</h2>
                                  </div>
                                  <div class="col-12 mt-4 mb-2 d-flex justify-content-end">
                                      <a class="btn btn-phoenix-primary px-5" href="detalleEmpl@${idTrab}">Atras</a>
                                      <a class="btn btn-primary ms-2" href="nuevoDerechoHab@${idTrab}"><span class="fa-solid fa-plus me-2"></span>Add Derch Hab</a>
                                  </div>
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
                                			  <th class="sort align-middle text-center ps-5 pe-5 text-uppercase" scope="col" data-sort="total">Nombres y Apellidos</th>
                                			  <th class="sort align-middle text-center white-space-nowrap ps-3 pe-3 text-uppercase" scope="col" data-sort="payment_status" >Vinculo</th>
                                			  <th class="sort align-middle text-center white-space-nowrap ps-3 pe-3 text-uppercase" scope="col" data-sort="payment_status" >Tipo Doc</th>
                                			  <th class="sort align-middle text-center white-space-nowrap text-start ps-3 pe-3 text-uppercase" scope="col" data-sort="fulfilment_status" >Nro Doc</th>
                                			  <th class="sort align-middle white-space-nowrap text-center text-uppercase ps-3 pe-3" scope="col" data-sort="delivery_type" >Fec Nac</th>
                                			  <th class="sort text-end text-center align-middle ps-3 pe-3 text-uppercase" scope="col"></th>
                                			</tr>
                                		  </thead>
                                		  <tbody class="list" id="customer-order-table-body">
                                			<c:forEach var="LovDerhab" items="${requestScope.LovDerhab}">
                                				<tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                				  <td class="order align-middle white-space-nowrap ps-0"><a class="fw-semi-bold" href="#!">#${LovDerhab.iexcoddep}</a></td>
                                				  <td class="customer align-middle white-space-nowrap pe-5 ps-5">
                                                    <a class="d-flex align-items-center text-900" href="#">
                                                        <div class="avatar avatar-m">
                                                            <img class="rounded-circle"
                                                            src="AWSorFTP_flgsource@verFotoDerechoHab@${idComp}@null@${iexlogo}@${LovDerhab.iexcoddep}@null@null@null@null@null"
                                                            alt="" />
                                                        </div>
                                                        <h6 class="mb-0 ms-3 text-900">${LovDerhab.iexapepatdep} ${LovDerhab.iexapematdep} ${LovDerhab.iexnomdep}</h6>
                                                      </a>
                                                  </td>
                                				  <td class="align-middle white-space-nowrap text-center text-700"><span class="badge badge-phoenix fs--1 badge-phoenix-info"><span class="badge-label">${LovDerhab.destipvinculo}</span></td>
                                				  <td class="align-middle white-space-nowrap text-center text-700">${LovDerhab.destipnroiddep}</td>
                                				  <td class="date align-middle white-space-nowrap fs--1 text-700 text-center pe-4">${LovDerhab.iexnroiddep}</td>
                                				  <td class="align-middle white-space-nowrap text-center fw-semi-bold text-1000"><a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LovDerhab.iexfecnac}</td>

                                				  <td class="align-middle white-space-nowrap text-end pe-0 ps-5">
                                					<div class="font-sans-serif btn-reveal-trigger position-static">
                                					  <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                                      data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                                      <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                                      <div class="dropdown-menu dropdown-menu-end py-2">
                                					    <a id="dropdownmenutable"  href="#" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#subirDerechoHabFoto${LovDerhab.iexcoddep}" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-camera me-2"></span>Agregar foto</a>
                                						<div class="dropdown-divider"></div>
                                						<a id="dropdownmenutable"  class="dropdown-item" href="#!"><span class="fa-solid fa-trash me-2"></span>Eliminar</a>
                                					  </div>
                                					</div>
                                				  </td>
                                				</tr>

                                				<div class="modal fade" id="subirDerechoHabFoto${LovDerhab.iexcoddep}" tabindex="-1">
                                                    <div class="modal-dialog modal-dialog-centered">
                                                      <div class="modal-content border">
                                                          <div class="modal-header border-200 bg-soft p-4">
                                                            <h5 class="modal-title text-1000 fs-2 lh-sm">Imagen de derecho habiente</h5>
                                                            <button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>
                                                          </div>
                                                          <div class="modal-body pt-4 pb-2 px-4">
                                                              <div class="col-12 col-md-12">
                                                                <form method="post"
                                                                action="AWSorFTP_flgsource_MultipartUpload@subirFotoDerHabiente@${idComp}@${idTrab}@null"
                                                                enctype="multipart/form-data" >
                                                                    <input type="hidden" name="idimg" value="${nrodoc}" >
                                                                    <input type="hidden" name="idDerHab" value="${LovDerhab.iexcoddep}" >
                                                                    <div class="mb-3">
                                                                          <label class="form-label">Subir Imagen solo en formato .jpg</label>
                                                                          <input class="form-control" name="uploadFile" type="file" />
                                                                    </div>

                                                                    <div class="col-sm-6 col-md-12 mt-2 mb-4">
                                                                      <div class="form-floating">
                                                                          <button class="btn btn-primary justify-content-end me-2 col-7" type="submit" ><span class="fa-solid fas fa-camera me-2"></span><span>Subir Foto</span></button>
                                                                      </div>
                                                                    </div>
                                                                </form>
                                                              </div>

                                                          </div>
                                                          <div class="modal-footer d-flex justify-content-end align-items-center px-0 pb-0 border-200 pt-0">
                                                            <!--<button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="submit"> <span class="fas fa-arrows-rotate me-2 fs--2"></span>Otros</button>-->
                                                            <button class="btn btn-sm btn-primary px-9 fs--2 my-0 mt-1" data-bs-dismiss="modal" type="submit">Cerrar</button>
                                                          </div>
                                                      </div>
                                                    </div>
                                                </div>
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