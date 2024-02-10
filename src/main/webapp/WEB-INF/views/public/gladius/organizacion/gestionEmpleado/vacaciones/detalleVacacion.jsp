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

$(document).ready(function(){
      var fechacargada=$("#iexfecinihidden").val();
      $("#iexfecini").val(fechacargada);

      var fechacargada2=$("#iexfecfinhidden").val();
      $("#iexfecfin").val(fechacargada2);
});

function formatearFecha1(){
    var fechaSeleccionada = $('#iexfecini').val();

    var anio=fechaSeleccionada.substring(0, 4);
    var mes=fechaSeleccionada.substring(5, 7);
    var dia=fechaSeleccionada.substring(8, 10);

    var fechaFormat=dia+"/"+mes+"/"+anio;
    $("#iexfecini").val(fechaFormat);
}

function formatearFecha2(){
    var fechaSeleccionada = $('#iexfecfin').val();

    var anio=fechaSeleccionada.substring(0, 4);
    var mes=fechaSeleccionada.substring(5, 7);
    var dia=fechaSeleccionada.substring(8, 10);

    var fechaFormat=dia+"/"+mes+"/"+anio;
    $("#iexfecfin").val(fechaFormat);
}

function isValidDate(day,month,year){
		var dteDate;
		month=month-1;
		dteDate=new Date(year,month,day);
		return ((day==dteDate.getDate()) && (month==dteDate.getMonth()) && (year==dteDate.getFullYear()));
}

function validate_fecha(fecha){
		var patron=new RegExp("^([0-9]{1,2})([/])([0-9]{1,2})([/])(19|20)+([0-9]{2})$");

		if(fecha.search(patron)==0)
		{
			var values=fecha.split("/");
			if(isValidDate(values[0],values[1],values[2]))
			{
				return true;
			}
		}
		return false;
}

function calcularDias(){
        formatearFecha2();

		var fechaInicial=document.getElementById("iexfecini").value;
		var fechaFinal=document.getElementById("iexfecfin").value;
		var resultado="";
		if(validate_fecha(fechaInicial) && validate_fecha(fechaFinal))
		{
			inicial=fechaInicial.split("/");
			final=fechaFinal.split("/");
			// obtenemos las fechas en milisegundos
			var dateStart=new Date(inicial[2],(inicial[1]-1),inicial[0]);
            var dateEnd=new Date(final[2],(final[1]-1),final[0]);
            if(dateStart<=dateEnd)
            {
				// la diferencia entre las dos fechas, la dividimos entre 86400 segundos
				// que tiene un dia, y posteriormente entre 1000 ya que estamos
				// trabajando con milisegundos.
				//resultado="La diferencia es de "+(((dateEnd-dateStart)/86400)/1000)+" días";
                                resultado=""+(((dateEnd-dateStart)/86400)/1000)+"";
			}else{
				resultado="La fecha inicial es posterior a la fecha final";
			}
		}else{
			if(!validate_fecha(fechaInicial))
				//resultado="La fecha inicial es incorrecta";
                                    resultado="0";
			if(!validate_fecha(fechaFinal))
				//resultado="La fecha final es incorrecta";
                                     resultado="0";
		}

		document.getElementById("iexnrodias").value=Number(resultado)+1;
		document.getElementById("iexnrodias2").value=Number(resultado)+1;
}

function enviaForm(variable){
    //document.getElementById("frmplaserv").submit();

    var fechaInicial=document.getElementById("iexfecini").value;
    var fechaFinal=document.getElementById("iexfecfin").value;
    var tipvac=document.getElementById("iextipvac").value;
    var resultado="";

    if(fechaInicial!== null && fechaFinal!== null &&  tipvac!== "" ){
        if(validate_fecha(fechaInicial) && validate_fecha(fechaFinal)){
            inicial=fechaInicial.split("/");
            final=fechaFinal.split("/");
            // obtenemos las fechas en milisegundos
            var dateStart=new Date(inicial[2],(inicial[1]-1),inicial[0]);
            var dateEnd=new Date(final[2],(final[1]-1),final[0]);

            if(dateStart<=dateEnd){
                // la diferencia entre las dos fechas, la dividimos entre 86400 segundos
                // que tiene un dia, y posteriormente entre 1000 ya que estamos
                // trabajando con milisegundos.
                //resultado="La diferencia es de "+(((dateEnd-dateStart)/86400)/1000)+" días";
                resultado=(((dateEnd-dateStart)/86400)/1000)+1;

                /*
                if(document.getElementById("saldo").value >= resultado){
                        document.getElementById("accion").value="INSVAC";
                        document.getElementById("formvacaciones").submit();
                        mostrarAlert()
                }else{
                    if(document.getElementById("iexflgnosaldo").checked ){
                        document.getElementById("accion").value="INSVAC";
                        document.getElementById("formvacaciones").submit();
                        mostrarAlert()
                     }else{
                    alert("Numero de Dias Programados es superior al Saldo de Dias");
                     }
                }*/

                 document.getElementById("accion").value="INSVAC";
                 document.getElementById("formvacaciones").submit();
            }else{
                alert("La fecha inicial es posterior a la fecha final");
            }
        }
        else{
            alert("Formatos de Fechas no son consistentes");
        }
    }else {
        alert("Debe ingresar correctamente el Tipo de Vacaciones, Fecha de Inicio y Fecha de Fin de la programacion vacacional");
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
                              <li class="breadcrumb-item active">Vacaciones</li>
                            </ol>
                          </nav>
                          <div class="mb-9">
                            <div class="row g-3 mb-4">
                              <div class="col-auto">
                                <h2 id="h2top" class="mb-0">Ver detalle vacaciones</h2>
                              </div>
                            </div>
                            <c:if test="${msg!=null}">
                                 <div id="alert" class="alert alert-outline-danger bg-danger bg-opacity-10 d-flex align-items-center" role="alert">
                                 	<span class="fa-regular fa-times-circle text-danger fs-0 me-3"></span>
                                 	<div class="col-11">
                                 		<strong class="text-black">Error al guardar</strong>
                                 		 <p class="mb-0 fw-semi-bold text-1000">${msg} <a href="#">Mas información</a></p>
                                 	</div>
                                 	<button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                                 </div>
                            </c:if>

                            <div class="row g-5">
                                 <div class="col-xl-10">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-4 mb-0 needs-validation" name="formvacaciones"  id="formvacaciones" method="POST" action="modificarVacaciones" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />

                                            <div class="col-sm-6 col-md-3 ">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">ID</label>
                                                  <input class="form-control" id="iexcorreldis" name="iexcorreldis" type="text" value="${iexcorrel}" disabled readonly/>
                                                  <input type="hidden" name="iexcorrel" id="iexcorrel" value="${iexcorrel}" />
                                            </div>
                                            <div class="col-sm-6 col-md-9">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Tipo Vacaciones</label>
                                                  <select class="form-select"  name="iextipvac"  id="iextipvac" required disabled>
                                                    <option value="" selected>Seleccionar</option>
                                                    <c:forEach var="lovTipvaca" items="${requestScope.lovTipvaca}">
                                                      <option value="${lovTipvaca.idLov}" ${lovTipvaca.idLov== requestScope.xVacacionesPrg.iextipvac ? 'selected' : ''} > ${lovTipvaca.desLov} </option>
                                                    </c:forEach>
                                                  </select>
                                            </div>
                                            <div class="col-sm-6 col-md-9">
                                                  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Periodo vacacional</label>
                                                  <select class="form-select"  name="iexpermesini" required disabled>
                                                    <option value="" selected>Seleccionar</option>
                                                    <c:forEach var="LstVacacionesCtl" items="${requestScope.LstVacacionesCtl}"    >
                                                       <option value="${LstVacacionesCtl.iexpermesini}"  ${LstVacacionesCtl.iexpermesini== requestScope.xVacacionesPrg.iexpermesini ? 'selected' : ''} >  ${LstVacacionesCtl.iexpermesini} - ${LstVacacionesCtl.iexpermesfin}  </option>
                                                    </c:forEach>
                                                  </select>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Fecha Inicio</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                              <input class="form-control datetimepicker" name="iexfecini" id="iexfecini" onchange="formatearFecha1();" type="text" value="${requestScope.xVacacionesPrg.iexfecini}" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required disabled/>
                                              <input class="form-control" id="iexfecinihidden" type="hidden" value="${requestScope.xVacacionesPrg.iexfecini}" />
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Fecha Fin</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                              <input class="form-control datetimepicker" name="iexfecfin" id="iexfecfin" onchange="calcularDias();" type="text" value="${requestScope.xVacacionesPrg.iexfecfin}" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true}' required disabled/>
                                              <input class="form-control" id="iexfecfinhidden" type="hidden" value="${requestScope.xVacacionesPrg.iexfecfin}" />
                                            </div>
                                            <div class="col-sm-6 col-md-4">
                                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">* Nro de dias</label>
                                              <input class="form-control" name="iexnrodiasdis" id="iexnrodias2" type="text" value="${requestScope.xVacacionesPrg.iexnrodias}" disabled readonly/>
                                              <input type="hidden" name="iexnrodias" id="iexnrodias" value="" />
                                            </div>
                                            <div class="col-sm-6 col-md-8">
                                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Glosa</label>
                                                <input class="form-control" name="iexglosa" type="text" value="${requestScope.xVacacionesPrg.iexglosa}" disabled/>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <input class="form-check-input" name="iexflgnosaldo" id="iexflgnosaldo" value="1" type="checkbox" disabled/>
                                                <label class="form-label fs-0 text-1000 ps-2 text-none mb-2" for="flexChecked">No considerar saldo? </label>
                                            </div>

                                            <div class="col-12 gy-6">
                                                <div class="row g-3 justify-content-end">
                                                  <div class="col-auto">
                                                    <a class="btn btn-phoenix-primary" href="verDetalleVac@${idTrab}@${perini}@${perfin}">Cancel</a>
                                                  </div>
                                                  <div class="col-auto">
                                                    <button class="btn btn-primary disabled" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar Vacaciones</button>
                                                  </div>
                                                </div>
                                            </div>

                                            <div id="alert" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                                            	<span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                                            	<p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                                            	<button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
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