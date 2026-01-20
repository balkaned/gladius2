<%--
    Created on : 15/06/2023, 4:20:00 PM
    Author     : Jan Quiroz
    Email	   : janquirozs@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
  <head>
    <jsp:include page="../../../../links.jsp"></jsp:include>
    <script src="resources/assets/js/gladius/fichaTrabajador.js"></script>
  </head>

  <body>
    <!-- ===============================================-->
    <!--    Main Content-->
    <!-- ===============================================-->
    <main class="main" id="top">
          <jsp:include page="../../../../navsMenu.jsp"></jsp:include>
          <jsp:include page="../../../../navTop.jsp"></jsp:include>
          <jsp:include page="../../../../modalFade.jsp"></jsp:include>

          <div class="content bg-100">
              <div class="pb-9">
                <!--<div class="row">
                  <div class="col-12">
                    <div class="row align-items-center justify-content-between g-3 mb-3">
                    </div>
                  </div>
                </div>-->
                <div class="row mt-0 mb-1">
                    <div class="col-12">
                      <div class="row align-items-center justify-content-between">
                        <div class="col-12 col-md-auto">
                          <h2 class="mb-0"></h2>
                        </div>
                        <div class="col-12 col-md-auto">
                          <div class="d-flex">
                            <div class="flex-1 d-md-none">
                              <button class="btn px-3 btn-phoenix-secondary text-700 me-2 pt-2 pb-1" data-phoenix-toggle="offcanvas" data-phoenix-target="#productFilterColumn"><span class="fa-solid fa-bars"></span></button>
                            </div>
                            <a class="btn btn-sm btn-phoenix-primary me-2" href="listEmpleados"><span class="fa-regular fa-address-card me-2"></span>Volver a lista trabajadores</a>
                            <!--<button class="btn btn-phoenix-secondary px-3 px-sm-5 me-2"><span class="fa-solid fa-thumbtack me-sm-2"></span><span class="d-none d-sm-inline">Shortlist</span></button>
                            <button class="btn px-3 btn-phoenix-secondary" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fa-solid fa-ellipsis"></span></button>
                            <ul class="dropdown-menu dropdown-menu-end p-0" style="z-index: 9999;">
                              <li><a class="dropdown-item" href="#!">View profile</a></li>
                              <li><a class="dropdown-item" href="#!">Report</a></li>
                              <li><a class="dropdown-item" href="#!">Manage notifications</a></li>
                              <li><a class="dropdown-item text-danger" href="#!">Delete Lead</a></li>
                            </ul>-->
                          </div>
                        </div>
                      </div>
                    </div>
                </div>
                <div class="row g-0 g-md-4 g-xl-6">
                  <jsp:include page="../navsGenericEmpl.jsp"></jsp:include>

                  <div class="col-md-8 col-lg-9 col-xl-9">
                    <div class="lead-details-container">
                      <ul class="nav nav-underline deal-details scrollbar flex-nowrap w-100 pb-1 mb-2" id="myTab" role="tablist" style="overflow-y: hidden;">
                        <li class="nav-item text-nowrap me-2" role="presentation">
                            <a class="nav-link active" id="activity-tab" data-bs-toggle="tab" href="#tab-activity" role="tab" aria-controls="tab-activity" aria-selected="false" tabindex="-1"><span class="fa-solid fa-archive me-2 tab-icon-color"></span>Datos personales</a>
                        </li>
                        <li class="nav-item text-nowrap me-2" role="presentation">
                            <a class="nav-link" id="notes-tab" data-bs-toggle="tab" href="#tab-notes" role="tab" aria-controls="tab-notes" aria-selected="false" tabindex="-1"><span class="fa-solid fas fa-graduation-cap me-2 tab-icon-color"></span>Datos laborales</a>
                        </li>
                        <li class="nav-item text-nowrap me-2" role="presentation">
                            <a class="nav-link" id="meeting-tab" data-bs-toggle="tab" href="#tab-meeting" role="tab" aria-controls="tab-meeting" aria-selected="true"><span class="fa-solid fa-credit-card me-2 tab-icon-color"></span>Información de pago</a>
                        </li>
                        <li class="nav-item text-nowrap me-2" role="presentation">
                            <a class="nav-link" id="task-tab" data-bs-toggle="tab" href="#tab-task" role="tab" aria-controls="tab-task" aria-selected="true"> <span class="fa-solid fas fa-medkit me-2 tab-icon-color"></span>Seguridad social</a>
                        </li>
                        <li class="nav-item text-nowrap me-2" role="presentation">
                            <a class="nav-link" id="call-tab" data-bs-toggle="tab" href="#tab-call" role="tab" aria-controls="tab-call" aria-selected="true"><span class="fa-solid fa-truck me-2 tab-icon-color"></span>Datos domicilio</a>
                        </li>
                      </ul>

                      <div class="tab-content" id="myTabContent">
                         <div class="tab-pane fade active show" id="tab-activity" role="tabpanel" aria-labelledby="activity-tab">
                            <h3 class="mt-3 mb-0">Datos personales</h3>
                            <div class="col-12 pe-3">
                               <p class="mt-0 d-flex justify-content-end mb-0 fs--1"><strong>Ult. fec mod: </strong> <span class="ms-1">${emp.iexfeccmod}</span></p>
                               <p class="mt-0 d-flex justify-content-end mb-0 fs--1"><strong>Ult. usu mod: </strong> <span class="ms-1">${emp.iexusumod}</span></p>
                            </div>
                           
                            <div class="row g-3 mt-0">
                                <div class="col-12">
                                    <form class="needs-validation" method="POST" action="updateEmplDatPers" novalidate>
                                        <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                        <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />

                                        <!-- SECCIÓN 1: Identificación y Estado -->
                                        <div class="card mb-4 shadow-sm">
                                            <div class="card-header bg-light border-bottom">
                                                <h5 class="mb-0 text-primary"><i class="fas fa-id-card me-2"></i>Identificación y Estado</h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row g-3">
                                                    <div class="col-md-2">
                                                        <label class="form-label fw-semibold">Código</label>
                                                        <input class="form-control bg-light" name="iexcodtra_display" type="text" value="${requestScope.emp.iexcodtra}" readonly disabled />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Tipo documento <span class="text-danger">*</span></label>
                                                        <select name="iextipdocid" class="form-select" required>
                                                           <option value="">-- Seleccionar --</option>
                                                           <c:forEach var="lovTipdoc" items="${lovTipdoc}">
                                                              <option value="${lovTipdoc.idLov}" ${lovTipdoc.idLov == requestScope.emp.iextipdocid ? 'selected' : ''}>${lovTipdoc.desLov}</option>
                                                           </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Número documento <span class="text-danger">*</span></label>
                                                        <input class="form-control" name="iexnrodoc" maxlength="15" type="text" value="${requestScope.emp.iexnrodoc}" placeholder="Ej: 12345678" required/>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <label class="form-label fw-semibold">Estado <span class="text-danger">*</span></label>
                                                        <select name="iexflgest" class="form-select" required>
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovEstados" items="${lovEstados}">
                                                                <option value="${lovEstados.idLov}" ${lovEstados.idLov == requestScope.emp.iexflgest ? 'selected' : ''}>${lovEstados.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <label class="form-label fw-semibold">Código anterior</label>
                                                        <input class="form-control" name="iexcodant" maxlength="20" type="text" value="${requestScope.emp.iexcodant}" placeholder="Opcional" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- SECCIÓN 2: Información Personal -->
                                        <div class="card mb-4 shadow-sm">
                                            <div class="card-header bg-light border-bottom">
                                                <h5 class="mb-0 text-primary"><i class="fas fa-user me-2"></i>Información Personal</h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row g-3">
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Nombres <span class="text-danger">*</span></label>
                                                        <input class="form-control" name="iexnomtra" type="text" value="${requestScope.emp.iexnomtra}" placeholder="Ej: Juan Carlos" required />
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Apellido paterno <span class="text-danger">*</span></label>
                                                        <input class="form-control" name="iexapepat" type="text" value="${requestScope.emp.iexapepat}" placeholder="Ej: García" required />
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Apellido materno <span class="text-danger">*</span></label>
                                                        <input class="form-control" name="iexapemat" type="text" value="${requestScope.emp.iexapemat}" placeholder="Ej: Pérez" required />
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Fecha nacimiento <span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <input class="form-control datetimepicker" name="iexfecnac" id="iexfecnac" onchange="formatearFecha1();" value="${fecnacIEX}" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required />
                                                            <span class="input-group-text"><i class="uil uil-calendar-alt"></i></span>
                                                        </div>
                                                        <input class="form-control" id="iexfecnachidden" type="hidden" value="${fecnacIEX}" />
                                                    </div>
                                                    <div class="col-md-2">
                                                        <label class="form-label fw-semibold">Sexo <span class="text-danger">*</span></label>
                                                        <select name="iexcodsex" class="form-select" required>
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovSexo" items="${lovSexo}">
                                                                <option value="${lovSexo.idLov}" ${lovSexo.idLov == requestScope.emp.iexcodsex ? 'selected' : ''}>${lovSexo.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Estado civil <span class="text-danger">*</span></label>
                                                        <select name="iexestcivil" class="form-select" required>
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovEstcivil" items="${lovEstcivil}">
                                                                <option value="${lovEstcivil.idLov}" ${lovEstcivil.idLov == requestScope.emp.iexestcivil ? 'selected' : ''}>${lovEstcivil.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Modalidad formativa</label>
                                                        <select name="iexmodform" class="form-select">
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovModForm" items="${lovModForm}">
                                                                <option value="${lovModForm.idLov}" ${lovModForm.idLov == requestScope.emp.iexmodform ? 'selected' : ''}>${lovModForm.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- SECCIÓN 3: Datos de Origen y Nacionalidad -->
                                        <div class="card mb-4 shadow-sm">
                                            <div class="card-header bg-light border-bottom">
                                                <h5 class="mb-0 text-primary"><i class="fas fa-globe-americas me-2"></i>Nacionalidad y Origen</h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row g-3">
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Nacionalidad <span class="text-danger">*</span></label>
                                                        <select name="iexnacion_origen" class="form-select" required>
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovNacionalidad" items="${lovNacionalidad}">
                                                                <option value="${lovNacionalidad.idLov}" ${lovNacionalidad.idLov == requestScope.emp.iexnacion_origen ? 'selected' : ''}>${lovNacionalidad.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">País emisor <span class="text-danger">*</span></label>
                                                        <select name="iexpaisemisor" id="iexpaisemisor" class="form-select" required>
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovPaisEmisor" items="${lovPaisEmisor}">
                                                                <option value="${lovPaisEmisor.idLov}" ${lovPaisEmisor.idLov == requestScope.emp.iexpaisemisor ? 'selected' : ''}>${lovPaisEmisor.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">País residencia</label>
                                                        <select name="iexcodlardist" class="form-select">
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovLarDistancia" items="${lovLarDistancia}">
                                                                <option value="${lovLarDistancia.idLov}" ${lovLarDistancia.idLov == requestScope.emp.iexcodlardist ? 'selected' : ''}>${lovLarDistancia.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <hr class="my-3">
                                                <h6 class="text-secondary mb-3"><i class="fas fa-map-marker-alt me-2"></i>Lugar de nacimiento</h6>
                                                <div class="row g-3">
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Departamento</label>
                                                        <select name="iexdepart_origen" id="iexdepart_origen" class="form-select">
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovDept_origen" items="${lovDept_origen}">
                                                                <option value="${lovDept_origen.idLov}" ${lovDept_origen.idLov == requestScope.emp.iexdepart_origen ? 'selected' : ''}>${lovDept_origen.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Provincia</label>
                                                        <select name="iexprovin_origen" id="iexprovin_origen" class="form-select">
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovProvin_origen" items="${lovProvin_origen}">
                                                                <option value="${lovProvin_origen.idLov}" ${lovProvin_origen.idLov == requestScope.emp.iexprovin_origen ? 'selected' : ''}>${lovProvin_origen.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Distrito</label>
                                                        <select name="iexdistri_origen" id="iexdistri_origen" class="form-select">
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovDist_origen" items="${lovDist_origen}">
                                                                <option value="${lovDist_origen.idLov}" ${lovDist_origen.idLov == requestScope.emp.iexdistri_origen ? 'selected' : ''}>${lovDist_origen.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-check">
                                                            <input type="checkbox" class="form-check-input" name="iexflgdomicil" value="1" ${requestScope.emp.iexflgdomicil=='1' ? 'checked=true' : ''} id="flexChecked" />
                                                            <label class="form-check-label fw-semibold" for="flexChecked">
                                                                <i class="fas fa-home me-1"></i>Es domiciliado
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- SECCIÓN 4: Formación Académica -->
                                        <div class="card mb-4 shadow-sm">
                                            <div class="card-header bg-light border-bottom">
                                                <h5 class="mb-0 text-primary"><i class="fas fa-graduation-cap me-2"></i>Formación Académica</h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row g-3">
                                                    <div class="col-md-6">
                                                        <label class="form-label fw-semibold">Grado de instrucción <span class="text-danger">*</span></label>
                                                        <select name="iexgrdinstruccion" class="form-select" required>
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovGrdInstruccion" items="${lovGrdInstruccion}">
                                                                <option value="${lovGrdInstruccion.idLov}" ${lovGrdInstruccion.idLov == requestScope.emp.iexgrdinstruccion ? 'selected' : ''}>${lovGrdInstruccion.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label fw-semibold">Centro de formación</label>
                                                        <select name="iexcentroform" class="form-select">
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovCenForm" items="${lovCenForm}">
                                                                <option value="${lovCenForm.idLov}" ${lovCenForm.idLov == requestScope.emp.iexcentroform ? 'selected' : ''}>${lovCenForm.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- SECCIÓN 5: Contacto -->
                                        <div class="card mb-4 shadow-sm">
                                            <div class="card-header bg-light border-bottom">
                                                <h5 class="mb-0 text-primary"><i class="fas fa-address-book me-2"></i>Información de Contacto</h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row g-3">
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Teléfono</label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-phone"></i></span>
                                                            <input class="form-control" name="iexnrotelf" maxlength="20" type="tel" value="${requestScope.emp.iexnrotelf}" placeholder="987 654 321" />
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Email personal</label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                                                            <input class="form-control" name="iexemail" type="email" value="${requestScope.emp.iexemail}" placeholder="empleado@gmail.com" />
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Email corporativo</label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-building"></i></span>
                                                            <input class="form-control" name="iexemail_coorp" type="email" value="${requestScope.emp.iexemail_coorp}" placeholder="empleado@empresa.com" />
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Alerta de éxito -->
                                        <div id="alert" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                                            <span class="fa-regular fa-check-circle text-success fs-3 me-3"></span>
                                            <p class="mb-0 fw-semi-bold text-1000 flex-grow-1">Los cambios se guardaron exitosamente</p>
                                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                        </div>

                                        <!-- Botones de acción -->
                                        <div class="card shadow-sm">
                                            <div class="card-body">
                                                <div class="d-flex justify-content-between align-items-center">
                                                    <div class="text-muted small">
                                                        <i class="fas fa-info-circle me-1"></i>
                                                        Los campos marcados con <span class="text-danger">*</span> son obligatorios
                                                    </div>
                                                    <div>
                                                        <a class="btn btn-outline-secondary me-2" href="listEmpleados">
                                                            <i class="fas fa-times me-2"></i>Cancelar
                                                        </a>
                                                        <button class="btn btn-primary px-5" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal">
                                                            <i class="fas fa-save me-2"></i>Guardar cambios
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Modal de confirmación -->
                                        <div class="modal fade" id="confirmModal" tabindex="-1">
                                            <div class="modal-dialog modal-dialog-centered">
                                                <div class="modal-content border-0 shadow">
                                                    <div class="modal-header bg-primary text-white border-0">
                                                        <h5 class="modal-title"><i class="fas fa-question-circle me-2"></i>Confirmar acción</h5>
                                                        <button class="btn-close btn-close-white" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body text-center py-4">
                                                        <div class="mb-3">
                                                            <i class="fas fa-exclamation-triangle text-warning" style="font-size: 3rem;"></i>
                                                        </div>
                                                        <h6 class="mb-2">¿Está seguro que desea guardar los cambios?</h6>
                                                        <p class="text-muted small mb-0">Esta acción actualizará la información del empleado</p>
                                                    </div>
                                                    <div class="modal-footer border-0 justify-content-center pb-4">
                                                        <button class="btn btn-outline-secondary px-4" type="button" data-bs-dismiss="modal">
                                                            <i class="fas fa-times me-2"></i>Cancelar
                                                        </button>
                                                        <button class="btn btn-primary px-5" onclick="mostrarAlert();" type="submit" data-bs-dismiss="modal">
                                                            <i class="fas fa-check me-2"></i>Confirmar
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
                     <!--
                      <div class="tab-content" id="myTabContent">
                          <div class="tab-pane fade" id="tab-notes" role="tabpanel" aria-labelledby="activity-tab">
                                <h3 class="mt-3 mb-0">Datos laborales</h3>
                                <div class="col-12 pe-3">
                                   <p class="mt-0 d-flex justify-content-end mb-0 fs--1"><strong>Ult. fec mod: </strong> <span class="ms-1">${emp2.iexfecmodlab}</span></p>
                                   <p class="mt-0 d-flex justify-content-end mb-0 fs--1"><strong>Ult. usu mod: </strong> <span class="ms-1">${emp2.iexusumodlab}</span></p>
                                </div>
                                <div class="row g-3 mt-0">
                                     <div class="col-xl-12">
                                       <div class="row gx-3 gy-4">
                                          <form class="row g-3 mb-0 needs-validation" method="POST" action="updateEmplDatLab" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />

                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Regimen laboral</label>
                                                <select class="form-select" name="iexreglab" data-choices="data-choices" data-options='{"removeItemButton":true,"placeholder":true}' required>
                                                  <option value="" selected >Seleccionar regimen</option>
                                                  <c:forEach var="lovRegLab" items="${lovRegLab}">
                                                      <option value="${lovRegLab.idLov}" ${lovRegLab.idLov == requestScope.emp2.iexreglab ? 'selected' : ''}  >${lovRegLab.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de trabajador</label>
                                                  <select class="form-select" name="iextiptra" required>
                                                    <option value="" selected >Seleccionar tipo trabajador</option>
                                                    <c:forEach var="lovTipTra" items="${lovTipTra}">
                                                        <option value="${lovTipTra.idLov}"   ${lovTipTra.idLov == requestScope.emp2.iextiptra ? 'selected' : ''}    >${lovTipTra.desLov}  </option>
                                                    </c:forEach>
                                                  </select>
                                            </div>
                                            <div class="col-sm-6 col-md-5">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Categoría trabajador</label>
                                                  <select class="form-select" name="iexcateg_trabajador" required>
                                                    <option value="" selected >Seleccionar categoria trabajador</option>
                                                    <c:forEach var="lovCateTra" items="${lovCateTra}">
                                                        <option value="${lovCateTra.idLov}"  ${lovCateTra.idLov == requestScope.emp2.iexcateg_trabajador ? 'selected' : ''}    > ${lovCateTra.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                            </div>
                                            <div class="col-sm-6 col-md-5">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Situación del pensionista</label>
                                                  <select class="form-select" name="iexsituapen" required>
                                                    <option value="" selected >Seleccionar situacion pensionista</option>
                                                    <c:forEach var="lovSitPen" items="${lovSitPen}">
                                                        <option value="${lovSitPen.idLov}"   ${lovSitPen.idLov == requestScope.emp2.iexsituapen ? 'selected' : ''}    > ${lovSitPen.desLov} </option>
                                                    </c:forEach>
                                                  </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de ingreso</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                  <input class="form-control datetimepicker" name="iexfecing" id="iexfecing" onchange="formatearFecha2();" value="${requestScope.emp2.iexfecing}" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required />
                                                  <input class="form-control" id="iexfecinghidden" type="hidden" value="${requestScope.emp2.iexfecing}" />
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de retiro</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                  <input class="form-control datetimepicker" name="iexfecret" id="iexfecret" onchange="formatearFecha3();" value="${requestScope.emp2.iexfecret}" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required />
                                                  <input class="form-control" id="iexfecrethidden" type="hidden" value="${requestScope.emp2.iexfecret}" />
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de contrato</label>
                                                  <select class="form-select" name="iextipcont" required>
                                                    <option value="" selected >Seleccionar tipo contrato</option>
                                                    <c:forEach var="lovTipCont" items="${lovTipCont}">
                                                        <option value="${lovTipCont.idLov}"  ${lovTipCont.idLov == requestScope.emp2.iextipcont ? 'selected' : ''}   >${lovTipCont.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fec ini contrato</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                  <input class="form-control datetimepicker" name="iexfecini_cont" id="iexfecini_cont" onchange="formatearFecha4();" value="${requestScope.emp2.iexfecini_cont}" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' />
                                                  <input class="form-control" id="iexfecini_conthidden" type="hidden" value="${requestScope.emp2.iexfecini_cont}" />
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fec fin contrato</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                  <input class="form-control datetimepicker" name="iexfecfin_cont" id="iexfecfin_cont" onchange="formatearFecha5();"value="${requestScope.emp2.iexfecfin_cont}" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' />
                                                  <input class="form-control" id="iexfecfin_conthidden" type="hidden" value="${requestScope.emp2.iexfecfin_cont}" />
                                            </div>
                                            <div class="col-sm-6 col-md-8">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Pliego</label>
                                                  <select class="form-select" name="iexpliego" id="organizerSingle" data-choices="data-choices" data-options='{"removeItemButton":true,"placeholder":true}' >
                                                    <option value="" selected >Seleccionar pliego</option>
                                                    <c:forEach var="lovPliego" items="${lovPliego}">
                                                        <option value="${lovPliego.idLov}" ${lovPliego.idLov == requestScope.emp2.iexpliego ? 'selected' : ''} >${lovPliego.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                            </div>
                                            <div class="col-sm-6 col-md-5">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Situación especial</label>
                                                  <select class="form-select" name="iexsituaesp" required >
                                                    <option value="" selected >Seleccionar situacion especial</option>
                                                    <c:forEach var="lovSituesp" items="${lovSituesp}">
                                                        <option value="${lovSituesp.idLov}"   ${lovSituesp.idLov == requestScope.emp2.iexsituaesp ? 'selected' : ''}  >${lovSituesp.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                            </div>
                                            <div class="col-sm-6 col-md-7">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Ocupación reg. público</label>
                                                  <select class="form-select" name="iexocupacion_pub" id="organizerSingle" data-choices="data-choices" data-options='{"removeItemButton":true,"placeholder":true}' >
                                                    <option value="" selected >Seleccionar ocupacion reg. publico</option>
                                                    <c:forEach var="lovOcupRegPub" items="${lovOcupRegPub}">
                                                        <option value="${lovOcupRegPub.idLov}"  ${lovOcupRegPub.idLov == requestScope.emp2.iexocupacion_pub ? 'selected' : ''}  >${lovOcupRegPub.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                            </div>
                                            <div class="col-sm-6 col-md-7">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Ocupación reg. privado</label>
                                                  <select class="form-select" name="iexocupacion_priv" id="organizerSingle" data-choices="data-choices" data-options='{"removeItemButton":true,"placeholder":true}' >
                                                    <option value="" selected >Seleccionar ocupacion reg. privado</option>
                                                    <c:forEach var="lovOcupRegPrv" items="${lovOcupRegPrv}">
                                                        <option value="${lovOcupRegPrv.idLov}"  ${lovOcupRegPrv.idLov == requestScope.emp2.iexocupacion_priv ? 'selected' : ''}    >${lovOcupRegPrv.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Área</label>
                                                  <select class="form-select" name="iexarea" required>
                                                    <option value="" selected >Seleccionar area</option>
                                                    <c:forEach var="lovArea" items="${lovArea}">
                                                        <option value="${lovArea.iexcodarea}" ${lovArea.iexcodarea == requestScope.emp2.iexarea ? 'selected' : ''}     >${lovArea.iexdesarea}</option>
                                                    </c:forEach>
                                                  </select>
                                            </div>
                                            <div class="col-sm-6 col-md-5">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Puesto</label>
                                                  <select class="form-select" name="iexpuesto" data-choices="data-choices" data-options='{"removeItemButton":true,"placeholder":true}' required>
                                                    <option value="" selected >Seleccionar puesto</option>
                                                    <c:forEach var="lovPuesto" items="${lovPuesto}"  >
                                                        <option value="${lovPuesto.iexpuesto}" ${lovPuesto.iexpuesto == requestScope.emp2.iexpuesto ? 'selected' : ''}  >${lovPuesto.iexdespuesto}</option>
                                                    </c:forEach>
                                                  </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Centro de costos</label>
                                                  <select class="form-select" name="iexccosto" required>
                                                    <option value="" selected >Seleccionar centro costo</option>
                                                    <c:forEach var="lovCcosto" items="${lovCcosto}">
                                                        <option value="${lovCcosto.iexccosto}"   ${lovCcosto.iexccosto == requestScope.emp2.iexccosto ? 'selected' : ''}   >${lovCcosto.iexdesccosto}</option>
                                                    </c:forEach>
                                                  </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Local</label>
                                                <select class="form-select" name="iexubilocal" required>
                                                    <option value="" selected >Seleccionar local</option>
                                                    <c:forEach var="lovUbicacion" items="${lovUbicacion}">
                                                        <option value="${lovUbicacion.iexubicod}"  ${lovUbicacion.iexubicod == requestScope.emp2.iexubilocal ? 'selected' : ''}  >  ${lovUbicacion.iexubides}  </option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div id="alertLab" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                                            	<span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                                            	<p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                                            	<a class="text-success fs-0 fw-bold" href="#" data-bs-dismiss="alert" aria-label="Close">x</a>
                                            </div>
                                            <div class="col-12 d-flex justify-content-end mt-6">
                                                <div class="col-sm-6 col-md-6">
                                                    <a class="btn btn-phoenix-primary" href="listEmpleados">Cancel</a>
                                                    <button class="btn btn-primary col-6" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal2" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar</button>
                                                </div>
                                            </div>
                                            <div class="modal fade" id="confirmModal2" tabindex="-1">
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
                                            		  <button class="btn btn-sm btn-primary px-9 my-0 mt-1" onclick="mostrarAlertLab();" type="submit" data-bs-dismiss="modal" >Confirmar</button>
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
                      -->
                     <div class="tab-content" id="myTabContent">
                          <div class="tab-pane fade" id="tab-notes" role="tabpanel" aria-labelledby="activity-tab">
                                <h3 class="mt-3 mb-0">Datos laborales</h3>
                                <div class="col-12 pe-3">
                                   <p class="mt-0 d-flex justify-content-end mb-0 fs--1"><strong>Ult. fec mod: </strong> <span class="ms-1">${emp2.iexfecmodlab}</span></p>
                                   <p class="mt-0 d-flex justify-content-end mb-0 fs--1"><strong>Ult. usu mod: </strong> <span class="ms-1">${emp2.iexusumodlab}</span></p>
                                </div> 
                                <div class="row g-3 mt-0">
                                    <div class="col-12">
                                        <form class="needs-validation" method="POST" action="updateEmplDatLab" novalidate>
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />

                                            <!-- SECCIÓN 1: Régimen y Clasificación -->
                                            <div class="card mb-4 shadow-sm">
                                                <div class="card-header bg-light border-bottom">
                                                    <h5 class="mb-0 text-primary"><i class="fas fa-briefcase me-2"></i>Régimen y Clasificación Laboral</h5>
                                                </div>
                                                <div class="card-body">
                                                    <div class="row g-3">
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Régimen laboral <span class="text-danger">*</span></label>
                                                            <select class="form-select" name="iexreglab" data-choices="data-choices" data-options='{"removeItemButton":true,"placeholder":true}' required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovRegLab" items="${lovRegLab}">
                                                                    <option value="${lovRegLab.idLov}" ${lovRegLab.idLov == requestScope.emp2.iexreglab ? 'selected' : ''}>${lovRegLab.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Tipo de trabajador <span class="text-danger">*</span></label>
                                                            <select class="form-select" name="iextiptra" required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovTipTra" items="${lovTipTra}">
                                                                    <option value="${lovTipTra.idLov}" ${lovTipTra.idLov == requestScope.emp2.iextiptra ? 'selected' : ''}>${lovTipTra.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Categoría de trabajador <span class="text-danger">*</span></label>
                                                            <select class="form-select" name="iexcateg_trabajador" required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovCateTra" items="${lovCateTra}">
                                                                    <option value="${lovCateTra.idLov}" ${lovCateTra.idLov == requestScope.emp2.iexcateg_trabajador ? 'selected' : ''}>${lovCateTra.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Situación del pensionista <span class="text-danger">*</span></label>
                                                            <select class="form-select" name="iexsituapen" required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovSitPen" items="${lovSitPen}">
                                                                    <option value="${lovSitPen.idLov}" ${lovSitPen.idLov == requestScope.emp2.iexsituapen ? 'selected' : ''}>${lovSitPen.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- SECCIÓN 2: Fechas de Ingreso y Retiro -->
                                            <div class="card mb-4 shadow-sm">
                                                <div class="card-header bg-light border-bottom">
                                                    <h5 class="mb-0 text-primary"><i class="fas fa-calendar-check me-2"></i>Fechas de Ingreso y Retiro</h5>
                                                </div>
                                                <div class="card-body">
                                                    <div class="row g-3">
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Fecha de ingreso <span class="text-danger">*</span></label>
                                                            <div class="input-group">
                                                                <input class="form-control datetimepicker" name="iexfecing" id="iexfecing" onchange="formatearFecha2();" value="${requestScope.emp2.iexfecing}" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required />
                                                                <span class="input-group-text"><i class="uil uil-calendar-alt"></i></span>
                                                            </div>
                                                            <input class="form-control" id="iexfecinghidden" type="hidden" value="${requestScope.emp2.iexfecing}" />
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Fecha de retiro</label>
                                                            <div class="input-group">
                                                                <input class="form-control datetimepicker" name="iexfecret" id="iexfecret" onchange="formatearFecha3();" value="${requestScope.emp2.iexfecret}" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' />
                                                                <span class="input-group-text"><i class="uil uil-calendar-alt"></i></span>
                                                            </div>
                                                            <input class="form-control" id="iexfecrethidden" type="hidden" value="${requestScope.emp2.iexfecret}" />
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- SECCIÓN 3: Información del Contrato -->
                                            <div class="card mb-4 shadow-sm">
                                                <div class="card-header bg-light border-bottom">
                                                    <h5 class="mb-0 text-primary"><i class="fas fa-file-contract me-2"></i>Información del Contrato</h5>
                                                </div>
                                                <div class="card-body">
                                                    <div class="row g-3">
                                                        <div class="col-md-12">
                                                            <label class="form-label fw-semibold">Tipo de contrato <span class="text-danger">*</span></label>
                                                            <select class="form-select" name="iextipcont" required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovTipCont" items="${lovTipCont}">
                                                                    <option value="${lovTipCont.idLov}" ${lovTipCont.idLov == requestScope.emp2.iextipcont ? 'selected' : ''}>${lovTipCont.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Fecha inicio contrato</label>
                                                            <div class="input-group">
                                                                <input class="form-control datetimepicker" name="iexfecini_cont" id="iexfecini_cont" onchange="formatearFecha4();" value="${requestScope.emp2.iexfecini_cont}" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' />
                                                                <span class="input-group-text"><i class="uil uil-calendar-alt"></i></span>
                                                            </div>
                                                            <input class="form-control" id="iexfecini_conthidden" type="hidden" value="${requestScope.emp2.iexfecini_cont}" />
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Fecha fin contrato</label>
                                                            <div class="input-group">
                                                                <input class="form-control datetimepicker" name="iexfecfin_cont" id="iexfecfin_cont" onchange="formatearFecha5();" value="${requestScope.emp2.iexfecfin_cont}" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' />
                                                                <span class="input-group-text"><i class="uil uil-calendar-alt"></i></span>
                                                            </div>
                                                            <input class="form-control" id="iexfecfin_conthidden" type="hidden" value="${requestScope.emp2.iexfecfin_cont}" />
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- SECCIÓN 4: Situación y Ocupación -->
                                            <div class="card mb-4 shadow-sm">
                                                <div class="card-header bg-light border-bottom">
                                                    <h5 class="mb-0 text-primary"><i class="fas fa-clipboard-list me-2"></i>Situación y Ocupación</h5>
                                                </div>
                                                <div class="card-body">
                                                    <div class="row g-3">
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Pliego</label>
                                                            <select class="form-select" name="iexpliego" data-choices="data-choices" data-options='{"removeItemButton":true,"placeholder":true}'>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovPliego" items="${lovPliego}">
                                                                    <option value="${lovPliego.idLov}" ${lovPliego.idLov == requestScope.emp2.iexpliego ? 'selected' : ''}>${lovPliego.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Situación especial <span class="text-danger">*</span></label>
                                                            <select class="form-select" name="iexsituaesp" required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovSituesp" items="${lovSituesp}">
                                                                    <option value="${lovSituesp.idLov}" ${lovSituesp.idLov == requestScope.emp2.iexsituaesp ? 'selected' : ''}>${lovSituesp.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Ocupación régimen público</label>
                                                            <select class="form-select" name="iexocupacion_pub" data-choices="data-choices" data-options='{"removeItemButton":true,"placeholder":true}'>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovOcupRegPub" items="${lovOcupRegPub}">
                                                                    <option value="${lovOcupRegPub.idLov}" ${lovOcupRegPub.idLov == requestScope.emp2.iexocupacion_pub ? 'selected' : ''}>${lovOcupRegPub.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Ocupación régimen privado</label>
                                                            <select class="form-select" name="iexocupacion_priv" data-choices="data-choices" data-options='{"removeItemButton":true,"placeholder":true}'>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovOcupRegPrv" items="${lovOcupRegPrv}">
                                                                    <option value="${lovOcupRegPrv.idLov}" ${lovOcupRegPrv.idLov == requestScope.emp2.iexocupacion_priv ? 'selected' : ''}>${lovOcupRegPrv.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- SECCIÓN 5: Asignación Organizacional -->
                                            <div class="card mb-4 shadow-sm">
                                                <div class="card-header bg-light border-bottom">
                                                    <h5 class="mb-0 text-primary"><i class="fas fa-sitemap me-2"></i>Asignación Organizacional</h5>
                                                </div>
                                                <div class="card-body">
                                                    <div class="row g-3">
                                                        <div class="col-md-4">
                                                            <label class="form-label fw-semibold">Área <span class="text-danger">*</span></label>
                                                            <select class="form-select" name="iexarea" required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovArea" items="${lovArea}">
                                                                    <option value="${lovArea.iexcodarea}" ${lovArea.iexcodarea == requestScope.emp2.iexarea ? 'selected' : ''}>${lovArea.iexdesarea}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label fw-semibold">Puesto <span class="text-danger">*</span></label>
                                                            <select class="form-select" name="iexpuesto" data-choices="data-choices" data-options='{"removeItemButton":true,"placeholder":true}' required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovPuesto" items="${lovPuesto}">
                                                                    <option value="${lovPuesto.iexpuesto}" ${lovPuesto.iexpuesto == requestScope.emp2.iexpuesto ? 'selected' : ''}>${lovPuesto.iexdespuesto}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label fw-semibold">Centro de costos <span class="text-danger">*</span></label>
                                                            <select class="form-select" name="iexccosto" required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovCcosto" items="${lovCcosto}">
                                                                    <option value="${lovCcosto.iexccosto}" ${lovCcosto.iexccosto == requestScope.emp2.iexccosto ? 'selected' : ''}>${lovCcosto.iexdesccosto}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-12">
                                                            <label class="form-label fw-semibold">Local / Ubicación <span class="text-danger">*</span></label>
                                                            <select class="form-select" name="iexubilocal" required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovUbicacion" items="${lovUbicacion}">
                                                                    <option value="${lovUbicacion.iexubicod}" ${lovUbicacion.iexubicod == requestScope.emp2.iexubilocal ? 'selected' : ''}>${lovUbicacion.iexubides}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Alerta de éxito -->
                                            <div id="alertLab" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                                                <span class="fa-regular fa-check-circle text-success fs-3 me-3"></span>
                                                <p class="mb-0 fw-semi-bold text-1000 flex-grow-1">Los cambios se guardaron exitosamente</p>
                                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                            </div>

                                            <!-- Botones de acción -->
                                            <div class="card shadow-sm">
                                                <div class="card-body">
                                                    <div class="d-flex justify-content-between align-items-center">
                                                        <div class="text-muted small">
                                                            <i class="fas fa-info-circle me-1"></i>
                                                            Los campos marcados con <span class="text-danger">*</span> son obligatorios
                                                        </div>
                                                        <div>
                                                            <a class="btn btn-outline-secondary me-2" href="listEmpleados">
                                                                <i class="fas fa-times me-2"></i>Cancelar
                                                            </a>
                                                            <button class="btn btn-primary px-5" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal2">
                                                                <i class="fas fa-save me-2"></i>Guardar cambios
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Modal de confirmación -->
                                            <div class="modal fade" id="confirmModal2" tabindex="-1">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content border-0 shadow">
                                                        <div class="modal-header bg-primary text-white border-0">
                                                            <h5 class="modal-title"><i class="fas fa-question-circle me-2"></i>Confirmar acción</h5>
                                                            <button class="btn-close btn-close-white" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body text-center py-4">
                                                            <div class="mb-3">
                                                                <i class="fas fa-exclamation-triangle text-warning" style="font-size: 3rem;"></i>
                                                            </div>
                                                            <h6 class="mb-2">¿Está seguro que desea guardar los cambios?</h6>
                                                            <p class="text-muted small mb-0">Esta acción actualizará la información laboral del empleado</p>
                                                        </div>
                                                        <div class="modal-footer border-0 justify-content-center pb-4">
                                                            <button class="btn btn-outline-secondary px-4" type="button" data-bs-dismiss="modal">
                                                                <i class="fas fa-times me-2"></i>Cancelar
                                                            </button>
                                                            <button class="btn btn-primary px-5" onclick="mostrarAlertLab();" type="submit" data-bs-dismiss="modal">
                                                                <i class="fas fa-check me-2"></i>Confirmar
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

                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade" id="tab-meeting" role="tabpanel" aria-labelledby="activity-tab">
                                <h3 class="mt-3 mb-0">Informacion de pago</h3>
                                <div class="col-12 pe-3">
                                   <p class="mt-0 d-flex justify-content-end mb-0 fs--1"><strong>Ult. fec mod: </strong> <span class="ms-1">${emp3.iexfecmodpag}</span></p>
                                   <p class="mt-0 d-flex justify-content-end mb-0 fs--1"><strong>Ult. usu mod: </strong> <span class="ms-1">${emp3.iexusumodpag}</span></p>
                                </div>
                                 <div class="row g-3 mt-0">

                                     <div class="col-12">
                                          <form class="needs-validation" method="POST" action="updateInfoPago" novalidate>
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                                            <!-- SECCIÓN 1: Tipo y periodo -->
                                            <div class="card mb-4 shadow-sm">
                                                <div class="card-header bg-light border-bottom">
                                                    <h5 class="mb-0 text-primary">
                                                        <i class="fas fa-calendar-alt me-2"></i>      
                                                         Tipo y periodo
                                                    </h5>
                                                </div>
                                                <div class="card-body">
                                                    <div class="row g-2">
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">
                                                                Tipo de pago
                                                            </label>
                                                            <select class="form-select" name="iextippago" required>
                                                                <option value="" selected >Seleccionar tipo pago</option>
                                                                    <c:forEach var="lovTipPago" items="${lovTipPago}">
                                                                        <option value="${lovTipPago.idLov}"   ${lovTipPago.idLov == requestScope.emp3.iextippago ? 'selected' : ''}    >${lovTipPago.desLov}</option>
                                                                    </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">
                                                                Periodo de remuneración
                                                            </label>
                                                            <select class="form-select" name="iexperrem" required>
                                                                <option value="" selected >Seleccionar periodo remuneracion</option>
                                                                <c:forEach var="lovPerRem" items="${lovPerRem}">
                                                                    <option value="${lovPerRem.idLov}"   ${lovPerRem.idLov == requestScope.emp3.iexperrem ? 'selected' : ''}     >${lovPerRem.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- SECCIÓN 2: Datos bancarios para haberes -->
                                            <div class="card mb-4 shadow-sm">
                                                <div class="card-header bg-light border-bottom">
                                                    <h5 class="mb-0 text-primary">
                                                        <i class="fas fa-university me-2"></i>      
                                                            Datos bancarios para haberes
                                                    </h5>
                                                </div>
                                                <div class="card-body">
                                                    <div class="row g-2">
                                                        <div class="col-md-4">
                                                            <label class="form-label fw-semibold">
                                                                Banco de haberes
                                                            </label>
                                                            <select class="form-select" name="iexcodban_hab" required>
                                                                <option value="" selected >Seleccionar banco</option>
                                                                <c:forEach var="lovBancoHab" items="${lovBancoHab}">
                                                                    <option value="${lovBancoHab.idLov}"   ${lovBancoHab.idLov == requestScope.emp3.iexcodban_hab ? 'selected' : ''}   >${lovBancoHab.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label fw-semibold">
                                                                Tipo de cuenta de haberes
                                                            </label>
                                                            <select class="form-select" name="iextipban_hab" required>
                                                                <option value="" selected >Seleccionar tipo cuenta</option>
                                                                <c:forEach var="lovTipCtaHab" items="${lovTipCtaHab}">
                                                                    <option value="${lovTipCtaHab.idLov}" ${lovTipCtaHab.idLov == requestScope.emp3.iextipban_hab ? 'selected' : ''}   >${lovTipCtaHab.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label fw-semibold">
                                                                Moneda de haberes
                                                            </label>
                                                               <select class="form-select" name="iexcodmon_hab" required>
                                                                    <option value="" selected >Seleccionar moneda</option>
                                                                    <c:forEach var="lovMonedaHab" items="${lovMonedaHab}">
                                                                        <option value="${lovMonedaHab.idLov}"    ${lovMonedaHab.idLov == requestScope.emp3.iexcodmon_hab ? 'selected' : ''}    >${lovMonedaHab.desLov}</option>
                                                                    </c:forEach>
                                                                </select>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Nro de cuenta bancaria</label>
                                                            <input class="form-control" name="iexnrocta_hab" type="number" maxlength="50" value="${requestScope.emp3.iexnrocta_hab}" placeholder="Ingrese el número de cuenta bancaria" />
                                                        </div>
                                                        <div class="col-md-6">
                                                            <input class="form-check-input" name="iexflgbancci_hab" id="flexChecked" value="1" ${requestScope.emp3.iexflgbancci_hab=='1' ? 'checked=true' : ''}  type="checkbox"/>
                                                            <label class="form-check-label ms-2" for="flexChecked">Nro cuenta haberes es interbancaria?</label> 
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- SECCIÓN 3: Datos bancarios para CTS -->
                                             <div class="card mb-4 shadow-sm">
                                                <div class="card-header bg-light border-bottom">
                                                    <h5 class="mb-0 text-primary">
                                                        <i class="fas fa-university me-2"></i>      
                                                            Datos bancarios para CTS
                                                    </h5>
                                                </div>
                                                <div class="card-body">
                                                    <div class="row g-2">
                                                        <div class="col-md-4">
                                                            <label class="form-label fw-semibold">
                                                                Banco de cts
                                                            </label>
                                                              <select class="form-select" name="iexcodban_cts" >
                                                                    <option value="" selected >Seleccionar banco cts</option>
                                                                    <c:forEach var="lovBancoCts" items="${lovBancoCts}">
                                                                        <option value="${lovBancoCts.idLov}" ${lovBancoCts.idLov == requestScope.emp3.iexcodban_cts ? 'selected' : ''}   >${lovBancoCts.desLov}</option>
                                                                    </c:forEach>
                                                                </select>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label fw-semibold">
                                                                Tipo de cuenta de cts
                                                            </label>
                                                             <select class="form-select" name="iextipban_cts" >
                                                                <option value="" selected >Seleccionar tipo cuenta cts</option>
                                                                <c:forEach var="lovTipCtaCts" items="${lovTipCtaCts}">
                                                                    <option value="${lovTipCtaCts.idLov}"  ${lovTipCtaCts.idLov == requestScope.emp3.iextipban_cts ? 'selected' : ''}  >${lovTipCtaCts.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label fw-semibold">
                                                                Moneda de cts
                                                            </label>
                                                                <select class="form-select" name="iexcodmon_cts" >
                                                                    <option value="" selected >Seleccionar moneda cts</option>
                                                                    <c:forEach var="lovMonedaCts" items="${lovMonedaCts}">
                                                                        <option value="${lovMonedaCts.idLov}"     ${lovMonedaCts.idLov == requestScope.emp3.iexcodmon_cts ? 'selected' : ''}    >${lovMonedaCts.desLov}</option>
                                                                    </c:forEach>
                                                                </select>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label fw-semibold">Nro cuenta cts</label>
                                                            <input id="validationCustom01" class="form-control" type="number" maxlength="50" name="iexnrocta_cts" value="${requestScope.emp3.iexnrocta_cts}" placeholder="Ingrese el número de cuenta CTS" />
                                                        </div>
                                                        <div class="col-md-6">
                                                            <input class="form-check-input" name="iexflgbancci_cts" id="flexChecked" value="1" ${requestScope.emp3.iexflgbancci_cts=='1' ? 'checked=true' : ''} type="checkbox"/>
                                                            <label class="form-check-label ms-2" for="flexChecked">Nro cuenta CTS es interbancaria?</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Alerta de éxito -->
                                            <div id="alertPago" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                                            	<span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                                            	<p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                            </div>
                                            
                                         
                                             <!-- Botones de acción -->
                                            <div class="card shadow-sm">
                                                <div class="card-body">
                                                    <div class="d-flex justify-content-between align-items-center">
                                                        <div class="text-muted small">
                                                            <i class="fas fa-info-circle me-1"></i>
                                                            Los campos marcados con <span class="text-danger">*</span> son obligatorios
                                                        </div>
                                                        <div>
                                                            <a class="btn btn-outline-secondary me-2" href="listEmpleados">
                                                                <i class="fas fa-times me-2"></i>Cancelar
                                                            </a>
                                                            <button class="btn btn-primary px-5" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal3">
                                                                <i class="fas fa-save me-2"></i>Guardar cambios
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Modal de confirmación -->
                                            <div class="modal fade" id="confirmModal3" tabindex="-1">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content border-0 shadow">
                                                        <div class="modal-header bg-primary text-white border-0">
                                                            <h5 class="modal-title"><i class="fas fa-question-circle me-2"></i>Confirmar acción</h5>
                                                            <button class="btn-close btn-close-white" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body text-center py-4">
                                                            <div class="mb-3">
                                                                <i class="fas fa-exclamation-triangle text-warning" style="font-size: 3rem;"></i>
                                                            </div>
                                                            <h6 class="mb-2">¿Está seguro que desea guardar los cambios?</h6>
                                                            <p class="text-muted small mb-0">Esta acción actualizará la información laboral del empleado</p>
                                                        </div>
                                                        <div class="modal-footer border-0 justify-content-center pb-4">
                                                            <button class="btn btn-outline-secondary px-4" type="button" data-bs-dismiss="modal">
                                                                <i class="fas fa-times me-2"></i>Cancelar
                                                            </button>
                                                            <button class="btn btn-primary px-5" onclick="mostrarAlertPago();" type="submit" data-bs-dismiss="modal">
                                                                <i class="fas fa-check me-2"></i>Confirmar
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

                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade" id="tab-task" role="tabpanel" aria-labelledby="activity-tab">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h3 class="mb-0">Seguridad social</h3>
                                        <div class="text-end">
                                            <p class="mb-0 fs--1 text-muted"><strong>Ult. fec mod:</strong> ${emp4.iexfecmodseg}</p>
                                            <p class="mb-0 fs--1 text-muted"><strong>Ult. usu mod:</strong> ${emp4.iexusumodseg}</p>
                                        </div>
                                    </div>

                                    <form class="needs-validation" method="POST" action="updateSegurSocial" novalidate>
                                        <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                        <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />

                                        <!-- Sistema de Pensiones -->
                                        <div class="card mb-3 shadow-sm">
                                            <div class="card-header bg-primary-subtle border-0 py-3">
                                                <h5 class="mb-0 text-primary">
                                                    <i class="fas fa-university me-2"></i>Sistema de Pensiones
                                                </h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row g-3">
                                                    <div class="col-md-12">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexflgjubil" id="iexflgjubil" value="1" ${requestScope.emp4.iexflgjubil=='1' ? 'checked' : ''} type="checkbox"/>
                                                            <label class="form-check-label fw-semibold" for="iexflgjubil">Es jubilado</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label fw-semibold">Fondo de pensiones <span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-piggy-bank"></i></span>
                                                            <select class="form-select" name="iexcodafp" required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovCodAfp" items="${lovCodAfp}">
                                                                    <option value="${lovCodAfp.idLov}" ${lovCodAfp.idLov == requestScope.emp4.iexcodafp ? 'selected' : ''}>${lovCodAfp.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label fw-semibold">Fecha inicio fondo de pensiones <span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-calendar"></i></span>
                                                            <input class="form-control datetimepicker" name="iexfecafp" id="iexfecafp" onchange="formatearFecha6();" value="${requestScope.emp4.iexfecafp}" type="text" placeholder="DD/MM/YYYY" data-options='{"disableMobile":true}' required />
                                                            <input class="form-control" id="iexfecafphidden" type="hidden" value="${requestScope.emp4.iexfecafp}" />
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexflgcomi_mix" id="iexflgcomi_mix" value="1" ${requestScope.emp4.iexflgcomi_mix=='1' ? 'checked' : ''} type="checkbox" />
                                                            <label class="form-check-label fw-semibold" for="iexflgcomi_mix">Comisión mixta</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label fw-semibold">CUSSP <span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-id-card"></i></span>
                                                            <input class="form-control" maxlength="50" name="iexcussp" value="${requestScope.emp4.iexcussp}" type="text" placeholder="Ingrese el número de CUSSP" required/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Seguro de Salud -->
                                        <div class="card mb-3 shadow-sm">
                                            <div class="card-header bg-success-subtle border-0 py-3">
                                                <h5 class="mb-0 text-primary">
                                                    <i class="fas fa-heartbeat me-2"></i>Seguro de Salud
                                                </h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row g-3">
                                                    <div class="col-md-6">
                                                        <label class="form-label fw-semibold">EsSalud <span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-hospital"></i></span>
                                                            <select class="form-select" name="iexessalud" required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovEssalud" items="${lovEssalud}">
                                                                    <option value="${lovEssalud.idLov}" ${lovEssalud.idLov == requestScope.emp4.iexessalud ? 'selected' : ''}>${lovEssalud.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexflgeps" id="iexflgeps" type="checkbox" value="1" ${requestScope.emp4.iexflgeps=='1' ? 'checked' : ''} />
                                                            <label class="form-check-label fw-semibold" for="iexflgeps">Tiene EPS</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label fw-semibold">Proveedor de EPS</label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-clinic-medical"></i></span>
                                                            <select class="form-select" id="iexcodeps" name="iexcodeps">
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovProvEps" items="${requestScope.lovProvEps}">
                                                                    <option value="${lovProvEps.idLov}" ${lovProvEps.idLov == requestScope.emp4.iexcodeps ? 'selected' : ''}>${lovProvEps.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Beneficios y Condiciones Especiales -->
                                        <div class="card mb-3 shadow-sm">
                                            <div class="card-header bg-info-subtle border-0 py-3">
                                                <h5 class="mb-0 text-info">
                                                    <i class="fas fa-shield-alt me-2"></i>Beneficios y Condiciones Especiales
                                                </h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row g-3">
                                                    <div class="col-md-6">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexsenati" id="iexsenati" value="1" ${requestScope.emp4.iexsenati=='1' ? 'checked' : ''} type="checkbox" />
                                                            <label class="form-check-label fw-semibold" for="iexsenati">SENATI</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexflgmas_vida" id="iexflgmas_vida" value="1" ${requestScope.emp4.iexflgmas_vida=='1' ? 'checked' : ''} type="checkbox" />
                                                            <label class="form-check-label fw-semibold" for="iexflgmas_vida">+Vida</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexconvdobtrib" id="iexconvdobtrib" value="1" ${requestScope.emp4.iexconvdobtrib=='1' ? 'checked' : ''} type="checkbox" />
                                                            <label class="form-check-label fw-semibold" for="iexconvdobtrib">Convenio para evitar doble tributación</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexdiscapacidad" id="iexdiscapacidad" value="1" ${requestScope.emp4.iexdiscapacidad=='1' ? 'checked' : ''} type="checkbox" />
                                                            <label class="form-check-label fw-semibold" for="iexdiscapacidad">Discapacidad</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexregalter" id="iexregalter" value="1" ${requestScope.emp4.iexregalter=='1' ? 'checked' : ''} type="checkbox" />
                                                            <label class="form-check-label fw-semibold" for="iexregalter">Régimen alternativo</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexsctrpension" id="iexsctrpension" value="1" ${requestScope.emp4.iexsctrpension=='1' ? 'checked' : ''} type="checkbox" />
                                                            <label class="form-check-label fw-semibold" for="iexsctrpension">SCTR Pensión</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexmadreresp" id="iexmadreresp" type="checkbox" value="1" ${requestScope.emp4.iexmadreresp=='1' ? 'checked' : ''}/>
                                                            <label class="form-check-label fw-semibold" for="iexmadreresp">Madre de responsabilidad limitada</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexexon5ta" id="iexexon5ta" value="1" ${requestScope.emp4.iexexon5ta=='1' ? 'checked' : ''} type="checkbox" />
                                                            <label class="form-check-label fw-semibold" for="iexexon5ta">Exoneración 5ta categoría</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Condiciones Laborales -->
                                        <div class="card mb-3 shadow-sm">
                                            <div class="card-header bg-warning-subtle border-0 py-3">
                                                <h5 class="mb-0 text-primary">
                                                    <i class="fas fa-briefcase me-2"></i>Condiciones Laborales
                                                </h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row g-3">
                                                    <div class="col-md-4">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexjornmax" id="iexjornmax" value="1" ${requestScope.emp4.iexjornmax=='1' ? 'checked' : ''} type="checkbox" />
                                                            <label class="form-check-label fw-semibold" for="iexjornmax">Jornada máxima</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexhornocturno" id="iexhornocturno" value="1" ${requestScope.emp4.iexhornocturno=='1' ? 'checked' : ''} type="checkbox" />
                                                            <label class="form-check-label fw-semibold" for="iexhornocturno">Horario nocturno</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-check">
                                                            <input class="form-check-input" name="iexsindicalizado" id="iexsindicalizado" value="1" ${requestScope.emp4.iexsindicalizado=='1' ? 'checked' : ''} type="checkbox" />
                                                            <label class="form-check-label fw-semibold" for="iexsindicalizado">Sindicalizado</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label fw-semibold">Nro de RUC CAS</label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-file-invoice"></i></span>
                                                            <input class="form-control" name="iexnroruc_cas" type="text" maxlength="11" value="${requestScope.emp4.iexnroruc_cas}" placeholder="Ingrese el RUC CAS" />
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Alerta de éxito -->
                                        <div id="alertSeg" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                                            <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                                            <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios</p>
                                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                        </div>

                                        <!-- Botones de acción -->
                                        <div class="card shadow-sm">
                                            <div class="card-body">
                                                <div class="d-flex justify-content-between align-items-center">
                                                    <div class="text-muted small">
                                                        <i class="fas fa-info-circle me-1"></i>
                                                        Los campos marcados con <span class="text-danger">*</span> son obligatorios
                                                    </div>
                                                    <div>
                                                        <a class="btn btn-outline-secondary me-2" href="listEmpleados">
                                                            <i class="fas fa-times me-2"></i>Cancelar
                                                        </a>
                                                        <button class="btn btn-primary px-5" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal4">
                                                            <i class="fas fa-save me-2"></i>Guardar cambios
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Modal de confirmación -->
                                        <div class="modal fade" id="confirmModal4" tabindex="-1">
                                            <div class="modal-dialog modal-dialog-centered">
                                                <div class="modal-content border-0 shadow">
                                                    <div class="modal-header bg-primary text-white border-0">
                                                        <h5 class="modal-title"><i class="fas fa-question-circle me-2"></i>Confirmar acción</h5>
                                                        <button class="btn-close btn-close-white" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body text-center py-4">
                                                        <div class="mb-3">
                                                            <i class="fas fa-exclamation-triangle text-warning" style="font-size: 3rem;"></i>
                                                        </div>
                                                        <h6 class="mb-2">¿Está seguro que desea guardar los cambios?</h6>
                                                        <p class="text-muted small mb-0">Esta acción actualizará la información de seguridad social del empleado</p>
                                                    </div>
                                                    <div class="modal-footer border-0 justify-content-center pb-4">
                                                        <button class="btn btn-outline-secondary px-4" type="button" data-bs-dismiss="modal">
                                                            <i class="fas fa-times me-2"></i>Cancelar
                                                        </button>
                                                        <button class="btn btn-primary px-5" onclick="mostrarAlertSeg();" type="submit" data-bs-dismiss="modal">
                                                            <i class="fas fa-check me-2"></i>Confirmar
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                            </div>
                        </div>

                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade" id="tab-call" role="tabpanel" aria-labelledby="activity-tab">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h3 class="mb-0">Datos domicilio</h3>
                                        <div class="text-end">
                                            <p class="mb-0 fs--1 text-muted"><strong>Ult. fec mod:</strong> ${emp5.iexfecmoddom}</p>
                                            <p class="mb-0 fs--1 text-muted"><strong>Ult. usu mod:</strong> ${emp5.iexusumoddom}</p>
                                        </div>
                                    </div>

                                    <form class="needs-validation" method="POST" action="updateEmplDatDomic" novalidate>
                                        <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                        <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />

                                        <!-- Dirección Principal -->
                                        <div class="card mb-3 shadow-sm">
                                            <div class="card-header bg-primary-subtle border-0 py-3">
                                                <h5 class="mb-0 text-primary">
                                                    <i class="fas fa-home me-2"></i>Dirección Principal
                                                </h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row g-3 mb-3">
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Tipo de vía <span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-road"></i></span>
                                                            <select class="form-select" name="iextipvia_dom1" required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovTipVia" items="${lovTipVia}">
                                                                    <option value="${lovTipVia.idLov}" ${lovTipVia.idLov == requestScope.emp5.iextipvia_dom1 ? 'selected' : ''}>${lovTipVia.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-5">
                                                        <label class="form-label fw-semibold">Nombre de vía <span class="text-danger">*</span></label>
                                                        <input class="form-control" name="iexnomvia_dom1" maxlength="20" value="${requestScope.emp5.iexnomvia_dom1}" type="text" placeholder="Los Girasoles" required />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Número de vía <span class="text-danger">*</span></label>
                                                        <input class="form-control" name="iexnrovia_dom1" maxlength="6" value="${requestScope.emp5.iexnrovia_dom1}" type="number" placeholder="435" required />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Número dpto.</label>
                                                        <input class="form-control" name="iexdeptin_dom1" maxlength="6" value="${requestScope.emp5.iexdeptin_dom1}" type="text" placeholder="203" />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Interior</label>
                                                        <input class="form-control" name="iexinterior_dom1" maxlength="6" value="${requestScope.emp5.iexinterior_dom1}" type="text" placeholder="1" />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Manzana</label>
                                                        <input class="form-control" name="iexmanzana_dom1" maxlength="6" value="${requestScope.emp5.iexmanzana_dom1}" type="text" placeholder="A" />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Número de lote</label>
                                                        <input class="form-control" name="iexlote_dom1" maxlength="6" value="${requestScope.emp5.iexlote_dom1}" type="text" placeholder="10" />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Km de referencia</label>
                                                        <input class="form-control" name="iexkilometro_dom1" maxlength="6" value="${requestScope.emp5.iexkilometro_dom1}" type="text" placeholder="21" />
                                                    </div>
                                                    <div class="col-md-2">
                                                        <label class="form-label fw-semibold">Etapa</label>
                                                        <input class="form-control" name="iexetapa_dom1" maxlength="6" value="${requestScope.emp5.iexetapa_dom1}" type="text" placeholder="E2" />
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Tipo de zona</label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-map"></i></span>
                                                            <select class="form-select" name="iextipzona_dom1">
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovTipZona" items="${lovTipZona}">
                                                                    <option value="${lovTipZona.idLov}" ${lovTipZona.idLov == requestScope.emp5.iextipzona_dom1 ? 'selected' : ''}>${lovTipZona.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <label class="form-label fw-semibold">Referencia</label>
                                                        <input class="form-control" name="iexreferencia_dom1" maxlength="40" value="${requestScope.emp5.iexreferencia_dom1}" type="text" placeholder="Ingrese una referencia" />
                                                    </div>
                                                </div>

                                                <h6 class="text-primary mb-3"><i class="fas fa-map-marked-alt me-2"></i>Ubicación geográfica</h6>
                                                <div class="row g-3">
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">País <span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-globe"></i></span>
                                                            <select class="form-select" id="iexpaisemisor1" name="iexnacion_origen1" required>
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovPaisEmisor" items="${lovPaisEmisor}">
                                                                    <option value="${lovPaisEmisor.idLov}" ${lovPaisEmisor.idLov == requestScope.emp5.iexnacion_origen1 ? 'selected' : ''}>${lovPaisEmisor.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Departamento <span class="text-danger">*</span></label>
                                                        <select class="form-select" id="iexdepart_origen1" name="iexdepart_origen1" required>
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovDept_origen" items="${lovDept_origen}">
                                                                <option value="${lovDept_origen.idLov}" ${lovDept_origen.idLov == requestScope.emp5.iexdepart_origen1 ? 'selected' : ''}>${lovDept_origen.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Provincia</label>
                                                        <select class="form-select" id="iexprovin_origen1" name="iexprovin_origen1">
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovProvin_origen1" items="${lovProvin_origen1}">
                                                                <option value="${lovProvin_origen1.idLov}" ${lovProvin_origen1.idLov == requestScope.emp5.iexprovin_origen1 ? 'selected' : ''}>${lovProvin_origen1.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Distrito</label>
                                                        <select class="form-select" name="iexdistri_origen1" id="iexdistri_origen1">
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovDist_origen1" items="${lovDist_origen1}">
                                                                <option value="${lovDist_origen1.idLov}" ${lovDist_origen1.idLov == requestScope.emp5.iexubigeo_dom1 ? 'selected' : ''}>${lovDist_origen1.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Dirección Secundaria -->
                                        <div class="card mb-3 shadow-sm">
                                            <div class="card-header bg-secondary-subtle border-0 py-3">
                                                <h5 class="mb-0 text-primary">
                                                    <i class="fas fa-house-user me-2"></i>Dirección Secundaria (Opcional)
                                                </h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row g-3 mb-3">
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Tipo de vía</label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-road"></i></span>
                                                            <select class="form-select" name="iextipvia_dom2">
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovTipVia2" items="${lovTipVia2}">
                                                                    <option value="${lovTipVia2.idLov}" ${lovTipVia2.idLov == requestScope.emp5.iextipvia_dom2 ? 'selected' : ''}>${lovTipVia2.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-5">
                                                        <label class="form-label fw-semibold">Nombre de vía</label>
                                                        <input class="form-control" name="iexnomvia_dom2" maxlength="30" value="${requestScope.emp5.iexnomvia_dom2}" type="text" placeholder="Los Girasoles" />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Número de vía</label>
                                                        <input class="form-control" name="iexnrovia_dom2" maxlength="6" value="${requestScope.emp5.iexnrovia_dom2}" type="number" placeholder="435" />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Número dpto.</label>
                                                        <input class="form-control" name="iexdeptin_dom2" maxlength="6" value="${requestScope.emp5.iexdeptin_dom2}" type="text" placeholder="203" />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Interior</label>
                                                        <input class="form-control" name="iexinterior_dom2" maxlength="6" value="${requestScope.emp5.iexinterior_dom2}" type="text" placeholder="1" />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Manzana</label>
                                                        <input class="form-control" name="iexmanzana_dom2" maxlength="6" value="${requestScope.emp5.iexmanzana_dom2}" type="text" placeholder="A" />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Número de lote</label>
                                                        <input class="form-control" name="iexlote_dom2" maxlength="6" value="${requestScope.emp5.iexlote_dom2}" type="text" placeholder="10" />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Km de referencia</label>
                                                        <input class="form-control" name="iexkilometro_dom2" maxlength="6" value="${requestScope.emp5.iexkilometro_dom2}" type="text" placeholder="21" />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label fw-semibold">Número de bloque</label>
                                                        <input class="form-control" name="iexblock_dom2" maxlength="6" type="text" value="${requestScope.emp5.iexblock_dom2}" placeholder="B3" />
                                                    </div>
                                                    <div class="col-md-2">
                                                        <label class="form-label fw-semibold">Etapa</label>
                                                        <input class="form-control" name="iexetapa_dom2" maxlength="6" type="text" value="${requestScope.emp5.iexetapa_dom2}" placeholder="E2" />
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Tipo de zona</label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-map"></i></span>
                                                            <select class="form-select" name="iextipzona_dom2">
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovTipZona2" items="${lovTipZona2}">
                                                                    <option value="${lovTipZona2.idLov}" ${lovTipZona2.idLov == requestScope.emp5.iextipzona_dom2 ? 'selected' : ''}>${lovTipZona2.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <label class="form-label fw-semibold">Referencia</label>
                                                        <input class="form-control" name="iexreferencia_dom2" maxlength="40" value="${requestScope.emp5.iexreferencia_dom2}" type="text" placeholder="Ingrese una referencia" />
                                                    </div>
                                                </div>

                                                <h6 class="text-secondary mb-3"><i class="fas fa-map-marked-alt me-2"></i>Ubicación geográfica</h6>
                                                <div class="row g-3">
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">País</label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-globe"></i></span>
                                                            <select class="form-select" id="iexpaisemisor2" name="iexnacion_origen2">
                                                                <option value="">-- Seleccionar --</option>
                                                                <c:forEach var="lovPaisEmisor" items="${lovPaisEmisor}">
                                                                    <option value="${lovPaisEmisor.idLov}" ${lovPaisEmisor.idLov == requestScope.emp5.iexnacion_origen2 ? 'selected' : ''}>${lovPaisEmisor.desLov}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Departamento</label>
                                                        <select class="form-select" id="iexdepart_origen2" name="iexdepart_origen2">
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovDept_origen" items="${lovDept_origen}">
                                                                <option value="${lovDept_origen.idLov}" ${lovDept_origen.idLov == requestScope.emp5.iexdepart_origen2 ? 'selected' : ''}>${lovDept_origen.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Provincia</label>
                                                        <select class="form-select" id="iexprovin_origen2" name="iexprovin_origen2">
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovProvin_origen2" items="${lovProvin_origen2}">
                                                                <option value="${lovProvin_origen2.idLov}" ${lovProvin_origen2.idLov == requestScope.emp5.iexprovin_origen2 ? 'selected' : ''}>${lovProvin_origen2.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label fw-semibold">Distrito</label>
                                                        <select class="form-select" name="iexdistri_origen2" id="iexdistri_origen2">
                                                            <option value="">-- Seleccionar --</option>
                                                            <c:forEach var="lovDist_origen2" items="${lovDist_origen2}">
                                                                <option value="${lovDist_origen2.idLov}" ${lovDist_origen2.idLov == requestScope.emp5.iexubigeo_dom2 ? 'selected' : ''}>${lovDist_origen2.desLov}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Selección de domicilio principal -->
                                        <div class="card mb-3 shadow-sm">
                                            <div class="card-header bg-info-subtle border-0 py-3">
                                                <h5 class="mb-0 text-info">
                                                    <i class="fas fa-check-circle me-2"></i>Domicilio a utilizar
                                                </h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <label class="form-label fw-semibold">Seleccione el domicilio principal <span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-map-marker-alt"></i></span>
                                                            <select class="form-select" name="iexflgdomicilio" required>
                                                                <option value="1" ${requestScope.emp5.iexflgdomicilio=='1' ? 'selected' : ''}>Dirección principal</option>
                                                                <option value="2" ${requestScope.emp5.iexflgdomicilio=='2' ? 'selected' : ''}>Dirección secundaria</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Alerta de éxito -->
                                        <div id="alertDom" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                                            <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                                            <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios</p>
                                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                        </div>

                                        <!-- Botones de acción -->
                                        <div class="card shadow-sm">
                                            <div class="card-body">
                                                <div class="d-flex justify-content-between align-items-center">
                                                    <div class="text-muted small">
                                                        <i class="fas fa-info-circle me-1"></i>
                                                        Los campos marcados con <span class="text-danger">*</span> son obligatorios
                                                    </div>
                                                    <div>
                                                        <a class="btn btn-outline-secondary me-2" href="listEmpleados">
                                                            <i class="fas fa-times me-2"></i>Cancelar
                                                        </a>
                                                        <button class="btn btn-primary px-5" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal5">
                                                            <i class="fas fa-save me-2"></i>Guardar cambios
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Modal de confirmación -->
                                        <div class="modal fade" id="confirmModal5" tabindex="-1">
                                            <div class="modal-dialog modal-dialog-centered">
                                                <div class="modal-content border-0 shadow">
                                                    <div class="modal-header bg-primary text-white border-0">
                                                        <h5 class="modal-title"><i class="fas fa-question-circle me-2"></i>Confirmar acción</h5>
                                                        <button class="btn-close btn-close-white" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body text-center py-4">
                                                        <div class="mb-3">
                                                            <i class="fas fa-exclamation-triangle text-warning" style="font-size: 3rem;"></i>
                                                        </div>
                                                        <h6 class="mb-2">¿Está seguro que desea guardar los cambios?</h6>
                                                        <p class="text-muted small mb-0">Esta acción actualizará los datos de domicilio del empleado</p>
                                                    </div>
                                                    <div class="modal-footer border-0 justify-content-center pb-4">
                                                        <button class="btn btn-outline-secondary px-4" type="button" data-bs-dismiss="modal">
                                                            <i class="fas fa-times me-2"></i>Cancelar
                                                        </button>
                                                        <button class="btn btn-primary px-5" onclick="mostrarAlertDom();" type="submit" data-bs-dismiss="modal">
                                                            <i class="fas fa-check me-2"></i>Confirmar
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
              </div>
              <jsp:include page="../../../../footer.jsp"></jsp:include>
          </div>

          <jsp:include page="../../../../demoWidget.jsp"></jsp:include>
    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->

    <jsp:include page="../../../../customize.jsp"></jsp:include>
  </body>
</html>