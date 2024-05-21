<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jan Quiroz
    Email	   : janquirozs@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
    <head>
      <jsp:include page="../../../links.jsp"></jsp:include>
      <script src="resources/assets/js/detallePlanillaGeneral.js"></script>
      <script src="resources/assets/js/boletasPlanillas.js"></script>
      <script src="resources/assets/js/asistenciasPlanillas.js"></script>
      <script src="resources/assets/js/otrosDatosPlanilla.js"></script>
    </head>

    <style>
        .popover {
           /*width: 470px !important;*/
           max-width:325px !important;
           height: 420px !important;
           overflow-y: auto;
           border-radius:12px 0px 0px 12px;
           /*overflow-x: none;*/
        }

        .popover-body{
            padding:0px;
        }
    </style>

    <body>
        <!-- ===============================================-->
        <!--    Main Content-->
        <!-- ===============================================-->
        <main class="main" id="top">
          <jsp:include page="../../../navsMenu.jsp"></jsp:include>
          <jsp:include page="../../../navTop.jsp"></jsp:include>
          <jsp:include page="../../../modalFade.jsp"></jsp:include>

          <div class="content">
            <nav class="mb-2 mt-2" aria-label="breadcrumb">
              <ol class="breadcrumb mb-0">
                <li class="breadcrumb-item"><a href="#!">Gestión de planillas</a></li>
                <li class="breadcrumb-item active">Planilla General</li>
              </ol>
            </nav>
            <div class="mb-1">
              <div class="g-3 mb-2">
                <div class="col-12">
                  <h2 id="h2top" class="mb-2">Gestión de planillas </h2>

                  <div class="row col-12">
                      <div class="col-12">
                        <span class="badge badge-tag me-2 mb-2">Regimen: ${requestScope.xproplaper.desregimen}</span>
                      </div>
                  </div>
                  <p class="col-8 mb-0 mt-0 fs--1">Proceso: ${requestScope.xproplaper.desproceso} ${requestScope.xproplaper.iexnroper} [${requestScope.xproplaper.timerfecini} - ${requestScope.xproplaper.timerfecfin}] &nbspGrupoPlanilla: ${requestScope.xproplaper.desgrppla}<span class="badge badge-phoenix fs--2 badge-phoenix-primary ms-2"><span class="badge-label">${requestScope.xproplaper.desestado}</span></p>
                </div>
              </div>
            </div>

            <div id="orderTable" data-list='{"valueNames":["id","trab","itp","est","fecini"],"page":5,"pagination":true}'>
              <div class="mb-3">
                <div class="row g-3">
                  <div class="col-auto">
                    <div class="search-box">
                      <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                        <input class="form-control search-input search" type="search" placeholder="Search trabajadores" aria-label="Search"/>
                        <span class="fas fa-search search-box-icon"></span>
                      </form>
                    </div>
                  </div>

                  <c:if test="${requestScope.xproplaper.flgestado!='3'}" >
                      <div class="col-auto">
                         <a class="btn btn-phoenix-secondary btn-sm mt-1" href="buscarPlanillaGen"><span class="fas fa-reply me-2"></span>Atras</a>
                        <a class="btn btn-phoenix-primary btn-sm mt-1" onclick="return enviaForm('2')" href="#"><span class="fas fa-play me-2"></span>1. Iniciar</a>
                        <a class="btn btn-phoenix-secondary btn-sm mt-1" href="verDetalleVariable@${iexcodreg}@${xproplaper.iexcodpro}@${iexperiodo}"><span class="fas fa-code-compare me-2"></span>Variables</a>
                        <!--<a class="btn btn-phoenix-secondary btn-sm mt-1" href="#"><span class="fas fa-arrows-turn-to-dots me-2"></span>2. Turnos</a>
                        <a class="btn btn-phoenix-secondary btn-sm mt-1" onclick="return enviaForm('34')" href="#"><span class="fas fa-database me-2"></span>4. Consolida</a>-->
                        <a class="btn btn-phoenix-secondary btn-sm mt-1" onclick="return enviaForm('3')" href="#"><span class="fas fa-wrench me-2"></span>5. Procesar</a>
                        <a class="btn btn-phoenix-secondary btn-sm mt-1" href="verDetalleBancos@${iexcodreg}@${xproplaper.iexcodpro}@${iexperiodo}"><span class="fas fa-vault me-2"></span>5. Bancos</a>
                        <a class="btn btn-phoenix-danger btn-sm mt-1" onclick="return enviaForm('6')" href="#"><span class="fas fa-trash me-2"></span>0. Borrar todo</a>
                        <a class="btn btn-primary btn-sm mt-1" onclick="enviaForm('35')" href="#"><span class="fas fa-magnifying-glass me-2"></span>Buscar todo</a>
                        <!--<a class="btn  btn-sm btn-danger mt-1" tabindex="0" role="button" data-bs-toggle="popover" data-bs-trigger="focus" title="Dismissible popover" data-bs-content="And here's some amazing content. It's very engaging. Right?">Dismissible popover</a>-->
                      </div>
                  </c:if>
                </div>
              </div>

              <form name="frmplaserv" id="frmplaserv" action="procesarPlanilla" method="POST" >
                  <input type="hidden" name="iexcodreg" id="iexcodreg" value="${iexcodreg}" />
                  <input type="hidden" name="accion" id="accion" value="" />
                  <input type="hidden" name="grppla" id="grppla" value="${xproplaper.desgrppla}" />
                  <input type="hidden" name="tipfile" id="tipfile" value="" />
                  <input type="hidden" name="iexcodpro" id="iexcodpro" value="${iexcodpro}" />
                  <input type="hidden" name="iexperiodo" id="iexperiodo" value="${iexperiodo}" />
                  <input type="hidden" name="iexcodtra" id="iexcodtra" value="-1" />
                  <input type="hidden" name="iexcorrel" id="iexcorrel" value="1" />
                  <input type="hidden" name="codcia" id="codcia" value="${idCom}" />

                  <c:if test="${requestScope.xproplaper.desgrppla=='PLA' || requestScope.xproplaper.desgrppla=='ADE'}">
                      <div id="customerOrdersTable" class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1" data-list='{"valueNames":["id","trab","itp","est","fecini"],"page":5, "pagination":true }' >
                        <div class="table-responsive scrollbar mx-n1 px-1">
                          <table class="table table-sm fs--1 mb-0">
                            <thead>
                                <tr>
                                  <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                    <div class="form-check mb-0 fs-0">
                                      <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                    </div>
                                  </th>
                                  <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="id" style="width:5%;">ID</th>
                                  <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="trab">TRABAJADOR</th>
                                  <th class="sort align-middle text-center pe-2 ps-2 white-space-nowrap" scope="col" data-sort="itp">I/T/P</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="est">ESTADO</th>
                                  <th class="sort align-middle text-center ps-4 pe-4" scope="col" data-sort="fecini">FECINI</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >DTEO</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >DTOT</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >VAC</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >DME</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >SUB</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >LIC</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >FAL</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >D.E</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >DOM</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >DPAG</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" ></th>
                                </tr>
                            </thead>
                            <tbody class="list" id="customer-order-table-body">
                                <c:forEach var="LstPlanillaRes" items="${requestScope.LstPlanillaRes}">
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static" >
                                    <td class="fs--1 align-middle px-0 py-3">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" />
                                      </div>
                                    </td>
                                    <td class="id align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#">#${LstPlanillaRes.iexcodtra}</a></td>
                                    <td class="trab align-middle text-start fw-semi-bold ps-3 white-space-nowrap pe-3 text-1000">
                                        ${LstPlanillaRes.destra}
                                        <div class="btn-group font-sans-serif btn-reveal-trigger position-static ms-2">
                                            <button class="btn btn-phoenix-secondary pt-1 pb-1 ps-0 pe-2 btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-caret-down ms-2"></span></button>
                                            <div class="dropdown-menu dropdown-menu-end py-2">
                                              <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="detalleEmpl@${LstPlanillaRes.iexcodtra}"><span class="fa-solid fa-person me-2"></span>Detalle empleado</a>
                                              <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="sueldoFijo@${LstPlanillaRes.iexcodtra}"><span class="fa-solid fa-money-bill-1-wave fs--1 me-2"></span>Sueldo fijo</a>
                                              <div class="dropdown-divider"></div>
                                              <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="sueldoVariable@${LstPlanillaRes.iexcodtra}"><span class="fa-solid fa-money-bill-trend-up me-2"></span>Sueldo variable</a>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="itp align-middle text-start fw-semi-bold ps-3 pe-0 text-1000">
                                        <a href="#" onclick="enviaForm_ind('2', '${LstPlanillaRes.iexcodtra}')" >I</a>
                                        <a href="#" onclick="enviaForm_ind('34', '${LstPlanillaRes.iexcodtra}')" >T</a>
                                        <a href="#" onclick="enviaForm_ind('3', '${LstPlanillaRes.iexcodtra}')" >P</a>
                                    </td>
                                    <td class="est align-middle text-center fw-semi-bold text-1000 ps-0 pe-0 white-space-nowrap">Proc</td>
                                    <td class="fecini align-middle text-start fs-9"><span class="fa-regular fa-calendar me-2"></span>${LstPlanillaRes.feciniFormat}</td>
                                    <td class="align-middle text-start fw-semi-bold text-600"><a onclick="verAsistenciaPeriodoTrabInicializacion('${LstPlanillaRes.iexcodtra}','${LstPlanillaRes.destra}','${LstPlanillaRes.feciniFormat}','${LstPlanillaRes.fecfinFormat}','${iexcodpro}','${iexperiodo}');" href="#" data-bs-toggle="modal" data-bs-target="#modalAsistencias" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-window-restore ms-2"></span> ${LstPlanillaRes.iexdiasteorico}</a></td>
                                    <td class="align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiamestot}</td>
                                    <td class="align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiavaca}</td>
                                    <td class="abr al align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiadm}</td>
                                    <td class="align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiasub}</td>
                                    <td class="align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdialic}</td>
                                    <td class="align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiafalta}</td>
                                    <td class="align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiaefectivo}</td>
                                    <td class="align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdominical}</td>
                                    <td class="align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiaspago}</td>

                                    <td class="align-middle text-center white-space-nowrap pe-0 action">
                                      <div class="font-sans-serif btn-reveal-trigger position-static">
                                        <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                        data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                        <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                        <div class="dropdown-menu dropdown-menu-end py-2">
                                          <a id="dropdownmenutable" class="dropdown-item" onclick="generarBoleta('${iexcodpro}','${LstPlanillaRes.iexcodtra}','${iexperiodo}','1','${requestScope.xproplaper.desgrppla}','${iexcodreg}');" href="#" type="button" data-bs-toggle="modal" data-bs-target="#modalGenerarBoleta" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" ><span class="fa-solid fa-window-restore me-2"></span>Boleta</a>
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
                  </c:if>

                  <c:if test="${requestScope.xproplaper.desgrppla=='PRV' || requestScope.xproplaper.desgrppla=='CTS' || requestScope.xproplaper.desgrppla=='GRA' }">
                    <div id="customerOrdersTable" class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1" data-list='{"valueNames":["id","trab","itp","est","fecini"],"page":5, "pagination":true }'>
                        <div class="table-responsive scrollbar mx-n1 px-1">
                          <table class="table table-sm fs--1 mb-0">
                            <thead>
                                <tr>
                                  <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                    <div class="form-check mb-0 fs-0">
                                      <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                    </div>
                                  </th>
                                  <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="id" style="width:5%;">ID</th>
                                  <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="trab">TRABAJADOR</th>
                                  <th class="sort align-middle text-center pe-2 ps-2 white-space-nowrap" scope="col" data-sort="itp">FECINI</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >FECFIN</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >AÑOS</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >MESES</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >DIAS</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" ></th>
                                </tr>
                            </thead>
                            <tbody class="list" id="customer-order-table-body">
                                <c:forEach var="LstPlanillaRes" items="${requestScope.LstPlanillaRes}">
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static" >
                                    <td class="fs--1 align-middle px-0 py-3">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" />
                                      </div>
                                    </td>
                                    <td class="id align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="editarConcepto@${concepto.codConcepto}">#${LstPlanillaRes.iexcodtra}</a></td>
                                    <td class="trab align-middle text-start fw-semi-bold ps-3 white-space-nowrap pe-3 text-1000">${LstPlanillaRes.destra}</td>
                                    <td class="est align-middle text-center fw-semi-bold text-600 ps-0 pe-0"><a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstPlanillaRes.iexfecini}</td>
                                    <td class="fecini align-middle text-start fw-semi-bold text-600"><a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstPlanillaRes.iexfecfin}</td>
                                    <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexanio_benef}</td>
                                    <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexmes_benef}</td>
                                    <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdia_benef}</td>

                                    <td class="align-middle text-center white-space-nowrap pe-0 action">
                                      <div class="font-sans-serif btn-reveal-trigger position-static">
                                        <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                        data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                        <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                        <div class="dropdown-menu dropdown-menu-end py-2">
                                          <a id="dropdownmenutable" class="dropdown-item" href="#" onclick="consulBol('${LstPlanillaRes.iexcodtra}')" ><span class="fa-solid fa-window-restore me-2"></span>Boleta</a>
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
                  </c:if>

                  <c:if test="${requestScope.xproplaper.desgrppla=='UTI' }">
                      <div id="customerOrdersTable" class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1" data-list='{"valueNames":["id","trab","itp","est","fecini"],"page":5, "pagination":true }'>
                          <div class="table-responsive scrollbar mx-n1 px-1">
                            <table class="table table-sm fs--1 mb-0">
                              <thead>
                                  <tr>
                                    <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                      </div>
                                    </th>
                                    <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="id" style="width:5%;">ID</th>
                                    <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="trab">TRABAJADOR</th>
                                    <th class="sort align-middle text-center pe-2 ps-2 white-space-nowrap" scope="col" data-sort="itp">DIAS TEO</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS MES</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS VAC</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS DM</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS SUB</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS LIC</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS FALTA</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS EFEC</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" ></th>
                                  </tr>
                              </thead>
                              <tbody class="list" id="customer-order-table-body">
                                  <c:forEach var="LstPlanillaRes" items="${requestScope.LstPlanillaRes}">
                                    <tr class="hover-actions-trigger btn-reveal-trigger position-static" >
                                      <td class="fs--1 align-middle px-0 py-3">
                                        <div class="form-check mb-0 fs-0">
                                          <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" />
                                        </div>
                                      </td>
                                      <td class="id align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="editarConcepto@${concepto.codConcepto}">#${LstPlanillaRes.iexcodtra}</a></td>
                                      <td class="trab align-middle text-start fw-semi-bold ps-3 white-space-nowrap pe-3 text-1000">${LstPlanillaRes.destra}</td>
                                      <td class="est align-middle text-center fw-semi-bold text-600 ps-0 pe-0"><a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstPlanillaRes.iexfecini}</td>
                                      <td class="fecini align-middle text-start fw-semi-bold text-600"><a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstPlanillaRes.iexfecfin}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiasteorico}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiamestot}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiavaca}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiadm}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiasub}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdialic}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiafalta}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiaefectivo}</td>

                                      <td class="align-middle text-center white-space-nowrap pe-0 action">
                                        <div class="font-sans-serif btn-reveal-trigger position-static">
                                          <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                          data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                          <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                          <div class="dropdown-menu dropdown-menu-end py-2">
                                            <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-window-restore me-2"></span>Boleta</a>
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
                  </c:if>

                  <div class="col-auto mt-4">
                      <a class="btn btn-phoenix-secondary btn-sm" onclick="traerOtrosDatos('${iexcodpro}','${iexperiodo}','1');" href="#" type="button" data-bs-toggle="modal" data-bs-target="#modalOtrosDatos" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" ><span class="fas fa-briefcase me-2"></span>Otros datos</a>
                      <a class="btn btn-phoenix-secondary btn-sm" href="buscarAfpsDesdePlanillas@${iexcodreg}@${iexcodpro}@${iexperiodo}"><span class="fas fa-handshake me-2"></span>Afp</a>

                      <div class="btn-group mb-1 me-1 ms-0 mt-1">
                        <button class="btn btn-sm btn-phoenix-secondary" type="button"><span class="fa-solid fa-hashtag fs--1 me-2"></span></span class="ps-5">Exportar</span></button>
                        <button class="btn btn-sm dropdown-toggle dropdown-toggle-split btn-phoenix-secondary" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="sr-only"></span></button>
                        <div class="dropdown-menu">
                          <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="AWSorFTP_flgsource@verReporteExcel@${idComp}@-1@null@null@ReportDatVarPla@3UP_CODPRO=${iexcodpro}UP_NROPER=${iexperiodo}UP_CORREL=1@null@null@null"><span class="fa-solid fa-download fs--1 me-2"></span>Excel variables</a>
                          <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="AWSorFTP_flgsource@verReporteExcel@${idComp}@-1@null@null@BoletaEmpRes@3UP_CODPRO=${iexcodpro}UP_NROPER=${iexperiodo}UP_CORREL=1@null@null@null"><span class="fa-solid fa-download fs--1 me-2"></span>Excel planilla mensual</a>
                          <div class="dropdown-divider"></div>
                          <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="AWSorFTP_flgsource@verReporteExcel@${idComp}@-1@null@null@BoletaEmpCtl@3UP_CODPRO=${iexcodpro}UP_NROPER=${iexperiodo}UP_CORREL=1@null@null@null"><span class="fa-solid fa-download fs--1 me-2"></span>Excel planilla mensual resumen</a>
                        </div>
                      </div>

                      <a class="btn btn-phoenix-secondary btn-sm" onclick="return traerTodasLasBoletasPDF();" target="_blank" href="AWSorFTP_flgsource@verReportePDF@${idComp}@-1@null@null@BoletaEmp@3UP_CODPRO=${iexcodpro}UP_NROPER=${iexperiodo}UP_CORREL=1@null@null@null"><span class="fas fa-download me-2"></span>6. Boletas PDF</a>
                      <a class="btn btn-phoenix-primary btn-sm" href="migracionPlanilla@${iexcodreg}@${xproplaper.iexcodpro}@${iexperiodo}"><span class="fas fa-diagram-successor me-2"></span>Migrar planilla</a>
                  </div>

                  <table class="w-100 table-stats table-stats mt-4">
                    <tr>
                      <th></th>
                      <th></th>
                      <th></th>
                    </tr>
                    <tr>
                      <td class="py-2 col-4">
                        <div class="d-inline-flex align-items-center">
                          <div class="d-flex bg-success-100 rounded-circle flex-center me-3" style="width:24px; height:24px"><span class="text-success-600 dark__text-success-300" data-feather="play" style="width:16px; height:16px"></span></div>
                          <p class="fw-bold mb-0">Tiempo de Inicializacion</p>
                        </div>
                      </td>
                      <td class="py-2">
                        <p class="ps-6 ps-sm-0 fw-semi-bold mb-0 mb-0 pb-3 pb-sm-0 fs--1">[${requestScope.xproplaper.timerfecini_iniciar}]&nbsp&nbsp&nbsp[${requestScope.xproplaper.timerfecfin_iniciar}]&nbsp&nbsp&nbsp[${requestScope.xproplaper.timerimp_iniciar}]&nbsp<span class="text-primary">segs</span></p>
                      </td>
                    </tr>
                    <tr>
                      <td class="py-2">
                        <div class="d-flex align-items-center">
                          <div class="d-flex bg-info-100 rounded-circle flex-center me-3" style="width:24px; height:24px"><span class="text-info-600 dark__text-info-300" data-feather="clock" style="width:16px; height:16px"></span></div>
                          <p class="fw-bold mb-0">Tiempo de Procesos</p>
                        </div>
                      </td>
                      <td class="py-2">
                        <p class="ps-6 ps-sm-0 fw-semi-bold mb-0 fs--1">[${requestScope.xproplaper.timerfecini_proc}]&nbsp&nbsp&nbsp[${requestScope.xproplaper.timerfecfin_proc}]&nbsp&nbsp&nbsp[${requestScope.xproplaper.timerimp_proc}]&nbsp<span class="text-primary">segs</span></p>
                      </td>
                    </tr>
                  </table>

                  <div class="card shadow-none border border-300 my-5 overflow-docs overflow-hidden" data-component-card="data-component-card" style="min-height: 250px;">
                    <div class="card-header p-4 border-bottom border-300 bg-soft">
                      <div class="row g-3 justify-content-between align-items-end">
                        <div class="col-12 col-md">
                          <h4 class="text-900 mb-0" data-anchor="data-anchor">Reporte embedded</h4>
                          <p class="mb-0 mt-2 text-800">Click en Preview para traer los datos planilla general o click en exportar para generar un archivo excel de salida en formato .xls</p>
                        </div>
                        <div class="col col-md-auto">
                          <nav class="nav nav-underline justify-content-end doc-tab-nav align-items-center" role="tablist">
                            <a  class="btn btn-link px-2 text-900 copy-code-btn" type="button"><span class="fas fa-download me-1"></span>Exportar excel</a>
                            <a class="btn btn-sm btn-phoenix-primary code-btn ms-2" data-bs-toggle="collapse" href="#example-code" role="button" aria-controls="example-code" aria-expanded="false"> <span class="me-2" data-feather="code"></span>View code</a><a onclick="traerDatosReporteEmbeddedResumenPlanilla();" class="btn btn-sm btn-phoenix-primary preview-btn ms-2"><span class="me-2" data-feather="eye"></span>Preview</a>
                          </nav>
                        </div>
                      </div>
                    </div>
                    <div class="card-body p-0">
                      <div class="p-4">
                        <div class="col-12 d-flex mt-2">
                            <div id="idresult" class="col-12 overflow-auto border border-200 rounded-1" style="height:600px;"></div>
                        </div>
                      </div>
                    </div>
                  </div>

                  <!--<div class="col-auto mt-4">
                      <a class="btn btn-primary btn-sm" onclick="traerDatosReporteResumenPlanilla();" href="#"><span class="fas fa-arrow-down me-2"></span>Traer embedded-reporting</a>
                      <a class="btn btn-phoenix-secondary btn-sm" onclick="#" href="#"><span class="fas fa-download me-2"></span>Exportar a excel</a>
                  </div>-->
                  <!--<div id="idresult" style="width:700px; height:600px; overflow: scroll;" ></div>-->
                  <!--<div class="d-flex mt-2 bg-200 border border-200 rounded-1 ">
                    <div id="idresult" class="overflow-auto" style="height:600px;"></div>
                  </div>-->
              </form>
            </div>
          </div>

          <jsp:include page="../../../demoWidget.jsp"></jsp:include>
        </main>
        <!-- ===============================================-->
        <!--    End of Main Content-->
        <!-- ===============================================-->

        <jsp:include page="../../../customize.jsp"></jsp:include>
    </body>

    <div id="modalGenerarBoleta" class="modal fade" tabindex="-1" aria-labelledby="scrollingLongModalLabel2" aria-hidden="true" >
      <div class="modal-dialog modal-lg modal-dialog-scrollable">
          <div class="modal-content bg-100">
                <div class="modal-header border-200 bg-soft p-4">
                   <h5 class="modal-title text-1000 fs-2 lh-sm">Generar boleta empleado</h5>
                   <button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>
                </div>
                <div class="modal-body p-4">
                    <form class="needs-validation" method="POST" action="modificarPeriodoPlan" novalidate >
                      <div id="alertModalSuccessEdit" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                          <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                          <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                          <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                      </div>

                      <div class="row g-3">
                          <div class="col-auto">
                              <a id="botonDescargarRep5ta" class="btn btn-phoenix-secondary btn-sm mt-1" onclick="descargarReporte5ta(${idComp});" target="_blank" href="#"><span class="fas fa-download me-2"></span>Reporte de 5ta</a>
                              <a id="botonDescargarBoletaTrab" class="btn btn-phoenix-secondary btn-sm mt-1" onclick="descargarBoleta(${idComp});" target="_blank"  href="#"><span class="fas fa-download me-2"></span>Boleta</a>
                              <a id="botonEliminarPlanTrab" class="btn btn-phoenix-danger btn-sm mt-1" onclick="eliminarPlanTrab();" href="#" ><span class="fas fa-trash me-2"></span>Eliminar planilla del trabajador</a>
                          </div>
                      </div>
                      <div class="row mt-3 g-3">
                          <div class="col-sm-6 col-md-2">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Id trab</label>
                                <input class="form-control" name="idTrabBol" id="idTrabBol" type="text" required disabled />
                                <input class="form-control" name="idTrabBolHidden" id="idTrabBolHidden" type="hidden" value="" />
                          </div>
                          <div class="col-sm-6 col-md-6">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Trabajador</label>
                                <input class="form-control" name="trabBol" id="trabBol" type="text" required disabled />
                                <!--<input class="form-control" name="idprocesoEdit" id="idprocesoEdit" type="hidden" value="" />-->
                          </div>
                          <div class="col-sm-6 col-md-3">
                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de ingreso</label>
                              <input class="form-control" name="feciniBol" id="feciniBol" type="text" required disabled />
                              <!--<input class="form-control" name="feciniBol" id="feciniBol" type="hidden" value="" />-->
                          </div>
                      </div>

                      <div class="row g-4">
                          <h4 class="mb-0 mt-7">Parámetros</h4>
                          <div id="orderTable" class="mt-2" data-list='{"valueNames":["codcon","descon","valor"],"page":10,"pagination":true}'>
                            <div class="mb-3" class="mt-0">
                                <div class="row g-3">
                                  <div class="col-auto">
                                    <div class="search-box">
                                      <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                        <input class="form-control search-input search" type="search" placeholder="Search conceptos" aria-label="Search"/>
                                        <span class="fas fa-search search-box-icon"></span>
                                      </form>
                                    </div>
                                  </div>
                                  <div id="customerOrdersTable" class="bg-white border border-200 rounded-2 position-relative top-1 overflow-auto" style="height:240px;" data-list='{"valueNames":["codcon","descon","valor"],"page":10, "pagination":true }'>
                                    <div class="table-responsive scrollbar mx-n1 px-1" >
                                      <table class="table table-sm fs--1 mb-0">
                                        <thead>
                                            <tr>
                                              <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                                <div class="form-check mb-0 fs-0">
                                                  <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                                </div>
                                              </th>
                                              <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="codcon" style="width:5%;">CODCON</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="descon">DESCON</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >VALOR</th>
                                            </tr>
                                        </thead>
                                        <tbody class="list" id="customer-order-table-body-param" >
                                        </tbody>
                                      </table>
                                    </div>
                                  </div>
                                </div>
                            </div>
                          </div>
                      </div>

                      <div class="row g-4">
                        <h4 class="mb-0 mt-7">Ingresos</h4>
                        <div id="orderTable" class="mt-2" data-list='{"valueNames":["codcon","descon","valor"],"page":10,"pagination":true}'>
                          <div class="mb-3" class="mt-0">
                              <div class="row g-3">
                                <div class="col-auto">
                                  <div class="search-box">
                                    <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                      <input class="form-control search-input search" type="search" placeholder="Search conceptos" aria-label="Search"/>
                                      <span class="fas fa-search search-box-icon"></span>
                                    </form>
                                  </div>
                                </div>
                                <div id="customerOrdersTable" class="bg-white border border-200 rounded-2 position-relative top-1 overflow-auto" style="height:240px;" data-list='{"valueNames":["codcon","descon","valor"],"page":10, "pagination":true }'>
                                  <div class="table-responsive scrollbar mx-n1 px-1" >
                                    <table class="table table-sm fs--1 mb-0">
                                      <thead>
                                          <tr>
                                            <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                              <div class="form-check mb-0 fs-0">
                                                <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                              </div>
                                            </th>
                                            <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="codcon" style="width:5%;">CODCON</th>
                                            <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="descon">DESCON</th>
                                            <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >VALOR</th>
                                          </tr>
                                      </thead>
                                      <tbody class="list" id="customer-order-table-body-ingresos" >
                                      </tbody>
                                    </table>
                                  </div>
                                </div>
                              </div>
                          </div>
                        </div>
                      </div>

                      <div class="row g-4">
                          <h4 class="mb-0 mt-7">Descuentos</h4>
                          <div id="orderTable" class="mt-2" data-list='{"valueNames":["codcon","descon","valor"],"page":10,"pagination":true}'>
                            <div class="mb-3" class="mt-0">
                                <div class="row g-3">
                                  <div class="col-auto">
                                    <div class="search-box">
                                      <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                        <input class="form-control search-input search" type="search" placeholder="Search conceptos" aria-label="Search"/>
                                        <span class="fas fa-search search-box-icon"></span>
                                      </form>
                                    </div>
                                  </div>
                                  <div id="customerOrdersTable" class="bg-white border border-200 rounded-2 position-relative top-1 overflow-auto" style="height:240px;" data-list='{"valueNames":["codcon","descon","valor"],"page":10, "pagination":true }'>
                                    <div class="table-responsive scrollbar mx-n1 px-1" >
                                      <table class="table table-sm fs--1 mb-0">
                                        <thead>
                                            <tr>
                                              <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                                <div class="form-check mb-0 fs-0">
                                                  <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                                </div>
                                              </th>
                                              <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="codcon" style="width:5%;">CODCON</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="descon">DESCON</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >VALOR</th>
                                            </tr>
                                        </thead>
                                        <tbody class="list" id="customer-order-table-body-descuentos" >
                                        </tbody>
                                      </table>
                                    </div>
                                  </div>
                                </div>
                            </div>
                          </div>
                      </div>

                      <div class="row g-4">
                        <h4 class="mb-0 mt-7">Aportes</h4>
                        <div id="orderTable" class="mt-2" data-list='{"valueNames":["codcon","descon","valor"],"page":10,"pagination":true}'>
                          <div class="mb-3" class="mt-0">
                              <div class="row g-3">
                                <div class="col-auto">
                                  <div class="search-box">
                                    <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                      <input class="form-control search-input search" type="search" placeholder="Search conceptos" aria-label="Search"/>
                                      <span class="fas fa-search search-box-icon"></span>
                                    </form>
                                  </div>
                                </div>
                                <div id="customerOrdersTable" class="bg-white border border-200 rounded-2 position-relative top-1 overflow-auto" style="height:240px;" data-list='{"valueNames":["codcon","descon","valor"],"page":10, "pagination":true }'>
                                  <div class="table-responsive scrollbar mx-n1 px-1" >
                                    <table class="table table-sm fs--1 mb-0">
                                      <thead>
                                          <tr>
                                            <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                              <div class="form-check mb-0 fs-0">
                                                <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                              </div>
                                            </th>
                                            <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="codcon" style="width:5%;">CODCON</th>
                                            <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="descon">DESCON</th>
                                            <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >VALOR</th>
                                          </tr>
                                      </thead>
                                      <tbody class="list" id="customer-order-table-body-aportes" >
                                      </tbody>
                                    </table>
                                  </div>
                                </div>
                              </div>
                          </div>
                        </div>
                      </div>

                      <div class="row g-4">
                          <h4 class="mb-0 mt-7">Neto</h4>
                          <div id="orderTable" class="mt-2" data-list='{"valueNames":["codcon","descon","valor"],"page":10,"pagination":true}'>
                            <div class="mb-3" class="mt-0">
                                <div class="row g-3">
                                  <div class="col-auto">
                                    <div class="search-box">
                                      <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                        <input class="form-control search-input search" type="search" placeholder="Search conceptos" aria-label="Search"/>
                                        <span class="fas fa-search search-box-icon"></span>
                                      </form>
                                    </div>
                                  </div>
                                  <div id="customerOrdersTable" class="bg-white border border-200 rounded-2 position-relative top-1 overflow-auto" style="height:240px;" data-list='{"valueNames":["codcon","descon","valor"],"page":10, "pagination":true }'>
                                    <div class="table-responsive scrollbar mx-n1 px-1" >
                                      <table class="table table-sm fs--1 mb-0">
                                        <thead>
                                            <tr>
                                              <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                                <div class="form-check mb-0 fs-0">
                                                  <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                                </div>
                                              </th>
                                              <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="codcon" style="width:5%;">CODCON</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="descon">DESCON</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >VALOR</th>
                                            </tr>
                                        </thead>
                                        <tbody class="list" id="customer-order-table-body-neto" >
                                        </tbody>
                                      </table>
                                    </div>
                                  </div>
                                </div>
                            </div>
                          </div>
                      </div>

                      <div class="row g-4">
                            <h4 class="mb-0 mt-7">Totales</h4>
                            <div id="orderTable" class="mt-2" data-list='{"valueNames":["codcon","descon","valor"],"page":10,"pagination":true}'>
                              <div class="mb-3" class="mt-0">
                                  <div class="row g-3">
                                    <div class="col-auto">
                                      <div class="search-box">
                                        <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                          <input class="form-control search-input search" type="search" placeholder="Search conceptos" aria-label="Search"/>
                                          <span class="fas fa-search search-box-icon"></span>
                                        </form>
                                      </div>
                                    </div>
                                    <div id="customerOrdersTable" class="bg-white border border-200 rounded-2 position-relative top-1 overflow-auto" style="height:240px;" data-list='{"valueNames":["codcon","descon","valor"],"page":10, "pagination":true }'>
                                      <div class="table-responsive scrollbar mx-n1 px-1" >
                                        <table class="table table-sm fs--1 mb-0">
                                          <thead>
                                              <tr>
                                                <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                                  <div class="form-check mb-0 fs-0">
                                                    <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                                  </div>
                                                </th>
                                                <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="codcon" style="width:5%;">CODCON</th>
                                                <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="descon">DESCON</th>
                                                <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >VALOR</th>
                                              </tr>
                                          </thead>
                                          <tbody class="list" id="customer-order-table-body-totales" >
                                          </tbody>
                                        </table>
                                      </div>
                                    </div>
                                  </div>
                              </div>
                            </div>
                      </div>
                    </form>
                </div>
                <div class="modal-footer d-flex justify-content-end align-items-center px-0 pb-0 border-200 pt-0">
                    <a class="btn btn-sm btn-primary px-3 my-0" data-bs-dismiss="modal" aria-label="Close">Cerrar</a>
                    <!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" type="submit"><div class="spinner-border spinner-border-sm" style="height:13px; width:13px;" role="status"></div><span class="ms-2">Guardar Periodo</span></button>-->
                    <!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" onclick="mostrarAlertModalEdit();" type="submit"><span class="ms-2">Guardar Periodo</span></button>-->
                </div>
          </div>
      </div>
    </div>

    <div id="modalAsistencias" class="modal fade" tabindex="-1" aria-labelledby="scrollingLongModalLabel2" aria-hidden="true" >
      <div class="modal-dialog modal-lg modal-dialog-scrollable">
          <div class="modal-content bg-100">
                <div class="modal-header border-200 bg-soft p-4">
                   <h5 class="modal-title text-1000 fs-2 lh-sm">Asistencias por periodo planilla/ empleado</h5>
                   <button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>
                </div>
                <div class="modal-body p-4">
                    <form class="needs-validation" id="formAsistencias" method="POST" action="gestionarModalAsistencias" novalidate >
                      <input id="accion" name="accion" type="hidden" value="" />
                      <input id="desfecdia" name="desfecdia" type="hidden" value="" />
                      <input id="iexcodfec" name="iexcodfec" type="hidden" value="" />
                      <input id="iexcodpro" name="iexcodpro" type="hidden" value="" />
                      <input id="iexperiodo" name="iexperiodo" type="hidden" value="" />
                      <input id="iexcorrel" name="iexcorrel" type="hidden" value="1" />
                      <input id="iexcodtra" name="iexcodtra" type="hidden" value="" />
                      <input id="iexcodcia" name="iexcodcia" type="hidden" value="" />

                      <div id="alertModalSuccessEdit" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                          <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                          <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                          <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                      </div>

                      <div class="row g-3 mt-1">
                          <div class="col-sm-6 col-md-2">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Id trab</label>
                                <input class="form-control" name="idTrabAsis" id="idTrabAsis" type="text" required disabled />
                                <input name="idTrabAsisHidden" id="idTrabAsisHidden" type="hidden" value="" />
                          </div>
                          <div class="col-sm-6 col-md-6">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Trabajador</label>
                                <input class="form-control" name="trabAsis" id="trabAsis" type="text" required disabled />
                                <input name="trabAsisHidden" id="trabAsisHidden" type="hidden" value="" />
                          </div>
                          <div class="col-sm-6 col-md-3">
                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de inicio</label>
                              <input class="form-control" name="feciniAsis" id="feciniAsis" type="text" required disabled />
                              <input name="feciniAsisHidden" id="feciniAsisHidden" type="hidden" value="" />
                          </div>
                          <div class="col-sm-6 col-md-3">
                            <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha fin</label>
                            <input class="form-control" name="fecfinAsis" id="fecfinAsis" type="text" required disabled />
                            <input name="fecfinAsisHidden" id="fecfinAsisHidden" type="hidden" value="" />
                          </div>
                          <div class="col-sm-6 col-md-6">
                            <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Seleccionar excel</label>
                            <input class="form-control" id="uploadFile" name="uploadFile" type="file" placeholder="" />
                          </div>
                      </div>
                      <div class="row g-4 mt-0">
                            <div class="col-auto">
                                <a class="btn btn-primary btn-sm mt-1" onclick="return verTurnos('1');" ><span class="fas fa-calendar-days me-2"></span>Traer turnos</a>
                                <a class="btn btn-phoenix-secondary btn-sm mt-1" onclick="return programarTurnos('3');" ><span class="fas fa-wrench me-2"></span>Programar turnos</a>
                                <a class="btn btn-phoenix-secondary btn-sm mt-1" onclick="return calificar('5');" ><span class="fa-regular fa-star me-2"></span>Calificar</a>
                                <a class="btn btn-phoenix-secondary btn-sm mt-1" onclick=""  ><span class="fas fa-gauge me-2"></span>Marcasiones ing/sal</a>
                                <a class="btn btn-phoenix-danger btn-sm mt-1" onclick="return borrarTodo('2');"  ><span class="fas fa-trash me-2"></span>Borrar todo</a>
                                <a class="btn btn-phoenix-secondary btn-sm mt-1" onclick="return consolidar('7');"  ><span class="fas fa-right-left me-2"></span>Consolidar</a>
                                <a id="btnReportAsis" class="btn btn-phoenix-secondary btn-sm mt-1" href="#" target="_blank" onclick="reporteAsistencias();" ><span class="fas fa-download me-2"></span>Reporte asistencias PDF</a>
                                <!--<a class="btn btn-phoenix-success btn-sm mt-1" type="button" data-bs-toggle="modal" data-bs-target="#confirmModalCargarExcel" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" href="#"><span class="fas fa-upload me-2"></span>Importar excel</a>-->
                            </div>
                      </div>
                      <div class="row g-3 bg-100 mt-0">
                        <!--<h4 class="mb-0 mt-7">Calendario</h4>-->
                        <div id="orderTable" class="mt-2" data-list='{"valueNames":["codcon","descon","valor"],"page":10,"pagination":true}'>
                          <div class="mb-3" class="mt-0">
                              <div class="row g-3">
                                  <div id="calendarbody1" class="table-responsive scrollbar mx-n1 px-1 bg-100" >
                                        <div class="mx-n4 px-4 mx-lg-n6 px-lg-6 border-y border-top">
                                          <div class="row py-3 gy-3 gx-0">
                                            <div class="col-6 col-md-4 order-1 d-flex align-items-center">
                                              <a href="#" class="btn btn-sm btn-phoenix-secondary px-4" >Hoy</a>
                                            </div>
                                            <div class="col-12 col-md-4 order-md-1 d-flex align-items-center justify-content-center">
                                              <button class="btn icon-item icon-item-sm shadow-none text-1100 p-0" type="button" data-event="prev" title="Previous"><span class="fas fa-chevron-left"></span></button>
                                              <h3 id="mesDes" class="text-1100 fw-semi-bold calendar-title mb-0"></h3>
                                              <button class="btn icon-item icon-item-sm shadow-none text-1100 p-0" type="button" data-event="next" title="Next"><span class="fas fa-chevron-right"></span></button>
                                            </div>
                                            <div class="col-6 col-md-4 ms-auto order-1 d-flex justify-content-end">
                                              <div>
                                                <div class="btn-group btn-group-sm" role="group">
                                                  <a href="#" class="btn btn-phoenix-primary active-view" >Mes</a>
                                                  <a href="#" class="btn btn-phoenix-secondary" >Semana</a>
                                                </div>
                                              </div>
                                            </div>
                                          </div>
                                        </div>
                                  </div>
                                   <div class="table-responsive mx-n1 px-1 bg-100" style="width: 742px !important;" >
                                        <table  class="table">
                                            <thead id="calendarHead2">
                                            </thead>
                                            <tbody id="calendarBody2" class="fs--2 fw-semi-bold text-1000">
                                            </tbody>
                                            <tfoot id="calendarFoot2">
                                            </tfoot>
                                        </table>
                                   </div>
                              </div>
                          </div>
                        </div>
                    </div>
                    </form>
                </div>
                <div class="modal-footer d-flex justify-content-end align-items-center px-0 pb-0 border-200 pt-0">
                    <a class="btn btn-sm btn-primary px-3 my-0" data-bs-dismiss="modal" aria-label="Close">Cerrar</a>
                    <!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" type="submit"><div class="spinner-border spinner-border-sm" style="height:13px; width:13px;" role="status"></div><span class="ms-2">Guardar Periodo</span></button>-->
                    <!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" onclick="mostrarAlertModalEdit();" type="submit"><span class="ms-2">Guardar Periodo</span></button>-->
                </div>
          </div>
      </div>
    </div>

    <div id="modalOtrosDatos" class="modal fade" tabindex="-1" aria-labelledby="scrollingLongModalLabel2" aria-hidden="true" >
      <div class="modal-dialog modal-lg modal-dialog-scrollable">
          <div class="modal-content bg-100">
                <div class="modal-header border-200 bg-soft p-4">
                   <h5 class="modal-title text-1000 fs-2 lh-sm">Otros datos</h5>
                   <button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>
                </div>
                <div class="modal-body p-4">
                    <form class="needs-validation g-3" method="POST" action="" novalidate >
                      <!--<div id="alertModalSuccessEdit" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                          <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                          <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                          <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                      </div>-->

                      <div class="row g-3 mt-0">
                          <div class="col-sm-6 col-md-5">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Regimen</label>
                                <input class="form-control" name="regOtros" id="regOtros" type="text" required disabled />
                                <input class="form-control" name="idRegOtrosHidden" id="idRegOtrosHidden" type="hidden" value="" />
                          </div>
                          <div class="col-sm-6 col-md-5">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Proceso</label>
                                <input class="form-control" name="procOtros" id="procOtros" type="text" required disabled />
                                <input class="form-control" name="idProcHidden" id="idProcHidden" type="hidden" value="" />
                          </div>
                          <div class="col-sm-6 col-md-5">
                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Periodo</label>
                              <input class="form-control" name="perOtros" id="perOtros" type="text" required disabled />
                              <input class="form-control" name="perOtrosHidden" id="perOtrosHidden" type="hidden" value="" />
                          </div>
                          <div class="col-sm-6 col-md-3">
                            <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Estado</label>
                            <input class="form-control" name="estadoOtros" id="estadoOtros" type="text" required disabled />
                            <input class="form-control" name="estadosOtrosHidden" id="estadosOtrosHidden" type="hidden" value="" />
                          </div>
                      </div>

                      <div class="row g-3">
                          <h4 class="mb-0 mt-7">Vacaciones</h4>
                          <div id="orderTable" class="mt-2" data-list='{"valueNames":["codcon","descon","valor"],"page":10,"pagination":true}'>
                            <div class="mb-3" class="mt-0">
                                <div class="row g-3">
                                  <div class="col-auto">
                                    <div class="search-box">
                                      <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                        <input class="form-control search-input search" type="search" placeholder="Search vacaciones" aria-label="Search"/>
                                        <span class="fas fa-search search-box-icon"></span>
                                      </form>
                                    </div>
                                  </div>
                                  <div class="col-auto">
                                      <div class="btn-group mb-1 me-1 ms-1 mt-1">
                                        <button class="btn btn-sm btn-phoenix-secondary" type="button"><span class="fa-solid fa-hashtag fs--1 me-2"></span></span class="ps-5">Exportar</span></button>
                                        <button class="btn btn-sm dropdown-toggle dropdown-toggle-split btn-phoenix-secondary" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="sr-only"></span></button>
                                        <div class="dropdown-menu">
                                          <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Exportar Excel Vacaciones</a>
                                        </div>
                                      </div>
                                  </div>
                                  <div id="customerOrdersTable" class="bg-white border border-200 rounded-2 position-relative top-1 overflow-auto" style="height:240px;" data-list='{"valueNames":["codcon","descon","valor"],"page":10, "pagination":true }'>
                                    <div class="table-responsive scrollbar mx-n1 px-1" >
                                      <table class="table table-sm fs--1 mb-0">
                                        <thead>
                                            <tr>
                                              <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                                <div class="form-check mb-0 fs-0">
                                                  <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                                </div>
                                              </th>
                                              <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="codcon" style="width:5%;">ID</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="descon">TRABAJADOR</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >TIPO</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >FECINI</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >FECFIN</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >NRO DIA</th>
                                              <th class="sort align-middle text-center pe-0 ps-3 white-space-nowrap" scope="col" >CODCON</th>
                                            </tr>
                                        </thead>
                                        <tbody class="list" id="otros-customer-order-table-body-vacaciones" >
                                        </tbody>
                                      </table>
                                    </div>
                                  </div>
                                </div>
                            </div>
                          </div>
                      </div>

                      <div class="row g-4">
                        <h4 class="mb-0 mt-7">Ausentismos</h4>
                        <div id="orderTable" class="mt-2" data-list='{"valueNames":["codcon","descon","valor"],"page":10,"pagination":true}'>
                          <div class="mb-3" class="mt-0">
                              <div class="row g-3">
                                <div class="col-auto">
                                  <div class="search-box">
                                    <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                      <input class="form-control search-input search" type="search" placeholder="Search ausentismos" aria-label="Search"/>
                                      <span class="fas fa-search search-box-icon"></span>
                                    </form>
                                  </div>
                                </div>
                                <div class="col-auto">
                                      <div class="btn-group mb-1 me-1 ms-1 mt-1">
                                        <button class="btn btn-sm btn-phoenix-secondary" type="button"><span class="fa-solid fa-hashtag fs--1 me-2"></span></span class="ps-5">Exportar</span></button>
                                        <button class="btn btn-sm dropdown-toggle dropdown-toggle-split btn-phoenix-secondary" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="sr-only"></span></button>
                                        <div class="dropdown-menu">
                                          <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Exportar Excel Vacaciones</a>
                                        </div>
                                      </div>
                                </div>
                                <div id="customerOrdersTable" class="bg-white border border-200 rounded-2 position-relative top-1 overflow-auto" style="height:240px;" data-list='{"valueNames":["codcon","descon","valor"],"page":10, "pagination":true }'>
                                  <div class="table-responsive scrollbar mx-n1 px-1" >
                                    <table class="table table-sm fs--1 mb-0">
                                      <thead>
                                          <tr>
                                            <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                              <div class="form-check mb-0 fs-0">
                                                <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                              </div>
                                            </th>
                                            <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="codcon" style="width:5%;">ID</th>
                                            <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="descon">TRABAJADOR</th>
                                            <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >TIPO</th>
                                            <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >FECINI</th>
                                            <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >FECFIN</th>
                                            <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >NRO DIA</th>
                                            <th class="sort align-middle text-center pe-0 ps-3 white-space-nowrap" scope="col" >CODCON</th>
                                          </tr>
                                      </thead>
                                      <tbody class="list" id="otros-customer-order-table-body-ausentismos" >
                                      </tbody>
                                    </table>
                                  </div>
                                </div>
                              </div>
                          </div>
                        </div>
                      </div>

                      <div class="row g-4">
                          <h4 class="mb-0 mt-7">Prestamos</h4>
                          <div id="orderTable" class="mt-2" data-list='{"valueNames":["codcon","descon","valor"],"page":10,"pagination":true}'>
                            <div class="mb-3" class="mt-0">
                                <div class="row g-3">
                                  <div class="col-auto">
                                    <div class="search-box">
                                      <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                        <input class="form-control search-input search" type="search" placeholder="Search prestamos" aria-label="Search"/>
                                        <span class="fas fa-search search-box-icon"></span>
                                      </form>
                                    </div>
                                  </div>
                                  <div class="col-auto">
                                    <div class="btn-group mb-1 me-1 ms-1 mt-1">
                                      <button class="btn btn-sm btn-phoenix-secondary" type="button"><span class="fa-solid fa-hashtag fs--1 me-2"></span></span class="ps-5">Exportar</span></button>
                                      <button class="btn btn-sm dropdown-toggle dropdown-toggle-split btn-phoenix-secondary" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="sr-only"></span></button>
                                      <div class="dropdown-menu">
                                        <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Exportar Excel Vacaciones</a>
                                      </div>
                                    </div>
                                  </div>

                                  <div id="customerOrdersTable" class="bg-white border border-200 rounded-2 position-relative top-1 overflow-auto" style="height:240px;" data-list='{"valueNames":["codcon","descon","valor"],"page":10, "pagination":true }'>
                                    <div class="table-responsive scrollbar mx-n1 px-1" >
                                      <table class="table table-sm fs--1 mb-0">
                                        <thead>
                                            <tr>
                                              <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                                <div class="form-check mb-0 fs-0">
                                                  <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                                </div>
                                              </th>
                                              <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="codcon" style="width:5%;">ID</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="descon">TRABAJADOR</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >TIPO</th>
                                              <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" >FEC CUOTA</th>
                                              <th class="sort align-middle text-center pe-3 ps-3 white-space-nowrap" scope="col" >ID CUOTA</th>
                                              <th class="sort align-middle text-center pe-2 ps-2 white-space-nowrap" scope="col" >CUOTA</th>
                                              <th class="sort align-middle text-center pe-0 ps-2 white-space-nowrap" scope="col" >CODCON</th>
                                            </tr>
                                        </thead>
                                        <tbody class="list" id="otros-customer-order-table-body-prestamos" >
                                        </tbody>
                                      </table>
                                    </div>
                                  </div>
                                </div>
                            </div>
                          </div>
                      </div>

                      <div class="row g-4">
                        <h4 class="mb-0 mt-7">Promedio</h4>
                        <div id="orderTable" class="mt-2" data-list='{"valueNames":["codcon","descon","valor"],"page":10,"pagination":true}'>
                          <div class="mb-3" class="mt-0">
                              <div class="row g-3">
                                <div class="col-auto">
                                  <div class="search-box">
                                    <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                      <input class="form-control search-input search" type="search" placeholder="Search promedios" aria-label="Search"/>
                                      <span class="fas fa-search search-box-icon"></span>
                                    </form>
                                  </div>
                                </div>
                                <div id="customerOrdersTable" class="bg-white border border-200 rounded-2 position-relative top-1 overflow-auto" style="height:240px;" data-list='{"valueNames":["codcon","descon","valor"],"page":10, "pagination":true }'>
                                  <div class="table-responsive scrollbar mx-n1 px-1" >
                                    <table class="table table-sm fs--1 mb-0">
                                      <thead>
                                          <tr>
                                            <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                              <div class="form-check mb-0 fs-0">
                                                <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                              </div>
                                            </th>
                                            <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="codcon" style="width:5%;">ID</th>
                                            <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="descon">TRABAJADOR</th>
                                            <th class="sort align-middle text-center pe-2 ps-2 white-space-nowrap" scope="col" >PERIODO FINAL</th>
                                            <th class="sort align-middle text-center pe-2 ps-2 white-space-nowrap" scope="col" >CONCEPTO FINAL</th>
                                            <th class="sort align-middle text-center pe-2 ps-2 white-space-nowrap" scope="col" >PERIODO ANT</th>
                                            <th class="sort align-middle text-center pe-2 ps-2 white-space-nowrap" scope="col" >CODCON</th>
                                            <th class="sort align-middle text-center pe-2 ps-2 white-space-nowrap" scope="col" >VALOR</th>
                                          </tr>
                                      </thead>
                                      <tbody class="list" id="otros-customer-order-table-body-promedio" >
                                      </tbody>
                                    </table>
                                  </div>
                                </div>
                              </div>
                          </div>
                        </div>
                      </div>
                    </form>
                </div>
                <div class="modal-footer d-flex justify-content-end align-items-center px-0 pb-0 border-200 pt-0">
                    <a class="btn btn-sm btn-primary px-3 my-0" data-bs-dismiss="modal" aria-label="Close">Cerrar</a>
                    <!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" type="submit"><div class="spinner-border spinner-border-sm" style="height:13px; width:13px;" role="status"></div><span class="ms-2">Guardar Periodo</span></button>-->
                    <!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" onclick="mostrarAlertModalEdit();" type="submit"><span class="ms-2">Guardar Periodo</span></button>-->
                </div>
          </div>
      </div>
    </div>

    <div id="modalLoading" class="modal fade" tabindex="-1" data-bs-backdrop="static" aria-labelledby="scrollingLongModalLabel2" aria-hidden="true" >
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content bg-100 rounded-2 border border-300">
            <form class="needs-validation" method="POST" action="" novalidate >
                <div class="modal-header border-bottom border-300 bg-300 bg-opacity-25 p-4">
                   <h5 id="h5modalLoadinglabel" class="modal-title text-1000 fs-2 lh-sm">Procesando datos</h5>
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

    <div id="modalLoadingBorrar" class="modal fade" tabindex="-1" data-bs-backdrop="static" aria-labelledby="scrollingLongModalLabel2" aria-hidden="true" >
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content bg-100 rounded-2 border border-300">
            <form class="needs-validation" method="POST" action="" novalidate >
                <div class="modal-header border-bottom border-300 bg-300 bg-opacity-25 p-4">
                   <h5 id="h5modalLoadinglabelb" class="modal-title text-1000 fs-2 lh-sm">Borrando datos</h5>
                   <!--<button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>-->
                </div>
                <div class="modal-body p-4 bg-300 bg-opacity-50 pt-3 pb-0">
                  <div class="mt-0 mb-0">
                      <p class="fs--1">Se esta procesando la transacción y actualizando espere unos minutos hasta que haya finalizado la tarea...</p>
                      <div class="col-12 text-center">
                          <div id="iconspinner" class="spinner-border text-danger" role="status">
                            <span class="visually-hidden">Loading...</span>
                          </div>
                      </div>
                  </div>
                </div>
                <div class="modal-footer bg-300 bg-opacity-25 d-flex justify-content-end align-items-center px-0 pb-0 border-top border-300 pt-0">
                    <button id="btnFooter" class="btn btn-sm btn-phoenix-danger text-danger px-9 my-0 mt-1 ps-4 pe-4" href="#"><div class="spinner-border spinner-border-sm" style="height:13px; width:13px;" role="status"></div><span id="spanbtnModalLoadingb" class="ms-2">Actualizando calendario</span></button>
                </div>
            </form>
          </div>
      </div>
    </div>

    <div id="modalLoadingIni" class="modal fade" data-bs-backdrop="static" tabindex="-1" aria-labelledby="scrollingLongModalLabel2" aria-hidden="true" >
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content bg-100 rounded-2 border border-300">
            <form class="needs-validation" method="POST" action="" novalidate >
                <div class="modal-header border-bottom border-300 bg-300 bg-opacity-25 p-4">
                   <h5 class="modal-title text-1000 fs-2 lh-sm">Inicializando calendario</h5>
                   <!--<button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>-->
                </div>
                <div class="modal-body p-4 bg-300 bg-opacity-50 pt-3 pb-0">
                  <div class="mt-0 mb-0">
                      <p class="fs--1">Espere unos segundos el sistema esta obteniendo la data de asistencias, turnos y marcaciones...</p>
                      <div class="col-12 text-center">
                          <div id="iconspinner" class="spinner-border text-primary" role="status">
                            <span class="visually-hidden">Loading...</span>
                          </div>
                      </div>
                  </div>
                </div>
                <div class="modal-footer bg-300 bg-opacity-25 d-flex justify-content-end align-items-center px-0 pb-0 border-top border-300 pt-0">
                    <button id="btnFooter" class="btn btn-sm btn-danger px-9 my-0 mt-1 ps-4 pe-4" href="#"><div class="spinner-border spinner-border-sm" style="height:13px; width:13px;" role="status"></div><span id="spanbtnModalLoading" class="ms-2">Inicializando</span></button>
                </div>
            </form>
          </div>
      </div>
    </div>
</html>


