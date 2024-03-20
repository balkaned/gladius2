<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jean Quiroz
    Email	   : jeanp.quiroz@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="links.jsp"></jsp:include>
<jsp:include page="navTop_ecomp.jsp"></jsp:include>

<style>
.btn.btn-primary{

}

.d-flex.align-items-center h4{
margin-left:50px !important;
font-size:16px;
}

.card-body{
border-radius:30px;
/*background-color:white;*/
}

.content{
/*background-color:#F1F5F9;*/
}

.badge.badge-phoenix.fs--2.mb-4.badge-phoenix-success{
margin-top:3px;
}

#imgcompany{
    border:0px solid red;
    width:80px;
    height:80px;
    margin-left:250px;
    border-radius:50px;
}

.mt-lg-3.mt-xl-0{
margin-top:10px !important;
}
</style>
      <div class="content">
        <nav class="mb-2" aria-label="breadcrumb">
          <ol class="breadcrumb mb-0">

          </ol>
        </nav>
        <div class="row gx-6 gy-3 mb-2 align-items-center">
          <div class="col-12 mb-0">
            <h2 id="h2top" class="mb-2">Compañias</h2>
            <h5 class="text-700 fw-semi-bold text-none">Seleccionar compañia a ingresar</h5>
          </div>
          <div class="row col-12 mt-2 ms-0">
            <div class="col-auto ps-2 pe-0">
                <a class="btn btn-primary mb-1 btn-sm ms-0" href="#"><i class="fa-solid fa-plus me-2"></i>Add compañia</a>
            </div>
            <div class="col-auto ps-2 pe-0">
                <a class="btn btn-phoenix-secondary mb-1 btn-sm" href="https://www.balkaned.com" ><span class="fas fa-earth-americas me-2"></span>Ir a sitio web</a>
            </div>
            <div class="col-auto ps-2 pe-0">
                <a class="btn btn-phoenix-secondary mb-1 text-900  btn-sm" href="logoff"><span class="fa-solid fa-key fs--1 me-2"></span>Log out</a>
            </div>
            <div class="col-auto ps-2">
                <div class="search-box me-3">
                    <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                      <input class="form-control search-input search" type="search" placeholder="Search compañias" aria-label="Search" />
                      <span class="fas fa-search search-box-icon"></span>
                    </form>
                </div>
            </div>
          </div>
        </div>

        <div class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 row-cols-xxl-4 g-3 mb-9">
            <c:forEach var="comp" items="${requestScope.compList}">
              <div class="col" style="width:380px;">
                <div class="card h-100 hover-actions-trigger">
                  <div class="card-body">
                    <div class="d-flex align-items-center">
                      <h4 class="mb-2 line-clamp-1 lh-sm flex-1 me-5">${comp.nombre}</h4>
                      <div class="hover-actions top-0 end-0 mt-4 me-4">
                        <a class="btn btn-primary btn-icon flex-shrink-0" href="home@${comp.id_companias}@${comp.id_usuario}"><span class="fa-solid fa-chevron-right"></span></a>
                      </div>
                    </div>
                    <span class="badge badge-phoenix fs--2 mb-4 badge-phoenix-success"><span class="badge-label">Activo</span></span>

                    <img id="imgcompany"
                    src="AWSorFTP_flgsource@verLogo@${comp.id_companias}@null@${comp.urlLogo}@null@null@null@null@null@null"
                    class="avatar" alt="Avatar">

                    <div class="d-flex align-items-center mb-2">
                        <p class="fw-bold mb-0 text-truncate lh-1">Tipo de conexión :
                        <span class="fw-semi-bold text-primary ms-1">${comp.iexflgsource} </span>
                        <c:if test="${comp.iexflgsource=='1'}"><i style="font-size: 20px;" class="fa-brands fa-aws ps-2 pe-2"></i>AWS S3</c:if>
                        <c:if test="${comp.iexflgsource=='2'}"><i style="font-size: 20px;" class="fa-solid fa-upload ps-2 pe-2"></i>FTP Filezilla</c:if>
                        </p>
                    </div>
                    <div class="d-flex align-items-center mb-2">
                        <span class="fa-solid far fa-newspaper me-2 text-700 fs--1 fw-extra-bold"></span>
                        <p class="fw-bold mb-0 text-truncate lh-1">RUC : <span class="fw-semi-bold text-primary ms-1">${comp.ruc}</span></p>
                    </div>
                    <div class="d-flex align-items-center mb-4">
                        <span class="fa-solid fas fa-bus me-2 text-700 fs--1 fw-extra-bold"></span>
                        <p class="fw-bold mb-0 lh-1">Dirección: <span class="ms-1 text-1100">${comp.direccion}</span></p>
                    </div>
                    <div class="d-flex justify-content-between text-700 fw-semi-bold">
                      <p class="mb-2"> Schema</p>
                      <p class="mb-2 text-1100">100%</p>
                    </div>
                    <div class="progress bg-success-100">
                      <div class="progress-bar rounded bg-${comp.schema}" role="progressbar" aria-label="Success example" style="width: 100%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                    <!--<div class="d-flex align-items-center mt-4">
                      <p class="mb-0 fw-bold fs--1">Started:<span class="fw-semi-bold text-600 ms-1">	17th Nov. 2020</span></p>
                    </div>
                    <div class="d-flex align-items-center mt-2">
                      <p class="mb-0 fw-bold fs--1">Deadline: <span class="fw-semi-bold text-600 ms-1">	21st May 2028</span></p>
                    </div>

                    <div class="mt-lg-3 mt-xl-0"> <i class="fa-solid fa-list-check me-1"></i>
                        <p class="d-inline-block fw-bold mb-0">287<span class="fw-normal">	Task</span></p>
                    </div>-->
                  </div>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>





