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
      function mostrarAlert(){
          //alert("se grabo exitosamente");
          var div=document.getElementById('alert');
          div.style.display = '';

          setTimeout(function() {
              $("#alerts").hide(6000);
          }, 3000);
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
                <li class="breadcrumb-item"><a href="#!">Configuración</a></li>
                <li class="breadcrumb-item active">Compañía</li>
              </ol>
            </nav>
            <div class="mb-9">
              <div class="row g-3 mb-4">
                <div class="col-auto">
                  <h2 id="h2top" class="mb-0">Editar Compañia</h2>
                </div>
              </div>

              <div class="row g-5">
                   <div class="col-xl-8">
                     <div class="row gx-3 gy-4">
                       <div class="form-group row mt-4">
                            <div class="col-md-9 col-sm-6 ">
                                 <!--<img id="imgcompanyTbl" src="verFoto@LOGO@${idCia}@${requestScope.xCia.urlLogo}@null" class="avatar" alt="Avatar" width="100" height="100" >-->

                                 <c:if test="${requestScope.xCia.urlLogo==null}"><img class="rounded-circle img-thumbnail bg-white shadow-sm mb-4" src="resources/assets/img/user_blank.jpg" width="100" height="100" /></c:if>

                                 <c:if test="${requestScope.xCia.urlLogo!=null}"><img class="rounded-circle img-thumbnail bg-white shadow-sm mb-4"
                                 src="AWSorFTP_flgsource@verLogo@${idCia}@null@${requestScope.xCia.urlLogo}@null@null@null@null@nul@null"
                                 width="100" height="100" /></c:if>

                                 <form method="post"
                                 action="AWSorFTP_flgsource_MultipartUpload@subirLogoCompania@${idCia}@null@null"
                                 enctype="multipart/form-data">
                                       <input type="hidden"  name="idimg" value="${idCia}" >
                                       <input type="file" name="uploadFile" class="form-control"/>

                                       <button class="btn btn-primary mt-3" type="submit"><span class="fas fa-cloud-upload-alt me-2"></span>Upload</button>
                                 </form>
                            </div>
                       </div>
                       <form class="row g-4 mb-0 needs-validation" method="POST" action="modificarCompania" novalidate >
                              <input class="form-control" name="iexcodcia2" type="hidden" value="${idCia}" />

                              <div class="col-sm-6 col-md-3">
                                   <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* ID</label>
                                   <input class="form-control" name="iexcodcia" type="number" value="${idCia}" required disabled required/>
                              </div>
                              <div class="col-sm-6 col-md-9">
                                 <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Descripcion CIA</label>
                                 <input class="form-control" name="iexdescia" type="text" value="${requestScope.xCia.descCia}" required/>
                              </div>
                              <div class="col-sm-6 col-md-8">
                                   <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Descripcion Corta CIA</label>
                                   <input class="form-control" name="iexdescorto" type="text" value="${requestScope.xCia.descCiaCorto}" required/>
                              </div>
                              <div class="col-sm-6 col-md-4">
                                 <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* RUC</label>
                                 <input class="form-control" name="iexnroruc" type="text" maxlength="17" value="${requestScope.xCia.nroRuc}" required/>
                              </div>
                              <div class="col-sm-6 col-md-12">
                                   <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Direccion</label>
                                   <input class="form-control" name="iexdireccion" type="text" value="${requestScope.xCia.direccionCia}" required/>
                              </div>
                              <div class="col-sm-6 col-md-4">
                                     <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Telefono</label>
                                     <input class="form-control" name="iexnrotelf" type="text" value="${requestScope.xCia.nroTelfCia}" required/>
                              </div>
                              <div class="col-sm-6 col-md-8">
                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Actividad Comercial</label>
                                  <select class="form-select" name="iexcodact" required>
                                    <option value="" selected >Seleccionar</option>
                                    <c:forEach var="lovTipAct" items="${lovTipAct}">
                                        <option value="${lovTipAct.idLov}" ${lovTipAct.idLov== requestScope.xCia.idActividadCia ? 'selected' : ''} >${lovTipAct.desLov}</option>
                                    </c:forEach>
                                  </select>
                              </div>
                              <div class="col-sm-6 col-md-6">
                                     <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Nombre del Representante</label>
                                     <input class="form-control" name="iexrepnombre" type="text"  value="${requestScope.xCia.nomRepesentante}" required/>
                              </div>
                              <div class="col-sm-6 col-md-6">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Cargo del Representante</label>
                                    <input class="form-control" name="iexrepcargo" type="text" value="${requestScope.xCia.desCargoRep}" required/>
                              </div>
                              <div class="col-sm-6 col-md-12">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Nro de Documento Identidad del Rep</label>
                                    <input class="form-control" name="iexrepdocid" maxlength="17" type="text" value="${requestScope.xCia.nroDocuRep}" required/>
                              </div>
                              <div class="col-sm-6 col-md-12">
                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Logo</label>
                                  <input class="form-control" name="iexreplogo" type="text" value="${requestScope.xCia.urlLogo}" placeholder="ID.jpg" required/>
                              </div>

                              <div class="col-sm-6 col-md-12">
                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Url File Report</label>
                                  <input class="form-control" name="iexurlfilereport" type="text" value="${requestScope.xCia.iexurlfilereport}" />
                              </div>
                              <div class="col-sm-6 col-md-5">
                              	  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Flag Source</label>
                              	  <select name="iexflgsource" class="form-select" required >
                              		  <option value="1" ${1 == requestScope.xCia.iexflgsource ? 'selected' : ''} >1: AWS S3</option>
                              		  <option value="2" ${2 == requestScope.xCia.iexflgsource ? 'selected' : ''} >2: FTP</option>
                              	  </select>
                              </div>
                              <hr>
                              <div class="col-sm-6 col-md-8">
                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">FTP_server</label>
                                  <input class="form-control" name="iexurlfileserver" type="text" value="${requestScope.xCia.iexurlfileserver}" placeholder="ftp.balkaned.com" />
                              </div>
                              <div class="col-sm-6 col-md-4">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">FTP_port</label>
                                    <input class="form-control" name="iexportsource" type="number" value="${requestScope.xCia.iexportsource}" placeholder="21"/>
                              </div>
                              <div class="col-sm-6 col-md-5">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">AWS_key_name or FTP_user</label>
                                    <input class="form-control" name="iexususource" type="text" value="${requestScope.xCia.iexususource}" />
                              </div>
                              <div class="col-sm-6 col-md-7">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">AWS_passPhrase or FTP_pass</label>
                                    <input class="form-control" name="iexpasssource" type="text" value="${requestScope.xCia.iexpasssource}" />
                              </div>
                              <div class="col-sm-6 col-md-6">
                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">AWS_bucket_name</label>
                                  <input class="form-control" name="iexsourcedes" type="text" value="${requestScope.xCia.iexsourcedes}" placeholder="gladiustest" />
                              </div>
                              <div class="col-sm-6 col-md-6">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">AWS_clientRegion</label>
                                    <input class="form-control" name="iexregiondes" type="text" value="${requestScope.xCia.iexregiondes}" placeholder="US_EAST_2" />
                              </div>

                              <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                  Se grabó exitosamente los cambios.
                              </div>
                              <div class="col-12 gy-6">
                                  <div class="row g-3 justify-content-end">
                                    <div class="col-auto">
                                      <a class="btn btn-phoenix-primary px-5" href="listCompanias">Cancel</a>
                                    </div>
                                    <div class="col-auto">
                                      <button class="btn btn-primary px-5 px-sm-15" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar Compania</button>
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

                       <form class="form-horizontal form-label-left"  id="formconcept"  method="POST" action="insertarConceptoComp" name="ciafijvar"  id="ciafijvar"  >
                           <input type="hidden"  name="idcia"  id="idcia"  value="${requestScope.xCia.idCodcia}"  readonly>
                           <input type="hidden"  name="accion" value="INSCONS" >

                           <div class="col-sm-6 col-md-7">
                               <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Concepto</label>
                               <select name="id_concepto" id="id_concepto"  class="form-select">
                                   <option value="">Selecciona Concepto</option>
                                   <c:forEach  var="lovConcepto" items="${lovConcepto}">
                                       <option value="${lovConcepto.codConcepto}" >${lovConcepto.codConcepto} - ${lovConcepto.desConcepto}</option>
                                    </c:forEach>
                               </select>
                           </div>
                           <div class="col-sm-6 col-md-7 mt-3">
                               <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de Registro</label>
                               <select name="tipo_reg" id="tipo_reg" class="form-select">
                                   <option value="">Seleccionar</option>
                                   <option value="1">Datos Dijos</option>
                                   <option value="2">Datos Variable</option>
                               </select>
                           </div>
                           <div class="ln_solid"></div>
                           <div class="form-group">
                                <div class="col-md-6 col-sm-6 mt-3">
                                     <button class="btn btn-primary" type="submit"><span class="fas fa-plus me-2"></span>Añadir</button>
                                </div>
                           </div>
                       </form>
                       </br>
                       <div class="form-group row mt-3">
                           <label class="control-label col-md-3 col-sm-3 "></label>
                           <div class="col-md-6 col-sm-6 ">
                               <table width="750">
                                   <tr>
                                       <td class="form-label fs-0 text-1000 ps-0 text-none mb-2">Conceptos Fijos</td>
                                       <td class="form-label fs-0 text-1000 ps-0 text-none mb-2">Conceptos Variables</td>
                                   </tr>
                                   <tr>
                                       <td>
                                           <div class="mt-3 border-top border-bottom border-200" id="customerOrdersTable" data-list='{"valueNames":["order","total","payment_status","fulfilment_status","delivery_type","date"],"page":6,"pagination":true}'>
                                               <div class="table-responsive scrollbar">
                                                    <table class="table table-sm fs--1 mb-0">
                                                      <tbody class="list" id="customer-order-table-body">
                                                        <c:forEach var="xCiaFij" items="${xCiaFij}">
                                                            <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                                              <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">${xCiaFij.iexcodcon}</td>
                                                              <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3"><span class="badge badge-phoenix fs--2 badge-phoenix-warning"><span class="badge-label">${xCiaFij.iexdescon}</span></td>
                                                              <td><a href="delConceptoComp@${idCia}@${xCiaFij.iexcodcon}">x</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                      </tbody>
                                                    </table>
                                               </div>
                                           </div>
                                       </td>
                                       <td class="ps-3">
                                           <div class="mt-3 border-top border-bottom border-200" id="customerOrdersTable" data-list='{"valueNames":["order","total","payment_status","fulfilment_status","delivery_type","date"],"page":6,"pagination":true}'>
                                              <div class="table-responsive scrollbar">
                                                   <table class="table table-sm fs--1 mb-0">
                                                     <tbody class="list" id="customer-order-table-body">
                                                       <c:forEach var="xCiaVar" items="${xCiaVar}">
                                                           <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                                             <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">${xCiaVar.iexcodcon}</td>
                                                             <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3"><span class="badge badge-phoenix fs--2 badge-phoenix-info"><span class="badge-label">${xCiaVar.iexdescon}</span></td>
                                                             <td><a href="delConceptoComp@${idCia}@${xCiaVar.iexcodcon}">x</a></td>
                                                           </tr>
                                                       </c:forEach>
                                                     </tbody>
                                                   </table>
                                              </div>
                                           </div>
                                       </td>
                                   </tr>
                               </table>
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