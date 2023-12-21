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
    <jsp:include page="../../../links.jsp"></jsp:include>
  </head>

  <script>
  	  function remove() {
  		var opcion = confirm("Esta seguro de Eliminar el Registro?");
  		if (opcion == true) {
  			return true;
  		} else {
  			return false;
  		}
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
            <div class="mb-12">
              <div class="row g-3 mb-4">
                <div class="col-auto">
                  <h2 id="h2top" class="mb-0">Detalle de tablas genéricas</h2>
                </div>
              </div>

              <div class="row g-5">
                 <div class="col-xl-7">
                   <div class="row gx-3 gy-4">
                      <form class="row g-4 mb-0 needs-validation" method="POST" action="modificarTblGenDetalle" novalidate >
                        <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                        <input class="form-control" name="iexcodtab2" type="hidden" value="${sessionScope.ttablaclbl.iexcodtab}" />

                        <div class="col-sm-6 col-md-3">
                               <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* ID</label>
                               <input class="form-control" name="iexcodtab" type="number"  value="${sessionScope.ttablaclbl.iexcodtab}" required readonly disabled/>
                        </div>
                        <div class="col-sm-6 col-md-9">
                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Nombre de la Tabla</label>
                              <input class="form-control" name="iexdestab" type="text" value="${sessionScope.ttablaclbl.iexdestab}" placeholder="" required readonly disabled/>
                        </div>
                        <div class="col-sm-6 col-md-3">
                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* ID Key</label>
                              <input class="form-control" name="iexkey" type="text" value="${ttabladxx.iexkey}" placeholder="" required/>
                        </div>
                        <div class="col-sm-6 col-md-9">
                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Descripcion Key</label>
                              <input class="form-control" name="desdet" type="text" value="${ttabladxx.desdet}" placeholder="" required/>
                        </div>
                        <table class="navy" >
                             <tr>
                                 <td>
                                      <table  class="navy"   >
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg1=='1'}">
                                                <tr>
                                                <td>${sessionScope.ttablaclbl.iexlbl1}</td>
                                                <td><input type='text' name='des1det' value="${ttabladxx.des1det}" class="form-control"  style="width: 250px ;background:#fcefa1"  ></td>
                                                </tr>
                                          </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg2=='1'}">
                                                <tr>
                                                <td>${sessionScope.ttablaclbl.iexlbl2}</td>
                                                <td><input type='text' name='des2det'  value="${ttabladxx.des2det}"  class="form-control"  style="width: 250px ;background:#fcefa1"  ></td>
                                               </tr>
                                           </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg3=='1'}">

                                                <tr>

                                                <td>${sessionScope.ttablaclbl.iexlbl3}</td>
                                                <td><input type='text' name='des3det'   value="${ttabladxx.des3det}"   class="form-control"  style="width: 250px ;background:#fcefa1"  ></td>
                                                </tr>
                                           </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg4=='1'}">
                                                <tr>
                                                <td>${sessionScope.ttablaclbl.iexlbl4}</td>
                                                <td><input type='text' name='des4det'  value="${ttabladxx.des4det}" class="form-control"  style="width: 250px ;background:#fcefa1"  ></td>
                                                </tr>
                                            </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg5=='1'}">

                                                <tr>

                                                <td>${sessionScope.ttablaclbl.iexlbl5}</td>
                                                <td><input type='text' name='des5det'  value="${ttabladxx.des5det}"  class="form-control"  style="width: 250px ;background:#fcefa1"  ></td>
                                                </tr>
                                          </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg2=='6'}">

                                                <tr>
                                                <td>${sessionScope.ttablaclbl.iexlbl6}</td>
                                                <td><input type='text' name='des6det' value="${ttabladxx.des6det}"   class="form-control"  style="width: 250px ;background:#fcefa1"></td>
                                                </tr>

                                          </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg2=='7'}"  >

                                                <tr>

                                                <td>${sessionScope.ttablaclbl.iexlbl7}</td>
                                                <td><input type='text' name='des7det' value="${ttabladxx.des7det}"  class="form-control"  style="width: 250px ;background:#fcefa1" ></td>
                                                </tr>

                                           </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg2=='8'}">

                                                <tr>
                                                <td>${sessionScope.ttablaclbl.iexlbl8}</td>
                                                <td><input type='text' name='des8det' value="${ttabladxx.des8det}" class="form-control"  style="width: 250px ;background:#fcefa1" ></td>
                                                </tr>
                                                </c:if>
                                      </table>
                                 </td>
                                 <td>
                                     <table class="navy" >
                                         <c:if test="${sessionScope.ttablaclbl.iexlblflg9=='1'}">
                                            <tr>
                                           <td>${sessionScope.ttablaclbl.iexlblval9}</td>
                                            <td><input type='text' name='val9det' value="${ttabladxx.val9det}"  class="form-control"  style="width: 250px ;background:#fcefa1" ></td>
                                            </tr>
                                            </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg10=='1'}">

                                                <tr>
                                            <td>${sessionScope.ttablaclbl.iexlblval10}</td>
                                            <td><input type='text' name='val10det' value="${ttabladxx.val10det}"  class="form-control"  style="width: 250px ;background:#fcefa1"  ></td>
                                            </tr>
                                          </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg11=='1'}">
                                                <tr>
                                            <td>${sessionScope.ttablaclbl.iexlblval11}</td>
                                            <td><input type='text' name='val11det' value="${ttabladxx.val11det}"  class="form-control"  style="width: 250px ;background:#fcefa1"  ></td>
                                            </tr>
                                               </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg12=='1'}">

                                                <tr>
                                            <td>${sessionScope.ttablaclbl.iexlblval12}</td>
                                            <td><input type='text' name='val12det' value="${ttabladxx.val12det}"  class="form-control"  style="width: 250px ;background:#fcefa1"  ></td>
                                             </tr>

                                                </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg13=='1'}">
                                                <tr>
                                              <td>${sessionScope.ttablaclbl.iexlblval13}</td>
                                            <td><input type='text' name='val13det' value="${ttabladxx.val13det}" class="form-control"  style="width: 250px ;background:#fcefa1"  ></td>
                                            </tr>

                                               </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg14=='1'}">
                                                <tr>
                                            <td>${sessionScope.ttablaclbl.iexlblval14}</td>
                                            <td><input type='text' name='val14det' value="${ttabladxx.val14det}"  class="form-control"  style="width: 250px ;background:#fcefa1" ></td>
                                           </tr>

                                              </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg15=='1'}">
                                                <tr>
                                          <td>${sessionScope.ttablaclbl.iexlblval15}</td>
                                            <td><input type='text' name='val15det' value="${ttabladxx.val15det}"  class="form-control"  style="width: 250px ;background:#fcefa1" ></td>
                                            </tr>

                                               </c:if>
                                          <c:if test="${sessionScope.ttablaclbl.iexlblflg16=='1'}">
                                                <tr>
                                            <td>${sessionScope.ttablaclbl.iexlblval16}</td>
                                            <td><input type='text' name='val16det' value="${ttabladxx.val16det}"  class="form-control"  style="width: 250px ;background:#fcefa1"  ></td>
                                          </tr>
                                           </c:if>
                                     </table>
                                 </td>
                             </tr>
                         </table>


                        <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                Se grabó exitosamente los cambios.
                        </div>
                        <div class="col-12 gy-6">
                            <div class="row g-3 justify-content-end">
                              <div class="col-auto">
                                <a class="btn btn-phoenix-primary px-5" href="listTablasGen">Atras</a>
                              </div>
                              <div class="col-auto">
                                <button class="btn btn-primary px-5 px-sm-15" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Grabar</button>
                              </div>
                            </div>
                        </div>
                        <div class="modal fade" id="confirmModal" tabindex="-1">
                          <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content border">
                              <form id="addEventForm" autocomplete="off">
                                <div class="modal-header border-200 p-4">
                                  <h5 class="modal-title text-1000 fs-4 lh-sm">Confirmar</h5>
                                  <button class="btn p-1 text-900" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs--1"></span></button>
                                </div>
                                <div class="modal-body pt-4 pb-2 px-4">
                                  <div class="mb-3">
                                    <label class="fw-bold mb-2 text-1000" for="leadStatus">Esta seguro que desea confirmar la operacion?</label>
                                  </div>
                                </div>
                              </form>
                              <div class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
                                  <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="button" data-bs-dismiss="modal" >Cancel</button>
                                  <button class="btn btn-sm btn-primary px-9 fs--2 my-0" onclick="mostrarAlert();" type="submit" data-bs-dismiss="modal" >Confirmar</button>
                              </div>
                            </div>
                          </div>
                      </div>
                      </form>
                   </div>
                 </div>
              </div>

              <div class="mt-4" id="orderTable" data-list='{"valueNames":["order","total","customer","payment_status","fulfilment_status","delivery_type","date"],"page":10,"pagination":true}'>
                  <div class="mb-4">
                    <div class="row g-3">
                      <div class="col-auto">
                        <div class="search-box">
                          <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                            <input class="form-control search-input search" type="search" placeholder="Search detalle" aria-label="Search" />
                            <span class="fas fa-search search-box-icon"></span>
                          </form>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1">
                    <div class="table-responsive scrollbar mx-n1 px-1">
                      <table class="table table-sm fs--1 mb-0">
                        <thead>
                          <tr>
                            <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                              <div class="form-check mb-0 fs-0">
                                <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                              </div>
                            </th>
                            <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="order" style="width:5%;">KEY</th>
                            <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">DESCRIPCION</th>
                            <th class="sort align-middle text-center ps-5" scope="col" ></th>
                          </tr>
                        </thead>
                        <tbody class="list" id="order-table-body">
                            <c:forEach var="LstTTablad" items="${requestScope.LstTTablad}">
                              <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                <td class="fs--1 align-middle px-0 py-3">
                                  <div class="form-check mb-0 fs-0">
                                    <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}' />
                                  </div>
                                </td>
                                <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" >#${LstTTablad.iexkey}</a></td>
                                <td class="total align-middle text-start fw-semi-bold text-1000 ps-5">${LstTTablad.desdet}</td>

                                <td class="align-middle text-end white-space-nowrap pe-0 action">
                                   <div class="font-sans-serif btn-reveal-trigger position-static">
                                     <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                     <div class="dropdown-menu dropdown-menu-end py-2">
                                          <a class="dropdown-item" href="curDetalleTblGen@${LstTTablad.iexcodtab}@${LstTTablad.iexkey}">Cur Detalle</a>
                                          <div class="dropdown-divider"></div>
                                          <a class="dropdown-item text-danger" onclick="return remove();" href="deletecurDetalleTblGen@${LstTTablad.iexcodtab}@${LstTTablad.iexkey}">Eliminar</a>
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

          <jsp:include page="../../../demoWidget.jsp"></jsp:include>
    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->

    <jsp:include page="../../../customize.jsp"></jsp:include>
  </body>
</html>