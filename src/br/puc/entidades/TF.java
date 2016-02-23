package br.puc.entidades;

public class TF {

	private String palavra;
	private double tf;
	
	public TF(){
		this.palavra = "";
		this.tf = 0;
	}

	
	
	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public double getTF() {
		return tf;
	}

	public void setTF(double TF) {
		this.tf = TF;
	}

	
	
}
