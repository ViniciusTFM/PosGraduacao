package br.puc.entidades;

public class PesoCurso {
	
	//atributos
	private String curso;
	private int peso;
	private int pesoPalavras;
	
	//construtor
	public PesoCurso(){
		this.curso = "";
		this.peso = 0;
	}

	//getters e setters
	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getPesoPalavras() {
		return pesoPalavras;
	}

	public void setPesoPalavras(int pesoPalavras) {
		this.pesoPalavras = pesoPalavras;
	}
	
	

}
