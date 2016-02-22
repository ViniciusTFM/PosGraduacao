package br.puc.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.puc.controller.CursoController;

/**
 * Servlet implementation class CursoServlet
 */
public class CursoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try{
			
			boolean retorno;
			CursoController controller = new CursoController();
			
			ServletContext context = request.getServletContext();
			String caminho = context.getRealPath("/WEB-INF");
			
			if(request.getParameter("operacao").equalsIgnoreCase("gerarArqInvertido")) {

				System.out.println(caminho);
				
				retorno = controller.gerarArquivoInvertido(caminho);
				
				if(retorno){
					response.sendRedirect("curso.jsp?not=ArqInvSuccess");
				}else{
					response.sendRedirect("curso.jsp?not=ArqInvError");
				}
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("operacao").equalsIgnoreCase("incluirCurso")) {
			
			String curso = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			
			ServletContext context = request.getServletContext();
			String caminho = context.getRealPath("/WEB-INF");
			
			try{
				
				boolean retorno = CursoController.salvarCurso(curso, descricao, caminho);
				
				if(retorno){
					response.sendRedirect("curso.jsp?not=CadCursoSuccess");
				}else{
					response.sendRedirect("curso.jsp?not=CadCursoError");
				}
				
			}catch(Exception e){
				System.out.println("Erro Servlet CursoServlet ao salvat curso: " + e);
			}
			
			


		}
	}
}
