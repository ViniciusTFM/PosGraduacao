package br.puc.entidades;

/** 
 *	Classe que contem os atributos referentes ao TF 
 * 
 */
public class FrequenciaTermos {

	//Atributos

	private String curso;
	private String palavra;
	private double frequencia;
	
	//Construtor Padr�o
	
	public FrequenciaTermos(){
		this.curso = "";
		this.palavra = "";
		this.frequencia = 0;
	}

	//Getters e Setters
	
	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public double getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(double frequencia) {
		this.frequencia = frequencia;
	}
	
}