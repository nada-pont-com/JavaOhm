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

import br.com.ohm.classes.Clientes_tem_Maquinas;
import br.com.ohm.classes.Maquina;
import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCClientes_tem_MaquinasDAO;
import br.com.ohm.jdbc.JDBCMaquinasDAO;

/**
 * Servlet implementation class Maquinas
 */
@WebServlet("/BuscaMaquinas")
public class BuscaMaquinas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscaMaquinas() {
        super();
        // TODO Auto-generated constructor stub
    }

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fase = request.getParameter("fase");
		String clienteId = request.getParameter("id");
		int fase1 = Integer.parseInt(fase);
		System.out.println(fase);
		System.out.println(clienteId);
		List<Maquina> maquinas = new ArrayList<Maquina>();
		List<Clientes_tem_Maquinas> clientes_tem_maquinas = new ArrayList<Clientes_tem_Maquinas>();
		List<Object> Object = new ArrayList<Object>();
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMaquinasDAO jdbcMaquinas = new JDBCMaquinasDAO(conexao);
			maquinas = jdbcMaquinas.buscaMaquinas(fase);
			JDBCClientes_tem_MaquinasDAO jdbcClientes_tem_Maquinas = new JDBCClientes_tem_MaquinasDAO(conexao);
			for(int i = 1;i<maquinas.size();i++){
				if(((fase1-1)!=0)&& (maquinas.get(i).getFase()!=fase1)&&(maquinas.get(i).getSubFase()!=fase1)) {
					//remover maquinas da fase anterior
					jdbcClientes_tem_Maquinas.deletarMaquinas(maquinas.get(i).getId(), clienteId);
				}
			}
			clientes_tem_maquinas = jdbcClientes_tem_Maquinas.clientesProcuramMaquinas(clienteId);				
			String json = "";
			System.out.println();
			if(clientes_tem_maquinas.size()==1) {				
				boolean retorno= jdbcClientes_tem_Maquinas.inserirMaquinasRespectivasFaseDoJogador(clienteId, maquinas);
				if(retorno) {
					clientes_tem_maquinas = jdbcClientes_tem_Maquinas.clientesProcuramMaquinas(clienteId);
					Object.add(clientes_tem_maquinas);
					Object.add(maquinas);
					json = new Gson().toJson(Object);
				}else {
					Map<String,String> msg = new HashMap<String,String>();
					msg.put("msg", "Erro ao carregar o maquinas");
					json = new Gson().toJson(msg);
				}
			}else {
				Object.add(clientes_tem_maquinas);
				Object.add(maquinas);
				json = new Gson().toJson(Object);
			}
			conec.fecharConexao();
			System.out.println(json+" ola");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
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
