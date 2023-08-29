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
                  <h2 id="h2top" class="mb-0">Validar registro de personal</h2>
                </div>
              </div>

              <div class="row g-5">
                   <div class="col-xl-5">
                     <div class="row gx-3 gy-4">
                       <form class="row g-4 mb-0 needs-validation" method="POST" action="validarNroDoc" novalidate >
                          <input class="form-control" name="accion" type="hidden" value="VAL" />
                          <div class="col-sm-6 col-md-12">
                            <div class="form-floating">
                                <select name="iextipdocid" class="form-select" required >
                                    <option value="01">DNI</option>
                                    <option value="02">RUC</option>
                                    <option value="04">CE</option>
                                </select>
                                <label>Tipo Documento (*)</label>
                            </div>
                          </div>
                          <div class="col-sm-6 col-md-12">
                            <div class="form-floating">
                              <input class="form-control" name="iexnrodocid" maxlength="15" type="text" value="" placeholder="street" required/>
                              <label>Nro de Documento</label>
                            </div>
                          </div>

                          <div class="col-12 d-flex justify-content-end mt-6">
                              <div class="col-auto">
                                    <a class="btn btn-phoenix-primary px-5" href="listEmpleados">Atras</a>
                                    <button class="btn btn-primary" type="submit"><span></span>Validar</a>
                              </div>
                           </div>

                          <c:forEach var="LstPerRegistrada" items="${requestScope.LstPerRegistrada}">
                              <div class="border-bottom py-4">
                                  <div class="d-flex">
                                    <div class="d-flex bg-primary-100 rounded-circle flex-center me-3 bg-primary-100" style="width:25px; height:25px"><span class="fa-solid text-primary-600 dark__text-primary-300 fs--1 fa-clipboard text-primary-600 dark__text-primary-300"></span></div>
                                    <div class="flex-1">
                                      <div class="d-flex justify-content-between flex-column flex-xl-row mb-2 mb-sm-0">
                                        <div class="flex-1 me-2">
                                          <h5 class="text-1000 lh-sm">${msg}</h5>
                                          <p class="fs--1 mb-0">${LstPerRegistrada.iexcodtra}<a class="ms-1" href="#!">Trabajador</a><span style="margin-left:10px;margin-bottom:6px;" class="badge badge-phoenix fs--2 badge-phoenix-danger"><span class="badge-label">Documento En uso</span><span class="ms-1" data-feather="x" style="height:12.8px;width:12.8px;"></span></span></p>
                                        </div>
                                        <!--<div class="fs--1"><span class="fa-regular fa-calendar-days text-primary me-2"></span><span class="fw-semi-bold">22 September, 2022, 4:33 PM</span></div>-->
                                      </div>
                                      <p class="fs--1 mb-0">${LstPerRegistrada.iexapepat} ${LstPerRegistrada.iexapemat}  ${LstPerRegistrada.iexnomtra}</p>
                                    </div>
                                  </div>
                              </div>
                          </c:forEach>
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