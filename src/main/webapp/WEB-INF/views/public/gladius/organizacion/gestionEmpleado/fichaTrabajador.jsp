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
              <div class="pb-9">
                <div class="row">
                  <div class="col-12">
                    <div class="row align-items-center justify-content-between g-3 mb-3">
                      <div class="col-12 col-md-auto">
                        <h2 id="h2top" class="mb-0"></h2>
                      </div>
                      <div class="col-12 col-md-auto">
                        <div class="d-flex">
                          <div class="flex-1 d-md-none">
                            <button class="btn px-3 btn-phoenix-secondary text-700 me-2" data-phoenix-toggle="offcanvas" data-phoenix-target="#productFilterColumn"><span class="fa-solid fa-bars"></span></button>
                          </div>
                          <button class="btn btn-primary me-2"><span class="fa-solid fas fa-camera-retro me-2"></span><span>Subir Foto</span></button>
                          <button class="btn btn-phoenix-secondary px-3 px-sm-5 me-2"><span class="fa-solid fa-thumbtack me-sm-2"></span><span class="d-none d-sm-inline">Shortlist</span></button>
                          <button class="btn px-3 btn-phoenix-secondary" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fa-solid fa-ellipsis"></span></button>
                          <ul class="dropdown-menu dropdown-menu-end p-0" style="z-index: 9999;">
                            <li><a class="dropdown-item" href="#!">View profile</a></li>
                            <li><a class="dropdown-item" href="#!">Report</a></li>
                            <li><a class="dropdown-item" href="#!">Manage notifications</a></li>
                            <li><a class="dropdown-item text-danger" href="#!">Delete Lead</a></li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row g-0 g-md-4 g-xl-6">
                  <div class="col-md-5 col-lg-5 col-xl-4">
                    <div class="sticky-leads-sidebar">
                      <div class="lead-details-offcanvas bg-soft scrollbar phoenix-offcanvas phoenix-offcanvas-fixed" id="productFilterColumn">
                        <div class="d-flex justify-content-between align-items-center mb-2 d-md-none">
                          <h3 class="mb-0">Ficha trabajador</h3>
                          <button class="btn p-0" data-phoenix-dismiss="offcanvas"><span class="uil uil-times fs-1"></span></button>
                        </div>

                        <div class="card mb-5">
                            <div class="card-header hover-actions-trigger position-relative mb-7" style="min-height: 130px; ">
                              <div class="bg-holder rounded-top" style="background-image: linear-gradient(0deg, #000000 -3%, rgba(0, 0, 0, 0) 83%), url(resources/assets/img/generic/59.png)">
                                <input class="d-none" id="upload-feed-cover-image" type="file" />
                                <label class="cover-image-file-input" for="upload-feed-cover-image"></label>
                                <div class="hover-actions end-0 bottom-0 pe-1 pb-2 text-white"><span class="fa-solid fa-camera me-2 overlay-icon"> </span></div>
                              </div>
                              <input class="d-none" id="upload-feed-porfile-picture" type="file" />
                              <label class="avatar avatar-4xl status-online feed-avatar-profile cursor-pointer" for="upload-feed-porfile-picture"><img class="rounded-circle img-thumbnail bg-white shadow-sm" src="resources/assets/img/team/59.webp" width="200" alt="" /></label>
                            </div>
                            <div class="card-body">
                              <div class="row">
                                <div class="col-12">
                                  <div class="d-flex flex-wrap mb-3 align-items-center">
                                    <h3 class="me-2">${nombrecompl}</h3><span class="fw-normal fs-0">${puesto}</span>
                                  </div>
                                  <div class="mb-3">
                                    <div class="d-flex align-items-center flex-wrap">
                                      <div class="d-flex me-4 mb-2"><span class="fa-solid fa-user-group fs--2 me-2 me-lg-1 me-xl-2"></span>
                                        <h6 class="d-inline-block mb-0">1297 <span class="fw-semi-bold">Followers</span></h6>
                                      </div>
                                      <div class="d-flex mb-2"><span class="fa-solid fa-user-check fs--2 me-2 me-lg-1 me-xl-2"></span>
                                        <h6 class="d-block d-xl-inline-block mb-0">
                                          3971 <span class="fw-semi-bold">Following</span></h6>
                                      </div>
                                    </div>
                                  </div>
                                  <p class="fw-semi-bold mb-0">Empresa<a href="#!"><span class="fa-solid fa-pencil fs--2 text-500 ms-3"></span></a></p>
                                  <p class="text-700 mb-0">${nombreComp} </p>
                                </div>
                              </div>
                            </div>
                        </div>
                        <div class="card mb-3">
                          <div class="card-body">
                            <div class="d-flex align-items-center mb-5">
                              <h3>Acerca de</h3>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-envelope-alt">  </span>
                                <h5 class="text-1000 mb-0">Email</h5>
                              </div><a href="mailto:shatinon@jeemail.com:">${email}</a>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-phone"> </span>
                                <h5 class="text-1000 mb-0">Telefono</h5>
                              </div><a href="tel:+1234567890">+${telefono}</a>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-directions"></span>
                                <h5 class="text-1000 mb-0">Dirección</h5>
                              </div><a href="#!">${direccion}</a>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-building"></span>
                                <h5 class="text-1000 mb-0">Nro Documento</h5>
                              </div>
                              <p class="mb-0 text-800">${nrodoc}</p>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-postcard"></span>
                                <h5 class="text-1000 mb-0">Puesto</h5>
                              </div>
                              <p class="mb-0 text-800">${puesto}</p>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"></span>
                                <h5 class="text-1000 mb-0">Ult. Actualización</h5>
                              </div>
                              <p class="mb-0 text-800">${fechaMod}</p>
                            </div>

                            <div>
                              <div class="d-flex align-items-center mb-1"><span class="me-2 uil uil-check-circle"></span>
                                <h5 class="text-1000 mb-0">Estado</h5>
                              </div><span class="badge badge-phoenix badge-phoenix-primary">Activo</span>
                            </div>
                          </div>
                        </div>


                            <div id="otropciones" class="email-content scrollbar-overlay">
                              <div class="d-flex justify-content-between align-items-center">
                                <p class="text-uppercase fs--2 text-600 mb-2 fw-bold">Otras opciones</p>
                                <button class="btn d-lg-none p-0 mb-2" data-phoenix-dismiss="offcanvas"><span class="uil uil-times fs-0"></span></button>
                              </div>
                              <ul class="nav flex-column border-top fs--1 vertical-nav mb-4">
                                <li class="nav-item"><a class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="../../apps/email/inbox.html">
                                    <div class="d-flex align-items-center"><span class="me-2 nav-icons uil uil-inbox"></span><span class="flex-1">Datos Personales</span><span class="nav-item-count"></span>
                                    </div>
                                  </a></li>
                                <li class="nav-item"><a class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none active" aria-current="page" href="#!">
                                    <div class="d-flex align-items-center"><span class="me-2 nav-icons uil uil-bill"></span><span class="flex-1">Sueldos Fijos</span><span class="nav-item-count"></span>
                                    </div>
                                  </a></li>
                                <li class="nav-item"><a class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="#!">
                                    <div class="d-flex align-items-center"><span class="me-2 nav-icons uil uil-dollar-alt"></span><span class="flex-1">Sueldos Variables</span>
                                    </div>
                                  </a></li>
                                <li class="nav-item"><a class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="#!">
                                    <div class="d-flex align-items-center"><span class="me-2 nav-icons uil uil-exclamation-circle"></span><span class="flex-1">Vacaciones</span>
                                    </div>
                                  </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="#!">
                                        <div class="d-flex align-items-center"><span class="me-2 nav-icons uil uil-assistive-listening-systems"></span><span class="flex-1">Ausentismo</span></div>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="#!">
                                        <div class="d-flex align-items-center"><span class="me-2 nav-icons uil uil-clipboard"></span><span class="flex-1">Contrato</span></div>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="#!">
                                        <div class="d-flex align-items-center"><span class="me-2 nav-icons uil uil-chat-bubble-user"></span><span class="flex-1">Derecho Habientes</span></div>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="#!">
                                        <div class="d-flex align-items-center"><span class="me-2 nav-icons uil uil-balance-scale"></span><span class="flex-1">Retencion Judicial</span></div>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="#!">
                                        <div class="d-flex align-items-center"><span class="me-2 nav-icons uil uil-file-contract-dollar"></span><span class="flex-1">Prestamos</span></div>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="#!">
                                        <div class="d-flex align-items-center"><span class="me-2 nav-icons uil uil-align-center-h"></span><span class="flex-1">Acumulado</span></div>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="#!">
                                        <div class="d-flex align-items-center"><span class="me-2 nav-icons uil uil-clock"></span><span class="flex-1">Gestion de Tiempo</span></div>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link py-2 ps-0 pe-3 border-end border-bottom text-start outline-none" aria-current="page" href="#!">
                                        <div class="d-flex align-items-center"><span class="me-2 nav-icons uil uil-analysis"></span><span class="flex-1">Legajo</span></div>
                                    </a>
                                </li>
                              </ul>
                            </div>
                      </div>
                      <div class="phoenix-offcanvas-backdrop d-lg-none top-0" data-phoenix-backdrop="data-phoenix-backdrop"></div>
                    </div>
                  </div>

                  <div class="col-md-7 col-lg-7 col-xl-8">
                    <div class="lead-details-container">
                      <nav class="navbar pb-4 px-0 sticky-top bg-soft nav-underline-scrollspy" id="navbar-deals-detail">
                        <ul class="nav nav-underline">
                          <li class="nav-item"><a class="nav-link pe-3" href="#scrollspyDatosPers">Datos Personales</a></li>
                          <li class="nav-item"><a class="nav-link pe-3" href="#scrollspyDatosLab">Datos Laborales</a></li>
                          <li class="nav-item"><a class="nav-link pe-3" href="#scrollspyInform">Información de Pago</a></li>
                          <li class="nav-item"><a class="nav-link pe-3" href="#scrollspySeguridad">Seguridad Social</a></li>
                          <li class="nav-item"><a class="nav-link pe-3" href="#scrollspyDomi">Datos Domicilio</a></li>
                        </ul>
                      </nav>

                      <div class="scrollspy-example bg-body-tertiary rounded-2" data-bs-spy="scroll" data-bs-offset="0" data-bs-target="#navbar-deals-detail" data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true" tabindex="0">
                        <div class="mb-18">
                              <div class="d-flex justify-content-between align-items-center mb-4" id="scrollspyDatosPers">
                                <h2 class="mb-0">Datos personales</h2>
                              </div>
                              <form class="row g-3 mb-9">
                                    <div class="col-sm-6 col-md-4">
                                      <div class="form-floating">
                                        <input class="form-control" id="floatingInputStreet" type="text" placeholder="street" />
                                        <label for="floatingInputStreet">Codigo Empleado</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-6 col-md-4">
                                      <div class="form-floating">
                                        <select class="form-select" id="floatingSelectCity">
                                          <option selected="selected">Seleccionar</option>
                                          <option value="1">Valor1</option>
                                        </select>
                                        <label for="floatingSelectCity">Tipo de Empleado</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-6 col-md-4">
                                      <div class="form-floating">
                                          <select class="form-select" id="floatingSelectCountry">
                                            <option selected="selected">Activo</option>
                                            <option value="1">Inactivo</option>
                                          </select>
                                          <label for="floatingSelectCountry">Estado</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-6 col-md-4">
                                      <div class="form-floating">
                                        <input class="form-control" id="floatingInputStreet" type="text" placeholder="street" />
                                        <label for="floatingInputStreet">Nro Documento</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-6 col-md-4">
                                      <div class="form-floating">
                                        <input class="form-control" id="floatingInputStreet" type="text" placeholder="street" />
                                        <label for="floatingInputStreet">Codigo Anterior</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-6">
                                      <div class="form-floating">
                                        <input class="form-control" id="floatingInputStreet" type="text" placeholder="street" />
                                        <label for="floatingInputStreet">Apellido Paterno</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-6">
                                      <div class="form-floating">
                                        <input class="form-control" id="floatingInputStreet" type="text" placeholder="street" />
                                        <label for="floatingInputStreet">Apellido Materno</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-6">
                                      <div class="form-floating">
                                        <input class="form-control" id="floatingInputStreet" type="text" placeholder="street" />
                                        <label for="floatingInputStreet">Nombres</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-6 col-md-4">
                                      <div class="flatpickr-input-container">
                                        <div class="form-floating">
                                          <input class="form-control datetimepicker" id="floatingInputStartDate" type="text" placeholder="end date" data-options='{"disableMobile":true}' />
                                          <label class="ps-6" for="floatingInputStartDate">Fecha de Nacimiento</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                        </div>
                                      </div>
                                    </div>
                                    <div class="col-sm-6 col-md-4">
                                      <div class="form-floating">
                                          <select class="form-select" id="floatingSelectCountry">
                                            <option selected="selected">Masculino</option>
                                            <option value="1">Femenino</option>
                                          </select>
                                          <label for="floatingSelectCountry">Sexo(*)[TT50]</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-6">
                                      <div class="form-floating">
                                          <select class="form-select" id="floatingSelectCountry">
                                            <option selected="selected">Seleccionar</option>
                                            <option value="1">Femenino</option>
                                          </select>
                                          <label for="floatingSelectCountry">Estado Civil (*) [TT68]</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-6">
                                      <div class="form-floating">
                                          <select class="form-select" id="floatingSelectCountry">
                                            <option selected="selected">Seleccionar</option>
                                            <option value="1">Femenino</option>
                                          </select>
                                          <label for="floatingSelectCountry">Modalidad Formativa [TT18]</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-6">
                                      <div class="form-floating">
                                          <select class="form-select" id="floatingSelectCountry">
                                            <option selected="selected">Seleccionar</option>
                                            <option value="1">Femenino</option>
                                          </select>
                                          <label for="floatingSelectCountry">Nacionalidad Origen (*) [TT4]</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-5">
                                      <div class="form-floating">
                                          <select class="form-select" id="floatingSelectCountry">
                                            <option selected="selected">Seleccionar</option>
                                            <option value="1">Femenino</option>
                                          </select>
                                          <label for="floatingSelectCountry">Pais Emisor (*) [TT26]</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-4">
                                      <div class="form-floating">
                                          <select class="form-select" id="floatingSelectCountry">
                                            <option selected="selected">Seleccionar</option>
                                            <option value="1">Femenino</option>
                                          </select>
                                          <label for="floatingSelectCountry">Departamento</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-4">
                                      <div class="form-floating">
                                          <select class="form-select" id="floatingSelectCountry">
                                            <option selected="selected">Seleccionar</option>
                                            <option value="1">Femenino</option>
                                          </select>
                                          <label for="floatingSelectCountry">Provincia</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-4">
                                      <div class="form-floating">
                                          <select class="form-select" id="floatingSelectCountry">
                                            <option selected="selected">Seleccionar</option>
                                            <option value="1">Femenino</option>
                                          </select>
                                          <label for="floatingSelectCountry">Distrito (*)</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-6">
                                      <div class="form-floating">
                                          <select class="form-select" id="floatingSelectCountry">
                                            <option selected="selected">Seleccionar</option>
                                            <option value="1">Femenino</option>
                                          </select>
                                          <label for="floatingSelectCountry">Grado Instruccion (*) [TT9]</label>
                                      </div>
                                    </div>
                                    <div class="col-sm-6">
                                      <div class="form-floating">
                                          <select class="form-select" id="floatingSelectCountry">
                                            <option selected="selected">Seleccionar</option>
                                            <option value="1">Femenino</option>
                                          </select>
                                          <label for="floatingSelectCountry">Centro de Formacion [TT51]</label>
                                      </div>
                                    </div>
                                    <div class="col-5">
                                         <div class="d-flex flex-wrap justify-content-between mb-3">
                                             <div class="d-flex flex-wrap justify-content-between mb-2">
                                                  <div class="form-check form-switch">
                                                      <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                      <label class="form-check-label fs-0" for="showPhone">Es Domiciliado?</label>
                                                  </div>
                                              </div>
                                         </div>
                                    </div>
                                    <div class="col-sm-7">
                                       <div class="form-floating">
                                           <select class="form-select" id="floatingSelectCountry">
                                             <option selected="selected">Seleccionar</option>
                                             <option value="1">Femenino</option>
                                           </select>
                                           <label for="floatingSelectCountry">Codigo de Larga Distancia [TT29]</label>
                                       </div>
                                    </div>
                                    <div class="col-sm-6">
                                       <div class="form-floating">
                                         <input class="form-control" id="floatingInputStreet" type="text" placeholder="street" />
                                         <label for="floatingInputStreet">Nro de Telefono</label>
                                       </div>
                                    </div>
                                    <div class="col-sm-6">
                                       <div class="form-floating">
                                         <input class="form-control" id="floatingInputStreet" type="text" placeholder="street" />
                                         <label for="floatingInputStreet">Email</label>
                                       </div>
                                    </div>
                                    <div class="col-sm-6">
                                       <div class="form-floating">
                                         <input class="form-control" id="floatingInputStreet" type="text" placeholder="street" />
                                         <label for="floatingInputStreet">Email Coorp</label>
                                       </div>
                                    </div>
                             </form>
                        </div>
                      </div>

                      <div class="scrollspy-example bg-body-tertiary rounded-2" data-bs-spy="scroll" data-bs-offset="0" data-bs-target="#navbar-deals-detail" data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true" tabindex="0">
                            <div class="mb-8">
                                  <div class="d-flex justify-content-between align-items-center mb-4" id="scrollspyDatosLab">
                                  <h2 class="mb-0">Datos laborales</h2>
                            </div>
                            <form class="row g-3 mb-9">
                                <div class="col-6">
                                  <div class="form-floating">
                                    <select class="form-select" id="floatingSelectCity">
                                      <option selected="selected">Seleccionar</option>
                                      <option value="1">Valor1</option>
                                    </select>
                                    <label for="floatingSelectCity">Regimen Laboral (*) [TT33]</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Tipo de Trabajador (*) [TT8]</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Categoria Trabajador [TT24] (*)</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Situacion del Pensionista [TT15] (*)</label>
                                  </div>
                                </div>
                                <div class="col-4">
                                  <div class="flatpickr-input-container">
                                    <div class="form-floating">
                                      <input class="form-control datetimepicker" id="floatingInputStartDate" type="text" placeholder="end date" data-options='{"disableMobile":true}' />
                                      <label class="ps-6" for="floatingInputStartDate">Fecha de Ingreso (*)</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                    </div>
                                  </div>
                                </div>
                                <div class="col-4">
                                  <div class="flatpickr-input-container">
                                    <div class="form-floating">
                                      <input class="form-control datetimepicker" id="floatingInputStartDate" type="text" placeholder="end date" data-options='{"disableMobile":true}' />
                                      <label class="ps-6" for="floatingInputStartDate">Fecha de Retiro</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                    </div>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Tipo de Contrato [TT12] (*)</label>
                                  </div>
                                </div>
                                <div class="col-4">
                                  <div class="flatpickr-input-container">
                                    <div class="form-floating">
                                      <input class="form-control datetimepicker" id="floatingInputStartDate" type="text" placeholder="end date" data-options='{"disableMobile":true}' />
                                      <label class="ps-6" for="floatingInputStartDate">Fec Ini Contrato</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                    </div>
                                  </div>
                                </div>
                                <div class="col-4">
                                  <div class="flatpickr-input-container">
                                    <div class="form-floating">
                                      <input class="form-control datetimepicker" id="floatingInputStartDate" type="text" placeholder="end date" data-options='{"disableMobile":true}' />
                                      <label class="ps-6" for="floatingInputStartDate">Fec Fin Contrato</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                    </div>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Pliego [TT31]</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Situacion Especial [TT35](*)</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Ocupacion Reg. Publico [TT10]</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Ocupacion Reg. Privado [TT30]</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Area (*)</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Puesto (*)</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Centro de Costos(*)</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Ubicación (*)</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Turno</label>
                                  </div>
                                </div>
                            </form>
                      </div>

                      <div class="scrollspy-example bg-body-tertiary rounded-2" data-bs-spy="scroll" data-bs-offset="0" data-bs-target="#navbar-deals-detail" data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true" tabindex="0">
                            <div class="mb-8">
                                  <div class="d-flex justify-content-between align-items-center mb-4" id="scrollspyInform">
                                  <h2 class="mb-0">Información de pago</h2>
                            </div>
                            <form class="row g-3 mb-9">
                                <div class="col-6">
                                  <div class="form-floating">
                                    <select class="form-select" id="floatingSelectCity">
                                      <option selected="selected">Seleccionar</option>
                                      <option value="1">Valor1</option>
                                    </select>
                                    <label for="floatingSelectCity">Tipo de pago (*) [TT19]</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Periodo Remuneracion (*) [TT13]</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Banco de Haberes (*) [TT36]</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Tipo de Cuenta de Haberes (*) [TT53]</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Moneda de Haberes [TT52] (*)</label>
                                  </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Es interbancario?</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                    <input class="form-control" id="floatingInputStreet" type="text" placeholder="street" />
                                    <label for="floatingInputStreet">Nro de Cuenta de Bancos (*)</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Banco de CTS (*) [TT36]</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Tipo de Banco Cts (*) [TT53]</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Inactivo</option>
                                      </select>
                                      <label for="floatingSelectCountry">Moneda de Cts (*) [TT52]</label>
                                  </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Es interbancario?</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-7">
                                  <div class="form-floating">
                                    <input class="form-control" id="floatingInputStreet" type="text" placeholder="street" />
                                    <label for="floatingInputStreet">Nro Cta Cts (*)</label>
                                  </div>
                                </div>
                            </form>
                      </div>

                      <div class="scrollspy-example bg-body-tertiary rounded-2" data-bs-spy="scroll" data-bs-offset="0" data-bs-target="#navbar-deals-detail" data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true" tabindex="0">
                            <div class="mb-8">
                                  <div class="d-flex justify-content-between align-items-center mb-4" id="scrollspySeguridad">
                                  <h2 class="mb-0">Seguridad social</h2>
                            </div>
                            <form class="row g-3 mb-9">
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Es Jubilado?</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-7">
                                  <div class="form-floating">
                                    <select class="form-select" id="floatingSelectCity">
                                      <option selected="selected">Seleccionar</option>
                                      <option value="1">Valor1</option>
                                    </select>
                                    <label for="floatingSelectCity">Fondo Pensiones (*) [TT11]</label>
                                  </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Comision Mixta?</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-5">
                                  <div class="flatpickr-input-container">
                                    <div class="form-floating">
                                      <input class="form-control datetimepicker" id="floatingInputStartDate" type="text" placeholder="end date" data-options='{"disableMobile":true}' />
                                      <label class="ps-6" for="floatingInputStartDate">Fecha Inicio Fondo Pensiones (*)</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                    </div>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                    <input class="form-control" id="floatingInputStreet" type="text" placeholder="street" />
                                    <label for="floatingInputStreet">Cussp (*)</label>
                                  </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Femenino</option>
                                      </select>
                                      <label for="floatingSelectCountry">Essalud (*) [TT32]</label>
                                  </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Senati</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Tiene Eps</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-6">
                                  <div class="form-floating">
                                      <select class="form-select" id="floatingSelectCountry">
                                        <option selected="selected">Seleccionar</option>
                                        <option value="1">Femenino</option>
                                      </select>
                                      <label for="floatingSelectCountry">Indica Proveedor de Eps (*) [TT14]</label>
                                  </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Mas vida</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Convenio para evitar Doble tributacion</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Discapacidad</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Reg. alternativo</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Sctr Pensionv</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Jornada Maxima</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Horario Nocturno</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Sindicalizado</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Exoneracion 5ta</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                                <div class="col-sm-6">
                                   <div class="form-floating">
                                     <input class="form-control" id="floatingInputStreet" type="text" placeholder="street" />
                                     <label for="floatingInputStreet">Nro de Ruc Cas</label>
                                   </div>
                                </div>
                                <div class="col-5">
                                     <div class="d-flex flex-wrap justify-content-between mb-3">
                                         <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <div class="form-check form-switch">
                                                  <input class="form-check-input" id="showPhone" type="checkbox" checked="checked" name="showPhone" />
                                                  <label class="form-check-label fs-0" for="showPhone">Madre de responsaibilidad Limitada</label>
                                              </div>
                                          </div>
                                     </div>
                                </div>
                            </form>
                      </div>

                      <div class="scrollspy-example bg-body-tertiary rounded-2" data-bs-spy="scroll" data-bs-offset="0" data-bs-target="#navbar-deals-detail" data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true" tabindex="0">
                          <div class="mb-8">
                                <div class="d-flex justify-content-between align-items-center mb-4" id="scrollspyDomi">
                                <h2 class="mb-0">Domicilio</h2>
                          </div>
                            <form class="row g-3 mb-9">
                                <div class="col-sm-6 col-md-4">
                                    <div class="flatpickr-input-container">
                                      <div class="form-floating">
                                        <input class="form-control datetimepicker" id="floatingInputerere" type="text" placeholder="end date" data-options='{"disableMobile":true}' />
                                        <label class="ps-6" >Start date</label><span class="uil uil-calendar-alt flatpickr-icon text-700"></span>
                                      </div>
                                    </div>
                                </div>
                            </form>
                      </div>
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