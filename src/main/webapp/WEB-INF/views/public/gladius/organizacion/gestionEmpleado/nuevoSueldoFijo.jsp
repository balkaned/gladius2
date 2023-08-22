<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jean Quiroz
    Email	   : jeanp.quiroz@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
  <head>
    <jsp:include page="../../../links.jsp"></jsp:include>
  </head>
  <script>
      $(document).ready(function() {
          $('#iexpaisemisor').change(function(event){
           $.ajaxSetup({cache:false});
                $.ajax({
                  url: "getlovs",
                  data: {"accion": "DEPX",
                      "codpais": $("#iexpaisemisor").val()},
                  success: function (data) {
                      var opt = "";
                           opt += "<option value='' > -- Selecciona -- </option>";
                           for (var i in data) {
                            opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                           }

                      $("#iexdepart_origen").html(opt);
                      $("#iexprovin_origen").html("<option value='' > -- Selecciona -- </option>");
                      $("#iexdistri_origen").html("<option value='' > -- Selecciona -- </option>");
                  }
              });
          });

           $('#iexdepart_origen').change(function(event){
           $.ajaxSetup({cache:false});
                $.ajax({
                  url: "getlovs",
                  data: {"accion": "PROVX",
                      "coddept": $("#iexdepart_origen").val()},
                  success: function (data) {
                      var opt = "";
                           opt += "<option value='' > -- Selecciona -- </option>";
                           for (var i in data) {
                            opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                           }

                      $("#iexprovin_origen").html(opt);
                      $("#iexdistri_origen").html("<option value='' > -- Selecciona -- </option>");
                  }
              });
          });

          $('#iexprovin_origen').change(function(event){
           $.ajaxSetup({cache:false});
                $.ajax({
                  url: "getlovs",
                  data: {"accion": "DISTX",
                      "codprov": $("#iexprovin_origen").val()},
                  success: function (data) {
                      var opt = "";
                           opt += "<option value=0 > -- Selecciona -- </option>";
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
                          url: "getlovs",
                          data: {"accion": "DEPX",
                              "codpais": $("#iexpaisemisor1").val()},
                          success: function (data) {
                              var opt = "";
                                   opt += "<option value='' > -- Selecciona -- </option>";
                                   for (var i in data) {
                                    opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                                   }

                              $("#iexdepart_origen1").html(opt);
                              $("#iexprovin_origen1").html("<option value='' > -- Selecciona -- </option>");
                              $("#iexubigeo_dom1").html("<option value='' > -- Selecciona -- </option>");
                          }
                   });
          });

          $('#iexdepart_origen1').change(function(event){
               $.ajaxSetup({cache:false});
                    $.ajax({
                      url: "getlovs",
                      data: {"accion": "PROVX",
                          "coddept": $("#iexdepart_origen1").val()},
                      success: function (data) {
                          var opt = "";
                               opt += "<option value='' > -- Selecciona -- </option>";
                               for (var i in data) {
                                opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                               }

                          $("#iexprovin_origen1").html(opt);
                          $("#iexubigeo_dom1").html("<option value='' > -- Selecciona -- </option>");
                      }
               });
          });

          $('#iexprovin_origen1').change(function(event){
                   $.ajaxSetup({cache:false});
                        $.ajax({
                          url: "getlovs",
                          data: {"accion": "DISTX",
                              "codprov": $("#iexprovin_origen1").val()},
                          success: function (data) {
                              var opt = "";
                                   opt += "<option value=0 > -- Selecciona -- </option>";
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
                          url: "getlovs",
                          data: {"accion": "DEPX",
                              "codpais": $("#iexpaisemisor2").val()},
                          success: function (data) {
                              var opt = "";
                                   opt += "<option value='' > -- Selecciona -- </option>";
                                   for (var i in data) {
                                    opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                                   }

                              $("#iexdepart_origen2").html(opt);
                              $("#iexprovin_origen2").html("<option value='' > -- Selecciona -- </option>");
                              $("#iexubigeo_dom2").html("<option value='' > -- Selecciona -- </option>");
                          }
                   });
          });

          $('#iexdepart_origen2').change(function(event){
                   $.ajaxSetup({cache:false});
                        $.ajax({
                          url: "getlovs",
                          data: {"accion": "PROVX",
                              "coddept": $("#iexdepart_origen2").val()},
                          success: function (data) {
                              var opt = "";
                                   opt += "<option value='' > -- Selecciona -- </option>";
                                   for (var i in data) {
                                    opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                                   }

                              $("#iexprovin_origen2").html(opt);
                              $("#iexubigeo_dom2").html("<option value='' > -- Selecciona -- </option>");
                          }
                   });
          });

          $('#iexprovin_origen2').change(function(event){
                   $.ajaxSetup({cache:false});
                        $.ajax({
                          url: "getlovs",
                          data: {"accion": "DISTX",
                              "codprov": $("#iexprovin_origen2").val()},
                          success: function (data) {
                              var opt = "";
                                   opt += "<option value=0 > -- Selecciona -- </option>";
                                   for (var i in data) {
                                    opt += "<option value="+data[i].idLov+" > "+data[i].desLov+" </option> ";
                                   }

                              $("#iexubigeo_dom2").html(opt);
                          }
                      });
                  });
          });
      });

      /*Calendar.setup({
      inputField     :    "iexfecing",     // id del campo de texto
      ifFormat     :     "%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto
      button     :    "lanzador"     // el id del botón que lanzará el calendario
          });

     Calendar.setup2({
      inputField     :    "iexfecret",     // id del campo de texto
      ifFormat     :     "%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto
      button     :    "lanzador2"     // el id del botón que lanzará el calendario
          });

      Calendar.setup3({
      inputField     :    "iexfecini_cont",     // id del campo de texto
      ifFormat     :     "%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto
      button     :    "lanzador3"     // el id del botón que lanzará el calendario
          });

      Calendar.setup4({
      inputField     :    "iexfecfin_cont",     // id del campo de texto
      ifFormat     :     "%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto
      button     :    "lanzador4"     // el id del botón que lanzará el calendario
          });*/

      function mostrarAlert(){
        //alert("se grabo exitosamente");
        var div=document.getElementById('alert');
        div.style.display = '';

        setTimeout(function() {
            $("#alerts").hide(6000);
        }, 3000);
      }

      function mostrarAlert2(){
          //alert("se grabo exitosamente");
          var div=document.getElementById('alert2');
          div.style.display = '';

          setTimeout(function() {
              $("#alerts").hide(6000);
          }, 3000);
      }

      function mostrarAlert3(){
        //alert("se grabo exitosamente");
        var div=document.getElementById('alert3');
        div.style.display = '';

        setTimeout(function() {
            $("#alerts").hide(6000);
        }, 3000);
     }

     function mostrarAlert4(){
         //alert("se grabo exitosamente");
         var div=document.getElementById('alert4');
         div.style.display = '';

         setTimeout(function() {
             $("#alerts").hide(6000);
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

  <body>
    <!-- ===============================================-->
    <!--    Main Content-->
    <!-- ===============================================-->
    <main class="main" id="top">
          <jsp:include page="../../../navsMenu.jsp"></jsp:include>
          <jsp:include page="../../../navTop.jsp"></jsp:include>
          <jsp:include page="../../../modalFade.jsp"></jsp:include>

          <div class="content">
              <div class="pb-9">
                <div class="row">
                  <div class="col-12">
                    <div class="row align-items-center justify-content-between g-3 mb-3">
                    </div>
                  </div>
                </div>
                <div class="row g-0 g-md-4 g-xl-6">
                  <div class="col-md-5 col-lg-5 col-xl-4">
                    <div class="sticky-leads-sidebar">
                      <div class="lead-details-offcanvas bg-soft scrollbar phoenix-offcanvas phoenix-offcanvas-fixed" id="productFilterColumn">
                        <div class="d-flex justify-content-between align-items-center mb-2 d-md-none">
                          <h4 class="mb-0">Ficha trabajador</h4>
                          <button class="btn p-0" data-phoenix-dismiss="offcanvas"><span class="uil uil-times fs-1"></span></button>
                        </div>

                        <div class="card mb-5">
                            <div class="card-header hover-actions-trigger position-relative mb-7" style="min-height: 130px; ">
                                <!--<div class="bg-holder rounded-top" style="background-image: linear-gradient(0deg, #000000 -3%, rgba(0, 0, 0, 0) 83%), url(resources/assets/img/generic/59.png)">-->
                                <!--<div class="bg-holder rounded-top" style="background-color:#e6ebf7; !important">-->
                                <div class="bg-holder rounded-top bg-100 #f6f7f8">
                                <input class="d-none" id="upload-feed-cover-image" type="file" />
                                <label class="cover-image-file-input" for="upload-feed-cover-image"></label>
                                <div class="hover-actions end-0 bottom-0 pe-1 pb-2 text-white"><span class="fa-solid fa-camera me-2 overlay-icon"> </span></div>
                              </div>-->
                              <input class="d-none" id="upload-feed-porfile-picture" type="file" />
                              <label class="avatar avatar-4xl status-online feed-avatar-profile cursor-pointer" for="upload-feed-porfile-picture">
                                <!--<img class="rounded-circle img-thumbnail bg-white shadow-sm" src="resources/assets/img/team/59.webp" width="200" alt="" />-->
                                <!--<img class="rounded-circle img-thumbnail bg-white shadow-sm" src="VerFoto@FOTOEMP@${idComp}@${iexlogo}" width="200" alt="" />-->
                                <c:if test="${iexlogo!=null}"><img class="rounded-circle img-thumbnail bg-white shadow-sm" src="verFoto@FOTOEMP@${idComp}@${iexlogo}" width="200" alt="" /></c:if>
                                <c:if test="${iexlogo==null && sexo.equals('M')}"><img class="rounded-circle img-thumbnail bg-white shadow-sm" src="resources/assets/img/man_user.jpg" width="200" alt="" /></c:if>
                                <c:if test="${iexlogo==null && sexo.equals('F')}"><img class="rounded-circle img-thumbnail bg-white shadow-sm" src="resources/assets/img/woman_user.jpg" width="200" alt="" /></c:if>
                              </label>
                            </div>
                            <div class="card-body">
                              <div class="row">
                                <div class="col-12">
                                  <div class="d-flex flex-wrap mb-3 align-items-center">
                                    <h3 id="nombreFichaTrab" class="me-2" >${nombrecompl}</h3>
                                    <span class="fw-normal fs-0">${puesto}</span>
                                  </div>
                                  <!--<div class="mb-3">
                                    <div class="d-flex align-items-center flex-wrap">
                                      <div class="d-flex me-4 mb-2"><span class="fa-solid fa-user-group fs--2 me-2 me-lg-1 me-xl-2"></span>
                                        <h6 class="d-inline-block mb-0">1297 <span class="fw-semi-bold">Followers</span></h6>
                                      </div>
                                      <div class="d-flex mb-2"><span class="fa-solid fa-user-check fs--2 me-2 me-lg-1 me-xl-2"></span>
                                        <h6 class="d-block d-xl-inline-block mb-0">
                                          3971 <span class="fw-semi-bold">Following</span></h6>
                                      </div>
                                    </div>
                                  </div>-->
                                  <p class="fw-semi-bold mb-0">Empresa<a href="#!"><span class="fa-solid fa-pencil fs--2 text-500 ms-3"></span></a></p>
                                  <p class="text-700 mb-0">${nombreComp} </p>
                                </div>
                              </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-12">
                              <form method="post" action="fileUploadServlet@${idTrab}@${idComp}" enctype="multipart/form-data" >
                                  <input type="hidden" name="accion" value="FOTOEMP" >
                                  <input type="hidden" name="idimg" value="${nrodoc}" >
                                  <input type="hidden" name="codciax" value="${idComp}" >
                                  <input type="hidden" name="idTrab" value="${idTrab}" >
                                  <input class="form-control" name="uploadFile" type="file" />

                                  <div class="col-sm-6 col-md-12 mt-2 mb-4">
                                    <div class="form-floating">
                                        <button class="btn btn-primary justify-content-end me-2 col-6" type="submit" ><span class="fa-solid fas fa-camera me-2"></span><span>Subir Foto</span></button>
                                    </div>
                                  </div>
                              </form>
                        </div>
                        <div class="card mb-3">
                          <div class="card-body">
                            <div class="d-flex align-items-center mb-5">
                              <h3>Acerca de</h3>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-envelope-alt">  </span>
                                <h5 class="text-1000 mb-0">Email</h5>
                              </div><a href="mailto:shatinon@jeemail.com:">${email}</a>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-phone"> </span>
                                <h5 class="text-1000 mb-0">Telefono</h5>
                              </div><a href="tel:+1234567890">+${telefono}</a>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-directions"></span>
                                <h5 class="text-1000 mb-0">Dirección</h5>
                              </div><a href="#!">${direccion}</a>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-building"></span>
                                <h5 class="text-1000 mb-0">Nro Documento</h5>
                              </div>
                              <p class="mb-0 text-800">${nrodoc}</p>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-postcard"></span>
                                <h5 class="text-1000 mb-0">Puesto</h5>
                              </div>
                              <p class="mb-0 text-800">${puesto}</p>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"></span>
                                <h5 class="text-1000 mb-0">Ult. Actualización</h5>
                              </div>
                              <p class="mb-0 text-800">${fechaMod}</p>
                            </div>

                            <div>
                              <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-check-circle"></span>
                                <h5 class="text-1000 mb-0">Estado</h5>
                              </div><span class="badge badge-phoenix badge-phoenix-primary">Activo</span>
                            </div>
                          </div>
                        </div>

                        <jsp:include page="navsempleado.jsp"></jsp:include>

                      </div>
                      <div class="phoenix-offcanvas-backdrop d-lg-none top-0" data-phoenix-backdrop="data-phoenix-backdrop"></div>
                    </div>
                  </div>

                  <div class="col-md-7 col-lg-7 col-xl-8">
                    <div class="lead-details-container">
                      <div class="content2">
                          <nav class="mb-2" aria-label="breadcrumb">
                            <ol class="breadcrumb mb-0">
                              <li class="breadcrumb-item"><a href="#!">Page</a></li>
                              <li class="breadcrumb-item active">Default</li>
                            </ol>
                          </nav>
                          <div class="mb-9">
                            <div class="row g-3 mb-4">
                              <div class="col-auto">
                                <h2 id="h2top" class="mb-0">Insertar Sueldo Fijo</h2>
                              </div>
                            </div>

                            <div class="row g-5">
                                 <div class="col-xl-8">
                                   <div class="row gx-3 gy-4">
                                     <form class="row g-4 mb-0 needs-validation" method="POST" action="insertarSueldoFijo" novalidate >
                                            <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                                            <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                                            <div class="col-sm-6 col-md-12">
                                              <div class="form-floating">
                                                <select class="form-select" name="iexcodcon" required >
                                                  <option value="" selected > -- Seleccionar -- </option>
                                                  <c:forEach var="lovConcepSue" items="${lovConcepSue}">
                                                      <option value="${lovConcepSue.codConcepto}" >${lovConcepSue.desConcepto}</option>
                                                  </c:forEach>
                                                </select>
                                                <label>Concepto (*)</label>
                                              </div>
                                            </div>
                                            <div class="col-sm-6 col-md-12">
                                                   <div class="form-floating">
                                                     <input class="form-control" name="iexvalcon" maxlength="18" type="text" value="" placeholder="street" required/>
                                                     <label>Valor (*)</label>
                                                   </div>
                                            </div>


                                            <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                                Se grabó exitosamente los cambios.
                                            </div>
                                            <div class="col-12 gy-6">
                                                <div class="row g-3 justify-content-end">
                                                  <div class="col-auto">
                                                    <a class="btn btn-phoenix-primary" href="sueldoFijo@${idTrab}">Cancel</a>
                                                    <button class="btn btn-primary" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar Sueldo Fijo</button>
                                                  </div>
                                                </div>
                                            </div>
                                            <div class="modal fade" id="confirmModal" tabindex="-1">
                                                <div class="modal-dialog modal-dialog-centered">
                                                  <div class="modal-content border">
                                                    <form id="addEventForm" autocomplete="off">
                                                      <div class="modal-header border-200 p-4">
                                                        <h5 class="modal-title text-1000 fs-4 lh-sm">Confirmar</h5>
                                                        <button class="btn p-1 text-900" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs--1"></span></button>
                                                      </div>
                                                      <div class="modal-body pt-4 pb-2 px-4">
                                                        <div class="mb-3">
                                                          <label class="fw-bold mb-2 text-1000" for="leadStatus">Esta seguro que desea confirmar la operacion?</label>
                                                        </div>
                                                      </div>
                                                    </form>
                                                    <div class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
                                                        <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="button" data-bs-dismiss="modal" >Cancel</button>
                                                        <button class="btn btn-sm btn-primary px-9 fs--2 my-0" onclick="mostrarAlert();" type="submit" data-bs-dismiss="modal" >Confirmar</button>
                                                    </div>
                                                  </div>
                                                </div>
                                            </div>
                                     </form>
                                   </div>
                                 </div>
                            </div>
                          </div>
                        </div>
                  </div>
                </div>
              </div>
          </div>

          <jsp:include page="../../../demoWidget.jsp"></jsp:include>
    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->

    <jsp:include page="../../../customize.jsp"></jsp:include>
  </body>
</html>