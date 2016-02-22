package br.puc.entidades;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	//atributos
	private int id;
	private String nome;
	private String email;
	private List<String> competencias;
	
	//construtor
	public Usuario(){
		this.id = 0;
		this.nome = "";
		this.email = "";
		this.competencias = new ArrayList<String>();
	}

	//getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<String> competencias) {
		this.competencias = competencias;
	}
}
