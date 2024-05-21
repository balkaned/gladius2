function traerOtrosDatos(iexcodpro,iexperiodo,iexcorrel){

    $.ajax({
         url: "traerDatosModalOtrosDatos",
         data: {
             "iexcodpro": iexcodpro,
             "iexperiodo": iexperiodo,
             "iexcorrel": iexcorrel
         },
         success: function (data) {
             document.getElementById("regOtros").value=data.desregimen;
             document.getElementById("procOtros").value=data.desproceso;

             var periodo2= data.iexnroper+" - ["+data.timerfecini+" - "+data.timerfecfin+"]";
             document.getElementById("perOtros").value=periodo2;
             document.getElementById("estadoOtros").value=data.desestado;
         }
    });

    $.ajax({
         url: "traerDatosModalOtrosVac",
         data: {
             "iexcodpro": iexcodpro,
             "iexperiodo": iexperiodo,
             "iexcorrel": iexcorrel
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
                               "<td class='codcon align-middle white-space-nowrap py-0'><a class='fw-semi-bold' href='#'>#"+data[i].iexcodtra+"</a></td>"+
                               "<td class='descon align-middle white-space-nowrap text-start fw-semi-bold ps-0 pe-0 text-1000'>"+data[i].destra+"</td>"+
                               "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-3 pe-3 white-space-nowrap'><span class='badge badge-phoenix fs--2 badge-phoenix-primary'><span class='badge-label'>"+data[i].tiporegistro+"</span></td>"+
                               "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].fecini+"</td>"+
                               "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].fecfin+"</td>"+
                               "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].nrodia+"</td>"+
                               "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-3 pe-3 white-space-nowrap'><span class='badge badge-tag me-2 mb-2'>"+data[i].codcon+"</span></td>"+
                            "</tr>";
              }

              $("#otros-customer-order-table-body-vacaciones").html(opt);
         }
    });

    $.ajax({
         url: "traerDatosModalOtrosAusen",
         data: {
             "iexcodpro": iexcodpro,
              "iexperiodo": iexperiodo,
              "iexcorrel": iexcorrel
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
                             "<td class='codcon align-middle white-space-nowrap py-0'><a class='fw-semi-bold' href='#'>#"+data[i].iexcodtra+"</a></td>"+
                             "<td class='descon align-middle white-space-nowrap text-start fw-semi-bold ps-0 pe-0 text-1000'>"+data[i].destra+"</td>"+
                             "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-3 pe-3 white-space-nowrap'><span class='badge badge-phoenix fs--2 badge-phoenix-danger'><span class='badge-label'>"+data[i].tiporegistro+"</span></td>"+
                             "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].fecini+"</td>"+
                             "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].fecfin+"</td>"+
                             "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].nrodia+"</td>"+
                             "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-3 pe-3 white-space-nowrap'><span class='badge badge-tag me-2 mb-2'>"+data[i].codcon+"</span></td>"+
                          "</tr>";
              }

              $("#otros-customer-order-table-body-ausentismos").html(opt);
         }
    });

    $.ajax({
         url: "traerDatosModalOtrosPrest",
         data: {
             "iexcodpro": iexcodpro,
             "iexperiodo": iexperiodo,
             "iexcorrel": iexcorrel
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
                           "<td class='codcon align-middle white-space-nowrap py-0'><a class='fw-semi-bold' href='#'>#"+data[i].iexcodtra+"</a></td>"+
                           "<td class='descon align-middle white-space-nowrap text-start fw-semi-bold ps-0 pe-0 text-1000'>"+data[i].destra+"</td>"+
                           "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-3 pe-3 white-space-nowrap'><span class='badge badge-phoenix fs--2 badge-phoenix-secondary'><span class='badge-label'>"+data[i].tiporegistro+"</span></td>"+
                           "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].feccuota+"</td>"+
                           "<td class='valor align-middle text-center fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].idcuota+"</td>"+
                           "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-0 pe-3 white-space-nowrap'>"+data[i].cuota+"</td>"+
                           "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-3 pe-3 white-space-nowrap'><span class='badge badge-tag me-2 mb-2'>"+data[i].codcon+"</span></td>"+
                        "</tr>";
              }

              $("#otros-customer-order-table-body-prestamos").html(opt);
         }
    });

    $.ajax({
         url: "traerDatosModalOtrosProm",
         data: {
             "iexcodpro": iexcodpro,
             "iexperiodo": iexperiodo,
             "iexcorrel": iexcorrel
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
                             "<td class='codcon align-middle white-space-nowrap py-0'><a class='fw-semi-bold' href='#'>#"+data[i].iexcodtra+"</a></td>"+
                             "<td class='descon align-middle white-space-nowrap text-start fw-semi-bold ps-0 pe-0 text-1000'>"+data[i].destra+"</td>"+
                             "<td class='valor align-middle text-center fw-semi-bold text-1000 ps-2 pe-2 white-space-nowrap'>"+data[i].periodo_proceso+"</td>"+
                             "<td class='valor align-middle text-center fw-semi-bold text-1000 ps-2 pe-2 white-space-nowrap'>"+data[i].concepto_destino+"</td>"+
                             "<td class='valor align-middle text-center fw-semi-bold text-1000 ps-2 pe-2 white-space-nowrap'>"+data[i].periodo_anterior+"</td>"+
                             "<td class='valor align-middle text-center fw-semi-bold text-1000 ps-2 pe-2 white-space-nowrap'>"+data[i].concepto_origen+"</td>"+
                             "<td class='valor align-middle text-end fw-semi-bold text-1000 ps-2 pe-2 white-space-nowrap'>"+data[i].valor_con+"</td>"+
                          "</tr>";
              }

              $("#otros-customer-order-table-body-promedio").html(opt);
         }
    });

}