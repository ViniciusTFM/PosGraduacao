package br.puc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.puc.entidades.Competencia;
import br.puc.entidades.Usuario;
import br.puc.funcoes.conexao;

public class UsuarioDao {
	
	/**
	 * TODO
	 * @function	consultarCompetencias - consulta todas as competencias no banco
	 * @return		lista com todas as competencias
	 */
	public static List<Competencia> consultarCompetencias(){
		
		List<Competencia> competencias = new ArrayList<Competencia>();
		
		try{
			
			Connection con = conexao.obterConexao();

			String sql = "SELECT * FROM TB_COMPETENCIA order by competencia;";

			PreparedStatement comando = con.prepareStatement(sql);

			System.out.println(" Executando comando ... ");

			ResultSet resultado = comando.executeQuery();

			System.out.println(" Resultados encontrados : \n");
			
			if(resultado.next()){
				do
				{
					Competencia comp = new Competencia();
					comp.setId(resultado.getInt("id"));
					comp.setCompetencia(resultado.getString("competencia"));
					
					competencias.add(comp);
				}
				while(resultado.next());
			}else{
				return(new ArrayList<Competencia>());
			}
			
			System.out.println("\nFechando conex�o ... ");
			con.close();
			
		}catch(Exception e){
			System.out.println("Erro ao consultar competencias: " + e);
		}
		
		return competencias;
	}
	
	/**
	 * TODO
	 * @function	addUsuario - faz insert d eum novo usuario
	 * @param 		nome do usuario
	 * @param 		email do usuario
	 * @return		id do usuario
	 */
	public static int addUsuario(String nome, String email){
		
		int id = 0;
		
		try{
			Connection con = conexao.obterConexao();
			
			String sql = "insert into tb_usuario (nome, email) values (?, ?);";
			
			PreparedStatement comando = con.prepareStatement(sql);
			
			//Seta os parametros para inserir no banco
			
			comando.setString(1, nome); 
			comando.setString(2, email); 
			
			//Executa o SQL
			comando.execute();
			
			String consulta = "select * from tb_usuario where email = ?";
			comando = con.prepareStatement(consulta);
			comando.setString(1, email);
			ResultSet resultado = comando.executeQuery();
			if (resultado.next()) {
				id = resultado.getInt("id");
			}
			con.close();
		}catch(Exception e){
			System.out.println("Erro ao salvar o usuario: " + e.getMessage());
		}
		return id;
	}
	
	/**
	 * TODO
	 * @function	salvarUsuario - faz a persistencia em duas tabelas, usuario e usuario_competencia
	 * @param 		nome do usuario
	 * @param 		email do usuario
	 * @param 		competencias - lista de competencias
	 * @return		boolean  	true: persistencia feita com sucesso
	 * 							false: erro ao fazer a persistencia
	 */
	public static boolean salvarUsuario(String nome, String email, List<String> competencias){
		
		boolean retorno = false;
		int id = addUsuario(nome, email);
		
		try{
			Connection con = conexao.obterConexao();
			
			//persistencia Montro para isso!!!!!
			String sql = "insert into tb_usuario_competencia (id_usuario, id_competencia) values (?,?);";
			PreparedStatement comando = con.prepareStatement(sql);
			
			for(int i = 0; i < competencias.size(); i++){
				comando.setInt(1, id); 
				comando.setInt(2, Integer.parseInt(competencias.get(i)));
				comando.execute();
			}

			con.close();
			retorno = true;
			
		}catch(Exception e){
			return false;
		}
		return retorno;
	}
	
	/**
	 * TODO
	 * @function	loginUsuario - faz a consulta do usuario e retorna os dados 
	 * 				do usuario
	 * @param 		email do usuario
	 * @return		dados do usuario logado
	 */
	public static Usuario loginUsuario(String email){
		
		Usuario user = new Usuario();
		List<String> comp = new ArrayList<String>();
		
		try{
			
			Connection con = conexao.obterConexao();
			
			String sql = 	"select u.id, u.nome, u.email, c.competencia from tb_usuario u "
							+ "inner join tb_usuario_competencia uc on (u.id = uc.id_usuario) "
							+ "inner join tb_competencia c on (c.id = uc.id_competencia) where email = ?; ";
			
			PreparedStatement comando = con.prepareStatement(sql);

			comando.setString(1, email); 
			
			ResultSet resultado = comando.executeQuery();
			
			if (resultado.next()) {
				
				user.setId(resultado.getInt("id"));
				user.setNome(resultado.getString("nome"));
				user.setEmail(resultado.getString("email"));
				
				do{
					comp.add(resultado.getString("competencia"));
				}while(resultado.next());
				
				user.setCompetencias(comp);
			}else{
				return (new Usuario());
			}
			
		}catch(Exception e){
			System.out.println("Erro ao fazer login: " + e.getMessage());
		}
		
		return user;
	}
	
	/**
	 * 
	 * @param outrasCompetencias
	 * @return
	 */
	public static List<String> cadastroCompetencias(String[] outrasCompetencias){
		
		List<String> chavesOutrasCompetencias = null;
		
		try{
			Connection con = conexao.obterConexao();
			
			String sql = "insert into tb_competencia (competencia) value (?);";
			
			PreparedStatement comando = con.prepareStatement(sql);
			
			for(int i = 0; i < outrasCompetencias.length; i++){
				comando.setString(1, outrasCompetencias[i]); 
				comando.execute();
			}
			
			chavesOutrasCompetencias = consultaOutrasCompetencias(outrasCompetencias);
		}catch(Exception e){
			System.out.println("Erro ao persistir outras competencias: " + e.getMessage());
		}
		return chavesOutrasCompetencias;
	}
	
	/**
	 * 
	 * @param outrasCompetencias
	 * @return
	 */
	public static List<String> consultaOutrasCompetencias(String[] outrasCompetencias){
		
		List<String> retorno = new ArrayList<String>();
		
		try{
			Connection con = conexao.obterConexao();
			
			String sql = "select id from tb_competencia where competencia = ?;";
			PreparedStatement comando = con.prepareStatement(sql);

			for(int i = 0; i < outrasCompetencias.length; i++){
				comando.setString(1, outrasCompetencias[i]);
				
				ResultSet resultado = comando.executeQuery();
			
				if (resultado.next()) {
					retorno.add(resultado.getString("id"));
				}
				
			}
			con.close();
			
			for(int i = 0; i < retorno.size(); i++){
				System.out.println(retorno.get(i));
			}
			
		}catch(Exception e){
			System.out.println("Erro ao consultar outras competencias: " + e);
		}
		
		return retorno;
	}
}
