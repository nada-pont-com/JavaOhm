package br.com.ohm.jdbcinterface;

import java.util.List;

import br.com.ohm.classes.Cliente;
import br.com.ohm.classes.Denuncia;
import br.com.ohm.classes.Usuario;

public interface DenunciaDAO {

	public boolean inserirDenuncia(Denuncia denuncia,List<Cliente> cliente);
	
	public boolean removerDenunciaPorCliente(Cliente login, Usuario usuario);
	
	public List<Denuncia> buscaDenuncia();
	
	public boolean removerDenunciaPorId(int id);
}
