package br.com.ohm.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ohm.classes.Cliente;
import br.com.ohm.classes.Denuncia;
import br.com.ohm.classes.Usuario;
import br.com.ohm.jdbcinterface.DenunciaDAO;

public class JDBCDenunciaDAO implements DenunciaDAO{

	private Connection conexao;
	
	public JDBCDenunciaDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public boolean inserirDenuncia(Denuncia denuncia,List<Cliente> cliente) {
		String comando = "INSERT INTO denuncias values (?,?,?,?)";
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setString(1,null);
			p.setString(2,denuncia.getDenuncia());
			p.setString(4,denuncia.getLoginDenuncia());
			p.setInt(3,cliente.get(0).getId());
			System.out.println(p);
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean removerDenunciaPorCliente(Cliente login, Usuario usuario) {
		String comando = "DELETE FROM denuncias WHERE clientes_id = " + login.getId();
		Statement p;
		
		if(usuario.getLogin().equals(login.getLogin())){
			
			try{
				p = this.conexao.createStatement();
				p.execute(comando);
			} catch(SQLException e){
				e.printStackTrace();
			}
		}else{
			return false;
		}
		return true;
	}

	public List<Denuncia> buscaDenuncia(){
		
		List<Denuncia> denuncias = new ArrayList<Denuncia>();
		Denuncia denuncia = null;
		
		String comando = "SELECT * FROM denuncias";
		try{
			PreparedStatement p = this.conexao.prepareStatement(comando);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				denuncia = new Denuncia();
				denuncia.setId(rs.getInt("id"));
				denuncia.setDenuncia(rs.getString("denuncia"));
				denuncia.setClientesId(rs.getInt("clientes_id"));
				denuncia.setLoginDenuncia(rs.getString("usuarios_login"));
				
				denuncias.add(denuncia);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return denuncias;
	}
	
	public boolean removerDenunciaPorId(int id){
		String comando = "DELETE FROM denuncias WHERE id = " + id;
		try{
			Statement p = this.conexao.createStatement();
			p.execute(comando);
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
}
