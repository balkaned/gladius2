<%--
  Created by IntelliJ IDEA.
  User: Javier
  Date: 23/10/2023
  Time: 02:08
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
<head>
	<jsp:include page="../../../../links.jsp"/>
	<style>
	</style>
</head>
<script>
    // Llamar a resaltarVariables() cada vez que se escriba algo en el textarea
    document.getElementById("codigoJava").addEventListener('input', resaltarVariables);

    $(document).ready(function () {
        $(".hashtag").click(function () {
            var txt = $.trim($(this).text()).substring(0, 11);
            $("#text-box").val($("#text-box").val() + txt);
        });
    });

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
        let codConcepto = document.getElementsByName('codConcepto')[0].value;
        let desConcepto = document.getElementsByName('desConcepto')[0].value;
        let desVariable = document.getElementsByName('desVariable')[0].value;
        let desAbreviacion = document.getElementsByName('desAbreviacion')[0].value;
        let descripcion = document.getElementsByName('descripcion')[0].value;

        if (codConcepto.trim() === '' || desConcepto.trim() === '' || desVariable.trim() === '' ||
            desAbreviacion.trim() === '' || descripcion.trim() === '') {
            mostrarAlertDanger();
        } else {
            mostrarAlertSuccess();
        }
    }
</script>
<body>
<main class="main" id="top">
	<jsp:include page="../../../../navsMenu.jsp"/>
	<jsp:include page="../../../../navTop.jsp"/>
	<jsp:include page="../../../../modalFade.jsp"/>

	<div class="content">
		<nav class="mb-2" aria-label="breadcrumb">
			<ol class="breadcrumb mb-0">
				<li class="breadcrumb-item"><a href="#!">Conf. Planillas</a></li>
				<li class="breadcrumb-item active">Procesos y Form</li>
			</ol>
		</nav>
		<div class="mb-9">
			<div class="row g-3 mb-4">
				<div class="col-auto">
					<h2 id="h2top" class="mb-0">Insertar Formula</h2>
				</div>
			</div>

			<div class="row g-5">
				<div class="col-xl-7">
					<div class="row gx-3 gy-4">
						<form class="row g-4 mb-0 needs-validation" method="POST" action="reemplazar" novalidate>
							<div class="col-sm-3 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									ID Proceso
									<input class="form-control mt-2" name="idProceso" type="text" value="" placeholder="" required/>
								</label>
							</div>

							<div class="col-sm-3 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									ID Formula
									<input class="form-control mt-2" name="desConcepto" type="text" value="" placeholder="" required/>
								</label>
							</div>

							<div class="col-sm-3 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									Orden
									<input class="form-control mt-2" name="desVariable" type="text" value="" placeholder="" required/>
								</label>
							</div>

							<div class="col-sm-3 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									Glosa
									<input class="form-control mt-2" name="desAbreviacion" type="text" value="" placeholder="" required/>
								</label>
							</div>

							<div class="col-sm-3 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									Concepto Resultado
									<input class="form-control mt-2" name="descripcion" type="text" value="" placeholder="" required/>
								</label>
							</div>

							<div class="col-sm-3 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									Estado
									<input class="form-control mt-2" name="descripcion" type="text" value="" placeholder="" required/>
								</label>
							</div>

							<div class="col-sm-3 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									Tipo de Ejecución
									<input class="form-control mt-2" name="descripcion" type="text" value="" placeholder="" required/>
								</label>
							</div>

							<div class="col-sm-3 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									Sql Program / Func
									<input class="form-control mt-2" name="descripcion" type="text" value="" placeholder="" required/>
								</label>
							</div>

							<div class="col-sm-3 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									Grupo de Ejecución
									<input class="form-control mt-2" name="descripcion" type="text" value="" placeholder="" required/>
								</label>
							</div>

							<table>
								<tr>
									<td style="width: 600px;">
										<table>
											<tr>
												<td>Lista de Conceptos</td>
											</tr>
											<tr>
												<td>
													<div class="row">
														<div class="col-sm12">
															<div class="card-box table-responsive">
																<table id="datable" class="table table-striped table-bordered">
																	<thead>
																	<tr>
																		<th>Conceptos</th>
																	</tr>
																	</thead>
																	<tbody>
																	<tr>
																		<td>
																			<div class="hashtag"><p>codigo - codigo</p></div>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<div class="hashtag"><p>$resultado$</p></div>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<div class="hashtag"><p>salto</p></div>
																		</td>
																	</tr>
																	</tbody>
																</table>
															</div>
														</div>
													</div>
												</td>
											</tr>
										</table>
									</td>
									<td style="width: 500rem;">
										<div contenteditable="true" id="code-editor">$resultado$ = 0;</div>
										Editor de Formula
										<br>
										<label>
											<textarea id="codigoJava" class="form-control" name="text-box" cols="60" rows="25">
                      </textarea>
										</label>
									</td>
									<td style="width: 100px;">
										<table style="align-content: center;">
											<tr>
												<td colspan="2">
													OPERADOR
												</td>
											</tr>
											<tr>
												<td style="align-content: center;">
													<div class="hashtag"><p style="color: red;"> + </p></div>
													<div class="hashtag"><p style="color: red;"> - </p></div>
													<div class="hashtag"><p style="color: red;"> * </p></div>
													<div class="hashtag"><p style="color: red;"> / </p></div>
													<div class="hashtag"><p style="color: red;"> = </p></div>

													<div class="hashtag"><p style="color: blue;"> ; </p></div>
													<div class="hashtag"><p style="color: green;"> [ </p></div>
													<div class="hashtag"><p style="color: green;"> ] </p></div>
													<div class="hashtag"><p style="color: green;"> ( </p></div>
													<div class="hashtag"><p style="color: green;"> ) </p></div>
													<div class="hashtag"><p style="color: green;"> { </p></div>
													<div class="hashtag"><p style="color: green;"> } </p></div>

													<div class="hashtag"><p style="color: blue;"> if </p></div>
													<div class="hashtag"><p style="color: blue;"> else </p></div>
													<div class="hashtag"><p style="color: blue;"> else if </p></div>
													<div class="hashtag"><p style="color: blue;"> end if </p></div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<button onclick="resaltarVariables()">Resaltar Variables</button>

							<div class="alert alert-success" role="alert" id="alert" style="display:none;">
								Se grabó exitosamente los cambios.
							</div>

							<div class="alert alert-danger" role="alert" id="incomplete" style="display:none;">
								Por favor, complete todos los campos requeridos.
							</div>

							<div class="col-12 gy-6">
								<div class="row g-3 justify-content-end">
									<div class="col-auto">
										<a class="btn btn-phoenix-primary px-5" href="reemplazar">Cancel</a>
									</div>
									<div class="col-auto">
										<button class="btn btn-primary px-5 px-sm-15" type="button" data-bs-toggle="modal"
														data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true"
														aria-expanded="false" data-bs-reference="parent">Guardar Formula
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
													<label class="fw-bold mb-2 text-1000">¿Esta seguro que desea confirmar la operación?</label>
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
	<jsp:include page="../../../../demoWidget.jsp"/>
</main>
<jsp:include page="../../../../customize.jsp"/>
</body>
</html>
