package br.com.ohm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.com.ohm.classes.Cripitografia;
import br.com.ohm.classes.Usuario;
import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCUsuarioDAO;

import com.google.gson.Gson;



/**
 * Servlet implementation class AlterarDados
 */
@WebServlet("/AlterarDados")
public class AlterarDados extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlterarDados() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			Usuario usuariobd = jdbcUsuario.buscaLogin(request.getParameter("txtlogin"));
			Map<String, String> msg = new HashMap<String, String>();
			if (request.getParameter("txtlogin").equals(usuariobd.getLogin())) {
				String senhaAtualCript = Cripitografia.criptografaSenha(request.getParameter("txtsenhaAtual").toString()); 
				if (senhaAtualCript.equals(usuariobd.getSenha())) {
					Usuario usuario = new Usuario();
					usuario.setLogin(request.getParameter("txtlogin"));
					usuario.setSenha(request.getParameter("txtsenha"));
					usuario.setPermissao(request.getParameter("hdpermissao"));
					usuario.setNome(request.getParameter("txtnome"));
					usuario.setNascimento(request.getParameter("txtnascimento"));
					usuario.setEmail(request.getParameter("txtemail"));
					boolean retorno = jdbcUsuario.atualizar(usuario);

					if (retorno) {
						msg.put("msg", "Os dados do usuário foram alterado com sucesso.");
					} else {
						msg.put("msg", "Não foi possível alterar os dados do usuário.");
						msg.put("erro", "true");
					}
				} else {
					msg.put("msg", "Senha não corresponde com o cadastro.");
					msg.put("erro", "true");
				}
			} else {	
				msg.put("msg", "Você não pode alterar seu usuário.");
				msg.put("erro", "true");
			}	
			conec.fecharConexao();

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}
}
