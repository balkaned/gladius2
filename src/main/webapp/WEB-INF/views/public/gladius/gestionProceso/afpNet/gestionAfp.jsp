<%-- Created on : 15/06/2023, 12:20:00 PM Author : Jan Quiroz Email : janquirozs@gmail.com --%>
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
        var div = document.getElementById('alert');
        div.style.display = '';

        setTimeout(function () {
            $("#alerts").hide(6000);
        }, 3000);
    }

    function SendAfpFile(tipo, flg) {

        var permes = $("#permes").val();
        console.log("permes: "+permes);

        if(permes == ''){
            return;
        }

        if (flg == 2) {
            document.getElementById("accion").value = "gestionAfp";

            $('#modalLoading').modal('show');
                setTimeout(function() {
                      document.getElementById("frmafpnetfile").submit();
            }, 5000);
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

    /*document.addEventListener('DOMContentLoaded', function () {
        var procesarBtn = document.getElementById('descargaBtn');
        var permesInput = document.getElementById('permes');

        procesarBtn.addEventListener('click', function (event) {
            var permes = permesInput.value.trim();
            if (permes === '') {
                alert('Por favor, ingrese el Periodo Mensual YYYYMM antes de descargar.');
                event.preventDefault();
            }
        });
    });*/
</script>

<body>
<!-- ===============================================-->
<!--    Main Content-->
<!-- ===============================================-->
<main class="main" id="top">
    <jsp:include page="../../../navsMenu.jsp"></jsp:include>
    <jsp:include page="../../../navTop.jsp"></jsp:include>
    <jsp:include page="../../../modalFade.jsp"></jsp:include>

    <div class="content bg-100">
        <nav class="mb-2" aria-label="breadcrumb">
            <ol class="breadcrumb mb-0">
                <li class="breadcrumb-item"><a href="#!">Gestión de Proceso Exernos</a></li>
                <li class="breadcrumb-item active">Afp Net</li>
            </ol>
        </nav>
        <div class="mb-9">
            <div class="row g-3 mb-4">
                <div class="col-auto">
                    <h2 id="h2top" class="mb-0">Reporte afp net</h2>
                </div>
            </div>

            <form class="row g-3 mb-0 needs-validation"  method="POST" action="" name="frmafpnetfile"  id="frmafpnetfile" novalidate>
                <input type="hidden" name="file" id="file" >
                <input type="hidden" name="accion" id="accion"  value="" >
                <div>
                    <div class="table-responsive scrollbar mx-n1 px-1">
                        <table class="table table-hover">
                            <span class="badge badge-tag me-2 mb-2">Generar archivos afp Net</span>
                            <tbody>
                                <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                    <td class="align-middle fw-semi-bold text-20">Periodo mensual</td>
                                    <td>
                                        <div class="col-sm-6 col-md-3">
                                            <input type="text" name="permes"  id="permes"  value="${requestScope.permes}" class="form-control" placeholder="YYYYMM" >
                                        </div>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="align-middle fw-semi-bold text-20">Generar afp net</td>
                                    <td class="align-middle fw-semi-bold text-20">
                                        <a class="btn btn-phoenix-secondary btn-sm" href="AWSorFTP_flgsource@verReporteExcel@${idComp}@null@null@null@Afpnet@1UP_PERMES=${P_PERMES}@null@null@null" id="descargaBtn"
                                              onclick="SendAfpFile('REP','1')"><span class="fa-solid fa-download me-2"></span>Descargar
                                        </a>
                                    </td>
                                    <td class="align-middle fw-semi-bold text-20">
                                        <a class="btn btn-phoenix-secondary btn-sm" href="#" id="procesarBtn" onclick="SendAfpFile('gestionAfp','2')"><span class="fa-solid fa-diagram-predecessor me-2"></span>Procesar
                                        </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
        <jsp:include page="../../../footer.jsp"></jsp:include>
    </div>

    <jsp:include page="../../../demoWidget.jsp"></jsp:include>

</main>
<!-- ===============================================-->
<!--    End of Main Content-->
<!-- ===============================================-->

<jsp:include page="../../../customize.jsp"></jsp:include>
</body>

<div id="modalLoading" class="modal fade" tabindex="-1" data-bs-backdrop="static" aria-labelledby="scrollingLongModalLabel2" aria-hidden="true" >
  <div class="modal-dialog modal-dialog-centered">
	  <div class="modal-content bg-100 rounded-2 border border-300">
		<form class="needs-validation" method="POST" action="" novalidate >
			<div class="modal-header border-bottom border-300 bg-300 bg-opacity-25 p-4">
			   <h5 id="h5modalLoadinglabel" class="modal-title text-1000 fs-2 lh-sm">Procesando datos Afpnet</h5>
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

</html>