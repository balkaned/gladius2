<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- ===============================================-->
<!--    Document Title-->
<!-- ===============================================-->
<title>Gladius</title>

<link rel="shortcut icon" href="resources/assets/img/icon1.png">

<!-- ===============================================-->
<!--    Favicons-->
<!-- ===============================================-->
<!--<link rel="apple-touch-icon" sizes="180x180" href="resources/assets/img/favicons/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="resources/assets/img/favicons/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="resources/assets/img/favicons/favicon-16x16.png">
<link rel="shortcut icon" type="image/x-icon" href="resources/assets/img/favicons/favicon.ico">-->
<link rel="manifest" href="resources/assets/img/favicons/manifest.json">
<meta name="msapplication-TileImage" content="resources/assets/img/favicons/mstile-150x150.png">
<meta name="theme-color" content="#ffffff">
<script src="resources/vendors/imagesloaded/imagesloaded.pkgd.min.js"></script>
<script src="resources/vendors/simplebar/simplebar.min.js"></script>
<script src="resources/assets/js/config.js"></script>

<!-- ===============================================-->
<!--    Stylesheets-->
<!-- ===============================================-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="">
<link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&amp;display=swap" rel="stylesheet">
<link href="resources/vendors/simplebar/simplebar.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
<link href="resources/assets/css/theme-rtl.min.css" type="text/css" rel="stylesheet" id="style-rtl">
<link href="resources/assets/css/theme.min.css" type="text/css" rel="stylesheet" id="style-default">
<link href="resources/assets/css/user-rtl.min.css" type="text/css" rel="stylesheet" id="user-style-rtl">
<link href="resources/assets/css/user.min.css" type="text/css" rel="stylesheet" id="user-style-default">

<link href="resources/assets/css/stylesgladius.css" type="text/css" rel="stylesheet" id="user-style-default">

<link href="resources/vendors/choices/choices.min.css" rel="stylesheet">
<link href="resources/vendors/flatpickr/flatpickr.min.css" rel="stylesheet">


<!-- ===============================================-->
<!--    JavaScripts-->
<!-- ===============================================-->
<script src="resources/vendors/popper/popper.min.js"></script>
<script src="resources/vendors/bootstrap/bootstrap.min.js"></script>
<script src="resources/vendors/anchorjs/anchor.min.js"></script>
<script src="resources/vendors/is/is.min.js"></script>
<script src="resources/vendors/fontawesome/all.min.js"></script>
<script src="resources/vendors/lodash/lodash.min.js"></script>
<script src="https://polyfill.io/v3/polyfill.min.js?features=window.scroll"></script>
<script src="resources/vendors/list.js/list.min.js"></script>
<script src="resources/vendors/feather-icons/feather.min.js"></script>
<script src="resources/vendors/dayjs/dayjs.min.js"></script>
<script src="resources/assets/js/phoenix.js"></script>

<link href="resources/vendors/leaflet/leaflet.css" rel="stylesheet">
<link href="resources/vendors/leaflet.markercluster/MarkerCluster.css" rel="stylesheet">
<link href="resources/vendors/leaflet.markercluster/MarkerCluster.Default.css" rel="stylesheet">

<script type="text/javascript" src="resources/vendor/jquery/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

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