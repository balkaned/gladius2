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

    $('#iexpaisemisor').change(function(event){
     $.ajaxSetup({cache:false});
          $.ajax({
            url: "getlovsDEPX",
            data: {"accion": "DEPX",
                "codpais": $("#iexpaisemisor").val()},
            success: function (data) {
                var opt = "";
                     opt += "<option value='' >Seleccionar</option>";
                     for (var i in data) {
                      opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                     }
                $("#iexdepart_origen").html(opt);
                $("#iexprovin_origen").html("<option value='' >Seleccionar</option>");
                $("#iexdistri_origen").html("<option value='' >Seleccionar</option>");
            }
     });
    });

    $('#iexdepart_origen').change(function(event){
     $.ajaxSetup({cache:false});
          $.ajax({
            url: "getlovsPROVX",
            data: {"accion": "PROVX",
                "coddept": $("#iexdepart_origen").val()},
            success: function (data) {
                var opt = "";
                     opt += "<option value='' >Seleccionar</option>";
                     for (var i in data) {
                      opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                     }
                $("#iexprovin_origen").html(opt);
                $("#iexdistri_origen").html("<option value='' >Seleccionar</option>");
            }
     });
    });

    $('#iexprovin_origen').change(function(event){
     $.ajaxSetup({cache:false});
          $.ajax({
            url: "getlovsDISTX",
            data: {"accion": "DISTX",
                "codprov": $("#iexprovin_origen").val()},
            success: function (data) {
                var opt = "";
                     opt += "<option value=0 >Seleccionar</option>";
                     for (var i in data) {
                      opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                     }
                $("#iexdistri_origen").html(opt);
            }
     });
    });

    $('#iexpaisemisor1').change(function(event){
         $.ajaxSetup({cache:false});
              $.ajax({
                url: "getlovsDEPX",
                data: {"accion": "DEPX",
                    "codpais": $("#iexpaisemisor1").val()},
                success: function (data) {
                    var opt = "";
                         opt += "<option value='' >Seleccionar</option>";
                         for (var i in data) {
                          opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                         }
                    $("#iexdepart_origen1").html(opt);
                    $("#iexprovin_origen1").html("<option value='' >Seleccionar</option>");
                    $("#iexubigeo_dom1").html("<option value='' >Seleccionar</option>");
                }
         });
    });

    $('#iexdepart_origen1').change(function(event){
         $.ajaxSetup({cache:false});
              $.ajax({
                url: "getlovsPROVX",
                data: {"accion": "PROVX",
                    "coddept": $("#iexdepart_origen1").val()},
                success: function (data) {
                    var opt = "";
                         opt += "<option value='' >Seleccionar</option>";
                         for (var i in data) {
                          opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                         }
                    $("#iexprovin_origen1").html(opt);
                    $("#iexubigeo_dom1").html("<option value='' >Seleccionar</option>");
                }
         });
    });

    $('#iexprovin_origen1').change(function(event){
         $.ajaxSetup({cache:false});
              $.ajax({
                url: "getlovsDISTX",
                data: {"accion": "DISTX",
                    "codprov": $("#iexprovin_origen1").val()},
                success: function (data) {
                    var opt = "";
                         opt += "<option value=0 >Seleccionar</option>";
                         for (var i in data) {
                          opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                         }
                    $("#iexdistri_origen1").html(opt);
                }
         });
    });

    $('#iexpaisemisor2').change(function(event){
     $.ajaxSetup({cache:false});
          $.ajax({
            url: "getlovsDEPX",
            data: {"accion": "DEPX",
                "codpais": $("#iexpaisemisor2").val()},
            success: function (data) {
                var opt = "";
                     opt += "<option value='' >Seleccionar</option>";
                     for (var i in data) {
                      opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                     }
                $("#iexdepart_origen2").html(opt);
                $("#iexprovin_origen2").html("<option value='' >Seleccionar</option>");
                $("#iexubigeo_dom2").html("<option value='' >Seleccionar</option>");
            }
     });
    });

    $('#iexdepart_origen2').change(function(event){
     $.ajaxSetup({cache:false});
          $.ajax({
            url: "getlovsPROVX",
            data: {"accion": "PROVX",
                "coddept": $("#iexdepart_origen2").val()},
            success: function (data) {
                var opt = "";
                     opt += "<option value='' >Seleccionar</option>";
                     for (var i in data) {
                      opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                     }
                $("#iexprovin_origen2").html(opt);
                $("#iexubigeo_dom2").html("<option value='' >Seleccionar</option>");
            }
     });
    });

    $('#iexprovin_origen2').change(function(event){
     $.ajaxSetup({cache:false});
          $.ajax({
            url: "getlovsDISTX",
            data: {"accion": "DISTX",
                "codprov": $("#iexprovin_origen2").val()},
            success: function (data) {
                var opt = "";
                     opt += "<option value=0 >Seleccionar</option>";
                     for (var i in data) {
                      opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                     }
                $("#iexdistri_origen2").html(opt);
          }
     });
    });

    $('#iexflgeps').change(function () {
      var iexflgeps=$("#iexflgeps").val();

      console.log("iexflgeps: "+iexflgeps);
      //alert(iexflgeps);

      if(iexflgeps==1){
        document.getElementById("iexcodeps").required="true";
      }else{
        document.getElementById("iexcodeps").required="false";
      }
    });
});

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


