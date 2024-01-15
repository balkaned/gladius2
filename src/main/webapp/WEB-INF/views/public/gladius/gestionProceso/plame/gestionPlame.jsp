<%-- Created on : 15/06/2023, 12:20:00 PM Author : Jean Quiroz Email : jeanp.quiroz@gmail.com --%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
      <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en-US" dir="ltr">

        <head>
          <jsp:include page="../../../links.jsp"></jsp:include>
        </head>
        <script>
          function mostrarAlert() {
            //alert("se grabo exitosamente");
            var div = document.getElementById('alert');
            div.style.display = '';

            setTimeout(function () {
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
                  <li class="breadcrumb-item"><a href="#!">Gestión de procesos externos</a></li>
                  <li class="breadcrumb-item active">Plame</li>
                </ol>
              </nav>
              <div class="mb-9">
                <div class="row g-3 mb-4">
                  <div class="col-auto">
                    <h2 id="h2top" class="mb-0">Reporte Plame</h2>
                  </div>
                </div>

                <div class="row g-5">
                  <div class="col-12">
                    <div class="row gx-3 gy-4">
                      <form class="row g-4 mb-0 needs-validation" method="POST" action="" novalidate>
                          <div class="col-12">
                            <div class="table-responsive scrollbar mx-n1 px-1">
                              <table class="table table-hover">

                               <span class="badge badge-tag me-2 mb-2">Generar Archivos Plame</span>
                                <tbody>
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                  <tr>
                                    <td class="align-middle fw-semi-bold text-20">Periodo Mensual</td>
                                    <td>
                                      <div class="col-sm-6 col-md-6">
                                        <input type="text" name="permes" id="permes" value="${requestScope.permes}" class="form-control"  placeholder ="yyyymm" required />
                                      </div>
                                    </td>
                                    <td></td>
                                  </tr>
                                  <tr class="md-10">
                                    <td class="align-middle fw-semi-bold text-20">Jornada Laboral[14] .jor</td>
                                    <td class="align-middle fw-semi-bold text-20"><a href="#"
                                        onclick="SendSunatFile('REP','14')">Descargar</a></td>
                                    <td class="align-middle fw-semi-bold text-20"><a href="#"
                                        onclick="SendSunatFile('EXE','14')">Actualizar</a></td>
                                  </tr>
                                  <tr>
                                    <td class="align-middle fw-semi-bold text-20">Dias No Laborados [15]</td>
                                    <td class="align-middle fw-semi-bold text-20"><a href="#"
                                        onclick="SendSunatFile('REP','15')">Descargar</a></td>
                                    <td class="align-middle fw-semi-bold text-20"><a href="#"
                                        onclick="SendSunatFile('EXE','15')">Actualizar</a></td>
                                  </tr>
                                  <tr>
                                    <td class="align-middle fw-semi-bold text-20">Detalle de Ingreso, Descuento [18]</td>
                                    <td class="align-middle fw-semi-bold text-20"><a href="#"
                                        onclick="SendSunatFile('REP','18')">Descargar</a></td>
                                    <td class="align-middle fw-semi-bold text-20"><a href="#"
                                        onclick="SendSunatFile('EXE','18')">Actualizar</a></td>
                                  </tr>
                                    <tr>
                                    <td class="align-middle fw-semi-bold text-20">Otras condiciones [26]</td>
                                    <td class="align-middle fw-semi-bold text-20"><a href="#"
                                        onclick="SendSunatFile('REP','26')">Descargar</a></td>
                                    <td class="align-middle fw-semi-bold text-20"><a href="#"
                                        onclick="SendSunatFile('EXE','26')">Actualizar</a></td>
                                  </tr>
                                  </tr>
                                </tbody>
                              </table>
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