<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" import="br.puc.entidades.Competencia"%>
<%@ page language="java" import="br.puc.controller.UsuarioController"%>

<!DOCTYPE html>
<html lang="en">

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Cadastrar Usuario | PosGraduacaoRecommander</title>
	
	<!-- Bibliotecas JavaScript -->
	
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src='js/bootstrap.min.js' ></script>
	<script type="text/javascript" src='js/ct-navbar.js'></script>
	<script type="text/javascript" src='js/rodape.js'></script>
	<script type="text/javascript" src='js/Perfil/cadastro_usuarios.js' charset="utf-8"></script>
	<script type="text/javascript" src='js/Perfil/notificacao_usuario.js' charset="utf-8"></script>
	
	
		<script type="text/javascript" src="http://platform.linkedin.com/in.js">
		api_key: 774xiufi82tenf
		authorize: true
		onLoad: onLinkedInLoad
		scope: r_basicprofile,r_emailaddress
	</script>
	
	<script type="text/javascript">
	
		function onLinkedInLoad() {
			IN.Event.on(IN, "auth", onLinkedInAuth);
		}
	
		function onLinkedInAuth() {
			IN.API.Profile("me")
//			.fields("firstName", "lastName", "industry", "location:(name)", "picture-url", "headline", "num-connections", "public-profile-url", "distance", "positions", "email-address", "educations", "date-of-birth", "summary")
			.fields("firstName", "lastName", "email-address")
			.result(displayProfiles)
			.error(displayProfilesErrors);
		}
		
		function displayProfiles(profiles) {
			member = profiles.values[0];
			
			var nome = JSON.stringify(member.firstName + " " + member.lastName);
			var email = JSON.stringify(member.emailAddress);
			
			nome = nome.replace('"', '');
			nome = nome.replace('"', '');
			
			email = email.replace('"', '');
			email = email.replace('"', '');
			
			$("#inputNome").val(nome);
			$("#inputEmail").val(email);
			
			IN.User.logout();
		}
		
		function displayProfilesErrors(error) {
			profilesDiv = document.getElementById("profiles");
			profilesDiv.innerHTML = error.message;
			console.log(error);
		}
	</script>
	
	
	<!-- Estilos CSS -->
	
	<link rel="stylesheet" type="text/css" href="css/metro-bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/docs.css">
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	<link rel="stylesheet" type="text/css" href="css/estilo.css">
	<link rel="stylesheet" type="text/css" href="css/ct-navbar.css">
	<link rel="stylesheet" type="text/css" href="css/pe-icon-7-stroke.css" />

</head>

<body>

	<!-- Static navbar -->
	<jsp:include page="navbar_nao_autenticado.jsp" />


	<!--  Notificações -->
	<%	if (request.getParameter("not") != null) {    
			if(request.getParameter("not").equalsIgnoreCase("CadUserError")) {
	%>			<input type="hidden" value="erroCadUsuario" id="notificacao" />
	<%		}
		}
	%>

	<div id="notific"></div>


	<!--  Corpo da Pagina -->
	<div class="container" style="margin-top: 110px">

		<form class="form-horizontal" name="cadastro_usuarios" action="UsuarioServlet" method="post">

			<fieldset>
				<legend>Cadastro de Usuário</legend>

				<div class="row">
					<div class="col-md-12">
						<script type="IN/Login"></script>
					</div>
				</div>
	
				<br>
	
				<div class="row"  style="margin-bottom: 20px;">
					<div class="col-md-6">
		
						<div class="control-group">
							<label class="control-label col-md-2" for="inputNome">Nome:</label>
							<div class="controls" >
								<input id="inputNome" name="nome" type="text" placeholder="Digite o seu nome..." required />
							</div>
						</div>
					</div>
				</div>
				<div class="row"  style="margin-bottom: 20px;">
					<div class="col-md-6" >
						<div class="control-group">
		
							<label class="control-label col-md-2" for="inputEmail">E-mail:</label>
							<div class="controls">
								<input id="inputEmail" name="email" type="email" placeholder="Digite o seu e-mail..." required />
							</div>
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="competencias">Competencias:</label>
					<div class="controls">
						<%
							int cont = 0;
							List<Competencia> listaCompetencia = UsuarioController.listarCompetencias();

							if (listaCompetencia.size() > 0) {
								for (int i = 0; i < listaCompetencia.size(); i++) {
									
									if (cont == 0) {
										%>	<div class="row">
										<%			}
									//if (listaCompetencia.get(i).getId() % 2 == 1) {
						%>				<!-- <div class="row">-->
						<%			//}
						%>
									<div role="main" class="col-md-3 ">
										<p>
										<input type="checkbox" name="competencia" value="<%out.print(listaCompetencia.get(i).getId());%>" />
							<%
										out.print(listaCompetencia.get(i).getCompetencia());
							%>
										</p>
									</div>

						<%
									if (cont == 3) {
										cont = 0;
						%>				</div>
						<%			}else{
										cont++;
									}
						
						//if (listaCompetencia.get(i).getId() % 2 == 0) {
							//				</div>
										//}
								}
							}
						%>
					</div>
				</div>

				<br> <span class="glyphicon glyphicon-eye-open"
					title="Caso não exista todas suas competencias nas opções acima,  você pode inserir-las em 'Outras competências'!"></span>
				<br>

				<div class="control-group">
					<label class="control-label" for="competencias">Outras Competencias: </label>
					<p>
						<a href="javascript:mais()"> 
							<span class="glyphicon glyphicon-plus"></span> Inserir campo
						</a>
					</p>
					<div class="controls">
						<div id="outrasCompetencias"></div>
					</div>
				</div>

				<div class="control-group">
					<div class="controls botoes">
						<br> <br> <input type="hidden" name="operacao" value="incluir" />
						<button class="btn btn-danger" type="button" onclick="window.location.href='/PosGraduacaoRecommander/index.jsp'">Cancelar</button>
						<button class="btn btn-success" type="reset">Limpar</button>
						<button class="btn btn-primary" type="submit">Salvar</button>
					</div>
				</div>

			</fieldset>
		</form>
	</div>

	<!--  Rodapé -->
	<jsp:include page="rodape.jsp" />
	
</body>
</html>