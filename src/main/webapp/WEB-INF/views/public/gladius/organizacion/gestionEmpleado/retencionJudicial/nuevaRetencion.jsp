<%--
    Created on : 15/06/2023, 12:20:00 PM
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
    <script src="resources/assets/js/gladius/scriptsEmpl.js"></script>
  </head>

<script>
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

          <div class="content bg-100">
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
                                <h2 id="h2top" class="mb-0">Nueva retención</h2>
                              </div>
                            </div>

                            <div class="row g-3">
                                 <div class="col-xl-9">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-3 mb-0 needs-validation" method="POST" action="insertarRetencion" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />

                                            <div class="col-12">
                                              <div class="card mb-3">
                                                <div class="card-body">
                                                  <div class="row g-3">
                                                    <div class="col-12 mb-2">
                                                      <h5 class="mb-0"><strong>Datos de la retención</strong></h5>
                                                    </div>

                                                    <div class="col-sm-6 col-md-6">
                                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de retención</label>
                                                        <select class="form-select" name="iextipretjud" required >
                                                          <option value="" selected >Seleccionar tipo retencion</option>
                                                          <c:forEach var="lovTipretj" items="${lovTipretj}">
                                                              <option value="${lovTipretj.idLov}"   ${lovTipretj.idLov == requestScope.iextipretjud ? 'selected' : ''}  >  ${lovTipretj.desLov} </option>
                                                          </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm-6 col-md-12">
                                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Resolución</label>
                                                        <input class="form-control" name="iexresolucion" maxlength="50" type="text" placeholder="Ingrese resolución" required />
                                                    </div>
                                                    <div class="col-sm-6 col-md-6">
                                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Proceso planilla</label>
                                                        <select class="form-select" name="iexcodpro" required >
                                                          <option value="" selected >Seleccionar proceso planilla</option>
                                                          <c:forEach var="lovProcesos" items="${lovProcesos}">
                                                              <option value="${lovProcesos.idProceso}" >  ${lovProcesos.desProceso} </option>
                                                          </c:forEach>
                                                        </select>
                                                    </div>

                                                    <div class="col-sm-6 col-md-6">
                                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de inicio</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                        <input class="form-control datetimepicker" name="iexfecini" id="iexfecini" onchange="formatearFecha1();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required />
                                                    </div>
                                                    <div class="col-sm-6 col-md-6">
                                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha fin</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                        <input class="form-control datetimepicker" name="iexfecfin" id="iexfecfin" onchange="formatearFecha2();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required />
                                                    </div>

                                                    <div class="col-sm-6 col-md-4">
                                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Porcentaje %</label>
                                                        <input class="form-control" name="iexpordesct" maxlength="10" type="number" step=0.01 placeholder="10%-> 10.0" required />
                                                    </div>
                                                    <div class="col-sm-6 col-md-6">
                                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Imp. fijo</label>
                                                        <input class="form-control" name="ieximpfijo" maxlength="10" step=0.01 type="number" value="0.0" required />
                                                    </div>

                                                  </div>
                                                </div>
                                              </div>



                                              <div class="d-flex justify-content-end gap-2 mt-3">
                                                <a class="btn btn-phoenix-primary" href="retencionJud@${idTrab}">Cancel</a>
                                                <button class="btn btn-primary" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar retencion</button>
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
                                            		  <button class="btn btn-sm btn-primary px-9 my-0 mt-1" type="submit" data-bs-dismiss="modal" >Confirmar</button>
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