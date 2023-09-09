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
                                <h2 id="h2top" class="mb-0">Legajo</h2>
                              </div>
                            </div>

                            <div class="row g-5">
                                 <div class="col-xl-8">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-4 mb-0 needs-validation" method="POST" action="buscarLegajo@${idTrab}" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                                            <div class="col-sm-6 col-md-12">
                                              <div class="form-floating">
                                                <select class="form-select" name="codgrpfile" required >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovGrpFile" items="${lovGrpFile}">
                                                      <option value="${lovGrpFile.idLov}"   ${lovGrpFile.idLov == requestScope.codgrpfile ? 'selected' : ''}  >  ${lovGrpFile.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                                <label>Grupo de Archivos(*)</label>
                                              </div>
                                            </div>

                                            <!--<div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                                Se grabó exitosamente los cambios.
                                            </div>-->
                                            <div class="col-12 gy-6">
                                                <div class="row g-3 justify-content-end">
                                                  <div class="col-auto">
                                                    <a class="btn btn-phoenix-primary" href="detalleEmpl@${idTrab}">Cancel</a>
                                                    <a class="btn btn-phoenix-secondary me-1 mb-1" href="nuevoGrupo@${idTrab}" >Agregar Grupo</a>
                                                    <button class="btn btn-primary" type="submit" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" ><span class="fa-solid fas fa-search me-2"></span>Buscar</button>
                                                  </div>
                                                </div>
                                            </div>
                                            <!--<div class="modal fade" id="confirmModal" tabindex="-1">
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
                                            </div>-->
                                     </form>
                                   </div>
                                 </div>
                            </div>

                            <div class="col-12 mt-5">
                                 <c:forEach var="listGrpFile" items="${requestScope.listGrpFile}"   varStatus="loopCounter" >
                                      <c:if test="${listGrpFile.iexdesgrpfile != permes_cur }">
                                           <c:if test="${loopCounter.count ==0 }">
                                               <div class="table-responsive">
                                                  <table class="table table-striped jambo_table bulk_action"  >
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

                                           <h5 id="h2top" class="mb-0 mt-5">${listGrpFile.desgrangrupo} -  ${listGrpFile.iexdesgrpfile}</h5>
                                           <div class="col-12 gy-6 mt-2">
                                               <div class="row g-3 justify-content-end">
                                                 <div class="col-auto">
                                                   <a class="btn btn-phoenix-primary" href="eliminarLegajo@${idTrab}">Eliminar</a>
                                                   <a class="btn btn-phoenix-secondary me-1 mb-1" href="actualizarLegajo@${idTrab}" >Actualizar</a>
                                                   <a class="btn btn-primary" href="ingresarImagen@${idTrab}@${listGrpFile.iexcodgrpfile}"><span class="fa-solid fas fa-cloud-upload-alt me-2"></span>Ingresar Imagen</a>
                                                 </div>
                                               </div>
                                           </div>
                                           <div class="border-top border-bottom border-200 mt-2" id="customerOrdersTable" data-list='{"valueNames":["order","total","payment_status","fulfilment_status","delivery_type","date"],"page":6,"pagination":true}'>
                                           	  <div class="table-responsive scrollbar">
                                           		<table class="table table-sm fs--1 mb-0">
                                           		  <thead>
                                           			<tr>
                                           			  <th class="sort white-space-nowrap align-middle ps-0 pe-3 text-uppercase" scope="col" data-sort="order" >ID</th>
                                           			  <th class="sort align-middle text-center ps-5 pe-5 text-uppercase" scope="col" data-sort="total">FileName</th>
                                           			  <th class="sort align-middle text-center white-space-nowrap text-start ps-3 pe-3 text-uppercase" scope="col" data-sort="fulfilment_status" >Aprobar</th>
                                           			  <th class="sort align-middle text-center white-space-nowrap ps-3 pe-3 text-uppercase" scope="col" data-sort="payment_status" >Estado</th>
                                           			  <th class="sort align-middle text-center ps-3 pe-3 text-uppercase" scope="col" ></th>
                                           			</tr>
                                           		  </thead>
                                      </c:if>
                                                   <tbody class="list" id="customer-order-table-body">
                                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                                          <td class="align-middle white-space-nowrap ps-3 pe-3"><a class="fw-semi-bold" href="#!">#${listGrpFile.iexcodimage}</a></td>
                                                          <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">${listGrpFile.iexurlimage}</td>
                                                          <td class="align-middle text-center fw-semi-bold ps-3 pe-3 text-1000"><span class="badge badge-phoenix fs--2 badge-phoenix-info"><span class="badge-label">Aprobar</span></td>
                                                          <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Activo</span></td>
                                                          <td class="align-middle white-space-nowrap text-end pe-0 ps-5">
                                                            <div class="font-sans-serif btn-reveal-trigger position-static">
                                                              <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                                              <div class="dropdown-menu dropdown-menu-end py-2">
                                                                <a class="dropdown-item" href="#">Descargar PDF</a>
                                                                <div class="dropdown-divider"></div>
                                                                <a class="dropdown-item text-warning" href="#!">Remove</a>
                                                              </div>
                                                            </div>
                                                          </td>
                                                        </tr>
                                                   </tbody>
                                 </c:forEach>
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