<!DOCTYPE html>
<html lang="en">

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Index | PosGraduacaoRecommander</title>

	<!-- Bibliotecas JavaScript -->
	
	<script type="text/javascript" src="js/jquery-1.11.3.min.js" ></script>
	<script type="text/javascript" src='js/bootstrap.min.js' ></script>
	<script type="text/javascript" src='js/ct-navbar.js' ></script>
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
    
    <div class="container" style="margin-top:150px">
    
	    <h3 id="thumbnails-default">Funções</h3>
	
	
		<div class="grid">
		    <div class="row col-md-12">
		          <div class="tile tile-red col-md-3 col-xs-12"  >
		          
		          	<a href="./curso.jsp" >
		              <h1>Curso</h1>
		            </a>
		          
		          </div>
		          
		          <div class="tile tile-turquoise col-md-3 col-xs-12" >
		          	<a href="RecomendacaoServlet?operacao=Recomendar" >
		              <h1>Recomendar Curso</h1>
		            </a>
		          </div>
		      </div>
	    </div>
	    
    </div>


    <jsp:include page="rodape.jsp" />
</body>
</html>