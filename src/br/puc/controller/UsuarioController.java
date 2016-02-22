package br.puc.controller;

import java.util.List;

import br.puc.dao.UsuarioDao;
import br.puc.entidades.Competencia;
import br.puc.entidades.Usuario;

public class UsuarioController {

	/**
	 * TODO
	 * @function	listarCompetencias - faz uma consulta no banco e retorna todas
	 * 				as competencias cadastradas
	 * @return		Lista de competencias
	 */
	public static List<Competencia> listarCompetencias(){
		
		List<Competencia> competencias = UsuarioDao.consultarCompetencias();
		
		return competencias;
	}

	/**
	 * TODO
	 * @function 			cadastroUsuario - faz o cadastro de um usuario
	 * @param nome			nome do usuario
	 * @param email			email do usuario
	 * @param competencias	lista de competencias 
	 * @return				booleano 	true - cadastrado com sucesso
	 * 									false - erro ao cadastrar
	 */
	public static boolean cadastroUsuario(String nome, String email, List<String> competencias){
		
		boolean retorno = false;
		
		retorno = UsuarioDao.salvarUsuario(nome, email, competencias);
		
		return retorno;
	}
	
	/**
	 * TODO
	 * @function		login - faz o login por email
	 * @param email		email do usuario
	 * @return			objeto do tipo Usuario contendo nome, email e uma lista de competencias
	 */
	public static Usuario login(String email){
		return UsuarioDao.loginUsuario(email);
	}
	
	/**
	 * 
	 * @param outrasCompetencias
	 * @return
	 */
	public static List<String> cadastroOutrasCompetencias (String[] outrasCompetencias){
		
		List<String> lista = UsuarioDao.cadastroCompetencias(outrasCompetencias);
		
		return lista;
	}
}
