package br.com.ohm.classes;

public class Pesquisa {
	
	private int id;
	private int valor;
	private int fase;
	private String tempo;
	private String pesquisa;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return this.valor;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}
	
	public int getFase() {
		return this.fase;
	}
	
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	
	public String getTempo() {
		return this.tempo;
	}
	
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	public String getPesquisa() {
		return this.pesquisa;
	}
}
