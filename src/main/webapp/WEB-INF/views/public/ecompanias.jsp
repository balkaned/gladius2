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
        <div class="row gx-6 gy-3 mb-4 align-items-center">
          <div class="col-12 mb-2">
            <h2 class="mb-0">Seleccionar compañia<span class="fw-normal text-700 ms-3"></span></h2>
          </div>
          <div class="col-auto pe-0">
            <a class="btn btn-primary px-5" href="#"><i class="fa-solid fa-plus me-2"></i>Add compañia</a>
          </div>
          <div class="col-auto ps-0 pe-0">
            <a class="btn btn-phoenix-primary ms-2" href="https://www.balkaned.com" ><span class="fas fa-earth-americas me-2"></span>Ir a sitio web</a>
          </div>
          <div class="col-auto ps-0">
            <a class="btn btn-phoenix-secondary text-900 ms-2" href="logoff"><span class="fa-solid fa-key fs--1 me-2"></span>Log out</a>
          </div>
        </div>
        <div class="row justify-content-between align-items-end mb-4 g-3">

          <div class="col-12 col-sm-auto">
            <div class="d-flex align-items-center">
              <div class="search-box me-3">
                <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                  <input class="form-control search-input search" type="search" placeholder="Search compañias" aria-label="Search" />
                  <span class="fas fa-search search-box-icon"></span>
                </form>
              </div>
              <!--<a class="btn btn-phoenix-primary px-3 me-1" href="../../apps/project-management/project-list-view.html" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="List view"><span class="fa-solid fa-list fs--2"></span></a><a class="btn btn-phoenix-primary px-3 me-1" href="../../apps/project-management/project-board-view.html" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Board view">
                <svg width="9" height="9" viewbox="0 0 9 9" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M0 0.5C0 0.223857 0.223858 0 0.5 0H1.83333C2.10948 0 2.33333 0.223858 2.33333 0.5V1.83333C2.33333 2.10948 2.10948 2.33333 1.83333 2.33333H0.5C0.223857 2.33333 0 2.10948 0 1.83333V0.5Z" fill="currentColor"></path>
                  <path d="M3.33333 0.5C3.33333 0.223857 3.55719 0 3.83333 0H5.16667C5.44281 0 5.66667 0.223858 5.66667 0.5V1.83333C5.66667 2.10948 5.44281 2.33333 5.16667 2.33333H3.83333C3.55719 2.33333 3.33333 2.10948 3.33333 1.83333V0.5Z" fill="currentColor"></path>
                  <path d="M6.66667 0.5C6.66667 0.223857 6.89052 0 7.16667 0H8.5C8.77614 0 9 0.223858 9 0.5V1.83333C9 2.10948 8.77614 2.33333 8.5 2.33333H7.16667C6.89052 2.33333 6.66667 2.10948 6.66667 1.83333V0.5Z" fill="currentColor"></path>
                  <path d="M0 3.83333C0 3.55719 0.223858 3.33333 0.5 3.33333H1.83333C2.10948 3.33333 2.33333 3.55719 2.33333 3.83333V5.16667C2.33333 5.44281 2.10948 5.66667 1.83333 5.66667H0.5C0.223857 5.66667 0 5.44281 0 5.16667V3.83333Z" fill="currentColor"></path>
                  <path d="M3.33333 3.83333C3.33333 3.55719 3.55719 3.33333 3.83333 3.33333H5.16667C5.44281 3.33333 5.66667 3.55719 5.66667 3.83333V5.16667C5.66667 5.44281 5.44281 5.66667 5.16667 5.66667H3.83333C3.55719 5.66667 3.33333 5.44281 3.33333 5.16667V3.83333Z" fill="currentColor"></path>
                  <path d="M6.66667 3.83333C6.66667 3.55719 6.89052 3.33333 7.16667 3.33333H8.5C8.77614 3.33333 9 3.55719 9 3.83333V5.16667C9 5.44281 8.77614 5.66667 8.5 5.66667H7.16667C6.89052 5.66667 6.66667 5.44281 6.66667 5.16667V3.83333Z" fill="currentColor"></path>
                  <path d="M0 7.16667C0 6.89052 0.223858 6.66667 0.5 6.66667H1.83333C2.10948 6.66667 2.33333 6.89052 2.33333 7.16667V8.5C2.33333 8.77614 2.10948 9 1.83333 9H0.5C0.223857 9 0 8.77614 0 8.5V7.16667Z" fill="currentColor"></path>
                  <path d="M3.33333 7.16667C3.33333 6.89052 3.55719 6.66667 3.83333 6.66667H5.16667C5.44281 6.66667 5.66667 6.89052 5.66667 7.16667V8.5C5.66667 8.77614 5.44281 9 5.16667 9H3.83333C3.55719 9 3.33333 8.77614 3.33333 8.5V7.16667Z" fill="currentColor"></path>
                  <path d="M6.66667 7.16667C6.66667 6.89052 6.89052 6.66667 7.16667 6.66667H8.5C8.77614 6.66667 9 6.89052 9 7.16667V8.5C9 8.77614 8.77614 9 8.5 9H7.16667C6.89052 9 6.66667 8.77614 6.66667 8.5V7.16667Z" fill="currentColor"></path>
                </svg></a><a class="btn btn-phoenix-primary px-3 border-0 text-900" href="../../apps/project-management/project-card-view.html" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Card view">
                <svg width="9" height="9" viewBox="0 0 9 9" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M0 0.5C0 0.223858 0.223858 0 0.5 0H3.5C3.77614 0 4 0.223858 4 0.5V3.5C4 3.77614 3.77614 4 3.5 4H0.5C0.223858 4 0 3.77614 0 3.5V0.5Z" fill="currentColor"></path>
                  <path d="M0 5.5C0 5.22386 0.223858 5 0.5 5H3.5C3.77614 5 4 5.22386 4 5.5V8.5C4 8.77614 3.77614 9 3.5 9H0.5C0.223858 9 0 8.77614 0 8.5V5.5Z" fill="currentColor"></path>
                  <path d="M5 0.5C5 0.223858 5.22386 0 5.5 0H8.5C8.77614 0 9 0.223858 9 0.5V3.5C9 3.77614 8.77614 4 8.5 4H5.5C5.22386 4 5 3.77614 5 3.5V0.5Z" fill="currentColor"></path>
                  <path d="M5 5.5C5 5.22386 5.22386 5 5.5 5H8.5C8.77614 5 9 5.22386 9 5.5V8.5C9 8.77614 8.77614 9 8.5 9H5.5C5.22386 9 5 8.77614 5 8.5V5.5Z" fill="currentColor"></path>
                </svg>
              </a>-->
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
                    </div><span class="badge badge-phoenix fs--1 mb-4 badge-phoenix-success"><span class="badge-label">Activo</span></span>
                        <img id="imgcompany"
                        src="AWSorFTP_flgsource@verLogo@${comp.id_companias}@null@${comp.urlLogo}@null@null@null@null@null@null"
                        class="avatar" alt="Avatar" width="100">
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
            </c:forEach>
          </div>
        </div>
      </div>





