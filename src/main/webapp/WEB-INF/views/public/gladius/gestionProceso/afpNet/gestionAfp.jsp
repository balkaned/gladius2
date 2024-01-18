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

    function SendAfpFile(tipo, flg) {
        if (flg == 2) {
            document.getElementById("accion").value = "gestionAfp";
            document.getElementById("frmafpnetfile").submit();

        } else if (flg == 1) {
            document.getElementById("accion").value = "REP";
            document.getElementById("frmafpnetfile").submit();

        }

    }
    document.addEventListener('DOMContentLoaded', function () {
        var procesarBtn = document.getElementById('procesarBtn');
        var permesInput = document.getElementById('permes');

        procesarBtn.addEventListener('click', function (event) {
            var permes = permesInput.value.trim();
            if (permes === '') {
                alert('Por favor, ingrese el Periodo Mensual YYYYMM antes de procesar.');
                event.preventDefault();
            }
        });
    });

    document.addEventListener('DOMContentLoaded', function () {
        var procesarBtn = document.getElementById('descargaBtn');
        var permesInput = document.getElementById('permes');

        procesarBtn.addEventListener('click', function (event) {
            var permes = permesInput.value.trim();
            if (permes === '') {
                alert('Por favor, ingrese el Periodo Mensual YYYYMM antes de descargar.');
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
                <li class="breadcrumb-item"><a href="#!">Gestión de Proceso Exernos</a></li>
                <li class="breadcrumb-item active">Afp Net</li>
            </ol>
        </nav>
        <div class="mb-9">
            <div class="row g-3 mb-4">
                <div class="col-auto">
                    <h2 id="h2top" class="mb-0">Reporte Afp Net</h2>
                </div>
            </div>

            <form class="row g-4 mb-0 needs-validation"  method="POST" action="" name="frmafpnetfile"  id="frmafpnetfile" novalidate>
                <input type="hidden" name="file" id="file" >
                <input type="hidden" name="accion" id="accion"  value="" >
                <div>
                    <div class="table-responsive scrollbar mx-n1 px-1">
                        <table class="table table-hover">
                            <span class="badge badge-tag me-2 mb-2">Generar Archivos Afp Net</span>
                            <tbody>
                                <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                    <td class="align-middle fw-semi-bold text-20">Periodo Mensual YYYYMM</td>
                                    <td>
                                        <div class="col-sm-6 col-md-4">
                                            <input type="text" name="permes"  id="permes"  value="${requestScope.permes}"   class="form-control" placeholder="YYYYMM" >
                                        </div>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="align-middle fw-semi-bold text-20">Generar Afp Net</td>
                                    <td class="align-middle fw-semi-bold text-20">
                                        <a class="btn btn-phoenix-secondary btn-sm" href="AWSorFTP_flgsource@verReporteExcel@${idComp}@null@null@null@Afpnet@1PP_PERMES=${P_PERMES}@null@null@null" id="descargaBtn"
                                              onclick="SendAfpFile('REP','1')"><span class="fa-solid fa-download me-2"></span>Descargar
                                        </a>
                                    </td>
                                    <td class="align-middle fw-semi-bold text-20">
                                        <a class="btn btn-phoenix-secondary btn-sm" href="#" id="procesarBtn" onclick="SendAfpFile('gestionAfp','2')"><span class="fa-solid fa-wrench me-2"></span>Procesar
                                        </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
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