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
                <li class="breadcrumb-item active">Opciones</li>
              </ol>
            </nav>
            <div class="mb-9">
              <div class="row g-3 mb-4">
                <div class="col-auto">
                  <h2 id="h2top" class="mb-0">Editar Opcion</h2>
                </div>
              </div>

              <div class="row g-5">
                   <div class="col-xl-8">
                     <div class="row gx-3 gy-4">
                       <form class="row g-4 mb-0 needs-validation" method="POST" action="modificarOpc" novalidate >
                              <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                              <input class="form-control" name="iexcodopc" type="hidden" value="${idOpc}" />

                              <div class="col-sm-6 col-md-3">
                                   <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* ID</label>
                                   <input class="form-control" name="iexcodopc" type="number"  value="${requestScope.xOpcion.iexcodopc}"  required disabled readonly/>
                              </div>
                              <div class="col-sm-6 col-md-9">
                                 <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Opciones</label>
                                 <input class="form-control" name="iexdesopc" type="text"  value="${requestScope.xOpcion.iexdesopc}" required/>
                              </div>
                              <div class="col-sm-6 col-md-4">
                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Estado</label>
                                  <select class="form-select" name="iexflgest" required>
                                    <option value="1"   ${'1'== requestScope.xOpcion.iexflgest ? 'selected' : ''}  >Activo</option>
                                    <option value="2"   ${'2'== requestScope.xOpcion.iexflgest ? 'selected' : ''} >Inactivo</option>
                                  </select>
                              </div>
                              <div class="col-sm-6 col-md-8">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Seccion Sistemas</label>
                                    <select class="form-select" name="iexcodsec" required>
                                      <option value="" selected >Seleccionar</option>
                                      <c:forEach var="lovSeccion" items="${lovSeccion}">
                                          <option value="${lovSeccion.iexcodsec}" ${lovSeccion.iexcodsec== requestScope.xOpcion.iexcodsec ? 'selected' : ''} >${lovSeccion.iexdessec} - ${lovSeccion.dessys}</option>
                                      </c:forEach>
                                    </select>
                              </div>
                              <div class="col-sm-6 col-md-12">
                                     <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Url Opc</label>
                                     <input class="form-control" name="iexurlopc" type="text"   value="${requestScope.xOpcion.iexurlopc}" required/>
                              </div>
                              <div class="col-sm-6 col-md-12">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Url Img</label>
                                    <input class="form-control" name="iexurlimg" type="text" value="${requestScope.xOpcion.iexurlimg}" placeholder="" required/>
                              </div>
                              <div class="col-sm-6 col-md-6">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* NEW Action</label>
                                    <input class="form-control" name="iexactionspring" type="text" value="${requestScope.xOpcion.iexactionspring}" placeholder="" required/>
                              </div>
                              <div class="col-sm-6 col-md-6">
                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Action OLD</label>
                                  <input class="form-control" name="iexaction" type="text" value="${requestScope.xOpcion.iexaction}" placeholder="" />
                              </div>
                              <div class="col-sm-6 col-md-6">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Code Apps</label>
                                    <input class="form-control" name="iexcodapps" type="text" value="${requestScope.xOpcion.iexcodapps}" placeholder="" required/>
                              </div>
                              <div class="col-sm-6 col-md-12">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Descripcion</label>
                                    <input class="form-control" name="iexdescripcion" type="text" value="${requestScope.xOpcion.iexdescripcion}" required/>
                              </div>

                              <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                  Se grabó exitosamente los cambios.
                              </div>
                              <div class="col-12 gy-6">
                                  <div class="row g-3 justify-content-end">
                                    <div class="col-auto">
                                      <a class="btn btn-phoenix-primary px-5" href="listOpciones">Cancel</a>
                                    </div>
                                    <div class="col-auto">
                                      <button class="btn btn-primary px-5 px-sm-15" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar Opcion</button>
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
          <jsp:include page="../../../demoWidget.jsp"></jsp:include>
    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->
    <jsp:include page="../../../customize.jsp"></jsp:include>
  </body>
</html>