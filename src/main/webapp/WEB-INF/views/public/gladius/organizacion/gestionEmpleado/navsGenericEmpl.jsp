<%--
    Created on : 15/06/2023, 12:20:00 PM
    Author     : Jean Quiroz
    Email	   : jeanp.quiroz@gmail.com
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-md-5 col-lg-5 col-xl-4">
    <div class="sticky-leads-sidebar">
      <div class="lead-details-offcanvas bg-soft scrollbar phoenix-offcanvas phoenix-offcanvas-fixed" id="productFilterColumn">
        <div class="d-flex justify-content-between align-items-center mb-2 d-md-none">
          <h4 class="mb-0">Ficha trabajador</h4>
          <button class="btn p-0" data-phoenix-dismiss="offcanvas"><span class="uil uil-times fs-1"></span></button>
        </div>

        <div class="card mb-2" style="height:275px;">
            <div class="card-header hover-actions-trigger position-relative mb-7" style="min-height: 70px; ">
                <!--<div class="bg-holder rounded-top" style="background-image: linear-gradient(0deg, #000000 -3%, rgba(0, 0, 0, 0) 83%), url(resources/assets/img/generic/59.png)">-->
                <!--<div class="bg-holder rounded-top" style="background-color:#e6ebf7; !important">-->
                <div class="bg-holder rounded-top bg-100 #f6f7f8">
                <input class="d-none" id="upload-feed-cover-image" type="file" />
                <label class="cover-image-file-input" for="upload-feed-cover-image"></label>
                <div class="hover-actions end-0 bottom-0 pe-1 pb-2 text-white">
                    <span class="fa-solid fa-camera me-2"></span>
                    <a href="#!" class="fs--1" data-bs-toggle="modal" data-bs-target="#reportsFilterModal2" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">Subir</a>
                </div>
              </div>
              <input class="d-none" id="upload-feed-porfile-picture" type="file" />
              <label class="avatar avatar-4xl status-online feed-avatar-profile cursor-pointer" for="upload-feed-porfile-picture">
                <c:if test="${sexo.equals('MA') && iexlogo==null}"><img class="rounded-circle img-thumbnail bg-white shadow-sm" src="resources/assets/img/user_blank.jpg" width="200" alt="" /></c:if>
                <c:if test="${iexlogo!=null}"><img class="rounded-circle img-thumbnail bg-white shadow-sm" src="verFoto@FOTOEMP@${idComp}@${iexlogo}@null" width="200" alt="" /></c:if>

                <c:if test="${iexlogo==null && sexo.equals('M')}"><img class="rounded-circle img-thumbnail bg-white shadow-sm" src="resources/assets/img/man_user.jpg" width="200" alt="" /></c:if>
                <c:if test="${iexlogo==null && sexo.equals('F')}"><img class="rounded-circle img-thumbnail bg-white shadow-sm" src="resources/assets/img/woman_user.jpg" width="200" alt="" /></c:if>
              </label>
            </div>
            <div class="card-body">
              <div class="row">
                <div class="col-12">
                  <div class="d-flex flex-wrap mb-3 align-items-center">
                    <h3 id="nombreFichaTrab" class="me-2" >${nombrecompl}</h3>
                    <span class="fw-normal fs-0 col-12">${puesto}</span>
                    <a href="#!" class="fs--1" data-bs-toggle="modal" data-bs-target="#reportsFilterModal" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">Informacion de contacto</a>
                    <div class="col-12 mt-2">
                        <c:if test="${estado.equals('1')}"><span class="badge badge-phoenix badge-phoenix-success">Activo</span></c:if>
                        <c:if test="${estado.equals('0')}"><span class="badge badge-phoenix badge-phoenix-danger">Inactivo</span></c:if>
                    </div>
                  </div>
                  <!--<div class="mb-3">
                    <div class="d-flex align-items-center flex-wrap">
                      <div class="d-flex me-4 mb-2"><span class="fa-solid fa-user-group fs--2 me-2 me-lg-1 me-xl-2"></span>
                        <h6 class="d-inline-block mb-0">1297 <span class="fw-semi-bold">Followers</span></h6>
                      </div>
                      <div class="d-flex mb-2"><span class="fa-solid fa-user-check fs--2 me-2 me-lg-1 me-xl-2"></span>
                        <h6 class="d-block d-xl-inline-block mb-0">
                          3971 <span class="fw-semi-bold">Following</span></h6>
                      </div>
                    </div>
                  </div>-->
                  <!--<p class="fw-semi-bold mb-0">Empresa<a href="#!"><span class="fa-solid fa-pencil fs--2 text-500 ms-3"></span></a></p>
                  <p class="text-700 mb-0">${nombreComp} </p>-->
                </div>
              </div>
            </div>
        </div>

        <jsp:include page="navsempleado.jsp"></jsp:include>

      </div>
      <div class="phoenix-offcanvas-backdrop d-lg-none top-0" data-phoenix-backdrop="data-phoenix-backdrop"></div>
    </div>
</div>

<div class="modal fade" id="reportsFilterModal" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content border">
      <form id="addEventForm" autocomplete="off">
        <div class="modal-header border-200 p-4">
          <h5 class="modal-title text-1000 fs-2 lh-sm">Acerca de</h5>
          <button class="btn p-1 text-danger" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs--1"> 				</span></button>
        </div>
        <div class="modal-body pt-4 pb-2 px-4">
            <div class="ps-3" style="font-size:13px;">
              <div class="mb-2">
                <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-envelope-alt">  </span>
                  <span class="text-1000 mb-0">Email</span>
                </div><a href="mailto:shatinon@jeemail.com:">${email}</a>
              </div>
              <div class="mb-2">
                <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-phone"> </span>
                  <span class="text-1000 mb-0">Telefono</span>
                </div><a href="tel:+1234567890">+${telefono}</a>
              </div>
              <div class="mb-2">
                <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-directions"></span>
                  <span class="text-1000 mb-0">Dirección</span>
                </div><a href="#!">${direccion}</a>
              </div>
              <div class="mb-2">
                <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-postcard"></span>
                  <span class="text-1000 mb-0">Nro Documento</span>
                </div>
                <p class="mb-0 text-800">${nrodoc}</p>
              </div>
              <div class="mb-2">
                <div class="d-flex align-items-center mb-1"><span class="me-2 fa-solid fas fa-graduation-cap"></span>
                  <span class="text-1000 mb-0">Puesto</span>
                </div>
                <p class="mb-0 text-800">${puesto}</p>
              </div>
              <div class="mb-2">
                <div class="d-flex align-items-center mb-1"><span class="me-2 fa-solid far fa-save"></span>
                  <span class="text-1000 mb-0">Ult. Actualización</span>
                </div>
                <p class="mb-0 text-800">${fechaMod}</p>
              </div>
              <div>
                <div class="d-flex align-items-center mb-1">
                  <span class="me-2 uil uil-check-circle"></span>
                  <span class="text-1000 mb-0">Estado</span>
                </div>
                <c:if test="${estado.equals('1')}"><span class="badge badge-phoenix badge-phoenix-success">Activo</span></c:if>
                <c:if test="${estado.equals('0')}"><span class="badge badge-phoenix badge-phoenix-danger">Inactivo</span></c:if>
              </div>
            </div>
        </div>
        <div class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
          <button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="submit"> <span class="fas fa-arrows-rotate me-2 fs--2"></span>Otros</button>
          <button class="btn btn-sm btn-primary px-9 fs--2 my-0" data-bs-dismiss="modal" type="submit">Cerrar</button>
        </div>
      </form>
    </div>
  </div>
</div>

<div class="modal fade" id="reportsFilterModal2" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content border">
        <div class="modal-header border-200 p-4">
          <h5 class="modal-title text-1000 fs-2 lh-sm">Subir Imagen</h5>
          <button class="btn p-1 text-danger" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs--1"> 				</span></button>
        </div>
        <div class="modal-body pt-4 pb-2 px-4">
            <div class="col-12 col-md-12">
              <form method="post" action="fileUploadServlet@${idTrab}@${idComp}" enctype="multipart/form-data" >
                  <input type="hidden" name="accion" value="FOTOEMP" >
                  <input type="hidden" name="idimg" value="${nrodoc}" >
                  <input type="hidden" name="codciax" value="${idComp}" >
                  <input type="hidden" name="idTrab" value="${idTrab}" >
                  <div class="mb-3">
                        <label class="form-label">Subir Imagen</label>
                        <input class="form-control" name="uploadFile" type="file" />
                  </div>

                  <div class="col-sm-6 col-md-12 mt-2 mb-4">
                    <div class="form-floating">
                        <button class="btn btn-primary justify-content-end me-2 col-6" type="submit" ><span class="fa-solid fas fa-camera me-2"></span><span>Subir Foto</span></button>
                    </div>
                  </div>
              </form>
            </div>

        </div>
        <div class="modal-footer d-flex justify-content-end align-items-center px-4 pb-4 border-0 pt-3">
          <!--<button class="btn btn-sm btn-phoenix-primary px-4 fs--2 my-0" type="submit"> <span class="fas fa-arrows-rotate me-2 fs--2"></span>Otros</button>-->
          <button class="btn btn-sm btn-primary px-9 fs--2 my-0" data-bs-dismiss="modal" type="submit">Cerrar</button>
        </div>
    </div>
  </div>
</div>