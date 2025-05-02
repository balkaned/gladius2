function enviaForm(variable){
    var opcion = confirm("Esta seguro de ejecutar este evento?");

    if (opcion == true) {
        if(variable==2){
            document.getElementById("accion").value="INIPRO";
            $("#h5modalLoadinglabel").text("Iniciando planilla");
            $("#spanbtnModalLoading").text("Iniciando");
            $('#modalLoading').modal('show');
        }else if(variable==3){
            document.getElementById("accion").value="EXEPRO";
            $("#h5modalLoadinglabel").text("Procesando planilla");
            $("#spanbtnModalLoading").text("Procesando");
            $('#modalLoading').modal('show');
        }else if(variable==4){
            document.getElementById("accion").value="VERBOLTOT";
        }else if(variable==5){
            document.getElementById("accion").value="EXPBOLTOT";
            document.getElementById("tipfile").value="xls";
        }else if(variable==6){
            document.getElementById("accion").value="DELPRO";
            $("#h5modalLoadinglabelb").text("Borrando toda la planilla");
            $("#spanbtnModalLoadingb").text("Borrando");
            $('#modalLoadingBorrar').modal('show');
        }else if(variable==7){
            document.getElementById("accion").value="CIEPRO";
        }else if(variable==8){
            document.getElementById("accion").value="EXPBOLTOT";
            document.getElementById("tipfile").value="pdf";
        }else if(variable==9){
            document.getElementById("accion").value="EXPTEXT";
            document.getElementById("tipfile").value="text";
        } else if(variable==10){
            document.getElementById("accion").value="QRYRESBAN";
            document.getElementById("tipfile").value="text";
        } else if(variable==11){
            document.getElementById("accion").value="EXEINIPRO";
            document.getElementById("tipfile").value="text";
        }else if(variable==12){
            //document.getElementById("accion").value="VERDETVAR";
            //document.getElementById("tipfile").value="text";
        }else if(variable==15){
            document.getElementById("accion").value="VERPLAVAC";
            document.getElementById("tipfile").value="xls";
        }else if(variable==16){
            document.getElementById("accion").value="VERPLAAUS";
            document.getElementById("tipfile").value="xls";
        }else if(variable==17){
            document.getElementById("accion").value="VERPLAPRES";
            document.getElementById("tipfile").value="xls";
        }else if(variable==18){
            document.getElementById("accion").value="VERPLADATVAR";
            document.getElementById("tipfile").value="xls";
        }else if(variable==25){
            document.getElementById("accion").value="VERDETTURNO";
            document.getElementById("tipfile").value="text";
        }else if(variable==30){
            document.getElementById("accion").value="UPLOADPLA";
            document.getElementById("tipfile").value="xls";
        }else if(variable==31){
            document.getElementById("accion").value="EXPRESCTL";
            document.getElementById("tipfile").value="xls";
        }else if(variable==33){
            document.getElementById("accion").value="EXPASISCCO";
            document.getElementById("tipfile").value="xls";
        }else if(variable==34){
            document.getElementById("accion").value="CALFASIST";
        }else if(variable==35){
            document.getElementById("accion").value="QRYPLA";
        }

        document.getElementById("frmplaserv").submit();

        return true;
    } else {
        return false;
    }
}

function enviaForm_ind(variable, trab){
    if(variable==2){
        document.getElementById("accion").value="INIPRO";
    }else if(variable==3){
        document.getElementById("accion").value="EXEPRO";
    }else if(variable==34){
        document.getElementById("accion").value="CALFASIST";
    }

    document.getElementById("iexcodtra").value=trab;
}

function consulBol(codtra){
    document.getElementById("accion").value="VERBOLTRA";
    document.getElementById("iexcodtra").value=codtra ;
    document.getElementById("frmplaserv").submit();
}

function verdetcon(codtra){
    document.getElementById("accion").value="VERDETCONCEP";
    document.getElementById("iexcodtra").value=codtra ;
    document.getElementById("frmplaserv").submit();
    myWindow = window.open("", "myWindow", "width=200,height=100");
}

function remove(){
    var opcion = confirm("Esta seguro de Eliminar el Registro?");
    if (opcion == true) {
        return true;
    } else {
        return false;
    }
}

function traerDatosReporteEmbeddedResumenPlanilla(){

    var iexcodpro = $("#iexcodpro").val();
    var iexperiodo = $("#iexperiodo").val();

    $.ajax({
         url: "traerDatosReporteResumenPlanilla",
         data: {
            "iexcodpro": iexcodpro,
            "nroper": iexperiodo,
            "nroper2": iexperiodo
         },
         success: function (data) {
             $("#idresult").html(data);
         }
    });
}

function traerTodasLasBoletasPDF(){
    var opcion = confirm("Esta seguro de que desea traer todas las boletas de todos los empleados?");

    if (opcion == true) {
        return true;
    } else {
        return false;
    }
}
