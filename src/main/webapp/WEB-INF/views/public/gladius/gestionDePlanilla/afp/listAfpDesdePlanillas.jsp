<%-- Created on : 15/06/2023, 12:20:00 PM
Author : Jean Quiroz Email : jeanp.quiroz@gmail.com --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
    <head>
        <jsp:include page="../../../links.jsp"></jsp:include>
    </head>
    <script>
        function mostrarAlert() {
            var div = document.getElementById('alert');
            div.style.display = '';

            setTimeout(function () {
                $("#alerts").hide(6000);
            }, 3000);
        }

        function remove() {
        	var opcion = confirm("Esta seguro de Eliminar el Registro?");
        	if (opcion == true) {
        		return true;
        	} else {
        		return false;
        	}
        }

        function mostrarAlertModalCopiarAfp(){
            var div = document.getElementById('alertModalSuccessCopy');
            div.style.display = '';

            setTimeout(function () {
                $("#alerts").hide(6000);
            }, 3000);
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
                        <li class="breadcrumb-item"><a href="#!">Gestión de planillas</a></li>
                        <li class="breadcrumb-item active">Afp</li>
                    </ol>
                </nav>
                <div class="mb-2">
                    <div class="row g-3 mb-1">
                        <div class="col-auto">
                            <h2 id="h2top" class="mb-0">Afp planillas</h2>
                        </div>
                    </div>
                    <div class="row g-5 mb-2">
                        <div class="col-xl-12">
                            <div class="row gx-3 gy-4">
                                <form class="row g-4 mb-0 needs-validation" method="POST" action="buscarAfps" novalidate>
                                    <div>
                                        <div class="col-sm-6 col-md-2">
                                            <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Periodo Mensual</label>
                                            <input class="form-control" id="idperiododisabled" name="idperiododisabled" maxlength="6" type="text" placeholder="yyyymm" value="${periodo}" required disabled />
                                            <input class="form-control" id="idperiodo" name="idperiodo" maxlength="6" type="hidden" placeholder="yyyymm" value="${periodo}" />
                                        </div>
                                        <div class="mt-2 col-auto">
                                            <!--<button class="btn btn-primary btn-sm mt-1" type="submit"><span class="fa-solid fa-magnifying-glass me-2"></span>Buscar</button>-->
                                            <a class="btn btn-phoenix-secondary btn-sm mt-1" href="nuevaAfp"><span class="fas fa-plus me-2"></span>Add Afp</a>
                                            <a class="btn btn-phoenix-danger btn-sm mt-1" href="#" type="button" data-bs-toggle="modal" data-bs-target="#modalCopiarPeriodoAfp" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-paste me-2"></span>Copiar Periodo Afp</a>
                                            <a class="btn btn-phoenix-secondary btn-sm mt-1" href="listarDetallePlanillaGen@${iexcodreg}@${iexcodpro}@${periodo}"><span class="fas fa-reply me-2"></span>Volver a planillas</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>


                    <div id="orderTable" data-list='{"valueNames":["periodo","idafp","desafp"],"page":10,"pagination":true}'>
                        <div class="mb-3">
                            <div class="row g-3">
                                <div class="col-auto">
                                    <div class="search-box">
                                      <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                        <input class="form-control search-input search" type="search" placeholder="Search afps" aria-label="Search"/>
                                        <span class="fas fa-search search-box-icon"></span>
                                      </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="customerOrdersTable" class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1" data-list='{"valueNames":["periodo","idafp","desafp"],"page":10, "pagination":true }'>
                            <div class="table-responsive scrollbar mx-n1 px-1">
                              <table class="table table-sm fs--1 mb-0">
                                <thead>
                                    <tr>
                                        <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                            <div class="form-check mb-0 fs-0">
                                                <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}'/>
                                            </div>
                                        </th>
                                        <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="periodo" style="width:5%;">PERIODOS</th>
                                        <th class="sort white-space-nowrap align-middle text-center ps-5" scope="col" data-sort="idafp">ID AFP</th>
                                        <th class="sort white-space-nowrap align-middle text-center ps-6" scope="col" data-sort="desafp">DES AFP</th>
                                        <th class="sort white-space-nowrap align-middle text-center ps-3" scope="col" >REX MAX ASEG</th>
                                        <th class="sort white-space-nowrap align-middle text-center ps-5" scope="col" >FONDO DE PENSION %</th>
                                        <th class="sort align-middle text-center ps-5" scope="col">COMISION FLUJO</th>
                                        <th class="sort align-middle text-center ps-5" scope="col" >COMISION FLUJO MIXTA</th>
                                        <th class="sort align-middle text-center ps-5" scope="col" >COMISION SALDO MIXTA</th>
                                        <th class="sort align-middle text-center ps-5" scope="col" >PRIMA SEGURO</th>
                                        <th class="sort align-middle text-center ps-5" scope="col" ></th>
                                </thead>
                                <tbody class="list" id="order-table-body">
                                    <c:forEach var="LstAfpPer" items="${requestScope.LstAfpPer}">
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                                <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}'/>
                                            </div>
                                          </td>
                                          <td class="periodo align-middle white-space-nowrap py-0"><span class="badge badge-tag me-2 mb-2">${LstAfpPer.iexpermes}</span></td>
                                          <td class="idafp align-middle text-start fw-semi-bold text-1000 ps-5"><a class="fw-semi-bold" href="#">#${LstAfpPer.iexcodafp}</a></td>
                                          <td class="desafp align-middle text-center fw-semi-bold text-1000">${LstAfpPer.iexdesafp}</td>
                                          <td class="align-middle white-space-nowrap text-900 fs--1 text-center">${LstAfpPer.iexremmax_asegu}</td>
                                          <td class="align-middle white-space-nowrap text-center fw-bold text-700">${LstAfpPer.iexaporte_oblig}</td>
                                          <td class="align-middle white-space-nowrap text-center ps-3fw-bold text-700">${LstAfpPer.iexcomis_sflu}</td>
                                          <td class="align-middle white-space-nowrap text-center fw-bold text-700">${LstAfpPer.iexcomis_sflu_mix}</td>
                                          <td class="align-middle white-space-nowrap text-center fw-bold text-700">${LstAfpPer.iexcomis_anual_mix}</td>
                                          <td class="align-middle white-space-nowrap text-center fw-bold text-700">${LstAfpPer.iexprima_seguro}</td>

                                          <td class="align-middle text-center white-space-nowrap pe-0 action">
                                              <div class="font-sans-serif btn-reveal-trigger position-static">
                                                <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                                data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                                <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                                <div class="dropdown-menu dropdown-menu-end py-2">
                                                  <a id="dropdownmenutable" class="dropdown-item" href="editarAfp@${LstAfpPer.iexcodafp}@${periodo}"><span class="fa-solid fa-pencil me-2"></span>Editar</a>
                                                  <div class="dropdown-divider"></div>
                                                  <a id="dropdownmenutable" class="dropdown-item" onclick="return remove();" href="deleteAfp@${LstAfpPer.iexcodafp}@${periodo}"><span class="fa-solid fa-trash me-2"></span>Eliminar</a></div>
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
        </main>

        <!-- ===============================================-->
        <!--    End of Main Content-->
        <!-- ===============================================-->

        <jsp:include page="../../../demoWidget.jsp"></jsp:include>
        <jsp:include page="../../../customize.jsp"></jsp:include>
    </body>

    <div id="modalCopiarPeriodoAfp" class="modal fade" tabindex="-1" aria-labelledby="scrollingLongModalLabel2" aria-hidden="true" >
      <div class="modal-dialog modal-sm modal-dialog-scrollable">
          <div class="modal-content bg-100">
            <form class="needs-validation" method="POST" action="copiarPeriodoAfp" novalidate >
                <div class="modal-header border-200 bg-soft p-4">
                   <h5 class="modal-title text-1000 fs-2 lh-sm">Copiar datos periodo Afp</h5>
                   <button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>
                </div>
                <div class="modal-body p-4">
                  <div id="alertModalSuccessCopy" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                      <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                      <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                      <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                  </div>

                  <div class="row mt-3">
                      <div class="col-sm-6 col-md-12">
                            <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Copiar de periodo</label>
                            <input class="form-control" name="idPeriodoCopy1" id="idPeriodoCopy1" type="text" maxlength="6" placeholder="yyyymm" required/>
                      </div>
                      <div class="col-sm-6 col-md-12 mt-2">
                            <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Pegar en periodo</label>
                            <input class="form-control" name="idPeriodoCopy2" id="idPeriodoCopy2" type="text" maxlength="6" placeholder="yyyymm" required />
                      </div>
                  </div>
                </div>
                <div class="modal-footer d-flex justify-content-end align-items-center px-0 pb-0 border-200 pt-0">
                    <a class="btn btn-sm btn-phoenix-primary px-3 my-0" data-bs-dismiss="modal" aria-label="Close">Cerrar</a>
                    <!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" type="submit"><div class="spinner-border spinner-border-sm" style="height:13px; width:13px;" role="status"></div><span class="ms-2">Guardar Periodo</span></button>-->
                    <button class="btn btn-sm btn-primary px-9 my-0 mt-0 ps-4 pe-4" onclick="mostrarAlertModalCopiarAfp();" type="submit"><span class="ms-2">Copiar contenido Afp</span></button>
                </div>
            </form>
          </div>
      </div>
    </div>
</html>
