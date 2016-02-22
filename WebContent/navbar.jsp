
<%
	//se não existir usuario na sessão eu redireciono para a tela de login
	if (session.getAttribute("id") == null) {
%>
	<jsp:forward page="login.jsp"></jsp:forward>
<%
	}
%>

<nav class="navbar navbar-ct-blue navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          
          <a class="navbar-brand navbar-brand-logo" href="./index.jsp">
                <div class="logo">
					<!-- <img src="img/curtir.png" /> -->
					<img src="img/r.jpeg" />     
	             </div>
                <div class="brand" style="width: 300px;"><% out.print(session.getAttribute("nome")); %></div>
              </a>
        </div>
      
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        	<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="index.jsp"> 
						<i class="pe-7s-home"> </i>
						<p>Inicio</p>
					</a>
				</li>
				
				<!-- Menu Usuarios -->
				<li class="dropdown"><!-- ./UsuarioServlet?operacao=logout -->
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
						<i class="pe-7s-user"></i>
						<p>Usuário</p>
					</a>
					<ul class="dropdown-menu dropdown">
						<li class="divider"></li>
						<li><a href="UsuarioServlet?operacao=logout"><span class="glyphicon glyphicon-off"></span>   Sair</a></li>
					</ul>
				</li>
				
				
			</ul>
        </div><!-- /.navbar-collapse -->
      
      </div><!-- /.container-fluid -->
    </nav>
<!--  end navbar --> 