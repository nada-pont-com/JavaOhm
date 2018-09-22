package br.com.ohm.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ohm.classes.Cliente;
import br.com.ohm.classes.Usuario;
import br.com.ohm.jdbcinterface.ClienteDAO;

public class JDBCClienteDAO implements ClienteDAO{
	private Connection conexao;
	public JDBCClienteDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public List<Cliente> buscaDadosClientes(String referencia) {
		System.out.println(referencia);
		int cont = 0;
		List<Cliente> clientes = new ArrayList<Cliente>();
		Cliente cliente = null;
		String comando = "SELECT * FROM clientes ORDER BY maior_pontuacao DESC, usuarios_login ASC";
		try {
		PreparedStatement p = this.conexao.prepareStatement(comando);
		ResultSet rs = p.executeQuery();
		while(rs.next()) {
			cont++;
			if(referencia != ""){
				if(referencia.equals(rs.getString("usuarios_login"))){
					System.out.println("entrou");
					cliente = new Cliente();
					cliente.setPosicao(cont);
					cliente.setMaiorPontuacao(rs.getInt("maior_pontuacao"));
					cliente.setLogin(rs.getString("usuarios_login"));
					cliente.setDinheiro(rs.getInt("dinheiro"));
					cliente.setDinheiroGeral(rs.getInt("dinheiro_geral"));
					cliente.setEnergia(rs.getInt("energia"));
					cliente.setFase(rs.getInt("fase"));
					cliente.setFranklin(rs.getInt("franklin"));
					cliente.setFranklinGeral(rs.getInt("franklin_geral"));
					cliente.setId(rs.getInt("id"));
					cliente.setTempo(rs.getString("tempo_jogo"));
					System.out.println("login: "+cliente.getLogin()+"tempo: "+cliente.getTempo()+"id "+cliente.getId()+" posi "+cliente.getPosicao());
					clientes.add(cliente);
				}
			}else{
				cliente = new Cliente();
				cliente.setPosicao(cont);
				cliente.setId(rs.getInt("id"));
				cliente.setMaiorPontuacao(rs.getInt("maior_pontuacao"));
				cliente.setLogin(rs.getString("usuarios_login"));
			
				clientes.add(cliente);
			}
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public boolean removerCliente(Cliente login, Usuario vitima){
		String comando = "DELETE FROM ";
		Statement p;
		
		System.out.println("Vitima login: " + vitima.getLogin());
		System.out.println("Login login: " + login.getLogin());
		if(vitima.getLogin().equals(login.getLogin())){
			String comandocm = comando + "clientes_tem_maquinas WHERE clientes_id = " + login.getId();
			String comandoc = comando + "clientes WHERE id = " + login.getId();
			
			try{
				p = this.conexao.createStatement();
				p.execute(comandocm);
				p.execute(comandoc);
			} catch(SQLException e){
				e.printStackTrace();
			}
		}else{
			return false;
		}
		return true;
	}
	
	public Cliente buscaClientePorId(int id){
		
		Cliente cliente = new Cliente();
		String comando = "SELECT * FROM clientes WHERE id = " + id;
		
		try{
			
			PreparedStatement p = this.conexao.prepareStatement(comando);
			ResultSet rs = p.executeQuery();
			if(rs.next()){
				cliente.setMaiorPontuacao(rs.getInt("maior_pontuacao"));
				cliente.setLogin(rs.getString("usuarios_login"));
				cliente.setDinheiro(rs.getInt("dinheiro"));
				cliente.setDinheiroGeral(rs.getInt("dinheiro_geral"));
				cliente.setEnergia(rs.getInt("energia"));
				cliente.setFase(rs.getInt("fase"));
				cliente.setFranklin(rs.getInt("franklin"));
				cliente.setFranklinGeral(rs.getInt("franklin_geral"));
				cliente.setId(rs.getInt("id"));
				cliente.setTempo(rs.getString("tempo_jogo"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return cliente;
	}

	public boolean inserirCliente(String login) {
		String comando = "INSERT INTO clientes VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	//  '0',   '10',     '0',     '0',       '1', '2000-01-01 00:00:00', '1',   '1',     '1',  'vini', '10'
	//energia,dinheiro,franklin,franklin_g,fase,     tempo,          maior_p,linha_pont,linha_a,login,dinheiro_g
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, 0);
			p.setInt(2, 10);
			p.setInt(3, 0);
			p.setInt(4, 0);
			p.setInt(5, 1);	
			p.setString(6, "2000-01-01 00:00:00");
			p.setInt(7, 10);
			p.setInt(8, 1);
			p.setInt(9, 1);
			p.setString(10, login);
			p.setInt(11, 10);
			System.out.println(p);
			p.execute();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
