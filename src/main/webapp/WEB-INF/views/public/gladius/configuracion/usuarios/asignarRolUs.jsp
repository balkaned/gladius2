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
           $('#lov_compania').change(function(event){
               $.ajaxSetup({cache:false});
                    $.ajax({
                      url: "getLovsLOVCODTRAxUSU",
                      data: {
                            "accion": "getLovsLOVCODTRAxUSU",
                            "iexcodcia": $("#lov_compania").val()
                      },
                      success: function (data) {
                           var opt = "";
                           opt += "<option value='' >Seleccionar</option>";
                           for (var i in data) {
                                opt += "<option value="+data[i].iexcodtra+" > "+data[i].iexapepat+" "+data[i].iexapemat+" "+data[i].iexnomtra+" - "+data[i].iexfecing+" </option> ";
                           }
                          $("#iexcodtra").html(opt);
                      }
                    });
           });
     });

     function remove() {
        var opcion = confirm("Esta seguro de Eliminar el Registro?");
        if (opcion == true) {
            return true;
        } else {
            return false;
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
                <li class="breadcrumb-item"><a href="#!">Page</a></li>
                <li class="breadcrumb-item active">Default</li>
              </ol>
            </nav>
            <div class="mb-12">
              <div class="row g-3 mb-4">
                <div class="col-auto">
                  <h2 id="h2top" class="mb-0">Asignar rol usuario</h2>
                </div>
              </div>

              <div class="row g-5">
                 <div class="col-xl-10">
                   <div class="row gx-3 gy-4">
                      <form class="row g-4 mb-0 needs-validation" method="POST" action="asignarRolxCiaIns" novalidate >
                        <input id="usuario_id" type="hidden" name="usuario_id" value="${idUsu}"  />

                        <div class="col-sm-6 col-md-7">
                        	  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Compania</label>
                        	  <select name="lov_compania" id="lov_compania" class="form-select" required >
                        		  <option value="" selected >Seleccionar</option>
                        		  <c:forEach var="listacia" items="${listacia}">
                        			  <option value="${listacia.idCodcia}" >${listacia.descCia}</option>
                        		  </c:forEach>
                        	  </select>
                        </div>
                        <div class="col-sm-6 col-md-5">
                        	  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Rol de Acceso</label>
                        	  <select name="lov_rol" class="form-select" required >
                        		  <option value="" selected >Seleccionar</option>
                        		  <c:forEach var="listarol" items="${listarol}">
                        			  <option value="${listarol.idRole}" >${listarol.desRole}</option>
                        		  </c:forEach>
                        	  </select>
                        </div>
                        <div class="col-sm-6 col-md-7">
                        	  <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Trabajador</label>
                        	  <select name="iexcodtra" id="iexcodtra" class="form-select" >
                        		  <option value="" selected >Seleccionar</option>
                        		  <c:forEach var="LstTrabajadorCia" items="${LstTrabajadorCia}">
                        			  <option value="${LstTrabajadorCia.iexcodtra}"  ${LstTrabajadorCia.iexcodtra == requestScope.iexcodtra ? 'selected' : ''}  >${LstTrabajadorCia.iexapepat} ${LstTrabajadorCia.iexapemat} ${LstTrabajadorCia.iexnomtra} - ${LstTrabajadorCia.iexfecing}</option>
                        		  </c:forEach>
                        	  </select>
                        </div>

                        <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                Se grabó exitosamente los cambios.
                        </div>
                        <div class="col-12 gy-6">
                            <div class="row g-3 justify-content-end">
                              <div class="col-auto">
                                <a class="btn btn-phoenix-primary px-5" href="listUsuarios">Atras</a>
                              </div>
                              <div class="col-auto">
                                <button class="btn btn-primary px-5 px-sm-15" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" ><span class="fas fa-plus me-2"></span>Agregar a Lista</button>
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

              <div class="mt-4" id="orderTable" data-list='{"valueNames":["order","total","customer","payment_status","fulfilment_status","delivery_type","date"],"page":10,"pagination":true}'>

                  <div class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1">
                    <div class="table-responsive scrollbar mx-n1 px-1">
                      <table class="table table-sm fs--1 mb-0">
                        <thead>
                          <tr>
                            <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                              <div class="form-check mb-0 fs-0">
                                <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                              </div>
                            </th>
                            <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="order">COMPAÑIA</th>
                            <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">ROL</th>
                            <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">CODTRA</th>
                            <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">NOMBRE TRABAJADOR</th>
                            <th class="sort align-middle text-center ps-5" scope="col" data-sort="date">USUARIO Y FECHA</th>
                            <th class="sort align-middle text-center ps-5" scope="col" ></th>
                          </tr>
                        </thead>
                        <tbody class="list" id="order-table-body">
                            <c:forEach var="usuxciaxrol" items="${requestScope.usuxciaxrol}">
                              <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                <td class="fs--1 align-middle px-0 py-3">
                                  <div class="form-check mb-0 fs-0">
                                    <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}' />
                                  </div>
                                </td>
                                <td class="total align-middle text-start fw-semi-bold text-1000 ps-5">${usuxciaxrol.descia}</td>
                                <td class="total align-middle text-start fw-semi-bold text-1000 ps-5"><span class="badge badge-phoenix fs--1 badge-phoenix-info"><span class="badge-label">${usuxciaxrol.desrol}</span></td>
                                <td class="total align-middle text-center fw-semi-bold text-1000 ps-5">${usuxciaxrol.codtra}</td>
                                <td class="total align-middle text-start fw-semi-bold text-1000 ps-5">${usuxciaxrol.destra}</td>
                                <td class="total align-middle text-start fw-semi-bold text-1000 ps-5">${usuxciaxrol.usucreades} ${usuxciaxrol.feccrea}</td>

                                <td class="align-middle text-end white-space-nowrap pe-0 action">
                                   <div class="font-sans-serif btn-reveal-trigger position-static">
                                     <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                     <div class="dropdown-menu dropdown-menu-end py-2">

                                          <div class="dropdown-divider"></div>
                                          <a class="dropdown-item text-danger" onclick="return remove();" href="eliminarRolXciaUsu@${usuxciaxrol.codusu}@${usuxciaxrol.codrol}@${usuxciaxrol.codcia}">Eliminar</a>
                                     </div>
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