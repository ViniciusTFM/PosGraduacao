$( document ).ready(function() {

	var notificacao = "";
	
	if($('#notificacao')){
		if($('#notificacao').val() == "erroCadUsuario"){
			notificacao = "<div class='alert alert-danger alert-dismissible' role='alert'>"
							+"	<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span class='glyphicon glyphicon-remove'></span></button>"
							+"	<strong>Erro ao realizar o cadastro!</strong> Tente Novamente."
						+"</div>";
		}
		if($('#notificacao').val() == "CadUserSuccess"){
			notificacao = "<div class='alert alert-info alert-dismissible' role='alert'>"
							+"	<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span class='glyphicon glyphicon-remove'></span></button>"
							+"	<strong>Cadastro Realizado com Sucesso!</strong> Sucesso ao realizar o cadastro do usuario."
						+"</div>";
		}
		if($('#notificacao').val() == "loginError"){
			notificacao = "<div class='alert alert-danger alert-dismissible' role='alert'>"
							+"	<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span class='glyphicon glyphicon-remove'></span></button>"
							+"	<strong>Erro ao realizar o login!</strong> Tente Novamente."
						+"</div>";
		}
	};

	$("#notific").append(notificacao);
	
});