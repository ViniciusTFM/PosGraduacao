package br.puc.funcoes;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import br.puc.entidades.FrequenciaTermos;;

public class Hash {

	/**
	 * TODO
	 * @function	gerarHash - recebe uma lista de objetos e os insere em uma tabela hash onde cada 
	 * 				posição possui uma lista de objetos
	 * @param 		listaPalavras -  contem todas as palavras com seu peso por documento
	 * @return		tabela hash 
	 */
	public Hashtable<String, ArrayList<FrequenciaTermos>> gerarHash (List<FrequenciaTermos> listaPalavras){
		
		Hashtable<String, ArrayList<FrequenciaTermos>> hash = new Hashtable<String, ArrayList<FrequenciaTermos>>();
		
		//insere dados na hash
		for(int i = 0; i < listaPalavras.size(); i++){
			
	        if((hash.get(listaPalavras.get(i).getPalavra())) == null){
	        	
	        	List<FrequenciaTermos> lista = new ArrayList<FrequenciaTermos>();
	        	lista.add(listaPalavras.get(i));
	        	
	        	hash.put(listaPalavras.get(i).getPalavra(), (ArrayList<FrequenciaTermos>) lista);
	        }else{
	        	//Busco a lista do hash
	        	List<FrequenciaTermos> lista = hash.get(listaPalavras.get(i).getPalavra());
	        	//add o novo objeto
	        	lista.add(listaPalavras.get(i));
	        	//Insiro a lista no hash
		        hash.put(listaPalavras.get(i).getPalavra(), (ArrayList<FrequenciaTermos>) lista);
	        }
		}
		return hash;
	}

	
	
	/**
	 * TODO
	 * @function	GerarArquivoInvertido gera um arquivo txt com as informações contidas na tabela hash
	 * 				por padrão, a função salva os objetos no txt conforme abaixo:
	 * 
	 * 				palavra; frequencia do termo no documento; nome do documento |
	 * 
	 * 				onde: 
	 * 					palavra = termo 
	 * 					frequencia do termo no documento = Quantidade de vezes que a palavra aparece no documento
	 * 					nome do documento = Por padrão é o nome do curso
	 * 					 / = Identificador de fim de objeto
	 * 
	 * @param 		tabela hash do tipo hashtable	
	 * @param 		caminho - caminho da pasta de documentos txt
	 * @return		TRUE se salvar com sucesso
	 * 				FALSE caso de algum erro ao salvar
	 */
	public boolean GerarArquivoInvertido(Hashtable<String, ArrayList<FrequenciaTermos>> hash, String caminho) {

		boolean status = false;

		try{
			
			FileWriter arq = new FileWriter(caminho + "/arquivo/Arquivo Invertido/arquivoInvertido.txt");
			
			PrintWriter gravarArq = new PrintWriter(arq);
			
			for(ArrayList<FrequenciaTermos> h : hash.values()) {
				
				for(int j=0; j<h.size(); j++){
				
					gravarArq.printf(h.get(j).getPalavra() + ";");
					gravarArq.printf(h.get(j).getFrequencia() + ";");
					gravarArq.printf(h.get(j).getCurso());
					gravarArq.printf("/"); //identificador de fim de objeto
				}
				gravarArq.printf("%n"); //quebra de linha
			}

			arq.close();
			status = true;
			
		}catch(Exception e){
			System.out.println("Erro ao salvar arquivo invertido em arquivo: " + e);
		}
		
		return status;
	}
}
