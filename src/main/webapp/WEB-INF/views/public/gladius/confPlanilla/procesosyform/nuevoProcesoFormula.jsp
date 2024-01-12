<%--
  Created by IntelliJ IDEA.
  User: space
  Date: 29/12/23
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
<head>
	<jsp:include page="../../../links.jsp"/>
	<title>Gladius</title>
</head>
<script>
    function mostrarAlertSuccess() {
        let div = document.getElementById('alert');
        div.style.display = '';

        setTimeout(function () {
            $("#alerts").hide(6000);
        }, 3000);
    }

    function mostrarAlertDanger() {
        let div = document.getElementById('incomplete');
        div.style.display = '';

        setTimeout(function () {
            $("#alerts").hide(6000);
        }, 3000);
    }

    function validarCampos() {
        let desProcesos = document.getElementById('desProcesos').value;
        let desCortoProcesos = document.getElementById('desCortoProcesos').value;
        let lov_grpplanilla = document.getElementById('lov_grpplanilla').value;
        let lov_regimenlab = document.getElementById('lov_regimenlab').value;
        let reporteBoleta = document.getElementById('reporteBoleta').value;
        let reporteIndividual = document.getElementById('reporteIndividual').value;
        let reporteResumen = document.getElementById('reporteResumen').value;
        let tipoProceso = document.getElementById('tipoProceso').value;

        if (desProcesos === '' || desCortoProcesos === '' || lov_grpplanilla === '' || lov_regimenlab === '' ||
            reporteBoleta === '' || reporteIndividual === '' || reporteResumen === '' || tipoProceso === '') {
            mostrarAlertDanger();
        } else {
            mostrarAlertSuccess();
        }
    }
</script>
<body>
<main class="main" id="top">
	<jsp:include page="../../../navsMenu.jsp"/>
	<jsp:include page="../../../navTop.jsp"/>
	<jsp:include page="../../../modalFade.jsp"/>

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
					<h2 id="h2top" class="mb-0">Insertar proceso y fórmula</h2>
				</div>
			</div>

			<div class="row g-5">
				<div class="col-xl-7">
					<div class="row gx-3 gy-4">
						<form class="row g-4 mb-0 needs-validation" method="POST" action="addProcesoFormula" novalidate>
							<div class="col-sm-6 col-md-6">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="desProcesos">* Descripción de
									Procesos</label>
								<input class="form-control" id="desProcesos" name="desProcesos" type="text" value="" placeholder="" required/>
							</div>

							<div class="col-sm-6 col-md-6">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="desCortoProcesos">Descripción Corto de
									Procesos</label>
								<input class="form-control" id="desCortoProcesos" name="desCortoProcesos" type="text" value=""
											 placeholder=""/>
							</div>

							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="lov_grpplanilla">Grupo de
									Planilla</label>
								<select id="lov_grpplanilla" name="lov_grpplanilla" class="form-select">
										<option value="">Seleccionar</option>
										<c:forEach var="Lovs_grpplanilla" items="${Lovs_grpplanilla}">
											<option value="${Lovs_grpplanilla.idLov}">${Lovs_grpplanilla.desLov}</option>
										</c:forEach>
									</select>
							</div>

							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="lov_regimenlab">Regimen Laboral</label>
								<select id="lov_regimenlab" name="lov_regimenlab" class="form-select">
										<option value="">Seleccionar</option>
										<c:forEach var="Lovs_reglaboral" items="${Lovs_reglaboral}">
											<option value="${Lovs_reglaboral.idLov}">${Lovs_reglaboral.desLov}</option>
										</c:forEach>
									</select>
							</div>

							<div class="col-sm-6 col-md-6">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="reporteBoleta">
									Reporte de Boleta
								</label>
								<input class="form-control" id="reporteBoleta" name="reporteBoleta" type="text" value=""
											 placeholder=""/>
							</div>

							<div class="col-sm-6 col-md-6">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="reporteIndividual">
									Reporte Individual
								</label>
								<input class="form-control" id="reporteIndividual" name="reporteIndividual" type="text" value=""
											 placeholder=""/>
							</div>

							<div class="col-sm-6 col-md-6">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="reporteResumen">
									Reporte Resumen
								</label>
								<input class="form-control" id="reporteResumen" name="reporteResumen" type="text" value=""
											 placeholder=""/>
							</div>

							<div class="col-sm-6 col-md-6">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="tipoProceso">
									Tipo de Proceso
								</label>
								<input class="form-control" id="tipoProceso" name="tipoProceso" type="text" value=""
											 placeholder=""/>
							</div>

							<div class="alert alert-success" role="alert" id="alert" style="display:none;">
								Se grabó exitosamente los cambios.
							</div>

							<div class="alert alert-danger" role="alert" id="incomplete" style="display:none;">
								Por favor, complete todos los campos requeridos.
							</div>

							<div class="col-12 gy-6">
								<div class="row g-3 justify-content-end">
									<div class="col-auto">
										<a class="btn btn-phoenix-primary px-5" href="listProcesoFormulas">Cancel</a>
									</div>
									<div class="col-auto">
										<button class="btn btn-primary px-5 px-sm-15" type="button" data-bs-toggle="modal"
														data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true"
														aria-expanded="false" data-bs-reference="parent">Guardar Proceso o fórmula
										</button>
									</div>
								</div>
							</div>
							<div class="modal fade" id="confirmModal" tabindex="-1">
								<div class="modal-dialog modal-dialog-centered">
									<div class="modal-content border">
										<form id="addEventForm" autocomplete="off">
											<div class="modal-header border-200 p-4">
												<h5 class="modal-title text-1000 fs-4 lh-sm">Confirmar</h5>
												<button class="btn p-1 text-900" type="button" data-bs-dismiss="modal" aria-label="Close"><span
												 class="fas fa-times fs--1"></span></button>
											</div>
											<div class="modal-body pt-4 pb-2 px-4">
												<div class="mb-3">
													<label class="fw-bold mb-2 text-1000">Esta seguro que desea confirmar la
														operacion?</label>
												</div>
											</div>
										</form>
										<div class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
											<button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="button"
															data-bs-dismiss="modal">Cancel
											</button>
											<button class="btn btn-sm btn-primary px-9 fs--2 my-0" onclick="validarCampos();" type="submit"
															data-bs-dismiss="modal">Confirmar
											</button>
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

	<jsp:include page="../../../demoWidget.jsp"/>
</main>
<jsp:include page="../../../customize.jsp"/>
</body>
</html>
