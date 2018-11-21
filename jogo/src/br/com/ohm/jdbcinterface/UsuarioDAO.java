package br.com.ohm.jdbcinterface;

import java.util.List;

import br.com.ohm.classes.Usuario;

public interface UsuarioDAO{
	
	public Usuario buscaLogin(String login);
	
	public boolean inserir(Usuario usuario);

	public List<Usuario> buscaPorLogin(String login);
	
	public String geraSenha();
	
	public String validaEmail(String email);
	
	public boolean atualizar(Usuario usuario);
	
	public boolean remover(String login, Usuario usuario);
	
	public void atualizarSenha(String novaSenha, String login);
}
