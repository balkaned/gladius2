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
                <li class="breadcrumb-item"><a href="#!">Configuración</a></li>
                <li class="breadcrumb-item active">Secciones</li>
              </ol>
            </nav>
            <div class="mb-9">
              <div class="row g-3 mb-4">
                <div class="col-auto">
                  <h2 id="h2top" class="mb-0">Secciones</h2>
                </div>
              </div>

              <div id="orderTable" data-list='{"valueNames":["order","total","customer","payment_status","fulfilment_status","delivery_type","date"],"page":10,"pagination":true}'>
                <div class="mb-4">
                  <div class="row g-3">
                    <div class="col-auto">
                      <div class="search-box">
                        <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                          <input class="form-control search-input search" type="search" placeholder="Search secciones" aria-label="Search" />
                          <span class="fas fa-search search-box-icon"></span>
                        </form>
                      </div>
                    </div>
                    <div class="col-auto scrollbar overflow-hidden-y flex-grow-1">
                      <div class="btn-group position-static" role="group">
                        <div class="btn-group position-static text-nowrap" role="group">
                          <button class="btn btn-phoenix-secondary px-7 flex-shrink-0" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                            Payment status<span class="fas fa-angle-down ms-2"></span></button>
                          <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                            <li>
                              <hr class="dropdown-divider" />
                            </li>
                            <li><a class="dropdown-item" href="#">Separated link</a></li>
                          </ul>
                        </div>
                        <div class="btn-group position-static text-nowrap" role="group">
                          <button class="btn btn-sm btn-phoenix-secondary px-7 flex-shrink-0" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                            Fulfilment status<span class="fas fa-angle-down ms-2"></span></button>
                          <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                            <li>
                              <hr class="dropdown-divider" />
                            </li>
                            <li><a class="dropdown-item" href="#">Separated link</a></li>
                          </ul>
                        </div>
                        <button class="btn btn-sm btn-phoenix-secondary px-7 flex-shrink-0">More filters </button>
                      </div>
                    </div>
                    <div class="col-auto">
                      <button class="btn btn-link text-900 me-4 px-0"><span class="fa-solid fa-file-export fs--1 me-2"></span>Export</button>
                      <a class="btn btn-primary" href="nuevaSeccion" ><span class="fas fa-plus me-2"></span>Add Seccion</a>
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
                          <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="order" style="width:5%;">ID</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">SECCION</th>
                          <th class="sort align-middle text-center ps-3" scope="col" data-sort="date">ORD</th>
                          <th class="sort align-middle text-center ps-4" scope="col" data-sort="date">SISTEMAS</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">URL</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">ACCION DEF</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">OBSERVACIONES</th>
                          <th class="sort align-middle text-center ps-5" scope="col" ></th>
                        </tr>
                      </thead>
                      <tbody class="list" id="order-table-body">
                          <c:forEach var="LstSeccion" items="${requestScope.LstSeccion}">
                            <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                              <td class="fs--1 align-middle px-0 py-3">
                                <div class="form-check mb-0 fs-0">
                                  <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}' />
                                </div>
                              </td>
                              <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="editarSeccion@${LstSeccion.iexcodsec}">#${LstSeccion.iexcodsec}</a></td>
                              <td class="total align-middle text-start fw-semi-bold text-1000 ps-5 pe-5">${LstSeccion.iexdessec}</td>
                              <td class="total align-middle text-center fw-semi-bold text-1000">${LstSeccion.iexordsec}</td>
                              <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-center"><span class="badge badge-phoenix fs--1 badge-phoenix-warning"><span class="badge-label">${LstSeccion.dessys}</span></td>
                              <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700">${LstSeccion.iexsecurl}</td>
                              <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700 ps-5">${LstSeccion.iexactiondef}</td>
                              <td class="fulfilment_status align-middle white-space-nowrap text-start text-1000 ps-5">${LstSeccion.iexsecobs}</td>
                              <td class="align-middle text-end white-space-nowrap pe-0 action">
                                 <div class="font-sans-serif btn-reveal-trigger position-static">
                                  <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                  data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                  <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2">
                                      <a id="dropdownmenutable" class="dropdown-item" href="editarSeccion@${LstSeccion.iexcodsec}"><span class="fa-solid fa-pencil me-2"></span>Editar</a>
                                      <div class="dropdown-divider"></div>
                                      <a id="dropdownmenutable" class="dropdown-item" onclick="return remove();" href="deleteSeccion@${LstSeccion.iexcodsec}"><span class="fa-solid fa-trash me-2"></span>Eliminar</a>
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