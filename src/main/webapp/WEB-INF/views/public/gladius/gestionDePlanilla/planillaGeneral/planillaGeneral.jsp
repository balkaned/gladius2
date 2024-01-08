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

  <script>
     function regimen(){
        $.ajax({
          url: "getlovsPROXCON",
          data: {"accion": "PROXCON",
              "iexcodreg": $("#iexcodreg2").val()},
          success: function (data) {
              var opt = "";
                   opt += "<option value='0' >Seleccionar</option>";
                   for (var i in data) {
                    opt += "<option value="+data[i].idProceso+" > "+data[i].desProceso+" </option> ";
                   }

              $("#idproceso").html(opt);
          }
        });
     }

     function remove() {
        var opcion = confirm("Esta seguro de Eliminar el Registro?");
        if (opcion == true) {
            return true;
        } else {
            return false;
        }
     }

     function formatearFecha1(){
           var fechaSeleccionada = $('#fecini').val();

           var anio=fechaSeleccionada.substring(0, 4);
           var mes=fechaSeleccionada.substring(5, 7);
           var dia=fechaSeleccionada.substring(8, 10);

           var fechaFormat=dia+"/"+mes+"/"+anio;
           $("#fecini").val(fechaFormat);
     }

     function formatearFecha2(){
        var fechaSeleccionada = $('#fecfin').val();

        var anio=fechaSeleccionada.substring(0, 4);
        var mes=fechaSeleccionada.substring(5, 7);
        var dia=fechaSeleccionada.substring(8, 10);

        var fechaFormat=dia+"/"+mes+"/"+anio;
        $("#fecfin").val(fechaFormat);
     }

     function formatearFecha3(){
         var fechaSeleccionada = $('#fecinit').val();

         var anio=fechaSeleccionada.substring(0, 4);
         var mes=fechaSeleccionada.substring(5, 7);
         var dia=fechaSeleccionada.substring(8, 10);

         var fechaFormat=dia+"/"+mes+"/"+anio;
         $("#fecinit").val(fechaFormat);
     }

     function formatearFecha4(){
          var fechaSeleccionada = $('#fecfint').val();

          var anio=fechaSeleccionada.substring(0, 4);
          var mes=fechaSeleccionada.substring(5, 7);
          var dia=fechaSeleccionada.substring(8, 10);

          var fechaFormat=dia+"/"+mes+"/"+anio;
          $("#fecfint").val(fechaFormat);
     }

     function formatearFecha5(){
           var fechaSeleccionada = $('#fecpago').val();

           var anio=fechaSeleccionada.substring(0, 4);
           var mes=fechaSeleccionada.substring(5, 7);
           var dia=fechaSeleccionada.substring(8, 10);

           var fechaFormat=dia+"/"+mes+"/"+anio;
           $("#fecpago").val(fechaFormat);
      }

      function formatearFecha6(){
         var fechaSeleccionada = $('#feccerti').val();

         var anio=fechaSeleccionada.substring(0, 4);
         var mes=fechaSeleccionada.substring(5, 7);
         var dia=fechaSeleccionada.substring(8, 10);

         var fechaFormat=dia+"/"+mes+"/"+anio;
         $("#feccerti").val(fechaFormat);
      }
  </script>

  <body>
    <!-- ===============================================-->
    <!--    Main Content-->
    <!-- ===============================================-->
    <main class="main" id="top">
          <jsp:include page="../../../navsMenu.jsp"></jsp:include>
          <jsp:include page="../../../navTop.jsp"></jsp:include>
          <jsp:include page="../../../modalFade.jsp"></jsp:include>

          <div class="content">
            <nav class="mb-2" aria-label="breadcrumb">
              <ol class="breadcrumb mb-0">
                <li class="breadcrumb-item"><a href="#!">Gestion de Planillas</a></li>
                <li class="breadcrumb-item active">Planilla General</li>
              </ol>
            </nav>
            <div class="mb-12">
              <div class="row g-3 mb-4">
                <div class="col-auto">
                  <h2 id="h2top" class="mb-0">Planilla general</h2>
                </div>
              </div>

              <div class="row g-5">
                 <div class="col-xl-7">
                   <div class="row gx-3 gy-4">
                      <form class="row g-4 mb-0 needs-validation" method="POST" action="buscarPlanillaGen" novalidate >
                        <input id="usuario_id" type="hidden" name="usuario_id" value="${idUsu}"  />

                        <div class="col-sm-6 col-md-9">
                        	  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Regimen laboral</label>
                        	  <select name="iexcodreg" id="iexcodreg" class="form-select" required >
                        		  <option value="" selected >Seleccionar</option>
                        		  <c:forEach var="Lovs_regimen" items="${Lovs_regimen}">
                        			  <option value="${Lovs_regimen.idLov}" >${Lovs_regimen.desLov}</option>
                        		  </c:forEach>
                        	  </select>
                        </div>
                        <div class="col-sm-6 col-md-5">
                        	<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Periodo mensual o año</label>
                        	<input class="form-control" name="iexpermes" type="text" placeholder="202301 or 2023" />
                        </div>

                        <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                Se grabó exitosamente los cambios.
                        </div>
                        <div class="col-12 gy-6">
                            <div class="row g-3 justify-content-end">
                              <div class="col-auto">
                                <button class="btn btn-primary px-5" type="submit" ><span class="fas fa-search me-2"></span>Buscar</button>
                              </div>
                              <div class="col-auto">
                                <button class="btn btn-phoenix-primary px-5" type="button" data-bs-toggle="modal" data-bs-target="#periodoModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" ><span class="fas fa-plus me-2"></span>Ingresar periodo</button>
                              </div>
                            </div>
                        </div>

                      </form>
                   </div>
                 </div>
              </div>

              <div class="mt-4" id="orderTable" data-list='{"valueNames":["order","total","customer","payment_status","fulfilment_status","delivery_type","date"],"page":10,"pagination":true}'>

                  <div class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1">
                    <div class="table-responsive scrollbar mx-n1 px-1">
                      <table class="table table-sm fs--1 mb-0">
                        <tbody class="list" id="order-table-body">
                            <c:forEach var="List_Procesos" items="${requestScope.List_Procesos}" varStatus="loopCounter">
                                <c:if test="${List_Procesos.iexpermes != permes_cur }">
                                    <c:if test="${loopCounter.count ==0 }">
                                      <div class="table-responsive">
                                         <table class="table table-striped jambo_table bulk_action"  >
                                        <tr>
                                            <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">Grupo</th>
                                            <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">Periodo</th>
                                            <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">Proceso</th>
                                            <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">Fecini </th>
                                            <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">Fecfin</th>
                                            <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">Fecha Proceso</th>
                                            <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">Estado</th>
                                            <th></th>
                                        </tr>
                                    </c:if>

                                     <c:if test="${loopCounter.count >1 }">
                                         </table>
                                         </div>
                                         <br>
                                     </c:if>

                                    <div class="col-auto mt-3">
                                        <h2 class="mb-2">${List_Procesos.desmes}</h2>
                                        <h5 class="text-700 fw-semi-bold">${List_Procesos.iexpermes}</h5>
                                    </div>
                                    <br>
                                   <div class="table-responsive">
                                      <table class="table table-sm fs--1 mb-0">
                                          <thead>
                                             <tr>
                                                  <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                                    <div class="form-check mb-0 fs-0">
                                                      <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                                    </div>
                                                  </th>
                                                  <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">GRUPO</th>
                                                  <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">PERIODO</th>
                                                  <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">PROCESO</th>
                                                  <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">FECINI</th>
                                                  <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">FECFIN</th>
                                                  <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">FECHA PROCESO</th>
                                                  <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">ESTADO</th>
                                                  <th></th>
                                             </tr>
                                          </thead>
                                </c:if>
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                    <td class="fs--1 align-middle px-0 py-3">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}' />
                                      </div>
                                    </td>
                                    <td class="total align-middle text-start fw-semi-bold text-1000 ps-5">${List_Procesos.desgrppla}</td>
                                    <td class="total align-middle text-start fw-semi-bold text-1000 ps-5"><a class="fw-semi-bold" href="editarUsuario@${LstUsuario.idUsuario}">${List_Procesos.iexnroper}</a></td>
                                    <td class="total align-middle text-start fw-semi-bold text-600 ps-5">[${List_Procesos.iexcodpro}] - ${List_Procesos.desproceso}</td>
                                    <td class="total align-middle text-center fw-semi-bold text-1000 ps-5">${List_Procesos.iexfecini}</td>
                                    <td class="total align-middle text-start fw-semi-bold text-1000 ps-5">${List_Procesos.iexfecfin}</td>
                                    <td class="total align-middle text-start fw-semi-bold text-1000 ps-5">${List_Procesos.iexfecope}</td>
                                    <td class="total align-middle text-center fw-semi-bold text-1000 ps-5"><span class="badge badge-phoenix fs--2 badge-phoenix-info"><span class="badge-label">${List_Procesos.desestado}</span></td>
                                    <td class="align-middle text-end white-space-nowrap pe-0 action">
                                       <div class="font-sans-serif btn-reveal-trigger position-static">
                                         <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                         <div class="dropdown-menu dropdown-menu-end py-2">
                                              <a class="dropdown-item" href="#">Ver detalle</a>
                                              <div class="dropdown-divider"></div>
                                              <a class="dropdown-item text-danger" onclick="return remove();" href="eliminarRolXciaUsu@${usuxciaxrol.codusu}@${usuxciaxrol.codrol}@${usuxciaxrol.codcia}">Eliminar</a>
                                         </div>
                                       </div>
                                     </td>
                                  </tr>
                                <c:set var="permes_cur" value="${List_Procesos.iexpermes}" />
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

  <div class="modal fade" id="periodoModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="addDealModal" aria-hidden="true">
          <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content bg-100 p-6">
                  <div class="modal-header border-0 p-0 mb-2">
                    <h3 class="mb-0">Periodo proceso</h3>
                    <button class="btn btn-sm btn-phoenix-secondary" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times text-danger"></span></button>
                  </div>
                  <div class="modal-body px-0">
                    <div class="row g-4">
                        <div class="col-12">
                            <form class="row g-4 mb-0 needs-validation" method="POST" action="insertarPeriodoPlan" novalidate>
                                <div class="col-sm-6 col-md-6">
                                      <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Regimen laboral</label>
                                      <select name="iexcodreg2" id="iexcodreg2" onchange="regimen();" class="form-select" required >
                                          <option value="" selected >Seleccionar</option>
                                          <c:forEach var="Lovs_regimen" items="${Lovs_regimen}">
                                              <option value="${Lovs_regimen.idLov}" >${Lovs_regimen.desLov}</option>
                                          </c:forEach>
                                      </select>
                                </div>
                                <div class="col-sm-6 col-md-6">
                                      <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Proceso</label>
                                      <select name="idproceso" id="idproceso" class="form-select" required >
                                      </select>
                                </div>
                                <div class="col-sm-6 col-md-4">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Año</label>
                                    <input class="form-control" name="idanio" type="text" placeholder="2023" required/>
                                </div>
                                <div class="col-sm-6 col-md-4">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Mes YYYYMM</label>
                                    <input class="form-control" name="idpermes" type="text" placeholder="yyyymm" required/>
                                </div>
                                <div class="col-sm-6 col-md-4">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Periodo</label>
                                    <input class="form-control" name="idperiodo" type="text" placeholder="202301" required/>
                                </div>
                                <div class="col-sm-6 col-md-4">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha Inicial Nomina</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                    <input class="form-control datetimepicker" id="fecini" name="fecini" onchange="formatearFecha1();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required/>
                                </div>
                                <div class="col-sm-6 col-md-4">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha Final Nomina</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                    <input class="form-control datetimepicker" id="fecfin" name="fecfin" onchange="formatearFecha2();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required/>
                                </div>
                                <div class="col-sm-6 col-md-4">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha Inicial Tiempo</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                    <input class="form-control datetimepicker" id="fecinit" name="fecinit" onchange="formatearFecha3();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required/>
                                </div>
                                <div class="col-sm-6 col-md-4">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha Final Tiempo</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                    <input class="form-control datetimepicker" id="fecfint" name="fecfint" onchange="formatearFecha4();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required/>
                                </div>
                                <div class="col-sm-6 col-md-4">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de Pago</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                    <input class="form-control datetimepicker" id="fecpago" name="fecpago" onchange="formatearFecha5();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required/>
                                </div>
                                <div class="col-sm-6 col-md-4">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de Certificado/Boleta</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                    <input class="form-control datetimepicker" id="feccerti" name="feccerti" onchange="formatearFecha6();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required/>
                                </div>
                                <div class="modal-footer border-0 pt-6 px-0 pb-0">
                                    <button class="btn btn-link text-danger px-3 my-0" data-bs-dismiss="modal" aria-label="Close">Cerrar</button>
                                    <button type="submit" class="btn btn-primary my-0">Guardar Periodo</button>
                                </div>
                            </form>
                        </div>
                    </div>
                  </div>
            </div>
          </div>
        </div>
</html>

