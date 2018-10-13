package br.com.ohm.jdbcinterface;

import java.util.List;

import br.com.ohm.classes.Bateria;
import br.com.ohm.classes.Clientes_tem_Baterias;

public interface Clientes_tem_BateriasDAO {

    public List<Clientes_tem_Baterias> clientesProcuramBaterias(String id);

    public boolean inserirBateriasRespectivasDaFaseDoJogador(String clienteId,List<Bateria> baterias);

    public boolean salvarBaterias(List<Clientes_tem_Baterias> listaDeBateriasDoCliente,String clienteId);

    public boolean deletarBaterias(int bateriasId,String clientesId);

    public boolean resetarBaterias(int id);
}
