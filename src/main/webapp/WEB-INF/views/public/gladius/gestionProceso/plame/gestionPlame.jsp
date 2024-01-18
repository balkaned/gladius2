<%-- Created on : 15/06/2023, 12:20:00 PM Author : Jean Quiroz Email : jeanp.quiroz@gmail.com --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %>
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

    function SendSunatFile(tipo, flg) {
        var accionField = document.getElementById("accion");
        var fileField = document.getElementById("file");

        if (accionField && fileField) {
            accionField.value = tipo;
            fileField.value = flg;
            document.getElementById("frmsunatfile").submit();
        } else {
            console.error("Error: No se encontraron los elementos HTML necesarios.");
        }
    }

    document.addEventListener('DOMContentLoaded', function () {
        var procesarBtn = document.getElementById('actualizarBtn');
        var actualizarBtn2 = document.getElementById('actualizarBtn2');
        var actualizarBtn3 = document.getElementById('actualizarBtn3');
        var actualizarBtn4 = document.getElementById('actualizarBtn4');
        var permesInput = document.getElementById('permes');

        function validarPermes(event) {
            var permes = permesInput.value.trim();
            if (permes === '') {
                alert('Por favor, ingrese el Periodo Mensual YYYYMM antes de actualizar.');
                event.preventDefault();
            }
        }

        procesarBtn.addEventListener('click', validarPermes);
        actualizarBtn2.addEventListener('click', validarPermes);
        actualizarBtn3.addEventListener('click', validarPermes);
        actualizarBtn4.addEventListener('click', validarPermes);
    });

    document.addEventListener('DOMContentLoaded', function () {
        var descargarBtn = document.getElementById('descargarBtn');
        var descargarBtn2 = document.getElementById('descargarBtn2');
        var descargarBtn3 = document.getElementById('descargarBtn3');
        var descargarBtn4 = document.getElementById('descargarBtn4');
        var permesInput = document.getElementById('permes');

        function validarPermes(event) {
            var permes = permesInput.value.trim();
            if (permes === '') {
                alert('Por favor, ingrese el Periodo Mensual YYYYMM antes de actualizar.');
                event.preventDefault();
            }
        }

        descargarBtn.addEventListener('click', validarPermes);
        descargarBtn2.addEventListener('click', validarPermes);
        descargarBtn3.addEventListener('click', validarPermes);
        descargarBtn4.addEventListener('click', validarPermes);
    });

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
                <li class="breadcrumb-item"><a href="#!">Gestión de Procesos Externos</a></li>
                <li class="breadcrumb-item active">Plame</li>
            </ol>
        </nav>
        <div class="mb-9">
            <div class="row g-3 mb-4">
                <div class="col-auto">
                    <h2 id="h2top" class="mb-0">Reporte Plame</h2>
                </div>
            </div>

            <form class="row g-4 mb-0 needs-validation" method="POST" action="" name="frmsunatfile" id="frmsunatfile" novalidate>
                <input type="hidden" name="file" id="file">
                <input type="hidden" name="accion" id="accion" value="">
                <div class="table-responsive scrollbar mx-n1 px-1">
                    <table class="table table-hover">
                        <span class="badge badge-tag me-2 mb-1 mt-3">Generar archivos Plame</span>
                        <tbody>
                            <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                <td class="align-middle fw-semi-bold text-20">Periodo Mensual YYYYMM</td>
                                <td>
                                    <div class="col-sm-6 col-md-4">
                                        <input type="text" name="permes" id="permes"  value="${requestScope.permes}" class="form-control" placeholder="YYYYMM" required/>
                                    </div>
                                </td>
                                <td></td>
                            </tr>
                            <tr class="md-10">
                                <td class="align-middle fw-semi-bold text-20">Jornada Laboral[14] .jor</td>
                                <td class="align-middle fw-semi-bold text-20">
                                    <a class="btn btn-phoenix-secondary btn-sm" href="expPlameFile?permes=${requestScope.permes}&file=14"
                                        id="descargarBtn"
                                        onclick=" SendSunatFile('REP','14')"><span class="fa-solid fa-download fs--1 me-2"></span>Descargar
                                    </a>
                                </td>
                                <td class="align-middle fw-semi-bold text-20">
                                    <a class="btn btn-phoenix-secondary btn-sm" href="#" id="actualizarBtn"
                                    onclick=" SendSunatFile('gestionPlame','14')"><span class="fa-solid fa-rotate fs--1 me-2"></span>Actualizar</a>
                                </td>
                            </tr>
                            <tr>
                                <td class="align-middle fw-semi-bold text-20">Dias No Laborados [15]</td>
                                <td class="align-middle fw-semi-bold text-20">
                                    <a class="btn btn-phoenix-secondary btn-sm" href="expPlameFile?permes=${requestScope.permes}&file=15" id="descargarBtn2"
                                        onclick="SendSunatFile('REP','15')"><span class="fa-solid fa-download fs--1 me-2"></span>Descargar
                                    </a>
                                </td>
                                <td class="align-middle fw-semi-bold text-20">
                                    <a class="btn btn-phoenix-secondary btn-sm" href="#" id="actualizarBtn2" onclick="SendSunatFile('gestionPlame','15')"><span class="fa-solid fa-rotate fs--1 me-2"></span>Actualizar</a>
                                </td>
                            </tr>
                            <tr>
                                <td class="align-middle fw-semi-bold text-20">Detalle de Ingreso, Descuento[18]</td>
                                <td class="align-middle fw-semi-bold text-20">
                                    <a class="btn btn-phoenix-secondary btn-sm" href="expPlameFile?permes=${requestScope.permes}&file=18" id="descargarBtn3"
                                        onclick="SendSunatFile('REP','18')"><span class="fa-solid fa-download fs--1 me-2"></span>Descargar
                                    </a>
                                </td>
                                <td class="align-middle fw-semi-bold text-20">
                                    <a class="btn btn-phoenix-secondary btn-sm" href="#" id="actualizarBtn3" onclick="SendSunatFile('gestionPlame','18')"><span class="fa-solid fa-rotate fs--1 me-2"></span>Actualizar</a>
                                </td>
                            </tr>
                            <tr>
                                <td class="align-middle fw-semi-bold text-20">Otras condiciones [26]</td>
                                <td class="align-middle fw-semi-bold text-20">
                                    <a class="btn btn-phoenix-secondary btn-sm" href="expPlameFile?permes=${requestScope.permes}&file=26" id="descargarBtn4"
                                        onclick="SendSunatFile('REP','26')"><span class="fa-solid fa-download fs--1 me-2"></span>Descargar</a>
                                </td>
                                <td class="align-middle fw-semi-bold text-20">
                                    <a class="btn btn-phoenix-secondary btn-sm" href="#" id="actualizarBtn4" onclick="SendSunatFile('gestionPlame','26')"><span class="fa-solid fa-rotate fs--1 me-2"></span>Actualizar</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
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

</html>