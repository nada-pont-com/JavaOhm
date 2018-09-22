package br.com.ohm.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ohm.classes.Bateria;
import br.com.ohm.jdbcinterface.BateriasDAO;

public class JDBCBateriasDAO implements BateriasDAO{
	
	private Connection conexao;
	
	public JDBCBateriasDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public List<Bateria> BuscaBaterias(String fase){
		Bateria bateria = null;
		List<Bateria> listadeBaterias = new ArrayList<Bateria>();
		try {
			String comando ="SELECT * FROM baterias WHERE fase=?";
			PreparedStatement p = conexao.prepareStatement(comando);
			p.setString(1, fase);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				bateria = new Bateria();
				int fasebd = rs.getInt("fase");
				int id = rs.getInt("id");
				int armazenamento = rs.getInt("armazenamento");
				String nome = rs.getString("nome");
				String desc = rs.getString("desc");
				int valor = rs.getInt("valor");
				
				bateria.setArmazenamento(armazenamento);
				bateria.setDesc(desc);
				bateria.setId(id);
				bateria.setNome(nome);
				bateria.setFase(fasebd);
				bateria.setValor(valor);
				
				listadeBaterias.add(bateria);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listadeBaterias;
	}

}
