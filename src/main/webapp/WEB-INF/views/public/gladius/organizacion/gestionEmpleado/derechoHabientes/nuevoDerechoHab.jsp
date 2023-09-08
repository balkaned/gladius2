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
                              <li class="breadcrumb-item"><a href="#!">Page</a></li>
                              <li class="breadcrumb-item active">Default</li>
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

                            <div class="row g-5">
                                 <div class="col-xl-12">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-4 mb-0 needs-validation" method="POST" action="insertarDerechoHab" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                                            <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <select class="form-select" name="iextipnroiddep" required >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipdoc" items="${lovTipdoc}">
                                                      <option value="${lovTipdoc.idLov}"   ${lovTipdoc.idLov == requestScope.iextipnroiddep ? 'selected' : ''}  >  ${lovTipdoc.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                                <label>Tipo de Documento(*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexnroiddep" maxlength="15" type="text" required />
                                                  <label>Nro Doc (*)</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexpaisemisor" id="iexpaisemisor" required >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovPaisEmisor" items="${lovPaisEmisor}">
                                                      <option value="${lovPaisEmisor.idLov}"   ${lovPaisEmisor.idLov == requestScope.iexpaisemisor ? 'selected' : ''}  >  ${lovPaisEmisor.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                                <label>Pais Emisor(*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexapepatdep" maxlength="100" type="text" required />
                                                  <label>Apellido Paterno (*)</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexapematdep" maxlength="100" type="text" required />
                                                  <label>Apellido Materno (*)</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexnomdep" maxlength="100" type="text" required />
                                                  <label>Nombres (*)</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="flatpickr-input-container">
                                                <div class="form-floating">
                                                  <input class="form-control datetimepicker" name="iexfecnac" id="iexfecnac" onchange="formatearFecha1();" type="text" placeholder="end date" data-options='{"disableMobile":true}' required />
                                                  <label class="ps-6" for="floatingInputStartDate">Fecha de Nacimiento (*)</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                                </div>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexsexo" required >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovSexo" items="${lovSexo}">
                                                      <option value="${lovSexo.idLov}"   ${lovSexo.idLov == requestScope.iexsexo ? 'selected' : ''}  >  ${lovSexo.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                                <label>Sexo (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <select class="form-select" name="iextipvinculo" required >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovVincul" items="${lovVincul}">
                                                      <option value="${lovVincul.idLov}"   ${lovVincul.idLov == requestScope.iexsexo ? 'selected' : ''}  >  ${lovVincul.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                                <label>Vinculo (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <select class="form-select" name="iextipdocacredit" required >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovAcredVincul" items="${lovAcredVincul}">
                                                      <option value="${lovAcredVincul.idLov}"   ${lovAcredVincul.idLov == requestScope.iexsexo ? 'selected' : ''}  >  ${lovAcredVincul.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                                <label>Tipo Documento Acredito Vinculo (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexnrodocacredit" maxlength="15" type="text" required />
                                                  <label>Nro de Doc Acredito Vinculo (*)</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexmesconcep" maxlength="50" type="text" />
                                                  <label>Mes de Concepcion</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexcodlar">
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovLarDistancia" items="${lovLarDistancia}">
                                                      <option value="${lovLarDistancia.idLov}"   ${lovLarDistancia.idLov == requestScope.iexcodlar ? 'selected' : ''}  >  ${lovLarDistancia.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                                <label>Larga Distancia</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexnrotelf" maxlength="50" type="text" />
                                                  <label>Nro de Telefono</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexemail" maxlength="50" type="text" />
                                                  <label>Email</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <select class="form-select" name="iextipvia_dom1" required>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipVia" items="${lovTipVia}">
                                                      <option value="${lovTipVia.idLov}"   ${lovTipVia.idLov == requestScope.iextipvia_dom1 ? 'selected' : ''}  >  ${lovTipVia.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                                <label>Tipo de Via (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexnomvia_dom1" maxlength="150" type="text" required/>
                                                  <label>Nom. Via</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexnrovia_dom1" maxlength="5" type="text" />
                                                  <label>Nro de Via</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexdeptin_dom1" maxlength="5" type="text" />
                                                  <label>Nro de Depto</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexinterior_dom1" maxlength="5" type="text" />
                                                  <label>Interior Domicilio</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexmanzana_dom1" maxlength="5" type="text" />
                                                  <label>Manzana</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexlote_dom1" maxlength="5" type="text" />
                                                  <label>Nro de Lote</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexkilometro_dom1" maxlength="5" type="text" />
                                                  <label>Km Referencia</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexblock_dom1" maxlength="5" type="text" />
                                                  <label>Nro de Bloqueo</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexetapa_dom1" maxlength="5" type="text" />
                                                  <label>Etapa Del Domicilio</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <select class="form-select" name="iextipzona_dom1" required>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipZona" items="${lovTipZona}">
                                                      <option value="${lovTipZona.idLov}"   ${lovTipZona.idLov == requestScope.iextipzona_dom1 ? 'selected' : ''}  >  ${lovTipZona.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                                <label>Tipo de zona dom1 (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-8">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexreferencia_dom1" maxlength="200" type="text" required/>
                                                  <label>Referencia dom1 (*)</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexpaisemisor1" id="iexpaisemisor1" required>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovPaisEmisor1" items="${lovPaisEmisor}">
                                                      <option value="${lovPaisEmisor1.idLov}"   ${lovPaisEmisor1.idLov == requestScope.iexnacion_origen1 ? 'selected' : ''}  >  ${lovPaisEmisor1.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                                <label>Pais Emisor (*) [TT26]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexdepart_origen1" id="iexdepart_origen1">
                                                  <option value="" selected >Seleccionar</option>
                                                </select>
                                                <label>Departamento</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexprovin_origen1" id="iexprovin_origen1">
                                                  <option value="" selected >Seleccionar</option>
                                                </select>
                                                <label>Provincia</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexubigeo_dom1" id="iexubigeo_dom1">
                                                  <option value="" selected >Seleccionar</option>
                                                </select>
                                                <label>Ubigeo</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <select class="form-select" name="iextipvia_dom2" id="iextipvia_dom2" >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipVia2" items="${lovTipVia2}">
                                                      <option value="${lovTipVia2.idLov}"   ${lovTipVia2.idLov == requestScope.iextipvia_dom2 ? 'selected' : ''}  >  ${lovTipVia2.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                                <label>Tipo Via 2</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexnomvia_dom2" maxlength="100" type="text" />
                                                  <label>Nom Via 2</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexnrovia_dom2" maxlength="6" type="text" />
                                                  <label>Nro Via 2</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexdeptin_dom2" maxlength="6" type="text" />
                                                  <label>Nro Dept 2</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexinterior_dom2" maxlength="6" type="text" />
                                                  <label>Interior 2</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexmanzana_dom2" maxlength="6" type="text" />
                                                  <label>Manzana Domicilio 2</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexlote_dom2" maxlength="6" type="text" />
                                                  <label>Nro de Lote 2</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexkilometro_dom2" maxlength="6" type="text" />
                                                  <label>Kilometro de Referencia 2</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexblock_dom2" maxlength="6" type="text" />
                                                  <label>Nro de Bloque 2</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexetapa_dom2" maxlength="6" type="text" />
                                                  <label>Etapa Domicilio 2</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <select class="form-select" name="iextipzona_dom2" id="iextipzona_dom2" >
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovTipZona2" items="${lovTipZona2}">
                                                      <option value="${lovTipZona2.idLov}"   ${lovTipZona2.idLov == requestScope.iextipzona_dom2 ? 'selected' : ''}  >  ${lovTipZona2.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                                <label>Tipo de zona dom 2</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-8">
                                                <div class="form-floating">
                                                  <input class="form-control" name="iexreferencia_dom2" maxlength="150" type="text" />
                                                  <label>Referencia Dom 2</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexpaisemisor2" id="iexpaisemisor2" required>
                                                  <option value="" selected >Seleccionar</option>
                                                  <c:forEach var="lovPaisEmisor2" items="${lovPaisEmisor}">
                                                      <option value="${lovPaisEmisor2.idLov}"   ${lovPaisEmisor2.idLov == requestScope.iexnacion_origen2 ? 'selected' : ''}  >  ${lovPaisEmisor2.desLov} </option>
                                                  </c:forEach>
                                                </select>
                                                <label>Pais Emisor (*) [TT26]</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexdepart_origen2" id="iexdepart_origen2">
                                                  <option value="" selected >Seleccionar</option>
                                                </select>
                                                <label>Departamento</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexprovin_origen2" id="iexprovin_origen2">
                                                  <option value="" selected >Seleccionar</option>
                                                </select>
                                                <label>Provincia</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexubigeo_dom2" id="iexubigeo_dom2">
                                                  <option value="" selected >Seleccionar</option>
                                                </select>
                                                <label>Ubigeo 2</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexcenasis" id="iexcenasis">
                                                  <option value="" selected >Seleccionar</option>
                                                  <option value="1" >Direccion Principal</option>
                                                  <option value="2" >Direccion Secundaria</option>
                                                </select>
                                                <label>Domicilio</label>
                                              </div>
                                            </div>

                                            <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                                Se grabó exitosamente los cambios.
                                            </div>
                                            <div class="col-12 gy-6">
                                                <div class="row g-3 justify-content-end">
                                                  <div class="col-auto">
                                                    <a class="btn btn-phoenix-primary" href="derechoHab@${idTrab}">Cancel</a>
                                                    <button class="btn btn-primary" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar DerechoHab</button>
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
                                                    <div class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
                                                        <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="button" data-bs-dismiss="modal" >Cancel</button>
                                                        <button class="btn btn-sm btn-primary px-9 fs--2 my-0" onclick="mostrarAlert();" type="submit" data-bs-dismiss="modal" >Confirmar</button>
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