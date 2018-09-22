package br.com.ohm.jdbcinterface;

import java.util.List;

import br.com.ohm.classes.Clientes_tem_Maquinas;

public interface Clientes_tem_MaquinasDAO {
	public List<Clientes_tem_Maquinas> clientesProcuramMaquinas(String id);
}
