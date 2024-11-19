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
      function mostrarAlert(){
          var div=document.getElementById('alert');
          div.style.display = '';

          setTimeout(function() {
              $("#alerts").hide(6000);
          }, 3000);
      }

      function subirimagen(){
          var uploadFile = $("#uploadFile").val();

          var parts=uploadFile.split(".");
          var part1=parts[0];
          var part2=parts[1];

          if(part2=="jpg" || part2=="png"){
            $('#modalLoading').modal('show');
            $("#formComp").submit();
          }else{
            alert("Solo se pueden subir imágenes en formato .jpg o .png");

            return;
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

          <div class="content bg-100">
            <nav class="mb-2" aria-label="breadcrumb">
              <ol class="breadcrumb mb-0">
                <li class="breadcrumb-item"><a href="#!">Configuración</a></li>
                <li class="breadcrumb-item active">Compañía</li>
              </ol>
            </nav>
            <div class="mb-9">
              <div class="row g-3 mb-4">
                <div class="col-auto">
                  <h2 id="h2top" class="mb-0">Editar compañía</h2>
                </div>
              </div>

              <div class="row g-3">
                   <div class="col-xl-8">
                     <div class="row gx-3 gy-4">
                       <div class="form-group row mt-4">
                            <div class="col-md-9 col-sm-6 ">
                                 <!--<img id="imgcompanyTbl" src="verFoto@LOGO@${idCia}@${requestScope.xCia.urlLogo}@null" class="avatar" alt="Avatar" width="100" height="100" >-->

                                 <c:if test="${requestScope.xCia.urlLogo==null}"><img id="imgcompanyedit" class="rounded-circle img-thumbnail bg-white shadow-sm mb-4" src="resources/assets/img/user_blank.jpg"/></c:if>

                                 <c:if test="${requestScope.xCia.urlLogo!=null}"><img id="imgcompanyedit" class="rounded-circle img-thumbnail bg-white shadow-sm mb-4"
                                 src="AWSorFTP_flgsource@verLogo@${idCia}@null@${requestScope.xCia.urlLogo}@null@null@null@null@nul@null"
                                 /></c:if>

                                 <form id="formComp" method="post" action="AWSorFTP_flgsource_MultipartUpload@subirLogoCompania@${idCia}@null@null" enctype="multipart/form-data">
                                       <input type="hidden"  name="idimg" value="${idCia}" >
                                       <p class="fs--1 mb-0 ms-1 text-600" style="font-style:italic;">Solo en formato .jpg y .png, se sugiere utilizar una imagen de 400 x 400 pixeles</p>
                                       <input type="file" id="uploadFile" name="uploadFile" class="form-control" />

                                       <a class="btn btn-phoenix-secondary btn-sm mt-3" href="#" onclick="subirimagen();" ><span class="fas fa-cloud-upload-alt me-2"></span>Upload</a>
                                 </form>
                            </div>
                       </div>
                       <form class="row g-3 mb-0 needs-validation" method="POST" action="modificarCompania" novalidate >
                              <input class="form-control" name="iexcodcia2" type="hidden" value="${idCia}" />

                              <div class="col-12">
                                <span class="col-sm-6 col-md-4 badge badge-tag me-2 mb-2">Datos principales</span>
                              </div>
                              <div class="col-sm-6 col-md-2">
                                   <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Id</label>
                                   <input class="form-control" name="iexcodcia" type="number" value="${idCia}" required disabled required/>
                              </div>
                              <div class="col-sm-6 col-md-9">
                                 <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Descripción cia</label>
                                 <input class="form-control" name="iexdescia" type="text" value="${requestScope.xCia.descCia}" required/>
                              </div>
                              <div class="col-sm-6 col-md-8">
                                   <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Descripción corta cia</label>
                                   <input class="form-control" name="iexdescorto" type="text" value="${requestScope.xCia.descCiaCorto}" required/>
                              </div>
                              <div class="col-sm-6 col-md-4">
                                 <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Ruc</label>
                                 <input class="form-control" name="iexnroruc" type="text" maxlength="17" value="${requestScope.xCia.nroRuc}" required/>
                              </div>
                              <div class="col-sm-6 col-md-12">
                                   <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Dirección</label>
                                   <input class="form-control" name="iexdireccion" type="text" value="${requestScope.xCia.direccionCia}" required/>
                              </div>
                              <div class="col-sm-6 col-md-4">
                                     <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Teléfono</label>
                                     <input class="form-control" name="iexnrotelf" type="text" value="${requestScope.xCia.nroTelfCia}" required/>
                              </div>
                              <div class="col-sm-6 col-md-6">
                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Actividad comercial</label>
                                  <select class="form-select" name="iexcodact" data-choices="data-choices" data-options='{"removeItemButton":true,"placeholder":true}' required>
                                    <option value="" selected >Seleccionar</option>
                                    <c:forEach var="lovTipAct" items="${lovTipAct}">
                                        <option value="${lovTipAct.idLov}" ${lovTipAct.idLov== requestScope.xCia.idActividadCia ? 'selected' : ''} >${lovTipAct.desLov}</option>
                                    </c:forEach>
                                  </select>
                              </div>
                              <div class="col-sm-6 col-md-6">
                                     <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nombre del representante</label>
                                     <input class="form-control" name="iexrepnombre" type="text"  value="${requestScope.xCia.nomRepresentante}" required/>
                              </div>
                              <div class="col-sm-6 col-md-6">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Cargo del representante</label>
                                    <input class="form-control" name="iexrepcargo" type="text" value="${requestScope.xCia.desCargoRep}" required/>
                              </div>
                              <div class="col-sm-6 col-md-5">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de doc identidad del rep</label>
                                    <input class="form-control" name="iexrepdocid" maxlength="17" type="text" value="${requestScope.xCia.nroDocuRep}" required/>
                              </div>
                              <div class="col-sm-6 col-md-7">
                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Logo</label>
                                  <input class="form-control" name="iexreplogo" type="text" value="${requestScope.xCia.urlLogo}" placeholder="ID.jpg" required/>
                              </div>
                              <div class="col-sm-6 col-md-7">
                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Url file report</label>
                                  <input class="form-control" name="iexurlfilereport" type="text" value="${requestScope.xCia.iexurlfilereport}" />
                              </div>

                              <div class="col-12 mt-7">
                                  <span class="col-sm-6 col-md-4 badge badge-tag me-2 mb-2">Datos de conexión</span>
                              </div>
                              <div class="col-sm-6 col-md-5">
                              	  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo conexión flag source</label>
                              	  <select name="iexflgsource" class="form-select" required >
                              		  <option value="1" ${1 == requestScope.xCia.urlflgsource ? 'selected' : ''} >1: AWS S3</option>
                              		  <option value="2" ${2 == requestScope.xCia.urlflgsource ? 'selected' : ''} >2: FTP</option>
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

                              <div id="alert" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                              	<span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                              	<p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                              	<a class="text-success fs-0 fw-bold" href="#" data-bs-dismiss="alert" aria-label="Close">x</a>
                              </div>
                              <div class="col-12 gy-6">
                                  <div class="row g-3 justify-content-end">
                                    <div class="col-auto">
                                      <a class="btn btn-phoenix-primary px-5" href="listCompanias">Cancel</a>
                                    </div>
                                    <div class="col-auto">
                                      <button class="btn btn-primary px-5 px-sm-15" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar compania</button>
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

                       <div class="col-12 mt-7">
                           <span class="col-sm-6 col-md-4 badge badge-tag me-2 mb-2">Conceptos habilitados</span>
                       </div>
                       <form class="form-horizontal form-label-left needs-validation"  id="formconcept"  method="POST" action="insertarConceptoComp" name="ciafijvar"  id="ciafijvar"  novalidate>
                           <input type="hidden"  name="idcia"  id="idcia"  value="${requestScope.xCia.idCodcia}"  readonly>
                           <input type="hidden"  name="accion" value="INSCONS" >

                           <div class="col-sm-6 col-md-7">
                               <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Concepto</label>
                               <select name="id_concepto" id="id_concepto"  class="form-select" data-choices="data-choices" data-options='{"removeItemButton":true,"placeholder":true}' required>
                                   <option value="">Seleccionar concepto</option>
                                   <c:forEach  var="lovConcepto" items="${lovConcepto}">
                                       <option value="${lovConcepto.codConcepto}" >${lovConcepto.codConcepto} - ${lovConcepto.desConcepto}</option>
                                    </c:forEach>
                               </select>
                           </div>
                           <div class="col-sm-6 col-md-4 mt-3">
                               <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de registro</label>
                               <select name="tipo_reg" id="tipo_reg" class="form-select " required>
                                   <option value="">Tipo de registro</option>
                                   <option value="1">Datos Dijos</option>
                                   <option value="2">Datos Variable</option>
                               </select>
                           </div>
                           <div class="ln_solid"></div>
                           <div class="form-group">
                                <div class="col-md-6 col-sm-6 mt-3">
                                     <button class="btn btn-phoenix-secondary btn-sm" type="submit"><span class="fas fa-plus me-2"></span>Add concepto</button>
                                </div>
                           </div>
                       </form>
                       </br>
                       <div class="col-sm-6 col-md-6">
                           <div>
                               <div>
                                   <div>
                                       <td>
                                           <div class="" id="customerOrdersTable" data-list='{"valueNames":["order","total","payment_status","fulfilment_status","delivery_type","date"],"page":6,"pagination":true}'>
                                               <div class="table-responsive scrollbar">
                                                    <table class="table table-sm fs--1">
                                                      <div class="fs--1 fw-bold">Conceptos fijos</div>
                                                      <tbody class="list" id="customer-order-table-body">
                                                        <c:forEach var="xCiaFij" items="${xCiaFij}">
                                                            <tr class="border border-300 rounded-2 hover-actions-trigger btn-reveal-trigger position-static">
                                                              <td class="align-middle white-space-nowrap text-center text-700 "><span class="badge badge-tag ">${xCiaFij.iexcodcon}</span></td>
                                                              <td class="align-middle white-space-nowrap text-start text-700 "><span class="badge badge-phoenix fs--2 badge-phoenix-primary"><span class="badge-label">${xCiaFij.iexdescon}</span></td>
                                                              <td><a class="pe-2" href="delConceptoComp@${idCia}@${xCiaFij.iexcodcon}">x</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                      </tbody>
                                                    </table>
                                               </div>
                                           </div>
                                       </td>
                                       <td class="">
                                           <div class="" id="customerOrdersTable" data-list='{"valueNames":["order","total","payment_status","fulfilment_status","delivery_type","date"],"page":6,"pagination":true}'>
                                              <div class="table-responsive scrollbar">
                                                   <table class="table table-sm fs--1">
                                                     <div class="fs--1 fw-bold mt-4">Conceptos variables</div>
                                                     <tbody class="list" id="customer-order-table-body">
                                                       <c:forEach var="xCiaVar" items="${xCiaVar}">
                                                           <tr class="border border-300 rounded-2 hover-actions-trigger btn-reveal-trigger position-static">
                                                             <td class="align-middle white-space-nowrap text-center text-700"><span class="badge badge-tag">${xCiaVar.iexcodcon}</span></td>
                                                             <td class="align-middle white-space-nowrap text-start text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span class="badge-label">${xCiaVar.iexdescon}</span></td>
                                                             <td><a class="pe-2" href="delConceptoComp@${idCia}@${xCiaVar.iexcodcon}">x</a></td>
                                                           </tr>
                                                       </c:forEach>
                                                     </tbody>
                                                   </table>
                                              </div>
                                           </div>
                                       </td>
                                   </div>
                               </div>
                           </div>
                       </div>
                     </div>
                   </div>
              </div>
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

  <div id="modalLoading" class="modal fade" tabindex="-1" data-bs-backdrop="static" aria-labelledby="scrollingLongModalLabel2" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered">
  	  <div class="modal-content bg-100 rounded-2 border border-300">
  		<form class="needs-validation" method="POST" action="" novalidate >
  			<div class="modal-header border-bottom border-300 bg-300 bg-opacity-25 p-4">
  			   <h5 id="h5modalLoadinglabel" class="modal-title text-1000 fs-2 lh-sm">Subiendo imagen a la nube</h5>
  			   <!--<button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>-->
  			</div>
  			<div class="modal-body p-4 bg-300 bg-opacity-50 pt-3 pb-0">
  			  <div class="mt-0 mb-0">
  				  <p class="fs--1">Se esta procesando la transacción y actualizando espere unos minutos hasta que haya finalizado la tarea...</p>
  				  <div class="col-12 text-center">
  					  <div id="iconspinner" class="spinner-border text-primary" role="status">
  						<span class="visually-hidden">Loading...</span>
  					  </div>
  				  </div>
  			  </div>
  			</div>
  			<div class="modal-footer bg-300 bg-opacity-25 d-flex justify-content-end align-items-center px-0 pb-0 border-top border-300 pt-0">
  				<button id="btnFooter" class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" href="#"><div class="spinner-border spinner-border-sm" style="height:13px; width:13px;" role="status"></div><span id="spanbtnModalLoading" class="ms-2">Subiendo imagen</span></button>
  			</div>
  		</form>
  	  </div>
    </div>
  </div>
</html>