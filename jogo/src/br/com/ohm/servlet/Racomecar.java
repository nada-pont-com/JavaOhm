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
import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCClienteDAO;
import br.com.ohm.jdbc.JDBCClientes_tem_BateriasDAO;
import br.com.ohm.jdbc.JDBCClientes_tem_MaquinasDAO;
import br.com.ohm.jdbc.JDBCClientes_tem_PesquisasDAO;

/**
* Servlet implementation class Racomecar
*/
@WebServlet("/Racomecar")
public class Racomecar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	* @see HttpServlet#HttpServlet()
	*/
	public Racomecar() {
		super();
		// TODO Auto-generated constructor stub
	}
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCClienteDAO jdbcCliente = new JDBCClienteDAO(conexao);
			JDBCClientes_tem_MaquinasDAO jdbcClientes_tem_Maquinas = new JDBCClientes_tem_MaquinasDAO(conexao);
			JDBCClientes_tem_BateriasDAO jdbcClientes_tem_Baterias = new JDBCClientes_tem_BateriasDAO(conexao);
			JDBCClientes_tem_PesquisasDAO jdbcClientes_tem_Pesquisas = new JDBCClientes_tem_PesquisasDAO(conexao);			
			HttpSession sessao = request.getSession();
			String login = sessao.getAttribute("login").toString();
			Cliente cliente = new Cliente();
			List<Cliente> listaDeClientes = new ArrayList<Cliente>();
			listaDeClientes =  jdbcCliente.buscaDadosClientes(login);
			cliente = listaDeClientes.get(0);
			cliente.setDinheiro(10);
			cliente.setDinheiroGeral(10);
			cliente.setEnergia(0);
			cliente.setFase(1);
			cliente.setTempo("2000-01-01 00:00:00");
			jdbcCliente.salvarCliente(cliente);
			jdbcClientes_tem_Maquinas.resetarMaquinas(cliente.getId());
			jdbcClientes_tem_Baterias.resetarBaterias(cliente.getId());
			jdbcClientes_tem_Pesquisas.resetarPesquisas(cliente.getId());
			String json = new Gson().toJson(cliente);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (IOException e) {
			//TODO: handle exception
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
