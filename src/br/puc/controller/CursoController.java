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
				
				//Calcular TFIDF
				List<FrequenciaTermos> frequenciaTermos = CalcularTFIDF(tf, idf, docs.get(i).getCurso());
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
			
				IDF idf = new IDF();
				int cont = 0;

				String termo = listaFrequencias.get(i).getPalavra();
				idf.setPalavra(termo);
				
				
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
				
				double calculo = Math.log((double)qtdDocumentos/(double)cont);
				
				System.out.println("Log("+qtdDocumentos+"/"+cont+") = " + calculo);
				
				idf.setIdf(calculo);
				
				listIDF.add(idf);
			}
		}catch(Exception e){
			System.out.println("Erro ao calcular IDF: " + e);
		}
		
		return listIDF;
	}
	
	/**
	 * 
	 * @param tf
	 * @param idf
	 * @param documento
	 * @return
	 */
	public static List<FrequenciaTermos> CalcularTFIDF(List<TF> tf, List<IDF> idf, String documento){
		
		List<FrequenciaTermos> freq = new ArrayList<FrequenciaTermos>();
	
		System.out.println(tf.size());
		System.out.println(idf.size());
		
		
		
		for(int i = 0; i < tf.size(); i++){
			
			if(tf.get(i).getPalavra().equals(idf.get(i).getPalavra())){
				FrequenciaTermos obj = new FrequenciaTermos();
				
				double tfidf = (tf.get(i).getTF() * idf.get(i).getIdf());
				
				obj.setCurso(documento);
				obj.setFrequencia(tfidf);
				obj.setPalavra(tf.get(i).getPalavra());
		
				System.out.println("Curso"+documento+"/ Frequencia: "+tfidf+"/ Palavra: "+ tf.get(i).getPalavra());
				
				freq.add(obj);
			}
		}
		
		
		return freq;
	}
	
	/**
	 * 
	 * @param lista
	 * @return
	 */
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
	 * @function	gerarArquivoInvertido faz todas as operaï¿½ï¿½es necessarias para a criaï¿½ï¿½o do arquivo invertido,
	 * 				incluindo o prï¿½-processamento dos documentos e a criaï¿½ï¿½o da tabela hash.
	 * @param		caminho - caminho da pasta de documentos txt
	 * @return		TRUE tabela hash criada e salva em arquivo
	 * 				FALSE erro durante a execução do processo
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
