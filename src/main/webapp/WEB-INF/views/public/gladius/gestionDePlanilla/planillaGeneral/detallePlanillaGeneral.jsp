<%--
  Created by IntelliJ IDEA.
  User: Jean Quiroz
  Date: 23/10/2023
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
    <head>
      <jsp:include page="../../../links.jsp"></jsp:include>
    </head>

    <script>
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
            //alert("ejecuta "+variable+" , trab:"+trab)
        }

        function consulBol(codtra){
            //document.getElementById("frmplaserv").submit();
            document.getElementById("accion").value="VERBOLTRA";
            document.getElementById("iexcodtra").value=codtra ;
            //alert("codtra :"+codtra);
            document.getElementById("frmplaserv").submit();
        }

        function verdetcon(codtra){
            //document.getElementById("frmplaserv").submit();
            document.getElementById("accion").value="VERDETCONCEP";
            document.getElementById("iexcodtra").value=codtra ;
            //alert("codtra :"+codtra);
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
            //alert("GenerarBoleta");

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
                             "<td class='id align-middle white-space-nowrap py-0'><a class='fw-semi-bold' href='#'>#"+data[i].procodcon+"</a></td>"+
                             "<td class='concept align-middle text-start fw-semi-bold ps-0 pe-0 text-1000'><span class='badge badge-phoenix fs--2 badge-phoenix-primary'>"+data[i].coodescon+"</span></td>"+
                             "<td class='var align-middle text-end fw-semi-bold text-1000 ps-0 pe-0 white-space-nowrap'>"+data[i].provalor+"</td>"+

                             "<td class='align-middle white-space-nowrap text-end pe-0 ps-5'>"+
                                "<div class='font-sans-serif btn-reveal-trigger position-static'>"+
                                  "<button class='btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2' type='button' data-bs-toggle='dropdown' data-boundary='window' aria-haspopup='true' aria-expanded='false' data-bs-reference='parent'><span class='fas fa-ellipsis-h fs--2'></span></button>"+
                                  "<div class='dropdown-menu dropdown-menu-end py-2'>"+
                                    "<a class='dropdown-item' href='detalleEmpl@${empl.iexcodtra}'>Ver Detalle</a>"+
                                    "<div class='dropdown-divider'></div>"+
                                    "<a class='dropdown-item text-warning' href='#!'>Remove</a>"+
                                  "</div>"+
                                "</div>"+
                             "</td>"+
                          "</tr>";
                      }

                      $("#customer-order-table-body_param").html(opt);
                 }
            });
        }

        function descargarBoleta(){
            var codtra = $("#idTrabBolHidden").val();

            var iexcodpro = $("#iexcodpro").val();
            var iexperiodo = $("#iexperiodo").val();
            var iexcorrel = $("#iexcorrel").val();

            var params="3UP_CODPRO="+iexcodpro+"UP_NROPER="+iexperiodo+"UP_CORREL="+iexcorrel;

            document.getElementById("botonDescargarBoletaTrab").href="AWSorFTP_flgsource@verReportePDF@${idComp}@"+codtra+"@null@null@BoletaEmpTra@"+params+"@null@null@null";
        }
    </script>

    <body>
        <!-- ===============================================-->
        <!--    Main Content-->
        <!-- ===============================================-->
        <main class="main" id="top">
          <jsp:include page="../../../navsMenu.jsp"></jsp:include>
          <jsp:include page="../../../navTop.jsp"></jsp:include>
          <jsp:include page="../../../modalFade.jsp"></jsp:include>

          <div class="content">
            <nav class="mb-2" aria-label="breadcrumb">
              <ol class="breadcrumb mb-0">
                <li class="breadcrumb-item"><a href="#!">Gestión de planillas</a></li>
                <li class="breadcrumb-item active">Planilla General</li>
              </ol>
            </nav>
            <div class="mb-1">
              <div class="row g-3 mb-2">
                <div class="col-12">
                  <h2 id="h2top" class="mb-2">Gestión de planillas </h2>
                  <div class="row col-12">
                      <div class="col-10">
                        <span class="badge badge-tag me-2 mb-2">Regimen: ${requestScope.xproplaper.desregimen}</span>
                      </div>
                      <div class="col-2">
                        <a class="btn btn-phoenix-secondary btn-sm" href="buscarPlanillaGen"><span class="fas fa-reply me-2"></span>Atras</a>
                      </div>
                  </div>
                  <p class="col-8 mb-0 mt-0 fs--1">Proceso: ${requestScope.xproplaper.desproceso} ${requestScope.xproplaper.iexnroper} [${requestScope.xproplaper.timerfecini} - ${requestScope.xproplaper.timerfecfin}] &nbspGrupoPlanilla: ${requestScope.xproplaper.desgrppla}<span class="badge badge-phoenix fs--2 badge-phoenix-primary ms-2"><span class="badge-label">${requestScope.xproplaper.desestado}</span></p>
                </div>
              </div>
            </div>

            <div id="orderTable" data-list='{"valueNames":["id","trab","itp","est","fecini"],"page":5,"pagination":true}'>
              <div class="mb-3">
                <div class="row g-3">
                  <div class="col-auto">
                    <div class="search-box">
                      <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                        <input class="form-control search-input search" type="search" placeholder="Search trabajadores" aria-label="Search"/>
                        <span class="fas fa-search search-box-icon"></span>
                      </form>
                    </div>
                  </div>
                  <div class="col-auto scrollbar overflow-hidden-y flex-grow-1">
                    <div class="btn-group position-static" role="group">
                      <div class="btn-group position-static text-nowrap" role="group">
                        <button class="btn btn-phoenix-secondary px-7 flex-shrink-0" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true"
                                aria-expanded="false" data-bs-reference="parent">
                          Payment status<span class="fas fa-angle-down ms-2"></span></button>
                        <ul class="dropdown-menu dropdown-menu-end">
                          <li><a class="dropdown-item" href="#">Action</a></li>
                          <li><a class="dropdown-item" href="#">Another action</a></li>
                          <li><a class="dropdown-item" href="#">Something else here</a></li>
                          <li>
                            <hr class="dropdown-divider"/>
                          </li>
                          <li><a class="dropdown-item" href="#">Separated link</a></li>
                        </ul>
                      </div>
                      <div class="btn-group position-static text-nowrap" role="group">
                        <button class="btn btn-sm btn-phoenix-secondary px-7 flex-shrink-0" type="button" data-bs-toggle="dropdown" data-boundary="window"
                                aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                          Fulfilment status<span class="fas fa-angle-down ms-2"></span></button>
                        <ul class="dropdown-menu dropdown-menu-end">
                          <li><a class="dropdown-item" href="#">Action</a></li>
                          <li><a class="dropdown-item" href="#">Another action</a></li>
                          <li><a class="dropdown-item" href="#">Something else here</a></li>
                          <li>
                            <hr class="dropdown-divider"/>
                          </li>
                          <li><a class="dropdown-item" href="#">Separated link</a></li>
                        </ul>
                      </div>
                      <button class="btn btn-sm btn-phoenix-secondary px-7 flex-shrink-0">More filters</button>
                    </div>
                  </div>

                  <c:if test="${requestScope.xproplaper.flgestado!='3'}">
                      <div class="col-auto">
                        <a class="btn btn-phoenix-primary btn-sm" onclick="return enviaForm('2')" href="#"><span class="fas fa-play me-2"></span>1. Iniciar</a>
                        <a class="btn btn-phoenix-secondary btn-sm" href="verDetalleVariable@${iexcodreg}@${xproplaper.iexcodpro}@${iexperiodo}"><span class="fas fa-square-root-variable me-2"></span>Variables</a>
                        <a class="btn btn-phoenix-secondary btn-sm" href="#"><span class="fas fa-arrows-turn-to-dots me-2"></span>2. Turnos</a>
                        <a class="btn btn-phoenix-secondary btn-sm" onclick="return enviaForm('34')" href="#"><span class="fas fa-database me-2"></span>4. Consolida</a>
                        <a class="btn btn-phoenix-secondary btn-sm" onclick="return enviaForm('3')" href="#"><span class="fas fa-wrench me-2"></span>5. Procesar</a>
                        <a class="btn btn-phoenix-secondary btn-sm" href="#"><span class="fas fa-vault me-2"></span>5. Bancos</a>
                        <a class="btn btn-phoenix-danger btn-sm" onclick="return enviaForm('6')" href="#"><span class="fas fa-trash me-2"></span>0. Borrar</a>
                        <a class="btn btn-primary btn-sm ms-3" onclick="enviaForm('35')" href="#"><span class="fas fa-magnifying-glass me-2"></span>Consultar todo</a>
                      </div>
                  </c:if>
                </div>
              </div>

              <form name="frmplaserv" id="frmplaserv" action="procesarPlanilla" method="POST">
                  <input type="hidden" name="iexcodreg" id="iexcodreg" value="${iexcodreg}">
                  <input type="hidden" name="accion" id="accion" value="">
                  <input type="hidden" name="grppla" value="${xproplaper.iexcodpro}">
                  <input type="hidden" name="tipfile" id="tipfile" value="">
                  <input type="hidden" name="iexcodpro" id="iexcodpro" value="${iexcodpro}">
                  <input type="hidden" name="iexperiodo" id="iexperiodo" value="${iexperiodo}">
                  <input type="hidden" name="iexcodtra" id="iexcodtra" value="-1">
                  <input type="hidden" name="iexcorrel" id="iexcorrel" value="1">

                  <c:if test="${requestScope.xproplaper.desgrppla=='PLA' || requestScope.xproplaper.desgrppla=='ADE'}">
                      <div id="customerOrdersTable" class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1" data-list='{"valueNames":["id","trab","itp","est","fecini"],"page":5, "pagination":true }'>
                        <div class="table-responsive scrollbar mx-n1 px-1">
                          <table class="table table-sm fs--1 mb-0">
                            <thead>
                                <tr>
                                  <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                    <div class="form-check mb-0 fs-0">
                                      <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                    </div>
                                  </th>
                                  <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="id" style="width:5%;">ID</th>
                                  <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="trab">TRABAJADOR</th>
                                  <th class="sort align-middle text-center pe-2 ps-2 white-space-nowrap" scope="col" data-sort="itp">I/T/P</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="est">ESTADO</th>
                                  <th class="sort align-middle text-center ps-4 pe-4" scope="col" data-sort="fecini">FECINI</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >DTEO</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >DTOT</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >VAC</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >DME</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >SUB</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >LIC</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >FAL</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >D.E</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >DOM</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >DPAG</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" ></th>
                                </tr>
                            </thead>
                            <tbody class="list" id="customer-order-table-body">
                                <c:forEach var="LstPlanillaRes" items="${requestScope.LstPlanillaRes}">
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static" >
                                    <td class="fs--1 align-middle px-0 py-3">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" />
                                      </div>
                                    </td>
                                    <td class="id align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="editarConcepto@${concepto.codConcepto}">#${LstPlanillaRes.iexcodtra}</a></td>
                                    <td class="trab align-middle text-start fw-semi-bold ps-3 white-space-nowrap pe-3 text-1000">
                                        ${LstPlanillaRes.destra}
                                        <div class="btn-group font-sans-serif btn-reveal-trigger position-static ms-2">
                                            <button class="btn btn-phoenix-primary pt-1 pb-1 ps-2 pe-2 btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-person"></span><span class="fas fa-caret-down ms-2"></span></button>
                                            <div class="dropdown-menu dropdown-menu-end py-2">
                                              <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="detalleEmpl@${LstPlanillaRes.iexcodtra}"><span class="fa-solid fa-person me-2"></span>Detalle Empleado</a>
                                              <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="sueldoFijo@${LstPlanillaRes.iexcodtra}"><span class="fa-solid fa-money-bill-trend-up fs--1 me-2"></span>Sueldo Fijo</a>
                                              <div class="dropdown-divider"></div>
                                              <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="sueldoVariable@${LstPlanillaRes.iexcodtra}"><span class="fa-solid fa-money-bill-transfer me-2"></span>Sueldo Variable</a>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="itp align-middle text-start fw-semi-bold ps-3 pe-0 text-1000">
                                        <a href="#" onclick="enviaForm_ind('2', '${LstPlanillaRes.iexcodtra}')" >I</a>
                                        <a href="#" onclick="enviaForm_ind('34', '${LstPlanillaRes.iexcodtra}')" >T</a>
                                        <a href="#" onclick="enviaForm_ind('3', '${LstPlanillaRes.iexcodtra}')" >P</a>
                                    </td>
                                    <td class="est align-middle text-center fw-semi-bold text-1000 ps-0 pe-0 white-space-nowrap"><a href="#">Proc</a></td>
                                    <td class="fecini align-middle text-start fw-semi-bold text-600"><a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstPlanillaRes.iexfecini}</td>
                                    <td class="al align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiasteorico}</td>
                                    <td class="al align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiamestot}</td>
                                    <td class="al align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiavaca}</td>
                                    <td class="abr al align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiadm}</td>
                                    <td class="al align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiasub}</td>
                                    <td class="al align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdialic}</td>
                                    <td class="al align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiafalta}</td>
                                    <td class="al align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiaefectivo}</td>
                                    <td class="al align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdominical}</td>
                                    <td class="al align-middle text-start fw-semi-bold text-600">${LstPlanillaRes.iexdiaspago}</td>

                                    <td class="align-middle text-center white-space-nowrap pe-0 action">
                                      <div class="font-sans-serif btn-reveal-trigger position-static">
                                        <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                        data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                        <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                        <div class="dropdown-menu dropdown-menu-end py-2">
                                          <a id="dropdownmenutable" class="dropdown-item" onclick="generarBoleta('${iexcodpro}','${LstPlanillaRes.iexcodtra}','${iexperiodo}','1','${requestScope.xproplaper.desgrppla}','${iexcodreg}');" href="#" type="button" data-bs-toggle="modal" data-bs-target="#modalGenerarBoleta" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" ><span class="fa-solid fa-window-restore me-2"></span>Boleta</a>
                                      </div>
                                    </td>
                                  </tr>
                                </c:forEach>
                            </tbody>
                          </table>
                        </div>
                        <div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
                            <div class="col-auto d-flex">
                              <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!" data-list-view="*">View all<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none" href="#!" data-list-view="less">View Less<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
                            </div>
                            <div class="col-auto d-flex">
                              <button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                              <ul class="mb-0 pagination"></ul>
                              <button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span></button>
                            </div>
                        </div>
                      </div>
                  </c:if>

                  <c:if test="${requestScope.xproplaper.desgrppla=='PRV' || requestScope.xproplaper.desgrppla=='CTS' || requestScope.xproplaper.desgrppla=='GRA' }">
                    <div id="customerOrdersTable" class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1" data-list='{"valueNames":["id","trab","itp","est","fecini"],"page":5, "pagination":true }'>
                        <div class="table-responsive scrollbar mx-n1 px-1">
                          <table class="table table-sm fs--1 mb-0">
                            <thead>
                                <tr>
                                  <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                    <div class="form-check mb-0 fs-0">
                                      <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                    </div>
                                  </th>
                                  <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="id" style="width:5%;">ID</th>
                                  <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="trab">TRABAJADOR</th>
                                  <th class="sort align-middle text-center pe-2 ps-2 white-space-nowrap" scope="col" data-sort="itp">FECINI</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >FECFIN</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >AÑOS</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >MESES</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" >DIAS</th>
                                  <th class="sort align-middle text-center ps-2 pe-2" scope="col" ></th>
                                </tr>
                            </thead>
                            <tbody class="list" id="customer-order-table-body">
                                <c:forEach var="LstPlanillaRes" items="${requestScope.LstPlanillaRes}">
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static" >
                                    <td class="fs--1 align-middle px-0 py-3">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" />
                                      </div>
                                    </td>
                                    <td class="id align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="editarConcepto@${concepto.codConcepto}">#${LstPlanillaRes.iexcodtra}</a></td>
                                    <td class="trab align-middle text-start fw-semi-bold ps-3 white-space-nowrap pe-3 text-1000">${LstPlanillaRes.destra}</td>
                                    <td class="est align-middle text-center fw-semi-bold text-600 ps-0 pe-0"><a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstPlanillaRes.iexfecini}</td>
                                    <td class="fecini align-middle text-start fw-semi-bold text-600"><a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstPlanillaRes.iexfecfin}</td>
                                    <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexanio_benef}</td>
                                    <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexmes_benef}</td>
                                    <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdia_benef}</td>

                                    <td class="align-middle text-center white-space-nowrap pe-0 action">
                                      <div class="font-sans-serif btn-reveal-trigger position-static">
                                        <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                        data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                        <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                        <div class="dropdown-menu dropdown-menu-end py-2">
                                          <a id="dropdownmenutable" class="dropdown-item" href="#" onclick="consulBol('${LstPlanillaRes.iexcodtra}')" ><span class="fa-solid fa-window-restore me-2"></span>Boleta</a>
                                      </div>
                                    </td>
                                  </tr>
                                </c:forEach>
                            </tbody>
                          </table>
                        </div>
                        <div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
                            <div class="col-auto d-flex">
                              <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!" data-list-view="*">View all<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none" href="#!" data-list-view="less">View Less<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
                            </div>
                            <div class="col-auto d-flex">
                              <button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                              <ul class="mb-0 pagination"></ul>
                              <button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span></button>
                            </div>
                        </div>
                    </div>
                  </c:if>

                  <c:if test="${requestScope.xproplaper.desgrppla=='UTI' }">
                      <div id="customerOrdersTable" class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1" data-list='{"valueNames":["id","trab","itp","est","fecini"],"page":5, "pagination":true }'>
                          <div class="table-responsive scrollbar mx-n1 px-1">
                            <table class="table table-sm fs--1 mb-0">
                              <thead>
                                  <tr>
                                    <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                                      </div>
                                    </th>
                                    <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="id" style="width:5%;">ID</th>
                                    <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="trab">TRABAJADOR</th>
                                    <th class="sort align-middle text-center pe-2 ps-2 white-space-nowrap" scope="col" data-sort="itp">DIAS TEO</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS MES</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS VAC</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS DM</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS SUB</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS LIC</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS FALTA</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" data-sort="itp">DIAS EFEC</th>
                                    <th class="sort align-middle text-center ps-2 pe-2" scope="col" ></th>
                                  </tr>
                              </thead>
                              <tbody class="list" id="customer-order-table-body">
                                  <c:forEach var="LstPlanillaRes" items="${requestScope.LstPlanillaRes}">
                                    <tr class="hover-actions-trigger btn-reveal-trigger position-static" >
                                      <td class="fs--1 align-middle px-0 py-3">
                                        <div class="form-check mb-0 fs-0">
                                          <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" />
                                        </div>
                                      </td>
                                      <td class="id align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="editarConcepto@${concepto.codConcepto}">#${LstPlanillaRes.iexcodtra}</a></td>
                                      <td class="trab align-middle text-start fw-semi-bold ps-3 white-space-nowrap pe-3 text-1000">${LstPlanillaRes.destra}</td>
                                      <td class="est align-middle text-center fw-semi-bold text-600 ps-0 pe-0"><a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstPlanillaRes.iexfecini}</td>
                                      <td class="fecini align-middle text-start fw-semi-bold text-600"><a href="#"><span class="fa-solid fa-calendar-days me-2"></span></a>${LstPlanillaRes.iexfecfin}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiasteorico}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiamestot}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiavaca}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiadm}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiasub}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdialic}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiafalta}</td>
                                      <td class="al align-middle text-center fw-semi-bold text-600">${LstPlanillaRes.iexdiaefectivo}</td>

                                      <td class="align-middle text-center white-space-nowrap pe-0 action">
                                        <div class="font-sans-serif btn-reveal-trigger position-static">
                                          <button class="btn btn-phoenix-secondary btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button"
                                          data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                                          <span class="fas fa-plus"></span><span class="fas fa-caret-down ms-2"></span></button>
                                          <div class="dropdown-menu dropdown-menu-end py-2">
                                            <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-window-restore me-2"></span>Boleta</a>
                                        </div>
                                      </td>
                                    </tr>
                                  </c:forEach>
                              </tbody>
                            </table>
                          </div>
                          <div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
                              <div class="col-auto d-flex">
                                <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!" data-list-view="*">View all<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none" href="#!" data-list-view="less">View Less<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
                              </div>
                              <div class="col-auto d-flex">
                                <button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                                <ul class="mb-0 pagination"></ul>
                                <button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span></button>
                              </div>
                          </div>
                      </div>
                  </c:if>

                  <div class="col-auto mt-4">
                      <a class="btn btn-phoenix-primary btn-sm" href="#"><span class="fas fa-briefcase me-2"></span>1. Otros datos</a>
                      <a class="btn btn-phoenix-secondary btn-sm" href="#"><span class="fas fa-handshake me-2"></span>Afp</a>

                      <div class="btn-group mb-1 me-1 ms-1 mt-1">
                        <button class="btn btn-sm btn-phoenix-secondary" type="button"><span class="fa-solid fa-hashtag fs--1 me-2"></span></span class="ps-5">Exportar</span></button>
                        <button class="btn btn-sm dropdown-toggle dropdown-toggle-split btn-phoenix-secondary" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="sr-only"></span></button>
                        <div class="dropdown-menu">
                          <a id="dropdownmenutable" target="_blank" class="dropdown-item" href="#">
                            <span class="fa-solid fa-download fs--1 me-2"></span>Excel Variables
                          </a>
                          <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Excel Todos</a>
                          <div class="dropdown-divider"></div>
                          <a id="dropdownmenutable" class="dropdown-item" href="#"><span class="fa-solid fa-download fs--1 me-2"></span>Excel Resumen</a>
                        </div>
                      </div>

                      <a class="btn btn-phoenix-secondary btn-sm" href="#"><span class="fas fa-database me-2"></span>6. Boletas PDF</a>
                      <a class="btn btn-phoenix-primary btn-sm" href="#"><span class="fas fa-diagram-successor me-2"></span>Migración planilla</a>
                  </div>

                  <table class="w-100 table-stats table-stats mt-4">
                    <tr>
                      <th></th>
                      <th></th>
                      <th></th>
                    </tr>
                    <tr>
                      <td class="py-2 col-4">
                        <div class="d-inline-flex align-items-center">
                          <div class="d-flex bg-success-100 rounded-circle flex-center me-3" style="width:24px; height:24px"><span class="text-success-600 dark__text-success-300" data-feather="play" style="width:16px; height:16px"></span></div>
                          <p class="fw-bold mb-0">Tiempo de Inicializacion</p>
                        </div>
                      </td>
                      <td class="py-2">
                        <p class="ps-6 ps-sm-0 fw-semi-bold mb-0 mb-0 pb-3 pb-sm-0 fs--1">[${requestScope.xproplaper.timerfecini_iniciar}]&nbsp&nbsp&nbsp[${requestScope.xproplaper.timerfecfin_iniciar}]&nbsp&nbsp&nbsp[${requestScope.xproplaper.timerimp_iniciar}]&nbsp<span class="text-primary">segs</span></p>
                      </td>
                    </tr>
                    <tr>
                      <td class="py-2">
                        <div class="d-flex align-items-center">
                          <div class="d-flex bg-info-100 rounded-circle flex-center me-3" style="width:24px; height:24px"><span class="text-info-600 dark__text-info-300" data-feather="clock" style="width:16px; height:16px"></span></div>
                          <p class="fw-bold mb-0">Tiempo de Procesos</p>
                        </div>
                      </td>
                      <td class="py-2">
                        <p class="ps-6 ps-sm-0 fw-semi-bold mb-0 fs--1">[${requestScope.xproplaper.timerfecini_proc}]&nbsp&nbsp&nbsp[${requestScope.xproplaper.timerfecfin_proc}]&nbsp&nbsp&nbsp[${requestScope.xproplaper.timerimp_proc}]&nbsp<span class="text-primary">segs</span></p>
                      </td>
                    </tr>
                  </table>

                  <div class="col-auto mt-4">
                      <a class="btn btn-primary btn-sm" href="#"><span class="fas fa-magnifying-glass me-2"></span>Ver reporte</a>
                  </div>
                  <div id="idresult" style="width:700px; height:600px; overflow: scroll;" >
                  </div>
              </form>
            </div>
          </div>

          <jsp:include page="../../../demoWidget.jsp"></jsp:include>
        </main>
        <!-- ===============================================-->
        <!--    End of Main Content-->
        <!-- ===============================================-->

        <jsp:include page="../../../customize.jsp"></jsp:include>
    </body>

    <div id="modalGenerarBoleta" class="modal fade" tabindex="-1" aria-labelledby="scrollingLongModalLabel2" aria-hidden="true" >
      <div class="modal-dialog modal-lg modal-dialog-scrollable">
          <div class="modal-content bg-100">
                <div class="modal-header border-200 bg-soft p-4">
                   <h5 class="modal-title text-1000 fs-2 lh-sm">Generar boleta empleado</h5>
                   <button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-0"></span></button>
                </div>
                <div class="modal-body p-4">
                    <form class="needs-validation" method="POST" action="modificarPeriodoPlan" novalidate >
                      <div id="alertModalSuccessEdit" class="alert alert-outline-success bg-success bg-opacity-10 d-flex align-items-center" role="alert" style="display:none !important;">
                          <span class="fa-regular fa-check-circle text-success fs-0 me-3"></span>
                          <p class="mb-0 fw-semi-bold text-1000 col-11">Se grabó exitosamente los cambios <a href="#">Mas información</a></p>
                          <button class="btn-close fs--2" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                      </div>

                      <div class="row g-4">
                          <div class="col-auto">
                              <a class="btn btn-phoenix-secondary btn-sm" onclick="#" target="_blank" href="#"><span class="fas fa-download me-2"></span>Reporte de 5ta</a>
                              <a id="botonDescargarBoletaTrab" onclick="descargarBoleta();" target="_blank" class="btn btn-phoenix-secondary btn-sm" href="#"><span class="fas fa-download me-2"></span>Boleta</a>
                              <a class="btn btn-phoenix-danger btn-sm" href="#" target="_blank"><span class="fas fa-trash me-2"></span>Eliminar planilla del trabajador</a>
                          </div>
                      </div>
                      <div class="row mt-3">
                          <div class="col-sm-6 col-md-2">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">ID trab</label>
                                <input class="form-control" name="idTrabBol" id="idTrabBol" type="text" required disabled />
                                <input class="form-control" name="idTrabBolHidden" id="idTrabBolHidden" type="hidden" value="" />
                          </div>
                          <div class="col-sm-6 col-md-6">
                                <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Trabajador</label>
                                <input class="form-control" name="trabBol" id="trabBol" type="text" required disabled />
                                <!--<input class="form-control" name="idprocesoEdit" id="idprocesoEdit" type="hidden" value="" />-->
                          </div>
                          <div class="col-sm-6 col-md-3">
                              <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Fecha de Ingreso</label>
                              <input class="form-control" name="feciniBol" id="feciniBol" type="text" required disabled />
                              <!--<input class="form-control" name="feciniBol" id="feciniBol" type="hidden" value="" />-->
                          </div>
                      </div>
                      <div id="orderTable" data-list='{"valueNames":["id","concept","var","des","abr"],"page":10,"pagination":true}'>
                        <h4 class="mb-2 mt-4">Parámetros</h4>
                        <div class="mb-3">
                            <div class="row g-3">
                              <div class="col-auto">
                                <div class="search-box">
                                  <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                    <input class="form-control search-input search" type="search" placeholder="Search parámetros" aria-label="Search"/>
                                    <span class="fas fa-search search-box-icon"></span>
                                  </form>
                                </div>
                              </div>
                            </div>
                        </div>

                        <div id="customerOrdersTable_param" class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1" data-list='{"valueNames":["id","concept","var","des","abr"],"page":10, "pagination":true }'>
                            <div class="table-responsive scrollbar mx-n1 px-1">
                              <table class="table table-sm fs--1 mb-0">
                                <thead>
                                    <tr>
                                      <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                        <div class="form-check mb-0 fs-0">
                                          <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select="{'body':'order-table-body'}" />
                                        </div>
                                      </th>
                                      <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="id" style="width:5%;">CODCON</th>
                                      <th class="sort align-middle text-center pe-0 ps-0" scope="col" data-sort="concept">DESCON</th>
                                      <th class="sort align-middle text-center pe-0 ps-0 white-space-nowrap" scope="col" data-sort="var">VALOR</th>
                                      <th class="sort align-middle text-center pe-0" scope="col" ></th>
                                    </tr>
                                </thead>
                                <tbody class="list" id="customer-order-table-body_param">
                                </tbody>
                              </table>
                            </div>
                            <div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
                                <div class="col-auto d-flex">
                                  <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!" data-list-view="*">View all<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none" href="#!" data-list-view="less">View Less<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
                                </div>
                                <div class="col-auto d-flex">
                                  <button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                                  <ul class="mb-0 pagination"></ul>
                                  <button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span></button>
                                </div>
                            </div>
                        </div>
                      </div>
                    </form>
                </div>
                <div class="modal-footer d-flex justify-content-end align-items-center px-0 pb-0 border-200 pt-0">
                    <a class="btn btn-sm btn-primary px-3 my-0" data-bs-dismiss="modal" aria-label="Close">Cerrar</a>
                    <!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" type="submit"><div class="spinner-border spinner-border-sm" style="height:13px; width:13px;" role="status"></div><span class="ms-2">Guardar Periodo</span></button>-->
                    <!--<button class="btn btn-sm btn-primary px-9 my-0 mt-1 ps-4 pe-4" onclick="mostrarAlertModalEdit();" type="submit"><span class="ms-2">Guardar Periodo</span></button>-->
                </div>
          </div>
      </div>
    </div>
</html>
