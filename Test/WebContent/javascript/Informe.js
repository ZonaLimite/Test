/**
 * 
 */
function iniciar(){
	selectOpcionCentro = document.getElementById("selectCentro");
	textCentro = document.getElementById("inputCentro");
	selectOpcionCentro.onchange = function(){
		textCentro.value=selectOpcionCentro.options[selectOpcionCentro.selectedIndex].value;
	}
	
	selectOpcionMaquina = document.getElementById("selectMaquina");
	textMaquina = document.getElementById("inputMaquina");
	selectOpcionMaquina.onchange = function(){
		textMaquina.value=selectOpcionMaquina.options[selectOpcionMaquina.selectedIndex].value;
	}
	
	selectOpcionModelo = document.getElementById("selectModelo");
	textModelo = document.getElementById("inputModelo");
	selectOpcionModelo.onchange = function(){
		textModelo.value=selectOpcionModelo.options[selectOpcionModelo.selectedIndex].value;
	}		
	
}
