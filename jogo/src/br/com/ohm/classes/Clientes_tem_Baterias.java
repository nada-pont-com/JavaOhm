package br.com.ohm.classes;

import java.io.Serializable;

public class Clientes_tem_Baterias implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int clientesId;
	private int bateriasId;
	private int quantidade;
	
	public void setClientesId(int clientesId) {
		this.clientesId = clientesId;
	}
	
	public int getClientesId() {
		return this.clientesId;
	}
	
	public void setBateriasId(int bateriasId) {
		this.bateriasId = bateriasId;
	}
	
	public int getBateriasId() {
		return bateriasId;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	
}
