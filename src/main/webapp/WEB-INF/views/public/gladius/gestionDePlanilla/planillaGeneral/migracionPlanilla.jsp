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
     $(document).ready(function() {
       $('#lov_compania').change(function(event){
           $.ajaxSetup({cache:false});
                $.ajax({
                  url: "getLovsLOVCODTRAxUSU",
                  data: {
                        "accion": "getLovsLOVCODTRAxUSU",
                        "iexcodcia": $("#lov_compania").val()
                  },
                  success: function (data) {
                       var opt = "";
                       opt += "<option value='' >Seleccionar trabajador</option>";
                       for (var i in data) {
                            opt += "<option value="+data[i].iexcodtra+" > "+data[i].iexapepat+" "+data[i].iexapemat+" "+data[i].iexnomtra+" - "+data[i].iexfecing+" </option> ";
                       }
                      $("#iexcodtra").html(opt);
                  }
                });
       });
     });

     function remove() {
        var opcion = confirm("Esta seguro de Eliminar el Registro?");
        if (opcion == true) {
            return true;
        } else {
            return false;
        }
     }

     function actualizar(id,iexcodtra,iexcodpro,iexperiodo,iexcodcon,iexcodreg) {
         var opcion = confirm("Esta seguro de actualizar el Registro?");

         if (opcion == true) {
             var valorActualizar=$("#"+id+"_valor").val();
             var id2="dropdownmenutable_"+id;
             document.getElementById(id2).href="actualizarMigValorTrabConcept@"+iexcodtra+"@"+iexcodpro+"@"+iexperiodo+"@"+iexcodcon+"@1@"+iexcodreg+"@"+valorActualizar;
             //return true;
         } else {
             return false;
         }
     }

     function enviaForm(variable){
          if(variable==13){
              document.getElementById ("formVariable").encoding="multipart/form-data";
              document.getElementById("accion").value="UPXLSVAR";
              document.getElementById("formVariable").submit();
          }
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
                <li class="breadcrumb-item"><a href="#!">Gestión de Planillas</a></li>
                <li class="breadcrumb-item active">Planilla General</li>
                <li class="breadcrumb-item active">Migración Planilla</li>
              </ol>
            </nav>
            <div class="mb-12">
              <div class="row g-3 mb-2">
                <div class="col-auto">
                  <h2 id="h2top" class="mb-0">Migración planilla</h2>
                </div>
              </div>

              <div class="row g-5">
                 <div class="col-xl-9">
                   <div class="row gx-3 gy-4">
                      <form id="formVariable" class="row g-4 mb-0 needs-validation" method="POST" action="gestionarMigracionTrabConcepValor" novalidate >
                        <input type="hidden" name="iexcodreg" id="iexcodreg" value="${requestScope.iexcodreg}" />
                        <input type="hidden" name="accion" id="accion" value="${requestScope.xaccion}" />
                        <input type="hidden" name="grppla" value="${requestScope.xgrppla}" />
                        <input type="hidden" name="tipfile" id="tipfile" value="" />
                        <input type="hidden" name="iexcodpro" id="iexcodpro" value="${requestScope.iexcodpro}" />
                        <input type="hidden" name="iexperiodo" id="iexperiodo" value="${requestScope.iexperiodo}" />
                        <input type="hidden" name="iexcodtra" id="iexcodtra" value="-1" />
                        <input type="hidden" name="iexcorrel" id="iexcorrel" value="1" />
                        <input type="hidden" name="idcodtra" id="idcodtra" value="" />
                        <input type="hidden" name="idcodcon" id="idcodcon" value="" />
                        <input type="hidden" name="idvalcon" id="idvalcon" value="" />

                        <div class="col-sm-6 col-md-8">
                            <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Regimen</label>
                            <input class="form-control" name="iexnroiddep" maxlength="15" type="text" placeholder="" value="${requestScope.xproplaper.desregimen}" disabled/>
                        </div>
                        <div class="col-sm-6 col-md-6">
                            <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Proceso</label>
                            <input class="form-control" name="iexnroiddep" maxlength="15" type="text" placeholder="" value="${requestScope.xproplaper.desproceso}" disabled/>
                        </div>
                        <div class="col-sm-6 col-md-6">
                            <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Periodo</label>
                            <input class="form-control" name="iexnroiddep" maxlength="15" type="text" placeholder="" value="${requestScope.xproplaper.iexnroper}  - [ ${requestScope.xproplaper.timerfecini} - ${requestScope.xproplaper.timerfecfin} ]" disabled/>
                        </div>
                        <div class="col-sm-6 col-md-4">
                            <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Estado</label>
                            <input class="form-control" name="iexnroiddep" maxlength="15" type="text" placeholder="" value="${requestScope.xproplaper.desestado}" disabled/>
                        </div>
                        <div class="col-sm-6 col-md-8">
                            <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Seleccionar excel</label>
                            <input class="form-control" id="uploadFile" name="uploadFile" type="file" placeholder="" />
                        </div>

                        <div id="alert" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                        	<span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                        	<p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                        	<button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                        <div class="col-12 gy-6">
                            <div class="col-12">
                                <a class="btn btn-phoenix-secondary btn-sm px-5" href="listarDetallePlanillaGen@${requestScope.iexcodreg}@${requestScope.iexcodpro}@${requestScope.iexperiodo}"><span class="fas fa-reply me-2"></span>Atras</a>
                                <a class="btn btn-primary btn-sm " type="button" data-bs-toggle="modal" data-bs-target="#confirmModalCargarExcel" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" href="#"><span class="fas fa-upload me-2"></span>Cargar excel</a>
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
                        		  <button class="btn btn-sm btn-phoenix-primary px-4  my-0 mt-1" type="button" data-bs-dismiss="modal" >Cancel</button>
                        		  <button class="btn btn-sm btn-primary px-9 my-0 mt-1" onclick="enviaForm('13')" type="submit" data-bs-dismiss="modal" >Confirmar</button>
                        	  </div>
                        	</div>
                          </div>
                        </div>
                        <div class="modal fade" id="confirmModalCargarExcel" tabindex="-1">
                          <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content border">
                              <form id="addEventForm" autocomplete="off">
                                <div class="modal-header border-200 p-4">
                                  <h5 class="modal-title text-1000 fs-4 lh-sm">Importar Excel</h5>
                                  <button class="btn p-1 text-900" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs--1"></span></button>
                                </div>
                                <div class="modal-body pt-4 pb-2 px-4">
                                  <div class="mb-3">
                                    <label class="fw-bold mb-2 text-1000" for="leadStatus">Esta seguro que desea importar el excel?</label>
                                  </div>
                                </div>
                              </form>
                              <div class="modal-footer d-flex justify-content-end align-items-center px-0 pb-0 border-200 pt-0">
                                  <button class="btn btn-sm btn-phoenix-secondary px-4  my-0 mt-1" type="button" data-bs-dismiss="modal" >Cancel</button>
                                  <button class="btn btn-sm btn-success px-9  my-0 mt-1" onclick="enviaForm('13')" type="submit" data-bs-dismiss="modal" ><span class="fa-solid fa-upload fs--1 me-2"></span>Subir</button>
                              </div>
                            </div>
                          </div>
                        </div>
                      </form>
                   </div>
                 </div>
              </div>

              <div class="mt-4" id="orderTable" data-list='{"valueNames":["id","trab","id_concept","des_concept"],"page":10,"pagination":true}'>
                  <div class="mb-3">
                    <div class="g-3">
                      <div class="col-auto">
                        <div class="search-box">
                          <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                            <input class="form-control search-input search" type="search" placeholder="Search trabajador" aria-label="Search"/>
                            <span class="fas fa-search search-box-icon"></span>
                          </form>
                        </div>
                      </div>

                      <div class="mt-3 mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1" data-list='{"valueNames":["id","trab","id_concept","des_concept"],"page":10, "pagination":true }' >
                        <div class="table-responsive scrollbar mx-n1 px-1">
                          <table class="table table-sm fs--1 mb-0">
                            <thead>
                              <tr>
                                <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                  <div class="form-check mb-0 fs-0">
                                    <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                  </div>
                                </th>
                                <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="id">ID</th>
                                <th class="sort align-middle text-center ps-5" scope="col" data-sort="trab">TRABAJADOR</th>
                                <th class="sort align-middle text-center ps-5" scope="col" data-sort="id_concept">ID CONCEPT</th>
                                <th class="sort align-middle text-center ps-5" scope="col" data-sort="des_concept">DESC CONCEPT</th>
                                <th class="sort align-middle text-center ps-5" scope="col" >VALOR</th>
                                <th class="sort align-middle text-center ps-5" scope="col" ></th>
                              </tr>
                            </thead>
                            <tbody class="list" id="order-table-body">
                                <c:forEach var="fdatavar" items="${requestScope.fdatavar}">
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                    <td class="fs--1 align-middle px-0 py-3">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}' />
                                      </div>
                                    </td>
                                    <td class="id align-middle white-space-nowrap text-start fw-semi-bold text-1000 ps-0"><a class="fw-semi-bold" href="editarConcepto@${concepto.codConcepto}">#${fdatavar.iexcodtra}</a></td>
                                    <td class="trab align-middle text-start fw-semi-bold text-1000 ps-5">${fdatavar.nomdestra}</td>
                                    <td class="id_concept align-middle text-center fw-semi-bold text-1000 ps-5"><span class="badge badge-tag me-2 mb-2">${fdatavar.iexcodcon}</span></td>
                                    <td class="des_concept align-middle text-start fw-semi-bold text-1000 ps-5">${fdatavar.coodescon}</td>
                                    <td class="align-middle text-start fw-semi-bold text-1000 ps-5">
                                         <input class="form-control" style="width:120px !important;" type="number" step=0.01 id="${fdatavar.iexcodtra}_${fdatavar.iexcodcon}_valor" name="${fdatavar.iexcodtra}_${fdatavar.iexcodcon}" value="${fdatavar.iexvalcon}"
                                         <c:if test="${requestScope.xproplaper.flgestado eq '3' }" > readonly </c:if> >
                                    </td>

                                    <td class="align-middle text-end white-space-nowrap pe-0 action">
                                       <div class="font-sans-serif btn-reveal-trigger position-static">
                                         <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                         data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                         <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                         <div class="dropdown-menu dropdown-menu-end py-2">
                                              <a id="dropdownmenutable_${fdatavar.iexcodtra}_${fdatavar.iexcodcon}" class="dropdown-item" onclick="return actualizar('${fdatavar.iexcodtra}_${fdatavar.iexcodcon}','${fdatavar.iexcodtra}','${requestScope.iexcodpro}','${requestScope.iexperiodo}','${fdatavar.iexcodcon}','${requestScope.iexcodreg}');" href="#"><span class="fa-solid fa-arrows-rotate me-2"></span>Actualizar</a>
                                              <div class="dropdown-divider"></div>
                                              <a id="dropdownmenutable" class="dropdown-item" onclick="return remove();" href="eliminarMigPlanConcepVariable@${requestScope.iexcodpro}@${requestScope.iexperiodo}@1@${fdatavar.iexcodtra}@${fdatavar.iexcodcon}@${requestScope.iexcodreg}"><span class="fa-solid fa-trash me-2"></span>Eliminar</a>
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