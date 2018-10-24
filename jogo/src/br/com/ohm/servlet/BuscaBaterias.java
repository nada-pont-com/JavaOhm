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

import br.com.ohm.classes.Bateria;
import br.com.ohm.classes.Clientes_tem_Baterias;
import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCBateriasDAO;
import br.com.ohm.jdbc.JDBCClientes_tem_BateriasDAO;

/**
 * Servlet implementation class Baterias
 */
@WebServlet("/BuscaBaterias")
public class BuscaBaterias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscaBaterias() {
        super();
        // TODO Auto-generated constructor stub
    }

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		// TODO Auto-generated method stub
		String fase = request.getParameter("fase");
		int fase1 = Integer.parseInt(fase);
		String clienteId = request.getParameter("id");
		List<Bateria> listaDeBaterias = new ArrayList<Bateria>();
		List<Clientes_tem_Baterias> listaDeClientesTemBaterias = new ArrayList<Clientes_tem_Baterias>();
		List<Object> Object = new ArrayList<Object>();
		try {
			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCBateriasDAO jdbcBaterias = new JDBCBateriasDAO(conexao);
			JDBCClientes_tem_BateriasDAO jdbcClientes_tem_Baterias = new JDBCClientes_tem_BateriasDAO(conexao);
			
			listaDeBaterias = jdbcBaterias.BuscaBaterias(fase);
			listaDeClientesTemBaterias = jdbcClientes_tem_Baterias.clientesProcuramBaterias(clienteId);
			String json = "";
			List<Bateria> baterias2 = null;
			for(int i = 0;i<listaDeClientesTemBaterias.size();i++){
				baterias2 = new ArrayList<Bateria>();
				baterias2 = jdbcBaterias.BuscaBateriasPorId(listaDeClientesTemBaterias.get(i).getBateriasId());
				System.out.println("get: "+baterias2.get(0).getFase());
				if(baterias2.get(0).getFase()!=fase1) {
					//remover baterias da fase anterior
					jdbcClientes_tem_Baterias.deletarBaterias(baterias2.get(0).getId(), clienteId);
				}
			}

			listaDeClientesTemBaterias = jdbcClientes_tem_Baterias.clientesProcuramBaterias(clienteId);
			
			if(listaDeClientesTemBaterias.size()==0) {
				boolean retorno = jdbcClientes_tem_Baterias.inserirBateriasRespectivasDaFaseDoJogador(clienteId, listaDeBaterias);
				if(retorno) {
					listaDeClientesTemBaterias = jdbcClientes_tem_Baterias.clientesProcuramBaterias(clienteId);
					Object.add(listaDeClientesTemBaterias);
					Object.add(listaDeBaterias);
					json = new Gson().toJson(Object);
				}else {
					Map<String, String> msg = new HashMap<String,String>();
					msg.put("msg", "Erro ao carregar baterias");
					json = new Gson().toJson(msg);
				}
			}else {
				Object.add(listaDeClientesTemBaterias);
				Object.add(listaDeBaterias);
				json = new Gson().toJson(Object);
			}
			conec.fecharConexao();

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO: handle exception
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}

}

