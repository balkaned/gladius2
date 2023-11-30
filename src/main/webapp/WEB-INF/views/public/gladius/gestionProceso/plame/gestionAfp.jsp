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
                  <li class="breadcrumb-item"><a href="#!">Page</a></li>
                  <li class="breadcrumb-item active">Default</li>
                </ol>
              </nav>
              <div class="mb-9">
                <div class="row g-3 mb-4">
                  <div class="col-auto">
                    <h2 id="h2top" class="mb-0">Reporte de Afp Net</h2>
                  </div>
                </div>

                <div class="row g-5">
                  <div class="col-xl-10">
                    <div class="row gx-3 gy-4">
                      <form class="row g-4 mb-0 needs-validation" method="POST" action="" novalidate>
                          <div class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1">
                            <div class="table-responsive scrollbar mx-n1 px-1">
                              <table class="table table-hover">
                               <caption>Generar Archivos Afp Net</caption>
                                <tbody>
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                  <tr>
                                    <td class="align-middle fw-semi-bold text-20">Periodo Mensual YYYYMM</td>
                                    <td>
                                      <input type="text" name="permes" id="permes" value="${requestScope.permes}"
                                        class="form-control"  required />
                                    </td>
                                    <td></td>
                                  </tr>
                                    <tr>
                                    <td class="align-middle fw-semi-bold text-20">Generar Afp Net</td>
                                    <td class="align-middle fw-semi-bold text-20"><a href="#"
                                        onclick="SendSunatFile('REP','26')">Descargar</a></td>
                                    <td class="align-middle fw-semi-bold text-20"><a href="#"
                                        onclick="SendSunatFile('EXE','26')">Procesar</a></td>
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