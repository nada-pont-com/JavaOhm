package br.com.ohm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;

import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCClienteDAO;
import br.com.ohm.jdbc.JDBCDenunciaDAO;
import br.com.ohm.classes.Cliente;
import br.com.ohm.classes.Denuncia;

/**
 * Servlet implementation class VisualizarDenuncia
 */
@WebServlet("/VisualizarDenuncia")
public class VisualizarDenuncia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizarDenuncia() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
    	try {
    		List<Object> objetos = new ArrayList<Object>();
	    	List<Denuncia> denuncias = new ArrayList<Denuncia>();
	    	List<Cliente> clientes = new ArrayList<Cliente>();
	    	List<Denuncia> denuncias2 = new ArrayList<Denuncia>();
	    	List<Cliente> clientes2 = new ArrayList<Cliente>();
	    	Conexao conec = new Conexao();
	    	Connection conexao = conec.abrirConexao();
	    	JDBCDenunciaDAO jdbcDenuncia = new JDBCDenunciaDAO(conexao);
	    	JDBCClienteDAO jdbcCliente = new JDBCClienteDAO(conexao);
	    	denuncias = jdbcDenuncia.buscaDenuncia();
	    	clientes = jdbcCliente.buscaDadosClientes("");
	    	Cliente cliente = null;
	    	for (int i = 0; i < denuncias.size(); i++) {
				for (int j = 0; j < clientes.size(); j++) {
					if(denuncias.get(i).getClientesId()==clientes.get(j).getId()) {
						cliente = new Cliente();
						cliente  = (clientes.get(j));
						denuncias2.add(denuncias.get(i));
						clientes2.add(cliente);
					}
				}
			}
	    	objetos.add(clientes2);
	    	objetos.add(denuncias2);
	    	conec.fecharConexao();
	    	
	    	String json = new Gson().toJson(objetos);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
    	}catch(IOException e){
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
