function formatearFecha1(){
  var fechaSeleccionada = $('#iexfecnac').val();

  var anio=fechaSeleccionada.substring(0, 4);
  var mes=fechaSeleccionada.substring(5, 7);
  var dia=fechaSeleccionada.substring(8, 10);

  var fechaFormat=dia+"/"+mes+"/"+anio;
  $("#iexfecnac").val(fechaFormat);
}

function formatearFecha2(){
    var fechaSeleccionada = $('#iexfecing').val();

    var anio=fechaSeleccionada.substring(0, 4);
    var mes=fechaSeleccionada.substring(5, 7);
    var dia=fechaSeleccionada.substring(8, 10);

    var fechaFormat=dia+"/"+mes+"/"+anio;
    $("#iexfecing").val(fechaFormat);
}

function formatearFecha3(){
      var fechaSeleccionada = $('#iexfecret').val();

      var anio=fechaSeleccionada.substring(0, 4);
      var mes=fechaSeleccionada.substring(5, 7);
      var dia=fechaSeleccionada.substring(8, 10);

      var fechaFormat=dia+"/"+mes+"/"+anio;
      $("#iexfecret").val(fechaFormat);
}

function formatearFecha4(){
    var fechaSeleccionada = $('#iexfecini_cont').val();

    var anio=fechaSeleccionada.substring(0, 4);
    var mes=fechaSeleccionada.substring(5, 7);
    var dia=fechaSeleccionada.substring(8, 10);

    var fechaFormat=dia+"/"+mes+"/"+anio;
    $("#iexfecini_cont").val(fechaFormat);
}

function formatearFecha5(){
  var fechaSeleccionada = $('#iexfecfin_cont').val();

  var anio=fechaSeleccionada.substring(0, 4);
  var mes=fechaSeleccionada.substring(5, 7);
  var dia=fechaSeleccionada.substring(8, 10);

  var fechaFormat=dia+"/"+mes+"/"+anio;
  $("#iexfecfin_cont").val(fechaFormat);
}

function formatearFecha6(){
  var fechaSeleccionada = $('#iexfecafp').val();

  var anio=fechaSeleccionada.substring(0, 4);
  var mes=fechaSeleccionada.substring(5, 7);
  var dia=fechaSeleccionada.substring(8, 10);

  var fechaFormat=dia+"/"+mes+"/"+anio;
  $("#iexfecafp").val(fechaFormat);
}

$(document).ready(function(){
  var fechacargada=$("#iexfecnachidden").val();
  $("#iexfecnac").val(fechacargada);

  var fechacargada2=$("#iexfecinghidden").val();
  $("#iexfecing").val(fechacargada2);

  var fechacargada3=$("#iexfecrethidden").val();
  $("#iexfecret").val(fechacargada3);

  var fechacargada4=$("#iexfecini_conthidden").val();
  $("#iexfecini_cont").val(fechacargada4);

  var fechacargada5=$("#iexfecfin_conthidden").val();
  $("#iexfecfin_cont").val(fechacargada5);

  var fechacargada6=$("#iexfecafphidden").val();
  $("#iexfecafp").val(fechacargada6);

  $('#myTab li a').click(function(){
      $(this).addClass('active');
      var thisselc=this.id;

      sessionStorage.setItem("myTabTrab",thisselc);
  });

  var myTabTrab = sessionStorage.getItem("myTabTrab");
  $('#'+myTabTrab).tab('show');
});

function mostrarAlertLab(){
    var div=document.getElementById('alertLab');
    div.style.display = '';

    setTimeout(function() {
      $("#alerts").hide(6000);
    }, 3000);
}

function mostrarAlertPago(){
  var div=document.getElementById('alertPago');
  div.style.display = '';

  setTimeout(function() {
      $("#alerts").hide(6000);
  }, 3000);
}

function mostrarAlertSeg(){
    var div=document.getElementById('alertSeg');
    div.style.display = '';

    setTimeout(function() {
      $("#alerts").hide(6000);
    }, 3000);
}

function mostrarAlertDom(){
  var div=document.getElementById('alertDom');
  div.style.display = '';

  setTimeout(function() {
      $("#alerts").hide(6000);
  }, 3000);
}
