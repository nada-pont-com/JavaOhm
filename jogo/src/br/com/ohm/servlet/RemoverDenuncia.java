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
import br.com.ohm.jdbc.JDBCDenunciaDAO;

/**
 * Servlet implementation class RemoverDenuncia
 */
@WebServlet("/RemoverDenuncia")
public class RemoverDenuncia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoverDenuncia() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		// TODO Auto-generated method stub
    	int id = Integer.parseInt(request.getParameter("id"));
		
		Conexao conec = new Conexao();
		Connection conexao = conec.abrirConexao();
		JDBCDenunciaDAO jdbcDenuncia = new JDBCDenunciaDAO(conexao);
		boolean retorno = jdbcDenuncia.removerDenunciaPorId(id);
		conec.fecharConexao();
		
		Map<String, String>msg = new HashMap<String, String>();
		if(retorno){
			msg.put("msg", "Denuncia removida com sucesso.");
		}else{
			msg.put("msg", "Não foi possível remover a denuncia.");
		}
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
