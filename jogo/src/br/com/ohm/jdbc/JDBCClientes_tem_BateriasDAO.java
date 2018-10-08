package br.com.ohm.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ohm.classes.Bateria;
import br.com.ohm.classes.Clientes_tem_Baterias;
import br.com.ohm.jdbcinterface.Clientes_tem_BateriasDAO;

public class JDBCClientes_tem_BateriasDAO implements Clientes_tem_BateriasDAO{
	private Connection conexao;
	
	public JDBCClientes_tem_BateriasDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public List<Clientes_tem_Baterias> clientesProcuramBaterias(String id){
		List<Clientes_tem_Baterias> listaDeBateriasDoCliente = new ArrayList<Clientes_tem_Baterias>();
		Clientes_tem_Baterias ClientesTemBaterias = null;
		String comando = "SELECT * FROM clientes_tem_baterias WHERE Clientes_id=?";
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setString(1, id);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				ClientesTemBaterias = new Clientes_tem_Baterias();
				int clientesId = rs.getInt("clientes_id");
				int bateriasId = rs.getInt("baterias_id");
				int quantidade = rs.getInt("quantidade");
				
				ClientesTemBaterias.setBateriasId(bateriasId);
				ClientesTemBaterias.setClientesId(clientesId);
				ClientesTemBaterias.setQuantidade(quantidade);
				
				listaDeBateriasDoCliente.add(ClientesTemBaterias);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listaDeBateriasDoCliente;
	}
	
	public boolean inserirBateriasRespectivasDaFaseDoJogador(String clienteId,List<Bateria> baterias) {
		String comando = "INSERT INTO clientes_tem_baterias (clientes_id,baterias_id,quantidade) VALUES (?,?,?)";
		try {
			for(int i = 0;i < baterias.size();i++) {
					PreparedStatement p = this.conexao.prepareStatement(comando);
					p.setString(1, clienteId);
					p.setInt(2, baterias.get(i).getId());
					p.setInt(3, 1);
					p.execute();
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean salvarBaterias(List<Clientes_tem_Baterias> listaDeBateriasDoCliente,String clienteId){
		String comando = "UPDATE clientes_tem_pesquisas SET quantidade=? WHERE pesquisas_id=? AND clientes_id=?";
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			for (int i = 0; i < listaDeBateriasDoCliente.size(); i++){
				p.setInt(1, listaDeBateriasDoCliente.get(i).getQuantidade());
				p.setInt(2, listaDeBateriasDoCliente.get(i).getBateriasId());
				p.setString(3, clienteId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean resetarBaterias(int id) {
		try {
			String comando = "DELETE FROM clientes_tem_baterias WHERE clientes_id = ?";
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
