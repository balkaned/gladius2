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
function enviaForm(variable){
    //document.getElementById("frmplaserv").submit();
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
    confirm("Esta segurod de actulizar los horarios?");
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
                          <div class="mb-0">
                            <div class="row g-3 mb-0">
                              <div class="col-auto">
                                <h2 id="h2top" class="mb-0">Gestion de Tiempos</h2>
                              </div>
                            </div>

                            <div class="row g-5">
                                 <div class="col-xl-12">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-4 mb-0 needs-validation" id="GestionTiempos" method="POST" action="grabarTurno@${idTrab}" novalidate >
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
                                             <caption>Configuracion de Turno</caption>
                                             <tr>
                                                 <div class="col-md-6">
                                                     <td>Control Asistenca para Pago?</td>
                                                     <td><input type="checkbox" name="iexctlasipag" id="iexctlasipag"  value="1" class="form-check-input" ${requestScope.xempxturno.iexctlasipag =='1' ? 'checked=true' : ''}  ></td>
                                                 </div>
                                             </tr>
                                            <tr>
                                                <td class="col-3">Tipo de Turno</td>
                                                <td>
                                                     <select id="slc_tipturno"  name="slc_tipturno" class="form-select text-black" onchange="jumpTo('slc_tipturno')" style="width: 250px;" >
                                                            <option value="-1" >Seleccionar</option>
                                                            <c:forEach  var="lovTipTurno" items="${lovTipTurno}">
                                                              <option value="${lovTipTurno.idLov}" ${lovTipTurno.idLov == xempxturno.iextipturno ? 'selected' : ''}>${lovTipTurno.desLov}</option>
                                                            </c:forEach>
                                                     </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2">
                                                    <table>
                                                        <tr>
                                                            <td> Lun <input type="checkbox" class="form-check-input" name="iexlunes" id="iexlunes" value="1" class="flat"  ${requestScope.xempxturno.iexlunes =='1' ? 'checked=true' : ''}   ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} > </td>
                                                            <td> Mar <input type="checkbox" class="form-check-input" name="iexmartes" id="iexmartes" value="1" class="flat" ${requestScope.xempxturno.iexmartes =='1' ? 'checked=true' : ''} ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'}  >  </td>
                                                            <td> Mie <input type="checkbox" class="form-check-input" name="iexmiercoles" id="iexmiercoles" value="1" class="flat"  ${requestScope.xempxturno.iexmiercoles =='1' ? 'checked=true' : ''} ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'}  >   </td>
                                                            <td> Jue <input type="checkbox" class="form-check-input" name="iexjueves" id="iexjueves" value="1" class="flat" ${requestScope.xempxturno.iexjueves =='1' ? 'checked=true' : ''} ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'}  >   </td>
                                                            <td> Vie <input type="checkbox" class="form-check-input" name="iexviernes" id="iexviernes" value="1" class="flat" ${requestScope.xempxturno.iexviernes =='1' ? 'checked=true' : ''} ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} >  </td>
                                                            <td> Sab <input type="checkbox" class="form-check-input" name="iexsabado" id="iexsabado" value="1" class="flat"  ${requestScope.xempxturno.iexsabado =='1' ? 'checked=true' : ''} ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} >   </td>
                                                            <td> Dom <input type="checkbox" class="form-check-input" name="iexdomingo" id="iexdomingo" value="1" class="flat"  ${requestScope.xempxturno.iexdomingo =='1' ? 'checked=true' : ''} ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'}  >   </td>
                                                       </tr>
                                                       <tr>
                                                           <td>
                                                                <select name="iexturlun"   id="iexturlun"  class="form-select text-black"  style="width: 170px;"   ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} >
                                                                   <option value="-1">-- --</option>
                                                                   <c:forEach  var="LstTurno" items="${LstTurno}">
                                                                        <option value=${LstTurno.iexcodturno}    ${LstTurno.iexcodturno == xempxturno.iexturlun ? 'selected' : ''}   > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                   </c:forEach>
                                                                </select>
                                                           </td>
                                                           <td>
                                                                <select name="iexturmar"   id="iexturmar"   class="form-select text-black"   style="width: 170px;"  ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} >
                                                                   <option value="-1">-- --</option>
                                                                   <c:forEach  var="LstTurno" items="${LstTurno}">
                                                                        <option value=${LstTurno.iexcodturno}  ${LstTurno.iexcodturno == xempxturno.iexturmar ? 'selected' : ''}  > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                   </c:forEach>
                                                                </select>
                                                           </td>
                                                           <td>
                                                                <select name="iexturmie"   id="iexturmie"  class="form-select text-black"   style="width: 170px;"   ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'}>
                                                                   <option value="-1">-- --</option>
                                                                   <c:forEach  var="LstTurno" items="${LstTurno}">
                                                                        <option value=${LstTurno.iexcodturno}  ${LstTurno.iexcodturno == xempxturno.iexturmie ? 'selected' : ''}  > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                   </c:forEach>
                                                                </select>
                                                           </td>
                                                           <td>
                                                                <select name="iexturjue"   id="iexturjue" class="form-select text-black"  style="width: 170px;"  ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} >
                                                                   <option value="-1">-- --</option>
                                                                   <c:forEach  var="LstTurno" items="${LstTurno}">
                                                                        <option value=${LstTurno.iexcodturno}  ${LstTurno.iexcodturno == xempxturno.iexturjue ? 'selected' : ''}  > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                   </c:forEach>
                                                                </select>
                                                           </td>
                                                           <td>
                                                                <select name="iexturvie"   id="iexturvie"    class="form-select text-black"   style="width: 170px;"   ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'}  >
                                                                   <option value="-1">-- --</option>
                                                                   <c:forEach  var="LstTurno" items="${LstTurno}">
                                                                        <option value=${LstTurno.iexcodturno}  ${LstTurno.iexcodturno == xempxturno.iexturvie ? 'selected' : ''}  > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                   </c:forEach>
                                                                </select>
                                                           </td>
                                                           <td>
                                                                <select name="iextursab"   id="iextursab"  class="form-select text-black"   style="width: 170px;"   ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} >
                                                                   <option value="-1">-- --</option>
                                                                   <c:forEach  var="LstTurno" items="${LstTurno}"  >
                                                                        <option value=${LstTurno.iexcodturno}  ${LstTurno.iexcodturno == xempxturno.iextursab ? 'selected' : ''}  > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                   </c:forEach>
                                                                </select>
                                                           </td>
                                                           <td>
                                                                <select name="iexturdom"   id="iexturdom"   class="form-select text-black"  style="width: 170px;"   ${ requestScope.xempxturno.iextipturno=='1' ? 'enabled' : 'disabled'} >
                                                                   <option value="-1">-- --</option>
                                                                   <c:forEach  var="LstTurno" items="${LstTurno}">
                                                                        <option value=${LstTurno.iexcodturno}  ${LstTurno.iexcodturno == xempxturno.iexturdom ? 'selected' : ''}  > [${LstTurno.iexflgturno}] : ${LstTurno.iexhorini}-${LstTurno.iexhorfin} </option>
                                                                   </c:forEach>
                                                                </select>
                                                           </td>
                                                       </tr>
                                                     </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" align="center">
                                                    <input type="button"  name="btn_actualizar_tipo_turno"   id="btn_actualizar_tipo_turno"  value="Grabar" class="btn btn-primary" onclick="updtipturno();"  >
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                           <table>
                                               <caption>Programación de Turnos</caption>
                                               <tr>
                                                   <td> Desde : <input type="text" name="fecini"  id="fecini"  value="${requestScope.fecini}" style="width: 200px;"  class="form-control"></td>
                                                   <td> Hasta : <input type="text" name="fecfin"  id="fecfin"  value="${requestScope.fecfin}"  style="width: 200px;"  class="form-control"></td>
                                                   <td></td>
                                                   <td colspan="5"></td>
                                               </tr>
                                               <tr>
                                                   <td class="col-md-12" style="float:left;margin-top:10px;"><input type="file" name="filexls" id="filexls" class="form-control col-md-6"><label style="width: 15px"></label><input type="button" name="btn_xls" value="Xls"  class="btn btn-primary" onclick="enviaForm('6')" ></td>
                                               </tr>
                                               <tr>
                                                   <td colspan="8">
                                                   <input type="button" name="btn_ver" value="Ver"  onclick="enviaForm('1')"  class="btn btn-primary" ><label style="width: 5px"> </label>
                                                   <input type="button" name="btn_programar" value="Turnos" onclick="enviaForm('3')" class="btn btn-primary" ><label style="width: 5px"> </label>
                                                   <input type="button" name="btn_calificar" value="Calificar" onclick="enviaForm('5')" class="btn btn-primary"  ><label style="width: 5px"> </label>
                                                   <input type="button" name="btn_vermarcas" value="Marcas" onclick="enviaForm('4')"  class="btn btn-primary" ><label style="width: 5px"> </label>
                                                   <input type="button" name="btn_borrar" value="Borrar" onclick="enviaForm('2')" class="btn btn-primary"  ><label style="width: 5px"> </label>
                                                    </td>
                                               </tr>
                                           </table>

                                           <div class="table-responsive">
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