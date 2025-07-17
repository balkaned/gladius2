
<!-- ===============================================-->
<!--    Modal Boletas -->
<!-- ===============================================-->

function generarBoleta(iexcodpro,iexcodtra,iexperiodo,iexcorrel,xgrppla,iexcodreg){
    $.ajax({
         url: "traerDatosDeBoleta",
         data: {
             "iexcodpro": iexcodpro,
             "iexcodtra": iexcodtra,
             "iexperiodo": iexperiodo,
             "iexcorrel": iexcorrel,
             "xgrppla": xgrppla,
             "iexcodreg": iexcodreg
         },
         success: function (data) {
             document.getElementById("idTrabBol").value=data.iexcodtra;
             document.getElementById("idTrabBolHidden").value=data.iexcodtra;
             document.getElementById("trabBol").value=data.destra;
             document.getElementById("feciniBol").value=data.iexfecing;

             document.getElementById("iexcodproGenBol").value=iexcodpro;
             document.getElementById("iexcodtraGenBol").value=iexcodtra;
             document.getElementById("iexperiodoGenBol").value=iexperiodo;
             document.getElementById("iexcorrelGenBol").value=iexcorrel;
             document.getElementById("xgrpplaGenBol").value=xgrppla;
             document.getElementById("iexcodregGenBol").value=iexcodreg;
         }
    });

    $.ajax({
         url: "traerDatosDeBoletaParam",
         data: {
             "iexcodpro": iexcodpro,
             "iexcodtra": iexcodtra,
             "iexperiodo": iexperiodo,
             "iexcorrel": iexcorrel,
             "xgrppla": xgrppla,
             "iexcodreg": iexcodreg
         },
         success: function (data) {
              var opt = "";

              for (var i in data) {
                  opt += "<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                               "<td class='fs--1 align-middle px-0 py-3'>"+
                                 "<div class='form-check mb-0 fs-0'>"+
                                   "<input class='form-check-input' id='checkbox-bulk-order-select' type='checkbox' />"+
                                 "</div>"+
                               "</td>"+
                               "<td class='codcon align-middle white-space-nowrap py-0'><a class='fw-semi-bold' href='#'>#"+data[i].procodcon+"</a></td>"+
                               "<td class='descon align-middle text-start fw-semi-bold ps-0 pe-0 text-1000'>"+data[i].coodescon+"</td>"+
                               "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].provalor+"</td>"+
                            "</tr>";
              }

              $("#customer-order-table-body-param").html(opt);
         }
    });

    $.ajax({
         url: "traerDatosDeBoletaIngresos",
         data: {
             "iexcodpro": iexcodpro,
             "iexcodtra": iexcodtra,
             "iexperiodo": iexperiodo,
             "iexcorrel": iexcorrel,
             "xgrppla": xgrppla,
             "iexcodreg": iexcodreg
         },
         success: function (data) {
              var opt = "";

              for (var i in data) {
                  opt += "<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                               "<td class='fs--1 align-middle px-0 py-3'>"+
                                 "<div class='form-check mb-0 fs-0'>"+
                                   "<input class='form-check-input' id='checkbox-bulk-order-select' type='checkbox' />"+
                                 "</div>"+
                               "</td>"+
                               "<td class='codcon align-middle white-space-nowrap py-0'><a class='fw-semi-bold' href='#'>#"+data[i].procodcon+"</a></td>"+
                               "<td class='descon align-middle text-start fw-semi-bold ps-0 pe-0 text-1000'><span class='badge badge-phoenix fs--2 badge-phoenix-primary'>"+data[i].coodescon+"</span></td>"+
                               "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].provalor+"</td>"+
                            "</tr>";
              }

              $("#customer-order-table-body-ingresos").html(opt);
         }
    });

    $.ajax({
         url: "traerDatosDeBoletaDescuentos",
         data: {
             "iexcodpro": iexcodpro,
             "iexcodtra": iexcodtra,
             "iexperiodo": iexperiodo,
             "iexcorrel": iexcorrel,
             "xgrppla": xgrppla,
             "iexcodreg": iexcodreg
         },
         success: function (data) {
              var opt = "";

              for (var i in data) {
                  opt += "<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                               "<td class='fs--1 align-middle px-0 py-3'>"+
                                 "<div class='form-check mb-0 fs-0'>"+
                                   "<input class='form-check-input' id='checkbox-bulk-order-select' type='checkbox' />"+
                                 "</div>"+
                               "</td>"+
                               "<td class='codcon align-middle white-space-nowrap py-0'><a class='fw-semi-bold' href='#'>#"+data[i].procodcon+"</a></td>"+
                               "<td class='descon align-middle text-start fw-semi-bold ps-0 pe-0 text-1000'><span class='badge badge-phoenix fs--2 badge-phoenix-primary'>"+data[i].coodescon+"</span></td>"+
                               "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].provalor+"</td>"+
                            "</tr>";
              }

              $("#customer-order-table-body-descuentos").html(opt);
         }
    });

    $.ajax({
         url: "traerDatosDeBoletaAportes",
         data: {
             "iexcodpro": iexcodpro,
             "iexcodtra": iexcodtra,
             "iexperiodo": iexperiodo,
             "iexcorrel": iexcorrel,
             "xgrppla": xgrppla,
             "iexcodreg": iexcodreg
         },
         success: function (data) {
              var opt = "";

              for (var i in data) {
                  opt += "<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                               "<td class='fs--1 align-middle px-0 py-3'>"+
                                 "<div class='form-check mb-0 fs-0'>"+
                                   "<input class='form-check-input' id='checkbox-bulk-order-select' type='checkbox' />"+
                                 "</div>"+
                               "</td>"+
                               "<td class='codcon align-middle white-space-nowrap py-0'><a class='fw-semi-bold' href='#'>#"+data[i].procodcon+"</a></td>"+
                               "<td class='descon align-middle text-start fw-semi-bold ps-0 pe-0 text-1000'><span class='badge badge-phoenix fs--2 badge-phoenix-primary'>"+data[i].coodescon+"</span></td>"+
                               "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].provalor+"</td>"+
                            "</tr>";
              }

              $("#customer-order-table-body-aportes").html(opt);
         }
    });

    $.ajax({
         url: "traerDatosDeBoletaNeto",
         data: {
             "iexcodpro": iexcodpro,
             "iexcodtra": iexcodtra,
             "iexperiodo": iexperiodo,
             "iexcorrel": iexcorrel,
             "xgrppla": xgrppla,
             "iexcodreg": iexcodreg
         },
         success: function (data) {
              var opt = "";

              for (var i in data) {
                  opt += "<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                               "<td class='fs--1 align-middle px-0 py-3'>"+
                                 "<div class='form-check mb-0 fs-0'>"+
                                   "<input class='form-check-input' id='checkbox-bulk-order-select' type='checkbox' />"+
                                 "</div>"+
                               "</td>"+
                               "<td class='codcon align-middle white-space-nowrap py-0'><a class='fw-semi-bold' href='#'>#"+data[i].procodcon+"</a></td>"+
                               "<td class='descon align-middle text-start fw-semi-bold ps-0 pe-0 text-1000'><span class='badge badge-phoenix fs--2 badge-phoenix-primary'>"+data[i].coodescon+"</span></td>"+
                               "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].provalor+"</td>"+
                            "</tr>";
              }

              $("#customer-order-table-body-neto").html(opt);
         }
    });

    $.ajax({
         url: "traerDatosDeBoletaTotales",
         data: {
             "iexcodpro": iexcodpro,
             "iexcodtra": iexcodtra,
             "iexperiodo": iexperiodo,
             "iexcorrel": iexcorrel,
             "xgrppla": xgrppla,
             "iexcodreg": iexcodreg
         },
         success: function (data) {
              var opt = "";

              for (var i in data) {
                  opt += "<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                               "<td class='fs--1 align-middle px-0 py-3'>"+
                                 "<div class='form-check mb-0 fs-0'>"+
                                   "<input class='form-check-input' id='checkbox-bulk-order-select' type='checkbox' />"+
                                 "</div>"+
                               "</td>"+
                               "<td class='codcon align-middle white-space-nowrap py-0'><a class='fw-semi-bold' href='#'>#"+data[i].procodcon+"</a></td>"+
                               "<td class='descon align-middle text-start fw-semi-bold ps-0 pe-0 text-1000'><span class='badge badge-phoenix fs--2 badge-phoenix-primary'>"+data[i].coodescon+"</span></td>"+
                               "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].provalor+"</td>"+
                            "</tr>";
              }

              $("#customer-order-table-body-totales").html(opt);
         }
    });
}

function obtenerData(){
    var data=$("#element1").val();
    return data;
}

function descargarBoleta(idComp){
    var codtra = $("#idTrabBolHidden").val();

    //var iexcodpro = $("#iexcodpro").val();
    var iexcodpro = "1";
    var iexperiodo = $("#iexperiodo").val();
    var iexcorrel = $("#iexcorrel").val();

    var params="3UP_CODPRO="+iexcodpro+"UP_NROPER="+iexperiodo+"UP_CORREL="+iexcorrel;

    document.getElementById("botonDescargarBoletaTrab").href="AWSorFTP_flgsource@verReportePDF@"+idComp+"@"+codtra+"@null@null@BoletaEmpTra@"+params+"@null@null@null";
}

function descargarReporte5ta(idComp){

    var codtra = $("#idTrabBolHidden").val();
    //var iexcodpro = $("#iexcodpro").val();
    var iexcodpro = "1";
    var iexperiodo = $("#iexperiodo").val();
    var iexcorrel = $("#iexcorrel").val();

    var params="3UP_CODPRO="+iexcodpro+"UP_NROPER="+iexperiodo+"UP_CORREL="+iexcorrel;

    document.getElementById("botonDescargarRep5ta").href="AWSorFTP_flgsource@verReportePDF@"+idComp+"@"+codtra+"@null@null@Boleta5taper@"+params+"@null@null@null";
}

function eliminarPlanTrab(){
    var iexcodpro = $("#iexcodpro").val();
    var codtra = $("#idTrabBolHidden").val();
    var iexperiodo = $("#iexperiodo").val();
    var iexcorrel = $("#iexcorrel").val();
    var xgrppla = $("#grppla").val();
    var iexcodreg = $("#iexcodreg").val();

    var opcion = confirm("Esta seguro de Eliminar al trabajador de la planilla?");

    if (opcion == true) {

        $.ajax({
             url: "botonEliminarPlanTrab",
             data: {
                 "iexcodpro": iexcodpro,
                 "iexcodtra": codtra,
                 "iexperiodo": iexperiodo,
                 "iexcorrel": iexcorrel,
                 "xgrppla": xgrppla
             },
             success: function (data) {
                 alert("Trabajador eliminado exitosamente!");
                 location.href="listarDetallePlanillaGen@"+iexcodreg+"@"+iexcodpro+"@"+iexperiodo+"";
             }
        });

        return true;
    } else {
        return false;
    }
}

function buscarParams(event) {

  var value = $("#inputParams").val();
  //alert("value: "+value);

  var iexcodpro = $("#iexcodproGenBol").val();
  var iexcodtra = $("#iexcodtraGenBol").val();
  var iexperiodo = $("#iexperiodoGenBol").val();
  var iexcorrel = $("#iexcorrelGenBol").val();
  var xgrppla = $("#xgrpplaGenBol").val();
  var iexcodreg = $("#iexcodregGenBol").val();

  $.ajax({
       url: "traerDatosDeBoletaParamBuscar",
       data: {
           "iexcodpro": iexcodpro,
           "iexcodtra": iexcodtra,
           "iexperiodo": iexperiodo,
           "iexcorrel": iexcorrel,
           "xgrppla": xgrppla,
           "iexcodreg": iexcodreg,
           "txtbuscar": value
       },
       success: function (data) {
            var opt = "";

            for (var i in data) {
                opt += "<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                             "<td class='fs--1 align-middle px-0 py-3'>"+
                               "<div class='form-check mb-0 fs-0'>"+
                                 "<input class='form-check-input' id='checkbox-bulk-order-select' type='checkbox' />"+
                               "</div>"+
                             "</td>"+
                             "<td class='codcon align-middle white-space-nowrap py-0'><a class='fw-semi-bold' href='#'>#"+data[i].procodcon+"</a></td>"+
                             "<td class='descon align-middle text-start fw-semi-bold ps-0 pe-0 text-1000'>"+data[i].coodescon+"</td>"+
                             "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].provalor+"</td>"+
                         "</tr>";
            }
            //$("#customer-order-table-body-param").html("");
            $("#customer-order-table-body-param").html(opt);
       }
  });

}