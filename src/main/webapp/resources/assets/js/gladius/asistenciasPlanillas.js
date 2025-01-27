
<!-- ===============================================-->
<!--    Modal Asistencias, Turnos y Marcaciones -->
<!-- ===============================================-->

function verAsistenciaPeriodoTrabInicializacion(codtra,nombretrab,fecini,fecfin,iexcodpro,iexperiodo){

    $('#modalLoadingIni').modal('show');

    document.getElementById("idTrabAsis").value=codtra;
    document.getElementById("idTrabAsisHidden").value=codtra;

    document.getElementById("trabAsis").value=nombretrab;
    document.getElementById("trabAsisHidden").value=nombretrab;

    document.getElementById("feciniAsis").value=fecini;
    document.getElementById("feciniAsisHidden").value=fecini;

    document.getElementById("fecfinAsis").value=fecfin;
    document.getElementById("fecfinAsisHidden").value=fecfin;

    document.getElementById("desfecdia").value=fecfin;
    document.getElementById("iexcodfec").value=fecfin;

    document.getElementById("iexcodpro").value=iexcodpro;
    document.getElementById("iexperiodo").value=iexperiodo;

    $.ajax({
         async: false,
         url: "traerLstTurnosModal",
         data: {
              "fecini": fecini
         },
         success: function (data) {

              $("#mesDes").text(data[0].mesDes+" "+data[0].anioDes);

              var opt = "";

              opt += "<tr>"+
                         "<td class='pt-0 pb-2'>"+
                             "<select name='id_domingo' id='id_domingo' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,1,"+codtra+")'>"+
                                 "<option value='-1' selected>Turno</option>";
                                 for (var i in data) {
                                     opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                                 }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                             "<select name='id_lunes' id='id_lunes' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,2,"+codtra+")'>"+
                                 "<option value='-1' selected>Turno</option>";
                                 for (var i in data) {
                                     opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                                 }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_martes' id='id_martes' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,3,"+codtra+")'>"+
                                  "<option value='-1' selected>Turno</option>";
                                  for (var i in data) {
                                      opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                                  }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_miercoles' id='id_miercoles' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,4,"+codtra+")'>"+
                               "<option value='-1' selected>Turno</option>";
                               for (var i in data) {
                                   opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                               }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_jueves' id='id_jueves' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,5,"+codtra+")'>"+
                              "<option value='-1' selected>Turno</option>";
                              for (var i in data) {
                                  opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                              }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_viernes' id='id_viernes' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,6,"+codtra+")'>"+
                               "<option value='-1' selected>Turno</option>";
                               for (var i in data) {
                                   opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                               }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_sabado' id='id_sabado' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,7,"+codtra+")'>"+
                                "<option value='-1' selected>Turno</option>";
                                for (var i in data) {
                                    opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                                }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'></td>"+
                     "</tr>"+
                     "<tr>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 text-warning border-bottom border-3 border-100'>Dom</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Lun</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Mar</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Mie</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Jue</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Vie</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 text-warning border-bottom border-3 border-100'>Sab</td>"+
                         "<td class='pt-2 pb-2'></td>"+
                     "</tr>";

              $("#calendarHead2").html(opt);
         }
    });

    $.ajax({
         async: false,
         url: "traerLstTurnoDiarioModal",
         data: {
              "codtra": codtra,
              "fecini": fecini,
              "fecfin": fecfin
         },
         success: function (data) {
             $("#calendarFoot2").html("");

             if(data.length > 0){

                 var opt2 = "<tr>";
                 var x=0;
                 var j=0;
                 var k=1;
                 var z=1;
                 var fecIni7="";
                 var fecfin7="";
                 var f=0;

                 for (var i in data) {
                    var inicioDiaSemana = data[0].iexcoddiasem;
                    var inib = data[0].iexcoddiasem-2;

                    if(i==0){
                        for(let l=1; l < inicioDiaSemana; l++){
                            var ini = data[i].iexcoddiasem;
                            x = x + ini;
                            var cal=31-inib;

                            opt2 += "<td class='pt-2 pb-2 ps-2 bg-200 bg-opacity-75 border border-100'>"+
                                        "<span class='ms-1 text-400 fs-0'>"+cal+"</span><br>"+
                                    "</td>";
                            j++;
                            inib=inib-1;
                        }
                    }

                    opt2 += "<td id='background"+i+"' class='pt-2 pb-2 ps-2 pe-2 bg-300 bg-opacity-50 border border-100'>"+
                               "<span id='spanDiaCalendar"+i+"' class='ms-1 text-900 fs-0'>"+data[i].diaCalendar+"</span><br>"+
                               "<span id='spanturno"+i+"' class='text-500'>["+data[i].iexflgturno+"]</span><br>"+
                               "<span id='spanfecdia"+i+"'class='text-500'>"+data[i].desfecdia2+"</span><br>"+
                               "<input type='hidden' id='ip_desfecdia"+i+"' value='"+data[i].desfecdia2+"' />"+
                               "<span id='spandesiniturno"+i+"' class='text-500'>Turno: "+data[i].desiniturno+" - "+data[i].desfinturno+"</span><br>";

                               if(data[i].desiniasist=="undefined" || data[i].desiniasist==null || data[i].desiniasist==""){
                                    opt2 += "<span class='text-500'></span>";
                               }else{
                                    opt2 += "<span class='text-500'>Asistencia: "+data[i].desiniasist+" - "+data[i].desfinasist+"</span><br>";
                               }

                       //opt2 += "<a id='popoverVer"+i+"' class='bg-soft btn btn-sm text-400 bg-white pt-0 pb-1 fs--1 mt-1 fw-semi-bold border border-1 border-300' title='Gestión de marcaciones' data-bs-toggle='popover' data-bs-html='true' data-bs-content=''><span id='dotv"+i+"' class='text-success fs-1 me-1'>&#x2022;</span>Marcación</a>";
                       opt2 += "<a id='popoverVer"+i+"' class='btn btn-sm text-success bg-white opacity-75 pt-2 ps-2 pe-2 pb-1 fs--1 mt-1 border border-1 border-300 rounded-circle ' title='Gestión de marcaciones' data-bs-toggle='popover' data-bs-html='true' data-bs-content=''>M</a>";
                       opt2 += "<a id='popoverAutoMark"+i+"' onclick='automark("+i+","+codtra+","+data[i].iexcodturno+")' class='ms-1 rounded-4 bg-soft btn btn-sm text-danger bg-white pt-2 mt-1 pb-2 fs--2 ps-2 pe-2 border border-1 border-300' >Auto-marca</a>";

                    j++;
                    f++;

                    if(z==1){
                        fecIni7 = data[i].desfecdia2;
                    }else if(j % 7 == 0){
                        f=1;
                        fecfin7 = data[i].desfecdia2;
                        z=0;

                        opt2 += "<td class='pt-0 pb-2 mt-2'>"+
                                     "<select name='id_row' id='id_row' style='width: 120px;' class='mt-2 form-select form-select-sm' onchange='program_tur_row(this,"+codtra+","+j+")'>"+
                                       "<option value='-1' selected>Turno</option>";
                                            opt2 += listarTurnosCombo();
                            opt2 += "</select>"+
                                 "<input id='fecIni7"+j+"' type='hidden' value="+fecIni7+">"+
                                 "<input id='fecfin7"+j+"' type='hidden' value="+fecfin7+">"+
                                 "</td>";

                        opt2 += "</tr>"+
                                "<tr>";
                        k++;
                    }

                    x = x+1;
                    z=z+1;
                    opt2 += "</td>";
                 }

                 var fa=f-1
                 var fb=1;

                 for(let l=f; l <= 7; l++){

                     opt2 += "<td class='pt-2 pb-2 ps-2 bg-200 bg-opacity-75 border border-100'>"+
                                 "<span class='ms-1 text-400 fs-0'>"+fb+"</span><br>"+
                             "</td>";
                     fb++;
                 }

                 opt2 += "<td class='pt-0 pb-2'>"+
                              "<select name='id_row' id='id_row' style='width: 120px;' class=' mt-2 form-select form-select-sm' onchange='program_tur_row(this,"+codtra+","+j+")'>"+
                                "<option value='-1' selected>Turno</option>";
                                     opt2 += listarTurnosCombo();
                     opt2 += "</select>"+
                          "</td>";

                 $("#calendarBody2").html(opt2);

                 var y=0;
                 var a=0;
                 var b=1;

                 for (var i in data) {
                     var inicioDiaSemana2 = data[0].iexcoddiasem;

                     y=b-inicioDiaSemana2;
                     a=b-inicioDiaSemana2+1;

                     if(b % 7 == 0){
                         $('#spanDiaCalendar'+y).addClass('text-warning');

                         $('#popoverVer'+y).removeClass('text-success');
                         $('#popoverVer'+y).addClass('text-secondary');

                         $('#popoverAutoMark'+y).removeClass('text-danger');
                         $('#popoverAutoMark'+y).addClass('text-secondary');
                         /*$('#dotv'+y).addClass('text-warning');
                         $('#dota'+y).addClass('text-warning');*/

                         $('#spanDiaCalendar'+a).addClass('text-warning');

                         $('#popoverVer'+a).removeClass('text-success');
                         $('#popoverVer'+a).addClass('text-secondary');

                         $('#popoverAutoMark'+a).removeClass('text-danger');
                         $('#popoverAutoMark'+a).addClass('text-secondary');
                         /*$('#dotv'+a).addClass('text-warning');
                         $('#dota'+a).addClass('text-warning');*/
                     }

                     b++;

                     if(data[i].iexcodturno == 999){
                        if(data[i].iexvacaind==1){
                           $('#background'+i).addClass('bg-info');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        } else if(data[i].iexauseind==1){
                           $('#background'+i).addClass('bg-body-quaternary');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        }else if(data[i].iexpermiso==1){
                           $('#background'+i).addClass('bg-warning opacity-50');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        }else{
                           $('#background'+i).addClass('bg-warning');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        }
                    }

                    if(data[i].iexcodturno != 999){
                        if(data[i].iexvacaind==1){
                           $('#background'+i).addClass('bg-info');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        } else if(data[i].iexauseind==1){
                           $('#background'+i).addClass('bg-body-quaternary');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        }else if(data[i].iexpermiso==1){
                           $('#background'+i).addClass('bg-warning');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        }else if(data[i].iexindfalta==1){
                           $('#background'+i).removeClass('bg-opacity-50 bg-300');
                           $('#background'+i).addClass('bg-opacity-75 bg-gradient bg-warning rounded-3');

                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        }
                    }

                     traerMarcacionesAsisModal(data[i].iexcodtra,data[i].iexcodfec,i,fecini,data[i].iexcodturno);
                 }
             }else{
                 var title="<h5 style='width:300px;' class='mt-3 ms-5 text-800 col-12' >El trabajador no registró asistencias para este periodo</h5>";

                 $("#calendarFoot2").html(title);
                 $("#calendarBody2").html("");
             }
         }
    });

    setTimeout(function() {
          $('#modalLoadingIni').modal('hide');
    }, 9000);
}

function verAsistenciaPeriodoTrab(codtra,nombretrab,fecini,fecfin,iexcodpro,iexperiodo){

    document.getElementById("idTrabAsis").value=codtra;
    document.getElementById("idTrabAsisHidden").value=codtra;

    document.getElementById("trabAsis").value=nombretrab;
    document.getElementById("trabAsisHidden").value=nombretrab;

    document.getElementById("feciniAsis").value=fecini;
    document.getElementById("feciniAsisHidden").value=fecini;

    document.getElementById("fecfinAsis").value=fecfin;
    document.getElementById("fecfinAsisHidden").value=fecfin;

    document.getElementById("desfecdia").value=fecfin;
    document.getElementById("iexcodfec").value=fecfin;

    document.getElementById("iexcodpro").value=iexcodpro;
    document.getElementById("iexperiodo").value=iexperiodo;

    $.ajax({
         async: false,
         url: "traerLstTurnosModal",
         data: {
              "fecini": fecini
         },
         success: function (data) {

              $("#mesDes").text(data[0].mesDes+" "+data[0].anioDes);

              var opt = "";

              opt += "<tr>"+
                         "<td class='pt-0 pb-2'>"+
                             "<select name='id_domingo' id='id_domingo' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,1,"+codtra+")'>"+
                                 "<option value='-1' selected>Turno</option>";
                                 for (var i in data) {
                                     opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                                 }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                             "<select name='id_lunes' id='id_lunes' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,2,"+codtra+")'>"+
                                 "<option value='-1' selected>Turno</option>";
                                 for (var i in data) {
                                     opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                                 }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_martes' id='id_martes' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,3,"+codtra+")'>"+
                                  "<option value='-1' selected>Turno</option>";
                                  for (var i in data) {
                                      opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                                  }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_miercoles' id='id_miercoles' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,4,"+codtra+")'>"+
                               "<option value='-1' selected>Turno</option>";
                               for (var i in data) {
                                   opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                               }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_jueves' id='id_jueves' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,5,"+codtra+")'>"+
                              "<option value='-1' selected>Turno</option>";
                              for (var i in data) {
                                  opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                              }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_viernes' id='id_viernes' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,6,"+codtra+")'>"+
                               "<option value='-1' selected>Turno</option>";
                               for (var i in data) {
                                   opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                               }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'>"+
                            "<select name='id_sabado' id='id_sabado' style='width: 120px;' class='form-select form-select-sm' onchange='program_tur_col(this,7,"+codtra+")'>"+
                                "<option value='-1' selected>Turno</option>";
                                for (var i in data) {
                                    opt += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
                                }
                     opt += "</select>"+
                         "</td>"+

                         "<td class='pt-0 pb-2'></td>"+
                     "</tr>"+
                     "<tr>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 text-warning border-bottom border-3 border-100'>Dom</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Lun</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Mar</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Mie</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Jue</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 border-bottom border-3 border-100'>Vie</td>"+
                         "<td class='pt-2 pb-2 text-center bg-200 bg-opacity-75 fs-0 fw-semi-bold text-800 text-warning border-bottom border-3 border-100'>Sab</td>"+
                         "<td class='pt-2 pb-2'></td>"+
                     "</tr>";

              $("#calendarHead2").html(opt);
         }
    });

    $.ajax({
         async: false,
         url: "traerLstTurnoDiarioModal",
         data: {
              "codtra": codtra,
              "fecini": fecini,
              "fecfin": fecfin
         },
         success: function (data) {
             $("#calendarFoot2").html("");

             if(data.length > 0){

                 var opt2 = "<tr>";
                 var x=0;
                 var j=0;
                 var k=1;
                 var z=1;
                 var fecIni7="";
                 var fecfin7="";
                 var f=0;

                 for (var i in data) {
                    var inicioDiaSemana = data[0].iexcoddiasem;
                    var inib = data[0].iexcoddiasem-2;

                    if(i==0){
                        for(let l=1; l < inicioDiaSemana; l++){
                            var ini = data[i].iexcoddiasem;
                            x = x + ini;
                            var cal=31-inib;

                            opt2 += "<td class='pt-2 pb-2 ps-2 bg-200 bg-opacity-75 border border-100'>"+
                                        "<span class='ms-1 text-400 fs-0'>"+cal+"</span><br>"+
                                    "</td>";
                            j++;
                            inib=inib-1;
                        }
                    }

                    opt2 += "<td id='background"+i+"' class='pt-2 pb-2 ps-2 pe-2 bg-300 bg-opacity-50 border border-100'>"+
                               "<span id='spanDiaCalendar"+i+"' class='ms-1 text-900 fs-0'>"+data[i].diaCalendar+"</span><br>"+
                               "<span id='spanturno"+i+"' class='text-500'>["+data[i].iexflgturno+"]</span><br>"+
                               "<span id='spanfecdia"+i+"'class='text-500'>"+data[i].desfecdia2+"</span><br>"+
                               "<input type='hidden' id='ip_desfecdia"+i+"' value='"+data[i].desfecdia2+"' />"+
                               "<span id='spandesiniturno"+i+"' class='text-500'>Turno: "+data[i].desiniturno+" - "+data[i].desfinturno+"</span><br>";

                               if(data[i].desiniasist=="undefined" || data[i].desiniasist==null || data[i].desiniasist==""){
                                    opt2 += "<span class='text-500'></span>";
                               }else{
                                    opt2 += "<span class='text-500'>Asistencia: "+data[i].desiniasist+" - "+data[i].desfinasist+"</span><br>";
                               }

                       //opt2 += "<a id='popoverVer"+i+"' class='bg-soft btn btn-sm text-400 bg-white pt-0 pb-1 fs--1 mt-1 fw-semi-bold border border-1 border-300' title='Gestión de marcaciones' data-bs-toggle='popover' data-bs-html='true' data-bs-content=''><span id='dotv"+i+"' class='text-success fs-1 me-1'>&#x2022;</span>Marcación</a>";
                       opt2 += "<a id='popoverVer"+i+"' class='btn btn-sm text-success bg-white opacity-75 pt-2 ps-2 pe-2 pb-1 fs--1 mt-1 border border-1 border-300 rounded-circle ' title='Gestión de marcaciones' data-bs-toggle='popover' data-bs-html='true' data-bs-content=''>M</a>";
                       opt2 += "<a id='popoverAutoMark"+i+"' onclick='automark("+i+","+codtra+","+data[i].iexcodturno+")' class='ms-1 rounded-4 bg-soft btn btn-sm text-danger bg-white pt-2 mt-1 pb-2 fs--2 ps-2 pe-2 border border-1 border-300' >Auto-marca</a>";

                    j++;
                    f++;

                    if(z==1){
                        fecIni7 = data[i].desfecdia2;
                    }else if(j % 7 == 0){
                        f=1;
                        fecfin7 = data[i].desfecdia2;
                        z=0;

                        opt2 += "<td class='pt-0 pb-2 mt-2'>"+
                                     "<select name='id_row' id='id_row' style='width: 120px;' class='mt-2 form-select form-select-sm' onchange='program_tur_row(this,"+codtra+","+j+")'>"+
                                       "<option value='-1' selected>Turno</option>";
                                            opt2 += listarTurnosCombo();
                            opt2 += "</select>"+
                                 "<input id='fecIni7"+j+"' type='hidden' value="+fecIni7+">"+
                                 "<input id='fecfin7"+j+"' type='hidden' value="+fecfin7+">"+
                                 "</td>";

                        opt2 += "</tr>"+
                                "<tr>";
                        k++;
                    }

                    x = x+1;
                    z=z+1;
                    opt2 += "</td>";
                 }

                 var fa=f-1
                 var fb=1;

                 for(let l=f; l <= 7; l++){

                     opt2 += "<td class='pt-2 pb-2 ps-2 bg-200 bg-opacity-75 border border-100'>"+
                                 "<span class='ms-1 text-400 fs-0'>"+fb+"</span><br>"+
                             "</td>";
                     fb++;
                 }

                 opt2 += "<td class='pt-0 pb-2'>"+
                              "<select name='id_row' id='id_row' style='width: 120px;' class=' mt-2 form-select form-select-sm' onchange='program_tur_row(this,"+codtra+","+j+")'>"+
                                "<option value='-1' selected>Turno</option>";
                                     opt2 += listarTurnosCombo();
                     opt2 += "</select>"+
                          "</td>";

                 $("#calendarBody2").html(opt2);

                 var y=0;
                 var a=0;
                 var b=1;

                 for (var i in data) {
                     var inicioDiaSemana2 = data[0].iexcoddiasem;

                     y=b-inicioDiaSemana2;
                     a=b-inicioDiaSemana2+1;

                     if(b % 7 == 0){
                         $('#spanDiaCalendar'+y).addClass('text-warning');

                         $('#popoverVer'+y).removeClass('text-success');
                         $('#popoverVer'+y).addClass('text-secondary');

                         $('#popoverAutoMark'+y).removeClass('text-danger');
                         $('#popoverAutoMark'+y).addClass('text-secondary');
                         /*$('#dotv'+y).addClass('text-warning');
                         $('#dota'+y).addClass('text-warning');*/

                         $('#spanDiaCalendar'+a).addClass('text-warning');

                         $('#popoverVer'+a).removeClass('text-success');
                         $('#popoverVer'+a).addClass('text-secondary');

                         $('#popoverAutoMark'+a).removeClass('text-danger');
                         $('#popoverAutoMark'+a).addClass('text-secondary');
                         /*$('#dotv'+a).addClass('text-warning');
                         $('#dota'+a).addClass('text-warning');*/
                     }

                     b++;

                     if(data[i].iexcodturno == 999){
                        if(data[i].iexvacaind==1){
                           $('#background'+i).addClass('bg-info');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        } else if(data[i].iexauseind==1){
                           $('#background'+i).addClass('bg-body-quaternary');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        }else if(data[i].iexpermiso==1){
                           $('#background'+i).addClass('bg-warning opacity-50');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        }else{
                           $('#background'+i).addClass('bg-warning');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        }
                    }

                    console.log("---------------------------------");
                    console.log("data[i].diaCalendar: "+data[i].diaCalendar);
                    console.log("i: "+i);
                    console.log("data[i].iexcodturno: "+data[i].iexcodturno);
                    console.log("data[i].iexindfalta: "+data[i].iexindfalta);

                    if(data[i].iexcodturno != 999){
                        if(data[i].iexvacaind==1){
                           $('#background'+i).addClass('bg-info');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        } else if(data[i].iexauseind==1){
                           $('#background'+i).addClass('bg-body-quaternary');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        }else if(data[i].iexpermiso==1){
                           $('#background'+i).addClass('bg-warning');
                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        }else if(data[i].iexindfalta==1){
                           $('#background'+i).removeClass('bg-opacity-50 bg-300');
                           $('#background'+i).addClass('bg-opacity-75 bg-gradient bg-warning rounded-3');

                           $('#spanDiaCalendar'+i).addClass('text-white');
                           $('#spanturno'+i).addClass('text-white');
                           $('#spanfecdia'+i).addClass('text-white');
                           $('#spandesiniturno'+i).addClass('text-white');
                        }
                    }

                     traerMarcacionesAsisModal(data[i].iexcodtra,data[i].iexcodfec,i,fecini,data[i].iexcodturno);
                 }
             }else{
                 var title="<h5 style='width:300px;' class='mt-3 ms-5 text-800 col-12' >El trabajador no registró asistencias para este periodo</h5>";

                 $("#calendarFoot2").html(title);
                 $("#calendarBody2").html("");
             }
         }
    });

    setTimeout(function() {
          $('#modalLoadingIni').modal('hide');
    }, 9000);
}

function listarTurnosCombo(){

    var htmlcombo="";

    $.ajax({
         async: false,
         url: "traerLstTurnosModal",
         data: {
              "fecini": '01/01/2024'
         },
         success: function (data) {
            for (var i in data) {
                 htmlcombo += "<option value="+data[i].iexcodturno+"> ["+data[i].iexflgturno+"] "+data[i].iexhorini+"--"+data[i].iexhorfin+" "+data[i].iexdesturno+"</option>";
             }
         }
    });

    return htmlcombo;
}

function program_tur_row(codturno,codtra,j){
   var opcion = confirm("Esta seguro de que desea cambiar masivamente el tipo de turno a toda la fila seleccionada? ");

   if (opcion == true) {
       var codturno = codturno.value;
       var fecini7 = document.getElementById("fecIni7"+j).value;
       var fecfin7 = document.getElementById("fecfin7"+j).value;

       $.ajax({
            async: false,
            url: "program_tur_row",
            data: {
               "codturno": codturno,
               "fecini7": fecini7,
               "fecfin7": fecfin7,
               "codtra": codtra
            },
            success: function (data) {
            }
       });

      var idTrabAsisHidden = document.getElementById("idTrabAsisHidden").value;
      var trabAsisHidden = document.getElementById("trabAsisHidden").value;
      var feciniAsisHidden = document.getElementById("feciniAsisHidden").value;
      var fecfinAsisHidden = document.getElementById("fecfinAsisHidden").value;
      var iexcodpro = document.getElementById("iexcodpro").value;
      var iexperiodo = document.getElementById("iexperiodo").value;
      var iexcorrel = document.getElementById("iexcorrel").value;

      $("#h5modalLoadinglabel").text("Actualizando");
      $("#spanbtnModalLoading").text("Actualizando calendario");
      $('#modalLoading').modal('show');

      verAsistenciaPeriodoTrab(idTrabAsisHidden,trabAsisHidden,feciniAsisHidden,fecfinAsisHidden,iexcodpro,iexperiodo);

      setTimeout(function() {
            $('#modalLoading').modal('hide');
      }, 4000);

      return true;
   } else {
      return false;
   }
}

function program_tur_col(codturno,dia,codtra){

    var opcion = confirm("Esta seguro de que desea cambiar masivamente el tipo de turno a toda la columna seleccionada? ");

   if (opcion == true) {
       var codturno = codturno.value;
       var fecini = document.getElementById("feciniAsisHidden").value;
       var fecfin = document.getElementById("fecfinAsisHidden").value;

       console.log("fecini: "+fecini);
       console.log("fecfin: "+fecfin);
       console.log("codturno: "+codturno);
       console.log("codtra: "+codtra);
       console.log("dia: "+dia);

       $.ajax({
            async: false,
            url: "program_tur_col",
            data: {
               "codturno": codturno,
               "fecini": fecini,
               "fecfin": fecfin,
               "codtra": codtra,
               "dia": dia
            },
            success: function (data) {
            }
       });

      var idTrabAsisHidden = document.getElementById("idTrabAsisHidden").value;
      var trabAsisHidden = document.getElementById("trabAsisHidden").value;
      var feciniAsisHidden = document.getElementById("feciniAsisHidden").value;
      var fecfinAsisHidden = document.getElementById("fecfinAsisHidden").value;
      var iexcodpro = document.getElementById("iexcodpro").value;
      var iexperiodo = document.getElementById("iexperiodo").value;
      var iexcorrel = document.getElementById("iexcorrel").value;

      $("#h5modalLoadinglabel").text("Actualizando");
      $("#spanbtnModalLoading").text("Actualizando calendario");
      $('#modalLoading').modal('show');

      verAsistenciaPeriodoTrab(idTrabAsisHidden,trabAsisHidden,feciniAsisHidden,fecfinAsisHidden,iexcodpro,iexperiodo);

      setTimeout(function() {
            $('#modalLoading').modal('hide');
      }, 4000);

      return true;
   } else {
      return false;
   }
}

function traerMarcacionesAsisModal(codtra,codfec,ind,fecini,codigoTurnoSeleccionado){

    $.ajax({
         async: true,
         url: "traerMarcacionesAsisModal",
         data: {
             "codtra": codtra,
             "codfec": codfec
             },
         success: function (data) {

            var html="<div  class='bg-100 border-top border-200 pt-3 pe-3 pb-3 ps-3 col-12'>"+
                        "<h6 class='text-500'>Datos de turno</h6>"+
                        "<form class='row g-1 mb-0 needs-validation' method='POST' action='' novalidate >"+
                            "<input id='indice"+ind+"' type='hidden' value="+ind+"/>"+
                            "<div class='col-sm-6 col-md-12'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Fecha: </div>"+
                                "<div class='fs--1 text-600'>"+data.desfecdia2+" ["+data.iexcodfec+"]</div>"+
                                "<input id='ipHiddenDesfecdia"+ind+"' type='hidden' value="+data.desfecdia2+">"+
                                "<input id='ipHiddeniexcodfec"+ind+"' type='hidden' value="+data.iexcodfec+">"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-12'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Turno: </div>";

                                traerLstTurnosModal(fecini,codigoTurnoSeleccionado,ind,data.desfecdia2,data.iexcodfec);

                                var opcionPopoverA = sessionStorage.getItem("opcionPopoverA");

                                html += opcionPopoverA;

                    html += "</div>"+
                            "<div class='col-sm-6 col-md-12'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Turno: </div>"+
                                "<div class='fs--1 text-600'>"+data.iexiniturno+" - "+data.iexfinturno+"</div>"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-12'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Asistencia: </div>"+
                                "<div class='fs--1 text-600'>"+data.iexiniasist+" - "+data.iexfinasist+"</div>"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-6'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Falta: </div>"+
                                "<div class='fs--1 text-600'>"+data.iexindfalta+"</div>"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-6'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Feriado: </div>"+
                                "<div class='fs--1 text-600'>"+data.iexindferiado+"</div>"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-6'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Ingreso antes: </div>"+
                                "<div class='fs--1 text-600'>"+data.iexhrsantes+" "+data.iexminantes+"</div>"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-6'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Tardanza: </div>"+
                                "<div class='fs--1 text-600'>"+data.iexhrstarde+" "+data.iexmintarde+"</div>"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-6'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Total de horas: </div>"+
                                "<div class='fs--1 text-600'>"+data.iexhrstotal+" "+data.iexmintotal+"</div>"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-6'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Salida antes: </div>"+
                                "<div class='fs--1 text-600'>"+data.iexhrssale_antes+" "+data.iexminsale_antes+"</div>"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-6'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Salida posterior: </div>"+
                                "<div class='fs--1 text-600'>"+data.iexhrspost+" "+data.iexminpost+"</div>"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-6'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Vacaciones: </div>"+
                                "<div class='fs--1 text-600'>"+data.iexvacaind+"</div>"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-6'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Ausentismo: </div>"+
                                "<div class='fs--1 text-600'>"+data.iexauseind+"</div>"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-6'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Permiso: </div>"+
                                "<div class='fs--1 text-600'>"+data.iexpermiso+"</div>"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-6'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Permiso horas: </div>"+
                                "<div class='fs--1 text-600'>"+data.iexhriniperm+" "+data.iexhrfinperm+"</div>"+
                            "</div>"+
                            "<div id='alert' class='alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center' role='alert' style='display:none !important;'>"+
                                "<span class='fa-regular fa-check-circle text-success fs-0 me-3'></span>"+
                                "<p class='mb-0 fw-semi-bold text-1000 col-10'>Se grabó exitosamente los cambios <a href='#'>No olvidar cerrar el modal de asistencias y volver a abrir para que se refelejen los cambios</a></p>"+
                                "<button class='btn-close ms-1 fs-0' type='button'' data-bs-dismiss='alert'' aria-label='Close'></button>"+
                            "</div>";

                    html += "<div class='row col-12 mt-3 ps-0'>"+
                                "<div class='col-auto pe-0' id='grabarClick"+ind+"' >"+
                                    "<a class='btn btn-sm btn-primary mt-1 ms-0'><span class='fa-regular fa-floppy-disk me-1'></span>Grabar</a>"+
                                    "<a class='btn btn-sm btn-phoenix-secondary mt-1 ms-1' onclick='calificarTurnoDia("+ind+");' ><span class='fa-solid fa-star text-yellow me-1'></span>Calificar</a>"+
                                "</div>"+
                            "</div>"+
                            "<div class='table-responsive scrollbar mt-3'>"+
                                "<table class='border border-200 table table-sm fs--1 mb-0'>"+
                                    "<thead>"+
                                        "<tr>"+
                                            "<th>NRO</th>"+
                                            "<th>FECHA Y HORA</th>"+
                                        "</tr>"+
                                    "</thead>"+
                                    "<tbody id='tbodyNroFechaHora'>";
                                        if(traertLstNroFechaHora(codtra,data.iexcodfec)==""){
                                            html+= "<td class='ms-2'>No hay data</td>";
                                        }else{
                                           html+= traertLstNroFechaHora(codtra,data.iexcodfec);
                                        }
                            html += "</tbody>"+
                                "</table>"+
                            "</div>"+
                            "<div class='mt-4'>"+
                                "<p class='fw-semi-bold'>Agregar marcaciones manuales</p>"+
                                "<div class='col-sm-6 col-md-6'>"+
                                 	"<label class='form-label fs--1 text-1000 ps-0 text-none mb-2' >Fecha</label><span class='uil uil-calendar-alt flatpickr-icon text-700'></span>"+
                                 	"<input class='form-control ' name='fecMan' id='fecMan"+ind+"' type='text' placeholder='dd/mm/yyyy' required />"+
                                "</div>"+
                                "<div class='col-sm-6 col-md-6 mt-2'>"+
                                    "<label class='form-label fs--1 text-1000 ps-0 text-none mb-2' >Hora</label>"+
                                    "<input class='form-control ' name='horaMan' id='horaMan"+ind+"'' type='text' placeholder='24:00' required />"+
                                "</div>"+
                                "<div class='row col-12 mt-3 ps-0'>"+
                                    "<div class='col-12 pe-0' id='grabarClick2"+ind+"' >"+
                                        "<a class='btn btn-sm btn-primary mt-1 ms-0' onclick='grabarMarcManual("+ind+");'><span class='fa-regular fa-floppy-disk me-1'></span>Grabar</a>"+
                                    "</div>"+
                                    "<div class='col-12 mt-3 m-3'>";
                                        if(traerMarcManualData(codtra,data.iexcodfec,ind)==""){
                                            html += "<td class='ms-2'>No hay data</td>";
                                        }else{
                                           html += traerMarcManualData(codtra,data.iexcodfec,ind);
                                        }

                            html += "</div>"+
                                    "<div class='col-12 ps-0 d-flex justify-content-end'>"+
                                        "<a class='btn btn-sm btn-phoenix-secondary mt-1 ms-1' onclick='cerrarPopover("+ind+");'>Cerrar</a>"+
                                    "</div>"+
                                "</div>"+
                            "</div>"+
                        "</form>"+
                     "</div>";

            $('#popoverVer'+ind).popover({
               container: "body",
               html: true,
               sanitize: false,
               content: function () {
                    return html;
               }
            })
         }
    });
}

function traerLstTurnosModal(fecini,codigoTurnoSeleccionado,ind,desfecdia,iexcodfec){
   var sel="";

   $.ajax({
     async: false,
     url: "traerLstTurnosModal",
     data: {
          "fecini": fecini
     },
     success: function (data2) {
          sel += "<select id='cod_turno"+ind+"' onchange='setearVariableCodTurnoSes("+ind+")' style='width:205px; font-size:10px;' class='form-select form-select-sm' name='iexcodcon'  required>"+
                    "<option value='' selected >Turno</option>";

          for (var i in data2){

              sel += "<option value="+data2[i].iexcodturno+"";
                 if(data2[i].iexcodturno==codigoTurnoSeleccionado){
                    sel += "' selected";
                 }else{
                    sel += " ";
                 }
              sel += ">";
              sel += ""+data2[i].iexhorini+"-"+data2[i].iexhorfin+" "+data2[i].iexdesturno+"</option>";
          }
          sel +="</select>";

          sessionStorage.setItem("opcionPopoverA",sel);
     }
   });
}

function cerrarPopover(ind){
    $("#popoverVer"+ind).popover('hide');
}

function setearVariableCodTurnoSes(ind,desfecdia){

    var codTurnoSelected = document.getElementById("cod_turno"+ind).value;
    var desfecdia = document.getElementById("ipHiddenDesfecdia"+ind).value;
    var iexcodfec = document.getElementById("ipHiddeniexcodfec"+ind).value;

    var onclick2="";
    var btn="";

    if(codTurnoSelected == "" || codTurnoSelected==null){
        btn+= "<a class='btn btn-sm btn-primary mt-2 ms-1'><span class='fa-regular fa-floppy-disk me-1'></span>Grabar</a>"+
              "<a class='btn btn-sm btn-phoenix-secondary mt-2 ms-1' onclick='calificarTurnoDia("+ind+");' ><span class='fa-regular fa-star me-1'></span>Calificar</a>"+
              "<input type='hidden' id='iexcodfec"+ind+"' value="+iexcodfec+" />"+
              "<input type='hidden' id='desfecdia"+ind+"' value="+desfecdia+" />"+
              "<input type='hidden' id='codTurnoSelected"+ind+"' value="+codTurnoSelected+" />";
    }else{
        btn+= "<a class='btn btn-sm btn-primary mt-2 ms-1' onclick='grabarTurnoDia("+ind+");'><span class='fa-regular fa-floppy-disk me-1'></span>Grabar</a>"+
              "<a class='btn btn-sm btn-phoenix-secondary mt-2 ms-1' onclick='calificarTurnoDia("+ind+");' ><span class='fa-regular fa-star me-1'></span>Calificar</a>"+
              "<input type='hidden' id='iexcodfec"+ind+"' value="+iexcodfec+" />"+
              "<input type='hidden' id='desfecdia"+ind+"' value="+desfecdia+" />"+
              "<input type='hidden' id='codTurnoSelected"+ind+"' value="+codTurnoSelected+" />";
    }

    $("#grabarClick"+ind).html(btn);
}

function grabarTurnoDia(ind){

    var iexcodfec = document.getElementById("iexcodfec"+ind).value;
    var desfecdia = document.getElementById("desfecdia"+ind).value;
    var codTurnoSelected = document.getElementById("cod_turno"+ind).value;
    var idTrabAsis= document.getElementById("idTrabAsis").value;

    $.ajax({
         async: false,
         url: "updateDatosTurnoDia",
         data: {
              "iexcodfec": iexcodfec,
              "desfecdia": desfecdia,
              "codTurnoSelected": codTurnoSelected,
              "idTrabAsis": idTrabAsis
         },
         success: function (data) {
            mostrarAlert();
         }
    });

    $("#popoverVer"+ind).popover('hide');
    traerTurnos();
}

function calificarTurnoDia(ind){

    var iexcodfec = document.getElementById("ipHiddeniexcodfec"+ind).value;
    var idTrabAsis= document.getElementById("idTrabAsis").value;
    var desfecdia = document.getElementById("ipHiddenDesfecdia"+ind).value;
    var fecini= document.getElementById("feciniAsisHidden").value;
    var codTurnoSelected = document.getElementById("cod_turno"+ind).value;

    $.ajax({
         async: false,
         url: "calififcarTurnoDia",
         data: {
              "desfecdia": desfecdia,
              "idTrabAsis": idTrabAsis
         },
         success: function (data) {
            mostrarAlert();
         }
    });

    $("#popoverVer"+ind).popover('hide');
    traerTurnos();
}

function grabarMarcManual(ind){
    var fecMan = document.getElementById("fecMan"+ind).value;
    var horaMan= document.getElementById("horaMan"+ind).value;

    if (fecMan == "") {
        alert("Ingrese una fecha en el formato dd/mm/yyyy");
    	return;
    }

    if (horaMan == "") {
        alert("Ingrese una hora en formato 24 h hh:mm");
    	return;
    }

    var desfecdia = document.getElementById("ipHiddenDesfecdia"+ind).value;
    var iexcodfec = document.getElementById("ipHiddeniexcodfec"+ind).value;
    var idTrabAsis= document.getElementById("idTrabAsis").value;

    $.ajax({
         async: false,
         url: "grabarMarcManual",
         data: {
              "fecMan": fecMan,
              "horaMan": horaMan,
              "desfecdia": desfecdia,
              "iexcodfec": iexcodfec,
              "idTrabAsis": idTrabAsis
         },
         success: function (data) {
            mostrarAlert();
         }
    });

    $("#popoverVer"+ind).popover('hide');
    traerTurnos();
}

function mostrarAlert(){
    var div=document.getElementById('alert');
    div.style.display = '';

    setTimeout(function() {
      $("#alerts").hide(6000);
    }, 3000);
}

function verTurnos(){

        var idTrabAsisHidden = document.getElementById("idTrabAsisHidden").value;
        var trabAsisHidden = document.getElementById("trabAsisHidden").value;
        var feciniAsisHidden = document.getElementById("feciniAsisHidden").value;
        var fecfinAsisHidden = document.getElementById("fecfinAsisHidden").value;
        var iexcodpro = document.getElementById("iexcodpro").value;
        var iexperiodo = document.getElementById("iexperiodo").value;

        $("#h5modalLoadinglabel").text("Obteniendo turnos");
        $("#spanbtnModalLoading").text("Actualizando calendario");
        $('#modalLoading').modal('show');

        verAsistenciaPeriodoTrab(idTrabAsisHidden,trabAsisHidden,feciniAsisHidden,fecfinAsisHidden,iexcodpro,iexperiodo);

        setTimeout(function() {
        	  $('#modalLoading').modal('hide');
        }, 3000);
}

function traerTurnos(){

        var idTrabAsisHidden = document.getElementById("idTrabAsisHidden").value;
        var trabAsisHidden = document.getElementById("trabAsisHidden").value;
        var feciniAsisHidden = document.getElementById("feciniAsisHidden").value;
        var fecfinAsisHidden = document.getElementById("fecfinAsisHidden").value;
        var iexcodpro = document.getElementById("iexcodpro").value;
        var iexperiodo = document.getElementById("iexperiodo").value;

        $("#h5modalLoadinglabel").text("Obteniendo turnos");
        $("#spanbtnModalLoading").text("Actualizando calendario");
        $('#modalLoading').modal('show');

        verAsistenciaPeriodoTrab(idTrabAsisHidden,trabAsisHidden,feciniAsisHidden,fecfinAsisHidden,iexcodpro,iexperiodo);

        setTimeout(function() {
        	  $('#modalLoading').modal('hide');
        }, 3000);
}

function programarTurnos(){

    var opcion = confirm("Esta seguro que desea programar los turnos?");

    if (opcion == true) {

        var idTrabAsisHidden = document.getElementById("idTrabAsisHidden").value;
        var trabAsisHidden = document.getElementById("trabAsisHidden").value;
        var feciniAsisHidden = document.getElementById("feciniAsisHidden").value;
        var fecfinAsisHidden = document.getElementById("fecfinAsisHidden").value;
        var iexcodpro = document.getElementById("iexcodpro").value;
        var iexperiodo = document.getElementById("iexperiodo").value;

        $("#h5modalLoadinglabel").text("Programando los turnos");
        $("#spanbtnModalLoading").text("Actualizando calendario");
        $('#modalLoading').modal('show');

        $.ajax({
        	 async: false,
        	 url: "programarTurnosAsis",
        	 data: {
        		"fecini": feciniAsisHidden,
        		"fecfin": fecfinAsisHidden,
        		"codtra": idTrabAsisHidden
        		},
        	 success: function (data) {
        	 }
        });

        verAsistenciaPeriodoTrab(idTrabAsisHidden,trabAsisHidden,feciniAsisHidden,fecfinAsisHidden,iexcodpro,iexperiodo);

        setTimeout(function() {
              $('#modalLoading').modal('hide');
        }, 6000);

       return true;
    } else {
        return false;
    }
}

function calificar(){

    var opcion = confirm("Esta seguro que desea calificar todo?");

    if (opcion == true) {

        var idTrabAsisHidden = document.getElementById("idTrabAsisHidden").value;
        var trabAsisHidden = document.getElementById("trabAsisHidden").value;
        var feciniAsisHidden = document.getElementById("feciniAsisHidden").value;
        var fecfinAsisHidden = document.getElementById("fecfinAsisHidden").value;
        var iexcodpro = document.getElementById("iexcodpro").value;
        var iexperiodo = document.getElementById("iexperiodo").value;

        $("#h5modalLoadinglabel").text("Calificando todas las asistencias");
        $("#spanbtnModalLoading").text("Actualizando calendario");
        $('#modalLoading').modal('show');

        $.ajax({
        	 async: false,
        	 url: "calificarTurnosGeneralAsis",
        	 data: {
        		"fecini": feciniAsisHidden,
        		"fecfin": fecfinAsisHidden,
        		"codtra": idTrabAsisHidden
        		},
        	 success: function (data) {
        	 }
        });

        verAsistenciaPeriodoTrab(idTrabAsisHidden,trabAsisHidden,feciniAsisHidden,fecfinAsisHidden,iexcodpro,iexperiodo);

        setTimeout(function() {
              $('#modalLoading').modal('hide');
        }, 8000);

        return true;
    } else {
        return false;
    }
}

function borrarTodo(){

    var opcion = confirm("Esta seguro que desea borrar todas las asistencias registradas?");

    if (opcion == true) {

        var idTrabAsisHidden = document.getElementById("idTrabAsisHidden").value;
        var trabAsisHidden = document.getElementById("trabAsisHidden").value;
        var feciniAsisHidden = document.getElementById("feciniAsisHidden").value;
        var fecfinAsisHidden = document.getElementById("fecfinAsisHidden").value;
        var iexcodpro = document.getElementById("iexcodpro").value;
        var iexperiodo = document.getElementById("iexperiodo").value;

        /*$('#iconspinner').addClass('text-danger');
        $('#btnFooter').addClass('text-danger');
        $('#btnFooter').addClass('btn btn-sm btn-phoenix-danger');*/

        $('#modalLoadingBorrar').modal('show');

        $.ajax({
        	 async: false,
        	 url: "borrarTodoTurnosGeneralAsis",
        	 data: {
        		"fecini": feciniAsisHidden,
        		"fecfin": fecfinAsisHidden,
        		"codtra": idTrabAsisHidden
        		},
        	 success: function (data) {

        	 }
        });

        verAsistenciaPeriodoTrab(idTrabAsisHidden,trabAsisHidden,feciniAsisHidden,fecfinAsisHidden,iexcodpro,iexperiodo);

        setTimeout(function() {
              $('#modalLoadingBorrar').modal('hide');
        }, 8000);

        return true;
    } else {
        return false;
    }
}

function consolidar(){

    var opcion = confirm("Esta seguro que desea consolidar?");

    if (opcion == true) {

        var idTrabAsisHidden = document.getElementById("idTrabAsisHidden").value;
        var trabAsisHidden = document.getElementById("trabAsisHidden").value;
        var feciniAsisHidden = document.getElementById("feciniAsisHidden").value;
        var fecfinAsisHidden = document.getElementById("fecfinAsisHidden").value;
        var iexcodpro = document.getElementById("iexcodpro").value;
        var iexperiodo = document.getElementById("iexperiodo").value;
        var iexcorrel = document.getElementById("iexcorrel").value;

        $("#h5modalLoadinglabel").text("Consolidando");
        $("#spanbtnModalLoading").text("Actualizando calendario");
        $('#modalLoading').modal('show');

        $.ajax({
        	 async: false,
        	 url: "consolidarGeneralAsis",
        	 data: {
        		"codtra": idTrabAsisHidden,
        		"iexcodpro": iexcodpro,
        		"iexperiodo": iexperiodo,
        		"iexcorrel": iexcorrel
        		},
        	 success: function (data) {

        	 }
        });

        verAsistenciaPeriodoTrab(idTrabAsisHidden,trabAsisHidden,feciniAsisHidden,fecfinAsisHidden,iexcodpro,iexperiodo);

        setTimeout(function() {
              $('#modalLoading').modal('hide');
        }, 8000);

        return true;
    } else {
        return false;
    }
}

function reporteAsistencias(){

    var idTrabAsisHidden = document.getElementById("idTrabAsisHidden").value;
    var trabAsisHidden = document.getElementById("trabAsisHidden").value;
    var feciniAsisHidden = document.getElementById("feciniAsisHidden").value;
    var fecfinAsisHidden = document.getElementById("fecfinAsisHidden").value;
    var iexcodpro = document.getElementById("iexcodpro").value;
    var iexperiodo = document.getElementById("iexperiodo").value;
    var iexcorrel = document.getElementById("iexcorrel").value;
    var iexcodcia = document.getElementById("codcia").value;

    var feciniProcesada=feciniAsisHidden.replaceAll("/", '-');
    var fecfinProcesada=fecfinAsisHidden.replaceAll("/", '-');

    console.log("iexcodcia: "+iexcodcia);
    console.log("iexcodpro: "+iexcodpro);
    console.log("iexperiodo: "+iexperiodo);
    console.log("iexcorrel: "+iexcorrel);
    console.log("feciniProcesada: "+feciniProcesada);
    console.log("fecfinProcesada: "+fecfinProcesada);

    //var nombrejasper="AsistEmpTra";
    var nombrejasper="asistencia";
    var params="5UP_CODPRO="+iexcodpro+"UP_NROPER="+iexperiodo+"UP_CORREL="+iexcorrel+"UP_FECINI="+feciniProcesada+"UP_FECFIN="+fecfinProcesada;

    document.getElementById("btnReportAsis").href="AWSorFTP_flgsource@verReportePDF@"+iexcodcia+"@"+idTrabAsisHidden+"@null@null@"+nombrejasper+"@"+params+"@null@null@null";
}

function verMarcaciones(){

    var idTrabAsisHidden = document.getElementById("idTrabAsisHidden").value;
    var feciniAsisHidden = document.getElementById("feciniAsisHidden").value;
    var fecfinAsisHidden = document.getElementById("fecfinAsisHidden").value;

    $.ajax({
         async: false,
         url: "traerDataDeIngresosYSalidas",
         data: {
            "codtra": idTrabAsisHidden,
            "fecini": feciniAsisHidden,
            "fecfin": fecfinAsisHidden
            },
         success: function (data) {

            var opt = "";

              opt += "<div class='scrollbar overflow-auto' style='height:0px !important;'>"+
                          "<div>"+
                                "<tr class='border border-200'>"+
                                  "<th class='sort white-space-nowrap align-middle ps-3 pe-3 text-uppercase fs--1' scope='col' data-sort='order' >ORDEN</th>"+
                                  "<th class='sort align-middle text-center ps-5 pe-5 text-uppercase fs--1' scope='col' data-sort='total'>FECHA</th>"+
                                  "<th class='sort align-middle text-center ps-5 pe-5 text-uppercase fs--1' scope='col' data-sort='total'>HORA</th>"+
                                  "<th class='sort align-middle text-center white-space-nowrap ps-3 pe-3 text-uppercase fs--1' scope='col' data-sort='payment_status' >TIPO</th>"+
                                  "<th class='sort align-middle text-center white-space-nowrap ps-3 pe-3 text-uppercase fs--1' scope='col' data-sort='payment_status' >FUENTE</th>"+
                                  "<th class='sort text-end text-center align-middle ps-3 pe-3 text-uppercase fs--1' scope='col'></th>"+
                                "</tr>"+
                          "</div>"+
                          "<div class='list' id='customer-order-table-body'>";

                              var j = 1;

                              for (var i in data) {

                                 opt +=	"<tr class='hover-actions-trigger btn-reveal-trigger position-static'>"+
                                          "<td class='align-middle white-space-nowrap fs--1 ps-6 pe-3'><a class='fw-semi-bold' href='#!'><a href='#'>"+j+"</a></td>"+
                                          "<td class='align-middle text-center fw-semi-bold fs--1 ps-3 pe-3 text-1000'><span class='fa-regular fa-calendar fs-0 me-2'></span>"+data[i].fechaEnLetras+"</td>"+
                                          "<td class='align-middle text-center fw-semi-bold fs--1 ps-3 pe-3 text-1000'><span class='fa-regular fa-clock text-primary fs-0 me-2'></span>"+data[i].hora+"</td>";

                                          if(data[i].tipmarka == 'Ingreso'){
                                            opt += "<td class='align-middle white-space-nowrap text-center fs--1 text-700 ps-3 pe-3'><span class='badge badge-phoenix fs--2 badge-phoenix-success'><span class='badge-label'><span class='me-2 fa-solid fa-arrow-up'></span>"+data[i].tipmarka+"</span></td>";
                                          }

                                          if(data[i].tipmarka == 'Salida'){
                                            opt += "<td class='align-middle white-space-nowrap text-center fs--1 text-700 ps-3 pe-3'><span class='badge badge-phoenix fs--2 badge-phoenix-danger'><span class='badge-label'><span class='me-2 fa-solid fa-arrow-down'></span>"+data[i].tipmarka+"</span></td>";
                                          }

                                          if(data[i].iexusucrea == undefined){
                                              opt+="<td class='align-middle text-center fw-semi-bold fs--1 ps-3 pe-3 text-1000'></td>";
                                          }else{
                                              opt+="<td class='align-middle text-center fw-semi-bold fs--1 ps-3 pe-3 text-1000'>"+data[i].iexusucrea+"</td>";
                                          }

                                     opt += "<td class='align-middle white-space-nowrap text-end pe-0 ps-5'>"+
                                            "<div class='font-sans-serif btn-reveal-trigger position-static'>"+
                                              "<button class='btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2' type='button' data-bs-toggle='dropdown' data-boundary='window' aria-haspopup='true' aria-expanded='false' data-bs-reference='parent'><span class='fas fa-ellipsis-h fs--2'></span></button>"+
                                              "<div class='dropdown-menu dropdown-menu-end py-2'>"+
                                                "<a class='dropdown-item' href='#'>Detalle</a>"+
                                                "<div class='dropdown-divider'></div>"+
                                                "<a class='dropdown-item text-danger' href='#'>Remove</a>"+
                                              "</div>"
                                            "</div>"+
                                          "</td>"+
                                        "</tr>";
                                 j++;
                              }
                   opt += "</div>"+
                     "</div>";

            $("#calendarHead2").html("");
            $("#calendarBody2").html(opt);
            $("#calendarFoot2").html("");
         }
    });
}

function traertLstNroFechaHora(codtra,iexcodfec){

    var html2 = "";

    $.ajax({
         async: false,
         url: "traertLstNroFechaHora",
         data: {
            "codtra": codtra,
            "codfec": iexcodfec.trim()
            },
         success: function (data) {
             for (var i in data) {

                 html2 += "<tr class='border border-200'>"+
                             "<td class='ps-3'>"+i+"</td>"+
                             "<td class='ps-3'>"+data[i].iexfechamarks+"</td>"+
                          "</tr>";
             }
         }
    });

    return html2;
}

function traerMarcManualData(codtra,iexcodfec,ind){

    var html3 = "";

    $.ajax({
         async: false,
         url: "traerMarcManualData",
         data: {
            "codtra": codtra,
            "codfec": iexcodfec.trim()
            },
         success: function (data) {
             for (var i in data) {

                 html3 += "<tr>"+
                             "<td class='ps-3 pe-3'>"+data[i].iexfecmarkas+"</td>"+
                             "<input type='hidden' id='iexfecmarkasIp_"+i+"_"+ind+"' value='"+data[i].iexfecmarkas+"' />"+
                             "<td class='ms-3'><a class='ms-2' href='#' onclick='deleteMarcMan("+codtra+","+i+","+ind+")'>x</a></td></br>"+
                          "</tr>";
             }
         }
    });

    return html3;
}

function automark(ind,codtra,iexcodturno){

    var opcion = confirm("Esta seguro que desea generar una marcación automática/ aleatoria? ");

    if (opcion == true) {
        var desfecdia = document.getElementById("ip_desfecdia"+ind).value;

        $.ajax({
             async: false,
             url: "automark",
             data: {
                "codtra": codtra,
                "iexcodturno": iexcodturno,
                "desfecdia": desfecdia
                },
             success: function (data) {

             }
        });

       var idTrabAsisHidden = document.getElementById("idTrabAsisHidden").value;
       var trabAsisHidden = document.getElementById("trabAsisHidden").value;
       var feciniAsisHidden = document.getElementById("feciniAsisHidden").value;
       var fecfinAsisHidden = document.getElementById("fecfinAsisHidden").value;
       var iexcodpro = document.getElementById("iexcodpro").value;
       var iexperiodo = document.getElementById("iexperiodo").value;
       var iexcorrel = document.getElementById("iexcorrel").value;

       $("#h5modalLoadinglabel").text("Actualizando");
       $("#spanbtnModalLoading").text("Actualizando calendario");
       $('#modalLoading').modal('show');

       verAsistenciaPeriodoTrab(idTrabAsisHidden,trabAsisHidden,feciniAsisHidden,fecfinAsisHidden,iexcodpro,iexperiodo);

       setTimeout(function() {
             $('#modalLoading').modal('hide');
       }, 4000);

       return true;
    } else {
       return false;
    }
}

function deleteMarcMan(codtra,a,ind){
    var opcion = confirm("Esta seguro que desea eliminar esta marcación? ");

    if (opcion == true) {
        var iexcodfec = document.getElementById("ipHiddeniexcodfec"+ind).value;
        var fechadel = document.getElementById("iexfecmarkasIp_"+a+"_"+ind).value;

        $.ajax({
             async: false,
             url: "deleteMarcMan",
             data: {
                "codtra": codtra,
                "iexcodfec": iexcodfec,
                "fechadel": fechadel
             },
             success: function (data) {

             }
        });

        $("#popoverVer"+ind).popover('hide');

       var idTrabAsisHidden = document.getElementById("idTrabAsisHidden").value;
       var trabAsisHidden = document.getElementById("trabAsisHidden").value;
       var feciniAsisHidden = document.getElementById("feciniAsisHidden").value;
       var fecfinAsisHidden = document.getElementById("fecfinAsisHidden").value;
       var iexcodpro = document.getElementById("iexcodpro").value;
       var iexperiodo = document.getElementById("iexperiodo").value;
       var iexcorrel = document.getElementById("iexcorrel").value;

       $("#h5modalLoadinglabel").text("Actualizando");
       $("#spanbtnModalLoading").text("Actualizando calendario");
       $('#modalLoading').modal('show');

       verAsistenciaPeriodoTrab(idTrabAsisHidden,trabAsisHidden,feciniAsisHidden,fecfinAsisHidden,iexcodpro,iexperiodo);

       setTimeout(function() {
             $('#modalLoading').modal('hide');
       }, 4000);

       return true;
    } else {
       return false;
    }
}