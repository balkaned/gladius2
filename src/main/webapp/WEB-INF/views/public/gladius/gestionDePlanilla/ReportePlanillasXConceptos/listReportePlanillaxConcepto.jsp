<%-- Created on : 15/06/2023, 12:20:00 PM Author : Jean Quiroz Email : jeanp.quiroz@gmail.com --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">

<head>
    <jsp:include page="../../../links.jsp"></jsp:include>
</head>
<script>
    function enviaForm(variable,tiposubmit,codcondel){
        var opcion = confirm("Esta seguro de realizar la operación?");

        if (opcion == true) {
            if(variable==1){
                document.getElementById("accion").value="ADD";
                document.getElementById("tiposubmit").value=tiposubmit;
            }else if(variable==2){
                document.getElementById("accion").value="DEL";
                document.getElementById("tiposubmit").value=tiposubmit;
                document.getElementById("codcondel").value=codcondel;
            }else if(variable==3){
                document.getElementById("accion").value="PLACONGENREP";
            }

            document.getElementById("frmReportPlanconcept").submit();

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
                <li class="breadcrumb-item"><a href="#!">Gestión de planillas</a></li>
                <li class="breadcrumb-item active">Reporte de planilla x concepto</li>
            </ol>
        </nav>
        <div class="mb-9">
            <div class="row g-3 mb-4">
                <div class="col-auto">
                    <h2 id="h2top" class="mb-0">Reporte planilla x conceptos</h2>
                </div>
            </div>

            <div class="row g-3">
                <div class="col-xl-8">
                    <div class="row gx-3 gy-4">
                        <form id="frmReportPlanconcept" class="row g-3 mb-0 needs-validation" method="POST" action="listReportePlanillaxConcepto" novalidate>
                            <input name="accion" id="accion" type="hidden" value="NUEVO"/>
                            <input name="tiposubmit" id="tiposubmit" type="hidden" value="${tiposubmit}"/>
                            <input name="codcondel" id="codcondel" type="hidden" value=""/>

                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Periodo inicio</label>
                                <input class="form-control" type="text" name="perini" id="perini" value="${xperini}" placeholder="202301" required>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Periodo fin</label>
                                <input class="form-control" type="text" name="perfin"  id="perfin" value="${xperfin}" placeholder="202304" required>
                            </div>
                            <div class="col-sm-6 col-md-8">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Codigo de concepto</label>
                                <select class="form-select" name="codcon" id="codcon" required>
                                    <option value="" selected>Seleccionar concepto</option>
                                    <c:forEach var = "lstConcepto" items = "${requestScope.lstConcepto}">
                                        <option  value="${lstConcepto.codConcepto}">${lstConcepto.codConcepto} - ${lstConcepto.desVariable} - ${lstConcepto.desConcepto}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="mb-3">
                                <button class="btn btn-phoenix-secondary btn-sm" onclick="return enviaForm('1','submit_por_click','');"><span class="fa-solid fa-plus me-2"></span>Add a tabla tmp</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-12">
                <div id="orderTable" data-list='{"valueNames":["order","total","customer","payment_status","fulfilment_status","delivery_type","date"],"page":10,"pagination":true}'>
                    <div class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1">
                        <div class="table-responsive scrollbar mx-n1 px-1">
                            <table class="table table-sm fs--1 mb-0">
                                <thead>
                                    <tr>
                                        <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                            <div class="form-check mb-0 fs-0">
                                                <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}'/>
                                            </div>
                                        </th>
                                        <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="order" style="width:5%;">ID</th>
                                        <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">DESCRIPCION</th>
                                        <th class="sort align-middle text-center ps-8 pe-4" scope="col" ></th>
                                    </tr>
                                </thead>
                                <tbody class="list" id="order-table-body">
                                    <c:if test="${not empty requestScope.listacon}">
                                        <c:forEach var="listacon" items="${requestScope.listacon}">
                                            <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                                <td class="fs--1 align-middle px-0 py-3">
                                                    <div class="form-check mb-0 fs-0">
                                                        <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}'/>
                                                    </div>
                                                </td>
                                                <td class="order align-middle white-space-nowrap py-0"><a href="#">#${listacon.codConcepto}</a></td>
                                                <td class="total align-middle text-start fw-semi-bold text-1000 ps-4">${listacon.desConcepto}</td>

                                                <td class="align-middle text-center white-space-nowrap pe-0 action">
                                                      <div class="font-sans-serif btn-reveal-trigger position-static">
                                                        <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                                        data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                                        <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                                        <div class="dropdown-menu dropdown-menu-end py-2">
                                                          <a id="dropdownmenutable" class="dropdown-item" onclick="return enviaForm('2','submit_por_click','${listacon.codConcepto}');" href="#"><span class="fa-solid fa-trash me-2"></span>Eliminar</a></div>
                                                      </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </tbody>
                            </table>

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

                            <div class="form-group row mt-3">
                                <div class="col-md-6 col-sm-6 ">
                                    <button class="btn btn-primary btn-sm" onclick="enviaForm('3','','')" type="button"><span class="fas fa-bolt me-2"></span>Generar reporte</button>
                                    <a class="btn btn-phoenix-success btn-sm" target="_blank"
                                        href="AWSorFTP_flgsource@verReporteExcel@${idComp}@null@null@null@PlaConRepAllDet@3UP_NROPER=${xperini}UP_NROPER2=${xperfin}UP_CODCON=${varcodcon}@null@null@null"
                                        ><span class="fas fa-download me-2"></span>Descargar excel</a>
                                </div>
                            </div>
                            <br>
                            <table class="table table-sm fs--1 mb-0">
                                <thead>
                                    <tr>
                                        <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                            <div class="form-check mb-0 fs-0">
                                                <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}'/>
                                            </div>
                                        </th>
                                        <th class="white-space-nowrap align-middle pe-0" scope="col" style="width:5%;">NROPER</th>
                                        <th class="align-middle white-space-nowrap text-center ps-2" scope="col" >CODPRO</th>
                                        <th class="align-middle white-space-nowrap text-center ps-0 pe-0" scope="col" >DESPRO</th>
                                        <th class="align-middle white-space-nowrap text-center ps-0 pe-0" scope="col" >CODCON</th>
                                        <th class="align-middle white-space-nowrap text-center ps-0 pe-0" scope="col" >DESCON</th>
                                        <th class="align-middle white-space-nowrap text-center ps-0 pe-0" scope="col" >VECES UTIL</th>
                                        <th class="align-middle white-space-nowrap text-center ps-0 pe-0" scope="col" >TOTAL</th>
                                        <th class="align-middle text-center ps-0 pe-0" scope="col" ></th>
                                    </tr>
                                </thead>
                                <tbody class="list" id="order-table-body">
                                    <c:forEach var="lsthistConcepto" items="${requestScope.lsthistConcepto}">
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                            <td class="fs--1 align-middle px-0 py-3">
                                                <div class="form-check mb-0 fs-0">
                                                    <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}'/>
                                                </div>
                                            </td>
                                            <td class="order align-middle white-space-nowrap py-0"><span class="fa-regular fa-calendar me-2"></span> ${lsthistConcepto.iexnroper}</td>
                                            <td class="total align-middle text-center fw-semi-bold text-1000">${lsthistConcepto.procodpro}</td>
                                            <td class="total align-middle white-space-nowrap text-start fw-semi-bold text-1000">${lsthistConcepto.despro}</td>
                                            <td class="total align-middle text-center fw-semi-bold text-1000"><span class="badge badge-phoenix badge-phoenix-secondary py-1 border-0 text-capitalize">${lsthistConcepto.procodcon}</span></td>
                                            <td class="total align-middle text-start fw-semi-bold text-1000">${lsthistConcepto.coodescon}</td>
                                            <td class="total align-middle text-center fs-9 ps-0 pe-0">${lsthistConcepto.cantidad}</td>
                                            <td class="total align-middle text-end fw-bold text-1000">${lsthistConcepto.provalor}</td>

                                            <td class="align-middle text-center white-space-nowrap pe-0 action">
                                              <div class="font-sans-serif btn-reveal-trigger position-static">
                                                <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                                data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                                <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                                <div class="dropdown-menu dropdown-menu-end py-2">
                                                  <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-download me-2"></span>Excel por periodo</a>
                                              </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                Se grabó exitosamente los cambios.
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
                            <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="button" data-bs-dismiss="modal">Cancel</button>
                            <button class="btn btn-sm btn-primary px-9 fs--2 my-0" onclick="mostrarAlert();" type="submit" data-bs-dismiss="modal">Confirmar</button>
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