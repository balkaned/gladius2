<%--
  Created by IntelliJ IDEA.
  User: Javier
  Date: 23/10/2023
  Time: 02:08
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
    <head>
        <jsp:include page="../../../../links.jsp"/>
        <link href='https://fonts.googleapis.com/css?family=JetBrains Mono' rel='stylesheet'>
        <script src="https://cdn.ckeditor.com/4.20.0/standard/ckeditor.js"></script>
    </head>
    <script>
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

        function mostrarAlert() {
            let div = document.getElementById('alert');
            div.style.display = '';

            setTimeout(function () {
                $("#alerts").hide(6000);
            }, 3000);
        }

        function valida_tipo_for(opcion) {
            if (opcion.value=="0"){
               document.getElementById("sqlprogram").value="";
               document.getElementById("grpeje").value="";
               document.getElementById("sqlprogram").disabled = true;
               document.getElementById("grpeje").disabled = true;
               document.getElementById("Layer1").disabled = false;
               document.getElementById("hashtag").disabled = false;
               document.getElementById("text-box").disabled = false;
            }else if (opcion.value=="1"){
               document.getElementById("sqlprogram").value="";
               document.getElementById("grpeje").value="";
               document.getElementById("sqlprogram").disabled = true;
               document.getElementById("grpeje").disabled = true;
               document.getElementById("Layer1").disabled = false;
               document.getElementById("hashtag").disabled = false;
               document.getElementById("text-box").disabled = false;
            }else if (opcion.value=="2") {
               document.getElementById("text-box").value="";
               document.getElementById("text-box").disabled = true;
               document.getElementById("sqlprogram").disabled = false;
               document.getElementById("grpeje").disabled = false;
               document.getElementById("Layer1").disabled = true;
               document.getElementById("hashtag").disabled = true;
            }
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
                     opt2 = "<div id='textAreaTraducido' class='form-control border-200 bg-dark text-white rounded-top-0 border-0 flex-1' rows='10'>"+nuevaFormula+"</div>";

                     //$("#bodyTraducido").html(nuevaFormula);
                     $("#bodyTraducido2").html(opt2);
                 }
            });
        }

        $(document).ready(function() {
            var element=document.getElementById('operadorif');

            var formulaPreConf = "if ($variable$ == 1){ }";

            var dato="addOperador('"+formulaPreConf+"');";
            element.setAttribute('onclick',dato);
        });

        //CKEDITOR.replace('text-box');
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
        <main class="main" id="top">
            <jsp:include page="../../../../navsMenu.jsp"/>
            <jsp:include page="../../../../navTop.jsp"/>
            <jsp:include page="../../../../modalFade.jsp"/>

            <div class="content">
                <nav class="mb-2" aria-label="breadcrumb">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="#!">Conf. Planillas</a></li>
                        <li class="breadcrumb-item active">Procesos y form</li>
                        <li class="breadcrumb-item active">Fórmulas</li>
                    </ol>
                </nav>
                <div class="mb-0">
                    <div class="row g-3 mb-0">
                        <div class="col-auto">
                            <h2 id="h2top" class="mb-0">Editar fórmula</h2>
                        </div>
                    </div>

                    <div class="row g-5">
                        <div class="col-xl-12">
                            <div class="row gx-3 gy-4">
                                <form class="row g-4 mb-0 needs-validation" method="POST" action="modificarFormula" novalidate >
                                    <div class="col-sm-6 col-md-2">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">ID Proceso</label>
                                        <input class="form-control" type="text" name="idprod" id="idprod" value="${idProceso}" disabled />
                                        <input type="hidden" name="idprod2" value="${idProceso}" />
                                    </div>
                                    <div class="col-sm-6 col-md-2">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">ID Formula</label>
                                        <input class="form-control" type="text" name="idfor" id="idfor" value="${idFormula}" disabled />
                                        <input type="hidden" name="idfor2" value="${idFormula}" />
                                    </div>
                                    <div class="col-sm-6 col-md-2">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Orden</label>
                                        <input class="form-control" type="text" name="nroorden" id="nroorden" value="${requestScope.fplanillax.nroOrden}" disabled />
                                        <input type="hidden" name="nroorden2" value="${requestScope.fplanillax.nroOrden}" />
                                    </div>
                                    <div class="col-sm-6 col-md-6">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Glosa</label>
                                        <input class="form-control" type="text" name="desglosa" id="desglosa" value="${requestScope.fplanillax.desGlosa}" required/>
                                    </div>
                                    <div class="col-sm-6 col-md-6">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Concepto resultado</label>
                                        <select id="codcon" name="codcon" class="form-select" required >
                                            <c:forEach var="Lovs_concept" items="${requestScope.Lovs_conxprod}">
                                                <option value="${Lovs_concept.codConcepto}" ${Lovs_concept.codConcepto == requestScope.fplanillax.idConcepto ? 'selected' : ''} >${Lovs_concept.desConcepto} - ${Lovs_concept.desVariable}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-6 col-md-3">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Estado</label>
                                        <select class="form-select" id="desestado" name="desestado" required disabled>
                                            <option class="text-info" value="1" ${requestScope.fplanillax.flgEstado=='1' ? 'selected' : ''} >1: Creado</option>
                                            <option class="text-danger" value="2" ${requestScope.fplanillax.flgEstado=='2' ? 'selected' : ''} >2: Error en compilación</option>
                                            <option class="text-success" value="3" ${requestScope.fplanillax.flgEstado=='3' ? 'selected' : ''} >3: Compilado correctamente</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-6 col-md-3">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de Ejecución</label>
                                        <select class="form-select" name="tipofor" id="tipofor" onchange="valida_tipo_for(this)" required >
                                            <option value="0"  ${requestScope.fplanillax.tipOut=='0' ? 'selected' : ''} > Seleccionar</option>
                                            <option value="1" ${requestScope.fplanillax.tipOut=='1' ? 'selected' : ''} > Ejecucion Normal</option>
                                            <option value="3" ${requestScope.fplanillax.tipOut=='3' ? 'selected' : ''} > Resultado Salto</option>
                                            <option value="2" ${requestScope.fplanillax.tipOut=='2' ? 'selected' : ''} > Ejecucion Stored Procedure DB</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-6 col-md-8">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Sql Program / Func</label>
                                        <input class="form-control" type="text" name="sqlprogram" id="sqlprogram" value="${requestScope.fplanillax.sqlprogram}" maxlength="180">
                                        <label class="form-check-label ms-2" for="flexChecked">Params required : (Numeric :p_codcia, Numeric :p_codpro, Varchar :p_nroper, Number :p_codtra, Varchar :p_concepFin, Varchar :p_grpeje)</label>
                                    </div>
                                    <div class="col-sm-6 col-md-4">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2 ">Grupo de Ejecución</label>
                                        <select class="form-select" name="grpeje" id="grpeje" />
                                            <option value="0" ${requestScope.fplanillax.grpeje=='0' ? 'selected' : ''} >Seleccionar</option>
                                            <option value="1" ${requestScope.fplanillax.grpeje=='1' ? 'selected' : ''} >x Trabajador</option>
                                            <option value="2"  ${requestScope.fplanillax.grpeje=='2' ? 'selected' : ''} >x Grupo</option>
                                        </select>
                                    </div>

                                    <div id="orderTable" data-list='{"valueNames":["concept"],"page":10,"pagination":true}'>
                                        <div class="mb-0">
                                          <div class="row g-3">
                                            <div class="col-auto">
                                              <a class="btn btn-primary btn-sm" href="nuevoConcepto" ><span class="fas fa-plus me-2"></span>Add concepto</a>
                                              <div class="btn-group mb-1 me-1 ms-0 mt-1">
                                                <button class="btn btn-sm btn-phoenix-secondary" type="button"><span class="fa-solid fa-passport fs--1 me-2"></span></span class="ps-5">Traductor de fórmula</span></button>
                                                <button class="btn btn-sm dropdown-toggle dropdown-toggle-split btn-phoenix-secondary" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="sr-only"></span></button>
                                                <div class="dropdown-menu">
                                                  <a id="dropdownmenutable" class="dropdown-item" href="#"  data-bs-toggle="modal" data-bs-target="#traductorModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fa-solid fa-bolt fs--1 me-2"></span>Traducir</a>
                                                </div>
                                              </div>
                                            </div>
                                            <div class="col-auto">
                                              <div class="search-box">
                                                  <div class="col-10">
                                                      <input class="form-control search-input search" type="search" placeholder="Search conceptos" aria-label="Search" />
                                                      <span class="fas fa-search search-box-icon"></span>
                                                  </div>
                                              </div>
                                            </div>
                                          </div>
                                        </div>

                                        <div class="row col-sm-6 col-md-12 mt-4">
                                            <div class="col-sm-6 col-md-5">
                                                <div class="" id="customerOrdersTable" data-list='{"valueNames":["concept"],"page":10,"pagination":true}'>
                                                    <div class="scrollbar">
                                                        <table class="fs--1 mb-0">
                                                          <thead>
                                                            <tr>
                                                              <!--<th class="sort white-space-nowrap align-middle ps-0 pe-0 text-start" style="width:5%;" scope="col" >#</th>-->
                                                              <th class="sort white-space-nowrap align-middle ps-3 pe-0" scope="col" data-sort="concept" ><span class="badge badge-tag me-2 mb-2">Conceptos</span></th>
                                                            </tr>
                                                          </thead>
                                                          <tbody class="list" id="customer-order-table-body" >
                                                            <tr><td class="concept align-middle white-space-nowrap ps-3 pe-3"><a onclick="addOperador('$resultado$');" class="hashtag btn btn-phoenix-secondary btn-sm me-2 mt-1 pt-1 pb-1 pe-2 ps-2">$resultado$</a></td></tr>
                                                            <tr><td class="concept align-middle white-space-nowrap ps-3 pe-3"><a onclick="addOperador('$salto$');" class="hashtag btn btn-phoenix-secondary btn-sm me-2 mt-1 pt-1 pb-1 pe-2 ps-2">$salto$</a></td></tr>
                                                            <c:forEach var="Lovs_conxprod" items="${requestScope.Lovs_conxprod}">
                                                                <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                                                  <td class="concept align-middle white-space-nowrap ps-3 pe-3" ><a onclick="addOperador('${Lovs_conxprod.desVariable}');" class="hashtag btn btn-phoenix-secondary btn-sm me-2 mt-1 pt-1 pb-1 pe-2 ps-2">${Lovs_conxprod.desVariable}</a> <span class="d-sm-inline">${Lovs_conxprod.desAbreviacionCapit}</span></td>
                                                                  <!--<td class="concept align-middle white-space-nowrap ps-3 pe-3" ><a onclick="addOperador('${Lovs_conxprod.desVariable}');" class="hashtag btn btn-link pe-3 ps-0 text-900 text-primary me-0"><span class="fa-solid fa-share-from-square me-2"></span>${Lovs_conxprod.desVariable}</a> <span class="d-none d-sm-inline">${Lovs_conxprod.desAbreviacionCapit}</span></td>-->
                                                                  <!--<td class="concept align-middle white-space-nowrap ps-3 pe-3" ><a onclick="addOperador('${Lovs_conxprod.desVariable}');" class="hashtag badge badge-phoenix badge-phoenix-primary fs--1 me-2"><span class="fa-solid fa-share-from-square me-2"></span>${Lovs_conxprod.desVariable}</a> <span class="d-none d-sm-inline">${Lovs_conxprod.desAbreviacionCapit}</span></td>-->
                                                                </tr>
                                                            </c:forEach>
                                                          </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
                                                          <div class="col-auto d-flex">
                                                            <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!" data-list-view="*">View all<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none" href="#!" data-list-view="less">View Less<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
                                                          </div>
                                                          <div class="col-auto d-flex">
                                                            <button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                                                            <ul class="mb-0 pagination"></ul>
                                                            <button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span></button>
                                                          </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-5">
                                                <h3 class="mb-4"> Editor de fórmulas</h3>
                                                <!--<textarea id="text-box" style="font-family: 'JetBrains Mono';font-size:11.5px;font-weight:400;" class="form-control border-200 rounded-bottom-0 border-0 flex-1 text-1000" placeholder="Escribe la fórmula aquí..." name="text-box" row="15">${requestScope.fplanillax.desFormula}</textarea>-->
                                                <!--id="textAreaTraductor" style="font-family: 'JetBrains Mono';font-size:11.5px;font-weight:400;" class="form-control border-200 rounded-bottom-0 border-0 flex-1"-->
                                                <textarea id="text-box" name="text-box" style="font-family: 'JetBrains Mono';font-size:13px;font-weight:400;" class="form-control border-200 rounded-bottom-0 border-0 flex-1 text-secondary"
                                                                        rows="15" placeholder="Escribe la fórmula aquí...">${requestScope.fplanillax.desFormula}</textarea>
                                                <a onclick="addOperador(' ');" class="btn btn-phoenix-primary w-100 mb-1 mt-2">Espacio</a>
                                                <!--<a onclick="addOperador('\n');" class="btn btn-phoenix-primary w-100 mb-0 mt-0">Enter</a>-->
                                            </div>
                                            <div class="col-sm-6 col-md-2">
                                                <table class="navy">
                                                    <tr>
                                                        <td colspan="2"><span class="badge badge-tag me-2 mb-2">Operador</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="align-middle white-space-nowrap text-start">
                                                            <a onclick="addOperador('+');" class="hashtag btn btn-phoenix-secondary btn-sm pt-0 pb-1 ps-2 pe-2 mb-1 fs-1 fw-semi-bold">+</a></br>
                                                            <a onclick="addOperador('-');" class="hashtag btn btn-phoenix-secondary btn-sm pt-0 pb-1 ps-2 pe-2 mb-1 fs-1 fw-semi-bold">-</a>
                                                            <a onclick="addOperador('*');" class="hashtag btn btn-phoenix-secondary btn-sm pt-0 pb-1 ps-2 pe-2 mb-1 fs-1 fw-semi-bold">*</a></br>
                                                            <a onclick="addOperador('/');" class="hashtag btn btn-phoenix-secondary btn-sm pt-0 pb-1 ps-2 pe-2 mb-1 fs-1 fw-semi-bold">/</a>
                                                            <a onclick="addOperador('=');" class="hashtag btn btn-phoenix-secondary btn-sm pt-0 pb-1 ps-2 pe-2 mb-1 fs-1 fw-semi-bold">=</a></br>
                                                            <br>
                                                            <a onclick="addOperador(';');" class="hashtag btn btn-phoenix-secondary btn-sm pt-0 pb-1 ps-2 pe-2 mb-1 fs-1 fw-semi-bold">;</a></br>
                                                            <a onclick="addOperador('[');" class="hashtag btn btn-phoenix-secondary btn-sm pt-0 pb-1 ps-2 pe-2 mb-1 fs-1 fw-semi-bold">[</a>
                                                            <a onclick="addOperador(']');" class="hashtag btn btn-phoenix-secondary btn-sm pt-0 pb-1 ps-2 pe-2 mb-1 fs-1 fw-semi-bold">]</a></br>
                                                            <a onclick="addOperador('(');" class="hashtag btn btn-phoenix-secondary btn-sm pt-0 pb-1 ps-2 pe-2 mb-1 fs-1 fw-semi-bold">(</a>
                                                            <a onclick="addOperador(')');" class="hashtag btn btn-phoenix-secondary btn-sm pt-0 pb-1 ps-2 pe-2 mb-1 fs-1 fw-semi-bold">)</a></br>
                                                            <a onclick="addOperador('{');" class="hashtag btn btn-phoenix-secondary btn-sm pt-0 pb-1 ps-2 pe-2 mb-1 fs-1 fw-semi-bold">{</a>
                                                            <a onclick="addOperador('}');" class="hashtag btn btn-phoenix-secondary btn-sm pt-0 pb-1 ps-2 pe-2 mb-1 fs-1 fw-semi-bold">}</a></br>
                                                            <br>
                                                            <a id="operadorif" class="hashtag btn btn-phoenix-warning btn-sm pt-1 pb-1 ps-2 pe-2 mb-1 fs--1">if</a>
                                                            <a onclick="addOperador('if ($variable$==1){} else{}');" class="hashtag btn btn-phoenix-warning btn-sm pt-1 pb-1 ps-2 pe-2 mb-1 fs--1">else</a></br>
                                                            <a onclick="addOperador('if ($variable$==1){} else if($variable$ < 20){}');" class="hashtag btn btn-phoenix-warning btn-sm pt-1 pb-1 ps-2 pe-2 mb-1 fs--1">else if</a>
                                                            <a onclick="addOperador('end if');" class="hashtag btn btn-phoenix-warning btn-sm pt-1 pb-1 ps-2 pe-2 mb-1 fs--1">end if</a></br>
                                                            <a onclick="addOperador('switch ($variable$){case 1: $variable$ =1; break;}');" class="hashtag btn btn-phoenix-warning btn-sm pt-1 pb-1 ps-2 pe-2 mb-1 fs--1">switch</a></br>
                                                            <a onclick="addOperador('\n');" class="hashtag btn btn-phoenix-primary btn-sm pt-2 pb-2 ps-3 pe-3 mb-1 fs--1">Enter</a>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="alert" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                                        <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                                        <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                                        <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                                    <div class="col-12 gy-6">
                                        <div class="row g-3 justify-content-end">
                                            <div class="col-auto">
                                                <a class="btn btn-phoenix-primary px-5" href="listFormulas@${idProceso}">Cancel</a>
                                            </div>
                                            <div class="col-auto">
                                                <button class="btn btn-primary px-5 px-sm-5" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">Guardar fórmula</button>
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
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="../../../../demoWidget.jsp"/>
        </main>
        <jsp:include page="../../../../customize.jsp"/>
    </body>

    <div class="modal fade" id="traductorModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content bg-100">
            <form class="needs-validation" action="javascript:addConceptoAgrup();" novalidate>
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
                        rows="10" placeholder="Fórmula traducida...">${requestScope.fplanillax.desFormula}</textarea>

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
