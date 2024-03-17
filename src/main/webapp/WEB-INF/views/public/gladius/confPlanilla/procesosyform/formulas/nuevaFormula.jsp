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
              //alert("valor="+opcion.value);
               document.getElementById("sqlprogram").value="";
               document.getElementById("grpeje").value="";
               document.getElementById("sqlprogram").disabled = true;
               document.getElementById("grpeje").disabled = true;
               document.getElementById("Layer1").disabled = false;
               document.getElementById("hashtag").disabled = false;
               //document.getElementById("text-box").disabled = false;
            }else if (opcion.value=="1"){
               document.getElementById("sqlprogram").value="";
               document.getElementById("grpeje").value="";
               document.getElementById("sqlprogram").disabled = true;
               document.getElementById("grpeje").disabled = true;
               document.getElementById("Layer1").disabled = false;
               document.getElementById("hashtag").disabled = false;
               //document.getElementById("text-box").disabled = false;
            }else if (opcion.value=="2") {
               //document.getElementById("text-box").value="";
               //document.getElementById("text-box").disabled = true;
               document.getElementById("sqlprogram").disabled = false;
               document.getElementById("grpeje").disabled = false;
               document.getElementById("Layer1").disabled = true;
               document.getElementById("hashtag").disabled = true;
            }
        }
    </script>
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
                            <h2 id="h2top" class="mb-0">Nueva fórmula</h2>
                        </div>
                    </div>

                    <div class="row g-5">
                        <div class="col-xl-12">
                            <div class="row gx-3 gy-4">
                                <form class="row g-4 mb-0 needs-validation" method="POST" action="insertarNuevaFormula" novalidate >
                                    <div class="col-sm-6 col-md-2">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">ID Proceso</label>
                                        <input class="form-control" type="text" name="idprod" id="idprod" value="${idProceso}" disabled  required />
                                        <input type="hidden" name="idprod2" value="${idProceso}" />
                                    </div>
                                    <!--<div class="col-sm-6 col-md-2">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">ID Formula</label>
                                        <input class="form-control" type="text" name="idfor" id="idfor" value="${idFormula}"/>
                                        <input type="hidden" name="idfor2" value="${idFormula}" />
                                    </div>-->
                                    <div class="col-sm-6 col-md-2">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Orden</label>
                                        <input class="form-control" type="text" name="nroorden" id="nroorden" value="${requestScope.fplanillax.nroOrden}" required/>
                                    </div>
                                    <div class="col-sm-6 col-md-6">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Glosa</label>
                                        <input class="form-control" type="text" name="desglosa" id="desglosa" value="${requestScope.fplanillax.desGlosa}" required/>
                                    </div>
                                    <div class="col-sm-6 col-md-6">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Concepto resultado</label>
                                        <select id="codcon" name="codcon" class="form-select" required >
                                            <option value="">Seleccionar</option>
                                            <c:forEach var="Lovs_concept" items="${requestScope.Lovs_conxprod}">
                                                <option value="${Lovs_concept.codConcepto}" ${Lovs_concept.codConcepto == requestScope.fplanillax.idConcepto ? 'selected' : ''} >${Lovs_concept.desConcepto} - ${Lovs_concept.desVariable}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <!--<div class="col-sm-6 col-md-3">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Estado</label>
                                        <select class="form-select" id="desestado" name="desestado">
                                            <option class="text-info" value="1" ${requestScope.fplanillax.flgEstado=='1' ? 'selected' : ''} >1: Creado</option>
                                            <option class="text-danger" value="2" ${requestScope.fplanillax.flgEstado=='2' ? 'selected' : ''} >2: Error en compilación</option>
                                            <option class="text-success" value="3" ${requestScope.fplanillax.flgEstado=='3' ? 'selected' : ''} >3: Compilado correctamente</option>
                                        </select>
                                    </div>-->
                                        <input type="hidden" name="desestado"  id="desestado" value="1"/>
                                    <div class="col-sm-6 col-md-3">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de Ejecución</label>
                                        <select class="form-select" name="tipofor" id="tipofor" onchange="valida_tipo_for(this)" required >
                                            <option value=""> Seleccionar</option>
                                            <option value="1" > Ejecucion Normal</option>
                                            <option value="3" > Resultado Salto</option>
                                            <option value="2" > Ejecucion Stored Procedure DB</option>
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
                                            <!--<div class="col-auto">
                                              <a class="btn btn-primary btn-sm" href="nuevoConcepto" ><span class="fas fa-plus me-2"></span>Add concepto</a>
                                            </div>-->
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
</html>
