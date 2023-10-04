<script>
      $(document).ready(function() {
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
                          $("iexdistri_origen1").html(opt);
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
      });

      function mostrarAlert(){
        //alert("se grabo exitosamente");
        var div=document.getElementById('alert');
        div.style.display = '';

        setTimeout(function() {
            $("#alert").hide(6000);
        }, 3000);
      }

      function mostrarAlert2(){
          //alert("se grabo exitosamente");
          var div=document.getElementById('alert2');
          div.style.display = '';

          setTimeout(function() {
              $("#alert").hide(6000);
          }, 3000);
      }

      function mostrarAlert3(){
        //alert("se grabo exitosamente");
        var div=document.getElementById('alert3');
        div.style.display = '';

        setTimeout(function() {
            $("#alert").hide(6000);
        }, 3000);
     }

     function mostrarAlert4(){
         //alert("se grabo exitosamente");
         var div=document.getElementById('alert4');
         div.style.display = '';

         setTimeout(function() {
             $("#alert").hide(6000);
         }, 3000);
     }

     function mostrarAlert5(){
          //alert("se grabo exitosamente");
          var div=document.getElementById('alert5');
          div.style.display = '';

          setTimeout(function() {
              $("#alerts").hide(6000);
          }, 3000);
     }
</script>