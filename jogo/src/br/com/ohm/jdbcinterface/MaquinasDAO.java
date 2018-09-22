package br.com.ohm.jdbcinterface;

import java.util.List;

import br.com.ohm.classes.Maquina;

public interface MaquinasDAO {

	
	public List<Maquina> buscaMaquinas(String fase);
}
