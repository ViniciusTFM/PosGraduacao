package br.puc.funcoes;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreProcessamento {
	
	/**
	 * TODO
	 * @function 	remocerAcentos remove todos acentos da String passada como parametro
	 * @param 		str
	 * @return 		string sem acentos
	 */
	public static String removerAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * TODO
	 * @function	removerCaracteresEspeciais - remove os caracteres especiais das palavras
	 * @param 		str - palavra
	 * @return		palavra sem os caracteres especiais 
	 */
	public static String removerCaracteresEspeciais(String str) {
		
		String[] caracteresEspeciais = { "\\.", ",", "-", ":", ";", "\\(", "\\)", "ª", "\\|", "\\\\", "\\/", "°", "\\@", "\\#", "\\$", "\\%", "\\&", "\\!" };

		// remove caracteres especiais
		for (int j = 0; j < caracteresEspeciais.length; j++) {
			str = str.replaceAll(caracteresEspeciais[j], "");
		}
		
		return str;
	}
	
	/**
	 * TODO
	 * @function 	remocerStopwords - remove todas as stopwords que estiver na lista
	 *           	de palavras recebidas por parametro
	 * @param 		listaPalavras
	 * @return 		lista de palavras sem as stopwords
	 */
	public static List<String> removerStopwods(List<String> listaPalavras, String caminho) {

		List<String> listaStopwords = new ArrayList<String>();
		listaStopwords = Arquivo.lerListaStopwords(caminho);

		try {

			// remover as stopwords
			for (int j = 0; j < listaStopwords.size(); j++) {
				for (int i = 0; i < listaPalavras.size(); i++) {

					if (listaStopwords.get(j).equalsIgnoreCase(listaPalavras.get(i))) {
						listaPalavras.remove(i);
					}
				}
			}

			// removendo linhas em branco
			for (int i = 0; i < listaPalavras.size(); i++) {
				if (listaPalavras.get(i).equals("")) {
					listaPalavras.remove(i);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao remover as stopwords da lista de palavras" + e);
		}

		return listaPalavras;
	}
	
	/**
	 * TODO
	 * @function	normalizarCompetencias - faz o pré-processamento nas competencias do usuario
	 * @param 		lista - lista de competencias
	 * @param 		caminho - caminho do diretorio dos arquivos txt
	 * @return		lista de competencias normalizadas
	 */
	public static List<String> normalizarCompetencias(List<String> lista, String caminho){
		
		List<String> listaPalavras = new ArrayList<String>();
		List<String> listaPalavrasSemStopwords = null;
		
		for(int i = 0; i < lista.size(); i++){
			String palavra = lista.get(i);
			String palavra2 = "";
			
			String[] teste = lista.get(i).split(" ");
			
			for(int j = 0; j < teste.length; j++){
				
					palavra2 = teste[j];
	
					palavra2 = removerAcentos(palavra2);
	
					//palavra2 = palavra2.toUpperCase(); // Maiuscula
					palavra2 = palavra2.toLowerCase();  // Minuscula
	
					// remove caracteres especiais
					palavra2 = removerCaracteresEspeciais(palavra2);

					listaPalavras.add(palavra2);
				}

			listaPalavrasSemStopwords = PreProcessamento.removerStopwods(listaPalavras, caminho);
		}
		
		return listaPalavrasSemStopwords;
	}
}
