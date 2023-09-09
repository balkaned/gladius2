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
    <jsp:include page="links.jsp"></jsp:include>
  </head>

  <body>
    <!-- ===============================================-->
    <!--    Main Content-->
    <!-- ===============================================-->
    <main class="main" id="top">
          <jsp:include page="navsMenu.jsp"></jsp:include>
          <jsp:include page="navTop.jsp"></jsp:include>
          <jsp:include page="modalFade.jsp"></jsp:include>


          <div id="kanban" class="content kanban-content mt-2" >
                  <div class="kanban-header">
                    <div class="row gx-0 justify-content-between justify-content-md-start">
                      <div class="col-auto">
                        <div class="dropdown">
                          <button class="btn btn-link text-decoration-none text-1100 fs-0 ps-4" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <span class="fs-1 me-2">Bienvenido</span><span class="fas fa-angle-down text-500 d-inline-block" data-fa-transform="up-2" style="min-width: 12px"></span></button>
                          <div class="dropdown-menu py-0"> <a class="dropdown-item" href="#!">Sparrow</a><a class="dropdown-item" href="#!">Boreas</a><a class="dropdown-item" href="#!">Erebus</a></div>
                        </div>
                      </div>
                      <div class="col-auto d-flex align-items-center">
                        <div class="avatar-group"><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-m ">
                              <img class="rounded-circle " src="resources/assets/img/team/30.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img/team/30.webp" alt="" /></div>
                                  <h6 class="text-white light">Stanly Drinkwater</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-m ">
                              <img class="rounded-circle " src="resources/assets/img/team/60.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img/team/60.webp" alt="" /></div>
                                  <h6 class="text-white light">Emma Watson</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-m ">
                              <img class="rounded-circle " src="resources/assets/img/team/25.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img/team/25.webp" alt="" /></div>
                                  <h6 class="text-white light">Igor Borvibson</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-m ">
                              <img class="rounded-circle " src="resources/assets/img/team/5.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img/team/5.webp" alt="" /></div>
                                  <h6 class="text-white light">Luis Bunuel</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div>
                        </div>
                        <button class="btn btn-primary ms-4 fs--2 px-3"><span class="fas fa-user-plus d-inline-block" style="min-width: 14px"></span><span class="d-none d-sm-inline ms-2">invite</span></button>
                      </div>
                      <div class="col-md-auto d-flex align-items-center ms-auto mt-2 mt-md-0">
                        <ul class="nav w-100 fs--1">
                          <li class="nav-item"><a class="nav-link d-flex align-items-center text-900 ps-0 pe-2 px-xl-3 fw-bold" href="#!" data-bs-toggle="modal" data-bs-target="#searchBoxModal"><span class="me-1 fas fa-search" data-fa-transform="up-2" style="min-width: 14px"></span><span class="d-none d-xxl-inline">Search</span></a></li>
                          <li class="nav-item"><a class="nav-link d-flex align-items-center text-900 px-2 px-xl-3 fw-bold" href="#!"><span class="me-1 fas fa-filter" data-fa-transform="up-2" style="min-width: 14px"></span><span class="d-none d-xxl-inline">Filter</span></a></li>
                          <li class="nav-item"><a class="nav-link d-flex align-items-center text-900 px-2 px-xl-3 fw-bold" href="#!"><span class="me-1 fa-solid fa-right-left" data-fa-transform="up-2" style="min-width: 14px"></span><span class="d-none d-xxl-inline">Export/import</span></a></li>
                          <li class="nav-item"><a class="nav-link d-flex align-items-center text-900 px-2 px-xl-3 fw-bold" href="#!"><span class="me-1 fas fa-palette" data-fa-transform="up-2" style="min-width: 14px"></span><span class="d-none d-xxl-inline">Modify</span></a></li>
                          <li class="nav-item"><a class="nav-link d-flex align-items-center text-900 px-2 px-xl-3 fw-bold" href="#!"><span class="me-1 fa-solid fa-bars-staggered" data-fa-transform="up-2" style="min-width: 14px"></span><span class="d-none d-xxl-inline">Gantt</span></a></li>
                          <li class="nav-item"><a class="nav-link d-flex align-items-center text-900 px-2 px-xl-3 fw-bold" href="#!"><span class="me-1 fa-solid fa-calendar-days" data-fa-transform="up-2" style="min-width: 14px"></span><span class="d-none d-xxl-inline">Calendar</span></a></li>
                          <li class="nav-item"><a class="nav-link d-flex align-items-center text-900 px-2 px-xl-3 fw-bold" href="#!"><span class="me-1 fa-solid fa-box-archive" data-fa-transform="up-2" style="min-width: 14px"></span><span class="d-none d-xxl-inline">Archive</span></a></li>
                          <li class="nav-item ms-auto"><a class="nav-link d-flex align-items-center pe-0 ps-1 ps-xl-3 text-900 h-100" data-phoenix-toggle="offcanvas" data-phoenix-target="#offcanvasKanban" href="#offcanvasKanban" role="button"><span class="fa-solid fa-bars d-inline" data-fa-transform="up-2" style="min-width: 14px"></span></a></li>
                        </ul>
                      </div>
                    </div>
                  </div>
                  <div class="kanban-container scrollbar" data-kanban-container="data-kanban-container">
                    <div class="kanban-column scrollbar collapsed">
                      <div class="kanban-column-header px-4 hover-actions-trigger">
                        <div class="d-flex align-items-center border-bottom border-3 py-3 border-warning">
                          <h5 class="mb-0 kanban-column-title">Unassaigned<span class="kanban-title-badge">3</span></h5>
                          <div class="hover-actions-trigger">
                            <button class="btn btn-sm btn-phoenix-default kanban-header-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h"></span></button>
                            <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Sort tasks</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Sort all tasks</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move all tasks</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Remove all tasks</span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Import</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Export</span><span class="fas fa-angle-right fs--2"></span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move column</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate column</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Delete column</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Archive column</span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Edit title &amp; description</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Edit colour</span><span class="fas fa-angle-right fs--2"></span></a>
                            </div>
                          </div><span class="uil uil-left-arrow-to-left fs-0 ms-auto kanban-collapse-icon" data-kanban-collapse="data-kanban-collapse"></span><span class="uil uil-arrow-from-right fs-0 ms-auto kanban-collapse-icon" data-kanban-collapse="data-kanban-collapse"></span>
                        </div>
                      </div>
                      <div class="kanban-items-container" data-sortable="data-sortable">
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-primary" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-primary"><span>Feature</span><span class="ms-1 d-inline-block fas fa-check-double" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Develop a new feature for the phoenix mobile app</p>
                              <div class="d-flex mt-2 align-items-center">
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-danger" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span>Bug</span><span class="ms-1 d-inline-block fas fa-bug" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Conduct user research to gather feedback on the latest product iteration</p>
                              <div class="d-flex mt-2 align-items-center">
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-warning" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-warning"><span>Issue</span><span class="ms-1 d-inline-block fa-solid fa-triangle-exclamation" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Review and approve marketing materials for the upcoming product launch</p>
                              <div class="d-flex mt-2 align-items-center">
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="py-3 px-4 kanban-add-task">
                        <button class="btn bg-sm bg-300 me-2 px-0" data-bs-toggle="modal" data-bs-target="#kanbanAddTask"><span class="fas fa-plus text-white dark__text-400" data-fa-transform="grow-4 down-1"></span></button>
                        <input class="form-control search-input rounded-3 px-3" placeholder="Add new task" />
                      </div>
                    </div>
                    <div class="kanban-column scrollbar">
                      <div class="kanban-column-header px-4 hover-actions-trigger">
                        <div class="d-flex align-items-center border-bottom border-3 py-3 border-300">
                          <h5 class="mb-0 kanban-column-title">To do<span class="kanban-title-badge">2</span></h5>
                          <div class="hover-actions-trigger">
                            <button class="btn btn-sm btn-phoenix-default kanban-header-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h"></span></button>
                            <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Sort tasks</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Sort all tasks</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move all tasks</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Remove all tasks</span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Import</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Export</span><span class="fas fa-angle-right fs--2"></span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move column</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate column</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Delete column</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Archive column</span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Edit title &amp; description</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Edit colour</span><span class="fas fa-angle-right fs--2"></span></a>
                            </div>
                          </div><span class="uil uil-left-arrow-to-left fs-0 ms-auto kanban-collapse-icon" data-kanban-collapse="data-kanban-collapse"></span><span class="uil uil-arrow-from-right fs-0 ms-auto kanban-collapse-icon" data-kanban-collapse="data-kanban-collapse"></span>
                        </div>
                      </div>
                      <div class="kanban-items-container" data-sortable="data-sortable">
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="position-relative mb-2 overflow-hidden rounded" style="height:200px; width:100%">
                                <div class="bg-holder" style="background-image:url(resources/assets/img/kanban/1.jpg);">
                                </div>
                                <!--/.bg-holder-->

                              </div>
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-danger" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span>Bug</span><span class="ms-1 d-inline-block fas fa-bug" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Test and debug code for the e-commerce website checkout process</p>
                              <div class="d-flex mt-2 align-items-center">
                                <p class="mb-0 text-600 fs--1 lh-1"><span class="fa-solid fa-paperclip fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>15</p>
                                <div class="avatar-group ms-auto">
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/30.webp" alt="" />

                                  </div>
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/57.webp" alt="" />

                                  </div>
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/25.webp" alt="" />

                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-warning" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-warning"><span>Issue</span><span class="ms-1 d-inline-block fa-solid fa-triangle-exclamation" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Write a blog post on industry trends and best practices</p>
                              <div class="d-flex mt-2 align-items-center">
                                <p class="mb-0 text-600 fs--1 lh-1 me-3 white-space-nowrap"><span class="fa-solid fa-calendar-xmark fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>Jan 25</p>
                                <div class="avatar-group ms-auto">
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/30.webp" alt="" />

                                  </div>
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/57.webp" alt="" />

                                  </div>
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/25.webp" alt="" />

                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="py-3 px-4 kanban-add-task">
                        <button class="btn bg-sm bg-300 me-2 px-0" data-bs-toggle="modal" data-bs-target="#kanbanAddTask"><span class="fas fa-plus text-white dark__text-400" data-fa-transform="grow-4 down-1"></span></button>
                        <input class="form-control search-input rounded-3 px-3" placeholder="Add new task" />
                      </div>
                    </div>
                    <div class="kanban-column scrollbar">
                      <div class="kanban-column-header px-4 hover-actions-trigger">
                        <div class="d-flex align-items-center border-bottom border-3 py-3 border-primary">
                          <h5 class="mb-0 kanban-column-title">Doing<span class="kanban-title-badge">4</span></h5>
                          <div class="hover-actions-trigger">
                            <button class="btn btn-sm btn-phoenix-default kanban-header-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h"></span></button>
                            <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Sort tasks</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Sort all tasks</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move all tasks</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Remove all tasks</span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Import</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Export</span><span class="fas fa-angle-right fs--2"></span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move column</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate column</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Delete column</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Archive column</span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Edit title &amp; description</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Edit colour</span><span class="fas fa-angle-right fs--2"></span></a>
                            </div>
                          </div><span class="uil uil-left-arrow-to-left fs-0 ms-auto kanban-collapse-icon" data-kanban-collapse="data-kanban-collapse"></span><span class="uil uil-arrow-from-right fs-0 ms-auto kanban-collapse-icon" data-kanban-collapse="data-kanban-collapse"></span>
                        </div>
                      </div>
                      <div class="kanban-items-container" data-sortable="data-sortable">
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-danger" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span>Bug</span><span class="ms-1 d-inline-block fas fa-bug" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Create wireframes for a new phoenix landing page design</p>
                              <div class="d-flex mt-2 align-items-center">
                                <p class="mb-0 text-600 fs--1 lh-1 me-3 white-space-nowrap"><span class="fa-solid fa-calendar-xmark fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>Jan 25</p>
                                <div class="avatar-group ms-auto">
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/57.webp" alt="" />

                                  </div>
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <div class="avatar-name rounded-circle text-warning bg-soft-warning"><span>R</span></div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-secondary" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-secondary"><span>Undefined</span><span class="ms-1 d-inline-block fas fa-spinner" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Set up and configure a new software tool for the marketing team</p>
                              <div class="d-flex mt-2 align-items-center">
                                <p class="mb-0 text-600 fs--1 lh-1"><span class="fa fa-check-square fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>5/34</p>
                                <div class="avatar-group ms-auto">
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/25.webp" alt="" />

                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-primary" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-primary"><span>Feature</span><span class="ms-1 d-inline-block fas fa-check-double" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Draft and send a press release to announce a new partnership</p>
                              <div class="d-flex mt-2 align-items-center">
                                <p class="mb-0 text-600 fs--1 lh-1 me-3 white-space-nowrap"><span class="fa-solid fa-calendar-xmark fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>Jan 25</p>
                                <p class="mb-0 text-600 fs--1 lh-1"><span class="fa-solid fa-paperclip fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>15</p>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="position-relative mb-2 overflow-hidden rounded" style="height:200px; width:100%">
                                <div class="bg-holder" style="background-image:url(resources/assets/img/kanban/glass.jpg);">
                                </div>
                                <!--/.bg-holder-->

                              </div>
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-warning" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-warning"><span>Issue</span><span class="ms-1 d-inline-block fa-solid fa-triangle-exclamation" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Conduct a security audit of the phoenix web applications</p>
                              <div class="d-flex mt-2 align-items-center">
                                <p class="mb-0 text-600 fs--1 lh-1 me-3 white-space-nowrap"><span class="fa-solid fa-calendar-xmark fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>Jan 25</p>
                                <p class="mb-0 text-600 fs--1 lh-1"><span class="fa-solid fa-paperclip fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>15</p>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="py-3 px-4 kanban-add-task">
                        <button class="btn bg-sm bg-300 me-2 px-0" data-bs-toggle="modal" data-bs-target="#kanbanAddTask"><span class="fas fa-plus text-white dark__text-400" data-fa-transform="grow-4 down-1"></span></button>
                        <input class="form-control search-input rounded-3 px-3" placeholder="Add new task" />
                      </div>
                    </div>
                    <div class="kanban-column scrollbar">
                      <div class="kanban-column-header px-4 hover-actions-trigger">
                        <div class="d-flex align-items-center border-bottom border-3 py-3 border-info">
                          <h5 class="mb-0 kanban-column-title">Review<span class="kanban-title-badge">4</span></h5>
                          <div class="hover-actions-trigger">
                            <button class="btn btn-sm btn-phoenix-default kanban-header-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h"></span></button>
                            <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Sort tasks</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Sort all tasks</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move all tasks</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Remove all tasks</span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Import</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Export</span><span class="fas fa-angle-right fs--2"></span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move column</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate column</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Delete column</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Archive column</span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Edit title &amp; description</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Edit colour</span><span class="fas fa-angle-right fs--2"></span></a>
                            </div>
                          </div><span class="uil uil-left-arrow-to-left fs-0 ms-auto kanban-collapse-icon" data-kanban-collapse="data-kanban-collapse"></span><span class="uil uil-arrow-from-right fs-0 ms-auto kanban-collapse-icon" data-kanban-collapse="data-kanban-collapse"></span>
                        </div>
                      </div>
                      <div class="kanban-items-container" data-sortable="data-sortable">
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-warning" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-warning"><span>Issue</span><span class="ms-1 d-inline-block fa-solid fa-triangle-exclamation" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Design and develop a new logo for the phoenix</p>
                              <div class="d-flex mt-2 align-items-center">
                                <p class="mb-0 text-600 fs--1 lh-1"><span class="fa-solid fa-paperclip fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>15</p>
                                <div class="avatar-group ms-auto">
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/57.webp" alt="" />

                                  </div>
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/25.webp" alt="" />

                                  </div>
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/30.webp" alt="" />

                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-warning" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-warning"><span>Issue</span><span class="ms-1 d-inline-block fa-solid fa-triangle-exclamation" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Create a fresh visual identity for Phoenix with a new logo design</p>
                              <div class="d-flex mt-2 align-items-center">
                                <p class="mb-0 text-600 fs--1 lh-1"><span class="fa fa-check-square fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>5/34</p>
                                <div class="avatar-group ms-auto">
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/25.webp" alt="" />

                                  </div>
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/57.webp" alt="" />

                                  </div>
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/30.webp" alt="" />

                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-secondary" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-secondary"><span>Undefined</span><span class="ms-1 d-inline-block fas fa-spinner" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Identify best software vendors for company-wide system through comprehensive research and evaluation</p>
                              <div class="d-flex mt-2 align-items-center">
                                <p class="mb-0 text-600 fs--1 lh-1"><span class="fa-solid fa-paperclip fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>15</p>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="position-relative mb-2 overflow-hidden rounded" style="height:200px; width:100%">
                                <div class="bg-holder" style="background-image:url(resources/assets/img/kanban/wall.jpg);">
                                </div>
                                <!--/.bg-holder-->

                              </div>
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-primary" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-primary"><span>Feature</span><span class="ms-1 d-inline-block fas fa-check-double" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Write and edit copy for a new email marketing campaign</p>
                              <div class="d-flex mt-2 align-items-center">
                                <p class="mb-0 text-600 fs--1 lh-1"><span class="fa-solid fa-paperclip fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>15</p>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="py-3 px-4 kanban-add-task">
                        <button class="btn bg-sm bg-300 me-2 px-0" data-bs-toggle="modal" data-bs-target="#kanbanAddTask"><span class="fas fa-plus text-white dark__text-400" data-fa-transform="grow-4 down-1"></span></button>
                        <input class="form-control search-input rounded-3 px-3" placeholder="Add new task" />
                      </div>
                    </div>
                    <div class="kanban-column scrollbar">
                      <div class="kanban-column-header px-4 hover-actions-trigger">
                        <div class="d-flex align-items-center border-bottom border-3 py-3 border-success">
                          <h5 class="mb-0 kanban-column-title">Release<span class="kanban-title-badge">3</span></h5>
                          <div class="hover-actions-trigger">
                            <button class="btn btn-sm btn-phoenix-default kanban-header-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h"></span></button>
                            <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Sort tasks</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Sort all tasks</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move all tasks</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Remove all tasks</span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Import</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Export</span><span class="fas fa-angle-right fs--2"></span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move column</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate column</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Delete column</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Archive column</span></a>
                              <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Edit title &amp; description</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Edit colour</span><span class="fas fa-angle-right fs--2"></span></a>
                            </div>
                          </div><span class="uil uil-left-arrow-to-left fs-0 ms-auto kanban-collapse-icon" data-kanban-collapse="data-kanban-collapse"></span><span class="uil uil-arrow-from-right fs-0 ms-auto kanban-collapse-icon" data-kanban-collapse="data-kanban-collapse"></span>
                        </div>
                      </div>
                      <div class="kanban-items-container" data-sortable="data-sortable">
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-primary" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-primary"><span>Feature</span><span class="ms-1 d-inline-block fas fa-check-double" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Improve Phoenix website usability through user testing</p>
                              <div class="d-flex mt-2 align-items-center">
                                <p class="mb-0 text-600 fs--1 lh-1"><span class="fa-solid fa-paperclip fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>15</p>
                                <div class="avatar-group ms-auto">
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/57.webp" alt="" />

                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="position-relative mb-2 overflow-hidden rounded" style="height:200px; width:100%">
                                <div class="bg-holder" style="background-image:url(resources/assets/img/kanban/home.jpg);">
                                </div>
                                <!--/.bg-holder-->

                              </div>
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-danger" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span>Bug</span><span class="ms-1 d-inline-block fas fa-bug" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Develop and deliver a training program for new employees</p>
                              <div class="d-flex mt-2 align-items-center">
                                <p class="mb-0 text-600 fs--1 lh-1"><span class="fa-solid fa-paperclip fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>15</p>
                                <div class="avatar-group ms-auto">
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/57.webp" alt="" />

                                  </div>
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/25.webp" alt="" />

                                  </div>
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/30.webp" alt="" />

                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="sortable-item-wrapper border-bottom px-2 py-2">
                          <div class="card sortable-item hover-actions-trigger">
                            <div class="card-body py-3 px-3">
                              <div class="kanban-status mb-1 position-relative lh-1"><span class="fa fa-circle me-2 d-inline-block text-secondary" style="min-width:1rem" data-fa-transform="shrink-1 down-3"></span><span class="badge badge-phoenix fs--2 badge-phoenix-secondary"><span>Undefined</span><span class="ms-1 d-inline-block fas fa-spinner" data-fa-transform="up-2" style="height:7.8px;width:7.8px;"></span></span>
                                <div class="font-sans-serif">
                                  <button class="btn btn-sm btn-phoenix-default kanban-item-dropdown-btn hover-actions" type="button" data-boundary="viewport" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="fas fa-ellipsis-h fa-rotate-90" data-fa-transform="shrink-2"></span></button>
                                  <div class="dropdown-menu dropdown-menu-end py-2" style="width: 15rem;"><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Duplicate</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to top</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Jump to bottom</span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Print/Download</span></a><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Share</span><span class="fas fa-angle-right fs--2"></span></a>
                                    <hr class="my-2" /><a class="dropdown-item d-flex flex-between-center border-1 undefined" href="#!"><span>Move to archive</span><span class="fas fa-angle-right fs--2"></span></a><a class="dropdown-item d-flex flex-between-center border-1 text-danger" href="#!"><span>Delete</span></a>
                                  </div>
                                </div>
                              </div>
                              <p class="mb-0 stretched-link" data-bs-toggle="modal" data-bs-target="#KanbanItemDetailsModal">Organize and lead a brainstorming session to generate new product ideas</p>
                              <div class="d-flex mt-2 align-items-center">
                                <p class="mb-0 text-600 fs--1 lh-1"><span class="fa-solid fa-paperclip fs--1 me-2 d-inline-block" style="min-width: 1rem;"></span>15</p>
                                <div class="avatar-group ms-auto">
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/57.webp" alt="" />

                                  </div>
                                  <div class="avatar avatar-s  border border-white rounded-pill">
                                    <img class="rounded-circle " src="resources/assets/img/team/25.webp" alt="" />

                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="py-3 px-4 kanban-add-task">
                        <button class="btn bg-sm bg-300 me-2 px-0" data-bs-toggle="modal" data-bs-target="#kanbanAddTask"><span class="fas fa-plus text-white dark__text-400" data-fa-transform="grow-4 down-1"></span></button>
                        <input class="form-control search-input rounded-3 px-3" placeholder="Add new task" />
                      </div>
                    </div>
                    <div class="kanban-column scrollbar position-relative bg-transparent">
                      <div class="d-flex h-100 flex-center fw-bold hover-bg-100"><a class="text-decoration-none stretched-link text-800" href="#!">
                          <div class="circle-btn bg-200 mx-auto"><span class="fas fa-plus" data-fa-transform="shrink-2"></span></div><span>Add another list</span>
                        </a></div>
                    </div>
                  </div>
                  <div class="phoenix-offcanvas phoenix-offcanvas-end bg-100 position-fixed" tabindex="-1" id="offcanvasKanban" style="max-width: 445px">
                    <div class="offcanvas-header">
                      <h3 class="offcanvas-title">Phoenix Kanban</h3>
                      <button class="btn p-1 fw-bolder" type="button" data-phoenix-dismiss="offcanvas" aria-label="Close"><span class="fas fa-times fs-0"> </span></button>
                    </div>
                    <div class="offcanvas-body">
                      <h4 class="text-1000 fw-semi-bold mb-0 mt-6">Admins </h4>
                      <div class="d-flex align-items-center mt-3">
                        <div class="dropdown"><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-xl  me-3 border border-white rounded-pill">
                              <img class="rounded-circle " src="resources/assets/img//team/14.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img//team/14.webp" alt="" /></div>
                                  <h6 class="text-white light">Sasha Blaus</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div>
                        </div>
                        <div class="flex-1"><a class="text-decoration-none text-1000 lh-1 fw-semi-bold" href="#!">Sasha Blaus</a>
                          <h6 class="mb-0 lh-1 text-1000 fw-semi-bold">@potatogirl</h6>
                        </div>
                      </div>
                      <h4 class="text-1000 fw-semi-bold mb-0 mt-5 mb-3">Members
                      </h4>
                      <div class="d-flex">
                        <div class="dropdown"><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-m  me-2 border border-white rounded-pill">
                              <img class="rounded-circle " src="resources/assets/img//team/33.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img//team/33.webp" alt="" /></div>
                                  <h6 class="text-white light">Tyrion Lannister</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div>
                        </div>
                        <div class="dropdown"><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-m  me-2 border border-white rounded-pill">
                              <img class="rounded-circle " src="resources/assets/img//team/30.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img//team/30.webp" alt="" /></div>
                                  <h6 class="text-white light">Milind Mikuja</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div>
                        </div>
                        <div class="dropdown"><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-m  me-2 border border-white rounded-pill">
                              <img class="rounded-circle " src="resources/assets/img//team/31.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img//team/31.webp" alt="" /></div>
                                  <h6 class="text-white light">Stanly Drinkwater</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div>
                        </div>
                        <div class="dropdown"><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-m  me-2 border border-white rounded-pill">
                              <img class="rounded-circle " src="resources/assets/img//team/60.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img//team/60.webp" alt="" /></div>
                                  <h6 class="text-white light">Josef Stravinsky</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div>
                        </div>
                        <div class="dropdown"><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-m  me-2 border border-white rounded-pill">
                              <img class="rounded-circle " src="resources/assets/img//team/65.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img//team/65.webp" alt="" /></div>
                                  <h6 class="text-white light">Igor Borvibson</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div>
                        </div>
                      </div>
                      <h4 class="text-1000 fw-semi-bold mb-0 mt-3 mb-3">Guests
                      </h4>
                      <div class="d-flex">
                        <div class="dropdown"><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-m  me-2 border border-white rounded-pill">
                              <img class="rounded-circle " src="resources/assets/img//team/2.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img//team/2.webp" alt="" /></div>
                                  <h6 class="text-white light">Tyrion Lannister</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div>
                        </div>
                        <div class="dropdown"><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-m  me-2 border border-white rounded-pill">
                              <img class="rounded-circle " src="resources/assets/img//team/3.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img//team/3.webp" alt="" /></div>
                                  <h6 class="text-white light">Milind Mikuja</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div>
                        </div>
                        <div class="dropdown"><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-m  me-2 border border-white rounded-pill">
                              <img class="rounded-circle " src="resources/assets/img//team/4.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img//team/4.webp" alt="" /></div>
                                  <h6 class="text-white light">Stanly Drinkwater</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div>
                        </div>
                        <div class="dropdown"><a class="dropdown-toggle dropdown-caret-none d-inline-block" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <div class="avatar avatar-m  me-2 border border-white rounded-pill">
                              <img class="rounded-circle " src="resources/assets/img//team/5.webp" alt="" />

                            </div>
                          </a>
                          <div class="dropdown-menu avatar-dropdown-menu p-0 overflow-hidden" style="width: 320px;">
                            <div class="position-relative">
                              <div class="bg-holder z-index--1" style="background-image:url(resources/assets/img/bg/bg-32.png);background-size: auto;">
                              </div>
                              <!--/.bg-holder-->

                              <div class="p-3">
                                <div class="text-end">
                                  <button class="btn p-0 me-2"><span class="fa-solid fa-user-plus text-white light"></span></button>
                                  <button class="btn p-0"><span class="fa-solid fa-ellipsis text-white light"></span></button>
                                </div>
                                <div class="text-center">
                                  <div class="avatar avatar-xl status-online position-relative me-2 me-sm-0 me-xl-2 mb-2"><img class="rounded-circle border border-white" src="resources/assets/img//team/5.webp" alt="" /></div>
                                  <h6 class="text-white light">Josef Stravinsky</h6>
                                  <p class="text-600 fw-semi-bold fs--2 mb-2">@tyrion222</p>
                                  <div class="d-flex flex-center mb-3">
                                    <h6 class="text-white light mb-0">224 <span class="fw-normal text-300">connections</span></h6><span class="fa-solid fa-circle text-700 mx-1" data-fa-transform="shrink-10 up-2"></span>
                                    <h6 class="text-white light mb-0">23 <span class="fw-normal text-300">mutual</span></h6>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="bg-white">
                              <div class="p-3 border-bottom">
                                <div class="d-flex justify-content-between">
                                  <div class="d-flex">
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-phone"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg me-2"><span class="fa-solid fa-message"></span></button>
                                    <button class="btn btn-phoenix-secondary btn-icon btn-icon-lg"><span class="fa-solid fa-video"></span></button>
                                  </div>
                                  <button class="btn btn-phoenix-primary"><span class="fa-solid fa-envelope me-2"></span>Send Email</button>
                                </div>
                              </div>
                              <ul class="nav d-flex flex-column py-3 border-bottom">
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900 d-inline-block" data-feather="clipboard"></span><span class="text-1000 flex-1">Assigned Projects</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                                <li class="nav-item"><a class="nav-link px-3 d-flex flex-between-center" href="#!"> <span class="me-2 text-900" data-feather="pie-chart"></span><span class="text-1000 flex-1">View activiy</span><span class="fa-solid fa-chevron-right fs--3"></span></a></li>
                              </ul>
                            </div>
                            <div class="p-3 d-flex justify-content-between"><a class="btn btn-link p-0 text-decoration-none" href="#!">Details </a><a class="btn btn-link p-0 text-decoration-none text-danger" href="#!">Unassign </a></div>
                          </div>
                        </div>
                      </div>
                      <h4 class="text-1000 fw-semi-bold mb-0 mt-7 mb-3 border-bottom pb-3">Description <span class="fas fa-pencil text-900 fs--1 ms-3 cursor-pointer" data-fa-transform="up-2"></span></h4>
                      <p>Phoenix is a rich and complex symbol that continues to capture the imagination of people across cultures and time periods. Whether seen as a symbol of hope, renewal, or mystery, the Phoenix remains an enduring icon of the human spirit.</p>
                      <ul class="list-unstyled mb-0">
                        <li><a class="text-1000 fw-semi-bold text-decoration-none d-flex flex-between-center py-3  border-bottom" href="#!"><span>Board Setting</span><span class="fas fa-angle-right fs--1 me-3"></span></a></li>
                        <li><a class="text-1000 fw-semi-bold text-decoration-none d-flex flex-between-center py-3  border-bottom" href="#!"><span>Duplicate Board</span><span class="fas fa-angle-right fs--1 me-3"></span></a></li>
                        <li><a class="text-1000 fw-semi-bold text-decoration-none d-flex flex-between-center py-3  border-bottom" href="#!"><span>Manage Labels</span><span class="fas fa-angle-right fs--1 me-3"></span></a></li>
                        <li><a class="text-1000 fw-semi-bold text-decoration-none d-flex flex-between-center py-3  border-bottom" href="#!"><span>Go to Archive</span><span class="fas fa-angle-right fs--1 me-3"></span></a></li>
                        <li><a class="text-1000 fw-semi-bold text-decoration-none d-flex flex-between-center py-3  border-bottom" href="#!"><span>Print</span><span class="fas fa-angle-right fs--1 me-3"></span></a></li>
                        <li><a class="text-1000 fw-semi-bold text-decoration-none d-flex flex-between-center py-3  border-bottom" href="#!"><span>Export As</span><span class="fas fa-angle-right fs--1 me-3"></span></a></li>
                        <li><a class="text-1000 fw-semi-bold text-decoration-none d-flex flex-between-center py-3  border-bottom" href="#!"><span>Integrations</span><span class="fas fa-angle-right fs--1 me-3"></span></a></li>
                        <li><a class="text-1000 fw-semi-bold text-decoration-none d-flex flex-between-center py-3  border-bottom" href="#!"><span>Privacy Settings</span><span class="fas fa-angle-right fs--1 me-3"></span></a></li>
                        <li><a class="text-1000 fw-semi-bold text-decoration-none d-flex flex-between-center py-3  border-bottom" href="#!"><span>Automation</span><span class="fas fa-angle-right fs--1 me-3"></span></a></li>
                        <li><a class="text-1000 fw-semi-bold text-decoration-none d-flex flex-between-center py-3 text-danger pb-0 pb-0" href="#!"><span>Leave Board</span></a></li>
                      </ul>
                    </div>
                  </div>


          </div>

          <jsp:include page="demoWidget.jsp"></jsp:include>

    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->

    <jsp:include page="customize.jsp"></jsp:include>
  </body>
</html>