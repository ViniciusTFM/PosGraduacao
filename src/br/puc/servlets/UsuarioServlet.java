package br.puc.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.puc.controller.UsuarioController;
import br.puc.entidades.Usuario;
import br.puc.funcoes.PreProcessamento;

/**
 * Servlet implementation class UsuarioServlet
 */
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{			
			
			if(request.getParameter("operacao").equalsIgnoreCase("logout")) {
				
				// no logout invalido a sessao
				HttpSession sessao = request.getSession();
				sessao.invalidate();
				// chamo novamente a pagina principal, que deve chamar a p�gina index
				// que ira mostrar o formulario para o usu�rio logar
				response.sendRedirect("login.jsp");
				
			}	
		}catch(Exception e){
			System.out.println("Erro Servlet Usuario: " + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		try{
		
			
			ServletContext context = request.getServletContext();
			String caminho = context.getRealPath("/WEB-INF");
			
			
			if(request.getParameter("operacao").equalsIgnoreCase("incluir")){

				List<String> listaCompetencias = new ArrayList<String>();
				List<String> listaIdOutrasCompetencias = null;
				boolean salvou = false;
				
				String nome = request.getParameter("nome");
				String email = request.getParameter("email");
				String competencias[]= request.getParameterValues("competencia");
				
				String outrasCompetencias[] = request.getParameterValues("outraCompetencia");
			
				if(competencias != null){
					for(int i = 0; i < competencias.length; i++){
						listaCompetencias.add(competencias[i]);
					}
				}

				if(outrasCompetencias != null){
					listaIdOutrasCompetencias = UsuarioController.cadastroOutrasCompetencias(outrasCompetencias);
				
					if(listaIdOutrasCompetencias != null){
						listaCompetencias.addAll(listaIdOutrasCompetencias);
					}
				}
				
				if(listaCompetencias.size() > 0){
					salvou = UsuarioController.cadastroUsuario(nome, email, listaCompetencias);
					if(salvou == true){
						response.sendRedirect("./login.jsp?not=CadUserSuccess");
					}else{
						response.sendRedirect("./cadastro_usuario.jsp?not=CadUserError");
					}
				}else{
					response.sendRedirect("./cadastro_usuario.jsp?not=CadUserError");
				}

				//redirecionar para login
			}else if(request.getParameter("operacao").equalsIgnoreCase("login")){
			
				String email = request.getParameter("email");
				Usuario user = UsuarioController.login(email);
				
				List<String> listaCompetencias = PreProcessamento.normalizarCompetencias(user.getCompetencias(), caminho);
				
				//cria uma sess�o
				HttpSession sessao = request.getSession();
				
				if(user.getId() == 0){
					response.sendRedirect("login.jsp?not=loginError");
				}else{
				
					// setando um atributo da sessao
					sessao.setAttribute("id", user.getId());
					sessao.setAttribute("nome", user.getNome());
					sessao.setAttribute("email", user.getEmail());
					sessao.setAttribute("listaCompetencias", listaCompetencias);
					
					response.sendRedirect("index.jsp");
				}
			}
		}catch(Exception e){
			System.out.println("Erro Servlet Usuario Login: " + e.getMessage());
		}
	}

}
