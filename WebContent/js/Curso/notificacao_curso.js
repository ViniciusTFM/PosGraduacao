$( document ).ready(function() {

	var notificacao = "";
	
	if($('#notificacao')){
		if($('#notificacao').val() == "CadCursoError"){
			notificacao = "<div class='alert alert-danger alert-dismissible' role='alert'>"
							+"	<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span class='glyphicon glyphicon-remove'></span></button>"
							+"	<strong>Erro ao realizar o cadastro!</strong> Tente Novamente."
						+"</div>";
		}
		if($('#notificacao').val() == "CadCursoSuccess"){
			notificacao = "<div class='alert alert-info alert-dismissible' role='alert'>"
							+"	<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span class='glyphicon glyphicon-remove'></span></button>"
							+"	<strong>Cadastro Realizado com Sucesso!</strong> Sucesso ao realizar o cadastro do Curso."
						+"</div>";
		}
		if($('#notificacao').val() == "ArqInvError"){
			notificacao = "<div class='alert alert-danger alert-dismissible' role='alert'>"
							+"	<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span class='glyphicon glyphicon-remove'></span></button>"
							+"	<strong>Erro ao gerar o arquivo invertido!</strong> Tente Novamente."
						+"</div>";
		}
		if($('#notificacao').val() == "ArqInvSuccess"){
			notificacao = "<div class='alert alert-info alert-dismissible' role='alert'>"
							+"	<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span class='glyphicon glyphicon-remove'></span></button>"
							+"	<strong>Arquivo invertido gerado com Sucesso!</strong> Sucesso ao gerar o Arquivo invertido."
						+"</div>";
		}
	};

	$("#notific").append(notificacao);
	
});