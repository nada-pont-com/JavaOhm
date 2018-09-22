package br.com.ohm.classes;

import java.io.Serializable;

public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String login;
	private int energia;
	private int dinheiro;
	private int dinheiroGeral;
	private int franklin;
	private int franklinGeral;
	private int fase;
	private String tempo;
	private int maiorPontuacao;
	private int posicao;
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}
	
	public int getDinheiro() {
		return this.dinheiro;
	}
	
	public void setDinheiroGeral(int dinheiroGeral) {
		this.dinheiroGeral = dinheiroGeral;
	}
	
	public int getDinheiroGeral() {
		return this.dinheiroGeral;
	}
	
	
	public void setEnergia(int energia) {
		this.energia = energia;
	}
	
	public int getEnergia() {
		return this.energia;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}
	
	public int getFase() {
		return this.fase;
	}

	public void setFranklin(int franklin) {
		this.franklin = franklin;
	}
	
	public int getFranklin() {
		return this.franklin;
	}
	
	public void setFranklinGeral(int franklinGeral) {
		this.franklinGeral = franklinGeral;
	}
	
	public int getFranklinGeral() {
		return this.franklinGeral;
	}

	public void setMaiorPontuacao(int maiorPontuacao) {
		this.maiorPontuacao = maiorPontuacao;
	}
	
	public int getMaiorPontuacao() {
		return this.maiorPontuacao;
	}
	
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	
	public String getTempo() {
		return this.tempo;
	}
	
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
	public int getPosicao() {
		return this.posicao;
	}
}
