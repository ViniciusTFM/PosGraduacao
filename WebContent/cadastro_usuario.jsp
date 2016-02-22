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
	<div class="container" style="margin-top: 150px">

		<form class="form-horizontal" name="cadastro_usuarios" action="UsuarioServlet" method="post">

			<fieldset>
				<legend>Cadastro de Usuário</legend>

				<div class="control-group">
					<label class="control-label" for="inputNome">Nome:</label>
					<div class="controls" >
						<input id="inputNome" name="nome" type="text" placeholder="Digite o seu nome..." required />
					</div>
				</div>

				<div class="control-group">

					<label class="control-label" for="inputEmail">E-mail:</label>
					<div class="controls">
						<input id="inputEmail" name="email" type="email" placeholder="Digite o seu e-mail..." required />
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
									if (listaCompetencia.get(i).getId() % 2 == 1) {
						%>				<div class="row">
						<%			}
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
									if (listaCompetencia.get(i).getId() % 2 == 0) {
						%>				</div>
						<%			}
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
					<div class="controls">
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