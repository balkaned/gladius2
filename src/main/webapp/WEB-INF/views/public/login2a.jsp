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

<jsp:include page="links.jsp"></jsp:include>

<script>
      var phoenixIsRTL = window.config.config.phoenixIsRTL;
      if (phoenixIsRTL) {
        var linkDefault = document.getElementById('style-default');
        var userLinkDefault = document.getElementById('user-style-default');
        linkDefault.setAttribute('disabled', true);
        userLinkDefault.setAttribute('disabled', true);
        document.querySelector('html').setAttribute('dir', 'rtl');
      } else {
        var linkRTL = document.getElementById('style-rtl');
        var userLinkRTL = document.getElementById('user-style-rtl');
        linkRTL.setAttribute('disabled', true);
        userLinkRTL.setAttribute('disabled', true);
      }
    </script>
  </head>


  <body>
    <f:form id="form" methot="post" modelAttribute="usuarioConeccion" action="verificarLogin2">
    <!-- ===============================================-->
    <!--    Main Content-->
    <!-- ===============================================-->
    <main class="main" id="top">
      <div class="container">
        <div class="row flex-center min-vh-100 py-5">
          <div class="col-sm-10 col-md-8 col-lg-5 col-xl-5 col-xxl-3"><a class="d-flex flex-center text-decoration-none mb-4" href="../../../index.jsp">
              <div class="d-flex align-items-center fw-bolder fs-5 d-inline-block"><img src="resources/assets/img/icons/logoLast.png" alt="phoenix" width="58" />
              </div>
            </a>
            <div class="text-center mb-7">
              <h3 class="text-1000">Login</h3>
              <p class="text-700">Login interno local</p>
            </div>

            <button class="btn btn-phoenix-secondary w-100 mb-3"><span class="fab fa-google text-danger me-2 fs--1"></span>Sign in with google</button>
            <button class="btn btn-phoenix-secondary w-100"><span class="fab fa-facebook text-primary me-2 fs--1"></span>Sign in with facebook</button>

            <div class="position-relative">
              <hr class="bg-200 mt-5 mb-4" />
              <div class="divider-content-center">or use email</div>
            </div>
            <div class="mb-3 text-start">
              <label class="form-label" for="email">Usuario</label>
              <div class="form-icon-container">
                <!--<input class="form-control form-icon-input" id="email" type="email" placeholder="name@example.com" /><span class="fas fa-user text-900 fs--1 form-icon"></span>-->
                <f:input id="iptUs" type="text" path="user" cssClass="form-control form-icon-input" aria-describedby="emailHelp" placeholder="Ingrese Usuario" value=""/><span class="fas fa-user text-900 fs--1 form-icon"></span>
              </div>
            </div>
            <div class="mb-3 text-start">
              <label class="form-label" for="password">Contraseña</label>
              <div class="form-icon-container">
                <!--<input class="form-control form-icon-input" id="password" type="password" placeholder="Password" /><span class="fas fa-key text-900 fs--1 form-icon"></span>-->
                <f:input id="ippass" value="" type="password" path="pass" cssClass="form-control form-icon-input" placeholder="Ingrese Contraseña"/><span class="fas fa-key text-900 fs--1 form-icon"></span>
              </div>
            </div>
            <div class="row flex-between-center mb-7">
              <div class="col-auto">
                <div class="form-check mb-0">
                  <input class="form-check-input" id="basic-checkbox" type="checkbox" checked="checked" />
                  <label class="form-check-label mb-0" for="basic-checkbox">Remember me</label>
                </div>
              </div>
              <div class="col-auto"><a class="fs--1 fw-semi-bold" href="../../../pages/authentication/simple/forgot-password.html">Forgot Password?</a></div>
            </div>
            <button type="submit" class="btn btn-primary w-100 mb-3">Acceder</button>
            <div class="text-center"><a class="fs--1 fw-bold" href="../../../pages/authentication/simple/sign-up.html">Create an account</a></div>
            <span style="float:left; text-align:left; font-size: 11px; height:50px; width:280px; border:0px solid green; color:#D90000;">
                 ${mensaje}
           </span>
          </div>
        </div>
      </div>

    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->
    </f:form>
    </body>

</html>