<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jean Quiroz
    Email	   : jeanp.quiroz@gmail.com
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

    $(document).ready(function(){
          var fechacargada=$("#iexfecnachidden").val();
          $("#iexfecnac").val(fechacargada);
    });
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
                                <h2 id="h2top" class="mb-0">Ver detalle derecho habiente</h2>
                              </div>
                            </div>
                            <c:if test="${msg!=null}">
                                 <div class="alert alert-danger alert-dismissible " role="alert">
                                    <strong>Error!</strong> ${msg}
                                 </div>
                            </c:if>

                            <div class="row g-5">
                                 <div class="col-xl-12">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-4 mb-0 needs-validation" method="POST" action="modificarDerechoHab" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />

                                            <div class="col-sm-6 col-md-2">
                                            	<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">ID</label>
                                            	<input class="form-control" name="iexcoddepdis" type="text" value="${iexcoddep}" disabled />
                                                <input class="form-control" name="iexcoddep" type="hidden" value="${iexcoddep}" />
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Tipo de Documento</label>
                                                <select class="form-select" name="iextipnroiddep" required disabled >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipdoc" items="${lovTipdoc}">
                                                      <option value="${lovTipdoc.idLov}" ${lovTipdoc.idLov == requestScope.derhabx.iextipnroiddep ? 'selected' : ''} >  ${lovTipdoc.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Nro Doc</label>
                                                <input class="form-control" name="iexnroiddep" maxlength="15" type="text" placeholder="#" value="${requestScope.derhabx.iexnroiddep}" required disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Pais Emisor</label>
                                                <select class="form-select" name="iexpaisemisor" id="iexpaisemisor" required disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovPaisEmisor" items="${lovPaisEmisor}">
                                                      <option value="${lovPaisEmisor.idLov}" ${lovPaisEmisor.idLov == requestScope.derhabx.iexpaisemisor ? 'selected' : ''} >  ${lovPaisEmisor.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Apellido Paterno</label>
                                                <input class="form-control" name="iexapepatdep" maxlength="100" type="text" value="${requestScope.derhabx.iexapepatdep}" placeholder="Reynoso" required disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Apellido Materno</label>
                                                <input class="form-control" name="iexapematdep" maxlength="100" type="text" value="${requestScope.derhabx.iexapematdep}" placeholder="Dominguez" required disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Nombres</label>
                                                <input class="form-control" name="iexnomdep" maxlength="100" type="text" value="${requestScope.derhabx.iexnomdep}" placeholder="Alberto Gabriel" required disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Fecha de Nacimiento</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                <input class="form-control datetimepicker" name="iexfecnac" id="iexfecnac" onchange="formatearFecha1();" value="${requestScope.derhabx.iexfecnac}" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required disabled/>
                                                <input class="form-control" id="iexfecnachidden" type="hidden" value="${requestScope.derhabx.iexfecnac}" />
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Sexo</label>
                                                <select class="form-select" name="iexsexo" required disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovSexo" items="${lovSexo}">
                                                      <option value="${lovSexo.idLov}" ${lovSexo.idLov == requestScope.derhabx.iexsexo? 'selected' : ''} >  ${lovSexo.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Vinculo</label>
                                                <select class="form-select" name="iextipvinculo" required disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovVincul" items="${lovVincul}">
                                                      <option value="${lovVincul.idLov}" ${lovVincul.idLov == requestScope.derhabx.iextipvinculo? 'selected' : ''} >  ${lovVincul.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Tipo Documento Acredito Vinculo</label>
                                                <select class="form-select" name="iextipdocacredit" required disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovAcredVincul" items="${lovAcredVincul}">
                                                      <option value="${lovAcredVincul.idLov}" ${lovAcredVincul.idLov == requestScope.derhabx.iextipdocacredit? 'selected' : ''} >  ${lovAcredVincul.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Nro de Doc Acredito Vinculo</label>
                                                <input class="form-control" name="iexnrodocacredit" maxlength="15" type="text" value="${requestScope.derhabx.iexnrodocacredit}" required placeholder="#" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Mes de Concepcion</label>
                                                <input class="form-control" name="iexmesconcep" maxlength="50" type="text" value="${requestScope.derhabx.iexmesconcep}" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Larga distancia</label>
                                                <select class="form-select" name="iexcodlar" disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovLarDistancia" items="${lovLarDistancia}">
                                                      <option value="${lovLarDistancia.idLov}" ${lovLarDistancia.idLov == requestScope.derhabx.iexcodlar? 'selected' : ''} >+${lovLarDistancia.idLov} - ${lovLarDistancia.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                 <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de Telefono</label>
                                                 <input class="form-control" name="iexnrotelf" maxlength="50" type="text" value="${requestScope.derhabx.iexnrotelf}" placeholder="+51 987 893556" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Email</label>
                                                <input class="form-control" name="iexemail" maxlength="50" type="text" value="${requestScope.derhabx.iexemail}" placeholder="usuario@gmail.com" disabled/>
                                            </div>
                                            <hr/>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Tipo de Via 1</label>
                                                <select class="form-select" name="iextipvia_dom1" required disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipVia" items="${lovTipVia}">
                                                      <option value="${lovTipVia.idLov}"  ${lovTipVia.idLov == requestScope.derhabx.iextipvia_dom1 ? 'selected' : ''} >  ${lovTipVia.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nom. Via 1</label>
                                                <input class="form-control" name="iexnomvia_dom1" maxlength="20" type="text" value="${requestScope.derhabx.iexnomvia_dom1}" required disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de Via 1</label>
                                                <input class="form-control" name="iexnrovia_dom1" maxlength="5" type="text" value="${requestScope.derhabx.iexnrovia_dom1}" placeholder="435" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de Depto 1</label>
                                                <input class="form-control" name="iexdeptin_dom1" maxlength="5" type="text" value="${requestScope.derhabx.iexdeptin_dom1}" placeholder="143" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Interior 1</label>
                                                <input class="form-control" name="iexinterior_dom1" maxlength="5" type="text" value="${requestScope.derhabx.iexinterior_dom1}" placeholder="1" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Manzana 1</label>
                                                <input class="form-control" name="iexmanzana_dom1" maxlength="5" type="text" value="${requestScope.derhabx.iexmanzana_dom1}" placeholder="A" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de Lote 1</label>
                                                <input class="form-control" name="iexlote_dom1" maxlength="5" type="text" value="${requestScope.derhabx.iexlote_dom1}" placeholder="LT3" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Km Referencia 1</label>
                                                <input class="form-control" name="iexkilometro_dom1" maxlength="5" type="text" value="${requestScope.derhabx.iexkilometro_dom1}" placeholder="21" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de Bloque 1</label>
                                                  <input class="form-control" name="iexblock_dom1" maxlength="5" type="text" value="${requestScope.derhabx.iexblock_dom1}" placeholder="B3" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Etapa Del Domicilio 1</label>
                                                <input class="form-control" name="iexetapa_dom1" maxlength="5" type="text" value="${requestScope.derhabx.iexetapa_dom1}" placeholder="E2" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Tipo de zona dom 1</label>
                                                <select class="form-select" name="iextipzona_dom1" required disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipZona" items="${lovTipZona}">
                                                      <option value="${lovTipZona.idLov}" ${lovTipZona.idLov == requestScope.derhabx.iextipzona_dom1 ? 'selected' : ''} >  ${lovTipZona.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-8">
                                                 <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Referencia dom 1</label>
                                                 <input class="form-control" name="iexreferencia_dom1" maxlength="200" type="text" value="${requestScope.derhabx.iexreferencia_dom1}" required disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Pais Emisor 1 [TT26]</label>
                                                <select class="form-select" name="iexpaisemisor1" id="iexpaisemisor1" required disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovPaisEmisor1" items="${lovPaisEmisor}">
                                                      <option value="${lovPaisEmisor1.idLov}" ${lovPaisEmisor1.idLov == requestScope.derhabx.iexnacion_origen1 ? 'selected' : ''} >  ${lovPaisEmisor1.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Departamento 1</label>
                                                <select class="form-select" name="iexdepart_origen1" id="iexdepart_origen1" disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovDept_origen1" items="${requestScope.lovDept_origen1}">
                                                      <option value="${lovDept_origen1.idLov}" ${lovDept_origen1.idLov == requestScope.derhabx.iexdepart_origen1 ? 'selected' : ''} >${lovDept_origen1.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Provincia 1</label>
                                                <select class="form-select" name="iexprovin_origen1" id="iexprovin_origen1" disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovProvin_origen1" items="${requestScope.lovProvin_origen1}">
                                                      <option value="${lovProvin_origen1.idLov}" ${lovProvin_origen1.idLov == requestScope.derhabx.iexprovin_origen1 ? 'selected' : ''} >${lovProvin_origen1.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Distrito 1</label>
                                                <select class="form-select" name="iexubigeo_dom1" id="iexdistri_origen1" disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovDist_origen1" items="${requestScope.lovDist_origen1}">
                                                      <option value="${lovDist_origen1.idLov}"  ${lovDist_origen1.idLov == requestScope.derhabx.iexubigeo_dom1 ? 'selected' : ''}   >${lovDist_origen1.desLov}</option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <hr/>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo Via 2</label>
                                                <select class="form-select" name="iextipvia_dom2" id="iextipvia_dom2" disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipVia2" items="${lovTipVia2}">
                                                      <option value="${lovTipVia2.idLov}" ${lovTipVia2.idLov == requestScope.derhabx.iextipvia_dom2 ? 'selected' : ''} >  ${lovTipVia2.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nom Via 2</label>
                                                <input class="form-control" name="iexnomvia_dom2" maxlength="20" value="${requestScope.derhabx.iexnomvia_dom2}" type="text" disabled />
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro Via 2</label>
                                                <input class="form-control" name="iexnrovia_dom2" maxlength="6" type="text" value="${requestScope.derhabx.iexnrovia_dom2}" placeholder="435" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro Dept 2</label>
                                                <input class="form-control" name="iexdeptin_dom2" maxlength="6" type="text" placeholder="143" value="${requestScope.derhabx.iexdeptin_dom2}" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Interior 2</label>
                                                <input class="form-control" name="iexinterior_dom2" maxlength="6" type="text" value="${requestScope.derhabx.iexinterior_dom2}" placeholder="1" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Manzana Domicilio 2</label>
                                                <input class="form-control" name="iexmanzana_dom2" maxlength="6" type="text" value="${requestScope.derhabx.iexmanzana_dom2}" placeholder="A" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de Lote 2</label>
                                                <input class="form-control" name="iexlote_dom2" maxlength="6" type="text" value="${requestScope.derhabx.iexlote_dom2}" placeholder="LT3" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Kilometro de Referencia 2</label>
                                                <input class="form-control" name="iexkilometro_dom2" maxlength="6" type="text" value="${requestScope.derhabx.iexkilometro_dom2}" placeholder="21" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro de Bloque 2</label>
                                                <input class="form-control" name="iexblock_dom2" maxlength="6" type="text" value="${requestScope.derhabx.iexblock_dom2}" placeholder="B3" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Etapa Domicilio 2</label>
                                                <input class="form-control" name="iexetapa_dom2" maxlength="6" type="text" value="${requestScope.derhabx.iexetapa_dom2}" placeholder="E2" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de zona dom 2</label>
                                                <select class="form-select" name="iextipzona_dom2" id="iextipzona_dom2" disabled >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipZona2" items="${lovTipZona2}">
                                                      <option value="${lovTipZona2.idLov}" ${lovTipZona2.idLov == requestScope.derhabx.iextipzona_dom2 ? 'selected' : ''} >  ${lovTipZona2.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-8">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Referencia Dom 2</label>
                                                <input class="form-control" name="iexreferencia_dom2" maxlength="150" type="text" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Pais Emisor 2 [TT26]</label>
                                                <select class="form-select" name="iexpaisemisor2" id="iexpaisemisor2" required disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovPaisEmisor2" items="${lovPaisEmisor}">
                                                      <option value="${lovPaisEmisor2.idLov}" ${lovPaisEmisor2.idLov == requestScope.derhabx.iexnacion_origen2 ? 'selected' : ''} >  ${lovPaisEmisor2.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Departamento 2</label>
                                                <select class="form-select" name="iexdepart_origen2" id="iexdepart_origen2" disabled>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovDept_origen2" items="${requestScope.lovDept_origen2}">
                                                        <option value="${lovDept_origen2.idLov}"     ${lovDept_origen2.idLov == requestScope.derhabx.iexdepart_origen2 ? 'selected' : ''}     >${lovDept_origen2.desLov}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Provincia 2</label>
                                                <select class="form-select" name="iexprovin_origen2" id="iexprovin_origen2" disabled>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovProvin_origen2" items="${requestScope.lovProvin_origen2}">
                                                          <option value="${lovProvin_origen2.idLov}"  ${lovProvin_origen2.idLov  == requestScope.derhabx.iexprovin_origen2 ? 'selected' : ''}       >${lovProvin_origen2.desLov}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Distrito 2</label>
                                                <select class="form-select" name="iexubigeo_dom2" id="iexdistri_origen2" disabled>
                                                    <option value="" selected >Seleccionar</option>
                                                    <c:forEach var="lovDist_origen2" items="${requestScope.lovDist_origen2}">
                                                        <option value="${lovDist_origen2.idLov}"  ${lovDist_origen2.idLov == requestScope.derhabx.iexubigeo_dom2 ? 'selected' : ''}   >${lovDist_origen2.desLov}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <hr/>
                                            <div class="col-sm-6 col-md-6">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Domicilio</label>
                                                <select class="form-select" name="iexcenasis" id="iexcenasis" disabled>
                                                  <option value="" selected >Seleccionar</option>
                                                  <option value="1" ${requestScope.derhabx.iexcenasis=='1' ? 'selected' : ''} >Direccion Principal</option>
                                                  <option value="2" ${requestScope.derhabx.iexcenasis=='2' ? 'selected' : ''} >Direccion Secundaria</option>
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
                                                    <button class="btn btn-primary disabled" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar DerechoHab</button>
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