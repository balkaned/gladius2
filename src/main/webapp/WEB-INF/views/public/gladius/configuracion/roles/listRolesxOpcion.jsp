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
                <li class="breadcrumb-item active">Roles</li>
                <li class="breadcrumb-item active">Roles x opción</li>
              </ol>
            </nav>
            <div class="mb-9">
              <div class="row g-3 mb-4">
                <div class="col-auto">
                  <h2 id="h2top" class="mb-0">Roles x opcion</h2>
                </div>
              </div>

              <div id="orderTable" data-list='{"valueNames":["order","total","customer","payment_status","fulfilment_status","delivery_type","date"],"page":10,"pagination":true}'>
                <div class="mb-4">
                  <div class="row g-3">
                    <div class="col-auto">
                      <div class="search-box">
                        <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                          <input class="form-control search-input search" type="search" placeholder="Search roles" aria-label="Search" />
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
                    <div class="col-12">
                      <a class="btn btn-sm btn-phoenix-secondary px-5" href="listRoles"><span class="fa-solid fa-reply me-2"></span>Atras</a>
                      <a class="btn btn-sm btn-primary" href="nuevoRolxOpcion@${idRol}" ><span class="fas fa-plus me-2"></span>Add RolxOpcion</a>
                      <div class="btn-group mb-1 me-1 ms-1 mt-1">
                        <button class="btn btn-sm btn-phoenix-secondary" type="button"><span class="fa-solid fa-hashtag fs--1 me-2"></span></span class="ps-5">Exportar</span></button>
                        <button class="btn btn-sm dropdown-toggle dropdown-toggle-split btn-phoenix-secondary" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="sr-only"></span></button>
                        <div class="dropdown-menu">
                      	  <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="#">
                      		<span class="fa-solid fa-download fs--1 me-2"></span>Exportar Excel Todos
                      	  </a>
                      	  <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Exportar Excel Solo Activos</a>
                      	  <div class="dropdown-divider"></div>
                      	  <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Otros</a>
                        </div>
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
                          <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="order" style="width:5%;">ID</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">SISTEMAS</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">SECCION</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">OPCIONES</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">CONS</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">REG</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">MOD</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">ELI</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">REP</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">EXP</th>
                          <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">EST</th>
                          <th class="sort align-middle text-center ps-5" scope="col" ></th>
                        </tr>
                      </thead>
                      <tbody class="list" id="order-table-body">
                          <c:forEach var="xRolxopc" items="${requestScope.xRolxopc}">
                            <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                              <td class="fs--1 align-middle px-0 py-3">
                                <div class="form-check mb-0 fs-0">
                                  <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}' />
                                </div>
                              </td>
                              <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="editarOpcionxRol@${xRolxopc.iexcodopc}@${idRol}">#${xRolxopc.iexcodopc}</a></td>
                              <td class="total align-middle text-start fw-semi-bold text-1000 ps-5"><span class="badge badge-phoenix fs--1 badge-phoenix-info"><span class="badge-label">${xRolxopc.dessys}</span></td>
                              <td class="total align-middle white-space-nowrap text-start fw-semi-bold text-1000 ps-5">${xRolxopc.dessec}</td>
                              <td class="total align-middle white-space-nowrap text-start fw-semi-bold text-1000 ps-5">${xRolxopc.desopc}</td>
                              <td class="total align-middle text-start fw-semi-bold text-1000 ps-5">${xRolxopc.iex_consultar}</td>
                              <td class="total align-middle text-start fw-semi-bold text-1000 ps-5">${xRolxopc.iex_registrar}</td>
                              <td class="total align-middle text-start fw-semi-bold text-1000 ps-5">${xRolxopc.iex_modificar}</td>
                              <td class="total align-middle text-start fw-semi-bold text-1000 ps-5">${xRolxopc.iex_eliminar}</td>
                              <td class="total align-middle text-start fw-semi-bold text-1000 ps-5">${xRolxopc.iex_descargar_xls}</td>
                              <td class="total align-middle text-start fw-semi-bold text-1000 ps-5">${xRolxopc.iex_descargar_pdf}</td>
                                <c:if test="${xRolxopc.iexflgest=='1'}"><td class="payment_status align-middle white-space-nowrap text-center fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Activo</span><span class="ms-1" style="height:12.8px;width:12.8px;"></span></span></td></c:if>
                                <c:if test="${xRolxopc.iexflgest==null}"><td class="payment_status align-middle white-space-nowrap text-center fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span class="badge-label">Inactivo</span><span class="ms-1" style="height:12.8px;width:12.8px;"></span></span></td></c:if>
                                <c:if test="${xRolxopc.iexflgest=='0'}"><td class="payment_status align-middle white-space-nowrap text-center fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span class="badge-label">Inactivo</span><span class="ms-1" style="height:12.8px;width:12.8px;"></span></span></td></c:if>

                              <td class="align-middle text-end white-space-nowrap pe-0 action">
                                 <div class="font-sans-serif btn-reveal-trigger position-static">
                                   <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                   data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                   <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                   <div class="dropdown-menu dropdown-menu-end py-2">
                                        <a id="dropdownmenutable"class="dropdown-item" href="editarOpcionxRol@${xRolxopc.iexcodopc}@${idRol}"><span class="fa-solid fa-pencil me-2"></span>Editar</a>
                                        <div class="dropdown-divider"></div>
                                        <a id="dropdownmenutable" class="dropdown-item" onclick="return remove();" href="deleteOpcionxRol@${xRolxopc.iexcodopc}@${idRol}"><span class="fa-solid fa-trash me-2"></span>Eliminar</a>
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