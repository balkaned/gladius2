function addOperador(value) {
    var campo = document.getElementById('text-box');
    var insertar = value;

    var inicio = campo.selectionStart;
    var fin = campo.selectionEnd;
    var texto = campo.value;
    campo.value = texto.slice(0, inicio) + insertar + texto.slice(fin);
    campo.selectionStart = campo.selectionEnd = inicio + insertar.length;
    campo.focus();
}

function mostrarAlert() {
    let div = document.getElementById('alert');
    div.style.display = '';

    setTimeout(function () {
        $("#alerts").hide(6000);
    }, 3000);
}

function valida_tipo_for(opcion) {
    if (opcion.value=="0"){
       document.getElementById("sqlprogram").value="";
       document.getElementById("grpeje").value="";
       document.getElementById("sqlprogram").disabled = true;
       document.getElementById("grpeje").disabled = true;
       document.getElementById("Layer1").disabled = false;
       document.getElementById("hashtag").disabled = false;
       //document.getElementById("text-box").disabled = false;
    }else if (opcion.value=="1"){
       document.getElementById("sqlprogram").value="";
       document.getElementById("grpeje").value="";
       document.getElementById("sqlprogram").disabled = true;
       document.getElementById("grpeje").disabled = true;
       document.getElementById("Layer1").disabled = false;
       document.getElementById("hashtag").disabled = false;
       //document.getElementById("text-box").disabled = false;
    }else if (opcion.value=="2") {
       //document.getElementById("text-box").value="";
       //document.getElementById("text-box").disabled = true;
       document.getElementById("sqlprogram").disabled = false;
       document.getElementById("grpeje").disabled = false;
       document.getElementById("Layer1").disabled = true;
       document.getElementById("hashtag").disabled = true;
    }
}

function traducirFormula(){
    var formula=document.getElementById("textAreaTraductor").value;

    $.ajax({
         url: "traducirFormulaAjax",
         data: {
             },
         success: function (data) {
             var opt = "";
             var opt2 = "";
             var onclickchar="";
             var formulaReplace="";
             var formulaResult="";
             var desVariableAux="";

             formula2=formula;
             nuevaFormula=formula2;

             var remplazarPor="";

             //Busca y remplaza variables de formula
             for (var i in data) {
                desVariableAux=data[i].desVariable.trim().toString();
                remplazarPor="["+"<label id='labelvariableform'>"+data[i].desVariable+"</label> "+"<label id='labelcomentario'>"+data[i].desAbreviacionCapit+"</label>]";

                nuevaFormula = nuevaFormula.replaceAll(desVariableAux,remplazarPor);
             }

             //Busca y remplaza operador if else elseif switch case break
             nuevaFormula = nuevaFormula.replaceAll("if","<label class='text-warning'>if&nbsp</label>");
             nuevaFormula = nuevaFormula.replaceAll("else","<label class='text-warning'>else&nbsp</label>");
             nuevaFormula = nuevaFormula.replaceAll("switch","<label class='text-warning'>switch</label>");
             nuevaFormula = nuevaFormula.replaceAll("case","<label class='text-warning'>case</label>");
             nuevaFormula = nuevaFormula.replaceAll("break","<label class='text-warning'>break</label>");
             nuevaFormula = nuevaFormula.replaceAll("  ","<label>&nbsp &nbsp</label>");
             nuevaFormula = nuevaFormula.replaceAll("\n","</br>");
             nuevaFormula = nuevaFormula.replaceAll(";","<label id='labelpuntoycoma' class='text-warning'>;</label>");

             opt2 = "<div id='textAreaTraducido' class='form-control border-200 bg-dark text-white rounded-top-0 border-0 flex-1' rows='10'>"+nuevaFormula+"</div>";

             $("#bodyTraducido2").html(opt2);
         }
    });
}

$(document).ready(function() {
    var element=document.getElementById('operadorif');

    var formulaPreConf = "if ($variable$ == 1){ }";

    var dato="addOperador('"+formulaPreConf+"');";
    element.setAttribute('onclick',dato);
});

//CKEDITOR.replace('text-box');