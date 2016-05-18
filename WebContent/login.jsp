<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login | Post Graduate Recommendation </title>

	<!-- Bibliotecas JavaScript -->

	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src='js/bootstrap.min.js' ></script>
	<script type="text/javascript" src='js/ct-navbar.js'></script>
	<script type="text/javascript" src='js/rodape.js'></script>
	<script type="text/javascript" src='js/Perfil/notificacao_usuario.js' charset="utf-8"></script>
	

	<!-- Estilos CSS -->

	<link rel="stylesheet" type="text/css" href="css/login.css" />
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

<!--  -->

<% 	if (request.getParameter("not") != null) {    
		if(request.getParameter("not").equalsIgnoreCase("CadUserSuccess")) {
%>			<input type="hidden" value="CadUserSuccess" id="notificacao" />
<%		}else if(request.getParameter("not").equalsIgnoreCase("loginError")) {
%>			<input type="hidden" value="loginError" id="notificacao" />
<%		}
	}
%>

	<div id="notific"></div>



		<div class="container" style="margin-top:150px">
			
			<div class="row" style="rigth: 50%">
				<div class="col-md-3 col-xs-12">
				
        			<div class="panel-title" align="center">
						<img src="img/r.jpeg" />
					</div>
					
					<hr />
						
					<form accept-charset="UTF-8" class="form-signin" action="UsuarioServlet" method="post">
						<fieldset>
							<div class="login_result"></div>
								
							<label class="panel-login"> </label> 
								
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
								<input class="form-control" placeholder="E-mail" id="username" type="email" name="email" /> 
							</div>
								
							<input type="hidden" name="operacao" value="login" /> 
							<input type="submit" value="Entrar" class="btn btn-lg btn-primary btn-block" onclick="notificacao();">
								
						 </fieldset>
					</form> 
					<br>
					<a href="./cadastro_usuario.jsp">
						Criar uma nova conta
					</a>
            
        		</div>
        		<div  class="col-md-9 col-xs-12">
	        		
	        		<div style="padding: 10px;">
	        			<p style="font-size: 24px;">
			        		
			        		<span style="padding-left:3em">
			        		O sistema de recomendação <i>PosGraduacaoRecommander</i> foi criado com o intuito de recomendar 
			        		cursos de Pós-Graduação de acordo com as competências do usuário.
							</span>

							<br>

			        		<span style="padding-left:3em">
			        		As competências são cadastradas junto ao perfil do usuário e elas são utilizadas para fazer o cruzamento de dados
			        		com a descrição dos cursos de Pós-Graduação da Puc Minas.
							</span>										        		
			        		
			        		<br>

			        		<span style="padding-left:3em">
			        		O resultado do cruzamento das competências com os dados do curso é a recomendação dos quatro cursos que mais semelhantes 
			        		aos dados cadastrados.
							</span>
	        			</p>
	        		</div>
        		
        		</div>
			</div>
		</div><!-- //row -->
	   <jsp:include page="rodape.jsp" />
	   
</body>
</html>