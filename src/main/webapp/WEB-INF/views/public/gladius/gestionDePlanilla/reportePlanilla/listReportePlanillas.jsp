<%-- Created on : 15/06/2023, 12:20:00 PM Author : Jean Quiroz Email : jeanp.quiroz@gmail.com --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page trimDirectiveWhitespaces="true" %>
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

    $(document).ready(function () {
        /* attach a submit handler to the form */
        $('#submit1').click(function (event) {
            $.ajaxSetup({cache: false});
            $.ajax({
                url: "lisReportePdf",
                data: {
                    "accion": "lisReportePdf",
                    "iexcodpro": $("#iexcodpro").val(),
                    "nroper": $("#nroper").val(),
                    "nroper2": $("#nroper2").val()
                },
                success: function (data) {
                    $("#idresult").html(data);
                }
            });

        });
    });

    document.addEventListener('DOMContentLoaded', function () {
        var submitBtn = document.getElementById('submit1');
        var nroperInput = document.getElementById('nroper');
        var nroper2Input = document.getElementById('nroper2');

        submitBtn.addEventListener('click', function (event) {
            var permes = nroperInput.value.trim();
            var permes2 = nroper2Input.value.trim();

            if (permes === '' || permes2 === '') {
                alert('Por favor, ingrese ambos Periodo Inicio y Periodo Fin YYYY antes de ver reporte.');
                event.preventDefault();
            }
        });
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
                <li class="breadcrumb-item"><a href="#!">Gestión de planillas</a></li>
                <li class="breadcrumb-item active">Reporte de planillas</li>
            </ol>
        </nav>
        <div class="mb-9">
            <div class="row g-3 mb-4">
                <div class="col-auto">
                    <h2 id="h2top" class="mb-0">Reporte de planillas</h2>
                </div>
            </div>

            <div class="row g-5">
                <div class="col-xl-8">
                    <div class="row gx-3 gy-4">
                        <form class="row g-4 mb-0 needs-validation" method="POST" action="" novalidate>
                            <div class="col-sm-6 col-md-6">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Proceso</label>
                                <select class="form-select" name="iexcodpro" id="iexcodpro"  required>
                                    <option value="" selected>Seleccionar</option>
                                    <c:forEach var="lovProcesos" items="${requestScope.lovProcesos}"
                                               varStatus="loopCounter">
                                        <option value="${lovProcesos.idProceso}" ${lovProcesos.idProceso==requestScope.iexcodpro
                                                ? 'selected' : '' }>${lovProcesos.desProceso}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Periodo Inicio</label>
                                <input class="form-control" type="text" id="nroper" name="nroper" placeholder="YYYY"
                                       required>
                            </div>

                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Periodo Fin</label>
                                <input class="form-control" type="text" id="nroper2" name="nroper2" placeholder="YYYY"
                                       required>
                            </div>

                            <div class="">
                                <a id="submit1" href="#" i-02d="procesarBtn" class="btn btn-primary btn-sm"><span
                                        class="fa-solid fa-eye me-2"></span>Traer embedded-reporting</a>
                                <INPUT TYPE="HIDDEN" NAME="ReportName" Value="DynamicTableExample.rpttemplate">
                                <INPUT TYPE="HIDDEN" NAME="accion" Value="lisReportePdf">
                            </div>
                        </form>

                        <div id="idresult" class="col-12 bg-200 border border-200 rounded-1" style="height:600px; overflow: scroll;"></div>
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