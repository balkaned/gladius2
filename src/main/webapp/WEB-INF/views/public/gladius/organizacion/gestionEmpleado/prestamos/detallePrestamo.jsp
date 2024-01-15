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
                              <li class="breadcrumb-item"><a href="#!">Organización</a></li>
                              <li class="breadcrumb-item active">Trabajadores</li>
                              <li class="breadcrumb-item active">Préstamos</li>
                            </ol>
                          </nav>
                          <div class="mb-9">
                            <div class="row g-3 mb-4">
                              <div class="col-auto">
                                <h2 id="h2top" class="mb-0">Información de prestamo</h2>
                              </div>
                            </div>

                            <div class="row g-5">
                                 <div class="col-xl-10">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-4 mb-0 needs-validation" method="POST" action="insertarPrestamo" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">* Tipo de Prestamo</label>
                                                <select class="form-select" name="iextipprestamo" required disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach  var="lovTippres" items="${lovTippres}">
                                                      <option value="${lovTippres.idLov}" ${lovTippres.idLov== requestScope.xPrestCab.iextippres ? 'selected' : ''} >${lovTippres.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">* Importe Bruto</label>
                                                <input class="form-control" name="ieximpbruto" maxlength="10" type="number" value="${requestScope.xPrestCab.ieximpbru}" required disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">* Nro Cuotas</label>
                                                <input class="form-control" name="iexnrocuota" maxlength="2" type="text" value="${requestScope.xPrestCab.iexnrocuotas}" required disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">* Tipo Interes</label>
                                                <select class="form-select" name="iextipinteres" required disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach  var="lovTipInteres" items="${lovTipInteres}">
                                                      <option value="${lovTipInteres.idLov}" ${lovTipInteres.idLov== requestScope.xPrestCab.iextipinteres ? 'selected' : ''} >${lovTipInteres.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                 <label class="form-label fs-0 text-1000 ps-0 text-none">* Interes</label>
                                                 <input class="form-control" name="iexinteres" maxlength="4" value="${requestScope.xPrestCab.iexinteres}" type="text" required disabled placeholder="%"/>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">* Importe Total</label>
                                                <input class="form-control" name="ieximptotal" maxlength="15" value="${requestScope.xPrestCab.ieximptotal}" type="number" required disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">Glosa</label>
                                                <input class="form-control" name="iexglosa" maxlength="50" value="${requestScope.xPrestCab.iexglosa}" type="text" disabled />
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none">* Fecha de Prestamo</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                  <input class="form-control datetimepicker" name="iexfecpres" id="iexfecpres" value="${requestScope.xPrestCab.iexfecpres}" onchange="formatearFecha1();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none">* Fecha Ini Vigencia</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                  <input class="form-control datetimepicker" name="iexfecinivig" id="iexfecinivig" value="${requestScope.xPrestCab.iexfecinivig}" onchange="formatearFecha2();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none">* Frecuencia Préstamo</label>
                                                <select class="form-select" name="iexfrecuencia" required disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach  var="lovFrecPrestamo" items="${lovFrecPrestamo}">
                                                      <option value="${lovFrecPrestamo.idLov}" ${lovFrecPrestamo.idLov== requestScope.xPrestCab.iexfrecuencia ? 'selected' : ''} >${lovFrecPrestamo.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                     </form>
                                   </div>
                                 </div>
                            </div>
                            <div class="col-auto mt-4">
                                <h3 id="h2top" class="mb-0">Cronograma</h3>
                            </div>

                            <div class="border-top border-bottom border-200 mt-4" id="customerOrdersTable" data-list='{"valueNames":["order","total","payment_status","fulfilment_status","delivery_type","date"],"page":6,"pagination":true}'>
                            	<div class="table-responsive scrollbar">
                            		<table class="table table-sm fs--1 mb-0">
                            		  <thead>
                            			<tr>
                            			  <th class="sort white-space-nowrap align-middle ps-0 pe-3 text-uppercase" scope="col" data-sort="order" >ID</th>
                            			  <th class="sort align-middle text-center ps-5 pe-5 text-uppercase" scope="col" data-sort="total">Fecha Prestamo</th>
                            			  <th class="sort align-middle text-center white-space-nowrap ps-3 pe-3 text-uppercase" scope="col" data-sort="payment_status" >Importe Prestamo</th>
                            			  <th class="sort align-middle text-center white-space-nowrap text-start ps-3 pe-3 text-uppercase" scope="col" data-sort="fulfilment_status" >Interes</th>
                            			  <th class="sort align-middle white-space-nowrap text-center text-uppercase ps-3 pe-3" scope="col" data-sort="delivery_type" >Importe Total</th>
                            			  <th class="sort text-end text-center align-middle ps-3 pe-3 text-uppercase" scope="col"></th>
                            			</tr>
                            		  </thead>
                            		  <tbody class="list" id="customer-order-table-body">
                            			<c:forEach var="xPrestDet" items="${requestScope.xPrestDet}">
                            				<tr class="hover-actions-trigger btn-reveal-trigger position-static">
                            				  <td class="align-middle white-space-nowrap ps-3 pe-3"><a class="fw-semi-bold" href="#!">#${xPrestDet.iexidcuota}</a></td>
                            				  <td class="align-middle text-center fw-semi-bold ps-3 pe-3 text-1000"><a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${xPrestDet.iexfecpre}</td>
                            				  <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">${xPrestDet.ieximpbru}</td>
                            				  <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">${xPrestDet.iexinteres}</td>
                            				  <td class="align-middle white-space-nowrap text-center fw-bold text-1000 ps-3 pe-3">${xPrestDet.ieximptotal}</td>

                            				  <td class="align-middle white-space-nowrap text-end pe-0 ps-5">
                            					<div class="font-sans-serif btn-reveal-trigger position-static">
                            					  <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                            					  <div class="dropdown-menu dropdown-menu-end py-2">
                            						<a class="dropdown-item" href="#!">Info</a>
                            					  </div>
                            					</div>
                            				  </td>
                            				</tr>
                            			</c:forEach>
                            		  </tbody>
                            		</table>
                            	</div>
                            	<div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
                            		  <div class="col-auto d-flex">
                            			<p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!" data-list-view="*">View all<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none" href="#!" data-list-view="less">View Less<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
                            		  </div>
                            		  <div class="col-auto d-flex">
                            			<button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                            			<ul class="mb-0 pagination"></ul>
                            			<button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span></button>
                            		  </div>
                            	</div>
                            </div>

                            <div class="col-12 gy-6">
                                <div class="row g-3 justify-content-end">
                                  <div class="col-auto">
                                    <a class="btn btn-phoenix-primary mt-3" href="prestamos@${idTrab}">Atras</a>
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