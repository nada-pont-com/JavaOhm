package br.com.ohm.classes;

import java.io.Serializable;

public class Maquina implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private int valor;
	private int pps;
	private int fase;
	private int subFase;
	private String nome;
	private String desc;
	
	
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
	
	public void setSubFase(int subFase) {
		this.subFase = subFase;
	}
	
	public int getSubFase() {
		return this.subFase;
	}
	
	public void setPps(int pps) {
		this.pps = pps;
	}
	
	public int getPps() {
		return this.pps;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
}
