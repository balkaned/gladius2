<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jan Quiroz
    Email	   : janquirozs@gmail.com
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
  <style>
    body {
        background-image: url("resources/assets/img/bg/bgloginglad7.jpg");
    }

    .labelglad{
        font-size:19px;
        color:black;
    }

    #logobanner{
        border-radius: 16px 16px 0px 0px;
        background-image: url("resources/assets/img/bg/bgLogo7.png");
        height:100px;
    }

    #spanIconEye{
        border:0px solid red;
        margin-top:-10px;
    }
  </style>

  <body>
    <f:form id="form" methot="post" modelAttribute="usuarioConeccion" action="verificarLogin2">
        <!-- ===============================================-->
        <!--    Main Content-->
        <!-- ===============================================-->
        <main class="main" id="top">
          <div class="container">
            <div class="row flex-center min-vh-100 py-5">
              <div class="col-sm-12 col-md-4">
                <!--<a class="d-flex flex-center text-decoration-none mb-2" href="../../../index.jsp">
                    <div class="d-flex align-items-center fw-bolder fs-5 d-inline-block">
                        <img src="resources/assets/img/icons/logoLast3.png" alt="" width="58" />
                    </div>
                </a>-->
                <div class="text-center mb-3">
                  <!--<h3 class="text-1000 text-white">Login</h3>-->
                  <!--<h1 class="display-6 text-white fs-0 ">Login al nuevo y renovado Gladius</h1>-->
                </div>

                <!--<button class="btn btn-phoenix-warning w-100 border border-500 mb-2"><span class="fab fa-google text-danger me-2 fs--1"></span>Sign in with google</button>
                <button class="btn btn-phoenix-info w-100 border border-500"><span class="fab fa-facebook text-primary me-2 fs--1"></span>Sign in with facebook</button>-->

                <!--<div class="position-relative">
                  <hr class="bg-200 mt-5 mb-4" />
                  <div class="divider-content-center">or use email</div>
                </div>-->
                <div class="bg-white border border-0 rounded-4 col-12 mt-0">
                    <div id="logobanner" class="bg-dark ms-0 mb-0 pb-0 border-0" >
                    </div>
                    <div class="mt-0 mb-0">
                        <a class="d-flex flex-center text-decoration-none mb-0" href="../../../index.jsp">
                            <div class="d-flex align-items-center mt-1 fw-bolder fs-5 d-inline-block">
                                <img class="mt-2" src="resources/assets/img/icons/logoLoginGlad4.png" alt="" width="150" />
                                <!--<label class="mt-3 text-black fs-2 fs-semi-bold" >Gladius</label>-->
                            </div>
                        </a>
                        <!--<label class="p-4 mt-0 mb-0 fs--1 text-600 text-start">Inicie sessión para obtener acceso a todas las funcionalidades de planillas, y cálculos de planillas, también obtendrá acceso a la información de cada trabajador y más.</label>-->
                    </div>

                    <div class="p-4 mt-0">
                        <div class="mb-2 text-start col-sm-6 col-md-12">
                          <label class="form-label" for="email">Usuario</label>
                          <div class="form-icon-container">
                            <!--<input class="form-control form-icon-input" id="email" type="email" placeholder="name@example.com" /><span class="fas fa-user text-900 fs--1 form-icon"></span>-->
                            <f:input id="iptUs" type="text" path="user" cssClass="form-control" aria-describedby="emailHelp" placeholder="Ingrese Usuario" value=""/>
                          </div>
                        </div>

                        <div class="mb-3 text-start col-sm-6 col-md-12">
                          <label class="form-label" for="password">Contraseña</label>
                          <div class="form-icon-container" data-password="data-password">
                            <f:input class="form-control form-icon-input" value="" id="ippass" path="pass" type="password" placeholder="Ingrese una constraseña" data-password-input="data-password-input" /><span class="fa-regular fa-eye text-body fs-0 form-icon"></span>
                          </div>
                        </div>
                        <div class="row flex-between-center mb-4">
                          <div class="col-sm-6">
                            <div class="form-check mb-0">
                              <!--<input class="form-check-input" id="basic-checkbox" type="checkbox" checked="checked" />-->
                              <!--<label class="form-check-label mb-0" for="basic-checkbox">Recordar contraseña</label>-->
                            </div>
                          </div>
                          <div class="col-auto"><a class="fs--1 fw-semi-bold" href="../../../pages/authentication/simple/forgot-password.html">Olvidó su contraseña?</a></div>
                        </div>
                        <!--<button type="submit" class="btn btn-primary w-100 mb-3" href="#"><span class="fas fa-rocket me-2"></span>Ingresar</button>-->
                        <button type="submit" class="btn btn-primary bg-black border rounded-5 w-100 mb-3" href="#">Iniciar sesión</button>
                        <!--<button class="btn btn-link text-900 me-4 px-0"><span class="fa-solid fa-file-export fs--1 me-2"></span>Export</button>
                        <button type="submit" class="btn btn-primary w-100 mb-3">Ingresar</button>-->
                        <!--<div class="text-center"><a class="fs--1 fw-bold" href="../../../pages/authentication/simple/sign-up.html">Create an account</a></div>-->

                        <!--<span style="float:left; text-align:left; font-size: 11px; height:50px; width:280px; border:0px solid green; color:#D90000;">
                             ${mensaje}
                       </span>-->

                       <div class="col-xl-12">
                            <c:if test="${mensaje!=null}">
                               <div id="alert" class="alert alert-outline-danger bg-danger bg-opacity-10 d-flex align-items-center alert-dismissible fade show" role="alert">
                                    <span class="fa-regular fa-times-circle text-danger fs-0 me-3"></span>
                                    <p class="mb-0 text-1000 fs--1 col-12">${mensaje} <a href="#" class="fs--1">Mas información.</a></p>
                                    <a class="text-danger fs-0 fw-bold ms-3" href="#" data-bs-dismiss="alert" aria-label="Close">x</a>
                                </div>
                            </c:if>
                       </div>
                    </div>
                </div>
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