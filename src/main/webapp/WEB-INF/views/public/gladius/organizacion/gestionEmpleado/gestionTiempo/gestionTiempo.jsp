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
    <script src="resources/assets/js/gladius/scriptsEmpl.js"></script>
    <script src="resources/assets/js/gladius/detallePlanillaGeneral.js"></script>
      <script src="resources/assets/js/gladius/boletasPlanillas.js"></script>
      <script src="resources/assets/js/gladius/asistenciasPlanillas.js"></script>
      <script src="resources/assets/js/gladius/otrosDatosPlanilla.js"></script>
  </head>

<script>
    function enviaForm(variable){
        if(variable==1){
            document.getElementById("accion").value="QRY";
        }else if(variable==2){
            document.getElementById("accion").value="DELMAS";
        }else if(variable==3){
            document.getElementById("accion").value="PROMAS";
        }else if(variable==4){
            document.getElementById("accion").value="MARCAS";
        }else if(variable==5){
            document.getElementById("accion").value="CALIFICA";
        }else if(variable==6){
            document.getElementById ("GestionTiempos").encoding="multipart/form-data";
            document.getElementById("accion").value="UPXLSMAR";
        }
        document.getElementById("GestionTiempos").submit();
    }

    function updturnpForm(desfec,  turno){
        document.getElementById("iexcodfec").value=turno;
        document.getElementById("desfecdia").value=desfec;
        document.getElementById("iexcodturno").value=document.getElementById(turno).value;
        document.getElementById("accion").value="UPDTURNO";
        //alert("Fecha :"+document.getElementById("iexcodfec").value+" , turno : "+ document.getElementById("iexcodturno").value);
        document.getElementById("GestionTiempos").submit();
    }

    function updtipturno(){
        document.getElementById("accion").value="UPDTIPTURNO";
        //alert("Fecha :"+document.getElementById("iexcodfec").value+" , turno : "+ document.getElementById("iexcodturno").value);
        confirm("Esta seguro de actualizar los horarios?");
        document.getElementById("GestionTiempos").submit();
    }

    function jumpTo(idselect) {
       var x = document.getElementById(idselect);
       if (x.value === "1") {
            document.getElementById("iexlunes").disabled = false;
            document.getElementById("iexmartes").disabled = false;
            document.getElementById("iexmiercoles").disabled = false;
            document.getElementById("iexjueves").disabled = false;
            document.getElementById("iexviernes").disabled = false;
            document.getElementById("iexsabado").disabled = false;
            document.getElementById("iexdomingo").disabled = false;
            document.getElementById("iexturlun").disabled = false;
            document.getElementById("iexturmar").disabled = false;
            document.getElementById("iexturmie").disabled = false;
            document.getElementById("iexturjue").disabled = false;
            document.getElementById("iexturvie").disabled = false;
            document.getElementById("iextursab").disabled = false;
            document.getElementById("iexturdom").disabled = false;
       }else{
            document.getElementById("iexlunes").disabled = true;
            document.getElementById("iexmartes").disabled = true;
            document.getElementById("iexmiercoles").disabled = true;
            document.getElementById("iexjueves").disabled = true;
            document.getElementById("iexviernes").disabled = true;
            document.getElementById("iexsabado").disabled = true;
            document.getElementById("iexdomingo").disabled = true;
            document.getElementById("iexturlun").disabled = true;
            document.getElementById("iexturmar").disabled = true;
            document.getElementById("iexturmie").disabled = true;
            document.getElementById("iexturjue").disabled = true;
            document.getElementById("iexturvie").disabled = true;
            document.getElementById("iextursab").disabled = true;
            document.getElementById("iexturdom").disabled = true;
       }
    }

    function program_tur_col(turno, dia){
        alert("Turno:"+turno.value+" , Dia:"+dia);
        document.getElementById("accion").value="UPDALLCOL";
        document.getElementById("parcodturno").value=turno.value;
        document.getElementById("pardiades").value=dia;
        document.getElementById("GestionTiempos").submit();
    }

    function program_tur_row(turno, fecini,  fecfin){
         alert("Turno :"+turno.value+" , Fecini:"+fecini+", Fecfin:"+fecfin);
         document.getElementById("accion").value="UPDALLROW";
         document.getElementById("parfecini").value=fecini;
         document.getElementById("parfecfin").value=fecfin;
         document.getElementById("parcodturno").value=turno.value;
         document.getElementById("GestionTiempos").submit();
    }
</script>

<style>
    .popover {
       /*width: 470px !important;*/
       max-width:325px !important;
       height: 420px !important;
       overflow-y: auto;
       border-radius:12px 0px 0px 12px;
       /*overflow-x: none;*/
    }

    .popover-body{
        padding:0px;
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

          <div class="content bg-100">
              <div class="pb-9">
                <div class="row mt-0 mb-1">
                    <div class="col-12">
                      <div class="row align-items-center justify-content-between">
                        <div class="col-12 col-md-auto">
                          <h2 class="mb-0"></h2>
                        </div>
                        <div class="col-12 col-md-auto">
                          <div class="d-flex">
                            <div class="flex-1 d-md-none">
                              <button class="btn px-3 btn-phoenix-secondary text-700 me-2" data-phoenix-toggle="offcanvas" data-phoenix-target="#productFilterColumn"><span class="fa-solid fa-bars"></span></button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                </div>

                <div class="row g-0 g-md-4 g-xl-6">
                  <jsp:include page="../navsGenericEmpl.jsp"></jsp:include>

                  <div class="col-md-7 col-lg-7 col-xl-8">
                    <div class="lead-details-container">
                      <div class="">
                          <div class="mb-0">
                            <div>
                              <div class="col-12 mt-4">
                                <h2 class="mb-0">Gestión de tiempos</h2>
                              </div>
                              <div class="col-12 mt-4 mb-2 d-flex justify-content-end">
                                  <a class="btn btn-phoenix-secondary btn-sm" href="detalleEmpl@${idTrab}"><span class="fa-solid fa-reply me-2"></span>Atras</a>
                                  <!--<a class="btn btn-phoenix-secondary ms-1 btn-sm" href="#"><span class="fa-solid fa-plus me-2"></span>Add tiempos</a>-->
                              </div>
                            </div>

                            <div class="row g-3">
                                 <div class="col-xl-12">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-3 mb-0 needs-validation" id="GestionTiempos" method="POST" action="grabarTurno@${idTrab}" novalidate >
                                        <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                        <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                                        <input type="hidden" name="accion"  id="accion"   >
                                        <input type="hidden" name="iexcodfec"  id="iexcodfec"   >
                                        <input type="hidden" name="desfecdia"  id="desfecdia"   >
                                        <input type="hidden" name="iexcodturno"  id="iexcodturno"  >
                                        <input type="hidden" name="parfecini"  id="parfecini"   >
                                        <input type="hidden" name="parfecfin"  id="parfecfin"   >
                                        <input type="hidden" name="parcodturno"  id="parcodturno"   >
                                        <input type="hidden" name="pardiades"  id="pardiades"   >

                                        <table>
                                             <tr>
                                                 <div class="col-md-12">
                                                    <input type="checkbox" name="iexctlasipag" id="iexctlasipag"  value="1" class="form-check-input" ${requestScope.xempxturno.iexctlasipag =='1' ? 'checked=true' : ''}  >
                                                    <label class="form-check-label ms-2">Control asistenca para pago?</label>
                                                 </div>
                                             </tr>
                                             <tr>
                                                <div class="col-sm-6 col-md-4">
                                                	  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo de turno</label>
                                                	  <select id="slc_tipturno" name="slc_tipturno" class="form-select" onchange="jumpTo('slc_tipturno')" >
                                                		 <option value="-1" >Seleccionar tipo turno</option>
                                                         <c:forEach  var="lovTipTurno" items="${lovTipTurno}">
                                                           <option value="${lovTipTurno.idLov}" ${lovTipTurno.idLov == xempxturno.iextipturno ? 'selected' : ''}>${lovTipTurno.desLov}</option>
                                                         </c:forEach>
                                                	  </select>
                                                </div>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="col-sm-6 col-md-7">
                                                        <table style="display: block;overflow-x: auto;white-space: nowrap;">
                                                            <tr>
                                                                <td class="text-body fs--1" > Lun <input type="checkbox" class="form-check-input text-body fs-9" name="iexlunes" id="iexlunes" value="1" class="flat"  ${requestScope.xempxturno.iexlunes =='1' ? 'checked=true' : ''}   ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} ></td>
                                                                <td class="text-body fs--1"> Mar <input type="checkbox" class="form-check-input text-body fs-9" name="iexmartes" id="iexmartes" value="1" class="flat" ${requestScope.xempxturno.iexmartes =='1' ? 'checked=true' : ''} ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'}  ></td>
                                                                <td class="text-body fs--1"> Mie <input type="checkbox" class="form-check-input text-body fs-9" name="iexmiercoles" id="iexmiercoles" value="1" class="flat"  ${requestScope.xempxturno.iexmiercoles =='1' ? 'checked=true' : ''} ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'}></td>
                                                                <td class="text-body fs--1"> Jue <input type="checkbox" class="form-check-input text-body fs-9" name="iexjueves" id="iexjueves" value="1" class="flat" ${requestScope.xempxturno.iexjueves =='1' ? 'checked=true' : ''} ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'}  ></td>
                                                                <td class="text-body fs--1"> Vie <input type="checkbox" class="form-check-input text-body fs-9" name="iexviernes" id="iexviernes" value="1" class="flat" ${requestScope.xempxturno.iexviernes =='1' ? 'checked=true' : ''} ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} ></td>
                                                                <td class="text-body fs--1"> Sab <input type="checkbox" class="form-check-input text-body fs-9" name="iexsabado" id="iexsabado" value="1" class="flat"  ${requestScope.xempxturno.iexsabado =='1' ? 'checked=true' : ''} ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} > </td>
                                                                <td class="text-body fs--1"> Dom <input type="checkbox" class="form-check-input text-body fs-9" name="iexdomingo" id="iexdomingo" value="1" class="flat"  ${requestScope.xempxturno.iexdomingo =='1' ? 'checked=true' : ''} ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'}  ></td>
                                                           </tr>
                                                           <tr>
                                                               <td>
                                                                    <select name="iexturlun" style="width:150px; font-size:11px;" id="iexturlun" class="form-select form-select-sm" ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} >
                                                                       <option value="-1">-- --</option>
                                                                       <c:forEach  var="LstTurno" items="${LstTurno}">
                                                                            <option value=${LstTurno.iexcodturno} ${LstTurno.iexcodturno == xempxturno.iexturlun ? 'selected' : ''}   > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                       </c:forEach>
                                                                    </select>
                                                               </td>
                                                               <td>
                                                                    <select name="iexturmar" style="width:150px; font-size:11px;" id="iexturmar" class="form-select form-select-sm" ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} >
                                                                       <option value="-1">-- --</option>
                                                                       <c:forEach  var="LstTurno" items="${LstTurno}">
                                                                            <option value=${LstTurno.iexcodturno}  ${LstTurno.iexcodturno == xempxturno.iexturmar ? 'selected' : ''}  > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                       </c:forEach>
                                                                    </select>
                                                               </td>
                                                               <td>
                                                                    <select name="iexturmie" style="width:150px; font-size:11px;" id="iexturmie" class="form-select form-select-sm" ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'}>
                                                                       <option value="-1">-- --</option>
                                                                       <c:forEach  var="LstTurno" items="${LstTurno}">
                                                                            <option value=${LstTurno.iexcodturno}  ${LstTurno.iexcodturno == xempxturno.iexturmie ? 'selected' : ''}  > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                       </c:forEach>
                                                                    </select>
                                                               </td>
                                                               <td>
                                                                    <select name="iexturjue" style="width:150px; font-size:11px;" id="iexturjue" class="form-select form-select-sm" ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} >
                                                                       <option value="-1">-- --</option>
                                                                       <c:forEach  var="LstTurno" items="${LstTurno}">
                                                                            <option value=${LstTurno.iexcodturno}  ${LstTurno.iexcodturno == xempxturno.iexturjue ? 'selected' : ''}  > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                       </c:forEach>
                                                                    </select>
                                                               </td>
                                                               <td>
                                                                    <select name="iexturvie" style="width:150px; font-size:11px;" id="iexturvie" class="form-select form-select-sm" ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'}  >
                                                                       <option value="-1">-- --</option>
                                                                       <c:forEach  var="LstTurno" items="${LstTurno}">
                                                                            <option value=${LstTurno.iexcodturno}  ${LstTurno.iexcodturno == xempxturno.iexturvie ? 'selected' : ''}  > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                       </c:forEach>
                                                                    </select>
                                                               </td>
                                                               <td>
                                                                    <select name="iextursab" style="width:150px; font-size:11px;" id="iextursab" class="form-select form-select-sm" ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} >
                                                                       <option value="-1">-- --</option>
                                                                       <c:forEach  var="LstTurno" items="${LstTurno}"  >
                                                                            <option value=${LstTurno.iexcodturno}  ${LstTurno.iexcodturno == xempxturno.iextursab ? 'selected' : ''}  > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                       </c:forEach>
                                                                    </select>
                                                               </td>
                                                               <td class="pe-10">
                                                                    <select name="iexturdom" style="width:150px; font-size:11px;" id="iexturdom" class="form-select form-select-sm" ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} >
                                                                       <option value="-1">-- --</option>
                                                                       <c:forEach  var="LstTurno" items="${LstTurno}">
                                                                            <option value=${LstTurno.iexcodturno}  ${LstTurno.iexcodturno == xempxturno.iexturdom ? 'selected' : ''}  > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                       </c:forEach>
                                                                    </select>
                                                               </td>
                                                           </tr>
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="col-md-12 col-sm-6 mt-2">
                                                        <button name="btn_actualizar_tipo_turno" id="btn_actualizar_tipo_turno" class="btn btn-primary btn-sm" type="submit" onclick="updtipturno();"><span class="fa-solid fa-business-time me-2"></span>Guardar horario</button>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                           <table>
                                               <tr>
                                                    <div class="col-12">
                                                        <span class="badge badge-tag me-2 mt-3 mb-0">Configuración de turnos</span>
                                                    </div>
                                                    <div class="col-sm-6 col-md-3 ">
                                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Desde</label>
                                                        <input type="text" name="fecini"  id="feciniRango"  value="${requestScope.fecini}" class="form-control datetimepicker" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required>
                                                    </div>
                                                    <div class="col-sm-6 col-md-3 ">
                                                        <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Hasta</label>
                                                        <input type="text" name="fecfin"  id="fecfinRango"  value="${requestScope.fecfin}" class="form-control datetimepicker" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required>
                                                    </div>
                                                    <div class="col-sm-6 col-md-6 ps-1">
                                                        <p class="fs--1 mb-2 ms-1 text-600" style="font-style:italic;">El rango de selección debde ser de 30 o 31 días calendario</p>
                                                        <a class="btn btn-sm btn-phoenix-primary" onclick="traerAsistenciasPorRango();" href="#" data-bs-toggle="modal" data-bs-target="#modalAsistenciasGT" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-window-restore me-2"></span>Traer asistencias</a>
                                                    </div>
                                               </tr>
                                               <!--<tr>
                                                   <td>
                                                        <div class="col-8">
                                                            <input type="file" name="filexls" id="filexls" class="form-control">
                                                            <button name="btn_xls" class="btn btn-phoenix-success btn-sm mt-2 mb-2 " onclick="enviaForm('6')">Exportar excel</button>
                                                        </div>
                                                   </td>
                                               </tr>
                                               <tr>
                                                   <td colspan="8">
                                                       <button name="btn_ver" class="btn btn-phoenix-secondary btn-sm mt-2 mb-2 " onclick="enviaForm('1')"><span class="fa-solid fa-binoculars me-2"></span>Ver</button>
                                                       <button name="btn_programar" class="btn btn-phoenix-secondary btn-sm mt-2 mb-2 " onclick="enviaForm('3')"><span class="fa-solid fa-arrows-turn-to-dots me-2"></span>Turnos</button>
                                                       <button name="btn_calificar" class="btn btn-phoenix-secondary btn-sm mt-2 mb-2 " onclick="enviaForm('5')"><span class="fa-solid fa-ranking-star me-2"></span>Calificar</button>
                                                       <button name="btn_vermarcas" class="btn btn-phoenix-secondary btn-sm mt-2 mb-2 " onclick="enviaForm('4')"><span class="fa-solid fa-clock-rotate-left me-2"></span>Marcas</button>
                                                       <button name="btn_borrar" class="btn btn-phoenix-danger btn-sm mt-2 mb-2 " onclick="enviaForm('2')"><span class="fa-solid fa-minus me-2"></span>Borrar</button>
                                                    </td>
                                               </tr>-->
                                           </table>

                                           <!--<div class="table-responsive">
                                               <table  class="table table-striped jambo_table bulk_action">
                                               <c:set var="counter" value="7"/>
                                               <%
                                                   int i = 0 ;
                                                   int j = 7 ;
                                                   int z=1;
                                                    String fecini_var="";
                                                    String fecfin_variable="";
                                                   %>

                                                   <thead>
                                                 <tr>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;" >
                                                           <select name="id_domingo" id="id_domingo" style="width: 75px ;background:#fcefa1; color:black;"   onchange="program_tur_col(this,'1')">
                                                               <option value="-1" selected>-- --</option>
                                                               <c:forEach var="LstTurno" items="${requestScope.LstTurno}" varStatus="loopCounter"  >
                                                               <option value=${LstTurno.iexcodturno}    >[${LstTurno.iexflgturno}] ${LstTurno.iexhorini}-${LstTurno.iexhorfin} ${LstTurno.iexdesturno}</option>
                                                           </c:forEach>
                                                           </select>
                                                       </td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;">
                                                           <select name="id_lunes" id="id_lunes" style="width: 75px ;background:#fcefa1; color:black;"  onchange="program_tur_col(this,'2')">
                                                               <option value="-1" selected>-- --</option>
                                                               <c:forEach var="LstTurno" items="${requestScope.LstTurno}" varStatus="loopCounter"  >
                                                               <option value=${LstTurno.iexcodturno}    >[${LstTurno.iexflgturno}] ${LstTurno.iexhorini}-${LstTurno.iexhorfin} ${LstTurno.iexdesturno}</option>
                                                           </c:forEach>
                                                           </select>
                                                       </td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;">
                                                           <select name="id_martes" id="id_martes" style="width: 75px ;background:#fcefa1; color:black;"   onchange="program_tur_col(this,'3')"   >
                                                               <option value="-1" selected>-- --</option>
                                                               <c:forEach var="LstTurno" items="${requestScope.LstTurno}" varStatus="loopCounter"  >
                                                              <option value=${LstTurno.iexcodturno}    >[${LstTurno.iexflgturno}] ${LstTurno.iexhorini}-${LstTurno.iexhorfin} ${LstTurno.iexdesturno}</option>
                                                           </c:forEach>
                                                           </select>
                                                       </td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;">
                                                           <select name="id_miercoles" id="id_miercoles" style="width: 75px ;background:#fcefa1; color:black;"  onchange="program_tur_col(this,'4')"  >
                                                               <option value="-1" selected>-- --</option>
                                                               <c:forEach var="LstTurno" items="${requestScope.LstTurno}" varStatus="loopCounter"  >
                                                              <option value=${LstTurno.iexcodturno}    >[${LstTurno.iexflgturno}] ${LstTurno.iexhorini}-${LstTurno.iexhorfin} ${LstTurno.iexdesturno}</option>
                                                           </c:forEach>
                                                           </select>
                                                       </td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;">
                                                           <select name="id_jueves" id="id_jueves" style="width: 75px ;background:#fcefa1; color:black;"  onchange="program_tur_col(this,'5')"  >
                                                           <option value="-1" selected>-- --</option>
                                                               <c:forEach var="LstTurno" items="${requestScope.LstTurno}" varStatus="loopCounter"  >
                                                           <option value=${LstTurno.iexcodturno}    >[${LstTurno.iexflgturno}] ${LstTurno.iexhorini}-${LstTurno.iexhorfin} ${LstTurno.iexdesturno}</option>
                                                           </c:forEach>
                                                           </select>
                                                       </td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;">
                                                           <select name="id_viernes" id="id_viernes" style="width: 75px ;background:#fcefa1; color:black;" onchange="program_tur_col(this,'6')"  >
                                                               <option value="-1" selected>-- --</option>
                                                               <c:forEach var="LstTurno" items="${requestScope.LstTurno}" varStatus="loopCounter"  >
                                                           <option value=${LstTurno.iexcodturno}    >[${LstTurno.iexflgturno}] ${LstTurno.iexhorini}-${LstTurno.iexhorfin} ${LstTurno.iexdesturno}</option>
                                                           </c:forEach>
                                                           </select>
                                                       </td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;" >
                                                           <select name="id_sabado" id="id_sabado" style="width: 75px ;background:#fcefa1; color:black;"  onchange="program_tur_col(this,'7')"  >
                                                              <option value="-1" selected>-- --</option>
                                                               <c:forEach var="LstTurno" items="${requestScope.LstTurno}" varStatus="loopCounter"  >
                                                            <option value=${LstTurno.iexcodturno}    >[${LstTurno.iexflgturno}] ${LstTurno.iexhorini}-${LstTurno.iexhorfin} ${LstTurno.iexdesturno}</option>
                                                           </c:forEach>
                                                           </select>
                                                       </td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;" ></td>
                                                   </tr>

                                                   <tr>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;" >D</td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;">L</td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;">M</td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;">M</td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;">J</td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;">V</td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;" >S</td>
                                                       <td style="width: 100px ;background:#fcefa1; color:black;" ></td>
                                                   </tr>
                                                   </thead>
                                                   <tbody>
                                                       <c:forEach var="LstTurnoDiario" items="${requestScope.LstTurnoDiario}" varStatus="loopCounter"  >
                                                        <c:if test="${loopCounter.count ==1 }" >
                                                            <c:set var="test" value="${LstTurnoDiario.iexcoddiasem}"/>
                                                           <%
                                                               Integer ini = (Integer)pageContext.getAttribute("test");
                                                               i = i+ ini;
                                                            %>

                                                            <%  for(int n = 1; n < ini; n+=1) { %>
                                                              <td>
                                                            </td>
                                                            <% }  %>
                                                      </c:if>

                                                       <td
                                                           <c:if test="${LstTurnoDiario.iexcodturno ==999 }" >
                                                                style="background:#ffa448;"
                                                           </c:if>
                                                           >
                                                      <span class="bold"> ${LstTurnoDiario.desfecdia} </span> <br>

                                                         <a href="#"  onClick="window.open('${pageContext.request.contextPath}/GestionTiempos?accion=VERDIADET&desfecdia=${LstTurnoDiario.desfecdia}&iexcodfec=${LstTurnoDiario.iexcodfec}', '', 'width=750,height=650')" > <span class="bold3"> ${LstTurnoDiario.desiniturno} - ${LstTurnoDiario.desfinturno} </span> </a> <br>
                                                         <span class="bold2"> ${LstTurnoDiario.desiniasist} - ${LstTurnoD<iario.desfinasist} </span> <br>
                                                         <select name="${LstTurnoDiario.iexcodfec}" id="${LstTurnoDiario.iexcodfec}" style="width: 75px ;background:#fcefa1; color:black;"  onchange="updturnpForm('${LstTurnoDiario.desfecdia}', '${LstTurnoDiario.iexcodfec}')" >
                                                               <c:forEach var="LstTurno" items="${requestScope.LstTurno}" varStatus="loopCounter"  >
                                                                   <option value=${LstTurno.iexcodturno}    ${LstTurno.iexcodturno == LstTurnoDiario.iexcodturno? 'selected' : ''}  >[${LstTurno.iexflgturno}] ${LstTurno.iexhorini}-${LstTurno.iexhorfin} ${LstTurno.iexdesturno}</option>
                                                               </c:forEach>
                                                         </select>

                                                        <c:set var="feccur" value="${LstTurnoDiario.desfecdia}"/>
                                                      <%
                                                          String fecfinal=(String)pageContext.getAttribute("feccur");

                                                         if (z==1) {
                                                                fecini_var=(String)pageContext.getAttribute("feccur");
                                                                %>
                                                                <%
                                                            }else if (i%7==0) {
                                                                fecfin_variable =(String)pageContext.getAttribute("feccur");
                                                                z=0;
                                                                 %>
                                                       <td>
                                                               <select name="id_row2"   id="id_row2"  style="width: 75px ;background:#fcefa1; color:black;"  onchange="program_tur_row(this,'<%=fecini_var%>','<%=fecfin_variable%>')">
                                                                   <option value="-1" selected>-- --</option>
                                                                   <c:forEach  var="LstTurno" items="${LstTurno}">
                                                                        <option value=${LstTurno.iexcodturno}   > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                   </c:forEach>
                                                               </select>
                                                         </td>
                                                            <%      }    fecfin_variable =(String)pageContext.getAttribute("feccur");   %>
                                                              <% if (i%7==0) { %>
                                                               </tr>
                                                               <tr>
                                                              <% } %>
                                                                  <%  i = i+1 ;  z = z+1; %>
                                                         </td>
                                                       </c:forEach>
                                                     <td>

                                                     <select name="id_row" id="id_row" style="width: 75px ;background:#fcefa1; color:black;"  onchange="program_tur_row(this,'<%=fecini_var%>','<%=fecfin_variable%>')" >
                                                       <c:forEach var="LstTurno" items="${requestScope.LstTurno}" varStatus="loopCounter"  >
                                                           <option value="-1" selected>-- --</option>
                                                           <option value=${LstTurno.iexcodturno}    >[${LstTurno.iexflgturno}] ${LstTurno.iexhorini}-${LstTurno.iexhorfin} ${LstTurno.iexdesturno}</option>
                                                       </c:forEach>
                                                     </select>
                                                     </td>
                                                   </tr>
                                                   </tbody>
                                                 </table>
                                           </div>-->
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

  <div id="modalAsistenciasGT" class="modal fade" tabindex="-1" aria-labelledby="scrollingLongModalLabel2" aria-hidden="true" >
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
  	  <div class="modal-content bg-100">
  			<div class="modal-header border-200 bg-soft p-4">
  			   <h5 class="modal-title text-1000 fs-2 lh-sm">Asistencias por rango de fechas x empleado</h5>
  			   <button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>
  			</div>
  			<div class="modal-body p-4">
  				<form class="needs-validation" id="formAsistencias" method="POST" action="gestionarModalAsistencias" novalidate >
  				  <input id="accion" name="accion" type="hidden" value="" />
  				  <input id="desfecdia" name="desfecdia" type="hidden" value="" />
  				  <input id="iexcodfec" name="iexcodfec" type="hidden" value="" />
  				  <input id="iexcodpro" name="iexcodpro" type="hidden" value="" />
  				  <input id="iexperiodo" name="iexperiodo" type="hidden" value="" />
  				  <input id="iexcorrel" name="iexcorrel" type="hidden" value="1" />
  				  <input id="iexcodtra" name="iexcodtra" type="hidden" value="" />
  				  <input id="iexcodcia" name="iexcodcia" type="hidden" value="" />

  				  <div id="alertModalSuccessEdit" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
  					  <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
  					  <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
  					  <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
  				  </div>

  				  <div class="row g-3 mt-1">
  					  <div class="col-sm-6 col-md-2">
  							<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Id trab</label>
  							<input class="form-control" name="idTrabAsis" id="idTrabAsis" type="text" required disabled />
  							<input name="idTrabAsisHidden" id="idTrabAsisHidden" type="hidden" value="" />
  					  </div>
  					  <div class="col-sm-6 col-md-6">
  							<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Trabajador</label>
  							<input class="form-control" name="trabAsis" id="trabAsis" type="text" required disabled />
  							<input name="trabAsisHidden" id="trabAsisHidden" type="hidden" value="" />
  					  </div>
  					  <div class="col-sm-6 col-md-3">
  						  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de inicio</label>
  						  <input class="form-control" name="feciniAsis" id="feciniAsis" type="text" required disabled />
  						  <input name="feciniAsisHidden" id="feciniAsisHidden" type="hidden" value="" />
  					  </div>
  					  <div class="col-sm-6 col-md-3">
  						<label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha fin</label>
  						<input class="form-control" name="fecfinAsis" id="fecfinAsis" type="text" required disabled />
  						<input name="fecfinAsisHidden" id="fecfinAsisHidden" type="hidden" value="" />
  					  </div>
  				  </div>
  				  <div class="row g-4 mt-0">
  						<div class="col-auto">
  							<!--<a class="btn btn-primary btn-sm mt-1" onclick="return verTurnos('1');" ><span class="fas fa-calendar-days me-2"></span>Traer turnos</a>
  							<a class="btn btn-phoenix-secondary btn-sm mt-1" onclick="return programarTurnos('3');" ><span class="fas fa-wrench me-2"></span>Programar turnos</a>
  							<a class="btn btn-phoenix-secondary btn-sm mt-1" onclick="return calificar('5');" ><span class="fa-regular fa-star text-warning me-2"></span>Calificar</a>
  							<a class="btn btn-phoenix-secondary btn-sm mt-1" onclick=""  ><span class="fas fa-gauge me-2"></span>Marcasiones ing/sal</a>
  							<a class="btn btn-phoenix-danger btn-sm mt-1" onclick="return borrarTodo('2');"  ><span class="fas fa-trash me-2"></span>Borrar todo</a>
  							<a class="btn btn-phoenix-secondary btn-sm mt-1" onclick="return consolidar('7');"  ><span class="fas fa-right-left me-2"></span>Consolidar</a>-->
  							<a id="btnReportAsis" class="btn btn-phoenix-secondary btn-sm mt-1" href="#" target="_blank" onclick="reporteAsistencias();" ><span class="fas fa-download me-2"></span>Reporte asistencias PDF</a>
  							<!--<a class="btn btn-phoenix-success btn-sm mt-1" type="button" data-bs-toggle="modal" data-bs-target="#confirmModalCargarExcel" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" href="#"><span class="fas fa-upload me-2"></span>Importar excel</a>-->
  						</div>
  				  </div>
  				  <div class="row g-3 bg-100 mt-0">
  					<!--<h4 class="mb-0 mt-7">Calendario</h4>-->
  					<div id="orderTable" class="mt-2" data-list='{"valueNames":["codcon","descon","valor"],"page":10,"pagination":true}'>
  					  <div class="mb-3" class="mt-0">
  						  <div class="row g-3">
  							  <div id="calendarbody1" class="table-responsive scrollbar mx-n1 px-1 bg-100" >
  									<div class="mx-n4 px-4 mx-lg-n6 px-lg-6 border-y border-top">
  									  <div class="row py-3 gy-3 gx-0">
  										<div class="col-6 col-md-4 order-1 d-flex align-items-center">
  										  <a href="#" class="btn btn-sm btn-phoenix-secondary px-4" >Hoy</a>
  										</div>
  										<div class="col-12 col-md-4 order-md-1 d-flex align-items-center justify-content-center">
  										  <button class="btn icon-item icon-item-sm shadow-none text-1100 p-0" type="button" data-event="prev" title="Previous"><span class="fas fa-chevron-left"></span></button>
  										  <h3 id="mesDes" class="text-1100 fw-semi-bold calendar-title mb-0"></h3>
  										  <button class="btn icon-item icon-item-sm shadow-none text-1100 p-0" type="button" data-event="next" title="Next"><span class="fas fa-chevron-right"></span></button>
  										</div>
  										<div class="col-6 col-md-4 ms-auto order-1 d-flex justify-content-end">
  										  <div>
  											<div class="btn-group btn-group-sm" role="group">
  											  <a href="#" class="btn btn-phoenix-primary active-view" >Mes</a>
  											  <a href="#" class="btn btn-phoenix-secondary" >Semana</a>
  											</div>
  										  </div>
  										</div>
  									  </div>
  									</div>
  							  </div>
  							   <div class="table-responsive mx-n1 px-1 bg-100" style="width: 742px !important;" >
  									<table  class="table">
  										<thead id="calendarHead2">
  										</thead>
  										<tbody id="calendarBody2" class="fs--2 fw-semi-bold text-1000">
  										</tbody>
  										<tfoot id="calendarFoot2">
  										</tfoot>
  									</table>
  							   </div>
  						  </div>
  					  </div>
  					</div>
  				</div>
  				</form>
  			</div>
  			<div class="modal-footer d-flex justify-content-end align-items-center px-0 pb-0 border-200 pt-0">
  				<a class="btn btn-sm btn-primary px-3 my-0" data-bs-dismiss="modal" aria-label="Close">Cerrar</a>
  				<!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" type="submit"><div class="spinner-border spinner-border-sm" style="height:13px; width:13px;" role="status"></div><span class="ms-2">Guardar Periodo</span></button>-->
  				<!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" onclick="mostrarAlertModalEdit();" type="submit"><span class="ms-2">Guardar Periodo</span></button>-->
  			</div>
  	  </div>
    </div>
  </div>
</html>