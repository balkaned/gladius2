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
    function mostrarAlert() {
        //alert("se grabo exitosamente");
        var div = document.getElementById('alert');
        div.style.display = '';

        setTimeout(function () {
            $("#alerts").hide(6000);
        }, 3000);
    }

    function formatearFecha1() {
        var fechaSeleccionada = $('#fecini').val();

        var anio = fechaSeleccionada.substring(0, 4);
        var mes = fechaSeleccionada.substring(5, 7);
        var dia = fechaSeleccionada.substring(8, 10);

        var fechaFormat = dia + "/" + mes + "/" + anio;
        $("#fecini").val(fechaFormat);
        localStorage.setItem('fechaGuardada1', fechaFormat);
    }

    $(document).ready(function () {
        var fechaGuardada1 = localStorage.getItem('fechaGuardada1');
        if (fechaGuardada1) {
            $("#fecini").val(fechaGuardada1);
        }
        setTimeout(function () {
            $("#fecini").val('');
            localStorage.removeItem('fechaGuardada1'); // Also clear it from localStorage
        }, 60000); // 60000 milliseconds = 1 minute
    });

    function formatearFecha2() {
        var fechaSeleccionada = $('#fecfin').val();

        var anio = fechaSeleccionada.substring(0, 4);
        var mes = fechaSeleccionada.substring(5, 7);
        var dia = fechaSeleccionada.substring(8, 10);

        var fechaFormat = dia + "/" + mes + "/" + anio;
        $("#fecfin").val(fechaFormat);
        localStorage.setItem('fechaGuardada', fechaFormat);
    }

    $(document).ready(function () {
        var fechaGuardada = localStorage.getItem('fechaGuardada');
        if (fechaGuardada) {
            $("#fecfin").val(fechaGuardada);
        }
        setTimeout(function () {
            $("#fecfin").val('');
            localStorage.removeItem('fechaGuardada'); // Also clear it from localStorage
        }, 60000); // 60000 milliseconds = 1 minute
    });

    function consultaDet() {
        document.getElementById("accion").value = "gestionTiempoListVacaciones";
    }

    function modificarDet(codtra, correl) {
        document.getElementById("accion").value = "getGestionVacaciones";
        document.getElementById("codtra").value = codtra;
        document.getElementById("correl").value = correl;
        document.getElementById("gtmvac").submit();

        console.log(codtra, correl);
    }


    function formatearFecha(fecha) {
        var partesFecha = fecha.split('/');
        var nuevaFecha = partesFecha[0] + '-' + partesFecha[1] + '-' + partesFecha[2];
        return nuevaFecha;
    }

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
                <li class="breadcrumb-item"><a href="#!">Gestión de tiempos</a></li>
                <li class="breadcrumb-item active">Gestión de vacaciones</li>
            </ol>
        </nav>
        <div class="mb-9">
            <div class="row g-3 mb-4">
                <div class="col-auto">
                    <h2 id="h2top" class="mb-0">Vacaciones</h2>
                </div>
            </div>

            <div class="row g-5">
                <div class="col-xl-8">
                    <div class="row gx-3 gy-4">
                        <form class="row g-4 mb-0 needs-validation" method="POST" action="gestionTiempoListVacaciones"
                              novalidate>
                            <!-- Regimen Dropdown -->
                            <div class="col-sm-6 col-md-7">
                                <!--<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Regimen</label>-->
                                <select class="form-select" name="iexcodreg" id="iexcodreg" onchange="regimen();" required>
                                    <option value="" selected>Seleccionar regimen</option>
                                    <c:forEach var="Lovs_regimen" items="${requestScope.Lovs_regimen}">
                                        <option value="${Lovs_regimen.idLov}" ${Lovs_regimen.idLov==requestScope.iexcodreg ? 'selected' : '' }>${Lovs_regimen.desLov}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Estado Dropdown -->
                            <div class="col-sm-6 col-md-4">
                                <!--<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Estado</label>-->
                                <select class="form-select" name="slc_estado" required>
                                    <option value="" selected>Seleccionar estado</option>
                                    <option value="0">Activos</option>
                                    <option value="1">Inactivo</option>
                                </select>
                            </div>

                            <!-- Fecha Inicio -->
                            <div class="col-sm-6 col-md-4">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha
                                    Inicio</label>
                                <span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                <input class="form-control datetimepicker" name="fecini" id="fecini"
                                       onchange="formatearFecha1();" type="text"
                                       placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required/>
                            </div>

                            <!-- Fecha Fin -->
                            <div class="col-sm-6 col-md-4">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha
                                    Fin</label>
                                <span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                <input class="form-control datetimepicker" name="fecfin" id="fecfin"
                                       onchange="formatearFecha2();" type="text"
                                       placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required/>
                            </div>

                            <!-- Buttons -->
                            <div class="d-grid gap-2 d-md-block ">
                                <button class="btn btn-primary btn-sm" onclick="consultaDet();"><span class="fa-solid fa-magnifying-glass me-2"></span>Buscar</button>
                                <a class="btn btn-phoenix-secondary btn-sm" href="nuevoGestionVacaciones"><span class="fas fa-plus me-2"></span>Agregar Vacación</a>

                                <div class="btn-group mb-1 me-1 ms-0 mt-1">
                                  <button class="btn btn-sm btn-success" type="button"><span class="fa-solid fa-hashtag fs--1 me-2"></span></span class="ps-5">Exportar</span></button>
                                  <button class="btn btn-sm dropdown-toggle dropdown-toggle-split btn-success" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="sr-only"></span></button>
                                  <div class="dropdown-menu">
                                	  <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="AWSorFTP_flgsource@verReporteExcel@${idComp}@null@null@null@ExportaReporteVacPrg@4PP_REGLAB=${P_REGLAB}PP_FLGEST=${P_FLGEST}PP_FECINI=${P_FECINI}PP_FECFIN=${P_FECFIN}@null@null@null">
                                		<span class="fa-solid fa-download fs--1 me-2"></span>Exportar Programación
                                	  </a>
                                	  <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="AWSorFTP_flgsource@verReporteExcel@${idComp}@null@null@null@ExportaResumenVacSal@2PP_REGLAB=${P_REGLAB}PP_FLGEST=${P_FLGEST}@null@null@null@null@null"><span class="fa-solid fa-download fs--1 me-2"></span>Exportar Saldo</a>
                                	  <div class="dropdown-divider"></div>
                                	  <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Otros</a>
                                  </div>
                                </div>

                                <!--<a class="btn btn-link text-900 me-4 px-0"
                                   href="AWSorFTP_flgsource@verReporteExcel@${idComp}@null@null@null@ExportaReporteVacPrg@4PP_REGLAB=${P_REGLAB}PP_FLGEST=${P_FLGEST}PP_FECINI=${P_FECINI}PP_FECFIN=${P_FECFIN}@null@null@null" target="_blank">
                                    <span class="fa-solid fa-file-export fs--1 ms-3 me-2"></span>Exportar Programación
                                </a>

                                <a class="btn btn-link text-900 me-4 px-0"
                                   href="AWSorFTP_flgsource@verReporteExcel@${idComp}@null@null@null@ExportaResumenVacSal@2PP_REGLAB=${P_REGLAB}PP_FLGEST=${P_FLGEST}@null@null@null@null@null" target="_blank">
                                    <span class="fa-solid fa-file-export fs--1 me-2"></span>Exportar Saldo
                                </a>-->
                            </div>
                        </form>
                    </div>
                </div>
            </div>

                        <div class=" mt-4 mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1">
                          <div class="table-responsive scrollbar mx-n1 px-1">
                        	<table class="table table-sm fs--1 mb-0">
                                <thead>
                                    <tr>
                                        <th class="white-space-nowrap fs--1 align-middle ps-0"
                                            style="width:26px;">
                                            <div class="form-check mb-0 fs-0">
                                                <input class="form-check-input"
                                                       id="checkbox-bulk-order-select"
                                                       type="checkbox"
                                                       data-bulk-select='{"body":"order-table-body"}'/>
                                            </div>
                                        </th>
                                        <th class="sort white-space-nowrap align-middle pe-3"
                                            scope="col" data-sort="order" style="width:5%;">
                                            ID
                                        </th>
                                        <th class="sort align-middle text-center ps-5"
                                            scope="col" data-sort="date">
                                            DOC
                                        </th>

                                        <th class="sort align-middle text-center ps-6"
                                            scope="col" data-sort="date">CODTRA
                                        </th>
                                        <th class="sort align-middle white-space-nowrap text-center ps-8 pe-8" scope="col" >NOMBRES y APELLIDOS</th>
                                        <th class="sort align-middle text-center ps-5"
                                            scope="col" data-sort="date">ESTADO
                                        </th>
                                        <th class="sort align-middle text-center ps-5"
                                            scope="col" data-sort="date">
                                            F.INGRESO
                                        </th>
                                        <th class="sort align-middle text-center white-space-nowrap ps-5 pe-5"
                                            scope="col" data-sort="date">
                                            TIPO VACA.
                                        </th>
                                        <th class="sort align-middle text-center pe-3"
                                            scope="col" data-sort="date">FECINI
                                        </th>
                                        <th class="sort align-middle text-center ps-3 pe-3"
                                            scope="col" data-sort="date">FECFIN
                                        </th>
                                        <th class="sort align-middle text-center pe-3"
                                            scope="col" data-sort="date">DIAS
                                        </th>
                                        <th class="sort align-middle text-center pe-0"
                                            scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody class="list" id="order-table-body">
                                    <c:forEach var="LstVacacionesView"
                                               items="${requestScope.LstVacacionesView}">
                                        <tr
                                                class="hover-actions-trigger btn-reveal-trigger position-static">
                                            <td class="fs--1 align-middle px-0 py-3">
                                                <div class="form-check mb-0 fs-0">
                                                    <input class="form-check-input"
                                                           type="checkbox"
                                                           data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}'/>
                                                </div>
                                            </td>

                                            <td
                                                    class="order align-middle white-space-nowrap py-0">
                                                <a class="fw-semi-bold"
                                                   href="editarGestionVacaciones@${LstVacacionesView.iexcodtra}@${LstVacacionesView.iexcorrel}">#${LstVacacionesView.iexcorrel}</a>
                                            </td>
                                            <td
                                                    class="total align-middle text-center fw-semi-bold text-600 ps-3">
                                                    ${LstVacacionesView.nrodoc}</td>
                                            <td class="total align-middle text-center fw-semi-bold text-1000"><span class="badge badge-tag me-2 fs--0 mb-2">${LstVacacionesView.iexcodtra}</span></td>
                                            <td
                                                    class="total align-middle text-start fw-semi-bold text-1000 ps-5">
                                                    ${LstVacacionesView.desnomtra}</td>
                                            <c:if
                                                    test="${LstVacacionesView.desestado=='activo'}">
                                                <td
                                                        class="payment_status align-middle white-space-nowrap text-center fw-bold text-700">
                                                                            <span
                                                                                    class="badge badge-phoenix fs--2 badge-phoenix-success"><span
                                                                                    class="badge-label">Activo</span><span
                                                                                    class="ms-1"
                                                                                    style="height:12.8px;width:12.8px;"></span></span>
                                                </td>
                                            </c:if>
                                            <c:if
                                                    test="${LstVacacionesView.desestado=='inactivo'}">
                                                <td
                                                        class="payment_status align-middle white-space-nowrap text-center fw-bold text-700">
                                                                            <span
                                                                                    class="badge badge-phoenix fs--2 badge-phoenix-danger"><span
                                                                                    class="badge-label">Inactivo</span><span
                                                                                    class="ms-1"
                                                                                    style="height:12.8px;width:12.8px;"></span></span>
                                                </td>
                                            </c:if>
                                            <td
                                                    class=" fulfilment_status align-middle white-space-nowrap text-center fw-semi-bold text-1000 ps-4 pe-4">
                                                    <a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstVacacionesView.fecing}</td>
                                            <td
                                                    class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-center">
                                                                        <span
                                                                                class="badge badge-phoenix fs--2 badge-phoenix-info"
                                                                                class="badge-label">${LstVacacionesView.destipvac}</span>
                                            </td>
                                            <td
                                                    class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-center ps-4">
                                                    <a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstVacacionesView.iexfecini}</td>
                                            <td
                                                    class="delivery_type align-middle white-space-nowrap text-900 fs--1 ps-4 text-center">
                                                    <a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstVacacionesView.fecfinrep}</td>
                                            <td
                                                    class="delivery_type align-middle  white-space-nowrap text-1000 fs--1  fw-bold text-center ps-4">
                                                    ${LstVacacionesView.iexnrodias}</td>
                                            <td class="align-middle text-end white-space-nowrap pe-0 action">
                                                <div class="font-sans-serif btn-reveal-trigger position-static">
                                                    <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                                    data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                                    <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                                    <div class="dropdown-menu dropdown-menu-end py-2">
                                                        <a id="dropdownmenutable" class="dropdown-item" href="editarGestionVacaciones@${LstVacacionesView.iexcodtra}@${LstVacacionesView.iexcorrel}"><span class="fa-solid fa-pencil me-2"></span>Editar</a>
                                                        <div class="dropdown-divider"></div>
                                                        <a id="dropdownmenutable" class="dropdown-item" onclick="return remove();" href="eliminarVacaciones@${LstVacacionesView.iexcodtra}@${LstVacacionesView.iexcorrel}"><span class="fa-solid fa-trash me-2"></span>Eliminar</a>
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

                        <!-- Alert and Modal sections -->
                        <div class="alert alert-success" role="alert" id="alert"
                             style="display:none;">
                            Se grabó exitosamente los cambios.
                        </div>

                        <div class="modal fade" id="confirmModal" tabindex="-1">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content border">
                                    <form id="addEventForm" autocomplete="off">
                                        <div class="modal-header border-200 p-4">
                                            <h5 class="modal-title text-1000 fs-4 lh-sm">Confirmar
                                            </h5>
                                            <button class="btn p-1 text-900" type="button"
                                                    data-bs-dismiss="modal" aria-label="Close"><span
                                                    class="fas fa-times fs--1"></span></button>
                                        </div>
                                        <div class="modal-body pt-4 pb-2 px-4">
                                            <div class="mb-3">
                                                <label class="fw-bold mb-2 text-1000"
                                                       for="leadStatus">Esta seguro que desea
                                                    confirmar la operacion?</label>
                                            </div>
                                        </div>
                                    </form>
                                    <div
                                            class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
                                        <button
                                                class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0"
                                                type="button" data-bs-dismiss="modal">Cancel
                                        </button>
                                        <button class="btn btn-sm btn-primary px-9 fs--2 my-0"
                                                onclick="mostrarAlert();" type="submit"
                                                data-bs-dismiss="modal">Confirmar
                                        </button>
                                    </div>
                                </div>
                            </div>
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


</html>