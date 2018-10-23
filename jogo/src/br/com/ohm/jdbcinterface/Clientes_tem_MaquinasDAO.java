package br.com.ohm.jdbcinterface;

import java.util.List;

import br.com.ohm.classes.Clientes_tem_Maquinas;
import br.com.ohm.classes.Maquina;

public interface Clientes_tem_MaquinasDAO {

	public List<Clientes_tem_Maquinas> clientesProcuramMaquinas(String id);

	public boolean inserirMaquinasRespectivasFaseDoJogador(String clienteId,List<Maquina> maquinas);

	public boolean inserirMaquinaEspecial(int clienteId);

	public boolean deletarMaquinas(int maquinasId,String clientesId);

	public boolean salvarMaquinas(List<Clientes_tem_Maquinas> listaDeMaquinasDoCliente,String clienteId);
}
