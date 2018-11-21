package br.com.ohm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCUsuarioDAO;

/**
 * Servlet implementation class VerificaAcessos
 */
@WebServlet("/VerificaAcessos")
public class VerificaAcessos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			int quant = jdbcUsuario.buscaAcessos();
			conec.fecharConexao();
			Map<String,String> msg = new HashMap<String,String>();
			if(quant==-1){
				msg.put("msg", "Erro ao cerregar acessos");
			}else{
				msg.put("quant", ""+quant);
			}
			String json = new Gson().toJson(msg);
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
