package br.puc.funcoes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import br.puc.entidades.FrequenciaTermos;

/**
 * @class 	A Classe ARQUIVO � responsavel por fazer a leitura dos arquivos
 *        	referentes aos cursos e apartir deles gerar o arquivo invertido 
 *        	com as palavras chave referentes a cada curso.
 * 
 * @author 	Vinicius Tadeu Ferreira Moreira
 */
public class Arquivo {
	
	/**
	 * TODO
	 * @function 	lerDiretorios - verifica todos os arquivos
	 * @param		caminho - caminho da pasta de documentos txt 
	 * @return 		Lista os nomes de todos os arquivos de cursos j� cadastrados
	 */
	public List<String> lerDiretorios(String caminho) {

		List<String> diretorios = new ArrayList<String>();

		try {

			File file = new File(caminho + "/arquivo/Cursos");
			File afile[] = file.listFiles();
			
			System.out.println(file);
			
			int i = 0;
			for (int j = afile.length; i < j; i++) {
				String arq = afile[i].getName();
				diretorios.add(arq);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao ler diretorio: " + e);
		}

		return (diretorios);
	}
	
	/**
	 * TODO
	 * @function 	listaStopwords - faz a leitura do arquivo de stopwords
	 * @param 		caminho - caminho da pasta de documentos txt 
	 * @return 		lista de stopwords
	 */
	public static List<String> lerListaStopwords(String caminho) {

		List<String> Stopwods = new ArrayList<String>();
		Scanner scanner;

		try {
			scanner = new Scanner(new FileReader(caminho + "/arquivo/Stopwords/stopwords.txt"));

			while (scanner.hasNext()) {
				Stopwods.add(scanner.next());
			}
		} catch (Exception e) {
			System.out.println("Erro ao fazer a leitura da lista de stopwords: " + e);
		}
		return Stopwods;
	}
	
	/**
	 * TODO
	 * @function 	lerArquivoCurso - l� o conteudo de um arquivo especificado
	 * @param 		documento - Nome do documento a ser lido
	 * @param 		caminho - caminho do documento
	 * @return 		lista com todos os termos de um documento
	 */
	public List<String> lerArquivoCurso(String documento, String caminho) {

		List<String> listaPalavras = new ArrayList<String>();

		Scanner scanner;

		try {
			
			scanner = new Scanner(new FileReader(caminho + "/arquivo/Cursos/" + documento));

			while (scanner.hasNext()) {

				String palavra = scanner.next();
				String palavra2 = "";

				for (int i = 0; i < palavra.length(); i++) {

					// normalizando a palavra
					palavra2 = palavra2 + palavra.charAt(i);

					palavra2 = PreProcessamento.removerAcentos(palavra2);

					//palavra2 = palavra2.toUpperCase(); // Maiuscula
					palavra2 = palavra2.toLowerCase();  // Minuscula

					// remove caracteres especiais
					palavra2 = PreProcessamento.removerCaracteresEspeciais(palavra2);
				}

				listaPalavras.add(palavra2);

			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao Ler o arquivo do curso " + documento + ": " + e);
		}

		return listaPalavras;
	}

	
	/**
	 * TODO
	 * @function 	lerHash - L� hash do arquivo txt
	 * @param 		caminho - caminho da pasta de documentos txt
	 * @return		Tabela hash do tipo Hashtable
	 */
	@SuppressWarnings("resource")
	public static Hashtable<String, ArrayList<FrequenciaTermos>> lerHash (String caminho){
		
		Hashtable<String, ArrayList<FrequenciaTermos>> hash = new Hashtable<String, ArrayList<FrequenciaTermos>>();
		
		//variavel caminho contem o caminho absoluto do arquivo hash
		caminho = caminho + "/arquivo/Arquivo Invertido/arquivoInvertido.txt";
		
		try {   
            //abre o arquivo   
            FileReader fr = new FileReader(caminho);   
            BufferedReader br = new BufferedReader(fr);   
            String temp; 
            String[] campos = null;
            
            /*	A cada intera��o, � uma linha do arquivo e atribui-a a temp 
             *	Le cada linha e coloca os registros no formato abaixo:
             *	curso;palavra;frequencia/
             *	onde: 
             *	 ;  = separador de atributo em um objeto
             *	 /	= separador de objetos em uma lista
             */
            while ((temp = br.readLine()) != null)   
            {
            	List<FrequenciaTermos> lista = new ArrayList<FrequenciaTermos>();
                String indiceHash = "";
            	String[] a = temp.split("/");   
                
            	for( String each : a ){
            		FrequenciaTermos ftr = new FrequenciaTermos();
            		
                	campos = each.split(";");
                	
                	indiceHash = campos[0];
                    
                	ftr.setPalavra(campos[0]);   
                    ftr.setFrequencia(Integer.parseInt(campos[1]));  
                    ftr.setCurso(campos[2]);
                    
                    lista.add(ftr);
                }
                
            	hash.put(indiceHash, (ArrayList<FrequenciaTermos>) lista);
                indiceHash = "";
            }   
        }   
        catch (FileNotFoundException el){   
            System.out.println("Arquivo não Encontrado: " + el);   
        }   
        catch (IOException e){   
            e.printStackTrace();   
        }   
		
		return hash;
	}
	
	/**
	 * TODO
	 * @param curso
	 * @param descricao
	 * @param caminho
	 * @return
	 */
	public static boolean gravarCurso(String curso, String descricao, String caminho){

		System.out.println(caminho);
		boolean status = false;
		caminho = caminho + "/arquivo/Cursos/"+curso+".txt";
		
		try{
			
			FileWriter arq = new FileWriter(caminho);
			
			PrintWriter gravarArq = new PrintWriter(arq);

			String[] a = descricao.split("\r\n"); 
			
			for(int i = 0; i<a.length; i++) {
				
					gravarArq.printf(a[i]); //linha
					gravarArq.printf("%n"); //quebra de linha
			}

			arq.close();
			status = true;
			
		}catch(Exception e){
            System.out.println("Arquivo não Encontrado: " + e);   
		}
		
		return status;
	}
	
}
