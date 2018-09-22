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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.com.ohm.classes.Cripitografia;
import br.com.ohm.classes.Usuario;
import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCUsuarioDAO;

/**
 * Servlet implementation class Entrar
 */
@WebServlet("/Entrar")
public class Entrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Entrar() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	// TODO Auto-generated method stub
    	try {
    		System.out.println("teste");
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			Usuario usuario = jdbcUsuario.buscaLogin(request.getParameter("txtlogin"));
			Map<String,String> msg = new HashMap<String,String>();
			System.out.println(usuario.getLogin());
			System.out.println(request.getParameter("txtlogin"));
			
			if(request.getParameter("txtlogin").equals(usuario.getLogin())) {
				System.out.println("teste2");
				String senhaCripitografada = Cripitografia.criptografaSenha(request.getParameter("pwdsenha").toString());
				if(senhaCripitografada.equals(usuario.getSenha())){
					HttpSession sessao = request.getSession();
					System.out.println("teste3");
					
					sessao.setAttribute("login", usuario.getLogin());
					sessao.setAttribute("permissao", usuario.getPermissao());
					
					if(sessao.getAttribute("permissao").equals("2")) {
						msg.put("url", "pags/adm/index.html");
					}else {
						msg.put("url", "pags/jogador/index.html");
					}
				}else {
					System.out.println("teste4");
					msg.put("msg", "Senha incorreta.");
				}
			}else {
				msg.put("msg", "Login não existe.");
			}
			
			String json = new Gson().toJson(msg);
			System.out.println(json);
			conec.fecharConexao();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
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
