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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.com.ohm.classes.Cliente;
import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCClienteDAO;
import br.com.ohm.jdbc.JDBCClientes_tem_BateriasDAO;
import br.com.ohm.jdbc.JDBCClientes_tem_MaquinasDAO;
import br.com.ohm.jdbc.JDBCClientes_tem_PesquisasDAO;

/**
 * Servlet implementation class ResetarJogo
 */
@WebServlet("/ResetarJogo")
public class ResetarJogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetarJogo() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
    	
    	List<Cliente> cliente = new ArrayList<Cliente>();
		HttpSession sessao = request.getSession();
    	Conexao conec = new Conexao();
    	Connection conexao = conec.abrirConexao();
		JDBCClienteDAO jdbcCliente = new JDBCClienteDAO(conexao);
		JDBCClientes_tem_BateriasDAO jdbcBaterias = new JDBCClientes_tem_BateriasDAO(conexao);
		JDBCClientes_tem_MaquinasDAO jdbcMaquinas = new JDBCClientes_tem_MaquinasDAO(conexao);
		JDBCClientes_tem_PesquisasDAO jdbcPesquisas = new JDBCClientes_tem_PesquisasDAO(conexao);
		
		Map<String, String>msg = new HashMap<String, String>();
		
		String login = request.getParameter("login");
		cliente = jdbcCliente.buscaDadosClientes(login);
		boolean retorno;
		retorno = jdbcPesquisas.resetarPesquisas(cliente.get(0).getId());
		String validador = "";
		if(retorno){
			retorno = jdbcMaquinas.resetarMaquinas(cliente.get(0).getId());
			if(retorno){
				retorno = jdbcBaterias.resetarBaterias(cliente.get(0).getId());
				if(retorno){
					retorno = jdbcCliente.resetarCliente(login);
					if(!retorno){
						validador = "Cliente";
					}
				}else{
					validador = "Baterias";
				}
			}else{
				validador = "Maquinas";
			}
		}else{
			validador = "Pesquisas";
		}
		 
		if(validador.equals("")){
			msg.put("msg", "Jogo resetado com sucesso!");
		}else{
			msg.put("msg", "Não foi possível resetar os jogo referente as "+validador);
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
