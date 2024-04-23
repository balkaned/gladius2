function regimen(){
    $.ajax({
      url: "getlovsPROXCON",
      data: {"accion": "PROXCON",
          "iexcodreg": $("#iexcodreg2").val()},
      success: function (data) {
          var opt = "";
               opt += "<option value='0' >Seleccionar proceso</option>";
               for (var i in data) {
                opt += "<option value="+data[i].idProceso+" > "+data[i].desProceso+" </option> ";
               }

          $("#idproceso").html(opt);
      }
    });
}

function editarPeriodo(codproceso,periodo){
    $.ajax({
      url: "getDatosPeriodo",
      data: {
          "codproceso": codproceso,
          "periodo": periodo
          },
      success: function (data) {
            $("#idanioEditdisabled").val(data.iexanio);
            $("#idanioEdit").val(data.iexanio);

            $("#idpermesEditdisabled").val(data.iexpermes);
            $("#idpermesEdit").val(data.iexpermes);

            $("#idperiododisabled").val(data.iexnroper);
            $("#idperiodoEdit").val(data.iexnroper);

            $("#iexcodreg2Edit").val(data.desregimen);

            $("#idprocesoEditdisabled").val(data.desproceso);
            $("#idprocesoEdit").val(data.iexcodpro);

            $("#feciniEdithidden").val(data.iexfecini);
            var fechacargada=$("#feciniEdithidden").val();
            $("#feciniEdit").val(fechacargada);

            $("#fecfinEdithidden").val(data.iexfecfin);
            var fechacargada2=$("#fecfinEdithidden").val();
            $("#fecfinEdit").val(fechacargada2);

            $("#fecinitEdithidden").val(data.timerfecini);
            var fechacargada3=$("#fecinitEdithidden").val();
            $("#fecinitEdit").val(fechacargada3);

            $("#fecfintEdithidden").val(data.timerfecfin);
            var fechacargada4=$("#fecfintEdithidden").val();
            $("#fecfintEdit").val(fechacargada4);

            $("#fecpagoEdithidden").val(data.iexfecpago);
            var fechacargada5=$("#fecpagoEdithidden").val();
            $("#fecpagoEdit").val(fechacargada5);

            $("#feccertiEdithidden").val(data.iexfeccerti);
            var fechacargada6=$("#feccertiEdithidden").val();
            $("#feccertiEdit").val(fechacargada6);
      }
    });
}

function remove() {
    var opcion = confirm("Esta seguro de Eliminar el Registro?");
    if (opcion == true) {
        return true;
    } else {
        return false;
    }
}

function formatearFecha1(){
   var fechaSeleccionada = $('#fecini').val();

   var anio=fechaSeleccionada.substring(0, 4);
   var mes=fechaSeleccionada.substring(5, 7);
   var dia=fechaSeleccionada.substring(8, 10);

   var fechaFormat=dia+"/"+mes+"/"+anio;
   $("#fecini").val(fechaFormat);
}

function formatearFecha2(){
    var fechaSeleccionada = $('#fecfin').val();

    var anio=fechaSeleccionada.substring(0, 4);
    var mes=fechaSeleccionada.substring(5, 7);
    var dia=fechaSeleccionada.substring(8, 10);

    var fechaFormat=dia+"/"+mes+"/"+anio;
    $("#fecfin").val(fechaFormat);
}

function formatearFecha3(){
     var fechaSeleccionada = $('#fecinit').val();

     var anio=fechaSeleccionada.substring(0, 4);
     var mes=fechaSeleccionada.substring(5, 7);
     var dia=fechaSeleccionada.substring(8, 10);

     var fechaFormat=dia+"/"+mes+"/"+anio;
     $("#fecinit").val(fechaFormat);
}

function formatearFecha4(){
      var fechaSeleccionada = $('#fecfint').val();

      var anio=fechaSeleccionada.substring(0, 4);
      var mes=fechaSeleccionada.substring(5, 7);
      var dia=fechaSeleccionada.substring(8, 10);

      var fechaFormat=dia+"/"+mes+"/"+anio;
      $("#fecfint").val(fechaFormat);
}

function formatearFecha5(){
       var fechaSeleccionada = $('#fecpago').val();

       var anio=fechaSeleccionada.substring(0, 4);
       var mes=fechaSeleccionada.substring(5, 7);
       var dia=fechaSeleccionada.substring(8, 10);

       var fechaFormat=dia+"/"+mes+"/"+anio;
       $("#fecpago").val(fechaFormat);
}

function formatearFecha6(){
     var fechaSeleccionada = $('#feccerti').val();

     var anio=fechaSeleccionada.substring(0, 4);
     var mes=fechaSeleccionada.substring(5, 7);
     var dia=fechaSeleccionada.substring(8, 10);

     var fechaFormat=dia+"/"+mes+"/"+anio;
     $("#feccerti").val(fechaFormat);
}

function formatearFecha1Edit(){
     var fechaSeleccionada = $('#feciniEdit').val();

     var anio=fechaSeleccionada.substring(0, 4);
     var mes=fechaSeleccionada.substring(5, 7);
     var dia=fechaSeleccionada.substring(8, 10);

     var fechaFormat=dia+"/"+mes+"/"+anio;
     $("#feciniEdit").val(fechaFormat);
}

function formatearFecha2Edit(){
      var fechaSeleccionada = $('#fecfinEdit').val();

      var anio=fechaSeleccionada.substring(0, 4);
      var mes=fechaSeleccionada.substring(5, 7);
      var dia=fechaSeleccionada.substring(8, 10);

      var fechaFormat=dia+"/"+mes+"/"+anio;
      $("#fecfinEdit").val(fechaFormat);
}

function formatearFecha3Edit(){
       var fechaSeleccionada = $('#fecinitEdit').val();

       var anio=fechaSeleccionada.substring(0, 4);
       var mes=fechaSeleccionada.substring(5, 7);
       var dia=fechaSeleccionada.substring(8, 10);

       var fechaFormat=dia+"/"+mes+"/"+anio;
       $("#fecinitEdit").val(fechaFormat);
}

function formatearFecha4Edit(){
    var fechaSeleccionada = $('#fecfintEdit').val();

    var anio=fechaSeleccionada.substring(0, 4);
    var mes=fechaSeleccionada.substring(5, 7);
    var dia=fechaSeleccionada.substring(8, 10);

    var fechaFormat=dia+"/"+mes+"/"+anio;
    $("#fecfintEdit").val(fechaFormat);
}

function formatearFecha5Edit(){
     var fechaSeleccionada = $('#fecpagoEdit').val();

     var anio=fechaSeleccionada.substring(0, 4);
     var mes=fechaSeleccionada.substring(5, 7);
     var dia=fechaSeleccionada.substring(8, 10);

     var fechaFormat=dia+"/"+mes+"/"+anio;
     $("#fecpagoEdit").val(fechaFormat);
}

function formatearFecha6Edit(){
   var fechaSeleccionada = $('#feccertiEdit').val();

   var anio=fechaSeleccionada.substring(0, 4);
   var mes=fechaSeleccionada.substring(5, 7);
   var dia=fechaSeleccionada.substring(8, 10);

   var fechaFormat=dia+"/"+mes+"/"+anio;
   $("#feccertiEdit").val(fechaFormat);
}

function mostrarAlertModal(){
    var div=document.getElementById('alertModalSuccess');
    div.style.display = '';

    setTimeout(function() {
      $("#alerts").hide(6000);
    }, 3000);
}

function mostrarAlertModalEdit(){
     var div=document.getElementById('alertModalSuccessEdit');
     div.style.display = '';

     setTimeout(function() {
      $("#alerts").hide(6000);
     }, 3000);
}