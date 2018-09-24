package br.com.ohm.classes;

import java.io.Serializable;

public class Bateria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	
	private int id;
	private String nome;
	private int fase;
	private int armazenamento;
	private String desc; 
	private int valor;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setFase(int fase) {
		this.fase = fase;
	}
	
	public int getFase() {
		return this.fase;
	}
	
	public void setArmazenamento(int armazenamento) {
		this.armazenamento = armazenamento;
	}
	
	public int getArmazenamento() {
		return this.armazenamento;
	}
	
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return this.desc;
	}

	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return this.valor;
	}
	
}
