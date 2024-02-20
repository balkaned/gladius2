<%--
  Created by IntelliJ IDEA.
  User: Javier
  Date: 23/10/2023
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
    <head>
        <jsp:include page="../../../../links.jsp"></jsp:include>
    </head>
    <script>
        function obtenerLista(){
            $.ajax({
                 url: "getConceptoXprocesoFormula",
                 data: {
                     "accion": "#",
                     "proceso": $("#proceso").val(),
                     "select_concepto": $("#select_concepto").val()
                     },
                 success: function (data) {
                      var opt = "";

                      for (var i in data) {
                           opt += "<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                                      "<td class='fs--1 align-middle px-0 py-3'>"+
                                            "<div class='form-check mb-0 fs-0'>"+
                                               "<input class='form-check-input' type='checkbox'/>"+
                                            "</div>"+
                                      "</td>"+
                                      "<td class='order align-middle white-space-nowrap py-0'><a class='fw-semi-bold' href='#!'>#"+data[i].procodcon+"</a></td>"+
                                      "<td class='total align-middle text-start ps-5 fw-semi-bold text-1000'><span class='badge badge-phoenix fs--2 badge-phoenix-secondary'>"+data[i].coodescon+"</span></td>"+
                                      "<td class='align-middle text-center white-space-nowrap pe-0 action'>"+
                                            "<div class='font-sans-serif btn-reveal-trigger position-static'>"+
                                                "<button class='btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2' type='button' "+
                                                                     "data-bs-toggle='dropdown' data-boundary='window' aria-haspopup='true' aria-expanded='false' data-bs-reference='parent'>"+
                                                                     "<span class='fas fa-plus'></span><span class='fas fa-caret-down ms-2'></span></button>"+
                                                "<div class='dropdown-menu dropdown-menu-end py-2'>"+
                                                    "<a id='dropdownmenutable' class='dropdown-item' href='editarConceptoXProceso@${proceso}@"+data[i].procodcon+"'><span class='fa-solid fa-pencil me-2'></span>Editar</a>"+
                                                    "<div class='dropdown-divider'></div>"+
                                                    "<a id='dropdownmenutable' class='dropdown-item' onclick='return remove();' href='deleteConceptoXProceso@${proceso}@"+data[i].procodcon+"'><span class='fa-solid fa-trash me-2'></span>Eliminar</a>"+
                                                "</div>"+
                                            "</div>"+
                                      "</td>"+
                                   "<tr/>";
                      }

                      $("#bodyConceptoxProceso").html(opt);
                 }
            });
        }

        function remove() {
            var opcion = confirm("Esta seguro de Eliminar el Registro?");
            if (opcion == true) {
                return true;
            } else {
                return false;
            }
        }
    </script>
    <body>
        <main class="main" id="top">
            <jsp:include page="../../../../navsMenu.jsp"></jsp:include>
            <jsp:include page="../../../../navTop.jsp"></jsp:include>
            <jsp:include page="../../../../modalFade.jsp"></jsp:include>

            <div class="content">
                <nav class="mb-2" aria-label="breadcrumb">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="#!">Conf. Planillas</a></li>
                        <li class="breadcrumb-item active">Procesos y form</li>
                        <li class="breadcrumb-item active">Concepto x proceso</li>
                    </ol>
                </nav>
                <div class="mb-3">
                    <div class="row g-3">
                        <div class="col-auto">
                            <h2 id="h2top" class="mb-2">Conceptos por proceso</h2>
                            <h5 class="text-700 fw-semi-bold text-none">${desproceso}</h5>
                        </div>
                    </div>
                </div>
                <div class="row g-5">
                    <div class="col-xl-8">
                        <div class="row gx-3 gy-4">
                            <!-- Input hidden fields -->
                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}"/>
                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}"/>
                            <input class="form-control" id="proceso" name="proceso" type="hidden" value="${proceso}"/>

                            <div class="col-sm-6 col-md-4">
                                <select class="form-select" id="select_concepto" name="slc_grpconcepto" required>
                                    <option value="">Seleccionar concepto</option>
                                    <option value="0" ${requestScope.slc_grpconcepto  == '0' ? 'selected' : ''}>Parametros</option>
                                    <option value="1" ${requestScope.slc_grpconcepto  == '1' ? 'selected' : ''}>Haberes</option>
                                    <option value="2" ${requestScope.slc_grpconcepto  == '2' ? 'selected' : ''}>Descuentos</option>
                                    <option value="3" ${requestScope.slc_grpconcepto  == '3' ? 'selected' : ''}>Aportes</option>
                                    <option value="4" ${requestScope.slc_grpconcepto  == '4' ? 'selected' : ''}>Neto</option>
                                    <option value="5" ${requestScope.slc_grpconcepto  == '5' ? 'selected' : ''}>Totales</option>
                                </select>
                            </div>
                            <div>
                                <a id="btnBuscar" class="btn btn-primary btn-sm" onclick="obtenerLista();"><span class="fa-solid fa-magnifying-glass me-2"></span>Buscar</a>
                                <a class="btn btn-phoenix-secondary btn-sm" href="nuevoConceptoXProceso@${proceso}"><span class="fas fa-plus me-2"></span>Add Concepto</a>
                                <a class="btn btn-phoenix-secondary text-900 btn-sm" href="listProcesoFormulas"><span class="fa-solid fa-reply me-2"></span>Atras</a>
                                <div class="btn-group mb-1 me-1 ms-0 mt-1">
                                  <button class="btn btn-sm btn-phoenix-secondary" type="button"><span class="fa-solid fa-hashtag fs--1 me-2"></span></span class="ps-5">Exportar</span></button>
                                  <button class="btn btn-sm dropdown-toggle dropdown-toggle-split btn-phoenix-secondary" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="sr-only"></span></button>
                                  <div class="dropdown-menu">
                                      <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="#">
                                        <span class="fa-solid fa-download fs--1 me-2"></span>Exportar Afectaciones
                                      </a>
                                      <!--<a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Exportar Excel Solo Activos</a>
                                      <div class="dropdown-divider"></div>
                                      <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Otros</a>-->
                                  </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="orderTable" class="mt-3">
                    <div class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1">
                        <div class="table-responsive scrollbar mx-n1 px-1">
                            <table class="table table-sm fs--1 mb-0">
                                <thead>
                                    <tr>
                                        <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                            <div class="form-check mb-0 fs-0">
                                                <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox"
                                                             data-bulk-select='{"body":"order-table-body"}'/>
                                            </div>
                                        </th>
                                        <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="order" style="width:5%;">ID</th>
                                        <th class="sort align-middle text-center pe-0" scope="col" data-sort="date">CONCEPTO</th>
                                        <th class="sort align-middle text-center pe-0" scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody id="bodyConceptoxProceso">
                                </tbody>
                            </table>
                        </div>
                        <div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
                            <div class="col-auto d-flex">
                                <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a
                             class="fw-semi-bold" href="#!" data-list-view="*">View all<span
                             class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none"
                                                                                                                                                                                href="#!" data-list-view="less">View
                                Less<span
                                 class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
                            </div>
                            <div class="col-auto d-flex">
                                <button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                                <ul class="mb-0 pagination"></ul>
                                <button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span>
                                </button>
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
