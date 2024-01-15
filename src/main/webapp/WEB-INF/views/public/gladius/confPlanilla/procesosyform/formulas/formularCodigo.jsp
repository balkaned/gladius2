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
    $(document).ready(function () {
        $(".hashtag").click(function () {
            var txt = $.trim($(this).text()).substring(0, 11);
            $("#text-box").val($("#text-box").val() + txt);
        });
    });
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
                <li class="breadcrumb-item active">Procesos y form</li>
				<li class="breadcrumb-item active">Fórmulas</li>
			</ol>
		</nav>
		<div class="mb-9">
			<div class="row g-3 mb-4">
				<div class="col-auto">
					<h2 id="h2top" class="mb-0">Editar Formula</h2>
				</div>
			</div>

			<div class="row g-5">
				<div class="col-xl-7">
					<div class="row gx-3 gy-4">
						<form class="row g-4 mb-0 needs-validation" method="POST" action="#!" novalidate>
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									ID Proceso
									<input type="text" readonly="true" name="idprod" hidden id="idprod" maxlength="50"
												 value="${requestScope.fplanillax.idProceso}" class="form-control"
												 style="width: 170px;background:#fcefa1"/>
								</label>
							</div>

							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									ID Formula
									<input type="text" name="idfor" hidden id="idfor" maxlength="50"
												 value="${requestScope.fplanillax.idFormula}" class="form-control"
												 style="width: 170px;background:#fcefa1"/>
								</label>
							</div>

							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									Orden
									<input type="text" name="nroorden" id="nroorden" maxlength="50"
												 value="${requestScope.fplanillax.nroOrden}" class="form-control"/>
								</label>
							</div>

							<div class="col-sm-3 col-md-3">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									Glosa
									<input type="text" name="desglosa" id="desglosa" maxlength="50"
												 value="${requestScope.fplanillax.desGlosa}" class="form-control"/>
								</label>
							</div>

							<div class="form-group row">
								<label class="control-label col-md-3 col-sm-3 ">Concepto Resultado</label>
								<div class="col-md-6 col-sm-6 ">
									<select id="codcon" name="codcon" class="form-control">
										<c:forEach var="Lovs_concept" items="${requestScope.Lovs_conxprod}">
											<option
											 value="${Lovs_concept.codConcepto}"   ${Lovs_concept.codConcepto == requestScope.fplanillax.idConcepto ? 'selected' : ''} >${Lovs_concept.desConcepto}
												- ${Lovs_concept.desVariable}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									Estado
									<input name="desestado" id="desestado" style="width: 20px;" maxlength="50"
												 value="${requestScope.fplanillax.flgEstado}" class="form-control"/> <label>
									1: Creado, 2: Error en compilacion, 3: Compilado correctamente </label>
								</label>
							</div>

							<div class="form-group row">
								<label class="control-label col-md-3 col-sm-3 ">Tipo de Ejecucion</label>
								<div class="col-md-6 col-sm-6 ">
									<select name="tipofor" id="tipofor" onchange="valida_tipo_for(this)" class="form-control"/>
									<option value="0"  ${requestScope.fplanillax.tipOut=='0' ? 'selected' : ''}  >Seleccionar
									</option>
									<option value="1" ${requestScope.fplanillax.tipOut=='1' ? 'selected' : ''} > Ejecucion Normal</option>
									<option value="3" ${requestScope.fplanillax.tipOut=='3' ? 'selected' : ''}  > Resultado Salto</option>
									<option value="2" ${requestScope.fplanillax.tipOut=='2' ? 'selected' : ''} > Ejecucion Stored
										Procedure DB
									</option>
									</select>
								</div>
							</div>

							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">
									Sql Program / Func
									<input type="text" name="sqlprogram" id="sqlprogram" value="${requestScope.fplanillax.sqlprogram}"
												 maxlength="180" class="form-control"> <label style="width: 620px">Params
									required : (Numeric :p_codcia, Numeric :p_codpro, Varchar :p_nroper, Number :p_codtra, Varchar
									:p_concepFin, Varchar :p_grpeje)</label>
								</label>
							</div>

							<div class="form-group row">
								<label class="control-label col-md-3 col-sm-3 ">Grupo de Ejecucion</label>
								<div class="col-md-6 col-sm-6 ">
									<select name="grpeje" id="grpeje" class="form-control"/>
									<option value="0" ${requestScope.fplanillax.grpeje=='0' ? 'selected' : ''} >Seleccionar
									</option>
									<option value="1" ${requestScope.fplanillax.grpeje=='1' ? 'selected' : ''} >x Trabajador</option>
									<option value="2"  ${requestScope.fplanillax.grpeje=='2' ? 'selected' : ''} >x Grupo</option>
									</select>
								</div>
							</div>

							<div class="container">
								<div class="row">
									<div class="col-sm-12 table-responsive">
										<table class="table table-striped table-bordered">
											<thead>
											<tr>
												<th>Conceptos</th>
											</tr>
											</thead>
											<tbody>
											<c:forEach var="Lovs_conxprod" items="${requestScope.Lovs_conxprod}">
												<tr>
													<td>
														<div class="hashtag">${Lovs_conxprod.desVariable} - ${Lovs_conxprod.desAbreviacion}</div>
													</td>
												</tr>
											</c:forEach>
											<tr>
												<td>
													<div class="hashtag">$resultado$</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="hashtag">$salto$</div>
												</td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-4">
										<textarea id="textbox" name="text-box" class="form-control" cols="60"
															rows="25">${requestScope.fplanillax.desFormula}</textarea>
									</div>
									<div class="col-sm-4">
										<table class="navy">
											<tr>
												<td colspan="2">OPERADOR</td>
											</tr>
											<tr>
												<td>
													<div class="hashtag">+</div>
													<div class="hashtag">-</div>
													<div class="hashtag">*</div>
													<div class="hashtag">/</div>
													<div class="hashtag">=</div>
													<br>
													<div class="hashtag">;</div>
													<div class="hashtag">[</div>
													<div class="hashtag">]</div>
													<div class="hashtag">(</div>
													<div class="hashtag">)</div>
													<div class="hashtag">{</div>
													<div class="hashtag">}</div>
													<br>
													<div class="hashtag">if</div>
													<div class="hashtag">else</div>
													<div class="hashtag">else if</div>
													<div class="hashtag">end if</div>
												</td>
											</tr>
										</table>
									</div>
								</div>
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
                                        <a class="btn btn-phoenix-primary px-5" href="#">Cancel</a>
                                    </div>
                                    <div class="col-auto">
                                        <button class="btn btn-primary px-5 px-sm-15" type="button" data-bs-toggle="modal"
                                                        data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true"
                                                        aria-expanded="false" data-bs-reference="parent">Guardar fórmula
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
                                      <button class="btn p-1 text-900" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs--1"></span></button>
                                    </div>
                                    <div class="modal-body pt-4 pb-2 px-4">
                                      <div class="mb-3">
                                        <label class="fw-bold mb-2 text-1000" for="leadStatus">Esta seguro que desea confirmar la operacion?</label>
                                      </div>
                                    </div>
                                  </form>
                                  <div class="modal-footer d-flex justify-content-end align-items-center px-0 pb-0 border-200 pt-0">
                                      <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0 mt-1" type="button" data-bs-dismiss="modal" >Cancel</button>
                                      <button class="btn btn-sm btn-primary px-9 fs--2 my-0 mt-1" onclick="mostrarAlert();" type="submit" data-bs-dismiss="modal" >Confirmar</button>
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
