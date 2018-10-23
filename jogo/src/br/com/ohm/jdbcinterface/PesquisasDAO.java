package br.com.ohm.jdbcinterface;

import java.util.List;

import br.com.ohm.classes.Pesquisa;

public interface PesquisasDAO {
	
	public List<Pesquisa> buscaPesquisas(String fase);

	public List<Pesquisa> buscaPesquisasPorId(int id);
}
