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
          $(document).ready(function () {

            $('#iexcodreg').change(function (event) {


              $.ajaxSetup({ cache: false });
              $.ajax({
                url: "getlovsLOVCODTRA",
                data: {
                  "accion": "LOVCODTRA",
                  "iexcodreg": $("#iexcodreg").val()
                },
                success: function (data) {
                  var opt = "";
                  opt += "<option value='' > -- Selecciona -- </option>";
                  for (var i in data) {
                    opt += "<option value=" + data[i].iexcodtra + " > " + data[i].iexapepat + " " + data[i].iexapemat + " " + data[i].iexnomtra + " - " + data[i].iexfecing + " </option> ";
                  }

                  $("#iexcodtra").html(opt);
                }
              });

            });


          });


          function formatearFecha1() {
            var fechaSeleccionada = $('#iexfecini').val();

            var anio = fechaSeleccionada.substring(0, 4);
            var mes = fechaSeleccionada.substring(5, 7);
            var dia = fechaSeleccionada.substring(8, 10);

            var fechaFormat = dia + "/" + mes + "/" + anio;
            $("#iexfecini").val(fechaFormat);
          }

          function formatearFecha2() {
            var fechaSeleccionada = $('#iexfecfin').val();

            var anio = fechaSeleccionada.substring(0, 4);
            var mes = fechaSeleccionada.substring(5, 7);
            var dia = fechaSeleccionada.substring(8, 10);

            var fechaFormat = dia + "/" + mes + "/" + anio;
            $("#iexfecfin").val(fechaFormat);
          }


          function isValidDate(day, month, year) {
            var dteDate;
            month = month - 1;
            dteDate = new Date(year, month, day);
            return ((day == dteDate.getDate()) && (month == dteDate.getMonth()) && (year == dteDate.getFullYear()));
          }

          function validate_fecha(fecha) {
            var patron = new RegExp("^([0-9]{1,2})([/])([0-9]{1,2})([/])(19|20)+([0-9]{2})$");

            if (fecha.search(patron) == 0) {
              var values = fecha.split("/");
              if (isValidDate(values[0], values[1], values[2])) {
                return true;
              }
            }
            return false;
          }

          function calcularDias() {
            formatearFecha2();

            var fechaInicial = document.getElementById("iexfecini").value;
            var fechaFinal = document.getElementById("iexfecfin").value;
            var resultado = "";
            if (validate_fecha(fechaInicial) && validate_fecha(fechaFinal)) {
              inicial = fechaInicial.split("/");
              final = fechaFinal.split("/");
              // obtenemos las fechas en milisegundos
              var dateStart = new Date(inicial[2], (inicial[1] - 1), inicial[0]);
              var dateEnd = new Date(final[2], (final[1] - 1), final[0]);
              if (dateStart <= dateEnd) {
                // la diferencia entre las dos fechas, la dividimos entre 86400 segundos
                // que tiene un dia, y posteriormente entre 1000 ya que estamos
                // trabajando con milisegundos.
                //resultado="La diferencia es de "+(((dateEnd-dateStart)/86400)/1000)+" días";
                resultado = "" + (((dateEnd - dateStart) / 86400) / 1000) + "";
              } else {
                resultado = "La fecha inicial es posterior a la fecha final";
              }
            } else {
              if (!validate_fecha(fechaInicial))
                //resultado="La fecha inicial es incorrecta";
                resultado = "0";
              if (!validate_fecha(fechaFinal))
                //resultado="La fecha final es incorrecta";
                resultado = "0";
            }

            document.getElementById("iexnrodias").value = Number(resultado) + 1;
          }

          function diasVacaciones() {
            alert("entro");
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
                    <h2 id="h2top" class="mb-0">Insertar Ausentismo</h2>
                  </div>
                </div>

                <div class="row g-5">
                  <div class="col-xl-8">
                    <div class="row gx-3 gy-4">
                      <form class="row g-4 mb-0 needs-validation" method="POST" id="gtmvac" name="gtmvac"
                        action="insertarGestionAusentismo" novalidate>
                        <input class="form-control" name="iexcodcia" type="hidden"
                          value="${requestScope.emp.iexcodcia}" />
                        <input class="form-control" name="iexcodtra" type="hidden"
                          value="${requestScope.emp.iexcodtra}" />

                        <div class="col-sm-4 col-md-12">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Regimen(*)</label>
                          <select class="form-select" name="iexcodreg" id="iexcodreg" required>
                            <option value="" selected>Seleccionar</option>
                            <c:forEach var="Lovs_regimen" items="${requestScope.Lovs_regimen}">
                              <option value="${Lovs_regimen.idLov}" ${Lovs_regimen.idLov==requestScope.iexcodreg
                                ? 'selected' : '' }>${Lovs_regimen.desLov}</option>
                            </c:forEach>
                          </select>
                        </div>
                        <div class="col-sm-6 col-md-12">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Trabajador(*)</label>
                          <select name="iexcodtra" id="iexcodtra" class="form-select" required>
                          </select>
                        </div>
                        <div class="col-sm-6 col-md-12">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de Ausentismo(*)</label>
                          <select class="form-select" name="iexcodreg" id="iexcodreg" required>
                            <option value="" selected>Seleccionar</option>
                            <c:forEach var="lovTipaus" items="${requestScope.lovTipaus}">
                              <option value="${lovTipaus.idLov}" ${lovTipaus.idLov==requestScope.iextipaus ? 'selected'
                                : '' }> ${lovTipaus.desLov} </option>
                            </c:forEach>
                          </select>
                        </div>
                        <div class="col-sm-4 col-md-4">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de Inicio (*)</label><span
                            class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                          <input class="form-control datetimepicker" name="iexfecini" id="iexfecini"
                            onchange="formatearFecha1();" type="text" onchange="calcularDias();"
                            placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required />
                        </div>
                        <div class="col-sm-4 col-md-4">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha Fin (*)</label><span
                            class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                          <input class="form-control datetimepicker" name="iexfecfin" id="iexfecfin"
                            onchange="calcularDias();" type="text" placeholder="dd/mm/yyyy"
                            data-options='{"disableMobile":true}' required />
                        </div>

                        <div class="col-sm-6 col-md-3">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">N° de Dias(*)</label>
                          <input class="form-control" name="iexnrodias" id="iexnrodias" maxlength="10" type="text"
                            placeholder="0" required readonly />
                        </div>
                        <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                          Se grabó exitosamente los cambios.
                        </div>
                        <div class="col-12 gy-6">
                          <div class="row g-3 justify-content-end">
                            <div class="col-auto">
                              <a class="btn btn-phoenix-primary px-5" href="gestionTiempoListAusentismo">Cancel</a>
                            </div>
                            <div class="col-auto">
                              <button class="btn btn-primary px-5 px-sm-9" type="button" data-bs-toggle="modal"
                                data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true"
                                aria-expanded="false" data-bs-reference="parent">Insertar Ausentismo</button>
                            </div>
                          </div>
                        </div>
                        <div class="modal fade" id="confirmModal" tabindex="-1">
                          <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content border">
                              <form id="addEventForm" autocomplete="off">
                                <div class="modal-header border-200 p-4">
                                  <h5 class="modal-title text-1000 fs-4 lh-sm">Confirmar</h5>
                                  <button class="btn p-1 text-900" type="button" data-bs-dismiss="modal"
                                    aria-label="Close"><span class="fas fa-times fs--1"></span></button>
                                </div>
                                <div class="modal-body pt-4 pb-2 px-4">
                                  <div class="mb-3">
                                    <label class="fw-bold mb-2 text-1000" for="leadStatus">Esta seguro que desea
                                      confirmar la operacion?</label>
                                  </div>
                                </div>
                              </form>
                              <div
                                class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
                                <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="button"
                                  data-bs-dismiss="modal">Cancel</button>
                                <button class="btn btn-sm btn-primary px-9 fs--2 my-0" onclick="mostrarAlert();"
                                  type="submit" data-bs-dismiss="modal">Confirmar</button>
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