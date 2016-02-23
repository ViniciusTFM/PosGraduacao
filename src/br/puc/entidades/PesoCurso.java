package br.puc.entidades;

public class PesoCurso {
	
	//atributos
	private String curso;
	private double peso;
	private double pesoPalavras;
	
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

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getPesoPalavras() {
		return pesoPalavras;
	}

	public void setPesoPalavras(double d) {
		this.pesoPalavras = d;
	}
	
	

}
