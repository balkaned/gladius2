function mostrarAlert(){
  var div=document.getElementById('alert');
  div.style.display = '';

  setTimeout(function() {
      $("#alerts").hide(6000);
  }, 3000);
}

function buscarConceptos(){
    $.ajax({
         url: "getConceptoxProcesoPromediable",
         data: {
             "codproceso": $("#codprocesoProm").val()
             },
         success: function (data) {
             var opt = "";
                  //opt += "<option value=0 >Seleccionar</option>";
                  for (var i in data) {
                   opt += "<option value="+data[i].procodcon+" > "+data[i].procodcon+" - "+data[i].coodescon+" </option> ";
                  }

             $("#idconceptoProm").html(opt);
         }
    });
}

function addConceptoPromediable(){
    $.ajax({
         url: "addConceptoPromediable",
         data: {
             "codprocesoaux": $("#codprocesoProm").val(),
             "codconceptoaux": $("#idconceptoProm").val(),
             "codproceso": $("#idproceso").val(),
             "codconcepto": $("#id_concept").val()
             },
         success: function (data) {
            actualizarTabla();
            mostrarAlertPromSucess();
         }
    });
}

function actualizarTabla(){
    $.ajax({
         url: "actualizarTblConceptoxProcesoPromediable",
         data: {
             "codproceso": $("#idproceso").val(),
             "codconcepto": $("#id_concept").val()
             },
         success: function (data) {
             var opt = "";
             var onclickchar="";

             for (var i in data) {
                //onclickchar=data[i].idproceso+"','"+data[i].codconcepto+"','"+data[i].idprocesoaux+"','"+data[i].codconceptaux;

                opt += "<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                          "<td class='align-middle white-space-nowrap ps-3 pe-3'><a class='fw-semi-bold' href='#!'>#</a></td>"+
                          "<td class='align-middle text-start fw-semi-bold ps-3 pe-3 text-1000'><span class='badge badge-phoenix fs--2 badge-phoenix-secondary'><span class='badge-label'>"+data[i].desprocesoaux+"</span></td>"+
                          "<td class='align-middle white-space-nowrap text-center text-700 ps-3 pe-3'>"+data[i].codconceptaux+"</td>"+
                          "<td class='align-middle white-space-nowrap text-center text-700 ps-3 pe-3'>"+data[i].desconceptaux+"</td>"+

                          "<td class='align-middle text-center white-space-nowrap pe-0 action'>"+
                            "<div class='font-sans-serif btn-reveal-trigger position-static'>"+
                              "<button class='btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2' type='button'"+
                              "data-bs-toggle='dropdown' data-boundary='window' aria-haspopup='true' aria-expanded='false' data-bs-reference='parent'>"+
                              "<span class='fas fa-plus'></span><span class='fas fa-caret-down ms-2'></span></button>"+
                              "<div class='dropdown-menu dropdown-menu-end py-2'>"+

                                "<div class='dropdown-divider'></div>"+
                                "<a id='dropdownmenutable' class='dropdown-item' onclick='return deleteConceptoPromediableAjax("+i+");'><span class='fa-solid fa-trash me-2'></span>Eliminar</a></div>"+
                                "<input type='hidden' id='varidproceso"+i+"' value="+data[i].idproceso+" />"+
                                "<input type='hidden' id='varcodconcepto"+i+"' value="+data[i].codconcepto+" />"+
                                "<input type='hidden' id='varidprocesoaux"+i+"' value="+data[i].idprocesoaux+" />"+
                                "<input type='hidden' id='varcodconceptaux"+i+"' value="+data[i].codconceptaux+" />"+
                            "</div>"+
                          "</td>"+
                        "</tr>";
             }

             $("#customer-order-table-body").html(opt);
         }
    });
}

function deleteConceptoPromediable(idproceso,codconcepto,idprocesoaux,codconceptaux){

    var opcion = confirm("Esta seguro de Eliminar el Registro?");

    if (opcion == true) {
        $.ajax({
             url: "deleteConceptoPromediable",
             data: {
                 "codprocesoaux": idprocesoaux,
                 "codconceptoaux": codconceptaux,
                 "codproceso": idproceso,
                 "codconcepto": codconcepto
                 },
             success: function (data) {
                actualizarTabla();
                mostrarAlertPromInfo();
             }
        });
    } else {
        return false;
    }
}

function deleteConceptoPromediableAjax(indice){

    var idproceso=$("#varidproceso"+indice).val();
    var codconcepto=$("#varcodconcepto"+indice).val();
    var idprocesoaux=$("#varidprocesoaux"+indice).val();
    var codconceptaux=$("#varcodconceptaux"+indice).val();

    var opcion = confirm("Esta seguro de Eliminar el Registro?");

    if (opcion == true) {
        $.ajax({
             url: "deleteConceptoPromediable",
             data: {
                 "codprocesoaux": idprocesoaux,
                 "codconceptoaux": codconceptaux,
                 "codproceso": idproceso,
                 "codconcepto": codconcepto
                 },
             success: function (data) {
                actualizarTabla();
                mostrarAlertPromInfo();
             }
        });
    } else {
        return false;
    }
}

function remove() {
    var opcion = confirm("Esta seguro de Eliminar el Registro?");
    if (opcion == true) {
        return true;
    } else {
        return false;
    }
}

function mostrarAlertPromSucess(){
  var div=document.getElementById('alertPromSuccess');
  div.style.display = '';
}

function mostrarAlertPromInfo(){
  var div=document.getElementById('alertPromInfo');
  div.style.display = '';
}

function addConceptoAgrup(){
    $.ajax({
         url: "addConceptoAgrup",
         data: {
             "codproceso": $("#idproceso").val(),
             "codconcepto": $("#id_concept").val(),
             "codconceptoaux": $("#idconceptoAgrp").val()
             },
         success: function (data) {
            actualizarTablaAgrup();
            mostrarAlertAgrupSucess();
         }
    });
}

function actualizarTablaAgrup(){
    $.ajax({
         url: "actualizarTblConceptoAgrup",
         data: {
             "codproceso": $("#idproceso").val(),
             "codconcepto": $("#id_concept").val()
             },
         success: function (data) {
             var opt = "";
             var onclickchar="";

             for (var i in data) {
                onclickchar="onclick='return deleteConceptoAgrup('"+data[i].idproceso+"','"+data[i].codconcepto+"','"+data[i].codconceptaux+"');'";

                opt += "<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                          "<td class='align-middle white-space-nowrap ps-3 pe-3'><a class='fw-semi-bold' href='#!'>#</a></td>"+
                          "<td class='align-middle text-start fw-semi-bold ps-3 pe-3 text-1000'><span class='badge badge-tag me-2 mb-2'>"+data[i].codconceptaux+"</span></td>"+
                          "<td class='align-middle white-space-nowrap text-center text-700 ps-3 pe-3'>"+data[i].desconceptaux+"</td>"+

                          "<td class='align-middle text-center white-space-nowrap pe-0 action'>"+
                            "<div class='font-sans-serif btn-reveal-trigger position-static'>"+
                              "<button class='btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2' type='button'"+
                              "data-bs-toggle='dropdown' data-boundary='window' aria-haspopup='true' aria-expanded='false' data-bs-reference='parent'>"+
                              "<span class='fas fa-plus'></span><span class='fas fa-caret-down ms-2'></span></button>"+
                              "<div class='dropdown-menu dropdown-menu-end py-2'>"+

                                "<div class='dropdown-divider'></div>"+
                                "<a id='dropdownmenutable' class='dropdown-item' onclick='return deleteConceptoAgrupAjax("+i+");' ><span class='fa-solid fa-trash me-2'></span>Eliminar</a></div>"+
                                "<input type='hidden' id='varAgrupidproceso"+i+"' value="+data[i].idproceso+" />"+
                                "<input type='hidden' id='varAgrupcodconcepto"+i+"' value="+data[i].codconcepto+" />"+
                                "<input type='hidden' id='varAgrupcodconceptaux"+i+"' value="+data[i].codconceptaux+" />"+
                            "</div>"+
                          "</td>"+
                        "</tr>";
             }

             $("#customer-order-table-body-agrup").html(opt);
         }
    });
}

function mostrarAlertAgrupSucess(){
  var div=document.getElementById('alertAgrupSuccess');
  div.style.display = '';
}

function mostrarAlertAgrupInfo(){
  var div=document.getElementById('alertAgrupInfo');
  div.style.display = '';
}

function deleteConceptoAgrup(idproceso,codconcepto,codconceptaux){

    var opcion = confirm("Esta seguro de Eliminar el Registro?");

    if (opcion == true) {
        $.ajax({
             url: "deleteConceptoAgrup",
             data: {
                 "codproceso": idproceso,
                 "codconcepto": codconcepto,
                 "codconceptoaux": codconceptaux
                 },
             success: function (data) {
                actualizarTablaAgrup();
                mostrarAlertAgrupInfo();
             }
        });
    } else {
        return false;
    }
}

function deleteConceptoAgrupAjax(indice){

    var idproceso=$("#varAgrupidproceso"+indice).val();
    var codconcepto=$("#varAgrupcodconcepto"+indice).val();
    var codconceptaux=$("#varAgrupcodconceptaux"+indice).val();

    var opcion = confirm("Esta seguro de Eliminar el Registro?");

    if (opcion == true) {
        $.ajax({
             url: "deleteConceptoAgrup",
             data: {
                 "codproceso": idproceso,
                 "codconcepto": codconcepto,
                 "codconceptoaux": codconceptaux
                 },
             success: function (data) {
                actualizarTablaAgrup();
                mostrarAlertAgrupInfo();
             }
        });
    } else {
        return false;
    }
}
