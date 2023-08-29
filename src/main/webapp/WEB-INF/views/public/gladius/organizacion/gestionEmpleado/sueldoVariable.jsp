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
             "iexcodreg": $("#iexcodreg").val()},
         success: function (data) {
             var opt = "";
                  opt += "<option value=0 > -- Selecciona -- </option>";
                  for (var i in data) {
                   opt += "<option value="+data[i].idProceso+" > "+data[i].desProceso+" </option> ";
                  }

             $("#iexcodpro").html(opt);
         }
       });
  }

  function procesoplanilla(){
         $.ajax({
          url: "getlovsPERX",
          data: {"accion": "PERX",
              "iexcodpro": $("#iexcodpro").val()},
          success: function (data) {
              var opt = "";
                    opt += "<option value=0 > -- Selecciona -- </option>";
                   for (var i in data) {
                    opt += "<option value="+data[i].iexnroper+" > "+data[i].iexnroper+" </option> ";
                   }

              $("#iexperiodo").html(opt);
          }
      });
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
              <div class="pb-9">
                <div class="row">
                  <div class="col-12">
                    <div class="row align-items-center justify-content-between g-3 mb-3">
                    </div>
                  </div>
                </div>
                <div class="row g-0 g-md-4 g-xl-6">

                  <jsp:include page="navsGenericEmpl.jsp"></jsp:include>

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
                                <h2 id="h2top" class="mb-0">Conceptos Variables</h2>
                              </div>
                            </div>

                            <div class="row g-5">
                                 <div class="col-xl-8">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-4 mb-0 needs-validation" method="POST" action="verDataSueldoVar@${idTrab}" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                                            <div class="col-sm-6 col-md-12">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexcodreg" id="iexcodreg" onchange="regimen();" required>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="Lovs_regimen" items="${Lovs_regimen}">
                                                      <option value="${Lovs_regimen.idLov}" >${Lovs_regimen.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                                <label>Regimen Laboral (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexcodpro" id="iexcodpro" onchange="procesoplanilla();" required >
                                                </select>
                                                <label>Proceso de Planilla (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexperiodo" id="iexperiodo" required >
                                                </select>
                                                <label>Periodo (*)</label>
                                              </div>
                                            </div>


                                            <!--<div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                                Se grabó exitosamente los cambios.
                                            </div>-->
                                            <div class="col-12 gy-6">
                                                <div class="row g-3 justify-content-end">
                                                  <div class="col-auto">
                                                    <a class="btn btn-phoenix-primary" href="detalleEmpl@${idTrab}">Atras</a>
                                                    <button class="btn btn-primary" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Ver Data</button>
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

          <jsp:include page="../../../demoWidget.jsp"></jsp:include>
    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->

    <jsp:include page="../../../customize.jsp"></jsp:include>
  </body>
</html>