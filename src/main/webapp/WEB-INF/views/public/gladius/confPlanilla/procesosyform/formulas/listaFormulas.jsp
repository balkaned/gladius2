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
      <link href='https://fonts.googleapis.com/css?family=JetBrains Mono' rel='stylesheet'>
    </head>

    <script>
      function remove() {
        var opcion = confirm("Esta seguro de Eliminar el Registro?");
        if (opcion == true) {
            return true;
        } else {
            return false;
        }
      }

      function compilar() {
        var opcion = confirm("Esta seguro de que desea compilar la fórmula?");
        if (opcion == true) {
            return true;
        } else {
            return false;
        }
      }

      function addOperador(value) {
          var campo = document.getElementById('text-box');
          var insertar = value;

          var inicio = campo.selectionStart;
          var fin = campo.selectionEnd;
          var texto = campo.value;
          campo.value = texto.slice(0, inicio) + insertar + texto.slice(fin);
          campo.selectionStart = campo.selectionEnd = inicio + insertar.length;
          campo.focus();
      }

      function traducirFormula(){
          var formula=document.getElementById("textAreaTraductor").value;

          $.ajax({
               url: "traducirFormulaAjax",
               data: {
                   },
               success: function (data) {
                   var opt = "";
                   var opt2 = "";
                   var onclickchar="";
                   var formulaReplace="";
                   var formulaResult="";
                   var desVariableAux="";

                   formula2=formula;
                   nuevaFormula=formula2;

                   var remplazarPor="";

                   //Busca y remplaza variables de formula
                   for (var i in data) {
                      desVariableAux=data[i].desVariable.trim().toString();
                      remplazarPor="["+"<label id='labelvariableform'>"+data[i].desVariable+"</label> "+"<label id='labelcomentario'>"+data[i].desAbreviacionCapit+"</label>]";

                      nuevaFormula = nuevaFormula.replaceAll(desVariableAux,remplazarPor);

                      /*opt += "<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                                "<td class='align-middle white-space-nowrap ps-3 pe-3'><a class='fw-semi-bold' href='#!'>"+i+"</a></td>"+
                                "<td class='align-middle white-space-nowrap text-start text-700 ps-3 pe-3'>"+data[i].desVariable+"</td>"+
                                "<td class='align-middle white-space-nowrap text-start text-700 ps-3 pe-3'>"+data[i].desAbreviacionCapit+"</td>"+
                              "</tr>";*/
                   }

                   //Busca y remplaza operador if else elseif switch case break
                   nuevaFormula = nuevaFormula.replaceAll("if","<label class='text-warning'>if&nbsp</label>");
                   nuevaFormula = nuevaFormula.replaceAll("else","<label class='text-warning'>else&nbsp</label>");
                   nuevaFormula = nuevaFormula.replaceAll("switch","<label class='text-warning'>switch</label>");
                   nuevaFormula = nuevaFormula.replaceAll("case","<label class='text-warning'>case</label>");
                   nuevaFormula = nuevaFormula.replaceAll("break","<label class='text-warning'>break</label>");
                   nuevaFormula = nuevaFormula.replaceAll("  ","<label>&nbsp &nbsp</label>");
                   nuevaFormula = nuevaFormula.replaceAll("\n","</br>");
                   nuevaFormula = nuevaFormula.replaceAll(";","<label id='labelpuntoycoma' class='text-warning'>;</label>");

                   /*nuevaFormula = nuevaFormula.replaceAll("=0","<label class='text-primary'>0</label>");
                   nuevaFormula = nuevaFormula.replaceAll("1","<label class='text-primary'>1</label>");
                   nuevaFormula = nuevaFormula.replaceAll("2","<label class='text-primary'>2</label>");
                   nuevaFormula = nuevaFormula.replaceAll("3","<label class='text-primary'>3</label>");
                   nuevaFormula = nuevaFormula.replaceAll("4","<label class='text-primary'>4</label>");
                   nuevaFormula = nuevaFormula.replaceAll("5","<label class='text-primary'>5</label>");
                   nuevaFormula = nuevaFormula.replaceAll("6","<label class='text-primary'>6</label>");
                   nuevaFormula = nuevaFormula.replaceAll("7","<label class='text-primary'>7</label>");
                   nuevaFormula = nuevaFormula.replaceAll("8","<label class='text-primary'>8</label>");
                   nuevaFormula = nuevaFormula.replaceAll("9","<label class='text-primary'>9</label>");*/

                   //opt2 = "<textarea id='textAreaTraducido' style='font-family: 'JetBrains Mono';font-size:11.5px;font-weight:400;' class='form-control border-200 bg-dark text-white rounded-top-0 border-0 flex-1' rows='10'>"+nuevaFormula+"</textarea>";
                   opt2 = "<div id='textAreaTraducido' class='form-control border-200 bg-1000 text-white rounded-top-0 border-0 flex-1' rows='10'>"+nuevaFormula+"</div>";

                   //$("#bodyTraducido").html(nuevaFormula);
                   $("#bodyTraducido2").html(opt2);
               }
          });
      }

      function obtenerFormula(idProceso,idFormula){
          $.ajax({
             url: "obtenerFormula",
             data: {
                 "idproceso": idProceso,
                 "idformula": idFormula
                 },
             success: function (data) {
                 document.getElementById("textAreaTraductor").value=data.desFormula;
             }
          });
      }

      $(document).ready(function() {
          var element=document.getElementById('operadorif');
          var formulaPreConf = "if ($variable$ == 1){ }";

          var dato="addOperador('"+formulaPreConf+"');";
          element.setAttribute('onclick',dato);
      });
    </script>
    <style>
        #textAreaTraducido{
            font-family: 'JetBrains Mono';
            font-size: 13px;
            font-weight: 400;
            color:#97ADC1 !important;
        }

        #labelvariableform{
        font-family: 'JetBrains Mono';
            color:#9876AA !important;
        }

        #labelpuntoycoma{
        font-family: 'JetBrains Mono';
            font-size: 13px;
            font-weight: 400;
        }

        #labelcomentario{
        font-family: 'JetBrains Mono';
            color:#808080 !important;
        }
    </style>
    <body>
        <!-- ===============================================-->
        <!--    Main Content-->
        <!-- ===============================================-->
        <main class="main" id="top">
          <jsp:include page="../../../../navsMenu.jsp"></jsp:include>
          <jsp:include page="../../../../navTop.jsp"></jsp:include>
          <jsp:include page="../../../../modalFade.jsp"></jsp:include>

          <div class="content">
            <nav class="mb-2" aria-label="breadcrumb">
              <ol class="breadcrumb mb-0">
                <li class="breadcrumb-item"><a href="#!">Conf. Planillas</a></li>
                <li class="breadcrumb-item active">Procesos y form</li>
                <li class="breadcrumb-item active">Fórmulas</li>
              </ol>
            </nav>
            <div class="mb-5">
              <div class="row g-3 mb-4">
                <div class="col-auto">
                  <h2 id="h2top" class="mb-2">Fórmulas</h2>
                  <h5 class="text-700 fw-semi-bold text-none">${desproceso}</h5>
                </div>
              </div>
            </div>
            <div id="orderTable" data-list='{"valueNames":["ordej","idoper","codconcept","concept","glosa","estado"],"page":15,"pagination":true}'>
              <div class="mb-4">
                <div class="row g-3">
                  <div class="col-auto">
                    <div class="search-box">
                      <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                        <input class="form-control search-input search" type="search" placeholder="Search formulas" aria-label="Search"/>
                        <span class="fas fa-search search-box-icon"></span>
                      </form>
                    </div>
                  </div>
                  <div class="col-auto scrollbar overflow-hidden-y flex-grow-1">
                    <div class="btn-group position-static" role="group">
                      <div class="btn-group position-static text-nowrap" role="group">
                        <button class="btn btn-phoenix-secondary px-7 flex-shrink-0" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true"
                                aria-expanded="false" data-bs-reference="parent">
                          Payment status<span class="fas fa-angle-down ms-2"></span></button>
                        <ul class="dropdown-menu dropdown-menu-end">
                          <li><a class="dropdown-item" href="#">Action</a></li>
                          <li><a class="dropdown-item" href="#">Another action</a></li>
                          <li><a class="dropdown-item" href="#">Something else here</a></li>
                          <li>
                            <hr class="dropdown-divider"/>
                          </li>
                          <li><a class="dropdown-item" href="#">Separated link</a></li>
                        </ul>
                      </div>
                      <div class="btn-group position-static text-nowrap" role="group">
                        <button class="btn btn-sm btn-phoenix-secondary px-7 flex-shrink-0" type="button" data-bs-toggle="dropdown" data-boundary="window"
                                aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                          Fulfilment status<span class="fas fa-angle-down ms-2"></span></button>
                        <ul class="dropdown-menu dropdown-menu-end">
                          <li><a class="dropdown-item" href="#">Action</a></li>
                          <li><a class="dropdown-item" href="#">Another action</a></li>
                          <li><a class="dropdown-item" href="#">Something else here</a></li>
                          <li>
                            <hr class="dropdown-divider"/>
                          </li>
                          <li><a class="dropdown-item" href="#">Separated link</a></li>
                        </ul>
                      </div>
                      <button class="btn btn-sm btn-phoenix-secondary px-7 flex-shrink-0">More filters</button>
                    </div>
                  </div>
                  <div>
                    <a class="btn btn-primary btn-sm" href="nuevaFormula@${requestScope.idProceso}"><span class="fas fa-plus me-2"></span>Add Formula</a>
                    <a class="btn btn-phoenix-secondary btn-sm ms-1" href="listProcesoFormulas"><span class="fas fa-reply me-2"></span>Atras</a>
                    <div class="btn-group mb-1 me-1 ms-0 mt-1">
                      <button class="btn btn-sm btn-phoenix-secondary" type="button"><span class="fa-solid fa-hashtag fs--1 me-2"></span></span class="ps-5">Exportar</span></button>
                      <button class="btn btn-sm dropdown-toggle dropdown-toggle-split btn-phoenix-secondary" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="sr-only"></span></button>
                      <div class="dropdown-menu">
                          <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="#">
                            <span class="fa-solid fa-download fs--1 me-2"></span>Exportar Excel Todos
                          </a>
                          <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Exportar Excel Solo Activos</a>
                          <div class="dropdown-divider"></div>
                          <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Otros</a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1">
                <div class="table-responsive scrollbar mx-n1 px-1">
                  <table class="table table-sm fs--1 mb-0">
                    <thead>
                        <tr>
                          <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                <div class="form-check mb-0 fs-0">
                                  <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                </div>
                          </th>
                          <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="ordej" style="width:5%;">ORD EJC</th>
                          <th class="sort align-middle text-center ps-3 pe-3" scope="col" data-sort="idoper">ID FORM</th>
                          <th class="sort align-middle text-center pe-0" scope="col" data-sort="codconcept">COD CONCEPTO</th>
                          <th class="sort align-middle text-center pe-0" scope="col" data-sort="concept">CONCEPTO</th>
                          <th class="sort align-middle text-center pe-0" scope="col" data-sort="glosa">GLOSA</th>
                          <th class="sort align-middle text-center pe-0" scope="col" data-sort="estado">ESTADO</th>
                          <th class="sort align-middle text-center pe-0" scope="col" ></th>
                        </tr>
                    </thead>
                    <tbody class="list" id="order-table-body">
                        <c:forEach var="formxcon" items="${requestScope.formulaXConceptoList}">
                          <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                            <td class="fs--1 align-middle px-0 py-3">
                                <div class="form-check mb-0 fs-0">
                                  <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}' />
                                </div>
                            </td>
                            <td class="ordej align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#${formxcon.formfororden}</a></td>
                            <td class="idoper align-middle text-center fw-semi-bold text-1000">${formxcon.formforcodfor}</td>
                            <td class="codconcept align-middle text-center fw-semi-bold text-1000"><span class="badge badge-tag me-2 mb-2"><span class="badge-label">${formxcon.formforcodcon}</span></td>
                            <td class="concept align-middle text-start fw-semi-bold text-600">${formxcon.conccoodescon}</td>
                            <td class="glosa align-middle text-start fw-semi-bold text-1000">${formxcon.formproglosa}</td>

                            <c:if test="${formxcon.formforflgest=='1'}"><td class="estado align-middle white-space-nowrap text-center fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-info"><span class="badge-label">1: Creado</span><span class="ms-1" style="height:12.8px;width:12.8px;"></span><span class="ms-1" data-feather="info" style="height:12.8px;width:12.8px;"></span></span></td></c:if>
                            <c:if test="${formxcon.formforflgest=='2'}"><td class="estado align-middle white-space-nowrap text-center fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span class="badge-label">2: Error en compilación</span><span class="ms-1" style="height:12.8px;width:12.8px;"></span><span class="ms-1" data-feather="x" style="height:12.8px;width:12.8px;"></span></span></td></c:if>
                            <c:if test="${formxcon.formforflgest=='3'}"><td class="estado align-middle white-space-nowrap text-center fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">3: Compilado correctamente</span><span class="ms-1" style="height:12.8px;width:12.8px;"></span><span class="ms-1" data-feather="check" style="height:12.8px;width:12.8px;"></span></span></td></c:if>

                            <td class="align-middle text-center white-space-nowrap pe-0 action">
                              <div class="font-sans-serif btn-reveal-trigger position-static">
                                <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                <div class="dropdown-menu dropdown-menu-end py-2">
                                  <a id="dropdownmenutable" class="dropdown-item" href="formularCodigo@${requestScope.idProceso}@${formxcon.formforcodfor}"><span class="fa-solid fa-square-root-variable me-2"></span>Formular</a>
                                  <a id="dropdownmenutable" class="dropdown-item" onclick="return compilar();" href="compilarFormula@${requestScope.idProceso}@${formxcon.formforcodfor}"><span class="fa-solid fa-diagram-project me-2"></span>Compilar fórmula</a>
                                  <a id="dropdownmenutable" class="dropdown-item" onclick="obtenerFormula('${requestScope.idProceso}','${formxcon.formforcodfor}');" href="#" data-bs-toggle="modal" data-bs-target="#traductorModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fa-solid fa-passport me-2"></span>Ver fórmula traductor</a>
                                  <div class="dropdown-divider"></div>
                                  <a id="dropdownmenutable" class="dropdown-item " onclick="return remove();" href="deleteFormula@${requestScope.idProceso}@${formxcon.formforcodfor}"><span class="fa-solid fa-trash me-2"></span>Eliminar</a>
                                </div>
                              </div>
                            </td>
                          </tr>
                        </c:forEach>
                    </tbody>
                  </table>
                </div>
                <div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
                  <div class="col-auto d-flex">
                    <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!" data-list-view="*">View all<span
                      class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none" href="#!" data-list-view="less">View Less<span
                      class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
                  </div>
                  <div class="col-auto d-flex">
                    <button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                    <ul class="mb-0 pagination"></ul>
                    <button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span></button>
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

    <div class="modal fade" id="traductorModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content bg-100">
            <form class="needs-validation" novalidate>
              <div class="modal-header border-200 bg-soft p-4">
                 <h5 class="modal-title text-1000 fs-2 lh-sm"><span class="fa-solid fa-passport me-2"></span>Traductor de fórmula</h5>
                 <button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>
              </div>
              <div class="modal-body p-4">
                <div class="">
                    <div id="alertAgrupSuccess" class="mt-1 alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                        <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                        <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                        <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <div id="alertAgrupInfo" class="mt-1 alert alert-outline-info bg-info bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;" >
                        <span class="fa-solid fa-info text-info fs-0 me-3"></span>
                        <div class="col-11">
                            <strong class="text-black">Información</strong>
                             <p class="mb-0 fw-semi-bold text-1000">Se eliminó exitosamente el elemento <a href="#">Mas información</a></p>
                        </div>
                        <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>

                    <div class="card d-flex flex-column mb-1">
                        <textarea id="textAreaTraductor" style="font-family: 'JetBrains Mono';font-size:11.5px;font-weight:400;" class="form-control border-200 rounded-bottom-0 border-0 flex-1"
                        rows="10" placeholder="Fórmula traducida..." disabled>${requestScope.fplanillax.desFormula}</textarea>

                        <div class="card-footer p-3">
                          <div class="d-flex justify-content-between align-items-center">
                            <!--<button class="btn p-0 me-3"><span class="fa-solid fa-image fs-0"></span></button>-->
                            <button class="btn p-0 me-3"><span class="fa-solid fa-calendar-alt fs-0"></span></button>
                            <!--<button class="btn p-0 me-3"><span class="fa-solid fa-map-marker-alt fs-0"></span></button>-->
                            <button class="btn p-0 me-3"><span class="fa-solid fa-tag fs-0"></span></button>
                            <div class="dropdown me-3 d-inline-block flex-1">
                              <button class="btn p-0 dropdown-toggle dropdown-caret-none d-flex align-items-center" type="button" data-bs-toggle="dropdown" aria-expanded="false"> <span class="fa-solid fa-globe-asia fs-0 me-1"></span><span class="me-1 lh-base d-none d-sm-block">Ejecutar</span><span class="fa-solid fa-caret-down fs--2 text-500"></span></button>
                              <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Compilar</a></li>
                                <li><a class="dropdown-item" href="#">Descargar</a></li>
                                <!--<li><a class="dropdown-item" href="#">Draft</a></li>-->
                              </ul>
                            </div>
                            <div class="d-flex align-items-center">
                              <a onclick="traducirFormula();" class="btn btn-primary btn-sm px-6 px-sm-8"><span class="fa-solid fa-bolt me-2"></span>Traducir</a>
                            </div>
                          </div>
                        </div>

                        <div id="bodyTraducido" class="bg-dark"></div>
                        <div id="bodyTraducido2"></div>
                    </div>
                </div>
              </div>
              <div class="modal-footer d-flex justify-content-end align-items-center px-0 pb-0 border-200 pt-0">
                    <a class="btn btn-sm btn-phoenix-primary px-9 my-0 ps-6 pe-6" data-bs-dismiss="modal" aria-label="Close">Cerrar</a>
                    <!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" type="submit">Guardar concepto prom</span></button>-->
              </div>
            </form>
        </div>
      </div>
    </div>
</html>
