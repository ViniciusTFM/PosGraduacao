package br.puc.entidades;

import java.util.ArrayList;
import java.util.List;

public class Documentos {

			//Atributos

		private String curso;
		private List<String> termos;
		
		//Construtor Padr�o
		
		public Documentos(){
			this.curso = "";
			this.termos = new ArrayList<String>();
		}

		//Getters e Setters
		
		public String getCurso() {
			return curso;
		}

		public void setCurso(String curso) {
			this.curso = curso;
		}

		public List<String> getTermo() {
			return termos;
		}

		public void setPalavra(List<String> termos) {
			this.termos = termos;
		}

	
}
