<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jean Quiroz
    Email	   : jeanp.quiroz@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<table  class="table table-striped jambo_table bulk_action"  >
      <thead>
        <tr>
          <th style="width: 1%">Id</th>
          <th style="width: 20%">Compania</th>
          <th>Ruc</th>
          <th>Direccion</th>
          <th>Rol</th>
          <th style="width: 20%">Ver</th>
        </tr>
      </thead>
      <tbody>
          <c:forEach var="comp" items="${requestScope.compList}">
            <tr>
              <td>${comp.id_companias}</td>
              <td>
                  <a><h2>${comp.nombre}</h2></a>
              </td>
              <td>
                ${comp.ruc}
              </td>
              <td>
                ${comp.direccion}
              </td>
               <td>

              </td>
              <td>
                <a href="empleadosList@${comp.id_companias}@${comp.id_usuario}" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> Ver </a>
              </td>
            </tr>
         </c:forEach>
      </tbody>
</table>