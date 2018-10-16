package br.com.ohm.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ohm.classes.Clientes_tem_Pesquisas;
import br.com.ohm.classes.Pesquisa;
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
                int pesquisas_id = rs.getInt("pesquisas_id");
                String tempo = rs.getString("tempo");
                String estado = rs.getString("estado");

                clientes_tem_Pesquisas.setClientes_id(clientes_id);
                clientes_tem_Pesquisas.setEstado(estado);
                clientes_tem_Pesquisas.setPesquisas_id(pesquisas_id);
                clientes_tem_Pesquisas.setTempo(tempo);

                listaDePesquisasDoCliente.add(clientes_tem_Pesquisas);

            }
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return listaDePesquisasDoCliente;
    }

    public boolean inserirPesquisas(String clienteId,List<Pesquisa> listaDePesquisas){
        String comando = "INSERT INTO clientes_tem_pesquisas (clientes_id,pesquisas_id,tempo,estado) VALUES (?,?,?,?)";
        try {
            for(int i = 0;i<listaDePesquisas.size();i++){
                PreparedStatement p = this.conexao.prepareStatement(comando);
                p.setString(1, clienteId);
                p.setInt(2, listaDePesquisas.get(i).getId());
                p.setString(3, listaDePesquisas.get(i).getTempo());
                p.setString(4, "n iniciada");
                p.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean inserirPesquisa(String clienteId,Pesquisa pesquisa){
        String comando = "INSERT INTO clientes_tem_pesquisas (clientes_id,pesquisas_id,tempo,estado) VALUES (?,?,?,?)";
        try {
            PreparedStatement p = this.conexao.prepareStatement(comando);
            p.setString(1, clienteId);
            p.setInt(2, pesquisa.getId());
            p.setString(3, pesquisa.getTempo());
            p.setString(4, "n iniciada");
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean salvarPesquisas(List<Clientes_tem_Pesquisas> listaDePesquisasDoCliente,String clienteId){
        String comando = "UPDATE clientes_tem_pesquisas SET estado=?, tempo=? WHERE pesquisas_id=? AND clientes_id=?";
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			for (int i = 0; i < listaDePesquisasDoCliente.size(); i++){
				p.setString(1, listaDePesquisasDoCliente.get(i).getEstado());
				p.setString(2, listaDePesquisasDoCliente.get(i).getTempo());
				p.setInt(3, listaDePesquisasDoCliente.get(i).getPesquisas_id());
                p.setString(4, clienteId);
                p.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
    }

    public boolean deletarPesquisa(int pesquisaId,String clienteId){
        String comando = "DELETE FROM clientes_tem_pesquisas WHERE pesquisas_id=? AND clientes_id = ?";
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, pesquisaId);
			p.setString(2, clienteId);
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
        return true;
    }

	public boolean resetarPesquisas(int id){
		try {
            String comando = "DELETE FROM clientes_tem_pesquisas WHERE clientes_id = ?";
            PreparedStatement p = this.conexao.prepareStatement(comando);
            p.setInt(1, id);
            p.execute();	
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;		
	}

}
