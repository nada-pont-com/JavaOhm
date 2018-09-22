package br.com.ohm.jdbcinterface;

import java.util.List;

import br.com.ohm.classes.Cliente;
import br.com.ohm.classes.Usuario;

public interface ClienteDAO {
	public List<Cliente> buscaDadosClientes(String referencia);
	
	public Cliente buscaClientePorId(int id);
	
	public boolean removerCliente(Cliente login, Usuario vitima);
	
	public boolean inserirCliente(String login);
}
