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

import com.google.gson.Gson;

import br.com.ohm.classes.Cliente;
import br.com.ohm.classes.Usuario;
import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCUsuarioDAO;
import br.com.ohm.jdbc.JDBCClienteDAO;
import br.com.ohm.jdbc.JDBCDenunciaDAO;

/**
 * Servlet implementation class RemoverConta
 */
@WebServlet("/RemoverConta")
public class RemoverConta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoverConta() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		// TODO Auto-generated method stub
    	int id = Integer.parseInt(request.getParameter("login"));
		
		Conexao conec = new Conexao();
		Connection conexao = conec.abrirConexao();
		JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
		JDBCClienteDAO jdbcCliente = new JDBCClienteDAO(conexao);
		JDBCDenunciaDAO jdbcDenuncia = new JDBCDenunciaDAO(conexao);
		Cliente login = jdbcCliente.buscaClientePorId(id);
		Usuario usuario = jdbcUsuario.buscaLogin(login.getLogin());
		Map<String, String>msg = new HashMap<String, String>();
		boolean delDen = jdbcDenuncia.removerDenunciaPorCliente(login, usuario);
		if(delDen){
			boolean delCli = jdbcCliente.removerCliente(login, usuario);
			if(delCli){
				boolean delUsu = jdbcUsuario.remover(login.getLogin(), usuario);
				if(delUsu){
					msg.put("msg", "Conta apagada com sucesso.");
				}else if(!delUsu){
					msg.put("msg", "Não foi possível deletar a conta do usuario.");
				}
			}else if(!delCli){
				msg.put("msg", "Não foi possível deletar a conta do jogador.");
			}
		}else if(!delDen){
			msg.put("msg", "Não foi possível deletar a denuncia.");
		}
		
		
		conec.fecharConexao();
	
		String json = new Gson().toJson(msg);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
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
		process(request, response);
	}

}
