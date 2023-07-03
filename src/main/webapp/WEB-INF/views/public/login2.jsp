<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jean Quiroz
    Email	   : jeanp.quiroz@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>

  </head>

  <body class="login">
  <f:form id="form" methot="post" modelAttribute="usuarioConeccion" action="verificarLogin2">
     <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
            <div align="center"><img src="resources/assets/img/icon1.png"  width="130" height="85"></div>
          <section class="login_content">
            <form  action="Login" method="POST" >
              <h1>Segundo Login de Verificacion</h1>
              <div>
                <!--<input type="text" class="form-control" placeholder="Username" required=""  name="usuario" />-->
                <f:input id="iptUs" type="text" path="user" cssClass="form-control form-icon-input" aria-describedby="emailHelp" placeholder="Ingrese Usuario" value=""/><span class="fas fa-user text-900 fs--1 form-icon"></span>
              </div>
              <div>
                <!--<input type="password" class="form-control" placeholder="Password" required=""  name="password" />-->
                <f:input id="ippass" value="" type="password" path="pass" cssClass="form-control form-icon-input" placeholder="Ingrese Contraseña"/><span class="fas fa-key text-900 fs--1 form-icon"></span>
              </div>
              <div>
                 <!--<button type="submit" class="btn btn-secondary btn-lg">Ingresar</button>-->
                 <button type="submit" class="btn btn-primary w-100 mb-3">Login2</button>
              </div>
               <span style="float:left; text-align:left; font-size: 11px; height:50px; width:280px; border:0px solid green; color:#D90000;">
                     ${mensaje}
               </span>

              <div class="clearfix"></div>

              <div class="separator">
                
                  <c:if test="${requestScope.msgErrorId eq 'ERROR'}">  
                       <div class="alert alert-success alert-dismissible " role="alert">
                                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
                                                </button>
                                                  <strong> ${requestScope.msg}</strong> 
                                              </div>
                
                   </c:if>
                <div class="clearfix">BALKANED SOFTWARE</div>
                <br />

                <div>
                  <h1></h1>
                  <p>©2018 All Rights Reserved. Privacy and Terms</p>
                </div>
              </div>
            </form>
          </section>
        </div>
    </f:form>
  </body>
</html>
