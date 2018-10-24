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

import com.google.gson.Gson;

import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCUsuarioDAO;
import br.com.ohm.classes.Usuario;

/**
 * Servlet implementation class buscaLogin
 */
@WebServlet("/BuscaUsuarios")
public class BuscaUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscaUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }
    //Utilizar no busca do ADM
    private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
    	List<Usuario> usuarios = new ArrayList<Usuario>();
    	try {
	    	Conexao conec = new Conexao();
	    	Connection conexao = conec.abrirConexao();
	    	JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
	    	usuarios = jdbcUsuario.buscaPorLogin(request.getParameter("busca").toString());
	    	conec.fecharConexao();
	    	
	    	String json = new Gson().toJson(usuarios);
    		response.setContentType("application/json");
    		response.setCharacterEncoding("UTF-8");
    		response.getWriter().write(json);
    	} catch (IOException e){
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
