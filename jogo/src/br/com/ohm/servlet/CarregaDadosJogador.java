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

import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCClienteDAO;
import br.com.ohm.classes.Cliente;



/**
 * Servlet implementation class CarregaDadosJogador
 */
@WebServlet("/CarregaDadosJogador")
public class CarregaDadosJogador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarregaDadosJogador() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	try {
    		List<Cliente> cliente = new ArrayList<Cliente>();
    		HttpSession sessao = request.getSession();
	    	Conexao conec = new Conexao();
	    	Connection conexao = conec.abrirConexao();
	    	JDBCClienteDAO jdbcCliente = new JDBCClienteDAO(conexao);
	    	cliente = jdbcCliente.buscaDadosClientes(sessao.getAttribute("login").toString());
	    	
	    	String json = new Gson().toJson(cliente);
			System.out.println(json);
			conec.fecharConexao();
	    	response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);    	
    	} catch (IOException e) {
    		// TODO: handle exception
    		
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
