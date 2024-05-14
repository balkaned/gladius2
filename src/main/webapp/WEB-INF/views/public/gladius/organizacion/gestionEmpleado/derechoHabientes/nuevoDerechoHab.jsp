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
  </head>

  <jsp:include page="../scriptsEmpl.jsp"></jsp:include>

<script>
function formatearFecha1(){
    var fechaSeleccionada = $('#iexfecnac').val();

    var anio=fechaSeleccionada.substring(0, 4);
    var mes=fechaSeleccionada.substring(5, 7);
    var dia=fechaSeleccionada.substring(8, 10);

    var fechaFormat=dia+"/"+mes+"/"+anio;
    $("#iexfecnac").val(fechaFormat);
}

function formatearFecha2(){
    var fechaSeleccionada = $('#iexfecfin').val();

    var anio=fechaSeleccionada.substring(0, 4);
    var mes=fechaSeleccionada.substring(5, 7);
    var dia=fechaSeleccionada.substring(8, 10);

    var fechaFormat=dia+"/"+mes+"/"+anio;
    $("#iexfecfin").val(fechaFormat);
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

          <div class="content">
              <div class="pb-9">
                <div class="row">
                  <div class="col-12">
                    <div class="row align-items-center justify-content-between g-3 mb-3">
                    </div>
                  </div>
                </div>
                <div class="row g-0 g-md-4 g-xl-6">

                  <jsp:include page="../navsGenericEmpl.jsp"></jsp:include>

                  <div class="col-md-7 col-lg-7 col-xl-8">
                    <div class="lead-details-container">
                      <div class="content2">
                          <nav class="mb-2" aria-label="breadcrumb">
                            <ol class="breadcrumb mb-0">
                              <li class="breadcrumb-item"><a href="#!">Organización</a></li>
                              <li class="breadcrumb-item active">Trabajadores</li>
                              <li class="breadcrumb-item active">Derecho habiente</li>
                            </ol>
                          </nav>
                          <div class="mb-9">
                            <div class="row g-3 mb-4">
                              <div class="col-auto">
                                <h2 id="h2top" class="mb-0">Insertar derecho habiente</h2>
                              </div>
                            </div>
                            <c:if test="${msg!=null}">
                                 <div class="alert alert-danger alert-dismissible " role="alert">
                                    <strong>Error!</strong> ${msg}
                                 </div>
                            </c:if>

                            <div class="row g-3">
                                 <div class="col-xl-12">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-3 mb-0 needs-validation" method="POST" action="insertarDerechoHab" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />

                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de documento</label>
                                                <select class="form-select" name="iextipnroiddep" required >
                                                  <option value="" selected >Seleccionar tipo documento</option>
                                                  <c:forEach var="lovTipdoc" items="${lovTipdoc}">
                                                      <option value="${lovTipdoc.idLov}"   ${lovTipdoc.idLov == requestScope.iextipnroiddep ? 'selected' : ''}  >  ${lovTipdoc.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro doc</label>
                                                <input class="form-control" name="iexnroiddep" maxlength="15" type="text" placeholder="Ingrese número documento" required/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">País emisor</label>
                                                <select class="form-select" name="iexpaisemisor" id="iexpaisemisor" required >
                                                  <option value="" selected >Seleccionar pais emisor</option>
                                                  <c:forEach var="lovPaisEmisor" items="${lovPaisEmisor}">
                                                      <option value="${lovPaisEmisor.idLov}"   ${lovPaisEmisor.idLov == requestScope.iexpaisemisor ? 'selected' : ''}  >  ${lovPaisEmisor.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Apellido paterno</label>
                                                <input class="form-control" name="iexapepatdep" maxlength="100" type="text" placeholder="Reynoso" required />
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Apellido materno</label>
                                                <input class="form-control" name="iexapematdep" maxlength="100" type="text" placeholder="Dominguez" required />
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nombres</label>
                                                <input class="form-control" name="iexnomdep" maxlength="100" type="text" placeholder="Alberto Gabriel" required />
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de nacimiento</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                <input class="form-control datetimepicker" name="iexfecnac" id="iexfecnac" onchange="formatearFecha1();" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required />
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Sexo</label>
                                                <select class="form-select" name="iexsexo" required >
                                                  <option value="" selected >Seleccionar sexo</option>
                                                  <c:forEach var="lovSexo" items="${lovSexo}">
                                                      <option value="${lovSexo.idLov}"   ${lovSexo.idLov == requestScope.iexsexo ? 'selected' : ''}  >  ${lovSexo.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Vínculo</label>
                                                <select class="form-select" name="iextipvinculo" required >
                                                  <option value="" selected >Seleccionar vinculo</option>
                                                  <c:forEach var="lovVincul" items="${lovVincul}">
                                                      <option value="${lovVincul.idLov}"   ${lovVincul.idLov == requestScope.iexsexo ? 'selected' : ''}  >  ${lovVincul.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo documento acredito vínculo</label>
                                                <select class="form-select" name="iextipdocacredit" required >
                                                  <option value="" selected >Seleccionar tipo doc acredito</option>
                                                  <c:forEach var="lovAcredVincul" items="${lovAcredVincul}">
                                                      <option value="${lovAcredVincul.idLov}"   ${lovAcredVincul.idLov == requestScope.iexsexo ? 'selected' : ''}  >  ${lovAcredVincul.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de doc acredito vínculo</label>
                                                <input class="form-control" name="iexnrodocacredit" maxlength="15" type="text" placeholder="Ingrese numero doc acredito" required />
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Mes de concepción</label>
                                                <input class="form-control" name="iexmesconcep" maxlength="50" type="text" placeholder="Ingrese mes" />
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Larga distancia</label>
                                                <select class="form-select" name="iexcodlar">
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovLarDistancia" items="${lovLarDistancia}">
                                                      <option value="${lovLarDistancia.idLov}" ${lovLarDistancia.idLov == requestScope.iexcodlar ? 'selected' : ''}  >+${lovLarDistancia.idLov} - ${lovLarDistancia.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-5">
                                                 <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Número de telefono</label>
                                                 <input class="form-control" name="iexnrotelf" maxlength="50" type="text" placeholder="987 893556" />
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Email</label>
                                                <input class="form-control" name="iexemail" maxlength="50" type="text" placeholder="Ingrese su correo"/>
                                            </div>
                                            <hr/>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de via 1</label>
                                                <select class="form-select" name="iextipvia_dom1" required>
                                                  <option value="" selected >Seleccionar tipo via</option>
                                                  <c:forEach var="lovTipVia" items="${lovTipVia}">
                                                      <option value="${lovTipVia.idLov}"   ${lovTipVia.idLov == requestScope.iextipvia_dom1 ? 'selected' : ''}  >  ${lovTipVia.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nom. via 1</label>
                                                <input class="form-control" name="iexnomvia_dom1" maxlength="20" type="text" placeholder="Ingrese el nombre de la vía" required/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de via 1</label>
                                                <input class="form-control" name="iexnrovia_dom1" maxlength="5" type="text" placeholder="435"/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de dpto 1</label>
                                                <input class="form-control" name="iexdeptin_dom1" maxlength="5" type="text" placeholder="203"/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Interior 1</label>
                                                <input class="form-control" name="iexinterior_dom1" maxlength="5" type="text" placeholder="1"/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Manzana 1</label>
                                                <input class="form-control" name="iexmanzana_dom1" maxlength="5" type="text" placeholder="A"/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de lote 1</label>
                                                <input class="form-control" name="iexlote_dom1" maxlength="5" type="text" placeholder="LT3"/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Km referencia 1</label>
                                                <input class="form-control" name="iexkilometro_dom1" maxlength="5" type="text" placeholder="21"/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de bloque 1</label>
                                                  <input class="form-control" name="iexblock_dom1" maxlength="5" type="text" placeholder="B3"/>
                                            </div>
                                            <div class="col-sm-6 col-md-2">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Etapa 1</label>
                                                <input class="form-control" name="iexetapa_dom1" maxlength="5" type="text" placeholder="E2"/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de zona 1</label>
                                                <select class="form-select" name="iextipzona_dom1" required>
                                                  <option value="" selected >Seleccionar tipo zona</option>
                                                  <c:forEach var="lovTipZona" items="${lovTipZona}">
                                                      <option value="${lovTipZona.idLov}"   ${lovTipZona.idLov == requestScope.iextipzona_dom1 ? 'selected' : ''}  >  ${lovTipZona.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-8">
                                                 <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Referencia 1</label>
                                                 <input class="form-control" name="iexreferencia_dom1" maxlength="200" type="text" placeholder="Ingrese una referencia" required/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">País emisor 1</label>
                                                <select class="form-select" name="iexpaisemisor1" id="iexpaisemisor1" required>
                                                  <option value="" selected >Seleccionar pais</option>
                                                  <c:forEach var="lovPaisEmisor1" items="${lovPaisEmisor}">
                                                      <option value="${lovPaisEmisor1.idLov}"   ${lovPaisEmisor1.idLov == requestScope.iexnacion_origen1 ? 'selected' : ''}  >  ${lovPaisEmisor1.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Departamento 1</label>
                                                <select class="form-select" name="iexdepart_origen1" id="iexdepart_origen1">
                                                  <option value="" selected >Seleccionar departamento</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Provincia 1</label>
                                                <select class="form-select" name="iexprovin_origen1" id="iexprovin_origen1">
                                                  <option value="" selected >Seleccionar provincia</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Distrito 1</label>
                                                <select class="form-select" name="iexubigeo_dom1" id="iexdistri_origen1">
                                                  <option value="" selected >Seleccionar distrito</option>
                                                </select>
                                            </div>
                                            <hr/>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo via 2</label>
                                                <select class="form-select" name="iextipvia_dom2" id="iextipvia_dom2" >
                                                  <option value="" selected >Seleccionar tipo via</option>
                                                  <c:forEach var="lovTipVia2" items="${lovTipVia2}">
                                                      <option value="${lovTipVia2.idLov}"   ${lovTipVia2.idLov == requestScope.iextipvia_dom2 ? 'selected' : ''}  >  ${lovTipVia2.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nom via 2</label>
                                                <input class="form-control" name="iexnomvia_dom2" maxlength="20" type="text" placeholder="Ingrese el nombre de la vía"/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro via 2</label>
                                                <input class="form-control" name="iexnrovia_dom2" maxlength="6" type="text" placeholder="435"/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro dept 2</label>
                                                <input class="form-control" name="iexdeptin_dom2" maxlength="6" type="text" placeholder="203"/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Interior 2</label>
                                                <input class="form-control" name="iexinterior_dom2" maxlength="6" type="text" placeholder="1"/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Manzana 2</label>
                                                <input class="form-control" name="iexmanzana_dom2" maxlength="6" type="text" placeholder="A"/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de lote 2</label>
                                                <input class="form-control" name="iexlote_dom2" maxlength="6" type="text" placeholder="LT3"/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Km referencia 2</label>
                                                <input class="form-control" name="iexkilometro_dom2" maxlength="6" type="text" placeholder="21"/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de bloque 2</label>
                                                <input class="form-control" name="iexblock_dom2" maxlength="6" type="text" placeholder="B3"/>
                                            </div>
                                            <div class="col-sm-6 col-md-2">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Etapa 2</label>
                                                <input class="form-control" name="iexetapa_dom2" maxlength="6" type="text" placeholder="E2"/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de zona 2</label>
                                                <select class="form-select" name="iextipzona_dom2" id="iextipzona_dom2" >
                                                  <option value="" selected >Seleccionar tipo zona</option>
                                                  <c:forEach var="lovTipZona2" items="${lovTipZona2}">
                                                      <option value="${lovTipZona2.idLov}"   ${lovTipZona2.idLov == requestScope.iextipzona_dom2 ? 'selected' : ''}  >  ${lovTipZona2.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-8">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Referencia 2</label>
                                                <input class="form-control" name="iexreferencia_dom2" maxlength="150" type="text" placeholder="Ingrese una referencia"/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">País emisor 2</label>
                                                <select class="form-select" name="iexpaisemisor2" id="iexpaisemisor2">
                                                  <option value="" selected >Seleccionar pais</option>
                                                  <c:forEach var="lovPaisEmisor2" items="${lovPaisEmisor}">
                                                      <option value="${lovPaisEmisor2.idLov}"   ${lovPaisEmisor2.idLov == requestScope.iexnacion_origen2 ? 'selected' : ''}  >  ${lovPaisEmisor2.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Departamento 2</label>
                                                <select class="form-select" name="iexdepart_origen2" id="iexdepart_origen2">
                                                  <option value="" selected >Seleccionar departamento</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Provincia 2</label>
                                                <select class="form-select" name="iexprovin_origen2" id="iexprovin_origen2">
                                                  <option value="" selected >Seleccionar provincia</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Distrito 2</label>
                                                <select class="form-select" name="iexubigeo_dom2" id="iexdistri_origen2">
                                                  <option value="" selected >Seleccionar distrito</option>
                                                </select>
                                            </div>
                                            <hr/>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Domicilio</label>
                                                <select class="form-select" name="iexcenasis" id="iexcenasis">
                                                  <option value="" selected >Seleccionar domicilio</option>
                                                  <option value="1" >Direccion principal</option>
                                                  <option value="2" >Direccion secundaria</option>
                                                </select>
                                            </div>

                                            <div id="alert" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                                                <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                                                <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                                                <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                                            </div>
                                            <div class="col-12 gy-6">
                                                <div class="row g-3 justify-content-end">
                                                  <div class="col-auto">
                                                    <a class="btn btn-phoenix-primary" href="derechoHab@${idTrab}">Cancel</a>
                                                  </div>
                                                  <div class="col-auto">
                                                    <button class="btn btn-primary" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar derecho hab</button>
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