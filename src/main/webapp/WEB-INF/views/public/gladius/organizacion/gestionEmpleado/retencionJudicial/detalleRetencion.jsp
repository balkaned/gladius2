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
    <jsp:include page="../../../../links.jsp"></jsp:include>
  </head>

  <jsp:include page="../scriptsEmpl.jsp"></jsp:include>

<script>
$(document).ready(function(){
      var fechacargada=$("#iexfecinihidden").val();
      $("#iexfecini").val(fechacargada);

      var fechacargada2=$("#iexfecfinhidden").val();
      $("#iexfecfin").val(fechacargada2);
});

function formatearFecha1(){
    var fechaSeleccionada = $('#iexfecini').val();

    var anio=fechaSeleccionada.substring(0, 4);
    var mes=fechaSeleccionada.substring(5, 7);
    var dia=fechaSeleccionada.substring(8, 10);

    var fechaFormat=dia+"/"+mes+"/"+anio;
    $("#iexfecini").val(fechaFormat);
}

function formatearFecha2(){
    var fechaSeleccionada = $('#iexfecfin').val();

    var anio=fechaSeleccionada.substring(0, 4);
    var mes=fechaSeleccionada.substring(5, 7);
    var dia=fechaSeleccionada.substring(8, 10);

    var fechaFormat=dia+"/"+mes+"/"+anio;
    $("#iexfecfin").val(fechaFormat);
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
              <div class="pb-9">
                <div class="row">
                  <div class="col-12">
                    <div class="row align-items-center justify-content-between g-3 mb-3">
                    </div>
                  </div>
                </div>
                <div class="row g-0 g-md-4 g-xl-6">

                  <jsp:include page="../navsGenericEmpl.jsp"></jsp:include>

                  <div class="col-md-7 col-lg-7 col-xl-8">
                    <div class="lead-details-container">
                      <div class="content2">
                          <nav class="mb-2" aria-label="breadcrumb">
                            <ol class="breadcrumb mb-0">
                              <li class="breadcrumb-item"><a href="#!">Organización</a></li>
                              <li class="breadcrumb-item active">Trabajadores</li>
                              <li class="breadcrumb-item active">Retención Judicial</li>
                            </ol>
                          </nav>
                          <div class="mb-9">
                            <div class="row g-3 mb-4">
                              <div class="col-auto">
                                <h2 id="h2top" class="mb-0">Ver detalle retención</h2>
                              </div>
                            </div>

                            <div class="row g-5">
                                 <div class="col-xl-9">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-4 mb-0 needs-validation" method="POST" action="modificarRetencion" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />

                                            <div class="col-sm-6 col-md-3 ">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">ID</label>
                                                <input type="text" name="iexcorrel"  value="${iexcorrel}"  class="form-control" disabled />
                                                <input type="hidden" name="iexcorrel2"  value="${iexcorrel}"  />
                                            </div>
                                            <div class="col-sm-6 col-md-9">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Tipo de Retencion</label>
                                                <select class="form-select" name="iextipretjud" required disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipretj" items="${lovTipretj}">
                                                      <option value="${lovTipretj.idLov}"   ${lovTipretj.idLov== requestScope.xRetenJudEmp.iextipretjud ? 'selected' : ''}  >  ${lovTipretj.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Resolucion</label>
                                                <input class="form-control" name="iexresolucion" maxlength="50" type="text" value="${requestScope.xRetenJudEmp.iexresolucion}" required disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Proceso Planilla</label>
                                                <select class="form-select" name="iexcodpro" required disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovProcesos" items="${lovProcesos}">
                                                      <option value="${lovProcesos.idProceso}" ${lovProcesos.idProceso== requestScope.xRetenJudEmp.iexcodpro ? 'selected' : ''} >  ${lovProcesos.desProceso} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Fecha de Inicio</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                <input class="form-control datetimepicker" name="iexfecini" id="iexfecini" onchange="formatearFecha1();" value="${requestScope.xRetenJudEmp.iexfecini}" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required disabled />
                                                <input class="form-control" id="iexfecinihidden" type="hidden" value="${requestScope.xRetenJudEmp.iexfecini}" />
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Fecha Fin</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                <input class="form-control datetimepicker" name="iexfecfin" id="iexfecfin" onchange="formatearFecha2();" type="text" value="${requestScope.xRetenJudEmp.iexfecfin}" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required disabled />
                                                <input class="form-control" id="iexfecfinhidden" type="hidden" value="${requestScope.xRetenJudEmp.iexfecfin}" />
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Porcentaje</label>
                                                <input class="form-control" name="iexpordesct" maxlength="10" type="number" value="${requestScope.xRetenJudEmp.iexpordesct}"  step=0.01 placeholder="10%-> 10.0" required disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Imp. Fijo</label>
                                                <input class="form-control" name="ieximpfijo" maxlength="10" step=0.01 type="number" value="${requestScope.xRetenJudEmp.ieximpfijo}" value="0.0" required disabled/>
                                            </div>

                                            <div id="alert" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                                            	<span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                                            	<p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                                            	<button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                                            </div>
                                            <div class="col-12 gy-6">
                                                <div class="row g-3 justify-content-end">
                                                  <div class="col-auto">
                                                    <a class="btn btn-phoenix-primary" href="retencionJud@${idTrab}">Cancel</a>
                                                  </div>
                                                  <div class="col-auto">
                                                    <button class="btn btn-primary disabled" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar Retencion</button>
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

          <jsp:include page="../../../../demoWidget.jsp"></jsp:include>
    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->

    <jsp:include page="../../../../customize.jsp"></jsp:include>
  </body>
</html>