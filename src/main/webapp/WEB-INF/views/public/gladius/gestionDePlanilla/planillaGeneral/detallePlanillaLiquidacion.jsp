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
    <jsp:include page="../../../links.jsp"></jsp:include>
  </head>

  <script>
     function formatearFecha1(){
          var fechaSeleccionada = $('#iexfeccese').val();

          var anio=fechaSeleccionada.substring(0, 4);
          var mes=fechaSeleccionada.substring(5, 7);
          var dia=fechaSeleccionada.substring(8, 10);

          var fechaFormat=dia+"/"+mes+"/"+anio;
          $("#iexfeccese").val(fechaFormat);
     }

     function formatearFecha2(){
        var fechaSeleccionada = $('#iexfecpago').val();

        var anio=fechaSeleccionada.substring(0, 4);
        var mes=fechaSeleccionada.substring(5, 7);
        var dia=fechaSeleccionada.substring(8, 10);

        var fechaFormat=dia+"/"+mes+"/"+anio;
        $("#iexfecpago").val(fechaFormat);
     }

     $(document).ready(function() {
       var fechacargada=$("#iexfeccesehidden").val();
       $("#iexfeccese").val(fechacargada);

       var fechacargada=$("#iexfecpagohidden").val();
       $("#iexfecpago").val(fechacargada);
     });

     function remove() {
        var opcion = confirm("Esta seguro de Eliminar el item?");
        if (opcion == true) {
            return true;
        } else {
            return false;
        }
     }

     function actualizar(id,iexcodtra,iexcodpro,iexperiodo,iexcodcon,iexcorrel,iexcodreg,grppla) {
         var opcion = confirm("Esta seguro de actualizar el item?");

         if (opcion == true) {
             var valorActualizar=$("#"+id+"_valor").val();
             console.log("valorActualiza: "+valorActualizar);
             var id2="dropdownmenutable_"+id;
             console.log("id2: "+id2);
             document.getElementById(id2).href="actualizarValorTrabConceptLiqVar@"+iexcodtra+"@"+iexcodpro+"@"+iexperiodo+"@"+iexcodcon+"@"+iexcorrel+"@"+iexcodreg+"@"+valorActualizar+"@"+grppla;

             return true;
         } else {
             return false;
         }
     }

     function enviaForm(variable){
          if(variable==15){
            var iexfeccese = $("#iexfeccese").val();
            if (iexfeccese == "") {
              alert("Fecha cese se debe completar");
              return;
            }

            var idxtipcese = $("#idxtipcese").val();
            if (idxtipcese == "") {
              alert("Tipo cese se debe seleccionar algún valor");
              return;
            }

            var iexfecpago = $("#iexfecpago").val();
            if (iexfecpago == "") {
              alert("Fecha de pago se debe completar");
              return;
            }

            var txtobservacion = $("#txtobservacion").val();
            if (txtobservacion == "") {
              alert("Observación se debe completar");
              return;
            }

            var opcion = confirm("Esta seguro de grabar los datos de la cabecera?");

            if (opcion == true) {
               document.getElementById("accion").value="UPDLIQ";
               document.getElementById("formLiqPlanillas").submit();
               return true;
            } else {
               return false;
            }
          }else if(variable==16){
            var slccodcon = $("#slccodcon").val();
            if (slccodcon == "") {
              alert("Debe seleccionar un concepto");
              return;
            }

            var txtimporte = $("#txtimporte").val();
            if (txtimporte == "") {
              alert("Debe ingresar un importe");
              return;
            }

            var opcion = confirm("Esta seguro que desea insertar el concepto a la lista?");

            if (opcion == true) {
               document.getElementById("accion").value="INSVARLIQ";
               document.getElementById("formLiqPlanillas").submit();
               return true;
            } else {
               return false;
            }
          } else if(variable==17){
                var opcion = confirm("Esta seguro que desea cerrar la planilla para este trabajador?");

                if (opcion == true) {
                   document.getElementById("accion").value="CIERRLIQ";
                   document.getElementById("formLiqPlanillas").submit();
                   return true;
                } else {
                   return false;
                }
          } else if(variable==18){
                var opcion = confirm("Esta seguro que desea eliminar al trabajador de la planilla liquidación?");

                if (opcion == true) {
                   document.getElementById("accion").value="DELPRO";
                   document.getElementById("formLiqPlanillas").submit();
                   return true;
                } else {
                   return false;
                }
          } else if(variable==19){
                var opcion = confirm("Esta seguro que desea procesar la planilla de liquidación para este trabajador?");

                if (opcion == true) {
                   document.getElementById("accion").value="EXEPROLIQ";
                   document.getElementById("formLiqPlanillas").submit();
                   return true;
                } else {
                   return false;
                }
          }

     }

     function descargarBoletaLiq(idComp){
         var codtra = $("#iexcodtra").val();

         var iexcodpro = $("#iexcodpro").val();
         var iexperiodo = $("#iexperiodo").val();
         var iexcorrel = $("#iexcorrel").val();

         var params="3UP_CODPRO="+iexcodpro+"UP_NROPER="+iexperiodo+"UP_CORREL="+iexcorrel;

         document.getElementById("botonDescargarBoletaTrabLiq").href="AWSorFTP_flgsource@verReportePDF@"+idComp+"@"+codtra+"@null@null@BoletaEmpTraLiq@"+params+"@null@null@null";
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

          <div class="content bg-100">
            <nav class="mb-2" aria-label="breadcrumb">
              <ol class="breadcrumb mb-0">
                <li class="breadcrumb-item"><a href="#!">Gestión de Planillas</a></li>
                <li class="breadcrumb-item active">Planilla General</li>
                <li class="breadcrumb-item active">Detalle Liq. Trabajador</li>
              </ol>
            </nav>
            <div class="mb-12">
              <div class="row g-3 mb-2">
                <div class="col-auto">
                  <h2 id="h2top" class="mb-0">Detalle Liq. Trabajador</h2>
                  <span class="badge badge-tag me-2 mb-2 fs--3">flgciedet: (${LstPlanillaRes.flgciedet})</span>
                </div>
              </div>
              <form id="formLiqPlanillas" class="row g-3 mb-0 needs-validation" method="POST" action="gestionarPlanLiq" novalidate >
                  <div class="row g-3">
                     <div class="col-xl-12">
                       <div class="row gx-3 gy-4">
                            <input type="hidden" name="iexcodreg" id="iexcodreg" value="${requestScope.iexcodreg}">
                            <input type="hidden" name="iexcodpro" id="iexcodpro" value="${requestScope.iexcodpro}">
                            <input type="hidden" name="iexperiodo" id="iexperiodo" value="${requestScope.iexperiodo}">
                            <input type="hidden" name="accion" id="accion" value="${requestScope.xaccion}" >
                            <input type="hidden" name="iexcodtra" id="iexcodtra" value="${requestScope.iexcodtra}">
                            <input type="hidden" name="iexcorrel" id="iexcorrel" value="${requestScope.iexcorrel}">

                            <input type="hidden" name="grppla" id="grppla" value="${requestScope.xgrppla}">
                            <input type="hidden" name="idcodcon" id="idcodcon" value="">
                            <input type="hidden" name="idvalcon" id="idvalcon" value="">

                            <div class="col-sm-6 col-md-4">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Regimen</label>
                                <input class="form-control" name="iexnroiddep" type="text" placeholder="#" value="${requestScope.xproplaper.desregimen}" disabled />
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Procesos</label>
                                <input class="form-control" name="iexnroiddep" type="text" placeholder="#" value="${requestScope.xproplaper.desproceso}" disabled />
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Periodo</label>
                                <input class="form-control" name="iexnroiddep" type="text" placeholder="#" value="${requestScope.xproplaper.iexnroper} [ ${requestScope.xproplaper.timerfecini} - ${requestScope.xproplaper.timerfecfin}" disabled />
                            </div>
                            <div class="col-sm-6 col-md-2">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Id de liquidaciones</label>
                                <input class="form-control text-center" name="iexnroiddep" type="text" placeholder="#" value="${LstPlanillaRes.iexcorrel}" disabled />
                            </div>
                            <div class="col-sm-6 col-md-2">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Id de trabajador</label>
                                <input class="form-control text-center" name="iexnroiddep" type="text" placeholder="#" value="${LstPlanillaRes.iexcodtra}" disabled />
                            </div>
                            <div class="col-sm-6 col-md-5">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Trabajador</label>
                                <input class="form-control" name="iexnroiddep" type="text" placeholder="#" value="${LstPlanillaRes.destra}" disabled />
                            </div>
                            <div class="col-sm-6 col-md-2">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de ingreso</label>
                                <input class="form-control text-center" name="iexnroiddep" type="text" placeholder="#" value="${LstPlanillaRes.iexfecing}" disabled />
                            </div>
                            <div class="col-sm-6 col-md-2">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de cese </label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                <input class="form-control datetimepicker" name="iexfeccese" id="iexfeccese" onchange="formatearFecha1();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required ${LstPlanillaRes.flgciedet == '3' ? 'disabled' : ''} />
                                <input class="form-control" id="iexfeccesehidden" type="hidden" value="${LstPlanillaRes.iexfeccese}" />
                            </div>
                            <div class="col-sm-6 col-md-1">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Año</label>
                                <input class="form-control text-center" name="iexnroiddep" type="text" placeholder="#" value="${LstPlanillaRes.iexanio_benef}" disabled />
                            </div>
                            <div class="col-sm-6 col-md-1">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Meses</label>
                                <input class="form-control text-center" name="iexnroiddep" type="text" placeholder="#" value="${LstPlanillaRes.iexmes_benef}" disabled />
                            </div>
                            <div class="col-sm-6 col-md-1">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Dia</label>
                                <input class="form-control text-center" name="iexnroiddep" type="text" placeholder="#" value="${LstPlanillaRes.iexdia_benef}" disabled />
                            </div>
                            <div class="col-sm-6 col-md-5">
                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de cese</label>
                                  <select id="idxtipcese" name="idxtipcese" class="form-select " required ${LstPlanillaRes.flgciedet == '3' ? 'disabled' : ''} >
                                      <option value="" selected >Seleccionar</option>
                                      <c:forEach var="lstTipCese" items="${lstTipCese}">
                                          <option value="${lstTipCese.idLov}" ${lstTipCese.idLov == LstPlanillaRes.iextipcese ? 'selected' : ''}>${lstTipCese.desLov}</option>
                                      </c:forEach>
                                  </select>
                            </div>
                            <div class="col-sm-6 col-md-2 mt-7">
                                <input type="checkbox" name="flgboltrunc" id="flgboltrunc" class="form-check-input" ${LstPlanillaRes.flgboltrunc == '1' ? 'checked=true' : ''} ${LstPlanillaRes.flgciedet == '3' ? 'disabled' : ''} >
                                <label class="form-check-label ms-2">Flag Boleta trunca</label>
                            </div>
                            <div class="col-sm-6 col-md-2">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de pago</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                <input class="form-control datetimepicker" name="iexfecpago" id="iexfecpago" onchange="formatearFecha2();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required ${LstPlanillaRes.flgciedet == '3' ? 'disabled' : ''} />
                                <input class="form-control" id="iexfecpagohidden" type="hidden" value="${LstPlanillaRes.fecpago}" />
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Observación</label>
                                <input class="form-control" id="txtobservacion" name="txtobservacion" type="text" value="${LstPlanillaRes.iexobscese}" placeholder="Ingrese observación aqui" required ${LstPlanillaRes.flgciedet == '3' ? 'disabled' : ''} />
                            </div>
                            <div class="col-sm-6 col-md-2">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Estado</label>
                                <c:if test="${LstPlanillaRes.flgciedet!='3'}">
                                    <a class="form-control text-center bg-100 text-success disabled" ><span class="fas fa-unlock me-2"></span>Abierto</a>
                                </c:if>
                                <c:if test="${LstPlanillaRes.flgciedet=='3'}">
                                    <a class="form-control text-center bg-100 text-danger disabled" ><span class="fas fa-lock me-2"></span>Cerrado</a>
                                </c:if>
                            </div>
                            <div class="col-12 gy-3">
                                <div class="col-12">
                                    <a class="btn btn-phoenix-secondary btn-sm px-5 mt-1" href="listarDetallePlanillaGen@${iexcodreg}@${iexcodpro}@${iexperiodo}"><span class="fas fa-reply me-2"></span>Atras</a>
                                    <a class="btn btn-primary btn-sm mt-1 ${LstPlanillaRes.flgciedet == '3' ? 'disabled' : ''} " onclick="enviaForm('15')" href="#"><span class="fas fa-floppy-disk me-2"></span>Guardar datos</a>
                                </div>
                            </div>

                            <div class="col-sm-6 col-md-4">
                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Concepto</label>
                              <select id="slccodcon" name="slccodcon" class="form-select" required ${LstPlanillaRes.flgciedet == '3' ? 'disabled' : ''} >
                                  <option value="" selected >Seleccionar</option>
                                  <c:forEach var="lovConcepProVar" items="${lovConcepProVar}">
                                     <option value="${lovConcepProVar.codConcepto}">[${lovConcepProVar.codConcepto}] - ${lovConcepProVar.desConcepto}</option>
                                  </c:forEach>
                              </select>
                            </div>
                            <div class="col-sm-6 col-md-2">
                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Importe</label>
                              <input class="form-control" name="txtimporte" id="txtimporte" maxlength="10" step=0.01 type="number" value="" placeholder="Ingrese importe: 00.00" required ${LstPlanillaRes.flgciedet == '3' ? 'disabled' : ''} />
                            </div>
                            <div class="col-sm-6 col-md-6">
                                <button class="btn btn-phoenix-primary btn-sm mt-5 ${LstPlanillaRes.flgciedet == '3' ? 'disabled' : ''} " type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" ><span class="fas fa-plus me-2"></span>Add variable</button>
                            </div>

                            <div id="alert" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                                <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                                <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                                <a class="text-success fs-0 fw-bold" href="#" data-bs-dismiss="alert" aria-label="Close">x</a>
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
                                      <button class="btn btn-sm btn-phoenix-primary px-4  my-0 mt-1" type="button" data-bs-dismiss="modal" >Cancel</button>
                                      <button class="btn btn-sm btn-primary px-9  my-0 mt-1" onclick="enviaForm('16')" type="submit" data-bs-dismiss="modal" >Confirmar</button>
                                  </div>
                                </div>
                              </div>
                            </div>
                       </div>
                     </div>
                  </div>

                  <div class="mt-4" id="orderTable" data-list='{"valueNames":["id","desconcept","val"],"page":10,"pagination":true}'>
                      <div class="mb-3">
                        <div class="g-3">
                          <div class="col-auto">
                            <div class="search-box">
                              <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                <input class="form-control search-input search" type="search" placeholder="Search concepto" aria-label="Search"/>
                                <span class="fas fa-search search-box-icon"></span>
                              </form>
                            </div>
                          </div>

                          <div class="mt-3 mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1" data-list='{"valueNames":["id","desconcept","val"],"page":10, "pagination":true }' >
                            <div class="table-responsive scrollbar mx-n1 px-1">
                              <table class="table table-sm fs--1 mb-0">
                                <thead>
                                  <tr>
                                    <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                      </div>
                                    </th>
                                    <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="id">ID CONCEPT</th>
                                    <th class="sort align-middle text-center ps-5" scope="col" data-sort="desconcept">DESCRIPCIÓN CONCEPTO</th>
                                    <th class="sort align-middle text-center ps-5" scope="col" data-sort="val" >VALOR</th>
                                    <th class="sort align-middle text-center ps-5" scope="col" ></th>
                                  </tr>
                                </thead>
                                <tbody class="list" id="order-table-body">
                                    <c:forEach var="fdatvar" items="${requestScope.fdatvar}">
                                      <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                        <td class="fs--1 align-middle px-0 py-3">
                                          <div class="form-check mb-0 fs-0">
                                            <input class="form-check-input" type="checkbox" />
                                          </div>
                                        </td>
                                        <td class="id align-middle white-space-nowrap text-start fw-semi-bold text-1000 ps-0"><a class="fw-semi-bold" href="#">${fdatvar.iexcodcon}</a></td>
                                        <td class="desconcept align-middle text-start fw-semi-bold text-1000 ps-5">${fdatvar.coodescon}</td>
                                        <td class="val align-middle text-start fw-semi-bold text-1000 ps-5">
                                            <c:if test="${LstPlanillaRes.flgciedet!='3'}">
                                                <input class="form-control text-end" style="width:120px !important;" type="number" step=0.01 id="${fdatvar.iexcodtra}_${fdatvar.iexcodcon}_valor" name="${fdatvar.iexcodtra}_${fdatvar.iexcodcon}" value="${fdatvar.iexvalcon}" />
                                            </c:if>
                                            <c:if test="${LstPlanillaRes.flgciedet=='3'}">
                                                <input class="form-control text-end" style="width:120px !important;" type="number" step=0.01 id="${fdatvar.iexcodtra}_${fdatvar.iexcodcon}_valor" name="${fdatvar.iexcodtra}_${fdatvar.iexcodcon}" value="${fdatvar.iexvalcon}" disabled />
                                            </c:if>
                                        </td>

                                        <td class="align-middle text-end white-space-nowrap pe-0 action">
                                           <div class="font-sans-serif btn-reveal-trigger position-static">
                                             <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                             data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                             <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                             <div class="dropdown-menu dropdown-menu-end py-2">
                                                  <c:if test="${LstPlanillaRes.flgciedet!='3'}">
                                                    <a id="dropdownmenutable_${iexcodtra}_${fdatvar.iexcodcon}" class="dropdown-item" onclick="return actualizar('${iexcodtra}_${fdatvar.iexcodcon}','${iexcodtra}','${iexcodpro}','${iexperiodo}','${fdatvar.iexcodcon}','${iexcorrel}','${iexcodreg}','${xgrppla}');" href="#"><span class="fa-solid fa-arrows-rotate me-2"></span>Actualizar</a>
                                                  </c:if>
                                                  <c:if test="${LstPlanillaRes.flgciedet=='3'}">
                                                    <a id="" class="dropdown-item disabled" href="#"><span class="fa-solid fa-arrows-rotate me-2"></span>Actualizar</a>
                                                  </c:if>
                                                  <div class="dropdown-divider"></div>
                                                  <c:if test="${LstPlanillaRes.flgciedet!='3'}">
                                                    <a id="dropdownmenutable" class="dropdown-item" onclick="return remove();" href="eliminarPlanConcepVarLiq@${iexcodpro}@${iexperiodo}@1@${iexcodtra}@${fdatvar.iexcodcon}@${iexcorrel}@${xgrppla}"><span class="fa-solid fa-trash me-2"></span>Eliminar</a>
                                                  </c:if>
                                                  <c:if test="${LstPlanillaRes.flgciedet=='3'}">
                                                    <a id="" class="dropdown-item disabled" onclick="return remove();" ><span class="fa-solid fa-trash me-2"></span>Eliminar</a>
                                                  </c:if>
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
                        </div>
                      </div>
                  </div>

                  <c:if test="${LstPlanillaRes.flgciedet!='3'}">
                    <a name="procesar" onclick="enviaForm('19')" class="btn btn-phoenix-success btn-sm"><span class="fas fa-wrench me-2"></span>Procesar</a>
                  </c:if>
                  <a id="botonDescargarBoletaTrabLiq" target="_blank"
                        onclick="descargarBoletaLiq(${idComp});"
                        class="btn btn-phoenix-secondary btn-sm"><span class="fas fa-calculator me-2"></span>Liq. Benef. sociales (LBS)</a>

                  <c:if test="${LstPlanillaRes.flgciedet!='3'}">
                    <a name="Cerrar" onclick="enviaForm('17')" class="btn btn-phoenix-secondary btn-sm"><span class="fas fa-user-lock me-2"></span>Cerrar</a>
                  </c:if>
                  <c:if test="${LstPlanillaRes.flgciedet!='3'}">
                    <a name="Eliminar" onclick="enviaForm('18')" class="btn btn-phoenix-danger btn-sm"><span class="fas fa-trash me-2"></span>Eliminar</a>
                  </c:if>

                  <a target="_blank"
                    href="AWSorFTP_flgsource@verReportePDF@${idComp}@${LstPlanillaRes.iexcodtra}@null@null@CertiLiqTra@3UP_CODPRO=${iexcodpro}UP_NROPER=${iexperiodo}UP_CORREL=${LstPlanillaRes.iexcorrel}@null@null@null"
                    class="btn btn-phoenix-primary btn-sm"><span class="fas fa-bookmark me-2"></span>Certificado de trab.</a>
              </form>
            </div>
            <jsp:include page="../../../footer.jsp"></jsp:include>
          </div>

          <jsp:include page="../../../demoWidget.jsp"></jsp:include>
    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->

    <jsp:include page="../../../customize.jsp"></jsp:include>
  </body>
</html>