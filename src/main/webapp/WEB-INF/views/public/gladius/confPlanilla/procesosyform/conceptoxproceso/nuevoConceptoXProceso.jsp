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
                <li class="breadcrumb-item active">Procesos y form</li>
                <li class="breadcrumb-item active">Concepto x proceso</li>
			</ol>
		</nav>
		<div class="mb-9">
			<div class="row g-5">
				<div class="col-xl-7">
					<div class="row gx-3 gy-4">
						<form class="row g-4 mb-0 needs-validation" method="POST"
									action="addConceptoXProceso@${requestScope.idxproceso}"
									novalidate>
							<h2>Insertar Concepto X Proceso</h2>

							<input type="hidden" id="idproceso" name="idproceso" style="width: 170px;" maxlength="50"
										 value="${requestScope.idxproceso}"/>

							<!-- input -->
							<div class="form-group row">
								<label class="control-label col-md-3 col-sm-3">Concepto</label>
								<div class="col-md-6 col-sm-6">
									<select name="idconcepto" class="form-control">
										<option value="">Seleccionar</option>
										<c:forEach var="LstConceptoIns" items="${requestScope.LstConceptoIns}">
											<option value="${LstConceptoIns.codConcepto}"> ${LstConceptoIns.codConcepto}
												- ${LstConceptoIns.desConcepto} </option>
										</c:forEach>
									</select>
								</div>
							</div>

							<!-- select -->
							<div class="col-md-12 col-sm-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="tip_concepto">Tipo de Concepto</label>
								<select id="tip_concepto" name="tip_concepto" class="form-control">
									<option value="0">Parametro</option>
									<option value="1">Ingresos</option>
									<option value="2">Descuentos</option>
									<option value="3">Aportes</option>
									<option value="4">Neto</option>
									<option value="5">Otros</option>
								</select>
							</div>

							<!-- input -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="id_concepto_pdt">Codigo PDT</label>
								<input class="form-control" id="id_concepto_pdt" name="id_concepto_pdt" type="text"
											 value=""/>
							</div>

							<!-- flag -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="flg_boleta">Flag Boleta</label>
								<input type="checkbox" class="form-check-input" name="flg_boleta" value="1" id="flg_boleta"/>
							</div>

							<!-- input -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="id_orden_bol">Orden</label>
								<input class="form-control" id="id_orden_bol" name="id_orden_bol" type="text"
											 maxlength="50" value=""/>
							</div>

							<!-- input -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="valor_bol">Valor</label>
								<input class="form-control" id="valor_bol" name="valor_bol" type="text"
											 maxlength="50" value=""/>
							</div>

							<!-- input -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="des_custom">Descripcion
									Customizada</label>
								<input class="form-control" id="des_custom" name="des_custom" type="text" maxlength="50"
											 value=""/>
							</div>

							<!-- flag -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="flg_promediable">Promediable</label>
								<input type="checkbox" class="form-check-input" name="flg_promediable" value="1" id="flg_promediable"/>
							</div>

							<!-- input -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="nro_meses_atras">Nro Meses
									Atras</label>
								<input class="form-control" id="nro_meses_atras" name="nro_meses_atras" type="text"
											 maxlength="50" value=""/>
							</div>

							<!-- modal -->
							<a href="#!">Ver Conceptos Promediables</a>

							<!-- modal -->
							<a href="#!">Ver Grupo de Conceptos</a>

							<!-- flag -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="flg_agrupable">Grupo de
									Concepto</label>
								<input type="checkbox" class="form-check-input" name="flg_agrupable" value="1" id="flg_agrupable"/>
							</div>

							<!-- modal -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Contabilidad</label>
								<a href="#!">Configura Cuentas Contables por Compañia</a>
							</div>

							<!-- radio -->
							<div class="form-group row mt-8">
								<label class="col-sm-6 col-md-4 control-label">Tipo de Ingreso
									<br>
									<small class="text-navy">Tipo de Concepto de Ingreso</small>
								</label>
								<div class="col-sm-6 col-md-8">
									<div class="radio">
										<label>
											<input type="radio" name="tip_ingreso" value="1" class="flat">
											Rem.Fija (Sueldos, Asig. fam. etc)
										</label>
									</div>
									<div class="radio">
										<label>
											<input type="radio" name="tip_ingreso" value="2" class="flat">
											Rem. Variable (Comisiones, bonificaciones, etc)
										</label>
									</div>
									<div class="radio">
										<label>
											<input type="radio" name="tip_ingreso" value="3" class="flat">
											Rem. Complementaria
										</label>
									</div>
									<div class="radio">
										<label>
											<input type="radio" name="tip_ingreso" value="4" class="flat">
											Ninguno
										</label>
									</div>
								</div>
							</div>

							<!-- flag -->
							<div class="form-group row mt-8">
								<label class="col-sm-6 col-md-4 control-label">Tipo de Descuento 5ta
									<br>
									<small class="text-navy">Flag de Descuento de 5ta</small>
								</label>
								<div class="col-sm-6 col-md-8">
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_pry_5ta" value="1" class="flat"> Rem.
											Proyecta 5ta.
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_des_5ta_mes" value="1" class="flat">
											Rem. Descuenta 5ta en el Mes
										</label>
									</div>
								</div>
							</div>

							<!-- flag -->
							<div class="form-group row mt-8">
								<label class="col-sm-6 col-md-4 control-label">Afectaciones - Empleador
									<br>
									<small class="text-navy">Flag de Descuento que se le aplica al Empleador </small>
								</label>
								<div class="col-sm-6 col-md-8">
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_ess_reg" value="1" class="flat">
											Essalud Seguro Regular de Trabajador
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_ess_pesq" value="1" class="flat">
											Essalud - CBSSP - Seg. Trab Pesquero
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_ess_agrac" value="1" class="flat">
											Essalud Seguro Agrario / Acuicultor
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_ess_sctr" value="1" class="flat">
											Essalud Sctr
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_extra_solid" value="1" class="flat">
											Imp. Extraord Solidaridad (8)
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_fondo_art" value="1" class="flat">
											Fondo Derechos Sociales del Artista
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_apo_senati" value="1" class="flat">
											Aportacion de Senati
										</label>
									</div>
								</div>
							</div>

							<!-- flag -->
							<div class="form-group row mt-8">
								<label class="col-sm-6 col-md-4 control-label">Afectaciones - Trabajador
									<br>
									<small class="text-navy">Afectaciones de descuentos que se efectuan al trabajador</small>
								</label>
								<div class="col-sm-6 col-md-8">
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_onp" value="1" class="flat">
											Sistema Nacional de Pensiones 19990
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_afp" value="1" class="flat">
											Sistema Privado de Pensiones
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_fond_compl_jub" value="1" class="flat">
											Fondo Compl. de Jubil Min, Met y Sider
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_esp_pens_pesq" value="1" class="flat">
											Reg. Esp. Pensiones Trab. Pesquero
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_5ta" value="1" class="flat">
											Imp. Renta de 5ta Categoria
										</label>
									</div>
								</div>
							</div>

							<!-- flag -->
							<div class="form-group row mt-8">
								<label class="col-sm-6 col-md-4 control-label">Afectaciones - Pensionistas
									<br>
									<small class="text-navy">Afectaciones de aportes al pensionista</small>
								</label>
								<div class="col-sm-6 col-md-8">
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_ess_seg_pen" value="1" class="flat">
											Essalud Seguro Regular Pensionista
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_cont_asis_previs" value="1" class="flat">
											Contrib. Solidaria Asistencia Previs.
										</label>
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
                                                        aria-expanded="false" data-bs-reference="parent">Guardar Concepto
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

</main>
<!-- ===============================================-->
<!--    End of Main Content-->
<!-- ===============================================-->
<jsp:include page="../../../../customize.jsp"></jsp:include>
</body>
</html>