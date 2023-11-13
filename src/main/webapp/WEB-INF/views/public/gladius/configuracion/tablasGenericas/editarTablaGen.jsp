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
            <div class="mb-9">
              <div class="row g-3 mb-4">
                <div class="col-auto">
                  <h2 id="h2top" class="mb-0">Editar Tabla Genérica</h2>
                </div>
              </div>

              <div class="row g-5">
                   <div class="col-xl-7">
                     <div class="row gx-3 gy-4">
                       <form class="row g-4 mb-0 needs-validation" method="POST" action="modificarTblGen" novalidate >
                              <input class="form-control" name="iexcodcia" type="hidden" value="${requestScope.emp.iexcodcia}" />
                              <input class="form-control" name="iexcodrol2" type="hidden" value="${requestScope.ttablacx.iexcodtab}" />

                              <div class="col-sm-6 col-md-3">
                                     <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">ID (*)</label>
                                     <input class="form-control" name="iexcodrol" type="number"  value="${requestScope.ttablacx.iexcodtab}" required readonly disabled/>
                              </div>
                              <div class="col-sm-6 col-md-12">
                                    <label class="form-label fs-0 text-1000 ps-0 text-none mb-2">Nombre de la Tabla (*)</label>
                                    <input class="form-control" name="iexdesrol" type="text" value="${requestScope.ttablacx.iexdestab}" placeholder="" required/>
                              </div>

                              <table class="navy">
                                   <tr>
                                      <td>Des</td>
                                      <td><input type="checkbox" name='iexlblflg1' value='1' value='1' ${requestScope.ttablacx.iexlblflg1=='1' ? 'checked=true' : ''} class="form-check-input"  ></td>
                                      <td class="ps-2"><input type='text' name='iexlbl1' value="${requestScope.ttablacx.iexlbl1}" class="form-control" style="width: 250px;" ></td>
                                      <td class="ps-3">des</td>
                                      <td><input type="checkbox" name='iexlblflg9' value='1' ${requestScope.ttablacx.iexlblflg9=='1' ? 'checked=true' : ''} class="form-check-input"  ></td>
                                      <td class="ps-2"><input type='text' name='iexlblval9' value="${requestScope.ttablacx.iexlblval9}" class="form-control" style="width: 250px;"  ></td>
                                   </tr>
                                   <tr>
                                      <td>Des</td>
                                      <td><input type="checkbox" name='iexlblflg2' value='1' ${requestScope.ttablacx.iexlblflg2=='1' ? 'checked=true' : ''} class="form-check-input" ></td>
                                      <td class="ps-2"><input type='text' name='iexlbl2' value="${requestScope.ttablacx.iexlbl2}" class="form-control" style="width: 250px;"   ></td>
                                      <td class="ps-3">des</td>
                                      <td><input type="checkbox" name='iexlblflg10'  value='1' ${requestScope.ttablacx.iexlblflg10=='1' ? 'checked=true' : ''} class="form-check-input" ></td>
                                      <td class="ps-2"><input type='text' name='iexlblval10' value="${requestScope.ttablacx.iexlblval10}" class="form-control" style="width: 250px;"  ></td>
                                   </tr>
                                   <tr>
                                      <td>Des</td>
                                      <td><input type="checkbox" name='iexlblflg3' ${requestScope.ttablacx.iexlblflg3=='1' ? 'checked=true' : ''} value='1' class="form-check-input"  ></td>
                                      <td class="ps-2"><input type='text' name='iexlbl3' value="${requestScope.ttablacx.iexlbl3}" class="form-control" style="width: 250px;"></td>
                                      <td class="ps-3">des</td>
                                      <td><input type="checkbox" name='iexlblflg11' value='1' ${requestScope.ttablacx.iexlblflg11=='1' ? 'checked=true' : ''} class="form-check-input"  ></td>
                                      <td class="ps-2"><input type='text' name='iexlblval11' value="${requestScope.ttablacx.iexlblval11}" class="form-control" style="width: 250px;"  ></td>
                                   </tr>
                                   <tr>
                                      <td>Des</td>
                                      <td><input type="checkbox" name='iexlblflg4' value='1' ${requestScope.ttablacx.iexlblflg4=='1' ? 'checked=true' : ''} class="form-check-input"  ></td>
                                      <td class="ps-2"><input type='text' name='iexlbl4' value="${requestScope.ttablacx.iexlbl4}" class="form-control"   style="width: 250px;"  ></td>
                                      <td class="ps-3">des</td>
                                      <td><input type="checkbox" name='iexlblflg12' value='1' ${requestScope.ttablacx.iexlblflg12=='1' ? 'checked=true' : ''} class="form-check-input" ></td>
                                      <td class="ps-2"><input type='text' name='iexlblval12' value="${requestScope.ttablacx.iexlblval12}" class="form-control"  style="width: 250px;"   ></td>
                                   </tr>
                                   <tr>
                                      <td>Des</td>
                                      <td><input type="checkbox" name='iexlblflg5' value='1' ${requestScope.ttablacx.iexlblflg5=='1' ? 'checked=true' : ''} class="form-check-input" ></td>
                                      <td class="ps-2"><input type='text' name='iexlbl5' value="${requestScope.ttablacx.iexlbl5}" class="form-control" style="width: 250px;" ></td>
                                      <td class="ps-3">des</td>
                                      <td><input type="checkbox" name='iexlblflg13'  value='1' ${requestScope.ttablacx.iexlblflg13=='1' ? 'checked=true' : ''} class="form-check-input" ></td>
                                      <td class="ps-2"><input type='text' name='iexlblval13' value="${requestScope.ttablacx.iexlblval13}" class="form-control"  style="width: 250px;"  ></td>
                                   </tr>
                                   <tr>
                                      <td>Des</td>
                                      <td><input type="checkbox" name='iexlblflg6' value='1' ${requestScope.ttablacx.iexlblflg6=='1' ? 'checked=true' : ''} class="form-check-input"  ></td>
                                      <td class="ps-2"><input type='text' name='iexlbl6' value="${requestScope.ttablacx.iexlbl6}" class="form-control" style="width: 250px;"  ></td>
                                      <td class="ps-3">des</td>
                                      <td><input type="checkbox" name='iexlblflg14'  value='1' ${requestScope.ttablacx.iexlblflg14=='1' ? 'checked=true' : ''} class="form-check-input" ></td>
                                      <td class="ps-2"><input type='text' name='iexlblval14' value="${requestScope.ttablacx.iexlblval14}" class="form-control"  style="width: 250px;" ></td>
                                  </tr>
                                  <tr>
                                      <td>Des</td>
                                      <td><input type="checkbox" name='iexlblflg7' value='1' ${requestScope.ttablacx.iexlblflg7=='1' ? 'checked=true' : ''} class="form-check-input"  ></td>
                                      <td class="ps-2"><input type='text' name='iexlbl7' value="${requestScope.ttablacx.iexlbl7}" class="form-control" style="width: 250px;"></td>
                                      <td class="ps-3">des</td>
                                      <td><input type="checkbox" name='iexlblflg15' value='1' ${requestScope.ttablacx.iexlblflg15=='1' ? 'checked=true' : ''} class="form-check-input"  ></td>
                                      <td class="ps-2"><input type='text' name='iexlblval15' value="${requestScope.ttablacx.iexlblval15}" class="form-control"  style="width: 250px;"  ></td>
                                  </tr>
                                  <tr>
                                      <td>Des</td>
                                      <td><input type="checkbox" name='iexlblflg8' ${requestScope.ttablacx.iexlblflg8=='1' ? 'checked=true' : ''} value='1' class="form-check-input"  ></td>
                                      <td class="ps-2"><input type='text' name='iexlbl8' value="${requestScope.ttablacx.iexlbl8}" class="form-control"  style="width: 250px;"></td>
                                      <td class="ps-3">des</td>
                                      <td><input type="checkbox" name='iexlblflg16' ${requestScope.ttablacx.iexlblflg16=='1' ? 'checked=true' : ''} value='1' class="form-check-input"  ></td>
                                      <td class="ps-2"><input type='text' name='iexlblval16' value="${requestScope.ttablacx.iexlblval16}" class="form-control" style="width: 250px;"></td>
                                  </tr>
                              </table>

                              <div class="alert alert-success" role="alert" id="alert" style="display:none;">
                                  Se grabó exitosamente los cambios.
                              </div>
                              <div class="col-12 gy-6">
                                  <div class="row g-3 justify-content-end">
                                    <div class="col-auto">
                                      <a class="btn btn-phoenix-primary px-5" href="listTablasGen">Cancel</a>
                                    </div>
                                    <div class="col-auto">
                                      <button class="btn btn-primary px-5 px-sm-15" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent" >Guardar Tabla Gen</button>
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
          <jsp:include page="../../../demoWidget.jsp"></jsp:include>
    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->
    <jsp:include page="../../../customize.jsp"></jsp:include>
  </body>
</html>