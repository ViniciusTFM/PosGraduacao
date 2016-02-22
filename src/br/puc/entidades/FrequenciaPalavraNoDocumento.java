package br.puc.entidades;

public class FrequenciaPalavraNoDocumento {

	private String palavra;
	private int frequencia;
	
	public FrequenciaPalavraNoDocumento(){
		this.palavra = "";
		this.frequencia = 0;
	}

	
	
	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public int getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(int freq) {
		this.frequencia = freq;
	}

	
	
}
