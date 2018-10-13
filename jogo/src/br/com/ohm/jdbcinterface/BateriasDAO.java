package br.com.ohm.jdbcinterface;

import java.util.List;

import br.com.ohm.classes.Bateria;

public interface BateriasDAO {


    public List<Bateria> BuscaBaterias(String fase);
    
    public List<Bateria> BuscaBateriasPorId(int id);
}
