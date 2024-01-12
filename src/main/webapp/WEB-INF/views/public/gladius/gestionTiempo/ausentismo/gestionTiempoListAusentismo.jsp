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

    $(document).ready(function () {
        // Bind change event to #iexcodreg
        $('#iexcodreg').change(function (event) {
            $.ajaxSetup({cache: false});
            $.ajax({
                url: "getlovsLOVCODTRA",
                data: {"accion": "LOVCODTRA", "iexcodreg": $("#iexcodreg").val()},
                success: function (data) {
                    var opt = "<option value='' >Selecciona</option>";
                    for (var i in data) {
                        opt += "<option value='" + data[i].iexcodtra + "'>" +
                            data[i].iexapepat + " " + data[i].iexapemat + " " +
                            data[i].iexnomtra + " - " + data[i].iexfecing + "</option>";
                    }
                    $("#iexcodtra").html(opt);
                }
            });
        });

        // Bind change event to #iexcodtra
        $('#iexcodtra').change(function () {
            var selectedValue = $(this).val();
            localStorage.setItem('selectedIexcodtra', selectedValue);

            // Set a timeout to clear the selected option from localStorage after 1 minute
            setTimeout(function () {
                localStorage.removeItem('selectedIexcodtra');

                // Reset the dropdown to its default state
                $('#iexcodtra').val(''); // Set the dropdown to its default option
            }, 60000);
        });

        // Set the selected option from localStorage when the page loads
        var savedValue = localStorage.getItem('selectedIexcodtra');
        if (savedValue) {
            $("#iexcodtra").val(savedValue);

            // Clear the savedValue after 1 minute from page load, if it exists
            setTimeout(function () {
                localStorage.removeItem('selectedIexcodtra');
                $("#iexcodtra").val(''); // Reset the dropdown after 1 minute
            }, 60000);
        }
    });

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
        document.getElementById("accion").value = "gestionTiempoListAusentismo";
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
                <li class="breadcrumb-item"><a href="#!">Gestion de tiempos</a></li>
                <li class="breadcrumb-item active">Gestion de Ausentismo</li>
            </ol>
        </nav>
        <div class="mb-9">
            <div class="row g-3 mb-4">
                <div class="col-auto">
                    <h2 id="h2top" class="mb-0">Ausentismo</h2>
                </div>
            </div>

            <div class="row g-5">
                <div class="col-xl-8">
                    <div class="row gx-3 gy-4">
                        <form class="row g-4 mb-0 needs-validation" method="POST" action="gestionTiempoListAusentismo"
                              novalidate>
                            <input class="form-control" name="iexcodcia" type="hidden"
                                   value="${requestScope.emp.iexcodcia}"/>

                            <div class="col-sm-6 col-md-7">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Regimen</label>
                                <select class="form-select" name="iexcodreg" id="iexcodreg" required>
                                    <option value="" selected>Seleccionar</option>
                                    <c:forEach var="Lovs_regimen" items="${requestScope.Lovs_regimen}">
                                        <option value="${Lovs_regimen.idLov}" ${Lovs_regimen.idLov==requestScope.iexcodreg
                                                ? 'selected' : '' }>${Lovs_regimen.desLov}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-6 col-md-9">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Trabajador</label>
                                <select name="iexcodtra" id="iexcodtra" class="form-select">
                                    <option value="0" selected>Seleccionar</option>
                                    <c:forEach var="LstTrabajadorReg" items="${requestScope.LstTrabajadorReg}">
                                        <option value="${LstTrabajadorReg.iexcodtra}"
                                            ${LstTrabajadorReg.iexcodtra==requestScope.iexcodtra ? 'selected' : '' }>
                                                ${LstTrabajadorReg.iexapepat} ${LstTrabajadorReg.iexapemat}
                                                ${LstTrabajadorReg.iexnomtra} - ${LstTrabajadorReg.iexfecing}</option>
                                    </c:forEach>

                                </select>
                            </div>

                            <div class="col-sm-6 col-md-4">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha Inicio</label><span
                                    class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                <input class="form-control datetimepicker" name="fecini" id="fecini"
                                       onchange="formatearFecha1();" type="text"
                                       placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}'/>
                            </div>

                            <div class="col-sm-6 col-md-4">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha Fin</label><span
                                    class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                <input class="form-control datetimepicker" name="fecfin" id="fecfin"
                                       onchange="formatearFecha2();" type="text"
                                       placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}'/>
                            </div>
                            <div class="d-grid gap-2 d-md-block">
                                <button class="btn btn-primary" type="submit"><span class="fa-solid fa-magnifying-glass me-2"></span>Buscar</button>
                                <a class="btn btn-phoenix-primary" href="nuevoGestionAusentismo"><span class="fas fa-plus me-2"></span>Agregar</a>
                                <button class="btn btn-link text-900 me-4 px-0 ps-3" type="button"><span class="fa-solid fa-file-export me-2"></span>Exportar Xls</button>
                            </div>
                        </form>
                     </div>
                </div>
            </div>

                        <div class="mt-4 mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1">
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
                                                style="width:5%;">ID
                                            </th>
                                            <th class="sort align-middle text-center ps-5" scope="col"
                                                data-sort="date">DOC
                                            </th>
                                            <th class="sort align-middle text-center ps-8 pe-4" scope="col"
                                                data-sort="date">
                                                NOMBRES y APELLIDOS
                                            </th>
                                            <th class="sort align-middle text-center ps-5" scope="col"
                                                data-sort="date">ESTADO
                                            </th>
                                            <th class="sort align-middle text-center ps-5" scope="col"
                                                data-sort="date">
                                                F.INGRESO
                                            </th>
                                            <th class="sort align-middle text-center ps-5 pe-5" scope="col"
                                                data-sort="date">
                                                TIPO VACA.
                                            </th>
                                            <th class="sort align-middle text-center pe-3" scope="col"
                                                data-sort="date">FECINI
                                            </th>
                                            <th class="sort align-middle text-center pe-3" scope="col"
                                                data-sort="date">FECFIN
                                            </th>
                                            <th class="sort align-middle text-center pe-0" scope="col"
                                                data-sort="date">DIAS
                                            </th>
                                            <th class="sort align-middle text-center pe-0" scope="col"></th>
                                        </tr>
                                        </thead>
                                        <tbody class="list" id="order-table-body">
                                        <c:forEach var="LstAusentismoView"
                                                   items="${requestScope.LstAusentismoView}">
                                            <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                                <td class="fs--1 align-middle px-0 py-3">
                                                    <div class="form-check mb-0 fs-0">
                                                        <input class="form-check-input" type="checkbox"
                                                               data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}'/>
                                                    </div>
                                                </td>
                                                <td class="order align-middle white-space-nowrap py-0"><a
                                                        class="fw-semi-bold"
                                                        href="editarGestionAusentismo@${LstAusentismoView.iexcodtra}@${LstAusentismoView.iexcorrel}">#${LstAusentismoView.iexcorrel}</a>
                                                </td>
                                                <td class="total align-middle text-center fw-semi-bold text-1000">
                                                        ${LstAusentismoView.nrodoc}</td>
                                                <td class="total align-middle text-center fw-semi-bold text-1000">
                                                        ${LstAusentismoView.desnomtra}</td>
                                                <c:if test="${LstAusentismoView.desestado=='activo'}">
                                                    <td
                                                            class="payment_status align-middle white-space-nowrap text-center fw-bold text-700">
                                          <span class="badge badge-phoenix fs--2 badge-phoenix-success"><span
                                                  class="badge-label">Activo</span><span class="ms-1"
                                                                                         style="height:12.8px;width:12.8px;"></span></span>
                                                    </td>
                                                </c:if>
                                                <c:if test="${LstAusentismoView.desestado=='inactivo'}">
                                                    <td
                                                            class="payment_status align-middle white-space-nowrap text-center fw-bold text-700">
                                          <span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span
                                                  class="badge-label">Inactivo</span><span class="ms-1"
                                                                                           style="height:12.8px;width:12.8px;"></span></span>
                                                    </td>
                                                </c:if>
                                                <td
                                                        class=" fulfilment_status align-middle white-space-nowrap text-center fw-bold text-700">
                                                        ${LstAusentismoView.fecing}</td>
                                                <td class="delivery_type align-middle white-space-nowrap text-center  fs--2 text-start">
                                                  <span class="badge badge-phoenix fs--2 badge-phoenix-info"
                                                 class="badge-label">${LstAusentismoView.destipaus}</span>
                                                </td>


                                                <td
                                                        class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">
                                                        ${LstAusentismoView.iexfecini}</td>
                                                <td
                                                        class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start ps-3">
                                                        ${LstAusentismoView.fecfinrep}</td>
                                                <td
                                                        class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start ps-4">
                                                        ${LstAusentismoView.iexnrodias}</td>

                                                <td class="align-middle text-end white-space-nowrap pe-0 action">
                                                    <div class="font-sans-serif btn-reveal-trigger position-static">
                                                        <button
                                                                class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2"
                                                                type="button" data-bs-toggle="dropdown"
                                                                data-boundary="window"
                                                                aria-haspopup="true" aria-expanded="false"
                                                                data-bs-reference="parent"><span
                                                                class="fas fa-ellipsis-h fs--2"></span></button>
                                                        <div class="dropdown-menu dropdown-menu-end py-2">
                                                            <a class="dropdown-item" href="editarGestionAusentismo@${LstAusentismoView.iexcodtra}@${LstAusentismoView.iexcorrel}">Editar</a>
                                                            <div class="dropdown-divider"></div>
                                                            <a class="dropdown-item text-danger" onclick="return remove();"
                                                               href="eliminarAusentismo@${LstAusentismoView.iexcodtra}@${LstAusentismoView.iexcorrel}">Eliminar</a>
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
                            <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                Se grabó exitosamente los cambios.
                            </div>
                            <div class="modal fade" id="confirmModal" tabindex="-1">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content border">
                                        <form id="addEventForm" autocomplete="off">
                                            <div class="modal-header border-200 p-4">
                                                <h5 class="modal-title text-1000 fs-4 lh-sm">Confirmar</h5>
                                                <button class="btn p-1 text-900" type="button" data-bs-dismiss="modal"
                                                        aria-label="Close"><span class="fas fa-times fs--1"></span>
                                                </button>
                                            </div>
                                            <div class="modal-body pt-4 pb-2 px-4">
                                                <div class="mb-3">
                                                    <label class="fw-bold mb-2 text-1000" for="leadStatus">Esta seguro
                                                        que desea
                                                        confirmar la operacion?</label>
                                                </div>
                                            </div>
                                        </form>
                                        <div
                                                class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
                                            <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="button"
                                                    data-bs-dismiss="modal">Cancel
                                            </button>
                                            <button class="btn btn-sm btn-primary px-9 fs--2 my-0"
                                                    onclick="mostrarAlert();"
                                                    type="submit" data-bs-dismiss="modal">Confirmar
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

    <jsp:include page="../../../demoWidget.jsp"></jsp:include>

</main>
<!-- ===============================================-->
<!--    End of Main Content-->
<!-- ===============================================-->

<jsp:include page="../../../customize.jsp"></jsp:include>
</body>

</html>