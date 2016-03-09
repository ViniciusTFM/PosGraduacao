<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" import="br.puc.entidades.Competencia"%>
<%@ page language="java" import="br.puc.controller.UsuarioController"%>

<!DOCTYPE html>
<html lang="pt">

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Recomendação | PosGraduacaoRecommander</title>
	
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

	<div class="container" style="margin-top: 150px">

		<form class="form-horizontal" name="cadastro_usuarios" action="UsuarioServlet" method="post">

			<fieldset>
				<legend>Lista Recomendação para Usuarios</legend>

				<div class="form-group">

				<div class="panel panel-primary">
					<div class="panel-heading">Recomendações</div>
						<div class="panel-body" style="padding: 0px;">
  				
								<table class="table">
								    <tr>
								        <th>Usuario</th>
								        <th>Email</th>
								        <th colspan="4">Recomendações</th>
								    </tr>
								   
									<c:forEach var="value" items="${listaRecomendacoesUsuarios}" varStatus="key">
				
									      <tr>
								       		<td ><c:out value="${value.nome}"></c:out></td>
									        <td ><c:out value="${value.email}"></c:out></td>
									       	
									       	<c:forEach var="competencias" items="${value.competencias}">
									       		<td><c:out value="${competencias}"></c:out></td>
									        </c:forEach>
									      </tr>
				
									</c:forEach>
			
								</table>
					
					  
					  		</div>
						</div>
					</div>
	

					<div class="control-group">
						<div class="controls">
							<button class="btn btn-info" type="button" onclick="window.location.href='/PosGraduacaoRecommander/index.jsp'">Voltar</button>
						</div>
					</div>

					
				</div>
				
			</fieldset>
		</form>
	</div>

	<jsp:include page="rodape.jsp" />
</body>
</html>