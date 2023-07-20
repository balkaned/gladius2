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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <jsp:include page="../links.jsp"></jsp:include>

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
    <!-- ===============================================-->
    <!--    Main Content-->
    <!-- ===============================================-->
    <main class="main" id="top">

    <jsp:include page="../navsMenu.jsp"></jsp:include>
    <jsp:include page="../navTop.jsp"></jsp:include>

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
                          <h3 class="mb-0">Lead Details</h3>
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
                          <li class="nav-item"><a class="nav-link" href="#scrollspySeguridad">Seguridad Social</a></li>
                          <li class="nav-item"><a class="nav-link" href="#scrollspyDomicilio">Datos Domicilio</a></li>
                        </ul>
                      </nav>

                      <div class="scrollspy-example bg-body-tertiary rounded-2" data-bs-spy="scroll" data-bs-offset="0" data-bs-target="#navbar-deals-detail" data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true" tabindex="0">
                        <div class="mb-8">
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

                        <div class="mb-8">
                          <div class="d-flex justify-content-between align-items-center mb-4" id="scrollspyDatosLab">
                            <h2 class="mb-0">Datos laborales</h2>
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
                                         <label for="floatingInputStreet">Email Coorporativo</label>
                                       </div>
                                    </div>
                                </form>
                          </div>
                        </div>

                        <div class="mb-8">
                          <h2 class="mb-2" id="scrollspyEmails">Emails</h2>
                          <div>
                            <div class="scrollbar">
                              <ul class="nav nav-underline flex-nowrap mb-1" id="emailTab" role="tablist">
                                <li class="nav-item me-3"><a class="nav-link text-nowrap border-0 active" id="mail-tab" data-bs-toggle="tab" href="#tab-mail" aria-controls="mail-tab" role="tab" aria-selected="true">Mails (68)<span class="text-700 fw-normal"></span></a></li>
                                <li class="nav-item me-3"><a class="nav-link text-nowrap border-0" id="drafts-tab" data-bs-toggle="tab" href="#tab-drafts" aria-controls="drafts-tab" role="tab" aria-selected="true">Drafts (6)<span class="text-700 fw-normal"></span></a></li>
                                <li class="nav-item me-3"><a class="nav-link text-nowrap border-0" id="schedule-tab" data-bs-toggle="tab" href="#tab-schedule" aria-controls="schedule-tab" role="tab" aria-selected="true">Scheduled (17)</a></li>
                              </ul>
                            </div>
                            <div class="search-box w-100 mb-3">
                              <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                                <input class="form-control search-input search" type="search" placeholder="Search..." aria-label="Search" />
                                <span class="fas fa-search search-box-icon"></span>

                              </form>
                            </div>
                            <div class="tab-content" id="profileTabContent">
                              <div class="tab-pane fade show active" id="tab-mail" role="tabpanel" aria-labelledby="mail-tab">
                                <div class="border-top border-bottom border-200" id="allEmailsTable" data-list='{"valueNames":["subject","sent","date","source","status"],"page":7,"pagination":true}'>
                                  <div class="table-responsive scrollbar mx-n1 px-1">
                                    <table class="table fs--1 mb-0">
                                      <thead>
                                        <tr>
                                          <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select='{"body":"all-email-table-body"}' />
                                            </div>
                                          </th>
                                          <th class="sort white-space-nowrap align-middle pe-3 ps-0 text-uppercase" scope="col" data-sort="subject" style="width:31%; min-width:200px">Subject</th>
                                          <th class="sort align-middle pe-3 text-uppercase" scope="col" data-sort="sent" style="width:15%; min-width:140px">Sent by</th>
                                          <th class="sort align-middle text-start text-uppercase" scope="col" data-sort="date" style="width:20%; min-width:200px">Date</th>
                                          <th class="sort align-middle pe-0 text-uppercase" scope="col" style="width:15%; min-width:160px">Action</th>
                                          <th class="sort align-middle text-end text-uppercase" scope="col" data-sort="status" style="width:15%; min-width:160px">Status</th>
                                        </tr>
                                      </thead>
                                      <tbody class="list" id="all-email-table-body">
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"Quary about purchased soccer socks","email":"jackson@mail.com"},"active":true,"sent":"Jackson Pollock","date":"Dec 29, 2021 10:23 am","source":"Call","type_status":{"label":"sent","type":"badge-phoenix-success"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">Quary about purchased soccer socks</a>
                                            <div class="fs--2 d-block">jackson@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Jackson Pollock</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 29, 2021 10:23 am</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-success">sent</span></td>
                                        </tr>
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"How to take the headache out of Order","email":"ansolo45@mail.com"},"active":true,"sent":"Ansolo Lazinatov","date":"Dec 27, 2021 3:27 pm","source":"Call","type_status":{"label":"delivered","type":"badge-phoenix-info"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">How to take the headache out of Order</a>
                                            <div class="fs--2 d-block">ansolo45@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Ansolo Lazinatov</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 27, 2021 3:27 pm</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-info">delivered</span></td>
                                        </tr>
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"The Arnold Schwarzenegger of Order","email":"ansolo45@mail.com"},"active":true,"sent":"Ansolo Lazinatov","date":"Dec 24, 2021 10:44 am","source":"Call","type_status":{"label":"Bounce","type":"badge-phoenix-warning"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">The Arnold Schwarzenegger of Order</a>
                                            <div class="fs--2 d-block">ansolo45@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Ansolo Lazinatov</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 24, 2021 10:44 am</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-warning">Bounce</span></td>
                                        </tr>
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"My order is not being taken","email":"jackson@mail.com"},"active":true,"sent":"Jackson Pollock","date":"Dec 19, 2021 4:55 pm","source":"Call","type_status":{"label":"Spam","type":"badge-phoenix-danger"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">My order is not being taken</a>
                                            <div class="fs--2 d-block">jackson@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Jackson Pollock</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 19, 2021 4:55 pm</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-danger">Spam</span></td>
                                        </tr>
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"Shipment is missing","email":"jackson@mail.com"},"active":true,"sent":"Jackson Pollock","date":"Dec 19, 2021 2:43 pm","source":"Call","type_status":{"label":"sent","type":"badge-phoenix-success"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">Shipment is missing</a>
                                            <div class="fs--2 d-block">jackson@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Jackson Pollock</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 19, 2021 2:43 pm</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-success">sent</span></td>
                                        </tr>
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"How can I order something urgently?","email":"ansolo45@mail.com"},"active":true,"sent":"Jackson Pollock","date":"Dec 19, 2021 2:43 pm","source":"Call","type_status":{"label":"Delivered","type":"badge-phoenix-info"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">How can I order something urgently?</a>
                                            <div class="fs--2 d-block">ansolo45@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Jackson Pollock</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 19, 2021 2:43 pm</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-info">Delivered</span></td>
                                        </tr>
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"How the delicacy of the products will be handled?","email":"ansolo45@mail.com"},"active":true,"sent":"Ansolo Lazinatov","date":"Dec 16, 2021 5:18 pm","source":"Call","type_status":{"label":"bounced","type":"badge-phoenix-warning"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">How the delicacy of the products will be handled?</a>
                                            <div class="fs--2 d-block">ansolo45@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Ansolo Lazinatov</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 16, 2021 5:18 pm</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-warning">bounced</span></td>
                                        </tr>
                                      </tbody>
                                    </table>
                                  </div>
                                  <div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
                                    <div class="col-auto d-flex">
                                      <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!" data-list-view="*">View all<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none" href="#!" data-list-view="less">View Less<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
                                    </div>
                                    <div class="col-auto d-flex">
                                      <button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                                      <ul class="mb-0 pagination"></ul>
                                      <button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span></button>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <div class="tab-pane fade" id="tab-drafts" role="tabpanel" aria-labelledby="drafts-tab">
                                <div class="border-top border-bottom border-200" id="draftsEmailsTable" data-list='{"valueNames":["subject","sent","date","source","status"],"page":7,"pagination":true}'>
                                  <div class="table-responsive scrollbar mx-n1 px-1">
                                    <table class="table fs--1 mb-0">
                                      <thead>
                                        <tr>
                                          <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select='{"body":"drafts-email-table-body"}' />
                                            </div>
                                          </th>
                                          <th class="sort white-space-nowrap align-middle pe-3 ps-0 text-uppercase" scope="col" data-sort="subject" style="width:31%; min-width:200px">Subject</th>
                                          <th class="sort align-middle pe-3 text-uppercase" scope="col" data-sort="sent" style="width:15%; min-width:140px">Sent by</th>
                                          <th class="sort align-middle text-start text-uppercase" scope="col" data-sort="date" style="width:20%; min-width:200px">Date</th>
                                          <th class="sort align-middle pe-0 text-uppercase" scope="col" style="width:15%; min-width:160px">Action</th>
                                          <th class="sort align-middle text-end text-uppercase" scope="col" data-sort="status" style="width:15%; min-width:160px">Status</th>
                                        </tr>
                                      </thead>
                                      <tbody class="list" id="drafts-email-table-body">
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"Quary about purchased soccer socks","email":"jackson@mail.com"},"active":true,"sent":"Jackson Pollock","date":"Dec 29, 2021 10:23 am","source":"Call","type_status":{"label":"sent","type":"badge-phoenix-success"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">Quary about purchased soccer socks</a>
                                            <div class="fs--2 d-block">jackson@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Jackson Pollock</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 29, 2021 10:23 am</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-success">sent</span></td>
                                        </tr>
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"How to take the headache out of Order","email":"ansolo45@mail.com"},"active":true,"sent":"Ansolo Lazinatov","date":"Dec 27, 2021 3:27 pm","source":"Call","type_status":{"label":"delivered","type":"badge-phoenix-info"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">How to take the headache out of Order</a>
                                            <div class="fs--2 d-block">ansolo45@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Ansolo Lazinatov</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 27, 2021 3:27 pm</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-info">delivered</span></td>
                                        </tr>
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"The Arnold Schwarzenegger of Order","email":"ansolo45@mail.com"},"active":true,"sent":"Ansolo Lazinatov","date":"Dec 24, 2021 10:44 am","source":"Call","type_status":{"label":"Bounce","type":"badge-phoenix-warning"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">The Arnold Schwarzenegger of Order</a>
                                            <div class="fs--2 d-block">ansolo45@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Ansolo Lazinatov</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 24, 2021 10:44 am</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-warning">Bounce</span></td>
                                        </tr>
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"My order is not being taken","email":"jackson@mail.com"},"active":true,"sent":"Jackson Pollock","date":"Dec 19, 2021 4:55 pm","source":"Call","type_status":{"label":"Spam","type":"badge-phoenix-danger"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">My order is not being taken</a>
                                            <div class="fs--2 d-block">jackson@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Jackson Pollock</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 19, 2021 4:55 pm</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-danger">Spam</span></td>
                                        </tr>
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"Shipment is missing","email":"jackson@mail.com"},"active":true,"sent":"Jackson Pollock","date":"Dec 19, 2021 2:43 pm","source":"Call","type_status":{"label":"sent","type":"badge-phoenix-success"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">Shipment is missing</a>
                                            <div class="fs--2 d-block">jackson@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Jackson Pollock</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 19, 2021 2:43 pm</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-success">sent</span></td>
                                        </tr>
                                      </tbody>
                                    </table>
                                  </div>
                                  <div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
                                    <div class="col-auto d-flex">
                                      <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!" data-list-view="*">View all<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none" href="#!" data-list-view="less">View Less<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
                                    </div>
                                    <div class="col-auto d-flex">
                                      <button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                                      <ul class="mb-0 pagination"></ul>
                                      <button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span></button>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <div class="tab-pane fade" id="tab-schedule" role="tabpanel" aria-labelledby="schedule-tab">
                                <div class="border-top border-bottom border-200" id="scheduledEmailsTable" data-list='{"valueNames":["subject","sent","date","source","status"],"page":7,"pagination":true}'>
                                  <div class="table-responsive scrollbar mx-n1 px-1">
                                    <table class="table fs--1 mb-0">
                                      <thead>
                                        <tr>
                                          <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select='{"body":"scheduled-email-table-body"}' />
                                            </div>
                                          </th>
                                          <th class="sort white-space-nowrap align-middle pe-3 ps-0 text-uppercase" scope="col" data-sort="subject" style="width:31%; min-width:200px">Subject</th>
                                          <th class="sort align-middle pe-3 text-uppercase" scope="col" data-sort="sent" style="width:15%; min-width:140px">Sent by</th>
                                          <th class="sort align-middle text-start text-uppercase" scope="col" data-sort="date" style="width:20%; min-width:200px">Date</th>
                                          <th class="sort align-middle pe-0 text-uppercase" scope="col" style="width:15%; min-width:160px">Action</th>
                                          <th class="sort align-middle text-end text-uppercase" scope="col" data-sort="status" style="width:15%; min-width:160px">Status</th>
                                        </tr>
                                      </thead>
                                      <tbody class="list" id="scheduled-email-table-body">
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"Quary about purchased soccer socks","email":"jackson@mail.com"},"active":true,"sent":"Jackson Pollock","date":"Dec 29, 2021 10:23 am","source":"Call","type_status":{"label":"sent","type":"badge-phoenix-success"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">Quary about purchased soccer socks</a>
                                            <div class="fs--2 d-block">jackson@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Jackson Pollock</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 29, 2021 10:23 am</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-success">sent</span></td>
                                        </tr>
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"How to take the headache out of Order","email":"ansolo45@mail.com"},"active":true,"sent":"Ansolo Lazinatov","date":"Dec 27, 2021 3:27 pm","source":"Call","type_status":{"label":"delivered","type":"badge-phoenix-info"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">How to take the headache out of Order</a>
                                            <div class="fs--2 d-block">ansolo45@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Ansolo Lazinatov</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 27, 2021 3:27 pm</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-info">delivered</span></td>
                                        </tr>
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"The Arnold Schwarzenegger of Order","email":"ansolo45@mail.com"},"active":true,"sent":"Ansolo Lazinatov","date":"Dec 24, 2021 10:44 am","source":"Call","type_status":{"label":"Bounce","type":"badge-phoenix-warning"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">The Arnold Schwarzenegger of Order</a>
                                            <div class="fs--2 d-block">ansolo45@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Ansolo Lazinatov</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 24, 2021 10:44 am</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-warning">Bounce</span></td>
                                        </tr>
                                        <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                          <td class="fs--1 align-middle px-0 py-3">
                                            <div class="form-check mb-0 fs-0">
                                              <input class="form-check-input" type="checkbox" data-bulk-select-row='{"mail":{"subject":"My order is not being taken","email":"jackson@mail.com"},"active":true,"sent":"Jackson Pollock","date":"Dec 19, 2021 4:55 pm","source":"Call","type_status":{"label":"Spam","type":"badge-phoenix-danger"}}' />
                                            </div>
                                          </td>
                                          <td class="subject order align-middle white-space-nowrap py-2 ps-0 text-"><a class="fw-semi-bold text-primary" href="#!">My order is not being taken</a>
                                            <div class="fs--2 d-block">jackson@mail.com</div>
                                          </td>
                                          <td class="sent align-middle white-space-nowrap text-start fw-bold text-700 py-2">Jackson Pollock</td>
                                          <td class="date align-middle white-space-nowrap text-900 py-2">Dec 19, 2021 4:55 pm</td>
                                          <td class="align-middle white-space-nowrap ps-3"><span class="fa-solid fa-phone text-primary me-2"></span>Call
                                          </td>
                                          <td class="status align-middle fw-semi-bold text-end py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-danger">Spam</span></td>
                                        </tr>
                                      </tbody>
                                    </table>
                                  </div>
                                  <div class="row align-items-center justify-content-between py-2 pe-0 fs--1">
                                    <div class="col-auto d-flex">
                                      <p class="mb-0 d-none d-sm-block me-3 fw-semi-bold text-900" data-list-info="data-list-info"></p><a class="fw-semi-bold" href="#!" data-list-view="*">View all<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a><a class="fw-semi-bold d-none" href="#!" data-list-view="less">View Less<span class="fas fa-angle-right ms-1" data-fa-transform="down-1"></span></a>
                                    </div>
                                    <div class="col-auto d-flex">
                                      <button class="page-link" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                                      <ul class="mb-0 pagination"></ul>
                                      <button class="page-link pe-0" data-list-pagination="next"><span class="fas fa-chevron-right"></span></button>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div>
                          <h2 class="mb-4" id="scrollspyAttachments">Attachments</h2>
                          <div class="border-top border-dashed border-300 pt-3 pb-4">
                            <div class="d-flex flex-between-center">
                              <div class="d-flex mb-1"><span class="fa-solid fa-image me-2 text-700 fs--1"></span>
                                <p class="text-1000 mb-0 lh-1">Silly_sight_1.png</p>
                              </div>
                              <div class="font-sans-serif btn-reveal-trigger">
                                <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h"></span></button>
                                <div class="dropdown-menu dropdown-menu-end py-2"><a class="dropdown-item" href="#!">Edit</a><a class="dropdown-item text-danger" href="#!">Delete</a><a class="dropdown-item" href="#!">Download</a><a class="dropdown-item" href="#!">Report abuse</a></div>
                              </div>
                            </div>
                            <p class="fs--1 text-700 mb-2"><span>768kB</span><span class="text-400 mx-1">| </span><a href="#!">Shantinan Mekalan </a><span class="text-400 mx-1">| </span><span class="text-nowrap">21st Dec, 12:56 PM</span></p><img class="rounded-2" src="../../assets/img/generic/40.png" alt="" />
                          </div>
                          <div class="border-top border-dashed border-300 py-4">
                            <div class="d-flex flex-between-center">
                              <div>
                                <div class="d-flex align-items-center mb-1"><span class="fa-solid fa-image me-2 fs--1 text-700"></span>
                                  <p class="text-1000 mb-0 lh-1">All_images.zip</p>
                                </div>
                                <p class="fs--1 text-700 mb-0"><span>12.8 mB</span><span class="text-400 mx-1">|</span><a href="#!">Yves Tanguy </a><span class="text-400 mx-1">| </span><span class="text-nowrap">19th Dec, 08:56 PM</span></p>
                              </div>
                              <div class="font-sans-serif btn-reveal-trigger">
                                <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h"></span></button>
                                <div class="dropdown-menu dropdown-menu-end py-2"><a class="dropdown-item" href="#!">Edit</a><a class="dropdown-item text-danger" href="#!">Delete</a><a class="dropdown-item" href="#!">Download</a><a class="dropdown-item" href="#!">Report abuse</a></div>
                              </div>
                            </div>
                          </div>
                          <div class="border-top border-dashed border-300 py-4">
                            <div class="d-flex flex-between-center">
                              <div>
                                <div class="d-flex align-items-center mb-1"><span class="fa-solid fa-file-lines me-2 fs--1 text-700"></span>
                                  <p class="text-1000 mb-0 lh-1">Project.txt</p>
                                </div>
                                <p class="fs--1 text-700 mb-0"><span>123 kB</span><span class="text-400 mx-1">| </span><a href="#!">Shantinan Mekalan </a><span class="text-400 mx-1">| </span><span class="text-nowrap">12th Dec, 12:56 PM</span></p>
                              </div>
                              <div class="font-sans-serif btn-reveal-trigger">
                                <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h"></span></button>
                                <div class="dropdown-menu dropdown-menu-end py-2"><a class="dropdown-item" href="#!">Edit</a><a class="dropdown-item text-danger" href="#!">Delete</a><a class="dropdown-item" href="#!">Download</a><a class="dropdown-item" href="#!">Report abuse </a></div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <footer class="footer position-absolute">
                <div class="row g-0 justify-content-between align-items-center h-100">
                  <div class="col-12 col-sm-auto text-center">
                    <p class="mb-0 mt-2 mt-sm-0 text-900">Thank you for creating with Phoenix<span class="d-none d-sm-inline-block"></span><span class="d-none d-sm-inline-block mx-1">|</span><br class="d-sm-none" />2023 &copy;<a class="mx-1" href="https://themewagon.com">Themewagon</a></p>
                  </div>
                  <div class="col-12 col-sm-auto text-center">
                    <p class="mb-0 text-600">v1.12.0</p>
                  </div>
                </div>
              </footer>
            </div>
      </div>
    <jsp:include page="../plugins.jsp"></jsp:include>
  </body>

</html>