package br.puc.funcoes;

import java.util.ArrayList;
import java.util.List;

public class teste {

	/*
	
	//CODIGOS UTEIS
	
	
	public static void main(String[] args) {
		Hashtable<String, ArrayList<FrequenciaTermos>> hash = lerHash("");
	}
	
	// verificar em que metodo isso irá encaixar
	public static void main(String[] args) {

		List<String> lista = LerDiretorios();
		List<FrequenciaTermos> frequenciaDosTermos = new ArrayList<FrequenciaTermos>();

		// Gera a lista de objetos com curso/frequencia do termo/termo de todos
		// os arquivos do diretorio

		for (int i = 0; i < lista.size(); i++) {

			List<String> listaPalavras = lerArquivoCurso(lista.get(i));

			List<String> listaPalavrasSemStopwords = removerStopwods(listaPalavras);

			frequenciaDosTermos = calcularFrequenciaTermos(listaPalavrasSemStopwords, frequenciaDosTermos,
					lista.get(i).replaceAll(".txt", ""));
		}

		// Proximo Passo: Chamar função para gerar a hash passando a lista de
		// frequencia dos termos
	}
	

  	//imprime hash
 
 	for(ArrayList<FrequenciaTermos> h : hash.values()) {
	
	for(int j=0; j < h.size(); j++){
	
		System.out.print(h.get(j).getPalavra() + ";");
		System.out.print(h.get(j).getFrequencia() + ";");
		System.out.print(h.get(j).getCurso());
		System.out.print(" "); //identificador de fim de objeto
	}
	System.out.println("\n"); //quebra de linha
	}
	
	
---------- RECOMEMDACAO CONTROLLER
	
	
		public static void main(String[] args) {
			List<String> lista = new ArrayList<String>();
			
			lista.add("infraestrutura");
			lista.add("suporte");
			lista.add("redes");
			lista.add("tcpip");
			lista.add("seguranca");
			lista.add("informacao");
			lista.add("servidores");
			lista.add("windows");
			lista.add("server");
	
			String caminho = "E:/eclipse/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/PosGraduacaoRecommander/WEB-INF";
			
			GerarRecomendacao(lista, caminho);
		
		}
	
	
		for(int i = 0; i < cursosRecomendados.size(); i++){
			System.out.println(cursosRecomendados.get(i));
		}
		
		
		for(int i = 0; i < cursos.size(); i++){
			System.out.print("Curso: " + cursos.get(i).getCurso());
			System.out.print(" Peso: " + cursos.get(i).getPeso());
			System.out.println(" PesoPalavra: " + cursos.get(i).getPesoPalavras());
		}
		System.out.println("\n\n");
	

	*/
}
