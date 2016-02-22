<!DOCTYPE html>
<html lang="en">

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Curso | PosGraduacaoRecommander</title>
	
	<!-- Bibliotecas JavaScript -->
	
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src='js/bootstrap.min.js' ></script>
	<script type="text/javascript" src='js/ct-navbar.js'></script>
	<script type="text/javascript" src='js/rodape.js'></script>
	<script type="text/javascript" src='js/Curso/notificacao_curso.js' charset="utf-8"></script>
		
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
	
	<!--  Notificações -->
	<%	if (request.getParameter("not") != null) {    
			if(request.getParameter("not").equalsIgnoreCase("CadCursoSuccess")) {
	%>			<input type="hidden" value="CadCursoSuccess" id="notificacao" />
	<%		}else if(request.getParameter("not").equalsIgnoreCase("CadCursoError")) {
	%>			<input type="hidden" value="CadCursoError" id="notificacao" />
	<%		}else if(request.getParameter("not").equalsIgnoreCase("ArqInvSuccess")) {
	%>			<input type="hidden" value="ArqInvSuccess" id="notificacao" />
	<%		}else if(request.getParameter("not").equalsIgnoreCase("ArqInvError")) {
	%>			<input type="hidden" value="ArqInvError" id="notificacao" />
	<%		}
		}
	%>

	<div id="notific"></div>

	
	<!--  Corpo da pagina -->
	<div class="container" style="margin-top: 150px">

		<h3 id="thumbnails-default">Funções</h3>

		<div class="grid">
			<div class="row col-md-12">
				<div class="tile tile-green col-md-3 col-xs-12">
					<a href="./cadastro_curso.jsp">
						<h1>Cadastrar Novo Curso</h1>
					</a>
				</div>

				<div class="tile tile-blue col-md-3 col-xs-12">
					<a href="CursoServlet?operacao=gerarArqInvertido">
						<h1>Gerar Arquivo Invertido</h1>
					</a>
				</div>

				<div class="tile tile-orange col-md-3 col-xs-12">

					<a href="./index.jsp">
						<h1>Voltar</h1>
					</a>
				</div>
			</div>
		</div>

	</div>

	<jsp:include page="rodape.jsp" />
</body>
</html>