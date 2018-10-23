package br.com.ohm.classes;

import java.io.Serializable;

public class Clientes_tem_Maquinas implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int clientes_id;
	private int maquinas_id;
	private int multiplicador;
	private int quantidade;
	private String pesquisada;
	
	public void setClientes_id(int clientes_id) {
		this.clientes_id = clientes_id;
	}
	
	public int getClientes_id() {
		return this.clientes_id;
	}
	
	public void setMaquinas_id(int maquinas_id) {
		this.maquinas_id = maquinas_id;
	}
	
	public int getMaquinas_id() {
		return this.maquinas_id;
	}
	
	public void setMultiplicador(int multiplicador) {
		this.multiplicador = multiplicador;
	}
	
	public int getMultiplicador() {
		return this.multiplicador;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}

	public void setPesquisada(String pesquisada) {
		this.pesquisada = pesquisada;
	}
	
	public String getPesquisada() {
		return this.pesquisada;
	}
}
