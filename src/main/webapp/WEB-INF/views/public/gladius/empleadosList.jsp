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

      <div class="modal fade" id="searchBoxModal" tabindex="-1" aria-hidden="true" data-bs-backdrop="true" data-phoenix-modal="data-phoenix-modal" style="--phoenix-backdrop-opacity: 1;">
        <div class="modal-dialog">
          <div class="modal-content mt-15 rounded-pill">
            <div class="modal-body p-0">
              <div class="search-box navbar-top-search-box" data-list='{"valueNames":["title"]}' style="width: auto;">
                <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                  <input class="form-control search-input fuzzy-search rounded-pill form-control-lg" type="search" placeholder="Search..." aria-label="Search" />
                  <span class="fas fa-search search-box-icon"></span>

                </form>
                <div class="btn-close position-absolute end-0 top-50 translate-middle cursor-pointer shadow-none" data-bs-dismiss="search">
                  <button class="btn btn-link btn-close-falcon p-0" aria-label="Close"></button>
                </div>
                <div class="dropdown-menu border border-300 font-base start-0 py-0 overflow-hidden w-100">
                  <div class="scrollbar-overlay" style="max-height: 30rem;">
                    <div class="list pb-3">
                      <h6 class="dropdown-header text-1000 fs--2 py-2">24 <span class="text-500">results</span></h6>
                      <hr class="text-200 my-0" />
                      <h6 class="dropdown-header text-1000 fs--1 border-bottom border-200 py-2 lh-sm">Recently Searched </h6>
                      <div class="py-2"><a class="dropdown-item" href="../../../apps/e-commerce/landing/product-details.html">
                          <div class="d-flex align-items-center">

                            <div class="fw-normal text-1000 title"><span class="fa-solid fa-clock-rotate-left" data-fa-transform="shrink-2"></span> Store Macbook</div>
                          </div>
                        </a>
                        <a class="dropdown-item" href="../../../apps/e-commerce/landing/product-details.html">
                          <div class="d-flex align-items-center">

                            <div class="fw-normal text-1000 title"> <span class="fa-solid fa-clock-rotate-left" data-fa-transform="shrink-2"></span> MacBook Air - 13″</div>
                          </div>
                        </a>

                      </div>
                      <hr class="text-200 my-0" />
                      <h6 class="dropdown-header text-1000 fs--1 border-bottom border-200 py-2 lh-sm">Products</h6>
                      <div class="py-2"><a class="dropdown-item py-2 d-flex align-items-center" href="../../../apps/e-commerce/landing/product-details.html">
                          <div class="file-thumbnail me-2"><img class="h-100 w-100 fit-cover rounded-3" src="resources/assets/img/products/60x60/3.png" alt="" /></div>
                          <div class="flex-1">
                            <h6 class="mb-0 text-1000 title">MacBook Air - 13″</h6>
                            <p class="fs--2 mb-0 d-flex text-700"><span class="fw-medium text-600">8GB Memory - 1.6GHz - 128GB Storage</span></p>
                          </div>
                        </a>
                        <a class="dropdown-item py-2 d-flex align-items-center" href="../../../apps/e-commerce/landing/product-details.html">
                          <div class="file-thumbnail me-2"><img class="img-fluid" src="resources/assets/img/products/60x60/3.png" alt="" /></div>
                          <div class="flex-1">
                            <h6 class="mb-0 text-1000 title">MacBook Pro - 13″</h6>
                            <p class="fs--2 mb-0 d-flex text-700"><span class="fw-medium text-600 ms-2">30 Sep at 12:30 PM</span></p>
                          </div>
                        </a>

                      </div>
                      <hr class="text-200 my-0" />
                      <h6 class="dropdown-header text-1000 fs--1 border-bottom border-200 py-2 lh-sm">Quick Links</h6>
                      <div class="py-2"><a class="dropdown-item" href="../../../apps/e-commerce/landing/product-details.html">
                          <div class="d-flex align-items-center">

                            <div class="fw-normal text-1000 title"><span class="fa-solid fa-link text-900" data-fa-transform="shrink-2"></span> Support MacBook House</div>
                          </div>
                        </a>
                        <a class="dropdown-item" href="../../../apps/e-commerce/landing/product-details.html">
                          <div class="d-flex align-items-center">

                            <div class="fw-normal text-1000 title"> <span class="fa-solid fa-link text-900" data-fa-transform="shrink-2"></span> Store MacBook″</div>
                          </div>
                        </a>

                      </div>
                      <hr class="text-200 my-0" />
                      <h6 class="dropdown-header text-1000 fs--1 border-bottom border-200 py-2 lh-sm">Files</h6>
                      <div class="py-2"><a class="dropdown-item" href="../../../apps/e-commerce/landing/product-details.html">
                          <div class="d-flex align-items-center">

                            <div class="fw-normal text-1000 title"><span class="fa-solid fa-file-zipper text-900" data-fa-transform="shrink-2"></span> Library MacBook folder.rar</div>
                          </div>
                        </a>
                        <a class="dropdown-item" href="../../../apps/e-commerce/landing/product-details.html">
                          <div class="d-flex align-items-center">

                            <div class="fw-normal text-1000 title"> <span class="fa-solid fa-file-lines text-900" data-fa-transform="shrink-2"></span> Feature MacBook extensions.txt</div>
                          </div>
                        </a>
                        <a class="dropdown-item" href="../../../apps/e-commerce/landing/product-details.html">
                          <div class="d-flex align-items-center">

                            <div class="fw-normal text-1000 title"> <span class="fa-solid fa-image text-900" data-fa-transform="shrink-2"></span> MacBook Pro_13.jpg</div>
                          </div>
                        </a>

                      </div>
                      <hr class="text-200 my-0" />
                      <h6 class="dropdown-header text-1000 fs--1 border-bottom border-200 py-2 lh-sm">Members</h6>
                      <div class="py-2"><a class="dropdown-item py-2 d-flex align-items-center" href="../../../pages/members.html">
                          <div class="avatar avatar-l status-online  me-2 text-900">
                            <img class="rounded-circle " src="resources/assets/img/team/40x40/10.webp" alt="" />

                          </div>
                          <div class="flex-1">
                            <h6 class="mb-0 text-1000 title">Carry Anna</h6>
                            <p class="fs--2 mb-0 d-flex text-700">anna@technext.it</p>
                          </div>
                        </a>
                        <a class="dropdown-item py-2 d-flex align-items-center" href="../../../pages/members.html">
                          <div class="avatar avatar-l  me-2 text-900">
                            <img class="rounded-circle " src="resources/assets/img/team/40x40/12.webp" alt="" />

                          </div>
                          <div class="flex-1">
                            <h6 class="mb-0 text-1000 title">John Smith</h6>
                            <p class="fs--2 mb-0 d-flex text-700">smith@technext.it</p>
                          </div>
                        </a>

                      </div>
                      <hr class="text-200 my-0" />
                      <h6 class="dropdown-header text-1000 fs--1 border-bottom border-200 py-2 lh-sm">Related Searches</h6>
                      <div class="py-2"><a class="dropdown-item" href="../../../apps/e-commerce/landing/product-details.html">
                          <div class="d-flex align-items-center">

                            <div class="fw-normal text-1000 title"><span class="fa-brands fa-firefox-browser text-900" data-fa-transform="shrink-2"></span> Search in the Web MacBook</div>
                          </div>
                        </a>
                        <a class="dropdown-item" href="../../../apps/e-commerce/landing/product-details.html">
                          <div class="d-flex align-items-center">

                            <div class="fw-normal text-1000 title"> <span class="fa-brands fa-chrome text-900" data-fa-transform="shrink-2"></span> Store MacBook″</div>
                          </div>
                        </a>

                      </div>
                    </div>
                    <div class="text-center">
                      <p class="fallback fw-bold fs-1 d-none">No Result Found.</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="content">
        <nav class="mb-2" aria-label="breadcrumb">
          <ol class="breadcrumb mb-0">
            <li class="breadcrumb-item"><a href="#!">Page 1</a></li>
            <li class="breadcrumb-item"><a href="#!">Page 2</a></li>
            <li class="breadcrumb-item active">Default</li>
          </ol>
        </nav>
        <div class="mb-9">
          <div class="row g-3 mb-4">
            <div class="col-auto">
              <h2 class="mb-0">Orders</h2>
            </div>
          </div>
          <ul class="nav nav-links mb-3 mb-lg-2 mx-n3">
            <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">All <span class="text-700 fw-semi-bold">(68817)</span></a></li>
            <li class="nav-item"><a class="nav-link" href="#">Pending payment <span class="text-700 fw-semi-bold">(6)</span></a></li>
            <li class="nav-item"><a class="nav-link" href="#">Unfulfilled <span class="text-700 fw-semi-bold">(17)</span></a></li>
            <li class="nav-item"><a class="nav-link" href="#">Completed<span class="text-700 fw-semi-bold">(6,810)</span></a></li>
            <li class="nav-item"><a class="nav-link" href="#">Refunded<span class="text-700 fw-semi-bold">(8)</span></a></li>
            <li class="nav-item"><a class="nav-link" href="#">Failed<span class="text-700 fw-semi-bold">(2)</span></a></li>
          </ul>
          <div id="orderTable" data-list='{"valueNames":["order","total","customer","payment_status","fulfilment_status","delivery_type","date"],"page":10,"pagination":true}'>
            <div class="mb-4">
              <div class="row g-3">
                <div class="col-auto">
                  <div class="search-box">
                    <form class="position-relative" data-bs-toggle="search" data-bs-display="static">
                      <input class="form-control search-input search" type="search" placeholder="Search orders" aria-label="Search" />
                      <span class="fas fa-search search-box-icon"></span>

                    </form>
                  </div>
                </div>
                <div class="col-auto scrollbar overflow-hidden-y flex-grow-1">
                  <div class="btn-group position-static" role="group">
                    <div class="btn-group position-static text-nowrap" role="group">
                      <button class="btn btn-phoenix-secondary px-7 flex-shrink-0" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                        Payment status<span class="fas fa-angle-down ms-2"></span></button>
                      <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                        <li>
                          <hr class="dropdown-divider" />
                        </li>
                        <li><a class="dropdown-item" href="#">Separated link</a></li>
                      </ul>
                    </div>
                    <div class="btn-group position-static text-nowrap" role="group">
                      <button class="btn btn-sm btn-phoenix-secondary px-7 flex-shrink-0" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent">
                        Fulfilment status<span class="fas fa-angle-down ms-2"></span></button>
                      <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                        <li>
                          <hr class="dropdown-divider" />
                        </li>
                        <li><a class="dropdown-item" href="#">Separated link</a></li>
                      </ul>
                    </div>
                    <button class="btn btn-sm btn-phoenix-secondary px-7 flex-shrink-0">More filters </button>
                  </div>
                </div>
                <div class="col-auto">
                  <button class="btn btn-link text-900 me-4 px-0"><span class="fa-solid fa-file-export fs--1 me-2"></span>Export</button>
                  <button class="btn btn-primary"><span class="fas fa-plus me-2"></span>Add order</button>
                </div>
              </div>
            </div>
            <div class="mx-n4 px-4 mx-lg-n6 px-lg-6 bg-white border-top border-bottom border-200 position-relative top-1">
              <div class="table-responsive scrollbar mx-n1 px-1">
                <table class="table table-sm fs--1 mb-0">
                  <thead>
                    <tr >
                      <th class="white-space-nowrap fs--1 align-middle ps-0" style="width:26px;">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" id="checkbox-bulk-order-select" type="checkbox" data-bulk-select='{"body":"order-table-body"}' />
                        </div>
                      </th>
                      <th class="sort white-space-nowrap align-middle pe-3" scope="col" data-sort="order" style="width:5%;">ORDER</th>
                      <th class="sort align-middle text-end" scope="col" data-sort="total" style="width:6%;">TOTAL</th>
                      <th class="sort align-middle ps-8" scope="col" data-sort="customer" style="width:28%; min-width: 250px;">CUSTOMER</th>
                      <th class="sort align-middle pe-3" scope="col" data-sort="payment_status" style="width:10%;">PAYMENT STATUS</th>
                      <th class="sort align-middle text-start pe-3" scope="col" data-sort="fulfilment_status" style="width:12%; min-width: 200px;">FULFILMENT STATUS</th>
                      <th class="sort align-middle text-start" scope="col" data-sort="delivery_type" style="width:30%;">DELIVERY TYPE</th>
                      <th class="sort align-middle text-end pe-0" scope="col" data-sort="date">DATE</th>
                    </tr>
                  </thead>
                  <tbody class="list" id="order-table-body">
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2453,"total":87,"customer":{"avatar":"/team/32.webp","name":"Carry Anna"},"payment_status":{"label":"Complete","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Dec 12, 12:56 PM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2453</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$87</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m"><img class="rounded-circle" src="resources/assets/img/team/32.webp" alt="" />
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Carry Anna</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Complete</span><span class="ms-1" data-feather="check" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-secondary"><span class="badge-label">Cancelled</span><span class="ms-1" data-feather="x" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Cash on delivery</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Dec 12, 12:56 PM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2452,"total":7264,"customer":{"avatar":"","name":"Milind Mikuja"},"fulfilment_status":{"label":"Ready to pickup","type":"badge-phoenix-info","icon":"info"},"payment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Free shipping","date":"Dec 9, 2:28PM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2452</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$7264</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m">
                            <div class="avatar-name rounded-circle"><span>M</span></div>
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Milind Mikuja</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-secondary"><span class="badge-label">Cancelled</span><span class="ms-1" data-feather="x" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-info"><span class="badge-label">Ready to pickup</span><span class="ms-1" data-feather="info" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Free shipping</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Dec 9, 2:28PM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2451,"total":375,"customer":{"avatar":"/team/35.webp","name":"Stanly Drinkwater"},"payment_status":{"label":"Pending","type":"badge-phoenix-warning","icon":"clock"},"fulfilment_status":{"label":"Completed","type":"badge-phoenix-success","icon":"check"},"delivery_type":"Local pickup","date":"Dec 4, 12:56 PM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2451</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$375</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m"><img class="rounded-circle" src="resources/assets/img/team/35.webp" alt="" />
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Stanly Drinkwater</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-warning"><span class="badge-label">Pending</span><span class="ms-1" data-feather="clock" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Completed</span><span class="ms-1" data-feather="check" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Local pickup</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Dec 4, 12:56 PM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2450,"total":657,"customer":{"avatar":"/team/57.webp","name":"Josef Stravinsky"},"payment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"fulfilment_status":{"label":"Partially Fulfiled","type":"badge-phoenix-warning","icon":"clock"},"delivery_type":"Standard shipping","date":"Dec 1, 4:07 AM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2450</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$657</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m"><img class="rounded-circle" src="resources/assets/img/team/57.webp" alt="" />
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Josef Stravinsky</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-secondary"><span class="badge-label">Cancelled</span><span class="ms-1" data-feather="x" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-warning"><span class="badge-label">Partially Fulfiled</span><span class="ms-1" data-feather="clock" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Standard shipping</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Dec 1, 4:07 AM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2449,"total":9562,"customer":{"avatar":"/team/58.webp","name":"Igor Borvibson"},"payment_status":{"label":"Failed","type":"badge-phoenix-danger","icon":"x"},"fulfilment_status":{"label":"Partially Fulfiled","type":"badge-phoenix-success","icon":"check"},"delivery_type":"Express","date":"Nov 28, 7:28 PM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2449</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$9562</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m"><img class="rounded-circle" src="resources/assets/img/team/58.webp" alt="" />
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Igor Borvibson</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span class="badge-label">Failed</span><span class="ms-1" data-feather="x" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Partially Fulfiled</span><span class="ms-1" data-feather="check" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Express</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Nov 28, 7:28 PM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2448,"total":46,"customer":{"avatar":"/team/59.webp","name":"Katerina Karenin"},"payment_status":{"label":"Paid","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Unfulfiled","type":"badge-phoenix-danger","icon":"x"},"delivery_type":"Local delivery","date":"Nov 24, 10:16 AM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2448</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$46</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m"><img class="rounded-circle" src="resources/assets/img/team/59.webp" alt="" />
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Katerina Karenin</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Paid</span><span class="ms-1" data-feather="check" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span class="badge-label">Unfulfiled</span><span class="ms-1" data-feather="x" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Local delivery</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Nov 24, 10:16 AM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2447,"total":953,"customer":{"avatar":"","name":"Roy Anderson"},"payment_status":{"label":"Pending","type":"badge-phoenix-warning","icon":"clock"},"fulfilment_status":{"label":"Fulfiled","type":"badge-phoenix-success","icon":"check"},"delivery_type":"Cash on delivery","date":"Nov 18, 5:43 PM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2447</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$953</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m">
                            <div class="avatar-name rounded-circle"><span>R</span></div>
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Roy Anderson</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-warning"><span class="badge-label">Pending</span><span class="ms-1" data-feather="clock" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Fulfiled</span><span class="ms-1" data-feather="check" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Cash on delivery</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Nov 18, 5:43 PM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2446,"total":12,"customer":{"avatar":"/team/31.webp","name":"Martina scorcese"},"payment_status":{"label":"Pending","type":"badge-phoenix-warning","icon":"clock"},"fulfilment_status":{"label":"Fulfiled","type":"badge-phoenix-success","icon":"check"},"delivery_type":"Standard shipping","date":"Nov 18, 2:09 AM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2446</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$12</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m"><img class="rounded-circle" src="resources/assets/img/team/31.webp" alt="" />
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Martina scorcese</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-warning"><span class="badge-label">Pending</span><span class="ms-1" data-feather="clock" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Fulfiled</span><span class="ms-1" data-feather="check" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Standard shipping</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Nov 18, 2:09 AM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2445,"total":3927,"customer":{"avatar":"/team/33.webp","name":"Luis Bunuel"},"payment_status":{"label":"Paid","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Canceled","type":"badge-phoenix-secondary","icon":"x"},"delivery_type":"Cash on delivery","date":"Nov 16, 3:22 PM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2445</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$3927</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m"><img class="rounded-circle" src="resources/assets/img/team/33.webp" alt="" />
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Luis Bunuel</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Paid</span><span class="ms-1" data-feather="check" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-secondary"><span class="badge-label">Canceled</span><span class="ms-1" data-feather="x" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Cash on delivery</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Nov 16, 3:22 PM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2444,"total":5937,"customer":{"avatar":"/team/34.webp","name":"Jean Renoir"},"payment_status":{"label":"Paid","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Ready to pickup","type":"badge-phoenix-secondary","icon":"info"},"delivery_type":"Local pickup","date":"Nov 09, 8:49 AM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2444</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$5937</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m"><img class="rounded-circle" src="resources/assets/img/team/34.webp" alt="" />
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Jean Renoir</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Paid</span><span class="ms-1" data-feather="check" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-secondary"><span class="badge-label">Ready to pickup</span><span class="ms-1" data-feather="info" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Local pickup</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Nov 09, 8:49 AM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2443,"total":124,"customer":{"avatar":"/team/29.webp","name":"Ricky Antony"},"payment_status":{"label":"Failed","type":"badge-phoenix-danger","icon":"x"},"fulfilment_status":{"label":"Unfulfiled","type":"badge-phoenix-danger","icon":"x"},"delivery_type":"Cash on delivery","date":"Nov 05, 4:35 PM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2443</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$124</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m"><img class="rounded-circle" src="resources/assets/img/team/29.webp" alt="" />
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Ricky Antony</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span class="badge-label">Failed</span><span class="ms-1" data-feather="x" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span class="badge-label">Unfulfiled</span><span class="ms-1" data-feather="x" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Cash on delivery</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Nov 05, 4:35 PM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2442,"total":542,"customer":{"avatar":"/team/3.webp","name":"Emma Watson"},"payment_status":{"label":"Pending","type":"badge-phoenix-warning","icon":"clock"},"fulfilment_status":{"label":"Fulfiled","type":"badge-phoenix-success","icon":"check"},"delivery_type":"Standard shipping","date":"Nov 05, 12:00 PM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2442</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$542</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m"><img class="rounded-circle" src="../../../assets/img/team/3.webp" alt="" />
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Emma Watson</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-warning"><span class="badge-label">Pending</span><span class="ms-1" data-feather="clock" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Fulfiled</span><span class="ms-1" data-feather="check" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Standard shipping</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Nov 05, 12:00 PM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2441,"total":1480,"customer":{"avatar":"/team/avatar.webp","name":"Jennifer Schramm","placeholder":true},"payment_status":{"label":"Paid","type":"badge-phoenix-success","icon":"check"},"fulfilment_status":{"label":"Ready to pickup","type":"badge-phoenix-info","icon":"info"},"delivery_type":"Local delivery","date":"Nov 02, 2:00 AM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2441</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$1480</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m"><img class="rounded-circle avatar-placeholder" src="../../../assets/img/team/avatar.webp" alt="" />
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Jennifer Schramm</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Paid</span><span class="ms-1" data-feather="check" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-info"><span class="badge-label">Ready to pickup</span><span class="ms-1" data-feather="info" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Local delivery</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Nov 02, 2:00 AM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2440,"total":80,"customer":{"avatar":"/team/25.webp","name":"Michael Jenkins"},"payment_status":{"label":"Cancelled","type":"badge-phoenix-secondary","icon":"x"},"fulfilment_status":{"label":"Unfulfiled","type":"badge-phoenix-danger","icon":"x"},"delivery_type":"Free shipping","date":"Oct 30, 4:25 PM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2440</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$80</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m"><img class="rounded-circle" src="../../../assets/img/team/25.webp" alt="" />
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Michael Jenkins</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-secondary"><span class="badge-label">Cancelled</span><span class="ms-1" data-feather="x" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-danger"><span class="badge-label">Unfulfiled</span><span class="ms-1" data-feather="x" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Free shipping</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Oct 30, 4:25 PM</td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                      <td class="fs--1 align-middle px-0 py-3">
                        <div class="form-check mb-0 fs-0">
                          <input class="form-check-input" type="checkbox" data-bulk-select-row='{"order":2439,"total":999,"customer":{"avatar":"/team/32.webp","name":"Raymond Mims"},"payment_status":{"label":"Pending","type":"badge-phoenix-warning","icon":"clock"},"fulfilment_status":{"label":"Fulfiled","type":"badge-phoenix-success","icon":"check"},"delivery_type":"Cash on delivery","date":"Oct 28, 3:00 PM"}' />
                        </div>
                      </td>
                      <td class="order align-middle white-space-nowrap py-0"><a class="fw-semi-bold" href="#!">#2439</a></td>
                      <td class="total align-middle text-end fw-semi-bold text-1000">$999</td>
                      <td class="customer align-middle white-space-nowrap ps-8"><a class="d-flex align-items-center" href="#!">
                          <div class="avatar avatar-m"><img class="rounded-circle" src="../../../assets/img/team/32.webp" alt="" />
                          </div>
                          <h6 class="mb-0 ms-3 text-900">Raymond Mims</h6>
                        </a></td>
                      <td class="payment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-warning"><span class="badge-label">Pending</span><span class="ms-1" data-feather="clock" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="fulfilment_status align-middle white-space-nowrap text-start fw-bold text-700"><span class="badge badge-phoenix fs--2 badge-phoenix-success"><span class="badge-label">Fulfiled</span><span class="ms-1" data-feather="check" style="height:12.8px;width:12.8px;"></span></span></td>
                      <td class="delivery_type align-middle white-space-nowrap text-900 fs--1 text-start">Cash on delivery</td>
                      <td class="date align-middle white-space-nowrap text-700 fs--1 ps-4 text-end">Oct 28, 3:00 PM</td>
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
      <script>
        var navbarTopStyle = window.config.config.phoenixNavbarTopStyle;
        var navbarTop = document.querySelector('.navbar-top');
        if (navbarTopStyle === 'darker') {
          navbarTop.classList.add('navbar-darker');
        }

        var navbarVerticalStyle = window.config.config.phoenixNavbarVerticalStyle;
        var navbarVertical = document.querySelector('.navbar-vertical');
        if (navbarVertical && navbarVerticalStyle === 'darker') {
          navbarVertical.classList.add('navbar-darker');
        }
      </script>
      <div class="support-chat-container">
        <div class="container-fluid support-chat">
          <div class="card bg-white">
            <div class="card-header d-flex flex-between-center px-4 py-3 border-bottom">
              <h5 class="mb-0 d-flex align-items-center gap-2">Demo widget<span class="fa-solid fa-circle text-success fs--3"></span></h5>
              <div class="btn-reveal-trigger">
                <button class="btn btn-link p-0 dropdown-toggle dropdown-caret-none transition-none d-flex" type="button" id="support-chat-dropdown" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h text-900"></span></button>
                <div class="dropdown-menu dropdown-menu-end py-2" aria-labelledby="support-chat-dropdown"><a class="dropdown-item" href="#!">Request a callback</a><a class="dropdown-item" href="#!">Search in chat</a><a class="dropdown-item" href="#!">Show history</a><a class="dropdown-item" href="#!">Report to Admin</a><a class="dropdown-item btn-support-chat" href="#!">Close Support</a></div>
              </div>
            </div>
            <div class="card-body chat p-0">
              <div class="d-flex flex-column-reverse scrollbar h-100 p-3">
                <div class="text-end mt-6"><a class="mb-2 d-inline-flex align-items-center text-decoration-none text-1100 hover-bg-soft rounded-pill border border-primary py-2 ps-4 pe-3" href="#!">
                    <p class="mb-0 fw-semi-bold fs--1">I need help with something</p><span class="fa-solid fa-paper-plane text-primary fs--1 ms-3"></span>
                  </a><a class="mb-2 d-inline-flex align-items-center text-decoration-none text-1100 hover-bg-soft rounded-pill border border-primary py-2 ps-4 pe-3" href="#!">
                    <p class="mb-0 fw-semi-bold fs--1">I can’t reorder a product I previously ordered</p><span class="fa-solid fa-paper-plane text-primary fs--1 ms-3"></span>
                  </a><a class="mb-2 d-inline-flex align-items-center text-decoration-none text-1100 hover-bg-soft rounded-pill border border-primary py-2 ps-4 pe-3" href="#!">
                    <p class="mb-0 fw-semi-bold fs--1">How do I place an order?</p><span class="fa-solid fa-paper-plane text-primary fs--1 ms-3"></span>
                  </a><a class="false d-inline-flex align-items-center text-decoration-none text-1100 hover-bg-soft rounded-pill border border-primary py-2 ps-4 pe-3" href="#!">
                    <p class="mb-0 fw-semi-bold fs--1">My payment method not working</p><span class="fa-solid fa-paper-plane text-primary fs--1 ms-3"></span>
                  </a>
                </div>
                <div class="text-center mt-auto">
                  <div class="avatar avatar-3xl status-online"><img class="rounded-circle border border-3 border-white" src="../../../assets/img/team/30.webp" alt="" /></div>
                  <h5 class="mt-2 mb-3">Eric</h5>
                  <p class="text-center text-black mb-0">Ask us anything – we’ll get back to you here or by email within 24 hours.</p>
                </div>
              </div>
            </div>
            <div class="card-footer d-flex align-items-center gap-2 border-top ps-3 pe-4 py-3">
              <div class="d-flex align-items-center flex-1 gap-3 border rounded-pill px-4">
                <input class="form-control outline-none border-0 flex-1 fs--1 px-0" type="text" placeholder="Write message" />
                <label class="btn btn-link d-flex p-0 text-500 fs--1 border-0" for="supportChatPhotos"><span class="fa-solid fa-image"></span></label>
                <input class="d-none" type="file" accept="image/*" id="supportChatPhotos" />
                <label class="btn btn-link d-flex p-0 text-500 fs--1 border-0" for="supportChatAttachment"> <span class="fa-solid fa-paperclip"></span></label>
                <input class="d-none" type="file" id="supportChatAttachment" />
              </div>
              <button class="btn p-0 border-0 send-btn"><span class="fa-solid fa-paper-plane fs--1"></span></button>
            </div>
          </div>
        </div>
        <button class="btn p-0 border border-200 btn-support-chat"><span class="fs-0 btn-text text-primary text-nowrap">Chat demo</span><span class="fa-solid fa-circle text-success fs--1 ms-2"></span><span class="fa-solid fa-chevron-down text-primary fs-1"></span></button>
      </div>
    </main>
    <!--===============================================-->
    <!--    End of Main Content-->
    <!--===============================================-->


    <div class="offcanvas offcanvas-end settings-panel border-0" id="settings-offcanvas" tabindex="-1" aria-labelledby="settings-offcanvas">
      <div class="offcanvas-header align-items-start border-bottom flex-column">
        <div class="pt-1 w-100 mb-6 d-flex justify-content-between align-items-start">
          <div>
            <h5 class="mb-2 me-2 lh-sm"><span class="fas fa-palette me-2 fs-0"></span>Theme Customizer</h5>
            <p class="mb-0 fs--1">Explore different styles according to your preferences</p>
          </div>
          <button class="btn p-1 fw-bolder" type="button" data-bs-dismiss="offcanvas" aria-label="Close"><span class="fas fa-times fs-0"> </span></button>
        </div>
        <button class="btn btn-phoenix-secondary w-100" data-theme-control="reset"><span class="fas fa-arrows-rotate me-2 fs--2"></span>Reset to default</button>
      </div>
      <div class="offcanvas-body scrollbar px-card" id="themeController">
        <div class="setting-panel-item mt-0">
          <h5 class="setting-panel-item-title">Color Scheme</h5>
          <div class="row gx-2">
            <div class="col-6">
              <input class="btn-check" id="themeSwitcherLight" name="theme-color" type="radio" value="light" data-theme-control="phoenixTheme" />
              <label class="btn d-inline-block btn-navbar-style fs--1" for="themeSwitcherLight"> <span class="mb-2 rounded d-block"><img class="img-fluid img-prototype mb-0" src="resources/assets/img/generic/default-light.png" alt=""/></span><span class="label-text">Light</span></label>
            </div>
            <div class="col-6">
              <input class="btn-check" id="themeSwitcherDark" name="theme-color" type="radio" value="dark" data-theme-control="phoenixTheme" />
              <label class="btn d-inline-block btn-navbar-style fs--1" for="themeSwitcherDark"> <span class="mb-2 rounded d-block"><img class="img-fluid img-prototype mb-0" src="resources/assets/img/generic/default-dark.png" alt=""/></span><span class="label-text"> Dark</span></label>
            </div>
          </div>
        </div>
        <div class="border rounded-3 p-4 setting-panel-item bg-white">
          <div class="d-flex justify-content-between align-items-center">
            <h5 class="setting-panel-item-title mb-1">RTL </h5>
            <div class="form-check form-switch mb-0">
              <input class="form-check-input ms-auto" type="checkbox" data-theme-control="phoenixIsRTL" />
            </div>
          </div>
          <p class="mb-0 text-700">Change text direction</p>
        </div>
        <div class="border rounded-3 p-4 setting-panel-item bg-white">
          <div class="d-flex justify-content-between align-items-center">
            <h5 class="setting-panel-item-title mb-1">Support Chat </h5>
            <div class="form-check form-switch mb-0">
              <input class="form-check-input ms-auto" type="checkbox" data-theme-control="phoenixSupportChat" />
            </div>
          </div>
          <p class="mb-0 text-700">Toggle support chat</p>
        </div>
        <div class="setting-panel-item">
          <h5 class="setting-panel-item-title">Navigation Type</h5>
          <div class="row gx-2">
            <div class="col-6">
              <input class="btn-check" id="navbarPositionVertical" name="navigation-type" type="radio" value="vertical" data-theme-control="phoenixNavbarPosition" data-page-url="../../../documentation/layouts/vertical-navbar.html" />
              <label class="btn d-inline-block btn-navbar-style fs--1" for="navbarPositionVertical"> <span class="mb-2 rounded d-block"><img class="img-fluid img-prototype d-dark-none" src="resources/assets/img/generic/default-light.png" alt=""/><img class="img-fluid img-prototype d-light-none" src="../../../assets/img/generic/default-dark.png" alt=""/></span><span class="label-text">Vertical</span></label>
            </div>
            <div class="col-6">
              <input class="btn-check" id="navbarPositionHorizontal" name="navigation-type" type="radio" value="horizontal" data-theme-control="phoenixNavbarPosition" data-page-url="../../../documentation/layouts/horizontal-navbar.html" />
              <label class="btn d-inline-block btn-navbar-style fs--1" for="navbarPositionHorizontal"> <span class="mb-2 rounded d-block"><img class="img-fluid img-prototype d-dark-none" src="resources/assets/img/generic/top-default.png" alt=""/><img class="img-fluid img-prototype d-light-none" src="../../../assets/img/generic/top-default-dark.png" alt=""/></span><span class="label-text"> Horizontal</span></label>
            </div>
            <div class="col-6">
              <input class="btn-check" id="navbarPositionCombo" name="navigation-type" type="radio" value="combo" data-theme-control="phoenixNavbarPosition" data-page-url="../../../documentation/layouts/combo-navbar.html" />
              <label class="btn d-inline-block btn-navbar-style fs--1" for="navbarPositionCombo"> <span class="mb-2 rounded d-block"><img class="img-fluid img-prototype d-dark-none" src="resources/assets/img/generic/nav-combo-light.png" alt=""/><img class="img-fluid img-prototype d-light-none" src="resources/assets/img/generic/nav-combo-dark.png" alt=""/></span><span class="label-text"> Combo</span></label>
            </div>
            <div class="col-6">
              <input class="btn-check" id="navbarPositionTopDouble" name="navigation-type" type="radio" value="dual-nav" data-theme-control="phoenixNavbarPosition" data-page-url="../../../documentation/layouts/dual-nav.html" />
              <label class="btn d-inline-block btn-navbar-style fs--1" for="navbarPositionTopDouble"> <span class="mb-2 rounded d-block"><img class="img-fluid img-prototype d-dark-none" src="resources/assets/img/generic/dual-light.png" alt=""/><img class="img-fluid img-prototype d-light-none" src="resources/assets/img/generic/dual-dark.png" alt=""/></span><span class="label-text"> Dual nav</span></label>
            </div>
          </div>
        </div>
        <div class="setting-panel-item">
          <h5 class="setting-panel-item-title">Vertical Navbar Appearance</h5>
          <div class="row gx-2">
            <div class="col-6">
              <input class="btn-check" id="navbar-style-default" type="radio" name="config.name" value="default" data-theme-control="phoenixNavbarVerticalStyle" />
              <label class="btn d-block w-100 btn-navbar-style fs--1" for="navbar-style-default"> <img class="img-fluid img-prototype d-dark-none" src="resources/assets/img/generic/default-light.png" alt="" /><img class="img-fluid img-prototype d-light-none" src="resources/assets/img/generic/default-dark.png" alt="" /><span class="label-text d-dark-none"> Default</span><span class="label-text d-light-none">Default</span></label>
            </div>
            <div class="col-6">
              <input class="btn-check" id="navbar-style-dark" type="radio" name="config.name" value="darker" data-theme-control="phoenixNavbarVerticalStyle" />
              <label class="btn d-block w-100 btn-navbar-style fs--1" for="navbar-style-dark"> <img class="img-fluid img-prototype d-dark-none" src="resources/assets/img/generic/vertical-darker.png" alt="" /><img class="img-fluid img-prototype d-light-none" src="resources/assets/img/generic/vertical-lighter.png" alt="" /><span class="label-text d-dark-none"> Darker</span><span class="label-text d-light-none">Lighter</span></label>
            </div>
          </div>
        </div>
        <div class="setting-panel-item">
          <h5 class="setting-panel-item-title">Horizontal Navbar Shape</h5>
          <div class="row gx-2">
            <div class="col-6">
              <input class="btn-check" id="navbarShapeDefault" name="navbar-shape" type="radio" value="default" data-theme-control="phoenixNavbarTopShape" data-page-url="../../../documentation/layouts/horizontal-navbar.html" />
              <label class="btn d-inline-block btn-navbar-style fs--1" for="navbarShapeDefault"> <span class="mb-2 rounded d-block"><img class="img-fluid img-prototype d-dark-none mb-0" src="resources/assets/img/generic/top-default.png" alt=""/><img class="img-fluid img-prototype d-light-none mb-0" src="resources/assets/img/generic/top-default-dark.png" alt=""/></span><span class="label-text">Default</span></label>
            </div>
            <div class="col-6">
              <input class="btn-check" id="navbarShapeSlim" name="navbar-shape" type="radio" value="slim" data-theme-control="phoenixNavbarTopShape" data-page-url="../../../documentation/layouts/vertical-navbar.html#horizontal-navbar-slim" />
              <label class="btn d-inline-block btn-navbar-style fs--1" for="navbarShapeSlim"> <span class="mb-2 rounded d-block"><img class="img-fluid img-prototype d-dark-none mb-0" src="resources/assets/img/generic/top-slim.png" alt=""/><img class="img-fluid img-prototype d-light-none mb-0" src="resources/assets/img/generic/top-slim-dark.png" alt=""/></span><span class="label-text"> Slim</span></label>
            </div>
          </div>
        </div>
        <div class="setting-panel-item">
          <h5 class="setting-panel-item-title">Horizontal Navbar Appearance</h5>
          <div class="row gx-2">
            <div class="col-6">
              <input class="btn-check" id="navbarTopDefault" name="navbar-top-style" type="radio" value="default" data-theme-control="phoenixNavbarTopStyle" />
              <label class="btn d-inline-block btn-navbar-style fs--1" for="navbarTopDefault"> <span class="mb-2 rounded d-block"><img class="img-fluid img-prototype d-dark-none mb-0" src="resources/assets/img/generic/top-default.png" alt=""/><img class="img-fluid img-prototype d-light-none mb-0" src="resources/assets/img/generic/top-style-darker.png" alt=""/></span><span class="label-text">Default</span></label>
            </div>
            <div class="col-6">
              <input class="btn-check" id="navbarTopDarker" name="navbar-top-style" type="radio" value="darker" data-theme-control="phoenixNavbarTopStyle" />
              <label class="btn d-inline-block btn-navbar-style fs--1" for="navbarTopDarker"> <span class="mb-2 rounded d-block"><img class="img-fluid img-prototype d-dark-none mb-0" src="resources/assets/img/generic/navbar-top-style-light.png" alt=""/><img class="img-fluid img-prototype d-light-none mb-0" src="resources/assets/img/generic/top-style-lighter.png" alt=""/></span><span class="label-text d-dark-none">Darker</span><span class="label-text d-light-none">Lighter</span></label>
            </div>
          </div>
        </div><a class="bun btn-primary d-grid mb-3 text-white dark__text-100 mt-5 btn btn-primary" href="https://themes.getbootstrap.com/product/phoenix-admin-dashboard-webapp-template/" target="_blank">Purchase template</a>
      </div>
    </div><a class="card setting-toggle" href="#settings-offcanvas" data-bs-toggle="offcanvas">
      <div class="card-body d-flex align-items-center px-2 py-1">
        <div class="position-relative rounded-start" style="height:34px;width:28px">
          <div class="settings-popover"><span class="ripple"><span class="fa-spin position-absolute all-0 d-flex flex-center"><span class="icon-spin position-absolute all-0 d-flex flex-center">
                  <svg width="20" height="20" viewBox="0 0 20 20" fill="#ffffff" xmlns="http://www.w3.org/2000/svg">
                    <path d="M19.7369 12.3941L19.1989 12.1065C18.4459 11.7041 18.0843 10.8487 18.0843 9.99495C18.0843 9.14118 18.4459 8.28582 19.1989 7.88336L19.7369 7.59581C19.9474 7.47484 20.0316 7.23291 19.9474 7.03131C19.4842 5.57973 18.6843 4.28943 17.6738 3.20075C17.5053 3.03946 17.2527 2.99914 17.0422 3.12011L16.393 3.46714C15.6883 3.84379 14.8377 3.74529 14.1476 3.3427C14.0988 3.31422 14.0496 3.28621 14.0002 3.25868C13.2568 2.84453 12.7055 2.10629 12.7055 1.25525V0.70081C12.7055 0.499202 12.5371 0.297594 12.2845 0.257272C10.7266 -0.105622 9.16879 -0.0653007 7.69516 0.257272C7.44254 0.297594 7.31623 0.499202 7.31623 0.70081V1.23474C7.31623 2.09575 6.74999 2.8362 5.99824 3.25599C5.95774 3.27861 5.91747 3.30159 5.87744 3.32493C5.15643 3.74527 4.26453 3.85902 3.53534 3.45302L2.93743 3.12011C2.72691 2.99914 2.47429 3.03946 2.30587 3.20075C1.29538 4.28943 0.495411 5.57973 0.0322686 7.03131C-0.051939 7.23291 0.0322686 7.47484 0.242788 7.59581L0.784376 7.8853C1.54166 8.29007 1.92694 9.13627 1.92694 9.99495C1.92694 10.8536 1.54166 11.6998 0.784375 12.1046L0.242788 12.3941C0.0322686 12.515 -0.051939 12.757 0.0322686 12.9586C0.495411 14.4102 1.29538 15.7005 2.30587 16.7891C2.47429 16.9504 2.72691 16.9907 2.93743 16.8698L3.58669 16.5227C4.29133 16.1461 5.14131 16.2457 5.8331 16.6455C5.88713 16.6767 5.94159 16.7074 5.99648 16.7375C6.75162 17.1511 7.31623 17.8941 7.31623 18.7552V19.2891C7.31623 19.4425 7.41373 19.5959 7.55309 19.696C7.64066 19.7589 7.74815 19.7843 7.85406 19.8046C9.35884 20.0925 10.8609 20.0456 12.2845 19.7729C12.5371 19.6923 12.7055 19.4907 12.7055 19.2891V18.7346C12.7055 17.8836 13.2568 17.1454 14.0002 16.7312C14.0496 16.7037 14.0988 16.6757 14.1476 16.6472C14.8377 16.2446 15.6883 16.1461 16.393 16.5227L17.0422 16.8698C17.2527 16.9907 17.5053 16.9504 17.6738 16.7891C18.7264 15.7005 19.4842 14.4102 19.9895 12.9586C20.0316 12.757 19.9474 12.515 19.7369 12.3941ZM10.0109 13.2005C8.1162 13.2005 6.64257 11.7893 6.64257 9.97478C6.64257 8.20063 8.1162 6.74905 10.0109 6.74905C11.8634 6.74905 13.3792 8.20063 13.3792 9.97478C13.3792 11.7893 11.8634 13.2005 10.0109 13.2005Z" fill="#2A7BE4"></path>
                  </svg></span></span></span></div>
        </div><small class="text-uppercase text-700 fw-bold py-2 pe-2 ps-1 rounded-end">customize</small>
      </div>
    </a>
  </body>

</html>