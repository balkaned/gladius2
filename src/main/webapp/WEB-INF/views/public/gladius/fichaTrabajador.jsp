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
                        <h2 id="h2top" class="mb-0">Ficha del Trabajador</h2>
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
                        <div class="card mb-3">
                          <div class="card-body">
                            <div class="row align-items-center g-3 text-center text-xxl-start">
                              <div class="col-12 col-xxl-auto">
                                <div class="avatar avatar-5xl"><img class="rounded-circle" src="resources/assets/img/team/33.webp" alt="" /></div>
                              </div>
                              <div class="col-12 col-sm-auto flex-1">
                                <h3 class="fw-bolder mb-2">${nombrecompl}</h3>
                                <p class="mb-0">${puesto}</p><a class="fw-bold" href="#!">${nombreComp}</a>
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

                        <div class="card mb-3">
                          <div class="card-body">

                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"></span>
                                <a class="mb-0">Datos Personales</a>
                              </div>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"></span>
                                <a class="mb-0">Sueldos Fijos</a>
                              </div>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"></span>
                                <a class="mb-0">Sueldos Variables</a>
                              </div>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"></span>
                                <a class="mb-0">Vacaciones</a>
                              </div>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"></span>
                                <a class="mb-0">Ausentismo</a>
                              </div>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"></span>
                                <a class="mb-0">Contrato</a>
                              </div>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"></span>
                                <a class="mb-0">DerechoHabientes</a>
                              </div>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"></span>
                                <a class="mb-0">Retencion Judicial</a>
                              </div>
                            </div>
                            <div class="mb-4">
                              <div class="d-flex align-items-center mb-1"></span>
                                <a class="mb-0">Prestamos</a>
                              </div>
                            </div>
                            <div class="mb-4">
                                 <div class="d-flex align-items-center mb-1"></span>
                                   <a class="mb-0">Acumulado</a>
                                 </div>
                            </div>
                            <div class="mb-4">
                                 <div class="d-flex align-items-center mb-1"></span>
                                   <a class="mb-0">Gestion de Tiempo</a>
                                 </div>
                            </div>
                            <div class="mb-4">
                                 <div class="d-flex align-items-center mb-1"></span>
                                   <a class="mb-0">Legajo</a>
                                 </div>
                            </div>

                          </div>
                        </div>
                      </div>
                      <div class="phoenix-offcanvas-backdrop d-lg-none top-0" data-phoenix-backdrop="data-phoenix-backdrop"></div>
                    </div>
                  </div>
                  <div class="col-md-7 col-lg-7 col-xl-8">
                    <div class="lead-details-container">
                      <nav class="navbar pb-4 px-0 sticky-top bg-soft nav-underline-scrollspy" id="navbar-deals-detail">
                        <ul class="nav nav-underline">
                          <li class="nav-item"><a class="nav-link pe-3" href="#scrollspyTask">Datos Personales</a></li>
                          <li class="nav-item"><a class="nav-link pe-3" href="#scrollspyDeals">Datos Laborales</a></li>
                          <li class="nav-item"><a class="nav-link pe-3" href="#scrollspyEmails">Información de Pago</a></li>
                          <li class="nav-item"><a class="nav-link" href="#scrollspyAttachments">Seguridad Social</a></li>
                          <li class="nav-item"><a class="nav-link" href="#scrollspyAttachments">Datos Domicilio</a></li>
                        </ul>
                      </nav>

                      <div class="scrollspy-example bg-body-tertiary rounded-2" data-bs-spy="scroll" data-bs-offset="0" data-bs-target="#navbar-deals-detail" data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true" tabindex="0">
                        <div class="mb-8">
                              <div class="d-flex justify-content-between align-items-center mb-4" id="scrollspyTask">
                                <h2 class="mb-0">Datos Personales</h2>

                              </div>

                              <div class="card">
                                <div class="card-body">
                                  <div class="row g-3">
                                    <div class="col-12">

                                      <div class="col-3">
                                          <div class="d-flex flex-wrap justify-content-between mb-3">
                                              <div class="d-flex flex-wrap justify-content-between mb-2">
                                                <h5 class="mb-0 text-1000 me-2">Codigo Empleado</h5>
                                              </div>
                                              <input class="form-control form-icon-input mb-3" id="firstName" type="text" placeholder="First Name" />
                                          </div>
                                      </div>
                                      <div class="col-8">
                                            <h5 class="mb-0 text-1000 mb-2">Tipo Documento</h5>
                                            <select class="form-select mb-3" aria-label="priority">
                                              <option value="low">Low</option>
                                              <option value="high">High</option>
                                              <option value="medium">Medium</option>
                                              <option value="urgent">Urgent</option>
                                            </select>
                                      </div>
                                      <div class="col-8">
                                            <h5 class="mb-0 text-1000 mb-2">Estado</h5>
                                            <select class="form-select mb-3" aria-label="stage">
                                              <option value="new">New</option>
                                              <option value="in-progress">In Progress</option>
                                              <option value="pending">Pending</option>
                                              <option value="canceled">Canceled</option>
                                              <option value="completed">Completed</option>
                                            </select>
                                      </div>
                                      <div class="col-5">
                                            <div class="d-flex flex-wrap justify-content-between mb-3">
                                                <div class="d-flex flex-wrap justify-content-between mb-2">
                                                  <h5 class="mb-0 text-1000 me-2">Nro Documento</h5>
                                                </div>
                                                <input class="form-control form-icon-input mb-3" id="firstName" type="text" placeholder="First Name" />
                                            </div>
                                      </div>
                                      <div class="col-5">
                                           <div class="d-flex flex-wrap justify-content-between mb-3">
                                               <div class="d-flex flex-wrap justify-content-between mb-2">
                                                 <h5 class="mb-0 text-1000 me-2">Codigo Anterior</h5>
                                               </div>
                                               <input class="form-control form-icon-input mb-3" id="firstName" type="text" placeholder="First Name" />
                                           </div>
                                      </div>
                                      <div class="col-8">
                                             <div class="d-flex flex-wrap justify-content-between mb-3">
                                                 <div class="d-flex flex-wrap justify-content-between mb-2">
                                                   <h5 class="mb-0 text-1000 me-2">Apellido Paterno</h5>
                                                 </div>
                                                 <input class="form-control form-icon-input mb-3" id="firstName" type="text" placeholder="First Name" />
                                             </div>
                                      </div>
                                      <div class="col-8">
                                           <div class="d-flex flex-wrap justify-content-between mb-3">
                                               <div class="d-flex flex-wrap justify-content-between mb-2">
                                                 <h5 class="mb-0 text-1000 me-2">Apellido Materno</h5>
                                               </div>
                                               <input class="form-control form-icon-input mb-3" id="firstName" type="text" placeholder="First Name" />
                                           </div>
                                      </div>
                                      <div class="col-8">
                                          <div class="d-flex flex-wrap justify-content-between mb-3">
                                              <div class="d-flex flex-wrap justify-content-between mb-2">
                                                <h5 class="mb-0 text-1000 me-2">Nombres</h5>
                                              </div>
                                              <input class="form-control form-icon-input mb-3" id="firstName" type="text" placeholder="First Name" />
                                        </div>
                                      </div>

                                      <div class="col-8">
                                           <div class="d-flex flex-wrap justify-content-between mb-3">
                                               <div class="d-flex flex-wrap justify-content-between mb-2">
                                                 <h5 class="mb-0 text-1000 me-2">Fecha de Nacimiento</h5>
                                               </div>
                                               <input class="form-control form-icon-input mb-3" id="firstName" type="text" placeholder="First Name" />
                                           </div>
                                      </div>
                                      <div class="col-8">
                                          <div class="d-flex flex-wrap justify-content-between mb-3">
                                             <div class="d-flex flex-wrap justify-content-between mb-2">
                                                <h5 class="mb-0 text-1000 mb-2">Sexo(*)[TT50]</h5>
                                             </div>
                                              <select class="form-select mb-3" aria-label="stage">
                                                <option value="new">New</option>
                                                <option value="in-progress">In Progress</option>
                                                <option value="pending">Pending</option>
                                                <option value="canceled">Canceled</option>
                                                <option value="completed">Completed</option>
                                              </select>
                                          </div>
                                      </div>
                                      <div class="col-8">
                                            <div class="d-flex flex-wrap justify-content-between mb-3">
                                               <div class="d-flex flex-wrap justify-content-between mb-2">
                                                  <h5 class="mb-0 text-1000 mb-2">Estado Civil (*) [TT68]</h5>
                                               </div>
                                                <select class="form-select mb-3" aria-label="stage">
                                                  <option value="new">New</option>
                                                  <option value="in-progress">In Progress</option>
                                                  <option value="pending">Pending</option>
                                                  <option value="canceled">Canceled</option>
                                                  <option value="completed">Completed</option>
                                                </select>
                                            </div>
                                      </div>
                                      <div class="col-8">
                                        <div class="d-flex flex-wrap justify-content-between mb-3">
                                      	 <div class="d-flex flex-wrap justify-content-between mb-2">
                                      		<h5 class="mb-0 text-1000 mb-2">Modalidad Formativa [TT18]</h5>
                                      	 </div>
                                      	  <select class="form-select mb-3" aria-label="stage">
                                      		<option value="new">New</option>
                                      		<option value="in-progress">In Progress</option>
                                      		<option value="pending">Pending</option>
                                      		<option value="canceled">Canceled</option>
                                      		<option value="completed">Completed</option>
                                      	  </select>
                                        </div>
                                      </div>
                                      <div class="col-8">
                                        <div class="d-flex flex-wrap justify-content-between mb-3">
                                      	 <div class="d-flex flex-wrap justify-content-between mb-2">
                                      		<h5 class="mb-0 text-1000 mb-2">Nacionalidad Origen (*) [TT4]</h5>
                                      	 </div>
                                      	  <select class="form-select mb-3" aria-label="stage">
                                      		<option value="new">New</option>
                                      		<option value="in-progress">In Progress</option>
                                      		<option value="pending">Pending</option>
                                      		<option value="canceled">Canceled</option>
                                      		<option value="completed">Completed</option>
                                      	  </select>
                                        </div>
                                      </div>
                                      <div class="col-8">
                                        <div class="d-flex flex-wrap justify-content-between mb-3">
                                      	 <div class="d-flex flex-wrap justify-content-between mb-2">
                                      		<h5 class="mb-0 text-1000 mb-2">Pais Emisor (*) [TT26]</h5>
                                      	 </div>
                                      	  <select class="form-select mb-3" aria-label="stage">
                                      		<option value="new">New</option>
                                      		<option value="in-progress">In Progress</option>
                                      		<option value="pending">Pending</option>
                                      		<option value="canceled">Canceled</option>
                                      		<option value="completed">Completed</option>
                                      	  </select>
                                        </div>
                                      </div>
                                      <div class="col-8">
                                        <div class="d-flex flex-wrap justify-content-between mb-3">
                                      	 <div class="d-flex flex-wrap justify-content-between mb-2">
                                      		<h5 class="mb-0 text-1000 mb-2">Departamento</h5>
                                      	 </div>
                                      	  <select class="form-select mb-3" aria-label="stage">
                                      		<option value="new">New</option>
                                      		<option value="in-progress">In Progress</option>
                                      		<option value="pending">Pending</option>
                                      		<option value="canceled">Canceled</option>
                                      		<option value="completed">Completed</option>
                                      	  </select>
                                        </div>
                                      </div>
                                      <div class="col-8">
                                        <div class="d-flex flex-wrap justify-content-between mb-3">
                                      	 <div class="d-flex flex-wrap justify-content-between mb-2">
                                      		<h5 class="mb-0 text-1000 mb-2">Provincia</h5>
                                      	 </div>
                                      	  <select class="form-select mb-3" aria-label="stage">
                                      		<option value="new">New</option>
                                      		<option value="in-progress">In Progress</option>
                                      		<option value="pending">Pending</option>
                                      		<option value="canceled">Canceled</option>
                                      		<option value="completed">Completed</option>
                                      	  </select>
                                        </div>
                                      </div>
                                      <div class="col-8">
                                        <div class="d-flex flex-wrap justify-content-between mb-3">
                                      	 <div class="d-flex flex-wrap justify-content-between mb-2">
                                      		<h5 class="mb-0 text-1000 mb-2">Distrito (*)</h5>
                                      	 </div>
                                      	  <select class="form-select mb-3" aria-label="stage">
                                      		<option value="new">New</option>
                                      		<option value="in-progress">In Progress</option>
                                      		<option value="pending">Pending</option>
                                      		<option value="canceled">Canceled</option>
                                      		<option value="completed">Completed</option>
                                      	  </select>
                                        </div>
                                      </div>
                                      <div class="col-8">
                                        <div class="d-flex flex-wrap justify-content-between mb-3">
                                      	 <div class="d-flex flex-wrap justify-content-between mb-2">
                                      		<h5 class="mb-0 text-1000 mb-2">Grado Instruccion (*) [TT9]</h5>
                                      	 </div>
                                      	  <select class="form-select mb-3" aria-label="stage">
                                      		<option value="new">New</option>
                                      		<option value="in-progress">In Progress</option>
                                      		<option value="pending">Pending</option>
                                      		<option value="canceled">Canceled</option>
                                      		<option value="completed">Completed</option>
                                      	  </select>
                                        </div>
                                      </div>
                                      <div class="col-8">
                                        <div class="d-flex flex-wrap justify-content-between mb-3">
                                      	 <div class="d-flex flex-wrap justify-content-between mb-2">
                                      		<h5 class="mb-0 text-1000 mb-2">Centro de Formacion [TT51]</h5>
                                      	 </div>
                                      	  <select class="form-select mb-3" aria-label="stage">
                                      		<option value="new">New</option>
                                      		<option value="in-progress">In Progress</option>
                                      		<option value="pending">Pending</option>
                                      		<option value="canceled">Canceled</option>
                                      		<option value="completed">Completed</option>
                                      	  </select>
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

                                      <div class="col-8">
                                        <div class="d-flex flex-wrap justify-content-between mb-3">
                                      	 <div class="d-flex flex-wrap justify-content-between mb-2">
                                      		<h5 class="mb-0 text-1000 mb-2">Codigo de Larga Distancia [TT29]</h5>
                                      	 </div>
                                      	  <select class="form-select mb-3" aria-label="stage">
                                      		<option value="new">New</option>
                                      		<option value="in-progress">In Progress</option>
                                      		<option value="pending">Pending</option>
                                      		<option value="canceled">Canceled</option>
                                      		<option value="completed">Completed</option>
                                      	  </select>
                                        </div>
                                      </div>
                                      <div class="col-5">
                                        <div class="d-flex flex-wrap justify-content-between mb-3">
                                            <div class="d-flex flex-wrap justify-content-between mb-2">
                                              <h5 class="mb-0 text-1000 me-2">Nro de Telefono</h5>
                                            </div>
                                            <input class="form-control form-icon-input mb-3" id="firstName" type="text" placeholder="First Name" />
                                        </div>
                                      </div>
                                      <div class="col-8">
                                          <div class="d-flex flex-wrap justify-content-between mb-3">
                                              <div class="d-flex flex-wrap justify-content-between mb-2">
                                                <h5 class="mb-0 text-1000 me-2">Email</h5>
                                              </div>
                                              <input class="form-control form-icon-input mb-3" id="firstName" type="text" placeholder="First Name" />
                                          </div>
                                      </div>
                                      <div class="col-8">
                                           <div class="d-flex flex-wrap justify-content-between mb-3">
                                               <div class="d-flex flex-wrap justify-content-between mb-2">
                                                 <h5 class="mb-0 text-1000 me-2">Email Coorp</h5>
                                               </div>
                                               <input class="form-control form-icon-input mb-3" id="firstName" type="text" placeholder="First Name" />
                                           </div>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>

                        </div>




                        <div class="mb-8">
                          <div class="d-flex justify-content-between align-items-center mb-4" id="scrollspyDeals">
                            <h2 class="mb-0">Deals</h2>
                            <button class="btn btn-primary btn-sm"><span class="fa-solid fa-plus me-2"></span>Add Deals</button>
                          </div>
                          <div class="border-top border-bottom border-200" id="leadDetailsTable" data-list='{"valueNames":["dealName","amount","stage","probability","date","type"],"page":5,"pagination":true}'>
                            <div class="table-responsive scrollbar mx-n1 px-1">
                              <table class="table fs--1 mb-0">
                                <thead>
                                  <tr>
                                    <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" type="checkbox" data-bulk-select='{"body":"lead-details-table-body"}' />
                                      </div>
                                    </th>
                                    <th class="sort white-space-nowrap align-middle pe-3 ps-0 text-uppercase" scope="col" data-sort="dealName" style="width:15%; min-width:200px">Deal name</th>
                                    <th class="sort align-middle pe-6 text-uppercase text-end" scope="col" data-sort="amount" style="width:15%; min-width:100px">Amount</th>
                                    <th class="sort align-middle text-start text-uppercase" scope="col" data-sort="stage" style="width:20%; min-width:200px">Stage</th>
                                    <th class="sort align-middle text-start text-uppercase" scope="col" data-sort="probability" style="width:20%; min-width:100px">Probability</th>
                                    <th class="sort align-middle ps-0 text-end text-uppercase" scope="col" data-sort="date" style="width:15%; min-width:120px">Closing Date</th>
                                    <th class="sort align-middle text-end text-uppercase" scope="col" data-sort="type" style="width:15%; min-width:140px">Type</th>
                                    <th class="align-middle pe-0 text-end" scope="col" style="width:15%;"> </th>
                                  </tr>
                                </thead>
                                <tbody class="list" id="lead-details-table-body">
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                    <td class="fs--1 align-middle px-0 py-3">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" type="checkbox" data-bulk-select-row='{"dealName":"Mocking Bird","active":true,"amount":"$6,800,000","stage_status":{"label":"won deal","type":"badge-phoenix-success"},"progress":{"min":"67","max":"145","color":"bg-info"},"date":"Dec 29, 2021","type_status":{"label":"warm","type":"badge-phoenix-info"}}' />
                                      </div>
                                    </td>
                                    <td class="dealName align-middle white-space-nowrap py-2 ps-0"><a class="fw-semi-bold text-primary" href="#!">Mocking Bird</a></td>
                                    <td class="amount align-middle white-space-nowrap text-start fw-bold text-700 py-2 text-end pe-6">$6,800,000</td>
                                    <td class="stage align-middle white-space-nowrap text-900 py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-success">won deal</span></td>
                                    <td class="probability align-middle white-space-nowrap">
                                      <p class="text-800 fs--2 mb-0">67%</p>
                                      <div class="progress bg-primary-100" style="height:3px;" role="progressbar">
                                        <div class="progress-bar bg-info" style="width: 46.206896551724135%" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                      </div>
                                    </td>
                                    <td class="date align-middle text-700 text-center py-2">Dec 29, 2021</td>
                                    <td class="type align-middle fw-semi-bold py-2 text-end"><span class="badge badge-phoenix fs--2 badge-phoenix-info">warm</span></td>
                                    <td class="align-middle text-end white-space-nowrap pe-0 action py-2">
                                      <div class="font-sans-serif btn-reveal-trigger position-static">
                                        <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                        <div class="dropdown-menu dropdown-menu-end py-2"><a class="dropdown-item" href="#!">View</a><a class="dropdown-item" href="#!">Export</a>
                                          <div class="dropdown-divider"></div><a class="dropdown-item text-danger" href="#!">Remove</a>
                                        </div>
                                      </div>
                                    </td>
                                  </tr>
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                    <td class="fs--1 align-middle px-0 py-3">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" type="checkbox" data-bulk-select-row='{"dealName":"Airbender","active":true,"amount":"$89,090,000","stage_status":{"label":"new Deal","type":"badge-phoenix-primary"},"progress":{"min":"34","max":"145","color":"bg-warning"},"date":"Mar 27, 2021","type_status":{"label":"hot","type":"badge-phoenix-danger"}}' />
                                      </div>
                                    </td>
                                    <td class="dealName align-middle white-space-nowrap py-2 ps-0"><a class="fw-semi-bold text-primary" href="#!">Airbender</a></td>
                                    <td class="amount align-middle white-space-nowrap text-start fw-bold text-700 py-2 text-end pe-6">$89,090,000</td>
                                    <td class="stage align-middle white-space-nowrap text-900 py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-primary">new Deal</span></td>
                                    <td class="probability align-middle white-space-nowrap">
                                      <p class="text-800 fs--2 mb-0">34%</p>
                                      <div class="progress bg-primary-100" style="height:3px;" role="progressbar">
                                        <div class="progress-bar bg-warning" style="width: 23.448275862068964%" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                      </div>
                                    </td>
                                    <td class="date align-middle text-700 text-center py-2">Mar 27, 2021</td>
                                    <td class="type align-middle fw-semi-bold py-2 text-end"><span class="badge badge-phoenix fs--2 badge-phoenix-danger">hot</span></td>
                                    <td class="align-middle text-end white-space-nowrap pe-0 action py-2">
                                      <div class="font-sans-serif btn-reveal-trigger position-static">
                                        <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                        <div class="dropdown-menu dropdown-menu-end py-2"><a class="dropdown-item" href="#!">View</a><a class="dropdown-item" href="#!">Export</a>
                                          <div class="dropdown-divider"></div><a class="dropdown-item text-danger" href="#!">Remove</a>
                                        </div>
                                      </div>
                                    </td>
                                  </tr>
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                    <td class="fs--1 align-middle px-0 py-3">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" type="checkbox" data-bulk-select-row='{"dealName":"Showmen","active":true,"amount":"$78,650,000","stage_status":{"label":"Canceled","type":"badge-phoenix-secondary"},"progress":{"min":"89","max":"145","color":"bg-success"},"date":"Jun 24, 2021","type_status":{"label":"cold","type":"badge-phoenix-warning"}}' />
                                      </div>
                                    </td>
                                    <td class="dealName align-middle white-space-nowrap py-2 ps-0"><a class="fw-semi-bold text-primary" href="#!">Showmen</a></td>
                                    <td class="amount align-middle white-space-nowrap text-start fw-bold text-700 py-2 text-end pe-6">$78,650,000</td>
                                    <td class="stage align-middle white-space-nowrap text-900 py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-secondary">Canceled</span></td>
                                    <td class="probability align-middle white-space-nowrap">
                                      <p class="text-800 fs--2 mb-0">89%</p>
                                      <div class="progress bg-primary-100" style="height:3px;" role="progressbar">
                                        <div class="progress-bar bg-success" style="width: 61.37931034482759%" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                      </div>
                                    </td>
                                    <td class="date align-middle text-700 text-center py-2">Jun 24, 2021</td>
                                    <td class="type align-middle fw-semi-bold py-2 text-end"><span class="badge badge-phoenix fs--2 badge-phoenix-warning">cold</span></td>
                                    <td class="align-middle text-end white-space-nowrap pe-0 action py-2">
                                      <div class="font-sans-serif btn-reveal-trigger position-static">
                                        <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                        <div class="dropdown-menu dropdown-menu-end py-2"><a class="dropdown-item" href="#!">View</a><a class="dropdown-item" href="#!">Export</a>
                                          <div class="dropdown-divider"></div><a class="dropdown-item text-danger" href="#!">Remove</a>
                                        </div>
                                      </div>
                                    </td>
                                  </tr>
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                    <td class="fs--1 align-middle px-0 py-3">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" type="checkbox" data-bulk-select-row='{"dealName":"Tarakihi","active":true,"amount":"$1,200,000","stage_status":{"label":"In Progress","type":"badge-phoenix-info"},"progress":{"min":"90","max":"145","color":"bg-success"},"date":"May 19, 2024","type_status":{"label":"hot","type":"badge-phoenix-danger"}}' />
                                      </div>
                                    </td>
                                    <td class="dealName align-middle white-space-nowrap py-2 ps-0"><a class="fw-semi-bold text-primary" href="#!">Tarakihi</a></td>
                                    <td class="amount align-middle white-space-nowrap text-start fw-bold text-700 py-2 text-end pe-6">$1,200,000</td>
                                    <td class="stage align-middle white-space-nowrap text-900 py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-info">In Progress</span></td>
                                    <td class="probability align-middle white-space-nowrap">
                                      <p class="text-800 fs--2 mb-0">90%</p>
                                      <div class="progress bg-primary-100" style="height:3px;" role="progressbar">
                                        <div class="progress-bar bg-success" style="width: 62.06896551724138%" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                      </div>
                                    </td>
                                    <td class="date align-middle text-700 text-center py-2">May 19, 2024</td>
                                    <td class="type align-middle fw-semi-bold py-2 text-end"><span class="badge badge-phoenix fs--2 badge-phoenix-danger">hot</span></td>
                                    <td class="align-middle text-end white-space-nowrap pe-0 action py-2">
                                      <div class="font-sans-serif btn-reveal-trigger position-static">
                                        <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                        <div class="dropdown-menu dropdown-menu-end py-2"><a class="dropdown-item" href="#!">View</a><a class="dropdown-item" href="#!">Export</a>
                                          <div class="dropdown-divider"></div><a class="dropdown-item text-danger" href="#!">Remove</a>
                                        </div>
                                      </div>
                                    </td>
                                  </tr>
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                    <td class="fs--1 align-middle px-0 py-3">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" type="checkbox" data-bulk-select-row='{"dealName":"Ponce d’leon","active":true,"amount":"$46,000","stage_status":{"label":"won Deal","type":"badge-phoenix-success"},"progress":{"min":"97","max":"145","color":"bg-success"},"date":"Aug 19, 2024","type_status":{"label":"cold","type":"badge-phoenix-warning"}}' />
                                      </div>
                                    </td>
                                    <td class="dealName align-middle white-space-nowrap py-2 ps-0"><a class="fw-semi-bold text-primary" href="#!">Ponce d’leon</a></td>
                                    <td class="amount align-middle white-space-nowrap text-start fw-bold text-700 py-2 text-end pe-6">$46,000</td>
                                    <td class="stage align-middle white-space-nowrap text-900 py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-success">won Deal</span></td>
                                    <td class="probability align-middle white-space-nowrap">
                                      <p class="text-800 fs--2 mb-0">97%</p>
                                      <div class="progress bg-primary-100" style="height:3px;" role="progressbar">
                                        <div class="progress-bar bg-success" style="width: 66.89655172413794%" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                      </div>
                                    </td>
                                    <td class="date align-middle text-700 text-center py-2">Aug 19, 2024</td>
                                    <td class="type align-middle fw-semi-bold py-2 text-end"><span class="badge badge-phoenix fs--2 badge-phoenix-warning">cold</span></td>
                                    <td class="align-middle text-end white-space-nowrap pe-0 action py-2">
                                      <div class="font-sans-serif btn-reveal-trigger position-static">
                                        <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                        <div class="dropdown-menu dropdown-menu-end py-2"><a class="dropdown-item" href="#!">View</a><a class="dropdown-item" href="#!">Export</a>
                                          <div class="dropdown-divider"></div><a class="dropdown-item text-danger" href="#!">Remove</a>
                                        </div>
                                      </div>
                                    </td>
                                  </tr>
                                  <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                                    <td class="fs--1 align-middle px-0 py-3">
                                      <div class="form-check mb-0 fs-0">
                                        <input class="form-check-input" type="checkbox" data-bulk-select-row='{"dealName":"leon","active":true,"amount":"$66,000","stage_status":{"label":"IN PROGRESS","type":"badge-phoenix-info"},"progress":{"min":"88","max":"145","color":"bg-success"},"date":"Aug 19, 2024","type_status":{"label":"cold","type":"badge-phoenix-warning"}}' />
                                      </div>
                                    </td>
                                    <td class="dealName align-middle white-space-nowrap py-2 ps-0"><a class="fw-semi-bold text-primary" href="#!">leon</a></td>
                                    <td class="amount align-middle white-space-nowrap text-start fw-bold text-700 py-2 text-end pe-6">$66,000</td>
                                    <td class="stage align-middle white-space-nowrap text-900 py-2"><span class="badge badge-phoenix fs--2 badge-phoenix-info">IN PROGRESS</span></td>
                                    <td class="probability align-middle white-space-nowrap">
                                      <p class="text-800 fs--2 mb-0">88%</p>
                                      <div class="progress bg-primary-100" style="height:3px;" role="progressbar">
                                        <div class="progress-bar bg-success" style="width: 60.689655172413794%" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                      </div>
                                    </td>
                                    <td class="date align-middle text-700 text-center py-2">Aug 19, 2024</td>
                                    <td class="type align-middle fw-semi-bold py-2 text-end"><span class="badge badge-phoenix fs--2 badge-phoenix-warning">cold</span></td>
                                    <td class="align-middle text-end white-space-nowrap pe-0 action py-2">
                                      <div class="font-sans-serif btn-reveal-trigger position-static">
                                        <button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                        <div class="dropdown-menu dropdown-menu-end py-2"><a class="dropdown-item" href="#!">View</a><a class="dropdown-item" href="#!">Export</a>
                                          <div class="dropdown-divider"></div><a class="dropdown-item text-danger" href="#!">Remove</a>
                                        </div>
                                      </div>
                                    </td>
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
    <jsp:include page="../plugins.jsp"></jsp:include>
  </body>

</html>