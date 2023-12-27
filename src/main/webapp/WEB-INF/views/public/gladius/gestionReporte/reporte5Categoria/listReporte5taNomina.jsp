<%-- Created on : 15/06/2023, 12:20:00 PM Author : Jean Quiroz Email : jeanp.quiroz@gmail.com --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">

<head>
    <jsp:include page="../../../links.jsp"></jsp:include>
</head>
<script>
    function mostrarAlert() {
        //alert("se grabo exitosamente");
        var div = document.getElementById('alert');
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
                <li class="breadcrumb-item"><a href="#!">Page</a></li>
                <li class="breadcrumb-item active">Default</li>
            </ol>
        </nav>
        <div class="mb-9">
            <div class="row g-3 mb-4">
                <div class="col-auto">
                    <h2 id="h2top" class="mb-0">Reporte De 5 Categoria</h2>
                </div>
            </div>

            <div class="row g-5">
                <div class="col-xl-8">
                    <div class="row gx-3 gy-4">
                        <form class="row g-4 mb-0 needs-validation" method="POST" action="" novalidate>
                            <div class="col-sm-6 col-md-6">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Codigo de
                                    Trabajador</label>
                                <select class="form-select" name="percodtra" id="percodtra"
                                        required>
                                    <option value="" selected>Seleccionar</option>
                                    <c:forEach var="LstEmpleadoRes" items="${requestScope.LstEmpleadoRes}">
                                        <option value="${LstEmpleadoRes.iexcodtra}"     ${LstEmpleadoRes.iexcodtra==requestScope.percodtra ? 'selected' : ''}     >
                                            [${LstEmpleadoRes.iexcodtra}]
                                            - ${LstEmpleadoRes.iexapepat} ${LstEmpleadoRes.iexapemat} ${LstEmpleadoRes.iexnomtra}   </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Anio</label>
                                <input class="form-control" type="text" id="peranio" name="peranio"
                                       value="${requestScope.peranio}">
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Trabajador</label>
                                <input class="form-control"
                                       type="text"
                                       value="${requestScope.fichaEmp.iexapepat} ${requestScope.fichaEmp.iexapemat} ${requestScope.fichaEmp.iexnomtra}"
                                       readonly="true"
                                       style="background-color:#F1F4F8;">
                            </div>

                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">N° Documento</label>
                                <input type="text" class="form-control" value="${requestScope.fichaEmp.iexnrodoc}"
                                       readonly="true" style="background-color:#F1F4F8;">
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Estado</label>
                                <input type="text" class="form-control" value="${requestScope.fichaEmp.iexflgest}"
                                       readonly="true" style="background-color:#F1F4F8;">
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de Ingreso</label>
                                <input type="text" class="form-control" value="${requestScope.fichaEmp.iexfecing}"
                                       readonly="true" style="background-color:#F1F4F8;">
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de Cese</label>
                                <input type="text" class="form-control" value="${requestScope.fichaEmp.iexfecret}"
                                       readonly="true" style="background-color:#F1F4F8;">
                            </div>
                            <div class="d-grid gap-2 d-md-block">
                                <button class="btn btn-primary" onclick="consultaDet();"><span
                                        class="fa-solid fa-magnifying-glass me-2"></span>Consultar
                                </button>

                            </div>

                            <div id="orderTable"
                                 data-list='{"valueNames":["order","total","customer","payment_status","fulfilment_status","delivery_type","date"],"page":10,"pagination":true}'>
                                <div
                                        class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1"
                                        style="width: 160%;">
                                    <div class="table-responsive scrollbar mx-n1 px-1">
                                        <table class="table table-sm fs--1 mb-0">
                                            <thead>
                                            <tr>
                                                <th class="white-space-nowrap fs--1 align-middle ps-0"
                                                    style="width:26px;">
                                                    <div class="form-check mb-0 fs-0">
                                                        <input class="form-check-input" id="checkbox-bulk-order-select"
                                                               type="checkbox"
                                                               data-bulk-select='{"body":"order-table-body"}'/>
                                                    </div>
                                                </th>
                                                <th class="sort white-space-nowrap align-middle pe-3" scope="col"
                                                    data-sort="order"
                                                    style="width:5%;">Periodo
                                                </th>
                                                <th class="sort align-middle text-center ps-5" scope="col"
                                                    data-sort="date">IDPROCESO
                                                </th>
                                                <th class="sort align-middle text-center ps-6" scope="col"
                                                    data-sort="date">PROCESO
                                                </th>
                                                <th class="sort align-middle text-center ps-8 pe-4" scope="col"
                                                    data-sort="date">CORREL
                                                </th>
                                                <th class="sort align-middle text-center ps-5" scope="col"
                                                    data-sort="date">TOTAL DE INGRESO
                                                </th>
                                                <th class="sort align-middle text-center ps-5" scope="col"
                                                    data-sort="date">
                                                    IMPORTE AFECTO 5TA
                                                </th>
                                                <th class="sort align-middle text-center ps-5 pe-5" scope="col"
                                                    data-sort="date">
                                                    ING 5TA OTRCIA MES
                                                </th>
                                                <th class="sort align-middle text-center pe-3" scope="col"
                                                    data-sort="date">RENTA 5TA
                                                </th>
                                                <th class="sort align-middle text-center pe-3" scope="col"
                                                    data-sort="date">RENTA 5TA OTRCIA MES
                                                </th>
                                                <th class="sort align-middle text-start pe-0" scope="col"
                                                    data-sort="date">NETO A RECIBIR
                                                </th>
                                                <th class="sort align-middle text-center pe-0" data-sort="date">BOLETA
                                                </th>
                                                <th class="sort align-middle text-center pe-0" data-sort="date">REP
                                                    5TA
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody class="list" id="order-table-body">
                                            <c:set var="xtotingreso" value="${0}"/>
                                            <c:set var="xtotimp5ta" value="${0}"/>
                                            <c:set var="xtotdesc5ta" value="${0}"/>
                                            <c:set var="xtotneto" value="${0}"/>
                                            <c:set var="ximp5taotrciames" value="${0}"/>
                                            <c:set var="xrenta5taotrciames" value="${0}"/>

                                            <c:forEach var="Res_planilla5ta"
                                                       items="${requestScope.Res_planilla5ta}">
                                                <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                                    <td class="fs--1 align-middle px-0 py-3">
                                                        <div class="form-check mb-0 fs-0">
                                                            <input class="form-check-input" type="checkbox"
                                                                   data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}'/>
                                                        </div>
                                                    </td>
                                                    <td class="order align-middle white-space-nowrap py-0">
                                                            ${Res_planilla5ta.iexnroper}
                                                    </td>
                                                    <td class="total align-middle text-center fw-semi-bold text-1000">
                                                            ${Res_planilla5ta.iexcodpro}</td>
                                                    <td class="total align-middle text-center fw-semi-bold text-1000">
                                                            ${Res_planilla5ta.descodpro}</td>
                                                    <td class="total align-middle text-center fw-semi-bold text-1000">
                                                            ${Res_planilla5ta.iexcorrel}</td>
                                                    <td class="total align-middle text-center fw-semi-bold text-1000">
                                                        <fmt:formatNumber value="${Res_planilla5ta.totalingreso}"
                                                                          type="number" maxFractionDigits="2"
                                                                          pattern='###,###.00'/></td>
                                                    <td class="total align-middle text-center fw-semi-bold text-1000">
                                                        <fmt:formatNumber value="${Res_planilla5ta.impafecto5ta}"
                                                                          type="number" maxFractionDigits="2"
                                                                          pattern='###,###.00'/></td>
                                                    <td class="total align-middle text-center fw-semi-bold text-1000">
                                                        <fmt:formatNumber
                                                                value="${Res_planilla5ta.remafect5taotrcia_mes}"
                                                                type="number" maxFractionDigits="2"
                                                                pattern='###,###.00'/></td>
                                                    <td class="total align-middle text-center fw-semi-bold text-1000">
                                                        <fmt:formatNumber value="${Res_planilla5ta.desc5ta}"
                                                                          type="number" maxFractionDigits="2"
                                                                          pattern='###,###.00'/></td>
                                                    <td class="total align-middle text-center fw-semi-bold text-1000">
                                                        <fmt:formatNumber
                                                                value="${Res_planilla5ta.rentafect5taotrcia_mes}"
                                                                type="number" maxFractionDigits="2"
                                                                pattern='###,###.00'/></td>
                                                    <td class="total align-middle text-center fw-semi-bold text-1000">
                                                        <fmt:formatNumber value="${Res_planilla5ta.totalneto}"
                                                                          type="number" maxFractionDigits="2"
                                                                          pattern='###,###.00'/></td>
                                                    <td class="total align-middle text-center fw-semi-bold text-1000">
                                                        <a href="#!" class="fs--1" data-bs-toggle="modal"
                                                           data-bs-target="#reportsFilterModal" aria-haspopup="true"
                                                           aria-expanded="false" data-bs-reference="parent">Boleta</a>
                                                    </td>
                                                    <td class="total align-middle text-center fw-semi-bold text-1000">
                                                        <a href="#"
                                                           onClick="window.open('${pageContext.request.contextPath}/PlanillaServlet?accion=VERCERT5TA&idproceso=${Res_planilla5ta.iexcodpro}&idcodtra=${Res_planilla5ta.iexcodtra}&idnroper=${Res_planilla5ta.iexnroper}&correl=${Res_planilla5ta.iexcorrel}', '', 'width=1200,height=850')">
                                                            5ta </a></td>
                                                </tr>

                                                <c:set var="xtotingreso"
                                                       value="${xtotingreso + Res_planilla5ta.totalingreso}"/>
                                                <c:set var="xtotimp5ta"
                                                       value="${xtotimp5ta + Res_planilla5ta.impafecto5ta}"/>
                                                <c:set var="xtotdesc5ta"
                                                       value="${xtotdesc5ta + Res_planilla5ta.desc5ta}"/>
                                                <c:set var="xtotneto" value="${xtotneto + Res_planilla5ta.totalneto}"/>

                                                <c:set var="ximp5taotrciames"
                                                       value="${ximp5taotrciames + Res_planilla5ta.remafect5taotrcia_mes}"/>
                                                <c:set var="xrenta5taotrciames"
                                                       value="${ xrenta5taotrciames + Res_planilla5ta.rentafect5taotrcia_mes}"/>
                                            </c:forEach>
                                            </tbody>
                                            <tfoot>
                                            <tr>
                                                <td></td>
                                                <td colspan="4"><h5>Totales</h5></td>
                                                <td class="total align-middle text-center "><h5><fmt:formatNumber
                                                        value="${xtotingreso}" type="number" maxFractionDigits="2"
                                                        pattern='###,###.00'/></h5></td>
                                                <td class="total align-middle text-center "><h5><fmt:formatNumber
                                                        value="${xtotimp5ta}" type="number" maxFractionDigits="2"
                                                        pattern='###,###.00'/></h5></td>
                                                <td class="total align-middle text-center"><h5><fmt:formatNumber
                                                        value="${ximp5taotrciames}" type="number" maxFractionDigits="2"
                                                        pattern='###,###.00'/></h5></td>
                                                <td class="total align-middle text-center "><h5><fmt:formatNumber
                                                        value="${xtotdesc5ta}" type="number" maxFractionDigits="2"
                                                        pattern='###,###.00'/></h5></td>
                                                <td class="total align-middle text-center "><h5><fmt:formatNumber
                                                        value="${xrenta5taotrciames}" type="number"
                                                        maxFractionDigits="2" pattern='###,###.00'/></h5></td>
                                                <td class="total align-middle text-center "><h5><fmt:formatNumber
                                                        value="${xtotneto}" type="number" maxFractionDigits="2"
                                                        pattern='###,###.00'/></h5></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            </tfoot>
                                        </table>
                                        <br>
                                        <c:set var="xtotaniorem5ta"
                                               value="${xtotimp5ta + ximp5taotrciames + xing5tacia_anterior }"/>
                                        <c:set var="xtotaniorent5ta"
                                               value="${xtotdesc5ta + xrenta5taotrciames + xret5tacia_anterior}"/>
                                        <h4> Total Ingresos Afectos a 5ta del Año : <fmt:formatNumber
                                                value="${xtotaniorem5ta}" type="number" maxFractionDigits="2"
                                                pattern='###,###.00'/></h4>

                                        <h4>Total Descuento 5ta del Año: <fmt:formatNumber value="${xtotaniorent5ta}"
                                                                                           type="number"
                                                                                           maxFractionDigits="2"
                                                                                           pattern='###,###.00'/></h4>


                                    </div>
                                    <div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
                                        <div class="col-auto d-flex">
                                            <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900"
                                               data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!"
                                                                                      data-list-view="*">View all<span
                                                class="fas fa-angle-right ms-1"
                                                data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none"
                                                                                         href="#!"
                                                                                         data-list-view="less">View Less<span
                                                class="fas fa-angle-right ms-1"
                                                data-fa-transform="down-1"></span></a>
                                        </div>
                                        <div class="col-auto d-flex">
                                            <button class="page-link" data-list-pagination="prev"><span
                                                    class="fas fa-chevron-left"></span></button>
                                            <ul class="mb-0 pagination"></ul>
                                            <button class="page-link pe-0" data-list-pagination="next"><span
                                                    class="fas fa-chevron-right"></span></button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</main>

<%-- Inicio Modal --%>
<div class="modal fade" id="reportsFilterModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content border">
            <form id="addEventForm" autocomplete="off">
                <div class="modal-header border-200 p-4">
                    <h5 class="modal-title text-1000 fs-2 lh-sm">REPORTE DE PLANILLA </h5>
                    <button class="btn p-1 text-danger" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs--1"> 				</span></button>
                </div>
                <div class="modal-body pt-4 pb-2 px-4">
                    <div class="ps-3" style="font-size:13px;">
                        <div class="mb-2">
                            <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-envelope-alt">  </span>
                                <span class="text-1000 mb-0">Email</span>
                            </div><a href="#!">${iexcodtra}</a>
                        </div>
                        <div class="mb-2">
                            <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-phone"> </span>
                                <span class="text-1000 mb-0">Telefono</span>
                            </div><a href="tel:+1234567890">+${telefono}</a>
                        </div>
                        <div class="mb-2">
                            <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-directions"></span>
                                <span class="text-1000 mb-0">Dirección</span>
                            </div><a href="#!">${direccion}</a>
                        </div>
                        <div class="mb-2">
                            <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-postcard"></span>
                                <span class="text-1000 mb-0">Nro Documento</span>
                            </div>
                            <p class="mb-0 text-800">${nrodoc}</p>
                        </div>
                        <div class="mb-2">
                            <div class="d-flex align-items-center mb-1"><span class="me-2 fa-solid fas fa-graduation-cap"></span>
                                <span class="text-1000 mb-0">Puesto</span>
                            </div>
                            <p class="mb-0 text-800">${puesto}</p>
                        </div>
                        <div class="mb-2">
                            <div class="d-flex align-items-center mb-1"><span class="me-2 fa-solid far fa-save"></span>
                                <span class="text-1000 mb-0">Ult. Actualización</span>
                            </div>
                            <p class="mb-0 text-800">${fechaMod}</p>
                        </div>
                        <div>
                            <div class="d-flex align-items-center mb-1">
                                <span class="me-2 uil uil-check-circle"></span>
                                <span class="text-1000 mb-0">Estado</span>
                            </div>
                            <c:if test="${estado.equals('1')}"><span class="badge badge-phoenix badge-phoenix-success">Activo</span></c:if>
                            <c:if test="${estado.equals('0')}"><span class="badge badge-phoenix badge-phoenix-danger">Inactivo</span></c:if>
                        </div>
                    </div>
                </div>
                <div class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
                    <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="submit"> <span class="fas fa-arrows-rotate me-2 fs--2"></span>Otros</button>
                    <button class="btn btn-sm btn-primary px-9 fs--2 my-0" data-bs-dismiss="modal" type="submit">Cerrar</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%-- Fin Modal --%>
<!-- ===============================================-->
<!--    End of Main Content-->
<!-- ===============================================-->

<jsp:include page="../../../demoWidget.jsp"></jsp:include>
<jsp:include page="../../../customize.jsp"></jsp:include>
</body>


</html>