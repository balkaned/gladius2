<%--
  Created by IntelliJ IDEA.
  User: space
  Date: 21/12/23
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../../../../links.jsp"></jsp:include>
    </head>

    <script>
        function mostrarAlert(){
          var div=document.getElementById('alert');
          div.style.display = '';

          setTimeout(function() {
        	  $("#alerts").hide(6000);
          }, 3000);
        }

        function buscarConceptos(){
            $.ajax({
                 url: "getConceptoxProcesoPromediable",
                 data: {
                     "codproceso": $("#codprocesoProm").val()
                     },
                 success: function (data) {
                     var opt = "";
                          //opt += "<option value=0 >Seleccionar</option>";
                          for (var i in data) {
                           opt += "<option value="+data[i].procodcon+" > "+data[i].coodescon+" </option> ";
                          }

                     $("#idconceptoProm").html(opt);
                 }
            });
        }

        function addConceptoPromediable(){
            $.ajax({
                 url: "addConceptoPromediable",
                 data: {
                     "codprocesoaux": $("#codprocesoProm").val(),
                     "codconceptoaux": $("#idconceptoProm").val(),
                     "codproceso": $("#idproceso").val(),
                     "codconcepto": $("#id_concept").val()
                     },
                 success: function (data) {
                    actualizarTabla();
                    mostrarAlertPromSucess();
                 }
            });
        }

        function actualizarTabla(){
            $.ajax({
                 url: "actualizarTblConceptoxProcesoPromediable",
                 data: {
                     "codproceso": $("#idproceso").val(),
                     "codconcepto": $("#id_concept").val()
                     },
                 success: function (data) {
                     var opt = "";
                     var onclickchar="";

                     for (var i in data) {
                        //onclickchar=data[i].idproceso+"','"+data[i].codconcepto+"','"+data[i].idprocesoaux+"','"+data[i].codconceptaux;

                        opt += "<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                                  "<td class='align-middle white-space-nowrap ps-3 pe-3'><a class='fw-semi-bold' href='#!'>#</a></td>"+
                                  "<td class='align-middle text-start fw-semi-bold ps-3 pe-3 text-1000'><span class='badge badge-phoenix fs--2 badge-phoenix-secondary'><span class='badge-label'>"+data[i].desprocesoaux+"</span></td>"+
                                  "<td class='align-middle white-space-nowrap text-center text-700 ps-3 pe-3'>"+data[i].codconceptaux+"</td>"+
                                  "<td class='align-middle white-space-nowrap text-center text-700 ps-3 pe-3'>"+data[i].desconceptaux+"</td>"+

                                  "<td class='align-middle text-center white-space-nowrap pe-0 action'>"+
                                    "<div class='font-sans-serif btn-reveal-trigger position-static'>"+
                                      "<button class='btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2' type='button'"+
                                      "data-bs-toggle='dropdown' data-boundary='window' aria-haspopup='true' aria-expanded='false' data-bs-reference='parent'>"+
                                      "<span class='fas fa-plus'></span><span class='fas fa-caret-down ms-2'></span></button>"+
                                      "<div class='dropdown-menu dropdown-menu-end py-2'>"+

                                        "<div class='dropdown-divider'></div>"+
                                        "<a id='dropdownmenutable' class='dropdown-item' onclick='return deleteConceptoPromediableAjax("+i+");'><span class='fa-solid fa-trash me-2'></span>Eliminar</a></div>"+
                                        "<input type='hidden' id='varidproceso"+i+"' value="+data[i].idproceso+" />"+
                                        "<input type='hidden' id='varcodconcepto"+i+"' value="+data[i].codconcepto+" />"+
                                        "<input type='hidden' id='varidprocesoaux"+i+"' value="+data[i].idprocesoaux+" />"+
                                        "<input type='hidden' id='varcodconceptaux"+i+"' value="+data[i].codconceptaux+" />"+
                                    "</div>"+
                                  "</td>"+
                                "</tr>";
                     }

                     $("#customer-order-table-body").html(opt);
                 }
            });
        }

        function deleteConceptoPromediable(idproceso,codconcepto,idprocesoaux,codconceptaux){

            var opcion = confirm("Esta seguro de Eliminar el Registro?");

            if (opcion == true) {
                $.ajax({
                     url: "deleteConceptoPromediable",
                     data: {
                         "codprocesoaux": idprocesoaux,
                         "codconceptoaux": codconceptaux,
                         "codproceso": idproceso,
                         "codconcepto": codconcepto
                         },
                     success: function (data) {
                        actualizarTabla();
                        mostrarAlertPromInfo();
                     }
                });
            } else {
                return false;
            }
        }

        function deleteConceptoPromediableAjax(indice){

            var idproceso=$("#varidproceso"+indice).val();
            var codconcepto=$("#varcodconcepto"+indice).val();
            var idprocesoaux=$("#varidprocesoaux"+indice).val();
            var codconceptaux=$("#varcodconceptaux"+indice).val();

            var opcion = confirm("Esta seguro de Eliminar el Registro?");

            if (opcion == true) {
                $.ajax({
                     url: "deleteConceptoPromediable",
                     data: {
                         "codprocesoaux": idprocesoaux,
                         "codconceptoaux": codconceptaux,
                         "codproceso": idproceso,
                         "codconcepto": codconcepto
                         },
                     success: function (data) {
                        actualizarTabla();
                        mostrarAlertPromInfo();
                     }
                });
            } else {
                return false;
            }
        }

        function remove() {
            var opcion = confirm("Esta seguro de Eliminar el Registro?");
            if (opcion == true) {
                return true;
            } else {
                return false;
            }
        }

        function mostrarAlertPromSucess(){
          var div=document.getElementById('alertPromSuccess');
          div.style.display = '';
        }

        function mostrarAlertPromInfo(){
          var div=document.getElementById('alertPromInfo');
          div.style.display = '';
        }

        function addConceptoAgrup(){
            $.ajax({
                 url: "addConceptoAgrup",
                 data: {
                     "codproceso": $("#idproceso").val(),
                     "codconcepto": $("#id_concept").val(),
                     "codconceptoaux": $("#idconceptoAgrp").val()
                     },
                 success: function (data) {
                    actualizarTablaAgrup();
                    mostrarAlertAgrupSucess();
                 }
            });
        }

        function actualizarTablaAgrup(){
            $.ajax({
                 url: "actualizarTblConceptoAgrup",
                 data: {
                     "codproceso": $("#idproceso").val(),
                     "codconcepto": $("#id_concept").val()
                     },
                 success: function (data) {
                     var opt = "";
                     var onclickchar="";

                     for (var i in data) {
                        onclickchar="onclick='return deleteConceptoAgrup('"+data[i].idproceso+"','"+data[i].codconcepto+"','"+data[i].codconceptaux+"');'";

                        opt += "<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                                  "<td class='align-middle white-space-nowrap ps-3 pe-3'><a class='fw-semi-bold' href='#!'>#</a></td>"+
                                  "<td class='align-middle text-start fw-semi-bold ps-3 pe-3 text-1000'><span class='badge badge-tag me-2 mb-2'>"+data[i].codconceptaux+"</span></td>"+
                                  "<td class='align-middle white-space-nowrap text-center text-700 ps-3 pe-3'>"+data[i].desconceptaux+"</td>"+

                                  "<td class='align-middle text-center white-space-nowrap pe-0 action'>"+
                                    "<div class='font-sans-serif btn-reveal-trigger position-static'>"+
                                      "<button class='btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2' type='button'"+
                                      "data-bs-toggle='dropdown' data-boundary='window' aria-haspopup='true' aria-expanded='false' data-bs-reference='parent'>"+
                                      "<span class='fas fa-plus'></span><span class='fas fa-caret-down ms-2'></span></button>"+
                                      "<div class='dropdown-menu dropdown-menu-end py-2'>"+

                                        "<div class='dropdown-divider'></div>"+
                                        "<a id='dropdownmenutable' class='dropdown-item' onclick='return deleteConceptoAgrupAjax("+i+");' ><span class='fa-solid fa-trash me-2'></span>Eliminar</a></div>"+
                                        "<input type='hidden' id='varAgrupidproceso"+i+"' value="+data[i].idproceso+" />"+
                                        "<input type='hidden' id='varAgrupcodconcepto"+i+"' value="+data[i].codconcepto+" />"+
                                        "<input type='hidden' id='varAgrupcodconceptaux"+i+"' value="+data[i].codconceptaux+" />"+
                                    "</div>"+
                                  "</td>"+
                                "</tr>";
                     }

                     $("#customer-order-table-body-agrup").html(opt);
                 }
            });
        }

        function mostrarAlertAgrupSucess(){
          var div=document.getElementById('alertAgrupSuccess');
          div.style.display = '';
        }

        function mostrarAlertAgrupInfo(){
          var div=document.getElementById('alertAgrupInfo');
          div.style.display = '';
        }

        function deleteConceptoAgrup(idproceso,codconcepto,codconceptaux){

            var opcion = confirm("Esta seguro de Eliminar el Registro?");

            if (opcion == true) {
                $.ajax({
                     url: "deleteConceptoAgrup",
                     data: {
                         "codproceso": idproceso,
                         "codconcepto": codconcepto,
                         "codconceptoaux": codconceptaux
                         },
                     success: function (data) {
                        actualizarTablaAgrup();
                        mostrarAlertAgrupInfo();
                     }
                });
            } else {
                return false;
            }
        }

        function deleteConceptoAgrupAjax(indice){

            var idproceso=$("#varAgrupidproceso"+indice).val();
        	var codconcepto=$("#varAgrupcodconcepto"+indice).val();
        	var codconceptaux=$("#varAgrupcodconceptaux"+indice).val();

            var opcion = confirm("Esta seguro de Eliminar el Registro?");

            if (opcion == true) {
                $.ajax({
                     url: "deleteConceptoAgrup",
                     data: {
                         "codproceso": idproceso,
                         "codconcepto": codconcepto,
                         "codconceptoaux": codconceptaux
                         },
                     success: function (data) {
                        actualizarTablaAgrup();
                        mostrarAlertAgrupInfo();
                     }
                });
            } else {
                return false;
            }
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
            <jsp:include page="../../../../demoWidget.jsp"></jsp:include>

            <div class="content">
                <nav class="mb-2" aria-label="breadcrumb">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="#!">Conf. Planillas</a></li>
                        <li class="breadcrumb-item active">Procesos y form</li>
                        <li class="breadcrumb-item active">Concepto x proceso</li>
                    </ol>
                </nav>
                <div class="mb-9">
                    <div class="row g-5">
                        <div class="col-xl-8">
                            <div class="row gx-3 gy-4">
                                <form class="row g-4 mb-0 needs-validation" method="POST" action="modificarConceptoXProceso" novalidate>
                                    <h2>Editar concepto x proceso</h2>
                                    <h5 class="text-700 mt-1 fw-semi-bold">${desproceso}</h5>

                                    <div class="col-sm-6 col-md-3">
                                         <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">ID Proceso</label>
                                         <input class="form-control" name="idprocesodis" type="text" value="${proceso}" readonly disabled/>
                                    </div>
                                    <input type="hidden" id="idproceso" name="idproceso" value="${proceso}"/>

                                    <div class="col-sm-6 col-md-4">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Concepto</label>
                                        <input type="text" id="id_conceptodis" name="id_conceptdis" value="${requestScope.proconceptox.procodcon}"  class="form-control"  disabled />
                                        <label class="form-check-label fs--2 ms-2">${requestScope.proconceptox.coodescon}</label>
                                        <input type="hidden" id="id_concept" name="id_concept" value="${requestScope.proconceptox.procodcon}"/>
                                    </div>
                                    <div class="col-sm-6 col-md-6">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="tip_concepto">Tipo de Concepto</label>
                                        <select id="tip_concepto" name="tip_concepto" class="form-select" required>
                                            <option value="0" ${requestScope.proconceptox.protipcon=='0' ? 'selected' : ''}>Parametro</option>
                                            <option value="1" ${requestScope.proconceptox.protipcon=='1' ? 'selected' : ''}>Ingresos</option>
                                            <option value="2" ${requestScope.proconceptox.protipcon=='2' ? 'selected' : ''}>Descuentos</option>
                                            <option value="3" ${requestScope.proconceptox.protipcon=='3' ? 'selected' : ''}>Aportes</option>
                                            <option value="4" ${requestScope.proconceptox.protipcon=='4' ? 'selected' : ''}>Neto</option>
                                            <option value="5" ${requestScope.proconceptox.protipcon=='5' ? 'selected' : ''}>Otros</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-6 col-md-4">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="id_concepto_pdt">Codigo PDT</label>
                                        <input class="form-control" id="id_concepto_pdt" name="id_concepto_pdt" type="text" value="${requestScope.proconceptox.procodconpdt}" />
                                    </div>
                                    <div class="col-sm-6 col-md-12">
                                        <input type="checkbox" class="form-check-input" name="flg_boleta" value="1" ${requestScope.proconceptox.proflgbol=='1' ? 'checked=true' : ''} id="flg_boleta"/>
                                        <label class="form-check-label ms-2" for="flg_boleta">Flag Boleta</label>
                                    </div>
                                    <div class="col-sm-6 col-md-3">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="id_orden_bol">Orden</label>
                                        <input class="form-control" id="id_orden_bol" name="id_orden_bol" type="text" maxlength="50" value="${requestScope.proconceptox.proorden}" />
                                    </div>
                                    <div class="col-sm-6 col-md-3">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="valor_bol">Valor</label>
                                        <input class="form-control" id="valor_bol" name="valor_bol" type="text" maxlength="50" value="${requestScope.proconceptox.provalor}" />
                                    </div>
                                    <div class="col-sm-6 col-md-6">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="des_custom">Descripcion customizada</label>
                                        <input class="form-control" id="des_custom" name="des_custom" type="text" maxlength="50" value="${requestScope.proconceptox.prodescustom}" />
                                    </div>
                                    <div class="col-sm-6 col-md-3">
                                        <input type="checkbox" class="form-check-input" name="flg_promediable" value="1" ${requestScope.proconceptox.flg_promediable=='1' ? 'checked=true' : ''} id="flg_promediable"/>
                                        <label class="form-check-label ms-2" for="flg_promediable">Promediable</label>
                                    </div>
                                    <div class="col-sm-6 col-md-3">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2" for="nro_meses_atras">Nro Meses Atras</label>
                                        <input class="form-control" id="nro_meses_atras" name="nro_meses_atras" type="text" maxlength="50" value="${requestScope.proconceptox.nro_meses_atras}"/>
                                    </div>
                                    <div class="col-sm-6 col-md-6">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Ver conceptos promediables</label>
                                        <button class="btn btn-phoenix-secondary btn-sm" type="button" data-bs-toggle="modal" data-bs-target="#conceptPromediableModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" ><span class="fas fa-plus me-2"></span>Conceptos promediables</button>
                                    </div>
                                    <div class="col-sm-6 col-md-4">
                                        <input type="checkbox" class="form-check-input" name="flg_agrupable" value="1" ${requestScope.proconceptox.flg_agrupable=='1' ? 'checked=true' : ''} id="flg_agrupable"/>
                                        <label class="form-check-label ms-2" for="flg_agrupable">Grupo de concepto</label>
                                    </div>
                                    <div class="col-sm-6 col-md-6">
                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Ver grupo de conceptos</label>
                                        <button class="btn btn-phoenix-secondary btn-sm" type="button" data-bs-toggle="modal" data-bs-target="#grupoConceptosModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" ><span class="fas fa-plus me-2"></span>Grupo de conceptos</button>
                                    </div>
                                    <!--<div class="col-sm-6 col-md-12">
                                        <label class="form-check-label ms-2" for="flg_agrupable">Contabilidad</label>
                                         <a class="ms-2 btn btn-phoenix-secondary btn-sm text-900 me-4 px-0 ps-3 pe-4" href="#"><span class="fa-solid fa-sliders fs--1 me-2"></span>Conf c.contables x compañia</a>
                                    </div>-->
                                    <div class="form-group row mt-8">
                                        <label class="col-sm-6 col-md-4 control-label text-1000 fw-semi-bold">Tipo de Ingreso
                                            <br>
                                            <small class="text-navy">Tipo de Concepto de Ingreso</small>
                                        </label>
                                        <div class="col-sm-6 col-md-8">
                                            <div class="radio">
                                                <input type="radio" name="tip_ingreso" value="1" ${requestScope.proconceptox.tip_ingreso=='1' ? 'checked' : ''} class="form-check-input">
                                                <label class="form-check-label ms-2">Rem.Fija (Sueldos, Asig. fam. etc)</label>
                                            </div>
                                            <div class="radio">
                                                <input type="radio" name="tip_ingreso" value="2" ${requestScope.proconceptox.tip_ingreso=='2' ? 'checked' : ''} class="form-check-input">
                                                <label class="form-check-label ms-2">Rem. Variable (Comisiones, bonificaciones, etc)</label>
                                            </div>
                                            <div class="radio">
                                                <input type="radio" name="tip_ingreso" value="3" ${requestScope.proconceptox.tip_ingreso=='3' ? 'checked' : ''} class="form-check-input">
                                                <label class="form-check-label ms-2">Rem. Complementaria</label>
                                            </div>
                                            <div class="radio">
                                                <input type="radio" name="tip_ingreso" value="4" ${requestScope.proconceptox.tip_ingreso=='4' ? 'checked' : ''} class="form-check-input">
                                                <label class="form-check-label ms-2">Ninguno</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row mt-8">
                                        <label class="col-sm-6 col-md-4 control-label text-1000 fw-semi-bold">Tipo de Descuento 5ta
                                            <br>
                                            <small class="text-navy">Flag de Descuento de 5ta</small>
                                        </label>
                                        <div class="col-sm-6 col-md-8">
                                            <div class="checkbox">
                                                <input class="form-check-input" type="checkbox" name="flg_pry_5ta" value="1" ${requestScope.proconceptox.flg_pry_5ta=='1' ? 'checked' : ''} >
                                                <label class="form-check-label ms-2"> Rem. Proyecta 5ta.</label>
                                            </div>
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_des_5ta_mes" value="1" ${requestScope.proconceptox.flg_des_5ta_mes=='1' ? 'checked' : ''} class="form-check-input">
                                                <label class="form-check-label ms-2">Rem. Descuenta 5ta en el Mes</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row mt-8">
                                        <label class="col-sm-6 col-md-4 control-label text-1000 fw-semi-bold">Afectaciones - Empleador
                                            <br>
                                            <small class="text-navy">Flag de Descuento que se le aplica al Empleador </small>
                                        </label>
                                        <div class="col-sm-6 col-md-8">
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_ess_reg" value="1" class="form-check-input" ${requestScope.proconceptox.flg_ess_reg=='1' ? 'checked' : ''}>
                                                <label class="form-check-label ms-2">Essalud Seguro Regular de Trabajador</label>
                                            </div>
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_ess_pesq" value="1" class="form-check-input" ${requestScope.proconceptox.flg_ess_pesq=='1' ? 'checked' : ''}>
                                                <label class="form-check-label ms-2">Essalud - CBSSP - Seg. Trab Pesquero</label>
                                            </div>
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_ess_agrac" value="1" class="form-check-input" ${requestScope.proconceptox.flg_ess_agrac=='1' ? 'checked' : ''} >
                                                <label class="form-check-label ms-2">Essalud Seguro Agrario / Acuicultor</label>
                                            </div>
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_ess_sctr" value="1" class="form-check-input" ${requestScope.proconceptox.flg_ess_sctr =='1' ? 'checked' : ''}  >
                                                <label class="form-check-label ms-2">Essalud Sctr</label>
                                            </div>
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_extra_solid" value="1" class="form-check-input" ${requestScope.proconceptox.flg_extra_solid=='1' ? 'checked' : ''}>
                                                <label class="form-check-label ms-2">Imp. Extraord Solidaridad (8)</label>
                                            </div>
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_fondo_art" value="1" class="form-check-input" ${requestScope.proconceptox.flg_fondo_art=='1' ? 'checked' : ''}>
                                                <label class="form-check-label ms-2">Fondo Derechos Sociales del Artista</label>
                                            </div>
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_apo_senati" value="1" class="form-check-input" ${requestScope.proconceptox.flg_apo_senati =='1' ? 'checked' : ''}>
                                                <label class="form-check-label ms-2">Aportacion de Senati</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row mt-8">
                                        <label class="col-sm-6 col-md-4 control-label text-1000 fw-semi-bold ">Afectaciones - Trabajador
                                            <br>
                                            <small class="text-navy">Afectaciones de descuentos que se efectuan al trabajador</small>
                                        </label>
                                        <div class="col-sm-6 col-md-8">
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_onp" value="1" class="form-check-input" ${requestScope.proconceptox.flg_onp  =='1' ? 'checked' : ''}>
                                                <label class="form-check-label ms-2">Sistema Nacional de Pensiones 19990</label>
                                            </div>
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_afp" value="1" class="form-check-input" ${requestScope.proconceptox.flg_afp  =='1' ? 'checked' : ''} >
                                                <label class="form-check-label ms-2">Sistema Privado de Pensiones</label>
                                            </div>
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_fond_compl_jub" value="1" class="form-check-input" ${requestScope.proconceptox.flg_fond_compl_jub  =='1' ? 'checked' : '' } >
                                                <label class="form-check-label ms-2">Fondo Compl. de Jubil Min, Met y Sider</label>
                                            </div>
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_esp_pens_pesq" value="1" class="form-check-input" ${requestScope.proconceptox.flg_esp_pens_pesq  =='1' ? 'checked' : '' }>
                                                <label class="form-check-label ms-2">Reg. Esp. Pensiones Trab. Pesquero</label>
                                            </div>
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_5ta" value="1" class="form-check-input" ${requestScope.proconceptox.flg_5ta  =='1' ? 'checked' : ''}>
                                                <label class="form-check-label ms-2">Imp. Renta de 5ta Categoria</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row mt-8">
                                        <label class="col-sm-6 col-md-4 control-label text-1000 fw-semi-bold">Afectaciones - Pensionistas
                                            <br>
                                            <small class="text-navy">Afectaciones de aportes al pensionista</small>
                                        </label>
                                        <div class="col-sm-6 col-md-8">
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_ess_seg_pen" value="1" class="form-check-input" ${requestScope.proconceptox.flg_ess_seg_pen =='1' ? 'checked' : ''}>
                                                <label class="form-check-label ms-2">Essalud Seguro Regular Pensionista</label>
                                            </div>
                                            <div class="checkbox">
                                                <input type="checkbox" name="flg_cont_asis_previs" value="1" class="form-check-input" ${requestScope.proconceptox.flg_cont_asis_previs =='1' ? 'checked' : ''}>
                                                <label class="form-check-label ms-2">Contrib. Solidaria Asistencia Previs.</label>
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
                                                <a class="btn btn-phoenix-primary px-5" href="listConceptoXProceso@${proceso}">Cancel</a>
                                            </div>
                                            <div class="col-auto">
                                                <button class="btn btn-primary px-5 px-sm-5" type="button" data-bs-toggle="modal"
                                                                data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true"
                                                                aria-expanded="false" data-bs-reference="parent">Guardar Concepto x proceso
                                                </button>
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
                                              <button class="btn btn-phoenix-primary btn-sm px-4 my-0 mt-1" type="button" data-bs-dismiss="modal" >Cancel</button>
                                              <button class="btn btn-primary btn-sm px-9 my-0 mt-1" onclick="mostrarAlert();" type="submit" data-bs-dismiss="modal" >Confirmar</button>
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
        </main>
        <!-- ===============================================-->
        <!--    End of Main Content-->
        <!-- ===============================================-->
        <jsp:include page="../../../../customize.jsp"></jsp:include>
    </body>

    <div class="modal fade" id="conceptPromediableModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content bg-100">
            <form class="needs-validation" action="javascript:addConceptoPromediable();" novalidate>
              <div class="modal-header border-200 bg-soft p-4">
                 <h5 class="modal-title text-1000 fs-2 lh-sm">Gestión de conceptos promediables</h5>
                 <button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>
              </div>
              <div class="modal-body p-4">
                <div class="row g-4">
                    <div id="alertPromSuccess" class="mt-2 mb-0 alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                        <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                        <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                        <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <div id="alertPromInfo" class="mt-2 mb-0 alert alert-outline-info bg-info bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;" >
                        <span class="fa-solid fa-info text-info fs-0 me-3"></span>
                        <div class="col-11">
                            <strong class="text-black">Información</strong>
                             <p class="mb-0 fw-semi-bold text-1000">Se eliminó exitosamente el elemento <a href="#">Mas información</a></p>
                        </div>
                        <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>

                    <div class="row col-12 mt-3">
                        <div class="col-sm-6 col-md-6">
                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Procesos</label>
                              <select name="codprocesoProm" id="codprocesoProm" onchange="buscarConceptos();" class="form-select" required >
                                  <option value="" selected >Seleccionar proceso</option>
                                  <c:forEach var="LstPromProceso" items="${LstPromProceso}">
                                      <option value="${LstPromProceso.idProceso}" >${LstPromProceso.desProceso}</option>
                                  </c:forEach>
                              </select>
                        </div>
                        <div class="col-sm-6 col-md-6">
                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Concepto</label>
                              <select name="idconceptoProm" id="idconceptoProm" class="form-select" required >
                                    <option value="">Seleccionar</option>
                              </select>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <button class="btn btn-phoenix-secondary btn-sm" type="submit" ><span class="fas fa-plus me-2"></span>Add concepto promediable</button>
                    </div>
                    <div class="border-top border-bottom border-200" id="customerOrdersTable" data-list='{"valueNames":["order","total","payment_status","fulfilment_status","delivery_type","date"],"page":4,"pagination":true}'>
                        <div class="table-responsive scrollbar">
                            <table class="table table-sm fs--1 mb-0">
                              <thead>
                                <tr>
                                  <th class="sort white-space-nowrap align-middle ps-0 pe-3 text-uppercase" scope="col" ></th>
                                  <th class="sort align-middle text-center ps-5 pe-5 text-uppercase" scope="col" data-sort="total">PROCESO</th>
                                  <th class="sort align-middle text-center white-space-nowrap ps-3 pe-3 text-uppercase" scope="col" data-sort="payment_status" >ID CONCEPTO</th>
                                  <th class="sort align-middle text-center white-space-nowrap text-start ps-3 pe-3 text-uppercase" scope="col" data-sort="fulfilment_status" >DESCONCEPTO</th>
                                  <th class="sort text-end text-center align-middle ps-3 pe-3 text-uppercase" scope="col"></th>
                                </tr>
                              </thead>
                              <tbody class="list" id="customer-order-table-body">
                                <c:forEach var="LstconceptoxProcesod" items="${requestScope.LstconceptoxProcesod}">
                                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                      <td class="align-middle white-space-nowrap ps-3 pe-3"><a class="fw-semi-bold" href="#!">#</a></td>
                                      <td class="align-middle text-start fw-semi-bold ps-3 pe-3 text-1000"><span class="badge badge-phoenix fs--2 badge-phoenix-secondary"><span class="badge-label">${LstconceptoxProcesod.desprocesoaux}</span></td>
                                      <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">${LstconceptoxProcesod.codconceptaux}</td>
                                      <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">${LstconceptoxProcesod.desconceptaux}</td>

                                      <td class="align-middle text-center white-space-nowrap pe-0 action">
                                        <div class="font-sans-serif btn-reveal-trigger position-static">
                                          <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                          data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                          <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                          <div class="dropdown-menu dropdown-menu-end py-2">

                                            <div class="dropdown-divider"></div>
                                            <a id="dropdownmenutable" class="dropdown-item" onclick="return deleteConceptoPromediable('${LstconceptoxProcesod.idproceso}','${LstconceptoxProcesod.codconcepto}','${LstconceptoxProcesod.idprocesoaux}','${LstconceptoxProcesod.codconceptaux}');" ><span class="fa-solid fa-trash me-2"></span>Eliminar</a></div>
                                        </div>
                                      </td>
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
              </div>
              <div class="modal-footer d-flex justify-content-end align-items-center px-0 pb-0 border-0 pt-0">
                    <a class="btn btn-sm btn-primary px-9 my-0 ps-6 pe-6" data-bs-dismiss="modal" aria-label="Close">Cerrar</a>
                    <!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" type="submit">Guardar concepto prom</span></button>-->
              </div>
            </form>
        </div>
      </div>
    </div>

    <div class="modal fade" id="grupoConceptosModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content bg-100">
            <form class="needs-validation" action="javascript:addConceptoAgrup();" novalidate>
              <div class="modal-header border-200 bg-soft p-4">
                 <h5 class="modal-title text-1000 fs-2 lh-sm">Gestión de agrupación de conceptos</h5>
                 <button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>
              </div>
              <div class="modal-body p-4">
                <div class="row g-4">
                    <div id="alertAgrupSuccess" class="mt-2 mb-0 alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                        <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                        <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                        <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <div id="alertAgrupInfo" class="mt-2 mb-0 alert alert-outline-info bg-info bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;" >
                        <span class="fa-solid fa-info text-info fs-0 me-3"></span>
                        <div class="col-11">
                            <strong class="text-black">Información</strong>
                             <p class="mb-0 fw-semi-bold text-1000">Se eliminó exitosamente el elemento <a href="#">Mas información</a></p>
                        </div>
                        <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>

                    <div class="col-sm-6 col-md-5 mt-3">
                          <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Concepto</label>
                          <select name="idconceptoAgrp" id="idconceptoAgrp" class="form-select" required >
                            <option value="" selected >Seleccionar concepto</option>
                            <c:forEach var="listaConAgrp" items="${listaConAgrp}">d
                                <option value="${listaConAgrp.procodcon}" >${listaConAgrp.coodescon}</option>
                            </c:forEach>
                          </select>
                    </div>
                    <div class="col-12">
                        <div class="col-sm-6 col-md-4">
                            <button class="btn btn-phoenix-secondary btn-sm" type="submit" ><span class="fas fa-plus me-2"></span>Add grupo concepto</button>
                        </div>
                    </div>
                    <div class="border-top border-bottom border-200" id="customerOrdersTable" data-list='{"valueNames":["order","total","payment_status","fulfilment_status","delivery_type","date"],"page":4,"pagination":true}'>
                        <div class="table-responsive scrollbar">
                            <table class="table table-sm fs--1 mb-0">
                              <thead>
                                <tr>
                                  <th class="sort white-space-nowrap align-middle ps-0 pe-3 text-uppercase" scope="col" ></th>
                                  <th class="sort align-middle text-center white-space-nowrap ps-3 pe-3 text-uppercase" scope="col" data-sort="payment_status" >ID CONCEPTO</th>
                                  <th class="sort align-middle text-center white-space-nowrap text-start ps-3 pe-3 text-uppercase" scope="col" data-sort="fulfilment_status" >DESCONCEPTO</th>
                                  <th class="sort text-end text-center align-middle ps-3 pe-3 text-uppercase" scope="col"></th>
                                </tr>
                              </thead>
                              <tbody class="list" id="customer-order-table-body-agrup">
                                <c:forEach var="listTblAgrpConc" items="${requestScope.listTblAgrpConc}">
                                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                      <td class="align-middle white-space-nowrap ps-3 pe-3"><a class="fw-semi-bold" href="#!">#</a></td>
                                      <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3"><span class="badge badge-tag me-2 mb-2">${listTblAgrpConc.codconceptaux}</span></td>
                                      <td class="align-middle white-space-nowrap text-center text-700 ps-3 pe-3">${listTblAgrpConc.desconceptaux}</td>

                                      <td class="align-middle text-center white-space-nowrap pe-0 action">
                                        <div class="font-sans-serif btn-reveal-trigger position-static">
                                          <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                          data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                          <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                          <div class="dropdown-menu dropdown-menu-end py-2">

                                            <div class="dropdown-divider"></div>
                                            <a id="dropdownmenutable" class="dropdown-item" onclick="return deleteConceptoAgrup('${listTblAgrpConc.idproceso}','${listTblAgrpConc.codconcepto}','${listTblAgrpConc.codconceptaux}');" ><span class="fa-solid fa-trash me-2"></span>Eliminar</a></div>
                                        </div>
                                      </td>
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
              </div>
              <div class="modal-footer d-flex justify-content-end align-items-center px-0 pb-0 border-0 pt-0">
                    <a class="btn btn-sm btn-primary px-9 my-0 ps-6 pe-6" data-bs-dismiss="modal" aria-label="Close">Cerrar</a>
                    <!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" type="submit">Guardar concepto prom</span></button>-->
              </div>
            </form>
        </div>
      </div>
    </div>
</html>
