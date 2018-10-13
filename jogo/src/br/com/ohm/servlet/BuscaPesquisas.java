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

import br.com.ohm.classes.Clientes_tem_Pesquisas;
import br.com.ohm.classes.Pesquisa;
import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCClientes_tem_PesquisasDAO;
import br.com.ohm.jdbc.JDBCPesquisasDAO;

/**
 * Servlet implementation class BuscaPesquisas
 */
@WebServlet("/BuscaPesquisas")
public class BuscaPesquisas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscaPesquisas() {
        super();
        // TODO Auto-generated constructor stub
    }

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String fase = request.getParameter("fase");
		int fase1 = Integer.parseInt(fase);
		String clienteId = request.getParameter("id");
		List<Pesquisa> listaDePesquisas = new ArrayList<Pesquisa>();
		List<Clientes_tem_Pesquisas> listaDePesquisasDoCliente = new ArrayList<Clientes_tem_Pesquisas>();
		List<Object> Object = new ArrayList<Object>();
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCPesquisasDAO jdbcPesquisas = new JDBCPesquisasDAO(conexao);
			JDBCClientes_tem_PesquisasDAO jdbcClientes_tem_Pesquisas = new JDBCClientes_tem_PesquisasDAO(conexao);
			listaDePesquisas =  jdbcPesquisas.buscaPesquisas(fase);
			listaDePesquisasDoCliente = jdbcClientes_tem_Pesquisas.buscaPesquisasDosClientes(clienteId);
			System.out.println(listaDePesquisasDoCliente.size()+"ola");
			String json = "";
			int size = listaDePesquisasDoCliente.size();
			List<Pesquisa> pesquisa2 = null;
			
			for(int i = 0;i<listaDePesquisasDoCliente.size();i++){
				pesquisa2 = new ArrayList<Pesquisa>();
				pesquisa2 = jdbcPesquisas.buscaPesquisasPorId(listaDePesquisasDoCliente.get(i).getPesquisas_id());
				if((pesquisa2.get(0).getFase()!=fase1)) {
					//remover maquinas da fase anterior
					if((listaDePesquisasDoCliente.get(i).getEstado()).equals("finalizada")){
						jdbcClientes_tem_Pesquisas.deletarPesquisa(pesquisa2.get(0).getId(), clienteId);
					}
				}
			}
			System.out.println(listaDePesquisas.size()+"list");
			System.out.println(listaDePesquisasDoCliente.size()+"ola2");
			listaDePesquisasDoCliente = jdbcClientes_tem_Pesquisas.buscaPesquisasDosClientes(clienteId);
			if(listaDePesquisasDoCliente.size()!=listaDePesquisas.size()){
				listaDePesquisas = new ArrayList<Pesquisa>();
				for(int i = 0;i<listaDePesquisasDoCliente.size();i++){
					pesquisa2 = new ArrayList<Pesquisa>();
					pesquisa2 = jdbcPesquisas.buscaPesquisasPorId(listaDePesquisasDoCliente.get(i).getPesquisas_id());
					listaDePesquisas.add(pesquisa2.get(0));
				}
			}
			System.out.println(listaDePesquisas.size()+"list2");

			if(listaDePesquisasDoCliente.size()!=size){
				boolean retorno = jdbcClientes_tem_Pesquisas.inserirPesquisas(clienteId,listaDePesquisas);
				if(retorno){
					listaDePesquisasDoCliente = jdbcClientes_tem_Pesquisas.buscaPesquisasDosClientes(clienteId);
					Object.add(listaDePesquisas);
					Object.add(listaDePesquisasDoCliente);
					json = new Gson().toJson(Object);
				}else{
					Map<String,String> msg = new  HashMap<String,String>();
					msg.put("msg", "Erro oa carregar pesquisas!");
					json = new Gson().toJson(msg);
				}
			}else{
				Object.add(listaDePesquisas);
				Object.add(listaDePesquisasDoCliente);
				json = new Gson().toJson(Object);
			}
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}

}
