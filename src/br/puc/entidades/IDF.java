package br.puc.entidades;

public class IDF {

	private String palavra;
	private double idf;
	
	public IDF(){
		this.palavra = "";
		this.idf = 0;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public double getIdf() {
		return idf;
	}

	public void setIdf(double idf) {
		this.idf = idf;
	}
	
}
