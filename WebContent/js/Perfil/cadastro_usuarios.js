var input = 1;

function mais() {

	var nova = document.getElementById("outrasCompetencias");
	var novadiv = document.createElement("div");
	var nomediv = "div";
	novadiv.id = input;
	novadiv.innerHTML = "<p>"
							+"Competência: <input type='text' name='outraCompetencia' placeholder='Digite uma compêtencia' />"
							+"<span class='glyphicon glyphicon-remove' onclick='javascript:removerDiv("+input+")' style='color:red;' title='Excluir Compêtencia'></span> "
						+"</p>";
	nova.appendChild(novadiv);
	 
	input++;
}

function removerDiv(id){

	document.getElementById(id).remove(); 

}