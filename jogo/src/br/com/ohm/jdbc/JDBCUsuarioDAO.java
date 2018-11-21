package br.com.ohm.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ohm.classes.Cripitografia;
import br.com.ohm.classes.Usuario;
import br.com.ohm.jdbcinterface.UsuarioDAO;

public class JDBCUsuarioDAO implements UsuarioDAO{
	
	private Connection conexao;
	public JDBCUsuarioDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public Usuario buscaLogin(String login){
		Usuario usuario = new Usuario();
		try {
			
		String comando = "SELECT * FROM usuarios WHERE login=?";
		PreparedStatement p = this.conexao.prepareStatement(comando);
		p.setString(1,login);
		ResultSet rs = p.executeQuery();
		while(rs.next()) {
			String loginbd = rs.getString("login");
			String nome = rs.getString("nome");
			String senha = rs.getString("senha");
			String email = rs.getString("email");
			String permissao = rs.getString("permissao");
			String nascimento = rs.getString("nascimento");
			
			usuario.setLogin(loginbd);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setPermissao(permissao);
			usuario.setNascimento(usuario.converteNascimentoParaFrontEnd(nascimento));
			
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public boolean inserir(Usuario usuario) {
		String comando = "INSERT INTO usuarios values (?,?,?,?,?,?,?)";
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setString(1, usuario.getLogin());
			p.setString(2, Cripitografia.criptografaSenha(usuario.getSenha()));
			p.setString(3, usuario.getNome());
			p.setString(4, usuario.getEmail());
			p.setString(5, usuario.getUltimoAcesso());
			p.setString(6, usuario.getPermissao());
			p.setString(7, usuario.converteNascimentoParaBd(usuario.getNascimento()));
			p.execute();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<Usuario> buscaPorLogin(String login){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			Usuario usuario = null;
			String comando = "SELECT * FROM usuarios ";
			if (login!="") {
				comando+= "WHERE login LIKE '%"+login+"%'";
			}
			
			PreparedStatement p = this.conexao.prepareStatement(comando);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				usuario = new Usuario();
				String loginbd = rs.getString("login");
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				String email = rs.getString("email");
				String permissao = rs.getString("permissao");
				String nascimento = rs.getString("nascimento");
				
				usuario.setLogin(loginbd);
				usuario.setSenha(senha);
				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setPermissao(permissao);
				usuario.setNascimento(usuario.converteNascimentoParaFrontEnd(nascimento));
				
				usuarios.add(usuario);
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return usuarios;
	}

	public String geraSenha(){

	    String[] carct ={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	    String geradorSenha="";


	    for (int x=0; x<10; x++){
	        int j = (int) (Math.random()*carct.length);
	        geradorSenha += carct[j];
		}
	    return geradorSenha;
	}

	public String validaEmail(String email){
		String login = "";
		try {
			
		String comando = "SELECT * FROM usuarios WHERE email=?";
		PreparedStatement p = this.conexao.prepareStatement(comando);
		p.setString(1,email);
		ResultSet rs = p.executeQuery();
		while(rs.next()) {
			login = rs.getString("login");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return login;
	}
	
	public boolean atualizar(Usuario usuario) {

		String comando = "UPDATE usuarios SET senha=?, nome=?, nascimento=?, email=?";
		comando += " WHERE login=?";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, Cripitografia.criptografaSenha(usuario.getSenha()));
			p.setString(2, usuario.getNome());
			p.setString(3, usuario.converteNascimentoParaBd(usuario.getNascimento()));
			p.setString(4, usuario.getEmail());
			p.setString(5, usuario.getLogin());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void atualizarUltimoAcesso(Usuario usuario){
		String comando = "UPDATE usuarios SET ultimo_acesso=? WHERE login=?";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, usuario.ultimoAcesso());
			p.setString(2, usuario.getLogin());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean remover(String login, Usuario vitima){
		String comando = "DELETE FROM ";
		Statement p;
			if(vitima.getLogin().equals(login)){			
				comando += "usuarios WHERE login = '" + vitima.getLogin()+"'";
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

	public void atualizarSenha(String novaSenha, String login) {
		// TODO Auto-generated method stub
		try {
			String comando = "UPDATE usuarios SET senha=? WHERE login=?";
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setString(1, Cripitografia.criptografaSenha(novaSenha));
			p.setString(2, login);
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public int buscaAcessos(){
		String comando = "SELECT *, COUNT(ultimo_acesso) AS qtd FROM  usuarios WHERE ultimo_acesso=?";
		Usuario usuario = new Usuario();
		int quant = 0;
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setString(1,usuario.ultimoAcesso());
			ResultSet rs = p.executeQuery();
			while(rs.next()){
				quant = rs.getInt("qtd");
				System.out.println(usuario.ultimoAcesso());

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return quant;
	}
	
}
