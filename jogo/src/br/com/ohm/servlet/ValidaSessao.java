package br.com.ohm.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.com.ohm.classes.Usuario;
import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCUsuarioDAO;

/**
 * Servlet implementation class ValidaSessao
 */
@WebServlet("/ValidaSessao")
public class ValidaSessao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidaSessao() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	// TODO Auto-generated method stub
    	try {
			HttpSession sessao = request.getSession();
			if(request.getParameter("p").equals(sessao.getAttribute("permissao"))) {
				System.out.println("ok");
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			Usuario usuario = jdbcUsuario.buscaLogin(sessao.getAttribute("login").toString());
			
			conec.fecharConexao();
			String json = new Gson().toJson(usuario);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			}else {
				System.out.println("Erro "+request.getParameter("p")+" sessao:"+sessao.getAttribute("permissao"));
			}
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
