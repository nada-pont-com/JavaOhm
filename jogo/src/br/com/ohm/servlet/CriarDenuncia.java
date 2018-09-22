package br.com.ohm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.ohm.classes.Cliente;
import br.com.ohm.classes.Denuncia;
import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCClienteDAO;
import br.com.ohm.jdbc.JDBCDenunciaDAO;

/**
 * Servlet implementation class Denunciar
 */
@WebServlet("/CriarDenuncia")
public class CriarDenuncia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriarDenuncia() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	Denuncia denuncia = new Denuncia();
    	List<Cliente> clientes = new ArrayList<Cliente>();
    	denuncia.setLoginDenuncia(request.getParameter("login"));
    	denuncia.setDenuncia(request.getParameter("denuncia"));
    	try {
	    	Conexao conec = new Conexao();
	    	Connection conexao = conec.abrirConexao();
	    	JDBCDenunciaDAO jdbcDenuncia = new JDBCDenunciaDAO(conexao);
	    	JDBCClienteDAO jdbcCliente = new JDBCClienteDAO(conexao);
	    	clientes = jdbcCliente.buscaDadosClientes(request.getParameter("loginD"));
	    	boolean retorno = jdbcDenuncia.inserirDenuncia(denuncia,clientes);
	    	
	    	Map<String, String> msg = new HashMap<String, String>();
	    	if(retorno) {
		    		
	    	}else {
	    		
	    	}
	    	conec.fecharConexao();
	    	
	    	String json = new Gson().toJson(msg);
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
