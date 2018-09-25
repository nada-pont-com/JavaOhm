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

import br.com.ohm.classes.Usuario;
import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCClienteDAO;
import br.com.ohm.jdbc.JDBCClientes_tem_MaquinasDAO;
import br.com.ohm.jdbc.JDBCUsuarioDAO;

/**
 * Servlet implementation class CadastroUsuario
 */
@WebServlet("/CadastroUsuario")
public class CadastroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	Usuario usuario = new Usuario();
    	try {
    		
			usuario.setLogin(request.getParameter("txtlogin"));
			usuario.setSenha(request.getParameter("pwdsenha"));
			usuario.setNome(request.getParameter("txtnome"));
			usuario.setEmail(request.getParameter("txtemail"));
			usuario.setUltimoAcesso(usuario.ultimoAcesso());
			usuario.setPermissao(request.getParameter("txtpermissao"));
			usuario.setNascimento(request.getParameter("txtnascimento"));
			usuario.setPermissao(usuario.verificaPermissao(request.getParameter("adm")));
			System.out.println(" "+usuario.getLogin()+" "+usuario.getNome()+" "+usuario.getNascimento()+" "+usuario.getPermissao()+" "+usuario.getSenha());
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			JDBCClienteDAO jdbcCliente = new JDBCClienteDAO(conexao);
			JDBCClientes_tem_MaquinasDAO jdbcClientes_tem_Maquinas = new JDBCClientes_tem_MaquinasDAO(conexao);
			Usuario usuariobd = jdbcUsuario.buscaLogin(usuario.getLogin());
			String validaEmail = jdbcUsuario.validaEmail(usuario.getEmail());
			Map<String,String> msg = new HashMap<String,String>();
			if(!validaEmail.equals("")) {
				msg.put("msg", "Email já utilizado.");
			}else if(usuario.getLogin().equals(usuariobd.getLogin())) {
				msg.put("msg", "Login já utilizado.");
			}else {
				boolean retorno = jdbcUsuario.inserir(usuario);
				if(retorno) {
					boolean inserirCliente = jdbcCliente.inserirCliente(usuario.getLogin());
					if(inserirCliente) {
						boolean inserirMaquinaEspecial = jdbcClientes_tem_Maquinas.inserirMaquinaEspecial(jdbcCliente.buscaDadosClientes(usuario.getLogin()).get(0).getId());
						if(inserirMaquinaEspecial) {							
							msg.put("msg", "Cadastro efetuado com sucesso!");						
						}else {
							msg.put("msg", "Erro ao efetuar o cadastro!");
							//jdbcUsuario.remover(usuario.getLogin(), usuario);
						}
					}else {
						msg.put("msg", "Erro ao efetuar o cadastro!");
						jdbcUsuario.remover(usuario.getLogin(), usuario);
					}
				}else {
					msg.put("msg", "Erro ao efetuar o cadastro!");
				}
			}
			String json = new Gson().toJson(msg);
			conec.fecharConexao();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			
    	}catch(Exception e) {
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
