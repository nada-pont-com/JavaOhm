package br.com.ohm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.com.ohm.classes.Cliente;
import br.com.ohm.classes.Usuario;
import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCClienteDAO;
import br.com.ohm.jdbc.JDBCUsuarioDAO;

/**
 * Servlet implementation class BuscaUsuario
 */
@WebServlet("/BuscaUsuario")
public class BuscaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscaUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	List<Object> objetos = new ArrayList<Object>();
    	try {
    		Conexao conec = new Conexao();
    		Connection conexao = conec.abrirConexao();
    		JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			JDBCClienteDAO jdbcCliente = new JDBCClienteDAO(conexao);
			String login = request.getParameter("login");
			Usuario usuario = new Usuario();
			if(login == null) {
				HttpSession sessao = request.getSession();
				usuario = jdbcUsuario.buscaLogin(sessao.getAttribute("login").toString());				
			}else {
				usuario = jdbcUsuario.buscaLogin(login);
			}
    		objetos.add(usuario);
    		if(usuario.getPermissao().equals("1")) {
    			
    			List<Cliente> clientes = jdbcCliente.buscaDadosClientes(usuario.getLogin());
    			Cliente cliente = clientes.get(0);
    			objetos.add(cliente);
    		}
    		response.setContentType("application/json");
    		response.setCharacterEncoding("UTF-8");
    		response.getWriter().write(new Gson().toJson(objetos));
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
		process(request, response);
	}

}
