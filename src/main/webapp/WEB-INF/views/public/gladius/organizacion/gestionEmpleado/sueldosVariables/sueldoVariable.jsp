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
    function regimen(){
       $.ajax({
         url: "getlovsPROXCON",
         data: {"accion": "PROXCON",
             "iexcodreg": $("#iexcodreg").val()},
         success: function (data) {
             var opt = "";
                  opt += "<option value=0 >Seleccionar proceso planilla</option>";
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
                    opt += "<option value=0 >Seleccionar periodo</option>";
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
                          <nav class="mt-3 mb-2" aria-label="breadcrumb">
                            <ol class="breadcrumb mb-0">
                              <li class="breadcrumb-item"><a href="#!">Organización</a></li>
                              <li class="breadcrumb-item active">Trabajadores</li>
                              <li class="breadcrumb-item active">Sueldo variables</li>
                            </ol>
                          </nav>
                          <div class="mb-9">
                            <div class="row g-3 mb-4">
                              <div class="col-auto">
                                <h2 id="h2top" class="mb-0">Conceptos variables</h2>
                              </div>
                            </div>

                            <div class="row g-3 mt-0">
                              <div class="col-12">
                                <form class="needs-validation" method="POST" action="verDataSueldoVar@${idTrab}" novalidate>
                                  <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                  <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                            
                                  <!-- CARD: Filtros -->
                                  <div class="card mb-4 shadow-sm">
                                    <div class="card-header bg-light border-bottom">
                                      <h5 class="mb-0 text-primary"><i class="fas fa-filter me-2"></i>Filtros</h5>
                                    </div>
                                    <div class="card-body">
                                      <div class="row g-3">
                                        <div class="col-md-4">
                                          <label class="form-label fw-semibold">Regimen laboral</label>
                                          <select class="form-select" name="iexcodreg" id="iexcodreg" onchange="regimen();" required>
                                            <option value="">Seleccionar regimen</option>
                                            <c:forEach var="Lovs_regimen" items="${Lovs_regimen}">
                                              <option value="${Lovs_regimen.idLov}">${Lovs_regimen.desLov}</option>
                                            </c:forEach>
                                          </select>
                                        </div>
                                        <div class="col-md-4">
                                          <label class="form-label fw-semibold">Proceso de planilla</label>
                                          <select class="form-select" name="iexcodpro" id="iexcodpro" onchange="procesoplanilla();" required>
                                            <option value="">Seleccionar proceso</option>
                                          </select>
                                        </div>
                                        <div class="col-md-4">
                                          <label class="form-label fw-semibold">Periodo</label>
                                          <select class="form-select" name="iexperiodo" id="iexperiodo" required>
                                            <option value="">Seleccionar periodo</option>
                                          </select>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                            
                                  <!-- CARD: Acciones -->
                                  <div class="card mb-0">
                                    <div class="card-body d-flex justify-content-end gap-2">
                                      <a class="btn btn-phoenix-secondary btn-sm" href="detalleEmpl@${idTrab}" onclick="return cargarinfoEmpl(this.href, event);">
                                        <span class="fa-solid fa-reply me-2"></span>Atras
                                      </a>
                                      <button class="btn btn-primary btn-sm" type="submit">
                                        <span class="fas fa-search me-2"></span>Ver data
                                      </button>
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
              <jsp:include page="../../../../footer.jsp"></jsp:include>
          </div>

          <jsp:include page="../../../../demoWidget.jsp"></jsp:include>
    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->

    <jsp:include page="../../../../customize.jsp"></jsp:include>
  </body>
</html>