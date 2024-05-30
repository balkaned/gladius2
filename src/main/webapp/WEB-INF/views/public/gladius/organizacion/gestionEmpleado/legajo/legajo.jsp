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
    function abriModalAprobar(iexcodgrpfile,iexcodimage){

         $('#modalConfirmacionAprobar').modal('show');

         document.getElementById("iexcodgrpfile_modal").value = iexcodgrpfile;
         document.getElementById("iexcodimage_modal").value = iexcodimage;
    }

    function cerrar(){
        $('#modalConfirmacionAprobar').modal('hide');
    }

    function aprobar(){
        var iexcodgrpfile = $("#iexcodgrpfile_modal").val();
        var iexcodimage = $("#iexcodimage_modal").val();

        $.ajax({
             url: "aprobarDocumentoLegajo",
             data: {
                 "iexcodgrpfile": iexcodgrpfile,
                 "iexcodimage": iexcodimage
             },
             success: function (data) {
                $("#frmLegajos").submit();
             }
        });
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
                <div class="row mt-0 mb-1">
                    <div class="col-12">
                      <div class="row align-items-center justify-content-between">
                        <div class="col-12 col-md-auto">
                          <h2 class="mb-0"></h2>
                        </div>
                        <div class="col-12 col-md-auto">
                          <div class="d-flex">
                            <div class="flex-1 d-md-none">
                              <button class="btn px-3 btn-phoenix-secondary text-700 me-2" data-phoenix-toggle="offcanvas" data-phoenix-target="#productFilterColumn"><span class="fa-solid fa-bars"></span></button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                </div>

                <div class="row g-0 g-md-4 g-xl-6">
                  <jsp:include page="../navsGenericEmpl.jsp"></jsp:include>

                  <div class="col-md-7 col-lg-7 col-xl-8">
                    <div class="lead-details-container">
                      <div class="content2">
                          <div class="mb-9">
                            <div class="row g-3 mt-2 mb-2">
                              <div class="col-auto">
                                <h2 id="h2top" class="mb-0">Legajo</h2>
                              </div>
                            </div>

                            <div class="row g-3">
                                 <div class="col-xl-9">
                                   <div class="row gx-3 gy-4">
                                     <form id="frmLegajos" class="row g-3 mb-0 needs-validation" method="POST" action="buscarLegajo@${idTrab}" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />

                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Grupo de archivos</label>
                                                <select class="form-select" name="codgrpfile" required >
                                                  <option value="" selected >Seleccionar grupo archivo</option>
                                                  <c:forEach var="lovGrpFile" items="${lovGrpFile}">
                                                      <option value="${lovGrpFile.idLov}" ${lovGrpFile.idLov == requestScope.codgrpfile ? 'selected' : ''}  >  ${lovGrpFile.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>

                                            <div class="col-12 gy-6">
                                                <div class="row g-3 d-flex justify-content-end">
                                                  <div class="col-auto ps-0 pe-0">
                                                    <a class="btn btn-phoenix-secondary btn-sm" href="detalleEmpl@${idTrab}"><span class="fa-solid fa-reply me-2"></span>Atras</a>
                                                  </div>
                                                  <div class="col-auto ps-0 pe-0 ms-1">
                                                    <a class="btn btn-phoenix-secondary btn-sm" href="nuevoGrupo@${idTrab}" ><span class="fas fa-plus me-2"></span>Add grupo archivo</a>
                                                  </div>
                                                  <div class="col-auto ps-0 pe-0 ms-1">
                                                    <button class="btn btn-primary btn-sm" type="submit" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" ><span class="fa-solid fas fa-search me-2"></span>Buscar</button>
                                                  </div>
                                                </div>
                                            </div>
                                     </form>
                                   </div>
                                 </div>
                            </div>

                            <div class="col-12 mt-5">
                                 <c:forEach var="listGrpFile" items="${requestScope.listGrpFile}"   varStatus="loopCounter" >
                                     <c:if test="${listGrpFile.iexdesgrpfile != permes_cur }">
                                           <c:if test="${loopCounter.count ==0 }">
                                               <div class="table-responsive">
                                                  <table class="table table-striped"  >
                                                     <tr>
                                                         <td>Id</td>
                                                         <td>FileName</td>
                                                         <td>Estado</td>
                                                         <td>Aprobar </td>
                                                         <td>Eliminar</td>
                                                         <td>Fecha Carga</td>
                                                     </tr>
                                           </c:if>
                                           <c:if test="${loopCounter.count >1 }">
                                                  </table>
                                               </div>
                                               <br>
                                           </c:if>

                                           <label class="form-label fs-0 text-1000 ps-0 text-none mt-4 mb-0">${listGrpFile.desgrangrupo} -  ${listGrpFile.iexdesgrpfile}</label>
                                           <div>
                                               <a class="btn btn-phoenix-secondary btn-sm mb-1" href="ingresarImagen@${idTrab}@${listGrpFile.iexcodgrpfile}@${codgrpfile}"><span class="fa-solid fas fa-plus me-2"></span>Add file</a>
                                               <a class="btn btn-phoenix-secondary btn-sm mb-1" href="editarGrupoArch@${idTrab}@${listGrpFile.iexcodgrpfile}@${codgrpfile}" ><span class="fas fa-pen me-2"></span>Editar grupo archivo</a>
                                               <a class="btn btn-phoenix-danger btn-sm mb-1" onclick="return remove();" href="delGrupoArch@${idTrab}@${listGrpFile.iexcodgrpfile}@${codgrpfile}">Borrar grupo</a>
                                           </div>

                                           <div class="border-top border-bottom border-200 mt-2" id="customerOrdersTable" data-list='{"valueNames":["order","total","payment_status","fulfilment_status","delivery_type","date"],"page":6,"pagination":true}'>
                                              <div class="table-responsive scrollbar">
                                           		<table class="table table-sm fs--1 mb-0">
                                           		  <thead>
                                           			<tr>
                                           			  <th class="sort white-space-nowrap align-middle ps-0 pe-3 text-uppercase" scope="col" data-sort="order" >ID</th>
                                           			  <th class="sort align-middle text-center ps-3 pe-3 text-uppercase" scope="col" data-sort="total">Descripción</th>
                                           			  <th class="sort align-middle text-center white-space-nowrap ps-1 pe-1 text-uppercase" scope="col" data-sort="payment_status" >Estado</th>
                                           			  <th class="sort align-middle text-center ps-5 pe-5 text-uppercase" scope="col" data-sort="total">FileName</th>
                                           			  <th class="sort align-middle text-center white-space-nowrap ps-3 pe-3 text-uppercase" scope="col" data-sort="payment_status" >Aprobación</th>
                                           			  <th class="sort align-middle text-center ps-0 pe-0 text-uppercase" scope="col" ></th>
                                           			</tr>
                                           		  </thead>
                                     </c:if>
                                                   <tbody class="list" id="customer-order-table-body">
                                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                                          <td class="align-middle white-space-nowrap ps-0 pe-3"><a class="fw-semi-bold" href="#!">#${listGrpFile.iexcodimage}</a></td>
                                                          <td class="align-middle white-space-nowrap text-start text-1000 ps-0 pe-0">${listGrpFile.iexdesimage}</td>
                                                          <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Activo</span></td>
                                                          <td class="align-middle white-space-nowrap text-center cursiva ps-0 pe-3">${listGrpFile.iexurlimage}</td>
                                                          <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">
                                                            <c:if test="${listGrpFile.iexestado_det == null}"><span class="badge badge-phoenix fs--2 badge-phoenix-secondary"><span class="badge-label">Pendiente</span></c:if>
                                                            <c:if test="${listGrpFile.iexestado_det == '3'}"><span class="badge badge-phoenix fs--2 badge-phoenix-primary"><span class="badge-label">Aprobado</span></c:if>
                                                          </td>

                                                          <td class="align-middle white-space-nowrap text-end pe-0 ps-0">
                                                            <div class="font-sans-serif btn-reveal-trigger position-static">
                                                              <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                                              data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                                              <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                                              <div id="dropdownmenutable" class="dropdown-menu dropdown-menu-end py-2">

                                                                <c:if test="${listGrpFile.iexestado_det != '3'}">
                                                                    <a class="dropdown-item" onclick="abriModalAprobar('${listGrpFile.iexcodgrpfile}','${listGrpFile.iexcodimage}');" href="#" ><span class="fas fa-bolt me-2"></span>Aprobar</a>
                                                                </c:if>

                                                                <a id="dropdownmenutable" class="dropdown-item"
                                                                href="AWSorFTP_flgsource@decargarDocumento@${idComp}@${idTrab}@null@null@null@null@legajo@${listGrpFile.iexcodgrpfile}@${listGrpFile.iexurlimage}"
                                                                ><span class="fas fa-download me-2"></span>Descargar</a>

                                                                <c:if test="${listGrpFile.iexestado_det != '3'}">
                                                                        <div class="dropdown-divider"></div>

                                                                        <a id="dropdownmenutable" class="dropdown-item" onclick="return remove();"
                                                                        href="AWSorFTP_flgsource@eliminarDocumento@${idComp}@${idTrab}@null@null@null@null@legajo@${listGrpFile.iexcodgrpfile}@${listGrpFile.iexcodimage}-${listGrpFile.iexgrpfile}"
                                                                        ><span class="fas fa-link-slash me-2"></span>Eliminar</a>
                                                                </c:if>
                                                              </div>
                                                            </div>
                                                          </td>
                                                        </tr>
                                     <c:set var="permes_cur" value="${listGrpFile.iexdesgrpfile}" />
                                 </c:forEach>
                                                   </tbody>
                                                 </table>
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

  <div class="modal fade" id="modalConfirmacionAprobar" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
  	<div class="modal-content border bg-100">
  	  <form id="addEventForm" autocomplete="off">
  		<div class="modal-header border-200 p-4">
  		  <h5 class="modal-title text-1000 fs-4 lh-sm">Aprobación</h5>
  		  <button class="btn p-1 text-900" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs--1"></span></button>
  		</div>
  		<div class="modal-body pt-4 pb-2 px-4">
  		  <div class="mb-3">
  			<label class="fw-bold mb-2 text-1000" for="leadStatus">Esta seguro que desea aprobar el documento?, si aprueba el documento, ya no se podrá eliminar, solo un usuario con Rol 'ADMIN' podrá eliminarlo.</label>
  		    <input id="iexcodgrpfile_modal" type="hidden" value="" />
  		    <input id="iexcodimage_modal" type="hidden" value="" />
  		  </div>
  		</div>
  	  </form>
  	  <div class="modal-footer d-flex justify-content-end align-items-center px-0 pb-0 border-200 pt-0">
  		  <button class="btn btn-sm btn-phoenix-secondary px-4 my-0 mt-1" onclick="cerrar();" type="button" data-bs-dismiss="modal" >Cancel</button>
  		  <button class="btn btn-sm btn-secondary px-9 my-0 mt-1" onclick="aprobar();" type="button" data-bs-dismiss="modal" ><span class="fas fa-bolt me-2 fs--1"></span>Aprobar</button>
  	  </div>
  	</div>
    </div>
  </div>
</html>