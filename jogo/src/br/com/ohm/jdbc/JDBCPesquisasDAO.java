package br.com.ohm.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ohm.classes.Pesquisa;
import br.com.ohm.jdbcinterface.PesquisasDAO;

public class JDBCPesquisasDAO implements PesquisasDAO{
	
	private Connection conexao;
	
	public JDBCPesquisasDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public List<Pesquisa> buscaPesquisas(String fase){
		List<Pesquisa> listaDePesquisas = new ArrayList<Pesquisa>();
		Pesquisa pesquisas = null;
		try {
			String comando = "SELECT * FROM pesquisas WHERE fase=? ORDER BY id";
			PreparedStatement p = conexao.prepareStatement(comando);
			p.setString(1, fase);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				pesquisas = new Pesquisa();
				int id = rs.getInt("id");
				int fasebd= rs.getInt("fase");
				int valor = rs.getInt("valor");
				int mudaFase = rs.getInt("mudaFase");
				String pesquisa = rs.getString("pesquisa");
				String tempo = rs.getString("tempo");
				
				pesquisas.setId(id);
				pesquisas.setFase(fasebd);
				pesquisas.setValor(valor);
				pesquisas.setMudaFase(mudaFase);
				pesquisas.setPesquisa(pesquisa);
				pesquisas.setTempo(tempo);
				
				listaDePesquisas.add(pesquisas);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listaDePesquisas;
	}

	public List<Pesquisa> buscaPesquisasPorId(int id){
		List<Pesquisa> listaDePesquisas = new ArrayList<Pesquisa>();
		Pesquisa pesquisa = null;
		try {
			String comando = "SELECT * FROM pesquisas WHERE id=?";
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				pesquisa = new Pesquisa();
				String tempo = rs.getString("tempo");
				int valor = rs.getInt("valor");
				int idBd = rs.getInt("id");
				int fase = rs.getInt("fase");
				int mudaFase = rs.getInt("mudaFase");
				String pesquisaBd = rs.getString("pesquisa");

				pesquisa.setId(idBd);
				pesquisa.setValor(valor);
				pesquisa.setFase(fase);
				pesquisa.setTempo(tempo);
				pesquisa.setMudaFase(mudaFase);
				pesquisa.setPesquisa(pesquisaBd);
				
				listaDePesquisas.add(pesquisa);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaDePesquisas;
	}
}
