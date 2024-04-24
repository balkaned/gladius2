function enviaForm(variable){
    var opcion = confirm("Esta seguro de ejecutar este evento?");

    if (opcion == true) {
        if(variable==2){
            document.getElementById("accion").value="INIPRO";
        }else if(variable==3){
            document.getElementById("accion").value="EXEPRO";
        }else if(variable==4){
            document.getElementById("accion").value="VERBOLTOT";
        }else if(variable==5){
            document.getElementById("accion").value="EXPBOLTOT";
            document.getElementById("tipfile").value="xls";
        }else if(variable==6){
            document.getElementById("accion").value="DELPRO";
        }else if(variable==7){
            document.getElementById("accion").value="CIEPRO";
        }else if(variable==8){
            document.getElementById("accion").value="EXPBOLTOT";
            document.getElementById("tipfile").value="pdf";
        }else if(variable==9){
            document.getElementById("accion").value="EXPTEXT";
            document.getElementById("tipfile").value="text";
        } else if(variable==10){
            document.getElementById("accion").value="QRYRESBAN";
            document.getElementById("tipfile").value="text";
        } else if(variable==11){
            document.getElementById("accion").value="EXEINIPRO";
            document.getElementById("tipfile").value="text";
        }else if(variable==12){
            //document.getElementById("accion").value="VERDETVAR";
            //document.getElementById("tipfile").value="text";
        }else if(variable==15){
            document.getElementById("accion").value="VERPLAVAC";
            document.getElementById("tipfile").value="xls";
        }else if(variable==16){
            document.getElementById("accion").value="VERPLAAUS";
            document.getElementById("tipfile").value="xls";
        }else if(variable==17){
            document.getElementById("accion").value="VERPLAPRES";
            document.getElementById("tipfile").value="xls";
        }else if(variable==18){
            document.getElementById("accion").value="VERPLADATVAR";
            document.getElementById("tipfile").value="xls";
        }else if(variable==25){
            document.getElementById("accion").value="VERDETTURNO";
            document.getElementById("tipfile").value="text";
        }else if(variable==30){
            document.getElementById("accion").value="UPLOADPLA";
            document.getElementById("tipfile").value="xls";
        }else if(variable==31){
            document.getElementById("accion").value="EXPRESCTL";
            document.getElementById("tipfile").value="xls";
        }else if(variable==33){
            document.getElementById("accion").value="EXPASISCCO";
            document.getElementById("tipfile").value="xls";
        }else if(variable==34){
            document.getElementById("accion").value="CALFASIST";
        }else if(variable==35){
            document.getElementById("accion").value="QRYPLA";
        }

        document.getElementById("frmplaserv").submit();
    } else {
        return false;
    }
}

function enviaForm_ind(variable , trab){
    if(variable==2){
        document.getElementById("accion").value="INIPRO";
    }else if(variable==3){
        document.getElementById("accion").value="EXEPRO";
    }else if(variable==34){
        document.getElementById("accion").value="CALFASIST";
    }

    document.getElementById("iexcodtra").value=trab ;
    document.getElementById("frmplaserv").submit();
}

function consulBol(codtra){
    document.getElementById("accion").value="VERBOLTRA";
    document.getElementById("iexcodtra").value=codtra ;
    document.getElementById("frmplaserv").submit();
}

function verdetcon(codtra){
    document.getElementById("accion").value="VERDETCONCEP";
    document.getElementById("iexcodtra").value=codtra ;
    document.getElementById("frmplaserv").submit();
    myWindow = window.open("", "myWindow", "width=200,height=100");
}

function getAFPPermes(permes){
    var url = "${pageContext.request.contextPath}/GestionAfp?accion=QRY&idperiodo="+permes+"&menu=false  ";
    myRef = window.open(url ,'mywin','left=20,top=20,width=1200,height=800,toolbar=1,resizable=0');
    myRef.focus();
}

function getReporteOtros(iexcodpro, iexperiodo, xgrppla, iexcodreg){
    var url = "${pageContext.request.contextPath}/PlanillaServlet?accion=VEROTRDATA&iexcodpro="+iexcodpro+"&iexperiodo="+iexperiodo+"&grppla="+xgrppla+"&iexcodreg="+iexcodreg+"&iexcorrel=1&menu=false ";
    myRef = window.open(url ,'mywin','left=20,top=20,width=1200,height=800,toolbar=1,resizable=0');
    myRef.focus()
}

function remove(){
    var opcion = confirm("Esta seguro de Eliminar el Registro?");
    if (opcion == true) {
        return true;
    } else {
        return false;
    }
}

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
                               "<td class='descon align-middle text-start fw-semi-bold ps-0 pe-0 text-1000'><span class='badge badge-phoenix fs--2 badge-phoenix-primary'>"+data[i].coodescon+"</span></td>"+
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

function descargarBoleta(){
    var codtra = $("#idTrabBolHidden").val();

    var iexcodpro = $("#iexcodpro").val();
    var iexperiodo = $("#iexperiodo").val();
    var iexcorrel = $("#iexcorrel").val();

    var params="3UP_CODPRO="+iexcodpro+"UP_NROPER="+iexperiodo+"UP_CORREL="+iexcorrel;

    document.getElementById("botonDescargarBoletaTrab").href="AWSorFTP_flgsource@verReportePDF@${idComp}@"+codtra+"@null@null@BoletaEmpTra@"+params+"@null@null@null";
}

function descargarReporte5ta(){
    var codtra = $("#idTrabBolHidden").val();
    var iexcodpro = $("#iexcodpro").val();
    var iexperiodo = $("#iexperiodo").val();
    var iexcorrel = $("#iexcorrel").val();

    var params="3UP_CODPRO="+iexcodpro+"UP_NROPER="+iexperiodo+"UP_CORREL="+iexcorrel;

    document.getElementById("botonDescargarRep5ta").href="AWSorFTP_flgsource@verReportePDF@${idComp}@"+codtra+"@null@null@Boleta5taper@"+params+"@null@null@null";
}

function eliminarPlanTrab(){
    var iexcodpro = $("#iexcodpro").val();
    var codtra = $("#idTrabBolHidden").val();
    var iexperiodo = $("#iexperiodo").val();
    var iexcorrel = $("#iexcorrel").val();
    var xgrppla = $("#grppla").val();
    var iexcodreg = $("#iexcodreg").val();

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
}

function traerDatosReporteResumenPlanilla(){

    var iexcodpro = $("#iexcodpro").val();
    var iexperiodo = $("#iexperiodo").val();

    $.ajax({
         url: "traerDatosReporteResumenPlanilla",
         data: {
            "iexcodpro": iexcodpro,
            "nroper": iexperiodo,
            "nroper2": iexperiodo
         },
         success: function (data) {
             $("#idresult").html(data);
         }
    });
}

function verAsistenciaPeriodoTrab(codtra,nombretrab,fecini,fecfin){

    document.getElementById("idTrabAsis").value=codtra;
    document.getElementById("trabAsis").value=nombretrab;
    document.getElementById("feciniAsis").value=fecini;
    document.getElementById("fecfinAsis").value=fecfin;

    $.ajax({
         url: "traerLstTurnosModal",
         data: {
              "fecini": fecini
         },
         success: function (data) {
              console.log("anioDes: "+data[0].anioDes);
              console.log("mesDes: "+data[0].mesDes);

              $("#mesDes").text(data[0].mesDes+" "+data[0].anioDes);

              var opt = "";

              opt += "<tr>"+
                         "<td class='pt-0 pb-2'>"+
                             "<select name='id_domingo' id='id_domingo' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,'1')'>"+
                                 "<option value='-1' selected>Turno</option>";
                                 for (var i in data) {
                                     opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                                 }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                             "<select name='id_lunes' id='id_lunes' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,'2')'>"+
                                 "<option value='-1' selected>Turno</option>";
                                 for (var i in data) {
                                     opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                                 }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_martes' id='id_martes' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,'3')'>"+
                                  "<option value='-1' selected>Turno</option>";
                                  for (var i in data) {
                                      opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                                  }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_miercoles' id='id_miercoles' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,'4')'>"+
                               "<option value='-1' selected>Turno</option>";
                               for (var i in data) {
                                   opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                               }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_jueves' id='id_jueves' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,'5')'>"+
                              "<option value='-1' selected>Turno</option>";
                              for (var i in data) {
                                  opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                              }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_viernes' id='id_viernes' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,'6')'>"+
                               "<option value='-1' selected>Turno</option>";
                               for (var i in data) {
                                   opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                               }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_sabado' id='id_sabado' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,'7')'>"+
                                "<option value='-1' selected>Turno</option>";
                                for (var i in data) {
                                    opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                                }

                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'></td>"+
                     "</tr>"+
                     "<tr>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Dom</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Lun</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Mar</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Mie</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Jue</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Vie</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Sab</td>"+
                         "<td class='pt-2 pb-2'></td>"+
                     "</tr>";

              $("#calendarHead2").html(opt);
         }
    });

    console.log("codtra: "+codtra);
    console.log("fecini: "+fecini);
    console.log("fecfin: "+fecfin);

    $.ajax({
         url: "traerLstTurnoDiarioModal",
         data: {
              "codtra": codtra,
              "fecini": fecini,
              "fecfin": fecfin
         },
         success: function (data) {
             console.log("success!: data.length: "+data.length);

             if(data.length > 0){
                console.log("data[0].desfecdia: "+data[0].desfecdia);
                console.log("data[0].desiniturno: "+data[0].desiniturno);
             }

             var opt2 = "<tr>";
             var x=0;
             var j=1;

             for (var i in data) {
                console.log("ingreso al for y muestro la data...");

                if(i==0){
                    var ini = data[i].iexcoddiasem;
                    console.log("data[i].iexcoddiasem: "+data[i].iexcoddiasem);
                    x = x + ini;

                    opt2 += "<td class='pt-2 pb-2 ps-2 bg-200 bg-opacity-75 border border-100'>"+
                                "<span class='ms-1 text-400 fs-0'>31</span><br>"+
                            "</td>";

                    /*for(var n=1; n<ini; n+=1){
                        opt2 += "<td>"+
                                "</td>";
                    }*/
                }

                opt2 += "<td class='pt-2 pb-2 ps-2 pe-2 bg-300 bg-opacity-50 border border-100'>"+
                           "<span id='spanDiaCalendar"+i+"' class='ms-1 text-900 fs-0'>"+data[i].diaCalendar+"</span><br>"+
                           "<span class='text-500'>["+data[i].iexflgturno+"]</span><br>"+
                           "<span class='text-500'>"+data[i].desfecdia+"</span><br>"+
                           "<span class='text-500'>"+data[i].desiniturno+" - "+data[i].desfinturno+"</span><br>";

                           if(data[i].desiniasist=="undefined" || data[i].desiniasist==null || data[i].desiniasist==""){
                                opt2 += "<span class='text-500'></span>";
                           }else{
                                opt2 += "<span class='text-500'>"+data[i].desiniasist+" - "+data[i].desfinasist+"</span><br>";
                           }

                           /*<select name="${LstTurnoDiario.iexcodfec}" id="${LstTurnoDiario.iexcodfec}" style="width: 75px ;background:#fcefa1; color:black;"  onchange="updturnpForm('${LstTurnoDiario.desfecdia}', '${LstTurnoDiario.iexcodfec}')" >
                              <c:forEach var="LstTurno" items="${requestScope.LstTurno}" varStatus="loopCounter"  >
                                 <option value=${LstTurno.iexcodturno} ${LstTurno.iexcodturno == LstTurnoDiario.iexcodturno? 'selected' : ''} >${LstTurno.iexhorini}-${LstTurno.iexhorfin} ${LstTurno.iexdesturno}</option>
                              </c:forEach>
                           </select>*/

                   //opt2 += "<a href='#' onClick='' >Ver</a> --- <a href='#' onClick=''>AutoMark</a>";
                   opt2 += "<a id='popoverVer"+i+"' class='btn btn-sm text-400 bg-white pt-0 pb-1 fs--1 fw-semi-bold border border-1 border-300' tabindex='0' role='button' data-bs-toggle='popover' data-bs-trigger='focus' title='Dismissible popover' data-bs-content='And heres some amazing content. Its very engaging. Right?'><span id='dotv"+i+"' class='text-success fs-1 me-1'>&#x2022;</span>Ver</a>";
                   opt2 += "<a id='popoverAutoMark"+i+"' class='btn btn-sm text-400 bg-white pt-0 pb-1 fs--1 fw-semi-bold border border-1 border-300' tabindex='0' role='button' data-bs-toggle='popover' data-bs-trigger='focus' title='Dismissible popover' data-bs-content='And heres some amazing content. Its very engaging. Right?'><span id='dota"+i+"' class='text-primary fs-1 me-1'>&#x2022;</span>Automark</a>";

                   /*"<td>"+
                        "<select class='form-select form-select-sm' name='id_row2' id='id_row2' style='width: 75px;' onchange='program_tur_row('','','')'>"+
                           "<option value='-1' selected>-- --</option>"+
                        "</select>"+
                    "</td>";*/

                console.log("j: "+j);
                j++;

                if(j % 7 == 0){
                    console.log("Ingreso a multiplo de 7...");
                    opt2 += "</tr>"+
                            "<tr>";
                }

                x = x+1;
                opt2 += "</td>";
             }

             $("#calendarBody2").html(opt2);

             for (var i in data) {
                 $('#popoverVer'+i).popover({
                        container: "body",
                        html: true,
                        content: function () {
                          return '<div class="popover-message">And heres some amazing content. Its very engaging. Right?</div>';
                        }
                 });

                 $('#popoverAutoMark'+i).popover({
                     container: "body",
                     html: true,
                     content: function () {
                       return '<div class="popover-message">And heres some amazing content. Its very engaging. Right?</div>';
                     }
                 });

                 for (var i in data) {
                     console.log("verificando el valor de i antes de agregar class: "+i);

                     var y=0;
                     var a=0;
                     y=i-1;
                     a=i-2;
                     console.log("verificando el valor de y antes de agregar class: "+y);

                     if(i % 7 == 0){
                         $('#spanDiaCalendar'+y).addClass('text-warning');
                         $('#popoverVer'+y).addClass('text-warning');
                         $('#popoverAutoMark'+y).addClass('text-warning');
                         $('#dotv'+y).addClass('text-warning');
                         $('#dota'+y).addClass('text-warning');

                         $('#spanDiaCalendar'+a).addClass('text-warning');
                         $('#popoverVer'+a).addClass('text-warning');
                         $('#popoverAutoMark'+a).addClass('text-warning');
                         $('#dotv'+a).addClass('text-warning');
                         $('#dota'+a).addClass('text-warning');
                     }
                 }
             }
         }
    });
}