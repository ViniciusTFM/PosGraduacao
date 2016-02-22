<!DOCTYPE html>
<html lang="en">

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Cadastrar Curso | PosGraduacaoRecommander</title>
	
	<!-- Bibliotecas JavaScript -->
	
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src='js/bootstrap.min.js' ></script>
	<script type="text/javascript" src='js/ct-navbar.js'></script>
	<script type="text/javascript" src='js/rodape.js'></script>
	
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
	<jsp:include page="navbar.jsp" />

	<jsp:useBean id="servlet" class="br.puc.servlets.CursoServlet" scope="session" />

	<div class="container" style="margin-top: 150px">

		<form class="form-horizontal" name="cadastro_cursos" action="CursoServlet" method="post">

			<fieldset>
				<legend>Cadastro de Usuário</legend>

				<div class="form-group">
					<label class="control-label" for="inputNome">Curso:</label>
					<div class="controls">
						<input id="inputNome" name="nome" type="text" placeholder="Digite nome do curso..." required />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label" for="descricaoCurso">Descrição do Curso:</label>
					<div class="controls">
						<textarea class="form-control" rows="10" id="descricaoCurso" name="descricao" placeholder="Digite a descrição do curso..." required></textarea>
					</div>
				</div>

				<div class="form-group">
					<div class="controls">
						<br><br><input type="hidden" name="operacao" value="incluirCurso" />
						<button class="btn btn-danger" type="button" onclick="window.location.href='/PosGraduacaoRecommander/curso.jsp'">Cancelar</button>
						<button class="btn btn-success" type="reset">Limpar</button>
						<button class="btn btn-primary" type="submit">Salvar</button>
					</div>
				</div>

			</fieldset>
		</form>
	</div>

	<jsp:include page="rodape.jsp" />
</body>
</html>