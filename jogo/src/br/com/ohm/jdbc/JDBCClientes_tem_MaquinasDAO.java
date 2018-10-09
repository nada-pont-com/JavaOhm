package br.com.ohm.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ohm.classes.Clientes_tem_Maquinas;
import br.com.ohm.classes.Maquina;
import br.com.ohm.jdbcinterface.Clientes_tem_MaquinasDAO;

public class JDBCClientes_tem_MaquinasDAO implements Clientes_tem_MaquinasDAO{
	
	private Connection conexao;
	
	public JDBCClientes_tem_MaquinasDAO(Connection conexao){
		this.conexao = conexao;
	}
	
	public List<Clientes_tem_Maquinas> clientesProcuramMaquinas(String id){
		List<Clientes_tem_Maquinas> listaDeClientesTemMaquinas = new ArrayList<Clientes_tem_Maquinas>();
		Clientes_tem_Maquinas maquinas = null;
		try {
			String comando = "SELECT * FROM clientes_tem_maquinas WHERE clientes_id=?";
			PreparedStatement p = conexao.prepareStatement(comando);
			p.setString(1, id);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				maquinas = new Clientes_tem_Maquinas();
				int clientes_id = rs.getInt("clientes_id");
				int maquinas_id = rs.getInt("maquinas_id");
				int multiplicador = rs.getInt("multiplicador");
				int quantidade = rs.getInt("quantidade");
				
				maquinas.setClientes_id(clientes_id);
				maquinas.setMaquinas_id(maquinas_id);
				maquinas.setMultiplicador(multiplicador);
				maquinas.setQuantidade(quantidade);
				
				listaDeClientesTemMaquinas.add(maquinas);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDeClientesTemMaquinas;
	}
	
	public boolean inserirMaquinasRespectivasFaseDoJogador(String clienteId,List<Maquina> maquinas) {
		try {
			String comando = "INSERT INTO clientes_tem_maquinas (clientes_id,maquinas_id,multiplicador,quantidade) VALUES(?,?,?,?)";
			for(int i = 0;i<maquinas.size();i++) {
				if(maquinas.get(i).getId()!=1) {
					PreparedStatement p = conexao.prepareStatement(comando);
					p.setString(1, clienteId);
					p.setInt(2,maquinas.get(i).getId());
					p.setInt(3, 1);
					p.setInt(4, 0);
					p.execute();
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean inserirMaquinaEspecial(int clienteId) {
		// TODO Auto-generated method stub
		String comando = "INSERT INTO clientes_tem_maquinas (clientes_id,maquinas_id,multiplicador,quantidade) VALUES(?,?,?,?)";
		try {
			PreparedStatement p = conexao.prepareStatement(comando);
			p.setInt(1, clienteId);
			p.setInt(2,1);
			p.setInt(3, 1);
			p.setInt(4, 1);
			p.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deletarMaquinas(int maquinasId,String clientesId){
		String comando = "DELETE FROM clientes_tem_maquinas WHERE maquinas_id=? AND clientes_id = ?";
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, maquinasId);
			p.setString(2, clientesId);
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean salvarMaquinas(List<Clientes_tem_Maquinas> listaDeMaquinasDoCliente,String clienteId){
		String comando = "UPDATE clientes_tem_maquinas SET quantidade=?, multiplicador=? WHERE maquinas_id=? AND clientes_id=?";
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			for (int i = 0; i < listaDeMaquinasDoCliente.size(); i++) {
				p.setInt(1, listaDeMaquinasDoCliente.get(i).getQuantidade());
				p.setInt(2, listaDeMaquinasDoCliente.get(i).getMultiplicador());
				p.setInt(3, listaDeMaquinasDoCliente.get(i).getMaquinas_id());
				p.setString(4, clienteId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean resetarMaquinas(int id) {
		try {
			String comando = "DELETE FROM clientes_tem_maquinas WHERE clientes_id = ? AND maquinas_id!=1";
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
