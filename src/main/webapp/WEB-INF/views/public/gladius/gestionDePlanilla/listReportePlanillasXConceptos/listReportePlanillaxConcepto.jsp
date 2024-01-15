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
                    var opt = "<option value='' > -- Selecciona -- </option>";
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

            <div class="row g-5">
                <div class="col-xl-8">
                    <div class="row gx-3 gy-4">
                        <form class="row g-4 mb-0 needs-validation" method="POST" action="" novalidate>
                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}"/>

                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Periodo Inicio</label>
                                <input class="form-control" type="text" name="perini" id="perini" value="${requestScope.xperini}" placeholder="2023" required>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Periodo Fin</label>
                                <input class="form-control" type="text" name="perfin"  id="perfin" value="${requestScope.xperfin}" placeholder="2024" required>
                            </div>
                            <div class="col-sm-6 col-md-6">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Codigo de Concepto</label>
                                <select class="form-select" name="codcon"  id="codcon" required>
                                    <option value="" selected>Seleccionar</option>
                                    <c:forEach var = "lstConcepto" items = "${requestScope.lstConcepto}">
                                        <option  value="${lstConcepto.codConcepto}">  ${lstConcepto.codConcepto} - ${lstConcepto.desVariable} -  ${lstConcepto.desConcepto}   </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="d-grid gap-2 d-md-block">
                                <button class="btn btn-primary" type="submit"><span class="fa-solid fa-plus me-2"></span>Agregar</button>
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
                                        data-sort="date">DESCRIPCION
                                    </th>
                                    <th class="sort align-middle text-center ps-8 pe-4" scope="col"
                                        data-sort="date">
                                        ELIMINAR
                                    </th>

                                </tr>
                                </thead>
                                <tbody class="list" id="order-table-body">
                                <c:if test="${not empty requestScope.listacon}">
                                <c:forEach var="listacon"
                                           items="${requestScope.listacon}">
                                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                        <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                                <input class="form-check-input" type="checkbox"
                                                       data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}'/>
                                            </div>
                                        </td>
                                        <td class="order align-middle white-space-nowrap py-0">
                                                ${listacon.codConcepto}
                                        </td>
                                        <td class="total align-middle text-center fw-semi-bold text-1000">
                                                ${listacon.desConcepto}</td>
                                        <td class="total align-middle text-center fw-semi-bold text-1000">
                                            <a href="${pageContext.request.contextPath}/GestionReportes?accion=PLACONDEL&codcon=${listacon.codConcepto}"  >Eliminar </a></td>
                                    </tr>
                                </c:forEach>
                                </c:if>
                                </tbody>
                            </table>

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

                            <div class="form-group row">
                                <label class="control-label col-md-3 col-sm-3 "><h4 class="date text-info"></h4></label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="button"  onclick="enviaForm('1')" name="GeneraReporte"  value="Genera Reporte" class="btn btn-primary dropdown-toggle" >
                                    <label style="width: 120px"></label><input type="button"  onclick="enviaForm('2')" name="GeneraAllReport"  value="Descargar" class="btn btn-primary dropdown-toggle" >
                                </div>
                            </div>
                            <br>
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
                                        style="width:5%;">NREPOR
                                    </th>
                                    <th class="sort align-middle text-center ps-5" scope="col"
                                        data-sort="date">CODPRO
                                    </th>
                                    <th class="sort align-middle text-center ps-8 pe-4" scope="col"
                                        data-sort="date">
                                        DESPRO
                                    </th>
                                    <th class="sort align-middle text-center ps-8 pe-4" scope="col"
                                        data-sort="date">
                                        CODCON
                                    </th>
                                    <th class="sort align-middle text-center ps-8 pe-4" scope="col"
                                        data-sort="date">
                                        DESCON
                                    </th>
                                    <th class="sort align-middle text-center ps-8 pe-4" scope="col"
                                        data-sort="date">
                                        VECES UTILIZADOS
                                    </th>
                                    <th class="sort align-middle text-center ps-8 pe-4" scope="col"
                                        data-sort="date">
                                        TOTAL
                                    </th>
                                    <th class="sort align-middle text-center ps-8 pe-4" scope="col"
                                        data-sort="date">
                                        VER
                                    </th>


                                </tr>
                                </thead>
                                <tbody class="list" id="order-table-body">
                                    <c:forEach var="lsthistConcepto"
                                               items="${requestScope.lsthistConcepto}">
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                            <td class="fs--1 align-middle px-0 py-3">
                                                <div class="form-check mb-0 fs-0">
                                                    <input class="form-check-input" type="checkbox"
                                                           data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}'/>
                                                </div>
                                            </td>
                                            <td class="order align-middle white-space-nowrap py-0">
                                                    ${lsthistConcepto.iexnroper}
                                            </td>
                                            <td class="total align-middle text-center fw-semi-bold text-1000">
                                                    ${lsthistConcepto.procodpro}</td>
                                            <td class="total align-middle text-center fw-semi-bold text-1000">
                                                    ${lsthistConcepto.despro}</td>
                                            <td class="total align-middle text-center fw-semi-bold text-1000">
                                                    ${lsthistConcepto.procodcon}</td>
                                            <td class="total align-middle text-center fw-semi-bold text-1000">
                                                    ${lsthistConcepto.coodescon}</td>
                                            <td class="total align-middle text-center fw-semi-bold text-1000">
                                                    ${lsthistConcepto.cantidad}</td>
                                            <td class="total align-middle text-center fw-semi-bold text-1000">
                                                    ${lsthistConcepto.provalor}</td>
                                            <td class="total align-middle text-center fw-semi-bold text-1000">
                                                <a href="${pageContext.request.contextPath}/GestionReportes?accion=PLACONGENREPDETXLS&codpro=${lsthistConcepto.procodpro}&nroper=${lsthistConcepto.iexnroper}&codcon=${lsthistConcepto.procodcon}" >Ver </a></td>

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

    <jsp:include page="../../../demoWidget.jsp"></jsp:include>

</main>
<!-- ===============================================-->
<!--    End of Main Content-->
<!-- ===============================================-->

<jsp:include page="../../../customize.jsp"></jsp:include>
</body>

</html>