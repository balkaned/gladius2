<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jan Quiroz
    Email	   : janquirozs@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="links.jsp"></jsp:include>
<jsp:include page="navTop_ecomp.jsp"></jsp:include>

<style>
.d-flex.align-items-center h4{
    margin-left:50px !important;
    font-size:15px;
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

#texto{
    margin-top:-50px;
}
</style>

<div class="content bg-100">
    <nav class="mb-0" aria-label="breadcrumb">
      <ol class="breadcrumb mb-0">
      </ol>
    </nav>
    <div class="row gx-6 gy-3 mb-0 align-items-center">
      <div class="col-12 mb-0">
        <h2 id="h2top" class="mb-1">Compañias</h2>
        <!--<h5 class="text-700 fw-semi-bold text-none">Seleccionar compañia a ingresar</h5>-->
      </div>
      <div class="row col-12 mt-2 ms-0">
        <div class="col-auto ps-2 pe-0">
            <a class="btn btn-phoenix-secondary mb-1 btn-sm ms-0" href="#"><i class="fa-solid fa-plus me-2"></i>Add compañia</a>
        </div>
        <!--<div class="col-auto ps-2 pe-0">
            <a class="btn btn-phoenix-secondary mb-1 btn-sm" target="_blank" href="https://www.balkaned.com" ><span class="fas fa-earth-americas me-2"></span>Ir a sitio web</a>
        </div>-->
        <!--<div class="col-auto ps-2 pe-0">
            <a class="btn btn-phoenix-secondary mb-1 text-900  btn-sm" href="logoff"><span class="fa-solid fa-key fs--1 me-2"></span>Log out</a>
        </div>
        <div class="col-auto ps-2">
            <div class="search-box me-3">
                <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                  <input class="form-control search-input search" type="search" placeholder="Search compañias" aria-label="Search" />
                  <span class="fas fa-search search-box-icon"></span>
                </form>
            </div>
        </div>-->
      </div>
    </div>

    <!--<div class="card" style="max-width:20rem;">
      <img class="card-img-top" src="resources/assets/img//generic/66.jpg" alt="..." />
      <div class="card-body">
        <h5 class="card-title">Title goes here</h5>
        <p class="card-text">Here is the example of the Multiple Container Sortable feature of the </p>
        <button class="btn btn-primary">Go somewhere</button>
      </div>
    </div>-->

    <div class="row mt-0">
        <c:forEach var="compList" items="${compList}">
          <div class="card p-0 me-3 mt-3 border border-300" style="max-width:18rem;">
            <!--<img class="card-img-top" src="resources/assets/img//generic/66.jpg" alt="..." />-->
            <img class="card-img-top" height="180" src="AWSorFTP_flgsource@verLogo@${compList.id_companias}@null@${compList.urlLogo}@null@null@null@null@null@null" />
            <div class="card-body bg-soft rounded-0 border-top" style="border-radius: 0px 0px 20px 20px !important">
              <h5 class="card-title">${compList.nombre}</h5>
              <p class="fs--1 card-text mb-0">Tipo de conexión: ${compList.iexflgsource}
                <c:if test="${compList.iexflgsource=='1'}"><i style="font-size: 20px;" class="fa-brands fa-aws ps-2 pe-2"></i> Buckets</c:if>
                <c:if test="${compList.iexflgsource=='2'}"><span class="fw-bold">FTP </span>Filezilla</c:if>
              </p>
              <p class="fs--1 card-text mb-0">Ruc: ${compList.ruc}</p>
              <p class="fs--1 card-text">Dirección: ${compList.direccion}</p>
              <a class="btn btn-primary" href="home@${compList.id_companias}@${compList.id_usuario}">Ingresar</a>
            </div>
          </div>

          <!--<div class="col" style="width:380px;">
            <div class="card bg-primary h-100 rounded-2 hover-actions-trigger border border-0">
              <div class="card-body ">
                <div class="d-flex align-items-start">
                  <h4 class="mb-2 text-white">${compList.nombre}</h4>
                  <div class="hover-actions top-0 end-0 mt-4 me-4">
                    <a class="btn btn-phoenix-primary btn-icon flex-shrink-0" href="home@${compList.id_companias}@${compList.id_usuario}"><span class="fa-solid fa-chevron-right"></span></a>
                  </div>
                </div>
                <span class="badge badge-phoenix fs--2 mb-4 badge-phoenix-success"><span class="badge-label">Activo</span></span>

                <img id="imgcompany"
                src="AWSorFTP_flgsource@verLogo@${compList.id_companias}@null@${compList.urlLogo}@null@null@null@null@null@null"
                class="avatar" alt="Avatar">

                <div id="texto">
                    <div class="d-flex align-items-center mb-2 text-white">
                        <span class="fa-solid fa-bolt me-2 text-white fs--1 fw-extra-bold"></span>
                        <p class="mb-0 fs--1">Tipo de conexión:
                        <span class="fw-semi-bold text-white ms-1">${compList.iexflgsource} </span>
                        <c:if test="${compList.iexflgsource=='1'}"><i style="font-size: 20px;" class="fa-brands fa-aws ps-2 pe-2"></i></c:if>
                        <c:if test="${compList.iexflgsource=='2'}">  FTP</c:if>
                        </p>
                    </div>
                    <div class="d-flex align-items-center mb-2 text-white">
                        <span class="fa-solid far fa-building me-2 text-white fs--1 fw-extra-bold"></span>
                        <p class="mb-0 fs--1">Ruc: <span class="fw-semi-bold ms-1">${compList.ruc}</span></p>
                    </div>
                    <div class="d-flex align-items-center mb-4 text-white">
                        <span class="fa-solid fas fa-bus me-2 text-white fs--1 fw-extra-bold"></span>
                        <p class=" mb-0 fs--1">Dirección: <span class="ms-1 text-white">${compList.direccion}</span></p>
                    </div>

                    <div class="d-flex justify-content-between text-700 fw-semi-bold">
                      <p class="mb-2"> Schema</p>
                      <p class="mb-2 text-1100">100%</p>
                    </div>
                    <div class="progress bg-success-100">
                      <div class="progress-bar rounded bg-${comp.schema}" role="progressbar" aria-label="Success example" style="width: 100%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                    <div class="d-flex align-items-center mt-4">
                      <p class="mb-0 fw-bold fs--1">Started:<span class="fw-semi-bold text-600 ms-1">	17th Nov. 2020</span></p>
                    </div>
                    <div class="d-flex align-items-center mt-2">
                      <p class="mb-0 fw-bold fs--1">Deadline: <span class="fw-semi-bold text-600 ms-1">	21st May 2028</span></p>
                    </div>

                    <div class="mt-lg-3 mt-xl-0"> <i class="fa-solid fa-list-check me-1"></i>
                        <p class="d-inline-block fw-bold mb-0">287<span class="fw-normal">	Task</span></p>
                    </div>
                </div>
              </div>
            </div>
          </div>-->
        </c:forEach>
      </div>
    </div>
</div>





