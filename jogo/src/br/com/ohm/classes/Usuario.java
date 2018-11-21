package br.com.ohm.classes; 

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	private String login;
	private String senha;
	private String nome;
	private String email;
	private String ultimoAcesso;
	private String permissao;
	private String nascimento;
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha() {
		return this.senha;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setUltimoAcesso(String ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}
	
	public String getUltimoAcesso() {
		return this.ultimoAcesso;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}
	
	public String getPermissao() {
		return this.permissao;
	}
	
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	
	public String getNascimento() {
		return this.nascimento;
	}
	
	public String converteNascimentoParaBd(String nascimento) {
		String[] nascimentoDividido = nascimento.split("/"); 
		String nascimentoConvertido = nascimentoDividido[2]+"-"+nascimentoDividido[1]+"-"+nascimentoDividido[0];
		return nascimentoConvertido;
	}
	
	public String converteNascimentoParaFrontEnd(String nascimento) {
		String[] nascimentoDividido = nascimento.split("-"); 
		String nascimentoConvertido = nascimentoDividido[2]+"/"+nascimentoDividido[1]+"/"+nascimentoDividido[0];
		return nascimentoConvertido;
	}

	public String ultimoAcesso() {
		DateFormat data = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String ultimoAcesso = "";
		ultimoAcesso = data.format(date).toString();
		return ultimoAcesso;
	}

	public String verificaPermissao(String validador) {
		String permissao;
		if(!"permisao".equals(validador)) {
			permissao = "1";
		}else {
			permissao = "2";
		}
		return permissao;
	}
}
