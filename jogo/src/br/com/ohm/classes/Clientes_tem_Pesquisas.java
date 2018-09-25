package br.com.ohm.classes;

import java.io.Serializable;

public class Clientes_tem_Pesquisas implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int clientes_id;
	private int pesquisas_id;
	private String tempo;
    private String estado;
    
    public void setClientes_id(int clientes_id) {
		this.clientes_id = clientes_id;
	}
	
	public int getClientes_id() {
		return this.clientes_id;
	}
	
	public void setPesquisas_id(int pesquisas_id) {
		this.pesquisas_id = pesquisas_id;
	}
	
	public int getPesquisas_id() {
		return this.pesquisas_id;
	}
	
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	
	public String getTempo() {
		return this.tempo;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getEstado() {
		return this.estado;
	}

}
