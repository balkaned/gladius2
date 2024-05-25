function mostrarAlert(){
    var div=document.getElementById('alert');
    div.style.display = '';

    setTimeout(function() {
        $("#alert").hide(6000);
    }, 3000);
}

function mostrarAlert2(){
    var div=document.getElementById('alert2');
    div.style.display = '';

    setTimeout(function() {
      $("#alert").hide(6000);
    }, 3000);
}

function mostrarAlert3(){
    var div=document.getElementById('alert3');
    div.style.display = '';

    setTimeout(function() {
        $("#alert").hide(6000);
    }, 3000);
}

function mostrarAlert4(){
    var div=document.getElementById('alert4');
    div.style.display = '';

    setTimeout(function() {
     $("#alert").hide(6000);
    }, 3000);
}

function mostrarAlert5(){
    var div=document.getElementById('alert5');
    div.style.display = '';

    setTimeout(function() {
      $("#alerts").hide(6000);
    }, 3000);
}

function remove() {
    var opcion = confirm("Esta seguro de Eliminar el Registro?");
    if (opcion == true) {
        return true;
    } else {
        return false;
    }
}