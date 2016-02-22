package br.puc.entidades;

public class Competencia {
	
	//atributos
	
	private int id;
	private String competencia;
	
	//construtor
	
	public Competencia(){
		this.id = 0;
		this.competencia = "";
	}

	//getters e setters 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}
}
