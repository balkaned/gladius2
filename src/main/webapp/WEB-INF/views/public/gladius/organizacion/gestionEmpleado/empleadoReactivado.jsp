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
      function mostrarAlert(){
          //alert("se grabo exitosamente");
          var div=document.getElementById('alert');
          div.style.display = '';

          setTimeout(function() {
              $("#alerts").hide(6000);
          }, 3000);
      }

      function formatearFecha1(){
          var fechaSeleccionada = $('#fechaing').val();

          var anio=fechaSeleccionada.substring(0, 4);
          var mes=fechaSeleccionada.substring(5, 7);
          var dia=fechaSeleccionada.substring(8, 10);

          var fechaFormat=dia+"/"+mes+"/"+anio;
          $("#fechaing").val(fechaFormat);
      }

     function gettra(){
          document.getElementById("accion").value="SELPIN2";
          document.getElementById("forms01").submit();
     }

     function reingtra(){
        var Fecha_ing = document.getElementById("fechaing").value.split("/");
        var Fecha1 = new Date(parseInt(Fecha_ing[2]),parseInt(Fecha_ing[1]-1),parseInt(Fecha_ing[0]));

        var Fecha_finult = document.getElementById("ultfecharet").value.split("/");
        var Fecha2 = new Date(parseInt(Fecha_finult[2]),parseInt(Fecha_finult[1]-1),parseInt(Fecha_finult[0]));

        if (Fecha1 > Fecha2 ){
                 alert ("La fecha es correcta");
                 document.getElementById("accion").value="REING";
                 document.getElementById("forms01").submit();
        }else{
              var opcion = confirm("La nueva fecha de ingreso debe ser mayor a la ultima fecha de cese");
              if (opcion == true) {
                    return false;
              } else {
                    return false;
              }
        }
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
                <li class="breadcrumb-item"><a href="#!">Trabajadores</a></li>
                <li class="breadcrumb-item active">Reingreso</li>
              </ol>
            </nav>
            <div class="mb-9">
              <div class="row g-3 mb-4">
                <div class="col-auto">
                  <h2 id="h2top" class="mb-0">Trabajador reingresado !</h2>
                </div>
              </div>

              <div class="row g-5">
                   <div class="col-xl-8">
                     <div class="row gx-3 gy-4">
                       <form name ="forms01"  id ="forms01" class="row g-4 mb-0 needs-validation" method="POST" action="procesarEmpleadoInactivo" novalidate>
                          <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                              <input class="form-control" name="iexcodtra" type="hidden" value="${requestScope.emp.iexcodtra}" />
                              <input type="hidden"  id="accion" name="accion" value="VAL">

                              <div class="col-sm-6 col-md-3">
                                   <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nuevo ID Trab</label>
                                   <input class="form-control" name="idtrabajador" id="idtrabajador"  type="text" value="${requestScope.xtrabnuevo.iexcodtra}" required readonly disabled/>
                              </div>
                              <div class="col-sm-6 col-md-9">
                                     <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Trabajador</label>
                                     <input class="form-control" name="nombres"  id="nombres" type="text" value="${requestScope.xtrabnuevo.iexapepat} ${requestScope.xtrabnuevo.iexapemat} ${requestScope.xtrabnuevo.iexnomtra}" required readonly disabled/>
                              </div>
                              <div class="col-sm-6 col-md-3">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Tipo Documento</label>
                                    <input class="form-control" name="tipodocumento"  id="tipodocumento" type="text" value="${requestScope.xtrabnuevo.iextipdocid}" required readonly disabled/>
                              </div>
                              <div class="col-sm-6 col-md-5">
                                      <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nro Documento</label>
                                      <input class="form-control" name="nrodoc"   id="nrodoc" type="text" value="${requestScope.xtrabnuevo.iexnrodoc}" required readonly disabled/>
                              </div>
                              <div class="col-sm-6 col-md-4">
                                 	  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2" >Fecha Ingreso</label>
                                 	  <input class="form-control" name="fechaing"  id="fechaing"  type="text" value="${requestScope.xtrabnuevo.iexfecing}" required readonly disabled/>
                              </div>

                              <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                  Se grabó exitosamente los cambios.
                              </div>
                              <div class="col-12 gy-6">
                                  <div class="row g-3 justify-content-end">
                                    <div class="col-auto">
                                      <a class="btn btn-phoenix-primary px-5" href="listEmpleados">Ir a Lista Trabajadores</a>
                                    </div>
                                    <div class="col-auto">
                                      <!--<button class="btn btn-primary px-5 px-sm-9" onclick="return reingtra();" type="submit" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Reingresar Trabajador</button>-->
                                    </div>
                                  </div>
                              </div>
                              <!--<div class="modal fade" id="confirmModal" tabindex="-1">
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
                              </div>-->
                       </form>
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