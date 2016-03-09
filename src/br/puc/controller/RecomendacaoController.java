package br.puc.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import br.puc.entidades.FrequenciaTermos;
import br.puc.entidades.PesoCurso;
import br.puc.entidades.Usuario;
import br.puc.funcoes.Arquivo;
import br.puc.funcoes.PreProcessamento;
import br.puc.dao.UsuarioDao;

public class RecomendacaoController {
	
	/**
	 * TODO
	 * COmentar esse metodo
	 * @function	gerarRecomendacao - 
	 * @param 		competencias
	 * @param 		caminho
	 * @return		
	 */
	public static List<String> gerarRecomendacao(List<String> competencias, String caminho){
		
		List<PesoCurso> cursos = new ArrayList<PesoCurso>();
		List<FrequenciaTermos> recomendacoes = null;
		List<String> cursosMaisRecomendados = null;
		
		try{	
			
			//leitura da hash e cruzamento de dados com as competencias
			recomendacoes = buscarDadosHash(competencias, caminho);
			
			//gera uma lista com as recomendações
			for(int i = 0; i < recomendacoes.size(); i++){
				
				boolean controle = false;
				
				for(int j = 0; j < cursos.size(); j++){
					
					//se o curso estiver na lista de cursos, soma o peso do curso e soma o peso das palavras no documento
					if(recomendacoes.get(i).getCurso().equals(cursos.get(j).getCurso())){
						cursos.get(j).setPeso(cursos.get(j).getPeso() + 1);
						cursos.get(j).setPesoPalavras(cursos.get(j).getPesoPalavras() + recomendacoes.get(i).getFrequencia());
						controle = true;
					}
				}
				
				//se o curso nao estiver na lista de cursos, add ele na lista
				if(controle == false){
					PesoCurso pc = new PesoCurso();
					pc.setCurso(recomendacoes.get(i).getCurso());
					pc.setPesoPalavras(recomendacoes.get(i).getFrequencia());
					pc.setPeso(1);
					cursos.add(pc);
				}
			}
			
			//ordena a lista de recomendacoes onde o primeiro elemento da lista é o curso mais recomendado
			cursosMaisRecomendados = ordenarRecomendacao(cursos);
			
		}catch(Exception e){
			System.out.println("Erro ao gerar a recomendacao: " + e);
		}
			
		return cursosMaisRecomendados;
	}
	
	/**
	 * TODO
	 * @function	buscarDadosHash - faz a leitura do arquivo da hash e recupera as listas referentes
	 * 				as palavras chave do perfil do usuario
	 * @param 		competencias - lista de palavras chave do usuario
	 * @param 		caminho - caminho onde é armazenado a hash
	 * @return		lista do tipo FrequenciaTermos com o cruzamento de dados da hash com as competencias
	 * 				obtidas pelo perfil do usuario
	 */
	public static List<FrequenciaTermos> buscarDadosHash(List<String> competencias, String caminho){
		
		List<FrequenciaTermos> cursoCompetencia = new ArrayList<FrequenciaTermos>();
		try{
			//Leitura da hash
			Hashtable<String, ArrayList<FrequenciaTermos>> hash = Arquivo.lerHash(caminho);
			
			//busca as competencias na tabela hash
			for(int i = 0; i < competencias.size(); i++){
				
				//recupera lista da hash de acordo com a palavra chave
				List<FrequenciaTermos> lista = hash.get(competencias.get(i));
				
				//adiciona os elementos da lista recuperada em uma unica lista
				if(lista != null){
					for(int j = 0; j < lista.size(); j++){
						cursoCompetencia.add(lista.get(j));
					}
				}else{
					System.out.println("Copetencia " + competencias.get(i) + " não existe na hash!");
				}
			}
		}catch(Exception e){
			System.out.println("Erro ao fazer ao fazer o cruzamento dos dados de perfil + hash: " + e);
		}
		return cursoCompetencia;
	}

	/**
	 * TODO
	 * @function	ordenarRecomendacao - recebe uma lista de cursos com seus respectivos pesos, e ordena os 
	 * 				cursos de forma que o primeiro elemento da lista seja o curso de maior peso, tanto por peso
	 * 				de palavras referentes ao perfil quanto peso de numero de palavras do perfil que aparecem 
	 * 				no documento
	 * @param 		listaCursos - Lista do tipo PesoCurso
	 * @return		lista de strings com os nomes dos cursos ordenados por peso
	 */
	@SuppressWarnings("unused")
	public static List<String> ordenarRecomendacao(List<PesoCurso> listaCursos){

		List<String> cursosRecomendados = new ArrayList<String>();
		int indice = 0;
		int cont = 0;
		
		try{
			//enquanto a lista de cursos por peso nao estiver vazia
			while(listaCursos.size() > 0){
			
				PesoCurso temp = listaCursos.get(0);
				indice = 0;
				
				//pega os 4 cursos mais relevantes e para a execucao do while
				if(cont == 4){
					break;
				}
				
				/*	compara todas as posicoes com a primeira e verifica se o peso é maior
				 *	add na variavel temp o curso que contem maior peso e maior peso por palavras 
				 */
				for(int j = 0; j < listaCursos.size(); j++){
					
					if ( listaCursos.get(j).getPeso() > temp.getPeso()) {
						temp = listaCursos.get(j);
						indice = j;
					}
	
					if(listaCursos.get(j).getPeso() == temp.getPeso()){
						if(listaCursos.get(j).getPesoPalavras() > temp.getPesoPalavras()){
							temp = listaCursos.get(j);
							indice = j;
						}
					}
				}
				
				//incrementa o contador
				cont++;
				//remove temp da lista
				listaCursos.remove(temp);
				//add temp na lista
				cursosRecomendados.add(temp.getCurso());
			}
		}catch(Exception e){
			System.out.println("Erro ao ordenar a recomendação: " + e);
		}
		
		return cursosRecomendados;
	}

	
	
	/**
	 * 
	 * @return
	 */
	public static List<Usuario>ListarRecomendacaoUsuarios(String caminho){
		

		List<Usuario> User = UsuarioDao.listarUsuarios();
		List<Usuario> listaRecomendacao = new ArrayList<Usuario>();
		
		for(int i = 0; i < User.size(); i++){
			List<String> listaCompetencias = PreProcessamento.normalizarCompetencias(User.get(i).getCompetencias(), caminho);
			List<String> listaCursos = gerarRecomendacao(listaCompetencias, caminho);
			
			Usuario u = new Usuario();
			u.setCompetencias(listaCursos);
			u.setEmail(User.get(i).getEmail());
			u.setNome(User.get(i).getNome());
			
			listaRecomendacao.add(u);
		}
		
		return listaRecomendacao;
	}
}
