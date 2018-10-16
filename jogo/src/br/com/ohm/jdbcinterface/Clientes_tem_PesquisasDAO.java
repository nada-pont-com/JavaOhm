package br.com.ohm.jdbcinterface;

import java.util.List;

import br.com.ohm.classes.Clientes_tem_Pesquisas;
import br.com.ohm.classes.Pesquisa;

public interface Clientes_tem_PesquisasDAO {

    public List<Clientes_tem_Pesquisas> buscaPesquisasDosClientes(String clienteId);

    public boolean inserirPesquisas(String clienteId,List<Pesquisa> listaDePesquisas);

    public boolean inserirPesquisa(String clienteId, Pesquisa pesquisa);
    
    public boolean salvarPesquisas(List<Clientes_tem_Pesquisas> listaDePesquisasDoCliente,String clienteId);
    
    public boolean deletarPesquisa(int pesquisaId,String clienteId);
    
    public boolean resetarPesquisas(int id);

   

}
