<%--
  Created by IntelliJ IDEA.
  User: Javier
  Date: 23/10/2023
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
<head>
  <jsp:include page="../../../links.jsp"></jsp:include>
</head>
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
        <li class="breadcrumb-item"><a href="#!">Conf. Planillas</a></li>
        <li class="breadcrumb-item active">Procesos y Forms</li>
      </ol>
    </nav>
    <div class="mb-9">
      <div class="row g-3 mb-4">
        <div class="col-auto">
          <h2 id="h2top" class="mb-0">Formulas</h2>
        </div>
      </div>
    </div>
    <div id="orderTable" data-list='{"valueNames":["order","total","customer","payment_status","fulfilment_status","delivery_type","date"],"page":10,"pagination":true}'>
      <div class="mb-4">
        <div class="row g-3">
          <div class="col-auto">
            <div class="search-box">
              <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                <input class="form-control search-input search" type="search" placeholder="Search conceptos" aria-label="Search"/>
                <span class="fas fa-search search-box-icon"></span>
              </form>
            </div>
          </div>
          <div class="col-auto scrollbar overflow-hidden-y flex-grow-1">
            <div class="btn-group position-static" role="group">
              <div class="btn-group position-static text-nowrap" role="group">
                <button class="btn btn-phoenix-secondary px-7 flex-shrink-0" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true"
                        aria-expanded="false" data-bs-reference="parent">
                  Payment status<span class="fas fa-angle-down ms-2"></span></button>
                <ul class="dropdown-menu dropdown-menu-end">
                  <li><a class="dropdown-item" href="#">Action</a></li>
                  <li><a class="dropdown-item" href="#">Another action</a></li>
                  <li><a class="dropdown-item" href="#">Something else here</a></li>
                  <li>
                    <hr class="dropdown-divider"/>
                  </li>
                  <li><a class="dropdown-item" href="#">Separated link</a></li>
                </ul>
              </div>
              <div class="btn-group position-static text-nowrap" role="group">
                <button class="btn btn-sm btn-phoenix-secondary px-7 flex-shrink-0" type="button" data-bs-toggle="dropdown" data-boundary="window"
                        aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                  Fulfilment status<span class="fas fa-angle-down ms-2"></span></button>
                <ul class="dropdown-menu dropdown-menu-end">
                  <li><a class="dropdown-item" href="#">Action</a></li>
                  <li><a class="dropdown-item" href="#">Another action</a></li>
                  <li><a class="dropdown-item" href="#">Something else here</a></li>
                  <li>
                    <hr class="dropdown-divider"/>
                  </li>
                  <li><a class="dropdown-item" href="#">Separated link</a></li>
                </ul>
              </div>
              <button class="btn btn-sm btn-phoenix-secondary px-7 flex-shrink-0">More filters</button>
            </div>
          </div>
          <div class="col-auto">
            <button class="btn btn-link text-900 me-4 px-0"><span class="fa-solid fa-file-export fs--1 me-2"></span>Export</button>
            <a class="btn btn-primary" href="#"><span class="fas fa-plus me-2"></span>Add new Formula</a>
          </div>
        </div>
      </div>
      <div class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1">
        <div class="table-responsive scrollbar mx-n1 px-1">
          <table class="table table-sm fs--1 mb-0">
            <thead>
            <tr>
              <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="order" style="width:5%;">Orden de Ejecucion</th>
              <th class="sort align-middle text-center pe-0" scope="col" data-sort="date">ID Operacion</th>
              <th class="sort align-middle text-center pe-0" scope="col" data-sort="date">Codigo Concepto</th>
              <th class="sort align-middle text-center pe-0" scope="col" data-sort="date">Concepto</th>
              <th class="sort align-middle text-center pe-0" scope="col" data-sort="date">Glosa</th>
              <th class="sort align-middle text-center pe-0" scope="col" data-sort="date">Estado</th>
              <th class="sort align-middle text-center pe-0" scope="col" data-sort="date">Accion</th>
            </tr>
            </thead>
            <tbody class="list" id="order-table-body">
            <c:forEach var="formxcon" items="${requestScope.formulaXConceptoList}">
              <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#${formxcon.formfororden}</a></td>
                <td class="total align-middle text-center fw-semi-bold text-1000">${formxcon.formforcodfor}</td>
                <td class="total align-middle text-center fw-semi-bold text-1000">${formxcon.formforcodcon}</td>
                <td class="total align-middle text-center fw-semi-bold text-1000">${formxcon.conccoodescon}</td>
                <td class="total align-middle text-center fw-semi-bold text-1000">${formxcon.formproglosa}</td>
                <td class="total align-middle text-center fw-semi-bold text-1000">${formxcon.formforflgest}</td>
                <td class="align-middle text-center white-space-nowrap pe-0 action">
                  <div class="font-sans-serif btn-reveal-trigger position-static">
                    <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button" data-bs-toggle="dropdown" data-boundary="window"
                            aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                    <div class="dropdown-menu dropdown-menu-end py-2">
                      <a class="dropdown-item text-black" href="#!">Formular</a>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item text-black" href="#!">Compilar</a>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item text-danger" href="#!">Eliminar</a>
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
            <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!" data-list-view="*">View all<span
              class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none" href="#!" data-list-view="less">View Less<span
              class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
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

  <jsp:include page="../../../demoWidget.jsp"></jsp:include>
</main>
<!-- ===============================================-->
<!--    End of Main Content-->
<!-- ===============================================-->

<jsp:include page="../../../customize.jsp"></jsp:include>
</body>
</html>
