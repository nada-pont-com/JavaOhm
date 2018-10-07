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

import br.com.ohm.classes.Clientes_tem_Maquinas;
import br.com.ohm.classes.Clientes_tem_Pesquisas;
import br.com.ohm.classes.Maquina;
import br.com.ohm.classes.Pesquisa;
import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCClientes_tem_MaquinasDAO;
import br.com.ohm.jdbc.JDBCClientes_tem_PesquisasDAO;
import br.com.ohm.jdbc.JDBCMaquinasDAO;
import br.com.ohm.jdbc.JDBCPesquisasDAO;

/**
 * Servlet implementation class SalvarJogo
 */
@WebServlet("/SalvarJogo")
public class SalvarJogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalvarJogo() {
        super();
        // TODO Auto-generated constructor stub
	}
	
	private void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		String identificador = request.getParameter("identificador");
		String fase = request.getParameter("fase");
		String clienteId = request.getParameter("clienteId");
		Map<String,String> msg = new HashMap<String,String>();
		Conexao conec = new Conexao();
		Connection conexao = conec.abrirConexao();
		switch (identificador) {
			case "maquinas":
				List<Clientes_tem_Maquinas> listadeMaquinasDoCliente = new ArrayList<Clientes_tem_Maquinas>();
				List<Maquina> listadeMaquinas = new ArrayList<Maquina>();
				JDBCMaquinasDAO jdbcMaquinas = new JDBCMaquinasDAO(conexao);
				JDBCClientes_tem_MaquinasDAO jdbcClientes_tem_Maquinas = new JDBCClientes_tem_MaquinasDAO(conexao);
				Clientes_tem_Maquinas clientes_tem_Maquinas = null;
				listadeMaquinas = jdbcMaquinas.buscaMaquinas(fase);
				for(int i = 1;i<listadeMaquinas.size();i++){
					clientes_tem_Maquinas = new Clientes_tem_Maquinas();
					clientes_tem_Maquinas.setMaquinas_id(Integer.parseInt(request.getParameter("id"+i)));
					clientes_tem_Maquinas.setMultiplicador(Integer.parseInt(request.getParameter("multiplicador"+i)));
					clientes_tem_Maquinas.setQuantidade(Integer.parseInt(request.getParameter("quantidade"+i)));

					listadeMaquinasDoCliente.add(clientes_tem_Maquinas);
				}
				boolean retorno = jdbcClientes_tem_Maquinas.salvarMaquinas(listadeMaquinasDoCliente,clienteId);
				if(retorno){
					msg.put("msg", "Maquinas salvas com sucesso");
				}
			break;
			case "pesquisas":
				List<Clientes_tem_Pesquisas> listadePesquisasDoCliente = new ArrayList<Clientes_tem_Pesquisas>();
				List<Pesquisa> listadePesquisas = new ArrayList<Pesquisa>();
				JDBCPesquisasDAO jdbcPesquisas = new JDBCPesquisasDAO(conexao);
				JDBCClientes_tem_PesquisasDAO jdbcClientes_tem_Pesquisas = new JDBCClientes_tem_PesquisasDAO(conexao);
				Clientes_tem_Pesquisas clientes_tem_Pesquisas = null;
				listadePesquisas = jdbcPesquisas.buscaPesquisas(fase);
				for(int i = 0;i<listadePesquisas.size();i++){

				}
			break;
			case "baterias":
				
			break;
			case "cliente":
				
			break;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		proccess(request,response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		proccess(request, response);
	}

}
