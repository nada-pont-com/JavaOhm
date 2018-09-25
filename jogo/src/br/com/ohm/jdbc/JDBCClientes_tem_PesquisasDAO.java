package br.com.ohm.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.ohm.classes.Clientes_tem_Pesquisas;
import br.com.ohm.jdbcinterface.Clientes_tem_PesquisasDAO;

public class JDBCClientes_tem_PesquisasDAO implements Clientes_tem_PesquisasDAO{

    private Connection conexao;

    public JDBCClientes_tem_PesquisasDAO(Connection conexao){
        this.conexao = conexao;
    }


    public List<Clientes_tem_Pesquisas> buscaPesquisasDosClientes(String clienteId){
        List<Clientes_tem_Pesquisas> listaDePesquisasDoCliente = new ArrayList<Clientes_tem_Pesquisas>();
        Clientes_tem_Pesquisas clientes_tem_Pesquisas = null;
        String comando = "SELECT * FROM clientes_tem_pesquisas WHERE clientes_id=?";
        try {
            PreparedStatement p = this.conexao.prepareStatement(comando);
            p.setString(1, clienteId);
            ResultSet rs = p.executeQuery();
            while(rs.next()){
                clientes_tem_Pesquisas = new Clientes_tem_Pesquisas();
                int clientes_id = rs.getInt("clientes_id");
                int pesquisa_id = rs.getInt("pesquisa_id");
            }
        } catch (SQLException e) {
            //TODO: handle exception
        }
        return null;
    }

}
