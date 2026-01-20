$(document).ready(function(){
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

                    // If the endpoint returned exactly one department, auto-select it to load provinces
                    if (Array.isArray(data) && data.length === 1) {
                        $("#iexdepart_origen1").val(data[0].idLov).trigger('change');
                    }
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

                    // If exactly one province returned, auto-select it to load districts
                    if (Array.isArray(data) && data.length === 1) {
                        $("#iexprovin_origen1").val(data[0].idLov).trigger('change');
                    }
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
                    $("#iexubigeo_dom1").html(opt);
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
                        $("#iexubigeo_dom2").html(opt);
                    }
             });
        });
});



