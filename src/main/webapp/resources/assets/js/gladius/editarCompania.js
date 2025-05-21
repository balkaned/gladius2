$(document).ready(function(){
    $('#myTabComp li a').click(function(){
      $(this).addClass('active');
      var thisselc=this.id;

      sessionStorage.setItem("myTabCompany",thisselc);
    });

    var myTabCompany = sessionStorage.getItem("myTabCompany");
    $('#'+myTabCompany).tab('show');
});

function mostrarAlert(){
    var div=document.getElementById('alert');
    div.style.display = '';

    setTimeout(function() {
      $("#alerts").hide(6000);
    }, 3000);
}

function submitFrmGeneral(){
    $("#frmGeneral").submit();
    mostrarAlert();
}

function subirimagen(){
    var uploadFile = $("#uploadFile").val();

    if(uploadFile == null){
    return;
    }

    var parts=uploadFile.split(".");
    var part1=parts[0];
    var part2=parts[1];

    if(part2=="jpg" || part2=="png" || part2=="jpeg"){
    $('#modalLoading').modal('show');
    $("#formComp").submit();
    }else{
    alert("Solo se pueden subir imágenes en formato .jpeg, .jpg, .png");

    return;
    }
}

function insertarConceptosTable() {
    var idciaConcepto = $("#idciaConcept").val();
    var id_concepto = $("#id_concepto").val();
    var tipo_reg = $("#tipo_reg").val();

    if (id_concepto == "") {
        alert("Debe seleccionar un concepto");
        return;
    }

    if (tipo_reg == "") {
        alert("Debe seleccionar algpun tipo de registro");
        return;
    }

    $.ajax({
         url: "insertarConceptoCompania",
         data: {
             "idcia": idciaConcepto,
             "id_concepto": id_concepto,
             "tipo_reg": tipo_reg
         },
         success: function (data) {
            refrescarTablaConceptos(idciaConcepto);
         }
    });
}

function refrescarTablaConceptos(idciaConcepto){
    // Traemos los datos de la tabla Conceptos Fijos
    $.ajax({
         url: "getConceptosFijos",
         data: {
             "idcia": idciaConcepto
         },
         success: function (data) {
             var opt = "";

             for (var i in data) {
                opt += "<tr class='border border-300 rounded-2 hover-actions-trigger btn-reveal-trigger position-static'>"+
                          "<td class='align-middle white-space-nowrap text-center text-700'><span class='badge badge-tag'>"+data[i].iexcodcon+"</span></td>"+
                          "<td class='align-middle white-space-nowrap text-start text-700'><span class='badge badge-phoenix fs--2 badge-phoenix-primary'><span class='badge-label'>"+data[i].iexdescon+"</span></td>"+
                          "<td><a class='pe-2' onclick='delConceptosCompaniatbl('"+idciaConcepto+"','"+data[i].iexcodcon+"');'>x</a></td>"+
                        "</tr>";
             }

             $("#customer-order-table-body-conceptFijos").html(opt);
         }
    });

    // Traemos los datos de la tabla Conceptos Variables
    $.ajax({
         url: "getConceptosVariables",
         data: {
             "idcia": idciaConcepto
         },
         success: function (data) {
             var opt = "";

             for (var i in data) {
                opt += "<tr class='border border-300 rounded-2 hover-actions-trigger btn-reveal-trigger position-static'>"+
                          "<td class='align-middle white-space-nowrap text-center text-700'><span class='badge badge-tag'>"+data[i].iexcodcon+"</span></td>"+
                          "<td class='align-middle white-space-nowrap text-start text-700'><span class='badge badge-phoenix fs--2 badge-phoenix-danger'><span class='badge-label'>"+data[i].iexdescon+"</span></td>"+
                          "<td><a class='pe-2' onclick='delConceptosCompaniatbl('"+idciaConcepto+"','"+data[i].iexcodcon+"');' >x</a></td>"+
                        "</tr>";
             }

             $("#customer-order-table-body-conceptVar").html(opt);
         }
    });
}

function delConceptosCompaniatbl(idCia,iexcodcon){
    $.ajax({
         url: "delConceptoCompania",
         data: {
             "idcia": idCia,
             "idCon": iexcodcon
         },
         success: function (data) {
            refrescarTablaConceptos(idCia);
         }
    });
}