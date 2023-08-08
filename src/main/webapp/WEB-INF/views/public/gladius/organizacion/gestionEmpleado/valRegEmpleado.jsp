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
                                    <option value="02">Ruc</option>
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
                              <button class="btn btn-primary" type="submit" >Validar</button>
                          </div>

                          <div align="center" class="navy">
                              <table>
                                  <tr>
                                      <td><span style="font-size:14px;">${requestScope.msg}</span></td>
                                  </tr>
                                    <c:forEach var="LstPerRegistrada" items="${requestScope.LstPerRegistrada}">
                                      <tr>
                                          <td><span style="font-size:14px;">Trabajador: ${LstPerRegistrada.iexcodtra}  - ${LstPerRegistrada.iexapepat} ${LstPerRegistrada.iexapemat}  ${LstPerRegistrada.iexnomtra}</span></td>
                                      </tr>
                                    </c:forEach>
                              </table>
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