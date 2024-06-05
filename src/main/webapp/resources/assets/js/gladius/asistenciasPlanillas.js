
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
             console.log("success!: data.length: "+data.length);
             $("#calendarFoot2").html("");

             if(data.length > 0){
                 //console.log("data[0].desfecdia: "+data[0].desfecdia);
                 //console.log("data[0].desiniturno: "+data[0].desiniturno);

                 var opt2 = "<tr>";
                 var x=0;
                 var j=1;

                 for (var i in data) {
                    //console.log("ingreso al for y muestro la data...");

                    if(i==0){
                        var ini = data[i].iexcoddiasem;
                        //console.log("data[i].iexcoddiasem: "+data[i].iexcoddiasem);
                        x = x + ini;

                        opt2 += "<td class='pt-2 pb-2 ps-2 bg-200 bg-opacity-75 border border-100'>"+
                                    "<span class='ms-1 text-400 fs-0'>31</span><br>"+
                                "</td>";

                        /*for(var n=1; n<ini; n+=1){
                            opt2 += "<td>"+
                                    "</td>";
                        }*/
                    }

                    opt2 += "<td id='background"+i+"' class='pt-2 pb-2 ps-2 pe-2 bg-300 bg-opacity-50 border border-100'>"+
                               "<span id='spanDiaCalendar"+i+"' class='ms-1 text-900 fs-0'>"+data[i].diaCalendar+"</span><br>"+
                               "<span id='spanturno"+i+"' class='text-500'>["+data[i].iexflgturno+"]</span><br>"+
                               "<span id='spanfecdia"+i+"'class='text-500'>"+data[i].desfecdia+"</span><br>"+
                               "<span id='spandesiniturno"+i+"' class='text-500'>"+data[i].desiniturno+" - "+data[i].desfinturno+"</span><br>";

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

                       //opt2 += "<a id='popoverVer"+i+"' class='bg-soft btn btn-sm text-400 bg-white pt-0 pb-1 fs--1 mt-1 fw-semi-bold border border-1 border-300' title='Gestión de marcaciones' data-bs-toggle='popover' data-bs-html='true' data-bs-content=''><span id='dotv"+i+"' class='text-success fs-1 me-1'>&#x2022;</span>Marcación</a>";
                       opt2 += "<a id='popoverVer"+i+"' class='btn btn-sm text-success bg-white opacity-75 pt-2 ps-2 pe-2 pb-1 fs--1 mt-1 border border-1 border-300 rounded-circle ' title='Gestión de marcaciones' data-bs-toggle='popover' data-bs-html='true' data-bs-content=''>M</a>";
                       opt2 += "<a id='popoverAutoMark"+i+"' class='btn btn-sm text-danger bg-white opacity-75 ms-1 pt-2 ps-2 pe-2 pb-1 fs--1 mt-1 border border-1 border-300 rounded-circle' title='Auto-marcado' data-bs-toggle='popover' data-bs-html='true' data-bs-content=''>A</a>";
                       //opt2 += "<a id='popoverAutoMark"+i+"' class='bg-soft btn btn-sm text-400 bg-white pt-0 mt-1 pb-1 fs--1 fw-semi-bold border border-1 border-300' tabindex='0' role='button' data-bs-toggle='popover' data-bs-trigger='focus' title='Auto-marcado' data-bs-content=''><span id='dota"+i+"' class='text-primary fs-1 me-1'>&#x2022;</span>Automarcado</a>";

                      // opt2 += "<a id='popover1' data-placement='bottom' data-toggle='popover' data-container='body' data-placement='left' type='button' data-html='true' href='#'>popover</a>";
                       /*"<td>"+
                            "<select class='form-select form-select-sm' name='id_row2' id='id_row2' style='width: 75px;' onchange='program_tur_row('','','')'>"+
                               "<option value='-1' selected>-- --</option>"+
                            "</select>"+
                        "</td>";*/

                    //console.log("j: "+j);
                    j++;

                    if(j % 7 == 0){
                        //console.log("Ingreso a multiplo de 7...");
                        opt2 += "</tr>"+
                                "<tr>";
                    }

                    x = x+1;
                    opt2 += "</td>";
                 }

                 $("#calendarBody2").html(opt2);

                 var y=0;
                 var a=0;
                 var b=1;

                 for (var i in data) {
                     y=b-1;
                     a=b-2;

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

                     if(data.length > 0){
                          //console.log("iexcodtra: "+data[i].iexcodtra);
                          //console.log("iexcodfec: "+data[i].iexcodfec);
                     }

                     /*console.log("data[i].iexcodturno: "+data[i].iexcodturno);

                     console.log("data[i].iexvacaind: "+data[i].iexvacaind);
                     console.log("data[i].iexauseind: "+data[i].iexauseind);
                     console.log("data[i].iexpermiso: "+data[i].iexpermiso);
                     console.log("data[i].iexindfalta: "+data[i].iexindfalta);*/

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
                           $('#background'+i).addClass('bg-opacity-75 bg-gradient bg-danger rounded-3');

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
             console.log("success!: data.length: "+data.length);
             $("#calendarFoot2").html("");

             if(data.length > 0){
                 //console.log("data[0].desfecdia: "+data[0].desfecdia);
                 //console.log("data[0].desiniturno: "+data[0].desiniturno);

                 var opt2 = "<tr>";
                 var x=0;
                 var j=1;

                 for (var i in data) {
                    //console.log("ingreso al for y muestro la data...");

                    if(i==0){
                        var ini = data[i].iexcoddiasem;
                        //console.log("data[i].iexcoddiasem: "+data[i].iexcoddiasem);
                        x = x + ini;

                        opt2 += "<td class='pt-2 pb-2 ps-2 bg-200 bg-opacity-75 border border-100'>"+
                                    "<span class='ms-1 text-400 fs-0'>31</span><br>"+
                                "</td>";

                        /*for(var n=1; n<ini; n+=1){
                            opt2 += "<td>"+
                                    "</td>";
                        }*/
                    }

                    opt2 += "<td id='background"+i+"' class='pt-2 pb-2 ps-2 pe-2 bg-300 bg-opacity-50 border border-100'>"+
                               "<span id='spanDiaCalendar"+i+"' class='ms-1 text-900 fs-0'>"+data[i].diaCalendar+"</span><br>"+
                               "<span id='spanturno"+i+"' class='text-500'>["+data[i].iexflgturno+"]</span><br>"+
                               "<span id='spanfecdia"+i+"'class='text-500'>"+data[i].desfecdia+"</span><br>"+
                               "<span id='spandesiniturno"+i+"' class='text-500'>"+data[i].desiniturno+" - "+data[i].desfinturno+"</span><br>";

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

                       //opt2 += "<a id='popoverVer"+i+"' class='bg-soft btn btn-sm text-400 bg-white pt-0 pb-1 fs--1 mt-1 fw-semi-bold border border-1 border-300' title='Gestión de marcaciones' data-bs-toggle='popover' data-bs-html='true' data-bs-content=''><span id='dotv"+i+"' class='text-success fs-1 me-1'>&#x2022;</span>Marcación</a>";
                       opt2 += "<a id='popoverVer"+i+"' class='btn btn-sm text-success bg-white opacity-75 pt-2 ps-2 pe-2 pb-1 fs--1 mt-1 border border-1 border-300 rounded-circle ' title='Gestión de marcaciones' data-bs-toggle='popover' data-bs-html='true' data-bs-content=''>M</a>";
                       opt2 += "<a id='popoverAutoMark"+i+"' class='btn btn-sm text-danger bg-white opacity-75 ms-1 pt-2 ps-2 pe-2 pb-1 fs--1 mt-1 border border-1 border-300 rounded-circle' title='Auto-marcado' data-bs-toggle='popover' data-bs-html='true' data-bs-content=''>A</a>";
                       //opt2 += "<a id='popoverAutoMark"+i+"' class='bg-soft btn btn-sm text-400 bg-white pt-0 mt-1 pb-1 fs--1 fw-semi-bold border border-1 border-300' tabindex='0' role='button' data-bs-toggle='popover' data-bs-trigger='focus' title='Auto-marcado' data-bs-content=''><span id='dota"+i+"' class='text-primary fs-1 me-1'>&#x2022;</span>Automarcado</a>";

                      // opt2 += "<a id='popover1' data-placement='bottom' data-toggle='popover' data-container='body' data-placement='left' type='button' data-html='true' href='#'>popover</a>";
                       /*"<td>"+
                            "<select class='form-select form-select-sm' name='id_row2' id='id_row2' style='width: 75px;' onchange='program_tur_row('','','')'>"+
                               "<option value='-1' selected>-- --</option>"+
                            "</select>"+
                        "</td>";*/

                    //console.log("j: "+j);
                    j++;

                    if(j % 7 == 0){
                        //console.log("Ingreso a multiplo de 7...");
                        opt2 += "</tr>"+
                                "<tr>";
                    }

                    x = x+1;
                    opt2 += "</td>";
                 }

                 $("#calendarBody2").html(opt2);

                 var y=0;
                 var a=0;
                 var b=1;

                 for (var i in data) {
                     y=b-1;
                     a=b-2;

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

                     if(data.length > 0){
                          //console.log("iexcodtra: "+data[i].iexcodtra);
                          //console.log("iexcodfec: "+data[i].iexcodfec);
                     }

                     /*console.log("data[i].iexcodturno: "+data[i].iexcodturno);

                     console.log("data[i].iexvacaind: "+data[i].iexvacaind);
                     console.log("data[i].iexauseind: "+data[i].iexauseind);
                     console.log("data[i].iexpermiso: "+data[i].iexpermiso);
                     console.log("data[i].iexindfalta: "+data[i].iexindfalta);*/

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
                            //bg-opacity-85 bg-gradient bg-danger
                           $('#background'+i).removeClass('bg-opacity-50 bg-300');
                           $('#background'+i).addClass('bg-opacity-85 bg-gradient bg-danger');

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

function traerMarcacionesAsisModal(codtra,codfec,ind,fecini,codigoTurnoSeleccionado){

    //console.log("traerMarcacionesAsisModal ind: "+ind);

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
                                "<div class='fs--1 text-600'>"+data.desfecdia+" ["+data.iexcodfec+"]</div>"+
                                "<input id='ipHiddenDesfecdia"+ind+"' type='hidden' value="+data.desfecdia+">"+
                                "<input id='ipHiddeniexcodfec"+ind+"' type='hidden' value="+data.iexcodfec+">"+
                            "</div>"+
                            "<div class='col-sm-6 col-md-12'>"+
                                "<div class='fs--1 text-1000 fw-semi-bold'>Turno: </div>";

                                traerLstTurnosModal(fecini,codigoTurnoSeleccionado,ind,data.desfecdia,data.iexcodfec);

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
                                    "<a class='btn btn-sm btn-phoenix-secondary mt-1 ms-1' onclick='calificarTurnoDia("+ind+");' ><span class='fa-regular fa-star text-warning me-1'></span>Calificar</a>"+
                                "</div>"+
                                "<div class='col-auto ps-0'>"+
                                    "<a class='btn btn-sm btn-phoenix-secondary mt-1 ms-3' onclick='cerrarPopover("+ind+");'>Cerrar</a>"+
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
   //console.log("traerLstTurnosModal desfecdia: "+desfecdia);
   var sel="";

   $.ajax({
     async: false,
     url: "traerLstTurnosModal",
     data: {
          "fecini": fecini
     },
     success: function (data2) {
          sel += "<select id='cod_turno"+ind+"' onchange='setearVariableCodTurnoSes("+ind+")' style='font-size:10px;' class='form-select form-select-sm' name='iexcodcon'  required>"+
                    "<option value='' selected >Turno</option>";

          //sessionStorage.setItem("desfecdia",desfecdia);
          //sessionStorage.setItem("iexcodfec",iexcodfec);

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

    //console.log("setearVariableCodTurnoSes desfecdia: "+desfecdia);

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
            //traerMarcacionesAsisModal(idTrabAsis,desfecdia,ind,fecini,codigoTurnoSeleccionado);

            console.log("ind: "+ind);
            //$('#popoverVer'+ind).popover('dispose');
            //$('#popoverVer'+ind).popover("show");

            //traerMarcacionesAsisModal(idTrabAsis,desfecdia,ind,fecini,codTurnoSelected);
            //traerMarcacionesAsisModal(idTrabAsis,iexcodfec,ind,fecini,codigoTurnoSeleccionado)

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

    var opcion = confirm("Esta seguro que desea traer los turnos en general?");

    if (opcion == true) {

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

        return true;
    } else {
        return false;
    }
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