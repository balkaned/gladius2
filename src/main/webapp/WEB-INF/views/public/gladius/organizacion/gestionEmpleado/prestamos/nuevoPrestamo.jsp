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
function formatearFecha1(){
    var fechaSeleccionada = $('#iexfecpres').val();

    var anio=fechaSeleccionada.substring(0, 4);
    var mes=fechaSeleccionada.substring(5, 7);
    var dia=fechaSeleccionada.substring(8, 10);

    var fechaFormat=dia+"/"+mes+"/"+anio;
    $("#iexfecpres").val(fechaFormat);
}

function formatearFecha2(){
    var fechaSeleccionada = $('#iexfecinivig').val();

    var anio=fechaSeleccionada.substring(0, 4);
    var mes=fechaSeleccionada.substring(5, 7);
    var dia=fechaSeleccionada.substring(8, 10);

    var fechaFormat=dia+"/"+mes+"/"+anio;
    $("#iexfecinivig").val(fechaFormat);
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
                              <li class="breadcrumb-item"><a href="#!">Page</a></li>
                              <li class="breadcrumb-item active">Default</li>
                            </ol>
                          </nav>
                          <div class="mb-9">
                            <div class="row g-3 mb-4">
                              <div class="col-auto">
                                <h2 id="h2top" class="mb-0">Insertar nuevo prestamo</h2>
                              </div>
                            </div>

                            <div class="row g-5">
                                 <div class="col-xl-10">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-4 mb-0 needs-validation" method="POST" action="insertarPrestamo" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">Tipo de Prestamo(*)</label>
                                                <select class="form-select" name="iextipprestamo" required >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach  var="lovTippres" items="${lovTippres}">
                                                      <option value="${lovTippres.idLov}" >${lovTippres.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">Importe Bruto</label>
                                                <input class="form-control" name="ieximpbruto" maxlength="10" type="number" required />
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">Nro Cuotas</label>
                                                <input class="form-control" name="iexnrocuota" maxlength="2" type="text" required />
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">Tipo Interes(*)</label>
                                                <select class="form-select" name="iextipinteres" required >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach  var="lovTipInteres" items="${lovTipInteres}">
                                                      <option value="${lovTipInteres.idLov}" >${lovTipInteres.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                 <label class="form-label fs-0 text-1000 ps-0 text-none">Interes</label>
                                                 <input class="form-control" name="iexinteres" maxlength="4" type="text" required placeholder="%"/>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">Importe Total</label>
                                                <input class="form-control" name="ieximptotal" maxlength="15" type="number" required />
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">Glosa</label>
                                                <input class="form-control" name="iexglosa" maxlength="50" type="text" />
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none">Fecha de Prestamo (*)</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                  <input class="form-control datetimepicker" name="iexfecpres" id="iexfecpres" onchange="formatearFecha1();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required />
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none">Fecha Ini Vigencia (*)</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                  <input class="form-control datetimepicker" name="iexfecinivig" id="iexfecinivig" onchange="formatearFecha2();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required />
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">Frecuencia Prestamo (*)</label>
                                                <select class="form-select" name="iexfrecuencia" required>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach  var="lovFrecPrestamo" items="${lovFrecPrestamo}">
                                                      <option value="${lovFrecPrestamo.idLov}" >${lovFrecPrestamo.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                            </div>

                                            <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                                Se grabó exitosamente los cambios.
                                            </div>
                                            <div class="col-12 gy-6">
                                                <div class="row g-3 justify-content-end">
                                                  <div class="col-auto">
                                                    <a class="btn btn-phoenix-primary" href="prestamos@${idTrab}">Cancel</a>
                                                    <button class="btn btn-primary" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar Prestamo</button>
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