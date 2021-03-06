package br.com.ohm.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.ohm.classes.Maquina;
import br.com.ohm.jdbcinterface.MaquinasDAO;

public class JDBCMaquinasDAO implements MaquinasDAO{
	
	private Connection conexao;
	
	public JDBCMaquinasDAO(Connection conexao){
		this.conexao = conexao;
	}
	
	public List<Maquina> buscaMaquinas(String fase){
		List<Maquina> listaDeMaquinas = new ArrayList<Maquina>();
		Maquina maquina = null;
		try {
			String comando = "SELECT * FROM maquinas WHERE fase=? or fase=? or subFase=?";
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setString(1, fase);
			p.setInt(2, 10);
			p.setString(3, fase);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				maquina = new Maquina();
				String nome = rs.getString("nome");
				String desc = rs.getString("desc");
				int valor = rs.getInt("valor");
				int id = rs.getInt("id");
				int fasebd = rs.getInt("fase");
				int subFase = rs.getInt("subFase");
				int pps  = rs.getInt("pps");
				
				maquina.setId(id);
				maquina.setValor(valor);
				maquina.setPps(pps);
				maquina.setFase(fasebd);
				maquina.setSubFase(subFase);
				maquina.setNome(nome);
				maquina.setDesc(desc);
				
				listaDeMaquinas.add(maquina);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaDeMaquinas;
	}

	public List<Maquina> buscaMaquinasPorId(int id){
		
		List<Maquina> listaDeMaquinas = new ArrayList<Maquina>();
		Maquina maquina = null;
		try {
			String comando = "SELECT * FROM maquinas WHERE id=?";
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				maquina = new Maquina();
				String nome = rs.getString("nome");
				String desc = rs.getString("desc");
				int valor = rs.getInt("valor");
				int idbd = rs.getInt("id");
				int fasebd = rs.getInt("fase");
				int subFase = rs.getInt("subFase");
				int pps  = rs.getInt("pps");
				
				maquina.setId(idbd);
				maquina.setValor(valor);
				maquina.setPps(pps);
				maquina.setFase(fasebd);
				maquina.setSubFase(subFase);
				maquina.setNome(nome);
				maquina.setDesc(desc);
				
				listaDeMaquinas.add(maquina);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaDeMaquinas;
	}
}
