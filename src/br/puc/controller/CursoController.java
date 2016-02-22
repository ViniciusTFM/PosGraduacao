package br.puc.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import br.puc.entidades.FrequenciaTermos;
import br.puc.entidades.IDF;
import br.puc.entidades.TF;
import br.puc.entidades.Documentos;
import br.puc.entidades.FrequenciaPalavraNoDocumento;
import br.puc.funcoes.Arquivo;
import br.puc.funcoes.Hash;
import br.puc.funcoes.PreProcessamento;

public class CursoController {
	
	/**
	 * TODO
	 * @function 	calcularFrequenciaTermos calcula a quantidade de termos
	 *           	identicos em cada arquivo e alimenta a lista de frequencia dos
	 *           	termos com objetos referentes a frequencia da palavra em um
	 *           	determinado documento
	 * @param 		listaPalavras - lista com todas as palavras de um curso
	 * @param 		frequenciaDosTermos - lista de frequencia dos termos contendo as 
	 * 				frequencias ja calculadas
	 * @param 		curso - curso "dono" das palavras 
	 * @return 		Lista de objetos FrequenciaTermos
	 */
	@SuppressWarnings("unused")
	public static List<FrequenciaTermos> calcularFrequenciaTermos(List<Documentos> docs) {

		
		try {
			for(int i = 0; i < docs.size(); i++){
				
				//calcula a frequencia das palavras iguais no documento
				List<FrequenciaPalavraNoDocumento> listaFrequencias = listaPalavrasIguais(docs.get(i).getTermo());
				
				//
				List<TF> tf = CalcularTf(listaFrequencias, docs.get(i).getTermo().size());
				
				//calcula o IDF
				List<IDF> idf = CalcularIdf(docs.size(), docs, listaFrequencias, docs.get(i).getTermo().size());
					
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao calcular a frequencia dos termos: " + e);
		}

		return (null);
	}

	/**
	 * TODO
	 * @param lista
	 * @param qtdTermos
	 * @return
	 */
	public static List<TF> CalcularTf (List<FrequenciaPalavraNoDocumento> lista, int qtdTermos){
		
		List<TF> listTF = new ArrayList<TF>();
		
		for(int i = 0; i < lista.size(); i++){
			
			double tf = (double)lista.get(i).getFrequencia() / (double)qtdTermos;
			
			System.out.println(lista.get(i).getPalavra() + " -  " + lista.get(i).getFrequencia()+" / "+ qtdTermos+ " = "+ tf);

			TF calc = new TF();
			calc.setPalavra(lista.get(i).getPalavra());
			calc.setTF(tf);
			
			listTF.add(calc);
			
		}
		
		return listTF;
	}
	
	
	/**
	 * TODO
	 * @param qtdDocumentos
	 * @param docs
	 * @param listaFrequencias
	 * @param tamLista
	 * @return
	 */
	public static List<IDF> CalcularIdf(int qtdDocumentos, List<Documentos> docs, List<FrequenciaPalavraNoDocumento> listaFrequencias, int tamLista){
		
		List<IDF> listIDF = new ArrayList<IDF>();
		
		try{
		
			for (int i = 0; i < listaFrequencias.size(); i++) {
			
				String termo = listaFrequencias.get(i).getPalavra();
				int cont = 0;
				
				cont = 0;
				
				for(int j = 0; j < docs.size(); j++){
					
					boolean existe = false;
					
					List<String> list = docs.get(j).getTermo();
					
					for(int l = 0; l < list.size(); l++){
						
						if(list.get(l).equals(termo)){
							existe = true;
						}
					}
					
					if(existe == true){
						cont ++;
					}
				}
				System.out.println(termo + " - " + cont);
				
			}
				
			System.out.println("teste");
			
		}catch(Exception e){
			
		}
		
		
		return listIDF;
	}
	
	
	public static void idf(List<Documentos> docs, String term) {
	    double n = 0;
	    
	    for (Documentos doc : docs) {
	        for (String termo : doc.getTermo()) {
	            System.out.println(termo);
	        }
	    }
	    //return Math.log(docs.size() / n);
	}
	
	public static List<FrequenciaPalavraNoDocumento> listaPalavrasIguais(List<String> lista){
		
		List<FrequenciaPalavraNoDocumento> frequencia = new ArrayList<FrequenciaPalavraNoDocumento>();

		try {
			
			
			for(int i = 0; i < lista.size(); i++){

				System.out.println(lista.size());
				
				int cont = 0;

				FrequenciaPalavraNoDocumento freq = new FrequenciaPalavraNoDocumento();
				String termo = lista.get(i);
			
				boolean existe = false;
				
				
				for(int j = 0; j < frequencia.size(); j++){
					if(frequencia.get(j).getPalavra().equalsIgnoreCase(termo)){
						existe = true;
					}
				}
				
				if(existe == false){
					for(int j = 0; j < lista.size(); j++){
						
						if(termo.equalsIgnoreCase(lista.get(j))){
							cont++;
						}
					}

					freq.setPalavra(lista.get(i));
					freq.setFrequencia(cont);
					
					frequencia.add(freq);	
				}
			}
		
			
		} catch (Exception e) {
			System.out.println("Erro ao calcular a frequencia dos termos: " + e);
		}
		
		
		return frequencia;
	}
	
	
	
	/**
	 * TODO
	 * @function	gerarArquivoInvertido faz todas as opera��es necessarias para a cria��o do arquivo invertido,
	 * 				incluindo o pr�-processamento dos documentos e a cria��o da tabela hash.
	 * @param		caminho - caminho da pasta de documentos txt
	 * @return		TRUE tabela hash criada e salva em arquivo
	 * 				FALSE erro durante a execu��o do processo
	 */
	public boolean gerarArquivoInvertido(String caminho){

		Arquivo arq = new Arquivo();
		Hash hash = new Hash();
		List<FrequenciaTermos> frequenciaDosTermos = new ArrayList<FrequenciaTermos>();
		
		List<Documentos> documentos = new ArrayList<Documentos>();
		
		boolean retorno = false;

		try{
			
			
			
			//Faz a leitura de todos os arquivos do diretorio
			List<String> lista = arq.lerDiretorios(caminho);
			
			// Gera a lista de objetos do tipo FrequenciaTermos com todos os arquivos do diretorio
			for (int i = 0; i < lista.size(); i++) {
				
				Documentos doc = new Documentos();
				
				//PRe-PROCESSAMENTO
				//lista com todas as palavras existentes nos arquivos
				List<String> listaPalavras = arq.lerArquivoCurso(lista.get(i), caminho);
				
				//retira as stopwords da lista de palavras
				List<String> listaPalavrasSemStopwords = PreProcessamento.removerStopwods(listaPalavras, caminho);
				
				
				doc.setCurso(lista.get(i).replaceAll(".txt", ""));
				doc.setPalavra(listaPalavrasSemStopwords);
				
				documentos.add(doc);
				
				
				//CALCULO DE FREQUENCIA DO TERMO NO DOCUMENTO
				//calcula a frequencia que cada palavra aparece nos documentos
				//frequenciaDosTermos = calcularFrequenciaTermos(listaPalavrasSemStopwords, frequenciaDosTermos,lista.get(i).replaceAll(".txt", ""));
			}

			List<FrequenciaTermos> freq = calcularFrequenciaTermos(documentos);
			
			
			System.out.println("Teste2");
	
			Hashtable<String, ArrayList<FrequenciaTermos>> hashtable = hash.gerarHash(frequenciaDosTermos);
			
			retorno = hash.GerarArquivoInvertido(hashtable, caminho);
		
		}catch(Exception e){
			System.out.println("Erro ao gerar arquivo invertido: " + e);
			return retorno;
		}
		
		return retorno;
	}
	
	/**
	 * 
	 * @param nome
	 * @param descricao
	 * @param caminho
	 * @return
	 */
	public static boolean salvarCurso(String curso, String descricao, String caminho){
		
		boolean status = Arquivo.gravarCurso(curso, descricao, caminho);
		
		return status;
	}
}
