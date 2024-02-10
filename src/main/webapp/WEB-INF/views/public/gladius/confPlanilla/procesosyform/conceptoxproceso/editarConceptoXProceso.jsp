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
									action="editConceptoXProceso@${requestScope.slc_proceso}@${requestScope.proconceptox.procodcon}"
									novalidate>
							<h2>${requestScope.pplanillax} - [${requestScope.proconceptox.coodescon}]</h2>

							<input type="hidden" id="idproceso" name="idproceso" style="width: 170px;" maxlength="50"
										 value="${requestScope.proconceptox.procodpro}" class="form-control"/>

							<!-- input -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="id_concept">Concepto</label>
								<input class="form-control" id="id_concept" name="id_concept" type="text"
											 maxlength="50" value="${requestScope.proconceptox.procodcon}" disabled/>
							</div>

							<!-- select -->
							<div class="col-md-12 col-sm-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="tip_concepto">Tipo de Concepto</label>
								<select id="tip_concepto" name="tip_concepto" class="form-control">
									<option value="0" ${requestScope.proconceptox.protipcon=='0' ? 'selected' : ''}>Parametro</option>
									<option value="1" ${requestScope.proconceptox.protipcon=='1' ? 'selected' : ''}>Ingresos</option>
									<option value="2" ${requestScope.proconceptox.protipcon=='2' ? 'selected' : ''}>Descuentos</option>
									<option value="3" ${requestScope.proconceptox.protipcon=='3' ? 'selected' : ''}>Aportes</option>
									<option value="4" ${requestScope.proconceptox.protipcon=='4' ? 'selected' : ''}>Neto</option>
									<option value="5" ${requestScope.proconceptox.protipcon=='5' ? 'selected' : ''}>Otros</option>
								</select>
							</div>

							<!-- input -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="id_concepto_pdt">Codigo PDT</label>
								<input class="form-control" id="id_concepto_pdt" name="id_concepto_pdt" type="text"
											 value="${requestScope.proconceptox.procodconpdt}"/>
							</div>

							<!-- flag -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="flg_boleta">Flag Boleta</label>
								<input type="checkbox" class="form-check-input" name="flg_boleta" value="1"
								${requestScope.proconceptox.proflgbol=='1' ? 'checked=true' : ''} id="flg_boleta"/>
							</div>

							<!-- input -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="id_orden_bol">Orden</label>
								<input class="form-control" id="id_orden_bol" name="id_orden_bol" type="text"
											 maxlength="50" value="${requestScope.proconceptox.proorden}"/>
							</div>

							<!-- input -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="valor_bol">Valor</label>
								<input class="form-control" id="valor_bol" name="valor_bol" type="text"
											 maxlength="50" value="${requestScope.proconceptox.provalor}"/>
							</div>

							<!-- input -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="des_custom">Descripcion
									Customizada</label>
								<input class="form-control" id="des_custom" name="des_custom" type="text" maxlength="50"
											 value="${requestScope.proconceptox.prodescustom}"/>
							</div>

							<!-- flag -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="flg_promediable">Promediable</label>
								<input type="checkbox" class="form-check-input" name="flg_promediable" value="1" id="flg_promediable"
								${requestScope.proconceptox.flg_promediable=='1' ? 'checked=true' : ''}/>
							</div>

							<!-- input -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="nro_meses_atras">Nro Meses
									Atras</label>
								<input class="form-control" id="nro_meses_atras" name="nro_meses_atras" type="text"
											 maxlength="50" value="${requestScope.proconceptox.nro_meses_atras}"/>
							</div>

							<!-- modal -->
							<a href="#!">Ver Conceptos Promediables</a>

							<!-- modal -->
							<a href="#!">Ver Grupo de Conceptos</a>

							<!-- flag -->
							<div class="col-sm-6 col-md-12">
								<label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="flg_agrupable">Grupo de
									Concepto</label>
								<input type="checkbox" class="form-check-input" name="flg_agrupable" value="1" id="flg_agrupable"
								${requestScope.proconceptox.flg_agrupable=='1' ? 'checked=true' : ''}/>
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
											<input type="radio" name="tip_ingreso" value="1" class="flat"
											${requestScope.proconceptox.tip_ingreso=='1' ? 'checked' : ''}>
											Rem.Fija (Sueldos, Asig. fam. etc)
										</label>
									</div>
									<div class="radio">
										<label>
											<input type="radio" name="tip_ingreso" value="2" class="flat"
											${requestScope.proconceptox.tip_ingreso=='2' ? 'checked' : ''}>
											Rem. Variable (Comisiones, bonificaciones, etc)
										</label>
									</div>
									<div class="radio">
										<label>
											<input type="radio" name="tip_ingreso" value="3" class="flat"
											${requestScope.proconceptox.tip_ingreso=='3' ? 'checked' : ''}>
											Rem. Complementaria
										</label>
									</div>
									<div class="radio">
										<label>
											<input type="radio" name="tip_ingreso" value="4" class="flat"
											${requestScope.proconceptox.tip_ingreso=='4' ? 'checked' : ''}>
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
											<input type="checkbox" name="flg_pry_5ta" value="1" class="flat"
											${requestScope.proconceptox.flg_pry_5ta=='1' ? 'checked' : ''}> Rem.
											Proyecta 5ta.
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_des_5ta_mes" value="1" class="flat"
											${requestScope.proconceptox.flg_des_5ta_mes=='1' ? 'checked' : ''}>
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
											<input type="checkbox" name="flg_ess_reg" value="1" class="flat"
											${requestScope.proconceptox.flg_ess_reg=='1' ? 'checked' : ''}>
											Essalud Seguro Regular de Trabajador
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_ess_pesq" value="1" class="flat"
											${requestScope.proconceptox.flg_ess_pesq=='1' ? 'checked' : ''}>
											Essalud - CBSSP - Seg. Trab Pesquero
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_ess_agrac" value="1" class="flat"
											${requestScope.proconceptox.flg_ess_agrac=='1' ? 'checked' : ''}>
											Essalud Seguro Agrario / Acuicultor
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_ess_sctr" value="1" class="flat"
											${requestScope.proconceptox.flg_ess_sctr =='1' ? 'checked' : ''}>
											Essalud Sctr
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_extra_solid" value="1" class="flat"
											${requestScope.proconceptox.flg_extra_solid=='1' ? 'checked' : ''}>
											Imp. Extraord Solidaridad (8)
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_fondo_art" value="1" class="flat"
											${requestScope.proconceptox.flg_fondo_art=='1' ? 'checked' : ''}>
											Fondo Derechos Sociales del Artista
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_apo_senati" value="1" class="flat"
											${requestScope.proconceptox.flg_apo_senati =='1' ? 'checked' : ''}>
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
											<input type="checkbox" name="flg_onp" value="1" class="flat"
											${requestScope.proconceptox.flg_onp  =='1' ? 'checked' : ''}>
											Sistema Nacional de Pensiones 19990
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_afp" value="1" class="flat"
											${requestScope.proconceptox.flg_afp  =='1' ? 'checked' : ''}>
											Sistema Privado de Pensiones
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_fond_compl_jub" value="1" class="flat"
											${requestScope.proconceptox.flg_fond_compl_jub  =='1' ? 'checked' : '' }>
											Fondo Compl. de Jubil Min, Met y Sider
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_esp_pens_pesq" value="1" class="flat"
											${requestScope.proconceptox.flg_esp_pens_pesq  =='1' ? 'checked' : '' }>
											Reg. Esp. Pensiones Trab. Pesquero
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_5ta" value="1" class="flat"
											${requestScope.proconceptox.flg_5ta  =='1' ? 'checked' : ''}>
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
											<input type="checkbox" name="flg_ess_seg_pen" value="1" class="flat"
											${requestScope.proconceptox.flg_ess_seg_pen =='1' ? 'checked' : ''}>
											Essalud Seguro Regular Pensionista
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="flg_cont_asis_previs" value="1" class="flat"
											${requestScope.proconceptox.flg_cont_asis_previs =='1' ? 'checked' : ''}>
											Contrib. Solidaria Asistencia Previs.
										</label>
									</div>
								</div>
							</div>

							<div id="alert" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                                <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                                <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                                <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                            <div id="alert" class="alert alert-outline-warning bg-warning bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                                <span class="fa-solid fa-triangle-exclamation text-warning fs-0 me-3"></span>
                                <p class="mb-0 fw-semi-bold text-1000 col-11">Por favor, complete todos los campos requeridos <a href="#">Mas información</a></p>
                                <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
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
                                      <button class="btn btn-sm btn-phoenix-primary px-4 my-0 mt-1" type="button" data-bs-dismiss="modal" >Cancel</button>
                                      <button class="btn btn-sm btn-primary px-9 my-0 mt-1" onclick="mostrarAlert();" type="submit" data-bs-dismiss="modal" >Confirmar</button>
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
