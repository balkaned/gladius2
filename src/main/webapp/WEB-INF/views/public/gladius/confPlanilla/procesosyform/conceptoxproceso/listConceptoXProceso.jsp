<%--
  Created by IntelliJ IDEA.
  User: Javier
  Date: 23/10/2023
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
<head>
	<jsp:include page="../../../../links.jsp"></jsp:include>
</head>
<script>
  function obtenerListaConcepto() {
    window.location.href = '${pageContext.request.contextPath}/listConceptoXProceso@${requestScope.slc_proceso}@'+document.getElementById('select_concepto').value;
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

	<div class="content">
		<nav class="mb-2" aria-label="breadcrumb">
			<ol class="breadcrumb mb-0">
				<li class="breadcrumb-item"><a href="#!">Conf. Planillas</a></li>
				<li class="breadcrumb-item active">Procesos y form</li>
				<li class="breadcrumb-item active">Concepto x proceso</li>
			</ol>
		</nav>
		<div class="mb-3">
			<div class="row g-3">
				<div class="col-auto">
					<h2 id="h2top" class="mb-0">Concepto por proceso</h2>
				</div>
			</div>
		</div>
		<div class="row g-5">
            <div class="col-xl-8">
                <div class="row gx-3 gy-4">
                    <form class="row g-4 mb-0 needs-validation" method="POST" action="" novalidate>
                        <!-- Input hidden fields -->
                        <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}"/>
                        <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}"/>

                        <div class="col-sm-6 col-md-4">
                            <!--<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Grupo de conceptos</label>-->
                            <select class="form-select" id="select_concepto" name="slc_grpconcepto">
                                <option value="">Seleccionar concepto</option>
                                <option value="0" ${requestScope.slc_grpconcepto  == '0' ? 'selected' : ''}>Parametros</option>
                                <option value="1" ${requestScope.slc_grpconcepto  == '1' ? 'selected' : ''}>Haberes</option>
                                <option value="2" ${requestScope.slc_grpconcepto  == '2' ? 'selected' : ''}>Descuentos</option>
                                <option value="3" ${requestScope.slc_grpconcepto  == '3' ? 'selected' : ''}>Aportes</option>
                                <option value="4" ${requestScope.slc_grpconcepto  == '4' ? 'selected' : ''}>Neto</option>
                                <option value="5" ${requestScope.slc_grpconcepto  == '5' ? 'selected' : ''}>Totales</option>
                            </select>
                        </div>

                        <div class="d-grid gap-2 d-md-block">
                            <button class="btn btn-primary btn-sm " onclick="obtenerListaConcepto();"><span class="fa-solid fa-magnifying-glass me-2"></span>Buscar</button>
                            <a class="btn btn-phoenix-secondary btn-sm" href="nuevoConceptoXProceso@${requestScope.slc_proceso}"><span class="fas fa-plus me-2"></span>Add Concepto</a>
                            <a class="btn btn-phoenix-secondary text-900 btn-sm" href="listProcesoFormulas"><span class="fa-solid fa-reply me-2"></span>Atras</a>
                            <div class="btn-group mb-1 me-1 ms-1 mt-1">
                              <button class="btn btn-sm btn-success" type="button"><span class="fa-solid fa-hashtag fs--1 me-2"></span></span class="ps-5">Exportar</span></button>
                              <button class="btn btn-sm dropdown-toggle dropdown-toggle-split btn-success" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="sr-only"></span></button>
                              <div class="dropdown-menu">
                            	  <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="#">
                            		<span class="fa-solid fa-download fs--1 me-2"></span>Exportar Excel Todos
                            	  </a>
                            	  <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Exportar Excel Solo Activos</a>
                            	  <div class="dropdown-divider"></div>
                            	  <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Otros</a>
                              </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

		<div id="orderTable" class="mt-3">
			<div class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1">
				<div class="table-responsive scrollbar mx-n1 px-1">
					<table class="table table-sm fs--1 mb-0">
						<thead>
						<tr>
							<th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
								<div class="form-check mb-0 fs-0">
									<input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox"
												 data-bulk-select='{"body":"order-table-body"}'/>
								</div>
							</th>
							<th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="order" style="width:5%;">ID
							</th>
							<th class="sort align-middle text-center pe-0" scope="col" data-sort="date">CONCEPTO</th>
							<th class="sort align-middle text-center pe-0" scope="col"></th>
						</tr>
						</thead>
						<tbody class="list" id="order-table-body">
						<c:forEach var="conceptoXProceso" items="${requestScope.conceptoXProcesoList}">
							<tr class="hover-actions-trigger btn-reveal-trigger position-static">
								<td class="fs--1 align-middle px-0 py-3">
									<div class="form-check mb-0 fs-0">
										<input class="form-check-input" type="checkbox"
													 data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}'/>
									</div>
								</td>
								<td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold"
																																					href="#!">#${conceptoXProceso.procodcon}</a>
								</td>
								<td class="total align-middle text-start ps-5 fw-semi-bold text-1000">
                  <span class="badge badge-phoenix fs--1 badge-phoenix-secondary">
                    <span class="badge-label">
												${conceptoXProceso.coodescon}
										</span>
                  </span>
								</td>
								<td class="align-middle text-center white-space-nowrap pe-0 action">
									<div class="font-sans-serif btn-reveal-trigger position-static">
										<button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2"
														type="button" data-bs-toggle="dropdown" data-boundary="window"
														aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span
										 class="fas fa-ellipsis-h fs--2"></span></button>
										<div class="dropdown-menu dropdown-menu-end py-2">
											<a class="dropdown-item" href="editarConceptoXProceso@${requestScope.slc_proceso}@${conceptoXProceso.procodcon}">Editar</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item text-danger" href="#!">Eliminar</a></div>
									</div>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
					<div class="col-auto d-flex">
						<p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a
					 class="fw-semi-bold" href="#!" data-list-view="*">View all<span
					 class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none"
																																										href="#!" data-list-view="less">View
						Less<span
						 class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
					</div>
					<div class="col-auto d-flex">
						<button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
						<ul class="mb-0 pagination"></ul>
						<button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../../../../demoWidget.jsp"></jsp:include>
</main>
<!-- ===============================================-->
<!--    End of Main Content-->
<!-- ===============================================-->

<jsp:include page="../../../../customize.jsp"></jsp:include>
</body>
</html>
