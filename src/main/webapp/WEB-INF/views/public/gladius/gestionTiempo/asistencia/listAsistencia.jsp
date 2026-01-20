<%-- Created: 2026-01-14 - Auto-generated to restore missing attendance list view --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">

<head>
    <jsp:include page="../../../links.jsp"></jsp:include>
</head>

<script>
    // Show alert when redirected with ?saved=1
    document.addEventListener('DOMContentLoaded', function () {
        const params = new URLSearchParams(window.location.search);
        if (params.get('saved') === '1') {
            const div = document.getElementById('alert');
            if (div) { div.style.display = ''; setTimeout(() => { $("#alert").hide(6000); }, 3000); }
        }
    });

    function consultaDet() {
        document.getElementById("accion").value = "listAsistencia";
    }

    function remove() {
        var opcion = confirm("Esta seguro de Eliminar el Registro?");
        return opcion == true;
    }
</script>

<body>
<main class="main" id="top">
    <jsp:include page="../../../navsMenu.jsp"></jsp:include>
    <jsp:include page="../../../navTop.jsp"></jsp:include>
    <jsp:include page="../../../modalFade.jsp"></jsp:include>

    <div class="content bg-100">
        <nav class="mb-2" aria-label="breadcrumb">
            <ol class="breadcrumb mb-0">
                <li class="breadcrumb-item"><a href="#!">Gestión de tiempos</a></li>
                <li class="breadcrumb-item active">Asistencias</li>
            </ol>
        </nav>
        <div class="mb-9">
            <div class="row g-3 mb-2">
                <div class="col-auto">
                    <h2 id="h2top" class="mb-0">Asistencias</h2>
                </div>
            </div>

            <div class="row g-3">
                <div class="col-xl-7">
                    <div class="row gx-3 gy-4">
                        <form class="row g-3 mb-0 needs-validation" method="GET" action="listAsistencia" novalidate>
                            <div class="col-sm-6 col-md-4">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha inicio</label>
                                <span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                <input class="form-control datetimepicker" name="fecini" id="fecini" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' />
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha fin</label>
                                <span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                <input class="form-control datetimepicker" name="fecfin" id="fecfin" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' />
                            </div>
                            <div class="">
                                <button class="btn btn-primary btn-sm" type="submit"><span class="fa-solid fa-magnifying-glass me-2"></span>Buscar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div id="alert" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important; margin-top:12px;">
                <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios</p>
                <a class="text-success fs-0 fw-bold" href="#" data-bs-dismiss="alert" aria-label="Close">x</a>
            </div>

            <div class="mt-4 mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1">
                <div class="table-responsive scrollbar mx-n1 px-1">
                    <table class="table table-sm fs--1 mb-0">
                        <thead>
                            <tr>
                                <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;"></th>
                                <th class="sort white-space-nowrap align-middle pe-3" scope="col" style="width:5%;">ID</th>
                                <th class="sort align-middle text-center ps-5" scope="col">NOMBRES y APELLIDOS</th>
                                <th class="sort align-middle text-center ps-5" scope="col">F. INGRESO</th>
                                <th class="sort align-middle text-center ps-5" scope="col">ACCIONES</th>
                            </tr>
                        </thead>
                        <tbody class="list" id="order-table-body">
                            <c:forEach var="empl" items="${requestScope.empleadoList}">
                                <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                    <td class="fs--1 align-middle px-0 py-3"></td>
                                    <td class="id align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="detalleEmpl@${empl.iexcodtra}">#${empl.iexcodtra}</a></td>
                                    <td class="nombreyapp align-middle white-space-nowrap ps-8">
                                        <a class="d-flex align-items-center" href="detalleEmpl@${empl.iexcodtra}">
                                            <div class="avatar avatar-m">
                                                <div class="avatar-name rounded-circle"><span>${empl.iexapepat.substring(0,1)}</span></div>
                                            </div>
                                            <h6 class="mb-0 ms-3 text-900">${empl.iexapepat} ${empl.iexapemat} ${empl.iexnomtra}</h6>
                                        </a>
                                    </td>
                                    <td class="fecini align-middle white-space-nowrap fs-9 ps-4 text-start">${empl.iexfecing}</td>
                                    <td class="align-middle text-end white-space-nowrap pe-0 action">
                                        <div class="font-sans-serif btn-reveal-trigger position-static">
                                            <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button" data-bs-toggle="dropdown" data-boundary="window">
                                                <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span>
                                            </button>
                                            <div class="dropdown-menu dropdown-menu-end py-2">
                                                <a id="dropdownmenutable" class="dropdown-item" href="gestionTiempo@${empl.iexcodtra}"><span class="fa-solid fa-eye me-2"></span>Ver detalles</a>
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
                        <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p>
                    </div>
                    <div class="col-auto d-flex">
                        <button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                        <ul class="mb-0 pagination"></ul>
                        <button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span></button>
                    </div>
                </div>
            </div>

        </div>
        <jsp:include page="../../../footer.jsp"></jsp:include>
    </div>

    <jsp:include page="../../../demoWidget.jsp"></jsp:include>

</main>

<jsp:include page="../../../customize.jsp"></jsp:include>
</body>

</html>