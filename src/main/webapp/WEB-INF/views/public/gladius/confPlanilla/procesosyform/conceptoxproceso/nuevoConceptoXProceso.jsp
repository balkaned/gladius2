<%--
  Created by IntelliJ IDEA.
  User: space
  Date: 21/12/23
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../../../../links.jsp"></jsp:include>
</head>
<script>
    //Funcion para validar campos vacios
		function validarCampos() {

		}
</script>
<body>
<!-- ===============================================-->
<!--    Main Content-->
<!-- ===============================================-->
<main class="main" id="top">
	<jsp:include page="../../../../navsMenu.jsp"></jsp:include>
	<jsp:include page="../../../../navTop.jsp"></jsp:include>
	<jsp:include page="../../../../modalFade.jsp"></jsp:include>
	<jsp:include page="../../../../demoWidget.jsp"></jsp:include>

	<div class="content">
		<nav class="mb-2" aria-label="breadcrumb">
			<ol class="breadcrumb mb-0">
				<li class="breadcrumb-item"><a href="#!">Conf. Planillas</a></li>
				<li class="breadcrumb-item active">Conceptos</li>
			</ol>
		</nav>
		<div class="mb-9">
			<div class="row g-3 mb-4">
				<div class="col-auto">
					<h2 id="h2top" class="mb-0">Insertar Concepto</h2>
				</div>
			</div>

			<div class="row g-5">
				<div class="col-xl-7">
					<div class="row gx-3 gy-4">
						<form class="row g-4 mb-0 needs-validation" method="POST" action="#!" novalidate>
							<h2>Nombre del proceso - [nombre del Concepto]</h2>

							<!-- input -->
							<div class="col-sm-6 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="concepto">Concepto</label>
								<input class="form-control" id="concepto" name="concepto" type="number" value="" placeholder="#" required/>
							</div>

							<!-- select -->
							<div>
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="tipoConcepto">Tipo de Concepto</label>
								<div class="col-md-6 col-sm-6 ">
									<select id="tipoConcepto" name="tip_concepto" class="form-control">
										<option value="0">Parametro</option>
										<option value="1">Ingresos</option>
										<option value="2">Descuentos</option>
										<option value="3">Aportes</option>
										<option value="4">Neto</option>
										<option value="5">Otros</option>
									</select>
								</div>
							</div>

							<!-- input -->
							<div class="col-sm-6 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="codigoPDT">Codigo PDT</label>
								<input class="form-control" id="codigoPDT" name="concepto" type="number" value="" placeholder="#" required/>
							</div>

							<!-- FLAG BOLETA -->

							<!-- input -->
							<div class="col-sm-6 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="orden">Orden</label>
								<input class="form-control" id="orden" name="concepto" type="number" value="" placeholder="#" required/>
							</div>

							<!-- input -->
							<div class="col-sm-6 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="valor">Valor</label>
								<input class="form-control" id="valor" name="concepto" type="number" value="" placeholder="#" required/>
							</div>

							<!-- input -->
							<div class="col-sm-6 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="desCustom">Descripcion Customizada</label>
								<input class="form-control" id="desCustom" name="concepto" type="number" value="" placeholder="#" required/>
							</div>

							<!-- PROMEDIABLE -->

							<!-- input -->
							<div class="col-sm-6 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="nroMesesAtras">Nro Meses Atras</label>
								<input class="form-control" id="nroMesesAtras" name="concepto" type="number" value="" placeholder="#" required/>
							</div>

							<!-- VER CONCEPTOS PROMEDIABLES -->
							<!-- GRUPO DE CONCEPTO -->
							<!-- CONTABILIDAD -->
							<!-- TIPO DE INGRESO -->
							<!-- ETC -->

							<!-- alerta de validación de campos-->
							<div class="alert alert-success" role="alert" id="alert" style="display:none;">
								Se grabó exitosamente los cambios.
							</div>

							<!-- alerta de validación de campos-->
							<div class="alert alert-danger" role="alert" id="incomplete" style="display:none;">
								Por favor, complete todos los campos requeridos.
							</div>

							<!-- Botones de acción -->
							<div class="col-12 gy-6">
								<div class="row g-3 justify-content-end">
									<div class="col-auto">
										<a class="btn btn-phoenix-primary px-5" href="listConceptoXProceso@list@1">Cancel</a>
									</div>
									<div class="col-auto">
										<button class="btn btn-primary px-5 px-sm-15" type="button" data-bs-toggle="modal"
														data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true"
														aria-expanded="false" data-bs-reference="parent">Guardar Concepto
										</button>
									</div>
								</div>
							</div>

							<!-- Botones de acción -->
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

							<!-- Fin del formulario-->
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</main>
<!-- ===============================================-->
<!--    End of Main Content-->
<!-- ===============================================-->
<jsp:include page="../../../../customize.jsp"></jsp:include>
</body>
</html>
