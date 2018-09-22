package br.com.ohm.classes;

import java.io.Serializable;

public class Denuncia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String loginDenuncia;
	private int clientesId;
	private String denuncia;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setLoginDenuncia(String loginDenuncia) {
		this.loginDenuncia = loginDenuncia;
	}
	
	public String getLoginDenuncia() {
		return this.loginDenuncia;
	}

	public void setDenuncia(String denuncia) {
		this.denuncia = denuncia;
	}
	
	public String getDenuncia() {
		return this.denuncia;
	}
	
	public void setClientesId(int clientesId) {
		this.clientesId = clientesId;
	}
	
	public int getClientesId() {
		return this.clientesId;
	}
	
	

}
