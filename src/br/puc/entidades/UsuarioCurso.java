package br.puc.entidades;

public class UsuarioCurso {

	private String nome;
	private String curso;
	
	public UsuarioCurso(){
		this.nome = "";
		this.curso = "";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
	
}
