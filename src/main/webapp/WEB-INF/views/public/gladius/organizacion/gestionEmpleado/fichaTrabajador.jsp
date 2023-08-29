<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jean Quiroz
    Email	   : jeanp.quiroz@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
  <head>
    <jsp:include page="../../../links.jsp"></jsp:include>
  </head>

  <jsp:include page="scriptsEmpl.jsp"></jsp:include>

  <body>
    <!-- ===============================================-->
    <!--    Main Content-->
    <!-- ===============================================-->
    <main class="main" id="top">
          <jsp:include page="../../../navsMenu.jsp"></jsp:include>
          <jsp:include page="../../../navTop.jsp"></jsp:include>
          <jsp:include page="../../../modalFade.jsp"></jsp:include>

          <div class="content">
              <div class="pb-9">
                <div class="row">
                  <div class="col-12">
                    <div class="row align-items-center justify-content-between g-3 mb-3">
                    </div>
                  </div>
                </div>
                <div class="row g-0 g-md-4 g-xl-6">

                  <jsp:include page="navsGenericEmpl.jsp"></jsp:include>

                  <div class="col-md-7 col-lg-7 col-xl-8">
                    <div class="lead-details-container">
                       <ul class="nav nav-underline deal-details scrollbar flex-nowrap w-100 pb-1 mb-6" id="myTab" role="tablist" style="overflow-y: hidden;">
                            <li class="nav-item text-nowrap me-2" role="presentation"><a class="nav-link active" id="activity-tab" data-bs-toggle="tab" href="#tab-activity" role="tab" aria-controls="tab-activity" aria-selected="false" tabindex="-1"> <span class="fa-solid fa-chart-line me-2 tab-icon-color"></span>Datos Personales</a></li>
                            <li class="nav-item text-nowrap me-2" role="presentation"><a class="nav-link" id="notes-tab" data-bs-toggle="tab" href="#tab-notes" role="tab" aria-controls="tab-notes" aria-selected="false" tabindex="-1"> <span class="fa-solid fa-clipboard me-2 tab-icon-color"></span>Datos Laborales</a></li>
                            <li class="nav-item text-nowrap me-2" role="presentation"><a class="nav-link" id="meeting-tab" data-bs-toggle="tab" href="#tab-meeting" role="tab" aria-controls="tab-meeting" aria-selected="true"> <span class="fa-solid fa-video me-2 tab-icon-color"></span>Información de Pago</a></li>
                            <li class="nav-item text-nowrap me-2" role="presentation"><a class="nav-link" id="task-tab" data-bs-toggle="tab" href="#tab-task" role="tab" aria-controls="tab-task" aria-selected="true"> <span class="fa-solid fa-square-check me-2 tab-icon-color"></span>Seguridad Social</a></li>
                            <li class="nav-item text-nowrap me-2" role="presentation"><a class="nav-link" id="call-tab" data-bs-toggle="tab" href="#tab-call" role="tab" aria-controls="tab-call" aria-selected="true"> <span class="fa-solid fa-phone me-2 tab-icon-color"></span>Datos Domicilio</a></li>
                       </ul>

                      <div class="tab-content" id="myTabContent">
                         <div class="tab-pane fade active show" id="tab-activity" role="tabpanel" aria-labelledby="activity-tab">
                            <h3 class="mb-4">Datos personales</h3>
                            <div class="row g-5">
                                 <div class="col-xl-12">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-4 mb-0 needs-validation" method="POST" action="updateEmplDatPers" novalidate >
                                        <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                        <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                                        <div class="col-sm-6 col-md-3">
                                          <div class="form-floating">
                                            <input class="form-control" name="iexcodtra" type="text" value="${requestScope.emp.iexcodtra}" readonly="true" required disabled />
                                            <label for="floatingEventInput">Codigo Empleado</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-5">
                                          <div class="form-floating">
                                              <select name="iextipdocid" class="form-select" required >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipdoc" items="${lovTipdoc}">
                                                      <option value="${lovTipdoc.idLov}"  ${lovTipdoc.idLov == requestScope.emp.iextipdocid ? 'selected' : ''}   >${lovTipdoc.desLov}</option>
                                                  </c:forEach>
                                              </select>
                                              <label>Tipo de Documento (*)</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-4">
                                          <div class="form-floating">
                                            <select name="iexflgest" class="form-select" required>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovEstados" items="${lovEstados}">
                                                      <option value="${lovEstados.idLov}"  ${lovEstados.idLov == requestScope.emp.iexflgest ? 'selected' : ''}   >${lovEstados.desLov}</option>
                                                  </c:forEach>
                                            </select>
                                            <label>Estado [TT54]</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                            <input class="form-control" name="iexnrodoc" type="text" value="${requestScope.emp.iexnrodoc}" placeholder="street" required/>
                                            <label>Nro Documento</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                            <input class="form-control" name="iexcodant" type="text" value="${requestScope.emp.iexcodant}" placeholder="street" />
                                            <label>Codigo Anterior</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                            <input class="form-control" name="iexapepat" type="text" value="${requestScope.emp.iexapepat}" placeholder="street" required/>
                                            <label>Apellido Paterno (*)</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                            <input class="form-control" name="iexapemat" type="text" value="${requestScope.emp.iexapemat}" placeholder="street" required />
                                            <label>Apellido Materno (*)</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-8">
                                          <div class="form-floating">
                                            <input class="form-control" name="iexnomtra" type="text" value="${requestScope.emp.iexnomtra}" placeholder="street" required />
                                            <label>Nombres (*)</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-4">
                                          <div class="flatpickr-input-container">
                                            <div class="form-floating">
                                              <input class="form-control datetimepicker" name="iexfecnac" id="floatingInputStartDate" value="${requestScope.emp.iexfecnac}" type="text" placeholder="end date" data-options='{"disableMobile":true}' required />
                                              <label class="ps-6" for="floatingInputStartDate">Fecha de Nacimiento</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                            </div>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                              <select name="iexcodsex" class="form-select" required >
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovSexo" items="${lovSexo}">
                                                        <option value="${lovSexo.idLov}"  ${lovSexo.idLov == requestScope.emp.iexcodsex ? 'selected' : ''}   >${lovSexo.desLov}</option>
                                                    </c:forEach>
                                              </select>
                                              <label>Sexo(*)[TT50]</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                              <select name="iexestcivil" class="form-select" required >
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovEstcivil" items="${lovEstcivil}">
                                                        <option value="${lovEstcivil.idLov}"  ${lovEstcivil.idLov == requestScope.emp.iexestcivil ? 'selected' : ''}   >${lovEstcivil.desLov}</option>
                                                    </c:forEach>
                                              </select>
                                              <label>Estado Civil (*) [TT68]</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                              <select name="iexmodform" class="form-select" >
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovModForm" items="${lovModForm}">
                                                        <option value="${lovModForm.idLov}"  ${lovModForm.idLov == requestScope.emp.iexmodform ? 'selected' : ''}   >${lovModForm.desLov}</option>
                                                    </c:forEach>
                                              </select>
                                              <label>Modalidad Formativa [TT18]</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                              <select name="iexnacion_origen" class="form-select" required >
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovNacionalidad" items="${lovNacionalidad}">
                                                        <option value="${lovNacionalidad.idLov}"  ${lovNacionalidad.idLov == requestScope.emp.iexnacion_origen ? 'selected' : ''}   >${lovNacionalidad.desLov}</option>
                                                    </c:forEach>
                                              </select>
                                              <label>Nacionalidad Origen (*) [TT4]</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                              <select name="iexpaisemisor" class="form-select" required="true" >
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovPaisEmisor" items="${lovPaisEmisor}">
                                                        <option value="${lovPaisEmisor.idLov}"  ${lovPaisEmisor.idLov == requestScope.emp.iexpaisemisor ? 'selected' : ''}   >${lovPaisEmisor.desLov}</option>
                                                    </c:forEach>
                                              </select>
                                              <label>Pais Emisor (*) [TT26]</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                              <select name="iexdepart_origen" class="form-select" >
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovDept_origen" items="${lovDept_origen}">
                                                        <option value="${lovDept_origen.idLov}"  ${lovDept_origen.idLov == requestScope.emp.iexdepart_origen ? 'selected' : ''}   >${lovDept_origen.desLov}</option>
                                                    </c:forEach>
                                              </select>
                                              <label>Departamento</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                              <select name="iexprovin_origen" class="form-select">
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovProvin_origen" items="${lovProvin_origen}">
                                                        <option value="${lovProvin_origen.idLov}"  ${lovProvin_origen.idLov == requestScope.emp.iexprovin_origen ? 'selected' : ''}   >${lovProvin_origen.desLov}</option>
                                                    </c:forEach>
                                              </select>
                                              <label>Provincia</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                              <select name="iexdistri_origen" class="form-select">
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovDist_origen" items="${lovDist_origen}">
                                                        <option value="${lovDist_origen.idLov}"  ${lovDist_origen.idLov == requestScope.emp.iexdistri_origen? 'selected' : ''}   >${lovDist_origen.desLov}</option>
                                                    </c:forEach>
                                              </select>
                                              <label>Distrito (*)</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                              <select name="iexgrdinstruccion" class="form-select" required >
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovGrdInstruccion" items="${lovGrdInstruccion}">
                                                        <option value="${lovGrdInstruccion.idLov}"  ${lovGrdInstruccion.idLov == requestScope.emp.iexgrdinstruccion ? 'selected' : ''}   >${lovGrdInstruccion.desLov}</option>
                                                    </c:forEach>
                                              </select>
                                              <label>Grado Instruccion (*) [TT9]</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                          <div class="form-floating">
                                              <select name="iexcentroform" class="form-select" >
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovCenForm" items="${lovCenForm}">
                                                        <option value="${lovCenForm.idLov}"  ${lovCenForm.idLov == requestScope.emp.iexcentroform ? 'selected' : ''}   >${lovCenForm.desLov}</option>
                                                    </c:forEach>
                                              </select>
                                              <label>Centro de Formacion [TT51]</label>
                                          </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                             <div class="form-check">
                                               <input type="checkbox" class="form-check-input" name="iexflgdomicil" value="1"  ${requestScope.emp.iexflgdomicil=='1' ? 'checked=true' : ''} id="flexChecked" />
                                               <label class="form-check-label fs-0 mb-5" for="flexChecked">Es domiciliado?</label>
                                             </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                           <div class="form-floating">
                                                  <select name="iexcodlardist" class="form-select" >
                                                     <option value="" selected >Seleccionar</option>
                                                     <c:forEach var="lovLarDistancia" items="${lovLarDistancia}">
                                                         <option value="${lovLarDistancia.idLov}"  ${lovLarDistancia.idLov == requestScope.emp.iexcodlardist? 'selected' : ''}   >${lovLarDistancia.desLov}</option>
                                                     </c:forEach>
                                               </select>
                                               <label>Codigo de Larga Distancia [TT29]</label>
                                           </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                           <div class="form-floating">
                                             <input class="form-control" name="iexnrotelf" type="text" value="${requestScope.emp.iexnrotelf}" placeholder="street" />
                                             <label>Nro de Telefono</label>
                                           </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                           <div class="form-floating">
                                             <input class="form-control" name="iexemail" type="text" value="${requestScope.emp.iexemail}" placeholder="street" />
                                             <label>Email</label>
                                           </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                           <div class="form-floating">
                                             <input class="form-control" name="iexemail_coorp" type="text" value="${requestScope.emp.iexemail_coorp}" placeholder="street" />
                                             <label>Email Coorporativo</label>
                                           </div>
                                        </div>

                                        <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                            Se grabó exitosamente los cambios.
                                        </div>
                                        <div class="col-12 d-flex justify-content-end mt-6">
                                            <div class="col-6">
                                                <a class="btn btn-phoenix-primary" href="listEmpleados">Cancel</a>
                                                <button class="btn btn-primary col-8" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar</button>
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
                                                <div class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
                                                    <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="button" data-bs-dismiss="modal" >Cancel</button>
                                                    <button class="btn btn-sm btn-primary px-9 fs--2 my-0" onclick="mostrarAlert();" type="submit" data-bs-dismiss="modal" >Confirmar</button>
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

                      <div class="tab-content" id="myTabContent">
                          <div class="tab-pane fade" id="tab-notes" role="tabpanel" aria-labelledby="activity-tab">
                                <h3 class="mb-4">Datos laborales</h3>
                                <div class="row g-5">
                                     <div class="col-xl-12">
                                       <div class="row gx-3 gy-4">
                                          <form class="row g-4 mb-0 needs-validation" method="POST" action="updateEmplDatLab" novalidate>
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexreglab" required>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovRegLab" items="${lovRegLab}">
                                                      <option value="${lovRegLab.idLov}" ${lovRegLab.idLov == requestScope.emp2.iexreglab ? 'selected' : ''}  >${lovRegLab.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                                <label>Regimen Laboral (*) [TT33]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iextiptra" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovTipTra" items="${lovTipTra}">
                                                        <option value="${lovTipTra.idLov}"   ${lovTipTra.idLov == requestScope.emp2.iextiptra ? 'selected' : ''}    >${lovTipTra.desLov}  </option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Tipo de Trabajador (*) [TT8]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexcateg_trabajador" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovCateTra" items="${lovCateTra}">
                                                        <option value="${lovCateTra.idLov}"  ${lovCateTra.idLov == requestScope.emp2.iexcateg_trabajador ? 'selected' : ''}    > ${lovCateTra.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Categoria Trabajador [TT24] (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexsituapen" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovSitPen" items="${lovSitPen}">
                                                        <option value="${lovSitPen.idLov}"   ${lovSitPen.idLov == requestScope.emp2.iexsituapen ? 'selected' : ''}    > ${lovSitPen.desLov} </option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Situacion del Pensionista [TT15] (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="flatpickr-input-container">
                                                <div class="form-floating">
                                                  <input class="form-control datetimepicker" name="iexfecing" id="floatingInputStartDate" value="${requestScope.emp2.iexfecing}" type="text" placeholder="end date" data-options='{"disableMobile":true}' required="true"/>
                                                  <label class="ps-6" for="floatingInputStartDate">Fecha de Ingreso (*)</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                </div>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="flatpickr-input-container">
                                                <div class="form-floating">
                                                  <input class="form-control datetimepicker" name="iexfecret" id="floatingInputStartDate" value="${requestScope.emp2.iexfecret}" type="text" placeholder="end date" data-options='{"disableMobile":true}' />
                                                  <label class="ps-6" for="floatingInputStartDate">Fecha de Retiro</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                </div>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-8">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iextipcont" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovTipCont" items="${lovTipCont}">
                                                        <option value="${lovTipCont.idLov}"  ${lovTipCont.idLov == requestScope.emp2.iextipcont ? 'selected' : ''}   >${lovTipCont.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Tipo de Contrato [TT12] (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                              <div class="flatpickr-input-container">
                                                <div class="form-floating">
                                                  <input class="form-control datetimepicker" name="iexfecini_cont" id="floatingInputStartDate" value="${requestScope.emp2.iexfecini_cont}" type="text" placeholder="end date" data-options='{"disableMobile":true}' />
                                                  <label class="ps-6" for="floatingInputStartDate">Fec Ini Contrato</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                </div>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                              <div class="flatpickr-input-container">
                                                <div class="form-floating">
                                                  <input class="form-control datetimepicker" name="iexfecfin_cont" id="floatingInputStartDate" value="${requestScope.emp2.iexfecfin_cont}" type="text" placeholder="end date" data-options='{"disableMobile":true}' />
                                                  <label class="ps-6" for="floatingInputStartDate">Fec Fin Contrato</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                </div>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-8">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexpliego">
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovPliego" items="${lovPliego}">
                                                        <option value="${lovPliego.idLov}"   ${lovPliego.idLov == requestScope.emp2.iexpliego ? 'selected' : ''}    >${lovPliego.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Pliego [TT31]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexsituaesp" required >
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovSituesp" items="${lovSituesp}">
                                                        <option value="${lovSituesp.idLov}"   ${lovSituesp.idLov == requestScope.emp2.iexsituaesp ? 'selected' : ''}  >${lovSituesp.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Situacion Especial [TT35](*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexocupacion_pub" >
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovOcupRegPub" items="${lovOcupRegPub}">
                                                        <option value="${lovOcupRegPub.idLov}"  ${lovOcupRegPub.idLov == requestScope.emp2.iexocupacion_pub ? 'selected' : ''}  >${lovOcupRegPub.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Ocupacion Reg. Publico [TT10]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexocupacion_priv">
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovOcupRegPrv" items="${lovOcupRegPrv}">
                                                        <option value="${lovOcupRegPrv.idLov}"  ${lovOcupRegPrv.idLov == requestScope.emp2.iexocupacion_priv ? 'selected' : ''}    >${lovOcupRegPrv.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Ocupacion Reg. Privado [TT30]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexarea" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovArea" items="${lovArea}">
                                                        <option value="${lovArea.iexcodarea}" ${lovArea.iexcodarea == requestScope.emp2.iexarea ? 'selected' : ''}     >${lovArea.iexdesarea}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Area (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexpuesto" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovPuesto" items="${lovPuesto}"  >
                                                        <option value="${lovPuesto.iexpuesto}" ${lovPuesto.iexpuesto == requestScope.emp2.iexpuesto ? 'selected' : ''}  >${lovPuesto.iexdespuesto}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Puesto (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexccosto" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovCcosto" items="${lovCcosto}">
                                                        <option value="${lovCcosto.iexccosto}"   ${lovCcosto.iexccosto == requestScope.emp2.iexccosto ? 'selected' : ''}   >${lovCcosto.iexdesccosto}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Centro de Costos(*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexubilocal" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovUbicacion" items="${lovUbicacion}">
                                                        <option value="${lovUbicacion.iexubicod}"  ${lovUbicacion.iexubicod == requestScope.emp2.iexubilocal ? 'selected' : ''}  >  ${lovUbicacion.iexubides}  </option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Ubicación (*)</label>
                                              </div>
                                            </div>

                                            <div class="alert alert-success" role="alert" id="alert2" style="display:none;">
                                                Se grabó exitosamente los cambios.
                                            </div>
                                            <div class="col-12 d-flex justify-content-end mt-6">
                                                <div class="col-6">
                                                    <a class="btn btn-phoenix-primary" href="listEmpleados">Cancel</a>
                                                    <button class="btn btn-primary col-8" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal2" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar</button>
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
                                                    <div class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
                                                        <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="button" data-bs-dismiss="modal" >Cancel</button>
                                                        <button class="btn btn-sm btn-primary px-9 fs--2 my-0" onclick="mostrarAlert2();" type="submit" data-bs-dismiss="modal" >Confirmar</button>
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

                      <div class="tab-content" id="myTabContent">
                          <div class="tab-pane fade" id="tab-meeting" role="tabpanel" aria-labelledby="activity-tab">
                                <h3 class="mb-4">Informacion de pago</h3>
                                <div class="row g-5">
                                     <div class="col-xl-12">
                                       <div class="row gx-3 gy-4">
                                          <form class="row g-4 mb-0 needs-validation" method="POST" action="updateInfoPago" novalidate>
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <select class="form-select" name="iextippago" required>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipPago" items="${lovTipPago}">
                                                      <option value="${lovTipPago.idLov}"   ${lovTipPago.idLov == requestScope.emp3.iextippago ? 'selected' : ''}    >${lovTipPago.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                                <label>Tipo de pago (*) [TT19]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexperrem" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovPerRem" items="${lovPerRem}">
                                                        <option value="${lovPerRem.idLov}"   ${lovPerRem.idLov == requestScope.emp3.iexperrem ? 'selected' : ''}     >${lovPerRem.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Periodo Remuneracion (*) [TT13]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexcodban_hab" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovBancoHab" items="${lovBancoHab}">
                                                        <option value="${lovBancoHab.idLov}"   ${lovBancoHab.idLov == requestScope.emp3.iexcodban_hab ? 'selected' : ''}   >${lovBancoHab.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Banco de Haberes (*) [TT36]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iextipban_hab" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovTipCtaHab" items="${lovTipCtaHab}">
                                                        <option value="${lovTipCtaHab.idLov}"    ${lovTipCtaHab.idLov == requestScope.emp3.iextipban_hab ? 'selected' : ''}   >${lovTipCtaHab.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Tipo de Cuenta de Haberes (*) [TT53]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexcodmon_hab" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovMonedaHab" items="${lovMonedaHab}">
                                                        <option value="${lovMonedaHab.idLov}"    ${lovMonedaHab.idLov == requestScope.emp3.iexcodmon_hab ? 'selected' : ''}    >${lovMonedaHab.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Moneda de Haberes [TT52] (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexflgbancci_hab" id="flexChecked" value="1" ${requestScope.emp3.iexflgbancci_hab=='1' ? 'checked=true' : ''}  type="checkbox"/>
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Es interbancario?</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <input class="form-control" name="iexnrocta_hab" value="${requestScope.emp3.iexnrocta_hab}" type="text" placeholder="street" required="true"/>
                                                <label>Nro de Cuenta de Bancos (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexcodban_cts" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovBancoCts" items="${lovBancoCts}">
                                                        <option value="${lovBancoCts.idLov}" ${lovBancoCts.idLov == requestScope.emp3.iexcodban_cts ? 'selected' : ''}   >${lovBancoCts.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Banco de CTS (*) [TT36]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iextipban_cts" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovTipCtaCts" items="${lovTipCtaCts}">
                                                        <option value="${lovTipCtaCts.idLov}"  ${lovTipCtaCts.idLov == requestScope.emp3.iextipban_cts ? 'selected' : ''}  >${lovTipCtaCts.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Tipo de Banco Cts (*) [TT53]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexcodmon_cts" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovMonedaCts" items="${lovMonedaCts}">
                                                        <option value="${lovMonedaCts.idLov}"     ${lovMonedaCts.idLov == requestScope.emp3.iexcodmon_cts ? 'selected' : ''}    >${lovMonedaCts.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Moneda de Cts (*) [TT52]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexflgbancci_cts" id="flexChecked" value="1" ${requestScope.emp3.iexflgbancci_cts=='1' ? 'checked=true' : ''} type="checkbox"/>
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Es interbancario?</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <input id="validationCustom01" class="form-control" name="iexnrocta_cts" value="${requestScope.emp3.iexnrocta_cts}" type="text" placeholder="street" required="true" />
                                                <label>Nro Cta Cts (*)</label>
                                              </div>
                                            </div>


                                            <div class="alert alert-success" role="alert" id="alert3" style="display:none;">
                                                Se grabó exitosamente los cambios.
                                            </div>
                                            <div class="col-12 d-flex justify-content-end mt-6">
                                                <div class="col-6">
                                                    <a class="btn btn-phoenix-primary" href="listEmpleados">Cancel</a>
                                                    <button class="btn btn-primary col-8" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal3" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar</button>
                                                </div>
                                            </div>
                                            <div class="modal fade" id="confirmModal3" tabindex="-1">
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
                                                    <div class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
                                                        <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="button" data-bs-dismiss="modal" >Cancel</button>
                                                        <button class="btn btn-sm btn-primary px-9 fs--2 my-0" onclick="mostrarAlert3();" type="submit" data-bs-dismiss="modal" >Confirmar</button>
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

                      <div class="tab-content" id="myTabContent">
                          <div class="tab-pane fade" id="tab-task" role="tabpanel" aria-labelledby="activity-tab">
                                <h3 class="mb-4">Seguridad social</h3>
                                <div class="row g-5">
                                     <div class="col-xl-12">
                                       <div class="row gx-3 gy-4">
                                         <form class="row g-4 mb-0 needs-validation" method="POST" action="updateSegurSocial" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                                            <div class="col-sm-6 col-md-6">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexflgjubil" id="flexChecked" value="1" ${requestScope.emp4.iexflgjubil=='1' ? 'checked=true' : ''} type="checkbox"/>
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Es jubilado?</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexcodafp" required>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovCodAfp" items="${lovCodAfp}">
                                                      <option value="${lovCodAfp.idLov}"  ${lovCodAfp.idLov == requestScope.emp4.iexcodafp ? 'selected' : ''}      >${lovCodAfp.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                                <label>Fondo Pensiones (*) [TT11]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexflgcomi_mix" id="flexChecked" value="1" ${requestScope.emp4.iexflgcomi_mix=='1' ? 'checked=true' : ''} type="checkbox" />
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Comisión Mixta?</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="flatpickr-input-container">
                                                <div class="form-floating">
                                                  <input class="form-control datetimepicker" name="iexfecafp" id="floatingInputStartDate" value="${requestScope.emp4.iexfecafp}" type="text" placeholder="end date" data-options='{"disableMobile":true}' required />
                                                  <label class="ps-6" for="floatingInputStartDate">Fecha Inicio Fondo Pensiones (*)</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                </div>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <input class="form-control" id="floatingInputStreet" name="iexcussp" value="${requestScope.emp4.iexcussp}" type="text" placeholder="street" required/>
                                                <label for="floatingInputStreet">Cussp (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexessalud" required>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovEssalud" items="${lovEssalud}">
                                                        <option value="${lovEssalud.idLov}"  ${lovEssalud.idLov == requestScope.emp4.iexessalud ? 'selected' : ''} >${lovEssalud.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Essalud (*) [TT32]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexsenati" id="flexChecked" value="1" ${requestScope.emp4.iexsenati=='1' ? 'checked=true' : ''} type="checkbox" />
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Senati</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexflgeps" id="flexChecked" type="checkbox" value="1" ${requestScope.emp4.iexflgeps=='1' ? 'checked=true' : ''} />
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Tiene Eps</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexcodeps" required >
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovProvEps" items="${requestScope.lovProvEps}">
                                                        <option value="${lovProvEps.idLov}" ${lovProvEps.idLov == requestScope.emp4.iexcodeps ? 'selected' : ''}   >${lovProvEps.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Indica Proveedor de Eps (*) [TT14]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexflgmas_vida" id="flexChecked" value="1" ${requestScope.emp4.iexflgmas_vida=='1' ? 'checked=true' : ''} type="checkbox" />
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Mas vida</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexconvdobtrib" id="flexChecked" value="1" ${requestScope.emp4.iexconvdobtrib=='1' ? 'checked=true' : ''} type="checkbox" />
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Convenio para evitar doble tributación</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexdiscapacidad" id="flexChecked" value="1" ${requestScope.emp4.iexdiscapacidad=='1' ? 'checked=true' : ''} type="checkbox" />
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Discapacidad</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexregalter" id="flexChecked" value="1" ${requestScope.emp4.iexregalter=='1' ? 'checked=true' : ''} type="checkbox" />
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Reg. Alternativo</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexsctrpension" id="flexChecked" value="1" ${requestScope.emp4.iexsctrpension=='1' ? 'checked=true' : ''} type="checkbox" />
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Sctr Pensionv</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexjornmax" id="flexChecked" value="1" ${requestScope.emp4.iexjornmax=='1' ? 'checked=true' : ''} type="checkbox" />
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Jornada Maxima</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexhornocturno" id="flexChecked" value="1" ${requestScope.emp4.iexhornocturno=='1' ? 'checked=true' : ''} type="checkbox" />
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Horario Nocturno</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexsindicalizado" id="flexChecked" value="1" ${requestScope.emp4.iexsindicalizado=='1' ? 'checked=true' : ''} type="checkbox" />
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Sindicalizado</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexexon5ta" id="flexChecked" value="1" ${requestScope.emp4.iexexon5ta=='1' ? 'checked=true' : ''} type="checkbox" />
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Exoneracion 5ta</label>
                                                 </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                               <div class="form-floating">
                                                 <input class="form-control" name="iexnroruc_cas" id="floatingInputStreet" maxlength="11" value="${requestScope.emp4.iexnroruc_cas}" type="text" placeholder="street" />
                                                 <label for="floatingInputStreet">Nro de Ruc Cas</label>
                                               </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                 <div class="form-check">
                                                   <input class="form-check-input" name="iexmadreresp" id="flexChecked" type="checkbox" value="1" ${requestScope.emp4.iexmadreresp=='1' ? 'checked=true' : ''}/>
                                                   <label class="form-check-label fs-0 mb-5" for="flexChecked">Madre de responsaibilidad Limitada</label>
                                                 </div>
                                            </div>

                                            <div class="alert alert-success" role="alert" id="alert4" style="display:none;">
                                                Se grabó exitosamente los cambios.
                                            </div>
                                            <div class="col-12 d-flex justify-content-end mt-6">
                                                <div class="col-6">
                                                    <a class="btn btn-phoenix-primary" href="listEmpleados">Cancel</a>
                                                    <button class="btn btn-primary col-8" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal4" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar</button>
                                                </div>
                                            </div>
                                            <div class="modal fade" id="confirmModal4" tabindex="-1">
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
                                                    <div class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
                                                        <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="button" data-bs-dismiss="modal" >Cancel</button>
                                                        <button class="btn btn-sm btn-primary px-9 fs--2 my-0" onclick="mostrarAlert4();" type="submit" data-bs-dismiss="modal" >Confirmar</button>
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

                      <div class="tab-content" id="myTabContent">
                          <div class="tab-pane fade" id="tab-call" role="tabpanel" aria-labelledby="activity-tab">
                              <h3 class="mb-4">Datos domicilio</h3>
                              <div class="row g-5">
                                   <div class="col-xl-12">
                                     <div class="row gx-3 gy-4">
                                        <form class="row g-4 mb-0 needs-validation" method="POST" action="updateEmplDatDomic" novalidate >
                                          <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                          <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                                          <div class="col-sm-6 col-md-4">
                                            <div class="form-floating">
                                              <select class="form-select" name="iextipvia_dom1" required>
                                                <option value="" selected >Seleccionar</option>
                                                <c:forEach var="lovTipVia" items="${lovTipVia}">
                                                    <option value="${lovTipVia.idLov}"   ${lovTipVia.idLov == requestScope.emp5.iextipvia_dom1 ? 'selected' : ''}   >${lovTipVia.desLov}</option>
                                                </c:forEach>
                                              </select>
                                              <label>Tipo de Via (*)</label>
                                            </div>
                                          </div>
                                          <div class="col-sm-6 col-md-5">
                                              <div class="form-floating">
                                                <input class="form-control" name="iexnomvia_dom1" maxlength="20" value="${requestScope.emp5.iexnomvia_dom1}" type="text" placeholder="street" required />
                                                <label>Nom. Via (*)</label>
                                              </div>
                                          </div>
                                          <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexnrovia_dom1" maxlength="4" value="${requestScope.emp5.iexnrovia_dom1}" type="text" placeholder="street" required />
                                                  <label>Nro Via (*)</label>
                                                </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <input class="form-control" name="iexdeptin_dom1" maxlength="4" value="${requestScope.emp5.iexdeptin_dom1}" type="text" placeholder="street" />
                                                <label>Nro Dept</label>
                                              </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexinterior_dom1" maxlength="4" value="${requestScope.emp5.iexinterior_dom1}" type="text" placeholder="street" />
                                                  <label>Interior</label>
                                                </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <input class="form-control" name="iexmanzana_dom1" maxlength="4" value="${requestScope.emp5.iexmanzana_dom1}" type="text" placeholder="street" />
                                                <label>Manzana domicilio 1</label>
                                              </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexlote_dom1" maxlength="4" value="${requestScope.emp5.iexlote_dom1}" type="text" placeholder="street" />
                                                  <label>Nro de Lote</label>
                                                </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <input class="form-control" name="iexkilometro_dom1" maxlength="4" value="${requestScope.emp5.iexkilometro_dom1}" type="text" placeholder="street" />
                                                <label>Kilometro de Referencia</label>
                                              </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                            <div class="form-floating">
                                              <input class="form-control" name="iexetapa_dom1" maxlength="4" value="${requestScope.emp5.iexetapa_dom1}" type="text" placeholder="street" />
                                              <label>Etapa dom1</label>
                                            </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                            <div class="form-floating">
                                                <select class="form-select" name="iextipzona_dom1" required >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipZona" items="${lovTipZona}">
                                                      <option value="${lovTipZona.idLov}"    ${lovTipZona.idLov == requestScope.emp5.iextipzona_dom1 ? 'selected' : ''}   >${lovTipZona.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                                <label>Tipo de zona dom1 (*)</label>
                                            </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <input class="form-control" name="iexreferencia_dom1" maxlength="40" value="${requestScope.emp5.iexreferencia_dom1}" type="text" placeholder="street" required/>
                                                <label>Referencia dom1 (*)</label>
                                              </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                               <div class="form-floating">
                                                   <select class="form-select" id="iexpaisemisor1" name="iexnacion_origen1" required >
                                                     <option value="" selected >Seleccionar</option>
                                                     <c:forEach var="lovPaisEmisor" items="${lovPaisEmisor}">
                                                         <option value="${lovPaisEmisor.idLov}"  ${lovPaisEmisor.idLov == requestScope.emp5.iexnacion_origen1 ? 'selected' : ''}>${lovPaisEmisor.desLov}</option>
                                                     </c:forEach>
                                                   </select>
                                                   <label>Pais Emisor 1 (*) [TT26]</label>
                                               </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                            <div class="form-floating">
                                                <select class="form-select" id="iexdepart_origen1" name="iexdepart_origen1" >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovDept_origen" items="${lovDept_origen}">
                                                      <option value="${lovDept_origen.idLov}"  ${lovDept_origen.idLov == requestScope.emp5.iexdepart_origen1 ? 'selected' : ''}>${lovDept_origen.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                                <label>Departamento 1</label>
                                            </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                            <div class="form-floating">
                                                <select class="form-select" id="iexprovin_origen1" name="iexprovin_origen1" >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovProvin_origen" items="${lovProvin_origen}">
                                                      <option value="${lovProvin_origen.idLov}" ${lovProvin_origen.idLov == requestScope.emp5.iexprovin_origen1  ? 'selected' : ''}>${lovProvin_origen.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                                <label>Provincia 1</label>
                                            </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                            <div class="form-floating">
                                                <select class="form-select" name="iexdistri_origen1" required>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovDist_origen" items="${lovDist_origen}">
                                                      <option value="${lovDist_origen.idLov}" ${lovDist_origen.idLov == requestScope.emp5.iexprovin_origen1  ? 'selected' : ''}>${lovDist_origen.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                                <label>Distrito 1 (*)</label>
                                            </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                                <div class="form-floating">
                                                    <select class="form-select" name="iextipvia_dom2" >
                                                      <option value="" selected >Seleccionar</option>
                                                      <c:forEach var="lovTipVia2" items="${lovTipVia2}">
                                                          <option value="${lovTipVia2.idLov}"  ${lovTipVia2.idLov == requestScope.emp5.iextipvia_dom2 ? 'selected' : ''}    >${lovTipVia2.desLov}</option>
                                                      </c:forEach>
                                                    </select>
                                                    <label>Tipo Via 2</label>
                                                </div>
                                          </div>
                                          <div class="col-sm-6 col-md-5">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexnomvia_dom2" maxlength="20" value="${requestScope.emp5.iexnomvia_dom2}" type="text" placeholder="street" />
                                                  <label>Nom. Via 2</label>
                                                </div>
                                          </div>
                                          <div class="col-sm-6 col-md-3">
                                                  <div class="form-floating">
                                                    <input class="form-control" name="iexnrovia_dom2" maxlength="4" value="${requestScope.emp5.iexnrovia_dom2}" type="text" placeholder="street" />
                                                    <label>Nro Via 2</label>
                                                  </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexdeptin_dom2" maxlength="4" value="${requestScope.emp5.iexdeptin_dom2}" type="text" placeholder="street" />
                                                  <label>Nro Dept 2</label>
                                                </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <input class="form-control" name="iexinterior_dom2" maxlength="4" value="${requestScope.emp5.iexinterior_dom2}" type="text" placeholder="street" />
                                                <label>Interior 2</label>
                                              </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <input class="form-control" name="iexmanzana_dom2" maxlength="4" value="${requestScope.emp5.iexmanzana_dom2}" type="text" placeholder="street" />
                                                <label>Manzana domicilio 2</label>
                                              </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <input class="form-control" name="iexlote_dom2" maxlength="4" value="${requestScope.emp5.iexlote_dom2}" type="text" placeholder="street" />
                                                <label>Nro de Lote 2</label>
                                              </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                            <div class="form-floating">
                                              <input class="form-control" name="iexkilometro_dom2" maxlength="4" value="${requestScope.emp5.iexkilometro_dom2}" type="text" placeholder="street" />
                                              <label>Kilometro de Referencia 2</label>
                                            </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                            <div class="form-floating">
                                              <input class="form-control" name="iexblock_dom2" maxlength="4" type="text" value="${requestScope.emp5.iexblock_dom2}" placeholder="street" />
                                              <label>Nro de Bloque 2</label>
                                            </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexetapa_dom2" maxlength="4" type="text" value="${requestScope.emp5.iexetapa_dom2}" placeholder="street" />
                                                  <label>Etapa dom 2</label>
                                                </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iextipzona_dom2" >
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovTipZona2" items="${lovTipZona2}">
                                                        <option value="${lovTipZona2.idLov}"    ${lovTipZona2.idLov == requestScope.emp5.iextipzona_dom2 ? 'selected' : ''}   >${lovTipZona2.desLov}</option>
                                                    </c:forEach>
                                                  </select>
                                                  <label>Tipo de zona dom 2</label>
                                              </div>
                                          </div>
                                          <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <input class="form-control" name="iexreferencia_dom2" maxlength="40" value="${requestScope.emp5.iexreferencia_dom2}" type="text" placeholder="street" />
                                                <label>Referencia dom 2</label>
                                              </div>
                                          </div>
                                          <div class="col-sm-6 col-md-6">
                                            <div class="form-floating">
                                                <select class="form-select" id="iexpaisemisor2" name="iexnacion_origen2" required >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovPaisEmisor" items="${lovPaisEmisor}">
                                                      <option value="${lovPaisEmisor.idLov}"  ${lovPaisEmisor.idLov == requestScope.emp5.iexnacion_origen2 ? 'selected' : ''}>${lovPaisEmisor.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                                <label>Pais Emisor 2 (*) [TT26]</label>
                                            </div>
                                          </div>
                                          <div class="col-sm-6 col-md-6">
                                            <div class="form-floating">
                                                <select class="form-select" id="iexdepart_origen2" name="iexdepart_origen2" >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovDept_origen" items="${lovDept_origen}">
                                                      <option value="${lovDept_origen.idLov}"  ${lovDept_origen.idLov == requestScope.emp5.iexdepart_origen2 ? 'selected' : ''}>${lovDept_origen.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                                <label>Departamento 2</label>
                                            </div>
                                          </div>
                                          <div class="col-sm-6 col-md-6">
                                            <div class="form-floating">
                                                <select class="form-select" id="iexprovin_origen2" name="iexprovin_origen2" >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovProvin_origen" items="${lovProvin_origen}">
                                                      <option value="${lovProvin_origen.idLov}" ${lovProvin_origen.idLov == requestScope.emp5.iexprovin_origen2  ? 'selected' : ''}>${lovProvin_origen.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                                <label>Provincia 2</label>
                                            </div>
                                          </div>
                                          <div class="col-sm-6 col-md-6">
                                            <div class="form-floating">
                                                <select class="form-select" name="iexdistri_origen2" required >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovDist_origen" items="${lovDist_origen}">
                                                      <option value="${lovDist_origen.idLov}" ${lovDist_origen.idLov == requestScope.emp5.iexprovin_origen2  ? 'selected' : ''}>${lovDist_origen.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                                <label>Distrito 2 (*)</label>
                                            </div>
                                          </div>
                                          <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                  <select class="form-select" name="iexflgdomicilio" required >
                                                    <option value="1"  ${requestScope.emp5.iexflgdomicilio=='1' ? 'selected' : ''} >Direccion Principal</option>
                                                    <option value="2" ${requestScope.emp5.iexflgdomicilio=='2' ? 'selected' : ''} >Direccion Secundaria</option>
                                                  </select>
                                                  <label>Domicilio 2 (*)</label>
                                              </div>
                                          </div>


                                          <div class="alert alert-success" role="alert" id="alert5" style="display:none;">
                                                Se grabó exitosamente los cambios.
                                          </div>
                                          <div class="col-12 d-flex justify-content-end mt-6">
                                                <div class="col-6">
                                                    <a class="btn btn-phoenix-primary" href="listEmpleados">Cancel</a>
                                                    <button class="btn btn-primary col-8" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal5" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar</button>
                                                </div>
                                          </div>
                                          <div class="modal fade" id="confirmModal5" tabindex="-1">
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
                                                <div class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
                                                    <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="button" data-bs-dismiss="modal" >Cancel</button>
                                                    <button class="btn btn-sm btn-primary px-9 fs--2 my-0" onclick="mostrarAlert5();" type="submit" data-bs-dismiss="modal" >Confirmar</button>
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
                  </div>
                </div>
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